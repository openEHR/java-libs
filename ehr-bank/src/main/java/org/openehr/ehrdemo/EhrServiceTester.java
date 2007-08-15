package org.openehr.ehrdemo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import javax.xml.namespace.*;

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
import org.apache.log4j.Logger;

public class EhrServiceTester {

	public static void main(String[] args) throws Exception {
		EhrServiceTester tester = new EhrServiceTester();

		// locally create a composition
		// tester.createVersions(); 

		// commit a composition to EhrService
		tester.commitContribution();

		// load a composition from EhrService
		//tester.retrieveLastComposition();
	}
	
	private static String fullVersionUid(String unique) {
		String fullUid = "939cec48-d629-4a3f-89f1-28c57338" + unique + 
				"::10aec661-5458-4ff6-8e63-c2265537196d::1";
		log.info("full uid: " + fullUid);
		return fullUid;
	}

	/*
	 * Initialize the ehr service 
	 */
	private void initService() throws Exception {

		// check if the service port and session are still valid
		if (servicePort != null && servicePort.isValidSession(sessionId)) {
			return;
		}

		EhrService service = new EhrServiceLocator();

		log.info("service locator created..");

		servicePort = service.getEhrServiceSoap();

		log.info("ehr service port opened..");

		// whatever userId and creditials
		sessionId = servicePort.openSession(USER, PASSWD);

		log.info("session opened with id: " + sessionId);
	}

	private void createEHR(String externalId, String namespace)
			throws Exception {

		initService();

		String ehrId = servicePort.findEhrId(sessionId, externalId, namespace);
		if (ehrId == null) {
			ehrId = servicePort.createEhr(sessionId, externalId, namespace);
			log.info("ehr created with id: " + ehrId);
		} else {
			log.info("ehr found with id: " + ehrId);
		}

		String subjectId = servicePort
				.getSubjectId(sessionId, ehrId, namespace);

		log.info("subjectId of the ehr: " + subjectId);
	}

	private void commitContribution() throws Exception {

		initService();

		String externalId = EXTERNAL_SUBJECT_ID;
		String namespace = NAMESPACE;
		String ehrId = servicePort.findEhrId(sessionId, externalId, namespace);
		if (ehrId == null) {
			createEHR(externalId, namespace);
		} else {
			log.info("ehr found with id: " + ehrId);
		}
		String compositionVersionUid = fullVersionUid(retrieveAndSetNextUid());
		ORIGINAL_VERSION[] versions = 
			createVersionsWithSingleComposition(compositionVersionUid);

		logToFile((COMPOSITION) versions[0].getData(), "sent");

		AUDIT_DETAILS audit = createAuditDetails();

		CommitStatus status = servicePort.commitContribution(sessionId, ehrId,
				audit, versions);

		log.info("commit status: " + status.getValue());
	}

	private void retrieveComposition(String compositionVersionUid)
			throws Exception {

		initService();

		COMPOSITION composition = servicePort.getComposition(sessionId,
				compositionVersionUid, true);

		logToFile(composition, "retrieved");

		log.info("composition retrieved successfully!!");
	}

	private void retrieveLastComposition() throws Exception {
		String compositionVersionUid = fullVersionUid(retrieveCurrentUid());
		retrieveComposition(compositionVersionUid);
	}

	// compositionVersionUid must be diff for each call	
	private static ORIGINAL_VERSION[] createVersionsWithSingleComposition(
			String compositionVersionUid) throws Exception {

		ObjectVersionID uid = new ObjectVersionID(compositionVersionUid);

		CodePhrase codePhrase = TestTerminologyAccess.CREATION;
		DvCodedText codedText = new DvCodedText("creation", ENGLISH, LATIN_1,
				codePhrase, TestTerminologyService.getInstance());
		PartyIdentified pi = new PartyIdentified(new PartyRef(new HierObjectID(
				"1-2-3-4-5"), ObjectRef.Type.PARTY), "committer name", null);

		AuditDetails audit1 = new AuditDetails("12.3.4.5", pi, new DvDateTime(
				"2007-08-14T10:10:00"), codedText, null, TestTerminologyService
				.getInstance());
		ObjectRef lr = new LocatableRef(new ObjectVersionID(
				"1.23.51.66::1.2.840.114.1.2.2::2"), ObjectRef.Namespace.LOCAL,
				ObjectRef.Type.CONTRIBUTION, null);

		Set<ObjectVersionID> otherUids = new HashSet<ObjectVersionID>();
		otherUids
				.add(new ObjectVersionID("1.4.14.5::1.2.840.114.1.2.2::4.2.2"));

		// chose test composition to be included in versions
		Composition kernelComposition = TestComposition.encounterGlucose();

		COMPOSITION composition = XMLBinding.convert(kernelComposition);

		OriginalVersion<COMPOSITION> version = new OriginalVersion<COMPOSITION>(
				uid, null, composition, codedText, audit1, lr, null, otherUids,
				null, true, TestTerminologyService.getInstance());

		ORIGINAL_VERSION[] versions = new ORIGINAL_VERSION[1];
		versions[0] = XMLBinding.convert(version);

		return versions;
	}

