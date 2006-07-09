/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ADLOutputterTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
/**
 * ADLOutputterTest
 *
 * @author Rong Chen
 * @author Mattias Forss, Johan Hjalmarsson
 *
 * @version 1.0 
 */
package org.openehr.am.archetype.output;

import junit.framework.TestCase;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyBindingItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.ontology.Query;
import org.openehr.am.archetype.ontology.QueryBindingItem;
import org.openehr.am.archetype.ontology.TermBindingItem;
import org.openehr.am.archetype.description.ArchetypeDescription;
import org.openehr.am.archetype.description.ArchetypeDescriptionItem;
import org.openehr.am.archetype.constraintmodel.domain.CCodedText;
import org.openehr.am.archetype.constraintmodel.domain.CCount;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.Cardinality;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.rm.datatypes.text.CodePhrase;
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
        List<String> keywords = new ArrayList<String>();
        keywords.add("apple");
        keywords.add("pear");
        List<String> urls = new ArrayList<String>();
        urls.add("original_res_url");
        Map<String, String> others = new HashMap<String,String>();
        
        ArchetypeDescriptionItem item = new ArchetypeDescriptionItem(
                TestCodeSet.ENGLISH, purpose, use, misuse, copyright,
                keywords, urls, others);

        clean();
        outputter.printDescriptionItem(item, 0, out);

        verify("[\"en\"] = <\r\n" +
                "    language = <\"en\">\r\n" + 
                "    purpose = <\"purpose\">\r\n" +
                "    keywords = <\"apple\",\"pear\">\r\n" +
                "    copyright = <\"copyright\">\r\n" +
                "    use = <\"use\">\r\n" +
                "    misuse = <\"misuse\">\r\n" +
                "    original_resource_uri = <\"original_res_url\">\r\n" +
                ">\r\n");
    }
    
    public void testPrintDescription() throws Exception {
        String author = "Jerry Mouse";
        String status = "draft";
        Map<String,String> authorMap = new HashMap<String,String>();
        authorMap.put("name", author);

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
        ArchetypeDescription description = new ArchetypeDescription(authorMap,
                null, status, items, null, null, null);

        clean();
        outputter.printDescription(description, out);

        verify("description\r\n" +
                "    original_author = <\r\n" +
                "        [\"name\"] = <\"" + author + "\">\r\n" +
                "    >\r\n" +
                "    lifecycle_state = <\"" + status + "\">\r\n" +
                "    details = <\r\n" +
                "        [\"en\"] = <\r\n" +
                "            language = <\"en\">\r\n" + 
                "            purpose = <\"purpose of this archetype\">\r\n" +
                "        >\r\n" +
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

        item = new DefinitionItem("ac0001", "text ac0001", "desc ac0001");
        items = new ArrayList<DefinitionItem>();
        items.add(item);
        item = new DefinitionItem("ac0002", "text ac0002", "desc ac0002");
        items.add(item);
        definitions = new OntologyDefinitions("en", items);
        List<OntologyDefinitions> constraintDefinitionsList =
                new ArrayList<OntologyDefinitions>();
        constraintDefinitionsList.add(definitions);
        
        List<String> languages = new ArrayList<String>();
        languages.add("en");
        List<String> terminologies = new ArrayList<String>();
        terminologies.add("local");
        
        List<String> terms = new ArrayList<String>();
        terms.add("[local::100000]");
        TermBindingItem termBindItem = new TermBindingItem("at0001",terms); 
        List<OntologyBindingItem> termBindList = new ArrayList<OntologyBindingItem>();
        termBindList.add(termBindItem);
        terms = new ArrayList<String>();
        terms.add("[local::200000]");
        termBindItem = new TermBindingItem("at0002",terms); 
        termBindList.add(termBindItem);
        OntologyBinding ontologyBind = new OntologyBinding("local",termBindList);
        List<OntologyBinding> termBindingList = new ArrayList<OntologyBinding>();
        termBindingList.add(ontologyBind);
        
        Query query = new Query("http://terminology.org?terminology_id=snomed_ct&&has_relation=[102002];with_target=[128004]");
        QueryBindingItem queryBindItem = new QueryBindingItem("ac0001",query); 
        List<OntologyBindingItem> constraintBindList = new ArrayList<OntologyBindingItem>();
        constraintBindList.add(queryBindItem);
        ontologyBind = new OntologyBinding("local",constraintBindList);
        List<OntologyBinding> constraintBindingList = new ArrayList<OntologyBinding>();
        constraintBindingList.add(ontologyBind);
        
        ArchetypeOntology ontology = new ArchetypeOntology("en", languages,
                terminologies, termDefinitionsList, constraintDefinitionsList, termBindingList, constraintBindingList);
        clean();
        outputter.printOntology(ontology, out);
        verify("ontology\r\n" +
                "    primary_language = <\"en\">\r\n" +
                "    languages_available = <\"en\", ...>\r\n" +
    			"    terminologies_available = <\"local\", ...>\r\n" +
                "    term_definitions = <\r\n" +
                "        [\"en\"] = <\r\n" +
                "            items = <\r\n" + 
                "                [\"at0001\"] = <\r\n" +
                "                    text = <\"text at0001\">\r\n" +
                "                    description = <\"desc at0001\">\r\n" +
                "                >\r\n" +
                "                [\"at0002\"] = <\r\n" +
                "                    text = <\"text at0002\">\r\n" +
                "                    description = <\"desc at0002\">\r\n" +
                "                >\r\n" +
        		"            >\r\n" +
        		"        >\r\n" +
        		"    >\r\n" +
                "    constraint_definitions = <\r\n" +
                "        [\"en\"] = <\r\n" +
                "            items = <\r\n" +
                "                [\"ac0001\"] = <\r\n" +
                "                    text = <\"text ac0001\">\r\n" +
                "                    description = <\"desc ac0001\">\r\n" +
                "                >\r\n" +
                "                [\"ac0002\"] = <\r\n" +
                "                    text = <\"text ac0002\">\r\n" +
                "                    description = <\"desc ac0002\">\r\n" +
                "                >\r\n" +
                "            >\r\n" +
                "        >\r\n" +
                "    >\r\n" +
        		"    term_binding = <\r\n" +
        		"        [\"local\"] = <\r\n" +
        		"            items = <\r\n" +
        		"                [\"at0001\"] = <[local::100000]>\r\n" +
        		"                [\"at0002\"] = <[local::200000]>\r\n" +
        		"            >\r\n" +
        		"        >\r\n" +
        		"    >\r\n" +
        		"    constraint_binding = <\r\n" +
        		"        [\"local\"] = <\r\n" +
        		"            items = <\r\n" +
        		"                [\"ac0001\"] = <http://terminology.org?terminology_id=snomed_ct&&has_relation=[102002];with_target=[128004]>\r\n" +
        	    "            >\r\n" +
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
        
        // test the single code term
        codes = new String[] {"at3102.0"};
        ccoded = new CCodedText("/path", terminology,
        		Arrays.asList(codes));
        clean();
        outputter.printCCodedText(ccoded, 1, out);
        verify("    [local::at3102.0]\r\n");
    }

    public void testPrintCCount() throws Exception {
        clean();
        CCount ccount = new CCount("/path", new Interval<Integer>(0, 3));
        outputter.printCCount(ccount, 0, out);
        verify("COUNT matches {\r\n" +
                "    magnitude matches {|0..3|}\r\n" +
                "}\r\n");
    }

    public void testPrintCDvOrdinal() throws Exception {
        Set<Ordinal> list = new LinkedHashSet<Ordinal>();
        for (int i = 1; i <= 4; i++) {
            list.add(new Ordinal(i, new CodePhrase("local", "at200" + i)));
        }
        CDvOrdinal cordinal = new CDvOrdinal("/path", list);
        clean();
        outputter.printCDvOrdinal(cordinal, 0, out);
        verify("1|[local::at2001],\r\n" +
                "2|[local::at2002],\r\n" +
                "3|[local::at2003],\r\n" +
                "4|[local::at2004]\r\n");
    }

    public void testPrintCDvQuantity() throws Exception {
        CDvQuantityItem item1 = new CDvQuantityItem(
        		new Interval<Double>(0.0, 200.0), "year");        
        CDvQuantityItem item2 = new CDvQuantityItem(
        		new Interval<Double>(1.0, 36.0), "month");
        List<CDvQuantityItem> list = new ArrayList<CDvQuantityItem>();
        list.add(item1);
        list.add(item2);
        CodePhrase property = new CodePhrase("openehr", "128");
        CDvQuantity cquantity = new CDvQuantity("/path", list, property);
        
        clean();
        outputter.printCDvQuantity(cquantity, 0, out);
        verify("C_QUANTITY <\r\n" +
			   "    property = [openehr::128]\r\n" +
			   "    list = <\r\n" +									
			   "        [\"1\"] = <\r\n" +
			   "            units = <\"year\">\r\n" +
			   "            magnitude = <|0.0..200.0|>\r\n" +
			   "        >\r\n" +
			   "        [\"2\"] = <\r\n" +
			   "            units = <\"month\">\r\n" +
			   "            magnitude = <|1.0..36.0|>\r\n" +
			   "        >\r\n" +									
			   "    >\r\n" +
			   ">\r\n");
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
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is ADLOutPutterTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2004-2005
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Mattias Forss, Johan Hjalmarsson
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */