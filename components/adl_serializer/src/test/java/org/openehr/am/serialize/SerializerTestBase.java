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

import org.openehr.rm.datatypes.text.CodePhrase;

import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

public class SerializerTestBase extends TestCase {

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
		String expected = readFile(expectedFile);
		String actual = out.toString();
		verify(expected, actual);	
	}
	
	private String readFile(String filename) throws Exception {
		InputStream input = null;
		try {
			input = this.getClass().getClassLoader().getResourceAsStream(filename);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String line = reader.readLine();
			StringBuffer buf = new StringBuffer();
			while(line != null) {
				buf.append(line);
				buf.append("\r\n");
				line = reader.readLine();				
			}			
			
			return buf.toString();
			
		} finally {
			if(input != null) {
				input.close();
			}
		}		
	}
	
	private void verify(String expected, String actual) {
		assertEquals(">>> expected: \r\n" + expected + "\r\n >>> actual:\r\n" + actual,
				expected, actual);
	}

	/* field */
	protected ADLSerializer outputter;
	protected StringWriter out;
	
	/* static fields */
	protected static final CodePhrase ENGLISH = new CodePhrase("iso-639", "en");
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
