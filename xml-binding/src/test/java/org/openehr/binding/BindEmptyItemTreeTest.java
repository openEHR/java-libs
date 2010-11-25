package org.openehr.binding;

import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.schemas.v1.*;

public class BindEmptyItemTreeTest extends XMLBindingTestBase {
	
	public void testBindXMLDvProportionToRM() throws Exception {
		ITEMTREE tree = ITEMTREE.Factory.parse(fromClasspath(
				"empty_item_tree.xml"));
		
		assertEquals(0, tree.getItemsArray().length);
		assertEquals(0, tree.sizeOfItemsArray());
		
		
		// do the data binding
		Object rmObj = binding.bindToRM(tree);
		
		assertTrue("unexpected type: " + 
				(rmObj == null ? "null" : rmObj.getClass()), 
				rmObj instanceof ItemTree);
		
		ItemTree itemTree = (ItemTree) rmObj;
		assertNotNull("unexpected null items attribute", itemTree.getItems());
		assertTrue(itemTree.getItems().isEmpty());
	}
}
