package org.openehr.tutorial.answer;

import java.util.TimeZone;

import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.tutorial.DataTypesExercise;
import org.openehr.tutorial.util.TestTerminologyAccess;

/**
 * Answers of coding exercise on data types classes
 * 
 * @author rong.chen
 */
public class DataTypesAnswer extends DataTypesExercise {
	
	public DvDateTime createDvDateTimeWithISOString() {
		String value = "2007-08-17T18:49:30";
		DvDateTime datetime = new DvDateTime(value);
		return datetime;
	}

	public DvDateTime createDvDateTimeWithIntValues() {
		int year = 2007;
		int month = 8;
		int day = 17;
		int hour = 18;
		int minute = 49;
		int second = 30;
		TimeZone locale = null; // default
		
		DvDateTime datetime = new DvDateTime(year, month, day, hour, minute, 
				second, locale);
		return datetime;
	}
	
	public DvText createDvText() {
		DvText text = new DvText("Blood glucose");
		return text;
	}
	
	public DvText createDvTextWithLanguageAndEncoding() {
		String value = "Blood glucose";
		CodePhrase language = TestTerminologyAccess.ENGLISH;
		CodePhrase encoding = TestTerminologyAccess.LATIN_1;		
		DvText dvtext = new DvText(value, language, encoding, termServ);
		return dvtext;
	}
	
	public DvCodedText createDvCodedText() {
		String value = "Event";
		CodePhrase definingCode = TestTerminologyAccess.EVENT;
		DvCodedText codedtext = new DvCodedText(value, definingCode);
		return codedtext;
	}
	
	public DvQuantity createDvQuantity() {
		String units = "mmol/l";
		double magnitude = 100.0;
		int precision = 0;
		DvQuantity quantity = new DvQuantity(units, magnitude, precision, 
				measureServ);
		return quantity;
	}	
}
