package org.openehr.tutorial;

import org.openehr.am.archetype.Archetype;
import org.openehr.tutorial.answer.ADLAnswer;

import junit.framework.TestCase;

public class ADLExerciseTest extends TestCase {
	
	public void testParseADL() throws Exception {
		Archetype archetype = instance.parseADL();
		assertNotNull("archetype is null", archetype);
	}
	
	public void testSerializeArchetypeIntoADL() throws Exception {
		String value = instance.serializeArchetypeToADL();
		assertNotNull("adl string is null", value);
	}
	
	private ADLExercise instance = new ADLExercise();
}
