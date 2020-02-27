package com.ethz.submissionds.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Configuration Object that handles data extraction from config.properties
 * 
 * https://bitbucket.org/ethbib_bit/aip-update
 * 
 * @author Lars Haendler
 *
 */
public class ConfigProperties
{

	private String configPath;
	private static final Logger logger = LogManager.getLogger();
	private static final String STRING_SPLITTER = "\\s*,\\s*"; // removes an
																					// leading or
																					// trailing
																					// spaces

	/**
	 * Constructor for setting up the Configuration object
	 * 
	 * @param confPath
	 *           relative url to config.properties
	 */
	public ConfigProperties(String confPath)
	{
		configPath = confPath;
	}

	/**
	 * Returns DB driver name
	 * 
	 * @return Strings
	 */
	public String getDbDriverName()
	{
		return getStringValue("db.drivername");
	}

	/**
	 * Return DB connection url
	 * 
	 * @return String
	 */
	public String getDbConnectionUrl()
	{
		return getStringValue("db.connectionurl");
	}

	/**
	 * Returns DB user name
	 * 
	 * @return String
	 */
	public String getDbUsername()
	{
		return getStringValue("db.username");
	}

	/**
	 * Returns DB password for username
	 * 
	 * @return String
	 */
	public String getDbPassword()
	{
		return getStringValue("db.password");
	}

	/**
	 * Returns queue status new
	 * 
	 * @return String
	 */
	public String getQueueStatusNew()
	{
		return getStringValue("queue.status.new");
	}

	/**
	 * Returns queue status update
	 * 
	 * @return String
	 */
	public String getQueueStatusUpdate()
	{
		return getStringValue("queue.status.update");
	}

	/**
	 * Returns queue status deleted
	 * 
	 * @return String
	 */
	public String getQueueStatusDeleted()
	{
		return getStringValue("queue.status.deleted");
	}

	/**
	 * Returns queue status started file copy
	 * 
	 * @return String
	 */
	public String getQueueStatusStartedcopy()
	{
		return getStringValue("queue.status.startedcopy");
	}

	/**
	 * Returns queue status preingested / finished in queue
	 * 
	 * @return String
	 */
	public String getQueueStatusPreingested()
	{
		return getStringValue("queue.status.preingested");
	}

	/**
	 * Returns the queue status waitie
	 * 
	 * @return
	 */
	public String getQueueStatusWaitIe()
	{
		return getStringValue("queue.status.waitie");
	}

	/**
	 * Returns queue status aip error
	 * 
	 * @return String
	 */
	public String getQueueStatusAipError()
	{
		return getStringValue("queue.status.error.aip");
	}

	/**
	 * Returns queue status meta data update error
	 * 
	 * @return
	 */
	public String getQueueStatusPostAipError()
	{
		return getStringValue("queue.status.error.md");
	}

	/**
	 * Returns queue status error when control is locked
	 * 
	 * @return
	 */
	public String getQueueStatusControlLockedError()
	{
		return getStringValue("queue.status.error.locked");
	}

	/**
	 * Returns queue status for update items that are 
	 * unprocessed duplicates of the same SIP_NAME 
	 * 
	 * @return String
	 */
	public String getQueueStatusDuplicateUpdate()
	{
		return getStringValue("queue.status.update.duplicate");
	}
	
	/**
	 * Returns queue status unique constraint error
	 * 
	 * @return
	 */
	public String getQueueStatusUniqueConstraintError()
	{
		return getStringValue("queue.status.error.unique");
	}

	/**
	 * Returns file system status working directory not clean
	 * 
	 * @return
	 */
	public String getQueueFsStatusWdNotClean()
	{
		return getStringValue("queue.fstatus.wdnotclean");
	}

	/**
	 * Returns file system status sftp not clean
	 * 
	 * @return
	 */
	public String getQueueFsStatusSftpNotClean()
	{
		return getStringValue("queue.fstatus.sftpnotclean");
	}

	/**
	 * Returns file system status all cleaned up
	 * 
	 * @return
	 */
	public String getQueueFsStatusClean()
	{
		return getStringValue("queue.fstatus.clean");
	}

	/**
	 * Returns number of days after which the cleanup process should start
	 * 
	 * @return String
	 */
	public String getQueueFsStatusNumberOfDays()
	{
		return getStringValue("queue.fstatus.numberofdays");
	}

	/**
	 * Returns max number of items delete per SFTP cleanup run
	 * 
	 * @return
	 */
	public String getQueueFsStatusMaxItems()
	{
		return getStringValue("queue.fstatus.sftp.maxitems");
	}

