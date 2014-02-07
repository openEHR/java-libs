package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.rm.support.basic.Interval;

public class ArchetypeInternalRefTest extends ParserTestBase {
	public void testParseInternalRefWithOverwrittingOccurrences() 
			throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.archetype_internal_ref.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);

		ArchetypeConstraint node = archetype.node("/attribute2");
		assertTrue("ArchetypeInternalRef expected, actual: " + node.getClass(),
				node instanceof ArchetypeInternalRef);

		ArchetypeInternalRef ref = (ArchetypeInternalRef) node;
		assertEquals("rmType wrong", "SECTION", ref.getRmTypeName());
		assertEquals("path wrong", "/attribute1", ref.getTargetPath());

		Interval<Integer> occurrences = new Interval<Integer>(1, 2);
		assertEquals("overwriting occurrences wrong", occurrences, ref
				.getOccurrences());
	}

	public void testParseInternalRefWithGenerics() throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-SOME_TYPE.generic_type_use_node.draft.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);
		
		ArchetypeConstraint node = archetype.node("/interval_attr2");
		assertTrue("ArchetypeInternalRef expected, actual: " + node.getClass(),
				node instanceof ArchetypeInternalRef);

		ArchetypeInternalRef ref = (ArchetypeInternalRef) node;
		assertEquals("rmType wrong", "INTERVAL<QUANTITY>", 
				ref.getRmTypeName());
		assertEquals("path wrong", "/interval_attr[at0001]", 
				ref.getTargetPath());
	}
	
	public void testParseInternalRefWithCommentWithSlashAfterOnlyOneSlashInTarget() 
			throws Exception {
		ADLParser parser = new ADLParser(loadFromClasspath(
				"adl-test-entry.archetype_internal_ref2.test.adl"));
		Archetype archetype = parser.parse();
		assertNotNull(archetype);

	}
	
}
