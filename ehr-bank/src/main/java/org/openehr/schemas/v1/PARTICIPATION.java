/**
 * PARTICIPATION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class PARTICIPATION  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_TEXT function;

    private org.openehr.schemas.v1.PARTY_PROXY performer;

    private org.openehr.schemas.v1.DV_INTERVAL time;

    private org.openehr.schemas.v1.DV_CODED_TEXT mode;

    public PARTICIPATION() {
    }

    public PARTICIPATION(
           org.openehr.schemas.v1.DV_TEXT function,
           org.openehr.schemas.v1.PARTY_PROXY performer,
           org.openehr.schemas.v1.DV_INTERVAL time,
           org.openehr.schemas.v1.DV_CODED_TEXT mode) {
           this.function = function;
           this.performer = performer;
           this.time = time;
           this.mode = mode;
    }


    /**
     * Gets the function value for this PARTICIPATION.
     * 
     * @return function
     */
    public org.openehr.schemas.v1.DV_TEXT getFunction() {
        return function;
    }


    /**
     * Sets the function value for this PARTICIPATION.
     * 
     * @param function
     */
    public void setFunction(org.openehr.schemas.v1.DV_TEXT function) {
        this.function = function;
    }


    /**
     * Gets the performer value for this PARTICIPATION.
     * 
     * @return performer
     */
    public org.openehr.schemas.v1.PARTY_PROXY getPerformer() {
        return performer;
    }


    /**
     * Sets the performer value for this PARTICIPATION.
     * 
     * @param performer
     */
    public void setPerformer(org.openehr.schemas.v1.PARTY_PROXY performer) {
        this.performer = performer;
    }


    /**
     * Gets the time value for this PARTICIPATION.
     * 
     * @return time
     */
    public org.openehr.schemas.v1.DV_INTERVAL getTime() {
        return time;
    }


    /**
     * Sets the time value for this PARTICIPATION.
     * 
     * @param time
     */
    public void setTime(org.openehr.schemas.v1.DV_INTERVAL time) {
        this.time = time;
    }


    /**
     * Gets the mode value for this PARTICIPATION.
     * 
     * @return mode
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getMode() {
        return mode;
    }


    /**
     * Sets the mode value for this PARTICIPATION.
     * 
     * @param mode
     */
    public void setMode(org.openehr.schemas.v1.DV_CODED_TEXT mode) {
        this.mode = mode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PARTICIPATION)) return false;
        PARTICIPATION other = (PARTICIPATION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.function==null && other.getFunction()==null) || 
             (this.function!=null &&
              this.function.equals(other.getFunction()))) &&
            ((this.performer==null && other.getPerformer()==null) || 
             (this.performer!=null &&
              this.performer.equals(other.getPerformer()))) &&
            ((this.time==null && other.getTime()==null) || 
             (this.time!=null &&
              this.time.equals(other.getTime()))) &&
            ((this.mode==null && other.getMode()==null) || 
             (this.mode!=null &&
              this.mode.equals(other.getMode())));
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
        if (getFunction() != null) {
            _hashCode += getFunction().hashCode();
        }
        if (getPerformer() != null) {
            _hashCode += getPerformer().hashCode();
        }
        if (getTime() != null) {
            _hashCode += getTime().hashCode();
        }
        if (getMode() != null) {
            _hashCode += getMode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PARTICIPATION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTICIPATION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("function");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "function"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("performer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "performer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_PROXY"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_INTERVAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "mode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
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
