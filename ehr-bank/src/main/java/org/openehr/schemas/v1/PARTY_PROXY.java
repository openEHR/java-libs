/**
 * PARTY_PROXY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class PARTY_PROXY  implements java.io.Serializable {
    private org.openehr.schemas.v1.PARTY_REF external_ref;

    public PARTY_PROXY() {
    }

    public PARTY_PROXY(
           org.openehr.schemas.v1.PARTY_REF external_ref) {
           this.external_ref = external_ref;
    }


    /**
     * Gets the external_ref value for this PARTY_PROXY.
     * 
     * @return external_ref
     */
    public org.openehr.schemas.v1.PARTY_REF getExternal_ref() {
        return external_ref;
    }


    /**
     * Sets the external_ref value for this PARTY_PROXY.
     * 
     * @param external_ref
     */
    public void setExternal_ref(org.openehr.schemas.v1.PARTY_REF external_ref) {
        this.external_ref = external_ref;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PARTY_PROXY)) return false;
        PARTY_PROXY other = (PARTY_PROXY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.external_ref==null && other.getExternal_ref()==null) || 
             (this.external_ref!=null &&
              this.external_ref.equals(other.getExternal_ref())));
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
        if (getExternal_ref() != null) {
            _hashCode += getExternal_ref().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PARTY_PROXY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_PROXY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("external_ref");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "external_ref"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_REF"));
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
