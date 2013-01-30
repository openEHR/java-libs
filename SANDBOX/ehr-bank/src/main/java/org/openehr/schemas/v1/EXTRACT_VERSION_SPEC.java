/**
 * EXTRACT_VERSION_SPEC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_VERSION_SPEC  implements java.io.Serializable {
    private boolean include_all_versions;

    private org.openehr.schemas.v1.DV_INTERVAL commit_time_interval;

    private boolean includes_revision_history;

    private boolean includes_data;

    public EXTRACT_VERSION_SPEC() {
    }

    public EXTRACT_VERSION_SPEC(
           boolean include_all_versions,
           org.openehr.schemas.v1.DV_INTERVAL commit_time_interval,
           boolean includes_revision_history,
           boolean includes_data) {
           this.include_all_versions = include_all_versions;
           this.commit_time_interval = commit_time_interval;
           this.includes_revision_history = includes_revision_history;
           this.includes_data = includes_data;
    }


    /**
     * Gets the include_all_versions value for this EXTRACT_VERSION_SPEC.
     * 
     * @return include_all_versions
     */
    public boolean isInclude_all_versions() {
        return include_all_versions;
    }


    /**
     * Sets the include_all_versions value for this EXTRACT_VERSION_SPEC.
     * 
     * @param include_all_versions
     */
    public void setInclude_all_versions(boolean include_all_versions) {
        this.include_all_versions = include_all_versions;
    }


    /**
     * Gets the commit_time_interval value for this EXTRACT_VERSION_SPEC.
     * 
     * @return commit_time_interval
     */
    public org.openehr.schemas.v1.DV_INTERVAL getCommit_time_interval() {
        return commit_time_interval;
    }


    /**
     * Sets the commit_time_interval value for this EXTRACT_VERSION_SPEC.
     * 
     * @param commit_time_interval
     */
    public void setCommit_time_interval(org.openehr.schemas.v1.DV_INTERVAL commit_time_interval) {
        this.commit_time_interval = commit_time_interval;
    }


    /**
     * Gets the includes_revision_history value for this EXTRACT_VERSION_SPEC.
     * 
     * @return includes_revision_history
     */
    public boolean isIncludes_revision_history() {
        return includes_revision_history;
    }


    /**
     * Sets the includes_revision_history value for this EXTRACT_VERSION_SPEC.
     * 
     * @param includes_revision_history
     */
    public void setIncludes_revision_history(boolean includes_revision_history) {
        this.includes_revision_history = includes_revision_history;
    }


    /**
     * Gets the includes_data value for this EXTRACT_VERSION_SPEC.
     * 
     * @return includes_data
     */
    public boolean isIncludes_data() {
        return includes_data;
    }


    /**
     * Sets the includes_data value for this EXTRACT_VERSION_SPEC.
     * 
     * @param includes_data
     */
    public void setIncludes_data(boolean includes_data) {
        this.includes_data = includes_data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_VERSION_SPEC)) return false;
        EXTRACT_VERSION_SPEC other = (EXTRACT_VERSION_SPEC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.include_all_versions == other.isInclude_all_versions() &&
            ((this.commit_time_interval==null && other.getCommit_time_interval()==null) || 
             (this.commit_time_interval!=null &&
              this.commit_time_interval.equals(other.getCommit_time_interval()))) &&
            this.includes_revision_history == other.isIncludes_revision_history() &&
            this.includes_data == other.isIncludes_data();
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
        _hashCode += (isInclude_all_versions() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCommit_time_interval() != null) {
            _hashCode += getCommit_time_interval().hashCode();
        }
        _hashCode += (isIncludes_revision_history() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIncludes_data() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT_VERSION_SPEC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_VERSION_SPEC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("include_all_versions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "include_all_versions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commit_time_interval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "commit_time_interval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_INTERVAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includes_revision_history");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "includes_revision_history"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includes_data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "includes_data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
