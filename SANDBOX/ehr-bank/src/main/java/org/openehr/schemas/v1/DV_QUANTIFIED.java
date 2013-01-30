/**
 * DV_QUANTIFIED.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class DV_QUANTIFIED  extends org.openehr.schemas.v1.DV_ORDERED  implements java.io.Serializable {
    private java.lang.String magnitude_status;

    public DV_QUANTIFIED() {
    }

    public DV_QUANTIFIED(
           org.openehr.schemas.v1.DV_INTERVAL normal_range,
           org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges,
           org.openehr.schemas.v1.CODE_PHRASE normal_status,
           java.lang.String magnitude_status) {
        super(
            normal_range,
            other_reference_ranges,
            normal_status);
        this.magnitude_status = magnitude_status;
    }


    /**
     * Gets the magnitude_status value for this DV_QUANTIFIED.
     * 
     * @return magnitude_status
     */
    public java.lang.String getMagnitude_status() {
        return magnitude_status;
    }


    /**
     * Sets the magnitude_status value for this DV_QUANTIFIED.
     * 
     * @param magnitude_status
     */
    public void setMagnitude_status(java.lang.String magnitude_status) {
        this.magnitude_status = magnitude_status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_QUANTIFIED)) return false;
        DV_QUANTIFIED other = (DV_QUANTIFIED) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.magnitude_status==null && other.getMagnitude_status()==null) || 
             (this.magnitude_status!=null &&
              this.magnitude_status.equals(other.getMagnitude_status())));
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
        if (getMagnitude_status() != null) {
            _hashCode += getMagnitude_status().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_QUANTIFIED.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_QUANTIFIED"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("magnitude_status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "magnitude_status"));
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
