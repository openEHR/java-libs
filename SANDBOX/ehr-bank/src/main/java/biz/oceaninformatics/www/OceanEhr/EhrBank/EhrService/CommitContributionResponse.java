/**
 * CommitContributionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService;

public class CommitContributionResponse  implements java.io.Serializable {
    private biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.CommitStatus commitContributionResult;

    public CommitContributionResponse() {
    }

    public CommitContributionResponse(
           biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.CommitStatus commitContributionResult) {
           this.commitContributionResult = commitContributionResult;
    }


    /**
     * Gets the commitContributionResult value for this CommitContributionResponse.
     * 
     * @return commitContributionResult
     */
    public biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.CommitStatus getCommitContributionResult() {
        return commitContributionResult;
    }


    /**
     * Sets the commitContributionResult value for this CommitContributionResponse.
     * 
     * @param commitContributionResult
     */
    public void setCommitContributionResult(biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.CommitStatus commitContributionResult) {
        this.commitContributionResult = commitContributionResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CommitContributionResponse)) return false;
        CommitContributionResponse other = (CommitContributionResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.commitContributionResult==null && other.getCommitContributionResult()==null) || 
             (this.commitContributionResult!=null &&
              this.commitContributionResult.equals(other.getCommitContributionResult())));
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
        if (getCommitContributionResult() != null) {
            _hashCode += getCommitContributionResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CommitContributionResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", ">CommitContributionResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commitContributionResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "CommitContributionResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService", "CommitStatus"));
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
