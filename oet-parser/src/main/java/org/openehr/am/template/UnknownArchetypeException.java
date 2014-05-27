package org.openehr.am.template;

/**
 * User: Iago.Corbal
 * Date: 2014-05-27
 * Time: 14:40
 */
public class UnknownArchetypeException extends FlatteningException {

    private String archetypeId;

    public UnknownArchetypeException(String archetypeId) {
        super("Unknown archetype: "+archetypeId);
        this.archetypeId = archetypeId;
    }

    public String getArchetypeId() {
        return archetypeId;
    }
}
