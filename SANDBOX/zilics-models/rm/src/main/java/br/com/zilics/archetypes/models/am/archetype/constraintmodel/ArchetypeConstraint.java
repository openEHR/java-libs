
package br.com.zilics.archetypes.models.am.archetype.constraintmodel;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;

/**
 * Archetype equivalent to {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable}
 * class in Common Reference Model.
 * <p>Defines commmon constraints for any inheritor of
 * {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable} in any reference model</p>
 *
 * @author Humberto
 */
public abstract class ArchetypeConstraint extends AMObject {
	private static final long serialVersionUID = 7372428877629635659L;

	@Ignore
	String canonicalPath;
	
	@Ignore
	Archetype ownerArchetype;
	
	/**
	 * Canonical Path of this node relative to root of archetype
	 * @return Canonical Path
	 */
	public String getCanonicalPath() {
		return canonicalPath;
	}
	
	/**
	 * Get the archetype owner of this constraint
	 * @return the owner
	 */
	public Archetype getOwnerArchetype() {
		return ownerArchetype;
	}
	
	/**
	 * Makes a simple copy of the constraint
	 * This method is used to duplicate constraints to resolve {@link ArchetypeInternalRef} references.
	 * (used by {@link Archetype#validate()})
	 * @return The simple copy of the constraint
	 */
    public abstract ArchetypeConstraint makeSimpleCopy();
}
