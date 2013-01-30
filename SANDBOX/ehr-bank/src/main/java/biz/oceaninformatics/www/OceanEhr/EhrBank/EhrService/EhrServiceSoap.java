/**
 * EhrServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public interface EhrServiceSoap extends java.rmi.Remote {
    public void closeSession(java.lang.String sessionId) throws java.rmi.RemoteException;
    public void closeTransaction(java.lang.String sessionId, biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.ActionType action) throws java.rmi.RemoteException;
    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.CommitStatus commitContribution(java.lang.String sessionId, java.lang.String ehrId, org.openehr.schemas.v1.AUDIT_DETAILS audit, org.openehr.schemas.v1.ORIGINAL_VERSION[] versions) throws java.rmi.RemoteException;
    public java.lang.String createEhr(java.lang.String sessionId, java.lang.String externalId, java.lang.String namespace) throws java.rmi.RemoteException;
    public java.lang.String findEhrId(java.lang.String sessionId, java.lang.String externalId, java.lang.String namespace) throws java.rmi.RemoteException;
    public java.lang.String findEhrIdNoNamespace(java.lang.String sessionId, java.lang.String externalId, java.lang.String namespace) throws java.rmi.RemoteException;
    public java.lang.String getSubjectId(java.lang.String sessionId, java.lang.String ehrUid, java.lang.String subjectIdNamespace) throws java.rmi.RemoteException;
    public java.lang.String getSystemId() throws java.rmi.RemoteException;
    public boolean hasTransaction(java.lang.String sessionId) throws java.rmi.RemoteException;
    public java.lang.String openSession(java.lang.String userId, java.lang.String credentials) throws java.rmi.RemoteException;
    public void openTransaction(java.lang.String sessionId) throws java.rmi.RemoteException;
    public void releaseResult(java.lang.String sessionId) throws java.rmi.RemoteException;
    public java.lang.Object[] retreiveResults(java.lang.String sessionId, int maxResults) throws java.rmi.RemoteException;
    public int runQuery(java.lang.String sessionId, java.lang.String queryStatement) throws java.rmi.RemoteException;
    public void seekResult(java.lang.String sessionId, int offset, biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.OffsetPoint fromWhere) throws java.rmi.RemoteException;
    public void setEhrId(java.lang.String sessionId, java.lang.String ehrId, java.lang.String externalId, java.lang.String namespace) throws java.rmi.RemoteException;
    public boolean isValidSession(java.lang.String sessionId) throws java.rmi.RemoteException;
    public org.openehr.schemas.v1.COMPOSITION getComposition(java.lang.String sessionId, java.lang.String compositionVersionUid, boolean includeOriginalContent) throws java.rmi.RemoteException;
    public org.openehr.schemas.v1.DV_ENCAPSULATED getOriginalContent(java.lang.String sessionId, java.lang.String compositionVersionUid) throws java.rmi.RemoteException;
    public byte[] getOriginalMultimediaData(java.lang.String sessionId, java.lang.String compositionVersionUid) throws java.rmi.RemoteException;
    public java.lang.String getOriginalParsableValue(java.lang.String sessionId, java.lang.String compositionVersionUid) throws java.rmi.RemoteException;
    public boolean hasOriginalContent(java.lang.String sessionId, java.lang.String compositionVersionUid) throws java.rmi.RemoteException;
}
