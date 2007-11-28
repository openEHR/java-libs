/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SimpleTerminologyAccessTest"
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

import java.util.*;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyAccess;

import junit.framework.TestCase;

public class SimpleTerminologyAccessTest extends TestCase {
	
	public void setUp() {
		instance = new SimpleTerminologyAccess(TEST_ID);
		
		Map<String, String> names = new HashMap<String, String>();
		names.put(LANG1, LANG1_GROUP1_NAME);
		names.put(LANG2, LANG2_GROUP1_NAME);		
		((SimpleTerminologyAccess) instance).addGroup(GROUP1_ID, 
				Arrays.asList(GROUP1_CODES), names);
		
		names.clear();
		names.put(LANG1, LANG1_GROUP2_NAME);
		names.put(LANG2, LANG2_GROUP2_NAME);		
		((SimpleTerminologyAccess) instance).addGroup(GROUP2_ID, 
				Arrays.asList(GROUP2_CODES), names);		
		
		for(int i = 0; i < GROUP1_RUBRICS_EN.length; i++) {			
			((SimpleTerminologyAccess) instance).addRubric(
					LANG1, GROUP1_CODES[i], GROUP1_RUBRICS_EN[i]);
		}
	}
	
	public void tearDown() {
		instance = null;
	}
	
	public void testGetId() {
		assertEquals("id wrong", TEST_ID, instance.id());
	}

	public void testGetAllCodes() {
		Set<CodePhrase> codes = new HashSet<CodePhrase>();
		for(String c : GROUP1_CODES) {
			codes.add(new CodePhrase(TEST_ID, c));
		}
		for(String c : GROUP2_CODES) {
			codes.add(new CodePhrase(TEST_ID, c));
		}		
		assertEquals("allCodes wrong", codes, instance.allCodes());
	}

	public void testGetCodesForGroupIdWithAllValidIds() {
		Set<CodePhrase> codes = new HashSet<CodePhrase>();
		for(String c : GROUP1_CODES) {
			codes.add(new CodePhrase(TEST_ID, c));
		}
		assertEquals("group1 codes wrong", codes, 
				instance.codesForGroupId(GROUP1_ID));
		
		codes.clear();
		for(String c : GROUP2_CODES) {
			codes.add(new CodePhrase(TEST_ID, c));
		}		
		assertEquals("group2 codes wrong", codes, 
				instance.codesForGroupId(GROUP2_ID));
	}

	public void testGetCodesForGroup1NameWithTwoLanguages() {
		Set<CodePhrase> codes = new HashSet<CodePhrase>();
		for(String c : GROUP1_CODES) {
			codes.add(new CodePhrase(TEST_ID, c));
		}
		assertEquals("group1 codes wrong", codes, 
				instance.codesForGroupName(LANG1_GROUP1_NAME, LANG1));		
		assertEquals("group1 codes wrong", codes, 
				instance.codesForGroupName(LANG2_GROUP1_NAME, LANG2));
	}
	
	public void testGetCodesForGroup2NameWithTwoLanguages() {
		Set<CodePhrase> codes = new HashSet<CodePhrase>();
		for(String c : GROUP2_CODES) {
			codes.add(new CodePhrase(TEST_ID, c));
		}
		assertEquals("group2 codes wrong", codes, 
				instance.codesForGroupName(LANG1_GROUP2_NAME, LANG1));		
		assertEquals("group2 codes wrong", codes, 
				instance.codesForGroupName(LANG2_GROUP2_NAME, LANG2));
	}

	public void testGetRubricForCodeWithExistingLangCode() {
		String code = "code1";
		String rubric = instance.rubricForCode(code, "en");
		String expected = "rubric1_en";
		assertEquals("rubric wrong for code " + code, expected, rubric);
	}
	
	public void testGetRubricForCodeWithNoneExistingLang() {
		String code = "code1";
		String rubric = instance.rubricForCode(code, "zh");
		String expected = null;
		assertEquals("rubric wrong for code " + code, expected, rubric);
	}
	
	public void testGetRubricForCodeWithNoneExistingCode() {
		String code = "code6";
		String rubric = instance.rubricForCode(code, "en");
		String expected = null;
		assertEquals("rubric wrong for code " + code, expected, rubric);
	}
	
	public void testhasCodeForGroupId() {
		for(String code : GROUP1_CODES) {
			CodePhrase codePhrase = new CodePhrase(TEST_ID, code);
			assertTrue("code: " + code + " missing from " + GROUP1_ID,
					instance.hasCodeForGroupId(GROUP1_ID, codePhrase));
		}
	}
	
	/* test fixture */
	private static final String TEST_ID = "test_id";
	private static final String LANG1 = "en";
	private static final String LANG2 = "fr";
	private static final String[] GROUP1_CODES = {
		"code1", "code2", "code3"
	};
	private static final String[] GROUP1_RUBRICS_EN = {
		"rubric1_en", "rubric2_en", "rubric3_en"
	};
	private static final String[] GROUP2_CODES = {
		"code4", "code5"
	};
	private static final String LANG1_GROUP1_NAME = "group1_en";
	private static final String LANG2_GROUP1_NAME = "group1_fr";
	private static final String LANG1_GROUP2_NAME = "group2_en";
	private static final String LANG2_GROUP2_NAME = "group2_fr";
	private static final String GROUP1_ID = "group 1";
	private static final String GROUP2_ID = "group 2";
	
	
	private TerminologyAccess instance;
}
