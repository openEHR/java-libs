/**
 * EVENT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public abstract class EVENT  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_DATE_TIME time;

    private org.openehr.schemas.v1.ITEM_STRUCTURE data;

    private org.openehr.schemas.v1.ITEM_STRUCTURE state;

    public EVENT() {
    }

    public EVENT(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.DV_DATE_TIME time,
           org.openehr.schemas.v1.ITEM_STRUCTURE data,
           org.openehr.schemas.v1.ITEM_STRUCTURE state) {
        super(
            name,
            uid,
            links,
            archetype_details,
            feeder_audit,
            archetype_node_id);
        this.time = time;
        this.data = data;
        this.state = state;
    }


    /**
     * Gets the time value for this EVENT.
     * 
     * @return time
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getTime() {
        return time;
    }


    /**
     * Sets the time value for this EVENT.
     * 
     * @param time
     */
    public void setTime(org.openehr.schemas.v1.DV_DATE_TIME time) {
        this.time = time;
    }


    /**
     * Gets the data value for this EVENT.
     * 
     * @return data
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getData() {
        return data;
    }


    /**
     * Sets the data value for this EVENT.
     * 
     * @param data
     */
    public void setData(org.openehr.schemas.v1.ITEM_STRUCTURE data) {
        this.data = data;
    }


    /**
     * Gets the state value for this EVENT.
     * 
     * @return state
     */
    public org.openehr.schemas.v1.ITEM_STRUCTURE getState() {
        return state;
    }


    /**
     * Sets the state value for this EVENT.
     * 
     * @param state
     */
    public void setState(org.openehr.schemas.v1.ITEM_STRUCTURE state) {
        this.state = state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EVENT)) return false;
        EVENT other = (EVENT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.time==null && other.getTime()==null) || 
             (this.time!=null &&
              this.time.equals(other.getTime()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              this.data.equals(other.getData()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState())));
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
        if (getTime() != null) {
            _hashCode += getTime().hashCode();
        }
        if (getData() != null) {
            _hashCode += getData().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EVENT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EVENT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ITEM_STRUCTURE"));
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
