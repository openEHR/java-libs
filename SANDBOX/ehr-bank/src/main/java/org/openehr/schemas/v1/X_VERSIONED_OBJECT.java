/**
 * X_VERSIONED_OBJECT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class X_VERSIONED_OBJECT  implements java.io.Serializable {
    private org.openehr.schemas.v1.HIER_OBJECT_ID uid;

    private org.openehr.schemas.v1.OBJECT_REF owner_id;

    private org.openehr.schemas.v1.DV_DATE_TIME time_created;

    private int total_version_count;

    private int extract_version_count;

    private org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] revision_history;

    private org.openehr.schemas.v1.ORIGINAL_VERSION[] versions;

    public X_VERSIONED_OBJECT() {
    }

    public X_VERSIONED_OBJECT(
           org.openehr.schemas.v1.HIER_OBJECT_ID uid,
           org.openehr.schemas.v1.OBJECT_REF owner_id,
           org.openehr.schemas.v1.DV_DATE_TIME time_created,
           int total_version_count,
           int extract_version_count,
           org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] revision_history,
           org.openehr.schemas.v1.ORIGINAL_VERSION[] versions) {
           this.uid = uid;
           this.owner_id = owner_id;
           this.time_created = time_created;
           this.total_version_count = total_version_count;
           this.extract_version_count = extract_version_count;
           this.revision_history = revision_history;
           this.versions = versions;
    }


    /**
     * Gets the uid value for this X_VERSIONED_OBJECT.
     * 
     * @return uid
     */
    public org.openehr.schemas.v1.HIER_OBJECT_ID getUid() {
        return uid;
    }


    /**
     * Sets the uid value for this X_VERSIONED_OBJECT.
     * 
     * @param uid
     */
    public void setUid(org.openehr.schemas.v1.HIER_OBJECT_ID uid) {
        this.uid = uid;
    }


    /**
     * Gets the owner_id value for this X_VERSIONED_OBJECT.
     * 
     * @return owner_id
     */
    public org.openehr.schemas.v1.OBJECT_REF getOwner_id() {
        return owner_id;
    }


    /**
     * Sets the owner_id value for this X_VERSIONED_OBJECT.
     * 
     * @param owner_id
     */
    public void setOwner_id(org.openehr.schemas.v1.OBJECT_REF owner_id) {
        this.owner_id = owner_id;
    }


    /**
     * Gets the time_created value for this X_VERSIONED_OBJECT.
     * 
     * @return time_created
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getTime_created() {
        return time_created;
    }


    /**
     * Sets the time_created value for this X_VERSIONED_OBJECT.
     * 
     * @param time_created
     */
    public void setTime_created(org.openehr.schemas.v1.DV_DATE_TIME time_created) {
        this.time_created = time_created;
    }


    /**
     * Gets the total_version_count value for this X_VERSIONED_OBJECT.
     * 
     * @return total_version_count
     */
    public int getTotal_version_count() {
        return total_version_count;
    }


    /**
     * Sets the total_version_count value for this X_VERSIONED_OBJECT.
     * 
     * @param total_version_count
     */
    public void setTotal_version_count(int total_version_count) {
        this.total_version_count = total_version_count;
    }


    /**
     * Gets the extract_version_count value for this X_VERSIONED_OBJECT.
     * 
     * @return extract_version_count
     */
    public int getExtract_version_count() {
        return extract_version_count;
    }


    /**
     * Sets the extract_version_count value for this X_VERSIONED_OBJECT.
     * 
     * @param extract_version_count
     */
    public void setExtract_version_count(int extract_version_count) {
        this.extract_version_count = extract_version_count;
    }


    /**
     * Gets the revision_history value for this X_VERSIONED_OBJECT.
     * 
     * @return revision_history
     */
    public org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] getRevision_history() {
        return revision_history;
    }


    /**
     * Sets the revision_history value for this X_VERSIONED_OBJECT.
     * 
     * @param revision_history
     */
    public void setRevision_history(org.openehr.schemas.v1.REVISION_HISTORY_ITEM[] revision_history) {
        this.revision_history = revision_history;
    }


    /**
     * Gets the versions value for this X_VERSIONED_OBJECT.
     * 
     * @return versions
     */
    public org.openehr.schemas.v1.ORIGINAL_VERSION[] getVersions() {
        return versions;
    }


    /**
     * Sets the versions value for this X_VERSIONED_OBJECT.
     * 
     * @param versions
     */
    public void setVersions(org.openehr.schemas.v1.ORIGINAL_VERSION[] versions) {
        this.versions = versions;
    }

    public org.openehr.schemas.v1.ORIGINAL_VERSION getVersions(int i) {
        return this.versions[i];
    }

    public void setVersions(int i, org.openehr.schemas.v1.ORIGINAL_VERSION _value) {
        this.versions[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof X_VERSIONED_OBJECT)) return false;
        X_VERSIONED_OBJECT other = (X_VERSIONED_OBJECT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.uid==null && other.getUid()==null) || 
             (this.uid!=null &&
              this.uid.equals(other.getUid()))) &&
            ((this.owner_id==null && other.getOwner_id()==null) || 
             (this.owner_id!=null &&
              this.owner_id.equals(other.getOwner_id()))) &&
            ((this.time_created==null && other.getTime_created()==null) || 
             (this.time_created!=null &&
              this.time_created.equals(other.getTime_created()))) &&
            this.total_version_count == other.getTotal_version_count() &&
            this.extract_version_count == other.getExtract_version_count() &&
            ((this.revision_history==null && other.getRevision_history()==null) || 
             (this.revision_history!=null &&
              java.util.Arrays.equals(this.revision_history, other.getRevision_history()))) &&
            ((this.versions==null && other.getVersions()==null) || 
             (this.versions!=null &&
              java.util.Arrays.equals(this.versions, other.getVersions())));
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
        if (getUid() != null) {
            _hashCode += getUid().hashCode();
        }
        if (getOwner_id() != null) {
            _hashCode += getOwner_id().hashCode();
        }
        if (getTime_created() != null) {
            _hashCode += getTime_created().hashCode();
        }
        _hashCode += getTotal_version_count();
        _hashCode += getExtract_version_count();
        if (getRevision_history() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRevision_history());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRevision_history(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getVersions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVersions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVersions(), i);
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
        new org.apache.axis.description.TypeDesc(X_VERSIONED_OBJECT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "X_VERSIONED_OBJECT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "uid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HIER_OBJECT_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("owner_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "owner_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_created");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "time_created"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total_version_count");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "total_version_count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extract_version_count");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "extract_version_count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("revision_history");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "revision_history"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REVISION_HISTORY_ITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "versions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ORIGINAL_VERSION"));
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
