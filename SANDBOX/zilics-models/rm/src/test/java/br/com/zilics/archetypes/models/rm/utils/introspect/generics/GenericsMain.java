package br.com.zilics.archetypes.models.rm.utils.introspect.generics;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.IdentityHashMap;


public class GenericsMain {
	private IdentityHashMap<Object, Integer> alreadyVisited = new IdentityHashMap<Object, Integer>();

	public static void main(String[] args) {
		GenericsMain obj = new GenericsMain();
		obj.printClass(SimpleClass.class, 0);
		System.out.println();
		
		obj = new GenericsMain();
		obj.printClass(ComplexWithInnerClasses.InnerClassA.InnerClassB.class, 0);
		System.out.println();

		obj = new GenericsMain();
		obj.printClass(AnotherClass.class, 0);
		System.out.println();

		obj = new GenericsMain();
		obj.printClass(ClassWith2Parameters.class, 0);
		System.out.println();

		obj = new GenericsMain();
		obj.printClass(ComplexWithInnerClasses.class, 0);
		System.out.println();
		
		obj = new GenericsMain();
		obj.printClass(ComplexWithInnerClasses.testA().getClass(), 0);
		System.out.println();
		
	}
	
	private GenericsMain() {
		alreadyVisited.put(Object.class, 1);
		alreadyVisited.put(Annotation.class, 1);
		alreadyVisited.put(Retention.class, 1);
		alreadyVisited.put(String.class, 1);
		alreadyVisited.put(Exception.class, 1);
	}
	
	private void printClassArray(Class<?>[] classes, int identSize) {
		ident(identSize);
		if (wasVisited(classes)) return;

		printId("Array of Classes", classes);
		for(int i = 0; i < classes.length; i++) {
			Class<?> clazz = classes[i];
			ident(identSize + 1);
			System.out.print("Class ");
			System.out.print(i);
			System.out.println(":");
			printClass(clazz, identSize + 2);
		}		
	}
	
	private void printFieldArray(Field[] fields, int identSize) {
		ident(identSize);
		if (wasVisited(fields)) return;

		printId("Array of Fields", fields);
		for(int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			ident(identSize + 1);
			System.out.print("Field ");
			System.out.print(i);
			System.out.println(":");
			printField(field, identSize + 2);
		}		
	}

	private void printMethodArray(Method[] methods, int identSize) {
		ident(identSize);
		if (wasVisited(methods)) return;

		printId("Array of Methods", methods);
		for(int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			ident(identSize + 1);
			System.out.print("Method ");
			System.out.print(i);
			System.out.println(":");
			printMethod(method, identSize + 2);
		}		
	}

	private void printConstructorArray(Constructor<?>[] constructors, int identSize) {
		ident(identSize);
		if (wasVisited(constructors)) return;

		printId("Array of Constructors", constructors);
		for(int i = 0; i < constructors.length; i++) {
			Constructor<?> constructor = constructors[i];
			ident(identSize + 1);
			System.out.print("Constructor ");
			System.out.print(i);
			System.out.println(":");
			printConstructor(constructor, identSize + 2);
		}		
	}
	
	private void printAnnotationArray(Annotation[] annotations, int identSize) {
		ident(identSize);
		if (wasVisited(annotations)) return;

		printId("Array of Annotations", annotations);
		for(int i = 0; i < annotations.length; i++) {
			Annotation annotation = annotations[i];
			ident(identSize + 1);
			System.out.print("Annotation ");
			System.out.print(i);
			System.out.println(":");
			printAnnotation(annotation, identSize + 2);
		}		
	}

	private <T extends GenericDeclaration> void printTypeVariableArray(TypeVariable<T>[] variables, int identSize) {
		ident(identSize);
		if (wasVisited(variables)) return;

		printId("Array of TypeVariables", variables);
		for(int i = 0; i < variables.length; i++) {
			TypeVariable<T> variable = variables[i];
			ident(identSize + 1);
			System.out.print("Variable ");
			System.out.print(i);
			System.out.println(":");
			printTypeVariable(variable, identSize + 2);
		}
	}
	
