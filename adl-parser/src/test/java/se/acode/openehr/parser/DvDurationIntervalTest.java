package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.support.basic.Interval;

public class DvDurationIntervalTest extends ParserTestBase {

    /**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public DvDurationIntervalTest() throws Exception {
        ADLParser parser = new ADLParser(
                loadFromClasspath("openEHR-EHR-CLUSTER.duration_interval_test.v0.adl"));
        archetype = parser.parse();
    }

    public void testParse() throws Exception {
        // This test is mainly to ensure all examples parse ok.
        // In addition, this is one test to ensure the special case of >X is working in the constraint. (No incorrect upper limit is imposed)
        Interval<DvDuration> interval = new Interval<DvDuration>(
                DvDuration.getInstance("PT0M"), null, false, true);

        assertCDuration(archetype.node("/items[at0003]/value/value"), null, interval);

    }

    private Archetype archetype;
}
