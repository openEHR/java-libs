/**
 * INSTRUCTION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class INSTRUCTION  extends org.openehr.schemas.v1.CARE_ENTRY  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_TEXT narrative;

    private org.openehr.schemas.v1.DV_DATE_TIME expiry_time;

    private org.openehr.schemas.v1.DV_PARSABLE wf_definition;

    private org.openehr.schemas.v1.ACTIVITY[] activities;

    public INSTRUCTION() {
    }

    public INSTRUCTION(
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
           org.openehr.schemas.v1.OBJECT_REF guideline_id,
           org.openehr.schemas.v1.DV_TEXT narrative,
           org.openehr.schemas.v1.DV_DATE_TIME expiry_time,
           org.openehr.schemas.v1.DV_PARSABLE wf_definition,
           org.openehr.schemas.v1.ACTIVITY[] activities) {
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
            work_flow_id,
            protocol,
            guideline_id);
        this.narrative = narrative;
        this.expiry_time = expiry_time;
        this.wf_definition = wf_definition;
        this.activities = activities;
    }


    /**
     * Gets the narrative value for this INSTRUCTION.
     * 
     * @return narrative
     */
    public org.openehr.schemas.v1.DV_TEXT getNarrative() {
        return narrative;
    }


    /**
     * Sets the narrative value for this INSTRUCTION.
     * 
     * @param narrative
     */
    public void setNarrative(org.openehr.schemas.v1.DV_TEXT narrative) {
        this.narrative = narrative;
    }


    /**
     * Gets the expiry_time value for this INSTRUCTION.
     * 
     * @return expiry_time
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getExpiry_time() {
        return expiry_time;
    }


    /**
     * Sets the expiry_time value for this INSTRUCTION.
     * 
     * @param expiry_time
     */
    public void setExpiry_time(org.openehr.schemas.v1.DV_DATE_TIME expiry_time) {
        this.expiry_time = expiry_time;
    }


    /**
     * Gets the wf_definition value for this INSTRUCTION.
     * 
     * @return wf_definition
     */
    public org.openehr.schemas.v1.DV_PARSABLE getWf_definition() {
        return wf_definition;
    }


    /**
     * Sets the wf_definition value for this INSTRUCTION.
     * 
     * @param wf_definition
     */
    public void setWf_definition(org.openehr.schemas.v1.DV_PARSABLE wf_definition) {
        this.wf_definition = wf_definition;
    }


    /**
     * Gets the activities value for this INSTRUCTION.
     * 
     * @return activities
     */
    public org.openehr.schemas.v1.ACTIVITY[] getActivities() {
        return activities;
    }


    /**
     * Sets the activities value for this INSTRUCTION.
     * 
     * @param activities
     */
    public void setActivities(org.openehr.schemas.v1.ACTIVITY[] activities) {
        this.activities = activities;
    }

    public org.openehr.schemas.v1.ACTIVITY getActivities(int i) {
        return this.activities[i];
    }

    public void setActivities(int i, org.openehr.schemas.v1.ACTIVITY _value) {
        this.activities[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof INSTRUCTION)) return false;
        INSTRUCTION other = (INSTRUCTION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.narrative==null && other.getNarrative()==null) || 
             (this.narrative!=null &&
              this.narrative.equals(other.getNarrative()))) &&
            ((this.expiry_time==null && other.getExpiry_time()==null) || 
             (this.expiry_time!=null &&
              this.expiry_time.equals(other.getExpiry_time()))) &&
            ((this.wf_definition==null && other.getWf_definition()==null) || 
             (this.wf_definition!=null &&
              this.wf_definition.equals(other.getWf_definition()))) &&
            ((this.activities==null && other.getActivities()==null) || 
             (this.activities!=null &&
              java.util.Arrays.equals(this.activities, other.getActivities())));
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
        if (getNarrative() != null) {
            _hashCode += getNarrative().hashCode();
        }
        if (getExpiry_time() != null) {
            _hashCode += getExpiry_time().hashCode();
        }
        if (getWf_definition() != null) {
            _hashCode += getWf_definition().hashCode();
        }
        if (getActivities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivities());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActivities(), i);
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
        new org.apache.axis.description.TypeDesc(INSTRUCTION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "INSTRUCTION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("narrative");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "narrative"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiry_time");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "expiry_time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wf_definition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "wf_definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PARSABLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "activities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ACTIVITY"));
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
