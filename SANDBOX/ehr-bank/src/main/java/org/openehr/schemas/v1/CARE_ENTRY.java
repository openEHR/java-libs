/**
 * CARE_ENTRY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class CARE_ENTRY  extends org.openehr.schemas.v1.ENTRY  implements java.io.Serializable {
    private org.openehr.schemas.v1.ITEM_STRUCTURE protocol;

    private org.openehr.schemas.v1.OBJECT_REF guideline_id;

    public CARE_ENTRY() {
    }

    public CARE_ENTRY(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.CODE_PHRASE language,
           org.openehr.schemas.v1.CODE_PHRASE encoding,
           org.openehr.schemas.v1.PARTY_PROXY subject,
           org.openehr.schemas.v1.PARTY_PROXY provider,
           org.openehr.schemas.v1.PARTICIPATION[] other_participations,
           org.openehr.schemas.v1.OBJECT_REF work_flow_id,
           org.openehr.schemas.v1.ITEM_STRUCTURE protocol,
           org.openehr.schemas.v1.OBJECT_REF guideline_id) {
        super(
            archetype_node_id,
            name,
            uid,
            links,
            archetype_details,
            feeder_audit,
            language,
            encoding,
            subject,
            provider,
            other_participations,
            work_flow_id);
        this.protocol = protocol;
        this.guideline_id = guideline_id;
    }


    /**
     * Gets the protocol value for this CARE_ENTRY.
     * 
     * @return protocol
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getProtocol() {
        return protocol;
    }


    /**
     * Sets the protocol value for this CARE_ENTRY.
     * 
     * @param protocol
     */
    public void setProtocol(org.openehr.schemas.v1.ITEM_STRUCTURE protocol) {
        this.protocol = protocol;
    }


    /**
     * Gets the guideline_id value for this CARE_ENTRY.
     * 
     * @return guideline_id
     */
    public org.openehr.schemas.v1.OBJECT_REF getGuideline_id() {
        return guideline_id;
    }


    /**
     * Sets the guideline_id value for this CARE_ENTRY.
     * 
     * @param guideline_id
     */
    public void setGuideline_id(org.openehr.schemas.v1.OBJECT_REF guideline_id) {
        this.guideline_id = guideline_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CARE_ENTRY)) return false;
        CARE_ENTRY other = (CARE_ENTRY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.protocol==null && other.getProtocol()==null) || 
             (this.protocol!=null &&
              this.protocol.equals(other.getProtocol()))) &&
            ((this.guideline_id==null && other.getGuideline_id()==null) || 
             (this.guideline_id!=null &&
              this.guideline_id.equals(other.getGuideline_id())));
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
        if (getProtocol() != null) {
            _hashCode += getProtocol().hashCode();
        }
        if (getGuideline_id() != null) {
            _hashCode += getGuideline_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CARE_ENTRY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CARE_ENTRY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protocol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "protocol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("guideline_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "guideline_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF"));
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
