package com.ethz.aipupdate.businesslogic;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axis.AxisFault;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.aipupdate.beans.DcMetaDataBean;
import com.ethz.aipupdate.beans.StreamBean;
import com.ethz.aipupdate.db.DaoOracle;
import com.ethz.aipupdate.db.QueueDaoOracle;
import com.ethz.aipupdate.db.TrackingDaoOracle;
import com.ethz.aipupdate.helper.ConfigProperties;
import com.ethz.aipupdate.helper.DataHelper;
import com.ethz.aipupdate.helper.FileOperations;
import com.ethz.aipupdate.helper.MetsParser;
import com.ethz.aipupdate.helper.PdsConnector;
import com.exlibris.dps.IEWebServicesProxy;
import com.exlibris.dps.MetaData;

/**
 * Business domain object that handles all post file update process of previous runs
 * metadata updates, SFTP cleanup, working directory cleanup
 * 
 * @author Lars Haendler
 *
 */
public class PostAipUpdateDomain
{

	private static final Logger logger = LogManager.getLogger();
	
	public final static String RIP_ERROR_NO_ID = "There is no rip with ID";
	public final static String RIP_FINISHED = "Finished";
	public final static String DC_ERROR_NO_CHANGE = "No change in metadata";
	public final static String MD_ERROR_CONTROL_LOCKED = "control is locked";

