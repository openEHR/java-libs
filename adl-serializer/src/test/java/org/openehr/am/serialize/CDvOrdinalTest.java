package org.openehr.am.serialize;

import java.util.*;

import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;

public class CDvOrdinalTest extends SerializerTestBase {

	public void testPrintCDvOrdinalWithAssumedValue() throws Exception {
		List<Ordinal> list = new ArrayList<Ordinal>();
		for (int i = 1; i <= 4; i++) {
			list.add(new Ordinal(i, new CodePhrase("local", "at200" + i)));
		}
		Ordinal assumed = new Ordinal(1, new CodePhrase("local", "at2001"));
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		cordinal = new CDvOrdinal("/path", occurrences, null, null, list, 
				null, assumed);
		clean();
		outputter.printCDvOrdinal(cordinal, 0, out);
		verifyByFile("c-dv-ordinal-test.adl");
	}
	
	public void testPrintCDvOrdinal() throws Exception {
		List<Ordinal> list = new ArrayList<Ordinal>();
		for (int i = 1; i <= 4; i++) {
			list.add(new Ordinal(i, new CodePhrase("local", "at200" + i)));
		}
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		cordinal = new CDvOrdinal("/path", occurrences, null, null, list, 
				null, null);
		clean();
		outputter.printCDvOrdinal(cordinal, 0, out);
		verifyByFile("c-dv-ordinal-test2.adl");
	}

	public void testPrintEmptyCDvOrdinal() throws Exception {
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		cordinal = new CDvOrdinal("/path", occurrences, null, null, null,
				null, null);
		clean();
		outputter.printCDvOrdinal(cordinal, 0, out);
		verifyByFile("c-dv-ordinal-test-empty.adl");
	}
	
	private CDvOrdinal cordinal;
}

