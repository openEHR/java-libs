/**
 * OBJECT_REF.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class OBJECT_REF  implements java.io.Serializable {
    private org.openehr.schemas.v1.OBJECT_ID id;

    private org.apache.axis.types.Token namespace;

    private org.apache.axis.types.Token type;

    public OBJECT_REF() {
    }

    public OBJECT_REF(
           org.openehr.schemas.v1.OBJECT_ID id,
           org.apache.axis.types.Token namespace,
           org.apache.axis.types.Token type) {
           this.id = id;
           this.namespace = namespace;
           this.type = type;
    }


    /**
     * Gets the id value for this OBJECT_REF.
     * 
     * @return id
     */
    public org.openehr.schemas.v1.OBJECT_ID getId() {
        return id;
    }


    /**
     * Sets the id value for this OBJECT_REF.
     * 
     * @param id
     */
    public void setId(org.openehr.schemas.v1.OBJECT_ID id) {
        this.id = id;
    }


    /**
     * Gets the namespace value for this OBJECT_REF.
     * 
     * @return namespace
     */
    public org.apache.axis.types.Token getNamespace() {
        return namespace;
    }


    /**
     * Sets the namespace value for this OBJECT_REF.
     * 
     * @param namespace
     */
    public void setNamespace(org.apache.axis.types.Token namespace) {
        this.namespace = namespace;
    }


    /**
     * Gets the type value for this OBJECT_REF.
     * 
     * @return type
     */
    public org.apache.axis.types.Token getType() {
        return type;
    }


    /**
     * Sets the type value for this OBJECT_REF.
     * 
     * @param type
     */
    public void setType(org.apache.axis.types.Token type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OBJECT_REF)) return false;
        OBJECT_REF other = (OBJECT_REF) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.namespace==null && other.getNamespace()==null) || 
             (this.namespace!=null &&
              this.namespace.equals(other.getNamespace()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNamespace() != null) {
            _hashCode += getNamespace().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OBJECT_REF.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("namespace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "namespace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "token"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "token"));
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
