package org.openehr.rm.datatypes.basic;

import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

import junit.framework.TestCase;

public class ParseDataValueTest extends TestCase {

	public void setUp() {
		dv = null;
	}
	
	public void testParseDvCount() {
		dv = DataValue.parseValue("DV_COUNT,1");
		assertEquals(dv, new DvCount(1));		
	}
	
	public void testDvCountRoundTrip() {
		DvCount count = new DvCount(3);
		String s = count.serialise();
		assertEquals("DvCount round trip failed", count, DataValue.parseValue(s));
	}
	
	public void testParseDvText() {
		dv = DataValue.parseValue("DV_TEXT,some text");
		assertEquals(dv, new DvText("some text"));
	}
	
	public void testDvTextRoundTrip() {
		DvText text = new DvText("jag heter..");
		String s = text.serialise();
		assertEquals("DvText round trip failed", text, DataValue.parseValue(s));
	}
	
	public void testParseCodePhrase() {
		dv = DataValue.parseValue("CODE_PHRASE,SNOMEDCT::12345678");
		assertEquals(dv, new CodePhrase("SNOMEDCT", "12345678"));
	}
	
	public void testCodePhraseRoundTrip() {
		CodePhrase cp = new CodePhrase("SNOMEDCT", "22334455");
		String s = cp.serialise();
		assertEquals("Code Phrase round trip failed", cp, DataValue.parseValue(s));
	}
	
	public void testParseDvQuantity() {
		dv = DataValue.parseValue("DV_QUANTITY,1.5,mg");
		assertEquals(dv, new DvQuantity("mg", 1.5, 1));
	}
	
	public void testDvQuantityRoundTrip() {
		DvQuantity dq = new DvQuantity("mg", 1.5, 3);
		String s = dq.serialise();
		assertEquals("DvQuantity round trip failed", dq, DataValue.parseValue(s));
	}
	
	public void testParseDvDateTime() {
		dv = DataValue.parseValue("DV_DATE_TIME,2004-10-31T20:10:55.000+01:00");
		assertEquals(dv, new DvDateTime("2004-10-31T20:10:55.000+01:00"));
	}
	
	public void testDvDateTimeRoundTrip() {
		DvDateTime datetime = new DvDateTime("2004-10-31T20:10:55.000+01:00");
		String s = datetime.serialise();
		assertEquals("DvDateTime round trip failed", datetime, DataValue.parseValue(s));
	}
	
	public void testParseDuration() {
		dv = DataValue.parseValue("DV_DURATION,P10D");
		assertEquals(dv, new DvDuration("P10D"));
	}
	
	public void testDurationRoundTrip() {
		DvDuration duration = new DvDuration("P1Y");
		String s = duration.serialise();
		assertEquals("DvDuration round trip failed", duration, DataValue.parseValue(s));
	}
	
	public void testCodedTextRoundTrip() {
		DvCodedText coded = new DvCodedText("coded text", "snomed", "123456");
		String s = coded.serialise();
		assertEquals("DvCodedText round trip failed", coded, DataValue.parseValue(s));
	}

	public void testParsableRoundTrip() {
		DvParsable parsable = new DvParsable("text", "txt");
		String s = parsable.serialise();
		assertEquals("DvCodedText round trip failed", parsable, DataValue.parseValue(s));
	}

	// test instance
	private DataValue dv;
}
