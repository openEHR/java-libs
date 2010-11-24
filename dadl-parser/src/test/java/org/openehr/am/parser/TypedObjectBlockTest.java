package org.openehr.am.parser;

public class TypedObjectBlockTest extends ParserTestBase {
	
	public void testParseTypedDvQuantity() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"typed_dv_quantity.dadl"));
		ContentObject obj = parser.parse();
		
		assertNotNull("contentObj null", obj);
		assertEquals("type identifier missing", "DvQuantity", 
				obj.getComplexObjectBlock().getTypeIdentifier());
	}
}
