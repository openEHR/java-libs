package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.primitive.CDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.basic.Interval;

public class CDurationTest extends ParserTestBase {

	/**
	 * Create new test case
	 * 
	 * @param test
	 * @throws Exception
	 */
	public CDurationTest() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.durations.test.adl"));
		archetype = parser.parse();
	}

	/**
	 * Tests duration constraints parsing
	 * 
	 * @throws Exception
	 */
	public void testParseCDuration() throws Exception {

		assertCDuration(archetype.node("/types[at0001]/items[at1001]/value"), 
				"PT0s", null);

		assertCDuration(archetype.node("/types[at0001]/items[at1002]/value"), 
				"P1d", null);

		assertCDuration(archetype.node("/types[at0001]/items[at1003]/value"), 
				"PT2h5m0s",	null);

		assertCDuration(archetype.node("/types[at0001]/items[at1004]/value"), 
				null,
				new Interval<DvDuration>(DvDuration.getInstance("PT1h55m0s"), 
						DvDuration.getInstance("PT2h5m0s")));

		assertCDuration(archetype.node("/types[at0001]/items[at1005]/value"),
				null,
				new Interval<DvDuration>(null, DvDuration.getInstance("PT1h"), 
						false, true));
		
		assertCDuration(archetype.node("/types[at0001]/items[at1006]/value"), 
				"P1DT1H2M3S", null);
		
		// bug fix for ISO durationg with weeks
		assertCDuration(archetype.node("/types[at0001]/items[at1007]/value"), 
				"P1W2DT1H2M3S", null);
		
		// bug fix for ISO duration with months
		assertCDuration(archetype.node("/types[at0001]/items[at1008]/value"), 
				"P3M1W2DT1H2M3S", null);

		// to supported newly added duration pattern
		assertCDuration(archetype.node("/types[at0001]/items[at1009]/value"), 
				null, null, null, "PDTH");		
	}
	
	/**
	 * Verifies the support for "|PT10M|", single duration interval
	 */
	public void testParseSingleDurationInverval() throws Exception {
		Interval<DvDuration> interval = new Interval<DvDuration> (
				DvDuration.getInstance("PT10M"), null);
		assertCDuration(archetype.node("/types[at0001]/items[at1010]/value"), 
				null, interval, null, null);
		
		// test with assumed value
		assertCDuration(archetype.node("/types[at0002]/items[at1010]/value"), 
				null, interval, "PT12M", null);
	}
	
	/**
	 * Tests parsing CDurations with assumed values
	 * 
	 * @throws Exception
	 */
	public void testParseCDurationWithAssumedValue() throws Exception {
		assertCDuration(archetype.node("/types[at0002]/items[at1001]/value"), 
				"PT0s", null, "P1d");

		assertCDuration(archetype.node("/types[at0002]/items[at1002]/value"), 
				"P1d", null, "P1d");

		assertCDuration(archetype.node("/types[at0002]/items[at1003]/value"), 
				"PT2h5m0s",	null, "P1d");

		assertCDuration(archetype.node("/types[at0002]/items[at1004]/value"), 
				null,
				new Interval<DvDuration>(DvDuration.getInstance("PT1h55m0s"), 
						DvDuration.getInstance("PT2h5m0s")), "P1d");

		assertCDuration(archetype.node("/types[at0002]/items[at1005]/value"), 
				null,
				new Interval<DvDuration>(null, DvDuration.getInstance("PT1h"), 
						false, true), "P1d");
		// to supported newly added duration pattern
		assertCDuration(archetype.node("/types[at0002]/items[at1006]/value"), 
				null, null, "P1d", "PDTH");
	}

	private Archetype archetype;
}
