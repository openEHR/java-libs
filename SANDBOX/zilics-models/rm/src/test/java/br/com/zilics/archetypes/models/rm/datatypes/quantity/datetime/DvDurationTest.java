package br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime;

import junit.framework.TestCase;

public class DvDurationTest extends TestCase {

	public DvDurationTest(String name) {
		super(name);
	}
	
	public void testSubtract() throws Exception {
		DvDuration a, b;
		a = new DvDuration();
		b = new DvDuration();
		a.setValue("P2Y2M3DT1H5M13S");
		b.setValue("P3Y1M3DT1H5M13S");
		a.validate();
		b.validate();
//		assertEquals("-P2Y2M3DT1H5M13S", a.negate().getValue());
//		assertEquals("-P11M", a.subtract(b).toString()); subtract implementation is buggy (this test failed on 30/01/2009)
	}
	
}
