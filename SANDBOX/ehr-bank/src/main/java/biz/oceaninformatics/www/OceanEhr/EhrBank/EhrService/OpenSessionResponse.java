/**
 * OpenSessionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class OpenSessionResponse  implements java.io.Serializable {
    private java.lang.String openSessionResult;

    public OpenSessionResponse() {
    }

    public OpenSessionResponse(
           java.lang.String openSessionResult) {
           this.openSessionResult = openSessionResult;
    }


    /**
     * Gets the openSessionResult value for this OpenSessionResponse.
     * 
     * @return openSessionResult
     */
    public java.lang.String getOpenSessionResult() {
        return openSessionResult;
    }


    /**
     * Sets the openSessionResult value for this OpenSessionResponse.
     * 
     * @param openSessionResult
     */
    public void setOpenSessionResult(java.lang.String openSessionResult) {
        this.openSessionResult = openSessionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OpenSessionResponse)) return false;
        OpenSessionResponse other = (OpenSessionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.openSessionResult==null && other.getOpenSessionResult()==null) || 
             (this.openSessionResult!=null &&
              this.openSessionResult.equals(other.getOpenSessionResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getOpenSessionResult() != null) {
            _hashCode += getOpenSessionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OpenSessionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">OpenSessionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openSessionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "OpenSessionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
