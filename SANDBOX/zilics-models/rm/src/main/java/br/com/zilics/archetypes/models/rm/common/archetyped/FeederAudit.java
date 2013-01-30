
package br.com.zilics.archetypes.models.rm.common.archetyped;

import java.util.List;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DvIdentifier;
import br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvEncapsulated;

/**
 * Audit and other meta-data for systems in the feeder chain.
 *
 * @author Humberto
 */
public class FeederAudit extends RMObject {
	private static final long serialVersionUID = 1866300849685374726L;

	@NotNull
	@EqualsField
	private FeederAuditDetails originatingSystemAudit;
    private List<DvIdentifier> originatingSystemItemIds;
    @EqualsField
    private FeederAuditDetails feederSystemAudit;
    private List<DvIdentifier> feederSystemItemIds;
    @EqualsField
    private DvEncapsulated originalContent;

    /**
     * Get the originatingSystemAudit
     * @return Any audit information item from the originating system.
     */
    public FeederAuditDetails getOriginatingSystemAudit() {
        return originatingSystemAudit;
    }

    /**
     * Set the originatingSystemAudit
     * @param originatingSystemAudit Any audit information item from the originating system.
     */
    public void setOriginatingSystemAudit(FeederAuditDetails originatingSystemAudit) {
    	assertMutable();
        this.originatingSystemAudit = originatingSystemAudit;
    }

    /**
     * Get the originatingSystemItemIds
     * @return Identifiers used for the item in the originating system,
     * e.g. filler and placer ids.
     */
    public List<DvIdentifier> getOriginatingSystemItemIds() {
        return getList(originatingSystemItemIds);
    }

    /**
     * Set the originatingSystemItemIds
     * @param originatingSystemItemIds Identifiers used for the item in the originating system,
     * e.g. filler and placer ids.
     */
    public void setOriginatingSystemItemIds(List<DvIdentifier> originatingSystemItemIds) {
    	assertMutable();
        this.originatingSystemItemIds = originatingSystemItemIds;
    }

    /**
     * Get the feederSystemAudit
     * @return Any audit information for the information item from the feeder
     * system, if different from the originating system.
     */
    public FeederAuditDetails getFeederSystemAudit() {
        return feederSystemAudit;
    }

    /**
     * Set the feederSystemAudit
     * @param feederSystemAudit Any audit information for the information item from the feeder
     * system, if different from the originating system.
     */
    public void setFeederSystemAudit(FeederAuditDetails feederSystemAudit) {
    	assertMutable();
        this.feederSystemAudit = feederSystemAudit;
    }

    /**
     * Get the feederSystemItemIds
     * @return Identifiers used for the item in the feeder system,
     * where the feeder system is distinct from the originating system.
     */
    public List<DvIdentifier> getFeederSystemItemIds() {
        return getList(feederSystemItemIds);
    }

    /**
     * Set the feederSystemItemIds
     * @param feederSystemItemIds Identifiers used for the item in the feeder system,
     * where the feeder system is distinct from the originating system.
     */
    public void setFeederSystemItemIds(List<DvIdentifier> feederSystemItemIds) {
    	assertMutable();
        this.feederSystemItemIds = feederSystemItemIds;
    }

    /**
     * Get the originalContent
     * @return Optional inline inclusion of or reference to original content
     * corresponding to the <I>open</I>EHR content at this node.
     * Typically a URI reference to a document or message in a persistent
     * store associated with the EHR.
     */
    public DvEncapsulated getOriginalContent() {
        return originalContent;
    }

    /**
     * Set the originalContent
     * @param originalContent Optional inline inclusion of or reference to original content
     * corresponding to the <I>open</I>EHR content at this node.
     * Typically a URI reference to a document or message in a persistent
     * store associated with the EHR.
     */
    public void setOriginalContent(DvEncapsulated originalContent) {
    	assertMutable();
        this.originalContent = originalContent;
    }
}
