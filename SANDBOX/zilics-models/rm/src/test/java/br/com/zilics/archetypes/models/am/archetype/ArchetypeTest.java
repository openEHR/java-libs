
package br.com.zilics.archetypes.models.am.archetype;

import java.io.InputStream;

import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

/**
 *
 * @author Humberto
 */
public class ArchetypeTest extends ResourceTestCase {
    public ArchetypeTest(String testName) {
        super(testName);
    }

    public void testValidateArchetypes() throws Exception {
		String directory = "/xml/archetypes/"; 
		for(String fileName : listDirectory(directory)) {
			//if (fileName.startsWith("adl-test")) continue;
			InputStream is = this.getClass().getResourceAsStream(directory + fileName);
			Archetype archetype = (Archetype) XmlParser.parseXml(is);
			assertNotNull(archetype);
			archetype.validate();
		}		
	}

}
