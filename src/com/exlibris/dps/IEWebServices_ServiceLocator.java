/**
 * IEWebServices_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package com.exlibris.dps;

import com.ethz.submissionds.SubmissionDsSingleton;

public class IEWebServices_ServiceLocator extends org.apache.axis.client.Service implements com.exlibris.dps.IEWebServices_Service {

    public IEWebServices_ServiceLocator() {
    }


    public IEWebServices_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IEWebServices_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IEWebServicesPort
    private java.lang.String IEWebServicesPort_address = "";

    public java.lang.String getIEWebServicesPortAddress() {
        return IEWebServicesPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IEWebServicesPortWSDDServiceName = "IEWebServicesPort";

    public java.lang.String getIEWebServicesPortWSDDServiceName() {
        return IEWebServicesPortWSDDServiceName;
    }

    public void setIEWebServicesPortWSDDServiceName(java.lang.String name) {
        IEWebServicesPortWSDDServiceName = name;
    }

    public com.exlibris.dps.IEWebServices_PortType getIEWebServicesPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SubmissionDsSingleton.wsLocation);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIEWebServicesPort(endpoint);
    }

    public com.exlibris.dps.IEWebServices_PortType getIEWebServicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.exlibris.dps.IEWebServicesSoapBindingStub _stub = new com.exlibris.dps.IEWebServicesSoapBindingStub(portAddress, this);
            _stub.setPortName(getIEWebServicesPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIEWebServicesPortEndpointAddress(java.lang.String address) {
        IEWebServicesPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.exlibris.dps.IEWebServices_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.exlibris.dps.IEWebServicesSoapBindingStub _stub = new com.exlibris.dps.IEWebServicesSoapBindingStub(new java.net.URL(IEWebServicesPort_address), this);
                _stub.setPortName(getIEWebServicesPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IEWebServicesPort".equals(inputPortName)) {
            return getIEWebServicesPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWebServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWebServicesPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IEWebServicesPort".equals(portName)) {
            setIEWebServicesPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
