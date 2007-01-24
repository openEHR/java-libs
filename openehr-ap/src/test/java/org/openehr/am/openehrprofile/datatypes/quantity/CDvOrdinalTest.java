package org.openehr.am.openehrprofile.datatypes.quantity;

import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;

import junit.framework.TestCase;

public class CDvOrdinalTest extends TestCase {
	
	public void testCreateEmptyCDvOrdinal() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = null;
		String nodeId = "at0010";
		CAttribute parent = null;
		
		CDvOrdinal c = new CDvOrdinal(path, occurrences, nodeId, parent, null,
				null, null);
		assertTrue("anyAllowed expected", c.isAnyAllowed());		
	}
	
	public void testCreateCDvOrdinalWithAssumedValue() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = null;
		String nodeId = "at0010";
		CAttribute parent = null;
		Ordinal assumed = new Ordinal(1, new CodePhrase("local", "at0001"));
		
		CDvOrdinal c = new CDvOrdinal(path, occurrences, nodeId, parent, null,
				null, assumed);
		assertEquals("assumed wrong", assumed, c.getAssumedValue());		
	}
}
