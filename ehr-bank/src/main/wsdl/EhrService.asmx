<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService" xmlns:s1="http://schemas.openehr.org/v1" xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" targetNamespace="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">The web services provided by EhrBank Server</wsdl:documentation>
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService">
      <s:import namespace="http://schemas.openehr.org/v1" />
      <s:element name="CloseSession">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CloseSessionResponse">
        <s:complexType />
      </s:element>
      <s:element name="CloseTransaction">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Action" type="tns:ActionType" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:simpleType name="ActionType">
        <s:restriction base="s:string">
          <s:enumeration value="Commit" />
          <s:enumeration value="Rollback" />
        </s:restriction>
      </s:simpleType>
      <s:element name="CloseTransactionResponse">
        <s:complexType />
      </s:element>
      <s:element name="CommitContribution">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="EhrId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Audit" type="s1:AUDIT_DETAILS" />
            <s:element minOccurs="0" maxOccurs="1" name="Versions" type="tns:ArrayOfORIGINAL_VERSION" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfORIGINAL_VERSION">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="ORIGINAL_VERSION" nillable="true" type="s1:ORIGINAL_VERSION" />
        </s:sequence>
      </s:complexType>
      <s:element name="CommitContributionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="CommitContributionResult" type="tns:CommitStatus" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:simpleType name="CommitStatus">
        <s:restriction base="s:string">
          <s:enumeration value="Succeeded" />
          <s:enumeration value="Provisional" />
          <s:enumeration value="Fail" />
        </s:restriction>
      </s:simpleType>
      <s:element name="CreateEhr">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExternalId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Namespace" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="CreateEhrResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="CreateEhrResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FindEhrId">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExternalId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Namespace" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FindEhrIdResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FindEhrIdResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FindEhrIdNoNamespace">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExternalId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Namespace" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FindEhrIdNoNamespaceResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FindEhrIdNoNamespaceResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSubjectId">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ehrUid" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="subjectIdNamespace" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSubjectIdResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSubjectIdResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetSystemId">
        <s:complexType />
      </s:element>
      <s:element name="GetSystemIdResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetSystemIdResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="HasTransaction">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="HasTransactionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="HasTransactionResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="OpenSession">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="UserId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Credentials" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="OpenSessionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="OpenSessionResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="OpenTransaction">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="OpenTransactionResponse">
        <s:complexType />
      </s:element>
      <s:element name="ReleaseResult">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ReleaseResultResponse">
        <s:complexType />
      </s:element>
      <s:element name="RetreiveResults">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="MaxResults" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfAnyType">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="anyType" nillable="true" />
        </s:sequence>
      </s:complexType>
      <s:element name="RetreiveResultsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="RetreiveResultsResult" type="tns:ArrayOfAnyType" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="RunQuery">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="QueryStatement" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="RunQueryResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="RunQueryResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SeekResult">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="Offset" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="FromWhere" type="tns:OffsetPoint" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:simpleType name="OffsetPoint">
        <s:restriction base="s:string">
          <s:enumeration value="Start" />
          <s:enumeration value="Current" />
          <s:enumeration value="End" />
        </s:restriction>
      </s:simpleType>
      <s:element name="SeekResultResponse">
        <s:complexType />
      </s:element>
      <s:element name="SetEhrId">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="EhrId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ExternalId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Namespace" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SetEhrIdResponse">
        <s:complexType />
      </s:element>
      <s:element name="IsValidSession">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sessionId" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="IsValidSessionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="IsValidSessionResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetComposition">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="compositionVersionUid" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="includeOriginalContent" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCompositionResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" ref="s1:composition" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetOriginalContent">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="compositionVersionUid" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetOriginalContentResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetOriginalContentResult" type="s1:DV_ENCAPSULATED" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetOriginalMultimediaData">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="compositionVersionUid" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetOriginalMultimediaDataResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetOriginalMultimediaDataResult" type="s:base64Binary" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetOriginalParsableValue">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="compositionVersionUid" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetOriginalParsableValueResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetOriginalParsableValueResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="HasOriginalContent">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="sessionId" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="compositionVersionUid" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="HasOriginalContentResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="HasOriginalContentResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
    <s:schema elementFormDefault="qualified" targetNamespace="http://schemas.openehr.org/v1">
      <s:complexType name="AUDIT_DETAILS">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="system_id" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="committer" type="s1:PARTY_PROXY" />
          <s:element minOccurs="0" maxOccurs="1" name="time_committed" type="s1:DV_DATE_TIME" />
          <s:element minOccurs="0" maxOccurs="1" name="change_type" type="s1:DV_CODED_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="description" type="s1:DV_TEXT" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="PARTY_PROXY" abstract="true">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="external_ref" type="s1:PARTY_REF" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="PARTY_REF">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_REF" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="OBJECT_REF">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="id" type="s1:OBJECT_ID" />
          <s:element minOccurs="0" maxOccurs="1" name="namespace" type="s:token" />
          <s:element minOccurs="0" maxOccurs="1" name="type" type="s:token" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="OBJECT_ID" abstract="true">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="value" type="s:token" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="TERMINOLOGY_ID">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_ID" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="GENERIC_ID">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_ID">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="scheme" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="UID_BASED_ID">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_ID" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="OBJECT_VERSION_ID">
        <s:complexContent mixed="false">
          <s:extension base="s1:UID_BASED_ID" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="HIER_OBJECT_ID">
        <s:complexContent mixed="false">
          <s:extension base="s1:UID_BASED_ID" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ARCHETYPE_ID">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_ID" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="TEMPLATE_ID">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_ID" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ACCESS_GROUP_REF">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_REF" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="LOCATABLE_REF">
        <s:complexContent mixed="false">
          <s:extension base="s1:OBJECT_REF">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="path" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="PARTY_IDENTIFIED">
        <s:complexContent mixed="false">
          <s:extension base="s1:PARTY_PROXY">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="name" type="s:string" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="identifiers" type="s1:DV_IDENTIFIER" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_IDENTIFIER">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="issuer" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="assigner" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="id" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="type" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DATA_VALUE" abstract="true" />
      <s:complexType name="DV_TEXT">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="hyperlink" type="s1:DV_URI" />
              <s:element minOccurs="0" maxOccurs="1" name="formatting" type="s:string" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="mappings" type="s1:TERM_MAPPING" />
              <s:element minOccurs="0" maxOccurs="1" name="language" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="encoding" type="s1:CODE_PHRASE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_URI">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s:anyURI" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_EHR_URI">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_URI" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="TERM_MAPPING">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="match" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="purpose" type="s1:DV_CODED_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="target" type="s1:CODE_PHRASE" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DV_CODED_TEXT">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_TEXT">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="defining_code" type="s1:CODE_PHRASE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="CODE_PHRASE">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="terminology_id" type="s1:TERMINOLOGY_ID" />
          <s:element minOccurs="0" maxOccurs="1" name="code_string" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DV_PARAGRAPH">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:DV_TEXT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_ENCAPSULATED" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="charset" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="language" type="s1:CODE_PHRASE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_PARSABLE">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_ENCAPSULATED">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="formalism" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_MULTIMEDIA">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_ENCAPSULATED">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="alternate_text" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" name="uri" type="s1:DV_URI" />
              <s:element minOccurs="0" maxOccurs="1" name="data" type="s:base64Binary" />
              <s:element minOccurs="0" maxOccurs="1" name="media_type" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="compression_algorithm" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="integrity_check" type="s:base64Binary" />
              <s:element minOccurs="0" maxOccurs="1" name="integrity_check_algorithm" type="s1:CODE_PHRASE" />
              <s:element minOccurs="1" maxOccurs="1" name="size" type="s:int" />
              <s:element minOccurs="0" maxOccurs="1" name="thumbnail" type="s1:DV_MULTIMEDIA" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_TIME_SPECIFICATION" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s1:DV_PARSABLE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_PERIODIC_TIME_SPECIFICATION">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_TIME_SPECIFICATION" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_GENERAL_TIME_SPECIFICATION">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_TIME_SPECIFICATION" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_BOOLEAN">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="value" type="s:boolean" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_INTERVAL">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="lower" type="s1:DV_ORDERED" />
              <s:element minOccurs="0" maxOccurs="1" name="upper" type="s1:DV_ORDERED" />
              <s:element minOccurs="0" maxOccurs="1" name="lower_included" type="s:boolean" />
              <s:element minOccurs="0" maxOccurs="1" name="upper_included" type="s:boolean" />
              <s:element minOccurs="1" maxOccurs="1" name="lower_unbounded" type="s:boolean" />
              <s:element minOccurs="1" maxOccurs="1" name="upper_unbounded" type="s:boolean" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_ORDERED" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="normal_range" type="s1:DV_INTERVAL" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="other_reference_ranges" type="s1:REFERENCE_RANGE" />
              <s:element minOccurs="0" maxOccurs="1" name="normal_status" type="s1:CODE_PHRASE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="REFERENCE_RANGE">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="meaning" type="s1:DV_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="range" type="s1:DV_INTERVAL" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DV_ORDINAL">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_ORDERED">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="value" type="s:int" />
              <s:element minOccurs="0" maxOccurs="1" name="symbol" type="s1:DV_CODED_TEXT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_QUANTIFIED" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_ORDERED">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="magnitude_status" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_AMOUNT">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_QUANTIFIED">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="accuracy" type="s:float" />
              <s:element minOccurs="0" maxOccurs="1" name="accuracy_is_percent" type="s:boolean" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_COUNT">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_AMOUNT">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="magnitude" type="s:long" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_QUANTITY">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_AMOUNT">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="magnitude" type="s:double" />
              <s:element minOccurs="0" maxOccurs="1" name="units" type="s:string" />
              <s:element minOccurs="0" maxOccurs="1" default="-1" name="precision" type="s:int" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_PROPORTION">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_AMOUNT">
            <s:sequence>
              <s:element minOccurs="1" maxOccurs="1" name="numerator" type="s:float" />
              <s:element minOccurs="1" maxOccurs="1" name="denominator" type="s:float" />
              <s:element minOccurs="1" maxOccurs="1" name="type" type="s1:PROPORTION_KIND" />
              <s:element minOccurs="0" maxOccurs="1" default="-1" name="precision" type="s:int" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:simpleType name="PROPORTION_KIND">
        <s:restriction base="s:string">
          <s:enumeration value="0" />
          <s:enumeration value="1" />
          <s:enumeration value="2" />
          <s:enumeration value="3" />
          <s:enumeration value="4" />
        </s:restriction>
      </s:simpleType>
      <s:complexType name="DV_DURATION">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_AMOUNT">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_TEMPORAL">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_QUANTIFIED">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="accuracy" type="s1:DV_DURATION" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_DATE_TIME">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_TEMPORAL">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_DATE">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_TEMPORAL">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_TIME">
        <s:complexContent mixed="false">
          <s:extension base="s1:DV_TEMPORAL">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="DV_STATE">
        <s:complexContent mixed="false">
          <s:extension base="s1:DATA_VALUE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s1:DV_CODED_TEXT" />
              <s:element minOccurs="1" maxOccurs="1" name="is_terminal" type="s:boolean" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="PARTY_RELATED">
        <s:complexContent mixed="false">
          <s:extension base="s1:PARTY_IDENTIFIED">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="relationship" type="s1:DV_CODED_TEXT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="PARTY_SELF">
        <s:complexContent mixed="false">
          <s:extension base="s1:PARTY_PROXY" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ATTESTATION">
        <s:complexContent mixed="false">
          <s:extension base="s1:AUDIT_DETAILS">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="attested_view" type="s1:DV_MULTIMEDIA" />
              <s:element minOccurs="0" maxOccurs="1" name="proof" type="s:string" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:DV_EHR_URI" />
              <s:element minOccurs="0" maxOccurs="1" name="reason" type="s1:DV_TEXT" />
              <s:element minOccurs="1" maxOccurs="1" name="is_pending" type="s:boolean" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ORIGINAL_VERSION">
        <s:complexContent mixed="false">
          <s:extension base="s1:VERSION">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="uid" type="s1:OBJECT_VERSION_ID" />
              <s:element minOccurs="0" maxOccurs="1" name="data" />
              <s:element minOccurs="0" maxOccurs="1" name="preceding_version_uid" type="s1:OBJECT_VERSION_ID" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="other_input_version_uids" type="s1:OBJECT_VERSION_ID" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="attestations" type="s1:ATTESTATION" />
              <s:element minOccurs="0" maxOccurs="1" name="lifecycle_state" type="s1:DV_CODED_TEXT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="VERSION" abstract="true">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="contribution" type="s1:OBJECT_REF" />
          <s:element minOccurs="0" maxOccurs="1" name="commit_audit" type="s1:AUDIT_DETAILS" />
          <s:element minOccurs="0" maxOccurs="1" name="signature" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="IMPORTED_VERSION">
        <s:complexContent mixed="false">
          <s:extension base="s1:VERSION">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="item" type="s1:ORIGINAL_VERSION" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EVENT_CONTEXT">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="start_time" type="s1:DV_DATE_TIME" />
          <s:element minOccurs="0" maxOccurs="1" name="end_time" type="s1:DV_DATE_TIME" />
          <s:element minOccurs="0" maxOccurs="1" name="location" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="setting" type="s1:DV_CODED_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="other_context" type="s1:ITEM_STRUCTURE" />
          <s:element minOccurs="0" maxOccurs="1" name="health_care_facility" type="s1:PARTY_IDENTIFIED" />
          <s:element minOccurs="0" maxOccurs="unbounded" name="participations" type="s1:PARTICIPATION" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ITEM_STRUCTURE" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="LOCATABLE" abstract="true">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="name" type="s1:DV_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="uid" type="s1:UID_BASED_ID" />
          <s:element minOccurs="0" maxOccurs="unbounded" name="links" type="s1:LINK" />
          <s:element minOccurs="0" maxOccurs="1" name="archetype_details" type="s1:ARCHETYPED" />
          <s:element minOccurs="0" maxOccurs="1" name="feeder_audit" type="s1:FEEDER_AUDIT" />
          <s:element minOccurs="0" maxOccurs="1" name="archetype_node_id" type="s:string"/>
        </s:sequence>
        <!-- s:attribute name="archetype_node_id" type="s:string"/ -->
      </s:complexType>
      <s:complexType name="LINK">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="meaning" type="s1:DV_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="type" type="s1:DV_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="target" type="s1:DV_EHR_URI" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ARCHETYPED">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="archetype_id" type="s1:ARCHETYPE_ID" />
          <s:element minOccurs="0" maxOccurs="1" name="template_id" type="s1:TEMPLATE_ID" />
          <s:element minOccurs="0" maxOccurs="1" name="rm_version" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="FEEDER_AUDIT">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="originating_system_item_ids" type="s1:DV_IDENTIFIER" />
          <s:element minOccurs="0" maxOccurs="unbounded" name="feeder_system_item_ids" type="s1:DV_IDENTIFIER" />
          <s:element minOccurs="0" maxOccurs="1" name="original_content" type="s1:DV_ENCAPSULATED" />
          <s:element minOccurs="0" maxOccurs="1" name="originating_system_audit" type="s1:FEEDER_AUDIT_DETAILS" />
          <s:element minOccurs="0" maxOccurs="1" name="feeder_system_audit" type="s1:FEEDER_AUDIT_DETAILS" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="FEEDER_AUDIT_DETAILS">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="system_id" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="location" type="s1:PARTY_IDENTIFIED" />
          <s:element minOccurs="0" maxOccurs="1" name="provider" type="s1:PARTY_IDENTIFIED" />
          <s:element minOccurs="0" maxOccurs="1" name="subject" type="s1:PARTY_PROXY" />
          <s:element minOccurs="0" maxOccurs="1" name="time" type="s1:DV_DATE_TIME" />
          <s:element minOccurs="0" maxOccurs="1" name="version_id" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="COMPOSITION">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="language" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="territory" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="category" type="s1:DV_CODED_TEXT" />
              <s:element minOccurs="0" maxOccurs="1" name="composer" type="s1:PARTY_PROXY" />
              <s:element minOccurs="0" maxOccurs="1" name="context" type="s1:EVENT_CONTEXT" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="content" type="s1:CONTENT_ITEM" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="CONTENT_ITEM" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ENTRY" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:CONTENT_ITEM">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="language" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="encoding" type="s1:CODE_PHRASE" />
              <s:element minOccurs="0" maxOccurs="1" name="subject" type="s1:PARTY_PROXY" />
              <s:element minOccurs="0" maxOccurs="1" name="provider" type="s1:PARTY_PROXY" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="other_participations" type="s1:PARTICIPATION" />
              <s:element minOccurs="0" maxOccurs="1" name="work_flow_id" type="s1:OBJECT_REF" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="PARTICIPATION">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="function" type="s1:DV_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="performer" type="s1:PARTY_PROXY" />
          <s:element minOccurs="0" maxOccurs="1" name="time" type="s1:DV_INTERVAL" />
          <s:element minOccurs="0" maxOccurs="1" name="mode" type="s1:DV_CODED_TEXT" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ADMIN_ENTRY">
        <s:complexContent mixed="false">
          <s:extension base="s1:ENTRY">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="data" type="s1:ITEM_STRUCTURE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="CARE_ENTRY" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:ENTRY">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="protocol" type="s1:ITEM_STRUCTURE" />
              <s:element minOccurs="0" maxOccurs="1" name="guideline_id" type="s1:OBJECT_REF" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ACTION">
        <s:complexContent mixed="false">
          <s:extension base="s1:CARE_ENTRY">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="time" type="s1:DV_DATE_TIME" />
              <s:element minOccurs="0" maxOccurs="1" name="description" type="s1:ITEM_STRUCTURE" />
              <s:element minOccurs="0" maxOccurs="1" name="ism_transition" type="s1:ISM_TRANSITION" />
              <s:element minOccurs="0" maxOccurs="1" name="instruction_details" type="s1:INSTRUCTION_DETAILS" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ISM_TRANSITION">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="current_state" type="s1:DV_CODED_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="transition" type="s1:DV_CODED_TEXT" />
          <s:element minOccurs="0" maxOccurs="1" name="careflow_step" type="s1:DV_CODED_TEXT" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="INSTRUCTION_DETAILS">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="instruction_id" type="s1:LOCATABLE_REF" />
          <s:element minOccurs="0" maxOccurs="1" name="activity_id" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="wf_details" type="s1:ITEM_STRUCTURE" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="OBSERVATION">
        <s:complexContent mixed="false">
          <s:extension base="s1:CARE_ENTRY">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="data" type="s1:HISTORY" />
              <s:element minOccurs="0" maxOccurs="1" name="state" type="s1:HISTORY" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="HISTORY">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="origin" type="s1:DV_DATE_TIME" />
              <s:element minOccurs="0" maxOccurs="1" name="period" type="s1:DV_DURATION" />
              <s:element minOccurs="0" maxOccurs="1" name="duration" type="s1:DV_DURATION" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="events" type="s1:EVENT" />
              <s:element minOccurs="0" maxOccurs="1" name="summary" type="s1:ITEM_STRUCTURE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EVENT" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="time" type="s1:DV_DATE_TIME" />
              <s:element minOccurs="0" maxOccurs="1" name="data" type="s1:ITEM_STRUCTURE" />
              <s:element minOccurs="0" maxOccurs="1" name="state" type="s1:ITEM_STRUCTURE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="POINT_EVENT">
        <s:complexContent mixed="false">
          <s:extension base="s1:EVENT" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="INTERVAL_EVENT">
        <s:complexContent mixed="false">
          <s:extension base="s1:EVENT">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="width" type="s1:DV_DURATION" />
              <s:element minOccurs="0" maxOccurs="1" name="sample_count" type="s:int" />
              <s:element minOccurs="0" maxOccurs="1" name="math_function" type="s1:DV_CODED_TEXT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EVALUATION">
        <s:complexContent mixed="false">
          <s:extension base="s1:CARE_ENTRY">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="data" type="s1:ITEM_STRUCTURE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="INSTRUCTION">
        <s:complexContent mixed="false">
          <s:extension base="s1:CARE_ENTRY">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="narrative" type="s1:DV_TEXT" />
              <s:element minOccurs="0" maxOccurs="1" name="expiry_time" type="s1:DV_DATE_TIME" />
              <s:element minOccurs="0" maxOccurs="1" name="wf_definition" type="s1:DV_PARSABLE" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="activities" type="s1:ACTIVITY" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ACTIVITY">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="description" type="s1:ITEM_STRUCTURE" />
              <s:element minOccurs="0" maxOccurs="1" name="timing" type="s1:DV_PARSABLE" />
              <s:element minOccurs="0" maxOccurs="1" name="action_archetype_id" type="s:string" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="SECTION">
        <s:complexContent mixed="false">
          <s:extension base="s1:CONTENT_ITEM">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:CONTENT_ITEM" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="GENERIC_ENTRY">
        <s:complexContent mixed="false">
          <s:extension base="s1:CONTENT_ITEM">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="data" type="s1:ITEM_TREE" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ITEM_TREE">
        <s:complexContent mixed="false">
          <s:extension base="s1:ITEM_STRUCTURE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:ITEM" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ITEM" abstract="true">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE" />
        </s:complexContent>
      </s:complexType>
      <s:complexType name="CLUSTER">
        <s:complexContent mixed="false">
          <s:extension base="s1:ITEM">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:ITEM" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ELEMENT">
        <s:complexContent mixed="false">
          <s:extension base="s1:ITEM">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="value" type="s1:DATA_VALUE" />
              <s:element minOccurs="0" maxOccurs="1" name="null_flavour" type="s1:DV_CODED_TEXT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EXTRACT_REQUEST">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="extract_spec" type="s1:EXTRACT_SPEC" />
              <s:element minOccurs="0" maxOccurs="1" name="update_spec" type="s1:EXTRACT_UPDATE_SPEC" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EXTRACT_SPEC">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="extract_type" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="includes_multimedia" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="link_depth" type="s:int" />
          <s:element minOccurs="0" maxOccurs="unbounded" name="criteria" type="s1:DV_PARSABLE" />
          <s:element minOccurs="1" maxOccurs="1" name="includes_directory" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="directory_archetype" type="s1:ARCHETYPE_ID" />
          <s:element minOccurs="0" maxOccurs="1" name="other_details" type="s1:ITEM_STRUCTURE" />
          <s:element minOccurs="0" maxOccurs="1" name="version_spec" type="s1:EXTRACT_VERSION_SPEC" />
          <s:element minOccurs="0" maxOccurs="1" name="manifest" type="s1:ArrayOfEXTRACT_ENTITY_MANIFEST" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EXTRACT_VERSION_SPEC">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="include_all_versions" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="commit_time_interval" type="s1:DV_INTERVAL" />
          <s:element minOccurs="1" maxOccurs="1" name="includes_revision_history" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="includes_data" type="s:boolean" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfEXTRACT_ENTITY_MANIFEST">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="entities" type="s1:EXTRACT_ENTITY_MANIFEST" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EXTRACT_ENTITY_MANIFEST">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="item_list" type="s1:OBJECT_REF" />
          <s:element minOccurs="0" maxOccurs="1" name="entity_identifier" type="s1:EXTRACT_ENTITY_IDENTIFIER" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EXTRACT_ENTITY_IDENTIFIER">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="entity_id" type="s1:HIER_OBJECT_ID" />
          <s:element minOccurs="0" maxOccurs="1" name="subject" type="s1:PARTY_IDENTIFIED" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EXTRACT_UPDATE_SPEC">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="persist_in_server" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="repeat_period" type="s1:DV_INTERVAL" />
          <s:element minOccurs="0" maxOccurs="unbounded" name="trigger_events" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="send_changes_only" type="s:boolean" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EXTRACT">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="request_id" type="s1:OBJECT_REF" />
              <s:element minOccurs="0" maxOccurs="1" name="time_created" type="s1:DV_DATE_TIME" />
              <s:element minOccurs="0" maxOccurs="1" name="system_id" type="s1:HIER_OBJECT_ID" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="partitipations" type="s1:PARTICIPATION" />
              <s:element minOccurs="1" maxOccurs="1" name="sequence_nr" type="s:long" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="demographics" type="s1:X_VERSIONED_OBJECT" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="chapters" type="s1:EXTRACT_CHAPTER" />
              <s:element minOccurs="0" maxOccurs="1" name="specification" type="s1:EXTRACT_SPEC" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="X_VERSIONED_OBJECT">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="uid" type="s1:HIER_OBJECT_ID" />
          <s:element minOccurs="0" maxOccurs="1" name="owner_id" type="s1:OBJECT_REF" />
          <s:element minOccurs="0" maxOccurs="1" name="time_created" type="s1:DV_DATE_TIME" />
          <s:element minOccurs="1" maxOccurs="1" name="total_version_count" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="extract_version_count" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="revision_history" type="s1:ArrayOfREVISION_HISTORY_ITEM" />
          <s:element minOccurs="0" maxOccurs="unbounded" name="versions" type="s1:ORIGINAL_VERSION" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="ArrayOfREVISION_HISTORY_ITEM">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:REVISION_HISTORY_ITEM" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="REVISION_HISTORY_ITEM">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="version_id" type="s1:OBJECT_VERSION_ID" />
          <s:element minOccurs="0" maxOccurs="unbounded" name="audits" type="s1:AUDIT_DETAILS" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="EXTRACT_CHAPTER">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="directory" type="s1:EXTRACT_FOLDER" />
              <s:element minOccurs="0" maxOccurs="1" name="content" type="s1:EXTRACT_CONTENT" />
              <s:element minOccurs="0" maxOccurs="1" name="entity_identifier" type="s1:EXTRACT_ENTITY_IDENTIFIER" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EXTRACT_FOLDER">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:OBJECT_REF" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="folders" type="s1:EXTRACT_FOLDER" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EXTRACT_CONTENT" abstract="true" />
      <s:complexType name="EHR_EXTRACT_CONTENT">
        <s:complexContent mixed="false">
          <s:extension base="s1:EXTRACT_CONTENT">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="compositions" type="s1:EXTRACT_ITEM" />
              <s:element minOccurs="0" maxOccurs="1" name="directory" type="s1:EXTRACT_ITEM" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="EXTRACT_ITEM">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="uid" type="s1:HIER_OBJECT_ID" />
          <s:element minOccurs="1" maxOccurs="1" name="is_primary" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="is_changed" type="s:boolean" />
          <s:element minOccurs="1" maxOccurs="1" name="is_masked" type="s:boolean" />
          <s:element minOccurs="0" maxOccurs="1" name="original_path" type="s1:DV_URI" />
          <s:element minOccurs="0" maxOccurs="1" name="item" type="s1:X_VERSIONED_OBJECT" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="FOLDER">
        <s:complexContent mixed="false">
          <s:extension base="s1:LOCATABLE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="folders" type="s1:FOLDER" />
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:OBJECT_REF" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ITEM_LIST">
        <s:complexContent mixed="false">
          <s:extension base="s1:ITEM_STRUCTURE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="items" type="s1:ELEMENT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ITEM_SINGLE">
        <s:complexContent mixed="false">
          <s:extension base="s1:ITEM_STRUCTURE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="1" name="item" type="s1:ELEMENT" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:complexType name="ITEM_TABLE">
        <s:complexContent mixed="false">
          <s:extension base="s1:ITEM_STRUCTURE">
            <s:sequence>
              <s:element minOccurs="0" maxOccurs="unbounded" name="rows" type="s1:CLUSTER" />
            </s:sequence>
          </s:extension>
        </s:complexContent>
      </s:complexType>
      <s:element name="composition" type="s1:COMPOSITION" />
    </s:schema>
  </wsdl:types>
  <wsdl:message name="CloseSessionSoapIn">
    <wsdl:part name="parameters" element="tns:CloseSession" />
  </wsdl:message>
  <wsdl:message name="CloseSessionSoapOut">
    <wsdl:part name="parameters" element="tns:CloseSessionResponse" />
  </wsdl:message>
  <wsdl:message name="CloseTransactionSoapIn">
    <wsdl:part name="parameters" element="tns:CloseTransaction" />
  </wsdl:message>
  <wsdl:message name="CloseTransactionSoapOut">
    <wsdl:part name="parameters" element="tns:CloseTransactionResponse" />
  </wsdl:message>
  <wsdl:message name="CommitContributionSoapIn">
    <wsdl:part name="parameters" element="tns:CommitContribution" />
  </wsdl:message>
  <wsdl:message name="CommitContributionSoapOut">
    <wsdl:part name="parameters" element="tns:CommitContributionResponse" />
  </wsdl:message>
  <wsdl:message name="CreateEhrSoapIn">
    <wsdl:part name="parameters" element="tns:CreateEhr" />
  </wsdl:message>
  <wsdl:message name="CreateEhrSoapOut">
    <wsdl:part name="parameters" element="tns:CreateEhrResponse" />
  </wsdl:message>
  <wsdl:message name="FindEhrIdSoapIn">
    <wsdl:part name="parameters" element="tns:FindEhrId" />
  </wsdl:message>
  <wsdl:message name="FindEhrIdSoapOut">
    <wsdl:part name="parameters" element="tns:FindEhrIdResponse" />
  </wsdl:message>
  <wsdl:message name="FindEhrIdNoNamespaceSoapIn">
    <wsdl:part name="parameters" element="tns:FindEhrIdNoNamespace" />
  </wsdl:message>
  <wsdl:message name="FindEhrIdNoNamespaceSoapOut">
    <wsdl:part name="parameters" element="tns:FindEhrIdNoNamespaceResponse" />
  </wsdl:message>
  <wsdl:message name="GetSubjectIdSoapIn">
    <wsdl:part name="parameters" element="tns:GetSubjectId" />
  </wsdl:message>
  <wsdl:message name="GetSubjectIdSoapOut">
    <wsdl:part name="parameters" element="tns:GetSubjectIdResponse" />
  </wsdl:message>
  <wsdl:message name="GetSystemIdSoapIn">
    <wsdl:part name="parameters" element="tns:GetSystemId" />
  </wsdl:message>
  <wsdl:message name="GetSystemIdSoapOut">
    <wsdl:part name="parameters" element="tns:GetSystemIdResponse" />
  </wsdl:message>
  <wsdl:message name="HasTransactionSoapIn">
    <wsdl:part name="parameters" element="tns:HasTransaction" />
  </wsdl:message>
  <wsdl:message name="HasTransactionSoapOut">
    <wsdl:part name="parameters" element="tns:HasTransactionResponse" />
  </wsdl:message>
  <wsdl:message name="OpenSessionSoapIn">
    <wsdl:part name="parameters" element="tns:OpenSession" />
  </wsdl:message>
  <wsdl:message name="OpenSessionSoapOut">
    <wsdl:part name="parameters" element="tns:OpenSessionResponse" />
  </wsdl:message>
  <wsdl:message name="OpenTransactionSoapIn">
    <wsdl:part name="parameters" element="tns:OpenTransaction" />
  </wsdl:message>
  <wsdl:message name="OpenTransactionSoapOut">
    <wsdl:part name="parameters" element="tns:OpenTransactionResponse" />
  </wsdl:message>
  <wsdl:message name="ReleaseResultSoapIn">
    <wsdl:part name="parameters" element="tns:ReleaseResult" />
  </wsdl:message>
  <wsdl:message name="ReleaseResultSoapOut">
    <wsdl:part name="parameters" element="tns:ReleaseResultResponse" />
  </wsdl:message>
  <wsdl:message name="RetreiveResultsSoapIn">
    <wsdl:part name="parameters" element="tns:RetreiveResults" />
  </wsdl:message>
  <wsdl:message name="RetreiveResultsSoapOut">
    <wsdl:part name="parameters" element="tns:RetreiveResultsResponse" />
  </wsdl:message>
  <wsdl:message name="RunQuerySoapIn">
    <wsdl:part name="parameters" element="tns:RunQuery" />
  </wsdl:message>
  <wsdl:message name="RunQuerySoapOut">
    <wsdl:part name="parameters" element="tns:RunQueryResponse" />
  </wsdl:message>
  <wsdl:message name="SeekResultSoapIn">
    <wsdl:part name="parameters" element="tns:SeekResult" />
  </wsdl:message>
  <wsdl:message name="SeekResultSoapOut">
    <wsdl:part name="parameters" element="tns:SeekResultResponse" />
  </wsdl:message>
  <wsdl:message name="SetEhrIdSoapIn">
    <wsdl:part name="parameters" element="tns:SetEhrId" />
  </wsdl:message>
  <wsdl:message name="SetEhrIdSoapOut">
    <wsdl:part name="parameters" element="tns:SetEhrIdResponse" />
  </wsdl:message>
  <wsdl:message name="IsValidSessionSoapIn">
    <wsdl:part name="parameters" element="tns:IsValidSession" />
  </wsdl:message>
  <wsdl:message name="IsValidSessionSoapOut">
    <wsdl:part name="parameters" element="tns:IsValidSessionResponse" />
  </wsdl:message>
  <wsdl:message name="GetCompositionSoapIn">
    <wsdl:part name="parameters" element="tns:GetComposition" />
  </wsdl:message>
  <wsdl:message name="GetCompositionSoapOut">
    <wsdl:part name="parameters" element="tns:GetCompositionResponse" />
  </wsdl:message>
  <wsdl:message name="GetOriginalContentSoapIn">
    <wsdl:part name="parameters" element="tns:GetOriginalContent" />
  </wsdl:message>
  <wsdl:message name="GetOriginalContentSoapOut">
    <wsdl:part name="parameters" element="tns:GetOriginalContentResponse" />
  </wsdl:message>
  <wsdl:message name="GetOriginalMultimediaDataSoapIn">
    <wsdl:part name="parameters" element="tns:GetOriginalMultimediaData" />
  </wsdl:message>
  <wsdl:message name="GetOriginalMultimediaDataSoapOut">
    <wsdl:part name="parameters" element="tns:GetOriginalMultimediaDataResponse" />
  </wsdl:message>
  <wsdl:message name="GetOriginalParsableValueSoapIn">
    <wsdl:part name="parameters" element="tns:GetOriginalParsableValue" />
  </wsdl:message>
  <wsdl:message name="GetOriginalParsableValueSoapOut">
    <wsdl:part name="parameters" element="tns:GetOriginalParsableValueResponse" />
  </wsdl:message>
  <wsdl:message name="HasOriginalContentSoapIn">
    <wsdl:part name="parameters" element="tns:HasOriginalContent" />
  </wsdl:message>
  <wsdl:message name="HasOriginalContentSoapOut">
    <wsdl:part name="parameters" element="tns:HasOriginalContentResponse" />
  </wsdl:message>
  <wsdl:portType name="EhrServiceSoap">
    <wsdl:operation name="CloseSession">
      <wsdl:input message="tns:CloseSessionSoapIn" />
      <wsdl:output message="tns:CloseSessionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CloseTransaction">
      <wsdl:input message="tns:CloseTransactionSoapIn" />
      <wsdl:output message="tns:CloseTransactionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CommitContribution">
      <wsdl:input message="tns:CommitContributionSoapIn" />
      <wsdl:output message="tns:CommitContributionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="CreateEhr">
      <wsdl:input message="tns:CreateEhrSoapIn" />
      <wsdl:output message="tns:CreateEhrSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="FindEhrId">
      <wsdl:input message="tns:FindEhrIdSoapIn" />
      <wsdl:output message="tns:FindEhrIdSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="FindEhrIdNoNamespace">
      <wsdl:input message="tns:FindEhrIdNoNamespaceSoapIn" />
      <wsdl:output message="tns:FindEhrIdNoNamespaceSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSubjectId">
      <wsdl:input message="tns:GetSubjectIdSoapIn" />
      <wsdl:output message="tns:GetSubjectIdSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetSystemId">
      <wsdl:input message="tns:GetSystemIdSoapIn" />
      <wsdl:output message="tns:GetSystemIdSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="HasTransaction">
      <wsdl:input message="tns:HasTransactionSoapIn" />
      <wsdl:output message="tns:HasTransactionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="OpenSession">
      <wsdl:input message="tns:OpenSessionSoapIn" />
      <wsdl:output message="tns:OpenSessionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="OpenTransaction">
      <wsdl:input message="tns:OpenTransactionSoapIn" />
      <wsdl:output message="tns:OpenTransactionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ReleaseResult">
      <wsdl:input message="tns:ReleaseResultSoapIn" />
      <wsdl:output message="tns:ReleaseResultSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="RetreiveResults">
      <wsdl:input message="tns:RetreiveResultsSoapIn" />
      <wsdl:output message="tns:RetreiveResultsSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="RunQuery">
      <wsdl:input message="tns:RunQuerySoapIn" />
      <wsdl:output message="tns:RunQuerySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SeekResult">
      <wsdl:input message="tns:SeekResultSoapIn" />
      <wsdl:output message="tns:SeekResultSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SetEhrId">
      <wsdl:input message="tns:SetEhrIdSoapIn" />
      <wsdl:output message="tns:SetEhrIdSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="IsValidSession">
      <wsdl:input message="tns:IsValidSessionSoapIn" />
      <wsdl:output message="tns:IsValidSessionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetComposition">
      <wsdl:input message="tns:GetCompositionSoapIn" />
      <wsdl:output message="tns:GetCompositionSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetOriginalContent">
      <wsdl:input message="tns:GetOriginalContentSoapIn" />
      <wsdl:output message="tns:GetOriginalContentSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetOriginalMultimediaData">
      <wsdl:input message="tns:GetOriginalMultimediaDataSoapIn" />
      <wsdl:output message="tns:GetOriginalMultimediaDataSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetOriginalParsableValue">
      <wsdl:input message="tns:GetOriginalParsableValueSoapIn" />
      <wsdl:output message="tns:GetOriginalParsableValueSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="HasOriginalContent">
      <wsdl:input message="tns:HasOriginalContentSoapIn" />
      <wsdl:output message="tns:HasOriginalContentSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="EhrServiceSoap" type="tns:EhrServiceSoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="CloseSession">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CloseSession" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CloseTransaction">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CloseTransaction" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CommitContribution">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CommitContribution" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateEhr">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CreateEhr" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FindEhrId">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/FindEhrId" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FindEhrIdNoNamespace">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/FindEhrIdNoNamespace" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSubjectId">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetSubjectId" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSystemId">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetSystemId" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="HasTransaction">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/HasTransaction" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="OpenSession">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/OpenSession" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="OpenTransaction">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/OpenTransaction" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ReleaseResult">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/ReleaseResult" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="RetreiveResults">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/RetreiveResults" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="RunQuery">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/RunQuery" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SeekResult">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/SeekResult" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SetEhrId">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/SetEhrId" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IsValidSession">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/IsValidSession" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetComposition">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetComposition" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetOriginalContent">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetOriginalContent" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetOriginalMultimediaData">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetOriginalMultimediaData" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetOriginalParsableValue">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetOriginalParsableValue" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="HasOriginalContent">
      <soap:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/HasOriginalContent" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="EhrServiceSoap12" type="tns:EhrServiceSoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="CloseSession">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CloseSession" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CloseTransaction">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CloseTransaction" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CommitContribution">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CommitContribution" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="CreateEhr">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/CreateEhr" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FindEhrId">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/FindEhrId" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FindEhrIdNoNamespace">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/FindEhrIdNoNamespace" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSubjectId">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetSubjectId" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetSystemId">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetSystemId" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="HasTransaction">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/HasTransaction" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="OpenSession">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/OpenSession" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="OpenTransaction">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/OpenTransaction" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ReleaseResult">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/ReleaseResult" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="RetreiveResults">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/RetreiveResults" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="RunQuery">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/RunQuery" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SeekResult">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/SeekResult" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SetEhrId">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/SetEhrId" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="IsValidSession">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/IsValidSession" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetComposition">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetComposition" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetOriginalContent">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetOriginalContent" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetOriginalMultimediaData">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetOriginalMultimediaData" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetOriginalParsableValue">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/GetOriginalParsableValue" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="HasOriginalContent">
      <soap12:operation soapAction="http://www.oceaninformatics.biz/OceanEhr/EhrBank/EhrService/HasOriginalContent" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="EhrService">
    <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">The web services provided by EhrBank Server</wsdl:documentation>
    <wsdl:port name="EhrServiceSoap" binding="tns:EhrServiceSoap">
      <soap:address location="http://demo.oceanehr.com/EhrBank13/EhrService.asmx" />
    </wsdl:port>
    <wsdl:port name="EhrServiceSoap12" binding="tns:EhrServiceSoap12">
      <soap12:address location="http://demo.oceanehr.com/EhrBank13/EhrService.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>