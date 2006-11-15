/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DataTypesBuildTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.build;

import java.util.*;

import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.basic.DvBoolean;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvParagraph;
import org.openehr.rm.datatypes.text.DvText;

public class DataTypesBuildTest extends BuildTestBase {
	
	 // test classes from datatypes.basic package
    public void testBuildDvBoolean() throws Exception {
        String type = "DvBoolean";
        Map<String, Object> values = new HashMap<String, Object>();

        // true value
        values.put("value", "true");
        RMObject obj = builder.construct(type, values);
        assertTrue(obj instanceof DvBoolean);
        DvBoolean booleanObj = (DvBoolean) obj;
        assertEquals("value", true, booleanObj.value());

        // false value
        values.clear();
        values.put("value", "false");
        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvBoolean);
        booleanObj = (DvBoolean) obj;
        assertEquals("value", false, booleanObj.value());
    }

    public void testBuildDvState() throws Exception {
        String type = "DvState";
        Map<String, Object> values = new HashMap<String, Object>();
        DvCodedText codedText = new DvCodedText("some text", lang, charset,
                new CodePhrase("test terms", "00001"), ts);
        values.put("value", codedText);
        values.put("terminal", "true");
        RMObject obj = builder.construct(type, values);
        assertTrue(obj instanceof DvState);
        DvState state = (DvState) obj;
        assertEquals("value", codedText, state.getValue());
        assertEquals("terminal", true, state.isTerminal());

        // non-terminal
        values = new HashMap<String, Object>();
        values.put("value", codedText);
        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvState);
        state = (DvState) obj;
        assertEquals("value", codedText, state.getValue());
        assertEquals("terminal", false, state.isTerminal());
    }

    // test classes from datatypes.text package
    public void testBuildDvText() throws Exception {
        String type = "DvText";
        Map<String, Object> values = new HashMap<String, Object>();
        String value = "test text value";
        values.put("value", value);
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvText);
        DvText text = (DvText) obj;
        assertEquals("value", value, text.getValue());
    }

    public void testBuildDvCodeText() throws Exception {
        String type = "DvCodedText";
        Map<String, Object> values = new HashMap<String, Object>();
        String value = "test text value";
        CodePhrase definingCode = new CodePhrase("local", "at0001");

        values.put("value", value);
        values.put("definingCode", definingCode);
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvCodedText);
        DvCodedText text = (DvCodedText) obj;
        assertEquals("value", value, text.getValue());
        assertEquals("definingCode", definingCode, text.getDefiningCode());
    }

    public void testBuildDvParagraph() throws Exception {
        String type = "DvParagraph";
        Map<String, Object> values = new HashMap<String, Object>();
        List<DvText> items = new ArrayList<DvText>();
        CodePhrase definingCode = new CodePhrase("local", "at0001");
        DvText lineOne = new DvText("line one", lang, charset, ts);
        DvText lineTwo = new DvText("line two", lang, charset, ts);
        DvText lineCoded = new DvCodedText("line coded", lang, charset,
                definingCode, ts);
        items.add(lineOne);
        items.add(lineTwo);
        items.add(lineCoded);
        values.put("items", items);
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvParagraph);
        DvParagraph paragraph = (DvParagraph) obj;
        assertEquals("items.size", items.size(), paragraph.getItems().size());
        assertEquals("items", items, paragraph.getItems());
    }

    // test datatypes.quantity classes
    public void testBuildDvCount() throws Exception {
        String type = "DvCount";

        // with referenceRanges
        Map<String, Object> values = new HashMap<String, Object>();
        DvText normal = new DvText(ReferenceRange.NORMAL, lang,
                charset, ts);
        DvCount lower = new DvCount(1);
        DvCount upper = new DvCount(10);
        ReferenceRange<DvCount> normalRange = new ReferenceRange<DvCount>(
                normal, new DvInterval<DvCount>(lower, upper));
        List<ReferenceRange<? extends DvOrdered>> referenceRanges =
                new ArrayList<ReferenceRange<? extends DvOrdered>>();
        referenceRanges.add(normalRange);
        values.put("referenceRanges", referenceRanges);
        values.put("magnitude", new Integer(5));

        RMObject obj = builder.construct(type, values);
        assertTrue(obj instanceof DvCount);
        DvCount count = (DvCount) obj;
        assertEquals("magnitude", 5, count.getMagnitude().intValue());
        assertEquals("referenceRanges", referenceRanges,
                count.getReferenceRanges());
        assertEquals("accuracy", 0.0, count.getAccuracy());
        assertEquals("accuracyPercent", false, count.isAccuracyPercent());

        // without referenceRanges
        values.clear();
        values.put("magnitude", 3);
        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvCount);
        count = (DvCount) obj;
        assertEquals("magnitude", 3, count.getMagnitude().intValue());
        assertEquals("referenceRanges", null, count.getReferenceRanges());
        assertEquals("accuracy", 0.0, count.getAccuracy());
        assertEquals("accuracyPercent", false, count.isAccuracyPercent());

        // with good string value
        values.clear();
        values.put("magnitude", "3");
        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvCount);

        // with bad string value
        values.clear();
        values.put("magnitude", "wrong type");
        try {
            obj = builder.construct(type, values);
            fail("attribute format exception should be thrown here");
        } catch (Exception e) {
            assertTrue(e instanceof AttributeFormatException);
        }
    }

    public void testBuildOrdinal() throws Exception {
        String type = "DvOrdinal";
        Map<String, Object> values = new HashMap<String, Object>();
        DvCodedText symbol = new DvCodedText("test text", lang,
                charset, new CodePhrase("test terms", "00001"),
                ts);
        values.put("symbol", symbol);
        values.put("value", 1);
        RMObject obj = builder.construct(type, values);
        assertTrue(obj instanceof DvOrdinal);
        DvOrdinal ordinal = (DvOrdinal) obj;
        assertEquals("value", 1, ordinal.getValue());
        assertEquals("symbol", symbol, ordinal.getSymbol());
    }

    public void testBuildQuantity() throws Exception {
        String type = "DvQuantity";
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("units", "kg/L");
        values.put("magnitude", new Double(100.0));
        RMObject obj = builder.construct(type, values);
        assertTrue(obj instanceof DvQuantity);
        DvQuantity quantity = (DvQuantity) obj;
        assertEquals("units", "kg/L", quantity.getUnits());
        assertEquals("magnitude", 100.0, quantity.getMagnitude());

        // test with good string value
        values.clear();
        values.put("units", "mg");
        values.put("magnitude", "10.0");
        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvQuantity);

        // test with bad string value
        values.put("units", "mg");
        values.put("magnitude", "wrong type");
        try {
            obj = builder.construct(type, values);
            fail("attribute format exception should be thrown here");
        } catch (Exception e) {
            assertTrue(e instanceof AttributeFormatException);
        }

    }

    // test datatypes.quantity.datetime classes
    public void testBuildDvDate() throws Exception {
        String type = "DvDate";

        // with good string value and no referenceRanges
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("value", "1999-10-20");
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvDate);
        DvDate date = (DvDate) obj;
        assertEquals("year", 1999, date.getYear());
        assertEquals("month", 10, date.getMonth());
        assertEquals("day", 20, date.getDay());

        // with bad string value
        values.clear();
        values.put("value", "bad values");
        try {
            obj = builder.construct(type, values);
            fail("attribute format exception should be thrown here");
        } catch (Exception e) {
            assertTrue(e instanceof AttributeFormatException);
        }

        // with missing required value
        values.clear();
        try {
            obj = builder.construct(type, values);
            fail("attribute missing exception should be thrown here");
        } catch (Exception e) {
            assertTrue(e instanceof AttributeMissingException);
        }
    }

    public void testBuildDvDateTime() throws Exception {
        String type = "DvDateTime";

        // without referenceRanges
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("value", "1999-10-20T18:15:45");
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvDateTime);
        DvDateTime datetime = (DvDateTime) obj;
        assertEquals("year", 1999, datetime.getYear());
        assertEquals("month", 10, datetime.getMonth());
        assertEquals("day", 20, datetime.getDay());
        assertEquals("hour", 18, datetime.getHour());
        assertEquals("minute", 15, datetime.getMinute());
        assertEquals("second", 45, datetime.getSecond());
    }

    public void testBuildDvTime() throws Exception {
        String type = "DvTime";

        // without referenceRanges
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("value", "18:15:45");
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvTime);
        DvTime datetime = (DvTime) obj;
        assertEquals("hour", 18, datetime.getHour());
        assertEquals("minute", 15, datetime.getMinute());
        assertEquals("second", 45, datetime.getSecond());
    }

    public void testBuildDvDuration() throws Exception {
        String type = "DvDuration";

        // without referenceRanges
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("value", "P10DT20H30M40S");
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvDuration);
        DvDuration duration = (DvDuration) obj;
        assertEquals("days", 10, duration.getDays());
        assertEquals("hours", 20, duration.getHours());
        assertEquals("minutes", 30, duration.getMinutes());
        assertEquals("seconds", 40, duration.getSeconds());
        assertEquals("fractionalSeconds", 0.0, duration.getFractionalSeconds());
    }

    // test datatypes.encapsulated classes
    public void testBuildParsable() throws Exception {
        String type = "DvParsable";
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("formalism", "GLIF3");
        values.put("value", "guideline text");
        RMObject obj = builder.construct(type, values);

        assertTrue(obj instanceof DvParsable);
        DvParsable parsable = (DvParsable) obj;
        assertEquals("formalism", "GLIF3", parsable.getFormalism());
        assertEquals("value", "guideline text", parsable.getValue());
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
 *  The Original Code is DataTypesBuildTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2006
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