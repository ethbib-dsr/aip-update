package com.ethz.submissionds.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Helper class with static methods for file based operations 
 * 
 * https://bitbucket.org/ethbib_bit/aip-update
 * 
 * @author Lars Haendler
 *
 */
public class FileOperations
{

	private static final Logger logger = LogManager.getLogger();
	
	private FileOperations()
	{
		
	}
	
	
	/**
	 * Creates a new file and puts content in ti
	 * 
	 * @param fileLocation
	 * @param content
	 */
	public static void createNewFileWithContent(String fileLocation, String content)
	{
		createNewFile(fileLocation);
		saveContentToFile(fileLocation, content);
	}
	
	
	/**
	 * Return the content of a text file
	 * 
	 * @param fileLocation
	 * @return
	 */
	public static String readoutFile(String fileLocation)
	{
		String fileContent = "";
		try
		{
			fileContent = new String(Files.readAllBytes(Paths.get(fileLocation)));
		}
		catch (IOException e)
		{
			logger.error(e.getMessage());
			System.exit(1);
		}		
		
		return fileContent;		
	}
	
	
	/**
	 * Add string content to an existing file
	 * 
	 * @param fileLocation
	 * @param content
	 */
	private static void saveContentToFile(String fileLocation, String content)
	{
		try
		{
			Files.write(Paths.get(fileLocation), content.getBytes());
			logger.debug("mets successfully created at " + fileLocation);
		}
		catch (IOException e)
		{
			logger.fatal("Could not add content to file " + fileLocation);
			logger.fatal(e.getMessage());
			System.exit(1);
		}
	}
	
	
	/**
	 * Create a new empty file
	 * 
	 * @param fileLocation
	 */
	private static void createNewFile(String fileLocation)
	{
		File theFile = new File(fileLocation);
		
		if(theFile.exists())
		{
			theFile.delete();
		}
		
		try
		{
			theFile.createNewFile();
		}
		catch (IOException e1)
		{
			logger.fatal("Could not create file " + fileLocation);
			System.exit(1);
		}
	}
	
	
	/**
	 * Recursive file/directory deletion
	 * 
	 * @param file
	 */
	public static void recursiveFileDelete(File file)
	{
		if(!file.exists())
			return;
		
		
		if(file.isDirectory())
		{
			for(File f : file.listFiles())
			{
				recursiveFileDelete(f);
			}
		}
		
		file.delete();
	}

}
