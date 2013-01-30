
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.datastructure.history.History;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;

/**
 * Purpose
 * {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Entry} subtype for all
 * clinical data in the past or present, ie which (by the time it is recorded)
 * has already occurred.
 * {@link Observation} data is expressed using the class {@link History}, which
 * guarantees that it is situated in time.
 *
 * @author Humberto
 */
public class Observation extends CareEntry {

	private static final long serialVersionUID = 683102497463755138L;
	private History<? extends ItemStructure> data;
    private History<? extends ItemStructure> state;

    /**
     * Get the data
     * @return the data of this observation
     */
    public History<? extends ItemStructure> getData() {
        return data;
    }

    /**
     * Set the data
     * @param data the data of this observation
     */
    public void setData(History<? extends ItemStructure> data) {
		assertMutable();
        this.data = data;
    }

    /**
     * Get the state
     * @return the recording of the state of subject of this observation during the observation process
     */
    public History<? extends ItemStructure> getState() {
        return state;
    }

    /**
     * Set the state
     * @param state the recording of the state of subject of this observation during the observation process
     */
    public void setState(History<? extends ItemStructure> state) {
		assertMutable();
        this.state = state;
    }
}
