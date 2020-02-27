package com.ethz.submissionds.businesslogic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.submissionds.beans.StreamBean;
import com.ethz.submissionds.db.DaoOracle;
import com.ethz.submissionds.db.QueueDaoOracle;
import com.ethz.submissionds.db.TrackingDaoOracle;
import com.ethz.submissionds.helper.AipUpdater;
import com.ethz.submissionds.helper.ConfigProperties;
import com.ethz.submissionds.helper.DataHelper;
import com.ethz.submissionds.helper.FileOperations;
import com.ethz.submissionds.helper.MetsParser;
import com.ethz.submissionds.helper.PdsConnector;
import com.ethz.submissionds.helper.SftpHelper;
import com.exlibris.dps.IEWebServicesProxy;
import com.exlibris.dps.IEWebServices_PortType;
import com.exlibris.dps.IEWebServices_ServiceLocator;

/**
 * Business domain class that handles the file update process of already existing
 * items in Rosetta
 * 
 * @author Lars Haendler
 *
 */
public class AipUpdateDomain
{
	private static final Logger logger = LogManager.getLogger();

	private ConfigProperties config;
	private QueueDaoOracle queueDao;
	private IEWebServicesProxy wsProxy;
	private PdsConnector con;
	private Long flag;
	
	public static final String ROSETTA_SET = "ROSETTA_SET";
	public static final String INPUT_SET = "INPUT_SET";
	public static final String NEW_FILES = "NEW_FILES";
	public static final String UPDATE_FILES = "UPDATE_FILES";
	public static final String DELETE_FILES = "DELETE_FILES";
	

