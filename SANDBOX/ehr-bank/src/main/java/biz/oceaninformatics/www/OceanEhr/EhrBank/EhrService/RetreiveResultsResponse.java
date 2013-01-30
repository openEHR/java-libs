/**
 * RetreiveResultsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class RetreiveResultsResponse  implements java.io.Serializable {
    private java.lang.Object[] retreiveResultsResult;

    public RetreiveResultsResponse() {
    }

    public RetreiveResultsResponse(
           java.lang.Object[] retreiveResultsResult) {
           this.retreiveResultsResult = retreiveResultsResult;
    }


    /**
     * Gets the retreiveResultsResult value for this RetreiveResultsResponse.
     * 
     * @return retreiveResultsResult
     */
    public java.lang.Object[] getRetreiveResultsResult() {
        return retreiveResultsResult;
    }


    /**
     * Sets the retreiveResultsResult value for this RetreiveResultsResponse.
     * 
     * @param retreiveResultsResult
     */
    public void setRetreiveResultsResult(java.lang.Object[] retreiveResultsResult) {
        this.retreiveResultsResult = retreiveResultsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetreiveResultsResponse)) return false;
        RetreiveResultsResponse other = (RetreiveResultsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.retreiveResultsResult==null && other.getRetreiveResultsResult()==null) || 
             (this.retreiveResultsResult!=null &&
              java.util.Arrays.equals(this.retreiveResultsResult, other.getRetreiveResultsResult())));
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
        if (getRetreiveResultsResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRetreiveResultsResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRetreiveResultsResult(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetreiveResultsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">RetreiveResultsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retreiveResultsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "RetreiveResultsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "anyType"));
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
