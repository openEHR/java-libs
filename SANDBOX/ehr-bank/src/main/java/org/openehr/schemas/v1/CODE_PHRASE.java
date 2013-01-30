/**
 * CODE_PHRASE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class CODE_PHRASE  implements java.io.Serializable {
    private org.openehr.schemas.v1.TERMINOLOGY_ID terminology_id;

    private java.lang.String code_string;

    public CODE_PHRASE() {
    }

    public CODE_PHRASE(
           org.openehr.schemas.v1.TERMINOLOGY_ID terminology_id,
           java.lang.String code_string) {
           this.terminology_id = terminology_id;
           this.code_string = code_string;
    }


    /**
     * Gets the terminology_id value for this CODE_PHRASE.
     * 
     * @return terminology_id
     */
    public org.openehr.schemas.v1.TERMINOLOGY_ID getTerminology_id() {
        return terminology_id;
    }


    /**
     * Sets the terminology_id value for this CODE_PHRASE.
     * 
     * @param terminology_id
     */
    public void setTerminology_id(org.openehr.schemas.v1.TERMINOLOGY_ID terminology_id) {
        this.terminology_id = terminology_id;
    }


    /**
     * Gets the code_string value for this CODE_PHRASE.
     * 
     * @return code_string
     */
    public java.lang.String getCode_string() {
        return code_string;
    }


    /**
     * Sets the code_string value for this CODE_PHRASE.
     * 
     * @param code_string
     */
    public void setCode_string(java.lang.String code_string) {
        this.code_string = code_string;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CODE_PHRASE)) return false;
        CODE_PHRASE other = (CODE_PHRASE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.terminology_id==null && other.getTerminology_id()==null) || 
             (this.terminology_id!=null &&
              this.terminology_id.equals(other.getTerminology_id()))) &&
            ((this.code_string==null && other.getCode_string()==null) || 
             (this.code_string!=null &&
              this.code_string.equals(other.getCode_string())));
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
        if (getTerminology_id() != null) {
            _hashCode += getTerminology_id().hashCode();
        }
        if (getCode_string() != null) {
            _hashCode += getCode_string().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CODE_PHRASE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminology_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "terminology_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERMINOLOGY_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code_string");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "code_string"));
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