	/**
	 * Constructor
	 * 
	 * sets up ConfigProperties object and initiates the class variables for
	 * QueueDaoOracle and TrackingDaoOracle
	 * 
	 * @param conf
	 */
	public AipUpdateDomain(ConfigProperties conf)
	{
		config = conf;
		queueDao = new QueueDaoOracle(config);
	}

	
	/**
	 * main controller method for the DomainUpdated object
	 * 
	 */
	public void run()
	{
		logger.debug("Aip update queue - started");

		con = new PdsConnector(config.getWebserviceHost(), 
										config.getWebserviceUser(), 
										config.getWebservicePassword(), 
										config.getWebserviceInstitute(), 
										config.getWebserviceCaller());
		
		flag = new Long(new Integer(0));
		wsProxy = new IEWebServicesProxy();

		List<Map<String, String>> currentQueue = queueDao.getUpdatedQueue(config.getMaxQueueRecordsUpdate());
		if(currentQueue.size()>0)
		{
			logger.info("Aip update queue size: " + currentQueue.size());
			
			processCurrentQueue(currentQueue);
		}
		else
		{
			logger.info("Nothing found for Aip update");
		}		
		
		logger.debug("Aip update queue - finished");
	}
	
	
	/**
	 * query controller method that does the heavy lifting
	 * 
	 * @param queue
	 */
	private void processCurrentQueue(List<Map<String, String>> queue)
	{
		for(Map<String, String> dbRow : queue)
		{
			//update data from db for further use
			dbRow = DataHelper.updateDataForAipUpdate(dbRow, config);			
			
			//copy from sftp to local filesystem
			logger.info("sftp " + dbRow.get(DaoOracle.SFTP_SOURCE) + " " + dbRow.get(DaoOracle.SIP_UPDATE_PATH));
			copyFromSftpLocation(dbRow);
			
			//create a physical copy of Rosetta mets.xml for reuse
			copyRosettaMetsToFileSystem(dbRow);
			
			//extract ie-pid and rep-pid
			MetsParser parser = new MetsParser(config, FileOperations.readoutFile(dbRow.get(DaoOracle.SIP_UPDATE_PATH) + config.getStorageFilenameMets()));
			String iePid = parser.getRosettaIePidFromDom();
			String repPid = parser.getRosettaRepPidFromDom();				
			
			//get all files from copied SIP
			Set<StreamBean> inputStreams = getStreamsFromFiles(dbRow);
			
			//get all files from current IE in Rosetta
			Set<StreamBean> rosettaStreams = getStreamsFromRosetta(dbRow);

			//removing all StreamBean that have not been changed to reduce the workload later on
			Map<String, Set<StreamBean>> cleanedSets = removeUnchangedStreamBeans(rosettaStreams, inputStreams);
			
			//overwrite the current set with the clean ups
			rosettaStreams = cleanedSets.get(ROSETTA_SET);
			inputStreams = cleanedSets.get(INPUT_SET);
			
			//get final map of file to be updated
			Map<String, Set<StreamBean>> filesForAip = getChangedFilesMap(rosettaStreams, inputStreams);
			
			//file updates
			if(isStreamSetEmpty(filesForAip))
			{
				//flag record in DB with error, do warn output and jump to next record in current queue
				queueDao.updateStatus(config.getQueueStatusAipError(), dbRow.get(DaoOracle.AMD_ID));
				logger.warn("amdid: " + dbRow.get(DaoOracle.AMD_ID) + ", sip-name: " + dbRow.get(DaoOracle.SIP_NAME) + " contains no file updates");
				continue;
			}
			else
			{
				logger.debug("iePid: " + iePid + ", repPid: " + repPid + ", " + DaoOracle.WORKFLOW_EXECUTION_ID + ": " + dbRow.get(DaoOracle.WORKFLOW_EXECUTION_ID));
				
				//initialize RIP id
				long ripId = -1;
				
				//start actual update process
				AipUpdater aipUpdater = new AipUpdater(config, wsProxy, con, queueDao, dbRow.get(DaoOracle.AMD_ID));
				
				//extract RIP ID
				ripId = aipUpdater.runFileUpdates(filesForAip, iePid, repPid);
				logger.debug("RIPID: " + ripId);
								
				//flag record in DB with waiting aip update because updates will be done asynchronous
				queueDao.updateStatus(config.getQueueStatusWaitIe(), dbRow.get(DaoOracle.AMD_ID));
				
				//add rip id to stream
				queueDao.updateSipId(String.valueOf(ripId), dbRow.get(DaoOracle.AMD_ID));
				
				//waiting time after each update
				DataHelper.applicationSleep(config.getControlUpdateWaiting());
			}
		}
	}
	
	
	/**
	 * Returns whether or not all StreamBean set are empty
	 * 
	 * @param streamBeanMap
	 * @return boolean
	 */
	private boolean isStreamSetEmpty(Map<String, Set<StreamBean>> streamBeanMap)
	{
		int entryCount = 0;
		
		for(String key : streamBeanMap.keySet())
		{
			entryCount += streamBeanMap.get(key).size();
		}
		
		if(entryCount == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Main logic to create a HashMap that holds three Sets: 
	 * 
	 * for new files - NEW_FILES
	 * updated files - UPDATE_FILES
	 * deleted files - DELETE_FILES
	 * 
	 * @param rosettaStreams
	 * @param inputStreams
	 * @return Map<String, Set<StreamBean>>
	 */
	private Map<String, Set<StreamBean>> getChangedFilesMap(Set<StreamBean> rosettaStreams, Set<StreamBean> inputStreams)
	{
		Map<String, Set<StreamBean>> returnMap = new HashMap<String, Set<StreamBean>>();		
		Set<StreamBean> newFiles = new HashSet<StreamBean>();
		Set<StreamBean> updateFiles = new HashSet<StreamBean>();
		Set<StreamBean> deleteFiles = new HashSet<StreamBean>();
		
		//find updated, iterate over whats in Rosetta and compare with Dspace
		for(Iterator<StreamBean> rosettaIterator = rosettaStreams.iterator(); rosettaIterator.hasNext();)
		{
			StreamBean rosettaStreamBean = rosettaIterator.next();
			
			for(Iterator<StreamBean> inputIterator = inputStreams.iterator(); inputIterator.hasNext();)
			{
				StreamBean inputStreamBean = inputIterator.next();
				
				/*
				 * if files have the same name they are an update 
				 * no need for md5 check because 
				 * removeUnchangedStreamBeans() that run prior already
				 * removed stream that are equal in name and md5
				 * 
				 */
				if(rosettaStreamBean.getFileName().equals(inputStreamBean.getFileName()))
				{
					//add old filePid to new inputStreamBean
					inputStreamBean.setFilePid(rosettaStreamBean.getFilePid());
					//put into update set
					updateFiles.add(inputStreamBean);
					//remove it because it has beend handled
					rosettaIterator.remove();
					//remove it from set to be iterated later on
					inputIterator.remove();
				}
			}
		}
		
		//deleted: rest from rosetta that had counterpart in Dspace				
		if(rosettaStreams.size() > 0)
		{
			deleteFiles = rosettaStreams;
		}
			
			
		//new: rest from dspace that had no counterpart in Rosetta
		if(inputStreams.size() > 0)
		{
			newFiles = inputStreams;
		}
		
		returnMap.put(NEW_FILES, newFiles);
		returnMap.put(UPDATE_FILES, updateFiles);
		returnMap.put(DELETE_FILES, deleteFiles);
		
		return returnMap;

	}
	
	
	/**
	 * Remove all StreamBeans from Rosetta Stream Beans and input Stream Beans that
	 * have not changed. Returns a HashMap with two entries.
	 * 
	 * ROSETTA_SET => Set<StreamBean> rosettaStreams
	 * INPUT_SET   => Set<StreamBean> inputStreams
	 * 
	 * @param rosettaStreams
	 * @param inputStreams
	 * @return List<Set<StreamBean>>
	 */
	private Map<String, Set<StreamBean>> removeUnchangedStreamBeans(Set<StreamBean> rosettaStreams, Set<StreamBean> inputStreams)
	{
		Map<String, Set<StreamBean>> returnMap = new HashMap<String, Set<StreamBean>>();
		
		for(Iterator<StreamBean> rosettaIterator = rosettaStreams.iterator(); rosettaIterator.hasNext();)
		{
			StreamBean rosettaStreamBean = rosettaIterator.next();
			
			for(Iterator<StreamBean> inputIterator = inputStreams.iterator(); inputIterator.hasNext();)
			{
				StreamBean inputStreamBean = inputIterator.next();
				
				//no change at all
				if(rosettaStreamBean.equals(inputStreamBean))
				{
					inputIterator.remove();
					rosettaIterator.remove();
					break;
				}
			}
		}
		
		returnMap.put(ROSETTA_SET, rosettaStreams);
		returnMap.put(INPUT_SET, inputStreams);
		
		return returnMap;
	}
	
	
	/**
	 * Create a physical copy of the current IE mets
	 * in the working directory of the stream to update
	 * 
	 * @param dbRow
	 */
	private void copyRosettaMetsToFileSystem(Map<String, String> dbRow)
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
			if(e instanceof AxisFault){
				logger.error(((AxisFault)e).getFaultString());
			}
			else
			{
				logger.error(e.getMessage());
			}
			logger.error("getIE() webservice crashed, SIP_NAME: " + dbRow.get(DaoOracle.SIP_NAME) + ", ie: " + ie);
			System.exit(1);
		}
		
		//save mets file content to a separate file to allow debug (but only if sftp is active)
		FileOperations.createNewFileWithContent(dbRow.get(DaoOracle.SIP_UPDATE_PATH) + config.getStorageFilenameMets(), metsContent);
		
	}
	
	
	
