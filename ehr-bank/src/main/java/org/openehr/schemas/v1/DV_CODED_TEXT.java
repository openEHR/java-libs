/**
 * DV_CODED_TEXT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_CODED_TEXT  extends org.openehr.schemas.v1.DV_TEXT  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE defining_code;

    public DV_CODED_TEXT() {
    }

    public DV_CODED_TEXT(
           java.lang.String value,
           org.openehr.schemas.v1.DV_URI hyperlink,
           java.lang.String formatting,
           org.openehr.schemas.v1.TERM_MAPPING[] mappings,
           org.openehr.schemas.v1.CODE_PHRASE language,
           org.openehr.schemas.v1.CODE_PHRASE encoding,
           org.openehr.schemas.v1.CODE_PHRASE defining_code) {
        super(
            value,
            hyperlink,
            formatting,
            mappings,
            language,
            encoding);
        this.defining_code = defining_code;
    }


    /**
     * Gets the defining_code value for this DV_CODED_TEXT.
     * 
     * @return defining_code
     */
    public org.openehr.schemas.v1.CODE_PHRASE getDefining_code() {
        return defining_code;
    }


    /**
     * Sets the defining_code value for this DV_CODED_TEXT.
     * 
     * @param defining_code
     */
    public void setDefining_code(org.openehr.schemas.v1.CODE_PHRASE defining_code) {
        this.defining_code = defining_code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_CODED_TEXT)) return false;
        DV_CODED_TEXT other = (DV_CODED_TEXT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.defining_code==null && other.getDefining_code()==null) || 
             (this.defining_code!=null &&
              this.defining_code.equals(other.getDefining_code())));
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
        if (getDefining_code() != null) {
            _hashCode += getDefining_code().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_CODED_TEXT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defining_code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "defining_code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
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
