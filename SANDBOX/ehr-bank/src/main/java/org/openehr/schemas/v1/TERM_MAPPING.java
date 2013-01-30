/**
 * TERM_MAPPING.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class TERM_MAPPING  implements java.io.Serializable {
    private java.lang.String match;

    private org.openehr.schemas.v1.DV_CODED_TEXT purpose;

    private org.openehr.schemas.v1.CODE_PHRASE target;

    public TERM_MAPPING() {
    }

    public TERM_MAPPING(
           java.lang.String match,
           org.openehr.schemas.v1.DV_CODED_TEXT purpose,
           org.openehr.schemas.v1.CODE_PHRASE target) {
           this.match = match;
           this.purpose = purpose;
           this.target = target;
    }


    /**
     * Gets the match value for this TERM_MAPPING.
     * 
     * @return match
     */
    public java.lang.String getMatch() {
        return match;
    }


    /**
     * Sets the match value for this TERM_MAPPING.
     * 
     * @param match
     */
    public void setMatch(java.lang.String match) {
        this.match = match;
    }


    /**
     * Gets the purpose value for this TERM_MAPPING.
     * 
     * @return purpose
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getPurpose() {
        return purpose;
    }


    /**
     * Sets the purpose value for this TERM_MAPPING.
     * 
     * @param purpose
     */
    public void setPurpose(org.openehr.schemas.v1.DV_CODED_TEXT purpose) {
        this.purpose = purpose;
    }


    /**
     * Gets the target value for this TERM_MAPPING.
     * 
     * @return target
     */
    public org.openehr.schemas.v1.CODE_PHRASE getTarget() {
        return target;
    }


    /**
     * Sets the target value for this TERM_MAPPING.
     * 
     * @param target
     */
    public void setTarget(org.openehr.schemas.v1.CODE_PHRASE target) {
        this.target = target;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TERM_MAPPING)) return false;
        TERM_MAPPING other = (TERM_MAPPING) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.match==null && other.getMatch()==null) || 
             (this.match!=null &&
              this.match.equals(other.getMatch()))) &&
            ((this.purpose==null && other.getPurpose()==null) || 
             (this.purpose!=null &&
              this.purpose.equals(other.getPurpose()))) &&
            ((this.target==null && other.getTarget()==null) || 
             (this.target!=null &&
              this.target.equals(other.getTarget())));
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
        if (getMatch() != null) {
            _hashCode += getMatch().hashCode();
        }
        if (getPurpose() != null) {
            _hashCode += getPurpose().hashCode();
        }
        if (getTarget() != null) {
            _hashCode += getTarget().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TERM_MAPPING.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERM_MAPPING"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("match");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "match"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purpose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "purpose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("target");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "target"));
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
