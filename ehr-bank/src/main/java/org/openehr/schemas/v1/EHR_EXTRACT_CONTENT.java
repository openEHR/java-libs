/**
 * EHR_EXTRACT_CONTENT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EHR_EXTRACT_CONTENT  extends org.openehr.schemas.v1.EXTRACT_CONTENT  implements java.io.Serializable {
    private org.openehr.schemas.v1.EXTRACT_ITEM[] compositions;

    private org.openehr.schemas.v1.EXTRACT_ITEM directory;

    public EHR_EXTRACT_CONTENT() {
    }

    public EHR_EXTRACT_CONTENT(
           org.openehr.schemas.v1.EXTRACT_ITEM[] compositions,
           org.openehr.schemas.v1.EXTRACT_ITEM directory) {
        this.compositions = compositions;
        this.directory = directory;
    }


    /**
     * Gets the compositions value for this EHR_EXTRACT_CONTENT.
     * 
     * @return compositions
     */
    public org.openehr.schemas.v1.EXTRACT_ITEM[] getCompositions() {
        return compositions;
    }


    /**
     * Sets the compositions value for this EHR_EXTRACT_CONTENT.
     * 
     * @param compositions
     */
    public void setCompositions(org.openehr.schemas.v1.EXTRACT_ITEM[] compositions) {
        this.compositions = compositions;
    }

    public org.openehr.schemas.v1.EXTRACT_ITEM getCompositions(int i) {
        return this.compositions[i];
    }

    public void setCompositions(int i, org.openehr.schemas.v1.EXTRACT_ITEM _value) {
        this.compositions[i] = _value;
    }


    /**
     * Gets the directory value for this EHR_EXTRACT_CONTENT.
     * 
     * @return directory
     */
    public org.openehr.schemas.v1.EXTRACT_ITEM getDirectory() {
        return directory;
    }


    /**
     * Sets the directory value for this EHR_EXTRACT_CONTENT.
     * 
     * @param directory
     */
    public void setDirectory(org.openehr.schemas.v1.EXTRACT_ITEM directory) {
        this.directory = directory;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EHR_EXTRACT_CONTENT)) return false;
        EHR_EXTRACT_CONTENT other = (EHR_EXTRACT_CONTENT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.compositions==null && other.getCompositions()==null) || 
             (this.compositions!=null &&
              java.util.Arrays.equals(this.compositions, other.getCompositions()))) &&
            ((this.directory==null && other.getDirectory()==null) || 
             (this.directory!=null &&
              this.directory.equals(other.getDirectory())));
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
        if (getCompositions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCompositions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCompositions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDirectory() != null) {
            _hashCode += getDirectory().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EHR_EXTRACT_CONTENT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EHR_EXTRACT_CONTENT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compositions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "compositions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_ITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("directory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "directory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_ITEM"));
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
