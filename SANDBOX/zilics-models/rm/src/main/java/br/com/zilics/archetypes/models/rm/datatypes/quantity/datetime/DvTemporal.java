
package br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime;

import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvAbsoluteQuantity;

/**
 * Specialised temporal variant of {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvAbsoluteQuantity} whose <I>diff</I> type is {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDuration}.
 * @author Humberto
 */
public abstract class DvTemporal<T extends DvTemporal<T>> extends DvAbsoluteQuantity<T, DvDuration> {

	private static final long serialVersionUID = -5854764770469298902L;

}
