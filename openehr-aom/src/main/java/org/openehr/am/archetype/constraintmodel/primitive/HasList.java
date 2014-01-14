package org.openehr.am.archetype.constraintmodel.primitive;

import java.util.List;

/**
 * Constraint with a list.
 */
public interface HasList<T> {

	/** Get list from constraint. */
	List<T> getList();

}
