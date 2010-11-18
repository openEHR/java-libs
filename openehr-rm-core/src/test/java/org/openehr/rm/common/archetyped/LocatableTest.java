/*
 * component:   "openEHR Reference Implementation"
 * description: "Class LocatableTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/common/archetyped/LocatableTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */

/**
 * LocatableTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.common.archetyped;

import java.util.*;

import junit.framework.TestCase;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.common.directory.Folder;
import org.openehr.rm.support.terminology.*;

public class LocatableTest extends TestCase {

    public LocatableTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    	DvText name = new DvText("test instance");
    	DvDateTime origin = new DvDateTime("2005-12-03T09:22:00");
    	ItemStructure summary = null;
    	instance = new TestLocatable("at0000", name, origin, summary, null);   
    	
    	list = new ArrayList<Locatable>();
    	for(int i = 1; i < 10; i++) {
    		String archetypeNodeId = "at000" + i;
    		String subname = "name" + i;
    		list.add(new TestLocatable(archetypeNodeId, subname));
    	}
    	
    	// test iteam for testing archetyped root node
    	list.add(new TestLocatable(
    			"openEHR-EHR-OBSERVATION.laboratory-lipids.v1", 
    			"archetyped root node"));
    	
    	assert(list.size() == 10);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    	list = null;
    }

    public void testGetLinks() throws Exception {
    	UIDBasedID uid = new HierObjectID("1-0-23-47-23");

        Folder folder = new Folder(uid, "at0001", new DvText("folder name"), 
        		null, null, null, null, null, null);
        folder.getLinks();        
    }

    public void testProcessArchetypePredicateWithMatch() throws Exception {
    	Object actual = instance.processPredicate("at0005", list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on archetyep predicate", "at0005", 
    			l.getArchetypeNodeId());
    }
    
    public void testProcessArchetypePredicateWithMatchedRootNode() throws Exception {
    	String aid = "openEHR-EHR-OBSERVATION.laboratory-lipids.v1";
    	Object actual = instance.processPredicate(aid, list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on archetyep predicate", aid, 
    			l.getArchetypeNodeId());
    }
    
    public void testProcessArchetypePredicateWithoutMatch() throws Exception {
    	Object actual = instance.processPredicate("at0025", list);
    	assertNull("match on archetype predicate unexpected", actual);
    }
    
    public void testProcessNamePredicateWithMatch() throws Exception {
    	Object actual = instance.processPredicate("'name8'", list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on name predicate", "at0008", 
    			l.getArchetypeNodeId());
    }
    
    public void testProcessNamePredicateWithoutMatch() throws Exception {
    	Object actual = instance.processPredicate("'name99'", list);
    	assertNull("match on name predicate unexpected", actual);
    }
    
    public void testProcessArchetypeCommaNamePredicateWithMatch() throws Exception {
    	Object actual = instance.processPredicate("at0008, 'name8'", list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on archetype and name predicate", "at0008", 
    			l.getArchetypeNodeId());
    }
    
    public void testProcessArchetypeCommaNamePredicateWithoutMatch() throws Exception {
    	Object actual = instance.processPredicate("at0088, 'name99'", list);
    	assertNull("match on name predicate unexpected", actual);
    }
    
    public void testProcessArchetypeAndNamePredicateWithMatchUppercaseAnd() throws Exception {
    	Object actual = instance.processPredicate("at0008 AND 'name8'", list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on archetyep predicate", "at0008", 
    			l.getArchetypeNodeId());
    }   
    
    public void testProcessArchetypeAndNamePredicateWithMatchLowercaseAnd() throws Exception {
    	Object actual = instance.processPredicate("at0008 and 'name8'", list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on archetyep predicate", "at0008", 
    			l.getArchetypeNodeId());
    }   
    
    public void testProcessArchetypeAndNamePredicateWithMatchUppercaseAndNameValue() throws Exception {
    	Object actual = instance.processPredicate("at0008 AND name/value='name8'", list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on archetyep predicate", "at0008", 
    			l.getArchetypeNodeId());
    }   
    
    public void testProcessArchetypeAndNamePredicateWithMatchLowercaseAndNameValue() throws Exception {
    	Object actual = instance.processPredicate("at0008 and name/value='name8'", list);
    	assertNotNull("match on archetype predicate expected", actual);
    	assertTrue("locatable expected", actual instanceof Locatable);
    	Locatable l = (Locatable) actual;
    	assertEquals("wrong match on archetyep predicate", "at0008", 
    			l.getArchetypeNodeId());
    } 
    
    public void testProcessArchetypeAndNamePredicateWithoutMatchUppercaseAnd() throws Exception {
    	Object actual = instance.processPredicate("at0088 AND 'name99'", list);
    	assertNull("match on name predicate unexpected", actual);
    }
    
    public void testProcessArchetypeAndNamePredicateWithoutMatchLowercaseAnd() throws Exception {
    	Object actual = instance.processPredicate("at0088 and 'name99'", list);
    	assertNull("match on name predicate unexpected", actual);
    }
    
    public void testItemAtPathFirstLevelAttribute() {
    	path = "/name";
    	value = instance.itemAtPath(path);
    	assertEquals(instance.getName(), value);
    }
    
    public void testItemAtPathAttributesAttribute() {
    	path = "/name/value";
    	value = instance.itemAtPath(path);
    	assertTrue(value instanceof String);
    	assertEquals(instance.getName().getValue(), value);
    }
    
    public void testItemAtPathWithAttributeHavingUnderscore() {
    	path = "/archetype_node_id";
    	value = instance.itemAtPath(path);
    	assertTrue(value instanceof String);
    	assertEquals(instance.getArchetypeNodeId(), value);
    }
    
    
    
    private List<Locatable> list;
    private Locatable instance;
    private String path;
    private Object value;
    
    /* Locatable subclass for testing use */
    private static class TestLocatable extends Locatable {
    	TestLocatable(String archetypeNodeId, String name) {
    		super(archetypeNodeId, new DvText(name));
    	} 
    	
    	// "borrowed" partly from history ;) 
    	@FullConstructor
        public TestLocatable(
        		@Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                @Attribute(name = "name", required=true) DvText name,
                @Attribute(name = "origin", required=true) DvDateTime origin,
                @Attribute(name = "summary") ItemStructure summary,
                @Attribute(name = "terminologyService", system=true) 
                				TerminologyService terminologyService){
    		super(archetypeNodeId, name);
    		this.origin = origin;
    		this.summary = summary;
    	} 
    	
    	public DvDateTime getOrigin() {
    		return origin;
    	}
    	
    	public ItemStructure getSummary() {
    		return summary;
    	}
    	
    	public String pathOfItem(Pathable item) {
			return null;
		}
		public List<Object> itemsAtPath(String path) {
			return null;
		}
		public boolean pathExists(String path) {
			return false;
		}
		public boolean pathUnique(String path) {
			return false;
		}    			
		
		private DvDateTime origin;
		private ItemStructure summary;
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
 *  The Original Code is LocatableTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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