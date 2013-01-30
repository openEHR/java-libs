/**
 * EXTRACT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class EXTRACT  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.OBJECT_REF request_id;

    private org.openehr.schemas.v1.DV_DATE_TIME time_created;

    private org.openehr.schemas.v1.HIER_OBJECT_ID system_id;

    private org.openehr.schemas.v1.PARTICIPATION[] partitipations;

    private long sequence_nr;

    private org.openehr.schemas.v1.X_VERSIONED_OBJECT[] demographics;

    private org.openehr.schemas.v1.EXTRACT_CHAPTER[] chapters;

    private org.openehr.schemas.v1.EXTRACT_SPEC specification;

    public EXTRACT() {
    }

    public EXTRACT(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.OBJECT_REF request_id,
           org.openehr.schemas.v1.DV_DATE_TIME time_created,
           org.openehr.schemas.v1.HIER_OBJECT_ID system_id,
           org.openehr.schemas.v1.PARTICIPATION[] partitipations,
           long sequence_nr,
           org.openehr.schemas.v1.X_VERSIONED_OBJECT[] demographics,
           org.openehr.schemas.v1.EXTRACT_CHAPTER[] chapters,
           org.openehr.schemas.v1.EXTRACT_SPEC specification) {
        super(
            name,
            uid,
            links,
            archetype_details,
            feeder_audit, 
            archetype_node_id);
        this.request_id = request_id;
        this.time_created = time_created;
        this.system_id = system_id;
        this.partitipations = partitipations;
        this.sequence_nr = sequence_nr;
        this.demographics = demographics;
        this.chapters = chapters;
        this.specification = specification;
    }


    /**
     * Gets the request_id value for this EXTRACT.
     * 
     * @return request_id
     */
    public org.openehr.schemas.v1.OBJECT_REF getRequest_id() {
        return request_id;
    }


    /**
     * Sets the request_id value for this EXTRACT.
     * 
     * @param request_id
     */
    public void setRequest_id(org.openehr.schemas.v1.OBJECT_REF request_id) {
        this.request_id = request_id;
    }


    /**
     * Gets the time_created value for this EXTRACT.
     * 
     * @return time_created
     */
    public org.openehr.schemas.v1.DV_DATE_TIME getTime_created() {
        return time_created;
    }


    /**
     * Sets the time_created value for this EXTRACT.
     * 
     * @param time_created
     */
    public void setTime_created(org.openehr.schemas.v1.DV_DATE_TIME time_created) {
        this.time_created = time_created;
    }


    /**
     * Gets the system_id value for this EXTRACT.
     * 
     * @return system_id
     */
    public org.openehr.schemas.v1.HIER_OBJECT_ID getSystem_id() {
        return system_id;
    }


    /**
     * Sets the system_id value for this EXTRACT.
     * 
     * @param system_id
     */
    public void setSystem_id(org.openehr.schemas.v1.HIER_OBJECT_ID system_id) {
        this.system_id = system_id;
    }


    /**
     * Gets the partitipations value for this EXTRACT.
     * 
     * @return partitipations
     */
    public org.openehr.schemas.v1.PARTICIPATION[] getPartitipations() {
        return partitipations;
    }


    /**
     * Sets the partitipations value for this EXTRACT.
     * 
     * @param partitipations
     */
    public void setPartitipations(org.openehr.schemas.v1.PARTICIPATION[] partitipations) {
        this.partitipations = partitipations;
    }

    public org.openehr.schemas.v1.PARTICIPATION getPartitipations(int i) {
        return this.partitipations[i];
    }

    public void setPartitipations(int i, org.openehr.schemas.v1.PARTICIPATION _value) {
        this.partitipations[i] = _value;
    }


    /**
     * Gets the sequence_nr value for this EXTRACT.
     * 
     * @return sequence_nr
     */
    public long getSequence_nr() {
        return sequence_nr;
    }


    /**
     * Sets the sequence_nr value for this EXTRACT.
     * 
     * @param sequence_nr
     */
    public void setSequence_nr(long sequence_nr) {
        this.sequence_nr = sequence_nr;
    }


    /**
     * Gets the demographics value for this EXTRACT.
     * 
     * @return demographics
     */
    public org.openehr.schemas.v1.X_VERSIONED_OBJECT[] getDemographics() {
        return demographics;
    }


    /**
     * Sets the demographics value for this EXTRACT.
     * 
     * @param demographics
     */
    public void setDemographics(org.openehr.schemas.v1.X_VERSIONED_OBJECT[] demographics) {
        this.demographics = demographics;
    }

    public org.openehr.schemas.v1.X_VERSIONED_OBJECT getDemographics(int i) {
        return this.demographics[i];
    }

    public void setDemographics(int i, org.openehr.schemas.v1.X_VERSIONED_OBJECT _value) {
        this.demographics[i] = _value;
    }


    /**
     * Gets the chapters value for this EXTRACT.
     * 
     * @return chapters
     */
    public org.openehr.schemas.v1.EXTRACT_CHAPTER[] getChapters() {
        return chapters;
    }


    /**
     * Sets the chapters value for this EXTRACT.
     * 
     * @param chapters
     */
    public void setChapters(org.openehr.schemas.v1.EXTRACT_CHAPTER[] chapters) {
        this.chapters = chapters;
    }

    public org.openehr.schemas.v1.EXTRACT_CHAPTER getChapters(int i) {
        return this.chapters[i];
    }

    public void setChapters(int i, org.openehr.schemas.v1.EXTRACT_CHAPTER _value) {
        this.chapters[i] = _value;
    }


    /**
     * Gets the specification value for this EXTRACT.
     * 
     * @return specification
     */
    public org.openehr.schemas.v1.EXTRACT_SPEC getSpecification() {
        return specification;
    }


    /**
     * Sets the specification value for this EXTRACT.
     * 
     * @param specification
     */
    public void setSpecification(org.openehr.schemas.v1.EXTRACT_SPEC specification) {
        this.specification = specification;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EXTRACT)) return false;
        EXTRACT other = (EXTRACT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.request_id==null && other.getRequest_id()==null) || 
             (this.request_id!=null &&
              this.request_id.equals(other.getRequest_id()))) &&
            ((this.time_created==null && other.getTime_created()==null) || 
             (this.time_created!=null &&
              this.time_created.equals(other.getTime_created()))) &&
            ((this.system_id==null && other.getSystem_id()==null) || 
             (this.system_id!=null &&
              this.system_id.equals(other.getSystem_id()))) &&
            ((this.partitipations==null && other.getPartitipations()==null) || 
             (this.partitipations!=null &&
              java.util.Arrays.equals(this.partitipations, other.getPartitipations()))) &&
            this.sequence_nr == other.getSequence_nr() &&
            ((this.demographics==null && other.getDemographics()==null) || 
             (this.demographics!=null &&
              java.util.Arrays.equals(this.demographics, other.getDemographics()))) &&
            ((this.chapters==null && other.getChapters()==null) || 
             (this.chapters!=null &&
              java.util.Arrays.equals(this.chapters, other.getChapters()))) &&
            ((this.specification==null && other.getSpecification()==null) || 
             (this.specification!=null &&
              this.specification.equals(other.getSpecification())));
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
        if (getRequest_id() != null) {
            _hashCode += getRequest_id().hashCode();
        }
        if (getTime_created() != null) {
            _hashCode += getTime_created().hashCode();
        }
        if (getSystem_id() != null) {
            _hashCode += getSystem_id().hashCode();
        }
        if (getPartitipations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPartitipations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPartitipations(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getSequence_nr()).hashCode();
        if (getDemographics() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDemographics());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDemographics(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getChapters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChapters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChapters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSpecification() != null) {
            _hashCode += getSpecification().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EXTRACT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("request_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "request_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "OBJECT_REF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_created");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "time_created"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_DATE_TIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("system_id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "system_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "HIER_OBJECT_ID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partitipations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "partitipations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTICIPATION"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequence_nr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "sequence_nr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("demographics");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "demographics"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "X_VERSIONED_OBJECT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chapters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "chapters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_CHAPTER"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("specification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "specification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EXTRACT_SPEC"));
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
