package org.openehr.ehrdemo;

import org.apache.log4j.Logger;
import org.openehr.schemas.v1.*;
import biz.oceaninformatics.www.OceanEhr.EhrBank.EhrService.*;

import org.apache.axis.types.Token;

public class EhrServiceTester {

	public static void main(String[] args) throws Exception {
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

	

	private static Logger log = Logger.getLogger(EhrServiceTester.class);
}
