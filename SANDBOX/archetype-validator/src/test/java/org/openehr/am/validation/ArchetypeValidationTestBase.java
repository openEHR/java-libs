package org.openehr.am.validation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.openehr.am.archetype.Archetype;

import se.acode.openehr.parser.ADLParser;

import junit.framework.TestCase;

public class ArchetypeValidationTestBase extends TestCase {

	public void setUp() {
		validator = new ArchetypeValidator();	
		errors = new ArrayList<ValidationError>();
	}
	
	public void tearDown() {		
		validator = null;
		archetype = null;
		errors = null;
	}
	
	/**
	 * Loads archetype from given filename
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	protected Archetype loadArchetype(String name) throws Exception {
    	InputStream input = 
    		this.getClass().getClassLoader().getResourceAsStream(name);
    	ADLParser parser = new ADLParser(input, "UTF-8");
		return parser.parse();
    }
	
	/**
	 * Asserts the first validation error is the same as the given error type
	 * 
	 * @param type
	 */
	protected void assertFirstErrorType(ErrorType type) {
		assertTrue("expected at least 1 error", errors.size() >= 1);
		assertEquals("unexpected error(s): " + errors, type, 
				errors.get(0).getType());
	}
	
	/**
	 * Asserts the second validation error is the same as the given error type
	 * 
	 * @param type
	 */
	protected void assertSecondErrorType(ErrorType type) {
		assertTrue("expected at least 2 error", errors.size() >= 2);
		assertEquals("errorType wrong",  type, errors.get(1).getType());
	}	
	
	protected ArchetypeValidator validator;
	protected Archetype archetype;
	protected List<ValidationError> errors;
}
