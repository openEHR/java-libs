/**
 * FEEDER_AUDIT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class FEEDER_AUDIT  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_IDENTIFIER[] originating_system_item_ids;

    private org.openehr.schemas.v1.DV_IDENTIFIER[] feeder_system_item_ids;

    private org.openehr.schemas.v1.DV_ENCAPSULATED original_content;

    private org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS originating_system_audit;

    private org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS feeder_system_audit;

    public FEEDER_AUDIT() {
    }

    public FEEDER_AUDIT(
           org.openehr.schemas.v1.DV_IDENTIFIER[] originating_system_item_ids,
           org.openehr.schemas.v1.DV_IDENTIFIER[] feeder_system_item_ids,
           org.openehr.schemas.v1.DV_ENCAPSULATED original_content,
           org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS originating_system_audit,
           org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS feeder_system_audit) {
           this.originating_system_item_ids = originating_system_item_ids;
           this.feeder_system_item_ids = feeder_system_item_ids;
           this.original_content = original_content;
           this.originating_system_audit = originating_system_audit;
           this.feeder_system_audit = feeder_system_audit;
    }


    /**
     * Gets the originating_system_item_ids value for this FEEDER_AUDIT.
     * 
     * @return originating_system_item_ids
     */
    public org.openehr.schemas.v1.DV_IDENTIFIER[] getOriginating_system_item_ids() {
        return originating_system_item_ids;
    }


    /**
     * Sets the originating_system_item_ids value for this FEEDER_AUDIT.
     * 
     * @param originating_system_item_ids
     */
    public void setOriginating_system_item_ids(org.openehr.schemas.v1.DV_IDENTIFIER[] originating_system_item_ids) {
        this.originating_system_item_ids = originating_system_item_ids;
    }

    public org.openehr.schemas.v1.DV_IDENTIFIER getOriginating_system_item_ids(int i) {
        return this.originating_system_item_ids[i];
    }

    public void setOriginating_system_item_ids(int i, org.openehr.schemas.v1.DV_IDENTIFIER _value) {
        this.originating_system_item_ids[i] = _value;
    }


    /**
     * Gets the feeder_system_item_ids value for this FEEDER_AUDIT.
     * 
     * @return feeder_system_item_ids
     */
    public org.openehr.schemas.v1.DV_IDENTIFIER[] getFeeder_system_item_ids() {
        return feeder_system_item_ids;
    }


    /**
     * Sets the feeder_system_item_ids value for this FEEDER_AUDIT.
     * 
     * @param feeder_system_item_ids
     */
    public void setFeeder_system_item_ids(org.openehr.schemas.v1.DV_IDENTIFIER[] feeder_system_item_ids) {
        this.feeder_system_item_ids = feeder_system_item_ids;
    }

    public org.openehr.schemas.v1.DV_IDENTIFIER getFeeder_system_item_ids(int i) {
        return this.feeder_system_item_ids[i];
    }

    public void setFeeder_system_item_ids(int i, org.openehr.schemas.v1.DV_IDENTIFIER _value) {
        this.feeder_system_item_ids[i] = _value;
    }


    /**
     * Gets the original_content value for this FEEDER_AUDIT.
     * 
     * @return original_content
     */
    public org.openehr.schemas.v1.DV_ENCAPSULATED getOriginal_content() {
        return original_content;
    }


    /**
     * Sets the original_content value for this FEEDER_AUDIT.
     * 
     * @param original_content
     */
    public void setOriginal_content(org.openehr.schemas.v1.DV_ENCAPSULATED original_content) {
        this.original_content = original_content;
    }


    /**
     * Gets the originating_system_audit value for this FEEDER_AUDIT.
     * 
     * @return originating_system_audit
     */
    public org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS getOriginating_system_audit() {
        return originating_system_audit;
    }


    /**
     * Sets the originating_system_audit value for this FEEDER_AUDIT.
     * 
     * @param originating_system_audit
     */
    public void setOriginating_system_audit(org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS originating_system_audit) {
        this.originating_system_audit = originating_system_audit;
    }


    /**
     * Gets the feeder_system_audit value for this FEEDER_AUDIT.
     * 
     * @return feeder_system_audit
     */
    public org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS getFeeder_system_audit() {
        return feeder_system_audit;
    }


    /**
     * Sets the feeder_system_audit value for this FEEDER_AUDIT.
     * 
     * @param feeder_system_audit
     */
    public void setFeeder_system_audit(org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS feeder_system_audit) {
        this.feeder_system_audit = feeder_system_audit;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FEEDER_AUDIT)) return false;
        FEEDER_AUDIT other = (FEEDER_AUDIT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.originating_system_item_ids==null && other.getOriginating_system_item_ids()==null) || 
             (this.originating_system_item_ids!=null &&
              java.util.Arrays.equals(this.originating_system_item_ids, other.getOriginating_system_item_ids()))) &&
            ((this.feeder_system_item_ids==null && other.getFeeder_system_item_ids()==null) || 
             (this.feeder_system_item_ids!=null &&
              java.util.Arrays.equals(this.feeder_system_item_ids, other.getFeeder_system_item_ids()))) &&
            ((this.original_content==null && other.getOriginal_content()==null) || 
             (this.original_content!=null &&
              this.original_content.equals(other.getOriginal_content()))) &&
            ((this.originating_system_audit==null && other.getOriginating_system_audit()==null) || 
             (this.originating_system_audit!=null &&
              this.originating_system_audit.equals(other.getOriginating_system_audit()))) &&
            ((this.feeder_system_audit==null && other.getFeeder_system_audit()==null) || 
             (this.feeder_system_audit!=null &&
              this.feeder_system_audit.equals(other.getFeeder_system_audit())));
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
        if (getOriginating_system_item_ids() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOriginating_system_item_ids());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOriginating_system_item_ids(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFeeder_system_item_ids() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFeeder_system_item_ids());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFeeder_system_item_ids(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOriginal_content() != null) {
            _hashCode += getOriginal_content().hashCode();
        }
        if (getOriginating_system_audit() != null) {
            _hashCode += getOriginating_system_audit().hashCode();
        }
        if (getFeeder_system_audit() != null) {
            _hashCode += getFeeder_system_audit().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FEEDER_AUDIT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "FEEDER_AUDIT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originating_system_item_ids");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "originating_system_item_ids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_IDENTIFIER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeder_system_item_ids");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "feeder_system_item_ids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_IDENTIFIER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "original_content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_ENCAPSULATED"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originating_system_audit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "originating_system_audit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "FEEDER_AUDIT_DETAILS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeder_system_audit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "feeder_system_audit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "FEEDER_AUDIT_DETAILS"));
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
