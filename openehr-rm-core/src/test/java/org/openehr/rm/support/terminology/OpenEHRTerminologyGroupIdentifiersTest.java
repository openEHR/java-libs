package org.openehr.rm.support.terminology;

import junit.framework.TestCase;

public class OpenEHRTerminologyGroupIdentifiersTest extends TestCase {

	public void testValidateTerminologyGroupIdWithValidId() {
		String id = 
			OpenEHRTerminologyGroupIdentifiers.ATTESTATION_REASON.toString();
		assertTrue(id + " should be valid", 
				OpenEHRTerminologyGroupIdentifiers.validTerminologyGroupId(id));
	}
	
	public void testValidateTerminologyGroupIdWithInvalidId() {
		String id = "unknow terminology group id";
		assertFalse(id + " should not be valid", 
				OpenEHRTerminologyGroupIdentifiers.validTerminologyGroupId(id));
	}
}
