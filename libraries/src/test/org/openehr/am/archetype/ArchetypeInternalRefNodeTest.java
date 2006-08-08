/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeInternalRefNodeTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$Revisi$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.am.archetype;

import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.domain.CCount;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datatypes.quantity.DvCount;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.StringWriter;

/**
 * ArchetypeInternalRefNodeTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeInternalRefNodeTest extends ConstraintTestBase {

    public ArchetypeInternalRefNodeTest(String test) {
        super(test);
    }

    /* test archetype with internal reference node definition

Composition[at0001] matches {
    content cardinality matches {*} matches {
        Section[at0003] matches {
            items cardinality matches {*} matches {
                Evaluation[at0005] matches {
                    -- required
                    data matches {
                        ItemSingle[at0007] matches {
                            representation matches {
                                Element[at0009] matches {
                                    value matches {
                                        DvCount[at0011] matches {
                                            magnitude matches {|0..8|}
                                        }
                                    }
                                }
                            }
                        }
                    }
                    protocol matches {
                        use_node ItemSingle [at0001]/content[at0003]/items[at0005]/data[at0007]
                    }
                }
            }
        }
    }
}

Composition[at0001] matches {
    content cardinality matches {*; ordered} matches {
        Section[at0003] matches {
            items cardinality matches {*; ordered} matches {
                Evaluation[at0005] matches {
                    data matches {
                        ItemSingle[at0007] matches {
                            representation matches {
                                Element[at0009] matches {
                                    value matches {
                                        COUNT matches {
                                            magnitude matches {|0..8|}
                                        }
                                    }
                                }
                            }
                        }
                    }
                    protocol matches {
                    }
                }
            }
        }
    }
}

    */
    private Archetype archetypeWithOptionalNode() throws Exception {
        CAttribute cattribute = null;
        CComplexObject ccobj = null;
        List<CObject> children = new ArrayList<CObject>();
        List<CAttribute> cattributes = new ArrayList<CAttribute>();

        // data attribute
        String[] nodes = {
            "at0001", "at0003", "at0005", "at0007", "at0009", "at0011"
        };

        String[] attributes = {
            "content", "items", "data", "representation", "value"
        };

        cattribute = itemSingle(nodes, attributes, true);
        cattributes.add(cattribute);

        nodes = new String[]{
            "at0001", "at0003", "at0005", "at0013", "at0015", "at0017"
        };

        attributes = new String[]{
            "content", "items", "protocol", "representation", "value"
        };

        // protocol attribute
        cattributes.add(internalRef());

        // evaluation object node
        children.add(ccobj);
        cattribute = new CSingleAttribute(path(nodes, attributes, 2, false),
                attributes[ 2 ], CAttribute.Existence.REQUIRED,
                children);

        ccobj = new CComplexObject(path(nodes, attributes, 2, true),
                "Evaluation", null, nodes[ 2 ], cattributes, null);

        // items object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute(path(nodes, attributes, 1, false),
                attributes[ 1 ], CAttribute.Existence.REQUIRED, Cardinality.LIST,
                children);
        cattributes = new ArrayList<CAttribute>();
        cattributes.add(cattribute);
        ccobj = new CComplexObject(path(nodes, attributes, 1, true),
                "Section", null, nodes[ 1 ], cattributes, null);

        // content object node
        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CMultipleAttribute(path(nodes, attributes, 0, false),
                attributes[ 0 ], CAttribute.Existence.REQUIRED, Cardinality.LIST,
                children);
        cattributes = new ArrayList<CAttribute>();
        cattributes.add(cattribute);
        CComplexObject definition =
                new CComplexObject(path(nodes, attributes, 0, true),
                        "Composition", null, nodes[ 0 ], cattributes, null);

        return new Archetype("openEHR-EHR-Composition.test_construction.v1",
                null, "test_construction", null, definition, ontology(10));
    }

    // attribute protocol of Evaluation
    private CAttribute internalRef() {
        CObject internalRef = new ArchetypeInternalRef(
                "/[at0001]/content[at0003]/items[at0005]/protocol", "ItemSingle",
                "/[at0001]/content[at0003]/items[at0005]/data[at0007]");
        List<CObject> children = new ArrayList<CObject>();
        children.add(internalRef);
        return new CSingleAttribute(
                "/[at0001]/content[at0003]/items[at0005]/.protocol",
                "protocol", CAttribute.Existence.OPTIONAL, children);
    }

    private CAttribute itemSingle(String[] nodes, String[] attributes,
                                  boolean required)
            throws Exception {
        List<CObject> children = null;
        CAttribute cattribute = null;
        CComplexObject ccobj = null;

        // leaf object node
        CCount ccount = new CCount(path(nodes, attributes, 5, true),
                new Interval<Integer>(new Integer(0), new Integer(8), true,
                        true));
        children = new ArrayList<CObject>();
        children.add(ccount);
        cattribute = new CSingleAttribute(path(nodes, attributes, 4, false),
                attributes[ 4 ], CAttribute.Existence.REQUIRED, children);
        List<CAttribute> cattributes = new ArrayList<CAttribute>();
        cattributes.add(cattribute);

        // value object node
        ccobj = new CComplexObject(path(nodes, attributes, 4, true),
                "Element", null, nodes[ 4 ], cattributes, null);

        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CSingleAttribute(path(nodes, attributes, 3, false),
                attributes[ 3 ], CAttribute.Existence.REQUIRED, children);
        cattributes = new ArrayList<CAttribute>();
        cattributes.add(cattribute);

        // representation object node
        ccobj = new CComplexObject(path(nodes, attributes, 3, true),
                "ItemSingle", null, nodes[ 3 ], cattributes,
                null);

        children = new ArrayList<CObject>();
        children.add(ccobj);
        cattribute = new CSingleAttribute(path(nodes, attributes, 2, false),
                attributes[ 2 ],
                ( required ? CAttribute.Existence.REQUIRED :
                CAttribute.Existence.OPTIONAL ),
                children);

        return cattribute;
    }

    private String path(String[] nodes, String[] attributes,
                        int endIndex, boolean node) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < nodes.length && i <= endIndex; i++) {
            buf.append("/");
            if (i != 0) {
                buf.append(attributes[ i - 1 ]);
            }
            buf.append("[");
            buf.append(nodes[ i ]);
            buf.append("]");
        }
        if (!node) {
            buf.append("/.");
            buf.append(attributes[ endIndex ]);
        }
        return buf.toString();
    }

    public void testBuildEvaluationWithRequiredValues() throws Exception {
        Archetype archetype = archetypeWithOptionalNode();

        StringWriter out = new StringWriter();
        
        Map<String, ErrorType> errorMap = new HashMap<String, ErrorType>();
        Map<String, String> valueMap = new HashMap<String, String>();
        Object rmobj = null;

        // try only required value
        errorMap.clear();
        valueMap.put("input1", "3");
        rmobj = archetype.buildRMObject(valueMap, errorMap, sysmap());
        assertEquals("errors: " + errorMap, 0, errorMap.size());

        // verify composition
        Composition composition = (Composition) rmobj;
        assertEquals("name", "text1", composition.getName().getValue());
        assertEquals("archetypeNodeId", "text1",
                composition.getName().getValue());
        List<Section> content = composition.getContent();
        Evaluation evaluation = (Evaluation) content.get(0).getItems().get(0);
        ItemSingle itemSingle = (ItemSingle) evaluation.getData();
        DvCount count = (DvCount) itemSingle.item().getValue();
        assertEquals(3, count.getMagnitude().intValue());
    }

    public void testBuildEvaluationWithOptionalValues() throws Exception {
        Archetype archetype = archetypeWithOptionalNode();
        
        Map<String, ErrorType> errorMap = new HashMap<String, ErrorType>();
        Map<String, String> valueMap = new HashMap<String, String>();
        Evaluation evaluation;
        ItemSingle itemSingle;
        DvCount count;
        Object rmobj = null;

        // try full values
        errorMap.clear();
        valueMap.put("input1", "3");
        valueMap.put("input2", "6");

        rmobj = archetype.buildRMObject(valueMap, errorMap, sysmap());
        assertEquals("errors: " + errorMap, 0, errorMap.size());

        // verify composition
        assertTrue(rmobj instanceof Composition);
        Composition composition = (Composition) rmobj;
        assertEquals("name", "text1", composition.getName().getValue());
        assertEquals("archetypeNodeId", "text1",
                composition.getName().getValue());
        evaluation =
                (Evaluation) composition.getContent().get(0).getItems().get(0);
        assertNotNull("data null", evaluation.getData());
        itemSingle = (ItemSingle) evaluation.getData();
        count = (DvCount) itemSingle.item().getValue();
        assertEquals(3, count.getMagnitude().intValue());

        assertNotNull("protocol null", evaluation.getProtocol());
        itemSingle = (ItemSingle) evaluation.getProtocol();
        count = (DvCount) itemSingle.item().getValue();
        assertEquals(6, count.getMagnitude().intValue());
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
 *  The Original Code is ArchetypeOptionalNodeTest.java
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