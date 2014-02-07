/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Element"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/itemstructure/representation/Element.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.itemstructure.representation;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.List;
import java.util.Set;

/**
 * The leaf variant of ITEM, to which a DATA_VALUE instance is
 * attached.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Element extends Item {

    /**
     * Construct an Element
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param value
     * @param nullFlavour       required if value is null
     * @throws IllegalArgumentException if both value and nullFlavour
     *                                  are null
     */
    @FullConstructor
            public Element(@Attribute(name = "uid") UIDBasedID uid,
                           @Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                           @Attribute(name = "name", required=true) DvText name,
                           @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                           @Attribute(name = "feederAudit") FeederAudit feederAudit,
                           @Attribute(name = "links") Set<Link> links,
                           @Attribute(name = "parent") Pathable parent, 
                           @Attribute(name = "value") DataValue value,
                           @Attribute(name = "nullFlavour") DvCodedText nullFlavour,
                           @Attribute(name = "terminologyService",  system=true) TerminologyService terminologyService) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
        if (( value == null && nullFlavour == null )
                || ( value != null && nullFlavour != null )) {
            throw new IllegalArgumentException(
                    "null or unnecessary nullFlavour");
        }
        if (value == null) {
            if (terminologyService == null) {
                throw new IllegalArgumentException("null terminologyService");
            }
            if (!terminologyService.terminology(TerminologyService.OPENEHR)
                    .codesForGroupName(TerminologyService.NULL_FLAVOURS, "en")
                    .contains(nullFlavour.getDefiningCode())) {
                throw new IllegalArgumentException(
                        "unknown nullFlavour: " + nullFlavour);
            }
        }
        this.value = value;
        this.nullFlavour = nullFlavour;
    }
    
    /**
     * Constructs an Element node by archetypeNodeId, name and non-null value
     *
     * @param archetypeNodeId
     * @param name
     * @param value
     * @throws IllegalArgumentException if name or value null
     */
    public Element(String archetypeNodeId, DvText name, DataValue value) {
        this(null, archetypeNodeId, name, null, null, null, null, value, null,
        		null);
    }
    
    /**
     * Constructs an Element node by archetypeNodeId, name and non-null value
     *
     * @param archetypeNodeId
     * @param name
     * @param value
     * @throws IllegalArgumentException if name or value null
     */
    public Element(String archetypeNodeId, String name, DataValue value) {
        this(archetypeNodeId, new DvText(name), value);
    }
    
    /**
     * Gets data value of this leaf
     *
     * @return value of this leaf
     */
    public DataValue getValue() {
        return value;
    }

    /**
     * Gets flavor of null value, like indeterminate, not asked etc
     *
     * @return null flavor
     * @deprecated use getNullFlavour instead
     */
    public DvCodedText getNullFlavor() {
        return nullFlavour;
    }
    
    /**    
     * Gets flavour of null value, like indeterminate, not asked etc 
     * 
     * @return null flavour 
     */ 
    public DvCodedText getNullFlavour() { 
        return nullFlavour;
    }

    /**
     * True if value logically not known, if indeterminate,
     * not asked etc.
     *
     * @return true if value null
     */
    public boolean isNull() {
        return value == null;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return path of given item
     */
    public String pathOfItem(Pathable item) {
        return null;  // todo: implement this method
    }

    /**
     * Return true if value equals
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object obj) {
    	if (obj == null) { return false; }
    	   if (obj == this) { return true; }
    	   if (obj.getClass() != getClass()) {
    	     return false;
    	   }
    	   Element element = (Element) obj;
    	   return new EqualsBuilder()
    	                 .appendSuper(super.equals(obj))
    	                 .append(value, element.value)
    	                 .append(nullFlavour, element.nullFlavour)
    	                 .isEquals();
    }

    /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(17, 71)
                .appendSuper(super.hashCode())
                .append(value)
                .append(nullFlavour)
                .toHashCode();
    }
    
    // POJO start
    Element() {
    }

    public void setValue(DataValue value) {
        this.value = value;
    }

    /**    
     * @deprecated Use setNullFlavour instead 
     */
    public void setNullFlavor(DvCodedText nullFlavor) {
        this.nullFlavour = nullFlavor;
    }
    
    public void setNullFlavour(DvCodedText nullFlavour) {    	 
        this.nullFlavour = nullFlavour; 
    }
    // POJO end

	@Override
	public List<Object> itemsAtPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pathExists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String path) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* fields */
    private DataValue value;
    private DvCodedText nullFlavour;
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
 *  The Original Code is Element.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2010
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Sebastian Garde
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */