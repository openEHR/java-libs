package org.openehr.tutorial.answer;

import org.openehr.am.serialize.ADLSerializer;
import org.openehr.am.archetype.Archetype;
import org.openehr.tutorial.ADLExercise;

import se.acode.openehr.parser.ADLParser;

/**
 * Answers of ADL related coding exercise 
 * 
 * @author rong.chen
 */
public class ADLAnswer extends ADLExercise {

	public Archetype parseADL() throws Exception {
		String adlfile = "openEHR-EHR-OBSERVATION.laboratory-glucose.v1.adl";
		ADLParser parser = new ADLParser(loadFromClasspath(adlfile));
		Archetype archetype = parser.parse();
		return archetype;
	}
	
	public String serializeArchetypeToADL() throws Exception {		
		String adlfile = "openEHR-EHR-OBSERVATION.laboratory-glucose.v1.adl";
		ADLParser parser = new ADLParser(loadFromClasspath(adlfile));
		Archetype archetype = parser.parse();
		
		ADLSerializer serializer = new ADLSerializer();
		String adl = serializer.output(archetype);
		return adl;
	}	
}