	// log with timestamp
	private static void logToFile(COMPOSITION composition, String type)
			throws Exception {
		String value = toXML(composition);
		File logDir = new File("log" + File.separator + type);
		if (!logDir.exists()) {
			logDir.mkdirs();
		}
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss")
				.format(new Date());
		String filename = "composition-" + type + "-" + timestamp + ".xml";
		File logfile = new File(logDir, filename);
		FileUtils.writeStringToFile(logfile, value, null);
	}

	private static AUDIT_DETAILS createAuditDetails() throws Exception {
		String systemId = "1-2-3-4-5";
		PartyRef pr = new PartyRef(new HierObjectID("1-2-3-4-5"),
				ObjectRef.Type.PARTY);

		PartyProxy committer = new PartyIdentified(pr, "party name", null);

		DvCodedText changeType = new DvCodedText("creation", ENGLISH, LATIN_1,
				TestTerminologyAccess.CREATION, TestTerminologyService
						.getInstance());

		DvDateTime timeCommitted = new DvDateTime(2007, 8, 14, 2, 3, 5, null);

		DvText description = new DvText("audit description");
		TerminologyService terminologyService = TestTerminologyService
				.getInstance();

		AuditDetails audit = new AuditDetails(systemId, committer,
				timeCommitted, changeType, description, terminologyService);
		return XMLBinding.convert(audit);
	}

	private static String toXML(COMPOSITION composition) throws Exception {
		StringWriter sw = new StringWriter();
		SerializationContext context = new SerializationContext(sw);
		QName xmlType = new QName("http://schemas.openehr.org/v1",
				"COMPOSITION");

		context.setPretty(true);
		context.serialize(xmlType, NullAttributes.singleton, composition,
				xmlType, Boolean.FALSE, Boolean.FALSE);
		return sw.toString();
	}

	// temp file based solution for getting unique uid for committing 
	private static String retrieveAndSetNextUid() throws Exception {
		File uiddir = new File("log" + File.separator + "uid");
		if (!uiddir.exists()) {
			uiddir.mkdirs();
		}
		File uidfile = new File(uiddir, "uid.txt");
		if (!uidfile.exists()) {
			FileUtils.writeStringToFile(uidfile, UID_START, null);
			return UID_START;
		}
		String value = FileUtils.readFileToString(uidfile, null);
		try {
			int uid = Integer.parseInt(value);
			String next = Integer.toString(uid + 1);
			FileUtils.writeStringToFile(uidfile, next, null);
			return next;
		} catch (Exception e) {
			FileUtils.writeStringToFile(uidfile, UID_START, null);
			return UID_START;
		}
	}
	
	static String retrieveCurrentUid() throws Exception {
		File uiddir = new File("log" + File.separator + "uid");
		File uidfile = new File(uiddir, "uid.txt");
		if (!uidfile.exists()) {
			return UID_START;
		}
		return FileUtils.readFileToString(uidfile, null);
	}

	/* fields */
	private EhrServiceSoap servicePort;

	private String sessionId;

	/* EhrService usr/pwd */
	private static final String USER = "guest";

	private static final String PASSWD = "guest";

	private static final String EXTERNAL_SUBJECT_ID = "1912121212";

	private static final String NAMESPACE = "local";

	private static final String UID_START = "1000";

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
