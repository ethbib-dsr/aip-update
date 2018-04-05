package com.ethz.aipupdate.helper;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ethz.aipupdate.beans.DcMetaDataBean;
import com.ethz.aipupdate.beans.StreamBean;

public class MetsParser
{
	private static final Logger logger = LogManager.getLogger();
	
	private String metsContent;
	private Document dom;
	ConfigProperties config;
	public final static String FILEID = "#FILEID#"; 
	
	public MetsParser(ConfigProperties conf, String content)
	{
		config = conf;
		metsContent = content;
		dom = createDOM();
	}
	
	public Set<StreamBean> getFilesFromRosettaSip()
	{
		//dom = createDOM();
		Set<StreamBean> returnSet = getRosettaFilesFromDom(config.getXPathAllFiles());
		
		return returnSet;
	}
	
	
	public Set<StreamBean> getFilesFromDspaceMets(String streamPath)
	{
		//dom = createDOM();
		Set<StreamBean> returnSet = getDspaceFilesFromDom(config.getXPathAllFiles(), streamPath);

		return returnSet;
	}
	
	
	public List<DcMetaDataBean> getIeDcMetaData()
	{
		List<DcMetaDataBean> dcList = new ArrayList<DcMetaDataBean>();

		String xPath = config.getXPathIeDc();

		try
		{
			XPath xpath  = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Object result = expr.evaluate(dom, XPathConstants.NODESET);
			
			NodeList nodes = (NodeList) result;

			for(int i=0; i<nodes.getLength(); i++)
			{
				DcMetaDataBean mdBean = new DcMetaDataBean(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
				dcList.add(mdBean);
			}
		}
		catch (XPathExpressionException e)
		{
			logger.error("XPathExpressionException: " + e.getMessage());
		}

		return dcList;
	}
	
	
	public List<DcMetaDataBean> getFileDcMetaData(String fid)
	{
		List<DcMetaDataBean> dcList = new ArrayList<DcMetaDataBean>();

		String xPath = config.getXPathFileDc().replace(FILEID, fid);
		
		//logger.debug(xPath);
				
		try
		{
			XPath xpath  = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Object result = expr.evaluate(dom, XPathConstants.NODESET);
			
			NodeList nodes = (NodeList) result;

			for(int i=0; i<nodes.getLength(); i++)
			{
				DcMetaDataBean mdBean = new DcMetaDataBean(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
				dcList.add(mdBean);
			}
		}
		catch (XPathExpressionException e)
		{
			logger.error("XPathExpressionException: " + e.getMessage());
		}

		return dcList;
	}	
	
	

	
	private Set<StreamBean> getDspaceFilesFromDom(String xPath, String streamPath)
	{
		Set<StreamBean> returnSet = new HashSet<StreamBean>();
		
		try
		{
			XPath xpath  = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Object result = expr.evaluate(dom, XPathConstants.NODESET);
			
			NodeList nodes = (NodeList) result;

			for(int i=0; i<nodes.getLength(); i++)
			{
				StreamBean streamBean = getDspaceStreamBeanFromDom(nodes.item(i).getAttributes().getNamedItem("FILEID").getNodeValue(), streamPath);
				returnSet.add(streamBean);
			}
		}
		catch (XPathExpressionException e)
		{
			logger.error("XPathExpressionException: " + e.getMessage());
		}

		
		return returnSet;
		
	}		

	
	
	private StreamBean getDspaceStreamBeanFromDom(String fileId, String streamPath)
	{
		//file name
		String fileName = extractNodeValueFromXPath(config.getXpathFileName().replace(FILEID, fileId));
		//logger.debug("name: " + fileName);
		
		//MD5 value
		String md5 = extractNodeValueFromXPath(config.getXPathFileMd5().replace(FILEID, fileId));
		//logger.debug("md5: " + md5);
		
		//file original name
		String originalName = extractNodeValueFromXPath(config.getXpathFileOriginalpath().replace(FILEID, fileId));
		//logger.debug("original name: " + originalName);
		
		StreamBean streamBean = new StreamBean(fileName, md5, originalName, streamPath + fileName);
		
		//add fileid for later use
		streamBean.setFilePid(fileId);
		
		return streamBean;
	}	
	
	
	
	
	private Set<StreamBean> getRosettaFilesFromDom(String xPath)
	{
		Set<StreamBean> returnSet = new HashSet<StreamBean>();
		
		try
		{
			XPath xpath  = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Object result = expr.evaluate(dom, XPathConstants.NODESET);
			
			NodeList nodes = (NodeList) result;

			for(int i=0; i<nodes.getLength(); i++)
			{
				//logger.debug(nodes.item(i).getAttributes().getNamedItem("FILEID").getNodeValue());
				
				StreamBean streamBean = getRosettaStreamBeanFromDom(nodes.item(i).getAttributes().getNamedItem("FILEID").getNodeValue());
				returnSet.add(streamBean);
			}
		}
		catch (XPathExpressionException e)
		{
			logger.error("XPathExpressionException: " + e.getMessage());
		}
		
		return returnSet;
		
	}			
	
	
	private StreamBean getRosettaStreamBeanFromDom(String fileId)
	{
		
		//MD5 value
		String md5 = extractNodeValueFromXPath(config.getXPathFileMd5().replace(FILEID, fileId));
		//logger.debug("md5: " + md5);
		
		//file name
		String fileName = extractNodeValueFromXPath(config.getXpathFileName().replace(FILEID, fileId));
		//logger.debug("name: " + fileName);
		
		//file size
		String fileSize = extractNodeValueFromXPath(config.getXpathFileSize().replace(FILEID, fileId));
		//logger.debug("size: " + fileSize);
		
		//file extension 
		String fileExt = extractNodeValueFromXPath(config.getXpathFileExtensions().replace(FILEID, fileId));
		//logger.debug("extension: " + fileExt);
		
		StreamBean streamBean = new StreamBean(fileName, md5, fileExt, fileSize, fileId);
		
		return streamBean;
	}
	
	
	public String getRosettaRepPidFromDom()
	{
		String repPid = extractNodeValueFromXPath(config.getXpathRosettaRepId());

		return repPid;
	}
	
	
	public String getRosettaIePidFromDom()
	{
		String iePid = extractNodeValueFromXPath(config.getXpathRosettaIePid());		
		
		return iePid;
	}
	

	
	private String extractNodeValueFromXPath(String xPathString)
	{
		//logger.debug(xPathString);
		
		String nodeValue = "";

		try
		{
			XPath xpath  = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPathString);
			Object result = expr.evaluate(dom, XPathConstants.NODESET);
			
			NodeList nodes = (NodeList) result;

			nodeValue = nodes.item(0).getTextContent();
		}
		catch (XPathExpressionException e)
		{
			logger.error("XPathExpressionException: " + e.getMessage());
		}				
		
		return nodeValue;
	}
	

	
	private Document createDOM()
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(false);
		DocumentBuilder builder = null;
		Document document = null;
		
		try
		{
			builder = builderFactory.newDocumentBuilder();
		}
		catch(ParserConfigurationException e)
		{
			logger.error(e.getMessage());
		}
		
		try
		{
			document = builder.parse(new InputSource(new StringReader(metsContent)));
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
		return document;
		
	}		
	

}
