/**
 * PARTY_IDENTIFIED.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class PARTY_IDENTIFIED  extends org.openehr.schemas.v1.PARTY_PROXY  implements java.io.Serializable {
    private java.lang.String name;

    private org.openehr.schemas.v1.DV_IDENTIFIER[] identifiers;

    public PARTY_IDENTIFIED() {
    }

    public PARTY_IDENTIFIED(
           org.openehr.schemas.v1.PARTY_REF external_ref,
           java.lang.String name,
           org.openehr.schemas.v1.DV_IDENTIFIER[] identifiers) {
        super(
            external_ref);
        this.name = name;
        this.identifiers = identifiers;
    }


    /**
     * Gets the name value for this PARTY_IDENTIFIED.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PARTY_IDENTIFIED.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the identifiers value for this PARTY_IDENTIFIED.
     * 
     * @return identifiers
     */
    public org.openehr.schemas.v1.DV_IDENTIFIER[] getIdentifiers() {
        return identifiers;
    }


    /**
     * Sets the identifiers value for this PARTY_IDENTIFIED.
     * 
     * @param identifiers
     */
    public void setIdentifiers(org.openehr.schemas.v1.DV_IDENTIFIER[] identifiers) {
        this.identifiers = identifiers;
    }

    public org.openehr.schemas.v1.DV_IDENTIFIER getIdentifiers(int i) {
        return this.identifiers[i];
    }

    public void setIdentifiers(int i, org.openehr.schemas.v1.DV_IDENTIFIER _value) {
        this.identifiers[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PARTY_IDENTIFIED)) return false;
        PARTY_IDENTIFIED other = (PARTY_IDENTIFIED) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.identifiers==null && other.getIdentifiers()==null) || 
             (this.identifiers!=null &&
              java.util.Arrays.equals(this.identifiers, other.getIdentifiers())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getIdentifiers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdentifiers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdentifiers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PARTY_IDENTIFIED.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_IDENTIFIED"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identifiers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "identifiers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_IDENTIFIER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
