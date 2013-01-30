package org.openehr.tutorial;

import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.tutorial.util.TestTerminologyService;

/**
 * Root class of all coding exercises providing common functionality
 * 
 * @author rong.chen
 */
public class ExerciseBase {
	
	/**
	 * Simple implementation of MeasurementService used for 
	 * this coding exercise 
	 */
	protected static MeasurementService measureServ = 
		SimpleMeasurementService.getInstance();
	
	/**
	 * Simple implementation of TerminologyService used for
	 * this coding exercise
	 */
	protected static TerminologyService termServ = 
		TestTerminologyService.getInstance();
}
