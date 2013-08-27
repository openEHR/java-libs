package se.acode.openehr.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.ResourceDescriptionItem;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Testcase for archetype description parsing
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeDescriptionTest extends ParserTestBase {

	/**
	 * Verifies the content of description instance after parsing
	 * 
	 * @throws Exception
	 */
	public void testParseFullArchetypeDescription() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.archetype_description.test.adl"));
		Archetype archetype = parser.parse();
		ResourceDescription description = archetype.getDescription();

		assertNotNull("description null", description);
		Map<String, String> originalAuthor = description.getOriginalAuthor();
		assertEquals("name wrong", "Sam Heard", originalAuthor.get("name"));
		assertEquals("organisation wrong", "Ocean Informatics", originalAuthor
				.get("organisation"));
		assertEquals("date wrong", "23/04/2006", originalAuthor.get("date"));
		assertEquals("email wrong", "sam.heard@oceaninformatics.biz",
				originalAuthor.get("email"));

		List<String> otherContributors = description.getOtherContributors();
		assertNotNull(otherContributors);
		assertEquals(1, otherContributors.size());
		assertEquals("Ian McNicoll, MD", otherContributors.get(0));

		assertEquals("lifecycleState wrong", "AuthorDraft", description
				.getLifecycleState());

		assertEquals("resourcePackageUri",
				"www.aihw.org.au/data_sets/diabetic_archetypes.html",
				description.getResourcePackageUri());
		
		Map<String, String> map = description.getOtherDetails();
		assertEquals("details 1", map.get("other 1"));
		assertEquals("details 2", map.get("other 2"));
	

		// TODO: parent_resource

		List<ResourceDescriptionItem> details = description.getDetails();
		assertNotNull("details null", details);
		assertEquals("details size wrong", 1, details.size());

		ResourceDescriptionItem item = details.get(0);
		assertNotNull("descriptionItem null", item);
		CodePhrase language = new CodePhrase("ISO_639-1", "en");
		assertEquals("language wrong", language, item.getLanguage());

		assertEquals(
				"purpose wrong",
				"For recording a problem, condition or"
						+ " issue that has ongoing significance to the person's health.",
				item.getPurpose());

		assertEquals("use wrong", "Used for recording any problem, present or"
				+ " past - so is used for recording past history as well as "
				+ "current problems. Used with changed 'Subject of care' for "
				+ "recording problems of relatives and so for family history.",
				item.getUse());

		assertEquals("misuse wrong", "Use specialisations for medical "
				+ "diagnoses, 'openEHR-EHR-EVALUATION.problem-diagnosis' and "
				+ "histological diagnoses 'openEHR-EHR-EVALUATION.problem-"
				+ "diagnosis-histological'", item.getMisuse());

		assertEquals("copyright wrong", "copyright (c) 2004 The openEHR "
				+ "Foundation", item.getCopyright());

		List<String> keywords = new ArrayList<String>();
		keywords.add("issue");
		keywords.add("condition");
		assertEquals("keywords wrong", keywords, item.getKeywords());

		map  = details.get(0).getOriginalResourceUri();
		assertEquals("http://guidelines.are.us/wherever/fr", 
				map.get("ligne guide"));
		assertEquals("http://some%20medline%20ref", map.get("medline"));
		
		map = details.get(0).getOtherDetails();
		assertEquals("item details 1", map.get("item other 1"));
		assertEquals("item details 2", map.get("item other 2"));
	}

	public void testParseOriginalAuthorAsLast() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.archetype_description2.test.adl"));
		try {
			parser.parse();
		} catch (Exception e) {
			e.printStackTrace();
			fail("failed to parse original author as last");
		}
	}

	public void testParseEmptyOtherContributors() throws Exception {
		ADLParser parser = new ADLParser(
				loadFromClasspath("adl-test-entry.empty_other_contributors.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull("failed to parse empty other contributors", archetype);
		assertNull("other_contributors not null", archetype.getDescription()
				.getOtherContributors());
	}

}

