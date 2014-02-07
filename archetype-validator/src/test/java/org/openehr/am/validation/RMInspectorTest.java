package org.openehr.am.validation;

import java.util.Set;

import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.common.generic.PartyProxy;

import junit.framework.TestCase;

public class RMInspectorTest extends TestCase {

	public void setUp() throws Exception {
		inspector = new RMInspector();
	}
	
	public void testRetrieveRMTypeWithElement() throws Exception {
		Class klass = inspector.retrieveRMType("ELEMENT");
		assertTrue("incorrect class for element", klass.equals(Element.class));
	}
	
	public void testRetrieveRMTypeWithEvaluation() throws Exception {
		Class klass = inspector.retrieveRMType("EVALUATION");
		assertTrue("incorrect class for evaluation", klass.equals(Evaluation.class));
	}
	
	public void testRetrieveRMTypeWithInteger() throws Exception {
		Class klass = inspector.retrieveRMType("INTEGER");
		assertTrue("incorrect class for integer", klass.equals(Integer.class));
	}
	
	public void testRetrieveRMTypeWithDvQuantity() throws Exception {
		Class klass = inspector.retrieveRMType("DV_QUANTITY");
		assertTrue("incorrect class for dvQuantity", klass.equals(DvQuantity.class));
	}
	
	public void testRetrieveRMTypeWithDvURI() throws Exception {
		Class klass = inspector.retrieveRMType("DV_URI");
		assertTrue("incorrect class for dvUri", klass.equals(DvURI.class));
	}
	
	public void testRetrieveRMTypeWithAbstractClassEVENT() throws Exception {
		Class klass = inspector.retrieveRMType("EVENT");
		assertNotNull("failed to retrieve EVENT", klass);
		assertTrue("incorrect class for Event", klass.equals(Event.class));
	}
	
	public void testRetrieveRMTypeWithAbstractClassPartyProxy() throws Exception {
		Class klass = inspector.retrieveRMType("PARTY_PROXY");
		assertNotNull("failed to retrieve PARTY_PROXY", klass);
		assertTrue("incorrect class for PARTY_PROXY", klass.equals(PartyProxy.class));
	}
	
	public void testRetrieveRMAttributeNamesWithEVENT() throws Exception {
		assertFalse("failed to retrieve attribute names with EVENT",
				inspector.retrieveRMAttributeNames("EVENT").isEmpty());
	}
	
	public void testRetrieveRMAttributeNamesWithObservation() throws Exception {
		Set<String> attrs = inspector.retrieveRMAttributeNames("OBSERVATION");
		assertTrue("data attribute missing", attrs.contains("data"));
		assertTrue("state attribute missing", attrs.contains("state"));
		assertTrue("protocol attribute missing", attrs.contains("protocol"));
		assertTrue("subject attribute missing", attrs.contains("subject"));
		assertTrue("provider attribute missing", attrs.contains("provider"));		
	}
	
	public void testRetrieveRMAttributeNamesWithCodedText() throws Exception {
		Set<String> attrs = inspector.retrieveRMAttributeNames("DV_CODED_TEXT");
		assertTrue("defining_code attribute missing", 
				attrs.contains("defining_code"));				
	}
	
	private RMInspector inspector;
}
