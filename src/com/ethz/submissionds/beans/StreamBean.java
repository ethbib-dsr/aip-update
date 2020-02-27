package com.ethz.submissionds.beans;

public class StreamBean
{

	private String fileName;
	private String md5Hash;
	private String extension;
	private String fileSize;
	private String originalFileName;
	private String filePid;
	private String addLocation;
	

	public StreamBean()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Short constructor for dspace mets
	 * 
	 * @param fileName
	 * @param md5Hash
	 */
	public StreamBean(String fileName, String md5Hash, String originalName, String addLocation)
	{
		this.fileName = fileName;
		this.md5Hash = md5Hash;
		this.originalFileName = originalName;
		this.addLocation = addLocation;
	}
	
	/**
	 * long constructor for rosetta mets
	 * 
	 * @param fileName
	 * @param md5Hash
	 * @param extension
	 * @param fileSize
	 */
	public StreamBean(String fileName, String md5Hash, String extension, String fileSize, String filePid)
	{
		this.fileName = fileName;
		this.md5Hash = md5Hash;
		this.extension = extension;
		this.fileSize = fileSize;
		this.filePid = filePid;
	}

	/**
	 * getter fileName
	 * 
	 * @return String
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * setter fileName
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * getter md5
	 * 
	 * @return String
	 */
	public String getMd5Hash()
	{
		return md5Hash;
	}

	/**
	 * setter md5
	 * 
	 * @param md5Hash
	 */
	public void setMd5Hash(String md5Hash)
	{
		this.md5Hash = md5Hash;
	}

	/**
	 * getter file extension
	 * 
	 * @return String
	 */
	public String getExtension()
	{
		return extension;
	}

	/**
	 * setter file extensions
	 * 
	 * @param extension
	 */
	public void setExtension(String extension)
	{
		this.extension = extension;
	}
	
	/**
	 * getter file size
	 * 
	 * @return String
	 */
	public String getFileSize()
	{
		return fileSize;
	}

	/**
	 * setter file size
	 * 
	 * @param fileSize
	 */
	public void setFileSize(String fileSize)
	{
		this.fileSize = fileSize;
	}

	/**
	 * getter original file name
	 * 
	 * @return String
	 */
	public String getOriginalFileName()
	{
		return originalFileName;
	}

	/**
	 * setter original file name
	 * 
	 * @param originalFileName
	 */
	public void setOriginalFileName(String originalFileName)
	{
		this.originalFileName = originalFileName;
	}

	/**
	 * Setter file pid
	 * 
	 * @return String
	 */
	public String getFilePid()
	{
		return filePid;
	}

	/**
	 * Getter file pid
	 * 
	 * @param filePid
	 */
	public void setFilePid(String filePid)
	{
		this.filePid = filePid;
	}

	/**
	 * Getter for add location on server
	 * 
	 * @return
	 */
	public String getAddLocation()
	{
		return addLocation;
	}

	/**
	 * Setter for add location on server
	 * 
	 * @param addLocation
	 */
	public void setAddLocation(String addLocation)
	{
		this.addLocation = addLocation;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((md5Hash == null) ? 0 : md5Hash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StreamBean other = (StreamBean) obj;
		if (fileName == null)
		{
			if (other.fileName != null)
				return false;
		}
		else if (!fileName.equals(other.fileName))
			return false;
		if (md5Hash == null)
		{
			if (other.md5Hash != null)
				return false;
		}
		else if (!md5Hash.equals(other.md5Hash))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "StreamBean [fileName=" + fileName + ", md5Hash=" + md5Hash + ", extension=" + extension + ", fileSize="
				+ fileSize + ", originalFileName=" + originalFileName + ", filePid=" + filePid + ", addLocation=" + addLocation + "]";
	}

}
