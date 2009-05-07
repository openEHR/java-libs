package se.acode.openehr.parser;

import junit.framework.TestCase;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.text.SimpleDateFormat;

import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.primitive.*;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.datatypes.quantity.datetime.*;

/**
 * ADLParser testing config and common logic
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ParserTestBase extends TestCase {

    public ParserTestBase(String test) {
        super(test);
    }
    
    public ParserTestBase(){    	
    }
    
    protected InputStream loadFromClasspath(String adl) throws Exception {
    	return this.getClass().getClassLoader().getResourceAsStream(adl);
    }

    // assert CComplexObject object has expected values
    void assertCComplexObject(CComplexObject obj, String rmTypeName,
                              String nodeID, Interval occurrences,
                              int attributes) throws Exception {
        assertCObject(obj, rmTypeName, nodeID, occurrences);
        assertEquals("attributes.size", attributes, obj.getAttributes().size());
    }

    // assert CObject has expected valuess
    void assertCObject(CObject obj, String rmTypeName, String nodeID,
                       Interval occurrences) throws Exception {
        assertEquals("rmTypeName", rmTypeName, obj.getRmTypeName());
        assertEquals("nodeID", nodeID, obj.getNodeID());
        assertEquals("occurrences", occurrences, obj.getOccurrences());
    }

    // assert CAttribute has expected values
    void assertCAttribute(CAttribute attr, String rmAttributeName,
                          CAttribute.Existence existence,
                          Cardinality cardinality, int children)
            throws Exception {
        assertEquals("rmAttributeName", rmAttributeName,
                attr.getRmAttributeName());
        assertEquals("existence", existence, attr.getExistence());
        if(attr instanceof CMultipleAttribute) {
            CMultipleAttribute mattr = (CMultipleAttribute) attr;
            assertEquals("cardinality", cardinality, mattr.getCardinality());
        }
        assertEquals("children.size", children, attr.getChildren().size());
    }

    // assert CAttribute has expected values
    void assertCAttribute(CAttribute attr, String rmAttributeName,
                          int children) throws Exception {
        assertCAttribute(attr, rmAttributeName, CAttribute.Existence.REQUIRED,
                null, children);
    }

    // assertion on primitive types
    void assertCBoolean(Object obj, boolean trueValid, boolean falseValid,
    		boolean assumed, boolean hasAssumed)
            throws Exception {
        CBoolean b = (CBoolean) fetchFirst(obj);
        assertEquals("trueValid", trueValid, b.isTrueValid());
        assertEquals("falseValid", falseValid, b.isFalseValid());
        assertEquals("assumed value", assumed, b.assumedValue().booleanValue());
        assertEquals("has assumed value", hasAssumed, b.hasAssumedValue());        
    }

    void assertCInteger(Object obj, Interval interval, int[] values, 
    		Integer assumed) throws Exception {
        CInteger c = (CInteger) fetchFirst(obj);
        assertEquals("interval", interval, c.getInterval());
        assertEquals("list", intSet(values), c.getList());
        assertEquals("unexpected assumed value", assumed, c.assumedValue());
    }

    void assertCReal(Object obj, Interval interval, double[] values, 
    		Double assumed) throws Exception {
        CReal c = (CReal) fetchFirst(obj);
        assertEquals("interval", interval, c.getInterval());
        assertEquals("list", doubleSet(values), c.getList());
        assertEquals("unexpected assumed value", assumed, c.assumedValue());
    }

    void assertCDate(Object obj, String pattern, Interval interval,
                     String[] values, String assumed)
            throws Exception {
        CDate c = (CDate) fetchFirst(obj);
        assertEquals("pattern", pattern, c.getPattern());
        assertEquals("interval", interval, c.getInterval());
        assertEquals("list", dateSet(values), c.getList());
        assertEquals("assumed value", 
        		assumed == null? null :new DvDate(assumed), c.assumedValue());
    }

    void assertCDateTime(Object obj, String pattern, Interval interval,
                         String[] values, String assumed)
    	throws Exception {
        CDateTime c = (CDateTime) fetchFirst(obj);
        assertEquals("pattern", pattern, c.getPattern());
        assertEquals("interval", interval, c.getInterval());
        assertEquals("list", dateTimeSet(values), c.getList());
        assertEquals("assumed value", 
        		assumed == null? null :new DvDateTime(assumed), c.assumedValue());
    }
    
    // without assumed value
    void assertCDateTime(Object obj, String pattern, Interval interval,
            String[] values) throws Exception {
    	assertCDateTime(obj, pattern, interval, values, null);
    }


    void assertCTime(Object obj, String pattern, Interval interval,
                     String[] values, String assumed) 
    		throws Exception {
        CTime c = (CTime) fetchFirst(obj);
        assertEquals("pattern", pattern, c.getPattern());
        assertEquals("interval", interval, c.getInterval());
        assertEquals("list", timeSet(values), c.getList());
        assertEquals("assumed value", 
        		assumed == null? null :new DvTime(assumed), c.assumedValue());
    }
    
    // without assumed value
    void assertCTime(Object obj, String pattern, Interval interval,
            String[] values) throws Exception {
    	assertCTime(obj, pattern, interval, values, null);
    }   
    
    // full assertion with CDuration
    void assertCDuration(Object obj, String value, Interval interval, 
    		String assumed, String pattern) throws Exception {    
        CDuration c = (CDuration) ((CPrimitiveObject) obj).getItem();
        assertEquals("list", value == null ? null : DvDuration.getInstance(value),
                c.getValue());
        assertEquals("interval", interval, c.getInterval());
        assertEquals("assumed value", 
        		assumed == null? null 
        				: DvDuration.getInstance(assumed), c.assumedValue());
        assertEquals("pattern wrong", pattern, c.getPattern());
    }
    
    // without pattern
    void assertCDuration(Object obj, String value, Interval interval, 
    		String assumed) throws Exception {
    	assertCDuration(obj, value, interval, assumed, null);
    }
    
    // without assumed value, pattern
    void assertCDuration(Object obj, String value, Interval interval)
    		throws Exception {
    	assertCDuration(obj, value, interval, null);
    }
    
    // fetch the first CPrimitive from the CAttribute
    CPrimitive fetchFirst(Object obj) {
        return ( (CPrimitiveObject) ( (CAttribute) obj ).getChildren()
                .get(0) ).getItem();
    }

    void assertCString(Object obj, String pattern, String[] values, 
    		String assumedValue) {
        CString c = (CString) ( (CPrimitiveObject) ( (CAttribute) obj ).getChildren()
                .get(0) ).getItem();
        if (pattern == null) {
            assertTrue("pattern null", c.getPattern() == null);
        } else {
            assertEquals("pattern", pattern, c.getPattern());
        }
        assertEquals("list", values == null
                ? null : Arrays.asList(values), c.getList());
        assertEquals("unexpected CString assumed value", assumedValue,
        		c.assumedValue());
    }

    void assertDateEquals(List set, String[] dates, String pattern)
            throws Exception {
        if (dates == null) {
            assertEquals("set not empty", 0, set.size());
            return;
        }
        List setFromArray = new ArrayList();
        for (int i = 0; i < dates.length; i++) {
            setFromArray.add(new SimpleDateFormat(pattern).parse(dates[ i ]));
        }
        assertTrue("set not equals array, expected: " + setFromArray
                + ", actual: " + set, set.equals(setFromArray));
    }

    // methods for coversion of data types
    List intSet(int[] values) {
        if (values == null) {
            return null;
        }
        List set = new ArrayList();
        for (int i = 0; i < values.length; i++) {
            set.add(new Integer(values[ i ]));
        }
        return set;
    }

    List doubleSet(double[] values) {
        if (values == null) {
            return null;
        }
        List set = new ArrayList();
        for (int i = 0; i < values.length; i++) {
            set.add(new Double(values[ i ]));
        }
        return set;
    }

    // set of DvDate
    List dateSet(String[] values) throws Exception {
        if (values == null) {
            return null;
        }
        List set = new ArrayList();
        for (int i = 0; i < values.length; i++) {
            set.add(date(values[ i ]));
        }
        return set;
    }

    // set of DvDateTime
    List dateTimeSet(String[] values) throws Exception {
        if (values == null) {
            return null;
        }
        List set = new ArrayList();
        for (int i = 0; i < values.length; i++) {
            set.add(dateTime(values[ i ]));
        }
        return set;
    }

    // set of DvTime
    List timeSet(String[] values) throws Exception {
        if (values == null) {
            return null;
        }
        List set = new ArrayList();
        for (int i = 0; i < values.length; i++) {
            set.add(time(values[ i ]));
        }
        return set;
    }

    // convert with default pattern
    DvDateTime dateTime(String value) throws Exception {
        return new DvDateTime(value);
    }

    DvTime time(String value) throws Exception {
        return new DvTime(value);
    }

    DvDate date(String value) throws Exception {
        return new DvDate(value);
    }

    Interval interval(int low, int up) {
        return new Interval(new Integer(low), new Integer(up));
    }

    /* fields */
    static protected File dir = new File("res" + File.separator + "test");
}
