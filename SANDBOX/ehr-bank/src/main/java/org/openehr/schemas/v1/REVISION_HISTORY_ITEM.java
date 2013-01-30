/**
 * REVISION_HISTORY_ITEM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class REVISION_HISTORY_ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.OBJECT_VERSION_ID version_id;

    private org.openehr.schemas.v1.AUDIT_DETAILS[] audits;

    public REVISION_HISTORY_ITEM() {
    }

    public REVISION_HISTORY_ITEM(
           org.openehr.schemas.v1.OBJECT_VERSION_ID version_id,
           org.openehr.schemas.v1.AUDIT_DETAILS[] audits) {
           this.version_id = version_id;
           this.audits = audits;
    }


    /**
     * Gets the version_id value for this REVISION_HISTORY_ITEM.
     * 
     * @return version_id
     */
    public org.openehr.schemas.v1.OBJECT_VERSION_ID getVersion_id() {
        return version_id;
    }


    /**
     * Sets the version_id value for this REVISION_HISTORY_ITEM.
     * 
     * @param version_id
     */
    public void setVersion_id(org.openehr.schemas.v1.OBJECT_VERSION_ID version_id) {
        this.version_id = version_id;
    }


    /**
     * Gets the audits value for this REVISION_HISTORY_ITEM.
     * 
     * @return audits
     */
    public org.openehr.schemas.v1.AUDIT_DETAILS[] getAudits() {
        return audits;
    }


    /**
     * Sets the audits value for this REVISION_HISTORY_ITEM.
     * 
     * @param audits
     */
    public void setAudits(org.openehr.schemas.v1.AUDIT_DETAILS[] audits) {
        this.audits = audits;
    }

    public org.openehr.schemas.v1.AUDIT_DETAILS getAudits(int i) {
        return this.audits[i];
    }

    public void setAudits(int i, org.openehr.schemas.v1.AUDIT_DETAILS _value) {
        this.audits[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof REVISION_HISTORY_ITEM)) return false;
        REVISION_HISTORY_ITEM other = (REVISION_HISTORY_ITEM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.version_id==null && other.getVersion_id()==null) || 
             (this.version_id!=null &&
              this.version_id.equals(other.getVersion_id()))) &&
            ((this.audits==null && other.getAudits()==null) || 
             (this.audits!=null &&
              java.util.Arrays.equals(this.audits, other.getAudits())));
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
        if (getVersion_id() != null) {
            _hashCode += getVersion_id().hashCode();
        }
        if (getAudits() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAudits());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAudits(), i);
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
        new org.apache.axis.description.TypeDesc(REVISION_HISTORY_ITEM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "REVISION_HISTORY_ITEM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "version_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_VERSION_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("audits");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "audits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUDIT_DETAILS"));
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
