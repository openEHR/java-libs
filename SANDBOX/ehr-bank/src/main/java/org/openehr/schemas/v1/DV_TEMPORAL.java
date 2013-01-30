/**
 * DV_TEMPORAL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class DV_TEMPORAL  extends org.openehr.schemas.v1.DV_QUANTIFIED  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_DURATION accuracy;

    public DV_TEMPORAL() {
    }

    public DV_TEMPORAL(
           org.openehr.schemas.v1.DV_INTERVAL normal_range,
           org.openehr.schemas.v1.REFERENCE_RANGE[] other_reference_ranges,
           org.openehr.schemas.v1.CODE_PHRASE normal_status,
           java.lang.String magnitude_status,
           org.openehr.schemas.v1.DV_DURATION accuracy) {
        super(
            normal_range,
            other_reference_ranges,
            normal_status,
            magnitude_status);
        this.accuracy = accuracy;
    }


    /**
     * Gets the accuracy value for this DV_TEMPORAL.
     * 
     * @return accuracy
     */
    public org.openehr.schemas.v1.DV_DURATION getAccuracy() {
        return accuracy;
    }


    /**
     * Sets the accuracy value for this DV_TEMPORAL.
     * 
     * @param accuracy
     */
    public void setAccuracy(org.openehr.schemas.v1.DV_DURATION accuracy) {
        this.accuracy = accuracy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DV_TEMPORAL)) return false;
        DV_TEMPORAL other = (DV_TEMPORAL) obj;
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
              this.accuracy.equals(other.getAccuracy())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DV_TEMPORAL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEMPORAL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accuracy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "accuracy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DURATION"));
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
