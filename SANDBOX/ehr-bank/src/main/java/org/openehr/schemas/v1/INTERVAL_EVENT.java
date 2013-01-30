/**
 * INTERVAL_EVENT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class INTERVAL_EVENT  extends org.openehr.schemas.v1.EVENT  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_DURATION width;

    private java.lang.Integer sample_count;

    private org.openehr.schemas.v1.DV_CODED_TEXT math_function;

    public INTERVAL_EVENT() {
    }

    public INTERVAL_EVENT(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.DV_DATE_TIME time,
           org.openehr.schemas.v1.ITEM_STRUCTURE data,
           org.openehr.schemas.v1.ITEM_STRUCTURE state,
           org.openehr.schemas.v1.DV_DURATION width,
           java.lang.Integer sample_count,
           org.openehr.schemas.v1.DV_CODED_TEXT math_function) {
        super(
            archetype_node_id,
            name,
            uid,
            links,
            archetype_details,
            feeder_audit,
            time,
            data,
            state);
        this.width = width;
        this.sample_count = sample_count;
        this.math_function = math_function;
    }


    /**
     * Gets the width value for this INTERVAL_EVENT.
     * 
     * @return width
     */
    public org.openehr.schemas.v1.DV_DURATION getWidth() {
        return width;
    }


    /**
     * Sets the width value for this INTERVAL_EVENT.
     * 
     * @param width
     */
    public void setWidth(org.openehr.schemas.v1.DV_DURATION width) {
        this.width = width;
    }


    /**
     * Gets the sample_count value for this INTERVAL_EVENT.
     * 
     * @return sample_count
     */
    public java.lang.Integer getSample_count() {
        return sample_count;
    }


    /**
     * Sets the sample_count value for this INTERVAL_EVENT.
     * 
     * @param sample_count
     */
    public void setSample_count(java.lang.Integer sample_count) {
        this.sample_count = sample_count;
    }


    /**
     * Gets the math_function value for this INTERVAL_EVENT.
     * 
     * @return math_function
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getMath_function() {
        return math_function;
    }


    /**
     * Sets the math_function value for this INTERVAL_EVENT.
     * 
     * @param math_function
     */
    public void setMath_function(org.openehr.schemas.v1.DV_CODED_TEXT math_function) {
        this.math_function = math_function;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof INTERVAL_EVENT)) return false;
        INTERVAL_EVENT other = (INTERVAL_EVENT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.width==null && other.getWidth()==null) || 
             (this.width!=null &&
              this.width.equals(other.getWidth()))) &&
            ((this.sample_count==null && other.getSample_count()==null) || 
             (this.sample_count!=null &&
              this.sample_count.equals(other.getSample_count()))) &&
            ((this.math_function==null && other.getMath_function()==null) || 
             (this.math_function!=null &&
              this.math_function.equals(other.getMath_function())));
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
        if (getWidth() != null) {
            _hashCode += getWidth().hashCode();
        }
        if (getSample_count() != null) {
            _hashCode += getSample_count().hashCode();
        }
        if (getMath_function() != null) {
            _hashCode += getMath_function().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(INTERVAL_EVENT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "INTERVAL_EVENT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("width");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "width"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DURATION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sample_count");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "sample_count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("math_function");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "math_function"));
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
