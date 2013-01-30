/**
 * IsValidSessionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class IsValidSessionResponse  implements java.io.Serializable {
    private boolean isValidSessionResult;

    public IsValidSessionResponse() {
    }

    public IsValidSessionResponse(
           boolean isValidSessionResult) {
           this.isValidSessionResult = isValidSessionResult;
    }


    /**
     * Gets the isValidSessionResult value for this IsValidSessionResponse.
     * 
     * @return isValidSessionResult
     */
    public boolean isIsValidSessionResult() {
        return isValidSessionResult;
    }


    /**
     * Sets the isValidSessionResult value for this IsValidSessionResponse.
     * 
     * @param isValidSessionResult
     */
    public void setIsValidSessionResult(boolean isValidSessionResult) {
        this.isValidSessionResult = isValidSessionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IsValidSessionResponse)) return false;
        IsValidSessionResponse other = (IsValidSessionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.isValidSessionResult == other.isIsValidSessionResult();
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
        _hashCode += (isIsValidSessionResult() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IsValidSessionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">IsValidSessionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isValidSessionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "IsValidSessionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
