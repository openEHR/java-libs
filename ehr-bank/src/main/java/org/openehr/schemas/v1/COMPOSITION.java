/**
 * COMPOSITION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.openehr.schemas.v1;

public class COMPOSITION  extends org.openehr.schemas.v1.LOCATABLE  implements java.io.Serializable {
    private org.openehr.schemas.v1.CODE_PHRASE language;

    private org.openehr.schemas.v1.CODE_PHRASE territory;

    private org.openehr.schemas.v1.DV_CODED_TEXT category;

    private org.openehr.schemas.v1.PARTY_PROXY composer;

    private org.openehr.schemas.v1.EVENT_CONTEXT context;

    private org.openehr.schemas.v1.CONTENT_ITEM[] content;

    public COMPOSITION() {
    }

    public COMPOSITION(
           java.lang.String archetype_node_id,
           org.openehr.schemas.v1.DV_TEXT name,
           org.openehr.schemas.v1.UID_BASED_ID uid,
           org.openehr.schemas.v1.LINK[] links,
           org.openehr.schemas.v1.ARCHETYPED archetype_details,
           org.openehr.schemas.v1.FEEDER_AUDIT feeder_audit,
           org.openehr.schemas.v1.CODE_PHRASE language,
           org.openehr.schemas.v1.CODE_PHRASE territory,
           org.openehr.schemas.v1.DV_CODED_TEXT category,
           org.openehr.schemas.v1.PARTY_PROXY composer,
           org.openehr.schemas.v1.EVENT_CONTEXT context,
           org.openehr.schemas.v1.CONTENT_ITEM[] content) {
        super(        		
            name,
            uid,
            links,
            archetype_details,
            feeder_audit,
            archetype_node_id);
        this.language = language;
        this.territory = territory;
        this.category = category;
        this.composer = composer;
        this.context = context;
        this.content = content;
    }


    /**
     * Gets the language value for this COMPOSITION.
     * 
     * @return language
     */
    public org.openehr.schemas.v1.CODE_PHRASE getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this COMPOSITION.
     * 
     * @param language
     */
    public void setLanguage(org.openehr.schemas.v1.CODE_PHRASE language) {
        this.language = language;
    }


    /**
     * Gets the territory value for this COMPOSITION.
     * 
     * @return territory
     */
    public org.openehr.schemas.v1.CODE_PHRASE getTerritory() {
        return territory;
    }


    /**
     * Sets the territory value for this COMPOSITION.
     * 
     * @param territory
     */
    public void setTerritory(org.openehr.schemas.v1.CODE_PHRASE territory) {
        this.territory = territory;
    }


    /**
     * Gets the category value for this COMPOSITION.
     * 
     * @return category
     */
    public org.openehr.schemas.v1.DV_CODED_TEXT getCategory() {
        return category;
    }


    /**
     * Sets the category value for this COMPOSITION.
     * 
     * @param category
     */
    public void setCategory(org.openehr.schemas.v1.DV_CODED_TEXT category) {
        this.category = category;
    }


    /**
     * Gets the composer value for this COMPOSITION.
     * 
     * @return composer
     */
    public org.openehr.schemas.v1.PARTY_PROXY getComposer() {
        return composer;
    }


    /**
     * Sets the composer value for this COMPOSITION.
     * 
     * @param composer
     */
    public void setComposer(org.openehr.schemas.v1.PARTY_PROXY composer) {
        this.composer = composer;
    }


    /**
     * Gets the context value for this COMPOSITION.
     * 
     * @return context
     */
    public org.openehr.schemas.v1.EVENT_CONTEXT getContext() {
        return context;
    }


    /**
     * Sets the context value for this COMPOSITION.
     * 
     * @param context
     */
    public void setContext(org.openehr.schemas.v1.EVENT_CONTEXT context) {
        this.context = context;
    }


    /**
     * Gets the content value for this COMPOSITION.
     * 
     * @return content
     */
    public org.openehr.schemas.v1.CONTENT_ITEM[] getContent() {
        return content;
    }


    /**
     * Sets the content value for this COMPOSITION.
     * 
     * @param content
     */
    public void setContent(org.openehr.schemas.v1.CONTENT_ITEM[] content) {
        this.content = content;
    }

    public org.openehr.schemas.v1.CONTENT_ITEM getContent(int i) {
        return this.content[i];
    }

    public void setContent(int i, org.openehr.schemas.v1.CONTENT_ITEM _value) {
        this.content[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof COMPOSITION)) return false;
        COMPOSITION other = (COMPOSITION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.territory==null && other.getTerritory()==null) || 
             (this.territory!=null &&
              this.territory.equals(other.getTerritory()))) &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.composer==null && other.getComposer()==null) || 
             (this.composer!=null &&
              this.composer.equals(other.getComposer()))) &&
            ((this.context==null && other.getContext()==null) || 
             (this.context!=null &&
              this.context.equals(other.getContext()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              java.util.Arrays.equals(this.content, other.getContent())));
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
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getTerritory() != null) {
            _hashCode += getTerritory().hashCode();
        }
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getComposer() != null) {
            _hashCode += getComposer().hashCode();
        }
        if (getContext() != null) {
            _hashCode += getContext().hashCode();
        }
        if (getContent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContent(), i);
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
        new org.apache.axis.description.TypeDesc(COMPOSITION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "COMPOSITION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("territory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "territory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CODE_PHRASE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "DV_CODED_TEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("composer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "composer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "PARTY_PROXY"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("context");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "context"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "EVENT_CONTEXT"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.openehr.org/v1", "CONTENT_ITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
