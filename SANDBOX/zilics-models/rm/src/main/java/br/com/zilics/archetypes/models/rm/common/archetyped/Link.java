
package br.com.zilics.archetypes.models.rm.common.archetyped;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;
import br.com.zilics.archetypes.models.rm.datatypes.uri.DvEHRURI;

/**
 * The Link type defines a logical relationship between two items,
 * such as two ENTRYs or an ENTRY and a COMPOSITION. Links can be
 * used across compositions, and across EHRs. Links can potentially
 * be used between interior (ie non archetype root) nodes, although
 * this probably should be prevented in archetypes. Multiple LINKs
 * can be attached to the root object of any archetyped structure to
 * give the effect of a 1->N link.
 *
 * @author Humberto
 */
public class Link extends RMObject {
	private static final long serialVersionUID = -3670248843920444445L;
	@NotNull
	@EqualsField
	private DvText meaning;
	@NotNull
	@EqualsField
    private DvText type;
	@NotNull
	@EqualsField
    private DvEHRURI target;

    /**
     * Get the meaning
     * @return The description of the relationship, usually in clinical terms, such as
     * "in responde to" (the relationship between test results and an order),
     * "follow-up to" and so on. Such relationships can represent any clinical
     * meaningful connection between pieces of information.
     */
    public DvText getMeaning() {
        return meaning;
    }

    /**
     * Set the meaning
     * @param meaning The description of the relationship, usually in clinical terms, such as
     * "in responde to" (the relationship between test results and an order),
     * "follow-up to" and so on. Such relationships can represent any clinical
     * meaningful connection between pieces of information.
     */
    public void setMeaning(DvText meaning) {
    	assertMutable();
        this.meaning = meaning;
    }

    /**
     * Get the type
     * @return The type attribute is used to indicate a clinical or domain-level
     * meaning for the kind of link, for example "problem" or "issue".
     * type values are designed appropriately, they can be used by the
     * requestor of EHR extracts to categorise links which must be followed
     * and which can be broken when the extract is created.
     */
    public DvText getType() {
        return type;
    }

    /**
     * Set the type
     * @param type The type attribute is used to indicate a clinical or domain-level
     * meaning for the kind of link, for example "problem" or "issue".
     * type values are designed appropriately, they can be used by the
     * requestor of EHR extracts to categorise links which must be followed
     * and which can be broken when the extract is created.
     */
    public void setType(DvText type) {
    	assertMutable();
        this.type = type;
    }

    /**
     * Get the target
     * @return The logical "to" object in the link relation, as per the linguistic
     * sense of the <I>meaning</I> attribute.
     */
    public DvEHRURI getTarget() {
        return target;
    }

    /**
     * Set the target
     * @param target The logical "to" object in the link relation, as per the linguistic
     * sense of the <I>meaning</I> attribute.
     */
    public void setTarget(DvEHRURI target) {
    	assertMutable();
        this.target = target;
    }
}
