package br.com.zilics.archetypes.models.rm.utils.xml;

import java.io.InputStream;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Element;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvCount;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;
import br.com.zilics.archetypes.models.rm.support.identification.ArchetypeID;
import br.com.zilics.archetypes.models.test.ResourceTestCase;

public class XmlParserTest extends ResourceTestCase {
	private static final String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private static final String namespace = "xmlns=\"http://schemas.openehr.org/v1\" " +
		"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ";
	public XmlParserTest(String testName) {
		super(testName);
	}
	
	private RMObject parseXml(String rootElement, String innerXml) throws Exception {
		return XmlParser.parseXml(header + "<" + rootElement + " " + namespace + ">" + innerXml + "</" + rootElement + ">");
	}
	
	public void testParseArchetypeID() throws Exception {
		RMObject obj = parseXml("archetype_id", "<value>Teste</value>");
		assertNotNull(obj);
		assertTrue(obj instanceof ArchetypeID);
		assertEquals("Teste", ((ArchetypeID) obj).getValue());
	}
	
	public void testParseElement() throws Exception {
		String xml =
			"<element xmlns=\"http://schemas.openehr.org/v1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" archetype_node_id=\"at0001\">\r\n" +
			"  <value xsi:type=\"DV_COUNT\">\r\n" +
			"    <magnitude>12</magnitude>\r\n" +
			"    <accuracy>0.0</accuracy>\r\n" +
			"    <accuracy_is_percent>false</accuracy_is_percent>\r\n" +
			"    <magnitude_status>=</magnitude_status>\r\n" +
			"  </value>\r\n" +
			"  <name>\r\n" +
			"    <value>some element</value>\r\n" +
			"  </name>\r\n" +
			"</element>";
		RMObject obj = XmlParser.parseXml(xml);
		assertTrue(obj instanceof Element);
		Element element = (Element) obj;
		assertNotNull(element);
		assertNotNull(element.getName());
		assertNotNull(element.getValue());
		assertTrue(element.getValue() instanceof DvCount);
		assertEquals("some element", element.getName().getValue());
		DvCount value = (DvCount) element.getValue();
		assertEquals(12L, value.getMagnitude().longValue());
		assertEquals("=", value.getMagnitudeStatus());
	}
	
	public void testParseWithADifferentName() throws Exception {
		String xml =
			"<text xmlns=\"http://schemas.openehr.org/v1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"DV_TEXT\">\r\n" +
			"  <value>Some text value</value>\r\n" +
			"</text>";
		RMObject obj = XmlParser.parseXml(xml);
		assertTrue(obj instanceof DvText);
		DvText text = (DvText) obj;
		assertEquals("Some text value", text.getValue());
	}
	
	private void parse(String directory) throws Exception {
		for(String fileName : listDirectory(directory)) {
			InputStream is = this.getClass().getResourceAsStream(directory + fileName);
			RMObject obj = XmlParser.parseXml(is);
			assertNotNull(obj);
		}		
	}

	public void testParseArchetypes() throws Exception {
		parse("/xml/archetypes/");
	}

	public void testParseTemplates() throws Exception {
		parse("/xml/templates/");
	}

	public void testParseCompositions() throws Exception {
		parse("/xml/compositions/");
	}
	
	public void testParseVersions() throws Exception {
		parse("/xml/versions/");
	}
	
}
