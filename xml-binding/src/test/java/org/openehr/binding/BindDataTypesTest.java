package org.openehr.binding;

import java.math.BigInteger;

import org.openehr.rm.datatypes.quantity.DvProportion;
import org.openehr.rm.datatypes.quantity.ProportionKind;
import org.openehr.schemas.v1.DVPROPORTION;

public class BindDataTypesTest extends XMLBindingTestBase {
	
	public void testBindDvProportionToXML() throws Exception {
		DvProportion bp = new DvProportion(0.5, 1.0, ProportionKind.RATIO, null);
		Object obj = binding.bindToXML(bp);
		
		assertTrue("XML class wrong", obj instanceof DVPROPORTION);	
	}	
	
	public void testBindXMLDvProportionToRM() throws Exception {
		DVPROPORTION xobj = DVPROPORTION.Factory.parse(
				fromClasspath("dv_proportion.xml"));
		
		assertTrue("expected dv_proportion, but got: " 
				+ (xobj == null ? null : xobj.getClass()),
				xobj instanceof DVPROPORTION);
		
		DVPROPORTION prop = (DVPROPORTION) xobj;		
		assertEquals("unexpected proportion.numerator", 0.5f, 
				prop.getNumerator());
		assertEquals("unexpected proportion.denominator", 1.0f, 
				prop.getDenominator());
		assertEquals("unexpected proportion.type", BigInteger.valueOf(0), 
				prop.getType());
		assertEquals("unexpected proportion.precision", 1, 
				prop.getPrecision());
		
		Object rmObj = binding.bindToRM(prop);
		assertTrue("expected dv_proportion", rmObj instanceof DvProportion);
	}
}