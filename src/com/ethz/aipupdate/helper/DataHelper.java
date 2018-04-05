package com.ethz.aipupdate.helper;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.ethz.aipupdate.beans.DcMetaDataBean;
import com.ethz.aipupdate.db.DaoOracle;
import com.exlibris.dps.IEWebServices_PortType;
import com.exlibris.dps.IEWebServices_ServiceLocator;
import com.jcraft.jsch.Logger;


public class DataHelper
{

	/**
	 * Defeat instantiation
	 * 
	 */
	private DataHelper()
	{
		// defeat instantiation
	}
	
	
	public static IEWebServices_PortType getRosettaWebservice(String webServiceUrl)
	{
	
		IEWebServices_PortType wsProxy = null;
		IEWebServices_ServiceLocator locator = new IEWebServices_ServiceLocator();
		try
		{
			wsProxy = locator.getIEWebServicesPort(new URL(webServiceUrl));
		}
		catch (MalformedURLException | ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return wsProxy;
	}
	
	
	/**
	 * compare if a given string start with another string
	 * 
	 * @param message
	 * @param toCompare
	 * @return boolean
	 */
	public static boolean startsWithString(String message, String toCompare)
	{
		return message.startsWith(toCompare);
	}
	
	
	/**
	 * Replaces any double forward slashes with single
	 * forward slashes and removes double backward slashes
	 * completely
	 * 
	 * This way it will only run on Unix environment
	 * 
	 * @param path
	 * @return String
	 */
	public static String cleanupPath(String path)
	{
		return path.replace("//", "/").replace("\\", "");
	}
	
	
	/**
	 * Adds a trailing slash at the end of the provided
	 * String value
	 * 
	 * @param inputPathwsProxy
	 * @return String
	 */
	public static String addTrailingSlash(String inputPath)
	{
		String returnPath = inputPath;
		
		if(!inputPath.endsWith(File.separator))
		{
			returnPath += File.separator;
		}
			
		return returnPath;
	}
	
	
	/**
	 * Generic extractor for parent directory path
	 * 
	 * @param inputPath
	 * @return String
	 */
	public static String getParentDirectoryPath(String inputPath)
	{
		
		if(!inputPath.endsWith("/"))
		{
			System.out.println("path does not end with '/': " + inputPath);
			System.exit(1);
			
		}
		
		String resultString = "";
		String regEx = "(.*\\/)\\S+\\/$";
		Pattern regexPattern = Pattern.compile(regEx);
		Matcher match = regexPattern.matcher(inputPath);
		 
		if(match.find()) {
			resultString = match.group(1);
		}	
		
		return resultString;
	}

	
	/**
	 * builds xml nodes of equal order from a list of DcMetaDataBeans
	 * 
	 * @param dcBeanList
	 * @return String
	 */
	public static String buildMetaDataContent(List<DcMetaDataBean> dcBeanList)
	{
		StringBuilder sb = new StringBuilder(2000);
		
		for(DcMetaDataBean dcItem : dcBeanList)
		{
			sb.append("<" + dcItem.getElementName() + ">");
			sb.append(StringEscapeUtils.escapeXml11(dcItem.getElementValue()));
			sb.append("</" + StringEscapeUtils.escapeXml11(dcItem.getElementName()) + ">");
			sb.append(System.getProperty("line.separator"));
		}
	
		return sb.toString();
	}
	
	
	/**
	 * Returns the value of a key in a DcMetaDataBean arraylist
	 * Caution: keys can be duplicate, should only be used when 
	 * uniqueness is guaranteed 
	 * 
	 * @param metaDataBeanList
	 * @param key
	 * @return String
	 */
	public static String getValueFromMetaDataBeanList(List<DcMetaDataBean> metaDataBeanList, String key)
	{
		String returnVal = "";
		
		if(!metaDataBeanList.isEmpty())
		{
			for(DcMetaDataBean metaDataBean : metaDataBeanList)
			{
				if(metaDataBean.getElementName().equals(key))
				{
					returnVal = metaDataBean.getElementValue();
					break;
				}
			}
		}
		
		return returnVal;
	}
	
	
	
	/**
	 * Updates the provided database entries accordingly 
	 * and add additional key-value-pairs
	 * 
	 * @param dbRow
	 * @return Map<String, String>
	 */
	public static Map<String, String> updateDataForAipUpdate(Map<String, String> dbRow, ConfigProperties config)
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
		//generate the actual sip location in the file system
		dbRow.put(DaoOracle.SIP_UPDATE_PATH, DataHelper.addTrailingSlash(config.getStorageUpdate() + dbRow.get(DaoOracle.AMD_ID)));
		//do nothing
		dbRow.put(DaoOracle.DOI, dbRow.get(DaoOracle.DOI));

		return dbRow;
	}		
	
	
}
