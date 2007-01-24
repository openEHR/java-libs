package org.openehr.am.serialize;

import java.util.LinkedHashSet;
import java.util.Set;

import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.rm.datatypes.text.CodePhrase;

public class CDvOrdinalTest extends SerializerTestBase {

	public CDvOrdinalTest(String test) {
		super(test);
	}

	public void testPrintCDvOrdinal() throws Exception {
		Set<Ordinal> list = new LinkedHashSet<Ordinal>();
		for (int i = 1; i <= 4; i++) {
			list.add(new Ordinal(i, new CodePhrase("local", "at200" + i)));
		}
		Ordinal assumed = new Ordinal(1, new CodePhrase("local", "at2001"));
		cordinal = new CDvOrdinal("/path", null, null, null, list, 
				null, assumed);
		clean();
		outputter.printCDvOrdinal(cordinal, 0, out);
		verifyByFile("c-dv-ordinal-test.adl");
	}
	
	private CDvOrdinal cordinal;
}

