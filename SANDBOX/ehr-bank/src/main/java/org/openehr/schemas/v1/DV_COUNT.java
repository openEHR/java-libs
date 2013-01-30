/**
 * DV_COUNT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_COUNT  extends org.openehr.schemas.v1.DV_AMOUNT  implements java.io.Serializable {
    private long magnitude;

    public DV_COUNT() {
    }

    public DV_COUNT(
           org.openehr.schemas.v1.DV_INTERVAL normal_range,
           org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges,
           org.openehr.schemas.v1.CODE_PHRASE normal_status,
           java.lang.String magnitude_status,
           java.lang.Float accuracy,
           java.lang.Boolean accuracy_is_percent,
           long magnitude) {
        super(
            normal_range,
            other_reference_ranges,
            normal_status,
            magnitude_status,
            accuracy,
            accuracy_is_percent);
        this.magnitude = magnitude;
    }


    /**
     * Gets the magnitude value for this DV_COUNT.
     * 
     * @return magnitude
     */
    public long getMagnitude() {
        return magnitude;
    }


    /**
     * Sets the magnitude value for this DV_COUNT.
     * 
     * @param magnitude
     */
    public void setMagnitude(long magnitude) {
        this.magnitude = magnitude;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_COUNT)) return false;
        DV_COUNT other = (DV_COUNT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.magnitude == other.getMagnitude();
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
        _hashCode += new Long(getMagnitude()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_COUNT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_COUNT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("magnitude");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "magnitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
