/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class ArchetypeTestCase"
 * keywords:    "archetype testing"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.testing;

import java.io.*;

import org.openehr.rm.binding.DADLBinding;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.schemas.v1.*;
import org.openehr.schemas.v1.QueryTestSpecificationDocument.QueryTestSpecification;
import org.openehr.am.archetype.*;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;
import se.acode.openehr.parser.ADLParser;

import junit.framework.TestCase;

/**
 * Root class of all concrete archetype testcase classes
 * 
 * provides base attributes and functions common to all test cases
 * 
 * @author Rong.Chen
 */
public abstract class ArchetypeTestCase extends TestCase {
	
	/**
	 * Create a test case with name
	 * 
	 * @param name
	 */
	public ArchetypeTestCase(String name) {
		super(name);
	}
	
	/**
	 * Loads dadl from file and binds data to RM object
	 * 
	 * @param filename of the reference model instance in dADL format
	 * @return the object form of the instances
	 */
	protected Object loadRMInstance(String filename) throws Exception {
		DADLParser parser = new DADLParser(fromClasspath(
				INSTANCE_PATH + "/" + filename));
		ContentObject contentObj = parser.parse();
		return binding.bind(contentObj);
	}
	
	/**
	 * Loads archetype from adl file
	 * 
	 * @param filename of the archetype in ADL format
	 * @return the archetype object
	 */
	protected Archetype loadArchetype(String filename) throws Exception {
		ADLParser parser = new ADLParser(fromClasspath(
				ARCHETYPE_PATH + "/" + filename));
		Archetype archetype = parser.parse();
		return archetype;
	}
	
	/**
	 * Loads the specification for archetype query test case
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	protected static QueryTestSpecification loadQueryTestCase(String filename) 
	throws Exception {
		QueryTestSpecificationDocument doc = 
			QueryTestSpecificationDocument.Factory.parse(
				fromClasspath(TESTCASE_PATH + "/" + filename));
		return doc.getQueryTestSpecification();
	}
	
	// TODO implement this validation specs
	protected static Object loadValidationTestCase(String filename) 
	throws Exception {
		return null;
	}
	
	/* 
	 * Loads given resources from classpath
	 * 
	 * @param adl
	 * @return
	 * @throws Exception
	 */
	private static InputStream fromClasspath(String res) throws Exception {
    	return ArchetypeTestCase.class.getClassLoader().getResourceAsStream(res);
    }
	
	/* static fields */
	private static final String ARCHETYPE_PATH = "archetypes";
	private static final String INSTANCE_PATH = "instances";
	private static final String TESTCASE_PATH = "testcases";
	
	private static DADLBinding binding = new DADLBinding();
}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is ArchetypeTestCase.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */