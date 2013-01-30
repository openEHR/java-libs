/**
 * ENTRY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class ENTRY  extends org.openehr.schemas.v1.CONTENT_ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE language;

    private org.openehr.schemas.v1.CODE_PHRASE encoding;

    private org.openehr.schemas.v1.PARTY_PROXY subject;

    private org.openehr.schemas.v1.PARTY_PROXY provider;

    private org.openehr.schemas.v1.PARTICIPATION[] other_participations;

    private org.openehr.schemas.v1.OBJECT_REF work_flow_id;

    public ENTRY() {
    }

    public ENTRY(
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
           org.openehr.schemas.v1.OBJECT_REF work_flow_id) {
        super(
            archetype_node_id,
            name,
            uid,
            links,
            archetype_details,
            feeder_audit);
        this.language = language;
        this.encoding = encoding;
        this.subject = subject;
        this.provider = provider;
        this.other_participations = other_participations;
        this.work_flow_id = work_flow_id;
    }


    /**
     * Gets the language value for this ENTRY.
     * 
     * @return language
     */
    public org.openehr.schemas.v1.CODE_PHRASE getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this ENTRY.
     * 
     * @param language
     */
    public void setLanguage(org.openehr.schemas.v1.CODE_PHRASE language) {
        this.language = language;
    }


    /**
     * Gets the encoding value for this ENTRY.
     * 
     * @return encoding
     */
    public org.openehr.schemas.v1.CODE_PHRASE getEncoding() {
        return encoding;
    }


    /**
     * Sets the encoding value for this ENTRY.
     * 
     * @param encoding
     */
    public void setEncoding(org.openehr.schemas.v1.CODE_PHRASE encoding) {
        this.encoding = encoding;
    }


    /**
     * Gets the subject value for this ENTRY.
     * 
     * @return subject
     */
    public org.openehr.schemas.v1.PARTY_PROXY getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this ENTRY.
     * 
     * @param subject
     */
    public void setSubject(org.openehr.schemas.v1.PARTY_PROXY subject) {
        this.subject = subject;
    }


    /**
     * Gets the provider value for this ENTRY.
     * 
     * @return provider
     */
    public org.openehr.schemas.v1.PARTY_PROXY getProvider() {
        return provider;
    }


    /**
     * Sets the provider value for this ENTRY.
     * 
     * @param provider
     */
    public void setProvider(org.openehr.schemas.v1.PARTY_PROXY provider) {
        this.provider = provider;
    }


    /**
     * Gets the other_participations value for this ENTRY.
     * 
     * @return other_participations
     */
    public org.openehr.schemas.v1.PARTICIPATION[] getOther_participations() {
        return other_participations;
    }


    /**
     * Sets the other_participations value for this ENTRY.
     * 
     * @param other_participations
     */
    public void setOther_participations(org.openehr.schemas.v1.PARTICIPATION[] other_participations) {
        this.other_participations = other_participations;
    }

    public org.openehr.schemas.v1.PARTICIPATION getOther_participations(int i) {
        return this.other_participations[i];
    }

    public void setOther_participations(int i, org.openehr.schemas.v1.PARTICIPATION _value) {
        this.other_participations[i] = _value;
    }


    /**
     * Gets the work_flow_id value for this ENTRY.
     * 
     * @return work_flow_id
     */
    public org.openehr.schemas.v1.OBJECT_REF getWork_flow_id() {
        return work_flow_id;
    }


    /**
     * Sets the work_flow_id value for this ENTRY.
     * 
     * @param work_flow_id
     */
    public void setWork_flow_id(org.openehr.schemas.v1.OBJECT_REF work_flow_id) {
        this.work_flow_id = work_flow_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ENTRY)) return false;
        ENTRY other = (ENTRY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.encoding==null && other.getEncoding()==null) || 
             (this.encoding!=null &&
              this.encoding.equals(other.getEncoding()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.provider==null && other.getProvider()==null) || 
             (this.provider!=null &&
              this.provider.equals(other.getProvider()))) &&
            ((this.other_participations==null && other.getOther_participations()==null) || 
             (this.other_participations!=null &&
              java.util.Arrays.equals(this.other_participations, other.getOther_participations()))) &&
            ((this.work_flow_id==null && other.getWork_flow_id()==null) || 
             (this.work_flow_id!=null &&
              this.work_flow_id.equals(other.getWork_flow_id())));
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
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getEncoding() != null) {
            _hashCode += getEncoding().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getProvider() != null) {
            _hashCode += getProvider().hashCode();
        }
        if (getOther_participations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOther_participations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOther_participations(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getWork_flow_id() != null) {
            _hashCode += getWork_flow_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ENTRY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ENTRY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encoding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "encoding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_PROXY"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provider");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "provider"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_PROXY"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other_participations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "other_participations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTICIPATION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("work_flow_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "work_flow_id"));
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
