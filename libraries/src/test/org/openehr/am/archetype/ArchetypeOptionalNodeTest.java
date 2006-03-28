/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeOptionalNodeTest"
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
/*
 * Copyright (C) 2004 Rong Chen, Acode HB, Sweden
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
package org.openehr.am.archetype;

import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.domain.CCount;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.support.basic.Interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Archetype testcase for optional nodes - cardinality, existence, occurrences
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeOptionalNodeTest extends ConstraintTestBase {

    public ArchetypeOptionalNodeTest(String test) {
        super(test);
    }

    /* test archetypeWithOptionalNode definition

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
												magnitude matches {|>= 0|}
											}
										}
									}
                                }
                            }
                        }
                        -- optional
                        protocol existence matches {0..1} matches {
                            ItemSingle[at0013] matches {
                                representation matches {
                                    Element[at0015] matches {
										value matches {
											DvCount[at0017] matches {
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
        cattribute = itemSingle(nodes, attributes, false);
        cattributes.add(cattribute);
                
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
                null, "test_construction", null, definition, ontology(20));
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
            if (i != 0) {
                buf.append("/");
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

    public void testPath() throws Exception {
        String[] nodes = {
            "at0001", "at0003", "at0005", "at0007", "at0009", "at0011"
        };

        String[] attributes = {
            "content", "items", "data", "representation", "value"
        };

        String path = path(nodes, attributes, 5, true);
        assertEquals("Got: " + path, "[at0001]/content[at0003]/items[at0005]" +
                "/data[at0007]/representation[at0009]/value[at0011]", path);

        path = path(nodes, attributes, 4, false);
        assertEquals("Got: " + path, "[at0001]/content[at0003]/items[at0005]" +
                "/data[at0007]/representation[at0009]/.value", path);
    }

    public void testBuildEvaluation() throws Exception {
        Archetype archetype = archetypeWithOptionalNode();
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
        assertEquals("archetypeNodeId", "text1", composition.getName().getValue());
        List<Section> content = composition.getContent();
        assertEquals("content.size", 1, content.size());
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