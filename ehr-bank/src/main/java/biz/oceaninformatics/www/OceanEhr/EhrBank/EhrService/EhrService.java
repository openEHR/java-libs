/**
 * EhrService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public interface EhrService extends javax.xml.rpc.Service {

/**
 * The web services provided by EhrBank Server
 */
    public java.lang.String getEhrServiceSoapAddress();

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap() throws javax.xml.rpc.ServiceException;

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getEhrServiceSoap12Address();

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap12() throws javax.xml.rpc.ServiceException;

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
