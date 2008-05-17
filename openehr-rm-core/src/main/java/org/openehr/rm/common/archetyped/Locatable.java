/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Locatable"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/archetyped/Locatable.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.archetyped;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datatypes.text.DvText;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Root structural class of all information models. Instances of
 * this class are immutalbe.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Locatable extends Pathable {

    /**
     * Constructs a Locatable
     *
     * @param uid              null if not specified
     * @param archetypeNodeId  not null
     * @param name             not null
     * @param archetypeDetails null if not specified
     * @param feederAudit      null if not specified
     * @param links            null if not specified
     * @param parent			 null if not specified
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty
     */
    protected Locatable(UIDBasedID uid, String archetypeNodeId, DvText name,
                        Archetyped archetypeDetails,FeederAudit feederAudit, 
                        Set<Link> links, Pathable parent) {    	
    	super(parent);
    	
        if (archetypeNodeId == null) {
            throw new IllegalArgumentException("null archetypeNodeId");
        }
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (links != null && links.isEmpty()) {
            throw new IllegalArgumentException("empty links");
        }
        this.uid = uid;
        this.archetypeNodeId = archetypeNodeId;
        this.name = name;
        this.archetypeDetails = archetypeDetails;
        this.feederAudit = feederAudit;
        this.links = links;
    }

    /**
     * Constructs a Locatable node by archetypeNodeId and name
     * same as name
     *
     * @param archetypeNodeId
     * @param name
     * @throws IllegalArgumentException if archetypeNodeId or name null
     */
    protected Locatable(String archetypeNodeId, DvText name) {
        this(null, archetypeNodeId, name, null, null, null, null);
    }

    /**
     * Optional globally unique object identifier for root object of
     * archetyped data structure.
     *
     * @return uid
     */
    public UIDBasedID getUid() {
        return uid;
    }

    /**
     * Design-time archetype id of this node taken from its generating
     * archetype; used to build archetype paths. Always in the form of an
     * "at" code, eg "at0005". This value enables a "standardised" name
     * for this node to be generated, by referring to the generating archetype
     * local ontology.
     *
     * @return archetypeNodeId
     */
    public String getArchetypeNodeId() {
        return archetypeNodeId;
    }

    /**
     * Runtime name of this fragment, used to build runtime paths.
     * This is the term provided via a clinical application or batch
     * process to name this EHR construct: its retention in the EHR
     * faithfully preserves the original label by which this entry
     * was known to end users.
     *
     * @return name
     */
    public DvText getName() {
        return name;
    }

    /**
     * Details of archetyping used on this node.
     *
     * @return archetype details
     */
    public Archetyped getArchetypeDetails() {
        return archetypeDetails;
    }

    /**
     * Audit trail from non-openEHR system of original commit of
     * information forming the content of this node, or from a
     * conversion gateway which has synthesised this node.
     *
     * @return feederAuidt
     */
    public FeederAudit getFeederAudit() {
        return feederAudit;
    }

    /**
     * Links to other archetyped structures (data whose root object
     * inherits from ARCHETYPED, such as ENTRY, SECTION and so on).
     * Links may be to structures in other compositions.
     *
     * @return links or null if unspecified
     */
    public Set<Link> getLinks() {
        return links;
    }    
	
    /**
     * True if this node is the root of an archetyped structure
     *
     * @return true if archetype root
     */
    public boolean isArchetypeRoot() {
        return archetypeDetails != null;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public abstract String pathOfItem(Pathable item);

    

    /**
     * Locate an attribute without further evaluation of the path
     *
     * @param path
     * @param attributeNames
     * @param attributes
     * @return the attribute found
     * @throws IllegalArgumentException if path invalid
     */
    Object locateAttribute(String path, List<String> attributeNames,
                                     List<Object> attributeValues) {
    	assert(path != null);
    	assert(attributeNames != null && attributeValues != null
    			&& attributeNames.size() == attributeValues.size());    	
    	
        for (int i = 0, j = attributeNames.size(); i < j; i++) {
        	String name = attributeNames.get(i);
        	Object value = attributeValues.get(i);
        	if(path.equals(name)) {
        		// the whole attribute value
        		return value;
        	} else if(path.startsWith(name)) {
        		// need further evaluation of the path
        		String remainingPath = path.substring(name.length());            	
            	if(remainingPath.startsWith(PREDICATE_START)) {
            		Iterable iterable = null;
            		if(value instanceof Iterable) {
            			iterable = (Iterable) value;
            		} else {
            			List list = new ArrayList();
            			list.add(value);
            			iterable = list;            			
            		}
            		int predicateEnd = remainingPath.indexOf(PREDICATE_END);
            		String expression = remainingPath.substring(
            				PREDICATE_START.length(), predicateEnd);
            		Object selected = processPredicate(expression, iterable);
            		
            		if(selected == null) {
            			return null; // nothing selected
            		}
            		if(predicateEnd == remainingPath.length() - 1) {
            			return selected;
            		} 
            		
            		// further processing required
            		assert(selected instanceof Locatable);
            		Locatable selectedLocatable = (Locatable) selected;
            		remainingPath = remainingPath.substring(predicateEnd + 1);
            		
            		return selectedLocatable.itemAtPath(remainingPath);
            	
            	} else { // no predicate
            		
            		if(value instanceof Locatable) {
            			Locatable l = (Locatable) value;
            			return l.itemAtPath(remainingPath);
            		}
            		
            	}
        	}
        }
        return null; // instead of IllegalPathException?
    }
    
    /**
     * Processes the predicate expression on given list of objects
     * and select the _first_ matching one
     * 
     * TODO rudimentary implementation of process predicates in path
     * 
     * @param expression
     * @param value
     * @return null if there is no match
     */
    Locatable processPredicate(String expression, 
    		Iterable<Locatable> collection) {
    	String name = null;
    	String archetypeNodeId = null;
    	expression = expression.trim();
    	int index;
    	
    	// [at0001, 'standing']
    	if(expression.contains(",")) {
    		index = expression.indexOf(",");
    		archetypeNodeId = expression.substring(0, index).trim();
    		name = expression.substring(expression.indexOf("'") + 1,
    				expression.lastIndexOf("'"));
    		
    	// [at0006 and name/value='any event']
    	} else if(expression.contains(" AND ")) {
    		index = expression.indexOf("AND");
    		archetypeNodeId = expression.substring(0, index).trim();
    		name = expression.substring(expression.indexOf("'") + 1,
    				expression.lastIndexOf("'"));    		
    	
    	// [at0006]        	
    	} else if (expression.startsWith("at")) {
    		archetypeNodeId = expression;
    		
    	// ['standing']	
    	} else if (expression.startsWith("'") && expression.endsWith("'")) {
    		name = expression.substring(1, expression.length() - 1);
    	} 
    	
    	for(Locatable node : collection) {
    		
    		if(archetypeNodeId != null 
    				&& !node.archetypeNodeId.equals(archetypeNodeId)) {
    			continue;
    		}
    		if(name != null && !node.name.getValue().equals(name)) {
    			continue;
    		}
    		return node; // found a match!
    	}
    	return null;
    }    

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return the item
     * @throws IllegalArgumentException if path invalid
     */
    public Object itemAtPath(String path) {
    	if (path == null) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        if (Locatable.ROOT.equals(path) || path.equals(whole())) {
            return this;
        }
        List<String> attributeNames = retrieveAttributeNames();
        List<Object> attributeValues = retrieveAttributeValues();
        String remainingPath = path.substring(ROOT.length());
        return locateAttribute(remainingPath, attributeNames, attributeValues); 
    }
    
    /**
     * Retrieves list of attribute names of current class
     * 
     * @return empty list if no annotation
     */
    protected List<String> retrieveAttributeNames() {
    	Constructor constructor = fullConstructor(this.getClass());
    	if(constructor == null) {
    		return Collections.EMPTY_LIST;
    	}
    	Annotation[][] annotations = constructor.getParameterAnnotations();
    	List<String> names = new ArrayList<String>();
		for (int i = 0; i < annotations.length; i++) {
			assert(annotations[i].length != 0);
			Attribute attribute = (Attribute) annotations[i][0];
			if( ! attribute.system()) {
				names.add(attribute.name());
			}
		}
    	return names;
    }
    
    /**
     * Retrieves list attribute values of the current object
     * 
     * @return empty list if no annotation
     */
    protected List<Object> retrieveAttributeValues() {
    	List<Object> values = new ArrayList<Object>();
    	List<String> names = retrieveAttributeNames();
    	Class rmClass = this.getClass();
    	Method getter;
    	Object value;
    	
    	for(String attr : names) {
    		value = null;
    		String getterName = "get" + attr.substring(0, 1).toUpperCase() 
    						+ attr.substring(1);
    		try {
    			getter = rmClass.getMethod(getterName, null);
    			value = getter.invoke(this, null);
    		} catch(Exception ignore) {
    			// TODO log as kernel warning
    		}
    		values.add(value);
    	}
    	return values;
    }
    
    // find the annotated full constructor of given class
    private static Constructor fullConstructor(Class klass) {
		Constructor[] array = klass.getConstructors();
		for (Constructor constructor : array) {
			if (constructor.isAnnotationPresent(FullConstructor.class)) {
				return constructor;
			}
		}
		return null;
	}
    
    
    /**
     * Clinical concept of the archetype as a whole, derived from the
     * archetypeNodeId  of the root node
     *
     * @return archetyep concept
     * @throws UnsupportedOperationException if this node is not root
     */
    public DvText concept() {
        if (!isArchetypeRoot()) {
            throw new UnsupportedOperationException("not root node");
        }
        return new DvText(archetypeDetails.getArchetypeId().conceptName());
    }

    /**
     * Return sting presentation of this locatable
     *
     * @return string presentation
     */
    public String toString() {
        return archetypeNodeId.equals(name) ?
                archetypeNodeId.toString() : archetypeNodeId + ", " + name;
    }

    /**
     * Equals if two actors has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Locatable )) return false;
        
        final Locatable loc = (Locatable) o;
        return new EqualsBuilder()
        		.appendSuper(super.equals(o))
                .append(uid, loc.uid)
                .append(archetypeNodeId, loc.archetypeNodeId)
                .append(name, loc.name)
                .append(archetypeDetails, loc.archetypeDetails)
                .append(feederAudit, loc.feederAudit)
                .append(links, loc.links)
                .isEquals();
    }

    /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(11, 29)
                .appendSuper(super.hashCode())
                .append(uid)
                .append(archetypeNodeId)
                .append(name)
                .append(archetypeDetails)
                .append(feederAudit)
                .append(links)
                .toHashCode();
    }

    /**
     * Return path of current whole node
     */
    public String whole() {
        return ROOT;// + "[" + getName().getValue() + "]";
    }

    public String nodeName() {
        return "[" + getName().getValue() + "]";
    }
    
    public String atNode() {
    		return ROOT + "[" + getArchetypeNodeId() + "]";
    }
    
    // POJO start
    protected Locatable() {    	
    }

    protected void setUid(UIDBasedID uid) {
        this.uid = uid;
    }

    protected void setArchetypeNodeId(String archetypeNodeId) {
        this.archetypeNodeId = archetypeNodeId;
    }

    protected void setName(DvText name) {
        this.name = name;
    }

    protected void setArchetypeDetails(Archetyped archetypeDetails) {
        this.archetypeDetails = archetypeDetails;
    }

    protected void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    protected void setLinks(Set<Link> links) {
        this.links = links;
    }
    
    // POJO end

    /**
     * Separator used to delimit segments in the path
     */
    public static final String PATH_SEPARATOR = "/";
    public static final String ROOT = PATH_SEPARATOR;
    private static final String PREDICATE_START = "[";
    private static final String PREDICATE_END = "]";
    
    /* fields */
    private UIDBasedID uid;
    private String archetypeNodeId;
    private DvText name;
    private Archetyped archetypeDetails;
    private FeederAudit feederAudit;
    private Set<Link> links;
   
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
 *  The Original Code is Locatable.java
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