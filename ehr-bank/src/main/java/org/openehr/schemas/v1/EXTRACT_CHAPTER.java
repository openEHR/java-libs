/**
 * EXTRACT_CHAPTER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_CHAPTER  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.EXTRACT_FOLDER directory;

    private org.openehr.schemas.v1.EXTRACT_CONTENT content;

    private org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER entity_identifier;

    public EXTRACT_CHAPTER() {
    }

    public EXTRACT_CHAPTER(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.EXTRACT_FOLDER directory,
           org.openehr.schemas.v1.EXTRACT_CONTENT content,
           org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER entity_identifier) {
        super(
            name,
            uid,
            links,
            archetype_details,
            feeder_audit, 
            archetype_node_id);
        this.directory = directory;
        this.content = content;
        this.entity_identifier = entity_identifier;
    }


    /**
     * Gets the directory value for this EXTRACT_CHAPTER.
     * 
     * @return directory
     */
    public org.openehr.schemas.v1.EXTRACT_FOLDER getDirectory() {
        return directory;
    }


    /**
     * Sets the directory value for this EXTRACT_CHAPTER.
     * 
     * @param directory
     */
    public void setDirectory(org.openehr.schemas.v1.EXTRACT_FOLDER directory) {
        this.directory = directory;
    }


    /**
     * Gets the content value for this EXTRACT_CHAPTER.
     * 
     * @return content
     */
    public org.openehr.schemas.v1.EXTRACT_CONTENT getContent() {
        return content;
    }


    /**
     * Sets the content value for this EXTRACT_CHAPTER.
     * 
     * @param content
     */
    public void setContent(org.openehr.schemas.v1.EXTRACT_CONTENT content) {
        this.content = content;
    }


    /**
     * Gets the entity_identifier value for this EXTRACT_CHAPTER.
     * 
     * @return entity_identifier
     */
    public org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER getEntity_identifier() {
        return entity_identifier;
    }


    /**
     * Sets the entity_identifier value for this EXTRACT_CHAPTER.
     * 
     * @param entity_identifier
     */
    public void setEntity_identifier(org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER entity_identifier) {
        this.entity_identifier = entity_identifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_CHAPTER)) return false;
        EXTRACT_CHAPTER other = (EXTRACT_CHAPTER) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.directory==null && other.getDirectory()==null) || 
             (this.directory!=null &&
              this.directory.equals(other.getDirectory()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
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
        int _hashCode = super.hashCode();
        if (getDirectory() != null) {
            _hashCode += getDirectory().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getEntity_identifier() != null) {
            _hashCode += getEntity_identifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT_CHAPTER.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_CHAPTER"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("directory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "directory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_FOLDER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_CONTENT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
