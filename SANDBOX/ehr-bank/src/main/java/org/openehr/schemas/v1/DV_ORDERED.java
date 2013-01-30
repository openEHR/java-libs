/**
 * DV_ORDERED.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class DV_ORDERED  extends org.openehr.schemas.v1.DATA_VALUE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_INTERVAL normal_range;

    private org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges;

    private org.openehr.schemas.v1.CODE_PHRASE normal_status;

    public DV_ORDERED() {
    }

    public DV_ORDERED(
           org.openehr.schemas.v1.DV_INTERVAL normal_range,
           org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges,
           org.openehr.schemas.v1.CODE_PHRASE normal_status) {
        this.normal_range = normal_range;
        this.other_reference_ranges = other_reference_ranges;
        this.normal_status = normal_status;
    }


    /**
     * Gets the normal_range value for this DV_ORDERED.
     * 
     * @return normal_range
     */
    public org.openehr.schemas.v1.DV_INTERVAL getNormal_range() {
        return normal_range;
    }


    /**
     * Sets the normal_range value for this DV_ORDERED.
     * 
     * @param normal_range
     */
    public void setNormal_range(org.openehr.schemas.v1.DV_INTERVAL normal_range) {
        this.normal_range = normal_range;
    }


    /**
     * Gets the other_reference_ranges value for this DV_ORDERED.
     * 
     * @return other_reference_ranges
     */
    public org.openehr.schemas.v1.REFERENCE_RANGE[] getOther_reference_ranges() {
        return other_reference_ranges;
    }


    /**
     * Sets the other_reference_ranges value for this DV_ORDERED.
     * 
     * @param other_reference_ranges
     */
    public void setOther_reference_ranges(org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges) {
        this.other_reference_ranges = other_reference_ranges;
    }

    public org.openehr.schemas.v1.REFERENCE_RANGE getOther_reference_ranges(int i) {
        return this.other_reference_ranges[i];
    }

    public void setOther_reference_ranges(int i, org.openehr.schemas.v1.REFERENCE_RANGE _value) {
        this.other_reference_ranges[i] = _value;
    }


    /**
     * Gets the normal_status value for this DV_ORDERED.
     * 
     * @return normal_status
     */
    public org.openehr.schemas.v1.CODE_PHRASE getNormal_status() {
        return normal_status;
    }


    /**
     * Sets the normal_status value for this DV_ORDERED.
     * 
     * @param normal_status
     */
    public void setNormal_status(org.openehr.schemas.v1.CODE_PHRASE normal_status) {
        this.normal_status = normal_status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_ORDERED)) return false;
        DV_ORDERED other = (DV_ORDERED) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.normal_range==null && other.getNormal_range()==null) || 
             (this.normal_range!=null &&
              this.normal_range.equals(other.getNormal_range()))) &&
            ((this.other_reference_ranges==null && other.getOther_reference_ranges()==null) || 
             (this.other_reference_ranges!=null &&
              java.util.Arrays.equals(this.other_reference_ranges, other.getOther_reference_ranges()))) &&
            ((this.normal_status==null && other.getNormal_status()==null) || 
             (this.normal_status!=null &&
              this.normal_status.equals(other.getNormal_status())));
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
        if (getNormal_range() != null) {
            _hashCode += getNormal_range().hashCode();
        }
        if (getOther_reference_ranges() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOther_reference_ranges());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOther_reference_ranges(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNormal_status() != null) {
            _hashCode += getNormal_status().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_ORDERED.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ORDERED"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("normal_range");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "normal_range"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_INTERVAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other_reference_ranges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "other_reference_ranges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REFERENCE_RANGE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("normal_status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "normal_status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
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
