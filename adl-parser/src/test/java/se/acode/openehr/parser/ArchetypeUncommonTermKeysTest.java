package se.acode.openehr.parser;

import java.util.*;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.ResourceDescriptionItem;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.am.archetype.ontology.ArchetypeTerm;


/**
 * Testcase for uncommon term keys (other than text, description and comment)
 *
 * @author Sebastian Garde
 * @version 1.0
 */
public class ArchetypeUncommonTermKeysTest extends ParserTestBase {

	/**
	 * Verifies the content of description instance after parsing
	 * 
	 * @throws Exception
	 */
	public void testArchetypeUncommonTerm() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.archetype_uncommonkeys.test.adl"));
		Archetype archetype = parser.parse();
		ArchetypeTerm aterm = archetype.getOntology().termDefinition("at0000");
	
		assertEquals("key value wrong", "another key value", aterm.getItem("anotherkey"));
		assertEquals("key value wrong", "test text", aterm.getItem("text"));
		assertEquals("key value wrong", "test description", aterm.getItem("description"));
	}

	
}

