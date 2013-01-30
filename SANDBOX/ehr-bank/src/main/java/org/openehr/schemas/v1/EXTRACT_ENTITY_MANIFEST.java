/**
 * EXTRACT_ENTITY_MANIFEST.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_ENTITY_MANIFEST  implements java.io.Serializable {
    private org.openehr.schemas.v1.OBJECT_REF[] item_list;

    private org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER entity_identifier;

    public EXTRACT_ENTITY_MANIFEST() {
    }

    public EXTRACT_ENTITY_MANIFEST(
           org.openehr.schemas.v1.OBJECT_REF[] item_list,
           org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER entity_identifier) {
           this.item_list = item_list;
           this.entity_identifier = entity_identifier;
    }


    /**
     * Gets the item_list value for this EXTRACT_ENTITY_MANIFEST.
     * 
     * @return item_list
     */
    public org.openehr.schemas.v1.OBJECT_REF[] getItem_list() {
        return item_list;
    }


    /**
     * Sets the item_list value for this EXTRACT_ENTITY_MANIFEST.
     * 
     * @param item_list
     */
    public void setItem_list(org.openehr.schemas.v1.OBJECT_REF[] item_list) {
        this.item_list = item_list;
    }

    public org.openehr.schemas.v1.OBJECT_REF getItem_list(int i) {
        return this.item_list[i];
    }

    public void setItem_list(int i, org.openehr.schemas.v1.OBJECT_REF _value) {
        this.item_list[i] = _value;
    }


    /**
     * Gets the entity_identifier value for this EXTRACT_ENTITY_MANIFEST.
     * 
     * @return entity_identifier
     */
    public org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER getEntity_identifier() {
        return entity_identifier;
    }


    /**
     * Sets the entity_identifier value for this EXTRACT_ENTITY_MANIFEST.
     * 
     * @param entity_identifier
     */
    public void setEntity_identifier(org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER entity_identifier) {
        this.entity_identifier = entity_identifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_ENTITY_MANIFEST)) return false;
        EXTRACT_ENTITY_MANIFEST other = (EXTRACT_ENTITY_MANIFEST) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.item_list==null && other.getItem_list()==null) || 
             (this.item_list!=null &&
              java.util.Arrays.equals(this.item_list, other.getItem_list()))) &&
            ((this.entity_identifier==null && other.getEntity_identifier()==null) || 
             (this.entity_identifier!=null &&
              this.entity_identifier.equals(other.getEntity_identifier())));
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
        if (getItem_list() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItem_list());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItem_list(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEntity_identifier() != null) {
            _hashCode += getEntity_identifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT_ENTITY_MANIFEST.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_ENTITY_MANIFEST"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item_list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "item_list"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entity_identifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "entity_identifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_ENTITY_IDENTIFIER"));
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
