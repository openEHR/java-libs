/*
 * Copyright (C) 2005 Acode HB, Sweden.
 * All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/gpl.txt
 *
 */

package se.acode.openehr.parser;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.StandardToStringStyle;

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
