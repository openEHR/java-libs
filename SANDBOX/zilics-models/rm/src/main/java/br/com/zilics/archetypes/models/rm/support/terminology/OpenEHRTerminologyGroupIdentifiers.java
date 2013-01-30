package br.com.zilics.archetypes.models.rm.support.terminology;


/**
 *
 * List of identifiers for groups in the openEHR terminology.
 *
 * @author Humberto
 * @author Fabiane Bizinella Nardon (ZILICS)
 * @version $Id: OpenEHRTerminologyGroupIdentifiers.java 56947 2008-04-28 20:01:14Z humberto.naves $
 */
public enum OpenEHRTerminologyGroupIdentifiers {

    AUDIT_CHANGE_TYPE("audit change type"),
    ATTESTATION_REASON("attestation reason"),
    COMPOSITION_CATEGORY("composition category"),
    EVENT_MATH_FUNCTION("event math function"),
    INSTRUCTION_STATES("instruction states"),
    INSTRUCTION_TRANSITIONS("instruction transitions"),
    NULL_FLAVOURS("null flavours"),PROPERTY("property"),
    PARTICIPATION_FUNCTION("participation function"),
    PARTICIPATION_MODE("participation mode"),
    SUBJECT_RELATIONSHIP("subject relationship"),
    SETTING("setting"),
    TERM_MAPPING_PURPOSE("term mapping purpose"),
    VERSION_LIFECYCLE_STATE("version lifecycle state");

    private final String groupName;

    private OpenEHRTerminologyGroupIdentifiers(String groupName) {
        this.groupName = groupName;
    }

    public String groupName() {
        return groupName;
    }
}
