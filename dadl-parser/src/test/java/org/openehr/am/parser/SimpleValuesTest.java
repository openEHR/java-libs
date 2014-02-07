package org.openehr.am.parser;

import java.util.List;

import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;

public class SimpleValuesTest extends ParserTestBase {
	
	public void testParseAndVerifySimpleValues() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"simple_values.dadl"));
		ContentObject content = parser.parse();
		
		assertNotNull("contentObject is null", content);
		
		assertNull("contentObject.complexObjectBlock is not null",
				content.getComplexObjectBlock());
		
		assertNotNull("contentObject.attributeValues is null",
				content.getAttributeValues());
		
		assertEquals("contentObject.attributeValues.size wrong", 1,
				content.getAttributeValues().size());
		
		AttributeValue av = content.getAttributeValues().get(0);
		
		assertEquals("attribute id wrong", "simple_values", av.getId());
		
		ObjectBlock ob = av.getValue();
		
		assertTrue("attribute value type wrong",
				ob instanceof SingleAttributeObjectBlock);
		
		SingleAttributeObjectBlock single = (SingleAttributeObjectBlock) ob;
		
		List<AttributeValue> values = single.getAttributeValues();
		
		assertEquals("total number of simple values wrong", 10, values.size());
		
		assertDateTimeValue(values.get(0), "2007-10-30T09:22:00");
		
		assertDateValue(values.get(1), "2008-04-02");
		
		assertTimeValue(values.get(2), "11:09:40");
		
		assertDurationValue(values.get(3), "PT10M");
		
		assertStringValue(values.get(4), "a string value");
		
		assertCharacterValue(values.get(5), 'a');
		
		assertIntegerValue(values.get(6), 100);
		
		assertRealValue(values.get(7), 9.5);
		
		assertBooleanValue(values.get(8), true);
	}	
	
	public void testParseValueList() throws Exception {
		DADLParser parser = new DADLParser(loadFromClasspath(
			"simple_values_list.dadl"));
		ContentObject content = parser.parse();
		
		assertNotNull("contentObject is null", content);
	}
	
	private void assertDateTimeValue(AttributeValue attr, String value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not datetime value", 
				pob.getSimpleValue() instanceof DateTimeValue);
		DateTimeValue actual = (DateTimeValue) pob.getSimpleValue();
		DvDateTime expected = new DvDateTime(value);
		assertEquals("datetime value wrong", actual.getValue(), expected);
	}
	
	private void assertDateValue(AttributeValue attr, String value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not date value", 
				pob.getSimpleValue() instanceof DateValue);
		DateValue actual = (DateValue) pob.getSimpleValue();
		DvDate expected = new DvDate(value);
		assertEquals("date value wrong", actual.getValue(), expected);
	}
	
	private void assertTimeValue(AttributeValue attr, String value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not time value", 
				pob.getSimpleValue() instanceof TimeValue);
		TimeValue actual = (TimeValue) pob.getSimpleValue();
		DvTime expected = new DvTime(value);
		assertEquals("time value wrong", actual.getValue(), expected);
	}
	
	private void assertDurationValue(AttributeValue attr, String value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not duration value", 
				pob.getSimpleValue() instanceof DurationValue);
		DurationValue actual = (DurationValue) pob.getSimpleValue();
		DvDuration expected = new DvDuration(value);
		assertEquals("duration value wrong", actual.getValue(), expected);
	}
	
	private void assertStringValue(AttributeValue attr, String value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not string value", 
				pob.getSimpleValue() instanceof StringValue);
		StringValue str = (StringValue) pob.getSimpleValue();
		assertEquals("string value wrong", str.getValue(), value);
	}
	
	private void assertCharacterValue(AttributeValue attr, char value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not Character value", 
				pob.getSimpleValue() instanceof CharacterValue);
		CharacterValue actual = (CharacterValue) pob.getSimpleValue();	
		Character expected = new Character(value);
		assertEquals("character value wrong", actual.getValue(), expected);
	}
	
	private void assertIntegerValue(AttributeValue attr, int value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not integer value", 
				pob.getSimpleValue() instanceof IntegerValue);
		IntegerValue actual = (IntegerValue) pob.getSimpleValue();	
		Integer expected = new Integer(value);
		assertEquals("integer value wrong", actual.getValue(), expected);
	}
	
	private void assertRealValue(AttributeValue attr, double value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not real value", 
				pob.getSimpleValue() instanceof RealValue);
		RealValue actual = (RealValue) pob.getSimpleValue();	
		Double expected = new Double(value);
		assertEquals("real value wrong", actual.getValue(), expected);
	}
	
	private void assertBooleanValue(AttributeValue attr, boolean value) {
		assertTrue(attr.getValue() instanceof PrimitiveObjectBlock);
		PrimitiveObjectBlock pob = (PrimitiveObjectBlock) attr.getValue();
		assertTrue("not boolean value", 
				pob.getSimpleValue() instanceof BooleanValue);
		BooleanValue actual = (BooleanValue) pob.getSimpleValue();	
		Boolean expected = new Boolean(value);
		assertEquals("boolean value wrong", actual.getValue(), expected);
	}
}
