package org.openehr.tutorial;

import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

import junit.framework.TestCase;

/**
 * Testcases that verify the coding exercises are properly done
 * 
 * @author rong.chen
 */
public class DataTypesExerciseTest extends TestCase {
	
	public void testCreateDvDateTime() {
		DvDateTime value = instance.createDvDateTime();
		assertNotNull("datetime is null", value);		
	}
	
	public void testCreateDvText() {
		DvText value = instance.createDvText();
		assertNotNull("dvtext is null", value);
	}
	
	public void testCreateDvCodedText() {
		DvCodedText value = instance.createDvCodedText();
		assertNotNull("dvCodedText is null", value);
	}
	
	public void testCreateDvQuantity() {
		DvQuantity value = instance.createDvQuantity();
		assertNotNull("dvQuantity is null", value);
	}
	
	private DataTypesExercise instance = new DataTypesExercise(); 
}