	private void printTypeArray(Type[] types, int identSize) {
		ident(identSize);
		if (wasVisited(types)) return;

		printId("Array of Types", types);
		for(int i = 0; i < types.length; i++) {
			Type type = types[i];
			ident(identSize + 1);
			System.out.print("Type ");
			System.out.print(i);
			System.out.println(":");
			printType(type, identSize + 2);
		}		
	}
	
	
	
	private <T extends GenericDeclaration> void printTypeVariable(TypeVariable<T> variable, int identSize) {
		ident(identSize);
		if (wasVisited(variable)) return;

		printId("TypeVariable", variable);
		ident(identSize);
		System.out.println("Variable name: " + variable.getName());
		ident(identSize);
		System.out.println("Generic Declaration:");
		printGenericDeclaration(variable.getGenericDeclaration(), identSize + 1);
		//if (variable.getBounds().length > 0) {
			ident(identSize);
			System.out.println("Bounds:");
			printTypeArray(variable.getBounds(), identSize + 1);
		//}
		
	}
	
	private <T> void printClass(Class<T> clazz, int identSize) {
		ident(identSize);
		if (wasVisited(clazz)) return;
		printId("Class", clazz);
		ident(identSize);
		System.out.println("Name: " + clazz.getName());
		ident(identSize);
		System.out.print("Modifiers: ");
		printClassModifiers(clazz.getModifiers());
		
		if (clazz.isArray()) {
			ident(identSize);
			System.out.println("Array component type:");
			printClass(clazz.getComponentType(), identSize + 1);
		} else {
			if (clazz.getDeclaredAnnotations().length > 0) {
				printAnnotationArray(clazz.getDeclaredAnnotations(), identSize);				
			}
			if (clazz.getTypeParameters().length > 0) {
				printTypeVariableArray(clazz.getTypeParameters(), identSize);
			}
			if (clazz.getDeclaredFields().length > 0) {
				ident(identSize);
				System.out.println("Declared Fields:");
				printFieldArray(clazz.getDeclaredFields(), identSize + 1);
			}
			if (clazz.getDeclaredMethods().length > 0) {
				ident(identSize);
				System.out.println("Declared Methods:");
				printMethodArray(clazz.getDeclaredMethods(), identSize + 1);
			}
			if (clazz.getDeclaredConstructors().length > 0) {
				ident(identSize);
				System.out.println("Declared Constructors:");
				printConstructorArray(clazz.getDeclaredConstructors(), identSize + 1);
			}
			if (clazz.getSuperclass() != null) {
				ident(identSize);
				System.out.println("Super class:");
				printClass(clazz.getSuperclass(), identSize + 1);
			}
			if (clazz.getGenericSuperclass() != null) {
				ident(identSize);
				System.out.println("Generic Super class:");
				printType(clazz.getGenericSuperclass(), identSize + 1);				
			}
			if (clazz.getInterfaces() != null && clazz.getInterfaces().length > 0) {
				ident(identSize);
				System.out.println("Implements:");
				printClassArray(clazz.getInterfaces(), identSize + 1);
			}
			if (clazz.getGenericInterfaces().length > 0) {
				ident(identSize);
				System.out.println("Generic Implements:");
				printTypeArray(clazz.getGenericInterfaces(), identSize + 1);				
			}

			if (clazz.getDeclaredClasses().length > 0) {
				ident(identSize);
				System.out.println("Declared Classes:");
				printClassArray(clazz.getDeclaredClasses(), identSize + 1);
			}
			if (clazz.getEnclosingClass() != null) {
				ident(identSize);
				System.out.println("Enclosing Class:");
				printClass(clazz.getEnclosingClass(), identSize + 1);
			}
			if (clazz.getEnclosingConstructor() != null) {
				ident(identSize);
				System.out.println("Enclosing Constructor:");
				printConstructor(clazz.getEnclosingConstructor(), identSize + 1);				
			}
			if (clazz.getEnclosingMethod() != null) {
				ident(identSize);
				System.out.println("Enclosing Method:");
				printMethod(clazz.getEnclosingMethod(), identSize + 1);								
			}
		}
		
	}
	
