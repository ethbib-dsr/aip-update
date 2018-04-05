package com.ethz.aipupdate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ethz.aipupdate.helper.ConfigProperties;

/**
 * Data Access Object superclass for Oracle connectivity
 * 
 * definition of constants for reuse in sub classes 
 * 
 * @author Lars Haendler
 *
 */
public class DaoOracle
{

	protected static final Logger logger = LogManager.getLogger();
	protected ConfigProperties config;
	protected Connection conn = null;
	
	protected static String TRACKING_TABLE;
	protected static String QUEUE_TABLE;
	
	public final static String WORKFLOW_EXECUTION_ID = "WORKFLOW_EXECUTION_ID";
	public final static String WORKSPACE_ID = "WORKSPACE_ID";
	public final static String AMD_ID = "AMD_ID";
	public final static String SUBMIT_TIMESTAMP = "SUBMIT_TIMESTAMP";
	public final static String SOURCE_PATH = "SOURCE_PATH";
	public final static String SIP_PATH = "SIP_PATH";
	public final static String SIP_TYPE = "SIP_TYPE";
	public final static String SIP_STATUS = "SIP_STATUS";
	public final static String SIP_NAME = "SIP_NAME";
	public final static String SFTP_SOURCE = "SFTP_SOURCE";
	public final static String SIP_UPDATE_PATH = "SIP_UPDATE_PATH";
	public final static String ENTITY_ID = "ENTITY_ID";
	public final static String SIP_ID = "SIP_ID";
	public final static String DOI = "DOI";
	public final static String SIP_STATUS_FS = "SIP_STATUS_FS";
	public final static String UPDATE_DT = "UPDATE_DT";
	
	
	/**
	 * Constructor that initializes 
	 * 
	 * @param config
	 */
	public DaoOracle(ConfigProperties config)
	{
		this.config = config;
		TRACKING_TABLE = config.getQueryTableTracking();
		QUEUE_TABLE = config.getQueryTableQueue();
	}

	
	/**
	 * Opens JDBC Oracle connection
	 * 
	 */
	protected void setupConnection()
	{

		try
		{
        	Class.forName(config.getDbDriverName());			
			conn = DriverManager.getConnection(config.getDbConnectionUrl(), config.getDbUsername(), config.getDbPassword());
		}
		catch (ClassNotFoundException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		catch(SQLException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
	}		
	
	
	
	/**
	 * Closes JDBC Oracle connection
	 * 
	 */
	protected void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
	}
	
	
	/**
	 * Returns null without quotes if String contains the
	 * word "null"
	 * 
	 * @param insertValue
	 * @return String
	 */
	protected String handleNullInsertValue(String insertValue)
	{
		String returnVal = "";
		
		if(insertValue == null || insertValue.toLowerCase().equals("null"))
		{
			returnVal = "null";
		}
		else
		{
			returnVal = "'" + insertValue + "'";
		}
		
		return returnVal;
	}
	
	
}
