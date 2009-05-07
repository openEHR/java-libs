/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Java ADL Parser"
 * keywords:    "binding"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (C) 2005,2006,2007 ACODE HB, Sweden"
 * copyright:   "Copyright (C) 2008,2009 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package se.acode.openehr.parser;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Super class of all parsed archetype items. It provides basic functions for
 * all parsed items.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Parsed {

    public String toString() {

        return ToStringBuilder.reflectionToString(this, style);
    }

    private static final ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
}
