/**
 * OBSERVATION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class OBSERVATION  extends org.openehr.schemas.v1.CARE_ENTRY  implements java.io.Serializable {
    private org.openehr.schemas.v1.HISTORY data;

    private org.openehr.schemas.v1.HISTORY state;

    public OBSERVATION() {
    }

    public OBSERVATION(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.CODE_PHRASE language,
           org.openehr.schemas.v1.CODE_PHRASE encoding,
           org.openehr.schemas.v1.PARTY_PROXY subject,
           org.openehr.schemas.v1.PARTY_PROXY provider,
           org.openehr.schemas.v1.PARTICIPATION[] other_participations,
           org.openehr.schemas.v1.OBJECT_REF work_flow_id,
           org.openehr.schemas.v1.ITEM_STRUCTURE protocol,
           org.openehr.schemas.v1.OBJECT_REF guideline_id,
           org.openehr.schemas.v1.HISTORY data,
           org.openehr.schemas.v1.HISTORY state) {
        super(
            archetype_node_id,
            name,
            uid,
            links,
            archetype_details,
            feeder_audit,
            language,
            encoding,
            subject,
            provider,
            other_participations,
            work_flow_id,
            protocol,
            guideline_id);
        this.data = data;
        this.state = state;
    }


    /**
     * Gets the data value for this OBSERVATION.
     * 
     * @return data
     */
    public org.openehr.schemas.v1.HISTORY getData() {
        return data;
    }


    /**
     * Sets the data value for this OBSERVATION.
     * 
     * @param data
     */
    public void setData(org.openehr.schemas.v1.HISTORY data) {
        this.data = data;
    }


    /**
     * Gets the state value for this OBSERVATION.
     * 
     * @return state
     */
    public org.openehr.schemas.v1.HISTORY getState() {
        return state;
    }


    /**
     * Sets the state value for this OBSERVATION.
     * 
     * @param state
     */
    public void setState(org.openehr.schemas.v1.HISTORY state) {
        this.state = state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OBSERVATION)) return false;
        OBSERVATION other = (OBSERVATION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
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
        new org.apache.axis.description.TypeDesc(OBSERVATION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBSERVATION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HISTORY"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HISTORY"));
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
