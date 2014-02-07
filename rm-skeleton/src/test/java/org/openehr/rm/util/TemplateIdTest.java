package org.openehr.rm.util;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.content.entry.Observation;


public class TemplateIdTest extends SkeletonGeneratorTestBase {

	public TemplateIdTest() throws Exception {
		super();
	}
	
	public void testWithBloodPressureArchetype() throws Exception {
		archetype = loadArchetype("openEHR-EHR-OBSERVATION.blood_pressure.v2.adl");
		String templateId = "Blood_pressure";
		
		instance = generator.create(archetype, templateId, null,
				GenerationStrategy.MINIMUM);
		
		assertTrue(instance instanceof Observation);
		
		Observation obs = (Observation) instance;
		Archetyped details = obs.getArchetypeDetails();
		assertNotNull(details);
		assertEquals("wrong templateId", templateId, details.getTemplateId().toString());
	}
}