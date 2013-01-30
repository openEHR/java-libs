package org.openehr.tutorial;

import java.io.InputStream;
import org.openehr.am.archetype.Archetype;

/**
 * Coding exercises for ADL parsing into archetypes in AOM form
 * and serialize archetype into ADL format 
 */
public class ADLExercise extends ExerciseBase {
	
	public Archetype parseADL() throws Exception {
		return null;
	}
	
	public String serializeArchetypeToADL() throws Exception {
		return null;
	}
	
	protected InputStream loadFromClasspath(String adl) throws Exception {
    	return this.getClass().getClassLoader().getResourceAsStream(adl);
    }
}
