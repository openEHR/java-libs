/**
 * VERSION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class VERSION  implements java.io.Serializable {
    private org.openehr.schemas.v1.OBJECT_REF contribution;

    private org.openehr.schemas.v1.AUDIT_DETAILS commit_audit;

    private java.lang.String signature;

    public VERSION() {
    }

    public VERSION(
           org.openehr.schemas.v1.OBJECT_REF contribution,
           org.openehr.schemas.v1.AUDIT_DETAILS commit_audit,
           java.lang.String signature) {
           this.contribution = contribution;
           this.commit_audit = commit_audit;
           this.signature = signature;
    }


    /**
     * Gets the contribution value for this VERSION.
     * 
     * @return contribution
     */
    public org.openehr.schemas.v1.OBJECT_REF getContribution() {
        return contribution;
    }


    /**
     * Sets the contribution value for this VERSION.
     * 
     * @param contribution
     */
    public void setContribution(org.openehr.schemas.v1.OBJECT_REF contribution) {
        this.contribution = contribution;
    }


    /**
     * Gets the commit_audit value for this VERSION.
     * 
     * @return commit_audit
     */
    public org.openehr.schemas.v1.AUDIT_DETAILS getCommit_audit() {
        return commit_audit;
    }


    /**
     * Sets the commit_audit value for this VERSION.
     * 
     * @param commit_audit
     */
    public void setCommit_audit(org.openehr.schemas.v1.AUDIT_DETAILS commit_audit) {
        this.commit_audit = commit_audit;
    }


    /**
     * Gets the signature value for this VERSION.
     * 
     * @return signature
     */
    public java.lang.String getSignature() {
        return signature;
    }


    /**
     * Sets the signature value for this VERSION.
     * 
     * @param signature
     */
    public void setSignature(java.lang.String signature) {
        this.signature = signature;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VERSION)) return false;
        VERSION other = (VERSION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contribution==null && other.getContribution()==null) || 
             (this.contribution!=null &&
              this.contribution.equals(other.getContribution()))) &&
            ((this.commit_audit==null && other.getCommit_audit()==null) || 
             (this.commit_audit!=null &&
              this.commit_audit.equals(other.getCommit_audit()))) &&
            ((this.signature==null && other.getSignature()==null) || 
             (this.signature!=null &&
              this.signature.equals(other.getSignature())));
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
        if (getContribution() != null) {
            _hashCode += getContribution().hashCode();
        }
        if (getCommit_audit() != null) {
            _hashCode += getCommit_audit().hashCode();
        }
        if (getSignature() != null) {
            _hashCode += getSignature().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VERSION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "VERSION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contribution");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "contribution"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commit_audit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "commit_audit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUDIT_DETAILS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signature");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "signature"));
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
