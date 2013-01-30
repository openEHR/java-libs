
package br.com.zilics.archetypes.models.rm.datastructure.history;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datastructure.DataStructure;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDuration;

/**
 * The abstract parent class of various concrete historical
 * structures, currently including discrete series and series of
 * states, either of which may be periodic.
 *
 * @author Humberto
 * @param <T> History of which type
 */
public class History<T extends ItemStructure> extends DataStructure {

	private static final long serialVersionUID = 216876376203769309L;
	@NotNull
	@EqualsField
	private DvDateTime origin;
    private DvDuration period;
    private DvDuration duration;
    
    @EqualsField
    private List<Event<T>> events;
    
    private ItemStructure summary;

    /**
     * Get the origin
     * @return Time origin of this event history.
     * The first event is not necessarily at the origin point.
     */
    public DvDateTime getOrigin() {
        return origin;
    }

    /**
     * Set the origin
     * @param origin Time origin of this event history.
     * The first event is not necessarily at the origin point.
     */
    public void setOrigin(DvDateTime origin) {
		assertMutable();
        this.origin = origin;
    }

    /**
     * Get the period
     * @return Period between samples in this segment if periodic.
     */
    public DvDuration getPeriod() {
        return period;
    }

    /**
     * Set the period
     * @param period Period between samples in this segment if periodic.
     */
    public void setPeriod(DvDuration period) {
		assertMutable();
        this.period = period;
    }

    /**
     * Get the duration
     * @return Duration of the entire History; either corresponds to the duration of all the
     * events, and/or the duration represented by the summary, if it exists.
     */
    public DvDuration getDuration() {
        return duration;
    }

    /**
     * Set the duration
     * @param duration Duration of the entire History; either corresponds to the duration of all the
     * events, and/or the duration represented by the summary, if it exists.
     */
    public void setDuration(DvDuration duration) {
		assertMutable();
        this.duration = duration;
    }

    /**
     * Get the events
     * @return The events in the series.
     */
    public List<Event<T>> getEvents() {
        return getList(events);
    }

    /**
     * Set the events
     * @param events The events in the series.
     */
    public void setEvents(List<Event<T>> events) {
		assertMutable();
        this.events = events;
    }

    /**
     * Get the summary
     * @return Optional summary data expressing e.g. text or image which
     * summarises entire History.
     */
    public ItemStructure getSummary() {
        return summary;
    }

    /**
     * Set the summary
     * @param summary Optional summary data expressing e.g. text or image which
     * summarises entire History.
     */
    public void setSummary(ItemStructure summary) {
		assertMutable();
        this.summary = summary;
    }
}
