package org.openehr.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.text.DvText;

public class BuildDvCountTest extends BuildTestBase {
	
	public void setUp() throws Exception {
		super.setUp();
		type = "DvCount";
		values = new HashMap<String, Object>();
	}
	
	public void tearDown() throws Exception {
		super.tearDown();
		obj = null;
	}
	
	public void testBuildDvCountWithOtherReferenceRanges() throws Exception {     
    	Map<String, Object> values = new HashMap<String, Object>();
        DvText normal = new DvText(ReferenceRange.NORMAL, lang, charset, ts);
        DvCount lower = new DvCount(1);
        DvCount upper = new DvCount(10);
        ReferenceRange<DvCount> normalRange = new ReferenceRange<DvCount>(
                normal, new DvInterval<DvCount>(lower, upper));
        List<ReferenceRange<DvCount>> otherReferenceRanges =
                new ArrayList<ReferenceRange<DvCount>>();
        otherReferenceRanges.add(normalRange);
        values.put("otherReferenceRanges", otherReferenceRanges);
        values.put("magnitude", new Integer(5));

        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvCount);
        DvCount count = (DvCount) obj;
        assertEquals("magnitude", 5, count.getMagnitude().intValue());
        assertEquals("otherReferenceRanges", otherReferenceRanges,
                count.getOtherReferenceRanges());
        assertEquals("accuracy", 0.0, count.getAccuracy());
        assertEquals("accuracyPercent", false, count.isAccuracyPercent());        
    }
    
    public void testBuildDvCountWithoutReferenceRanges() throws Exception {
        values.put("magnitude", 3);
        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvCount);
        DvCount count = (DvCount) obj;
        assertEquals("magnitude", 3, count.getMagnitude().intValue());
        assertEquals("referenceRanges", null, count.getOtherReferenceRanges());
        assertEquals("accuracy", 0.0, count.getAccuracy());
        assertEquals("accuracyPercent", false, count.isAccuracyPercent());
    }
    
    public void testBuildDvCountWithGoodStringValue() throws Exception {
        values.put("magnitude", "10");
        obj = builder.construct(type, values);
        assertTrue(obj instanceof DvCount);
    }    
    
    
    public void testBuildDvCountWithBadStringValue() throws Exception {
        values.put("magnitude", "wrong type");
        try {
            obj = builder.construct(type, values);
            fail("attribute format exception should be thrown here");
        } catch (Exception e) {
            assertTrue(e instanceof AttributeFormatException);
        }
    }

    private String type = "DvCount";
    private Map<String, Object> values;
    private RMObject obj;
}
