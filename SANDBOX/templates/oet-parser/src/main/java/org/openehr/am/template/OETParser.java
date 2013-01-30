package org.openehr.am.template;

import java.io.InputStream;

import openEHR.v1.template.TemplateDocument;

public class OETParser {
	
	public void OETParser() {
		
	}

	public TemplateDocument parseTemplate(InputStream input) throws Exception {
		return TemplateDocument.Factory.parse(input);
	}


}