package com.ethz.submissionds.businesslogic;

import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.submissionds.db.DaoOracle;
import com.ethz.submissionds.db.QueueDaoOracle;
import com.ethz.submissionds.helper.ConfigProperties;


/**
 * Business domain object that handles the cleanup of update items in the queue
 * table flagging all intermittened update as to be removed from the file system
 * 
 * @author Lars Haendler
 *
 */
public class CleanupUpdateDuplicatesDomain
{
	private static final Logger logger = LogManager.getLogger();

	private ConfigProperties config;
	private QueueDaoOracle queueDao;

	public CleanupUpdateDuplicatesDomain(ConfigProperties conf)
	{
		config = conf;
		queueDao = new QueueDaoOracle(config);
	}

	public void run()
	{
		logger.debug("queue table duplicate cleanup - started");

		Map<String, Integer> duplicateSipNames;
		duplicateSipNames = queueDao.getDuplicateUpdatesSameSipname();

		if (duplicateSipNames.size() > 0)
		{
			logger.warn("Duplicate '" + config.getQueueStatusUpdate() + "' found, " + duplicateSipNames.size() + " "
					+ DaoOracle.SIP_NAME + " duplicates");
			
			for(String sipName : duplicateSipNames.keySet())
			{
				logger.warn(sipName + " (" + duplicateSipNames.get(sipName) + " duplicates)");
				handleDuplicate(sipName);
			}
			
		}
		

		logger.debug("queue table duplicate cleanup - fininshed");
	}	
	
	
	private void handleDuplicate(String sipName)
	{
		Set<String> amdIds;
		amdIds = queueDao.getAmdIdsOfDuplicates(sipName, config.getQueueStatusUpdate());
		
		for(String amdId : amdIds)
		{
			logger.warn("update " + amdId 
							+ ", SIP_STATUS_FS='" + config.getQueueFsStatusSftpNotClean() 
							+ "', SIP_STATUS='" + config.getQueueStatusDuplicateUpdate() + "'");
			
			queueDao.updateStatus(config.getQueueStatusDuplicateUpdate(), amdId);
			queueDao.updateFsStatus(config.getQueueFsStatusSftpNotClean(), amdId);
		}

	}

}
