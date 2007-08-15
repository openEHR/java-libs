/**
 * GetCompositionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class GetCompositionResponse  implements java.io.Serializable {
    private org.openehr.schemas.v1.COMPOSITION composition;

    public GetCompositionResponse() {
    }

    public GetCompositionResponse(
           org.openehr.schemas.v1.COMPOSITION composition) {
           this.composition = composition;
    }


    /**
     * Gets the composition value for this GetCompositionResponse.
     * 
     * @return composition
     */
    public org.openehr.schemas.v1.COMPOSITION getComposition() {
        return composition;
    }


    /**
     * Sets the composition value for this GetCompositionResponse.
     * 
     * @param composition
     */
    public void setComposition(org.openehr.schemas.v1.COMPOSITION composition) {
        this.composition = composition;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetCompositionResponse)) return false;
        GetCompositionResponse other = (GetCompositionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.composition==null && other.getComposition()==null) || 
             (this.composition!=null &&
              this.composition.equals(other.getComposition())));
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
        if (getComposition() != null) {
            _hashCode += getComposition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetCompositionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">GetCompositionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("composition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "composition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "COMPOSITION"));
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
