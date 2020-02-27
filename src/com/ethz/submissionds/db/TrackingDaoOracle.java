package com.ethz.submissionds.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.ethz.submissionds.helper.ConfigProperties;

/**
 * Data Access Object for Oracle tracking table
 * 
 * @author Lars Haendler
 *
 */

public class TrackingDaoOracle extends DaoOracle
{

	/**
	 * Constructor
	 * 
	 * @param config
	 */
	public TrackingDaoOracle(ConfigProperties config)
	{
		super(config);
	}

	
	/**
	 * Insert a new entry in Tracking table containing defined
	 * data from Queue table 
	 * 
	 * @param entry
	 */
	public void insertTrackingEntry(Map<String, String> entry)
	{
		setupConnection();
		
		logger.debug(buildInsertQuery(entry));

		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildInsertQuery(entry));
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
	 * Builds and returns SQL Insert Query for 
	 * new entry in the tracking table, data structure is pre-defined
	 * 
	 * @param entry
	 * @return String
	 */
	private String buildInsertQuery(Map<String, String> entry)
	{
		StringBuilder insertQuery = new StringBuilder(300);
		insertQuery.append("insert into " + DaoOracle.TRACKING_TABLE);
		insertQuery.append(" (");
		
		insertQuery.append(DaoOracle.WORKFLOW_EXECUTION_ID + ","); 	//1
		insertQuery.append(DaoOracle.WORKSPACE_ID + ","); 				//2
		insertQuery.append(DaoOracle.AMD_ID + ","); 						//3
		insertQuery.append(DaoOracle.SUBMIT_TIMESTAMP + ",");			//4
		insertQuery.append(DaoOracle.SOURCE_PATH + ","); 				//5
		insertQuery.append(DaoOracle.SIP_PATH + ","); 					//6
		insertQuery.append(DaoOracle.SIP_TYPE + ","); 					//7
		insertQuery.append(DaoOracle.SIP_STATUS + ",");					//8
		insertQuery.append(DaoOracle.SIP_NAME);							//9

		insertQuery.append(") ");
		insertQuery.append("values (");
		
		insertQuery.append("'" + entry.get(DaoOracle.WORKFLOW_EXECUTION_ID) + "',");			//1
		insertQuery.append("'" + entry.get(DaoOracle.WORKSPACE_ID) + "',"); 						//2
		insertQuery.append("'" + entry.get(DaoOracle.AMD_ID) + "', ");								//3
		insertQuery.append("to_date('" + entry.get(DaoOracle.SUBMIT_TIMESTAMP) + "', '" + config.getOracleTimeFormat() + "'), ");	//4
		insertQuery.append("'" + entry.get(DaoOracle.SOURCE_PATH) + "', "); 						//5
		insertQuery.append("'" + entry.get(DaoOracle.SIP_PATH) + "',"); 							//6 
		insertQuery.append("'" + entry.get(DaoOracle.SIP_TYPE) + "',"); 							//7
		insertQuery.append("'" + entry.get(DaoOracle.SIP_STATUS) + "',"); 						//8
		insertQuery.append("'" + entry.get("SIP_NAME") + "'"); 										//9
		
		insertQuery.append(")");
		
		return insertQuery.toString();
	}

	
	/**
	 * Update SIP_STATUS of a tracking table row with supplied
	 * status, AMD_ID is used to indentify row
	 * 
	 * @param status
	 * @param amdid
	 */
	public void updateStatus(String status, String amdid)
	{
		setupConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildUpdateSipStatusStatement(status, amdid));
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
	 * Checks whether or not a Sip Name has occurences in tracking table
	 * 
	 * @param sipName
	 * @return
	 */
	public boolean hasIeFromSipName(String sipName)
	{
		boolean hasItems = false;
		
	
		setupConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildSelectIeFromSipNameStatement(sipName));
			ResultSet rs = stmt.executeQuery();

			if(rs.next())
			{
				hasItems = true;
			}
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();		
		
		return hasItems;
	}
	
	
	/**
	 * Returns IE Entity Id from tracking table 
	 * with provided SIP_NAME 
	 * 
	 * @param sipName
	 * @return String
	 */
	public String getIeFromSipName(String sipName)
	{
		String ie = "";
		
		setupConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(buildSelectIeFromSipNameStatement(sipName));
			ResultSet rs = stmt.executeQuery();

			if(rs.next())
			{
				ie = rs.getString(DaoOracle.ENTITY_ID);
			}
			else
			{
				logger.fatal("No record with " + DaoOracle.SIP_NAME + "='" + sipName + "' in " + config.getQueryTableTracking());
				System.exit(1);
			}
		}
		catch (SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		closeConnection();			
		
		return ie;
	}
	
	
	/**
	 * Returns and builds SQL update statement
	 * updates a tracking table row with supplied status
	 * 
	 * @param status
	 * @param amdid
	 * @return String
	 */
	private String buildUpdateSipStatusStatement(String status, String amdid)
	{
		StringBuilder sb = new StringBuilder(200);
				
		sb.append("update ");
		sb.append(DaoOracle.TRACKING_TABLE);
		sb.append(" set ");
		sb.append(DaoOracle.SIP_STATUS);
		sb.append("='" + status + "'");
		sb.append(" where ");
		sb.append(DaoOracle.AMD_ID + " = '" + amdid + "'");
				
		return sb.toString();
	}	
	
	
	/**
	 * Returns and builds SQL query to retrieve ENTITY_ID
	 * with provided SIP_NAME from tracking table
	 * 
	 * @param sipName
	 * @return String
	 */
	private String buildSelectIeFromSipNameStatement(String sipName)
	{
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("select ");
		sb.append(DaoOracle.ENTITY_ID);
		sb.append(" from ");
		sb.append(DaoOracle.TRACKING_TABLE);
		sb.append(" where ");
		sb.append(DaoOracle.SIP_NAME + " = '" + sipName + "'");
		sb.append(" and ");
		sb.append(DaoOracle.SIP_STATUS + " = '" + config.getTrackingIeFished() + "'");
		
		return sb.toString();
	}

}
