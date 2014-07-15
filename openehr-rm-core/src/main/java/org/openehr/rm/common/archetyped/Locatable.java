/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Locatable"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * copyright:   "Copyright (c) 2010 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/archetyped/Locatable.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.archetyped;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datatypes.text.DvText;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Root structural class of all information models. Instances of
 * this class are immutalbe.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Locatable extends Pathable implements Settable, Cloneable {

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

    public String getOriginalArchetypeNodeId() {
        return originalArchetypeNodeId;
    }

    public String setOriginalArchetypeNodeId(String originalArchetypeNodeId) {
        return this.originalArchetypeNodeId = originalArchetypeNodeId;
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



    /*
     * Simple fix doesn't take care of "/" inside predicates
     * e.g. data/events[at0006 and name/value='any event']
     * 
     * OG - 2010-03-15: Added fix that seems to solve this problem.
     */
    public static List<String> dividePathIntoSegments(String path) {
    	List<String> segments = new ArrayList<String>();
    	StringTokenizer tokens = new StringTokenizer(path, "/");
    	while(tokens.hasMoreTokens()) {
    		String next = tokens.nextToken();
    		if (next.matches(".+\\[.+[^\\]]$")) {
    			do {
    				next = next + "/" + tokens.nextToken();
    			} while (!next.matches(".*]$"));
    		}
    		segments.add(next);
    	}
    	return segments;
    }

    /*
     * generic path evaluation covers all rmClass
     */
    private Object genericPathEvaluator(String path, Object object) {
    	if(path == null || object == null) {
    		return null;
    	}
    	List<String> segments = dividePathIntoSegments(path);
    	return evaluatePathSegment(segments, object);
    }

    /*
     * Evaluate recursively the path segments 
     */
    private Object evaluatePathSegment(List<String> pathSegments,
    		Object object) {
    	if(pathSegments.isEmpty()) {
    		return object;
    	}
    	String pathSegment = pathSegments.remove(0);
    	Object value =  null;

    	int index = pathSegment.indexOf("[");
    	String expression = null;
    	String attributeName = null;

    	// has [....] predicate expression
    	if(index > 0) {

    		assert(pathSegment.indexOf("]") > index);

    		attributeName = pathSegment.substring(0, index);
    		expression = pathSegment.substring(index + 1,
    				pathSegment.indexOf("]"));
    	} else {
    		attributeName = pathSegment;
    	}

    	value = getAttributeValue(object, attributeName);
    	if(expression != null && value != null ) {
    		value = processPredicate(expression, value);
    	}
        if(expression == null && value instanceof ArrayList && !pathSegments.isEmpty()) {
            ArrayList arrayList = ((ArrayList)value);
            if (!arrayList.isEmpty()){
                value = arrayList.get(0);
            }
        }
    	if(value != null) {
    		return evaluatePathSegment(pathSegments, value);
    	}
    	return null;
    }

    public String toCamelCase(String underscoreSeparated) {
    	if( ! underscoreSeparated.contains("_")) {
    		return underscoreSeparated;
    	}
		StringTokenizer tokens = new StringTokenizer(underscoreSeparated, "_");
		StringBuffer buf = new StringBuffer();
		while (tokens.hasMoreTokens()) {
			String word = tokens.nextToken();
			if (buf.length() == 0) {
				buf.append(word);
			} else {
				buf.append(word.substring(0, 1).toUpperCase());
				buf.append(word.substring(1));
			}
		}
		return buf.toString();
	}

    public String toFirstUpperCaseCamelCase(String name) {
    	name = toCamelCase(name);
    	return name.substring(0, 1).toUpperCase()
		+ name.substring(1);
    }

    /**
     * Processes the predicate expression on given object
     * 1. if the object is a container, select the _first_ matching one
     * 2. only return the object if itself meets the predicate
     *
     * only shortcut expressions for at0000 and name are supported
     * for example: [at0001, 'node name']
     *
     * @param expression
     * @param value
     * @return null if there is no match
     */
    Object processPredicate(String expression, Object object) {
    	String name = null;
    	String archetypeNodeId = null;
    	expression = expression.trim();
    	int index;

    	// shortcut syntax, [at0001, 'standing']
    	if(expression.contains(",")
    			// avoid [at0001 and/value='status, 2nd']
    			&& expression.indexOf(",") < expression.indexOf("'")) {
    		index = expression.indexOf(",");
    		archetypeNodeId = expression.substring(0, index).trim();
    		name = expression.substring(expression.indexOf("'") + 1,
    				expression.lastIndexOf("'"));

    	// [at0006 and name/value='any event']
    	// [at0006 AND name/value='any event']
    	} else if(expression.contains(" AND ")
    			|| expression.contains(" and ")) {

    		// OG - 20100401: Fixed bug where the name contained 'AND' or 'and',
    		// i.e. 'MEDICINSK BEHANDLING'.
    		if(expression.contains(" AND ")) {
    			index = expression.indexOf(" AND ");
    		} else {
    			index = expression.indexOf(" and ");
    		}
    		archetypeNodeId = expression.substring(0, index).trim();
    		name = expression.substring(expression.indexOf("'") + 1,
    				expression.lastIndexOf("'"));
    	// just name, ['standing']
    	} else if (expression.startsWith("'") && expression.endsWith("'")) {
    		name = expression.substring(1, expression.length() - 1);

    	// archetyped root node id or at-coded node
    	// [at0006] or [openEHR-EHR-OBSERVATION.laboratory-lipids.v1]
    	} else {
    		archetypeNodeId = expression;
    	}

    	Iterable collection = null;
    	if(object instanceof Iterable) {
    		collection = (Iterable) object;
    	} else {
    		List list = new ArrayList();
    		list.add(object);
    		collection = list;
    	}

    	for(Object item : collection) {
    		if(item instanceof Locatable) {
    			Locatable locatable = (Locatable) item;
        		if(archetypeNodeId != null
        				&& !locatable.archetypeNodeId.equals(archetypeNodeId)) {
        			continue;
        		}
        		if(name != null && !locatable.name.getValue().equals(name)) {
        			continue;
        		}
    		}
    		// TODO other non-locatable predicates!!
    		// e.g. time > 10:20:15
    		return item; // found a match!
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
        return genericPathEvaluator(path, this);
    }

    /*
     * Retrieves the value of named attribute of given object
     */
    private Object getAttributeValue(Object obj, String attribute) {
    	Class rmClass = obj.getClass();
    	Object value = null;
    	Method getter = null;
    	String getterName = "get" + toFirstUpperCaseCamelCase(attribute);

    	try {
			getter = rmClass.getMethod(getterName, null);
			value = getter.invoke(obj, null);

    	} catch(Exception e) {
			// TODO log as kernel warning
			// e.printStackTrace();
		}
		return value;
    }

    /*
     * Sets the value of named attribute of given object
     */
    private void setAttributeValue(Object obj, String attribute, Object value) {
    	Class rmClass = obj.getClass();
    	String setterName = "set" + toFirstUpperCaseCamelCase(attribute);

    	try {
			Method setter = getMethodByName(rmClass, setterName);
			if(setter == null) {
				throw new IllegalArgumentException("unkown setter method: " + setterName + " for rmClass=" + rmClass);
			}
			setter.invoke(obj, value);

    	} catch(Exception e) {
    		// TODO log as kernel warning
			e.printStackTrace();
		}
    }

    private Method getMethodByName(Class klass, String method) {
    	Method[] methods = klass.getMethods();
    	for(Method m : methods) {
    		if(method.equals(m.getName())) {
    			return m;
    		}
    	}
    	return null;
    }


    public void set(String path, Object value) {
    	int i = path.lastIndexOf("/");
    	if(i < 0 || i == path.length()) {
    		throw new IllegalArgumentException(
    				"invalid path for setting value: " + path);
    	}
    	String objPath = "/";
    	if(i > 0) {
    		objPath = path.substring(0, i);
    	}
    	String attributeName = path.substring(i + 1);

    	Object obj = itemAtPath(objPath);
    	if(obj == null) {
    		throw new IllegalArgumentException("Item not found on path: " + path);
    	}
    	setAttributeValue(obj, attributeName, value);
    }

    /**
     * Computes the path of parent object
     *
     * @param path
     * @return
     */
    public static String parentPath(String path) {
    	List<String> list = dividePathIntoSegments(path);
    	int pathLevel = list.size();
    	if(pathLevel == 0) {
    		throw new IllegalArgumentException("Unable to compute parent path: "
    				+ path);
    	} else if(pathLevel == 1) {
    		return PATH_SEPARATOR;
    	}
    	StringBuffer buf = new StringBuffer();
    	for(int j = 0; j < pathLevel - 1; j++) {
    		buf.append(PATH_SEPARATOR);
    		buf.append(list.get(j));
    	}
    	return buf.toString();
    }

    public void addChild(String path, Object child) {
    	List<String> list = dividePathIntoSegments(path);
    	int pathLevel = list.size();
    	String objPath = PATH_SEPARATOR;

    	if(pathLevel > 1) {
    		StringBuffer buf = new StringBuffer();
    		for(int j = 0; j < pathLevel - 1; j++) {
    			buf.append(PATH_SEPARATOR);
    			buf.append(list.get(j));
    		}
    		objPath = buf.toString();
    	}

    	Object obj = itemAtPath(objPath);
    	if(obj == null) {
    		throw new IllegalArgumentException("Item not found on path: " + path);
    	}

    	String attributeName = list.get(list.size() - 1);
    	Object attributeValue = getAttributeValue(obj, attributeName);
    	if(attributeValue == null) {
    		attributeValue = new ArrayList();
    	}
    	if(attributeValue instanceof List) {
    		List parent = (List) attributeValue;
    		parent.add(child);
    	} else {
    		throw new IllegalArgumentException(
    				"non-container parent attribute on path: " + path);
    	}
    }

    public void removeChild(String path) {
    	int i = path.lastIndexOf(PATH_SEPARATOR);
    	if(i < 0 || i == path.length()) {
    		throw new IllegalArgumentException(
    				"invalid path for setting value: " + path);
    	}
    	String objPath = PATH_SEPARATOR;
    	List<String> list = dividePathIntoSegments(path);
    	int pathLevel = list.size();
    	if(pathLevel > 1) {
    		StringBuffer buf = new StringBuffer();
    		for(int j = 0; j < pathLevel - 1; j++) {
    			buf.append(PATH_SEPARATOR);
    			buf.append(list.get(j));
    		}
    		objPath = buf.toString();
    	}

    	Object obj = itemAtPath(objPath);
    	if(obj == null) {
    		throw new IllegalArgumentException("Item not found on path: " + path);
    	}

    	Object child = itemAtPath(path);
    	if(child == null) {
    		throw new IllegalArgumentException("Unknown child on path: " + path);
    	}

    	String attributeName = list.get(list.size() - 1);
    	int predicateIndex = attributeName.indexOf("[");
    	if(predicateIndex > 0) {
    		attributeName = attributeName.substring(0, predicateIndex);
    	}
    	Object attributeValue = getAttributeValue(obj, attributeName);

    	if(attributeValue == null) {
    		throw new IllegalArgumentException(
    				"parent attribute not found on path: " + path);
    	}
    	if(attributeValue instanceof List) {

    		List parent = (List) attributeValue;
    		parent.remove(child);

    	} else {
    		throw new IllegalArgumentException(
    				"non-container parent attribute on path: " + path);
    	}
	}

	/**
     * Clinical concept of the archetype as a whole, derived from the
     * archetypeNodeId  of the root node
     *
     * @return archetype concept
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

    @Override public Locatable clone() {
    	try {
    		return (Locatable) super.clone();
    	} catch(CloneNotSupportedException e) {
    		throw new AssertionError();
    	}
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

    /* fields */
    private UIDBasedID uid;
    private String archetypeNodeId;
    private String originalArchetypeNodeId;
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