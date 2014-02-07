package org.openehr.am.parser;

public class TypedObjectBlockTest extends ParserTestBase {
	
	public void testParseTypedDvQuantity() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath("typed_dv_quantity.dadl"));
		ContentObject obj = parser.parse();
		
		assertNotNull("contentObj null", obj);
      
      // message, expected, actual
      assertEquals("type identifier missing", "DV_QUANTITY", 
				obj.getComplexObjectBlock().getTypeIdentifier());
	}
}
