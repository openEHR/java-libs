package org.openehr.am.openehrprofile.datatypes.text;

import java.util.*;

import org.openehr.am.archetype.constraintmodel.CAttribute;
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
		Interval<Integer> occurrences = null;
		String nodeId = "at0010";
		CAttribute parent = null;
		TerminologyID terminologyId = null;
		List<String> codeList = null;
		CCodePhrase constraint = new CCodePhrase(path, occurrences, nodeId, 
				parent, terminologyId, codeList);
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
				nodeId, null, terminologyId, codeList);
		
		assertNotNull(constraint);
		assertFalse("anyAllowed unexpected", constraint.isAnyAllowed());
		assertEquals("codeList wrong", codeList, constraint.getCodeList());		
	}
}
