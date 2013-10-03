package org.openehr.rm.util;

import org.openehr.rm.composition.content.entry.Observation;

public class TestEvents extends SkeletonGeneratorTestBase {

	public TestEvents() throws Exception {

	}
	
	public void testWithApgarScoreArchetype_PointEvent() throws Exception {
		archetype = loadArchetype("openEHR-EHR-OBSERVATION.apgar.v1.adl");
		instance = generator.create(archetype, GenerationStrategy.MAXIMUM);
		assertTrue("failed to create Action instance", instance instanceof Observation);
	}
	
	/*
	public void testWithBloodPressureArchetype_Interval() throws Exception {
		archetype = loadArchetype("openEHR-EHR-OBSERVATION.blood_pressure.v1.adl");
		instance = generator.create(archetype, GenerationStrategy.MAXIMUM);
		assertTrue("failed to create Action instance", instance instanceof Observation);
	}
	*/

}