package org.openehr.am.template;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import openEHR.v1.template.TEMPLATE;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.Cardinality;
import org.openehr.am.archetype.constraintmodel.ConstraintRef;
import org.openehr.am.archetype.constraintmodel.primitive.CPrimitive;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.am.openehrprofile.datatypes.basic.CDvState;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.am.serialize.ADLSerializer;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;

import com.thoughtworks.xstream.XStream;

import se.acode.openehr.parser.ADLParser;

import junit.framework.TestCase;

public class TemplateTestBase extends TestCase {
	
	TemplateTestBase() {
		try {
			loadArchetypeMap();
			parser = new OETParser();			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUp() throws Exception {
		flattener = new Flattener();
		serializer = new ADLSerializer();
	}
	
	public void tearDown() throws Exception {
		template = null;
	}
	
	protected void flattenTemplate(String templateFilename) throws Exception {
		template = loadTemplate(templateFilename);
		flattened = flattener.toFlattenedArchetype(template, 
				archetypeMap, templateMap);
	}
	
	protected void printPaths(String filter) {
		for (String key : flattened.getPathNodeMap().keySet()) {
			if (filter == null  || (filter != null && key.contains(filter))) {
				System.out.println(key);
			}
		}
	}
	
	protected void assertRequired(String path) throws Exception {
		CObject cobj = assertCObject(path);
		assertTrue("expected to be required", cobj.isRequired());
	}
	
	protected void assertNotAllowed(String path) throws Exception {
		CObject cobj = assertCObject(path);
		Interval<Integer> notAllowed = new Interval<Integer>(0,0);
		assertEquals(notAllowed, cobj.getOccurrences());
	}
	
	protected CObject assertCObject(String path, Archetype archetype) {
		ArchetypeConstraint ac = archetype.node(path);
		assertTrue("unexpected non-CObject: " + 
				(ac == null ? null : ac.getClass()) + " on path: [" + path + "]", 
				ac instanceof CObject);
		CObject cobj = (CObject) ac;
		return cobj;
	}
	
	protected CObject assertCObject(String name, String path, Archetype archetype) {
		ArchetypeConstraint ac = archetype.node(path);
		assertTrue("unexpected non-CObject[ " + name + "] " + 
				(ac == null ? null : ac.getClass()) + " on path: " + path, 
				ac instanceof CObject);
		CObject cobj = (CObject) ac;
		return cobj;
	}
	
	protected CObject assertCObject(String path) {
		return assertCObject(path, flattened);
	}
	
	protected Archetype loadArchetype(String id) throws Exception {
		ADLParser adlParser = new ADLParser(fromClasspath(ARCHETYPE_PATH + 
    			id));
    	return adlParser.parse();    	
    }
	
	/**
	 * Load all archetypes from predefined path
	 * 
	 * @throws Exception
	 */
	protected void loadAllArchetypes() throws Exception {
		archetypeMap = new HashMap<String, Archetype>();
		
		File[] files = new File(ARCHETYPE_PATH).listFiles();
		for(File file : files) {
			if(file.getName().contains(".adl")) {
				ADLParser adlParser = new ADLParser(file);
		    	Archetype archetype = adlParser.parse();
		    	archetypeMap.put(archetype.getArchetypeId().toString(), archetype);
			}
		}		    	
    }
	
	protected TEMPLATE loadTemplate(String id) throws Exception {
		template = parser.parseTemplate(
				fromClasspath(TEMPLATE_PATH + id)).getTemplate();
		return template;
	}
	
	protected InputStream fromClasspath(String filename) throws Exception {
		return this.getClass().getClassLoader().getResourceAsStream(filename);
	}	
	
	/**
	 * Compares two CComplexObject by values
	 * 
	 * The reason not to use the built-in equals() directly is 
	 * the path can be different between flattened archetype and expected 
	 * one directly loaded from ADL.
	 */
	protected void assertCComplexObjectEquals(String message,
			CComplexObject ccobj1, CComplexObject ccobj2) {
		
		assertEquals(message + ", ccobj.rmTypeName not equals at path: " + ccobj1.path(), 
				ccobj1.getRmTypeName(), ccobj2.getRmTypeName());
		
		assertEquals(message + "ccobj.nodeId not equals at path: " + ccobj1.path(), 
				ccobj1.getNodeId(), ccobj2.getNodeId());
		
		assertEquals(message + "ccobj.occurrences not equals at path: " + ccobj1.path(), 
				ccobj1.getOccurrences(), ccobj2.getOccurrences());
		
		assertEquals(message + 
			", ccobj.attributes total number not equals at path: " + ccobj1.path(), 
			ccobj1.getAttributes().size(), ccobj2.getAttributes().size());
			
		for(int i = 0, j = ccobj2.getAttributes().size(); i < j; i++) {
			CAttribute cattr1 = ccobj1.getAttributes().get(i);
			CAttribute cattr2 = ccobj2.getAttributes().get(i);
			assertCAttributeEquals(message, cattr1, cattr2);
		} 
	}
	
	protected void assertCComplexObjectOfRmType(String message,
			ArchetypeConstraint ac, String rmType) throws Exception {
		assertTrue(message + ", unexpected constraint: " + ac, 
				ac instanceof CComplexObject);
		CComplexObject ccobj = (CComplexObject) ac;
		assertEquals(message + ", unexpected rmType: " + ccobj.getRmTypeName(), 
				rmType, ccobj.getRmTypeName());
	}
	
	protected void assertCComplexObjectOfRmType(ArchetypeConstraint ac, 
			String rmType) throws Exception {
		assertCComplexObjectOfRmType("Unexpected constraint type " 
				+ (ac == null ? "null" : ac.getClass() + " at " + ac.path()), 
				ac, rmType);
	}
	
	protected void assertCompositionConstraint(ArchetypeConstraint ac) 
			throws Exception {
		assertCComplexObjectOfRmType(ac, COMPOSITION);
	}
	
	protected void assertItemTreeConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, ITEM_TREE);
	}

