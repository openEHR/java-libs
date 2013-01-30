package br.com.zilics.archetypes.models.adl.serializer;

import java.io.InputStream;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class ADLSerializerTest extends ResourceTestCase {
	private ADLSerializer serializer;
	
	public ADLSerializerTest(String testName) {
		super(testName);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		serializer = new ADLSerializer();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		serializer = null;
	}
	
	public void testSerialize() throws Exception {
		String directoryName = "/xml/archetypes/";
		for(String fileName : listDirectory(directoryName)) {
			InputStream is = this.getClass().getResourceAsStream(directoryName + fileName);
			Archetype archetype = (Archetype) XmlParser.parseXml(is);
			String output = serializer.output(archetype);
			System.out.println(output);
			assertNotNull(output);
		}
	}
}
