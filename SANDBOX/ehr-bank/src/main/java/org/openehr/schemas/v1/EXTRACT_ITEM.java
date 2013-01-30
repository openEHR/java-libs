/**
 * EXTRACT_ITEM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_ITEM  implements java.io.Serializable {
    private org.openehr.schemas.v1.HIER_OBJECT_ID uid;

    private boolean is_primary;

    private boolean is_changed;

    private boolean is_masked;

    private org.openehr.schemas.v1.DV_URI original_path;

    private org.openehr.schemas.v1.X_VERSIONED_OBJECT item;

    public EXTRACT_ITEM() {
    }

    public EXTRACT_ITEM(
           org.openehr.schemas.v1.HIER_OBJECT_ID uid,
           boolean is_primary,
           boolean is_changed,
           boolean is_masked,
           org.openehr.schemas.v1.DV_URI original_path,
           org.openehr.schemas.v1.X_VERSIONED_OBJECT item) {
           this.uid = uid;
           this.is_primary = is_primary;
           this.is_changed = is_changed;
           this.is_masked = is_masked;
           this.original_path = original_path;
           this.item = item;
    }


    /**
     * Gets the uid value for this EXTRACT_ITEM.
     * 
     * @return uid
     */
    public org.openehr.schemas.v1.HIER_OBJECT_ID getUid() {
        return uid;
    }


    /**
     * Sets the uid value for this EXTRACT_ITEM.
     * 
     * @param uid
     */
    public void setUid(org.openehr.schemas.v1.HIER_OBJECT_ID uid) {
        this.uid = uid;
    }


    /**
     * Gets the is_primary value for this EXTRACT_ITEM.
     * 
     * @return is_primary
     */
    public boolean isIs_primary() {
        return is_primary;
    }


    /**
     * Sets the is_primary value for this EXTRACT_ITEM.
     * 
     * @param is_primary
     */
    public void setIs_primary(boolean is_primary) {
        this.is_primary = is_primary;
    }


    /**
     * Gets the is_changed value for this EXTRACT_ITEM.
     * 
     * @return is_changed
     */
    public boolean isIs_changed() {
        return is_changed;
    }


    /**
     * Sets the is_changed value for this EXTRACT_ITEM.
     * 
     * @param is_changed
     */
    public void setIs_changed(boolean is_changed) {
        this.is_changed = is_changed;
    }


    /**
     * Gets the is_masked value for this EXTRACT_ITEM.
     * 
     * @return is_masked
     */
    public boolean isIs_masked() {
        return is_masked;
    }


    /**
     * Sets the is_masked value for this EXTRACT_ITEM.
     * 
     * @param is_masked
     */
    public void setIs_masked(boolean is_masked) {
        this.is_masked = is_masked;
    }


    /**
     * Gets the original_path value for this EXTRACT_ITEM.
     * 
     * @return original_path
     */
    public org.openehr.schemas.v1.DV_URI getOriginal_path() {
        return original_path;
    }


    /**
     * Sets the original_path value for this EXTRACT_ITEM.
     * 
     * @param original_path
     */
    public void setOriginal_path(org.openehr.schemas.v1.DV_URI original_path) {
        this.original_path = original_path;
    }


    /**
     * Gets the item value for this EXTRACT_ITEM.
     * 
     * @return item
     */
    public org.openehr.schemas.v1.X_VERSIONED_OBJECT getItem() {
        return item;
    }


    /**
     * Sets the item value for this EXTRACT_ITEM.
     * 
     * @param item
     */
    public void setItem(org.openehr.schemas.v1.X_VERSIONED_OBJECT item) {
        this.item = item;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_ITEM)) return false;
        EXTRACT_ITEM other = (EXTRACT_ITEM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.uid==null && other.getUid()==null) || 
             (this.uid!=null &&
              this.uid.equals(other.getUid()))) &&
            this.is_primary == other.isIs_primary() &&
            this.is_changed == other.isIs_changed() &&
            this.is_masked == other.isIs_masked() &&
            ((this.original_path==null && other.getOriginal_path()==null) || 
             (this.original_path!=null &&
              this.original_path.equals(other.getOriginal_path()))) &&
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
        int _hashCode = 1;
        if (getUid() != null) {
            _hashCode += getUid().hashCode();
        }
        _hashCode += (isIs_primary() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIs_changed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIs_masked() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOriginal_path() != null) {
            _hashCode += getOriginal_path().hashCode();
        }
        if (getItem() != null) {
            _hashCode += getItem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT_ITEM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_ITEM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "uid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HIER_OBJECT_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_primary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_primary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_changed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_changed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("is_masked");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "is_masked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("original_path");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "original_path"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_URI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "X_VERSIONED_OBJECT"));
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
