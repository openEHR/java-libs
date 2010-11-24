package org.openehr.am.template;

import java.util.*;

import openEHR.v1.template.Statement;
import openEHR.v1.template.TextConstraint;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;

public class TextConstraintTest extends TemplateTestBase {
	
	public void setUp() throws Exception {		
		super.setUp();
		
		archetype = loadArchetype(
			"openEHR-EHR-ITEM_TREE.medication_test_one.v1.adl");
		
		constraint = archetype.node(PATH);
		
		rule = Statement.Factory.newInstance();
		rule.setPath(PATH);
	}
	
	// <Rule path="/items[at0001]" default="Ipren" />
	public void testSetDefaultTextConstraint() throws Exception {
		String defaultValue = "Ipren";
		flattener.applyDefaultValueConstraint(constraint, defaultValue);
		
		archetype.reloadNodeMaps();
		constraint = archetype.node(PATH + "/value/value");
		assertCStringWithDefaultValue(constraint, defaultValue);
	}
	
	// test the combination of coded_text and name constraint
	// which triggered a bug once
	public void testSetDefaultCodedTextAndName() throws Exception {
		flattenTemplate("test_default_coded_name.oet");

		constraint = flattened.node("/data[at0001]/items[at0002]/items" +
				"[at0003 and name/value='Dose Units']/value");		
		assertCCodePhraseWithDefaultValue(constraint, "SNOMED-CT", "258835005");
		
		constraint = flattened.node("/data[at0001]/items[at0002]/items" +
				"[at0003 and name/value='Dose Units']/name/value");
		assertCStringWithSingleValue(constraint, "Dose Units");
	}
	
	public void testTermMapAfterSetDefaultCodedTextAndName() throws Exception {
		flattenTemplate("test_default_coded_name.oet");
		String path = "/data[at0001]/items[at0002]/items" +
					"[at0003 and name/value='Dose Units']/value";
		constraint = flattened.node(path);		
		assertCCodePhraseWithDefaultValue(constraint, "SNOMED-CT", "258835005");
		
		TermMap termMap = flattener.getTermMap();
		assertEquals(1, termMap.countTerminologies());
		assertEquals("units", termMap.getText("SNOMED-CT", "258835005", path));
	}
	
	// <Rule path="/items[at0001]" default="SNOMED-CT::258835005::mg/dygn"/>
	
	public void testSetDefaultCodedTextConstraint() throws Exception {
		String defaultValue = "SNOMED-CT::258835005::mg/dygn";		
		flattener.applyDefaultValueConstraint(constraint, defaultValue);
		
		archetype.reloadNodeMaps();
		constraint = archetype.node(PATH + "/value");		
		assertCCodePhraseWithDefaultValue(constraint, "SNOMED-CT", "258835005");
	}
	
	//	<Rule path="/items[at0001]" name="status">
	//	  <constraint xsi:type="textConstraint">
	//		<includedValues>SNOMED-CT::10036::Nej</includedValues>
	//		<includedValues>SNOMED-CT::10035::Ja</includedValues>
	//		<includedValues>SNOMED-CT::10037::Ok�nt</includedValues>
	//	  </constraint>
	//	</Rule>
	public void testSetTextConstraintWithCodedIncludedValues() throws Exception {
		TextConstraint tc = TextConstraint.Factory.newInstance();
		tc.addIncludedValues("SNOMED-CT::10036::Nej");
		tc.addIncludedValues("SNOMED-CT::10035::Ja");
		tc.addIncludedValues("SNOMED-CT::10037::Uknown");
		rule.setConstraint(tc);
		
		ccobj = (CComplexObject) constraint;
		flattener.applyValueConstraint(archetype, ccobj, rule);
		
		archetype.reloadNodeMaps();
		constraint = archetype.node(PATH + "/value/defining_code");			
		String[] expectedCodes = { "10036", "10035", "10037" };
		List<String> codeList = Arrays.asList(expectedCodes);
		assertCCodePhraseWithCodeList(constraint, "SNOMED-CT", codeList);	
	}
	
