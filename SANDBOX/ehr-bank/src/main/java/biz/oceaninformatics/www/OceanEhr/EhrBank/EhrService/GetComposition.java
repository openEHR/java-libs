/**
 * GetComposition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class GetComposition  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.String compositionVersionUid;

    private boolean includeOriginalContent;

    public GetComposition() {
    }

    public GetComposition(
           java.lang.String sessionId,
           java.lang.String compositionVersionUid,
           boolean includeOriginalContent) {
           this.sessionId = sessionId;
           this.compositionVersionUid = compositionVersionUid;
           this.includeOriginalContent = includeOriginalContent;
    }


    /**
     * Gets the sessionId value for this GetComposition.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetComposition.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the compositionVersionUid value for this GetComposition.
     * 
     * @return compositionVersionUid
     */
    public java.lang.String getCompositionVersionUid() {
        return compositionVersionUid;
    }


    /**
     * Sets the compositionVersionUid value for this GetComposition.
     * 
     * @param compositionVersionUid
     */
    public void setCompositionVersionUid(java.lang.String compositionVersionUid) {
        this.compositionVersionUid = compositionVersionUid;
    }


    /**
     * Gets the includeOriginalContent value for this GetComposition.
     * 
     * @return includeOriginalContent
     */
    public boolean isIncludeOriginalContent() {
        return includeOriginalContent;
    }


    /**
     * Sets the includeOriginalContent value for this GetComposition.
     * 
     * @param includeOriginalContent
     */
    public void setIncludeOriginalContent(boolean includeOriginalContent) {
        this.includeOriginalContent = includeOriginalContent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetComposition)) return false;
        GetComposition other = (GetComposition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sessionId==null && other.getSessionId()==null) || 
             (this.sessionId!=null &&
              this.sessionId.equals(other.getSessionId()))) &&
            ((this.compositionVersionUid==null && other.getCompositionVersionUid()==null) || 
             (this.compositionVersionUid!=null &&
              this.compositionVersionUid.equals(other.getCompositionVersionUid()))) &&
            this.includeOriginalContent == other.isIncludeOriginalContent();
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
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        if (getCompositionVersionUid() != null) {
            _hashCode += getCompositionVersionUid().hashCode();
        }
        _hashCode += (isIncludeOriginalContent() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetComposition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">GetComposition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compositionVersionUid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "compositionVersionUid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeOriginalContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "includeOriginalContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
