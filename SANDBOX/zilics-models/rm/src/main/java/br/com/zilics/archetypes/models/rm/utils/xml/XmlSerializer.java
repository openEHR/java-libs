package br.com.zilics.archetypes.models.rm.utils.xml;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.exception.XmlSerializerException;
import br.com.zilics.archetypes.models.rm.utils.introspect.generics.BaseResolver;
import br.com.zilics.archetypes.models.rm.utils.introspect.generics.GenericsResolver;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmFieldData;

import static br.com.zilics.archetypes.models.rm.utils.xml.XmlUtils.*;


/**
 * The serializer for XMLs
 * 
 * @see XmlParser
 * @author Humberto Naves
 *
 */
public final class XmlSerializer {
	private XmlSerializer() {}

	/**
	 * Delegate method for {@link #serializeXml(RMObject, String, boolean)} with prettPrint=true
	 * @param obj the object to serialize
	 * @param rootElementName the name of the root element (or null if you want an automatic name)
	 * @return the string containing the xml
	 * @throws XmlSerializerException Any exception raised during the method
	 */
	public static String serializeXml(RMObject obj, String rootElementName) throws XmlSerializerException {
		return serializeXml(obj, rootElementName, true);
	}
	
	/**
	 * Serialize a {@link RMObject} to string
	 * @param obj the object to serialize
	 * @param rootElementName the name of the root element (or null if you want an automatic name)
	 * @param prettyPrint ident the final string?
	 * @return the XML form of the object
	 * @throws XmlSerializerException Any exception raised during the method
	 */
	public static String serializeXml(RMObject obj, String rootElementName, boolean prettyPrint) throws XmlSerializerException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		serializeXml(obj, rootElementName, baos, prettyPrint);
		try {
			return baos.toString("UTF-8");
		} catch (Throwable t) {
			throw new XmlSerializerException("Could not convert to UTF-8", t);
		}
	}

	/**
	 * Serialize a {@link RMObject} to the output stream
	 * @param obj the object to serialize
	 * @param rootElementName the name of the root element (or null if you want an automatic name)
	 * @param os the output stream
	 * @throws XmlSerializerException Any exception raised during the method
	 */
	public static void serializeXml(RMObject obj, String rootElementName, OutputStream os) throws XmlSerializerException {
		serializeXml(obj, rootElementName, os, true);
	}
	
	/**
	 * Serialize a {@link RMObject} to the output stream
	 * @param obj the object to serialize
	 * @param rootElementName the name of the root element (or null if you want an automatic name)
	 * @param os the output stream
	 * @param prettyPrint ident the final string?
	 * @throws XmlSerializerException Any exception raised during the method
	 */
	public static void serializeXml(RMObject obj, String rootElementName, OutputStream os, boolean prettyPrint) throws XmlSerializerException {
	    XMLOutputter outputter;

	    outputter = new XMLOutputter();
	    if (prettyPrint)
	    	outputter.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));
        
        RmClassData rmClassData = IntrospectorData.getRmClassDataByJavaClass(obj.getClass());

        Element rootElement = new Element(rmClassData.getRmClassName().toLowerCase());
        rootElement.setNamespace(defaultNamespace);
        rootElement.addNamespaceDeclaration(xsiNamespace);
        if (rootElementName != null && !rootElementName.equals(rootElement.getName())) {
        	rootElement.setAttribute("type", rmClassData.getRmClassName(), xsiNamespace);
        	rootElement.setName(rootElementName);
        }

        GenericsResolver resolver;
       	resolver = BaseResolver.makeInstance(rmClassData.getJavaClass());

        serializeElement(obj, resolver, rootElement);
        try {
        	outputter.output(rootElement, os);
        	os.flush();
        } catch (Throwable t) {
        	throw new XmlSerializerException("Error while outputting", t);
        }
	}

	/**
	 * Create a new XML element based on the value with the name of "rmFieldName"
	 * @param value the value (source of the  XML element)
	 * @param rmFieldName the name of the XML element (tag)
	 * @param fieldGenericClass to pass on to subsequent serializeElement
	 * @param parent the parent element
	 * @return the new formed element
	 * @throws XmlSerializerException Any exception raised during the method
	 */
	private static Element newElementFromObject(Object value, String rmFieldName, GenericsResolver fieldGenericClass, Element parent) throws XmlSerializerException {
    	Element child = new Element(rmFieldName, defaultNamespace);
    	parent.addContent(child);
		if (value != null && value instanceof RMObject && value.getClass() != fieldGenericClass.getResolvedClass()) {
			fieldGenericClass = BaseResolver.makeInstance(value.getClass());
			child.setAttribute("type", IntrospectorData.getRmClassDataByJavaClass(fieldGenericClass.getResolvedClass()).getRmClassName(),
					xsiNamespace);
		}

		if (value instanceof Enum) {
			child.setText(value.toString());
		} else if (value instanceof RMObject) {
			serializeElement((RMObject) value, fieldGenericClass, child);
		} else
			child.setText(value.toString());
		return child;
    }

	/**
	 * The main recursive method of the serializer
	 * @param obj the object to serialize
	 * @param resolver the tracking resolver
	 * @param element the current XML element
	 * @throws XmlSerializerException Any exception raised during the method
	 */
    @SuppressWarnings("unchecked")
	private static void serializeElement(RMObject obj, GenericsResolver resolver, Element element) throws XmlSerializerException {
    	RmClassData rmClassData = IntrospectorData.getRmClassDataByJavaClass(resolver.getResolvedClass());
    	for(String rmFieldName : rmClassData.getAllFieldRmNames()) {
    		RmFieldData rmField = rmClassData.getRmFieldByRmName(rmFieldName);
    		GenericsResolver fieldResolver;
    		Object value;

    		
			try {
				value = rmField.getValue(obj);
			} catch (Throwable t) {
				throw new XmlSerializerException("Could not get field value", t);
			}
    		
    		if (value == null) continue;

    		try {
    			// Keep tracking of generics
    			fieldResolver = resolver.resolveField(rmField.getJavaField());
    		} catch(Throwable t) {
    			throw new XmlSerializerException("Can't resolve field!", t);
    		}

    		if (value instanceof Map) {
    			Map<String, Object> map = (Map<String, Object>) value;

    			String mapKey = rmField.getMapKey();

    			GenericsResolver valueType;
        		try {
        			valueType = fieldResolver.resolveTypeParameter("V");
        		} catch(Throwable t) {
        			throw new XmlSerializerException("Can't resolve type parameter!", t);
        		}
    			for(String key : map.keySet()) {
    				Object val = map.get(key);
    				Element child = newElementFromObject(val, rmFieldName, valueType, element);
    				if (rmField.isXmlAttribute())
    					child.setAttribute(mapKey, key);
    			}
    		} else if (value instanceof Collection) {
    			Collection<Object> collection= (Collection<Object>) value;
    			GenericsResolver elementType;
    			try {
    				elementType = fieldResolver.resolveTypeParameter("E");
    			} catch(Throwable t) {
    				throw new XmlSerializerException("Can't resolve type parameter!", t);
    			}
    			for(Object o : collection) {
    				newElementFromObject (o, rmFieldName, elementType, element);
    			}
    		} else if (!(value instanceof RMObject) && rmField.getMapKey() != null) {
    			if (value instanceof Enum)
    				element.setAttribute(rmFieldName, value.toString());
    			else
    				element.setAttribute(rmFieldName, value.toString());
    		} else {
    			newElementFromObject (value, rmFieldName, fieldResolver, element);    			
    		}
    	}
    	
	}
	
	
}
