/**
 * GetOriginalMultimediaDataResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class GetOriginalMultimediaDataResponse  implements java.io.Serializable {
    private byte[] getOriginalMultimediaDataResult;

    public GetOriginalMultimediaDataResponse() {
    }

    public GetOriginalMultimediaDataResponse(
           byte[] getOriginalMultimediaDataResult) {
           this.getOriginalMultimediaDataResult = getOriginalMultimediaDataResult;
    }


    /**
     * Gets the getOriginalMultimediaDataResult value for this GetOriginalMultimediaDataResponse.
     * 
     * @return getOriginalMultimediaDataResult
     */
    public byte[] getGetOriginalMultimediaDataResult() {
        return getOriginalMultimediaDataResult;
    }


    /**
     * Sets the getOriginalMultimediaDataResult value for this GetOriginalMultimediaDataResponse.
     * 
     * @param getOriginalMultimediaDataResult
     */
    public void setGetOriginalMultimediaDataResult(byte[] getOriginalMultimediaDataResult) {
        this.getOriginalMultimediaDataResult = getOriginalMultimediaDataResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetOriginalMultimediaDataResponse)) return false;
        GetOriginalMultimediaDataResponse other = (GetOriginalMultimediaDataResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getOriginalMultimediaDataResult==null && other.getGetOriginalMultimediaDataResult()==null) || 
             (this.getOriginalMultimediaDataResult!=null &&
              java.util.Arrays.equals(this.getOriginalMultimediaDataResult, other.getGetOriginalMultimediaDataResult())));
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
        if (getGetOriginalMultimediaDataResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetOriginalMultimediaDataResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGetOriginalMultimediaDataResult(), i);
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
        new org.apache.axis.description.TypeDesc(GetOriginalMultimediaDataResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">GetOriginalMultimediaDataResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getOriginalMultimediaDataResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "GetOriginalMultimediaDataResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
