/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DataValue"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/basic/DataValue.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.basic;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.*;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract parent type of all concrete data value types
 * 
 * @author Rong Chen
 * @version 1.0
 */
public abstract class DataValue extends RMObject {

    // POJO start
    protected DataValue() {
    }
    // POJO end

    /**
     * String form displayable for humans
     *
     * @return string presentation of this DataValue
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }
    
    public abstract String getReferenceModelName();
    
    /**
     * Serialise the value in string format.
     * 
     * @return
     */
    public abstract String serialise();
    
    /**
     * Parse a serialised dataValue and returns a concrete DataValue class
     * 
     * The format is the following:
     * [REFERENCE_MODEL_CLASS_NAME],[SERIALIZED FORMAT]
     * 
     * @param value
     * @return instance of DataValue
     */
    public static DataValue parseValue(String value) {
    	if(value == null) {
    		throw new IllegalArgumentException("null value");
    	}
    	
    	int i = value.indexOf(",");
    	if(i < 0 || i == value.length()) {
    		throw new IllegalArgumentException("wrong string format");
    	}
    	
    	String rmName = value.substring(0, i);
    	DataValue dv = dataValueMap.get(rmName);
    	
    	if(dv == null) {
    		throw new IllegalArgumentException("unsupported RM class[" + rmName + "]");
    	}
    	
    	String v = value.substring(i + 1).trim();    	
    	return dv.parse(v);
    }
    
    /**
     * Parse a serialised dataValue. Implemented by subclasses.
     * 
     * @param value
     * @return instance of DataValue
     */
    public DataValue parse(String value) {
    	throw new RuntimeException("no implementation");
    }
    
    private final static Map<String, DataValue> dataValueMap;
    
    /*
     * Initiate the mapping between ReferenceModelName and concrete dataValue 
     */
    static {
    	dataValueMap = new HashMap<String, DataValue>();
    	dataValueMap.put(ReferenceModelName.DV_COUNT.getName(), new DvCount(0));
    	dataValueMap.put(ReferenceModelName.DV_BOOLEAN.getName(), new DvBoolean(false));
    	dataValueMap.put(ReferenceModelName.DV_QUANTITY.getName(), new DvQuantity(1));
    	dataValueMap.put(ReferenceModelName.DV_PROPORTION.getName(), new DvProportion(1,1,ProportionKind.FRACTION, 0));
    	dataValueMap.put(ReferenceModelName.DV_TEXT.getName(), new DvText("text"));
    	dataValueMap.put(ReferenceModelName.DV_CODED_TEXT.getName(), new DvCodedText("text", new CodePhrase("tm", "cd")));
    	dataValueMap.put(ReferenceModelName.CODE_PHRASE.getName(), new CodePhrase("tm","cd"));
    	dataValueMap.put(ReferenceModelName.DV_ORDINAL.getName(), new DvOrdinal(0,"text","tm","cd"));
    	dataValueMap.put(ReferenceModelName.DV_DATE_TIME.getName(), new DvDateTime("2001-02-11T00"));
        dataValueMap.put(ReferenceModelName.DV_DATE.getName(), new DvDate("2001-02-11"));
        dataValueMap.put(ReferenceModelName.DV__TIME.getName(), new DvTime("00:00:00.000Z"));
		dataValueMap.put(ReferenceModelName.DV_DURATION.getName(), new DvDuration("P10D"));
		dataValueMap.put(ReferenceModelName.DV_PARSABLE.getName(), new DvParsable("text", "txt"));
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
 *  The Original Code is DataValue.java
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