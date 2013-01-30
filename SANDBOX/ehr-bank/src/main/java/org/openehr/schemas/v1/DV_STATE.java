/**
 * DV_STATE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_STATE  extends org.openehr.schemas.v1.DATA_VALUE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_CODED_TEXT value;

    private boolean is_terminal;

    public DV_STATE() {
    }

    public DV_STATE(
           org.openehr.schemas.v1.DV_CODED_TEXT value,
           boolean is_terminal) {
        this.value = value;
        this.is_terminal = is_terminal;
    }


    /**
     * Gets the value value for this DV_STATE.
     * 
     * @return value
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getValue() {
        return value;
    }


    /**
     * Sets the value value for this DV_STATE.
     * 
     * @param value
     */
    public void setValue(org.openehr.schemas.v1.DV_CODED_TEXT value) {
        this.value = value;
    }


    /**
     * Gets the is_terminal value for this DV_STATE.
     * 
     * @return is_terminal
     */
    public boolean isIs_terminal() {
        return is_terminal;
    }


    /**
     * Sets the is_terminal value for this DV_STATE.
     * 
     * @param is_terminal
     */
    public void setIs_terminal(boolean is_terminal) {
        this.is_terminal = is_terminal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_STATE)) return false;
        DV_STATE other = (DV_STATE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            this.is_terminal == other.isIs_terminal();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        _hashCode += (isIs_terminal() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_STATE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_STATE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_terminal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_terminal"));
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
