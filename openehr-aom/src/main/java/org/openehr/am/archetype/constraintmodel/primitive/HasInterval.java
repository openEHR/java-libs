package org.openehr.am.archetype.constraintmodel.primitive;

import org.openehr.rm.support.basic.Interval;

/**
 * Constraints with intervals.
 */
public interface HasInterval<T extends Comparable<?>> {

	/** Get interval from constraint. */
	Interval<T> getInterval();

}
