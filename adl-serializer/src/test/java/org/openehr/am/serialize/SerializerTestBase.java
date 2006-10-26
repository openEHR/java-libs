/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SerializerTestBase"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005,2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision: $"
 * last_change: "$LastChangedDate: $"
 */
package org.openehr.am.serialize;

import java.io.*;
import java.util.*;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.CodeSetAccess;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.TerminologyService;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

public class SerializerTestBase extends TestCase 
	implements TerminologyService, CodeSetAccess {

	/**
	 * Constructor
	 * 
	 * @param test
	 */
	public SerializerTestBase(String test) {
		super(test);
	}

	/**
	 * The fixture set up called before every test method.
	 */
	protected void setUp() throws Exception {
		outputter = new ADLSerializer();
	}

	/**
	 * The fixture clean up called after every test method.
	 */
	protected void tearDown() throws Exception {
		outputter = null;
	}

	/* clean the string writer for next test */
	protected void clean() {
		out = new StringWriter();
	}

	/* take the result from writer and compare with the expected */
	protected void verify(String expected) {
		String actual = out.toString();
		verify(expected, actual);
	}
	
	protected void verifyByFile(String expectedFile) throws Exception {
		List<String> expected = readFile(expectedFile);
		List<String> actual = toLines(out.toString());
		verifyByLines(expected, actual);	
	}
	
	private List<String> readFile(String filename) throws Exception {
		InputStream input = null;
		List<String> list = new ArrayList<String>();
		try {
			input = this.getClass().getClassLoader().getResourceAsStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line = reader.readLine();
			while(line != null) {
				list.add(line);
				line = reader.readLine();				
			}			
			
			return list;
			
		} finally {
			if(input != null) {
				input.close();
			}
		}		
	}
	
	private List<String> toLines(String source) {
		List<String> list = new ArrayList<String>();
		StringTokenizer tokens = new StringTokenizer(source, "\r\n");
		while(tokens.hasMoreTokens()) {
			list.add(tokens.nextToken());
		}
		return list;	
	}
	
	private void verify(String expected, String actual) {
		assertEquals(">>> expected: \r\n" + expected + "\r\n >>> actual:\r\n" + actual,
				expected, actual);
	}
	
	/* verify line by line, preceeding and trailing white spaces, line returns are ignored */
	private void verifyByLines(List<String> expected, List<String> actual) {
		int expectedSize = expected.size();
		int actualSize = actual.size();
		
		// check if preceeding lines match
		for(int i = 0; i < expectedSize && i < actualSize; i++) {
			String lineExpected = expected.get(i).trim();
			String lineActual = actual.get(i).trim();
			if( ! lineExpected.equals(lineActual)) {
				assertEquals(">>> line no." + i + " different.. ", lineExpected, lineActual);
			}
		}
		
		if(expectedSize != actualSize) {
			assertEquals("total number of lines wrong", expectedSize, actualSize);
		}
	}

	/* field */
	protected ADLSerializer outputter;
	protected StringWriter out;
	
	/* static fields */
	protected static final CodePhrase ENGLISH = new CodePhrase("iso-639", "en");

	// mocked terminologyService
	public CodeSetAccess codeSet(String arg0) {
		// return this mock implementation
		return this;	
	}

	public boolean hasCodeSet(String arg0) {
		return true;
	}

	public boolean hasTerminology(String arg0) {
		return true;
	}

	public TerminologyAccess terminology(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	// mocked codeSetAccess
	public Set<CodePhrase> allCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasCode(CodePhrase arg0) {
		return true;
	}

	public boolean hasLang(CodePhrase arg0) {
		return true;
	}

	public String id() {
		// TODO Auto-generated method stub
		return null;
	}
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is SerializerTestBase.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2004-2006
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): 
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */
