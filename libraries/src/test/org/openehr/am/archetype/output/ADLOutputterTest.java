/**
 * ADLOutputterTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.output;

import junit.framework.TestCase;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.description.ArchetypeDescription;
import org.openehr.am.archetype.description.ArchetypeDescriptionItem;
import org.openehr.am.archetype.constraintmodel.domain.*;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.Cardinality;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.rm.support.terminology.TestCodeSet;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.identification.ArchetypeID;

import java.util.*;
import java.io.*;

public class ADLOutputterTest extends TestCase {

    public ADLOutputterTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        outputter = new ADLOutputter();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        outputter = null;
    }

    public void testPrintHeader() throws Exception {
        String id = "openEHR-EHR-EVALUATION.adverse_reaction-medication.v1";
        String parentId = "openEHR-EHR-EVALUATION.adverse_reaction.v1";
        String conceptCode = "at0000";

        clean();
        outputter.printHeader(new ArchetypeID(id),
                new ArchetypeID(parentId), conceptCode, out);

        verify("archetype\r\n" +
                "    " + id + "\r\n" +
                "specialize\r\n" +
                "    " + parentId + "\r\n" +
                "concept\r\n" +
                "    [" + conceptCode + "]\r\n");
    }

    public void testPrintDescriptionItem() throws Exception {
        String purpose = "purpose";
        String use = "use";
        String misuse = "misuse";
        String copyright = "copyright";
        ArchetypeDescriptionItem item = new ArchetypeDescriptionItem(
                TestCodeSet.ENGLISH, purpose, use, misuse, copyright);

        clean();
        outputter.printDescriptionItem(item, 0, out);

        verify("description(\"" + TestCodeSet.ENGLISH.getCodeString() +
                "\") = <\r\n" +
                "    purpose = <\"purpose\">\r\n" +
                "    use = <\"use\">\r\n" +
                "    misuse = <\"misuse\">\r\n" +
                "    copyright = <\"copyright\">\r\n" +
                ">\r\n");
    }

    public void testPrintDescription() throws Exception {
        String author = "Jerry Mouse";
        String status = "draft";
        String organisation = "Mouse Academy";

        List<ArchetypeDescriptionItem> items =
                new ArrayList<ArchetypeDescriptionItem>();
        String[][] others = {
            {"revision", "1.1"},
            {"adl_version", "0.9"},
            {"rights", "all rights reserved"}
        };
        Map<String, String> otherDetails = new HashMap<String, String>();
        for (String[] pair : others) {
            otherDetails.put(pair[ 0 ], pair[ 1 ]);
        }
        ArchetypeDescriptionItem item = new ArchetypeDescriptionItem(
                TestCodeSet.ENGLISH, "purpose of this archetype");
        items.add(item);
        ArchetypeDescription description = new ArchetypeDescription(author,
                organisation, status, null, items, null);

        clean();
        outputter.printDescription(description, out);

        verify("description\r\n" +
                "    author = <\"" + author + "\">\r\n" +
                "    status = <\"" + status + "\">\r\n" +
                "    description(\"en\") = <\r\n" +
                "        purpose = <\"purpose of this archetype\">\r\n" +
                "    >\r\n");
    }

    public void testPrintOntology() throws Exception {
        DefinitionItem item = new DefinitionItem("at0001",
                "text at0001", "desc at0001");
        List<DefinitionItem> items = new ArrayList<DefinitionItem>();
        items.add(item);
        item = new DefinitionItem("at0002", "text at0002", "desc at0002");
        items.add(item);
        OntologyDefinitions definitions = new OntologyDefinitions("en", items);
        List<OntologyDefinitions> termDefinitionsList =
                new ArrayList<OntologyDefinitions>();
        termDefinitionsList.add(definitions);
        List<String> languages = new ArrayList<String>();
        languages.add("en");
        List<String> terminologies = new ArrayList<String>();
        terminologies.add("en");
        ArchetypeOntology ontology = new ArchetypeOntology("en", languages,
                terminologies, termDefinitionsList, null, null, null);

        clean();
        outputter.printOntology(ontology, out);
        verify("ontology\r\n" +
                "    primary_language = <\"en\">\r\n" +
                "    languages_available = <\"en\", ...>\r\n" +
                "    term_definitions(\"en\") = <\r\n" +
                "        items(\"at0001\") = <\r\n" +
                "            text = <\"text at0001\">\r\n" +
                "            description = <\"desc at0001\">\r\n" +
                "        >\r\n" +
                "        items(\"at0002\") = <\r\n" +
                "            text = <\"text at0002\">\r\n" +
                "            description = <\"desc at0002\">\r\n" +
                "        >\r\n" +
                "    >\r\n");
    }

    public void testPrintExistence() throws Exception {
        clean();
        outputter.printExistence(CAttribute.Existence.REQUIRED, out);
        verify("");

        clean();
        outputter.printExistence(CAttribute.Existence.OPTIONAL, out);
        verify("existence matches {0..1}");

        clean();
        outputter.printExistence(CAttribute.Existence.NOT_ALLOWED, out);
        verify("existence matches {0}");
    }

    public void testPrintCardinality() throws Exception {
        clean();
        outputter.printCardinality(Cardinality.LIST, out);
        verify("cardinality matches {*; ordered}");

        clean();
        outputter.printCardinality(Cardinality.SET, out);
        verify("cardinality matches {*; unordered; unique}");

        clean();
        Cardinality cardinality = new Cardinality(true, true,
                new Interval<Integer>(2, 8));
        outputter.printCardinality(cardinality, out);
        verify("cardinality matches {2..8; ordered; unique}");
    }

    public void testPrintCCodedText() throws Exception {
        String[] codes = {"at2001", "at2002", "at2003"};
        String terminology = "local";
        CCodedText ccoded = new CCodedText("/path", terminology,
                Arrays.asList(codes));

        clean();
        outputter.printCCodedText(ccoded, 1, out);
        verify("    [" + terminology + "::\r\n" +
                "    " + codes[ 0 ] + ",\r\n" +
                "    " + codes[ 1 ] + ",\r\n" +
                "    " + codes[ 2 ] + "]\r\n");
    }

    public void testPrintCCount() throws Exception {
        clean();
        CCount ccount = new CCount("/path", new Interval<Integer>(0, 3));
        outputter.printCCount(ccount, 0, out);
        verify("COUNT matches {\r\n" +
                "    magnitude matches {|0..3|}\r\n" +
                "}\r\n");
    }

    public void testPrintCOrdinal() throws Exception {
        List<Ordinal> list = new ArrayList<Ordinal>();
        for (int i = 1; i <= 4; i++) {
            list.add(new Ordinal(i, "local", "at200" + i));
        }
        COrdinal cordinal = new COrdinal("/path", list);
        clean();
        outputter.printCOrdinal(cordinal, 0, out);
        verify("1|[local::at2001],\r\n" +
                "2|[local::at2002],\r\n" +
                "3|[local::at2003],\r\n" +
                "4|[local::at2004]\r\n");
    }

    public void testPrintCQuantity() throws Exception {
        CQuantity cquantity = new CQuantity("/path",
                new Interval<Double>(0.0, 20.0), "mg");
        clean();
        outputter.printCQuantity(cquantity, 0, out);
        verify("QUANTITY matches {\r\n" +
                "    magnitude matches {|0.0..20.0|}\r\n" +
                "    units matches {\"mg\"}\r\n" +
                "}\r\n");
    }

    public void testPrintInterval() throws Exception {
        clean();
        outputter.printInterval(new Interval<Integer>(0, 10), out);
        verify("|0..10|");

        clean();
        outputter.printInterval(new Interval<Integer>(null, 10, false, false),
                out);
        verify("|<10|");

        clean();
        outputter.printInterval(new Interval<Integer>(null, 10, false, true),
                out);
        verify("|<=10|");

        clean();
        outputter.printInterval(new Interval<Integer>(0, null, false, false),
                out);
        verify("|>0|");

        clean();
        outputter.printInterval(new Interval<Integer>(0, null, true, false),
                out);
        verify("|>=0|");
    }

    /* verify bug fix */
    public void testPrintCString() throws Exception {
        clean();
        List<String> values = new ArrayList<String>();
        String value = "some value";
        values.add(value);
        outputter.printCString(new CString(null, values), out);
        verify("\"" + value + "\"");
    }

    /* test archetype ontology */
    private ArchetypeOntology ontology() {
        List<DefinitionItem> items = new ArrayList<DefinitionItem>();
        for (int i = 0; i < 99; i++) {
            items.add(new DefinitionItem("at000" + i, "text" + i, "desc" + i));
        }
        String lang = TestCodeSet.ENGLISH.getCodeString();
        OntologyDefinitions defs = new OntologyDefinitions(lang, items);
        List<OntologyDefinitions> defsList = new ArrayList<OntologyDefinitions>();
        defsList.add(defs);
        return new ArchetypeOntology(lang, Arrays.asList(new String[]{lang}),
                null, defsList, null, null, null);
    }

    /* clean the string writer for next test */
    private void clean() {
        out = new StringWriter();
    }

    /* take the result from writer and compare with the expected */
    private void verify(String expected) {
        String actual = out.toString();
        assertEquals("expected: \r\n" + expected + "\r\nactual:\r\n" + actual,
                expected, actual);
    }

    /* field */
    private ADLOutputter outputter;
    private StringWriter out;
}