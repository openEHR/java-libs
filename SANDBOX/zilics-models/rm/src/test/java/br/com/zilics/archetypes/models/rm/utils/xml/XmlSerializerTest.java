package br.com.zilics.archetypes.models.rm.utils.xml;

import java.io.InputStream;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Element;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvCount;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class XmlSerializerTest extends ResourceTestCase {
	public XmlSerializerTest(String testName) {
		super(testName);
	}
	
	
	public void testSerializeElement() throws Exception {
		Element element = new Element();
		element.setName(new DvText());
		element.getName().setValue("some element");
		element.setArchetypeNodeId("at0001");
		
		DvCount value = new DvCount();
		element.setValue(value);
		value.setMagnitude(12L);
		value.setMagnitudeStatus("=");
		
		String expected =
			"<element xmlns=\"http://schemas.openehr.org/v1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" archetype_node_id=\"at0001\">\r\n" +
				"  <name>\r\n" +
				"    <value>some element</value>\r\n" +
				"  </name>\r\n" +
				"  <value xsi:type=\"DV_COUNT\">\r\n" +
				"    <magnitude_status>=</magnitude_status>\r\n" +
				"    <accuracy>0.0</accuracy>\r\n" +
				"    <accuracy_is_percent>false</accuracy_is_percent>\r\n" +
				"    <magnitude>12</magnitude>\r\n" +
				"  </value>\r\n" +
				"</element>";

		String xml = XmlSerializer.serializeXml(element, null);
		assertEquals(expected, xml);
	}
	
	public void testSerializeWithADifferentName() throws Exception {
		DvText text = new DvText();
		text.setValue("Some text value");
		String expected =
			"<text xmlns=\"http://schemas.openehr.org/v1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"DV_TEXT\">\r\n" +
			"  <value>Some text value</value>\r\n" +
			"</text>";
		String xml = XmlSerializer.serializeXml(text, "text");
		assertEquals(expected, xml);
	}

	public void testSerializeWithoutPrettyPrint() throws Exception {
		DvText text = new DvText();
		text.setValue("Some text value");
		String expected =
			"<dv_text xmlns=\"http://schemas.openehr.org/v1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
			  "<value>Some text value</value>" +
			"</dv_text>";
		String xml = XmlSerializer.serializeXml(text, null, false);
		assertEquals(expected, xml);
	}

	private void parseAndSerialize(String directory) throws Exception {
		for(String fileName : listDirectory(directory)) {
			InputStream is = this.getClass().getResourceAsStream(directory + fileName);
			RMObject obj = XmlParser.parseXml(is);
			String serialized = XmlSerializer.serializeXml(obj, null);
			assertNotNull(serialized);
			XmlParser.parseXml(serialized);
		}
	}

	public void testParseAndSerializeArchetypes() throws Exception {
		parseAndSerialize("/xml/archetypes/");
	}

	public void testParseAndSerializeTemplates() throws Exception {
		parseAndSerialize("/xml/templates/");
	}
	
	public void testParseAndSerializeCompositions() throws Exception {
		parseAndSerialize("/xml/compositions/");
	}

	public void testParseAndSerializeVersions() throws Exception {
		parseAndSerialize("/xml/versions/");
	}
	
	
}
