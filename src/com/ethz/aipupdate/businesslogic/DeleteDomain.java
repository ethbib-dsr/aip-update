package com.ethz.aipupdate.businesslogic;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.aipupdate.db.DaoOracle;
import com.ethz.aipupdate.db.QueueDaoOracle;
import com.ethz.aipupdate.db.TrackingDaoOracle;
import com.ethz.aipupdate.helper.ConfigProperties;
import com.ethz.aipupdate.helper.PdsConnector;
import com.exlibris.dps.IEWebServicesProxy;

/**
 * Business domain class that handles the process of deleted items.
 * 
 * @author Lars Haendler
 *
 */
public class DeleteDomain
{
	private static final Logger logger = LogManager.getLogger();
	
	private ConfigProperties config;
	private QueueDaoOracle queueDao;	
	private TrackingDaoOracle trackingDao;

	private IEWebServicesProxy wsProxy;
	private PdsConnector con;
	private Long flag;

	/**
	 * Constructor
	 * 
	 * sets up ConfigProperties object and initiates the class variables for
	 * QueueDaoOracle and TrackingDaoOracle
	 * 
	 * @param conf
	 */
	public DeleteDomain(ConfigProperties conf)
	{
		config = conf;
		queueDao = new QueueDaoOracle(config);
		trackingDao = new TrackingDaoOracle(config);
	}
	
	
	/**
	 * Controller method that initializes up all relevant variables for a DeletedDomain object
	 * 
	 */
	public void run() 
	{
		logger.debug("Delete Domain run()");
		
		con = new PdsConnector(config.getWebserviceHost(), 
				config.getWebserviceUser(), 
				config.getWebservicePassword(), 
				config.getWebserviceInstitute(), 
				config.getWebserviceCaller());

		flag = new Long(new Integer(0));
		wsProxy = new IEWebServicesProxy();
		
		List<Map<String, String>> deletedItems = queueDao.getDeleteQueue(config.getQueueStatusDeleted());

		//TODO: remove on production
		logger.debug(deletedItems);
		
		if(deletedItems.isEmpty())
		{
			logger.warn("No items with " + config.getQueueStatusDeleted() + " found");
		}
		else
		{
			processCurrentQueue(deletedItems);
		}
	}
	
	
	/**
	 * Iterates over queue and handles each item accordingly
	 * 
	 * @param List<Map<String, String>> rows
	 */
	private void processCurrentQueue(List<Map<String, String>> rows)
	{
		
		List<String> igoreStatusTypes = buildIgnoreSipStatusList();
		
		for(Map<String, String> deletedRow : rows)
		{
			logger.info(deletedRow.get(DaoOracle.SIP_NAME) + ", " + deletedRow.get(DaoOracle.AMD_ID));
			
			//check if really an entry exists
			if(trackingDao.hasIeFromSipName(deletedRow.get(DaoOracle.SIP_NAME)))
			{
				String ie = trackingDao.getIeFromSipName(deletedRow.get(DaoOracle.SIP_NAME));

				logger.debug(ie);

				//if all other entries in queue have been fishinied
				if(queueDao.countItemsNotStatus(deletedRow.get(DaoOracle.WORKFLOW_EXECUTION_ID), igoreStatusTypes) == 0)
				{
					if(updateEntityType(ie))
					{
						logger.debug("DNX update successful");
						//queue table tracking status finished
						queueDao.updateStatus(config.getQueueStatusFinishedAip(), deletedRow.get(DaoOracle.AMD_ID));
						//queue table fs tracking status clean
						queueDao.updateFsStatus(config.getQueueFsStatusClean(), deletedRow.get(DaoOracle.AMD_ID));
					}
					else
					{
						logger.warn("No update for " + deletedRow.get(DaoOracle.WORKFLOW_EXECUTION_ID));
						//queue table tracking status deleted error
						queueDao.updateStatus(config.getQueueStatusDeletedError(), deletedRow.get(DaoOracle.AMD_ID));
					}
				}
				else
				{
					logger.warn("No Update, there are still unfinished " + deletedRow.get(DaoOracle.WORKFLOW_EXECUTION_ID) + " items in queue");
				}
			}
			else
			{
				logger.warn(deletedRow.get(DaoOracle.SIP_NAME) + " queue entry ignored. There are no tacking table entries for the item. ");
				queueDao.updateStatus(config.getQueueStatusDeletedError(), deletedRow.get(DaoOracle.AMD_ID));
			}
		}
	}
	

	/**
	 * Complete update mechanism for a single IE
	 * 1. extracts dnx mets
	 * 2. replaces IE entity type
	 * 3. updates dnx mets
	 * 
	 * @param String ie
	 * @return boolean
	 */
	private boolean updateEntityType(String ie)
	{
		String oldDnxRecord = null;
		String newDnxRecord = null;
		boolean hasGetDnxCall = false;
		boolean hasUpdateDnxCall = false;
		
		//get complete DNX xml
		try
		{
			oldDnxRecord = wsProxy.getDNX(con.getPdsHandle(), ie, "");
			hasGetDnxCall = true;
		}
		catch (RemoteException e)
		{
			logger.error(e.getMessage());
			System.exit(1);
		}
		
		//update complete DNX xml
		if(oldDnxRecord != null)
		{
			if(oldDnxRecord.contains(config.getDnxDeleteEntitytypeSearch()))
			{
				newDnxRecord = updateDnxRecord(oldDnxRecord);
				
				try
				{
					wsProxy.updateDNX(con.getPdsHandle(), ie, newDnxRecord);
					hasUpdateDnxCall = true;
				}
				catch (RemoteException e)
				{
					logger.error(e.getMessage());
					System.exit(1);
				}
			}
			else
			{
				logger.warn("Could not find '" + config.getDnxDeleteEntitytypeSearch() + "' in dnx xml string");
			}
		}
		
		return (hasGetDnxCall && hasUpdateDnxCall);
	}
	
	
	/**
	 * Updates a dnx record provided as string by doing a simple replace
	 * is extendable
	 * 
	 * @param dnxRecord
	 * @return
	 */
	private String updateDnxRecord(String dnxRecord)
	{
		String returnString = dnxRecord;
		
		if(dnxRecord.contains(config.getDnxDeleteEntitytypeSearch()))
		{
			returnString = dnxRecord.replace(config.getDnxDeleteEntitytypeSearch(), config.getDnxDeleteEntitytypeReplace());
		}
		
		return returnString;
	}
	
	
	/**
	 * Build ignore list that is used to find all IEs that should
	 * be ignored when collecting deleted items
	 * 
	 * @return List<String>
	 */
	private List<String> buildIgnoreSipStatusList()
	{
		List<String> statusList = new ArrayList<>();
		
		statusList.add(config.getTrackingIeFished());
		statusList.add(config.getQueueStatusFinishedAip());
		statusList.add(config.getQueueStatusDeleted());
		statusList.add(config.getQueueStatusDeletedError());
		
		return statusList;
	}

}
