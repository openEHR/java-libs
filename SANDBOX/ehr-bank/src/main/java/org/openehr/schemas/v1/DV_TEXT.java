/**
 * DV_TEXT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_TEXT  extends org.openehr.schemas.v1.DATA_VALUE  implements java.io.Serializable {
    private java.lang.String value;

    private org.openehr.schemas.v1.DV_URI hyperlink;

    private java.lang.String formatting;

    private org.openehr.schemas.v1.TERM_MAPPING[] mappings;

    private org.openehr.schemas.v1.CODE_PHRASE language;

    private org.openehr.schemas.v1.CODE_PHRASE encoding;

    public DV_TEXT() {
    }

    public DV_TEXT(
           java.lang.String value,
           org.openehr.schemas.v1.DV_URI hyperlink,
           java.lang.String formatting,
           org.openehr.schemas.v1.TERM_MAPPING[] mappings,
           org.openehr.schemas.v1.CODE_PHRASE language,
           org.openehr.schemas.v1.CODE_PHRASE encoding) {
        this.value = value;
        this.hyperlink = hyperlink;
        this.formatting = formatting;
        this.mappings = mappings;
        this.language = language;
        this.encoding = encoding;
    }


    /**
     * Gets the value value for this DV_TEXT.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this DV_TEXT.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the hyperlink value for this DV_TEXT.
     * 
     * @return hyperlink
     */
    public org.openehr.schemas.v1.DV_URI getHyperlink() {
        return hyperlink;
    }


    /**
     * Sets the hyperlink value for this DV_TEXT.
     * 
     * @param hyperlink
     */
    public void setHyperlink(org.openehr.schemas.v1.DV_URI hyperlink) {
        this.hyperlink = hyperlink;
    }


    /**
     * Gets the formatting value for this DV_TEXT.
     * 
     * @return formatting
     */
    public java.lang.String getFormatting() {
        return formatting;
    }


    /**
     * Sets the formatting value for this DV_TEXT.
     * 
     * @param formatting
     */
    public void setFormatting(java.lang.String formatting) {
        this.formatting = formatting;
    }


    /**
     * Gets the mappings value for this DV_TEXT.
     * 
     * @return mappings
     */
    public org.openehr.schemas.v1.TERM_MAPPING[] getMappings() {
        return mappings;
    }


    /**
     * Sets the mappings value for this DV_TEXT.
     * 
     * @param mappings
     */
    public void setMappings(org.openehr.schemas.v1.TERM_MAPPING[] mappings) {
        this.mappings = mappings;
    }

    public org.openehr.schemas.v1.TERM_MAPPING getMappings(int i) {
        return this.mappings[i];
    }

    public void setMappings(int i, org.openehr.schemas.v1.TERM_MAPPING _value) {
        this.mappings[i] = _value;
    }


    /**
     * Gets the language value for this DV_TEXT.
     * 
     * @return language
     */
    public org.openehr.schemas.v1.CODE_PHRASE getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this DV_TEXT.
     * 
     * @param language
     */
    public void setLanguage(org.openehr.schemas.v1.CODE_PHRASE language) {
        this.language = language;
    }


    /**
     * Gets the encoding value for this DV_TEXT.
     * 
     * @return encoding
     */
    public org.openehr.schemas.v1.CODE_PHRASE getEncoding() {
        return encoding;
    }


    /**
     * Sets the encoding value for this DV_TEXT.
     * 
     * @param encoding
     */
    public void setEncoding(org.openehr.schemas.v1.CODE_PHRASE encoding) {
        this.encoding = encoding;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_TEXT)) return false;
        DV_TEXT other = (DV_TEXT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.hyperlink==null && other.getHyperlink()==null) || 
             (this.hyperlink!=null &&
              this.hyperlink.equals(other.getHyperlink()))) &&
            ((this.formatting==null && other.getFormatting()==null) || 
             (this.formatting!=null &&
              this.formatting.equals(other.getFormatting()))) &&
            ((this.mappings==null && other.getMappings()==null) || 
             (this.mappings!=null &&
              java.util.Arrays.equals(this.mappings, other.getMappings()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.encoding==null && other.getEncoding()==null) || 
             (this.encoding!=null &&
              this.encoding.equals(other.getEncoding())));
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
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getHyperlink() != null) {
            _hashCode += getHyperlink().hashCode();
        }
        if (getFormatting() != null) {
            _hashCode += getFormatting().hashCode();
        }
        if (getMappings() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMappings());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMappings(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getEncoding() != null) {
            _hashCode += getEncoding().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_TEXT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hyperlink");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "hyperlink"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_URI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "formatting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mappings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "mappings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "TERM_MAPPING"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encoding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "encoding"));
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
