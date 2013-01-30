/**
 * EVENT_CONTEXT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EVENT_CONTEXT  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_DATE_TIME start_time;

    private org.openehr.schemas.v1.DV_DATE_TIME end_time;

    private java.lang.String location;

    private org.openehr.schemas.v1.DV_CODED_TEXT setting;

    private org.openehr.schemas.v1.ITEM_STRUCTURE other_context;

    private org.openehr.schemas.v1.PARTY_IDENTIFIED health_care_facility;

    private org.openehr.schemas.v1.PARTICIPATION[] participations;

    public EVENT_CONTEXT() {
    }

    public EVENT_CONTEXT(
           org.openehr.schemas.v1.DV_DATE_TIME start_time,
           org.openehr.schemas.v1.DV_DATE_TIME end_time,
           java.lang.String location,
           org.openehr.schemas.v1.DV_CODED_TEXT setting,
           org.openehr.schemas.v1.ITEM_STRUCTURE other_context,
           org.openehr.schemas.v1.PARTY_IDENTIFIED health_care_facility,
           org.openehr.schemas.v1.PARTICIPATION[] participations) {
           this.start_time = start_time;
           this.end_time = end_time;
           this.location = location;
           this.setting = setting;
           this.other_context = other_context;
           this.health_care_facility = health_care_facility;
           this.participations = participations;
    }


    /**
     * Gets the start_time value for this EVENT_CONTEXT.
     * 
     * @return start_time
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getStart_time() {
        return start_time;
    }


    /**
     * Sets the start_time value for this EVENT_CONTEXT.
     * 
     * @param start_time
     */
    public void setStart_time(org.openehr.schemas.v1.DV_DATE_TIME start_time) {
        this.start_time = start_time;
    }


    /**
     * Gets the end_time value for this EVENT_CONTEXT.
     * 
     * @return end_time
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getEnd_time() {
        return end_time;
    }


    /**
     * Sets the end_time value for this EVENT_CONTEXT.
     * 
     * @param end_time
     */
    public void setEnd_time(org.openehr.schemas.v1.DV_DATE_TIME end_time) {
        this.end_time = end_time;
    }


    /**
     * Gets the location value for this EVENT_CONTEXT.
     * 
     * @return location
     */
    public java.lang.String getLocation() {
        return location;
    }


    /**
     * Sets the location value for this EVENT_CONTEXT.
     * 
     * @param location
     */
    public void setLocation(java.lang.String location) {
        this.location = location;
    }


    /**
     * Gets the setting value for this EVENT_CONTEXT.
     * 
     * @return setting
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getSetting() {
        return setting;
    }


    /**
     * Sets the setting value for this EVENT_CONTEXT.
     * 
     * @param setting
     */
    public void setSetting(org.openehr.schemas.v1.DV_CODED_TEXT setting) {
        this.setting = setting;
    }


    /**
     * Gets the other_context value for this EVENT_CONTEXT.
     * 
     * @return other_context
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getOther_context() {
        return other_context;
    }


    /**
     * Sets the other_context value for this EVENT_CONTEXT.
     * 
     * @param other_context
     */
    public void setOther_context(org.openehr.schemas.v1.ITEM_STRUCTURE other_context) {
        this.other_context = other_context;
    }


    /**
     * Gets the health_care_facility value for this EVENT_CONTEXT.
     * 
     * @return health_care_facility
     */
    public org.openehr.schemas.v1.PARTY_IDENTIFIED getHealth_care_facility() {
        return health_care_facility;
    }


    /**
     * Sets the health_care_facility value for this EVENT_CONTEXT.
     * 
     * @param health_care_facility
     */
    public void setHealth_care_facility(org.openehr.schemas.v1.PARTY_IDENTIFIED health_care_facility) {
        this.health_care_facility = health_care_facility;
    }


    /**
     * Gets the participations value for this EVENT_CONTEXT.
     * 
     * @return participations
     */
    public org.openehr.schemas.v1.PARTICIPATION[] getParticipations() {
        return participations;
    }


    /**
     * Sets the participations value for this EVENT_CONTEXT.
     * 
     * @param participations
     */
    public void setParticipations(org.openehr.schemas.v1.PARTICIPATION[] participations) {
        this.participations = participations;
    }

    public org.openehr.schemas.v1.PARTICIPATION getParticipations(int i) {
        return this.participations[i];
    }

    public void setParticipations(int i, org.openehr.schemas.v1.PARTICIPATION _value) {
        this.participations[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EVENT_CONTEXT)) return false;
        EVENT_CONTEXT other = (EVENT_CONTEXT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.start_time==null && other.getStart_time()==null) || 
             (this.start_time!=null &&
              this.start_time.equals(other.getStart_time()))) &&
            ((this.end_time==null && other.getEnd_time()==null) || 
             (this.end_time!=null &&
              this.end_time.equals(other.getEnd_time()))) &&
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.setting==null && other.getSetting()==null) || 
             (this.setting!=null &&
              this.setting.equals(other.getSetting()))) &&
            ((this.other_context==null && other.getOther_context()==null) || 
             (this.other_context!=null &&
              this.other_context.equals(other.getOther_context()))) &&
            ((this.health_care_facility==null && other.getHealth_care_facility()==null) || 
             (this.health_care_facility!=null &&
              this.health_care_facility.equals(other.getHealth_care_facility()))) &&
            ((this.participations==null && other.getParticipations()==null) || 
             (this.participations!=null &&
              java.util.Arrays.equals(this.participations, other.getParticipations())));
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
        if (getStart_time() != null) {
            _hashCode += getStart_time().hashCode();
        }
        if (getEnd_time() != null) {
            _hashCode += getEnd_time().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getSetting() != null) {
            _hashCode += getSetting().hashCode();
        }
        if (getOther_context() != null) {
            _hashCode += getOther_context().hashCode();
        }
        if (getHealth_care_facility() != null) {
            _hashCode += getHealth_care_facility().hashCode();
        }
        if (getParticipations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParticipations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParticipations(), i);
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
        new org.apache.axis.description.TypeDesc(EVENT_CONTEXT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EVENT_CONTEXT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("start_time");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "start_time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("end_time");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "end_time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "location"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("setting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "setting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other_context");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "other_context"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("health_care_facility");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "health_care_facility"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_IDENTIFIED"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "participations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTICIPATION"));
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
