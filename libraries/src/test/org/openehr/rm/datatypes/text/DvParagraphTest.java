package org.openehr.rm.datatypes.text;

import java.util.*;
import junit.framework.TestCase;

public class DvParagraphTest extends TestCase {
	
	public void testEqualsWithTwoEqualParagraphs() throws Exception {
		String[] values = new String[] { "apple", "pear" };
		DvParagraph p1 = asTextParagraph(values);
		DvParagraph p2 = asTextParagraph(values);
		assertTrue("should equals", p1.equals(p2));
		assertTrue("should equals", p2.equals(p1));		
	}
	
	public void testEqualsWithDifferentNumberOfItems() throws Exception {
		String[] fruits = new String[] { "apple", "pear" };
		String[] moreFruits = new String[] { "apple", "pear", "grape" };
		
		DvParagraph p1 = asTextParagraph(fruits);
		DvParagraph p2 = asTextParagraph(moreFruits);
		assertFalse("shoud not equal", p1.equals(p2));
		assertFalse("shoud not equal", p2.equals(p1));
	}
	
	public void testEqualsWithDifferentValueOfItems() throws Exception {
		String[] fruits = new String[] { "apple", "pear" };
		String[] drinks = new String[] { "tea" , "coffee" };
		
		DvParagraph p1 = asTextParagraph(fruits);
		DvParagraph p2 = asTextParagraph(drinks);
		assertFalse("shoud not equal", p1.equals(p2));
		assertFalse("shoud not equal", p2.equals(p1));
	}
	
	/* construct a paragraph out of string array */
	private DvParagraph asTextParagraph(String[] values) {
		List<DvText> items = new ArrayList<DvText>();
		for(String s : values) {
			items.add(new DvText(s));
		}
		return new DvParagraph(items);
	}
	
	/* construct a paragraph out of string array */
	private DvParagraph asCodedTextParagraph(String[] values) {
		List<DvText> items = new ArrayList<DvText>();
		for(String s : values) {
			CodePhrase definingCode = new CodePhrase("ICD10", "A019");
			items.add(new DvCodedText(s, definingCode));
		}
		return new DvParagraph(items);
	}
}
