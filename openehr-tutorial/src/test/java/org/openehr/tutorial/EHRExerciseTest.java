package org.openehr.tutorial;

import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.entry.Observation;

import junit.framework.TestCase;

public class EHRExerciseTest extends TestCase {
	
	public void testCreateComposition() {
		Composition value = instance.createComposition();
		assertNotNull("composition is null", value);
	}
	
	public void testCreateObservation() {
		Observation value = instance.createObservation();
		assertNotNull("observation is null", value);
	}
	
	private EHRExercise instance = new EHRExercise();
}
