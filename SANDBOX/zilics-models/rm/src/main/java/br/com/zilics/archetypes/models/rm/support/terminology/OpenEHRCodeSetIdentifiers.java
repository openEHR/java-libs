
package br.com.zilics.archetypes.models.rm.support.terminology;

/**
 *
 * List of identifiers for code sets in the openEHR terminology.
 *
 * @author Humberto
 */
public enum OpenEHRCodeSetIdentifiers {

    CHARACTER_SETS ("character sets"),
    COMPRESSION_ALGORITHMS ("compression algorithms"),
    COUNTRIES ("countries"),
    INTEGRITY_CHECK_ALGORITHMS ("integrity check algorithms"),
    LANGUAGES ("languages"),
    MEDIA_TYPES ("media types"),
    NORMAL_STATUSES ("normal statuses");

    private final String codeSetName;

    private OpenEHRCodeSetIdentifiers(String codeSetName) {
        this.codeSetName = codeSetName;
    }

    public String codeSetName() {
        return codeSetName;
    }

}