	private void printField(Field field, int identSize) {
		ident(identSize);
		if (wasVisited(field)) return;
		printId("Field", field);
		ident(identSize);
		System.out.println("Name: " + field.getName());
		ident(identSize);
		System.out.println("Type:");
		printClass(field.getType(), identSize + 1);
		ident(identSize);
		System.out.print("Modifiers: ");
		printFieldModifiers(field.getModifiers());
		ident(identSize);
		System.out.println("Generic Type:");
		printType(field.getGenericType(), identSize + 1);
		if (field.getDeclaredAnnotations().length > 0) {
			printAnnotationArray(field.getDeclaredAnnotations(), identSize);				
		}
	}
	
	private void printMethod(Method method, int identSize) {
		ident(identSize);
		if (wasVisited(method)) return;
		printId("Method", method);
		ident(identSize);
		System.out.println("Name: " + method.getName());
		ident(identSize);
		System.out.print("Modifiers: ");
		printMethodModifiers(method.getModifiers());
		if (method.getDeclaredAnnotations().length > 0) {
			printAnnotationArray(method.getDeclaredAnnotations(), identSize);				
		}
		if (method.getTypeParameters().length > 0) {
			printTypeVariableArray(method.getTypeParameters(), identSize);
		}
		if (method.getTypeParameters().length > 0) {
			ident(identSize);
			System.out.println("Parameters:");
			for(int i = 0; i < method.getParameterTypes().length; i++) {
				ident(identSize + 1);
				System.out.print("Parameter ");
				System.out.print(i);
				System.out.println(":");
				ident(identSize + 2);
				System.out.println("Type:");
				printClass(method.getParameterTypes()[i], identSize + 3);
				ident(identSize + 2);
				System.out.println("Generic Type:");
				printType(method.getGenericParameterTypes()[i], identSize + 3);
				ident(identSize + 2);
				System.out.println("Annotations:");
				printAnnotationArray(method.getParameterAnnotations()[i], identSize + 3);
			}
		}
		ident(identSize);
		System.out.println("Return type:");
		printClass(method.getReturnType(), identSize + 1);
		ident(identSize);
		System.out.println("Generic Return type:");
		printType(method.getGenericReturnType(), identSize + 1);
		if (method.getExceptionTypes().length > 0) {
			ident(identSize);
			System.out.println("Throws:");
			for(int i = 0; i < method.getExceptionTypes().length; i++) {
				ident(identSize + 1);
				System.out.print("Exception ");
				System.out.print(i);
				System.out.println(":");
				ident(identSize + 2);
				System.out.println("Type:");
				printClass(method.getExceptionTypes()[i], identSize + 3);
				ident(identSize + 2);
				System.out.println("Generic Type:");
				printType(method.getGenericExceptionTypes()[i], identSize + 3);
			}			
		}
		if (method.getDefaultValue() != null) {
			ident(identSize);
			System.out.println("Default value: " + method.getDefaultValue());
		}
	}

