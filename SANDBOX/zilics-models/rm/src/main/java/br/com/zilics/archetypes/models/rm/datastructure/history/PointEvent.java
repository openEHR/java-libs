
package br.com.zilics.archetypes.models.rm.datastructure.history;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;

/**
 * Defines a single point event in a series
 *
 * @author Humberto
 * @param <T> PointEvent's type
 */
public class PointEvent<T extends ItemStructure> extends Event<T> {

	private static final long serialVersionUID = 1057893714851501058L;

}
