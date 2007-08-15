package org.openehr.ehrdemo;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.sun.org.apache.xml.internal.utils.DefaultErrorHandler;

public class XMLValidator {
	public static void main(String[] args) throws Exception {
		validate();
	}
	
	private static void validate() throws Exception {
//		 parse an XML document into a DOM tree
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(false);
		
		
	    DocumentBuilder parser = docFactory.newDocumentBuilder();
	    Document document = parser.parse(new File("Composition.xml"));

	    // create a SchemaFactory capable of understanding WXS schemas
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    
	    
	    factory.setErrorHandler(new ErrorHandler() {
		        public void fatalError( SAXParseException e ) throws SAXException {
		        	System.out.print("error in schema loading: " + e.getMessage());
		        	throw e;
		        }
		        public void error( SAXParseException e ) throws SAXException {
		        	System.out.print("error in schema loading: " + e.getMessage());
		            throw e;
		        }
		        public void warning( SAXParseException e ) throws SAXException {
		        	System.out.print("warning in schema loading: " + e.getMessage());
		        }
		    }
	    );

	    // load a WXS schema, represented by a Schema instance
	    //File schemaPath = new File("src\\main\\xsd\\Composition.xsd");
	    String[] schemaList = {
	    		"Composition.xsd", "Content.xsd", "Structure.xsd", 
	    		"BaseTypes.xsd"
	    };
	    Source[] schemaFiles = new Source[schemaList.length];
	    for(int i = 0; i < schemaFiles.length; i++) {
	    	File file = new File(schemaList[i]);
	    	schemaFiles[i] = new StreamSource(file);
 	    }	    
	    Schema schema = factory.newSchema(schemaFiles);

	    // create a Validator instance, which can be used to validate an instance document
	    Validator validator = schema.newValidator();

	    // validate the DOM tree
	    validator.validate(new DOMSource(document));
	    
	}
}
