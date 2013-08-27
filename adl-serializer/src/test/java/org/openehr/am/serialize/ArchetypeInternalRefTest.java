package org.openehr.am.serialize;

import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.rm.support.basic.Interval;

public class ArchetypeInternalRefTest extends SerializerTestBase {

	public void testPrintArchetypeInternalRef() throws Exception {	
		String path = "/attribute2";
		String type = "SECTION";
		Interval<Integer> occurrences = new Interval<Integer>(1, 2);
		CAttribute parent = null;
		String target = "/attribute1";
		
		ArchetypeInternalRef ref = new ArchetypeInternalRef(path, type, 
				occurrences, null, parent, target);
		clean();
		outputter.printArchetypeInternalRef(ref, 0, out);
		verifyByFile("archetype-internal-ref-test.adl");
	}
}
