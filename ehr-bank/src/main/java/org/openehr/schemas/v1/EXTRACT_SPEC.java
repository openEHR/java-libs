/**
 * EXTRACT_SPEC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_SPEC  implements java.io.Serializable {
    private java.lang.String extract_type;

    private boolean includes_multimedia;

    private int link_depth;

    private org.openehr.schemas.v1.DV_PARSABLE[] criteria;

    private boolean includes_directory;

    private org.openehr.schemas.v1.ARCHETYPE_ID directory_archetype;

    private org.openehr.schemas.v1.ITEM_STRUCTURE other_details;

    private org.openehr.schemas.v1.EXTRACT_VERSION_SPEC version_spec;

    private org.openehr.schemas.v1.EXTRACT_ENTITY_MANIFEST[] manifest;

    public EXTRACT_SPEC() {
    }

    public EXTRACT_SPEC(
           java.lang.String extract_type,
           boolean includes_multimedia,
           int link_depth,
           org.openehr.schemas.v1.DV_PARSABLE[] criteria,
           boolean includes_directory,
           org.openehr.schemas.v1.ARCHETYPE_ID directory_archetype,
           org.openehr.schemas.v1.ITEM_STRUCTURE other_details,
           org.openehr.schemas.v1.EXTRACT_VERSION_SPEC version_spec,
           org.openehr.schemas.v1.EXTRACT_ENTITY_MANIFEST[] manifest) {
           this.extract_type = extract_type;
           this.includes_multimedia = includes_multimedia;
           this.link_depth = link_depth;
           this.criteria = criteria;
           this.includes_directory = includes_directory;
           this.directory_archetype = directory_archetype;
           this.other_details = other_details;
           this.version_spec = version_spec;
           this.manifest = manifest;
    }


    /**
     * Gets the extract_type value for this EXTRACT_SPEC.
     * 
     * @return extract_type
     */
    public java.lang.String getExtract_type() {
        return extract_type;
    }


    /**
     * Sets the extract_type value for this EXTRACT_SPEC.
     * 
     * @param extract_type
     */
    public void setExtract_type(java.lang.String extract_type) {
        this.extract_type = extract_type;
    }


    /**
     * Gets the includes_multimedia value for this EXTRACT_SPEC.
     * 
     * @return includes_multimedia
     */
    public boolean isIncludes_multimedia() {
        return includes_multimedia;
    }


    /**
     * Sets the includes_multimedia value for this EXTRACT_SPEC.
     * 
     * @param includes_multimedia
     */
    public void setIncludes_multimedia(boolean includes_multimedia) {
        this.includes_multimedia = includes_multimedia;
    }


    /**
     * Gets the link_depth value for this EXTRACT_SPEC.
     * 
     * @return link_depth
     */
    public int getLink_depth() {
        return link_depth;
    }


    /**
     * Sets the link_depth value for this EXTRACT_SPEC.
     * 
     * @param link_depth
     */
    public void setLink_depth(int link_depth) {
        this.link_depth = link_depth;
    }


    /**
     * Gets the criteria value for this EXTRACT_SPEC.
     * 
     * @return criteria
     */
    public org.openehr.schemas.v1.DV_PARSABLE[] getCriteria() {
        return criteria;
    }


    /**
     * Sets the criteria value for this EXTRACT_SPEC.
     * 
     * @param criteria
     */
    public void setCriteria(org.openehr.schemas.v1.DV_PARSABLE[] criteria) {
        this.criteria = criteria;
    }

    public org.openehr.schemas.v1.DV_PARSABLE getCriteria(int i) {
        return this.criteria[i];
    }

    public void setCriteria(int i, org.openehr.schemas.v1.DV_PARSABLE _value) {
        this.criteria[i] = _value;
    }


    /**
     * Gets the includes_directory value for this EXTRACT_SPEC.
     * 
     * @return includes_directory
     */
    public boolean isIncludes_directory() {
        return includes_directory;
    }


    /**
     * Sets the includes_directory value for this EXTRACT_SPEC.
     * 
     * @param includes_directory
     */
    public void setIncludes_directory(boolean includes_directory) {
        this.includes_directory = includes_directory;
    }


    /**
     * Gets the directory_archetype value for this EXTRACT_SPEC.
     * 
     * @return directory_archetype
     */
    public org.openehr.schemas.v1.ARCHETYPE_ID getDirectory_archetype() {
        return directory_archetype;
    }


    /**
     * Sets the directory_archetype value for this EXTRACT_SPEC.
     * 
     * @param directory_archetype
     */
    public void setDirectory_archetype(org.openehr.schemas.v1.ARCHETYPE_ID directory_archetype) {
        this.directory_archetype = directory_archetype;
    }


    /**
     * Gets the other_details value for this EXTRACT_SPEC.
     * 
     * @return other_details
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getOther_details() {
        return other_details;
    }


    /**
     * Sets the other_details value for this EXTRACT_SPEC.
     * 
     * @param other_details
     */
    public void setOther_details(org.openehr.schemas.v1.ITEM_STRUCTURE other_details) {
        this.other_details = other_details;
    }


    /**
     * Gets the version_spec value for this EXTRACT_SPEC.
     * 
     * @return version_spec
     */
    public org.openehr.schemas.v1.EXTRACT_VERSION_SPEC getVersion_spec() {
        return version_spec;
    }


    /**
     * Sets the version_spec value for this EXTRACT_SPEC.
     * 
     * @param version_spec
     */
    public void setVersion_spec(org.openehr.schemas.v1.EXTRACT_VERSION_SPEC version_spec) {
        this.version_spec = version_spec;
    }


    /**
     * Gets the manifest value for this EXTRACT_SPEC.
     * 
     * @return manifest
     */
    public org.openehr.schemas.v1.EXTRACT_ENTITY_MANIFEST[] getManifest() {
        return manifest;
    }


    /**
     * Sets the manifest value for this EXTRACT_SPEC.
     * 
     * @param manifest
     */
    public void setManifest(org.openehr.schemas.v1.EXTRACT_ENTITY_MANIFEST[] manifest) {
        this.manifest = manifest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_SPEC)) return false;
        EXTRACT_SPEC other = (EXTRACT_SPEC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.extract_type==null && other.getExtract_type()==null) || 
             (this.extract_type!=null &&
              this.extract_type.equals(other.getExtract_type()))) &&
            this.includes_multimedia == other.isIncludes_multimedia() &&
            this.link_depth == other.getLink_depth() &&
            ((this.criteria==null && other.getCriteria()==null) || 
             (this.criteria!=null &&
              java.util.Arrays.equals(this.criteria, other.getCriteria()))) &&
            this.includes_directory == other.isIncludes_directory() &&
            ((this.directory_archetype==null && other.getDirectory_archetype()==null) || 
             (this.directory_archetype!=null &&
              this.directory_archetype.equals(other.getDirectory_archetype()))) &&
            ((this.other_details==null && other.getOther_details()==null) || 
             (this.other_details!=null &&
              this.other_details.equals(other.getOther_details()))) &&
            ((this.version_spec==null && other.getVersion_spec()==null) || 
             (this.version_spec!=null &&
              this.version_spec.equals(other.getVersion_spec()))) &&
            ((this.manifest==null && other.getManifest()==null) || 
             (this.manifest!=null &&
              java.util.Arrays.equals(this.manifest, other.getManifest())));
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
        if (getExtract_type() != null) {
            _hashCode += getExtract_type().hashCode();
        }
        _hashCode += (isIncludes_multimedia() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getLink_depth();
        if (getCriteria() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCriteria());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCriteria(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isIncludes_directory() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDirectory_archetype() != null) {
            _hashCode += getDirectory_archetype().hashCode();
        }
        if (getOther_details() != null) {
            _hashCode += getOther_details().hashCode();
        }
        if (getVersion_spec() != null) {
            _hashCode += getVersion_spec().hashCode();
        }
        if (getManifest() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManifest());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManifest(), i);
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
        new org.apache.axis.description.TypeDesc(EXTRACT_SPEC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_SPEC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extract_type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "extract_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includes_multimedia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "includes_multimedia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("link_depth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "link_depth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("criteria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "criteria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PARSABLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includes_directory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "includes_directory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("directory_archetype");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "directory_archetype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ARCHETYPE_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other_details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "other_details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version_spec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "version_spec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_VERSION_SPEC"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "manifest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_ENTITY_MANIFEST"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "entities"));
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
