/**
 * GetSubjectId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class GetSubjectId  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.String ehrUid;

    private java.lang.String subjectIdNamespace;

    public GetSubjectId() {
    }

    public GetSubjectId(
           java.lang.String sessionId,
           java.lang.String ehrUid,
           java.lang.String subjectIdNamespace) {
           this.sessionId = sessionId;
           this.ehrUid = ehrUid;
           this.subjectIdNamespace = subjectIdNamespace;
    }


    /**
     * Gets the sessionId value for this GetSubjectId.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this GetSubjectId.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the ehrUid value for this GetSubjectId.
     * 
     * @return ehrUid
     */
    public java.lang.String getEhrUid() {
        return ehrUid;
    }


    /**
     * Sets the ehrUid value for this GetSubjectId.
     * 
     * @param ehrUid
     */
    public void setEhrUid(java.lang.String ehrUid) {
        this.ehrUid = ehrUid;
    }


    /**
     * Gets the subjectIdNamespace value for this GetSubjectId.
     * 
     * @return subjectIdNamespace
     */
    public java.lang.String getSubjectIdNamespace() {
        return subjectIdNamespace;
    }


    /**
     * Sets the subjectIdNamespace value for this GetSubjectId.
     * 
     * @param subjectIdNamespace
     */
    public void setSubjectIdNamespace(java.lang.String subjectIdNamespace) {
        this.subjectIdNamespace = subjectIdNamespace;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetSubjectId)) return false;
        GetSubjectId other = (GetSubjectId) obj;
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
            ((this.ehrUid==null && other.getEhrUid()==null) || 
             (this.ehrUid!=null &&
              this.ehrUid.equals(other.getEhrUid()))) &&
            ((this.subjectIdNamespace==null && other.getSubjectIdNamespace()==null) || 
             (this.subjectIdNamespace!=null &&
              this.subjectIdNamespace.equals(other.getSubjectIdNamespace())));
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
        if (getEhrUid() != null) {
            _hashCode += getEhrUid().hashCode();
        }
        if (getSubjectIdNamespace() != null) {
            _hashCode += getSubjectIdNamespace().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetSubjectId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">GetSubjectId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "sessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ehrUid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "ehrUid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subjectIdNamespace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "subjectIdNamespace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
