
package br.com.zilics.archetypes.models.rm.support.terminology;

import java.util.Map;
import java.util.Set;

/**
 * Defines an object providing proxy access to a terminology service.
 *
 * @author Humberto
 */
public interface TerminologyService  {
    /**
     * Get the terminology by name
     * @param name name of the terminology
     * @return an interface to the terminology named name. Allowable names include
     * "openeh", "centc251" any name from are taken from the
     * <a href="http://www.nlm.nih.gov/research/umls/metaa1.html">US NLM UMLS meta-data list</a>
     */
    public TerminologyAccess terminology(String name);

    /**
     * Get the code set by name
     * @param name Name of the code set
     * @return an interface to the code_set identified by the external
     * identifier name (e.g. "ISO_639-1").
     */
    public CodeSetAccess codeSet(String name);

    /**
     * Get the code set by id
     * @param id Id of the code set
     * @return an interface to the code_set identified by the external
     * identifier name (e.g. "ISO_639-1").
     */
    public CodeSetAccess codeSetForId(String id);

    /**
     * Verifies if the terminology is available
     * @param name Terminology name
     * @return True if terminology named name known by this service.
     */
    public boolean hasTerminology(String name);

    /**
     * Verifies if the code set is available
     * @param name Name of the code set
     * @return True if code_set linked to internal name
     * (e.g. "languages") is available.
     */
    public boolean hasCodeSet(String name);

    /**
     * Get the available terminologies
     * @return Set of all terminology identifiers known in the
     * terminology service.
     */
    public Set<String> terminologyIdentifiers();

    /**
     * Get the available code sets
     * @return Set of all code set identifiers known in the terminology service.
     */
    public Set<String> codeSetIdentifiers();

    /**
     * Get the openEHR code sets
     * @return Set of all code sets identifiers for which there is an
     * internal openEHR name; returned as a Hash of ids keyed by internal
     * name.
     */
    public Map<String, String> openEHRCodeSets();
}
