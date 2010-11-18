package org.openehr.rm.common.archetyped;

import java.util.*;

import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.TestMeasurementService;

import junit.framework.TestCase;

/**
 * Test cases for path-based value setting implemented in Locatable class.
 * 
 * @author rong.chen
 *
 */

public class PathBasedValueSettingTest extends TestCase {
	
	public void testSetDvTextOnElement() throws Exception {
		String text = "test text";
		DvText dvText = new DvText(text);
		Element element = new Element("at0001", "element", dvText);
		element.setValue(dvText);
		
		// set the value with path
		text = "new text";
		DvText newText = new DvText(text);
		element.set("/value", newText);
		
		DvText actual = (DvText) element.getValue();
		assertEquals("failed to set dvText value with path", text, 
				actual.getValue());
	}
	
	public void testSetDvTexStringValuetOnElement() throws Exception {
		String text = "test text";
		DvText dvText = new DvText(text);
		Element element = new Element("at0001", "element", dvText);
		element.setValue(dvText);
		
		// set the value with path
		text = "new text";
		element.set("/value/value", text);
		
		DvText actual = (DvText) element.getValue();
		assertEquals("failed to set dvText string value with path", text, 
				actual.getValue());
	}
	
	public void testSetDvQuantityOnItemList() throws Exception {
		DvQuantity dvq = new DvQuantity("mg", 2.3, 1, ms);
		Element element = new Element("at0002", "element", dvq);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		// set the value with path
		dvq = new DvQuantity("mg", 6.2, 1, ms);
		list.set("/items[at0002]/value", dvq);
		
		DvQuantity actual = (DvQuantity) list.getItems().get(0).getValue();
		assertEquals("failed to set dvQuantity on list", 6.2, actual.getMagnitude());
	}
	
	public void testSetDvQuantityMagnitudeOnItemList() throws Exception {
		DvQuantity dvq = new DvQuantity("mg", 2.3, 1, ms);
		Element element = new Element("at0002", "element", dvq);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		// set the value with path
		Double d = new Double(6.2);
		list.set("/items[at0002]/value/magnitude", d);
		
		DvQuantity actual = (DvQuantity) list.getItems().get(0).getValue();
		assertEquals("failed to set dvQuantity on list", 6.2, actual.getMagnitude());
	}
	
	public void testSetDvCodedTextOnItemList() throws Exception {
		CodePhrase cp = new CodePhrase("SNOMED-CT", "12345678");
		DvCodedText coded = new DvCodedText("yes", cp);
		Element element = new Element("at0002", "element", coded);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		// set the value with path
		cp = new CodePhrase("SNOMED-CT", "12340000");
		coded = new DvCodedText("nope", cp);
		list.set("/items[at0002]/value", coded);
		
		DvCodedText actual = (DvCodedText) list.getItems().get(0).getValue();
		assertEquals("failed to set codedText.value on list", "nope", actual.getValue());
		assertEquals("failed to set codedText.definingCode on list", "12340000", 
				actual.getDefiningCode().getCodeString());
	}
	
	public void testSetDvCodedTextDefiningCodeStringOnItemList() throws Exception {
		CodePhrase cp = new CodePhrase("SNOMED-CT", "12345678");
		DvCodedText coded = new DvCodedText("yes", cp);
		Element element = new Element("at0002", "element", coded);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		// set the value with path
		String newCode = "12340000";
		list.set("/items[at0002]/value/defining_code/code_string", newCode);
		
		DvCodedText actual = (DvCodedText) list.getItems().get(0).getValue();
		assertEquals("failed to set codedText.definingCode on list", "12340000", 
				actual.getDefiningCode().getCodeString());
	}
	
	public void testSetDvCodedTextStringValueOnItemList() throws Exception {
		CodePhrase cp = new CodePhrase("SNOMED-CT", "12345678");
		DvCodedText coded = new DvCodedText("yes", cp);
		Element element = new Element("at0002", "element", coded);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		// set the value with path
		String text = "nope";
		list.set("/items[at0002]/value/value", text);
		
		DvCodedText actual = (DvCodedText) list.getItems().get(0).getValue();
		assertEquals("failed to set codedText.value on list", "nope", actual.getValue());
	}
	
	public void testSetDvOrdinalOnItemList() throws Exception {
		CodePhrase cp = new CodePhrase("SNOMED-CT", "12345678");
		DvCodedText coded = new DvCodedText("yes", cp);
		DvOrdinal ordinal = new DvOrdinal(1, coded);
		Element element = new Element("at0002", "element", ordinal);
		List<Element> items = new ArrayList<Element>();
		items.add(element);
		ItemList list = new ItemList("at0001", "list", items);
		
		// set the value with path
		cp = new CodePhrase("SNOMED-CT", "12340000");
		coded = new DvCodedText("nope", cp);
		ordinal = new DvOrdinal(2, coded);
		list.set("/items[at0002]/value", ordinal);
		
		DvOrdinal actual = (DvOrdinal) list.getItems().get(0).getValue();
		assertEquals("failed to set ordinal.value on list", 2, actual.getValue());
		assertEquals("failed to set ordinal.symbol.value on list", "nope", 
				actual.getSymbol().getValue());
	}
	
	private MeasurementService ms = new TestMeasurementService();
}
