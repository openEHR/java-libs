package br.com.zilics.archetypes.models.rm.utils.validation;

import java.lang.reflect.Modifier;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.utils.introspect.generics.BaseResolver;
import br.com.zilics.archetypes.models.rm.utils.introspect.generics.GenericsResolver;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmFieldData;
import junit.framework.TestCase;

public class ValidationUtilsTest extends TestCase {
	public ValidationUtilsTest(String testName) {
		super(testName);
	}
	
	
	private void populateObject(RMObject instance, GenericsResolver parent, int depth) throws Exception {
		if (depth == 0) return;
		RmClassData classData = IntrospectorData.getRmClassDataByJavaClass(instance.getClass());
		for(RmFieldData fieldData : classData.getAllRmFields()) {
			GenericsResolver resolver;
			try {
				resolver = parent.resolveField(fieldData.getJavaField());
			} catch(Throwable t) {
				t.printStackTrace();
				throw new Exception(t);
			}
			if (RMObject.class.isAssignableFrom(resolver.getResolvedClass())) {
				RmClassData child = IntrospectorData.getRmClassDataByJavaClass(resolver.getResolvedClass());
				RmClassData concrete = child.getConcreteSubclasses().iterator().next();
				if (concrete != child) {
					resolver = BaseResolver.makeInstance(concrete.getJavaClass());
				}
					
				RMObject fieldValue = (RMObject) concrete.newInstance();
				fieldData.setValue(instance, fieldValue);
				populateObject(fieldValue, resolver, depth - 1);
			} else {
				Class<?> clazz = resolver.getResolvedClass();
				Object value = null;
				if (clazz == String.class) {
					value = "String";
				} else if (clazz == Integer.class) {
					value = Integer.valueOf(1);
				} else if (clazz == Double.class) {
					value = Double.valueOf(1.0);
				} else if (clazz == Long.class) {
					value = Long.valueOf(1L);
				} else if (clazz == Short.class) {
					value = Short.valueOf((short) 1);
				} else if (clazz == Byte.class) {
					value = Byte.valueOf((byte) 1);
				} else if (clazz == Boolean.class) {
					value = Boolean.TRUE;
				} else if (clazz == Float.class) {
					value = Float.valueOf(1.0f);
				}
				if (value != null)
					fieldData.setValue(instance, value);
			}
		}
	}
	
	public void testValidate1Depth() throws Exception {
		for(RmClassData classData: IntrospectorData.getAllRmClassData()) {
			Class<?> clazz = classData.getJavaClass();
			if (Modifier.isAbstract(clazz.getModifiers())) continue;
			RMObject instance = (RMObject) clazz.newInstance();
			
			ValidationResult result = new ValidationResult();
			instance.validate(result);
		}
	}
	
	private void testValidateNDepth(int depth) throws Exception {
		for(RmClassData classData: IntrospectorData.getAllRmClassData()) {
			Class<?> clazz = classData.getJavaClass();
			if (Modifier.isAbstract(clazz.getModifiers())) continue;
			RMObject instance = (RMObject) clazz.newInstance();

			GenericsResolver genericClassData = BaseResolver.makeInstance(classData.getJavaClass());
			ValidationResult result = new ValidationResult();
			populateObject(instance, genericClassData, depth);
			
			instance.validate(result);
		}
	}
	
	
	public void testValidate5Depth() throws Exception {
		for(int depth = 2; depth <= 6; depth++)
			testValidateNDepth(depth);
	}

}
