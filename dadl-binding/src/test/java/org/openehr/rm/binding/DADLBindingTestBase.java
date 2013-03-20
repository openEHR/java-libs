package org.openehr.rm.binding;

import java.io.InputStream;

import org.openehr.am.parser.*;

import junit.framework.TestCase;

public class DADLBindingTestBase extends TestCase {

	public void setUp() throws Exception {
		binding = new DADLBinding();
	}
	
	public void tearDown() throws Exception {
		rmObj = null;
	}
	
	/*
	 * Loads dadl from file and binds data to RM object
	 */
	Object bind(String filename) throws Exception {
		DADLParser parser = new DADLParser(fromClasspath(filename));
		ContentObject contentObj = parser.parse();
		return binding.bind(contentObj);
	}
	
	Object bindString(String dadl) throws Exception {
		DADLParser parser = new DADLParser(dadl);
		ContentObject contentObj = parser.parse();
		return binding.bind(contentObj);
	}
	
	/* 
	 * Loads given resource from classpath
	 * 
	 * @param adl
	 * @return
	 * @throws Exception
	 */
	InputStream fromClasspath(String res) throws Exception {
    	return this.getClass().getClassLoader().getResourceAsStream(res);
    }
	
	protected Object rmObj;
	protected DADLBinding binding;
}
