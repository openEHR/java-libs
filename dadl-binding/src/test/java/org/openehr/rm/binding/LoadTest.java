package org.openehr.rm.binding;

import org.openehr.rm.composition.content.entry.Observation;

public class LoadTest extends DADLBindingTestBase {
	
	public void testLoadHeight() throws Exception {
		rmObj = bind("observation2.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Observation: "	+ rmObj.getClass().getName(), 
				rmObj instanceof Observation);
	}
	
	public void testLoadBodyWegith() throws Exception {
		rmObj = bind("body_weight.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Observation: "	+ rmObj.getClass().getName(), 
				rmObj instanceof Observation);
	}
	
	public void testLoadDemographics() throws Exception {
		rmObj = bind("demographics.dadl");
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Observation: "	+ rmObj.getClass().getName(), 
				rmObj instanceof Observation);
	}
}
