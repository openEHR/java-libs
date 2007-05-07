package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;

public class DvCodedTextTest extends ParserTestBase {

	public void testParse() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-composition.dv_coded_text.test.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
        
        CObject node = archetype.node("/category/defining_code");
        assertTrue("CCodePhrase expected, but got " + node.getClass(),
        		node instanceof CCodePhrase);
        CCodePhrase ccp = (CCodePhrase) node;
        assertEquals("terminologyId wrong", "openehr", 
        		ccp.getTerminologyId().toString());
        assertEquals("codeString wrong", "431", ccp.getCodeList().get(0));
	}
}
