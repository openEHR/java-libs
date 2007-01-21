/*
 * Copyright (C) 2005,2006 Acode HB, Sweden.
 * All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/gpl.txt
 *
 */

package se.acode.openehr.parser;

import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.*;

/**
 * Test case tests parsing of domain type constraints extension.
 *
 * @author Rong Chen
 * @version 1.0
 */

public class CCodePhraseTest extends ParserTestBase {

	public CCodePhraseTest(String test) throws Exception {
		super(test);
	}

	/**
	 * The fixture set up called before every test method.
	 */
	protected void setUp() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.c_code_phrase.test.adl"));
		archetype = parser.parse();
	}

	/**
	 * The fixture clean up called after every test method.
	 */
	protected void tearDown() throws Exception {
		archetype = null;
	}

	/**
	 * Verifies parsing of a simple CCodePhrase
	 * 
	 * @throws Exception
	 */
	public void testParseExternalCodes() throws Exception {
		CObject node = archetype.node("/types[at0001]/items[at10002]/value");
		String[] codes = { "F43.00", "F43.01", "F32.02" };
		assertCCodePhrase(node, "icd10", codes, "F43.01");
	}
	
	/**
	 * Verifies parsing of a simple CCodePhrase with codes defined locally
	 * 
	 * @throws Exception
	 */
	public void testParseLocalCodes() throws Exception {
		CObject node = archetype.node("/types[at0001]/items[at10003]/value");
		String[] codeList = { "at1311","at1312", "at1313", "at1314","at1315" }; 
		assertCCodePhrase(node, "local", codeList, null);
	}
	
	private void assertCCodePhrase(CObject actual, String terminologyId,
			String[] codes, String assumedValue) {
		assertTrue("CCodePhrase expected, got " + actual.getClass(), 
				actual instanceof CCodePhrase);
		CCodePhrase cCodePhrase = (CCodePhrase) actual;
		assertEquals("terminology", terminologyId, 
				cCodePhrase.getTerminologyId().getValue());
		List<String> codeList = cCodePhrase.getCodeList();
		assertEquals("codes.size wrong", codes.length, codeList.size());
		
		for (int i = 0; i < codes.length; i++) {
			Object c = codeList.get(i);
			assertEquals("code wrong, got: " + c, codes[i], c);
		}
		
		if(assumedValue == null) {
			assertFalse(cCodePhrase.hasAssumedValue());
		} else {
			assertTrue("expected assumedValue", cCodePhrase.hasAssumedValue());
			assertEquals("assumed value wrong", assumedValue, 
				cCodePhrase.getAssumedValue().getCodeString());
		}
	}

	private Archetype archetype;
}
