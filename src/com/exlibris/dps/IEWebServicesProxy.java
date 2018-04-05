package com.exlibris.dps;

public class IEWebServicesProxy implements com.exlibris.dps.IEWebServices_PortType {
  private String _endpoint = null;
  private com.exlibris.dps.IEWebServices_PortType iEWebServices_PortType = null;
  
  public IEWebServicesProxy() {
    _initIEWebServicesProxy();
  }
  
  public IEWebServicesProxy(String endpoint) {
    _endpoint = endpoint;
    _initIEWebServicesProxy();
  }
  
  private void _initIEWebServicesProxy() {
    try {
      iEWebServices_PortType = (new com.exlibris.dps.IEWebServices_ServiceLocator()).getIEWebServicesPort();
      if (iEWebServices_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iEWebServices_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iEWebServices_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iEWebServices_PortType != null)
      ((javax.xml.rpc.Stub)iEWebServices_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.exlibris.dps.IEWebServices_PortType getIEWebServices_PortType() {
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType;
  }
  
  public com.exlibris.dps.MetaData getSharedMDByMid(java.lang.String pdsHandle, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getSharedMDByMid(pdsHandle, mid);
  }
  
  public com.exlibris.dps.MetaData[] getSharedMDByType(java.lang.String pdsHandle, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getSharedMDByType(pdsHandle, type, subType);
  }
  
  public void unassignSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    iEWebServices_PortType.unassignSharedMD(pdsHandle, pid, mid);
  }
  
  public void generateFixityEvent(java.lang.String pdsHandle, com.exlibris.dps.FixityEvent event) throws java.rmi.RemoteException, com.exlibris.dps.FixityEventException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.LockedIeException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    iEWebServices_PortType.generateFixityEvent(pdsHandle, event);
  }
  
  public void updateSharedMD(java.lang.String pdsHandle, com.exlibris.dps.MetaData metadata) throws java.rmi.RemoteException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    iEWebServices_PortType.updateSharedMD(pdsHandle, metadata);
  }
  
  public void deleteSharedMD(java.lang.String pdsHandle, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    iEWebServices_PortType.deleteSharedMD(pdsHandle, mid);
  }
  
  public void updateIEMD(java.lang.String pdsHandle, java.lang.String iePID, com.exlibris.dps.MetaData[] metadata) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    iEWebServices_PortType.updateIEMD(pdsHandle, iePID, metadata);
  }
  
  public java.lang.String getIE(java.lang.String pdsHandle, java.lang.String iePid, java.lang.Long flags) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getIE(pdsHandle, iePid, flags);
  }
  
  public java.lang.String getIEMD(java.lang.String pdsHandle, java.lang.String iePID) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getIEMD(pdsHandle, iePID);
  }
  
  public com.exlibris.dps.MetaData[] getSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getSharedMD(pdsHandle, pid, type, subType);
  }
  
  public java.lang.String getMD(java.lang.String pdsHandle, java.lang.String PID, java.lang.String mid, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getMD(pdsHandle, PID, mid, type, subType);
  }
  
  public void updateMD(java.lang.String pdsHandle, java.lang.String PID, com.exlibris.dps.MetaData[] metadata) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    iEWebServices_PortType.updateMD(pdsHandle, PID, metadata);
  }
  
  public java.lang.String updateDNX(java.lang.String pdsHandle, java.lang.String PID, java.lang.String content) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.updateDNX(pdsHandle, PID, content);
  }
  
  public void assignSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    iEWebServices_PortType.assignSharedMD(pdsHandle, pid, mid);
  }
  
  public java.lang.String getHeartBit() throws java.rmi.RemoteException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getHeartBit();
  }
  
  public java.lang.String createSharedMD(java.lang.String pdsHandle, com.exlibris.dps.MetaData metadata) throws java.rmi.RemoteException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.createSharedMD(pdsHandle, metadata);
  }
  
  public java.lang.String getDNX(java.lang.String pdsHandle, java.lang.String PID, java.lang.String section) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getDNX(pdsHandle, PID, section);
  }
  
  public long updateRepresentation(java.lang.String pdsHandle, java.lang.String iePid, java.lang.String repPid, java.lang.String submissionReason, com.exlibris.dps.RepresentationContent[] representationContent) throws java.rmi.RemoteException, com.exlibris.dps.FixityEventException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException, com.exlibris.dps.InvalidTypeException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.updateRepresentation(pdsHandle, iePid, repPid, submissionReason, representationContent);
  }
  
  public java.lang.String getRipStatus(java.lang.String pdsHandle, java.lang.Long ripID) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException{
    if (iEWebServices_PortType == null)
      _initIEWebServicesProxy();
    return iEWebServices_PortType.getRipStatus(pdsHandle, ripID);
  }
  
  
}