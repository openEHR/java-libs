package br.com.zilics.archetypes.models.rm.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.exception.XmlParserException;
import br.com.zilics.archetypes.models.rm.utils.introspect.generics.BaseResolver;
import br.com.zilics.archetypes.models.rm.utils.introspect.generics.GenericsResolver;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmFieldData;

import static br.com.zilics.archetypes.models.rm.utils.xml.XmlUtils.*;

/**
 * The new parser/serializer uses the annotations @RmClass, @RmField and @MapItem.
 * The format of the XML is very close to the structure of classes and fields of the Reference Model. For example:
 * <pre>
 * &lt;element xmlns="http://schemas.openehr.org/v1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" archetype_node_id="at0001"&gt;
 *   &lt;value xsi:type="DV_COUNT"&gt;
 *     &lt;magnitude&gt;12&lt;/magnitude&gt;
 *     &lt;accuracy&gt;0.0&lt;/accuracy&gt;
 *     &lt;accuracy_is_percent&gt;false&lt;/accuracy_is_percent&gt;
 *     &lt;magnitude_status&gt;=&lt;/magnitude_status&gt;
 *   &lt;/value&gt;
 *   &lt;name&gt;
 *     &lt;value&gt;some element&lt;/value&gt;
 *   &lt;/name&gt;
 * &lt;/element&gt;
 * </pre>
 * Corresponds to an object obj of type Element such that obj.getValue() is and DvCount and obj.getName().getValue() equals "some element".
 * Generally speaking we have the following rules:
 * <ol><li>The name of the XML tag is the name of the field of the corresponding class of the parent node that contains the object. To be more precise, in the above example, the tag &lt;name&gt; comes from the field "name" (of type DvText) of the class Element.</li>
 * <li>The type of and XML tag can be overriden by the "xsi:type" attribute</li>
 * <li>Multiple fields correponds to multiple nodes in XML having the same tag name.</li></ol>
 * 
 * @author Humberto Naves
 *
 */
public final class XmlParser {
	private XmlParser() {}
	
	/**
	 * Delegate method to {@link #parseXml(InputStream)} creating an input stream
	 * based on the string
	 * @param xml the string form of the xml
	 * @return the parsed object
	 * @throws XmlParserException Any exception raised during the method
	 */
	public static RMObject parseXml(String xml) throws XmlParserException {
		ByteArrayInputStream bais;
		try {
			// All XMLs are UTF-8 encoded
			bais = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		} catch (Throwable t) {
			throw new XmlParserException("Invalid charset", t, null);
		}
		return parseXml(bais);
	}
	
	/**
	 * Parse an input string containing the XML to be parsed
	 * @param is the input stream
	 * @return the parsed object
	 * @throws XmlParserException Any exception raised during the method
	 */
	public static RMObject parseXml(InputStream is) throws XmlParserException {
		//Transform the Input stream into a JDOM document
    	Document document = parseInputStream(is);
    	
    	// Try to infer the type of the root element based on its name
    	String rmClassName = document.getRootElement().getName().toUpperCase();
    	
    	// If it has "xsi:type" attribute, then override the type here
    	if (document.getRootElement().getAttributeValue("type", xsiNamespace) != null)
    		rmClassName = document.getRootElement().getAttributeValue("type", xsiNamespace);
    	
    	
    	// Prepare the introspector for the root element
    	RmClassData rootClassData = IntrospectorData.getRmClassDataByRmClassName(rmClassName);
    	GenericsResolver parent = BaseResolver.makeInstance(rootClassData.getJavaClass());


        RMObject result;
        try {
        	// Make a new instance
			result = (RMObject) rootClassData.newInstance();
		} catch (Throwable t) {
			throw new XmlParserException("Invalid root element: " + rootClassData.getRmClassName(), t, document.getRootElement());
		}
		
		//Now the hard work
        parseElement(result, parent, document.getRootElement());
        
        return result;		
	}
	
	/**
	 * Transform an input stream in a {@link Document} representing the parsed XML.
	 * @param is the input stream
	 * @return the document
	 * @throws XmlParserException Any exception raised during the method
	 */
	private static Document parseInputStream(InputStream is) throws XmlParserException {
    	SAXBuilder builder = new LineNumberSAXBuilder(); // To keep track of the line numbers

    	builder.setValidation(false);
        builder.setExpandEntities(false);
        
    	Document document;
		try {
			document = builder.build(is);
		} catch (Throwable t) {
			throw new XmlParserException("Error while parsing", t, null);
		}
		
		// Check the namespaces
        if (!document.getRootElement().getNamespace().equals(defaultNamespace) ||
                !document.getRootElement().getNamespace("xsi").equals(xsiNamespace))
                throw new XmlParserException("Invalid namespaces", document.getRootElement());
    	
        return document;
	}
	
