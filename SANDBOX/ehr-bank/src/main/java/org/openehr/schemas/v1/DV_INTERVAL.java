/**
 * DV_INTERVAL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_INTERVAL  extends org.openehr.schemas.v1.DATA_VALUE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_ORDERED lower;

    private org.openehr.schemas.v1.DV_ORDERED upper;

    private java.lang.Boolean lower_included;

    private java.lang.Boolean upper_included;

    private boolean lower_unbounded;

    private boolean upper_unbounded;

    public DV_INTERVAL() {
    }

    public DV_INTERVAL(
           org.openehr.schemas.v1.DV_ORDERED lower,
           org.openehr.schemas.v1.DV_ORDERED upper,
           java.lang.Boolean lower_included,
           java.lang.Boolean upper_included,
           boolean lower_unbounded,
           boolean upper_unbounded) {
        this.lower = lower;
        this.upper = upper;
        this.lower_included = lower_included;
        this.upper_included = upper_included;
        this.lower_unbounded = lower_unbounded;
        this.upper_unbounded = upper_unbounded;
    }


    /**
     * Gets the lower value for this DV_INTERVAL.
     * 
     * @return lower
     */
    public org.openehr.schemas.v1.DV_ORDERED getLower() {
        return lower;
    }


    /**
     * Sets the lower value for this DV_INTERVAL.
     * 
     * @param lower
     */
    public void setLower(org.openehr.schemas.v1.DV_ORDERED lower) {
        this.lower = lower;
    }


    /**
     * Gets the upper value for this DV_INTERVAL.
     * 
     * @return upper
     */
    public org.openehr.schemas.v1.DV_ORDERED getUpper() {
        return upper;
    }


    /**
     * Sets the upper value for this DV_INTERVAL.
     * 
     * @param upper
     */
    public void setUpper(org.openehr.schemas.v1.DV_ORDERED upper) {
        this.upper = upper;
    }


    /**
     * Gets the lower_included value for this DV_INTERVAL.
     * 
     * @return lower_included
     */
    public java.lang.Boolean getLower_included() {
        return lower_included;
    }


    /**
     * Sets the lower_included value for this DV_INTERVAL.
     * 
     * @param lower_included
     */
    public void setLower_included(java.lang.Boolean lower_included) {
        this.lower_included = lower_included;
    }


    /**
     * Gets the upper_included value for this DV_INTERVAL.
     * 
     * @return upper_included
     */
    public java.lang.Boolean getUpper_included() {
        return upper_included;
    }


    /**
     * Sets the upper_included value for this DV_INTERVAL.
     * 
     * @param upper_included
     */
    public void setUpper_included(java.lang.Boolean upper_included) {
        this.upper_included = upper_included;
    }


    /**
     * Gets the lower_unbounded value for this DV_INTERVAL.
     * 
     * @return lower_unbounded
     */
    public boolean isLower_unbounded() {
        return lower_unbounded;
    }


    /**
     * Sets the lower_unbounded value for this DV_INTERVAL.
     * 
     * @param lower_unbounded
     */
    public void setLower_unbounded(boolean lower_unbounded) {
        this.lower_unbounded = lower_unbounded;
    }


    /**
     * Gets the upper_unbounded value for this DV_INTERVAL.
     * 
     * @return upper_unbounded
     */
    public boolean isUpper_unbounded() {
        return upper_unbounded;
    }


    /**
     * Sets the upper_unbounded value for this DV_INTERVAL.
     * 
     * @param upper_unbounded
     */
    public void setUpper_unbounded(boolean upper_unbounded) {
        this.upper_unbounded = upper_unbounded;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_INTERVAL)) return false;
        DV_INTERVAL other = (DV_INTERVAL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.lower==null && other.getLower()==null) || 
             (this.lower!=null &&
              this.lower.equals(other.getLower()))) &&
            ((this.upper==null && other.getUpper()==null) || 
             (this.upper!=null &&
              this.upper.equals(other.getUpper()))) &&
            ((this.lower_included==null && other.getLower_included()==null) || 
             (this.lower_included!=null &&
              this.lower_included.equals(other.getLower_included()))) &&
            ((this.upper_included==null && other.getUpper_included()==null) || 
             (this.upper_included!=null &&
              this.upper_included.equals(other.getUpper_included()))) &&
            this.lower_unbounded == other.isLower_unbounded() &&
            this.upper_unbounded == other.isUpper_unbounded();
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
        if (getLower() != null) {
            _hashCode += getLower().hashCode();
        }
        if (getUpper() != null) {
            _hashCode += getUpper().hashCode();
        }
        if (getLower_included() != null) {
            _hashCode += getLower_included().hashCode();
        }
        if (getUpper_included() != null) {
            _hashCode += getUpper_included().hashCode();
        }
        _hashCode += (isLower_unbounded() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isUpper_unbounded() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_INTERVAL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_INTERVAL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lower");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lower"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ORDERED"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upper");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "upper"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ORDERED"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lower_included");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lower_included"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upper_included");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "upper_included"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lower_unbounded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lower_unbounded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("upper_unbounded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "upper_unbounded"));
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
