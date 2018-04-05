package com.ethz.aipupdate.helper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.axis.AxisFault;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.aipupdate.beans.StreamBean;
import com.ethz.aipupdate.businesslogic.AipUpdateDomain;
import com.ethz.aipupdate.db.QueueDaoOracle;
import com.exlibris.dps.Fixity;
import com.exlibris.dps.IEWebServicesProxy;
import com.exlibris.dps.Operation;
import com.exlibris.dps.RepresentationContent;

/**
 * AIP Updater Object that handles all aip-updates
 * 
 * https://bitbucket.org/ethbib_bit/aip-update
 * 
 * @author Lars Haendler
 *
 */

public class AipUpdater
{
	private static final Logger logger = LogManager.getLogger();
	private ConfigProperties config;	
	private IEWebServicesProxy wsProxy;
	private PdsConnector con;
	private QueueDaoOracle queueDao;
	private String amdId;
	
	private static String FIXITY_MD5 = "MD5";
	
	static String REASON_UPDATE = "aip-update Updater";

	public AipUpdater(ConfigProperties conf, IEWebServicesProxy webserviceProxy, PdsConnector pdsCon, QueueDaoOracle queue, String amd)
	{
		config = conf;
		wsProxy = webserviceProxy;
		con = pdsCon;
		queueDao = queue;
		amdId = amd;
		
		
	}

	
	public long runFileUpdates(Map<String, Set<StreamBean>> fileMap, String iePid, String repPid)
	{
		List<RepresentationContent> repList = new ArrayList<RepresentationContent>();
		long ripId = 0;
		
		repList.addAll(generateNewFileRepresentations(fileMap.get(AipUpdateDomain.NEW_FILES)));
		repList.addAll(generateUpdateFileRepresentation(fileMap.get(AipUpdateDomain.UPDATE_FILES)));
		repList.addAll(generateDeleteFileRepresentations(fileMap.get(AipUpdateDomain.DELETE_FILES)));
		
		RepresentationContent[] repContentArray = new RepresentationContent[repList.size()];
		repContentArray = repList.toArray(repContentArray);
		
		try
		{
			ripId = wsProxy.updateRepresentation(con.getPdsHandle(), iePid, repPid, REASON_UPDATE, repContentArray);
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

		return ripId;
	}
	
	
	private List<RepresentationContent> generateUpdateFileRepresentation(Set<StreamBean> updateFileBeans)
	{
		List<RepresentationContent> updRepsList = new ArrayList<RepresentationContent>();
		
		for(StreamBean streamBean : updateFileBeans)
		{
			logger.debug("replace: " + streamBean.getFileName());
			
			RepresentationContent rep = new RepresentationContent();
			
			rep.setLabel(streamBean.getFileName()); // file name
			rep.setFixity(createFixity(FIXITY_MD5, streamBean.getMd5Hash())); //fixity
			rep.setFileOriginalPath(streamBean.getOriginalFileName()); // original file path
			rep.setNewFile(streamBean.getAddLocation()); //path on server
			rep.setOldFilePid(streamBean.getFilePid()); //file pid
			rep.setOperation(Operation.REPLACE);
			
			updRepsList.add(rep);
		}
		
		return updRepsList;
	}
	
	
	private List<RepresentationContent> generateDeleteFileRepresentations(Set<StreamBean> deleteFileBeans)
	{
		List<RepresentationContent> delRepsList = new ArrayList<RepresentationContent>();
		
		for(StreamBean streamBean : deleteFileBeans)
		{
			logger.debug("remove: " + streamBean.getFileName());
			
			RepresentationContent rep = new RepresentationContent();
			
			rep.setOldFilePid(streamBean.getFilePid());
			rep.setOperation(Operation.REMOVE);
			
			delRepsList.add(rep);
		}
		
		return delRepsList;
	}
	
	
	private List<RepresentationContent> generateNewFileRepresentations(Set<StreamBean> newFileBeans)
	{
		List<RepresentationContent> newRepsList = new ArrayList<RepresentationContent>();
		
		for(StreamBean streamBean : newFileBeans)
		{
			logger.debug("add: " + streamBean.getFileName());
			
			RepresentationContent rep = new RepresentationContent();
			
			rep.setLabel(streamBean.getFileName()); // file name
			rep.setFixity(createFixity(FIXITY_MD5, streamBean.getMd5Hash())); //fixity
			rep.setFileOriginalPath(streamBean.getOriginalFileName()); // original file path
			rep.setNewFile(streamBean.getAddLocation()); //path on server
			rep.setOperation(Operation.ADD);
			
			newRepsList.add(rep);
		}
		
		return newRepsList;
	}
	
	
	private Fixity createFixity(String fixityType, String fixityValue)
	{
		Fixity fixity = new Fixity();
		
		fixity.setAlgorithmType(fixityType);
		fixity.setValue(fixityValue);
		
		return fixity;
	}
}