	private <T> void printConstructor(Constructor<T> constructor, int identSize) {
		ident(identSize);
		if (wasVisited(constructor)) return;
		printId("Constructor", constructor);
		ident(identSize);
		System.out.println("Name: " + constructor.getName());
		ident(identSize);
		System.out.print("Modifiers: ");
		printMethodModifiers(constructor.getModifiers());
		if (constructor.getDeclaredAnnotations().length > 0) {
			printAnnotationArray(constructor.getDeclaredAnnotations(), identSize);				
		}
		if (constructor.getTypeParameters().length > 0) {
			printTypeVariableArray(constructor.getTypeParameters(), identSize);
		}
		if (constructor.getTypeParameters().length > 0) {
			ident(identSize);
			System.out.println("Parameters:");
			for(int i = 0; i < constructor.getParameterTypes().length; i++) {
				ident(identSize + 1);
				System.out.print("Parameter ");
				System.out.print(i);
				System.out.println(":");
				ident(identSize + 2);
				System.out.println("Type:");
				printClass(constructor.getParameterTypes()[i], identSize + 3);
				ident(identSize + 2);
				System.out.println("Generic Type:");
				printType(constructor.getGenericParameterTypes()[i], identSize + 3);
				ident(identSize + 2);
				System.out.println("Annotations:");
				printAnnotationArray(constructor.getParameterAnnotations()[i], identSize + 3);
			}
		}
		if (constructor.getExceptionTypes().length > 0) {
			ident(identSize);
			System.out.println("Throws:");
			for(int i = 0; i < constructor.getExceptionTypes().length; i++) {
				ident(identSize + 1);
				System.out.print("Exception ");
				System.out.print(i);
				System.out.println(":");
				ident(identSize + 2);
				System.out.println("Type:");
				printClass(constructor.getExceptionTypes()[i], identSize + 3);
				ident(identSize + 2);
				System.out.println("Generic Type:");
				printType(constructor.getGenericExceptionTypes()[i], identSize + 3);
			}			
		}
	}

	private void printType(Type type, int identSize) {
		if (type instanceof Class) {
			printClass((Class<?>) type, identSize);
		} else if (type instanceof TypeVariable) {
			printTypeVariable((TypeVariable<?>) type, identSize);
		} else if (type instanceof WildcardType) {
			printWildcardType((WildcardType) type, identSize);
		} else if (type instanceof ParameterizedType) {
			printParameterizedType((ParameterizedType) type, identSize);
		} else if (type instanceof GenericArrayType) {
			printGenericArrayType((GenericArrayType) type, identSize);
		} else {
			printUnknown("Type", type, identSize);
		}
	}
	
	private void printWildcardType(WildcardType wildcardType, int identSize) {
		ident(identSize);
		if (wasVisited(wildcardType)) return;
		printId("WildcardType", wildcardType);
		if (wildcardType.getLowerBounds().length > 0) {
			ident(identSize);
			System.out.println("Lower Bounds:");
			printTypeArray(wildcardType.getLowerBounds(), identSize + 1);
		}
		if (wildcardType.getUpperBounds().length > 0) {
			ident(identSize);
			System.out.println("Upper Bounds:");
			printTypeArray(wildcardType.getUpperBounds(), identSize + 1);
		}
	}
	
	private void printParameterizedType(ParameterizedType parameterizedType, int identSize) {
		ident(identSize);
		if (wasVisited(parameterizedType)) return;
		printId("ParameterizedType", parameterizedType);
		ident(identSize);
		//Raw type is always a class, but we use printType for safety reasons
		System.out.println("Raw type:");
		printType(parameterizedType.getRawType(), identSize + 1);
		if (parameterizedType.getOwnerType() != null) {
			ident(identSize);
			System.out.println("Owner type:");
			printType(parameterizedType.getOwnerType(), identSize + 1);
		}
		if (parameterizedType.getActualTypeArguments().length > 0) {
			ident(identSize);
			System.out.println("Actual Type arguments:");
			printTypeArray(parameterizedType.getActualTypeArguments(), identSize + 1);
		}
	}

	private void printGenericArrayType(GenericArrayType genericArrayType, int identSize) {
		ident(identSize);
		if (wasVisited(genericArrayType)) return;
		printId("GenericArrayType", genericArrayType);
		ident(identSize);
		System.out.println("Component type:");
		printType(genericArrayType.getGenericComponentType(), identSize + 1);
	}
	
