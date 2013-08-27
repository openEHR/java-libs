package org.openehr.am.parser;

public class SingleValueListTest extends ParserTestBase {
	
	// verify the fix of a bug introducing duplicated items in single value list
	public void testParseSingleValueLists() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"single_values_list.dadl"));
		content = parser.parse();
		assertEquals(1, listSize(0));
		assertEquals(1, listSize(1));
		assertEquals(1, listSize(2));
		assertEquals(1, listSize(3));
		assertEquals(1, listSize(4));
		assertEquals(1, listSize(5));
		assertEquals(1, listSize(6));
		assertEquals(1, listSize(7));
		assertEquals(1, listSize(8));
		assertEquals(1, listSize(9));		
	}
	
	int listSize(int index) {
		return ((PrimitiveObjectBlock) ((AttributeValue) 
				((SingleAttributeObjectBlock) content.getAttributeValues()
						.get(0).getValue()).getAttributeValues().get(index))
						.getValue()).getSimpleListValue().size();
	}
	
	private ContentObject content;		
}
