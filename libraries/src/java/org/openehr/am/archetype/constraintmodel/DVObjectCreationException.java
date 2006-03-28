/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DVObjectCreationException"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.am.archetype.constraintmodel;

/**
 * DataValueCreationException
 *
 * @author Rong Chen
 * @version 1.0
 */

public class DVObjectCreationException extends Exception {

    /* constructor */
    private DVObjectCreationException(ErrorType errorType) {
        this.errorType = errorType;
    }

    /**
     * Type of error caused this exception
     *
     * @return error type
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /* member */
    private ErrorType errorType;

    /**
     * Exception caused by bad index
     */
    public static final DVObjectCreationException BAD_INDEX =
            new DVObjectCreationException(ErrorType.BAD_INDEX);

    /**
     * Exception caused by bad format
     */
    public static final DVObjectCreationException BAD_FORMAT =
                new DVObjectCreationException(ErrorType.BAD_FORMAT);

    /**
     * Exception caused by bad value
     */
    public static final DVObjectCreationException BAD_VALUE =
                new DVObjectCreationException(ErrorType.BAD_VALUE);
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
 *  The Original Code is DVObjectCreationException.java
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