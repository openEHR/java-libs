
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import br.com.zilics.archetypes.models.rm.support.basic.Interval;

/**
 * Generic class defining an interval(e.g. range) of a {@link DvOrdered} type.
 * @author Humberto
 * @param <T> Interval of which type 
 */
public class DvInterval<T extends DvOrdered<T>> extends Interval<T> {


	private static final long serialVersionUID = -8506102609503288752L;


}
