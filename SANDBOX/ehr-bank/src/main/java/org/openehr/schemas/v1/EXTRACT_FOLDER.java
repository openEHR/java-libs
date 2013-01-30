/**
 * EXTRACT_FOLDER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT_FOLDER  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.OBJECT_REF[] items;

    private org.openehr.schemas.v1.EXTRACT_FOLDER[] folders;

    public EXTRACT_FOLDER() {
    }

    public EXTRACT_FOLDER(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.OBJECT_REF[] items,
           org.openehr.schemas.v1.EXTRACT_FOLDER[] folders) {
        super(
            name,
            uid,
            links,
            archetype_details,
            feeder_audit, 
            archetype_node_id);
        this.items = items;
        this.folders = folders;
    }


    /**
     * Gets the items value for this EXTRACT_FOLDER.
     * 
     * @return items
     */
    public org.openehr.schemas.v1.OBJECT_REF[] getItems() {
        return items;
    }


    /**
     * Sets the items value for this EXTRACT_FOLDER.
     * 
     * @param items
     */
    public void setItems(org.openehr.schemas.v1.OBJECT_REF[] items) {
        this.items = items;
    }

    public org.openehr.schemas.v1.OBJECT_REF getItems(int i) {
        return this.items[i];
    }

    public void setItems(int i, org.openehr.schemas.v1.OBJECT_REF _value) {
        this.items[i] = _value;
    }


    /**
     * Gets the folders value for this EXTRACT_FOLDER.
     * 
     * @return folders
     */
    public org.openehr.schemas.v1.EXTRACT_FOLDER[] getFolders() {
        return folders;
    }


    /**
     * Sets the folders value for this EXTRACT_FOLDER.
     * 
     * @param folders
     */
    public void setFolders(org.openehr.schemas.v1.EXTRACT_FOLDER[] folders) {
        this.folders = folders;
    }

    public org.openehr.schemas.v1.EXTRACT_FOLDER getFolders(int i) {
        return this.folders[i];
    }

    public void setFolders(int i, org.openehr.schemas.v1.EXTRACT_FOLDER _value) {
        this.folders[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT_FOLDER)) return false;
        EXTRACT_FOLDER other = (EXTRACT_FOLDER) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.items==null && other.getItems()==null) || 
             (this.items!=null &&
              java.util.Arrays.equals(this.items, other.getItems()))) &&
            ((this.folders==null && other.getFolders()==null) || 
             (this.folders!=null &&
              java.util.Arrays.equals(this.folders, other.getFolders())));
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
        if (getFolders() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFolders());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFolders(), i);
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
        new org.apache.axis.description.TypeDesc(EXTRACT_FOLDER.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_FOLDER"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("items");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "items"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folders");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "folders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_FOLDER"));
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