	/**
	 * Returns the queue status update finished
	 * 
	 * @return String
	 */
	public String getQueueStatusFinishedAip()
	{
		return getStringValue("queue.status.finished");
	}

	/**
	 * Return the queue status for erroneous deleted items
	 * 
	 * @return
	 */
	public String getQueueStatusDeletedError()
	{
		return getStringValue("queue.status.deleted.error");
	}

	/**
	 * Returns seconds application should wait between update operations
	 * 
	 * @return integer
	 */
	public int getControlUpdateWaiting()
	{
		return getIntValue("control.update.waiting");
	}

	/**
	 * Returns max records from queue for ingest
	 * 
	 * @return String
	 */
	public String getMaxQueueRecordsIngest()
	{
		return getStringValue("queue.maxrecords.ingest");
	}

	/**
	 * Returns max records from queue for updates
	 * 
	 * @return String
	 */
	public String getMaxQueueRecordsUpdate()
	{
		return getStringValue("queue.maxrecords.update");
	}

	/**
	 * Returns workspace id for tracking table
	 * 
	 * @return String
	 */
	public String getTrackingWorkspaceId()
	{
		return getStringValue("tracking.workspaceid");
	}

	/**
	 * Returns path for ingest storage
	 * 
	 * @return String
	 */
	public String getStorageIngest()
	{
		return getStringValue("storage.ingest");
	}

	/**
	 * Returns path for ingest storage
	 * 
	 * @return String
	 */
	public String getStorageUpdate()
	{
		return getStringValue("storage.update");
	}

	/**
	 * Returns mets file name that is used for debug
	 * 
	 * @return String
	 */
	public String getStorageFilenameMets()
	{
		return getStringValue("storage.file.metsupdate");
	}

	/**
	 * Returns mets file name for post-aip debug
	 * 
	 * @return String
	 */
	public String getPostaipStorageFilenameMets()
	{
		return getStringValue("storage.file.metspostupdate");
	}

	/**
	 * Returns sip_type for tracking table
	 * 
	 * @return String
	 */
	public String getTrackingSipType()
	{
		return getStringValue("tracking.siptype");
	}

	/**
	 * Returns finished status of IE after item has been completely ingested
	 * 
	 * @return
	 */
	public String getTrackingIeFished()
	{
		return getStringValue("tracking.ie.finished");
	}

	/**
	 * Returns status new for tracking table
	 * 
	 * @return String
	 */
	public String getTrackingStatusNew()
	{
		return getStringValue("tracking.status.new");
	}

	/**
	 * Return status finished for tracking table
	 * 
	 * @return
	 */
	public String getTrackingStatusFinished()
	{
		return getStringValue("tracking.status.finished");
	}

	/**
	 * Returns status copying files for tracking table
	 * 
	 * @return String
	 */
	public String getTrackingStatusCopying()
	{
		return getStringValue("tracking.status.copyingfiles");
	}

	/**
	 * Returns FQN for tracking table
	 * 
	 * @return String
	 */
	public String getQueryTableTracking()
	{
		return getStringValue("query.table.tracking");
	}

	/**
	 * Returns FQN for queue table
	 * 
	 * @return String
	 */
	public String getQueryTableQueue()
	{
		return getStringValue("query.table.queue");
	}

	/**
	 * Returns Oracle specific time format for copying timestamp from queue table
	 * to tracking table
	 * 
	 * @return String
	 */
	public String getOracleTimeFormat()
	{
		return getStringValue("query.oracle.timeformat");
	}
	
	/**
	 * Returns Java time format to use in accordance to make DB updates with
	 * the Oracle to_date time format 'query.oracle.timeformat'
	 * 
	 * @return String
	 */
	public String getJavaTimeFormat()
	{
		return getStringValue("query.java.timeformat");
	}

	/**
	 * Returns SFTP host name
	 * 
	 * @return String
	 */
	public String getSftpHost()
	{
		return getStringValue("sftp.host");
	}

	/**
	 * Returns SFTP user name
	 * 
	 * @return String
	 */
	public String getSftpUser()
	{
		return getStringValue("sftp.user");
	}

	/**
	 * Returns SFTP port
	 * 
	 * @return int
	 */
	public int getSftpPort()
	{
		return getIntValue("sftp.port");
	}

	/**
	 * Returns SFTP password for user name
	 * 
	 * @return String
	 */
	public String getSftpPassword()
	{
		return getStringValue("sftp.password");
	}