	/**
	 * Read out all files from Rosetta
	 * Get the ie string from the tracking table.
	 * Get the mets.xml content from the webservice. 
	 * Write the mets file onto the file system for debugging. 
	 * Use the MetsParser object to get the return set of StreamBean objects
	 * 
	 * @param dbRow
	 * @return Set<StreamBean>
	 */
	private Set<StreamBean> getStreamsFromRosetta(Map<String, String> dbRow)
	{
		String metsContent = FileOperations.readoutFile(dbRow.get(DaoOracle.SIP_UPDATE_PATH) + config.getStorageFilenameMets());
	
		MetsParser parser = new MetsParser(config, metsContent);
		
		Set<StreamBean> rosettaSet = parser.getFilesFromRosettaSip();
		
		return rosettaSet;
	}

	
	/**
	 * Read out mets file from the file system and generate a set of StreamBean
	 * object that represent each file in the SIP
	 * 
	 * @param dbRow
	 * @return Set<StreamBean>
	 */
	private Set<StreamBean> getStreamsFromFiles(Map<String, String> dbRow)
	{
		//set up the path and get a file object
		String iePath = dbRow.get(DaoOracle.SIP_UPDATE_PATH) + dbRow.get(DaoOracle.SIP_NAME) + File.separator + config.getSipIePath();
		String filesPath = dbRow.get(DaoOracle.SIP_UPDATE_PATH) + dbRow.get(DaoOracle.SIP_NAME) + File.separator + config.getSipStreamsPath();
		File ie = new File(iePath);
		
		//if the mets file does not exist abort cause somet must be really wrong
		if(!ie.exists())
		{
			logger.fatal(iePath + " cannot be found");
			System.exit(1);
		}
		
		String ieContent = "";
		try
		{
			ieContent = new String(Files.readAllBytes(Paths.get(iePath)));
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
			System.exit(1);
		}

		MetsParser metsParser = new MetsParser(config, ieContent);
		
		//return Set directly from the MetsParser object
		return metsParser.getFilesFromDspaceMets(filesPath);
	}
	
	
	/**
	 * Copies folder from sftp location to local file system
	 * 
	 * @param dbRow<String, String>
	 */
	private void copyFromSftpLocation(Map<String, String> dbRow)
	{
		//copy from sftp to folder
		SftpHelper sftp = new SftpHelper(config);
		sftp.copy(dbRow.get(DaoOracle.SFTP_SOURCE), dbRow.get(DaoOracle.SIP_UPDATE_PATH));
		sftp.closeSftpChannel();			
	}
	
}
