package org.openehr.tutorial;

import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.tutorial.answer.DataTypesAnswer;
import org.openehr.tutorial.util.*;

import junit.framework.TestCase;

/**
 * Testcases that verify the coding exercises are properly done
 * 
 * @author rong.chen
 */
public class DataTypesExerciseTest extends TestCase {
	
	public void testCreateDvDateTimeWithISOString() {
		DvDateTime value = instance.createDvDateTimeWithISOString();
		assertNotNull("datetime created with string is null", value);	
		assertEquals("year wrong", 2007, value.getYear());
		assertEquals("mond wrong", 8, value.getMonth());
		assertEquals("day wrong", 17, value.getDay());
		assertEquals("hour wrong", 18, value.getHour());
		assertEquals("minute wrong", 49, value.getMinute());
		assertEquals("second wrong", 30, value.getSecond());
	}
	
	public void testCreateDvDateTimeIntValues() {
		DvDateTime value = instance.createDvDateTimeWithIntValues();
		assertNotNull("datetime created with int is null", value);
		assertEquals("year wrong", 2007, value.getYear());
		assertEquals("mond wrong", 8, value.getMonth());
		assertEquals("day wrong", 17, value.getDay());
		assertEquals("hour wrong", 18, value.getHour());
		assertEquals("minute wrong", 49, value.getMinute());
		assertEquals("second wrong", 30, value.getSecond());
	}
	
	public void testCreateDvText() {
		DvText value = instance.createDvText();
		assertNotNull("dvtext is null", value);
		assertEquals("DvText value wrong", "Blood glucose", value.getValue());
	}
	
	public void testCreateDvTextWithLanguageAndEncoding() {
		DvText value = instance.createDvTextWithLanguageAndEncoding();
		assertNotNull("dvtext is null", value);
		assertEquals("DvCodedText value wrong", "Blood glucose", 
				value.getValue());
		assertEquals("language wrong", TestTerminologyAccess.ENGLISH, 
				value.getLanguage());
		assertEquals("encoding wrong", TestTerminologyAccess.LATIN_1,
				value.getEncoding());
	}
	
	public void testCreateDvCodedText() {
		DvCodedText value = instance.createDvCodedText();
		assertNotNull("DvCodedText is null", value);
		assertEquals("DvCodedText value wrong", "Event", value.getValue());
		assertEquals("defining code wrong", TestTerminologyAccess.EVENT, 
				value.getDefiningCode());
	}
	
	public void testCreateDvQuantity() {
		DvQuantity value = instance.createDvQuantity();
		assertNotNull("dvQuantity is null", value);
		assertEquals("magnitude wrong", 100.0, value.getMagnitude(), 0.000001);
		assertEquals("precision wrong", 0, value.getPrecision());
		assertEquals("units wrong", "mmol/l", value.getUnits());
	}
	
	private DataTypesExercise instance = new DataTypesExercise(); 
}
