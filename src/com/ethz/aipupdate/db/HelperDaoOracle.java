package com.ethz.aipupdate.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.ethz.aipupdate.helper.ConfigProperties;

public class HelperDaoOracle extends DaoOracle
{

	public HelperDaoOracle(ConfigProperties config)
	{
		super(config);
	}

	
	public String buildUpdateTrackingQuery()
	{
		return "";
	}
	
	
	protected String readoutUpdateQueueFile()
	{
		String sqlPath = System.getProperty("user.dir") + config.getQueueUpdateFile();
		String content = "";
		
		
		if(new File(sqlPath).exists())
		{
			try
			{
				content = new String(Files.readAllBytes(Paths.get(sqlPath)));
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			logger.fatal("Cannot open sql file " + sqlPath);
			System.exit(1);
		}		
		
		return content;
	}
	
	
	/**
	 * convert array into string
	 * each item wrapped in single quotes and 
	 * separated by comma 
	 * 
	 * @param elementsForIn
	 * @return String
	 */
	protected String buildInQuery(List<String> elementsForIn)
	{
		StringBuilder query = new StringBuilder(200);
	
		int i = 1;
		
		for(String element : elementsForIn)
		{
			query.append("'" + element +  "'");
			query.append(i==elementsForIn.size() ? "" : ", ");
			
			i++;
		}
		
		return query.toString();
	}
	
}
