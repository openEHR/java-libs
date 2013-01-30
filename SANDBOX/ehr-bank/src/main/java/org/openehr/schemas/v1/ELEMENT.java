/**
 * ELEMENT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ELEMENT  extends org.openehr.schemas.v1.ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.DATA_VALUE value;

    private org.openehr.schemas.v1.DV_CODED_TEXT null_flavour;

    public ELEMENT() {
    }

    public ELEMENT(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.DATA_VALUE value,
           org.openehr.schemas.v1.DV_CODED_TEXT null_flavour) {
        super(
            archetype_node_id,
            name,
            uid,
            links,
            archetype_details,
            feeder_audit);
        this.value = value;
        this.null_flavour = null_flavour;
    }


    /**
     * Gets the value value for this ELEMENT.
     * 
     * @return value
     */
    public org.openehr.schemas.v1.DATA_VALUE getValue() {
        return value;
    }


    /**
     * Sets the value value for this ELEMENT.
     * 
     * @param value
     */
    public void setValue(org.openehr.schemas.v1.DATA_VALUE value) {
        this.value = value;
    }


    /**
     * Gets the null_flavour value for this ELEMENT.
     * 
     * @return null_flavour
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getNull_flavour() {
        return null_flavour;
    }


    /**
     * Sets the null_flavour value for this ELEMENT.
     * 
     * @param null_flavour
     */
    public void setNull_flavour(org.openehr.schemas.v1.DV_CODED_TEXT null_flavour) {
        this.null_flavour = null_flavour;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ELEMENT)) return false;
        ELEMENT other = (ELEMENT) obj;
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
            ((this.null_flavour==null && other.getNull_flavour()==null) || 
             (this.null_flavour!=null &&
              this.null_flavour.equals(other.getNull_flavour())));
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
        if (getNull_flavour() != null) {
            _hashCode += getNull_flavour().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ELEMENT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ELEMENT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DATA_VALUE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("null_flavour");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "null_flavour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
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
