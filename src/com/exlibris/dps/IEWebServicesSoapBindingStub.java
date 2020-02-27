/**
 * IEWebServicesSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exlibris.dps;

public class IEWebServicesSoapBindingStub extends org.apache.axis.client.Stub implements com.exlibris.dps.IEWebServices_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[19];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSharedMDByMid");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData"));
        oper.setReturnClass(com.exlibris.dps.MetaData.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getSharedMDByMid"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSharedMDByType");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "subType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData"));
        oper.setReturnClass(com.exlibris.dps.MetaData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getSharedMDByType"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("unassignSharedMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"),
                      "com.exlibris.dps.LockedIeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generateFixityEvent");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "event"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dps.exlibris.com/", "fixityEvent"), com.exlibris.dps.FixityEvent.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "FixityEventException"),
                      "com.exlibris.dps.FixityEventException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "FixityEventException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"),
                      "com.exlibris.dps.LockedIeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateSharedMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData"), com.exlibris.dps.MetaData.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"),
                      "com.exlibris.dps.InvalidXmlException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"),
                      "com.exlibris.dps.InvalidTypeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteSharedMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateIEMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "iePID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData"), com.exlibris.dps.MetaData[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"),
                      "com.exlibris.dps.InvalidXmlException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"),
                      "com.exlibris.dps.LockedIeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"),
                      "com.exlibris.dps.InvalidTypeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getIE");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "iePid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "flags"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getIE"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getIEMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "iePID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getIEMD"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSharedMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "subType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData"));
        oper.setReturnClass(com.exlibris.dps.MetaData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getSharedMD"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "type"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "subType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getMD"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData"), com.exlibris.dps.MetaData[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"),
                      "com.exlibris.dps.InvalidXmlException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"),
                      "com.exlibris.dps.LockedIeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"),
                      "com.exlibris.dps.InvalidTypeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateDNX");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "content"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "updateDNX"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"),
                      "com.exlibris.dps.InvalidXmlException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"),
                      "com.exlibris.dps.LockedIeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"),
                      "com.exlibris.dps.InvalidTypeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("assignSharedMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"),
                      "com.exlibris.dps.LockedIeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getHeartBit");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createSharedMD");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "metadata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData"), com.exlibris.dps.MetaData.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createSharedMD"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"),
                      "com.exlibris.dps.InvalidXmlException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"),
                      "com.exlibris.dps.InvalidTypeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDNX");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "section"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDNX"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateRepresentation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "iePid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "repPid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "submissionReason"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "representationContent"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://dps.exlibris.com/", "representationContent"), com.exlibris.dps.RepresentationContent[].class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        oper.setReturnClass(long.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "FixityEventException"),
                      "com.exlibris.dps.FixityEventException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "FixityEventException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"),
                      "com.exlibris.dps.NotInPermanentException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"),
                      "com.exlibris.dps.InvalidXmlException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"),
                      "com.exlibris.dps.InvalidMIDException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"),
                      "com.exlibris.dps.LockedIeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"),
                      "com.exlibris.dps.InvalidTypeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRipStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "pdsHandle"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ripID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getRipStatus"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"),
                      "com.exlibris.dps.UserAuthorizeException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"),
                      "com.exlibris.dps.IEWSException",
                      new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException"), 
                      true
                     ));
        _operations[18] = oper;

    }

    public IEWebServicesSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public IEWebServicesSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public IEWebServicesSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "fixity");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.Fixity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "fixityEvent");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.FixityEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "FixityEventException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.FixityEventException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "IEWSException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.IEWSException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidMIDException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.InvalidMIDException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidTypeException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.InvalidTypeException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "InvalidXmlException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.InvalidXmlException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "LockedIeException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.LockedIeException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "metaData");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.MetaData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "NotInPermanentException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.NotInPermanentException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "operation");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.Operation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "representationContent");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.RepresentationContent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://dps.exlibris.com/", "UserAuthorizeException");
            cachedSerQNames.add(qName);
            cls = com.exlibris.dps.UserAuthorizeException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            //lars: manuell timeout of 20 minutes
            //sorry for the hard-coded value :(
            _call.setTimeout(1200000);
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.exlibris.dps.MetaData getSharedMDByMid(java.lang.String pdsHandle, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getSharedMDByMid"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, mid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exlibris.dps.MetaData) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exlibris.dps.MetaData) org.apache.axis.utils.JavaUtils.convert(_resp, com.exlibris.dps.MetaData.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.exlibris.dps.MetaData[] getSharedMDByType(java.lang.String pdsHandle, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getSharedMDByType"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, type, subType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exlibris.dps.MetaData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exlibris.dps.MetaData[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.exlibris.dps.MetaData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void unassignSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "unassignSharedMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, pid, mid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.LockedIeException) {
              throw (com.exlibris.dps.LockedIeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void generateFixityEvent(java.lang.String pdsHandle, com.exlibris.dps.FixityEvent event) throws java.rmi.RemoteException, com.exlibris.dps.FixityEventException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.LockedIeException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "generateFixityEvent"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, event});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.FixityEventException) {
              throw (com.exlibris.dps.FixityEventException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.LockedIeException) {
              throw (com.exlibris.dps.LockedIeException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateSharedMD(java.lang.String pdsHandle, com.exlibris.dps.MetaData metadata) throws java.rmi.RemoteException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "updateSharedMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, metadata});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidXmlException) {
              throw (com.exlibris.dps.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidTypeException) {
              throw (com.exlibris.dps.InvalidTypeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void deleteSharedMD(java.lang.String pdsHandle, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "deleteSharedMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, mid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateIEMD(java.lang.String pdsHandle, java.lang.String iePID, com.exlibris.dps.MetaData[] metadata) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "updateIEMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, iePID, metadata});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidXmlException) {
              throw (com.exlibris.dps.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.LockedIeException) {
              throw (com.exlibris.dps.LockedIeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidTypeException) {
              throw (com.exlibris.dps.InvalidTypeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getIE(java.lang.String pdsHandle, java.lang.String iePid, java.lang.Long flags) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getIE"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, iePid, flags});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getIEMD(java.lang.String pdsHandle, java.lang.String iePID) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getIEMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, iePID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.exlibris.dps.MetaData[] getSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getSharedMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, pid, type, subType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.exlibris.dps.MetaData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.exlibris.dps.MetaData[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.exlibris.dps.MetaData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getMD(java.lang.String pdsHandle, java.lang.String PID, java.lang.String mid, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, PID, mid, type, subType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateMD(java.lang.String pdsHandle, java.lang.String PID, com.exlibris.dps.MetaData[] metadata) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "updateMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, PID, metadata});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidXmlException) {
              throw (com.exlibris.dps.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.LockedIeException) {
              throw (com.exlibris.dps.LockedIeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidTypeException) {
              throw (com.exlibris.dps.InvalidTypeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String updateDNX(java.lang.String pdsHandle, java.lang.String PID, java.lang.String content) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "updateDNX"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, PID, content});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidXmlException) {
              throw (com.exlibris.dps.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.LockedIeException) {
              throw (com.exlibris.dps.LockedIeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidTypeException) {
              throw (com.exlibris.dps.InvalidTypeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void assignSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "assignSharedMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, pid, mid});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.LockedIeException) {
              throw (com.exlibris.dps.LockedIeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getHeartBit() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getHeartBit"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String createSharedMD(java.lang.String pdsHandle, com.exlibris.dps.MetaData metadata) throws java.rmi.RemoteException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "createSharedMD"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, metadata});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidXmlException) {
              throw (com.exlibris.dps.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidTypeException) {
              throw (com.exlibris.dps.InvalidTypeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getDNX(java.lang.String pdsHandle, java.lang.String PID, java.lang.String section) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getDNX"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, PID, section});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public long updateRepresentation(java.lang.String pdsHandle, java.lang.String iePid, java.lang.String repPid, java.lang.String submissionReason, com.exlibris.dps.RepresentationContent[] representationContent) throws java.rmi.RemoteException, com.exlibris.dps.FixityEventException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException, com.exlibris.dps.InvalidTypeException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "updateRepresentation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, iePid, repPid, submissionReason, representationContent});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Long) _resp).longValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Long) org.apache.axis.utils.JavaUtils.convert(_resp, long.class)).longValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.FixityEventException) {
              throw (com.exlibris.dps.FixityEventException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.NotInPermanentException) {
              throw (com.exlibris.dps.NotInPermanentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidXmlException) {
              throw (com.exlibris.dps.InvalidXmlException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidMIDException) {
              throw (com.exlibris.dps.InvalidMIDException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.LockedIeException) {
              throw (com.exlibris.dps.LockedIeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.InvalidTypeException) {
              throw (com.exlibris.dps.InvalidTypeException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getRipStatus(java.lang.String pdsHandle, java.lang.Long ripID) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://dps.exlibris.com/", "getRipStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {pdsHandle, ripID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.UserAuthorizeException) {
              throw (com.exlibris.dps.UserAuthorizeException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.exlibris.dps.IEWSException) {
              throw (com.exlibris.dps.IEWSException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
