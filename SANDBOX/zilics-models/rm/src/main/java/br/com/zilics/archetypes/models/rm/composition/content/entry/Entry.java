
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import java.util.List;

import br.com.zilics.archetypes.models.rm.common.generic.Participation;
import br.com.zilics.archetypes.models.rm.common.generic.PartyProxy;
import br.com.zilics.archetypes.models.rm.composition.content.ContentItem;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectRef;

/**
 * The abstract parent of all Entry subtypes. An Entry is the root of
 * a logical item of  hard  clinical information created in the
 * "clinical statement" context, within a clinical session.
 *
 * @author Humberto
 */
public abstract class Entry extends ContentItem {
	private static final long serialVersionUID = 5463677200027463075L;
	private CodePhrase language;
    private CodePhrase encoding;
    private PartyProxy subject;

    private PartyProxy provider;

    private List<Participation> otherParticipations;

    private ObjectRef workflowId;

    /**
     * Get the language
     * @return the language in which this Entry is written
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Set the language
     * @param language the language in which this Entry is written
     */
    public void setLanguage(CodePhrase language) {
		assertMutable();
        this.language = language;
    }

    /**
     * Get the encoding
     * @return the name of character set in which text values in this Entry are encoded
     */
    public CodePhrase getEncoding() {
        return encoding;
    }

    /**
     * Set the encoding
     * @param encoding the name of character set in which text values in this Entry are encoded
     */
    public void setEncoding(CodePhrase encoding) {
		assertMutable();
        this.encoding = encoding;
    }

    /**
     * Get the subject
     * @return the Id of human subject of this Entry
     */
    public PartyProxy getSubject() {
        return subject;
    }

    /**
     * Set the subject
     * @param subject the Id of human subject of this Entry
     */
    public void setSubject(PartyProxy subject) {
		assertMutable();
        this.subject = subject;
    }

    /**
     * Get the provider
     * @return the optional indentification of provider of the information in this Entry
     */
    public PartyProxy getProvider() {
        return provider;
    }

    /**
     * Set the provider
     * @param provider the optional indentification of provider of the information in this Entry
     */
    public void setProvider(PartyProxy provider) {
		assertMutable();
        this.provider = provider;
    }

    /**
     * Get other participations
     * @return other participations at entry level
     */
    public List<Participation> getOtherParticipations() {
        return getList(otherParticipations);
    }

    /**
     * Set other participations
     * @param otherParticipations other participations at entry level
     */
    public void setOtherParticipations(List<Participation> otherParticipations) {
		assertMutable();
        this.otherParticipations = otherParticipations;
    }

    /**
     * Get the work flow Id
     * @return the identifier of externally held workflow engine data for this workflow execution, for this subject of care
     */
    public ObjectRef getWorkflowId() {
        return workflowId;
    }

    /**
     * Set the work flow Id
     * @param workflowId the identifier of externally held workflow engine data for this workflow execution, for this subject of care
     */
    public void setWorkflowId(ObjectRef workflowId) {
		assertMutable();
        this.workflowId = workflowId;
    }
}