	/**
	 * Create the objects here (RMObjects, Strings, numbers, booleans, Enums, etc)
	 * @param clazz the class of the instance
	 * @param element the XML element that corresponds to object
	 * @return the instance
	 * @throws XmlParserException raised if can't instantiate that clazz
	 */
    private static Object newInstanceFromElement(Class<?> clazz, Element element) throws XmlParserException {
    	Object value;
		if (clazz == String.class) {
			value = element.getTextNormalize();
		} else if (clazz == Integer.class || clazz == Integer.TYPE) {
			value = Integer.parseInt(element.getTextNormalize());
		} else if (clazz == Long.class || clazz == Long.TYPE) {
			value = Long.parseLong(element.getTextNormalize());
		} else if (clazz == Boolean.class || clazz == Boolean.TYPE) {
			value = Boolean.parseBoolean(element.getTextNormalize());
		} else if (clazz == Double.class || clazz == Double.TYPE) {
			value = Double.parseDouble(element.getTextNormalize());
		} else if (clazz == Float.class || clazz == Float.TYPE) {
			value = Float.parseFloat(element.getTextNormalize());
		} else if (clazz.isEnum()) {
			try {
				// All enums must have the method byValue(String param) to instantiate them
				Method method = clazz.getMethod("byValue", new Class<?>[] {String.class});
				value = method.invoke(null, element.getTextNormalize());
			} catch (Throwable t) {
				throw new XmlParserException("Cannot instantiate an enum: " + clazz, t, element);
			}
			
		} else if (RMObject.class.isAssignableFrom(clazz)) {
			try {
				value = clazz.newInstance();
			} catch (Throwable t) {
				throw new XmlParserException("Can't create new instance", t, element);
			}
		} else if (clazz == Object.class) {
			value = element.getTextNormalize(); // Transform it into string
		} else {
			throw new XmlParserException("Class not known: " + clazz + "!", element);
		}
		return value;
    }
    
    
    /**
     * Set the parent attribute (only for locatables)
     * @param obj the parent
     * @param child the child
     * @param rmFieldName the name of the field that contains the child
     */
    private static void setParent(RMObject obj, RMObject child, String rmFieldName) {
		if ((child instanceof Locatable) && (obj instanceof Locatable)) {
			Locatable c = (Locatable) child;
			Locatable p = (Locatable) obj;
			c.setOwnerAttributeName(rmFieldName);  // We populate this fields for the A-path queries
			c.setParent(p);                        // We populate this fields for the A-path queries
		}    	
    }
    
    
    /**
     * The main recursive method for parsing XMLs. It goes deep in the structure of the XML, while keeping track of the generic
     * types and using the introspector to populate the field values.
     * 
     * @param obj the base object to populate the fields
     * @param parent the parent generics resolver (the generics resolver of obj, that is obj.getClass() == parent.getResolvedClass())
     * @param element the  current XML element
     * @throws XmlParserException raised by any exception occurred
     */
    @SuppressWarnings("unchecked")
	private static void parseElement(RMObject obj, GenericsResolver parent, Element element) throws XmlParserException {
    	// We first generate a map of the field values and then we populate the object in the final step
    	Map<String, Object> fieldValues = new HashMap<String, Object>();
    	
    	// Get the introspector data about the class of obj
    	RmClassData rmClass = IntrospectorData.getRmClassDataByJavaClass(parent.getResolvedClass());
    	
    	// Traverse through all the children
    	for(Element subElement : (List<Element>) element.getChildren()) {
    		String rmFieldName = subElement.getName();
    		
    		// The name of XML tag is the official name of the field.
    		RmFieldData field = rmClass.getRmFieldByRmName(rmFieldName);
    		if (field == null)
    			throw new XmlParserException("Unknown field `" + rmFieldName + "' of " + rmClass.getRmClassName(), subElement);

    		GenericsResolver resolver;
			try {
				resolver = parent.resolveField(field.getJavaField());
			} catch (Throwable t) {
				throw new XmlParserException("Could not resolve " + field.getJavaField(), t, element);
			}
    		Object value;

    		// Map handler
    		if (Map.class.isAssignableFrom(resolver.getResolvedClass())) {
    			value = fieldValues.get(rmFieldName);
    			if (value == null)
    				value = new LinkedHashMap();
    			
    			String mapKey = field.getMapKey();
    			String key = subElement.getAttributeValue(mapKey);
    			if (key == null) key = subElement.getChildTextNormalize(mapKey, defaultNamespace);

    			// GenericsResolver is a handy tool now
    			GenericsResolver valueType;
    			try {
    				valueType = resolver.resolveTypeParameter("V");
    			} catch (Throwable t) {
    				throw new XmlParserException("Could not resolve type parameter V", t, element);
    			}

    			// type override
    			if (subElement.getAttributeValue("type", xsiNamespace) != null)
    				valueType = BaseResolver.makeInstance(
    						IntrospectorData.getJavaClassByRmClassName(subElement.getAttributeValue("type", xsiNamespace)));
    			Object o = newInstanceFromElement(valueType.getResolvedClass(), subElement);
    			if (o instanceof RMObject) {
    				parseElement((RMObject) o, valueType, subElement);
    				
    				// For locatables
    				setParent(obj, (RMObject) o, rmFieldName);
    			}
    			((Map<String, Object>) value).put(key, o);
    		} else if (Collection.class.isAssignableFrom(resolver.getResolvedClass())) {
    			value = fieldValues.get(rmFieldName);
    			if (value == null) {
    				if (resolver.getResolvedClass() == Set.class)
    					value = new HashSet<Object>();
    				else
    					value = new ArrayList<Object>();
    			}
    			Collection<Object> collection = (Collection<Object>) value;


    			GenericsResolver elementType;
    			try {
    				elementType = resolver.resolveTypeParameter("E");
    			} catch (Throwable t) {
    				throw new XmlParserException("Could not resolve type parameter E", t, element);
    			}

    			// type override
    			if (subElement.getAttributeValue("type", xsiNamespace) != null)
    				elementType = BaseResolver.makeInstance(
    						IntrospectorData.getJavaClassByRmClassName(subElement.getAttributeValue("type", xsiNamespace)));
    			Object o = newInstanceFromElement(elementType.getResolvedClass(), subElement);
    			if (o instanceof RMObject) {
    				parseElement((RMObject) o, elementType, subElement);

    				// For locatables
    				setParent(obj, (RMObject) o, rmFieldName);
    			}
    			collection.add(o);
    		} else {
    			// type override
    			if (subElement.getAttributeValue("type", xsiNamespace) != null)
    				resolver = BaseResolver.makeInstance(
    						IntrospectorData.getJavaClassByRmClassName(subElement.getAttributeValue("type", xsiNamespace)));
    			value = newInstanceFromElement(resolver.getResolvedClass(), subElement);
    			if (value instanceof RMObject) {
    				parseElement((RMObject) value, resolver, subElement);

    				// For locatables
    				setParent(obj, (RMObject) value, rmFieldName);
    			}
    		}
    		
    		fieldValues.put(rmFieldName, value);
    	}
    	
    	// Traverse through all the attributes
    	for(Attribute attribute : (List<Attribute>) element.getAttributes()) {
    		if (attribute.getNamespace() == xsiNamespace) continue;
    		String rmFieldName = attribute.getName();
    		Field field = rmClass.getJavaFieldByRmName(rmFieldName);
    		if (field == null)
    			throw new XmlParserException("Unknown field `" + rmFieldName + "' of " + rmClass.getRmClassName(), element);

    		GenericsResolver resolver;
			try {
				resolver = parent.resolveField(field);
			} catch (Throwable t) {
				throw new XmlParserException("Could not resolve field " + field, t, element);
			}

    		Class<?> fieldClass = resolver.getResolvedClass();
    		Object value;

    		if (fieldClass == String.class || fieldClass == Object.class)  {
    			value = attribute.getValue();
    		} else if (fieldClass == Integer.class || fieldClass == Integer.TYPE) {
    			value = Integer.parseInt(attribute.getValue());
    		} else if (fieldClass == Boolean.class || fieldClass == Boolean.TYPE) {
    			value = Boolean.parseBoolean(attribute.getValue());
    		} else {
    			throw new XmlParserException("Class not known: " + fieldClass + "!", element);
    		}
    		
    		fieldValues.put(rmFieldName, value);
    	}
    	
    	// Now the cheap trick to create empty container fields annotated with @NotNull (and without @NotEmpty)
    	for(RmFieldData field : rmClass.getAllRmFields()) {
    		if (fieldValues.containsKey(field.getRmFieldName())) continue;
    		
    		if (!field.isNotNull() || field.isNotEmpty()) continue;

    		GenericsResolver resolver;
    		Object val;
			try {
				resolver = parent.resolveField(field.getJavaField());
			} catch (Throwable t) {
				throw new XmlParserException("Could not resolve " + field.getJavaField(), t, element);
			}
			if (Map.class.isAssignableFrom(resolver.getResolvedClass())) {
				val = new LinkedHashMap();
			} else if (Collection.class.isAssignableFrom(resolver.getResolvedClass())) {
				if (resolver.getResolvedClass() == Set.class) {
					val = new HashSet();
				} else {
					val = new ArrayList();
				}
			} else val = null;

			if (val != null)
				fieldValues.put(field.getRmFieldName(), val);
    	}

    	// Populate the fields into the object
    	for(String rmFieldName : fieldValues.keySet()) {
    		RmFieldData field = rmClass.getRmFieldByRmName(rmFieldName);
    		try {
    			field.setValue(obj, fieldValues.get(rmFieldName));
    		} catch (Throwable t) {
    			throw new XmlParserException("Can't set field: " + field.getRmFieldName() + " to value:" + fieldValues.get(rmFieldName), t, element);
    		}    	
    	}
    }
	
	

}
