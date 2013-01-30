package org.openehr.am.parser;

import java.util.List;

public class KeyedObjectTest extends ParserTestBase {
	
	public void testParseAndVerifySimpleValues() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"keyed_objects.dadl"));
		ContentObject content = parser.parse();
		
		assertNotNull("contentObject is null", content);
		
		assertNull("contentObject.complexObjectBlock is not null",
				content.getComplexObjectBlock());
		
		assertNotNull("contentObject.attributeValues is null",
				content.getAttributeValues());
		
		assertEquals("contentObject.attributeValues.size wrong", 1,
				content.getAttributeValues().size());
		
		AttributeValue av = content.getAttributeValues().get(0);
		
		ObjectBlock ob = av.getValue();
		
		assertTrue("not multiple attribute object block",
				ob instanceof MultipleAttributeObjectBlock);
		
		MultipleAttributeObjectBlock maob = (MultipleAttributeObjectBlock) ob;
		
		List<KeyedObject> keyedObjects = maob.getKeyObjects();
		
		assertEquals("total keyed objects wrong", keyedObjects.size(), 2);
		
		assertKeyedObject(keyedObjects.get(0), 1, "name", "systolic");
		assertKeyedObject(keyedObjects.get(1), 2, "name", "diastolic");
	}
	
	private void assertKeyedObject(KeyedObject ko, int key, 
			String attribute, String value) {
		SimpleValue keyValue = ko.getKey();
		assertTrue("key is not int", keyValue instanceof IntegerValue);
		IntegerValue iv = (IntegerValue) keyValue;
		assertEquals("key value wrong", iv.getValue(), new Integer(key));
		
		ObjectBlock ob = ko.getObject();
		assertTrue("value is not single attribute object block, " + ob.getClass(), 
				ob instanceof SingleAttributeObjectBlock);
		SingleAttributeObjectBlock saob = (SingleAttributeObjectBlock) ob;
		List<AttributeValue> attributes = saob.getAttributeValues();
		assertEquals("total attribute wrong", attributes.size(), 1);
		AttributeValue av = attributes.get(0);
		assertEquals("attribute id wrong", av.getId(), attribute);
		
		ob = av.getValue();
		assertTrue("value is not primitive object block", 
				ob instanceof PrimitiveObjectBlock);		
		SimpleValue sv = ((PrimitiveObjectBlock) ob).getSimpleValue();
		assertTrue("value is not string", sv instanceof StringValue);
		StringValue str = (StringValue) sv;
		assertEquals("string value wrong", str.getValue(), value);
	}
}
