package com.ethz.submissionds.helper;

import java.io.File;
import java.util.Properties;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpHelper
{
	public final static String CUR_DIR = ".";
	public final static String AB_DIR = "..";
	
	private ConfigProperties config;
	private static final Logger logger = LogManager.getLogger();
	
	private JSch jsch;
	private Session session;
	private Properties sftpConf;
	private Channel channel;
	private ChannelSftp sftpChannel;

	
	/**
	 * Constructor
	 * 
	 * @param config
	 */
	public SftpHelper(ConfigProperties config)
	{
		this.config = config;
		initializeSftp();
	}
	
	
	/**
	 * Copy a remote directory with all content
	 * to a local destination
	 * 
	 * @param remoteDir
	 * @param localDir
	 */
	public void copy(String remoteDir, String localDir)
	{
		try
		{
			File currentDir = new File(remoteDir);
			copyDir(remoteDir, localDir +  File.separator + currentDir.getName());
		}
		catch (SftpException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
	}
	
	
	/**
	 * Recursive copy of folder and content
	 * 
	 * @param remoteDir
	 * @param localDir
	 * @throws SftpException
	 */
	private void copyDir(String remoteDir, String localDir) throws SftpException
	{
		Vector<ChannelSftp.LsEntry> dirList = sftpChannel.ls(remoteDir);
		
		for(ChannelSftp.LsEntry item : dirList)
		{
			if(! (item.getFilename().equals(SftpHelper.AB_DIR) || item.getFilename().equals(SftpHelper.CUR_DIR)))
			{
				File currentFile = new File(localDir + File.separator + item.getFilename());
				String remoteFilePath = remoteDir + File.separator + item.getFilename();
				String localeFilePath = localDir + File.separator + item.getFilename();
				
				if(item.getAttrs().isDir())
				{
					currentFile.mkdirs();
					copyDir(remoteFilePath, localeFilePath);
				}
				else
				{
					sftpChannel.get(remoteFilePath, localeFilePath);
				}
			}
		}		
	}
	
	
	/**
	 * Wrapper for deletion of directoy including subdirectories
	 * 
	 * @param remoteDir
	 */
	public void deleteDir(String remoteDir)
	{
		recursiveDirectoryDelete(remoteDir);
	}
	
	
	/**
	 * Delete a SFTP directory including ALL its contents
	 * "With great power comes great responsibility"
	 * Make sure you provide the correct path
	 * 
	 * @param remoteDir
	 */
	private void recursiveDirectoryDelete(String remoteDir)
	{
		try
		{
			if(isDirectory(remoteDir))
			{
				@SuppressWarnings("unchecked")
				Vector<ChannelSftp.LsEntry> dirList = sftpChannel.ls(remoteDir);
				
				for(ChannelSftp.LsEntry entry : dirList)
				{
					if(!(entry.getFilename().equals(".") || entry.getFilename().equals("..")))
					{
						if(entry.getAttrs().isDir())
						{
							recursiveDirectoryDelete(remoteDir + entry.getFilename() + File.separator);
						}
						else
						{
							sftpChannel.rm(remoteDir + entry.getFilename());
						}
					}
				}

				sftpChannel.cd("..");
				sftpChannel.rmdir(remoteDir);
			}
		}
		catch (SftpException e)
		{
			logger.warn("SFTP problem: " + e.getMessage());
		}
	}
	
	
	/**
	 * Checks whether or not the current path is a directory
	 * 
	 * @param remoteDirectory
	 * @return
	 * @throws SftpException
	 */
	private boolean isDirectory(String remoteDirectory) throws SftpException
	{
		return sftpChannel.stat(remoteDirectory).isDir();
	}
	
	
	/**
	 * Deletes parent directory if it is empty
	 * 
	 * @param remoteDirectory
	 */
	public void deleteParentIfEmpty(String remoteDirectory)
	{
		String parentPath = DataHelper.getParentDirectoryPath(remoteDirectory);
		logger.debug("parent: " + parentPath);
		
		try
		{
			Vector<ChannelSftp.LsEntry> dirList = sftpChannel.ls(parentPath);
			
			if(isDirectoryEmpty(dirList))
			{
				logger.debug("remove parent directory");
				sftpChannel.rmdir(parentPath);
			}
			else
			{
				logger.warn("parent directory not empty, cannot be removed ");
			}
		}
		catch (SftpException e)
		{
			logger.warn("SFTP problem: " + e.getMessage());
		}
	}
	
	
	/**
	 * Check if a directory is empty while ignoring "." and ".."
	 * 
	 * @param directoryListing
	 * @return boolean
	 */
	private boolean isDirectoryEmpty(Vector<ChannelSftp.LsEntry> directoryListing)
	{
		boolean isEmpty = true;
		
		for(ChannelSftp.LsEntry entry : directoryListing)
		{
			if(!(entry.getFilename().equals(".") || entry.getFilename().equals("..")))
			{
				isEmpty = false;
			}
		}	
		
		return isEmpty;
	}
	
	
	/**
	 * Initialize SFTP connection with the server
	 * 
	 */
	public void initializeSftp()
	{
		jsch = new JSch();

		try
		{
			session = jsch.getSession(config.getSftpUser(), config.getSftpHost(), config.getSftpPort());
		}
		catch (JSchException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}
		
		sftpConf = new Properties();
		sftpConf.put("StrictHostKeyChecking", "no");
		
		session.setPassword(config.getSftpPassword());
		session.setConfig(sftpConf);
		
		try
		{
			session.connect();
	      channel = session.openChannel("sftp");
	      channel.connect();
		}
		catch (JSchException e)
		{
			logger.fatal(e.getMessage());
			System.exit(1);
		}

      sftpChannel = (ChannelSftp)channel;
	}

	
	/**
	 * Close SFTP connection with the server
	 * 
	 */
	public void closeSftpChannel()
	{
      sftpChannel.exit();
		channel.disconnect();
		
		session.disconnect();
	}	
	
}
