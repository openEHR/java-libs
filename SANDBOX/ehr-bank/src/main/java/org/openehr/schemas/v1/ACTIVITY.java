/**
 * ACTIVITY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ACTIVITY  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.ITEM_STRUCTURE description;

    private org.openehr.schemas.v1.DV_PARSABLE timing;

    private java.lang.String action_archetype_id;

    public ACTIVITY() {
    }

    public ACTIVITY(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.ITEM_STRUCTURE description,
           org.openehr.schemas.v1.DV_PARSABLE timing,
           java.lang.String action_archetype_id) {
        super(
            name,
            uid,
            links,
            archetype_details,
            feeder_audit, 
            archetype_node_id);
        this.description = description;
        this.timing = timing;
        this.action_archetype_id = action_archetype_id;
    }


    /**
     * Gets the description value for this ACTIVITY.
     * 
     * @return description
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ACTIVITY.
     * 
     * @param description
     */
    public void setDescription(org.openehr.schemas.v1.ITEM_STRUCTURE description) {
        this.description = description;
    }


    /**
     * Gets the timing value for this ACTIVITY.
     * 
     * @return timing
     */
    public org.openehr.schemas.v1.DV_PARSABLE getTiming() {
        return timing;
    }


    /**
     * Sets the timing value for this ACTIVITY.
     * 
     * @param timing
     */
    public void setTiming(org.openehr.schemas.v1.DV_PARSABLE timing) {
        this.timing = timing;
    }


    /**
     * Gets the action_archetype_id value for this ACTIVITY.
     * 
     * @return action_archetype_id
     */
    public java.lang.String getAction_archetype_id() {
        return action_archetype_id;
    }


    /**
     * Sets the action_archetype_id value for this ACTIVITY.
     * 
     * @param action_archetype_id
     */
    public void setAction_archetype_id(java.lang.String action_archetype_id) {
        this.action_archetype_id = action_archetype_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ACTIVITY)) return false;
        ACTIVITY other = (ACTIVITY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.timing==null && other.getTiming()==null) || 
             (this.timing!=null &&
              this.timing.equals(other.getTiming()))) &&
            ((this.action_archetype_id==null && other.getAction_archetype_id()==null) || 
             (this.action_archetype_id!=null &&
              this.action_archetype_id.equals(other.getAction_archetype_id())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getTiming() != null) {
            _hashCode += getTiming().hashCode();
        }
        if (getAction_archetype_id() != null) {
            _hashCode += getAction_archetype_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ACTIVITY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ACTIVITY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "timing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_PARSABLE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("action_archetype_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "action_archetype_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
