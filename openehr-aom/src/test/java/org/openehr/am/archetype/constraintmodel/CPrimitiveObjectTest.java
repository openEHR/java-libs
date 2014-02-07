package org.openehr.am.archetype.constraintmodel;

import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.rm.support.basic.Interval;

import junit.framework.TestCase;

public class CPrimitiveObjectTest extends TestCase {
	
	public void testConstructorWithItem() {
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		CString item = new CString("file.*", null);
		new CPrimitiveObject("/path", occurrences, null, null, item);		
	}
}
