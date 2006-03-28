/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
/**
 * ArchetypeTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype;

import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.domain.CCodedText;
import org.openehr.am.archetype.constraintmodel.domain.CCount;
import org.openehr.am.archetype.constraintmodel.domain.CQuantity;
import org.openehr.am.archetype.constraintmodel.primitive.CDateTime;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.SingleEvent;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.support.basic.Interval;

import java.util.*;

public class ArchetypeTest extends ConstraintTestBase {

    public ArchetypeTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    }

    /* test archetypeInstructionMinimium definition

    Composition[at0001] matches {
        content cardinality matches {*} matches {
            Section[at0003] matches {
                items cardinality matches {*} matches {
                    Evaluation[at0005] matches {
                        data matches {
                            ItemSingle[at0007] matches {
                                representation matches {
                                    Element[at0009] matches {
										value matches {
											DvCount[at00011] matches {
												magnitude matches {|0..8|}
											}
										}
									}
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    */
    private Archetype archetypeEvaluationMinimium() throws Exception {
        List<CObject> children = null;
        CAttribute cattribute = null;
        CComplexObject ccobj = null;

        // value object node
        CCount ccount = new CCount("[at0001]/content[at0003]/items[at0005]" +
                "/data[at0007]/representation[at0009]/value",
                new Interval<Integer>(new Integer(0), new Integer(8), true,
                        true));
        children = new ArrayList<CObject>();
        children.add(ccount);
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/representation[at0009]/.value",
                "value", CAttribute.Existence.REQUIRED, children);
        List<CAttribute> attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/representation[at0009]",
                "Element", null, "at0009", attributes, null);

        // representation object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/.representation", "representation",
                CAttribute.Existence.REQUIRED, children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items[at0005]" +
                "/data[at0007]/", "ItemSingle", null, "at0007", attributes,
                null);

        // data object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/.data", "data", CAttribute.Existence.REQUIRED,
                children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items[at0005]",
                "Evaluation", null, "at0005", attributes, null);

        // items object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute("[at0001]/content[at0003]/.items",
                "items", CAttribute.Existence.REQUIRED, Cardinality.LIST,
                children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]",
                "Section", null, "at0003", attributes, null);

        // content object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute("[at0001]/.content/", "content",
                CAttribute.Existence.REQUIRED, Cardinality.LIST, children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        CComplexObject definition = new CComplexObject("[at0001]/",
                "Composition", null, "at0001", attributes, null);

        return new Archetype("openEHR-EHR-Composition.test_construction.v1",
                null, "test_construction", null, definition, ontology(100));
    }

    /* test archetypeObservationMinimium definition
        -- observation

    Composition[at0001] matches {
        content cardinality matches {*} matches {
            Section[at0003] matches {
                items cardinality matches {*} matches {
                    Observation[at0005] matches {
                        data matches {
                            SingleEvent[at0007] matches {
                                origin matches {yyyy-mm-dd}
                                item matches {
                                    ItemSingle[at0009] matches {
                                        representation matches {
                                            Element[at00011] matches {
                                                value matches {
                                                    DvCount[at0013] matches {
                                                        magnitude matches {|>= 0|}
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    */
    private Archetype archetypeObservationMinimium() throws Exception {
        List<CObject> children = null;
        CAttribute cattribute = null;
        CComplexObject ccobj = null;

        // value object node
        CCount ccount = new CCount(
                "[at0001]/content[at0003]/items[at0005]" +
                "/data[at0007]/item[at0009]/representation[at00011]/value[at0013]",
                new Interval<Integer>(new Integer(0), new Integer(8), true,
                        true));
        children = new ArrayList<CObject>();
        children.add(ccount);
        cattribute = new CSingleAttribute(
                "[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/item[at0009]/representation[at00011]/.value/",
                "value", CAttribute.Existence.REQUIRED, children);
        List<CAttribute> attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/item[at0009]/representation[at00011]",
                "Element", null, "at00011", attributes, null);

        // representation object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/item[at0009]/.representation",
                "representation",
                CAttribute.Existence.REQUIRED, children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items[at0005]" +
                "/data[at0007]/item[at0009]/", "ItemSingle", null,
                "at0009", attributes, null);

        // item object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        attributes = new ArrayList<CAttribute>();
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/.item", "item", CAttribute.Existence.REQUIRED,
                children);
        attributes.add(cattribute);

        CDateTime cdate = new CDateTime("yyyy-mm-dd hh:mm:ss", null, null);
        CPrimitiveObject cpobj = new CPrimitiveObject("[at0001]/content" +
                "[at0003]/items[at0005]/data[at0007]/.origin", cdate);
        children = new ArrayList<CObject>();
        children.add(cpobj);
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/.origin", "origin",
                CAttribute.Existence.REQUIRED, children);
        attributes.add(cattribute);

        ccobj = new CComplexObject("[at0001]/content[at0003]/items[at0005]" +
                "/data[at0007]",
                "SingleEvent", null, "at0007", attributes, null);

        // data object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/.data/", "data", CAttribute.Existence.REQUIRED,
                children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items[at0005]",
                "Observation", null, "at0005", attributes, null);

        // items object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute("[at0001]/content[at0003]/.items",
                "items", CAttribute.Existence.REQUIRED, Cardinality.LIST,
                children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]",
                "Section", null, "at0003", attributes, null);

        // content object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute("[at0001]/.content/", "content",
                CAttribute.Existence.REQUIRED, Cardinality.LIST, children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        CComplexObject definition = new CComplexObject("[at0001]/",
                "Composition", null, "at0001", attributes, null);

        return new Archetype("openEHR-EHR-Composition.test_construction.v1",
                null, "test_construction", null, definition, ontology(100));
    }

    /* test archetypeInstruction definition

    Composition[at0001] matches {
        content cardinality matches {*} matches {
            Section[at0003] matches {
                items cardinality matches {*} matches {
                    Instruction[at0005] matches {
                        state matches {
                            DvState[at0006]
                                value matches {
                                    matches {
                                        [local::
                                        at00061, 	-- initial
                                        at00062, 	-- wait
                                        at00063, 	-- completed
                                    }
                                }
                            }
                        }
                        action matches {
                            ItemList[at0007] matches {
                                representation matches {
                                    Cluster[at0009] matches {
                                        items cardinality matches {*; ordered} matches {
                                            ELEMENT[at00010] matches {	-- Name of medication
                                                value matches {
                                                    DvText[at0011] matches {
                                                        value matches {*}
                                                    }
                                                }
                                            }
                                            ELEMENT[at00011] matches {	-- Strength
                                                value matches {
                                                    DvQuantity matches {
                                                        units matches {"s"}
                                                        magnitude matches {|>=0.0|}
                                                    }
                                                }
                                            }
                                            ELEMENT[at00020] matches {	-- Form
                                                value matches {
                                                    [local::
                                                    at00021,      -- tablet
                                                    at00022,      -- powder
                                                    at00023]      -- solution
                                                }
                                            }
                                            ELEMENT[at00030] matches {	-- Dose
                                                value matches {
                                                    DvQuantity matches {
                                                        units matches {"s"}
                                                        magnitude matches {|>=0.0|}
                                                    }
                                                }
                                            }
                                            ELEMENT[at00040] matches {	-- Route
                                                value matches {
                                                    [local::
                                                    at00041,      -- oral
                                                    at00042,      -- injection
                                                    at00043]      -- i.v.
                                                }
                                            }
                                            ELEMENT[at00050] matches {	-- assigned coded_text
                                                value matches {
                                                    [local::at00051]
                                                }
                                            }
                                        }
									}
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    */
    private Archetype archetypeInstruction() throws Exception {
        List<CObject> children = null;
        CAttribute cattribute = null;
        CComplexObject ccobj = null;

        // leaf value object nodes in a list
        String path = "[at0001]/content[at0003]/items[at0005]/action[at0007]" +
                "/representation[at0009]/";

        // -- name
        // add one more level for DvText
        CObject cstring = new CPrimitiveObject(path + "items[at00010]/.value",
                        new CString(".*", null));
        List<CObject> alternatives = new ArrayList<CObject>();
        alternatives.add(cstring);
        List<CAttribute> attributes = new ArrayList<CAttribute>();
        String spath = "[at0001]/content[at0003]/items[at0005]/action[at0007]"
                + "/representation[at0009]/value[at0010]/.value";
        attributes.add(new CSingleAttribute(spath, "value",
                CAttribute.Existence.REQUIRED, alternatives));
        CComplexObject dvtext = new CComplexObject(path, "DvText", null,
                "node", attributes, null);
        CComplexObject name = element(path + "items[at00010]", "at00010",
                dvtext);

        // -- strength
        CComplexObject strength = element(path + "items[at00011]", "at00011",
                new CQuantity(path + "items[at00011]/.value",
                        new Interval<Double>(new Double(0), null, false, false),
                        "s"));

        // -- form
        CComplexObject form = element(path + "items[at00020]", "at00020",
                new CCodedText(path + "items[at00020]/.value", "local",
                        Arrays.asList(
                                new String[]{"at00021", "at00022", "at00023"})));

        // -- dose
        CComplexObject dose = element(path + "items[at00030]", "at00030",
                new CQuantity(path + "items[at00030]/.value",
                        new Interval<Double>(new Double(0), null, false, false),
                        "s"));

        // -- route
        CComplexObject route = element(path + "items[at00040]", "at00040",
                new CCodedText(path + "items[at00040]/.value", "local",
                        Arrays.asList(
                                new String[]{"at00041", "at00042", "at00043"})));

        // -- assigned coded text - veryfying bug fix
        CComplexObject assigned = element(path + "items[at00050]", "at00050",
                new CCodedText(path + "items[at00050]/.value", "local",
                        Arrays.asList(
                                new String[]{"at00051"})));

        children = new ArrayList<CObject>();
        children.add(name);
        children.add(strength);
        children.add(form);
        children.add(dose);
        children.add(route);
        children.add(assigned);
        cattribute = new CMultipleAttribute(path + ".items", "items",
                CAttribute.Existence.REQUIRED, Cardinality.LIST, children);

        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject(path, "Cluster", null, "at0009",
                attributes, null);

        // representation object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CSingleAttribute("[at0001]/content[at0003]/items" +
                "[at0005]/action[at0007]/.representation", "representation",
                CAttribute.Existence.REQUIRED, children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items[at0005]" +
                "/action[at0007]/", "ItemList", null, "at0007", attributes,
                null);

        // action object node
        path = "[at0001]/content[at0003]/items[at0005]/";
        children = new ArrayList<CObject>();
        children.add(ccobj);
        CAttribute action = new CSingleAttribute(path + ".action/", "action",
                CAttribute.Existence.REQUIRED, children);
        children = new ArrayList<CObject>();
        children.add(ccobj(path + "state/", null, "DvState",
                new CCodedText(path + "state/.value", "local",
                        Arrays.asList("at00061", "at00062", "at00063"))));
        CAttribute state = new CSingleAttribute(path + ".state/", "state",
                CAttribute.Existence.REQUIRED, children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(action);
        attributes.add(state);
        ccobj = new CComplexObject("[at0001]/content[at0003]/items[at0005]",
                "Instruction", null, "at0005", attributes, null);

        // items object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute("[at0001]/content[at0003]/.items",
                "items", CAttribute.Existence.REQUIRED, Cardinality.LIST,
                children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        ccobj = new CComplexObject("[at0001]/content[at0003]",
                "Section", null, "at0003", attributes, null);

        // content object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute("[at0001]/.content/", "content",
                CAttribute.Existence.REQUIRED, Cardinality.LIST, children);
        attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        CComplexObject definition = new CComplexObject("[at0001]/",
                "Composition", null, "at0001", attributes, null);

        return new Archetype("openEHR-EHR-Composition.test_construction.v1",
                null, "test_construction", null, definition, ontology(100));
    }

    // create an element with single attriubte which contains single cobject
    private CComplexObject element(String path, String node, CObject cobj) {
        return ccobj(path, node, "Element", cobj);
    }

    // create an named ccomplexobject with given cobject
    private CComplexObject ccobj(String path, String node, String type,
                                 CObject cobj) {
        List<CObject> children = new ArrayList<CObject>();
        children.add(cobj);
        CAttribute cattribute = new CSingleAttribute(path,
                "value", CAttribute.Existence.REQUIRED, children);
        List<CAttribute> attributes = new ArrayList<CAttribute>();
        attributes.add(cattribute);
        return new CComplexObject(path, type, null, node, attributes,
                null);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    public void testBuildWithBadValues() throws Exception {
        Archetype archetype = archetypeEvaluationMinimium();
        String path  = "[at0001]/content[at0003]/items" +
                "[at0005]/data[at0007]/representation[at0009]/value";

        // try missing value
        assertOnlyError(archetype, path, null, ErrorType.MISSING);
        assertOnlyError(archetype, path, "", ErrorType.MISSING);

        // try bad format
        assertOnlyError(archetype, path, "integer expected", ErrorType.BAD_FORMAT);

        // try bad value
        assertOnlyError(archetype, path, "9", ErrorType.BAD_VALUE);
        assertOnlyError(archetype, path, "-1", ErrorType.BAD_VALUE);
    }

    private void assertOnlyError(Archetype archetype,
                                 String path, String value,
                                 ErrorType errorType) {
        Map<String,ErrorType> errorMap = new HashMap<String,ErrorType>();
        Map<String, String> valueMap = new HashMap<String, String>();
        valueMap.put(Archetype.INPUT + 1, value);

        Object obj = archetype.buildRMObject(valueMap, errorMap, sysmap());
        assertTrue(obj == null);
        assertEquals("error number", 1, errorMap.size());
        assertEquals("error path", path, errorMap.keySet().iterator().next());
        assertEquals("error type", errorType, errorMap.get(path));
    }

    public void testBuildEvaluation() throws Exception {
        Archetype archetype = archetypeEvaluationMinimium();
        Map<String, ErrorType> errorMap = new HashMap<String, ErrorType>();
        Map<String, String> valueMap = new HashMap<String, String>();
        Object rmobj = null;

        // try good value
        errorMap.clear();
        valueMap.put("input1", "3");
        rmobj = archetype.buildRMObject(valueMap, errorMap, sysmap());
        assertEquals("errors: " + errorMap, 0, errorMap.size());

        // verify composition
        assertTrue(rmobj instanceof Composition);
        Composition composition = (Composition) rmobj;
        assertEquals("name", "text1", composition.getName().getValue());
        assertEquals("meaning", "text1", composition.getName().getValue());
        List<Section> content = composition.getContent();
        assertEquals("content.size", 1, content.size());

        // verify section
        Section section = content.get(0);
        assertEquals("name", "text3", section.getName().getValue());
        assertEquals("meaning", "text3", section.getName().getValue());
        List<ContentItem> items = section.getItems();
        assertEquals("items.size", 1, content.size());

        // verify evaluation
        ContentItem item = items.get(0);
        assertTrue(item instanceof Evaluation);
        Evaluation evaluation = (Evaluation) item;
        assertEquals("name", "text5", evaluation.getName().getValue());
        assertEquals("meaning", "text5", evaluation.getName().getValue());

        // verify itemSingle
        ItemStructure itemStructure = evaluation.getData();
        assertTrue(itemStructure instanceof ItemSingle);
        ItemSingle itemSingle = (ItemSingle) itemStructure;
        assertEquals("name", "text7", itemSingle.getName().getValue());
        assertEquals("meaning", "text7", itemSingle.getName().getValue());

        // verify element
        Element element = itemSingle.item();
        assertEquals("name", "text9", element.getName().getValue());
        assertEquals("meaning", "text9", element.getName().getValue());

        // verify dvCount
        DataValue datavalue = element.getValue();
        assertTrue(datavalue instanceof DvCount);
        DvCount count = (DvCount) datavalue;
        assertEquals("magnitude", new Integer(3), count.getMagnitude());
    }

    public void testBuildObservation() throws Exception {
        Archetype archetype = archetypeObservationMinimium();
        Map<String, ErrorType> errorMap = new HashMap<String, ErrorType>();
        Map<String, String> valueMap = new HashMap<String, String>();
        Object rmobj = null;

        // try good value
        errorMap.clear();
        valueMap.put("input1", "3");
        valueMap.put("input2", "2001-10-30 02:04:00");
        rmobj = archetype.buildRMObject(valueMap, errorMap, sysmap());
        assertEquals("errors: " + errorMap, 0, errorMap.size());

        // verify composition
        assertTrue(rmobj instanceof Composition);
        Composition composition = (Composition) rmobj;
        assertEquals("name", "text1", composition.getName().getValue());
        assertEquals("meaning", "text1", composition.getName().getValue());
        List<Section> content = composition.getContent();
        assertEquals("content.size", 1, content.size());

        // verify section
        Section section = content.get(0);
        assertEquals("name", "text3", section.getName().getValue());
        assertEquals("meaning", "text3", section.getName().getValue());
        List<ContentItem> items = section.getItems();
        assertEquals("items.size", 1, content.size());

        // verify obseration
        ContentItem item = items.get(0);
        assertTrue(item instanceof Observation);
        Observation observation = (Observation) item;
        assertEquals("name", "text5", observation.getName().getValue());
        assertEquals("meaning", "text5", observation.getName().getValue());

        // verify history
        History history = observation.getData();
        assertTrue(history instanceof SingleEvent);
        SingleEvent singleEvent = (SingleEvent) history;
        assertEquals("name", "text7", singleEvent.getName().getValue());
        assertEquals("meaning", "text7", singleEvent.getName().getValue());

        // verify itemSingle
        ItemStructure itemStructure = singleEvent.getItem();
        assertTrue(itemStructure instanceof ItemSingle);
        ItemSingle itemSingle = (ItemSingle) itemStructure;
        assertEquals("name", "text9", itemSingle.getName().getValue());
        assertEquals("meaning", "text9", itemSingle.getName().getValue());

        // verify element
        Element element = itemSingle.item();
        assertEquals("name", "text11", element.getName().getValue());
        assertEquals("meaning", "text11", element.getName().getValue());

        // verify dvCount
        DataValue datavalue = element.getValue();
        assertTrue(datavalue instanceof DvCount);
        DvCount count = (DvCount) datavalue;
        assertEquals("magnitude", new Integer(3), count.getMagnitude());
    }

    public void testBuildInstruction() throws Exception {
        Archetype archetype = archetypeInstruction();
        ArchetypeID archetypeId = archetype.getArchetypeId();
        Map<String, ErrorType> errorMap = new HashMap<String, ErrorType>();
        Map<String, String> valueMap = new HashMap<String, String>();
        Object rmobj = null;

        // try good value
        errorMap.clear();
        valueMap.put("input1", "some medicine name"); // name
        valueMap.put("input2", "10.0"); // strength
        valueMap.put("input3", "1"); // form
        valueMap.put("input4", "20.0"); // dose
        valueMap.put("input5", "1"); // route
        valueMap.put("input6", "1"); // state

        rmobj = archetype.buildRMObject(valueMap, errorMap, sysmap());
        assertEquals("errors: " + errorMap, 0, errorMap.size());

        // verify composition
        assertTrue(rmobj instanceof Composition);
        Composition composition = (Composition) rmobj;
        assertEquals("name", "text1", composition.getName().getValue());
        assertEquals("meaning", "text1", composition.getName().getValue());
        List<Section> content = composition.getContent();
        assertEquals("content.size", 1, content.size());

        // verify section
        Section section = content.get(0);
        assertEquals("name", "text3", section.getName().getValue());
        assertEquals("meaning", "text3", section.getName().getValue());
        List<ContentItem> items = section.getItems();
        assertEquals("items.size", 1, content.size());

        // verify instruction
        ContentItem item = items.get(0);
        assertTrue(item instanceof Instruction);
        Instruction instruction = (Instruction) item;
        assertEquals("name", "text5", instruction.getName().getValue());
        assertEquals("meaning", "text5", instruction.getName().getValue());

        // todo: verify itemList

        // verify archetypeDetails
        Archetyped archetypeDetails = composition.getArchetypeDetails();
        assertNotNull("archetypeDetails null", archetypeDetails);
        assertEquals("archetypeId wrong", archetypeId,
                archetypeDetails.getArchetypeID());
    }

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
 *  The Original Code is ArchetypeTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */