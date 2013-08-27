package org.openehr.rm.binding;

import java.io.*;
import java.util.*;

import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datatypes.basic.DvBoolean;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

public class BindRMToDADLTest extends DADLBindingTestBase {
	
	public void tearDown() {
		expected = null;
		actual = null;
	}
	
	public void testBindDvText() throws Exception {
		DvText dvText = new DvText("sitting");
		expected = readLines("typed_dv_text.dadl");
		actual = binding.toDADL(dvText);
		assertEquals(expected, actual);
	}
	
	public void testBindDvBoolean() throws Exception {
		DvBoolean dv = new DvBoolean("true");
		expected = readLines("typed_dv_boolean.dadl");
		actual = binding.toDADL(dv);
		assertEquals(expected, actual);
	}
	
	public void testBindDvQuantity() throws Exception {
		DvQuantity q = new DvQuantity("mmHg", 120.0, 1);
		expected = readLines("typed_dv_quantity2.dadl");
		actual = binding.toDADL(q);		
		assertEquals(expected, actual);		
	}
	
	public void testBindDvOrdinal() throws Exception {
		DvOrdinal ordinal = new DvOrdinal(1, 
				new DvCodedText("Sitting", 
						new CodePhrase("SNOMED-CT", "12345678")));
		
		expected = readLines("typed_dv_ordinal.dadl");
		actual = binding.toDADL(ordinal);
		
		assertLinesEqual(expected, actual);
	}
	
	public void testBindDvDateTime() throws Exception {
	    DvDateTime dv = (new DvDateTime(2013, 03, 04, 16, 46, 16, TimeZone.getDefault()));
	    List<String> dvStrLines = binding.toDADL(dv);
	    Object rmObj2 = bindString(toString(dvStrLines));
	    assertEquals(dv, rmObj2);
	}
	
	public void testBindPointEvent() throws Exception {
		rmObj = bind("point_event2.dadl");
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not PointEvent: " + rmObj.getClass().getName(), 
				rmObj instanceof PointEvent);
		
		PointEvent event = (PointEvent) rmObj;
		
		expected = readLines("point_event2.dadl");
		actual = binding.toDADL(event);
		
		assertLinesEqual(expected, actual);
	}
	
	public void testBindHistory() throws Exception {
		rmObj = bind("history.dadl");
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not History: " + rmObj.getClass().getName(), 
				rmObj instanceof History);
		
		History history = (History) rmObj;
		
		expected = readLines("history2.dadl");
		actual = binding.toDADL(history);
		
		//print(actual);
		
		assertLinesEqual(expected, actual);
	}
	
	public void testBindObservation() throws Exception {
		rmObj = bind("observation.dadl");
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Observation: " + rmObj.getClass().getName(), 
				rmObj instanceof Observation);
		
		Observation obs = (Observation) rmObj;
		
		expected = readLines("observation3.dadl");
		actual = binding.toDADL(obs);
		
		//print(actual);
		
		String dadl = toString(actual);
		
		// round-trip!!
		rmObj = bindString(dadl);
		
		assertNotNull("rmObject null", rmObj);
		assertTrue("rmObject not Observation: " + rmObj.getClass().getName(), 
				rmObj instanceof Observation);
		
		obs = (Observation) rmObj;
		List<String> afterRoundTrip = binding.toDADL(obs);
	
		assertLinesEqual(actual, afterRoundTrip);
	}
	
	private List<String> readLines(String name) throws Exception {
		List<String> lines = new ArrayList<String>();
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(
				this.getClass().getClassLoader().getResourceAsStream(name)));
		
		String line = reader.readLine();
		while(line != null) {
			lines.add(line);
			line = reader.readLine();
		}		
		return lines;
	}
	
	private String toString(List<String> lines) {
		StringBuffer buf = new StringBuffer();
		for(int i = 0, j = lines.size(); i < j; i++) {
			buf.append(lines.get(i));
		}
		return buf.toString();
	}
	
	private void assertLinesEqual(List<String> expected, List<String> actual) {
		assertEquals("line number differ, expected: " + 
				expected.size() + ", but got: " + actual.size(), 
				expected.size(), actual.size());
		for(int i = 0; i < expected.size(); i++) {			
			assertEquals("line " + i + " differ", expected.get(i).trim(), 
					actual.get(i).trim());
		}
	}
	
	private void print(List<String> lines) {
		System.out.println("total lines: " + lines.size());
		for(Iterator<String> it = lines.iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
	}
	
	private List<String> expected;
	private List<String> actual;
}
