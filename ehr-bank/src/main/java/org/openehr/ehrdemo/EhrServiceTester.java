package org.openehr.ehrdemo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import javax.xml.namespace.*;

import org.apache.log4j.Logger;
import org.openehr.rm.common.changecontrol.*;
import org.openehr.rm.common.generic.*;
import org.openehr.rm.composition.*;
import org.openehr.rm.datatypes.quantity.datetime.*;
import org.openehr.rm.datatypes.text.*;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.support.terminology.*;
import org.openehr.schemas.v1.*;

import biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.*;

import org.apache.axis.message.*;
import org.apache.axis.encoding.*;

import org.apache.commons.io.FileUtils;

public class EhrServiceTester {

	public static void main(String[] args) throws Exception {
		
		//createVersions();
		
		commitContribution();
		
		//loadComposition();
	}	
	
	private static void createEHR() throws Exception {
		// Make a service
		EhrService service = new EhrServiceLocator();

		log.info("service locator initialized..");

		EhrServiceSoap port = service.getEhrServiceSoap();	

		log.info("port opened..");

		// whatever userId and creditials
		String sessionId = port.openSession("guest", "guest");

		log.info("session opened with id: " + sessionId);

		String externalId = "1912121212";
		String namespace = "local";

		String ehrId = port.findEhrId(sessionId, externalId, namespace);
		if (ehrId == null) {
			ehrId = port.createEhr(sessionId, externalId, namespace);
			log.info("ehr created with id: " + ehrId);
		} else {
			log.info("ehr found with id: " + ehrId);
		}

		String subjectId = port.getSubjectId(sessionId, ehrId, namespace);

		log.info("subjectId of the ehr: " + subjectId);
	}
	
	private static void commitContribution() throws Exception {
		// Make a service
		EhrService service = new EhrServiceLocator();

		log.info("service locator initialized..");

		EhrServiceSoap port = service.getEhrServiceSoap();	

		log.info("port opened..");

		// whatever userId and creditials
		String sessionId = port.openSession("guest", "guest");

		log.info("session opened with id: " + sessionId);

		String externalId = "1912121212";
		String namespace = "local";

		String ehrId = port.findEhrId(sessionId, externalId, namespace);
		if (ehrId == null) {
			ehrId = port.createEhr(sessionId, externalId, namespace);
			log.info("ehr created with id: " + ehrId);
		} else {
			log.info("ehr found with id: " + ehrId);
		}

		String subjectId = port.getSubjectId(sessionId, ehrId, namespace);

		log.info("subjectId of the ehr: " + subjectId);
		
		ORIGINAL_VERSION[] versions = createVersions();
		AUDIT_DETAILS audit = createAuditDetails();		
		
		CommitStatus status = port.commitContribution(sessionId, ehrId, audit, versions);
		
		log.info("comit status: " + status.getValue());
	}

	private static ORIGINAL_VERSION[] createVersions() throws Exception  {
		// uid must be diff for each call
		ObjectVersionID uid = new ObjectVersionID("1.4.4.8::1.2.840.114.1.2.3::1");
        
		CodePhrase codePhrase = TestTerminologyAccess.CREATION;
        DvCodedText codedText = new DvCodedText("creation", ENGLISH,
                LATIN_1, codePhrase, TestTerminologyService.getInstance());
        PartyIdentified pi = new PartyIdentified(new PartyRef(
                new HierObjectID("1-2-3-4-5"), 
                ObjectRef.Type.PARTY), "committer name", null);
        AuditDetails audit1 = new AuditDetails("12.3.4.5", pi, 
                new DvDateTime("2006-05-01T10:10:00"), codedText, null,
                TestTerminologyService.getInstance());
        ObjectRef lr = new LocatableRef(new ObjectVersionID(
                "1.23.51.66::1.2.840.114.1.2.2::2"), ObjectRef.Namespace.LOCAL,
                ObjectRef.Type.CONTRIBUTION, null);
        
        Set<ObjectVersionID> otherUids = new HashSet<ObjectVersionID>();
        otherUids.add(new ObjectVersionID("1.4.14.5::1.2.840.114.1.2.2::4.2.2"));
        
        Composition kernelComposition = 
        	TestComposition.compositionWithTwoEntries();
        
        COMPOSITION composition = XMLBinding.convert(kernelComposition);
        
        writeToFile(toXML(composition), "Composition.xml");
        
        OriginalVersion<COMPOSITION> version = new OriginalVersion<COMPOSITION>(
        		uid, null, composition, codedText, audit1, lr, null, 
        		otherUids, null, true, TestTerminologyService.getInstance());	
        
        ORIGINAL_VERSION[] versions = new ORIGINAL_VERSION[1];        
        versions[0] = XMLBinding.convert(version);       
        
        
        log.info("versions created..");
        return versions;      
        
	}
	
