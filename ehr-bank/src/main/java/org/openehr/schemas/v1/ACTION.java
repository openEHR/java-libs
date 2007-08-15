/**
 * ACTION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ACTION  extends org.openehr.schemas.v1.CARE_ENTRY  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_DATE_TIME time;

    private org.openehr.schemas.v1.ITEM_STRUCTURE description;

    private org.openehr.schemas.v1.ISM_TRANSITION ism_transition;

    private org.openehr.schemas.v1.INSTRUCTION_DETAILS instruction_details;

    public ACTION() {
    }

    public ACTION(
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
           org.openehr.schemas.v1.DV_DATE_TIME time,
           org.openehr.schemas.v1.ITEM_STRUCTURE description,
           org.openehr.schemas.v1.ISM_TRANSITION ism_transition,
           org.openehr.schemas.v1.INSTRUCTION_DETAILS instruction_details) {
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
        this.time = time;
        this.description = description;
        this.ism_transition = ism_transition;
        this.instruction_details = instruction_details;
    }


    /**
     * Gets the time value for this ACTION.
     * 
     * @return time
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getTime() {
        return time;
    }


    /**
     * Sets the time value for this ACTION.
     * 
     * @param time
     */
    public void setTime(org.openehr.schemas.v1.DV_DATE_TIME time) {
        this.time = time;
    }


    /**
     * Gets the description value for this ACTION.
     * 
     * @return description
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ACTION.
     * 
     * @param description
     */
    public void setDescription(org.openehr.schemas.v1.ITEM_STRUCTURE description) {
        this.description = description;
    }


    /**
     * Gets the ism_transition value for this ACTION.
     * 
     * @return ism_transition
     */
    public org.openehr.schemas.v1.ISM_TRANSITION getIsm_transition() {
        return ism_transition;
    }


    /**
     * Sets the ism_transition value for this ACTION.
     * 
     * @param ism_transition
     */
    public void setIsm_transition(org.openehr.schemas.v1.ISM_TRANSITION ism_transition) {
        this.ism_transition = ism_transition;
    }


    /**
     * Gets the instruction_details value for this ACTION.
     * 
     * @return instruction_details
     */
    public org.openehr.schemas.v1.INSTRUCTION_DETAILS getInstruction_details() {
        return instruction_details;
    }


    /**
     * Sets the instruction_details value for this ACTION.
     * 
     * @param instruction_details
     */
    public void setInstruction_details(org.openehr.schemas.v1.INSTRUCTION_DETAILS instruction_details) {
        this.instruction_details = instruction_details;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ACTION)) return false;
        ACTION other = (ACTION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.time==null && other.getTime()==null) || 
             (this.time!=null &&
              this.time.equals(other.getTime()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.ism_transition==null && other.getIsm_transition()==null) || 
             (this.ism_transition!=null &&
              this.ism_transition.equals(other.getIsm_transition()))) &&
            ((this.instruction_details==null && other.getInstruction_details()==null) || 
             (this.instruction_details!=null &&
              this.instruction_details.equals(other.getInstruction_details())));
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
        if (getTime() != null) {
            _hashCode += getTime().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getIsm_transition() != null) {
            _hashCode += getIsm_transition().hashCode();
        }
        if (getInstruction_details() != null) {
            _hashCode += getInstruction_details().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ACTION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ACTION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ism_transition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ism_transition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ISM_TRANSITION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instruction_details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "instruction_details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "INSTRUCTION_DETAILS"));
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
