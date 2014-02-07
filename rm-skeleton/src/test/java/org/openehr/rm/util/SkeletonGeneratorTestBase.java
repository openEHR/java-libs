package org.openehr.rm.util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import openEHR.v1.template.TEMPLATE;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DADLParser;
import org.openehr.am.serialize.ADLSerializer;
import org.openehr.am.template.Flattener;
import org.openehr.am.template.OETParser;
import org.openehr.binding.XMLBinding;
import org.openehr.rm.binding.DADLBinding;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.schemas.v1.COMPOSITION;
import org.openehr.schemas.v1.CompositionDocument;

import com.thoughtworks.xstream.XStream;

import se.acode.openehr.parser.ADLParser;
import junit.framework.TestCase;

public class SkeletonGeneratorTestBase extends TestCase {
	
	public SkeletonGeneratorTestBase() throws Exception {
		dadlBinding = new DADLBinding();	
		xmlBinding = new XMLBinding();
		generator = SkeletonGenerator.getInstance();
		oetParser = new OETParser();
		flattener = new Flattener();
		adlSerializer = new ADLSerializer();
		measurementService = SimpleMeasurementService.getInstance();
		
		setXmlOptions();
		loadArchetypeMap();
	}
	
	private void setXmlOptions() {
		xmlOptions = new XmlOptions();
		xmlOptions.setSavePrettyPrint();
		xmlOptions.setSaveAggressiveNamespaces();
		xmlOptions.setSaveOuter();
		xmlOptions.setSaveInner();
	}
	
	public void tearDown() {
		instance = null;
		expected = null;
	}
	
	protected Archetype loadArchetype(String id) throws Exception {
		ADLParser adlParser = new ADLParser(fromClasspath(ARCHETYPE_PATH + id));
		archetype = adlParser.parse();
		return archetype;
    }
	
	protected InputStream loadXML(String filename) throws Exception {
		return fromClasspath(XML_PATH + filename);
	}	
	
	/* load a test composition in xml format */
	protected Composition loadXMLComposition(String filename) throws Exception {
		COMPOSITION comp = CompositionDocument.Factory.parse(
				loadXML(filename)).getComposition();
		
		assertTrue("expected composition, but got: " 
				+ (comp == null ? null : comp.getClass()),
				comp instanceof COMPOSITION);

		Object rmObj = xmlBinding.bindToRM(comp);
		
		assertTrue("rmObj not a Composition, got "
				+ (rmObj == null ? null : rmObj.getClass()),
				rmObj instanceof Composition);
		
		return (Composition) rmObj;	
	}
	
	protected InputStream fromClasspath(String filename) throws Exception {
		return this.getClass().getClassLoader().getResourceAsStream(filename);
	}
	
	protected Archetype flattenTemplate(String templateFilename) throws Exception {
		TEMPLATE template = loadTemplate(templateFilename);
		Archetype flattened = flattener.toFlattenedArchetype(template, 
				archetypeMap, null);
		return flattened;
	}
	
	protected TEMPLATE loadTemplate(String name) throws Exception {
		String path = TEMPLATE_PATH + name + ".oet";
		TEMPLATE template = oetParser.parseTemplate(
				fromClasspath(path)).getTemplate();
		return template;
	}
	
	/*
	 * Loads dadl from file and binds data to RM object
	 */
	protected Object loadData(String filename) throws Exception {
		DADLParser parser = new DADLParser(fromClasspath(DATA_PATH + filename));
		ContentObject contentObj = parser.parse();
		return  dadlBinding.bind(contentObj);
	}
	
	protected void loadArchetypeMap() throws Exception {
		String[] ids = {
				"openEHR-EHR-ITEM_TREE.medication_test_one.v1",
				"openEHR-EHR-SECTION.medications.v1",
				"openEHR-EHR-INSTRUCTION.medication.v1",
				"openEHR-EHR-ITEM_TREE.medication.v1",
				"openEHR-EHR-SECTION.ad_hoc_heading.v1"
		};
		
		archetypeMap = new HashMap<String, Archetype>();
		
		for(String id : ids) {
			Archetype archetype = loadArchetype(id + ".adl");
			archetypeMap.put(archetype.getArchetypeId().toString(), archetype);
		}			
	}
	
	protected void printXML(Object obj) throws Exception {
		Object value = xmlBinding.bindToXML(obj);
		XmlObject xmlObj = (XmlObject) value;
		xmlObj.save(System.out, xmlOptions);
	}	
	
	protected void saveXML(Object obj, String filename) throws Exception {
		Object value = xmlBinding.bindToXML(obj);
		XmlObject xmlObj = (XmlObject) value;
		xmlObj.save(new File(filename), xmlOptions);
	}
	
	protected void printStreamXML(Object toPrint) throws Exception {
		XStream xstream = new XStream();
		String xml = xstream.toXML(toPrint);
		System.out.println(xml);
	}
	
	/**
	 * Print out given archetype in ADL for debugging purpose
	 * 
	 * @param toPrint
	 * @throws Exception
	 */
	protected void printADL(Archetype toPrint) throws Exception {
		adlSerializer.output(toPrint, System.out);
	}
	
	protected DADLBinding dadlBinding; 	
	private XMLBinding xmlBinding;
	private OETParser oetParser;
	private Flattener flattener;
	private ADLSerializer adlSerializer;
	private XmlOptions xmlOptions;
	private MeasurementService measurementService;
	
	protected Map<String, Archetype> archetypeMap;	
	protected SkeletonGenerator generator;
	protected Object instance;
	protected Object expected;
	protected Archetype archetype;
	
	/* path for test fixtures */
	private static final String ARCHETYPE_PATH = "adl/";
	private static final String DATA_PATH = "dadl/";
	private static final String TEMPLATE_PATH = "oet/";
	private static final String XML_PATH = "xml/";
}
