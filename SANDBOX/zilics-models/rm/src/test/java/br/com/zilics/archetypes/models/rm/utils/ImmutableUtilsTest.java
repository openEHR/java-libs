package br.com.zilics.archetypes.models.rm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.exception.ImmutableException;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import junit.framework.TestCase;

public class ImmutableUtilsTest extends TestCase {
	public ImmutableUtilsTest(String testName) {
		super(testName);
	}
	
	public void testImmutableSetMethod() throws Exception {
		for(RmClassData classData : IntrospectorData.getAllRmClassData()) {
			if (classData.isAbstractClass()) continue;
			Class<?> clazz = classData.getJavaClass();
			RMObject instance = (RMObject) clazz.newInstance();
			instance.setImmutable(true);
			
			while(clazz != RMObject.class) {
				for(Field field : clazz.getDeclaredFields()) {
					if (field.getType().isPrimitive()) continue;
					if (Modifier.isStatic(field.getModifiers())) continue;

					String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1);
					Method setMethod;
					try {
						setMethod = clazz.getMethod(setMethodName, new Class<?>[]{ field.getType() });
					} catch(NoSuchMethodException ex) {
						continue;
					}
					
					try {
						setMethod.invoke(instance, new Object[]{ null });
						fail("Expecting an exception here![" + setMethod + "]");
					} catch(InvocationTargetException ex) {
						if (!(ex.getTargetException() instanceof ImmutableException))
							fail("ImmutableException expected");
					}
				}
				clazz = clazz.getSuperclass();
			}			
		}
	}

	public void testImmutableGetMethod() throws Exception {
		for(RmClassData classData : IntrospectorData.getAllRmClassData()) {
			Class<?> clazz = classData.getJavaClass();
			if (Modifier.isAbstract(clazz.getModifiers())) continue;
			RMObject instance = (RMObject) clazz.newInstance();
			instance.setImmutable(true);
			
			while(clazz != RMObject.class) {
				for(Field field : clazz.getDeclaredFields()) {
					if (field.getType().isPrimitive()) continue;
					if (Modifier.isStatic(field.getModifiers())) continue;

					String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1);
					Method getMethod;
					try {
						getMethod = clazz.getMethod(getMethodName, new Class<?>[]{});
					} catch(NoSuchMethodException ex) {
						continue;
					}
					
					if (!Collection.class.isAssignableFrom(field.getType()) &&
					    !Map.class.isAssignableFrom(field.getType())) continue;
					
					Object value;
					if (List.class.isAssignableFrom(field.getType())) {
						value = Collections.EMPTY_LIST;
					} else if (Set.class.isAssignableFrom(field.getType())) {
						value = Collections.EMPTY_SET;
					} else if (Map.class.isAssignableFrom(field.getType())) {
						value = Collections.EMPTY_MAP;
					} else
						value = Collections.EMPTY_LIST;
					
					field.setAccessible(true);
					field.set(instance, value);
					
					Object val = getMethod.invoke(instance, new Object[]{});
					try {
						if (val instanceof Collection) {
							((Collection<?>) val).clear();
						} else {
							((Map<?,?>) val).clear();
						}
						fail("Expecting an exception here! " + field);
					} catch(UnsupportedOperationException ex) {
						
					}
				}
				clazz = clazz.getSuperclass();
			}			
		}
	}
	
	public void testRecursiveness() throws Exception {
		MockImmutableTestClass m1 = new MockImmutableTestClass();
		MockImmutableTestClass m2 = new MockImmutableTestClass();
		MockImmutableTestClass m3 = new MockImmutableTestClass();
		MockImmutableTestClass m4 = new MockImmutableTestClass();
		MockImmutableTestClass m5 = new MockImmutableTestClass();
		MockImmutableTestClass m6 = new MockImmutableTestClass();
		MockImmutableTestClass m7 = new MockImmutableTestClass();
		MockImmutableTestClass m8 = new MockImmutableTestClass();
		MockImmutableTestClass m9 = new MockImmutableTestClass();
		
		m1.directAttribute = m2;
		MockImmutableTestClass.staticAttribute = m3;
		m1.arrayAttribute = new MockImmutableTestClass[] { m4 };
		m1.listAttribute = new ArrayList<MockImmutableTestClass>();
		m1.listAttribute.add(m5);
		m1.mapAttribute = new HashMap<String, MockImmutableTestClass>();
		m1.mapAttribute.put("some", m6);
		m1.directAttributeWithIgnore = m7;
		m1.directAttributeWithTransient = m8;
		m2.directAttribute = m9;
		
		m1.setImmutable(true);
		assertTrue(m1.isImmutable());
		assertTrue(m2.isImmutable());
		assertFalse(m3.isImmutable());
		assertTrue(m4.isImmutable());
		assertTrue(m5.isImmutable());
		assertTrue(m6.isImmutable());
		assertTrue(m7.isImmutable());
		assertTrue(m8.isImmutable());
		assertTrue(m9.isImmutable());
	}

}

class MockImmutableTestClass extends RMObject {

	private static final long serialVersionUID = 2975981960036408543L;
	
	MockImmutableTestClass directAttribute;
	static MockImmutableTestClass staticAttribute;
	MockImmutableTestClass[] arrayAttribute;
	List<MockImmutableTestClass> listAttribute;
	Map<String, MockImmutableTestClass> mapAttribute;

	@Ignore
	MockImmutableTestClass directAttributeWithIgnore;

	transient MockImmutableTestClass directAttributeWithTransient;

}
