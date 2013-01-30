
package br.com.zilics.archetypes.models.rm.common.changecontrol;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * Versions whose content is an {@link OriginalVersion} copied from another location;
 * this class inherits commit_audit and contribution from version, providing
 * imported versions with their own audit trail and contribution, distinct from those
 * of the imported {@link OriginalVersion}.
 * @author Humberto
 * @param <T> Type of the versioned object
 */
public class ImportedVersion<T extends RMObject> extends Version<T> {
	private static final long serialVersionUID = -5739620026492379387L;
	@NotNull
	@EqualsField
	private OriginalVersion<T> item;

    /**
     * Get the item
     * @return The {@link OriginalVersion} object that was imported.
     */
    public OriginalVersion<T> getItem() {
        return item;
    }

    /**
     * Set the item
     * @param item The {@link OriginalVersion} object that was imported.
     */
    public void setItem(OriginalVersion<T> item) {
    	assertMutable();
        this.item = item;
    }    
}
