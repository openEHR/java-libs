package org.openehr.am.openehrprofile.datatypes.quantity;

import java.util.*;

import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;

import junit.framework.TestCase;

public class CDvOrdinalTest extends TestCase {
	
	public void testCreateEmptyCDvOrdinal() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		String nodeId = "at0010";
		CAttribute parent = null;
		Set<Ordinal> list = null;
		
		CDvOrdinal c = new CDvOrdinal(path, occurrences, nodeId, parent, list,
				null, null);
		assertTrue("anyAllowed expected", c.isAnyAllowed());	
		assertNull("null list expected", c.getList());
	}
	
	public void testCreateCDvOrdinalWithAssumedValue() {
		String path = "/term_definitions[en]/items[at0001]/text/";
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		String nodeId = "at0010";
		CAttribute parent = null;
		Ordinal assumed = new Ordinal(1, new CodePhrase("local", "at0001"));
		
		CDvOrdinal c = new CDvOrdinal(path, occurrences, nodeId, parent, null,
				null, assumed);
		assertEquals("assumed wrong", assumed, c.getAssumedValue());		
	}
	
	public void testEqualsWithDifferentOrdinal() {
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		Set<Ordinal> set1 = new HashSet<Ordinal>();
		Ordinal ord1 = new Ordinal(1, new CodePhrase("local", "at0001"));
		set1.add(ord1);
		
		Set<Ordinal> set2 = new HashSet<Ordinal>();
		Ordinal ord2 = new Ordinal(1, new CodePhrase("local", "at0003"));
		set2.add(ord2);
		
		CDvOrdinal cdo1 = new CDvOrdinal("/path", occurrences, set1);
		CDvOrdinal cdo2 = new CDvOrdinal("/path", occurrences, set2);
		
		assertFalse("CDvOrdinal with different ordinal should not equal",
				cdo1.equals(cdo2));
		assertFalse("CDvOrdinal with different ordinal should not equal",
				cdo2.equals(cdo1));
	}
	
	public void testEqualsWithSameOrdinal() {
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		Set<Ordinal> set1 = new HashSet<Ordinal>();
		Ordinal ord1 = new Ordinal(1, new CodePhrase("local", "at0001"));
		set1.add(ord1);
		
		Set<Ordinal> set2 = new HashSet<Ordinal>();
		Ordinal ord2 = new Ordinal(1, new CodePhrase("local", "at0001"));
		set2.add(ord2);
		
		CDvOrdinal cdo1 = new CDvOrdinal("/path", occurrences, set1);
		CDvOrdinal cdo2 = new CDvOrdinal("/path", occurrences, set2);
		
		assertTrue("CDvOrdinal with the same ordinal should equal",
				cdo1.equals(cdo2));
		assertTrue("CDvOrdinal with different ordinal should equal",
				cdo2.equals(cdo1));
	}
}
