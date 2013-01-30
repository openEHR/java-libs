
package br.com.zilics.archetypes.models.rm.datatypes.encapsulated;

import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;
import br.com.zilics.archetypes.models.rm.datatypes.uri.DvURI;

/**
 * A specialisation of {@link DvEncapsulated} for audiovisual and biosignal types.
 * Includes further metadata relating to multimedia types which are not
 * applicable to other subtypes of {@link DvEncapsulated}.
 *
 * @author Humberto
 */
public class DvMultimedia extends DvEncapsulated {

	private static final long serialVersionUID = -3298442566463859448L;
	private String alternateText;
    private DvURI uri;
    private String data;
    private CodePhrase mediaType;
    private CodePhrase compressionAlgorithm;
    private String integrityCheck;
    private CodePhrase integrityCheckAlgorithm;
    private DvMultimedia thumbnail;

    /**
     * Get the alternateText
     * @return Text to display in lieu of multimedia display/replay.
     */
    public String getAlternateText() {
        return alternateText;
    }

    /**
     * Set the alternateText
     * @param alternateText Text to display in lieu of multimedia display/replay.
     */
    public void setAlternateText(String alternateText) {
		assertMutable();
        this.alternateText = alternateText;
    }

    /**
     * Get the uri
     * @return URI reference to electronic information stored outside the record as a
     * file, database entry, etc, if suplied as reference.
     */
    public DvURI getUri() {
        return uri;
    }

    /**
     * Set the uri
     * @param uri URI reference to electronic information stored outside the record as a
     * file, database entry, etc, if suplied as reference.
     */
    public void setUri(DvURI uri) {
		assertMutable();
        this.uri = uri;
    }

    /**
     * Get the data
     * @return The actual data found at <I>uri</I>, if supplied inline.
     */
    public String getData() {
        return data;
    }

    /**
     * Set the data
     * @param data The actual data found at <I>uri</I>, if supplied inline.
     */
    public void setData(String data) {
		assertMutable();
        this.data = data;
    }

    /**
     * Get the mediaType
     * @return Data media type coded from <I>open</I>EHR code set "media types".
     */
    public CodePhrase getMediaType() {
        return mediaType;
    }

    /**
     * Set the mediaType
     * @param mediaType Data media type coded from <I>open</I>EHR code set "media types".
     */
    public void setMediaType(CodePhrase mediaType) {
		assertMutable();
        this.mediaType = mediaType;
    }

    /**
     * Get the compressionAlgorithm
     * @return Compression type, a coded value from the <I>open</I>EHR "Integrity check" code set.
     * Void means no compression.
     */
    public CodePhrase getCompressionAlgorithm() {
        return compressionAlgorithm;
    }

    /**
     * Set the compressionAlgorithm
     * @param compressionAlgorithm Compression type, a coded value from the <I>open</I>EHR "Integrity check" code set.
     * Void means no compression.
     */
    public void setCompressionAlgorithm(CodePhrase compressionAlgorithm) {
		assertMutable();
        this.compressionAlgorithm = compressionAlgorithm;
    }

    /**
     * Get the integrityCheck
     * @return Binary cryptographic integrity checksum.
     */
    public String getIntegrityCheck() {
        return integrityCheck;
    }

    /**
     * Set the integrityCheck
     * @param integrityCheck Binary cryptographic integrity checksum.
     */
    public void setIntegrityCheck(String integrityCheck) {
		assertMutable();
        this.integrityCheck = integrityCheck;
    }

    /**
     * Get the integrityCheckAlgorithm
     * @return Type of integrity check, a coded value from the <I>open</I>EHR "Integrity check" code set.
     */
    public CodePhrase getIntegrityCheckAlgorithm() {
        return integrityCheckAlgorithm;
    }

    /**
     * Set the integrityCheckAlgorithm
     * @param integrityCheckAlgorithm Type of integrity check, a coded value from the <I>open</I>EHR "Integrity check" code set.
     */
    public void setIntegrityCheckAlgorithm(CodePhrase integrityCheckAlgorithm) {
		assertMutable();
        this.integrityCheckAlgorithm = integrityCheckAlgorithm;
    }

    public DvMultimedia getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(DvMultimedia thumbnail) {
		assertMutable();
        this.thumbnail = thumbnail;
    }

    /**
     * Customized toString() for the Composition Summary list format.
     * @return the Multimedia file alternateText
     */
    @Override
	public String toString(){
    	return this.alternateText;
    }
}