	public void testAggregatedTermMapAfterSetCodedIncludedValues() throws Exception {
		TextConstraint tc = TextConstraint.Factory.newInstance();
		tc.addIncludedValues("SNOMED-CT::10036::Nej");
		tc.addIncludedValues("SNOMED-CT::10035::Ja");
		tc.addIncludedValues("SNOMED-CT::10037::Uknown");
		rule.setConstraint(tc);
		
		ccobj = (CComplexObject) constraint;
		flattener.applyValueConstraint(archetype, ccobj, rule);
		TermMap termMap = flattener.getTermMap();
		assertEquals(1, termMap.countTerminologies());
		String path = ccobj.path() + "/value";
		assertEquals("Nej", termMap.getText("SNOMED-CT", "10036", path));
	}
	
	public void testExistingTypesRemovedAfterSetTextConstraint() throws Exception {
		archetype = loadArchetype(
			"openEHR-EHR-ITEM_TREE.medication_test_one.v2.adl");	
		constraint = archetype.node(PATH);
		
		TextConstraint tc = TextConstraint.Factory.newInstance();
		tc.addIncludedValues("SNOMED-CT::10036::Nej");
		rule.setConstraint(tc);
		
		ccobj = (CComplexObject) constraint;
		flattener.applyValueConstraint(archetype, ccobj, rule);
		archetype.reloadNodeMaps();
		constraint = archetype.node(PATH);
		CComplexObject cobj = (CComplexObject) constraint;
		CAttribute cattr = cobj.getAttribute("value");
		assertEquals("unexpected multiple children", 1,
				cattr.getChildren().size());		
	}
	
	public void testSetNameAndDefaultOnCodedTextNode() throws Exception {
		
		// bug on set the following on dv_coded_text
		// <Rule path="/items[at0006]" default="SNOMED-CT::258835005::mg/dygn" 
		// 		name="dosenhet" />		
		archetype = loadArchetype(
			"openEHR-EHR-ITEM_TREE.medication_test_two.v1.adl");
		
		rule.setDefault("SNOMED-CT::258835005::mg/dygn");
		rule.setName("dosenhet");
		
		flattener.applyRule(archetype, rule);		
		archetype.reloadNodeMaps();
		
		// verify default value
		constraint = archetype.node("/items" +
				"[at0001 and name/value='dosenhet']/value");
		assertCCodePhraseWithDefaultValue(constraint, "SNOMED-CT", "258835005");
		
		// verify the original dv_coded_text is gone
		CComplexObject cobj = (CComplexObject) archetype.node("/items" +
				"[at0001 and name/value='dosenhet']");
		CAttribute attr = cobj.getAttribute("value");
		assertEquals("unexpected duplicated child", 1, attr.getChildren().size());
		
		// verify name 
		constraint = archetype.node("/items" +
				"[at0001 and name/value='dosenhet']/name/value");
		assertCStringWithSingleValue(constraint, "dosenhet");
	}
	
	public void testSetTextConstraintWithExcludedValues() throws Exception {
		archetype = loadArchetype(
				"openEHR-EHR-ADMIN_ENTRY.heart_failure_contact.v1.adl");
		String path = "/data[at0001]/items[at0009]/items[at0010]";
		constraint = archetype.node(path);
		
		TextConstraint tc = TextConstraint.Factory.newInstance();
		tc.addExcludedValues("local::at0022");
		rule.setConstraint(tc);
		
		ccobj = (CComplexObject) constraint;
		flattener.applyValueConstraint(archetype, ccobj, rule);
		
		archetype.reloadNodeMaps();
		constraint = archetype.node(path + "/value/defining_code" );			
		String[] expectedCodes = { "at0020", "at0021"};
		List<String> codeList = Arrays.asList(expectedCodes);
		assertCCodePhraseWithCodeList(constraint, "local", codeList);
	}
	
	private static final String PATH = "/items[at0001]";
	private Statement rule;
	private ArchetypeConstraint constraint;
	private CComplexObject ccobj;
}
