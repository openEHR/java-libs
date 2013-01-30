package org.openehr.ehrdemo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import javax.xml.namespace.*;

import org.openehr.rm.composition.*;
import org.openehr.schemas.v1.*;

import biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.*;

import org.apache.axis.message.*;
import org.apache.axis.encoding.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class EhrServiceTester {

	public static void main(String[] args) throws Exception {
		EhrServiceTester tester = new EhrServiceTester();

		// commit a composition to EhrService
		//tester.commitContribution();

		// load a composition from EhrService
		tester.retrieveLastComposition();
	}
	
	/**
	 * Creates a full version Uid based on given unique value
	 * 
	 * @param unique
	 * @return
	 */
	public static String fullVersionUid(String unique) {
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

	private String createEHR(String externalId, String namespace)
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

		log.info("subjectId of the ehr: " + subjectId 
				+ ", ehrId: " + ehrId);
		
		return ehrId;
	}

	private void commitContribution() throws Exception {

		initService();

		String externalId = EXTERNAL_SUBJECT_ID;
		String namespace = NAMESPACE;
		String ehrId = servicePort.findEhrId(sessionId, externalId, namespace);
		if (ehrId == null) {
			ehrId = createEHR(externalId, namespace);
		} else {
			log.info("ehr found with id: " + ehrId);
		}
		String compositionVersionUid = fullVersionUid(retrieveAndSetNextUid());
		
		// create a test composition with given glucose value
		TestComposition testComp = new TestComposition();
		testComp.setGlucoseValue(125.0);
		Composition composition = testComp.glucoseComposition();
		
		ORIGINAL_VERSION[] versions = 
			TestComposition.createVersionsWithComposition(
				compositionVersionUid, composition);

		logToFile((COMPOSITION) versions[0].getData(), "sent");

		AUDIT_DETAILS audit = TestComposition.createAuditDetails();
		
		log.info("committing composition..");

		CommitStatus status = servicePort.commitContribution(sessionId, ehrId,
				audit, versions);

		log.info("commit status: " + status.getValue());
	}

	/**
	 * Retrieves composition with full version UID
	 * 
	 * @param compositionVersionUid
	 * @return
	 * @throws Exception
	 */
	public COMPOSITION retrieveComposition(String compositionVersionUid)
			throws Exception {

		initService();

		COMPOSITION composition = servicePort.getComposition(sessionId,
				compositionVersionUid, true);

		logToFile(composition, "retrieved");

		log.info("composition retrieved successfully!!");
		
		return composition;
	}

	/**
	 * Retrieves the last composition known to this client
	 * based on locally managed uid segment
	 * 
	 * @throws Exception
	 */
	public COMPOSITION retrieveLastComposition() throws Exception {
		String compositionVersionUid = fullVersionUid(retrieveCurrentUid());
		return retrieveComposition(compositionVersionUid);
	}
	
	/**
	 * Retrieves a composition by the given id
	 * 
	 * The id is part of a larger UID string
	 * 
	 * @param id
	 * @throws Exception
	 */
	public COMPOSITION retrieveCompositionById(String id) throws Exception {
		String compositionVersionUid = fullVersionUid(id);
		return retrieveComposition(compositionVersionUid);
	}
	
	/**
	 * Utility method to log a given composition to named sub-directory
	 * 
	 * @param composition
	 * @param type
	 * @throws Exception
	 */
	public static void logToFile(COMPOSITION composition, String type)
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

	

	/**
	 * Utility method to convert a composition to string
	 * 
	 * @param composition
	 * @return
	 * @throws Exception
	 */
	public static String toXML(COMPOSITION composition) throws Exception {
		StringWriter sw = new StringWriter();
		SerializationContext context = new SerializationContext(sw);
		QName xmlType = new QName("http://schemas.openehr.org/v1",
				"COMPOSITION");

		context.setPretty(true);
		context.serialize(xmlType, NullAttributes.singleton, composition,
				xmlType, Boolean.FALSE, Boolean.FALSE);
		return sw.toString();
	}
	
	// todo
	public static String fromXML() throws Exception {
		return null;
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

	private static Logger log = Logger.getLogger(EhrServiceTester.class);
}
