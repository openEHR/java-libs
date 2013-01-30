package org.openehr.am.parser;

public class EmptyAttributeListBlockTest extends ParserTestBase {
	
	public void testParseBlockWithEmptyAttrList() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"empty_attr_list.dadl"));
		ContentObject obj = parser.parse();
		
		assertNotNull("contentObj null", obj);
		assertEquals("type identifier missing", "DESTINATION_PROFILE", 
				obj.getComplexObjectBlock().getTypeIdentifier());
		
		assertTrue(obj.getComplexObjectBlock() instanceof SingleAttributeObjectBlock);
	}
	
	public void testParseEmptyAttrListWithoutTypeIdentifier() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"empty_attr_list_without_type.dadl"));
		ContentObject obj = parser.parse();
		
		assertNotNull("contentObj null", obj);
		
		assertNull(obj.getComplexObjectBlock().getTypeIdentifier());
		
		assertTrue(obj.getComplexObjectBlock() instanceof SingleAttributeObjectBlock);
	}
}
