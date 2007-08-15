/**
 * FindEhrIdNoNamespaceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class FindEhrIdNoNamespaceResponse  implements java.io.Serializable {
    private java.lang.String findEhrIdNoNamespaceResult;

    public FindEhrIdNoNamespaceResponse() {
    }

    public FindEhrIdNoNamespaceResponse(
           java.lang.String findEhrIdNoNamespaceResult) {
           this.findEhrIdNoNamespaceResult = findEhrIdNoNamespaceResult;
    }


    /**
     * Gets the findEhrIdNoNamespaceResult value for this FindEhrIdNoNamespaceResponse.
     * 
     * @return findEhrIdNoNamespaceResult
     */
    public java.lang.String getFindEhrIdNoNamespaceResult() {
        return findEhrIdNoNamespaceResult;
    }


    /**
     * Sets the findEhrIdNoNamespaceResult value for this FindEhrIdNoNamespaceResponse.
     * 
     * @param findEhrIdNoNamespaceResult
     */
    public void setFindEhrIdNoNamespaceResult(java.lang.String findEhrIdNoNamespaceResult) {
        this.findEhrIdNoNamespaceResult = findEhrIdNoNamespaceResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FindEhrIdNoNamespaceResponse)) return false;
        FindEhrIdNoNamespaceResponse other = (FindEhrIdNoNamespaceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.findEhrIdNoNamespaceResult==null && other.getFindEhrIdNoNamespaceResult()==null) || 
             (this.findEhrIdNoNamespaceResult!=null &&
              this.findEhrIdNoNamespaceResult.equals(other.getFindEhrIdNoNamespaceResult())));
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
        if (getFindEhrIdNoNamespaceResult() != null) {
            _hashCode += getFindEhrIdNoNamespaceResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FindEhrIdNoNamespaceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">FindEhrIdNoNamespaceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("findEhrIdNoNamespaceResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "FindEhrIdNoNamespaceResult"));
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