	private ConfigProperties config;
	private QueueDaoOracle queueDao;
	private IEWebServicesProxy wsProxy;
	private PdsConnector con;
	private Long flag;	
	
	
	/**
	 * Constructor
	 * 
	 * @param conf
	 */
	public PostAipUpdateDomain(ConfigProperties conf)
	{
		config = conf;
		queueDao = new QueueDaoOracle(config);
	}
	
	
	/**
	 * Starter method to initialize all relevant objects
	 * and starts necessary controllers
	 * 
	 */
	public void run()
	{
		logger.debug("Post-Aip update queue - started");
		
		con = new PdsConnector(config.getWebserviceHost(), 
				config.getWebserviceUser(), 
				config.getWebservicePassword(), 
				config.getWebserviceInstitute(), 
				config.getWebserviceCaller());

		wsProxy = new IEWebServicesProxy();		
		
		List<Map<String, String>> currentQueue = queueDao.getPostAipQueue(config.getQueueStatusWaitIe());
		if(currentQueue.size()>0)
		{
			logger.info("Post-Aip update queue size: " + currentQueue.size());
			
			//start meta data update controller
			runDcUpdates(currentQueue);
		}
		else
		{
			logger.info("Nothing found for Post-Aip update");
		}			
		
		logger.debug("Post-Aip update queue - finished");
	}
	
	
	/**
	 * Meta data update controller
	 * 
	 * @param queue
	 */
	public void runDcUpdates(List<Map<String, String>> queue)
	{
		for(Map<String, String> dbRow : queue)
		{
			//if RIP ID OK
			if(isRipFinished(dbRow.get(DaoOracle.SIP_ID)))
			{
				dbRow = DataHelper.updateDataForAipUpdate(dbRow, config);
				
				//get mets IE again
				copyUpdatedRosettaMetsToFileSystem(dbRow);
				
				//extract ie-pid and rep-pid
				MetsParser parser = new MetsParser(config, FileOperations.readoutFile(dbRow.get(DaoOracle.SIP_UPDATE_PATH) + config.getPostaipStorageFilenameMets()));
				String iePid = parser.getRosettaIePidFromDom();
				
				logger.debug("IE: " + iePid + " AMD_ID: " + dbRow.get(DaoOracle.AMD_ID) + ", SIP_NAME: " + dbRow.get(DaoOracle.SIP_NAME));
		
				//add IE number to entity_id in DB
				queueDao.updateEntityId(iePid, dbRow.get(DaoOracle.AMD_ID));
				
				logger.debug("start ie md update");
				
				//ie dc update
				updateIeDc(iePid, dbRow);

				logger.debug("start file md update");
				
				//ie file update
				updateFileDc(dbRow);
				
				//set status to AIP_FINISHED in queue
				queueDao.updateStatus(config.getQueueStatusFinishedAip(), dbRow.get(DaoOracle.AMD_ID));
				
				//set fs status to working directory not clean
				queueDao.updateFsStatus(config.getQueueFsStatusWdNotClean(), dbRow.get(DaoOracle.AMD_ID));
			}
			else
			{
				logger.info("RIPID " + dbRow.get(DaoOracle.SIP_ID) + " not finished");
			}
		}
	}

	
	/**
	 * Run IE meta data updates 
	 * 
	 * @param iePid
	 * @param dbRow
	 */
	private void updateIeDc(String iePid, Map<String, String> dbRow)
	{
		String msgError = "";
		
		try
		{
			wsProxy.updateMD(con.getPdsHandle(), iePid, getMetsDcMetadata(getCurrentIePath(dbRow)));
		}
		catch (RemoteException e)
		{
			if(e instanceof AxisFault){
				msgError = ((AxisFault)e).getFaultString();
				
				//if(msgError.contains(PostAipUpdateDomain.DC_ERROR_NO_CHANGE))
				if(msgError.contains(PostAipUpdateDomain.DC_ERROR_NO_CHANGE))					
				{
					//this error is a "just fine" error
					logger.warn(msgError);
				}
				else if(msgError.contains(PostAipUpdateDomain.MD_ERROR_CONTROL_LOCKED))
				{
					//control locked error
					//maybe NAS error
					queueDao.updateStatus(config.getQueueStatusControlLockedError(), dbRow.get(DaoOracle.AMD_ID));
					logger.warn(msgError);
				}
				else
				{
					//a real webservice error
					queueDao.updateStatus(config.getQueueStatusPostAipError(), dbRow.get(DaoOracle.AMD_ID));
					logger.error("Axis error: " + msgError);
					System.exit(1);
				}
			}
			else
			{
				//any other remote error
				queueDao.updateStatus(config.getQueueStatusPostAipError(), dbRow.get(DaoOracle.AMD_ID));
				logger.error(msgError);
				System.exit(1);
			}
		}

	}
	
	
	/**
	 * Run file meta data update
	 * 
	 * @param dbRow
	 */
	private void updateFileDc(Map<String, String> dbRow)
	{
		//get all files from dspace mets and rosetta mets
		Set<StreamBean> dspaceFiles = getStreamsFromDspaceMets(dbRow);
		Set<StreamBean> rosettaFiles = getStreamsFromRosettaMets(dbRow);

		//initialize parser for dspace mets and rosetta ie mets
		MetsParser dspaceMetsParser = new MetsParser(config, FileOperations.readoutFile(getCurrentIePath(dbRow)));
		MetsParser rosettaMetsParser = new MetsParser(config, FileOperations.readoutFile(dbRow.get(DaoOracle.SIP_UPDATE_PATH) + config.getPostaipStorageFilenameMets()));		
		
		//get list of files to be ignored
		List<String> ignoreFiles = config.getFileDcIgnoredFiles();
		
		//iterate over each file currently in Rosetta
		for(StreamBean rosettaStream : rosettaFiles)
		{
			logger.debug("file check for md update: " + rosettaStream.getFileName());
			
			//ignore files that are supposed to be ignored
			if(ignoreFiles.contains(rosettaStream.getFileName()))
			{
				logger.warn(rosettaStream.getFileName() + " in filedc.ignorefiles");
			}
			else
			{
				//find dsapceStream and get dc
				for(StreamBean dspaceStream : dspaceFiles)
				{
					if(compareAndUpdateFileStreamBeans(dspaceStream, rosettaStream, dspaceMetsParser, rosettaMetsParser, dbRow.get(DaoOracle.AMD_ID)))
					{
						break;
					}
				}
			}
		}		
	}
	
	
	/**
	 * Compare a Rosetta StreamBean with a DSpace StreamBean
	 * When they are equal (in name) the md update is initialized
	 * 
	 * @param dspaceStream
	 * @param rosettaStream
	 * @param dspaceMetsParser
	 * @param rosettaMetsParser
	 * @param amdId
	 * @return boolean
	 */
	private boolean compareAndUpdateFileStreamBeans(StreamBean dspaceStream, StreamBean rosettaStream, MetsParser dspaceMetsParser, MetsParser rosettaMetsParser, String amdId)
	{

		if(dspaceStream.equals(rosettaStream))
		{
			//get meta data list with dspace fid
			logger.debug("fpid: " + dspaceStream.getFilePid());
			List<DcMetaDataBean> dspaceMetaBeans = dspaceMetsParser.getFileDcMetaData(dspaceStream.getFilePid());
			logger.debug("fpid: " + rosettaStream.getFilePid());
			List<DcMetaDataBean> rosettaMetaBeans = rosettaMetsParser.getFileDcMetaData(rosettaStream.getFilePid());
			
			//logger.debug("file: " + dspaceStream.getFileName() + ", dspace fid " + dspaceStream.getFilePid() + ", rosetta fid " + rosettaStream.getFilePid());
			if(!isFileDcMetadataEqual(dspaceMetaBeans, rosettaMetaBeans))
			{
				logger.debug("file md update: " + rosettaStream.getFilePid());
				
				try
				{
					wsProxy.updateMD(con.getPdsHandle(), rosettaStream.getFilePid(), getFileDcMetadata(dspaceMetaBeans));
				}
				catch (RemoteException e)
				{
					//add error status to db
					queueDao.updateStatus(config.getQueueStatusAipError(), amdId);
					//log error depending on 
					if(e instanceof AxisFault){
						logger.error(((AxisFault)e).getFaultString());
					}
					else
					{
						logger.error(e.getMessage());
					}
					//stop
					System.exit(1);
				}
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * Compare if meta in two Lists of DcMetaDataBean has the same content
	 * Currently only the dc:right entry is used for comparison
	 * 
	 * @param dspaceMetaBeans
	 * @param rosettaMetaBeans
	 * @return boolean
	 */
	private boolean isFileDcMetadataEqual(List<DcMetaDataBean> dspaceMetaBeans, List<DcMetaDataBean> rosettaMetaBeans)
	{
		boolean returnVal = false;
		
		String dcRightsRosetta = DataHelper.getValueFromMetaDataBeanList(rosettaMetaBeans, config.getFileDcUpdateNode1());
		String dcRightsDspace = DataHelper.getValueFromMetaDataBeanList(dspaceMetaBeans, config.getFileDcUpdateNode1());

		if(dcRightsDspace.equals(dcRightsRosetta))
		{
			returnVal = true;
		}
		else
		{
			logger.debug("rosetta: " + dcRightsRosetta + " <> dspace: " +dcRightsDspace);
		}
		
		
		return returnVal;
	}
	
	
	/**
	 * Returns whether or not an update identified by RIPID is finished
	 * Caution: the finished status can come in two flaviors
	 *          a real Finished as success message
	 *          an error message telling that the RIPID no longer exists
	 * 
	 * @param rid
	 * @return boolean
	 */
	private boolean isRipFinished(String rid)
	{
		long ripId = Long.parseLong(rid);
		String msgSucces = "";
		String msgError = "";
		
		try
		{
			msgSucces = wsProxy.getRipStatus(con.getPdsHandle(), ripId);
			logger.debug("ripid msg: " + msgSucces);
		}
		catch (RemoteException e)
		{
			if(e instanceof AxisFault){
				
				/*
				an error does not necessarily mean something went wrong
				an error most of the time means that the ripid entry
				has been removed from V2l1_REP00.HRRIPITEM
				terrible webservice coding by EL
				*/
				
				msgError = ((AxisFault)e).getFaultString();
				
				//handling of a good error
				if(DataHelper.startsWithString(msgError, PostAipUpdateDomain.RIP_ERROR_NO_ID))
				{
					logger.warn("webservice error: " + msgError);
				}		
				//handling of the rest
				else
				{
					logger.error("webservice error: " + msgError);
				}
			}	
			else
			{
				logger.error(e.getMessage());
			}
		}
		
		if(DataHelper.startsWithString(msgSucces.toLowerCase(), PostAipUpdateDomain.RIP_FINISHED.toLowerCase()) || 
				DataHelper.startsWithString(msgError, PostAipUpdateDomain.RIP_ERROR_NO_ID))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * Returns a set of StreamBeans from the Dspace mets.xml
	 * 
	 * @param dbRow
	 * @return Set<StreamBean>
	 */
	private Set<StreamBean> getStreamsFromDspaceMets(Map<String, String> dbRow)
	{
		String metsFilePath = dbRow.get(DaoOracle.SIP_UPDATE_PATH) + dbRow.get(DaoOracle.SIP_NAME) + File.separator + config.getSipIePath();
		String filesPath = dbRow.get(DaoOracle.SIP_UPDATE_PATH) + dbRow.get(DaoOracle.SIP_NAME) + File.separator + config.getSipStreamsPath();
		
		String metsContent = FileOperations.readoutFile(metsFilePath);
	
		MetsParser parser = new MetsParser(config, metsContent);
		
		Set<StreamBean> dspaceMetsSet = parser.getFilesFromDspaceMets(filesPath);
		
		return dspaceMetsSet;
	}	
	
	
	/**
	 * Returns a set of StreamBeans from Rosetta PostAip IE mets.xml
	 * 
	 * @param dbRow
	 * @return Set<StreamBean>
	 */
	private Set<StreamBean> getStreamsFromRosettaMets(Map<String, String> dbRow)
	{
		String streamPath = dbRow.get(DaoOracle.SIP_UPDATE_PATH) + config.getPostaipStorageFilenameMets();
		
		MetsParser parser = new MetsParser(config, FileOperations.readoutFile(streamPath));
		
		Set<StreamBean> rosettaMesSet = parser.getFilesFromDspaceMets(streamPath);
				
		return rosettaMesSet;
	}
	
	
	/**
	 * Get Rosetta IE mets.xml from webservice
	 * This will be a different (because updated) version than we already
	 * have from AipUpdateDomain IE mets.xml 
	 * 
	 * @param dbRow
	 */
	private void copyUpdatedRosettaMetsToFileSystem(Map<String, String> dbRow)
	{
		String metsContent = "";
		
		//get IE from SIP_NAME
		TrackingDaoOracle trackingDao = new TrackingDaoOracle(config);
		String ie = trackingDao.getIeFromSipName(dbRow.get(DaoOracle.SIP_NAME));
		
		if(ie.isEmpty())
		{
			logger.error("IE for " + dbRow.get(DaoOracle.SIP_NAME) + " is empty");
			System.exit(1);
		}

		//get mets file content from webservice
		try
		{
			metsContent = wsProxy.getIE(con.getPdsHandle(), ie, flag);
		}
		catch (RemoteException e)
		{
			logger.error(e.getMessage());
			System.exit(1);
		}
		
		//save mets file content to a separate file to allow debug (but only if sftp is active)
		FileOperations.createNewFileWithContent(dbRow.get(DaoOracle.SIP_UPDATE_PATH) + config.getPostaipStorageFilenameMets(), metsContent);
	}		
	
	
	/**
	 * Build and return a IE DC meta data MetaData[] array 
	 * that can be used for webservice meta data updates
	 * 
	 * @param metsFilePath
	 * @return
	 */
	private MetaData[] getMetsDcMetadata(String metsFilePath)
	{
		//setup of mets file parser
		String metsContent = FileOperations.readoutFile(metsFilePath);
		MetsParser parser = new MetsParser(config, metsContent);
		
		//get Metadata beans
		List<DcMetaDataBean> dcBeanList = parser.getIeDcMetaData();
		
		StringBuilder sb = new StringBuilder(2000);
		sb.append(System.getProperty("line.separator"));
		sb.append(config.getDcUpdateHeader());
		sb.append(System.getProperty("line.separator"));
		sb.append(DataHelper.buildMetaDataContent(dcBeanList));
		sb.append(config.getDcUpdateFooter());
				
		//instantiate with correct size
		MetaData[] md = new MetaData[1];
		
		md[0] = new MetaData();
		md[0].setType(config.getDcUpdateIeType());
		md[0].setSubType(config.getDcUpdateIeSubtype());
		md[0].setContent(sb.toString());		
		//do not set MID
		
		return md;
	}
	

	/**
	 * Build and return a file DC meta data MetaData[] array 
	 * that can be used for webservice meta data updates
	 * 
	 * @param metaDataBeans
	 * @return
	 */
	private MetaData[] getFileDcMetadata(List<DcMetaDataBean> metaDataBeans)
	{
		StringBuilder sb = new StringBuilder(2000);
		sb.append(System.getProperty("line.separator"));
		sb.append(config.getDcUpdateHeader());
		sb.append(System.getProperty("line.separator"));
		sb.append(DataHelper.buildMetaDataContent(metaDataBeans));
		sb.append(config.getDcUpdateFooter());
				
		//instantiate with correct size
		MetaData[] md = new MetaData[1];
		
		md[0] = new MetaData();
		md[0].setType(config.getDcUpdateIeType());
		md[0].setSubType(config.getDcUpdateIeSubtype());
		md[0].setContent(sb.toString());		
		//do not set MID
		
		return md;
	}	


	/**
	 * Returns the IE mets.xml path for the current item
	 * 
	 * @param dbRow
	 * @return
	 */
	private String getCurrentIePath(Map<String, String> dbRow)
	{
		return dbRow.get(DaoOracle.SIP_UPDATE_PATH) + dbRow.get(DaoOracle.SIP_NAME) + File.separator + config.getSipIePath();
	}
	

}
