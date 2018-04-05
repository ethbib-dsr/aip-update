package com.ethz.aipupdate;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * AIP update application
 * 
 * Files from a storage location will be checked and if needed
 * updated in the Rosetta repository by using the IE update 
 * web service 
 * 
 * https://bitbucket.org/ethbib_bit/aip-update
 * 
 * @author Lars Haendler
 *
 */

public class AppStarter {

	private static final Logger logger = LogManager.getLogger();
	private static ServerSocket s;
	private static final int PORT = 7474;


	public static void main(String[] args) {
		
		logger.info("AIP Update Application started");
		
		
		//check that no two instances of the application run at once
		try
		{
			s = new ServerSocket(PORT, 10, InetAddress.getLocalHost());
		}
		catch (UnknownHostException e)
		{
			logger.error("Application already running");
			logger.error(e.getMessage());
			System.exit(2);
		}
		catch (IOException e)
		{
			logger.error("Unexpected error: " + e.getMessage());
			System.exit(2);
		}		
		
		
		//get relative path to config.properties from App paramter
		if(args.length == 0)
		{
			logger.error("No path to config.properties supplied. Relative path has to be supplied as an argument when running AppStarter.");
		}
		else
		{
			String confPath = System.getProperty("user.dir") + args[0];
			
			if(new File(confPath).exists())
			{
				final AipUpdateSingleton app = AipUpdateSingleton.getInstance(confPath);
				logger.debug("AipUpdateSingleton created");
				app.init();
			}
			else
			{
				logger.error("No working path to config.properties found.");
			}
		}		
		
		
		
		//close down connection that makes sure the application is a singleton
		try
		{
			s.close();
		}
		catch (IOException e)
		{
			logger.error("Unexpected error: " + e.getMessage());
			System.exit(2);
		}		
		
		
		logger.info("AIP Update Application finished");
	}
	
}