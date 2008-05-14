package org.openehr.build;

import java.util.HashMap;
import java.util.Map;

import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.TerminologyID;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

public class CommonSupportBuildTest extends BuildTestBase {
	
	public CommonSupportBuildTest() throws Exception {
		builder = new RMObjectBuilder();
		ms =  SimpleMeasurementService.getInstance();
		ts = SimpleTerminologyService.getInstance();
	}
	
	public void setUp() {
		valueMap = new HashMap<String, Object>();
	}
	
	public void testBuildPartySelf() throws Exception {
		assertBuildRMClass(PartySelf.class);
	}
	
	public void testBuildPartySelfWithUpperCaseUnderscoreClassName() throws Exception {
		rmObj = builder.construct("PARTY_SELF", valueMap);
		assertBuildRMClass(PartySelf.class);
	}
	
	public void testBuildTerminologyID() throws Exception {
		valueMap.put("value", "openehr(1.0)");
		assertBuildRMClass(TerminologyID.class);
	}
	
	public void testBuildArchetypeID() throws Exception {
		valueMap.put("value", "openEHR-EHR-OBSERVATION.blood_pressure.v1");
		assertBuildRMClass(ArchetypeID.class);
	}
	
	public void testBuildArchetyped() throws Exception {
		ArchetypeID archetypeId = new ArchetypeID(
				"openEHR-EHR-OBSERVATION.blood_pressure.v1");
		valueMap.put("archetypeId", archetypeId);
		valueMap.put("rmVersion", "1.0.1");
		assertBuildRMClass(Archetyped.class);
	}
	
	private void assertBuildRMClass(Class rmClass) throws Exception {
		rmObj = builder.construct(rmClass.getSimpleName(), valueMap);
		assertTrue("failed to build " + rmClass, rmClass.isInstance(rmObj));
	}
	
	private Map<String, Object> valueMap;
	private RMObject rmObj;
	private RMObjectBuilder builder;
	private MeasurementService ms;
	private TerminologyService ts;
}
