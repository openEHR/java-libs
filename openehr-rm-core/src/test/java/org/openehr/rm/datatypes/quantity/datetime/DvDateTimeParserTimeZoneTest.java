package org.openehr.rm.datatypes.quantity.datetime;

import java.util.TimeZone;

import junit.framework.TestCase;

public class DvDateTimeParserTimeZoneTest extends TestCase {
	public DvDateTimeParserTimeZoneTest() {
		defaultTimeZone = TimeZone.getDefault();		
	}
	
	public void setUp() throws Exception {
		System.setProperty("user.timezone", "GMT-3:00");
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3:00"));    	    	
	}	
	
	public void tearDown() throws Exception {
		TimeZone.setDefault(defaultTimeZone);
	}
	
	public void testParseTime() {
		try {
			DvDateTimeParser.parseTime("010000");
		} catch(Exception e) {
			fail("failed to parse 010000 in GMT-3 timezone");
		} 
	}
	
	private TimeZone defaultTimeZone;
}
