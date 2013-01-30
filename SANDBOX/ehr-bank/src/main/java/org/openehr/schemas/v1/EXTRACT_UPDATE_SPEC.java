/**
 * EXTRACT_UPDATE_SPEC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_UPDATE_SPEC  implements java.io.Serializable {
    private boolean persist_in_server;

    private org.openehr.schemas.v1.DV_INTERVAL repeat_period;

    private java.lang.String[] trigger_events;

    private boolean send_changes_only;

    public EXTRACT_UPDATE_SPEC() {
    }

    public EXTRACT_UPDATE_SPEC(
           boolean persist_in_server,
           org.openehr.schemas.v1.DV_INTERVAL repeat_period,
           java.lang.String[] trigger_events,
           boolean send_changes_only) {
           this.persist_in_server = persist_in_server;
           this.repeat_period = repeat_period;
           this.trigger_events = trigger_events;
           this.send_changes_only = send_changes_only;
    }


    /**
     * Gets the persist_in_server value for this EXTRACT_UPDATE_SPEC.
     * 
     * @return persist_in_server
     */
    public boolean isPersist_in_server() {
        return persist_in_server;
    }


    /**
     * Sets the persist_in_server value for this EXTRACT_UPDATE_SPEC.
     * 
     * @param persist_in_server
     */
    public void setPersist_in_server(boolean persist_in_server) {
        this.persist_in_server = persist_in_server;
    }


    /**
     * Gets the repeat_period value for this EXTRACT_UPDATE_SPEC.
     * 
     * @return repeat_period
     */
    public org.openehr.schemas.v1.DV_INTERVAL getRepeat_period() {
        return repeat_period;
    }


    /**
     * Sets the repeat_period value for this EXTRACT_UPDATE_SPEC.
     * 
     * @param repeat_period
     */
    public void setRepeat_period(org.openehr.schemas.v1.DV_INTERVAL repeat_period) {
        this.repeat_period = repeat_period;
    }


    /**
     * Gets the trigger_events value for this EXTRACT_UPDATE_SPEC.
     * 
     * @return trigger_events
     */
    public java.lang.String[] getTrigger_events() {
        return trigger_events;
    }


    /**
     * Sets the trigger_events value for this EXTRACT_UPDATE_SPEC.
     * 
     * @param trigger_events
     */
    public void setTrigger_events(java.lang.String[] trigger_events) {
        this.trigger_events = trigger_events;
    }

    public java.lang.String getTrigger_events(int i) {
        return this.trigger_events[i];
    }

    public void setTrigger_events(int i, java.lang.String _value) {
        this.trigger_events[i] = _value;
    }


    /**
     * Gets the send_changes_only value for this EXTRACT_UPDATE_SPEC.
     * 
     * @return send_changes_only
     */
    public boolean isSend_changes_only() {
        return send_changes_only;
    }


    /**
     * Sets the send_changes_only value for this EXTRACT_UPDATE_SPEC.
     * 
     * @param send_changes_only
     */
    public void setSend_changes_only(boolean send_changes_only) {
        this.send_changes_only = send_changes_only;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_UPDATE_SPEC)) return false;
        EXTRACT_UPDATE_SPEC other = (EXTRACT_UPDATE_SPEC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.persist_in_server == other.isPersist_in_server() &&
            ((this.repeat_period==null && other.getRepeat_period()==null) || 
             (this.repeat_period!=null &&
              this.repeat_period.equals(other.getRepeat_period()))) &&
            ((this.trigger_events==null && other.getTrigger_events()==null) || 
             (this.trigger_events!=null &&
              java.util.Arrays.equals(this.trigger_events, other.getTrigger_events()))) &&
            this.send_changes_only == other.isSend_changes_only();
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
        _hashCode += (isPersist_in_server() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getRepeat_period() != null) {
            _hashCode += getRepeat_period().hashCode();
        }
        if (getTrigger_events() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTrigger_events());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTrigger_events(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isSend_changes_only() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT_UPDATE_SPEC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_UPDATE_SPEC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persist_in_server");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "persist_in_server"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repeat_period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "repeat_period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_INTERVAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trigger_events");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "trigger_events"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("send_changes_only");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "send_changes_only"));
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