	private void printAnnotation(Annotation annotation, int identSize) {
		ident(identSize);
		if (wasVisited(annotation)) return;
		printId("Annotation", annotation);
		ident(identSize);
		System.out.println("ToString: " + annotation);
		ident(identSize);
		System.out.println("Annotation Type:");
		printClass(annotation.annotationType(), identSize + 1);
	}

	private void printGenericDeclaration(GenericDeclaration declaration, int identSize) {
		if (declaration instanceof Class) {
			printClass((Class<?>) declaration, identSize);
		} else if (declaration instanceof Method) {
			printMethod((Method) declaration, identSize);
		} else if (declaration instanceof Constructor) {
			printConstructor((Constructor<?>) declaration, identSize);
		} else {
			printUnknown("Generic Declaration", declaration, identSize);
		}
	}
	
	private void ident(int size) {
		while(size-- > 0) System.out.print("   ");
	}
	
	private void printId(String name, Object obj) {
		System.out.println(name + " Id: " + Integer.toHexString(System.identityHashCode(obj)));
	}
	
	private boolean wasVisited(Object obj) {
		if (alreadyVisited.containsKey(obj)) {
			System.out.println(" (*) " + obj + "[Id: " + Integer.toHexString(System.identityHashCode(obj)) + "][class: " + obj.getClass().getSimpleName() + "]");
			return true;
		}
		alreadyVisited.put(obj, 1);
		return false;
	}
	
	private void printUnknown(String preamble, Object obj, int identSize) {
		ident(identSize);
		if (wasVisited(obj)) return;
		System.out.println("Unknown " + preamble + ": " + obj + "[Id: " + Integer.toHexString(System.identityHashCode(obj)) + "][class: " + obj.getClass().getSimpleName() + "]");		
	}
	
	private void printClassModifiers(int mod) {
		if ((mod & MODIFIER_PUBLIC) != 0)	System.out.print("public ");

		if ((mod & MODIFIER_ABSTRACT) != 0)	System.out.print("abstract ");
		if ((mod & MODIFIER_FINAL) != 0)		System.out.print("final ");
		if ((mod & MODIFIER_INTERFACE) != 0)	System.out.print("interface ");

		if ((mod & MODIFIER_ANNOTATION) != 0)	System.out.print("annotation ");
		if ((mod & MODIFIER_ENUM) != 0)	System.out.print("enum ");
		if ((mod & MODIFIER_SYNTHETIC) != 0)	System.out.print("synthetic ");

		if ((mod & MODIFIER_SUPER) != 0)	System.out.print("[ACC_SUPER] ");
		System.out.println();
	}
	
	private void printFieldModifiers(int mod) {
		if ((mod & MODIFIER_PUBLIC) != 0)	System.out.print("public ");
		if ((mod & MODIFIER_PRIVATE) != 0)	System.out.print("private ");
		if ((mod & MODIFIER_PROTECTED) != 0)	System.out.print("protected ");

		if ((mod & MODIFIER_STATIC) != 0)	System.out.print("static ");
		if ((mod & MODIFIER_FINAL) != 0)		System.out.print("final ");
		if ((mod & MODIFIER_VOLATILE) != 0)	System.out.print("volatile ");

		if ((mod & MODIFIER_TRANSIENT) != 0)	System.out.print("transient ");
		if ((mod & MODIFIER_SYNTHETIC) != 0)	System.out.print("synthetic ");

		if ((mod & MODIFIER_ENUM) != 0)	System.out.print("enum ");
		System.out.println();
	}

