/**
 * ATTESTATION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ATTESTATION  extends org.openehr.schemas.v1.AUDIT_DETAILS  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_MULTIMEDIA attested_view;

    private java.lang.String proof;

    private org.openehr.schemas.v1.DV_EHR_URI[] items;

    private org.openehr.schemas.v1.DV_TEXT reason;

    private boolean is_pending;

    public ATTESTATION() {
    }

    public ATTESTATION(
           java.lang.String system_id,
           org.openehr.schemas.v1.PARTY_PROXY committer,
           org.openehr.schemas.v1.DV_DATE_TIME time_committed,
           org.openehr.schemas.v1.DV_CODED_TEXT change_type,
           org.openehr.schemas.v1.DV_TEXT description,
           org.openehr.schemas.v1.DV_MULTIMEDIA attested_view,
           java.lang.String proof,
           org.openehr.schemas.v1.DV_EHR_URI[] items,
           org.openehr.schemas.v1.DV_TEXT reason,
           boolean is_pending) {
        super(
            system_id,
            committer,
            time_committed,
            change_type,
            description);
        this.attested_view = attested_view;
        this.proof = proof;
        this.items = items;
        this.reason = reason;
        this.is_pending = is_pending;
    }


    /**
     * Gets the attested_view value for this ATTESTATION.
     * 
     * @return attested_view
     */
    public org.openehr.schemas.v1.DV_MULTIMEDIA getAttested_view() {
        return attested_view;
    }


    /**
     * Sets the attested_view value for this ATTESTATION.
     * 
     * @param attested_view
     */
    public void setAttested_view(org.openehr.schemas.v1.DV_MULTIMEDIA attested_view) {
        this.attested_view = attested_view;
    }


    /**
     * Gets the proof value for this ATTESTATION.
     * 
     * @return proof
     */
    public java.lang.String getProof() {
        return proof;
    }


    /**
     * Sets the proof value for this ATTESTATION.
     * 
     * @param proof
     */
    public void setProof(java.lang.String proof) {
        this.proof = proof;
    }


    /**
     * Gets the items value for this ATTESTATION.
     * 
     * @return items
     */
    public org.openehr.schemas.v1.DV_EHR_URI[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this ATTESTATION.
     * 
     * @param items
     */
    public void setItems(org.openehr.schemas.v1.DV_EHR_URI[] items) {
        this.items = items;
    }

    public org.openehr.schemas.v1.DV_EHR_URI getItems(int i) {
        return this.items[i];
    }

    public void setItems(int i, org.openehr.schemas.v1.DV_EHR_URI _value) {
        this.items[i] = _value;
    }


    /**
     * Gets the reason value for this ATTESTATION.
     * 
     * @return reason
     */
    public org.openehr.schemas.v1.DV_TEXT getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this ATTESTATION.
     * 
     * @param reason
     */
    public void setReason(org.openehr.schemas.v1.DV_TEXT reason) {
        this.reason = reason;
    }


    /**
     * Gets the is_pending value for this ATTESTATION.
     * 
     * @return is_pending
     */
    public boolean isIs_pending() {
        return is_pending;
    }


    /**
     * Sets the is_pending value for this ATTESTATION.
     * 
     * @param is_pending
     */
    public void setIs_pending(boolean is_pending) {
        this.is_pending = is_pending;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ATTESTATION)) return false;
        ATTESTATION other = (ATTESTATION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.attested_view==null && other.getAttested_view()==null) || 
             (this.attested_view!=null &&
              this.attested_view.equals(other.getAttested_view()))) &&
            ((this.proof==null && other.getProof()==null) || 
             (this.proof!=null &&
              this.proof.equals(other.getProof()))) &&
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems()))) &&
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason()))) &&
            this.is_pending == other.isIs_pending();
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
        if (getAttested_view() != null) {
            _hashCode += getAttested_view().hashCode();
        }
        if (getProof() != null) {
            _hashCode += getProof().hashCode();
        }
        if (getItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        _hashCode += (isIs_pending() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ATTESTATION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ATTESTATION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attested_view");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "attested_view"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_MULTIMEDIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("proof");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "proof"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_EHR_URI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_pending");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_pending"));
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
