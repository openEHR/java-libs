package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;

public class SectionEvaluationTest extends TemplateTestBase {
	
	/**
	 * Verify flattened section with a single evaluation
	 * 
	 * @throws Exception
	 */
	public void testSectionWithSingleEvaluation() throws Exception {
		flattenTemplate("test_section_evaluation.oet");		
				
		// section level
		ac = flattened.node("/");
		assertSectionConstraint(ac);
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "section header");
		
		// evaluation level
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']");
		assertEvaluationConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']/name/value");
		assertCStringWithSingleValue(ac, "evaluation header");		
		
		// insider newly embedded evaluation
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']/data[at0001]");
		assertItemTreeConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']/data[at0001]" +
				"/items[at0002]");
		assertElementConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']/data[at0001]" +
				"/items[at0005]");
		assertElementConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']/data[at0001]" +
				"/items[at0003]");
		assertElementConstraint(ac);
	}
	
	/**
	 * Verify flattened section with two evaluations
	 * 
	 * @throws Exception
	 */
	public void testSectionWithTwoEvaluations() throws Exception {
		flattenTemplate("test_section_evaluation2.oet");	
		
		// section level
		ac = flattened.node("/");
		assertSectionConstraint(ac);
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "section header");
		
		// first evaluation
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation one']");
		assertEvaluationConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation one']/name/value");
		assertCStringWithSingleValue(ac, "evaluation one");		
		
		// second evaluation
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation two']");
		assertEvaluationConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation two']/name/value");
		assertCStringWithSingleValue(ac, "evaluation two");
		
		// insider 1st evaluation
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation one']/data[at0001]");
		assertItemTreeConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation one']/data[at0001]/items[at0002]");
		assertElementConstraint(ac);
		
		// insider 2nd evaluation
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation two']/data[at0001]");
		assertItemTreeConstraint(ac);
		
		ac = flattened.node("/items" +
				"[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation two']/data[at0001]/items[at0002]");
		assertElementConstraint(ac);
	}
	
	public void testNestedSectionWithEvaluation() throws Exception {
		template = loadTemplate("test_nested_section_evaluation.oet");		
		flattened = flattener.toFlattenedArchetype(template, archetypeMap);	
		
		// section level one
		ac = flattened.node("/");
		assertSectionConstraint(ac);
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "section level one");
		
		// section level two
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']/name/value");
		assertCStringWithSingleValue(ac, "section level two");		
		
		// evaluation level
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']" +
				"/items[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']");
		assertEvaluationConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']" +
				"/items[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']/name/value");
		assertCStringWithSingleValue(ac, "evaluation header");		
		
		// insider newly embedded evaluation
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']" +
				"/items[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']/data[at0001]");
		assertItemTreeConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']" +
				"/items[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']" +
				"/data[at0001]/items[at0002]");
		assertElementConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']" +
				"/items[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']" +
				"/data[at0001]/items[at0005]");
		assertElementConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='section level two']" +
				"/items[openEHR-EHR-EVALUATION.structured_summary.v1" +
				" and name/value='evaluation header']" +
				"/data[at0001]/items[at0003]");
		assertElementConstraint(ac);
	}
	
	ArchetypeConstraint ac;
}
