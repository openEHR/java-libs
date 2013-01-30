
package br.com.zilics.archetypes.models.rm.common.archetyped;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.support.identification.ArchetypeID;
import br.com.zilics.archetypes.models.rm.support.identification.TemplateID;

/**
 * Archetypes act as the configuration basis for the particular
 * structures of instances defined by the reference model. To enable
 * archetypes to be used to create valid data, key classes in the
 * reference model act as "root" points for archetyping; accordingly,
 * these classes have the archetype_details attribute set.
 * An instance of the class <code>Archetyped</code> contains the
 * relevant archetype identification information, allowing generating
 * archetypes to be matched up with data instances.
 *
 * @author Humberto
 */
public class Archetyped extends RMObject {
	private static final long serialVersionUID = 5250888672524155071L;

	@NotNull
	@EqualsField
	private ArchetypeID archetypeId;
	@EqualsField
    private TemplateID templateId;
	@EqualsField
    private String rmVersion;

    /**
     * Get the archetype identifier
     * @return globally unique archetype identifier
     */
    public ArchetypeID getArchetypeId() {
        return archetypeId;
    }

    /**
     * Set the archetype identifier
     * @param archetypeId globally unique archetype identifier
     */
    public void setArchetypeId(ArchetypeID archetypeId) {
    	assertMutable();
        this.archetypeId = archetypeId;
    }

    /**
     * Get the template identifier
     * @return globally unique template identifier
     */
    public TemplateID getTemplateId() {
        return templateId;
    }

    /**
     * Set the template identifier
     * @param templateId globally unique template identifier
     */
    public void setTemplateId(TemplateID templateId) {
    	assertMutable();
        this.templateId = templateId;
    }

    /**
     * Get version
     * @return version of the openEHR reference model used to create this object. Expressed in terms of the release version string, e.g. "1.0", "1.2.4"
     */
    public String getRmVersion() {
        return rmVersion;
    }

    /**
     * Set version
     * @param rmVersion version of the openEHR reference model used to create this object. Expressed in terms of the release version string, e.g. "1.0", "1.2.4"
     */
    public void setRmVersion(String rmVersion) {
    	assertMutable();
        this.rmVersion = rmVersion;
    }
}
