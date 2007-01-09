package org.openehr.am.archetype.constraintmodel;

import org.openehr.am.archetype.constraintmodel.primitive.CString;

import junit.framework.TestCase;

public class CPrimitiveObjectTest extends TestCase {
	
	public void testConstructorWithItem() {
		CString item = new CString("file.*", null);
		new CPrimitiveObject("/path", null, null, null, null, item);		
	}
}
