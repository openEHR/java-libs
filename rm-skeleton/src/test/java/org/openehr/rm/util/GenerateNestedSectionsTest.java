package org.openehr.rm.util;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.composition.content.navigation.Section;

public class GenerateNestedSectionsTest extends SkeletonGeneratorTestBase {
	
	public GenerateNestedSectionsTest() throws Exception {		
	}
	
	public void testWithOptionalChildren() throws Exception {
		Archetype flattened = flattenTemplate(
				"test_nested_sections_with_optional_children");
		
		Object obj = generator.create(flattened, null, archetypeMap,
				GenerationStrategy.MINIMUM);
		
		assertTrue(obj instanceof Section);		
		Section section = (Section) obj;
		
		// section.name from archetype
		String path = "/items[openEHR-EHR-SECTION.ad_hoc_heading.v1]/name/value";
		assertNull("unexpected nested section", section.itemAtPath(path));		
	}
}
