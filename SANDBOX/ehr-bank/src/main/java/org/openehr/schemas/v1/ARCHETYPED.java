/**
 * ARCHETYPED.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ARCHETYPED  implements java.io.Serializable {
    private org.openehr.schemas.v1.ARCHETYPE_ID archetype_id;

    private org.openehr.schemas.v1.TEMPLATE_ID template_id;

    private java.lang.String rm_version;

    public ARCHETYPED() {
    }

    public ARCHETYPED(
           org.openehr.schemas.v1.ARCHETYPE_ID archetype_id,
           org.openehr.schemas.v1.TEMPLATE_ID template_id,
           java.lang.String rm_version) {
           this.archetype_id = archetype_id;
           this.template_id = template_id;
           this.rm_version = rm_version;
    }


    /**
     * Gets the archetype_id value for this ARCHETYPED.
     * 
     * @return archetype_id
     */
    public org.openehr.schemas.v1.ARCHETYPE_ID getArchetype_id() {
        return archetype_id;
    }


    /**
     * Sets the archetype_id value for this ARCHETYPED.
     * 
     * @param archetype_id
     */
    public void setArchetype_id(org.openehr.schemas.v1.ARCHETYPE_ID archetype_id) {
        this.archetype_id = archetype_id;
    }


    /**
     * Gets the template_id value for this ARCHETYPED.
     * 
     * @return template_id
     */
    public org.openehr.schemas.v1.TEMPLATE_ID getTemplate_id() {
        return template_id;
    }


    /**
     * Sets the template_id value for this ARCHETYPED.
     * 
     * @param template_id
     */
    public void setTemplate_id(org.openehr.schemas.v1.TEMPLATE_ID template_id) {
        this.template_id = template_id;
    }


    /**
     * Gets the rm_version value for this ARCHETYPED.
     * 
     * @return rm_version
     */
    public java.lang.String getRm_version() {
        return rm_version;
    }


    /**
     * Sets the rm_version value for this ARCHETYPED.
     * 
     * @param rm_version
     */
    public void setRm_version(java.lang.String rm_version) {
        this.rm_version = rm_version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ARCHETYPED)) return false;
        ARCHETYPED other = (ARCHETYPED) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.archetype_id==null && other.getArchetype_id()==null) || 
             (this.archetype_id!=null &&
              this.archetype_id.equals(other.getArchetype_id()))) &&
            ((this.template_id==null && other.getTemplate_id()==null) || 
             (this.template_id!=null &&
              this.template_id.equals(other.getTemplate_id()))) &&
            ((this.rm_version==null && other.getRm_version()==null) || 
             (this.rm_version!=null &&
              this.rm_version.equals(other.getRm_version())));
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
        if (getArchetype_id() != null) {
            _hashCode += getArchetype_id().hashCode();
        }
        if (getTemplate_id() != null) {
            _hashCode += getTemplate_id().hashCode();
        }
        if (getRm_version() != null) {
            _hashCode += getRm_version().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ARCHETYPED.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPED"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archetype_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "archetype_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("template_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "template_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TEMPLATE_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rm_version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "rm_version"));
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
