/**
 * EXTRACT_ENTITY_IDENTIFIER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_ENTITY_IDENTIFIER  implements java.io.Serializable {
    private org.openehr.schemas.v1.HIER_OBJECT_ID entity_id;

    private org.openehr.schemas.v1.PARTY_IDENTIFIED subject;

    public EXTRACT_ENTITY_IDENTIFIER() {
    }

    public EXTRACT_ENTITY_IDENTIFIER(
           org.openehr.schemas.v1.HIER_OBJECT_ID entity_id,
           org.openehr.schemas.v1.PARTY_IDENTIFIED subject) {
           this.entity_id = entity_id;
           this.subject = subject;
    }


    /**
     * Gets the entity_id value for this EXTRACT_ENTITY_IDENTIFIER.
     * 
     * @return entity_id
     */
    public org.openehr.schemas.v1.HIER_OBJECT_ID getEntity_id() {
        return entity_id;
    }


    /**
     * Sets the entity_id value for this EXTRACT_ENTITY_IDENTIFIER.
     * 
     * @param entity_id
     */
    public void setEntity_id(org.openehr.schemas.v1.HIER_OBJECT_ID entity_id) {
        this.entity_id = entity_id;
    }


    /**
     * Gets the subject value for this EXTRACT_ENTITY_IDENTIFIER.
     * 
     * @return subject
     */
    public org.openehr.schemas.v1.PARTY_IDENTIFIED getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this EXTRACT_ENTITY_IDENTIFIER.
     * 
     * @param subject
     */
    public void setSubject(org.openehr.schemas.v1.PARTY_IDENTIFIED subject) {
        this.subject = subject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_ENTITY_IDENTIFIER)) return false;
        EXTRACT_ENTITY_IDENTIFIER other = (EXTRACT_ENTITY_IDENTIFIER) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entity_id==null && other.getEntity_id()==null) || 
             (this.entity_id!=null &&
              this.entity_id.equals(other.getEntity_id()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject())));
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
        if (getEntity_id() != null) {
            _hashCode += getEntity_id().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT_ENTITY_IDENTIFIER.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_ENTITY_IDENTIFIER"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entity_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "entity_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HIER_OBJECT_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_IDENTIFIED"));
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
