/**
 * FEEDER_AUDIT_DETAILS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class FEEDER_AUDIT_DETAILS  implements java.io.Serializable {
    private java.lang.String system_id;

    private org.openehr.schemas.v1.PARTY_IDENTIFIED location;

    private org.openehr.schemas.v1.PARTY_IDENTIFIED provider;

    private org.openehr.schemas.v1.PARTY_PROXY subject;

    private org.openehr.schemas.v1.DV_DATE_TIME time;

    private java.lang.String version_id;

    public FEEDER_AUDIT_DETAILS() {
    }

    public FEEDER_AUDIT_DETAILS(
           java.lang.String system_id,
           org.openehr.schemas.v1.PARTY_IDENTIFIED location,
           org.openehr.schemas.v1.PARTY_IDENTIFIED provider,
           org.openehr.schemas.v1.PARTY_PROXY subject,
           org.openehr.schemas.v1.DV_DATE_TIME time,
           java.lang.String version_id) {
           this.system_id = system_id;
           this.location = location;
           this.provider = provider;
           this.subject = subject;
           this.time = time;
           this.version_id = version_id;
    }


    /**
     * Gets the system_id value for this FEEDER_AUDIT_DETAILS.
     * 
     * @return system_id
     */
    public java.lang.String getSystem_id() {
        return system_id;
    }


    /**
     * Sets the system_id value for this FEEDER_AUDIT_DETAILS.
     * 
     * @param system_id
     */
    public void setSystem_id(java.lang.String system_id) {
        this.system_id = system_id;
    }


    /**
     * Gets the location value for this FEEDER_AUDIT_DETAILS.
     * 
     * @return location
     */
    public org.openehr.schemas.v1.PARTY_IDENTIFIED getLocation() {
        return location;
    }


    /**
     * Sets the location value for this FEEDER_AUDIT_DETAILS.
     * 
     * @param location
     */
    public void setLocation(org.openehr.schemas.v1.PARTY_IDENTIFIED location) {
        this.location = location;
    }


    /**
     * Gets the provider value for this FEEDER_AUDIT_DETAILS.
     * 
     * @return provider
     */
    public org.openehr.schemas.v1.PARTY_IDENTIFIED getProvider() {
        return provider;
    }


    /**
     * Sets the provider value for this FEEDER_AUDIT_DETAILS.
     * 
     * @param provider
     */
    public void setProvider(org.openehr.schemas.v1.PARTY_IDENTIFIED provider) {
        this.provider = provider;
    }


    /**
     * Gets the subject value for this FEEDER_AUDIT_DETAILS.
     * 
     * @return subject
     */
    public org.openehr.schemas.v1.PARTY_PROXY getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this FEEDER_AUDIT_DETAILS.
     * 
     * @param subject
     */
    public void setSubject(org.openehr.schemas.v1.PARTY_PROXY subject) {
        this.subject = subject;
    }


    /**
     * Gets the time value for this FEEDER_AUDIT_DETAILS.
     * 
     * @return time
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getTime() {
        return time;
    }


    /**
     * Sets the time value for this FEEDER_AUDIT_DETAILS.
     * 
     * @param time
     */
    public void setTime(org.openehr.schemas.v1.DV_DATE_TIME time) {
        this.time = time;
    }


    /**
     * Gets the version_id value for this FEEDER_AUDIT_DETAILS.
     * 
     * @return version_id
     */
    public java.lang.String getVersion_id() {
        return version_id;
    }


    /**
     * Sets the version_id value for this FEEDER_AUDIT_DETAILS.
     * 
     * @param version_id
     */
    public void setVersion_id(java.lang.String version_id) {
        this.version_id = version_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FEEDER_AUDIT_DETAILS)) return false;
        FEEDER_AUDIT_DETAILS other = (FEEDER_AUDIT_DETAILS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.system_id==null && other.getSystem_id()==null) || 
             (this.system_id!=null &&
              this.system_id.equals(other.getSystem_id()))) &&
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.provider==null && other.getProvider()==null) || 
             (this.provider!=null &&
              this.provider.equals(other.getProvider()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.time==null && other.getTime()==null) || 
             (this.time!=null &&
              this.time.equals(other.getTime()))) &&
            ((this.version_id==null && other.getVersion_id()==null) || 
             (this.version_id!=null &&
              this.version_id.equals(other.getVersion_id())));
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
        if (getSystem_id() != null) {
            _hashCode += getSystem_id().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getProvider() != null) {
            _hashCode += getProvider().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getTime() != null) {
            _hashCode += getTime().hashCode();
        }
        if (getVersion_id() != null) {
            _hashCode += getVersion_id().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FEEDER_AUDIT_DETAILS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "FEEDER_AUDIT_DETAILS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("system_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "system_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "location"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_IDENTIFIED"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provider");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "provider"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_IDENTIFIED"));
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
        elemField.setFieldName("time");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "version_id"));
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
