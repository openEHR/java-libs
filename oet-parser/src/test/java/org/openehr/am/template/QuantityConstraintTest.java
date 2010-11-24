package org.openehr.am.template;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.rm.support.basic.Interval;

import openEHR.v1.template.QuantityConstraint;
import openEHR.v1.template.QuantityUnitConstraint;
import openEHR.v1.template.Statement;

public class QuantityConstraintTest extends TemplateTestBase {
	
	public void setUp() throws Exception {		
		super.setUp();		
		archetype = loadArchetype(
			"openEHR-EHR-ITEM_TREE.medication_quantity_test.v1.adl");
		
		constraint = archetype.node(PATH);
		ccobj = (CComplexObject) constraint;		
		rule = Statement.Factory.newInstance();
		rule.setPath(PATH);		
	}
	
	public void tearDown() {
		constraint = null;
		flattened = null;
		archetype = null;
	}
	
	public void testSetQuantityConstraintWithMagnitudeAndUnits() throws Exception {
		QuantityConstraint qc = QuantityConstraint.Factory.newInstance();
		QuantityUnitConstraint quc = qc.addNewUnitMagnitude();
		quc.setUnit("/min");
		quc.setMaxMagnitude(300);
		quc.setMinMagnitude(10);
		quc.setIncludesMaximum(true);
		quc.setIncludesMinimum(true);
		rule.setConstraint(qc);
		
		flattener.applyValueConstraint(archetype, ccobj, rule);
		archetype.reloadNodeMaps();
		
		constraint = archetype.node(PATH + "/value");			
		CDvQuantityItem item = new CDvQuantityItem(
				new Interval<Double>(Double.valueOf(10.0), 
						Double.valueOf(300)), "/min");		
		assertCDvQuantityWithSingleItem(constraint, item);	
	}
	
	public void testSetQuantityConstraintWithIncludedUnits() throws Exception {
		String[] units = { "mmol/L" };
		QuantityConstraint qc = QuantityConstraint.Factory.newInstance();
		qc.setIncludedUnitsArray(units);
		rule.setConstraint(qc);
		
		flattener.applyValueConstraint(archetype, ccobj, rule);
		archetype.reloadNodeMaps();
		
		constraint = archetype.node(PATH + "/value");			
		CDvQuantityItem item = new CDvQuantityItem(units[0]);		
		assertCDvQuantityWithSingleItem(constraint, item);	
	}
	
	public void testSetQuantityConstraintWithIncludedUnitsOnTemplate() throws Exception {
		flattenTemplate("test_quantity_constraint_included_units.oet");
		constraint = flattened.node(PATH + "/value");			
		CDvQuantityItem item = new CDvQuantityItem("mmol/L");		
		assertCDvQuantityWithSingleItem(constraint, item);	
	}
	
	public void testIncludedUnitsWithEmptyCDvQuantity() throws Exception {
		flattenTemplate("test_quantity_constraint_included_units3.oet");
		String path = "/data[at0001]/items[at0004]";
		ArchetypeConstraint ac = flattened.node(path + "/value");			
		CDvQuantityItem item = new CDvQuantityItem("mg/day");		
		assertCDvQuantityWithSingleItem(ac, item);	
	}
	
	public void testSetQuantityConstraintWithMagnitudeAndUnitsOnTemplate() throws Exception {
		flattenTemplate("test_quantity_constraint_magnitude.oet");
		constraint = flattened.node(PATH + "/value");	
		CDvQuantityItem item = new CDvQuantityItem(
				new Interval<Double>(10.0, 300.0), "/min");		
		assertCDvQuantityWithSingleItem(constraint, item);	
	}
	
	public void testSetQuantityConstraintWithExcludedUnitsOnTemplate() throws Exception {
		flattenTemplate("test_quantity_constraint_excluded_units.oet");
		ArchetypeConstraint ac = flattened.node(PATH + "/value");			
		CDvQuantityItem item = new CDvQuantityItem(
				new Interval<Double>(0.0, 1000.0), "cm");		
		assertCDvQuantityWithSingleItem(ac, item);	
	}
	
	public void testSetQuantityConstraintWithMixedConstraints() throws Exception {
		flattenTemplate("test_quantity_constraint_mixed.oet");
		String path = "/items[openEHR-EHR-OBSERVATION.lab_test.v1 and " +
				"name/value='B-HB']/data[at0001]/events[at0002]/data[at0003]" +
				"/items[at0078]/value";
		ArchetypeConstraint ac = flattened.node(path);
		Interval<Double> magnitude = new Interval<Double>(20.0, 300.0);
		CDvQuantityItem item = new CDvQuantityItem(magnitude, "g/L");		
		assertCDvQuantityWithSingleItem(ac, item);	
	}
	
	private static final String PATH = "/items[at0001]";
	private Statement rule;
	private ArchetypeConstraint constraint;
	private CComplexObject ccobj;
	
}
