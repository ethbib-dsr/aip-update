package com.ethz.submissionds.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ethz.submissionds.helper.ConfigProperties;

public class HelperDaoOracle extends DaoOracle
{

	/**
	 * Construtor that needs config.propperies to work
	 * 
	 * @param config
	 */
	public HelperDaoOracle(ConfigProperties config)
	{
		super(config);
	}

	
	/**
	 * building a snippet that includes to_date(currentdate, OracleTimestamp
	 * e.g.
	 * UPDATE_DT=to_date('20200220-14:34:47', 'YYYYMMDD-HH24:MI:SS')
	 * 
	 * @param updateCol
	 * @return
	 */
	public String buildDateUpdateSqlSnippet(String updateCol)
	{
		// UPDATE_DT=to_date('20200220-14:34:47', 'YYYYMMDD-HH24:MI:SS')
		
		DateFormat dateFormat = new SimpleDateFormat(config.getJavaTimeFormat());
		Date currentDate = new Date();
		
		StringBuilder sb = new StringBuilder (100);
		
		sb.append(updateCol + "=to_date('");
		sb.append(dateFormat.format(currentDate) + "', '");
		sb.append(config.getOracleTimeFormat() + "')");
		
		return sb.toString();
	}
	
	
	/**
	 * Readout sql file from conf directory 
	 * file contains lenghty sql statements to find all 
	 * current-run updates 
	 * 
	 * @return
	 */
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
