/**
 * DV_PROPORTION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_PROPORTION  extends org.openehr.schemas.v1.DV_AMOUNT  implements java.io.Serializable {
    private float numerator;

    private float denominator;

    private org.openehr.schemas.v1.PROPORTION_KIND type;

    private java.lang.Integer precision;

    public DV_PROPORTION() {
    }

    public DV_PROPORTION(
           org.openehr.schemas.v1.DV_INTERVAL normal_range,
           org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges,
           org.openehr.schemas.v1.CODE_PHRASE normal_status,
           java.lang.String magnitude_status,
           java.lang.Float accuracy,
           java.lang.Boolean accuracy_is_percent,
           float numerator,
           float denominator,
           org.openehr.schemas.v1.PROPORTION_KIND type,
           java.lang.Integer precision) {
        super(
            normal_range,
            other_reference_ranges,
            normal_status,
            magnitude_status,
            accuracy,
            accuracy_is_percent);
        this.numerator = numerator;
        this.denominator = denominator;
        this.type = type;
        this.precision = precision;
    }


    /**
     * Gets the numerator value for this DV_PROPORTION.
     * 
     * @return numerator
     */
    public float getNumerator() {
        return numerator;
    }


    /**
     * Sets the numerator value for this DV_PROPORTION.
     * 
     * @param numerator
     */
    public void setNumerator(float numerator) {
        this.numerator = numerator;
    }


    /**
     * Gets the denominator value for this DV_PROPORTION.
     * 
     * @return denominator
     */
    public float getDenominator() {
        return denominator;
    }


    /**
     * Sets the denominator value for this DV_PROPORTION.
     * 
     * @param denominator
     */
    public void setDenominator(float denominator) {
        this.denominator = denominator;
    }


    /**
     * Gets the type value for this DV_PROPORTION.
     * 
     * @return type
     */
    public org.openehr.schemas.v1.PROPORTION_KIND getType() {
        return type;
    }


    /**
     * Sets the type value for this DV_PROPORTION.
     * 
     * @param type
     */
    public void setType(org.openehr.schemas.v1.PROPORTION_KIND type) {
        this.type = type;
    }


    /**
     * Gets the precision value for this DV_PROPORTION.
     * 
     * @return precision
     */
    public java.lang.Integer getPrecision() {
        return precision;
    }


    /**
     * Sets the precision value for this DV_PROPORTION.
     * 
     * @param precision
     */
    public void setPrecision(java.lang.Integer precision) {
        this.precision = precision;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_PROPORTION)) return false;
        DV_PROPORTION other = (DV_PROPORTION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.numerator == other.getNumerator() &&
            this.denominator == other.getDenominator() &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.precision==null && other.getPrecision()==null) || 
             (this.precision!=null &&
              this.precision.equals(other.getPrecision())));
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
        _hashCode += new Float(getNumerator()).hashCode();
        _hashCode += new Float(getDenominator()).hashCode();
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getPrecision() != null) {
            _hashCode += getPrecision().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_PROPORTION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PROPORTION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numerator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "numerator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denominator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "denominator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PROPORTION_KIND"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("precision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "precision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
