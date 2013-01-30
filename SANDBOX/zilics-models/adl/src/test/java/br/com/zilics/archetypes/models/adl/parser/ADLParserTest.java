package br.com.zilics.archetypes.models.adl.parser;

import java.io.InputStream;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class ADLParserTest extends ResourceTestCase {
	public ADLParserTest(String testName) {
		super(testName);
	}
	
	public void testParse() throws Exception {
		String directoryName = "/adl/";
		for(String fileName : listDirectory(directoryName)) {
			InputStream is = this.getClass().getResourceAsStream(directoryName + fileName);
			ADLParser parser = new ADLParser(is);
			Archetype archetype = parser.parse();
			assertNotNull(archetype);
			//System.out.println(XmlSerializer.serializeXml(archetype, null));
			archetype.validate();
		}
	}
}
