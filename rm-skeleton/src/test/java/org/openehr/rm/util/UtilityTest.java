package org.openehr.rm.util;

import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.DvText;

public class UtilityTest extends SkeletonGeneratorTestBase {
	
	public UtilityTest() throws Exception {		
	}
	
	public void testRetrieveName() throws Exception {
		archetype = loadArchetype("openEHR-EHR-ITEM_TREE.medication_test_one.v1.adl");
		String expected = "Medication name";
		DvText retrievedName = generator.retrieveName("at0001", archetype);
		
		assertEquals("failed to retrieve name", expected, retrievedName.getValue());
	}
	
	public void testCreateFromEmptyCDvQuantity() throws Exception {
		CDvQuantity any = CDvQuantity.anyAllowed("/at0001");
		DvQuantity dq = generator.createDvQuantity(any);
		assertNotNull("failed to create dv_quantity out of any-allowed c_dv_quantity");
	}
	
	
}
