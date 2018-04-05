/**
 * IEWebServices_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exlibris.dps;

public interface IEWebServices_PortType extends java.rmi.Remote {
    public com.exlibris.dps.MetaData getSharedMDByMid(java.lang.String pdsHandle, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException;
    public com.exlibris.dps.MetaData[] getSharedMDByType(java.lang.String pdsHandle, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException;
    public void unassignSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException;
    public void generateFixityEvent(java.lang.String pdsHandle, com.exlibris.dps.FixityEvent event) throws java.rmi.RemoteException, com.exlibris.dps.FixityEventException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.LockedIeException;
    public void updateSharedMD(java.lang.String pdsHandle, com.exlibris.dps.MetaData metadata) throws java.rmi.RemoteException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException;
    public void deleteSharedMD(java.lang.String pdsHandle, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException;
    public void updateIEMD(java.lang.String pdsHandle, java.lang.String iePID, com.exlibris.dps.MetaData[] metadata) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException;
    public java.lang.String getIE(java.lang.String pdsHandle, java.lang.String iePid, java.lang.Long flags) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException;
    public java.lang.String getIEMD(java.lang.String pdsHandle, java.lang.String iePID) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException;
    public com.exlibris.dps.MetaData[] getSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException;
    public java.lang.String getMD(java.lang.String pdsHandle, java.lang.String PID, java.lang.String mid, java.lang.String type, java.lang.String subType) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException;
    public void updateMD(java.lang.String pdsHandle, java.lang.String PID, com.exlibris.dps.MetaData[] metadata) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException;
    public java.lang.String updateDNX(java.lang.String pdsHandle, java.lang.String PID, java.lang.String content) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException;
    public void assignSharedMD(java.lang.String pdsHandle, java.lang.String pid, java.lang.String mid) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException;
    public java.lang.String getHeartBit() throws java.rmi.RemoteException;
    public java.lang.String createSharedMD(java.lang.String pdsHandle, com.exlibris.dps.MetaData metadata) throws java.rmi.RemoteException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidTypeException, com.exlibris.dps.IEWSException;
    public java.lang.String getDNX(java.lang.String pdsHandle, java.lang.String PID, java.lang.String section) throws java.rmi.RemoteException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException;
    public long updateRepresentation(java.lang.String pdsHandle, java.lang.String iePid, java.lang.String repPid, java.lang.String submissionReason, com.exlibris.dps.RepresentationContent[] representationContent) throws java.rmi.RemoteException, com.exlibris.dps.FixityEventException, com.exlibris.dps.NotInPermanentException, com.exlibris.dps.InvalidXmlException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.InvalidMIDException, com.exlibris.dps.LockedIeException, com.exlibris.dps.IEWSException, com.exlibris.dps.InvalidTypeException;
    public java.lang.String getRipStatus(java.lang.String pdsHandle, java.lang.Long ripID) throws java.rmi.RemoteException, com.exlibris.dps.UserAuthorizeException, com.exlibris.dps.IEWSException;
}
