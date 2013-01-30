/**
 * IMPORTED_VERSION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class IMPORTED_VERSION  extends org.openehr.schemas.v1.VERSION  implements java.io.Serializable {
    private org.openehr.schemas.v1.ORIGINAL_VERSION item;

    public IMPORTED_VERSION() {
    }

    public IMPORTED_VERSION(
           org.openehr.schemas.v1.OBJECT_REF contribution,
           org.openehr.schemas.v1.AUDIT_DETAILS commit_audit,
           java.lang.String signature,
           org.openehr.schemas.v1.ORIGINAL_VERSION item) {
        super(
            contribution,
            commit_audit,
            signature);
        this.item = item;
    }


    /**
     * Gets the item value for this IMPORTED_VERSION.
     * 
     * @return item
     */
    public org.openehr.schemas.v1.ORIGINAL_VERSION getItem() {
        return item;
    }


    /**
     * Sets the item value for this IMPORTED_VERSION.
     * 
     * @param item
     */
    public void setItem(org.openehr.schemas.v1.ORIGINAL_VERSION item) {
        this.item = item;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IMPORTED_VERSION)) return false;
        IMPORTED_VERSION other = (IMPORTED_VERSION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.item==null && other.getItem()==null) || 
             (this.item!=null &&
              this.item.equals(other.getItem())));
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
        if (getItem() != null) {
            _hashCode += getItem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IMPORTED_VERSION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "IMPORTED_VERSION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ORIGINAL_VERSION"));
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
