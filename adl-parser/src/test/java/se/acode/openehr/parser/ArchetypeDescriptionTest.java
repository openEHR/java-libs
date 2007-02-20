/*
 * Copyright (C) 2006 Rong Chen, Acode HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package se.acode.openehr.parser;

import java.util.*;

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
	public void testArchetypeDescription() throws Exception {
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
		assertEquals(2, otherContributors.size());
		assertEquals("Author1", otherContributors.get(0));
		assertEquals("Author2", otherContributors.get(1));

		assertEquals("lifecycleState wrong", "AuthorDraft", description
				.getLifecycleState());

		// archetype_package_uri in ADL 
		assertEquals("resourcePackageUri",
				"www.aihw.org.au/data_sets/diabetic_archetypes.html",
				description.getResourcePackageUri());

		// TODO: other_details
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

		// TODO: original_resource_uri
		// TODO: other_details
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
