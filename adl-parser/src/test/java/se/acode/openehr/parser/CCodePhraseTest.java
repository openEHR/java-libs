package se.acode.openehr.parser;

import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;

import java.util.*;

/**
 * Test case tests parsing of domain type constraints extension.
 *
 * @author Rong Chen
 * @version 1.0
 */

public class CCodePhraseTest extends ParserTestBase {

	public CCodePhraseTest(String test) throws Exception {
		super(test);
	}

	/**
	 * The fixture set up called before every test method.
	 */
	protected void setUp() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.c_code_phrase.test.adl"));
		archetype = parser.parse();
	}

	/**
	 * The fixture clean up called after every test method.
	 */
	protected void tearDown() throws Exception {
		archetype = null;
	}

	/**
	 * Verifies parsing of a simple CCodePhrase
	 * 
	 * @throws Exception
	 */
	public void testParseExternalCodes() throws Exception {
		CObject node = archetype.node("/types[at0001]/items[at10002]/value");
		String[] codes = { "F43.00", "F43.01", "F32.02" };
		assertCCodePhrase(node, "icd10", codes, null);
	}
	
	public void testParseExternalCodesWithAssumedValue() throws Exception {
		CObject node = archetype.node("/types[at0001]/items[at10005]/value");
		String[] codes = { "F43.00", "F43.01", "F32.02" };
		assertCCodePhrase(node, "icd10", codes, "F43.00");
	}
	
	/**
	 * Verifies parsing of a simple CCodePhrase with codes defined locally
	 * 
	 * @throws Exception
	 */
	public void testParseLocalCodes() throws Exception {
		CObject node = archetype.node("/types[at0001]/items[at10003]/value");
		String[] codeList = { "at1311","at1312", "at1313", "at1314","at1315" }; 
		assertCCodePhrase(node, "local", codeList, null);
	}
	
	public void testParseEmptyCodeList() throws Exception {
		CObject node = archetype.node("/types[at0001]/items[at10004]/value");
		String[] codeList = null; 
		assertCCodePhrase(node, "icd10", codeList, null);
	}
	
	private void assertCCodePhrase(CObject actual, String terminologyId,
			String[] codes, String assumedValue) {
		
		// check type
		assertTrue("CCodePhrase expected, got " + actual.getClass(), 
				actual instanceof CCodePhrase);
		CCodePhrase cCodePhrase = (CCodePhrase) actual;
		
		// check terminology
		assertEquals("terminology", terminologyId, 
				cCodePhrase.getTerminologyId().getValue());
		
		// check code list
		if(codes == null) {
			assertNull("codeList expected null", cCodePhrase.getCodeList());
		} else {
			List<String> codeList = cCodePhrase.getCodeList();
			assertEquals("codes.size wrong", codes.length, codeList.size());		
			for (int i = 0; i < codes.length; i++) {
				Object c = codeList.get(i);
				assertEquals("code wrong, got: " + c, codes[i], c);
			}
		}
		
		// check assumed value
		if(assumedValue == null) {
			assertFalse(cCodePhrase.hasAssumedValue());
		} else {
			assertTrue("expected assumedValue", cCodePhrase.hasAssumedValue());
			assertEquals("assumed value wrong", assumedValue, 
				cCodePhrase.getAssumedValue().getCodeString());
		}
	}

	private Archetype archetype;
}
