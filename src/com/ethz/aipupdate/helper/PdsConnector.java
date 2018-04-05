package com.ethz.aipupdate.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdsConnector {

	private static final Logger log = Logger.getLogger( PdsConnector.class.getName() );
	
	private String pdsHandle;
	private String pdsHost;
	private String pdsUser;
	private String pdsPassword;
	private String pdsInstitute;
	private String pdsCaller;
	private static final String PDS_HANDLE = "pds_handle=";
	
	public PdsConnector(String pdsHost, String pdsUser, String pdsPassword, String pdsInstitute, String pdsCaller) {
		this.pdsHost = pdsHost;
		this.pdsUser = pdsUser;
		this.pdsPassword = pdsPassword;
		this.pdsInstitute = pdsInstitute;
		this.pdsCaller = pdsCaller;
		fillPdsHandle();
	}

	
	private void fillPdsHandle() {
		URL pdsConnect = null;
		BufferedReader reader = null;
		
		try {
			pdsConnect = new URL(pdsHost + pdsUser + pdsPassword + pdsInstitute + pdsCaller);
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, e.toString(), e);
		}

		try {
			reader = new BufferedReader(new InputStreamReader(pdsConnect.openStream(), "UTF-8"));
		    for (String line; (line = reader.readLine()) != null;) {
		        if(hasHandle(line))
		        {
		        	setPdsHandle(extractHandleFromLine(line));
		        	break;
		        }
		    }			
		} catch (UnsupportedEncodingException e) {
			log.log(Level.SEVERE, e.toString(), e);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}
	}
	
	private boolean hasHandle(String line) {
		return (line.contains(PDS_HANDLE));
	}
	
	
	private String extractHandleFromLine(String line) {

		String resultString = "";
		String regexString = "&" + PDS_HANDLE +  "(.*)&";
		String regex = regexString;
		
		Pattern regexPattern = Pattern.compile(regex);
		Matcher match = regexPattern.matcher(line);
		 
		if(match.find()) {
			resultString = match.group(1);
		}	
		
		return resultString;
	}
	
	
	public String getPdsHandle() {
		return pdsHandle;
	}

	private void setPdsHandle(String pdsHandle) {
		this.pdsHandle = pdsHandle;
	}
	
}
