/**
 * AUDIT_DETAILS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class AUDIT_DETAILS  implements java.io.Serializable {
    private java.lang.String system_id;

    private org.openehr.schemas.v1.PARTY_PROXY committer;

    private org.openehr.schemas.v1.DV_DATE_TIME time_committed;

    private org.openehr.schemas.v1.DV_CODED_TEXT change_type;

    private org.openehr.schemas.v1.DV_TEXT description;

    public AUDIT_DETAILS() {
    }

    public AUDIT_DETAILS(
           java.lang.String system_id,
           org.openehr.schemas.v1.PARTY_PROXY committer,
           org.openehr.schemas.v1.DV_DATE_TIME time_committed,
           org.openehr.schemas.v1.DV_CODED_TEXT change_type,
           org.openehr.schemas.v1.DV_TEXT description) {
           this.system_id = system_id;
           this.committer = committer;
           this.time_committed = time_committed;
           this.change_type = change_type;
           this.description = description;
    }


    /**
     * Gets the system_id value for this AUDIT_DETAILS.
     * 
     * @return system_id
     */
    public java.lang.String getSystem_id() {
        return system_id;
    }


    /**
     * Sets the system_id value for this AUDIT_DETAILS.
     * 
     * @param system_id
     */
    public void setSystem_id(java.lang.String system_id) {
        this.system_id = system_id;
    }


    /**
     * Gets the committer value for this AUDIT_DETAILS.
     * 
     * @return committer
     */
    public org.openehr.schemas.v1.PARTY_PROXY getCommitter() {
        return committer;
    }


    /**
     * Sets the committer value for this AUDIT_DETAILS.
     * 
     * @param committer
     */
    public void setCommitter(org.openehr.schemas.v1.PARTY_PROXY committer) {
        this.committer = committer;
    }


    /**
     * Gets the time_committed value for this AUDIT_DETAILS.
     * 
     * @return time_committed
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getTime_committed() {
        return time_committed;
    }


    /**
     * Sets the time_committed value for this AUDIT_DETAILS.
     * 
     * @param time_committed
     */
    public void setTime_committed(org.openehr.schemas.v1.DV_DATE_TIME time_committed) {
        this.time_committed = time_committed;
    }


    /**
     * Gets the change_type value for this AUDIT_DETAILS.
     * 
     * @return change_type
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getChange_type() {
        return change_type;
    }


    /**
     * Sets the change_type value for this AUDIT_DETAILS.
     * 
     * @param change_type
     */
    public void setChange_type(org.openehr.schemas.v1.DV_CODED_TEXT change_type) {
        this.change_type = change_type;
    }


    /**
     * Gets the description value for this AUDIT_DETAILS.
     * 
     * @return description
     */
    public org.openehr.schemas.v1.DV_TEXT getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AUDIT_DETAILS.
     * 
     * @param description
     */
    public void setDescription(org.openehr.schemas.v1.DV_TEXT description) {
        this.description = description;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AUDIT_DETAILS)) return false;
        AUDIT_DETAILS other = (AUDIT_DETAILS) obj;
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
            ((this.committer==null && other.getCommitter()==null) || 
             (this.committer!=null &&
              this.committer.equals(other.getCommitter()))) &&
            ((this.time_committed==null && other.getTime_committed()==null) || 
             (this.time_committed!=null &&
              this.time_committed.equals(other.getTime_committed()))) &&
            ((this.change_type==null && other.getChange_type()==null) || 
             (this.change_type!=null &&
              this.change_type.equals(other.getChange_type()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription())));
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
        if (getCommitter() != null) {
            _hashCode += getCommitter().hashCode();
        }
        if (getTime_committed() != null) {
            _hashCode += getTime_committed().hashCode();
        }
        if (getChange_type() != null) {
            _hashCode += getChange_type().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AUDIT_DETAILS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUDIT_DETAILS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("system_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "system_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("committer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "committer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_PROXY"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_committed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "time_committed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("change_type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "change_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
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