	/**
	 * Returns SFTP home directory
	 * 
	 * @return String
	 */
	public String getSftpHomeDir()
	{
		return getStringValue("sftp.homedir");
	}

	/**
	 * Returns the relative path to file containing the sql query to extract
	 * update records
	 * 
	 * @return String
	 */
	public String getQueueUpdateFile()
	{
		return getStringValue("queue.updt.file");
	}

	/**
	 * Return the URL of the webservice
	 * 
	 * @return String
	 */
	public String getWebserviceUrl()
	{
		return getStringValue("webservice.url");
	}

	/**
	 * Return the web service host, the first part the URL
	 * 
	 * @return String
	 */
	public String getWebserviceHost()
	{
		return getStringValue("webservice.pdsHost");
	}

	/**
	 * Returns the web service user query
	 * 
	 * @return String
	 */
	public String getWebserviceUser()
	{
		return getStringValue("webservice.pdsUser");
	}

	/**
	 * Returns the web service password query
	 * 
	 * @return String
	 */
	public String getWebservicePassword()
	{
		return getStringValue("webservice.pdsPassword");
	}

	/**
	 * Returns the web service institution query
	 * 
	 * @return String
	 */
	public String getWebserviceInstitute()
	{
		return getStringValue("webservice.pdsInstitute");
	}

	/**
	 * Returns the web services call name
	 * 
	 * @return String
	 */
	public String getWebserviceCaller()
	{
		return getStringValue("webservice.pdsCaller");
	}

	/**
	 * Returns xpath to get all files from mets struct map
	 * 
	 * @return String
	 */
	public String getXPathAllFiles()
	{
		return getStringValue("metsparser.allfiles");
	}

	/**
	 * Returns xpath for IE DC meta data section
	 * 
	 * @return String
	 */
	public String getXPathIeDc()
	{
		return getStringValue("metsparser.ie.dc");
	}

	/**
	 * Returns xpath for file DC meta data section
	 * 
	 * @return String
	 */
	public String getXPathFileDc()
	{
		return getStringValue("metsparser.file.dc");
	}

	/**
	 * Returns xpath to get the md5 value of a file #FILEID# has to be replace
	 * with a valid fileid
	 * 
	 * @return String
	 */
	public String getXPathFileMd5()
	{
		return getStringValue("metsparser.file.md5");
	}

	/**
	 * Returns xpath to get the file name of file #FILEID# has to be replace with
	 * a valid fileid
	 * 
	 * @return String
	 */
	public String getXpathFileName()
	{
		return getStringValue("metsparser.file.name");
	}

	/**
	 * Returns xpath to get the file extension of a file #FILEID# has to be
	 * replace with a valid fileid
	 * 
	 * @return String
	 */
	public String getXpathFileExtensions()
	{
		return getStringValue("metsparser.file.extension");
	}

	/**
	 * Returns xpath to get the file size of a file #FILEID# has to be replace
	 * with a valid fileid
	 * 
	 * @return String
	 */
	public String getXpathFileSize()
	{
		return getStringValue("metsparser.file.size");
	}

	/**
	 * Returns xpath to get the original file path of a file #FILEID# has to be
	 * replace with a valid fileid
	 * 
	 * @return
	 */
	public String getXpathFileOriginalpath()
	{
		return getStringValue("metsparser.file.originalpath");
	}

	/**
	 * Returns xpath to representation id in Rosetta mets.xml
	 * 
	 * @return String
	 */
	public String getXpathRosettaRepId()
	{
		return getStringValue("metsparser.rosetta.reppid");
	}

	/**
	 * Returns xpath to ie pid in Rosetta mets.xml
	 * 
	 * @return String
	 */
	public String getXpathRosettaIePid()
	{
		return getStringValue("metsparser.rosetta.iepid");
	}

	/**
	 * Returns relative path of ie.xml inside the SIP
	 * 
	 * @return String
	 */
	public String getSipIePath()
	{
		return getStringValue("storage.sip.relativeiepath");
	}

	/**
	 * Returns relative path to streams directory inside SIP
	 * 
	 * @return String
	 */
	public String getSipStreamsPath()
	{
		return getStringValue("storage.sip.relativestreamspath");
	}

	/**
	 * Return search string for dnx delete entity type
	 * 
	 * @return
	 */
	public String getDnxDeleteEntitytypeSearch()
	{
		return getStringValue("dnxdelete.entitytype.search");
	}

	/**
	 * Return replace string for dnx delete entity type
	 * 
	 * @return
	 */
	public String getDnxDeleteEntitytypeReplace()
	{
		return getStringValue("dnxdelete.entitytype.replace");
	}

