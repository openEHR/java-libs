/**
 * DV_MULTIMEDIA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_MULTIMEDIA  extends org.openehr.schemas.v1.DV_ENCAPSULATED  implements java.io.Serializable {
    private java.lang.String alternate_text;

    private org.openehr.schemas.v1.DV_URI uri;

    private byte[] data;

    private org.openehr.schemas.v1.CODE_PHRASE media_type;

    private org.openehr.schemas.v1.CODE_PHRASE compression_algorithm;

    private byte[] integrity_check;

    private org.openehr.schemas.v1.CODE_PHRASE integrity_check_algorithm;

    private int size;

    private org.openehr.schemas.v1.DV_MULTIMEDIA thumbnail;

    public DV_MULTIMEDIA() {
    }

    public DV_MULTIMEDIA(
           org.openehr.schemas.v1.CODE_PHRASE charset,
           org.openehr.schemas.v1.CODE_PHRASE language,
           java.lang.String alternate_text,
           org.openehr.schemas.v1.DV_URI uri,
           byte[] data,
           org.openehr.schemas.v1.CODE_PHRASE media_type,
           org.openehr.schemas.v1.CODE_PHRASE compression_algorithm,
           byte[] integrity_check,
           org.openehr.schemas.v1.CODE_PHRASE integrity_check_algorithm,
           int size,
           org.openehr.schemas.v1.DV_MULTIMEDIA thumbnail) {
        super(
            charset,
            language);
        this.alternate_text = alternate_text;
        this.uri = uri;
        this.data = data;
        this.media_type = media_type;
        this.compression_algorithm = compression_algorithm;
        this.integrity_check = integrity_check;
        this.integrity_check_algorithm = integrity_check_algorithm;
        this.size = size;
        this.thumbnail = thumbnail;
    }


    /**
     * Gets the alternate_text value for this DV_MULTIMEDIA.
     * 
     * @return alternate_text
     */
    public java.lang.String getAlternate_text() {
        return alternate_text;
    }


    /**
     * Sets the alternate_text value for this DV_MULTIMEDIA.
     * 
     * @param alternate_text
     */
    public void setAlternate_text(java.lang.String alternate_text) {
        this.alternate_text = alternate_text;
    }


    /**
     * Gets the uri value for this DV_MULTIMEDIA.
     * 
     * @return uri
     */
    public org.openehr.schemas.v1.DV_URI getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this DV_MULTIMEDIA.
     * 
     * @param uri
     */
    public void setUri(org.openehr.schemas.v1.DV_URI uri) {
        this.uri = uri;
    }


    /**
     * Gets the data value for this DV_MULTIMEDIA.
     * 
     * @return data
     */
    public byte[] getData() {
        return data;
    }


    /**
     * Sets the data value for this DV_MULTIMEDIA.
     * 
     * @param data
     */
    public void setData(byte[] data) {
        this.data = data;
    }


    /**
     * Gets the media_type value for this DV_MULTIMEDIA.
     * 
     * @return media_type
     */
    public org.openehr.schemas.v1.CODE_PHRASE getMedia_type() {
        return media_type;
    }


    /**
     * Sets the media_type value for this DV_MULTIMEDIA.
     * 
     * @param media_type
     */
    public void setMedia_type(org.openehr.schemas.v1.CODE_PHRASE media_type) {
        this.media_type = media_type;
    }


    /**
     * Gets the compression_algorithm value for this DV_MULTIMEDIA.
     * 
     * @return compression_algorithm
     */
    public org.openehr.schemas.v1.CODE_PHRASE getCompression_algorithm() {
        return compression_algorithm;
    }


    /**
     * Sets the compression_algorithm value for this DV_MULTIMEDIA.
     * 
     * @param compression_algorithm
     */
    public void setCompression_algorithm(org.openehr.schemas.v1.CODE_PHRASE compression_algorithm) {
        this.compression_algorithm = compression_algorithm;
    }


    /**
     * Gets the integrity_check value for this DV_MULTIMEDIA.
     * 
     * @return integrity_check
     */
    public byte[] getIntegrity_check() {
        return integrity_check;
    }


    /**
     * Sets the integrity_check value for this DV_MULTIMEDIA.
     * 
     * @param integrity_check
     */
    public void setIntegrity_check(byte[] integrity_check) {
        this.integrity_check = integrity_check;
    }


    /**
     * Gets the integrity_check_algorithm value for this DV_MULTIMEDIA.
     * 
     * @return integrity_check_algorithm
     */
    public org.openehr.schemas.v1.CODE_PHRASE getIntegrity_check_algorithm() {
        return integrity_check_algorithm;
    }


    /**
     * Sets the integrity_check_algorithm value for this DV_MULTIMEDIA.
     * 
     * @param integrity_check_algorithm
     */
    public void setIntegrity_check_algorithm(org.openehr.schemas.v1.CODE_PHRASE integrity_check_algorithm) {
        this.integrity_check_algorithm = integrity_check_algorithm;
    }


    /**
     * Gets the size value for this DV_MULTIMEDIA.
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }


    /**
     * Sets the size value for this DV_MULTIMEDIA.
     * 
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }


    /**
     * Gets the thumbnail value for this DV_MULTIMEDIA.
     * 
     * @return thumbnail
     */
    public org.openehr.schemas.v1.DV_MULTIMEDIA getThumbnail() {
        return thumbnail;
    }


    /**
     * Sets the thumbnail value for this DV_MULTIMEDIA.
     * 
     * @param thumbnail
     */
    public void setThumbnail(org.openehr.schemas.v1.DV_MULTIMEDIA thumbnail) {
        this.thumbnail = thumbnail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_MULTIMEDIA)) return false;
        DV_MULTIMEDIA other = (DV_MULTIMEDIA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.alternate_text==null && other.getAlternate_text()==null) || 
             (this.alternate_text!=null &&
              this.alternate_text.equals(other.getAlternate_text()))) &&
            ((this.uri==null && other.getUri()==null) || 
             (this.uri!=null &&
              this.uri.equals(other.getUri()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              java.util.Arrays.equals(this.data, other.getData()))) &&
            ((this.media_type==null && other.getMedia_type()==null) || 
             (this.media_type!=null &&
              this.media_type.equals(other.getMedia_type()))) &&
            ((this.compression_algorithm==null && other.getCompression_algorithm()==null) || 
             (this.compression_algorithm!=null &&
              this.compression_algorithm.equals(other.getCompression_algorithm()))) &&
            ((this.integrity_check==null && other.getIntegrity_check()==null) || 
             (this.integrity_check!=null &&
              java.util.Arrays.equals(this.integrity_check, other.getIntegrity_check()))) &&
            ((this.integrity_check_algorithm==null && other.getIntegrity_check_algorithm()==null) || 
             (this.integrity_check_algorithm!=null &&
              this.integrity_check_algorithm.equals(other.getIntegrity_check_algorithm()))) &&
            this.size == other.getSize() &&
            ((this.thumbnail==null && other.getThumbnail()==null) || 
             (this.thumbnail!=null &&
              this.thumbnail.equals(other.getThumbnail())));
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
        if (getAlternate_text() != null) {
            _hashCode += getAlternate_text().hashCode();
        }
        if (getUri() != null) {
            _hashCode += getUri().hashCode();
        }
        if (getData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMedia_type() != null) {
            _hashCode += getMedia_type().hashCode();
        }
        if (getCompression_algorithm() != null) {
            _hashCode += getCompression_algorithm().hashCode();
        }
        if (getIntegrity_check() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIntegrity_check());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIntegrity_check(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIntegrity_check_algorithm() != null) {
            _hashCode += getIntegrity_check_algorithm().hashCode();
        }
        _hashCode += getSize();
        if (getThumbnail() != null) {
            _hashCode += getThumbnail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_MULTIMEDIA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_MULTIMEDIA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alternate_text");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "alternate_text"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uri");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "uri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_URI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("media_type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "media_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compression_algorithm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "compression_algorithm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("integrity_check");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "integrity_check"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("integrity_check_algorithm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "integrity_check_algorithm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("size");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "size"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thumbnail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "thumbnail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_MULTIMEDIA"));
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
