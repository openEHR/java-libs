package org.openehr.am.template;

import java.io.InputStream;


import openEHR.v1.template.TEMPLATE;


import junit.framework.TestCase;

public class TemplateTestBase extends TestCase {
	
	TemplateTestBase() {
		try {
		
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setUp() throws Exception {
		parser = new OETParser();
		
	}
	
	public void tearDown() throws Exception {
		parser = null;		
		template = null;
	}
	
	
	protected TEMPLATE loadTemplate(String id) throws Exception {
		template = parser.parseTemplate(fromClasspath("templates/" + id)).getTemplate();
		return template;
	}
	
	protected InputStream fromClasspath(String filename) throws Exception {
		return this.getClass().getClassLoader().getResourceAsStream(filename);
	}	
	
	
	protected TEMPLATE template;
	protected OETParser parser;	
	
}