	private void printMethodModifiers(int mod) {
		if ((mod & MODIFIER_PUBLIC) != 0)	System.out.print("public ");
		if ((mod & MODIFIER_PRIVATE) != 0)	System.out.print("private ");
		if ((mod & MODIFIER_PROTECTED) != 0)	System.out.print("protected ");

		if ((mod & MODIFIER_STATIC) != 0)	System.out.print("static ");
		if ((mod & MODIFIER_FINAL) != 0)		System.out.print("final ");
		if ((mod & MODIFIER_SYNCHRONIZED) != 0)	System.out.print("synchronized ");

		if ((mod & MODIFIER_BRIDGE) != 0)	System.out.print("bridge ");
		if ((mod & MODIFIER_VARARGS) != 0)	System.out.print("varargs ");

		if ((mod & MODIFIER_NATIVE) != 0)	System.out.print("native ");
		if ((mod & MODIFIER_ABSTRACT) != 0)	System.out.print("abstract ");

		if ((mod & MODIFIER_STRICT) != 0)	System.out.print("strictfp ");

		if ((mod & MODIFIER_SYNTHETIC) != 0)	System.out.print("synthetic ");
		System.out.println();
	}

	private static final int MODIFIER_PUBLIC           = 0x00000001;
    private static final int MODIFIER_PRIVATE          = 0x00000002;
    private static final int MODIFIER_PROTECTED        = 0x00000004;
    private static final int MODIFIER_STATIC           = 0x00000008;
    private static final int MODIFIER_FINAL            = 0x00000010;
    private static final int MODIFIER_SYNCHRONIZED     = 0x00000020;
    private static final int MODIFIER_VOLATILE         = 0x00000040;
    private static final int MODIFIER_TRANSIENT        = 0x00000080;
    private static final int MODIFIER_NATIVE           = 0x00000100;
    private static final int MODIFIER_INTERFACE        = 0x00000200;
    private static final int MODIFIER_ABSTRACT         = 0x00000400;
    private static final int MODIFIER_STRICT           = 0x00000800;
    private static final int MODIFIER_SUPER     = 0x00000020;
    private static final int MODIFIER_BRIDGE    = 0x00000040;
    private static final int MODIFIER_VARARGS   = 0x00000080;
    private static final int MODIFIER_SYNTHETIC = 0x00001000;
    private static final int MODIFIER_ANNOTATION= 0x00002000;
    private static final int MODIFIER_ENUM      = 0x00004000;



}


@Retention(RetentionPolicy.RUNTIME)
@interface SimpleAnnotation {
	public String value() default "Valor";
}

@Retention(RetentionPolicy.RUNTIME)
@interface ComplexAnnotation {

}

class SimpleClass<T> extends Object {

	public T   simpleField;
	public T[] arrayField;
	
}

class AnotherClass<T> extends SimpleClass<T> {
	public StrangeClass<?, ?> different;
}

class StrangeClass<T extends SimpleClass<T>, V extends T> {
	T strangeField;
}

class UmmClass extends OmmClass<UmmClass> {
	
}

class OmmClass<T extends UmmClass> {
	T field;
}


interface SimpleInterface<T> {
	public <E extends T> void method1(@SimpleAnnotation E v) throws Exception;
}

class ClassWith2Parameters<T, V extends T> extends SimpleClass<T> implements SimpleInterface<V> {
	public V anotherField;
	public T meAgain;
	
	public SimpleClass<? extends T> hello;
	
	public <E extends V> void method1(@ComplexAnnotation E v) {
	}

}


@SimpleAnnotation("ops")
class ComplexWithInnerClasses<T> {

	public T field1;
	
	@ComplexAnnotation
	public class InnerClassA<V extends T> {
		
		public V field2;
		
		public class InnerClassB<W extends ComplexWithInnerClasses<T> & InnerInterfaceC<V>> {
			
			public W field3;
			
			public V field4;
			
			public ComplexWithInnerClasses<T>.InnerClassA<V>.InnerClassC field5;
			
		}
		
		public class InnerClassC {
			
		}
		
	}

	public static InnerInterfaceA testA() {
		return new InnerInterfaceA() {
			public void test() {
			}
			
		};
	}
	
	public static interface InnerInterfaceA {
		public void test();
	}
	
	public static interface InnerInterfaceB<K> {
		
	}

	public interface InnerInterfaceC<K> {
		
	}
}




