package com.ethz.aipupdate.beans;

public class DcMetaDataBean
{

	private String elementName;
	private String elementValue;
	
	public DcMetaDataBean(String elementName, String elementValue)
	{
		super();
		this.elementName = elementName;
		this.elementValue = elementValue;
	}

	public String getElementName()
	{
		return elementName;
	}

	public void setElementName(String elementName)
	{
		this.elementName = elementName;
	}

	public String getElementValue()
	{
		return elementValue;
	}

	public void setElementValue(String elementValue)
	{
		this.elementValue = elementValue;
	}

	@Override
	public String toString()
	{
		return "DcMetaDataBean [elementName=" + elementName + ", elementValue=" + elementValue + "]";
	}

}
