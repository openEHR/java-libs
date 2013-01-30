
package br.com.zilics.archetypes.models.rm.datatypes.text;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;

/**
 * A mapping of a term to a text item.
 *
 * @author Humberto
 */
public class TermMapping extends DataValue {

	private static final long serialVersionUID = -6177754919546934369L;
	@NotNull
	@EqualsField
	private Match match;
    private DvCodedText purpose;
	@NotNull
	@EqualsField
    private CodePhrase target;

    /**
     * Get the match
     * @return The relative match of the target term with respect to the mapped text item.
     * Result meanings:
     * <PRE> '>': the mapping is to a broader term e.g. original text = "arbovirus infection", target = "viral infection";
     * '=': the mapping is to a (supposedly) equivalent to the original item;
     * '<': the mapping is to a narrower term e.g. original text = "diabetes", mapping = "diabetes mellitus";
     * '?': the king of mapping is unknown.</PRE>
     */
    public Match getMatch() {
        return match;
    }

    /**
     * Set the match
     * @param match The relative match of the target term with respect to the mapped text item.
     * Result meanings:
     * <PRE> '>': the mapping is to a broader term e.g. original text = "arbovirus infection", target = "viral infection";
     * '=': the mapping is to a (supposedly) equivalent to the original item;
     * '<': the mapping is to a narrower term e.g. original text = "diabetes", mapping = "diabetes mellitus";
     * '?': the king of mapping is unknown.</PRE>The relative match of the target term with respect to the mapped text item.
     */
    public void setMatch(Match match) {
		assertMutable();
        this.match = match;
    }

    /**
     * Get the purpose
     * @return Purpose of the mapping e.g. "automated data mining", "billing", "interoperability".
     */
    public DvCodedText getPurpose() {
        return purpose;
    }

    /**
     * Set the purpose
     * @param purpose Purpose of the mapping e.g. "automated data mining", "billing", "interoperability".
     */
    public void setPurpose(DvCodedText purpose) {
		assertMutable();
        this.purpose = purpose;
    }

    /**
     * Get the target
     * @return The target term of the mapping.
     */
    public CodePhrase getTarget() {
        return target;
    }

    /**
     * Set the target
     * @param target The target term of the mapping.
     */
    public void setTarget(CodePhrase target) {
		assertMutable();
        this.target = target;
    }
    
    /**
     * The mapping is to an equivalent term.
     *
     * @return is equivalent
     */
    public boolean equivalent() {
        return Match.EQUIVALENT.equals(match);
    }

    /**
     * The mapping is to a broader term.
     *
     * @return true if broader
     */
    public boolean broader() {
        return Match.BROADER.equals(match);
    }

    /**
     * The mapping is to a narrower term.
     *
     * @return true if narrower
     */
    public boolean narrower() {
        return Match.NARROWER.equals(match);
    }

    /**
     * The kind of mapping is unknown
     *
     * @return true if unknown
     */
    public boolean unkonwn() {
        return Match.UNKNOWN.equals(match);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
        return "[" + match + "]" + target;
    }
    

}
