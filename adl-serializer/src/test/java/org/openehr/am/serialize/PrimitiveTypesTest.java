package org.openehr.am.serialize;

import java.util.ArrayList;
import java.util.List;

import org.openehr.am.archetype.constraintmodel.primitive.CBoolean;
import org.openehr.am.archetype.constraintmodel.primitive.CDate;
import org.openehr.am.archetype.constraintmodel.primitive.CDateTime;
import org.openehr.am.archetype.constraintmodel.primitive.CInteger;
import org.openehr.am.archetype.constraintmodel.primitive.CReal;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.am.archetype.constraintmodel.primitive.CTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.support.basic.Interval;

public class PrimitiveTypesTest extends SerializerTestBase {

	public PrimitiveTypesTest(String test) {
		super(test);
	}
	
	public void testPrintCString() throws Exception {
		List<String> values = new ArrayList<String>();
		String value = "value";
		String assumed = "assumed value";
		values.add(value);
		CString cstring = new CString(null, values, assumed);
		outputter.printCString(cstring, out);
		verifyByFile("c-string-test.adl");
	}
	
	public void testPrintCBoolean () throws Exception {
		CBoolean cboolean = new CBoolean(true, true, true, true);
		outputter.printCBoolean(cboolean, out);
		verify("true, false; true");
	}
	
	public void testPrintDate() throws Exception {
		DvDate assumed = new DvDate(2001, 10, 20);
		CDate cdate = new CDate("yyyy-mm-dd", null, null, assumed);
		outputter.printCDate(cdate, out);
		verify("yyyy-mm-dd; 2001-10-20");
	}
	
	public void testPrintTime() throws Exception {
		DvTime assumed = new DvTime("00:00:00");
		CTime ctime = new CTime("hh:mm:ss", null, null, assumed);
		outputter.printCTime(ctime, out);
		verify("hh:mm:ss; 00:00:00");
	}
	public void testPrintDateTime() throws Exception {
		DvDateTime assumed = new DvDateTime("2004-10-31T20:10:55");
		String pattern = "yyyy-mm-ddThh:mm:ss";
		CDateTime cdatetime = new CDateTime(pattern, null, null, assumed);
		outputter.printCDateTime(cdatetime, out);
		verify("yyyy-mm-ddThh:mm:ss; 2004-10-31T20:10:55");
	}
	public void testPrintInteger() throws Exception {
		Integer assumed = new Integer(8);
		Interval<Integer> i = new Interval<Integer>(new Integer(1), 
				new Integer(10));
		CInteger ci = new CInteger(i, null, assumed);
		outputter.printCInteger(ci, out);
		verify("|1..10|; 8");
	}
	public void testPrintReal() throws Exception {
		Double assumed = new Double(8.0);
		Interval<Double> i = new Interval<Double>(new Double(1.0),
				new Double(8.0));
		CReal cr = new CReal(i, null, assumed);
		outputter.printCReal(cr, out);
		verify("|1.0..8.0|; 8.0");
	}
}