	/**
	 * Returns dc update header
	 * 
	 * @return String
	 */
	public String getDcUpdateHeader()
	{
		return getStringValue("dcupdate.header");
	}

	/**
	 * Returns dc update footer
	 * 
	 * @return String
	 */
	public String getDcUpdateFooter()
	{
		return getStringValue("dcupdate.footer");
	}

	/**
	 * Return the dc update ie type value
	 * 
	 * @return String
	 */
	public String getDcUpdateIeType()
	{
		return getStringValue("dcupdate.ie.type");
	}

	/**
	 * Returns the dc update ie subtype value
	 * 
	 * @return String
	 */
	public String getDcUpdateIeSubtype()
	{
		return getStringValue("dcupdate.ie.subtype");
	}

	/**
	 * Returns the post aip update parameter
	 * 
	 * @return String
	 */
	public String getQueuePostaipupdate()
	{
		return getStringValue("queue.postaipupdate");
	}
	
	/**
	 * Return the cleanup update duplicates parameter
	 * 
	 * @return String
	 */
	public String getQueueCleanupUpdates()
	{
		return getStringValue("queue.cleanup.upt-duplicate");
	}

	/**
	 * Return the queue delete paramter
	 * 
	 * @return String
	 */
	public String getQueueDelete()
	{
		return getStringValue("queue.delete");
	}

	/**
	 * Returns ArrayList of all files that should be ignored when running file dc
	 * meta data updates
	 * 
	 * @return String
	 */
	public List<String> getFileDcIgnoredFiles()
	{
		String fileString = getStringValue("filedc.ignorefiles");

		List<String> fileArray = new ArrayList<String>();

		if (!fileString.isEmpty())
		{
			fileArray = Arrays.asList(fileString.split(STRING_SPLITTER));
		}

		return fileArray;
	}

	/**
	 * Returns first node that should be used for updating file dc meta data
	 * 
	 * @return String
	 */
	public String getFileDcUpdateNode1()
	{
		return getStringValue("filedc.dcupdate.node1");
	}

	/**
	 * Returns the interger value of a provided String
	 * 
	 * @param String
	 *           key
	 * @return int
	 */
	public int getIntValue(String key)
	{
		return Integer.valueOf(getElementFromProperty(key));
	}

	/**
	 * Returns String value of supplied key also checks if quotes are used and
	 * extracts values accordingly
	 * 
	 * @param key
	 *           key used to get the String value from properties files
	 * @return value the key
	 */
	public String getStringValue(String key)
	{
		String content = getElementFromProperty(key).trim();

		if (!content.isEmpty())
		{
			// Allow the use of special characters or spaces in property values
			if (hasQuotedContent(content))
			{
				return getQuotedContent(content);
			}
			else
			{
				return content;
			}
		}
		else
		{
			return "";
		}

	}

	/**
	 * Checks whether or not a single or double quotes enclose the String
	 * 
	 * @param content
	 * @return boolean
	 */
	private boolean hasQuotedContent(String content)
	{
		if ((content.charAt(0) == '"' && content.charAt(content.length() - 1) == '"')
				|| (content.charAt(0) == '\'' && content.charAt(content.length() - 1) == '\''))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Extracts value from a string that is enclosed by double or single quotes
	 * 
	 * @param content
	 * @return String
	 */
	private String getQuotedContent(String content)
	{
		if ((content.charAt(0) == '"' && content.charAt(content.length() - 1) == '"')
				|| (content.charAt(0) == '\'' && content.charAt(content.length() - 1) == '\''))
		{
			content = content.substring(1, content.length() - 1);
		}

		return content;
	}

	/**
	 * Generic properties file reader that tries to find the supplied key in the
	 * properties file. The corresponding value is returned as String.
	 * 
	 * @param key
	 *           key used to get the value from properties file
	 * @return value of the key
	 */
	private String getElementFromProperty(String key)
	{
		File configFile = new File(configPath);
		String property = "";

		try
		{
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			if (!props.containsKey(key))
			{
				logger.warn("key: " + key + " does not exist in properties file");
			}

			property = props.getProperty(key);
			reader.close();
		}
		catch (FileNotFoundException ex)
		{
			logger.error("properties.config does not exisit");
			logger.error(ex.getMessage());
			System.exit(1);
		}
		catch (IOException ex)
		{
			logger.error("IO Error while opening properties.config");
			logger.error(ex.getMessage());
			System.exit(1);
		}

		return property;
	}

}
