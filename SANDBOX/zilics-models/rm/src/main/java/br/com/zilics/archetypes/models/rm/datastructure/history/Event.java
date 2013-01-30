
package br.com.zilics.archetypes.models.rm.datastructure.history;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;

/**
 * Defines the abstract notion of a single event in a series. This class is
 * generic, allowing types to be generated which are locked to a particular
 * spatial types, such as EVENT<ItemList> Subtypes express point or interval
 * data.
 *
 * @author Humberto
 * @param <T> Event of which type
 */
public abstract class Event<T extends ItemStructure> extends Locatable {
	private static final long serialVersionUID = -1063133073178998363L;
	@NotNull
	@EqualsField
	private DvDateTime time;
	@EqualsField
    private ItemStructure state;
	@NotNull
	@EqualsField
    private T data;

    /**
     * Get the time
     * @return Time of this event. If the width is non-zero, it is the time
     * point of the trailing edge of the event.
     */
    public DvDateTime getTime() {
        return time;
    }

    /**
     * Set the time
     * @param time Time of this event. If the width is non-zero, it is the time
     * point of the trailing edge of the event.
     */
    public void setTime(DvDateTime time) {
		assertMutable();
        this.time = time;
    }

    /**
     * Get the state
     * @return Optional state data for this event.
     */
    public ItemStructure getState() {
        return state;
    }

    /**
     * Set the state
     * @param state Optional state data for this event.
     */
    public void setState(ItemStructure state) {
		assertMutable();
        this.state = state;
    }

    /**
     * Get the data
     * @return The data of this event.
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data
     * @param data The data of this event.
     */
    public void setData(T data) {
		assertMutable();
        this.data = data;
    }
}
