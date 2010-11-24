package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;

public class CompositionTemplateTest extends TemplateTestBase {

	public void setUp() throws Exception {
		super.setUp();	
	}
	
	public void testContentSlotWithSingleEmptySection() throws Exception {
		flattenTemplate("test_composition.oet");
		
		constraint = flattened.node("/content[openEHR-EHR-SECTION.ad_hoc_heading.v1]");
		assertSectionConstraint(constraint);	
	}
	
	public void testContentSlotWithSingleNamedSection() throws Exception {
		flattenTemplate("test_composition2.oet");
		
		constraint = flattened.node(
				"/content[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='header']");
		assertSectionConstraint(constraint);
		
		constraint = flattened.node(
				"/content[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='header']/name/value");
		assertCStringWithSingleValue(constraint, "header");		
	}
	
	public void testContentSlotWithMaxOccurrences() throws Exception {
		flattenTemplate("test_composition4.oet");
		
		constraint = flattened.node(
				"/content[openEHR-EHR-SECTION.ad_hoc_heading.v1]");
		assertSectionConstraint(constraint);
		CComplexObject ccobj = (CComplexObject) constraint;
		assertEquals("wrong max occurrences", 1, 
				ccobj.getOccurrences().getUpper().intValue());	
	}
	
	/**
	 * Verify an incremental number appended to archetype_id inside node_id
	 * for duplicated sibling archetypes on the same level
	 * 
	 * @throws Exception
	 */
	public void testContentSlotWithTwoNamedSections() throws Exception {
		flattenTemplate("test_composition3.oet");
		
		// first section
		constraint = flattened.node(
				"/content[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='one']");
		assertSectionConstraint(constraint);
		
		constraint = flattened.node(
				"/content[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='one']/name/value");
		assertCStringWithSingleValue(constraint, "one");		
		
		// second section, NOTE the "_1" suffix after archetype_id
		constraint = flattened.node(
				"/content[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']");
		assertSectionConstraint(constraint);
		
		constraint = flattened.node(
				"/content[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']/name/value");
		assertCStringWithSingleValue(constraint, "two");
	}
	
	public void testContentSlotWithFiveNamedSections() throws Exception {
		flattenTemplate("test_composition5.oet");
		
		// first section
		constraint = flattened.node("/content" +
				"[openEHR-EHR-SECTION.ad_hoc_heading.v1 and name/value='one']");
		assertSectionConstraint(constraint);
		
		constraint = flattened.node("/content" +
				"[openEHR-EHR-SECTION.ad_hoc_heading.v1 and name/value='one']" +
				"/name/value");
		assertCStringWithSingleValue(constraint, "one");		
		
		// 3rd section
		constraint = flattened.node("/content" +
				"[openEHR-EHR-SECTION.ad_hoc_heading.v1 and name/value='three']");
		assertSectionConstraint(constraint);
		
		constraint = flattened.node("/content" +
				"[openEHR-EHR-SECTION.ad_hoc_heading.v1 and name/value='three']" +
				"/name/value");
		assertCStringWithSingleValue(constraint, "three");
		
		// 5th section
		constraint = flattened.node("/content" +
				"[openEHR-EHR-SECTION.ad_hoc_heading.v1 and name/value='five']");
		assertSectionConstraint(constraint);
		
		constraint = flattened.node("/content" +
				"[openEHR-EHR-SECTION.ad_hoc_heading.v1 and name/value='five']" +
				"/name/value");
		assertCStringWithSingleValue(constraint, "five");
	}
	
	private ArchetypeConstraint constraint;
}
