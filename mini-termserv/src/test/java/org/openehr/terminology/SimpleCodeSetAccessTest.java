/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SimpleCodeSetAccessTest"
 * keywords:    "terminology"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2007 Rong Chen"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.terminology;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.CodeSetAccess;

import java.util.*;
import junit.framework.TestCase;

public class SimpleCodeSetAccessTest extends TestCase {
	
	public void setUp() {
		String id = CODESET_ID;
		Set<String> codes = new HashSet<String>(Arrays.asList(CODES));
		instance = new SimpleCodeSetAccess(id, codes);
	}
	
	public void testGetId() {
		assertEquals("id wrong", CODESET_ID, instance.id());
	}
	
	public void testGetAllCodes() {
		Set<CodePhrase> allCodes = new HashSet<CodePhrase>();
		for(String c : CODES) {
			allCodes.add(new CodePhrase(CODESET_ID, c));
		}
		assertEquals("allCodes wrong", allCodes, instance.allCodes());
	}
	
	public void testHasCode() {
		for(String c : CODES) {
			CodePhrase code = new CodePhrase(CODESET_ID, c);
			assertTrue("code " + c + " should exist", instance.hasCode(code));
		}
	}
	
	/* fixtures */
	private final static String CODESET_ID = "test_id";
	private final static String[] CODES = {
		"code1", "code2", "code3", "code4"
	};
	
	/* test instance */
	private CodeSetAccess instance;	
}
