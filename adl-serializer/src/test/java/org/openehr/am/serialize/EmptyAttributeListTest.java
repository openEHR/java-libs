package org.openehr.am.serialize;

import java.util.List;

import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.CAttribute.Existence;
import org.openehr.rm.support.basic.Interval;

public class EmptyAttributeListTest extends SerializerTestBase {
	
	public void testOutputCComplexObjectWithEmptyAttributeList() throws Exception {
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		List<CAttribute> attributes = null;
		CComplexObject ccobj = new CComplexObject("/path", "DV_TEXT", 
				occurrences, "at0001", attributes, null);
		outputter.printCComplexObject(ccobj, 0, out);
        verifyByFile("empty-attribute-list-test.adl");
	}
	
	public void testOutputCAttributeWithEmptyChildrenList() throws Exception {
		
		List<CObject> children = null;
		CSingleAttribute cattr = new CSingleAttribute("/path", "value", 
				Existence.REQUIRED, children);
		
		outputter.printCAttribute(cattr, 0, out);        
		verifyByFile("empty-children-list-test.adl");
	}
}