	private static void loadComposition() throws Exception {
//		 Make a service
		EhrService service = new EhrServiceLocator();

		log.info("service locator initialized..");

		EhrServiceSoap port = service.getEhrServiceSoap();	

		log.info("port opened..");

		// whatever userId and creditials
		String sessionId = port.openSession("guest", "guest");

		log.info("session opened with id: " + sessionId);

		String compositionVersionUid = "1.4.4.7::1.2.840.114.1.2.3::1";
		
		COMPOSITION composition = 
			port.getComposition(sessionId, compositionVersionUid, true);

		String value = toXML(composition);
		
		writeToFile(value, "composition-returned-" + timestampe() + ".xml");
		
		log.info("composition retrieved: \n" + value);
	}
	
	private static String timestampe() {
		return new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
	}
	
	private static AUDIT_DETAILS createAuditDetails() throws Exception {
		String systemId = "1-2-3-4-5";
		PartyRef pr = new PartyRef(new HierObjectID("1-2-3-4-5"), 
                ObjectRef.Type.PARTY);
        
		PartyProxy committer = new PartyIdentified(pr, "party name", null);
		
		DvCodedText changeType =   new DvCodedText("creation", ENGLISH,
                LATIN_1, TestTerminologyAccess.CREATION, 
                TestTerminologyService.getInstance());
 		
        DvDateTime timeCommitted = new DvDateTime(2007, 8, 14, 2, 3, 5, null);
		
        DvText description = new DvText("some desc");
        TerminologyService terminologyService = TestTerminologyService.getInstance();
        
        AuditDetails audit = new AuditDetails(systemId, committer, timeCommitted, 
        		changeType, description, terminologyService);
        return XMLBinding.convert(audit);
	}
	
	private static String toXML(COMPOSITION composition) throws Exception {
    	StringWriter sw = new StringWriter();
    	SerializationContext context = new SerializationContext(sw);
    	QName elementType = new QName("composition");
    	QName xmlType = new QName("http://schemas.openehr.org/v1", "COMPOSITION");
    	
    	context.serialize(elementType, NullAttributes.singleton, composition,
    			xmlType, Boolean.FALSE, Boolean.FALSE);
    	
    	return sw.toString();
    }
	
	private static void writeToFile(String value, String filename) throws IOException {
		FileUtils.writeStringToFile(new File(filename), value, null);
	}

	/* fields */
	public static final TerminologyID LANGUAGE;
	public static final TerminologyID COUNTRIES;
    public static final TerminologyID CHARSET;
    public static final TerminologyID SNOMEDCT;


    static {
    	LANGUAGE = new TerminologyID("ISO_639-1");
        COUNTRIES = new TerminologyID("ISO_3166-1");
    	CHARSET = new TerminologyID("charset-test");
        SNOMEDCT = new TerminologyID("snomedct-test");
    }
    
	public static final CodePhrase ENGLISH;
	public static final CodePhrase SWEDEN;
    public static final CodePhrase LATIN_1;

    static {
        ENGLISH = new CodePhrase(LANGUAGE, "en");
        SWEDEN = new CodePhrase(COUNTRIES, "se");
        LATIN_1 = new CodePhrase(CHARSET, "ISO-8859-1");
    }
    
    private static Logger log = Logger.getLogger(EhrServiceTester.class);
}