	protected void assertItemListConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, ITEM_LIST);
	}

	protected void assertElementConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, ELEMENT);
	}

	protected void assertSectionConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, SECTION);
	}

	protected void assertObservationConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, OBSERVATION);
	}

	protected void assertInstructionConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, INSTRUCTION);
	}

	protected void assertEvaluationConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, EVALUATION);
	}

	protected void assertActionConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, ACTION);
	}

	protected void assertActivityConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, ACTIVITY);
	}	
	
	protected void assertDvTextConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, DV_TEXT);
	}
	
	protected void assertDvCodedTextConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, DV_TEXT);
	}
	
	protected void assertDvCountConstraint(ArchetypeConstraint ac)
			throws Exception {
		assertCComplexObjectOfRmType(ac, DV_COUNT);
	}

	protected void assertCDomainTypeEquals(String message, CDomainType cobj1,
			CDomainType cobj2) {
		
		if(cobj1 instanceof CDvQuantity) {
		
			assertTrue(message, cobj2 instanceof CDvQuantity);
			
			CDvQuantity cdq1 = (CDvQuantity) cobj1;
			CDvQuantity cdq2 = (CDvQuantity) cobj2;
			assertEquals(message, cdq1.getAssumedValue(), cdq2.getAssumedValue());
			assertEquals(message, cdq1.getDefaultValue(), cdq2.getDefaultValue());
			assertEquals(message, cdq1.getList(), cdq2.getList());
			assertEquals(message, cdq1.getProperty(), cdq2.getProperty());
			assertEquals(message, cdq1.getOccurrences(), cdq1.getOccurrences());		
		
		} else if(cobj1 instanceof CDvOrdinal) {
			
			assertTrue(message, cobj2 instanceof CDvOrdinal);
			
			CDvOrdinal cdq1 = (CDvOrdinal) cobj1;
			CDvOrdinal cdq2 = (CDvOrdinal) cobj2;
			assertEquals(message, cdq1.getAssumedValue(), cdq2.getAssumedValue());
			assertEquals(message, cdq1.getDefaultValue(), cdq2.getDefaultValue());
			assertEquals(message, cdq1.getList(), cdq2.getList());
			assertEquals(message, cdq1.getOccurrences(), cdq1.getOccurrences());		
		
		} else if(cobj1 instanceof CCodePhrase) {
		
			assertTrue(message, cobj2 instanceof CCodePhrase);
		
			CCodePhrase cdq1 = (CCodePhrase) cobj1;
			CCodePhrase cdq2 = (CCodePhrase) cobj2;
			assertEquals(message, cdq1.getAssumedValue(), cdq2.getAssumedValue());
			assertEquals(message, cdq1.getDefaultValue(), cdq2.getDefaultValue());
			assertEquals(message, cdq1.getTerminologyId(), cdq2.getTerminologyId());
			assertEquals(message, cdq1.getCodeList(), cdq2.getCodeList());
			assertEquals(message, cdq1.getOccurrences(), cdq1.getOccurrences());			
		
		} else if(cobj1 instanceof CDvState) {
		
			assertTrue(message, cobj2 instanceof CDvState);
		
			CDvState cdq1 = (CDvState) cobj1;
			CDvState cdq2 = (CDvState) cobj2;
			assertEquals(message, cdq1.getAssumedValue(), cdq2.getAssumedValue());
			assertEquals(message, cdq1.getDefaultValue(), cdq2.getDefaultValue());
			assertEquals(message, cdq1.getValue(), cdq2.getValue());
			assertEquals(message, cdq1.getOccurrences(), cdq1.getOccurrences());
		}
	}
	
	protected void assertCAttributeEquals(String message, CAttribute cattr1, 
			CAttribute cattr2) {
		
		assertEquals(message + ", cattribute.rmAttributeName not equal at path: " 
				+ cattr1.path(), cattr1.getRmAttributeName(), cattr2.getRmAttributeName());
		
		assertEquals(message + ", cattribute.existence not equal at path: " + 
				cattr1.path(), cattr1.getExistence(), cattr2.getExistence());
		
		assertEquals(message + ", cattribute.children size not equal at path: " 
				+ cattr1.path(), cattr1.getChildren().size(), cattr2.getChildren().size());
		
		for(int i = 0, j = cattr1.getChildren().size(); i < j; i++) {
			CObject cobj1 = cattr1.getChildren().get(i);
			CObject cobj2 = cattr2.getChildren().get(i);
			
			
			assertEquals(message + ", different cobj types at path: " 
					+ cobj1.path(),cobj1.getClass(), cobj2.getClass());		
					
			// Both complex types
			if(cobj1 instanceof CComplexObject && cobj2 instanceof CComplexObject) {
			
				assertCComplexObjectEquals(message, (CComplexObject) cobj1, (CComplexObject) cobj2);
			
			} else if(cobj1 instanceof CPrimitiveObject){
				
				assertTrue(message, cobj2 instanceof CPrimitiveObject);
				
				CPrimitiveObject cpo1 = (CPrimitiveObject) cobj1;
				CPrimitiveObject cpo2 = (CPrimitiveObject) cobj2;
				
				assertEquals(message, cpo1.getItem(), cpo2.getItem());
				assertEquals(message, cpo1.getOccurrences(), cpo2.getOccurrences());
			
			} else if(cobj1 instanceof ConstraintRef){ 
				
				assertTrue(message, cobj2 instanceof ConstraintRef);
				
			} else if(cobj1 instanceof CDomainType){ 
				
				assertTrue(message, cobj2 instanceof CDomainType);
				
				CDomainType cdt1 = (CDomainType) cobj1;
				CDomainType cdt2 = (CDomainType) cobj2;
				assertCDomainTypeEquals(message, cdt1, cdt2);
				
			} else {
				// use the built-in equals 
				assertEquals(message, cobj1, cobj2);
			}
		}
	}
	
	/**
	 * Assert that the given ArchetypeConstraint is a CPrimitiveObject 
	 * with single string valued c_string item 
	 * 
	 * @param ac
	 * @param onlyValue
	 */
	protected void assertCStringWithSingleValue(ArchetypeConstraint ac, String onlyValue) {
		CString cs = assertCString(ac);
		assertTrue("expected only value in CString", cs.getList().size() == 1);
		assertEquals(onlyValue, cs.getList().get(0));
	}
	
	/**
	 * Assert that the given ArchetypeConstraint is a CPrimitiveObject 
	 * with a default valued c_string item 
	 * 
	 * @param ac
	 * @param defaultValue
	 */
	protected void assertCStringWithDefaultValue(ArchetypeConstraint ac,
			String defaultValue) {
		CString cs = assertCString(ac);
		assertEquals("wrong default value in c_string", defaultValue, 
				cs.defaultValue());
	}
	
	/**
	 * Assert that the given ArchetypeConstraint is a CPrimitiveObject 
	 * with c_string item, then return c_string for further inspection
	 * 
	 * @param ac
	 * @param onlyValue
	 */
	protected CString assertCString(ArchetypeConstraint ac) {
		assertNotNull("null instead of CPrimitiveObject", ac);
		assertTrue("expected CPrimitiveObject instead of " + ac.getClass(), 
				ac instanceof CPrimitiveObject);
		CPrimitiveObject cop = (CPrimitiveObject) ac;
		CPrimitive cp = cop.getItem();
		assertTrue("expected CString instead of " + cp.getClass(),
				cp instanceof CString);
		CString cstring = (CString) cp;
		return cstring;
	}
	
	protected void assertCCodePhraseWithDefaultValue(
			ArchetypeConstraint ac,	String terminologyId, String code) {
		assertTrue("unexpected constraint instead of CCodePhrase: " + 
				(ac == null ? "null" : ac.getClass()), 
				ac instanceof CCodePhrase);
		CCodePhrase ccp = (CCodePhrase) ac;
		CodePhrase cp = new CodePhrase(terminologyId, code);
		assertEquals("unexpected CCodePhrase.defaultValue", cp,
				ccp.getDefaultValue());
	}
	
	protected void assertCDvQuantityWithSingleItem(ArchetypeConstraint ac, 
			CDvQuantityItem item) throws Exception {
		assertTrue("unexpected constraint instead of CDvQuantity: " + 
				(ac == null ? "null" : ac.getClass()), 
				ac instanceof CDvQuantity);
		CDvQuantity cdq = (CDvQuantity) ac;
		assertNotNull("expecting non-empty list", cdq.getList());	
		assertEquals("expecting only one item", 1, cdq.getList().size());
		assertEquals("cDvQuantityItem diff", item, cdq.getList().get(0));
	}
	
	protected void assertCCodePhraseWithCodeList(
			ArchetypeConstraint ac,	String terminologyId, 
			List<String> codeList) {
		assertTrue("unexpected constraint instead of CCodePhrase: " + 
				(ac == null ? "null" : ac.getClass()), 
				ac instanceof CCodePhrase);
		CCodePhrase ccp = (CCodePhrase) ac;
		assertEquals("unexpected CCodePhrase.defaultValue", terminologyId,
				ccp.getTerminologyId().toString());
		assertEquals("unexpected CCodePhrase.codeList", codeList,
				ccp.getCodeList());		
	}
	
	protected void loadArchetypeMap() throws Exception {
		String[] ids = {
				"openEHR-EHR-COMPOSITION.prescription.v1", 
				"openEHR-EHR-INSTRUCTION.medication.v1", 
				"openEHR-EHR-ITEM_TREE.medication.v1", 
				"openEHR-EHR-ITEM_TREE.medication_mod.v1",
				"openEHR-EHR-SECTION.medications.v1",
				"openEHR-EHR-ITEM_TREE.medication_test_one.v1",
				"openEHR-EHR-ITEM_TREE.medication_test_three.v1",
				"openEHR-EHR-ITEM_TREE.medication_multiple_constraint_test.v1",
				"openEHR-EHR-ITEM_TREE.medication_multiple_constraint_test2.v1",
				"openEHR-EHR-SECTION.ad_hoc_heading.v1",
				"openEHR-EHR-SECTION.simple_section_name.v1",
				"openEHR-EHR-COMPOSITION.test.v1",
				"openEHR-EHR-EVALUATION.structured_summary.v1",
				"openEHR-EHR-EVALUATION.review_of_procedures.v1",
				"openEHR-EHR-ACTION.medication.v1",
				"openEHR-EHR-ITEM_TREE.medication.v1",
				"openEHR-EHR-ITEM_TREE.medication_quantity_test.v1",
				"openEHR-EHR-ITEM_TREE.medication_quantity_test2.v1",
				"openEHR-EHR-OBSERVATION.waist_hip.v2",
				"openEHR-EHR-OBSERVATION.lab_test.v1",
				"openEHR-EHR-OBSERVATION.heart_failure_stage.v2",
				"openEHR-EHR-EVALUATION.medication_review.v1"
		};
		
		archetypeMap = new HashMap<String, Archetype>();
		
		for(String id : ids) {
			Archetype archetype = loadArchetype(id + ".adl");
			archetypeMap.put(archetype.getArchetypeId().toString(), archetype);
		}			
	}
	
	/**
	 * Print out given archetype in ADL for debugging purpose
	 * 
	 * @param toPrint
	 * @throws Exception
	 */
	protected void printADL(Archetype toPrint) throws Exception {
		serializer.output(toPrint, System.out);
	}
	
	/**
	 * Print out given archetype in XML for debugging purpose
	 * 
	 * @param toPrint
	 * @throws Exception
	 */
	protected void printXML(Object toPrint) throws Exception {
		XStream xstream = new XStream();
		String xml = xstream.toXML(toPrint);
		System.out.println(xml);
	}
	
	protected String toXML(Archetype toPrint) throws Exception {
		XStream xstream = new XStream();
		String xml = xstream.toXML(toPrint);
		return xml;
	}
	
	protected void writeXML(Archetype archetype, String filename)
		throws Exception {
		FileUtils.writeStringToFile(new File(filename), toXML(archetype), "UTF-8");
	}
	
	protected void writeADL(Archetype archetype, String filename)
		throws Exception {
		
		FileWriter writer = new FileWriter(filename);
		try {
			serializer.output(archetype, writer);
		} finally {
			writer.close();
		}
	}
	
	/**
	 * Assert all c_objects and named c_attributed on the path are required
	 * 
	 * @param pathvalue
	 * @throws Exception
	 */	
	protected void assertTemplateLevelRequired(String key, String pathvalue) 
			throws Exception {
		
		// check the occurrences of c_object
		assertSingleRequired(key, pathvalue);	
		
		int i = pathvalue.lastIndexOf("/");		
		if(pathvalue.endsWith("']")) {
			int j = pathvalue.substring(0, pathvalue.length() - 2).lastIndexOf("'");
			if(j > 0 && i > j) { // '/' inside name
				i = pathvalue.substring(0, i).lastIndexOf("/");
			}
		}		
		String attr = pathvalue.substring(i + 1);		
		String newpath = null;
		if(i > 0) {
			newpath = pathvalue.substring(0, i);
		} else {
			newpath = "/";
		}		
		if(newpath.endsWith("name")) {
			i = newpath.lastIndexOf("/");
			if(i > 0) { // name/value
				attr = newpath.substring(i + 1) + attr;
				newpath = newpath.substring(0, i);				
			} 			
		}		
		i =  attr.indexOf("[");
		if(i > 0) {
			attr = attr.substring(0, i);
		}
		
		// check the existence and cardinality on c_attribute
		CObject cobj = assertCObject(newpath);
		if(cobj instanceof CComplexObject) {
			CComplexObject ccobj = (CComplexObject) cobj;
			CAttribute cattr = ccobj.getAttribute(attr);
			if(cattr == null) {
				fail("c_attribute of name " + attr + " not found on path: " + pathvalue);
			}
			assertTrue("unexpected optional attribute on path: " + cattr.path(), cattr.isRequired());
			
			// No need to check cardinality
			//if(cattr instanceof CMultipleAttribute) {
			//	CMultipleAttribute cma = (CMultipleAttribute) cattr;
			//	Cardinality cardinality = cma.getCardinality();
			//	assertNotNull("unexpected null lower limit of cardinality on: "
			//			+ pathvalue, cardinality.getInterval().getLower());
			//	assertEquals("unexpected lower limit of cardinality on : " 
			//			+ pathvalue, 1, cardinality.getInterval().getLower().intValue());
			//}
		}
		
		if( ! "/".equals(newpath)) { // recursive
			assertTemplateLevelRequired(key, newpath);
		}
	}
	
	protected void assertTemplateLevelRequired(String path) throws Exception {
		assertTemplateLevelRequired(null, path);
	}
	
	protected void assertSingleRequired(String name, String path) throws Exception {
		assertMaxOccurrences(name, path, 1);
		assertMinOccurrences(name, path, 1);
	}
	
	protected void assertMaxOccurrences(String name, String path, int max) throws Exception {
		assertMaxOccurrences(null, path, max, flattened);
	}
	
	protected void assertMaxOccurrences(String name, String path, int max, Archetype archetype) throws Exception {
		CObject cobj = assertCObject(name, path, archetype);
		assertNotNull("unexpected null maximum occurrences at path [" + name + "] "+  path,  
				cobj.getOccurrences().getUpper());
		assertEquals("unexpected maximum occurrences at path [" + name + "] "+  path, max, 
				cobj.getOccurrences().getUpper().intValue());
	}
	
	protected void assertMinOccurrences(String path, int min, Archetype archetype) throws Exception {
		assertMinOccurrences(null, path, min, archetype);
	}
	
	protected void assertMinOccurrences(String name, String path, int min, Archetype archetype) throws Exception {
		CObject cobj = assertCObject(path, archetype);
		assertNotNull("unexpected null minimum occurrences at path [" + name + "] "+  path, 
				cobj.getOccurrences().getLower());
		assertEquals("unexpected minimum occurrences at path [" + name + "] "+ path, min, 
				cobj.getOccurrences().getLower().intValue());
	}
	
	protected void assertMinOccurrences(String name, String path, int min) throws Exception {
		assertMinOccurrences(name, path, min, flattened);
	}

	
	
	/* static constant fields */
	protected static final String COMPOSITION = "COMPOSITION";
	protected static final String SECTION = "SECTION";
	protected static final String EVALUATION = "EVALUATION";
	protected static final String OBSERVATION = "OBSERVATION";
	protected static final String INSTRUCTION = "INSTRUCTION";
	protected static final String ACTIVITY = "ACTIVITY";
	protected static final String ACTION = "ACTION";
	protected static final String ITEM_TREE = "ITEM_TREE";
	protected static final String ITEM_LIST = "ITEM_LIST";
	protected static final String ELEMENT = "ELEMENT";
	protected static final String DV_TEXT = "DV_TEXT";
	protected static final String DV_COUNT = "DV_COUNT";
	protected static final String DV_CODED_TEXT = "DV_CODED_TEXT";
	
		
	/* path for test fixtures */
	private static final String ARCHETYPE_PATH = "archetypes/";
	private static final String TEMPLATE_PATH = "templates/"; 
	
	/* member fields */
	protected TEMPLATE template;
	protected OETParser parser;	
	protected Flattener flattener;
	protected ADLSerializer serializer;
	protected Map<String, Archetype> archetypeMap;
	protected Map<String, TEMPLATE> templateMap;
	protected Archetype flattened;
	protected Archetype archetype;
	protected Archetype expected;
	
	private static final Logger log = Logger.getLogger(TemplateTestBase.class);
}
