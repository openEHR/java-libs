package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;

public class SectionTemplateTest extends TemplateTestBase {
	
	public void testSingleSectionWithName() throws Exception {		
		flattenTemplate("test_simple_section.oet");		
		expected = loadArchetype("openEHR-EHR-SECTION.simple_section_name.v1.adl");	
				
		assertCComplexObjectEquals("failed to flatten simple section template",  
				expected.getDefinition(), flattened.getDefinition());
		
		// verify node.path
		ac = flattened.node("/");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "single"); 
	}	
	
	public void testTwoNestedSections() throws Exception {		
		flattenTemplate("test_nested_section.oet");		
	
		// verify node.path
		ac = flattened.node("/");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "one");
		
		// use archetype_id instead of at0000
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']/name/value");
		assertCStringWithSingleValue(ac, "two");
	}
	
	public void testFiveNestedSections() throws Exception {		
		flattenTemplate("test_more_nested_section.oet");	
			
		// first object level, equivalent to "/[at0000]"
		ac = flattened.node("/");
		assertSectionConstraint(ac);		
		
		ac = flattened.node("/name/value");
		assertCStringWithSingleValue(ac, "one");
		
		// second object level
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']");
		assertSectionConstraint(ac);		
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']/name/value");
		assertCStringWithSingleValue(ac, "two");
		
		// third object level
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='three']");
		assertSectionConstraint(ac);		
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='three']/name/value");
		assertCStringWithSingleValue(ac, "three");
		
		// fourth object level
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='three']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='four']");
		assertSectionConstraint(ac);		
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='three']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='four']/name/value");
		assertCStringWithSingleValue(ac, "four");
		
		// fifth object level
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='three']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='four']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='five']");
		assertSectionConstraint(ac);
		
		ac = flattened.node("/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='two']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='three']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='four']" +
				"/items[openEHR-EHR-SECTION.ad_hoc_heading.v1" +
				" and name/value='five']/name/value");
		assertCStringWithSingleValue(ac, "five");		
	}
	
	ArchetypeConstraint ac;
}
