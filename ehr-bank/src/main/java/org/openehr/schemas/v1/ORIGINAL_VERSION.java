/**
 * ORIGINAL_VERSION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ORIGINAL_VERSION  extends org.openehr.schemas.v1.VERSION  implements java.io.Serializable {
    private org.openehr.schemas.v1.OBJECT_VERSION_ID uid;

    private java.lang.Object data;

    private org.openehr.schemas.v1.OBJECT_VERSION_ID preceding_version_uid;

    private org.openehr.schemas.v1.OBJECT_VERSION_ID[] other_input_version_uids;

    private org.openehr.schemas.v1.ATTESTATION[] attestations;

    private org.openehr.schemas.v1.DV_CODED_TEXT lifecycle_state;

    public ORIGINAL_VERSION() {
    }

    public ORIGINAL_VERSION(
           org.openehr.schemas.v1.OBJECT_REF contribution,
           org.openehr.schemas.v1.AUDIT_DETAILS commit_audit,
           java.lang.String signature,
           org.openehr.schemas.v1.OBJECT_VERSION_ID uid,
           java.lang.Object data,
           org.openehr.schemas.v1.OBJECT_VERSION_ID preceding_version_uid,
           org.openehr.schemas.v1.OBJECT_VERSION_ID[] other_input_version_uids,
           org.openehr.schemas.v1.ATTESTATION[] attestations,
           org.openehr.schemas.v1.DV_CODED_TEXT lifecycle_state) {
        super(
            contribution,
            commit_audit,
            signature);
        this.uid = uid;
        this.data = data;
        this.preceding_version_uid = preceding_version_uid;
        this.other_input_version_uids = other_input_version_uids;
        this.attestations = attestations;
        this.lifecycle_state = lifecycle_state;
    }


    /**
     * Gets the uid value for this ORIGINAL_VERSION.
     * 
     * @return uid
     */
    public org.openehr.schemas.v1.OBJECT_VERSION_ID getUid() {
        return uid;
    }


    /**
     * Sets the uid value for this ORIGINAL_VERSION.
     * 
     * @param uid
     */
    public void setUid(org.openehr.schemas.v1.OBJECT_VERSION_ID uid) {
        this.uid = uid;
    }


    /**
     * Gets the data value for this ORIGINAL_VERSION.
     * 
     * @return data
     */
    public java.lang.Object getData() {
        return data;
    }


    /**
     * Sets the data value for this ORIGINAL_VERSION.
     * 
     * @param data
     */
    public void setData(java.lang.Object data) {
        this.data = data;
    }


    /**
     * Gets the preceding_version_uid value for this ORIGINAL_VERSION.
     * 
     * @return preceding_version_uid
     */
    public org.openehr.schemas.v1.OBJECT_VERSION_ID getPreceding_version_uid() {
        return preceding_version_uid;
    }


    /**
     * Sets the preceding_version_uid value for this ORIGINAL_VERSION.
     * 
     * @param preceding_version_uid
     */
    public void setPreceding_version_uid(org.openehr.schemas.v1.OBJECT_VERSION_ID preceding_version_uid) {
        this.preceding_version_uid = preceding_version_uid;
    }


    /**
     * Gets the other_input_version_uids value for this ORIGINAL_VERSION.
     * 
     * @return other_input_version_uids
     */
    public org.openehr.schemas.v1.OBJECT_VERSION_ID[] getOther_input_version_uids() {
        return other_input_version_uids;
    }


    /**
     * Sets the other_input_version_uids value for this ORIGINAL_VERSION.
     * 
     * @param other_input_version_uids
     */
    public void setOther_input_version_uids(org.openehr.schemas.v1.OBJECT_VERSION_ID[] other_input_version_uids) {
        this.other_input_version_uids = other_input_version_uids;
    }

    public org.openehr.schemas.v1.OBJECT_VERSION_ID getOther_input_version_uids(int i) {
        return this.other_input_version_uids[i];
    }

    public void setOther_input_version_uids(int i, org.openehr.schemas.v1.OBJECT_VERSION_ID _value) {
        this.other_input_version_uids[i] = _value;
    }


    /**
     * Gets the attestations value for this ORIGINAL_VERSION.
     * 
     * @return attestations
     */
    public org.openehr.schemas.v1.ATTESTATION[] getAttestations() {
        return attestations;
    }


    /**
     * Sets the attestations value for this ORIGINAL_VERSION.
     * 
     * @param attestations
     */
    public void setAttestations(org.openehr.schemas.v1.ATTESTATION[] attestations) {
        this.attestations = attestations;
    }

    public org.openehr.schemas.v1.ATTESTATION getAttestations(int i) {
        return this.attestations[i];
    }

    public void setAttestations(int i, org.openehr.schemas.v1.ATTESTATION _value) {
        this.attestations[i] = _value;
    }


    /**
     * Gets the lifecycle_state value for this ORIGINAL_VERSION.
     * 
     * @return lifecycle_state
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getLifecycle_state() {
        return lifecycle_state;
    }


    /**
     * Sets the lifecycle_state value for this ORIGINAL_VERSION.
     * 
     * @param lifecycle_state
     */
    public void setLifecycle_state(org.openehr.schemas.v1.DV_CODED_TEXT lifecycle_state) {
        this.lifecycle_state = lifecycle_state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ORIGINAL_VERSION)) return false;
        ORIGINAL_VERSION other = (ORIGINAL_VERSION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.uid==null && other.getUid()==null) || 
             (this.uid!=null &&
              this.uid.equals(other.getUid()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData()))) &&
            ((this.preceding_version_uid==null && other.getPreceding_version_uid()==null) || 
             (this.preceding_version_uid!=null &&
              this.preceding_version_uid.equals(other.getPreceding_version_uid()))) &&
            ((this.other_input_version_uids==null && other.getOther_input_version_uids()==null) || 
             (this.other_input_version_uids!=null &&
              java.util.Arrays.equals(this.other_input_version_uids, other.getOther_input_version_uids()))) &&
            ((this.attestations==null && other.getAttestations()==null) || 
             (this.attestations!=null &&
              java.util.Arrays.equals(this.attestations, other.getAttestations()))) &&
            ((this.lifecycle_state==null && other.getLifecycle_state()==null) || 
             (this.lifecycle_state!=null &&
              this.lifecycle_state.equals(other.getLifecycle_state())));
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
        if (getUid() != null) {
            _hashCode += getUid().hashCode();
        }
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        if (getPreceding_version_uid() != null) {
            _hashCode += getPreceding_version_uid().hashCode();
        }
        if (getOther_input_version_uids() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOther_input_version_uids());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOther_input_version_uids(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAttestations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttestations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttestations(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLifecycle_state() != null) {
            _hashCode += getLifecycle_state().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ORIGINAL_VERSION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ORIGINAL_VERSION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "uid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_VERSION_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preceding_version_uid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "preceding_version_uid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_VERSION_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other_input_version_uids");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "other_input_version_uids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_VERSION_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attestations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "attestations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ATTESTATION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lifecycle_state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "lifecycle_state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
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
