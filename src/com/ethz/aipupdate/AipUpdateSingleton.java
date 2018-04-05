package com.ethz.aipupdate;

import com.ethz.aipupdate.businesslogic.IngestDomain;
import com.ethz.aipupdate.businesslogic.PostAipUpdateDomain;
import com.ethz.aipupdate.businesslogic.AipUpdateDomain;
import com.ethz.aipupdate.businesslogic.DeleteDomain;
import com.ethz.aipupdate.businesslogic.FileSystemCleanupDomain;
import com.ethz.aipupdate.helper.ConfigProperties;

/**
 * AIP update Singleton
 * 
 * Singleton class to make sure the application is only started once and cannot
 * have multiple instances or used by multiple thread. Multi instance I/O would
 * put too much strain on the current NAS/SAN environment.
 * 
 * Main class responsible for the whole application
 * 
 * 
 * https://bitbucket.org/ethbib_bit/aip-update
 * 
 * @author Lars Haendler
 *
 */

public class AipUpdateSingleton
{

	private static AipUpdateSingleton instance = null;
	private static ConfigProperties config;
	public static String wsLocation;

	private AipUpdateSingleton()
	{
		// defeat instantiation
	}

	public static AipUpdateSingleton getInstance(String configPath)
	{
		if (instance == null)
		{
			synchronized (AipUpdateSingleton.class)
			{
				if (instance == null)
				{
					instance = new AipUpdateSingleton();
					config = new ConfigProperties(configPath);
					wsLocation = config.getWebserviceUrl();
				}
			}
		}

		return instance;
	}

	public synchronized void init()
	{
		
		//sip-status: FEEDER_SUBMITTED
		if(Integer.parseInt(config.getMaxQueueRecordsIngest()) > 0)
		{
			IngestDomain domainIngest = new IngestDomain(config);
			domainIngest.run();
		}
		
		//sip-status: AIP_UPDATE_WAITING
		if(Integer.parseInt(config.getQueuePostaipupdate()) > 0)
		{
			PostAipUpdateDomain domainPostUpdate = new PostAipUpdateDomain(config);
			domainPostUpdate.run();
		}
		
		//sip-status: FEEDER_UPDATED
		if(Integer.parseInt(config.getMaxQueueRecordsUpdate()) > 0)
		{
			AipUpdateDomain domainAipupdate = new AipUpdateDomain(config);
			domainAipupdate.run();	
		}
		
		//sip-status-fs: WD_NOT_CLEAN & SFTP_NOT_CLEAN 
		if(Integer.parseInt(config.getQueueFsStatusMaxItems()) > 0)
		{
			FileSystemCleanupDomain cleaner = new FileSystemCleanupDomain(config);
			cleaner.run();
		}
		
		//handle deleted items
		//currently deactivated
		/*
		if(Integer.parseInt(config.getQueueDelete()) > 0 )
		{
			DeleteDomain deleteDomain = new DeleteDomain(config);
			deleteDomain.run();
		}
		*/
	}

}
