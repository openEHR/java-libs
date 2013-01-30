/*
 * component:   "openEHR Reference Java Implementation"
 * description: "Class ValidationTestBase"
 * keywords:    "validator"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * support:     "openEHR Java Project <ref_impl_java@openehr.org>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.validation;

import java.io.InputStream;
import java.util.List;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.RMObject;
import org.openehr.rm.binding.DADLBinding;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;

import se.acode.openehr.parser.ADLParser;

import junit.framework.TestCase;

public class ValidationTestBase extends TestCase {
	
	protected void tearDown() {
		archetype = null;
		data = null;
		errors = null;
	}
	
	InputStream loadFromClasspath(String filename) throws Exception {
    	return this.getClass().getClassLoader().getResourceAsStream(filename);
    }
	
	protected void loadArchetype(String name) throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath("archetypes/" + name));
		archetype = parser.parse();				
	}
	
	protected void loadData(String name) throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(name));
		ContentObject contentObj = parser.parse();
		data = (RMObject) binding.bind(contentObj);
	}
	
	protected void loadDataAndValidate(String name) throws Exception {
		loadData("data/" + name);
		validate();
	}
	
	protected void validate() throws Exception {
		errors = validator.validate((Locatable) data, archetype);		
	}
	
	// assert validation passed
	protected void assertValidationPassed() {
		assertTrue("unexpected validation error(s)", errors.isEmpty());
	}
	
	// assert the last error in the list using given details
	protected void assertLastValidationError(String runtimePath, 
			String archetypePath, ErrorType errorType) {
		
		assertTrue("unexpected total error: " + errors.size(), 
				errors.size() == 1);		
		error = errors.get(0);		
		
		assertValidationError(error, runtimePath, archetypePath, 
				errorType);
	}
	
	// assert the details of given validation error
	protected void assertValidationError(ValidationError error, 
			String runtimePath, String archetypePath, 
			ErrorType errorType) {
		
		assertEquals("unexpected runtime path", runtimePath, 
				error.getRuntimePath());
		
		assertEquals("unexpected archetype path", archetypePath,
				error.getArchetypePath());
		
		assertEquals("unexpected validation error type", errorType, 
				error.getErrorType());
	}
	
	// test instances
	protected DataValidator validator = new DataValidatorImpl();
	protected List<ValidationError> errors = null;
	protected ValidationError error = null;
 	protected Archetype archetype = null;
	protected RMObject data = null;	
	
	private static DADLBinding binding = new DADLBinding();	
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
 *  The Original Code is ValidationTestBase.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2008
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