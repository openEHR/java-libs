package org.openehr.am.archetype.constraintmodel;

import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.basic.MultiplicityInterval;

import junit.framework.TestCase;

public class CPrimitiveObjectTest extends TestCase {
	
	public void testConstructorWithItem() {
		MultiplicityInterval occurrences = new MultiplicityInterval(1, 1);
		CString item = new CString("file.*", null);
		new CPrimitiveObject("/path", occurrences, null, null, item);		
	}
}
