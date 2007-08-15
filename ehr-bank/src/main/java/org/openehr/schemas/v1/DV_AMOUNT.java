/**
 * DV_AMOUNT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_AMOUNT  extends org.openehr.schemas.v1.DV_QUANTIFIED  implements java.io.Serializable {
    private java.lang.Float accuracy;

    private java.lang.Boolean accuracy_is_percent;

    public DV_AMOUNT() {
    }

    public DV_AMOUNT(
           org.openehr.schemas.v1.DV_INTERVAL normal_range,
           org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges,
           org.openehr.schemas.v1.CODE_PHRASE normal_status,
           java.lang.String magnitude_status,
           java.lang.Float accuracy,
           java.lang.Boolean accuracy_is_percent) {
        super(
            normal_range,
            other_reference_ranges,
            normal_status,
            magnitude_status);
        this.accuracy = accuracy;
        this.accuracy_is_percent = accuracy_is_percent;
    }


    /**
     * Gets the accuracy value for this DV_AMOUNT.
     * 
     * @return accuracy
     */
    public java.lang.Float getAccuracy() {
        return accuracy;
    }


    /**
     * Sets the accuracy value for this DV_AMOUNT.
     * 
     * @param accuracy
     */
    public void setAccuracy(java.lang.Float accuracy) {
        this.accuracy = accuracy;
    }


    /**
     * Gets the accuracy_is_percent value for this DV_AMOUNT.
     * 
     * @return accuracy_is_percent
     */
    public java.lang.Boolean getAccuracy_is_percent() {
        return accuracy_is_percent;
    }


    /**
     * Sets the accuracy_is_percent value for this DV_AMOUNT.
     * 
     * @param accuracy_is_percent
     */
    public void setAccuracy_is_percent(java.lang.Boolean accuracy_is_percent) {
        this.accuracy_is_percent = accuracy_is_percent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_AMOUNT)) return false;
        DV_AMOUNT other = (DV_AMOUNT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accuracy==null && other.getAccuracy()==null) || 
             (this.accuracy!=null &&
              this.accuracy.equals(other.getAccuracy()))) &&
            ((this.accuracy_is_percent==null && other.getAccuracy_is_percent()==null) || 
             (this.accuracy_is_percent!=null &&
              this.accuracy_is_percent.equals(other.getAccuracy_is_percent())));
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
        if (getAccuracy() != null) {
            _hashCode += getAccuracy().hashCode();
        }
        if (getAccuracy_is_percent() != null) {
            _hashCode += getAccuracy_is_percent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_AMOUNT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_AMOUNT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accuracy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "accuracy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accuracy_is_percent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "accuracy_is_percent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
