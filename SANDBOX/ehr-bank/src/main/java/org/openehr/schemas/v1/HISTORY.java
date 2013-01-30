/**
 * HISTORY.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class HISTORY  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_DATE_TIME origin;

    private org.openehr.schemas.v1.DV_DURATION period;

    private org.openehr.schemas.v1.DV_DURATION duration;

    private org.openehr.schemas.v1.EVENT[] events;

    private org.openehr.schemas.v1.ITEM_STRUCTURE summary;

    public HISTORY() {
    }

    public HISTORY(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.DV_DATE_TIME origin,
           org.openehr.schemas.v1.DV_DURATION period,
           org.openehr.schemas.v1.DV_DURATION duration,
           org.openehr.schemas.v1.EVENT[] events,
           org.openehr.schemas.v1.ITEM_STRUCTURE summary) {
        super(
            name,
            uid,
            links,
            archetype_details,
            feeder_audit, 
            archetype_node_id);
        this.origin = origin;
        this.period = period;
        this.duration = duration;
        this.events = events;
        this.summary = summary;
    }


    /**
     * Gets the origin value for this HISTORY.
     * 
     * @return origin
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getOrigin() {
        return origin;
    }


    /**
     * Sets the origin value for this HISTORY.
     * 
     * @param origin
     */
    public void setOrigin(org.openehr.schemas.v1.DV_DATE_TIME origin) {
        this.origin = origin;
    }


    /**
     * Gets the period value for this HISTORY.
     * 
     * @return period
     */
    public org.openehr.schemas.v1.DV_DURATION getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this HISTORY.
     * 
     * @param period
     */
    public void setPeriod(org.openehr.schemas.v1.DV_DURATION period) {
        this.period = period;
    }


    /**
     * Gets the duration value for this HISTORY.
     * 
     * @return duration
     */
    public org.openehr.schemas.v1.DV_DURATION getDuration() {
        return duration;
    }


    /**
     * Sets the duration value for this HISTORY.
     * 
     * @param duration
     */
    public void setDuration(org.openehr.schemas.v1.DV_DURATION duration) {
        this.duration = duration;
    }


    /**
     * Gets the events value for this HISTORY.
     * 
     * @return events
     */
    public org.openehr.schemas.v1.EVENT[] getEvents() {
        return events;
    }


    /**
     * Sets the events value for this HISTORY.
     * 
     * @param events
     */
    public void setEvents(org.openehr.schemas.v1.EVENT[] events) {
        this.events = events;
    }

    public org.openehr.schemas.v1.EVENT getEvents(int i) {
        return this.events[i];
    }

    public void setEvents(int i, org.openehr.schemas.v1.EVENT _value) {
        this.events[i] = _value;
    }


    /**
     * Gets the summary value for this HISTORY.
     * 
     * @return summary
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this HISTORY.
     * 
     * @param summary
     */
    public void setSummary(org.openehr.schemas.v1.ITEM_STRUCTURE summary) {
        this.summary = summary;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HISTORY)) return false;
        HISTORY other = (HISTORY) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.origin==null && other.getOrigin()==null) || 
             (this.origin!=null &&
              this.origin.equals(other.getOrigin()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.duration==null && other.getDuration()==null) || 
             (this.duration!=null &&
              this.duration.equals(other.getDuration()))) &&
            ((this.events==null && other.getEvents()==null) || 
             (this.events!=null &&
              java.util.Arrays.equals(this.events, other.getEvents()))) &&
            ((this.summary==null && other.getSummary()==null) || 
             (this.summary!=null &&
              this.summary.equals(other.getSummary())));
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
        if (getOrigin() != null) {
            _hashCode += getOrigin().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getDuration() != null) {
            _hashCode += getDuration().hashCode();
        }
        if (getEvents() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEvents());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEvents(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSummary() != null) {
            _hashCode += getSummary().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HISTORY.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HISTORY"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "origin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DURATION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duration");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "duration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DURATION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("events");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "events"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EVENT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "summary"));
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
