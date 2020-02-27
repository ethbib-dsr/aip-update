package com.ethz.submissionds.businesslogic;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.submissionds.db.DaoOracle;
import com.ethz.submissionds.db.QueueDaoOracle;
import com.ethz.submissionds.helper.ConfigProperties;
import com.ethz.submissionds.helper.DataHelper;
import com.ethz.submissionds.helper.FileOperations;
import com.ethz.submissionds.helper.SftpHelper;

/**
 * Business domain object 
 * that handles the file system cleanup of all processed items 
 * it does not discriminate between ingested or updated items  
 * 
 * @author Lars Haendler
 *
 */
public class FileSystemCleanupDomain {

	private static final Logger logger = LogManager.getLogger();
	
	private ConfigProperties config;
	private QueueDaoOracle queueDao;
	
	private static final int SFTP_FOLDER_NAME_LENGTH = 14;
	
	/**
	 * Constructor
	 * 
	 * @param conf
	 */
	public FileSystemCleanupDomain(ConfigProperties conf)
	{
		config = conf;
		queueDao = new QueueDaoOracle(config);
	}
	
	
	public void run()
	{
		logger.debug("file system cleanup - started");

		//get queue of WD_NOT_CLEAN
		logger.info("start WD_NOT_CLEAN older than " + config.getQueueFsStatusNumberOfDays() + " days" + " days (" + config.getQueueFsStatusMaxItems() + " items)");
		List<Map<String, String>> wdNotCleanItems = queueDao.getFileSystemCleanupQueue(config.getQueueFsStatusWdNotClean(), config.getQueueFsStatusNumberOfDays(), config.getQueueFsStatusMaxItems());

		//run cleanup over the all relevant items
		cleanWorkingDirectory(wdNotCleanItems);
		
		//TODO get queue with SFTP_NOT_CLEAN
		logger.info("start SFTP_NOT_CLEAN older than " + config.getQueueFsStatusNumberOfDays() + " days (" + config.getQueueFsStatusMaxItems() + " items)");
		List<Map<String, String>> sftpNotCleanItems = queueDao.getFileSystemCleanupQueue(config.getQueueFsStatusSftpNotClean(), config.getQueueFsStatusNumberOfDays(), config.getQueueFsStatusMaxItems());
		
		//run cleanup over all relevant items
		cleanSftpDirectory(sftpNotCleanItems);
		
		logger.debug("file system cleanup - finished");
	}
	
	
	/**
	 * Cleans up the update working directory
	 * 
	 * @param dbRows
	 */
	public void cleanWorkingDirectory(List<Map<String, String>> dbRows)
	{
		for(Map<String, String> dbRow : dbRows)
		{
			File deleteFile = new File(config.getStorageUpdate() + dbRow.get(DaoOracle.AMD_ID));
			
			if(deleteFile.exists())
			{
				logger.debug("delete: " + deleteFile.getAbsolutePath());
				FileOperations.recursiveFileDelete(deleteFile);
			}
			else
			{
				logger.warn("Could not find folder: " + deleteFile.getAbsolutePath() + "(" + dbRow.get(DaoOracle.AMD_ID) + ")");
			}

			//set new fs queue status because SFTP cleanup has still to be done
			queueDao.updateFsStatus(config.getQueueFsStatusSftpNotClean(), dbRow.get(DaoOracle.AMD_ID));
		}
	}
	
	
	/**
	 * Cleans up the SFTP directories recursively
	 * 
	 * @param dbRows
	 */
	public void cleanSftpDirectory(List<Map<String, String>> dbRows)
	{
		SftpHelper sftpHelper = new SftpHelper(config);
		
		for(Map<String, String> dbRow : dbRows)
		{
			//add the correct SOURCE_PATH to current map
			dbRow.put(DaoOracle.SFTP_SOURCE, DataHelper.addTrailingSlash(DataHelper.cleanupPath(dbRow.get(DaoOracle.SIP_PATH))));

			logger.info("delete: " + dbRow.get(DaoOracle.SFTP_SOURCE));
			
			if (isSftpPathValid(dbRow.get(DaoOracle.SFTP_SOURCE), config.getSftpHomeDir()))
			{
				//delete directory
				sftpHelper.deleteDir(dbRow.get(DaoOracle.SFTP_SOURCE));
				
				//optional: delete parent because of a parent datetime directory
				sftpHelper.deleteParentIfEmpty(dbRow.get(DaoOracle.SFTP_SOURCE));
				
				//queue update to show sftp clean up
				queueDao.updateFsStatus(config.getQueueFsStatusClean(), dbRow.get(DaoOracle.AMD_ID));
			}
			else
			{
				logger.warn("SFTP path invalid: " + dbRow.get(DaoOracle.SFTP_SOURCE));
				continue;
			}
		}
		
		sftpHelper.closeSftpChannel();
	}


	/**
	 * Check if provided itemPath is valid in relation to the sftp home directory
	 * 
	 * @param itemPath
	 * @param sftpHomeDir
	 * @return boolean
	 */
	private boolean isSftpPathValid(String itemPath, String sftpHomeDir)
	{
		if(itemPath.contains(sftpHomeDir) && 
			(itemPath.length() > (sftpHomeDir.length() + SFTP_FOLDER_NAME_LENGTH)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
