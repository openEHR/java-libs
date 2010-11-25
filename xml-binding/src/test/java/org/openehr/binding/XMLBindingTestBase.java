package org.openehr.binding;

import java.io.InputStream;

import com.thoughtworks.xstream.XStream;

import junit.framework.TestCase;

public class XMLBindingTestBase extends TestCase {
	
	public void setUp() throws Exception {
		binding = new XMLBinding();
	}
	
	protected InputStream fromClasspath(String filename) throws Exception {
		return this.getClass().getClassLoader().getResourceAsStream(filename);
	}
	
	protected String toXML(Object obj) throws Exception {
		XStream xstream = new XStream();
		String xml = xstream.toXML(obj);
		return xml;
	}
	
	protected void printXML(Object obj) throws Exception {
		System.out.println(toXML(obj));
	}
	
	protected XMLBinding binding;
}
