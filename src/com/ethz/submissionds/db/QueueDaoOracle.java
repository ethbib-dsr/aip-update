package com.ethz.submissionds.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ethz.submissionds.helper.ConfigProperties;


/**
 * Data Access Object for Oracle queue table
 * 
 * @author Lars Haendler
 *
 */

public class QueueDaoOracle extends DaoOracle
{

	/**
	 * Constructor uses the parents class constructor
	 * and sets up the local ConfigProperties variable
	 * 
	 * @param config
	 */
	public QueueDaoOracle(ConfigProperties config)
	{
		super(config);
	}
	

	/**
	 * Queries the queue table and fill the result into a arrayList.
	 * Each arraylist entry contains all relevant columns in form 
	 * of a hashmap 
	 *  
	 * 
	 * @param sipStatus 
	 * @param limit
	 * @return List<Map<String, String>> 
	 */
	public List<Map<String, String>> getSubmittedQueue(String sipStatus, String limit)
	{
		List<Map<String, String>> currentQueue = new ArrayList<Map<String, String>>();
		Map<String, String> dbRows;
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		setupConnection();
		
		try
		{
			stmt = conn.prepareStatement(buildSelectSubmittedQueueStatement(sipStatus, limit));
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows = new HashMap<String, String>();
				
				dbRows.put(DaoOracle.WORKFLOW_EXECUTION_ID, rs.getString(DaoOracle.WORKFLOW_EXECUTION_ID));
				dbRows.put(DaoOracle.AMD_ID, rs.getString(DaoOracle.AMD_ID));
				dbRows.put(DaoOracle.SUBMIT_TIMESTAMP, rs.getString(DaoOracle.SUBMIT_TIMESTAMP));
				dbRows.put(DaoOracle.SOURCE_PATH, rs.getString(DaoOracle.SOURCE_PATH));
				dbRows.put(DaoOracle.SIP_PATH, rs.getString(DaoOracle.SIP_PATH));
				dbRows.put(DaoOracle.SIP_NAME, rs.getString(DaoOracle.SIP_NAME));
				dbRows.put(DaoOracle.SIP_STATUS, rs.getString(DaoOracle.SIP_STATUS));
				dbRows.put(DaoOracle.DOI, rs.getString(DaoOracle.DOI));
				
				currentQueue.add(dbRows);
			}
			
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();		
		
		return currentQueue;
	}	
	
	
	/**
	 * Queries the queue table for items that need to be updated. 
	 * The results are used to fill an ArrayList.
	 * Each entry in the ArrayList contains the relevant columns in form
	 * of a hashmap
	 * 
	 * @param limit
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> getUpdatedQueue(String limit)
	{
		
		List<Map<String, String>> currentQueue = new ArrayList<Map<String, String>>();
		Map<String, String> dbRows;
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		setupConnection();
		
		try
		{
			stmt = conn.prepareStatement(buildSelectUpdatedQueueStatement(limit));
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows = new HashMap<String, String>();
				
				dbRows.put(DaoOracle.WORKFLOW_EXECUTION_ID, rs.getString(DaoOracle.WORKFLOW_EXECUTION_ID));
				dbRows.put(DaoOracle.AMD_ID, rs.getString(DaoOracle.AMD_ID));
				dbRows.put(DaoOracle.SUBMIT_TIMESTAMP, rs.getString(DaoOracle.SUBMIT_TIMESTAMP));
				dbRows.put(DaoOracle.SOURCE_PATH, rs.getString(DaoOracle.SOURCE_PATH));
				dbRows.put(DaoOracle.SIP_PATH, rs.getString(DaoOracle.SIP_PATH));
				dbRows.put(DaoOracle.SIP_NAME, rs.getString(DaoOracle.SIP_NAME));
				dbRows.put(DaoOracle.SIP_STATUS, rs.getString(DaoOracle.SIP_STATUS));
				dbRows.put(DaoOracle.DOI, rs.getString(DaoOracle.DOI));
				
				currentQueue.add(dbRows);
			}
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();		

		return currentQueue;
	}		
	
	
	/**
	 * Queries the queue table for items that had been updated with aipupdate
	 * but now need to post-processing. 
	 * The results are used to fill an ArrayList. Each entry in the ArrayList 
	 * contains the relevant columns in form of a hashmap
	 * 
	 * @param sipStatus
	 * @return List<Map<String, String>>
	 */	
	public List<Map<String, String>> getPostAipQueue(String sipStatus)
	{
		List<Map<String, String>> currentQueue = new ArrayList<Map<String, String>>();
		Map<String, String> dbRows;
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		setupConnection();
		
		try
		{
			stmt = conn.prepareStatement(buildSelectPostAipQueueStatement(sipStatus));
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows = new HashMap<String, String>();
				
				dbRows.put(DaoOracle.WORKFLOW_EXECUTION_ID, rs.getString(DaoOracle.WORKFLOW_EXECUTION_ID));
				dbRows.put(DaoOracle.AMD_ID, rs.getString(DaoOracle.AMD_ID));
				dbRows.put(DaoOracle.SUBMIT_TIMESTAMP, rs.getString(DaoOracle.SUBMIT_TIMESTAMP));
				dbRows.put(DaoOracle.SOURCE_PATH, rs.getString(DaoOracle.SOURCE_PATH));
				dbRows.put(DaoOracle.SIP_PATH, rs.getString(DaoOracle.SIP_PATH));
				dbRows.put(DaoOracle.SIP_NAME, rs.getString(DaoOracle.SIP_NAME));
				dbRows.put(DaoOracle.SIP_STATUS, rs.getString(DaoOracle.SIP_STATUS));
				dbRows.put(DaoOracle.SIP_ID, rs.getString(DaoOracle.SIP_ID));
				dbRows.put(DaoOracle.DOI, rs.getString(DaoOracle.DOI));
				
				currentQueue.add(dbRows);
			}
			
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();		
		
		return currentQueue;
	}		
	
	
	/**
	 * Queries the queue table for items that are deleted in DSpace
	 *
	 * The results are used to fill an ArrayList. Each entry in the ArrayList 
	 * contains the relevant columns in form of a hashmap
	 * 
	 * @param fsStatus
	 * @param maxItems
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> getDeleteQueue(String fsStatus)
	{
		List<Map<String, String>> deletQueue = new ArrayList<Map<String, String>>();
		Map<String, String> dbRows;
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		setupConnection();
		
		try
		{
			stmt = conn.prepareStatement(buildSelectDeleteQueueStatement(fsStatus, ""));
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows = new HashMap<String, String>();
				
				dbRows.put(DaoOracle.WORKFLOW_EXECUTION_ID, rs.getString(DaoOracle.WORKFLOW_EXECUTION_ID));
				dbRows.put(DaoOracle.AMD_ID, rs.getString(DaoOracle.AMD_ID));
				dbRows.put(DaoOracle.SUBMIT_TIMESTAMP, rs.getString(DaoOracle.SUBMIT_TIMESTAMP));
				dbRows.put(DaoOracle.SOURCE_PATH, rs.getString(DaoOracle.SOURCE_PATH));
				dbRows.put(DaoOracle.SIP_PATH, rs.getString(DaoOracle.SIP_PATH));
				dbRows.put(DaoOracle.SIP_NAME, rs.getString(DaoOracle.SIP_NAME));
				dbRows.put(DaoOracle.SIP_STATUS, rs.getString(DaoOracle.SIP_STATUS));
				
				deletQueue.add(dbRows);
			}
			
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();	
		
		return deletQueue;
	}
	



	/**
	 * Queries the queue table for items that where the file system is ready
	 * to be cleaned up
	 * The results are used to fill an ArrayList. Each entry in the ArrayList 
	 * contains the relevant columns in form of a hashmap
	 * 
	 * @param sipStatus
	 * @return List<Map<String, String>>
	 */		
	public List<Map<String, String>> getFileSystemCleanupQueue(String fsStatus, String dateRange, String maxItems)
	{
		List<Map<String, String>> currentQueue = new ArrayList<Map<String, String>>();
		
		Map<String, String> dbRows;
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		setupConnection();
		
		try
		{
			stmt = conn.prepareStatement(buildSelectFsStatusQueueStatement(fsStatus, dateRange, maxItems));
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows = new HashMap<String, String>();
				
				dbRows.put(DaoOracle.WORKFLOW_EXECUTION_ID, rs.getString(DaoOracle.WORKFLOW_EXECUTION_ID));
				dbRows.put(DaoOracle.AMD_ID, rs.getString(DaoOracle.AMD_ID));
				dbRows.put(DaoOracle.SUBMIT_TIMESTAMP, rs.getString(DaoOracle.SUBMIT_TIMESTAMP));
				dbRows.put(DaoOracle.SOURCE_PATH, rs.getString(DaoOracle.SOURCE_PATH));
				dbRows.put(DaoOracle.SIP_PATH, rs.getString(DaoOracle.SIP_PATH));
				dbRows.put(DaoOracle.SIP_NAME, rs.getString(DaoOracle.SIP_NAME));
				dbRows.put(DaoOracle.SIP_STATUS, rs.getString(DaoOracle.SIP_STATUS));
				dbRows.put(DaoOracle.SIP_ID, rs.getString(DaoOracle.SIP_ID));
				dbRows.put(DaoOracle.DOI, rs.getString(DaoOracle.DOI));
				dbRows.put(DaoOracle.SIP_STATUS_FS, rs.getString(DaoOracle.SIP_STATUS_FS));
				
				currentQueue.add(dbRows);
			}			
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();
		
		return currentQueue;
	}
	
	
	/**
	 * Returns a HashMap containing SIP_NAMEs that have more than one item
	 * with 'FEEDER_UPDATED' status
	 * 
	 * @return HashMap<String, Integer>
	 */
	public Map<String, Integer> getDuplicateUpdatesSameSipname()
	{
		Map<String, Integer> dbRows = new HashMap<String, Integer>();
		
		PreparedStatement stmt;
		ResultSet rs = null;

		String count_variable = "amount";

		setupConnection();	
		
		try
		{
			stmt = conn.prepareStatement(buildSelectDuplicateUpdatesQuery(count_variable));
			
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows.put(rs.getString(DaoOracle.SIP_NAME), rs.getInt(count_variable));
			}			
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();
		
		return dbRows;
	}
	
	
	/**
	 * Queries the tracking table INGEST_TRACKING_RECORD_E_PUB_Q for possible duplicates.
	 * This can happen, when the SIP_STATUS for an update action is set to FEEDER_SUBMITTED
	 * instead of FEEDER_UPDATED. With FEEDER_SUBMITTED, the update logic wants to create a new
	 * item, instead of an update.
	 * See https://dinkum.ethbib.ethz.ch/pages/viewpage.action?pageId=58819828
	 * The results are used to fill an ArrayList. Each entry in the ArrayList
	 * * is an WORKFLOW_EXECUTION_ID with duplicate potential.
	 *
	 * @return List<String>
	 */
	public List<String> getUniqueConstraintIDs()
	{
		List<String> dbRows = new ArrayList<String>();
		PreparedStatement stmt;
		ResultSet rs = null;
		
		setupConnection();
		try
		{
			stmt = conn.prepareStatement(buildSelectUniqueConstraintIDs());
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows.add(rs.getString(DaoOracle.WORKFLOW_EXECUTION_ID));
			}
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		closeConnection();
		
		return dbRows;
	}
	
	
	
	
	
	
	public Set<String> getAmdIdsOfDuplicates(String sipName, String sipStatus)
	{
		Set<String> dbRows = new HashSet<String>();
		
		PreparedStatement stmt;
		ResultSet rs = null;
		
		//logger.debug(buildAmdIdsFromDuplicates(sipName, sipStatus));
		
		setupConnection();
		try
		{
			stmt = conn.prepareStatement(buildAmdIdsOfDuplicates(sipName, sipStatus));
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				dbRows.add(rs.getString(DaoOracle.AMD_ID));
			}
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		closeConnection();				
		
		return dbRows;
	}
	
	
	/**
	 * Updates the status of a row in the queue table. 
	 * New status has to be provided and identification of the row
	 * is done via unique AMD_ID column
	 * 
	 * @param status
	 * @param amdid
	 */
	public void updateStatus(String status, String amdId)
	{
		setupConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildUpdateSipStatusStatement(status, amdId));
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();	
	}
	
	
	/**
	 * Update the file system status of a row in the queue table
	 * New status has to be provided and identification of the row
	 * is done via unique AMD_ID column
	 * 
	 * @param status
	 * @param amdId
	 */
	public void updateFsStatus(String status, String amdId)
	{
		setupConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildUpdateFsStatusStatement(status, amdId));
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		
		closeConnection();
	}
	
	
	/**
	 * Updates the sip id of a row in the queue table.
	 * New sip Id has to be provided and identification of that 
	 * row is done via unique AMD_ID column
	 * 
	 * @param entityId
	 * @param amdid
	 */
	public void updateSipId(String sipId, String amdId)
	{
		setupConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildUpdateSipIdStatement(sipId, amdId));
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();
	}
	
	
	/**
	 * Updates the entity_id of a row in the queue table.
	 * New entity id has to be provided and row is identified 
	 * by amd_id col
	 * 
	 * @param entityId
	 * @param amdId
	 */
	public void updateEntityId(String entityId, String amdId)
	{
		setupConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildUpdateEntityIdStatement(entityId, amdId));
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();
	}

	
	/**
	 * Returns the count of item in queue with the same sip-id that do not
	 * have the status supplied 
	 * 
	 * @param sipId
	 * @param notStatusArrayList
	 * @return int
	 */
	public int countItemsNotStatus(String sipId, List<String> notStatusArrayList)
	{

		PreparedStatement stmt;
		ResultSet rs = null;
		int resultCount = 0;
		
		setupConnection();
		
		try
		{
			stmt = conn.prepareStatement(buildCountItemsNotStatus(sipId, notStatusArrayList));
			rs = stmt.executeQuery();
			rs.next();
			resultCount = rs.getInt(1);
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();
		
		return resultCount;
	}
	
	
	
	
	private String buildAmdIdsOfDuplicates(String sipName, String sipStatus)
	{
		StringBuilder sb = new StringBuilder(450);
		
		/*
		select
		AMD_ID, SUBMIT_TIMESTAMP, SIP_NAME
		from
		INGEST_TRACKING_RECORD_E_PUB_Q
		where
		SIP_NAME = 'ReColl-107185' and 
		SIP_STATUS = 'FEEDER_UPDATED' and 
		SUBMIT_TIMESTAMP != (
			Select 
				MAX(SUBMIT_TIMESTAMP) 
			from 
				INGEST_TRACKING_RECORD_E_PUB_Q 
			where SIP_NAME = 'ReColl-107185' and 
				SIP_STATUS = 'FEEDER_UPDATED')
		ORDER BY SUBMIT_TIMESTAMP ASC
		 */
		
		sb.append("select ");
		sb.append(DaoOracle.AMD_ID);
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_NAME + "='" + sipName + "'");
		sb.append(" and " + DaoOracle.SIP_STATUS + "='" + sipStatus + "'");
		sb.append(" and " + DaoOracle.SUBMIT_TIMESTAMP + " != (");
		
		sb.append("select ");
		sb.append("MAX(" + DaoOracle.SUBMIT_TIMESTAMP + ") ");
		sb.append("from " + DaoOracle.QUEUE_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_NAME + "='" + sipName + "'");
		sb.append(" and " + DaoOracle.SIP_STATUS + "='" + sipStatus + "'");

		sb.append(")");
		sb.append(" order by " + DaoOracle.SUBMIT_TIMESTAMP + " ASC");
		
		
		return sb.toString();
	}
	
	
	
	/**
	 * SQL builder for a query that returns WORKFLOW_EXECUTION_IDs that would result in an
	 * Oracle unique constraint error, because of a wrong SIP_STATUS.
	 * See https://dinkum.ethbib.ethz.ch/pages/viewpage.action?pageId=58819828
	 * 
	 * @return String
	 */
	private String buildSelectUniqueConstraintIDs()
	{
		StringBuilder sb = new StringBuilder(250);
		
		// See Dinkum entry "Unique Constraint error in Research Collection
		// https://dinkum.ethbib.ethz.ch/pages/viewpage.action?pageId=58819828
		
		// SELECT WORKFLOW_EXECUTION_ID, COUNT(*) FROM INGEST_TRACKING_RECORD_E_PUB_Q 
		// WHERE SIP_STATUS = 'FEEDER_SUBMITTED' 
		// OR SIP_STATUS = 'FINISHED' 
		// OR SIP_STATUS = 'FEEDER_PREINGESTED' 
		// GROUP BY WORKFLOW_EXECUTION_ID HAVING COUNT(*) > 1
		
		sb.append("select ");
		sb.append(DaoOracle.WORKFLOW_EXECUTION_ID + ", ");
		sb.append("count(*) ");
		
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		
		sb.append(" where ");
		sb.append(DaoOracle.SIP_STATUS + " = '"+config.getQueueStatusNew()+"'");
		sb.append(" or ");
		sb.append(DaoOracle.SIP_STATUS + " = '"+config.getTrackingIeFished()+"'");
		sb.append(" or ");
		sb.append(DaoOracle.SIP_STATUS + " = '"+config.getQueueStatusPreingested()+"'");		
		sb.append("GROUP BY WORKFLOW_EXECUTION_ID HAVING COUNT(*) > 1");		

		return sb.toString();
	}
	
	
	/**
	 * SQL builder for a query that returns all SIP_NAMEs that have more
	 * than one FEEDER_UPDATED status
	 * 
	 * @param countName
	 * @return String
	 */
	private String buildSelectDuplicateUpdatesQuery(String countName)
	{
		StringBuilder sb = new StringBuilder(250);
		
		sb.append("select ");
		sb.append(DaoOracle.SIP_NAME + ", ");
		sb.append("count(*) as " + countName);
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" where SIP_STATUS = '" + config.getQueueStatusUpdate() + "'");
		sb.append(" group by " + DaoOracle.SIP_NAME + " ");
		sb.append("having count(*) > 1");
		sb.append(" order by " +  DaoOracle.SIP_NAME + " asc");
		//sb.append(" fetch first " + rows + " rows only");

		return sb.toString();
	}	
	
	
	/**
	 * SQL builder for a query that returns the number of rows
	 * that have the same sip-id but do not have the status 
	 * supplied in the notStatusArrayList
	 * 
	 * @param sipId
	 * @param notStatusArrayList
	 * @return String
	 */
	private String buildCountItemsNotStatus(String sipId, List<String> notStatusArrayList)
	{
		StringBuilder sb = new StringBuilder(200);
		HelperDaoOracle helper = new HelperDaoOracle(config);

		sb.append("select ");
		sb.append("count(*)");
	
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_STATUS + " NOT IN (" + helper.buildInQuery(notStatusArrayList) + ")");
		sb.append(" AND " + DaoOracle.WORKFLOW_EXECUTION_ID + "='" + sipId + "'");
		
		return sb.toString();
	}
	
	
	/**
	 * SQL builder for entity_id update using amd_id
	 * as key
	 * 
	 * @param entityId
	 * @param amdid
	 * @return
	 */
	private String buildUpdateEntityIdStatement(String entityId, String amdid)
	{
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("update ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" set ");
		sb.append(DaoOracle.ENTITY_ID);
		sb.append("='" + entityId + "'");
		sb.append(", " + new HelperDaoOracle(config).buildDateUpdateSqlSnippet(DaoOracle.UPDATE_DT));
		sb.append(" where ");
		sb.append(DaoOracle.AMD_ID + " = '" + amdid + "'");
				
		return sb.toString();
	}	
	
	
	
	/**
	 * SQL Update statement for SIP_ID is built and returned
	 * 
	 * @param ripId
	 * @param amdid
	 * @return String
	 */
	private String buildUpdateSipIdStatement(String ripId, String amdid)
	{
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("update ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" set ");
		sb.append(DaoOracle.SIP_ID);
		sb.append("='" + ripId + "'");
		sb.append(", " + new HelperDaoOracle(config).buildDateUpdateSqlSnippet(DaoOracle.UPDATE_DT));
		sb.append(" where ");
		sb.append(DaoOracle.AMD_ID + " = '" + amdid + "'");
		
		logger.debug(sb.toString());
				
		return sb.toString();
	}


	/**
	 * SQL Update statement for SIP_STATUS is built and returned
	 * 
	 * @param status
	 * @param amdid
	 * @return String
	 */
	private String buildUpdateSipStatusStatement(String status, String amdId)
	{
		StringBuilder sb = new StringBuilder(200);
				
		sb.append("update ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" set ");
		sb.append(DaoOracle.SIP_STATUS);
		sb.append("='" + status + "'");
		sb.append(", " + new HelperDaoOracle(config).buildDateUpdateSqlSnippet(DaoOracle.UPDATE_DT));
		sb.append(" where ");
		sb.append(DaoOracle.AMD_ID + " = '" + amdId + "'");
				
		return sb.toString();
	}
	
	
	/**
	 * SQL Update statement for SIP_STATUS_FS and 
	 * 
	 * @param status
	 * @param amdId
	 * @return String
	 */
	private String buildUpdateFsStatusStatement(String status, String amdId)
	{
		StringBuilder sb = new StringBuilder(200);
				
		sb.append("update ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" set ");
		sb.append(DaoOracle.SIP_STATUS_FS);
		sb.append("='" + status + "'");
		sb.append(", " + new HelperDaoOracle(config).buildDateUpdateSqlSnippet(DaoOracle.UPDATE_DT));
		sb.append(" where ");
		sb.append(DaoOracle.AMD_ID + " = '" + amdId + "'");
				
		return sb.toString();
	}	
	
	
	/**
	 * SQL Select statement for querying the queue table is built and returned 
	 * 
	 * @param sipStatus
	 * @param rows
	 * @return String
	 */
	private String buildSelectSubmittedQueueStatement(String sipStatus, String rows)
	{
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("select ");
		sb.append(DaoOracle.WORKFLOW_EXECUTION_ID + ", ");
		sb.append(DaoOracle.AMD_ID + ", ");
		sb.append(DaoOracle.SUBMIT_TIMESTAMP + ", ");
		sb.append(DaoOracle.SOURCE_PATH + ", ");
		sb.append(DaoOracle.SIP_STATUS + ", ");
		sb.append(DaoOracle.SIP_NAME + ", ");
		sb.append(DaoOracle.DOI + ", ");
		sb.append(DaoOracle.SIP_PATH);
	
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_STATUS + " = '" + sipStatus + "'");
		//sb.append(" AND " + DaoOracle.WORKFLOW_EXECUTION_ID + "='e-pub-6777'"); 
		sb.append(" order by " + DaoOracle.SUBMIT_TIMESTAMP + " asc");
		sb.append(" fetch first " + rows + " rows only");

		return sb.toString();
	}	
	
	
	/**
	 * SQL Select statement for querying the queue table for FEEDER_DELETED record
	 * 
	 * @param fsStatus
	 * @param maxItems (not used atm)
	 * @return String
	 */
	private String buildSelectDeleteQueueStatement(String fsStatus, String maxItems)
	{
		//maxItems is not used
		
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("select ");
		sb.append(DaoOracle.WORKFLOW_EXECUTION_ID + ", ");
		sb.append(DaoOracle.AMD_ID + ", ");
		sb.append(DaoOracle.SUBMIT_TIMESTAMP + ", ");
		sb.append(DaoOracle.SOURCE_PATH + ", ");
		sb.append(DaoOracle.SIP_STATUS + ", ");
		sb.append(DaoOracle.SIP_NAME + ", ");
		sb.append(DaoOracle.SIP_PATH);
	
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_STATUS + " = '" + fsStatus + "'");

		return sb.toString();
	}	
	
	
	/**
	 * SQL Select statement for querying the queue table for AIP_WAITING records
	 * also return the RIPID that is stored in SIP_ID col
	 * 
	 * @param sipStatus
	 * @return String
	 */
	private String buildSelectPostAipQueueStatement(String sipStatus)
	{
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("select ");
		sb.append(DaoOracle.WORKFLOW_EXECUTION_ID + ", ");
		sb.append(DaoOracle.AMD_ID + ", ");
		sb.append(DaoOracle.SUBMIT_TIMESTAMP + ", ");
		sb.append(DaoOracle.SOURCE_PATH + ", ");
		sb.append(DaoOracle.SIP_STATUS + ", ");
		sb.append(DaoOracle.SIP_NAME + ", ");
		sb.append(DaoOracle.SIP_PATH + ", ");
		sb.append(DaoOracle.DOI + ", ");
		sb.append(DaoOracle.SIP_ID);
	
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_STATUS + " = '" + sipStatus + "'");

		return sb.toString();
	}		

	
	/**
	 * SQL select statement for querying the queue table rows with a supplied 
	 * SIP_STATUS_FS and UPDATE_DT smaller that supplied update date
	 * 
	 * @param fsStatus
	 * @param updateDate 
	 * @return String
	 */
	private String buildSelectFsStatusQueueStatement(String fsStatus, String dateRange, String maxItems)
	{
		if(dateRange.trim().isEmpty())
		{
			dateRange = "30";
		}
		
		StringBuilder sb = new StringBuilder(300);

		sb.append("select ");
		sb.append(DaoOracle.WORKFLOW_EXECUTION_ID + ", ");
		sb.append(DaoOracle.AMD_ID + ", ");
		sb.append(DaoOracle.SUBMIT_TIMESTAMP + ", ");
		sb.append(DaoOracle.SOURCE_PATH + ", ");
		sb.append(DaoOracle.SIP_STATUS + ", ");
		sb.append(DaoOracle.SIP_STATUS_FS + ", ");
		sb.append(DaoOracle.SIP_NAME + ", ");
		sb.append(DaoOracle.SIP_PATH + ", ");
		sb.append(DaoOracle.DOI + ", ");
		sb.append(DaoOracle.SIP_ID);
	
		sb.append(" from ");
		sb.append(DaoOracle.QUEUE_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_STATUS_FS + " = '" + fsStatus + "'");
		sb.append(" and (");
		sb.append(DaoOracle.UPDATE_DT + " < TRUNC(SYSDATE)-" + dateRange + " OR " + DaoOracle.UPDATE_DT + " is null)");
		sb.append(" fetch first " + maxItems + " rows only");
		
		return sb.toString();
	}
	
	
	/**
	 * Helper method that reads out the sql file containing the query
	 * that finds all items for the update queue
	 * Placeholders #LIMIT# and #QUEUETABLE# will be replaced
	 * 
	 * @param rows
	 * @return String
	 */
	private String buildSelectUpdatedQueueStatement(String rows)
	{
		String query;
		
		HelperDaoOracle helper = new HelperDaoOracle(config);
		query = helper.readoutUpdateQueueFile().replace("#LIMIT#", rows).replace("#QUEUETABLE#", DaoOracle.QUEUE_TABLE);

		return query;
	}
	
}
