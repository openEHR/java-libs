/**
 * LINK.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class LINK  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_TEXT meaning;

    private org.openehr.schemas.v1.DV_TEXT type;

    private org.openehr.schemas.v1.DV_EHR_URI target;

    public LINK() {
    }

    public LINK(
           org.openehr.schemas.v1.DV_TEXT meaning,
           org.openehr.schemas.v1.DV_TEXT type,
           org.openehr.schemas.v1.DV_EHR_URI target) {
           this.meaning = meaning;
           this.type = type;
           this.target = target;
    }


    /**
     * Gets the meaning value for this LINK.
     * 
     * @return meaning
     */
    public org.openehr.schemas.v1.DV_TEXT getMeaning() {
        return meaning;
    }


    /**
     * Sets the meaning value for this LINK.
     * 
     * @param meaning
     */
    public void setMeaning(org.openehr.schemas.v1.DV_TEXT meaning) {
        this.meaning = meaning;
    }


    /**
     * Gets the type value for this LINK.
     * 
     * @return type
     */
    public org.openehr.schemas.v1.DV_TEXT getType() {
        return type;
    }


    /**
     * Sets the type value for this LINK.
     * 
     * @param type
     */
    public void setType(org.openehr.schemas.v1.DV_TEXT type) {
        this.type = type;
    }


    /**
     * Gets the target value for this LINK.
     * 
     * @return target
     */
    public org.openehr.schemas.v1.DV_EHR_URI getTarget() {
        return target;
    }


    /**
     * Sets the target value for this LINK.
     * 
     * @param target
     */
    public void setTarget(org.openehr.schemas.v1.DV_EHR_URI target) {
        this.target = target;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LINK)) return false;
        LINK other = (LINK) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.meaning==null && other.getMeaning()==null) || 
             (this.meaning!=null &&
              this.meaning.equals(other.getMeaning()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.target==null && other.getTarget()==null) || 
             (this.target!=null &&
              this.target.equals(other.getTarget())));
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
        if (getMeaning() != null) {
            _hashCode += getMeaning().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getTarget() != null) {
            _hashCode += getTarget().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LINK.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "LINK"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meaning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "meaning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("target");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "target"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_EHR_URI"));
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
