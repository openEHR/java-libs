/**
 * EhrServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class EhrServiceLocator extends org.apache.axis.client.Service implements biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrService {

/**
 * The web services provided by EhrBank Server
 */

    public EhrServiceLocator() {
    }


    public EhrServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EhrServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EhrServiceSoap
    private java.lang.String EhrServiceSoap_address = "http://demo.oceanehr.com/EhrBank13/EhrService.asmx";

    public java.lang.String getEhrServiceSoapAddress() {
        return EhrServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EhrServiceSoapWSDDServiceName = "EhrServiceSoap";

    public java.lang.String getEhrServiceSoapWSDDServiceName() {
        return EhrServiceSoapWSDDServiceName;
    }

    public void setEhrServiceSoapWSDDServiceName(java.lang.String name) {
        EhrServiceSoapWSDDServiceName = name;
    }

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EhrServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEhrServiceSoap(endpoint);
    }

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoapStub _stub = new biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoapStub(portAddress, this);
            _stub.setPortName(getEhrServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEhrServiceSoapEndpointAddress(java.lang.String address) {
        EhrServiceSoap_address = address;
    }


    // Use to get a proxy class for EhrServiceSoap12
    private java.lang.String EhrServiceSoap12_address = "http://demo.oceanehr.com/EhrBank13/EhrService.asmx";

    public java.lang.String getEhrServiceSoap12Address() {
        return EhrServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EhrServiceSoap12WSDDServiceName = "EhrServiceSoap12";

    public java.lang.String getEhrServiceSoap12WSDDServiceName() {
        return EhrServiceSoap12WSDDServiceName;
    }

    public void setEhrServiceSoap12WSDDServiceName(java.lang.String name) {
        EhrServiceSoap12WSDDServiceName = name;
    }

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EhrServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEhrServiceSoap12(endpoint);
    }

    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap getEhrServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap12Stub _stub = new biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getEhrServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEhrServiceSoap12EndpointAddress(java.lang.String address) {
        EhrServiceSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoapStub _stub = new biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoapStub(new java.net.URL(EhrServiceSoap_address), this);
                _stub.setPortName(getEhrServiceSoapWSDDServiceName());
                return _stub;
            }
            if (biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap12Stub _stub = new biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.EhrServiceSoap12Stub(new java.net.URL(EhrServiceSoap12_address), this);
                _stub.setPortName(getEhrServiceSoap12WSDDServiceName());
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
        if ("EhrServiceSoap".equals(inputPortName)) {
            return getEhrServiceSoap();
        }
        else if ("EhrServiceSoap12".equals(inputPortName)) {
            return getEhrServiceSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "EhrService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "EhrServiceSoap"));
            ports.add(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "EhrServiceSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EhrServiceSoap".equals(portName)) {
            setEhrServiceSoapEndpointAddress(address);
        }
        else 
if ("EhrServiceSoap12".equals(portName)) {
            setEhrServiceSoap12EndpointAddress(address);
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
