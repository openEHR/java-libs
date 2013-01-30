
package br.com.zilics.archetypes.models.rm.common.changecontrol;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;
import br.com.zilics.archetypes.models.rm.support.identification.HierObjectID;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectRef;

/**
 * Version control abstraction, defining  semantics for versioning one complex
 * object.
 * @author Humberto
 * @param <T> Type of the versioned object
 */
public class VersionedObject<T> extends RMObject {
	private static final long serialVersionUID = -2794719049011122242L;
	@NotNull
	@EqualsField
	private HierObjectID uid;
	@NotNull
	@EqualsField
    private ObjectRef ownerId;
	@NotNull
	@EqualsField
    private DvDateTime timeCreated;

    /**
     * Get the uid
     * @return Unique identifier of this version container. This id will be the same in all
     * instances of the same container in a distributed environment, meaning that it
     * can be understood as the uid of the "virtual version tree".
     */
    public HierObjectID getUid() {
        return uid;
    }

    /**
     * Set the uid
     * @param uid Unique identifier of this version container. This id will be the same in all
     * instances of the same container in a distributed environment, meaning that it
     * can be understood as the uid of the "virtual version tree".
     */
    public void setUid(HierObjectID uid) {
    	assertMutable();
        this.uid = uid;
    }

    /**
     * Get the ownerId
     * @return Reference to object to which this version container belongs, e.g. the id of
     * the containing EHR or other relevant owning entity.
     */
    public ObjectRef getOwnerId() {
        return ownerId;
    }

    /**
     * Set the ownerId
     * @param ownerId Reference to object to which this version container belongs, e.g. the id of
     * the containing EHR or other relevant owning entity.
     */
    public void setOwnerId(ObjectRef ownerId) {
    	assertMutable();
        this.ownerId = ownerId;
    }

    /**
     * Get the timeCreated
     * @return Time of initial creation of this versioned object.
     */
    public DvDateTime getTimeCreated() {
        return timeCreated;
    }

    /**
     * Set the timeCreated
     * @param timeCreated Time of initial creation of this versioned object.
     */
    public void setTimeCreated(DvDateTime timeCreated) {
    	assertMutable();
        this.timeCreated = timeCreated;
    }
}
