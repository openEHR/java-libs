/**
 * LOCATABLE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_TEXT name;

    private org.openehr.schemas.v1.UID_BASED_ID uid;

    private org.openehr.schemas.v1.LINK[] links;

    private org.openehr.schemas.v1.ARCHETYPED archetype_details;

    private org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit;

    private java.lang.String archetype_node_id;  // attribute

    public LOCATABLE() {
    }

    public LOCATABLE(
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           java.lang.String archetype_node_id) {
           this.name = name;
           this.uid = uid;
           this.links = links;
           this.archetype_details = archetype_details;
           this.feeder_audit = feeder_audit;
           this.archetype_node_id = archetype_node_id;
    }


    /**
     * Gets the name value for this LOCATABLE.
     * 
     * @return name
     */
    public org.openehr.schemas.v1.DV_TEXT getName() {
        return name;
    }


    /**
     * Sets the name value for this LOCATABLE.
     * 
     * @param name
     */
    public void setName(org.openehr.schemas.v1.DV_TEXT name) {
        this.name = name;
    }


    /**
     * Gets the uid value for this LOCATABLE.
     * 
     * @return uid
     */
    public org.openehr.schemas.v1.UID_BASED_ID getUid() {
        return uid;
    }


    /**
     * Sets the uid value for this LOCATABLE.
     * 
     * @param uid
     */
    public void setUid(org.openehr.schemas.v1.UID_BASED_ID uid) {
        this.uid = uid;
    }


    /**
     * Gets the links value for this LOCATABLE.
     * 
     * @return links
     */
    public org.openehr.schemas.v1.LINK[] getLinks() {
        return links;
    }


    /**
     * Sets the links value for this LOCATABLE.
     * 
     * @param links
     */
    public void setLinks(org.openehr.schemas.v1.LINK[] links) {
        this.links = links;
    }

    public org.openehr.schemas.v1.LINK getLinks(int i) {
        return this.links[i];
    }

    public void setLinks(int i, org.openehr.schemas.v1.LINK _value) {
        this.links[i] = _value;
    }


    /**
     * Gets the archetype_details value for this LOCATABLE.
     * 
     * @return archetype_details
     */
    public org.openehr.schemas.v1.ARCHETYPED getArchetype_details() {
        return archetype_details;
    }


    /**
     * Sets the archetype_details value for this LOCATABLE.
     * 
     * @param archetype_details
     */
    public void setArchetype_details(org.openehr.schemas.v1.ARCHETYPED archetype_details) {
        this.archetype_details = archetype_details;
    }


    /**
     * Gets the feeder_audit value for this LOCATABLE.
     * 
     * @return feeder_audit
     */
    public org.openehr.schemas.v1.FEEDER_AUDIT getFeeder_audit() {
        return feeder_audit;
    }


    /**
     * Sets the feeder_audit value for this LOCATABLE.
     * 
     * @param feeder_audit
     */
    public void setFeeder_audit(org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit) {
        this.feeder_audit = feeder_audit;
    }


    /**
     * Gets the archetype_node_id value for this LOCATABLE.
     * 
     * @return archetype_node_id
     */
    public java.lang.String getArchetype_node_id() {
        return archetype_node_id;
    }


    /**
     * Sets the archetype_node_id value for this LOCATABLE.
     * 
     * @param archetype_node_id
     */
    public void setArchetype_node_id(java.lang.String archetype_node_id) {
        this.archetype_node_id = archetype_node_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LOCATABLE)) return false;
        LOCATABLE other = (LOCATABLE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.uid==null && other.getUid()==null) || 
             (this.uid!=null &&
              this.uid.equals(other.getUid()))) &&
            ((this.links==null && other.getLinks()==null) || 
             (this.links!=null &&
              java.util.Arrays.equals(this.links, other.getLinks()))) &&
            ((this.archetype_details==null && other.getArchetype_details()==null) || 
             (this.archetype_details!=null &&
              this.archetype_details.equals(other.getArchetype_details()))) &&
            ((this.feeder_audit==null && other.getFeeder_audit()==null) || 
             (this.feeder_audit!=null &&
              this.feeder_audit.equals(other.getFeeder_audit()))) &&
            ((this.archetype_node_id==null && other.getArchetype_node_id()==null) || 
             (this.archetype_node_id!=null &&
              this.archetype_node_id.equals(other.getArchetype_node_id())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getUid() != null) {
            _hashCode += getUid().hashCode();
        }
        if (getLinks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLinks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLinks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getArchetype_details() != null) {
            _hashCode += getArchetype_details().hashCode();
        }
        if (getFeeder_audit() != null) {
            _hashCode += getFeeder_audit().hashCode();
        }
        if (getArchetype_node_id() != null) {
            _hashCode += getArchetype_node_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LOCATABLE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "LOCATABLE"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("archetype_node_id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "archetype_node_id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "uid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "UID_BASED_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "links"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "LINK"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archetype_details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "archetype_details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPED"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeder_audit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "feeder_audit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "FEEDER_AUDIT"));
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
