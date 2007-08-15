/**
 * INSTRUCTION_DETAILS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class INSTRUCTION_DETAILS  implements java.io.Serializable {
    private org.openehr.schemas.v1.LOCATABLE_REF instruction_id;

    private java.lang.String activity_id;

    private org.openehr.schemas.v1.ITEM_STRUCTURE wf_details;

    public INSTRUCTION_DETAILS() {
    }

    public INSTRUCTION_DETAILS(
           org.openehr.schemas.v1.LOCATABLE_REF instruction_id,
           java.lang.String activity_id,
           org.openehr.schemas.v1.ITEM_STRUCTURE wf_details) {
           this.instruction_id = instruction_id;
           this.activity_id = activity_id;
           this.wf_details = wf_details;
    }


    /**
     * Gets the instruction_id value for this INSTRUCTION_DETAILS.
     * 
     * @return instruction_id
     */
    public org.openehr.schemas.v1.LOCATABLE_REF getInstruction_id() {
        return instruction_id;
    }


    /**
     * Sets the instruction_id value for this INSTRUCTION_DETAILS.
     * 
     * @param instruction_id
     */
    public void setInstruction_id(org.openehr.schemas.v1.LOCATABLE_REF instruction_id) {
        this.instruction_id = instruction_id;
    }


    /**
     * Gets the activity_id value for this INSTRUCTION_DETAILS.
     * 
     * @return activity_id
     */
    public java.lang.String getActivity_id() {
        return activity_id;
    }


    /**
     * Sets the activity_id value for this INSTRUCTION_DETAILS.
     * 
     * @param activity_id
     */
    public void setActivity_id(java.lang.String activity_id) {
        this.activity_id = activity_id;
    }


    /**
     * Gets the wf_details value for this INSTRUCTION_DETAILS.
     * 
     * @return wf_details
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getWf_details() {
        return wf_details;
    }


    /**
     * Sets the wf_details value for this INSTRUCTION_DETAILS.
     * 
     * @param wf_details
     */
    public void setWf_details(org.openehr.schemas.v1.ITEM_STRUCTURE wf_details) {
        this.wf_details = wf_details;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof INSTRUCTION_DETAILS)) return false;
        INSTRUCTION_DETAILS other = (INSTRUCTION_DETAILS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.instruction_id==null && other.getInstruction_id()==null) || 
             (this.instruction_id!=null &&
              this.instruction_id.equals(other.getInstruction_id()))) &&
            ((this.activity_id==null && other.getActivity_id()==null) || 
             (this.activity_id!=null &&
              this.activity_id.equals(other.getActivity_id()))) &&
            ((this.wf_details==null && other.getWf_details()==null) || 
             (this.wf_details!=null &&
              this.wf_details.equals(other.getWf_details())));
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
        if (getInstruction_id() != null) {
            _hashCode += getInstruction_id().hashCode();
        }
        if (getActivity_id() != null) {
            _hashCode += getActivity_id().hashCode();
        }
        if (getWf_details() != null) {
            _hashCode += getWf_details().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(INSTRUCTION_DETAILS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "INSTRUCTION_DETAILS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instruction_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "instruction_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "LOCATABLE_REF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activity_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "activity_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wf_details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "wf_details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
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
