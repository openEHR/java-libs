package org.openehr.ehrdemo;

import java.lang.reflect.*;
import java.util.*;

import org.openehr.build.RMObjectBuilder;
import org.openehr.build.SystemValue;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

/**
 * Binds data from generated XML binding class to openEHR RM classes
 * 
 * @author Rong.Chen
 */
public class RMBinding {
	
	public RMBinding() {
		try {
			TerminologyService termServ;
			MeasurementService measureServ;
			termServ = SimpleTerminologyService.getInstance();
			measureServ = SimpleMeasurementService.getInstance();

			CodePhrase lang = new CodePhrase("ISO_639-1", "en");
			CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8");

			Map<SystemValue, Object> values = new HashMap<SystemValue, Object>();
			values.put(SystemValue.LANGUAGE, lang);
			values.put(SystemValue.CHARSET, charset);
			values.put(SystemValue.ENCODING, charset);
			values.put(SystemValue.TERMINOLOGY_SERVICE, termServ);
			values.put(SystemValue.MEASUREMENT_SERVICE, measureServ);
			builder = new RMObjectBuilder(values);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed to start DADLBinding..");
		}
	}
	
	/**
	 * Binds data from XML binding classes to RM classes using reflection
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public Object bindToRM(Object object) throws Exception {
		Method[] methods = object.getClass().getMethods();
		Object value = null;
		Map<String, Object> valueMap = new HashMap<String, Object>();
		
		for(Method method : methods) {
			String name = method.getName();
			if(name.equals("getClass")) {
				continue;
			}
			if(name.startsWith("get") && method.getParameterTypes().length == 0) {
				log("getter: " + name);
				value = method.invoke(object, null);
				
				if(value == null) {
					continue;
				}
				
				log("value.class: " + value.getClass() + ", " + isXMLBindingClass(value));
				
				if(value.getClass().isArray()) {
					
					Object[] array = (Object[]) value;
					Object[] done = new Object[array.length];
					for(int i = 0; i < array.length; i++) {
						done[i] = bindToRM(array[i]);
					}
					value = done;
					
				} else if(value.getClass().getSimpleName().equals("PARTY_REF")) {
				
					value = new PartyRef(new HierObjectID("1.3.3.1.2.42.1"), 
							"RGANISATION");						
				
				}  else if(isXMLBindingClass(value)) {
		
					value = bindToRM(value);
				
				} else if(value.getClass().equals(
				
						org.apache.axis.types.Token.class)) {
					Method toStr = value.getClass().getMethod("toString", null);
					value = (String) toStr.invoke(value, null);
				
					
				}  
				name = name.substring(3, name.length());
				name = name.substring(0, 1).toLowerCase() + name.substring(1);
				
				log("attribute: " + name);
				
				valueMap.put(name, value);
			}
			
		}
		
		String className = object.getClass().getSimpleName();
		
		log("building class: " + className);
		
		Object rmObj = builder.construct(className, valueMap);
		
		return rmObj;
	}
	
	private boolean isXMLBindingClass(Object obj) {
		return obj.getClass().getName().contains("org.openehr.schemas.v1");
	}
	
	
	
	private void log(String msg) {
		//System.out.println(">>>> " + msg);
	}
	
	
	private RMObjectBuilder builder;
}
