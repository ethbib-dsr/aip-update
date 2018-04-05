package com.ethz.aipupdate.businesslogic;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.aipupdate.db.DaoOracle;
import com.ethz.aipupdate.db.QueueDaoOracle;
import com.ethz.aipupdate.db.TrackingDaoOracle;
import com.ethz.aipupdate.helper.ConfigProperties;
import com.ethz.aipupdate.helper.DataHelper;
import com.ethz.aipupdate.helper.SftpHelper;

/**
 * Business domain object 
 * that handles completely new items that are going to be ingested into Rosetta  
 * 
 * @author Lars Haendler
 *
 */
public class IngestDomain
{
	private static final Logger logger = LogManager.getLogger();
	
	private ConfigProperties config;
	private QueueDaoOracle queueDao;
	private TrackingDaoOracle trackingDao;
	
	
	/**
	 * Constructor
	 * 
	 * sets up ConfigProperties object and
	 * initiates the class variables for 
	 * QueueDaoOracle and TrackingDaoOracle  
	 * 
	 * @param conf 
	 */
	public IngestDomain(ConfigProperties conf)
	{
		config = conf;
		queueDao = new QueueDaoOracle(config);
		trackingDao = new TrackingDaoOracle(config);
	}
	
	
	/**
	 * Starts and controls the domain submitted
	 * 
	 */
	public void run()
	{
		logger.debug("Ingest queue - started");
		
		List<Map<String, String>> currentQueue = queueDao.getSubmittedQueue(config.getQueueStatusNew(),config.getMaxQueueRecordsIngest());
		if(currentQueue.size()>0)
		{
			logger.info("Ingest queue size: " + currentQueue.size());
			processCurrentQueue(currentQueue);
		}
		else
		{
			logger.info("Nothing found for ingest");
		}
		
		logger.debug("Ingest queue - finished");
	}
	
	
	/**
	 * Processes the provided queue of Rosetta objects one by one
	 * 
	 * @param queue
	 */
	private void processCurrentQueue(List<Map<String, String>> queue)
	{
		for(Map<String, String> dbRow : queue)
		{
			logger.info("item: " + dbRow.get(DaoOracle.WORKFLOW_EXECUTION_ID) + " (" + dbRow.get(DaoOracle.AMD_ID) + ")");
			
			//update data for insert
			dbRow = updateDataForInsert(dbRow);
			logger.debug("Queue entry data updated");
			
			//add row to tracking table
			trackingDao.insertTrackingEntry(dbRow);
			logger.info("Added to tracking table");

			//set FILE_COPY_STARTED in queue table
			queueDao.updateStatus(config.getQueueStatusStartedcopy(), dbRow.get(DaoOracle.AMD_ID));
			logger.debug("Queue status: " + config.getQueueStatusStartedcopy()); 

			//physical copy from sftp to local storage
			logger.info("cp " + dbRow.get(DaoOracle.SFTP_SOURCE) + " " + config.getStorageIngest() + ")");
			SftpHelper sftp = new SftpHelper(config);
			sftp.copy(dbRow.get(DaoOracle.SFTP_SOURCE), config.getStorageIngest());
			sftp.closeSftpChannel();

			//set tracking table entry to FEEDER_SUBMITTED
			trackingDao.updateStatus(config.getTrackingStatusFinished(), dbRow.get(DaoOracle.AMD_ID));
			logger.info("Tracking status: " + config.getTrackingStatusFinished());			

			//set queue table to status FEEDER_PREINGESTED
			queueDao.updateStatus(config.getQueueStatusPreingested(), dbRow.get(DaoOracle.AMD_ID));
			logger.info("Queue status: " + config.getQueueStatusPreingested());
		}
	}
	
	
	/**
	 * Updates the provided database entries accordingly 
	 * and add additional key-value-pairs
	 * 
	 * @param dbRow
	 * @return Map<String, String>
	 */
	private Map<String, String> updateDataForInsert(Map<String, String> dbRow)
	{
		//do nothing
		dbRow.put(DaoOracle.WORKFLOW_EXECUTION_ID, dbRow.get(DaoOracle.WORKFLOW_EXECUTION_ID)); 
		//get from config
		dbRow.put(DaoOracle.WORKSPACE_ID, config.getTrackingWorkspaceId()); 
		//do nothing
		dbRow.put(DaoOracle.AMD_ID, dbRow.get(DaoOracle.AMD_ID)); 
		//do nothing
		dbRow.put(DaoOracle.SUBMIT_TIMESTAMP, dbRow.get(DaoOracle.SUBMIT_TIMESTAMP)); 
		//cleanup path
		dbRow.put(DaoOracle.SOURCE_PATH, DataHelper.cleanupPath(dbRow.get(DaoOracle.SOURCE_PATH))); 
		//TODO: constant or whatever
		dbRow.put(DaoOracle.SFTP_SOURCE, DataHelper.addTrailingSlash(DataHelper.cleanupPath(dbRow.get(DaoOracle.SIP_PATH))));
		//consists of static path and the SIP name instead of break down the original sip_path and add static as prefix
		dbRow.put(DaoOracle.SIP_PATH, DataHelper.cleanupPath(config.getStorageIngest()) + dbRow.get(DaoOracle.SIP_NAME));
		//constant from config
		dbRow.put(DaoOracle.SIP_TYPE, config.getTrackingSipType());
		//constant from config
		dbRow.put(DaoOracle.SIP_STATUS, config.getTrackingStatusNew());
		//do nothing
		dbRow.put(DaoOracle.SIP_NAME, dbRow.get(DaoOracle.SIP_NAME));
		//do nothing
		dbRow.put(DaoOracle.DOI, dbRow.get(DaoOracle.DOI));
		
		return dbRow;
	}
	
}
