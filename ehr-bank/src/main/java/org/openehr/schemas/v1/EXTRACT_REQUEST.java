/**
 * EXTRACT_REQUEST.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_REQUEST  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.EXTRACT_SPEC extract_spec;

    private org.openehr.schemas.v1.EXTRACT_UPDATE_SPEC update_spec;

    public EXTRACT_REQUEST() {
    }

    public EXTRACT_REQUEST(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.EXTRACT_SPEC extract_spec,
           org.openehr.schemas.v1.EXTRACT_UPDATE_SPEC update_spec) {
        super(
            name,
            uid,
            links,
            archetype_details,
            feeder_audit, 
            archetype_node_id);
        this.extract_spec = extract_spec;
        this.update_spec = update_spec;
    }


    /**
     * Gets the extract_spec value for this EXTRACT_REQUEST.
     * 
     * @return extract_spec
     */
    public org.openehr.schemas.v1.EXTRACT_SPEC getExtract_spec() {
        return extract_spec;
    }


    /**
     * Sets the extract_spec value for this EXTRACT_REQUEST.
     * 
     * @param extract_spec
     */
    public void setExtract_spec(org.openehr.schemas.v1.EXTRACT_SPEC extract_spec) {
        this.extract_spec = extract_spec;
    }


    /**
     * Gets the update_spec value for this EXTRACT_REQUEST.
     * 
     * @return update_spec
     */
    public org.openehr.schemas.v1.EXTRACT_UPDATE_SPEC getUpdate_spec() {
        return update_spec;
    }


    /**
     * Sets the update_spec value for this EXTRACT_REQUEST.
     * 
     * @param update_spec
     */
    public void setUpdate_spec(org.openehr.schemas.v1.EXTRACT_UPDATE_SPEC update_spec) {
        this.update_spec = update_spec;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_REQUEST)) return false;
        EXTRACT_REQUEST other = (EXTRACT_REQUEST) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.extract_spec==null && other.getExtract_spec()==null) || 
             (this.extract_spec!=null &&
              this.extract_spec.equals(other.getExtract_spec()))) &&
            ((this.update_spec==null && other.getUpdate_spec()==null) || 
             (this.update_spec!=null &&
              this.update_spec.equals(other.getUpdate_spec())));
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
        if (getExtract_spec() != null) {
            _hashCode += getExtract_spec().hashCode();
        }
        if (getUpdate_spec() != null) {
            _hashCode += getUpdate_spec().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT_REQUEST.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_REQUEST"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extract_spec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "extract_spec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_SPEC"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("update_spec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "update_spec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_UPDATE_SPEC"));
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
