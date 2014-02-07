package org.openehr.rm.util;

import java.util.List;

import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.navigation.Section;

public class RMUtilTest extends SkeletonGeneratorTestBase {

	public RMUtilTest() throws Exception {
		super();
	}
	
	public void testPurgeWithOneEmptyOneFilledSections() throws Exception {
		composition = loadXMLComposition("to_purge.xml");
		
		List<ContentItem> items = composition.getContent();
		assertEquals("unexpected total sections before purge", 2, items.size());
		
		Section section = (Section) items.get(0);
		assertEquals("unexpected total section.items before purge", 2, 
				section.getItems().size());		
		
		RMUtil.purge(composition);
		
		items = composition.getContent();
		assertEquals("unexpected total sections after purge", 1, items.size());
		assertEquals("unexpected total section.items before purge", 1, 
				section.getItems().size());
	}

	private Composition composition;
}
