
package br.com.zilics.archetypes.models.rm.support.terminology;

import java.util.Set;

import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;

/**
 * Defines an object providing proxy access to a terminology.
 *
 * <p>
 * This interface is implemented as defined by the OpenEHR standard.
 * For more information, see http://www.openehr.org.
 * </p>
 * <p>
 * Some of the methods are not part of the specification, but extensions
 * to that.
 * </p>
 *
 * @author Humberto
 * @author Fabiane Bizinella Nardon (Zilics)
 * @version $Id: TerminologyAccess.java 56947 2008-04-28 20:01:14Z humberto.naves $
 */
public interface TerminologyAccess {

    /**
     * Returns identification of this terminology
     * @return ID not null or empty
     */
    public String id();

    /**
     * Returns all codes known in this terminology
     * @return Set of DvCodePhrase
     */
    public Set<CodePhrase> allCodes();

    /**
     * Returns all codes under grouper groupID of this terminology
     * @param groupID the group Id
     * @return Set of CodePhrase for given group ID, empty set returned if not found
     * @throws IllegalArgumentException if groupID null or empty
     */
    public Set<CodePhrase> codesForGroupID(String groupID) throws IllegalArgumentException;

    /**
     * Return all codes under grouper whose group name in "language" is the parameter
     * "name", from this terminology.
     * @param name the name of the group.
     * @param language the language.
     * @return Set of CodePhrase for given group name, empty set returned if not found
     * @throws IllegalArgumentException if name,language null or empty
     */
    public Set<CodePhrase> codesForGroupName(String name, String language) throws IllegalArgumentException;

    /**
     * Returns all rubric of given code and language.
     * The rubric is the description assciated with a code.
     * @param code the code.
     * @param language the language
     * @return rubric of given code and language or null if not found
     * @throws IllegalArgumentException  if code,language null or empty
     */
    public String rubricForCode(String code, String language) throws IllegalArgumentException;

    /**
     * True if aCode is known in group groupID in the terminology
     * @param groupID the group id.
     * @param aCode the code.
     * @throws java.lang.IllegalArgumentException if groupID or code are null of empty.
     * @return True if aCode is known in group groupID in the terminology
     */
    public boolean hasCodeForGroupID(String groupID, CodePhrase aCode) throws IllegalArgumentException;

}
