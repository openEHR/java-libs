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
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datatypes.text.DvText;

import java.util.Set;
import java.util.Collection;

/**
 * Root structural class of all information models. Instances of
 * this class are immutalbe.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Locatable extends RMObject {

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
    protected Locatable(ObjectID uid, String archetypeNodeId, DvText name,
                        Archetyped archetypeDetails,FeederAudit feederAudit, 
                        Set<Link> links, Locatable parent) {
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
        this.parent = parent;
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
     * @return objectID nu
     */
    public ObjectID getUid() {
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
     * Parent of this node in compositional hierarchy
     * 
     * @return parent null if not specified
     */
	public Locatable getParent() {
		return parent;
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
    public abstract String pathOfItem(Locatable item);

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
        return null; // can be further processed by sub-class
    }

    /**
     * Locate an attribute of given path
     *
     * @param path
     * @param attributeNames
     * @param attributes
     * @return the attribute found
     * @throws IllegalArgumentException if path invalid
     */
    protected Object locateAttribute(String path, String[] attributeNames,
                                        Locatable[] attributes) {
        for (int i = 0, j = attributes.length; i < j; i++) {
            Object ret = checkAttribute(path, attributeNames[ i ],
                    attributes[ i ]);
            if (ret != null) {
                return ret;
            }
        }
        throw new IllegalArgumentException("invalid path: " + path);
    }

    /**
     * Check if given single attribute match the path
     *
     * @param path
     * @param attributeName
     * @param attribute
     * @return null if not matching
     */
    protected Object checkAttribute(String path, String attributeName,
                                       Locatable attribute) {
        if (attribute == null) {
            return null;
        }
        String attrPath = ROOT + attributeName;
        //String attrPathWithNode = attrPath + attribute.whole().substring(1);
        
        // Get path in form: /<attr>[<arthcetypeNodeId>]
        String attrPathWithNode = attrPath + attribute.atNode().substring(1);
        if (path.equals(attrPathWithNode) || path.equals(attrPath)) {
            return attribute;
        } else if (path.startsWith(attrPathWithNode)) {
            return attribute.itemAtPath(path.substring(
                    attrPathWithNode.length()));
        } else if (path.startsWith(attrPath)) {
            return attribute.itemAtPath(path.substring(attrPath.length()));
        }
        return null;
    }

    /**
     * Check if given multiple attribute match the path
     *
     * @param path
     * @param attributeName
     * @param attribute
     * @return null if not matching
     */
    protected Object checkAttribute(String path,
                                       String attributeName,
                                       Collection<? extends Locatable> attribute) {
        if (attribute == null) {
            return null;
        }
        String attr = ROOT + attributeName;
        if (path.startsWith(attr)) {
            path = path.substring(attr.length());
            for (Locatable locatable : attribute) {
                //String node = locatable.whole().substring(1);
                String node = locatable.nodeName();
                if (path.startsWith(node)) {
                    if (path.equals(node)) {
                        return locatable;
                    }                    
                    String subpath = path.substring(node.length());
                    if (locatable.validPath(subpath)) {
                        return locatable.itemAtPath(subpath);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Return true if the path is valid with respect to the current
     * item.
     *
     * @param path
     * @return true if valid
     */
    public boolean validPath(String path) {
        if (ROOT.equals(path) /*|| whole().equals(path)*/) {
            return true;
        }
        return false;  // can be further processed by sub-class
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
        return new DvText(archetypeDetails.getArchetypeID().conceptName());
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
                .append(uid, loc.uid)
                .append(archetypeNodeId, loc.archetypeNodeId)
                .append(name, loc.name)
                .append(archetypeDetails, loc.archetypeDetails)
                .append(feederAudit, loc.feederAudit)
                .append(links, loc.links)
                .append(parent, loc.parent)
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
                .append(parent)
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

    protected void setUid(ObjectID uid) {
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
    
	protected void setParent(Locatable parent) {
		this.parent = parent;
	}
    // POJO end

    /**
     * Separator used to delimit element in the path
     */
    public static final String PATH_SEPARATOR = "/";
    public static final String ROOT = PATH_SEPARATOR;

    /* fields */
    private ObjectID uid;
    private String archetypeNodeId;
    private DvText name;
    private Archetyped archetypeDetails;
    private FeederAudit feederAudit;
    private Set<Link> links;
    private Locatable parent;
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