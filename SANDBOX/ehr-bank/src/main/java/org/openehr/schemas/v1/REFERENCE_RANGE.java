/**
 * REFERENCE_RANGE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class REFERENCE_RANGE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_TEXT meaning;

    private org.openehr.schemas.v1.DV_INTERVAL range;

    public REFERENCE_RANGE() {
    }

    public REFERENCE_RANGE(
           org.openehr.schemas.v1.DV_TEXT meaning,
           org.openehr.schemas.v1.DV_INTERVAL range) {
           this.meaning = meaning;
           this.range = range;
    }


    /**
     * Gets the meaning value for this REFERENCE_RANGE.
     * 
     * @return meaning
     */
    public org.openehr.schemas.v1.DV_TEXT getMeaning() {
        return meaning;
    }


    /**
     * Sets the meaning value for this REFERENCE_RANGE.
     * 
     * @param meaning
     */
    public void setMeaning(org.openehr.schemas.v1.DV_TEXT meaning) {
        this.meaning = meaning;
    }


    /**
     * Gets the range value for this REFERENCE_RANGE.
     * 
     * @return range
     */
    public org.openehr.schemas.v1.DV_INTERVAL getRange() {
        return range;
    }


    /**
     * Sets the range value for this REFERENCE_RANGE.
     * 
     * @param range
     */
    public void setRange(org.openehr.schemas.v1.DV_INTERVAL range) {
        this.range = range;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof REFERENCE_RANGE)) return false;
        REFERENCE_RANGE other = (REFERENCE_RANGE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.meaning==null && other.getMeaning()==null) || 
             (this.meaning!=null &&
              this.meaning.equals(other.getMeaning()))) &&
            ((this.range==null && other.getRange()==null) || 
             (this.range!=null &&
              this.range.equals(other.getRange())));
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
        if (getMeaning() != null) {
            _hashCode += getMeaning().hashCode();
        }
        if (getRange() != null) {
            _hashCode += getRange().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(REFERENCE_RANGE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REFERENCE_RANGE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meaning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "meaning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("range");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "range"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_INTERVAL"));
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
