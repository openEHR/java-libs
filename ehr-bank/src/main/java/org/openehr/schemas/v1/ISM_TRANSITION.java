/**
 * ISM_TRANSITION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class ISM_TRANSITION  implements java.io.Serializable {
    private org.openehr.schemas.v1.DV_CODED_TEXT current_state;

    private org.openehr.schemas.v1.DV_CODED_TEXT transition;

    private org.openehr.schemas.v1.DV_CODED_TEXT careflow_step;

    public ISM_TRANSITION() {
    }

    public ISM_TRANSITION(
           org.openehr.schemas.v1.DV_CODED_TEXT current_state,
           org.openehr.schemas.v1.DV_CODED_TEXT transition,
           org.openehr.schemas.v1.DV_CODED_TEXT careflow_step) {
           this.current_state = current_state;
           this.transition = transition;
           this.careflow_step = careflow_step;
    }


    /**
     * Gets the current_state value for this ISM_TRANSITION.
     * 
     * @return current_state
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getCurrent_state() {
        return current_state;
    }


    /**
     * Sets the current_state value for this ISM_TRANSITION.
     * 
     * @param current_state
     */
    public void setCurrent_state(org.openehr.schemas.v1.DV_CODED_TEXT current_state) {
        this.current_state = current_state;
    }


    /**
     * Gets the transition value for this ISM_TRANSITION.
     * 
     * @return transition
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getTransition() {
        return transition;
    }


    /**
     * Sets the transition value for this ISM_TRANSITION.
     * 
     * @param transition
     */
    public void setTransition(org.openehr.schemas.v1.DV_CODED_TEXT transition) {
        this.transition = transition;
    }


    /**
     * Gets the careflow_step value for this ISM_TRANSITION.
     * 
     * @return careflow_step
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getCareflow_step() {
        return careflow_step;
    }


    /**
     * Sets the careflow_step value for this ISM_TRANSITION.
     * 
     * @param careflow_step
     */
    public void setCareflow_step(org.openehr.schemas.v1.DV_CODED_TEXT careflow_step) {
        this.careflow_step = careflow_step;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ISM_TRANSITION)) return false;
        ISM_TRANSITION other = (ISM_TRANSITION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.current_state==null && other.getCurrent_state()==null) || 
             (this.current_state!=null &&
              this.current_state.equals(other.getCurrent_state()))) &&
            ((this.transition==null && other.getTransition()==null) || 
             (this.transition!=null &&
              this.transition.equals(other.getTransition()))) &&
            ((this.careflow_step==null && other.getCareflow_step()==null) || 
             (this.careflow_step!=null &&
              this.careflow_step.equals(other.getCareflow_step())));
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
        if (getCurrent_state() != null) {
            _hashCode += getCurrent_state().hashCode();
        }
        if (getTransition() != null) {
            _hashCode += getTransition().hashCode();
        }
        if (getCareflow_step() != null) {
            _hashCode += getCareflow_step().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ISM_TRANSITION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ISM_TRANSITION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("current_state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "current_state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "transition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("careflow_step");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "careflow_step"));
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
