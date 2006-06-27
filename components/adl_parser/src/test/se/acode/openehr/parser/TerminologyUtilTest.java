package se.acode.openehr.parser;

import org.openehr.rm.datatypes.text.DvCodedText;

import junit.framework.TestCase;

public class TerminologyUtilTest extends TestCase {
	
	/**
	 * Tests if terminology can get loaded
	 * 
	 * @throws Exception
	 */
	public void testLoadTerminlogy() throws Exception {
		TerminologyUtil tu = null;
		
		try {
			tu = TerminologyUtil.getInstance();
		} catch(Throwable e) {
			fail("Exception caught: " + e);
		}
		
		// total count
		assertEquals("count wrong", 65, tu.count());
		
		// instance of dvCodedText
		DvCodedText codedtext = tu.codedText("Pressure");
		assertNotNull("Pressure not found", codedtext);
		assertEquals("code wrong", "125", 
				codedtext.getDefiningCode().getCodeString());		
	}
	
	public void testFromCode() throws Exception {
		TerminologyUtil tu = TerminologyUtil.getInstance();
		
		DvCodedText codedText = tu.fromCode("128");
		assertEquals("rubric wrong", "time", codedText.getValue());
	}
}
