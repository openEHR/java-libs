package org.openehr.binding;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openehr.schemas.v1.*;

public class CreateXMLObjectTest extends XMLBindingTestBase {
	
	public void testCreateDVTEXT() throws Exception {
		DVTEXT dvtext = DVTEXT.Factory.newInstance();
		dvtext.setValue("test value");		
	}
	
	public void testCreateDvQuantity() throws Exception {
		DVQUANTITY dvq = DVQUANTITY.Factory.newInstance();
		dvq.setUnits("mmol/l");
		dvq.setMagnitude(6.1);		
	}
	
	public void testCreateElement() throws Exception {
		ELEMENT element = ELEMENT.Factory.newInstance();
		element.setName(dvText("name"));
		element.setValue(dvQuantity(6.1, "mmol/l"));
	}
	
	public void testCreateItemTree() throws Exception {
		ITEMTREE tree = ITEMTREE.Factory.newInstance();
		tree.setName(dvText("item tree"));
		ELEMENT element = element("element", 6.1, "mmol/l");
		tree.insertNewItems(0);
		tree.setItemsArray(0, element);
		
		assertNotNull(tree.getItemsArray(0));
		assertEquals("element", 
				((ELEMENT) tree.getItemsArray(0)).getName().getValue());
	}
	
	public void testCreatePointEvent() throws Exception {
		POINTEVENT pe = POINTEVENT.Factory.newInstance();
		pe.setName(dvText("any event"));
		pe.setTime(dvDateTime("2008-05-22T20:04:26"));
		pe.setData(itemTree());
	}
	
	public void testCreateObservation() throws Exception {
		OBSERVATION obs = OBSERVATION.Factory.newInstance();
		obs.setName(dvText("Lipid studies"));
		obs.setArchetypeNodeId("openEHR-EHR-OBSERVATION.laboratory-lipids.v1");
		obs.setLanguage(codePhrase("ISO_639-1", "en"));
		obs.setEncoding(codePhrase("IANA_character-sets", "UTF-8"));
		obs.setArchetypeDetails(archetyped(
				"openEHR-EHR-OBSERVATION.laboratory-lipids.v1",
				"Lipids", "1.0.1"));
		obs.setSubject(partySelf());
		obs.setData(history());
		//FileUtils.writeStringToFile(new File("xml.txt"), obs.toString(), null);
	}
	
	public void testCreateInstanceWithReflection() throws Exception {
		Class klass = Class.forName("org.openehr.schemas.v1.DVTEXT");
		Class factoryClass = klass.getClasses()[0];
		Method factoryMethod = factoryClass.getMethod("newInstance", null);
		Object obj = factoryMethod.invoke(null, null);
		assertTrue(obj instanceof DVTEXT);
	}
	
	public void testCallSetterWithReflection() throws Exception {
		Class klass = Class.forName("org.openehr.schemas.v1.DVTEXT");
		Class factoryClass = klass.getClasses()[0];
		Method factoryMethod = factoryClass.getMethod("newInstance", null);
		Object obj = factoryMethod.invoke(null, null);
		Method setterMethod = klass.getMethod("setValue", String.class);
		setterMethod.invoke(obj, "new value");
		
		assertEquals("new value", ((DVTEXT) obj).getValue());
	}
	
	private HISTORY history() {
		HISTORY history = HISTORY.Factory.newInstance();
		history.setName(dvText("data"));
		history.setOrigin(dvDateTime("2008-05-22T20:04:26"));
		history.insertNewEvents(0);
		history.setEventsArray(0, pointEvent());
		return history;
	}
	
	private PARTYSELF partySelf() {
		return PARTYSELF.Factory.newInstance();
	}
	
	private ARCHETYPED archetyped(String archetypeId, String templateId,
			String rmVersion) {
		ARCHETYPED archetyped = ARCHETYPED.Factory.newInstance();
		archetyped.setArchetypeId(archetypeId(archetypeId));
		archetyped.setTemplateId(templateId(templateId));
		archetyped.setRmVersion(rmVersion);
		return archetyped;
	}
	
	private TEMPLATEID templateId(String value) {
		TEMPLATEID tid = TEMPLATEID.Factory.newInstance();
		tid.setValue(value);
		return tid;
	}
	
	private ARCHETYPEID archetypeId(String value) {
		ARCHETYPEID aid = ARCHETYPEID.Factory.newInstance();
		aid.setValue(value);
		return aid;
	}	
	
	private TERMINOLOGYID terminologyId(String value) {
		TERMINOLOGYID tid = TERMINOLOGYID.Factory.newInstance();
		tid.setValue(value);
		return tid;
	}
	
	private CODEPHRASE codePhrase(String terminologyId, String code) {
		CODEPHRASE cp = CODEPHRASE.Factory.newInstance();
		cp.setTerminologyId(terminologyId(terminologyId));
		cp.setCodeString(code);
		return cp;
	}
	
	private POINTEVENT pointEvent() {
		POINTEVENT pe = POINTEVENT.Factory.newInstance();
		pe.setName(dvText("any event"));
		pe.setTime(dvDateTime("2008-05-22T20:04:26"));
		pe.setData(itemTree());
		return pe;
	}
	
	private ITEMTREE itemTree() {
		ITEMTREE tree = ITEMTREE.Factory.newInstance();
		tree.setName(dvText("data"));
		ELEMENT element = null;
		
		element = element("Total Cholesterol", 6.1, "mmol/l");
		tree.insertNewItems(0);
		tree.setItemsArray(0, element);
		
		element = element("Triglycerides", 2.3, "mmol/l");
		tree.insertNewItems(1);
		tree.setItemsArray(1, element);
		
		return tree;
	}
	
	private ELEMENT element(String name, double value, String units) {
		ELEMENT element = ELEMENT.Factory.newInstance();
		element.setName(dvText(name));
		element.setValue(dvQuantity(value, units));
		return element;
	}
	
	private DVDATETIME dvDateTime(String value) {
		DVDATETIME datetime = DVDATETIME.Factory.newInstance();
		datetime.setValue(value);
		return datetime;
	}

	private DVTEXT dvText(String value) {
		DVTEXT text = DVTEXT.Factory.newInstance();
		text.setValue(value);
		return text;
	}
	
	private DVQUANTITY dvQuantity(double magnitude, String units) {
		DVQUANTITY dvq = DVQUANTITY.Factory.newInstance();
		dvq.setUnits(units);
		dvq.setMagnitude(magnitude);
		return dvq;
	}
}
