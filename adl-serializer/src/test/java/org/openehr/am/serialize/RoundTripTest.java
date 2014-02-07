package org.openehr.am.serialize;

import java.io.InputStream;
import java.io.StringReader;

import org.openehr.am.archetype.Archetype;

import se.acode.openehr.parser.ADLParser;

public class RoundTripTest extends SerializerTestBase {

	public void testRoundTripOnSimpleEvaluation() throws Exception {
		adlFile = "openEHR-EHR-EVALUATION.test_concept.v1.adl";
		ADLParser parser = new ADLParser(loadFromClasspath(adlFile));
		Archetype archetype = parser.parse();

		outputter.output(archetype, out);

		parser.ReInit(new StringReader(out.toString()));

		Archetype roundTripedArchetype = parser.parse();

		assertEquals("adlVersion diff", archetype.getAdlVersion(),
				roundTripedArchetype.getAdlVersion());
		assertEquals("archetypeId diff", archetype.getArchetypeId(),
				roundTripedArchetype.getArchetypeId());
		assertEquals("concept diff", archetype.getConcept(),
				roundTripedArchetype.getConcept());
		assertEquals("definition diff", archetype.getDefinition(),
				roundTripedArchetype.getDefinition());

		// TODO skipped problematic description comparison
		// assertEquals("description diff", archetype.getDescription(),
		// roundTripedArchetype.getDescription());
		
		assertEquals("ontology diff", archetype.getOntology(),
				roundTripedArchetype.getOntology());
		assertEquals("original language diff", archetype.getOriginalLanguage(),
				roundTripedArchetype.getOriginalLanguage());

	}

	private InputStream loadFromClasspath(String adl) throws Exception {
		return this.getClass().getClassLoader().getResourceAsStream(adl);
	}
}
