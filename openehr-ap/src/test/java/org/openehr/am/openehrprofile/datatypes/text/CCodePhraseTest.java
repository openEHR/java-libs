package org.openehr.am.openehrprofile.datatypes.text;

import java.util.*;

import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.identification.TerminologyID;

import junit.framework.TestCase;

/**
 * Simple testcase for CDvCodedText
 * 
 * @author Rong.Chen
 */
public class CCodePhraseTest extends TestCase {
	
	public void testCreateEmptyCCodePhrase() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		String nodeId = "at0010";
		CAttribute parent = null;
		TerminologyID terminologyId = null;
		List<String> codeList = null;
		CCodePhrase constraint = new CCodePhrase(path, occurrences, nodeId, 
				parent, terminologyId, codeList, null, null);
		assertTrue("anyAllowed expected", constraint.isAnyAllowed());
	}

	public void testCreateCCodePhraseWithCodeList() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(0, 1);
		String nodeId = "at0010";
		TerminologyID terminologyId = new TerminologyID("openehr");
		List<String> codeList = new ArrayList<String>();
		codeList.add("mean");
		codeList.add("total");
		
		CCodePhrase constraint = new CCodePhrase(path, occurrences, 
				nodeId, null, terminologyId, codeList, null, null);
		
		assertNotNull(constraint);
		assertFalse("anyAllowed unexpected", constraint.isAnyAllowed());
		assertEquals("codeList wrong", codeList, constraint.getCodeList());		
	}
	
	public void testValidValue() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(0, 1);
		String nodeId = "at0010";
		TerminologyID terminologyId = new TerminologyID("test");
		List<String> codeList = new ArrayList<String>();
		codeList.add("100");
		codeList.add("101");
		
		CCodePhrase constraint = new CCodePhrase(path, occurrences, 
				nodeId, null, terminologyId, codeList, null, null);
		
		assertFalse("expected invalid - both wrong", 
				constraint.validValue(new CodePhrase("test2", "102")));
		assertFalse("expected invalid - wrong code", 
				constraint.validValue(new CodePhrase("test", "102")));
		assertFalse("expected invalid - wrong terminology", 
				constraint.validValue(new CodePhrase("test2", "100")));
		assertFalse("expected invalid - null value",
				constraint.validValue(null));		
		
		assertTrue("expected valid", 
				constraint.validValue(new CodePhrase("test", "100")));
		assertTrue("expected valid", 
				constraint.validValue(new CodePhrase("test", "101")));		
	}
	
	
	public void testEqualsWithDifferentCode() {
		CCodePhrase planned = new CCodePhrase("/defining_code", "openehr", 
				"524");
		CCodePhrase postponed = new CCodePhrase("/defining_code", "openehr", 
				"527");
		
		assertFalse("two CCodePhrase with different code should not be equal",
				planned.equals(postponed));
		
		assertFalse("two CCodePhrase with different code should not be equal",
				postponed.equals(planned));		
	}
	
	public void testEqualsWithDifferentTerminology() {
		CCodePhrase planned = new CCodePhrase("/defining_code", "openehr", 
				"524");
		CCodePhrase postponed = new CCodePhrase("/defining_code", "another", 
				"524");
		
		assertFalse("two CCodePhrase with different terminology should not be equal",
				planned.equals(postponed));
		
		assertFalse("two CCodePhrase with different terminology should not be equal",
				postponed.equals(planned));		
	}
	
	public void testEqualsWithSameCodeAndTerminology() {
		CCodePhrase planned = new CCodePhrase("/defining_code", "openehr", 
				"527");
		CCodePhrase postponed = new CCodePhrase("/defining_code", "openehr", 
				"527");
		
		assertTrue("two CCodePhrase with same terminology and code should be equal",
				planned.equals(postponed));
		
		assertTrue("two CCodePhrase with same terminology and code should be equal",
				postponed.equals(planned));		
	}
}
