
package br.com.zilics.archetypes.models.rm.support.terminology;

import java.util.Set;

import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;

/**
 * Defines an object providing proxy access to a code set.
 * @author Humberto
 * @author Fabiane Bizinella Nardon (ZILICS)
 */
public interface CodeSetAccess {

    /**
     * External identifier of this code set
     *
     * @return ID not null or empty
     */
    public String id();

    /**
     * Return all codes known in this code set
     *
     * @return Set of DvCodePhrase
     */
    public Set<CodePhrase> allCodes();

    /**
     * True if code set knows about aCode.
     *
     * @param aCode the code.
     * @return True if code set knows about aCode.
     */
    public boolean hasCode(CodePhrase aCode);

    /**
     * True if code set knows about aLang.
     *
     * @param aLang the language
     * @return True if code set knows about aLang.
     */
    public boolean hasLang(CodePhrase aLang);
}
