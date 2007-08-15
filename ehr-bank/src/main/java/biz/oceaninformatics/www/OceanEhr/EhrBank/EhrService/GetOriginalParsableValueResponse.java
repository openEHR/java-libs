/**
 * GetOriginalParsableValueResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class GetOriginalParsableValueResponse  implements java.io.Serializable {
    private java.lang.String getOriginalParsableValueResult;

    public GetOriginalParsableValueResponse() {
    }

    public GetOriginalParsableValueResponse(
           java.lang.String getOriginalParsableValueResult) {
           this.getOriginalParsableValueResult = getOriginalParsableValueResult;
    }


    /**
     * Gets the getOriginalParsableValueResult value for this GetOriginalParsableValueResponse.
     * 
     * @return getOriginalParsableValueResult
     */
    public java.lang.String getGetOriginalParsableValueResult() {
        return getOriginalParsableValueResult;
    }


    /**
     * Sets the getOriginalParsableValueResult value for this GetOriginalParsableValueResponse.
     * 
     * @param getOriginalParsableValueResult
     */
    public void setGetOriginalParsableValueResult(java.lang.String getOriginalParsableValueResult) {
        this.getOriginalParsableValueResult = getOriginalParsableValueResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetOriginalParsableValueResponse)) return false;
        GetOriginalParsableValueResponse other = (GetOriginalParsableValueResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getOriginalParsableValueResult==null && other.getGetOriginalParsableValueResult()==null) || 
             (this.getOriginalParsableValueResult!=null &&
              this.getOriginalParsableValueResult.equals(other.getGetOriginalParsableValueResult())));
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
        if (getGetOriginalParsableValueResult() != null) {
            _hashCode += getGetOriginalParsableValueResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetOriginalParsableValueResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">GetOriginalParsableValueResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getOriginalParsableValueResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "GetOriginalParsableValueResult"));
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
