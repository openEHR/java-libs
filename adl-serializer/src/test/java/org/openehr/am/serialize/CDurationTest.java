package org.openehr.am.serialize;

import org.openehr.am.archetype.constraintmodel.primitive.CDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.basic.Interval;

public class CDurationTest extends SerializerTestBase {

	public CDurationTest(String test) {
		super(test);
	}

	public void testCDurationWithDayValue() throws Exception {
		// 5 day 
		DvDuration duration = new DvDuration(0, 0, 0, 5, 0, 0, 0, 0.0);
		cd = new CDuration(duration, null, null, null);
		outputter.printCDuration(cd, out);
		verify("P5D");
	}
	
	public void testCDurationWithHourValue() throws Exception {
		// 1 hour 
		DvDuration duration = new DvDuration(0, 0, 0, 0, 1, 0, 0, 0.0);
		cd = new CDuration(duration, null, null, null);
		outputter.printCDuration(cd, out);
		verify("PT1H");
	}

	public void testCDurationWithInterval() throws Exception {
		DvDuration d1 = new DvDuration(0, 0, 0, 0, 0, 0, 0, 0.0);
		DvDuration d2 = new DvDuration(0, 0, 0, 0, 0, 1, 30, 0.0);
		Interval<DvDuration> i = new Interval<DvDuration>(d1, d2);
		cd = new CDuration(null, i, null, null);
		outputter.printCDuration(cd, out);
		verify("|PT0S..PT1M30S|");
	}

	public void testCDurationWithPattern() throws Exception {
		String pattern = "PD";
		cd = new CDuration(null, null, null, pattern);
		outputter.printCDuration(cd, out);
		verify("PD");
	}

	public void testCDurationWithPatternAssumedValue() throws Exception {
		String pattern = "PD";
		DvDuration assumed = new DvDuration(0, 0, 0, 1, 0, 0, 0, 0.0);
		cd = new CDuration(null, null, assumed, pattern);
		outputter.printCDuration(cd, out);
		verify("PD; P1D");
	}
	
	public void testCDurationWithIntervalAssumedValue() throws Exception {
		DvDuration d1 = new DvDuration(0, 0, 0, 0, 0, 0, 0, 0.0);
		DvDuration d2 = new DvDuration(0, 0, 0, 0, 0, 1, 30, 0.0);
		Interval<DvDuration> i = new Interval<DvDuration>(d1, d2);
		DvDuration assumed = new DvDuration(0, 0, 0, 1, 0, 0, 0, 0.0);
		cd = new CDuration(null, i, assumed, null);
		outputter.printCDuration(cd, out);
		verify("|PT0S..PT1M30S|; P1D");
	}

	private CDuration cd;
}
