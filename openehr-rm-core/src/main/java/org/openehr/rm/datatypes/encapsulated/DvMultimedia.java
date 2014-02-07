/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvMultimedia"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/encapsulated/DvMultimedia.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
package org.openehr.rm.datatypes.encapsulated;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.terminology.OpenEHRCodeSetIdentifiers;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * A specialisation of Encapsulated for audiovisual and biosignal types.
 * Includes further metadata relating to multimedia types which are not
 * applicable to other subtypes of Encapsulated.
 * Instances of this class are immutable
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvMultimedia extends DvEncapsulated {

    /**
     * Constructs a Multimedia by specifying all components
     *
     * @param charset
     * @param language
     * @param size                    >=0
     * @param alternateText
     * @param mediaType               not null and a valid code
     * @param compressionAlgorithm    null or a valid code
     * @param integrityCheck          not null if integrityCheckAlgorithm not null
     * @param integrityCheckAlgorithm null or a valid code
     * @param thumbnail
     * @param uri                   not null if data is null
     * @param data                    not null if uri is null
     * @param terminologyService
     * @throws IllegalArgumentException if any invalid argument
     */
	@FullConstructor
    public DvMultimedia(@Attribute (name = "charset") CodePhrase charset, 
						@Attribute (name = "language") CodePhrase language,
						@Attribute (name = "alternateText") String alternateText,
                        @Attribute (name = "mediaType", required = true) CodePhrase mediaType,
                        @Attribute (name = "compressionAlgorithm", required = true) CodePhrase compressionAlgorithm,
                        @Attribute (name = "integrityCheck") byte[] integrityCheck,
                        @Attribute (name = "integrityCheckAlgorithm", required = true) CodePhrase integrityCheckAlgorithm,
                        @Attribute (name = "thumbnail") DvMultimedia thumbnail, 
						@Attribute (name = "uri") DvURI uri,
                        @Attribute (name = "data") byte[] data,
                        @Attribute (name = "terminologyService", system = true) TerminologyService terminologyService) {
        
		super(charset, language, terminologyService);
        
		if (mediaType == null) {
            throw new IllegalArgumentException("null mediaType");
        }
        if (compressionAlgorithm == null) {
            throw new IllegalArgumentException("null compressionAlgorithm");
        }
        if (integrityCheck != null &&
                integrityCheckAlgorithm == null) {
            throw new IllegalArgumentException(
                    "null integrity check algorithm");
        }

        if (!terminologyService.codeSetForId(
        		OpenEHRCodeSetIdentifiers.MEDIA_TYPES).hasCode(mediaType)) {
            throw new IllegalArgumentException(
                    "unknown media types: " + mediaType);
        }
        if (!terminologyService.codeSetForId(
        		OpenEHRCodeSetIdentifiers.COMPRESSION_ALGORITHMS).hasCode(
        				compressionAlgorithm)) {
        	throw new IllegalArgumentException("unknown compression algorithm: "
                    + compressionAlgorithm);
        }
        if (!terminologyService.codeSetForId(
        		OpenEHRCodeSetIdentifiers.INTEGRITY_CHECK_ALGORITHMS).hasCode(
        				integrityCheckAlgorithm)) {
        	throw new IllegalArgumentException(
        			"unknown integrity check algorithm: "
        			+ integrityCheckAlgorithm);
        }
        if (uri == null && data == null) {
            throw new IllegalArgumentException("both uri and ata are null");
        }
        this.alternateText = alternateText;
        this.mediaType = mediaType;
        this.compressionAlgorithm = compressionAlgorithm;
        this.integrityCheck = integrityCheck;
        this.integrityCheckAlgorithm = integrityCheckAlgorithm;
        this.thumbnail = thumbnail;
        this.uri = uri;
        this.data = data;
    }
	
	@Override
	public int getSize() {
		return data == null ? 0 : data.length;
	}

    /**
     * Text to display in lieu of multimedia display/replay
     *
     * @return alternate text, null if unspecified
     */
    public String getAlternateText() {
        return alternateText;
    }

    /**
     * Data media type coded from the IANA MIME types,
     * openEHR "media types" code set, see
     * <blockquote>
     * <a href="http://www.iana.org/assignments/mediatypes">
     * <i>http://www.iana.org/assignments/mediatypes</i></a>
     * </blockquote>
     *
     * @return media type
     */
    public CodePhrase getMediaType() {
        return mediaType;
    }

    /**
     * Compression type, a coded value from the openEHR
     * "compression algorithm" code set
     *
     * @return null if no compression
     */
    public CodePhrase getCompressionAlgorithm() {
        return compressionAlgorithm;
    }

    /**
     * binary cryptographic integrity checksum
     *
     * @return null if no checksum
     */
    public byte[] getIntegrityCheck() {
        return integrityCheck;
    }

    /**
     * Type of integrity check, a coded value from the openEHR
     * "integrity check algorithm" code set.
     *
     * @return null if has no integrity check
     */
    public CodePhrase getIntegrityCheckAlgorithm() {
        return integrityCheckAlgorithm;
    }

    /**
     * The thumbnail for this item, if one exists; mainly for graphics formats.
     *
     * @return thumbnail null if unspecified
     */
    public DvMultimedia getThumbnail() {
        return thumbnail;
    }

    /**
     * URI reference to electronic information stored outside the record
     *
     * @return possibly null if data inline
     */
    public DvURI getUri() {
        return uri;
    }

    /**
     * The actual data found at 'uri', if supplied inline
     *
     * @return possibly null if data external
     */
    public byte[] getData() {
        return data;
    }

    /**
     * True if the data is stored externally to the record,
     * as indicated by uri
     *
     * @return true if external
     */
    public boolean isExternal() {
        return uri != null;
    }

    /**
     * True if the data is stored in expanded form
     *
     * @return true if data inline
     */
    public boolean isInline() {
        return data != null;
    }

    /**
     * True if the data is stored in compressed form
     *
     * @return true if compressed
     */
    public boolean isCompressed() {
        return compressionAlgorithm != null;
    }

    /**
     * True if an integrity check has been computed
     *
     * @return true if has integrity check
     */
    public boolean hasIntegrityCheck() {
        return integrityCheckAlgorithm != null;
    }

    /**
     * string form displayable for humans
     *
     * @return string presentation
     */
    public String toString() {
        return mediaType.toString();
    }

    // POJO start
    private DvMultimedia() {
    }

    private void setAlternateText(String alternateText) {
        this.alternateText = alternateText;
    }

    private void setMediaType(CodePhrase mediaType) {
        this.mediaType = mediaType;
    }

    private void setCompressionAlgorithm(CodePhrase compressionAlgorithm) {
        this.compressionAlgorithm = compressionAlgorithm;
    }

    private void setIntegrityCheck(byte[] integrityCheck) {
        this.integrityCheck = integrityCheck;
    }

    private void setIntegrityCheckAlgorithm(CodePhrase integrityCheckAlgorithm) {
        this.integrityCheckAlgorithm = integrityCheckAlgorithm;
    }

    private void setThumbnail(DvMultimedia thumbnail) {
        this.thumbnail = thumbnail;
    }

    private void setUri(DvURI uri) {
        this.uri = uri;
    }

    private void setData(byte[] data) {
        this.data = data;
    }
    // POJO end

    /* fields */
    private String alternateText;
    private CodePhrase mediaType;
    private CodePhrase compressionAlgorithm;
    private byte[] integrityCheck;
    private CodePhrase integrityCheckAlgorithm;
    private DvMultimedia thumbnail;
    private DvURI uri;
    private byte[] data;	
	@Override
	public String getReferenceModelName() {
		return "DV_MULTIMEDIA";
	}

	@Override
	public String serialise() {
		// TODO Auto-generated method stub
		return null;
	}	
}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is DvMultimedia.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */