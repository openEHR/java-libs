/*
 * component:   "openEHR Reference Implementation"
 * description: "Class LocatableReference"
 * keywords:    "support"
 *
 * author:      ""
 * support:     ""
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/ObjectVersionID.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * Reference to a LOCATABLE instance inside the top-level content structure 
 * inside a VERSION<T>; the path attribute is applied to the object that 
 * VERSION.data points to.
 */
public class LocatableReference extends ObjectReference {

	/**
	 * Construct a LocatableReference
	 * 
	 * @param id
	 * @param namespace
	 * @param type
	 */
	public LocatableReference(ObjectVersionID id, Namespace namespace, 
			Type type, String path) {
		super(id, namespace, type);
		if (path != null && StringUtils.isEmpty(path)) {
			throw new IllegalArgumentException("empty path");
		}
		this.path = path;
	}

	/**
	 * The path to an instance in question, as an absolute path with 
	 * respect to the object found at VERSION.data. An empty path 
	 * means that the object referred to by id being specified.
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * A URI form of the reference, created by concatenating the 
	 * following:
	 * "ehr://" + id.value + "/" + path
	 */
	public String asURI() {
		return "ehr://" + getId().getValue() + "/" + path;
	}

    /**
     * Two LocatableReferences equals if they have same values for id,
     * namespace, type and path.
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ObjectReference )) return false;
        if (!super.equals(o)) return false;

        final LocatableReference locRef = (LocatableReference) o;

        return new EqualsBuilder()
                .append(path, locRef.path)
                .isEquals();
    }

    /**
     * Return a hash code of this locatable reference
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
        			.appendSuper(super.hashCode())
                .append(path)
                .toHashCode();
    }
	// POJO start
	LocatableReference() {
	}
	
	void setPath(String path) {
		this.path = path;
	}

	/* fields */
	private String path;
}
