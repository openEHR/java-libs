/**
 * CommitContribution.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class CommitContribution  implements java.io.Serializable {
    private java.lang.String sessionId;

    private java.lang.String ehrId;

    private org.openehr.schemas.v1.AUDIT_DETAILS audit;

    private org.openehr.schemas.v1.ORIGINAL_VERSION[] versions;

    public CommitContribution() {
    }

    public CommitContribution(
           java.lang.String sessionId,
           java.lang.String ehrId,
           org.openehr.schemas.v1.AUDIT_DETAILS audit,
           org.openehr.schemas.v1.ORIGINAL_VERSION[] versions) {
           this.sessionId = sessionId;
           this.ehrId = ehrId;
           this.audit = audit;
           this.versions = versions;
    }


    /**
     * Gets the sessionId value for this CommitContribution.
     * 
     * @return sessionId
     */
    public java.lang.String getSessionId() {
        return sessionId;
    }


    /**
     * Sets the sessionId value for this CommitContribution.
     * 
     * @param sessionId
     */
    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }


    /**
     * Gets the ehrId value for this CommitContribution.
     * 
     * @return ehrId
     */
    public java.lang.String getEhrId() {
        return ehrId;
    }


    /**
     * Sets the ehrId value for this CommitContribution.
     * 
     * @param ehrId
     */
    public void setEhrId(java.lang.String ehrId) {
        this.ehrId = ehrId;
    }


    /**
     * Gets the audit value for this CommitContribution.
     * 
     * @return audit
     */
    public org.openehr.schemas.v1.AUDIT_DETAILS getAudit() {
        return audit;
    }


    /**
     * Sets the audit value for this CommitContribution.
     * 
     * @param audit
     */
    public void setAudit(org.openehr.schemas.v1.AUDIT_DETAILS audit) {
        this.audit = audit;
    }


    /**
     * Gets the versions value for this CommitContribution.
     * 
     * @return versions
     */
    public org.openehr.schemas.v1.ORIGINAL_VERSION[] getVersions() {
        return versions;
    }


    /**
     * Sets the versions value for this CommitContribution.
     * 
     * @param versions
     */
    public void setVersions(org.openehr.schemas.v1.ORIGINAL_VERSION[] versions) {
        this.versions = versions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CommitContribution)) return false;
        CommitContribution other = (CommitContribution) obj;
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
            ((this.ehrId==null && other.getEhrId()==null) || 
             (this.ehrId!=null &&
              this.ehrId.equals(other.getEhrId()))) &&
            ((this.audit==null && other.getAudit()==null) || 
             (this.audit!=null &&
              this.audit.equals(other.getAudit()))) &&
            ((this.versions==null && other.getVersions()==null) || 
             (this.versions!=null &&
              java.util.Arrays.equals(this.versions, other.getVersions())));
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
        if (getEhrId() != null) {
            _hashCode += getEhrId().hashCode();
        }
        if (getAudit() != null) {
            _hashCode += getAudit().hashCode();
        }
        if (getVersions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVersions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVersions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommitContribution.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">CommitContribution"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "SessionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ehrId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "EhrId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("audit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "Audit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "AUDIT_DETAILS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "Versions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "ORIGINAL_VERSION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "ORIGINAL_VERSION"));
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
