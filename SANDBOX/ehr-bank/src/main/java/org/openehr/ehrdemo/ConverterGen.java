/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ConverterGen"
 * keywords:    "xml binding"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.ehrdemo;

import java.io.*;
import java.lang.reflect.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.jar.*;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Code generator for a converter that converts objects from openEHR kernel 
 * to XML binding objects generated from Ocean EhrBank WSDL (same as the openEHR
 * XML schema v1)
 * 
 * It requires 1) XML binding classes are on the classpath;
 *             2) Java kernel rm-core and rm-domain jars on the classpath;
 *             3) dir to write generated src exists 
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class ConverterGen {

	public static void main(String[] args) throws Exception {
		ConverterGen gen = new ConverterGen();
		gen.loadAndCheckPackages();
		gen.generate();	
	}	
		
	/* generate the whole class */
	private void generate() throws Exception {
		StringBuffer buf = new StringBuffer();
		
		buf.append("package org.openehr.binding;\n\n");
		
		generateImportStatements(buf);
		
		// insert time-stamp  etc
		buf.append("/**\n");
		buf.append(" * DO NOT MODIFY!! Generated on ");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		buf.append(format.format(new Date()));
		buf.append("\n */\n\n");
		
		buf.append("public class XMLBinding {\n");
		
		for(String name: bindingClassMap.keySet()) {
			Class bindingClass = bindingClassMap.get(name); 
			
			if(Modifier.isAbstract(bindingClass.getModifiers()) 
					|| bindingClass.isEnum() || bindingClass.isInterface()) {
				continue; // skip abstract class
			}
			// Class bindingClass = bindingClassMap.get("ACTION");	
			generateConvertForConcreteClass(buf, bindingClass);
		}
		
		for(String abstractClass: abstractBindingClassMap.keySet()) {
			
			if(findKernelClass(abstractClass) == null) {
				continue;
			}
			
			generateCovertForAbstractClasses(buf, abstractClass);
		}	
		
		buf.append("}\n");
		
		File output = new File(
				"build\\generated\\org\\openehr\\binding\\XMLBinding.java");
		
		FileUtils.writeStringToFile(output, buf.toString(), null);
	}

	private String toCamelPattern(String value) {
		StringTokenizer tokens = new StringTokenizer(value, "_");
		StringBuffer buf = new StringBuffer();
		while(tokens.hasMoreTokens()) {
			buf.append(upperFirst(tokens.nextToken()));
		}
		return buf.toString();
	}
	
	private void loadAndCheckPackages() throws Exception {
		this.bindingClassMap = loadClasses(BINDING_PACKAGE, BINDING_PACKAGE);

		this.abstractBindingClassMap = new TreeMap<String, List<String>>();
		
		log.info("total binding classes loaded: " + bindingClassMap.size());
		
		this.kernelClassMap = loadClasses(KERNEL_CORE, KERNEL_PACKAGE);
		kernelClassMap.putAll(loadClasses(KERNEL_DOMAIN, KERNEL_PACKAGE));
		
		for (String c : kernelClassMap.keySet()) {
			// log.info("class: " + c);
		}
		log.info("total kernel classes loaded: " + kernelClassMap.size());
		
		loadAbstractBindingClassMap();
		
		checkClassNameMatch();
	}
	
	// has to be loaded after both binding and kernel class maps are loaded!!
	private void loadAbstractBindingClassMap() {
		for (String c : bindingClassMap.keySet()) {

			log.info("class: " + c);

			Class bindingClass = bindingClassMap.get(c);

			if (eitherBindingOrKernelClassAbstract(bindingClass)) {
				List<String> concreteClasses = 
					findSubConcreteBindingClass(bindingClass);
				abstractBindingClassMap.put(c, concreteClasses);

				log.info("abstract class: " + c + ", sub: " + concreteClasses);
			}
		}
		log.info("total abstract binding classes loaded: " 
				+ abstractBindingClassMap.size());
	}
	
	/*
	 * Ture if either of the pair is abstract
	 */
	private boolean eitherBindingOrKernelClassAbstract(Class bindingClass) {
		if (Modifier.isAbstract(bindingClass.getModifiers())) {
			return true;
		}
		Class kernelClass = findKernelClass(bindingClass);
		if(kernelClass != null 
				&& Modifier.isAbstract(kernelClass.getModifiers())) {
			return true;
		}
		return false;
	}
	
	/* find list of concrete subclass names */
	private List<String> findSubConcreteBindingClass(Class parent) {
		List<String> list = new ArrayList<String>();
		for(Class c : bindingClassMap.values()) {
			if(parent.equals(c.getSuperclass())) {
				if(Modifier.isAbstract(c.getModifiers())) {
					
					list.addAll(findSubConcreteBindingClass(c));
				
				} else if(findKernelClass(c) != null){
					
					// skip un-supported binding class!!
					list.add(c.getSimpleName());
				}
			}
		}
		return list;
	}
	
	/* 
	 * Generate convert() for abstract binding class based on 
	 * casting it to its concrete subclass
	 */
	private void generateCovertForAbstractClasses(StringBuffer buf, 
			String superClass) {
		List<String> concreteClasses = abstractBindingClassMap.get(superClass);
		String kernelSuperName = getKernelClassName(superClass);
		String kernelInstance = lowerFirst(toCamelPattern(kernelSuperName));
		
		buf.append("    public static ");
		buf.append(superClass);
		buf.append(" convert(");
		buf.append(kernelSuperName);
		buf.append(" ");
		buf.append(kernelInstance);
		buf.append(") throws Exception {\n");
		
		buf.append("        if(");
		buf.append(kernelInstance);
		buf.append(" == null) {\n");
		buf.append("            return null;\n");
	    buf.append("        }\n");
		
		for(int i = 0, j = concreteClasses.size(); i < j; i++) {
			String concrete = concreteClasses.get(i);
			if(i != 0) {
				buf.append(" else ");
			} else {
				buf.append("        ");
			}
			buf.append("if(");
			buf.append(kernelInstance);
			buf.append(" instanceof ");
			buf.append(getKernelClassName(concrete));
			buf.append(") {\n");
			buf.append("            ");
			buf.append("return convert((");
			buf.append(getKernelClassName(concrete));
			buf.append(") ");
			buf.append(kernelInstance);
			buf.append(");\n");
			buf.append("        }");						
		}		
		buf.append("\n        throw new IllegalArgumentException(" +
				"\"unknown or un-supported type\");\n");
		buf.append("    }\n\n");
	}
	
	private String getKernelClassName(String bindingClassName) {
		return findKernelClass(bindingClassName).getSimpleName();
	}
	
	private void checkClassNameMatch() {
		outer:
		for(String bindingClassName : bindingClassMap.keySet()) {
			String bindingName = bindingClassName.replace("_", "");
			for(String kernelClassName: kernelClassMap.keySet()) {
				if(kernelClassName.equalsIgnoreCase(bindingName)) {
					continue outer;
				}
			}
			log.error("missing kernel class for binding class: " + 
					bindingClassName);
		} 
	}

	private static SortedMap<String, Class> loadClasses(String pckgname, 
			String jarRootPackageName) throws ClassNotFoundException {
		
		List<Class> classes = new ArrayList<Class>();
		// Get a File object for the package
		File directory = null;
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			String path = pckgname.replace('.', '/');
			URL resource = cld.getResource(path);
			if (resource == null) {
				throw new ClassNotFoundException("No resource for " + path);
			}
			directory = new File(resource.getFile());
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(pckgname + " (" + directory
					+ ") does not appear to be a valid package");
		}
		
		// unjarred classes
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					classes.add(Class.forName(pckgname + '.'
							+ files[i].substring(0, files[i].length() - 6)));
				}
			}
        
		// classes in a jar
		} else if(directory.toString().indexOf(".jar!") > 0) {
			int index = directory.toString().indexOf(".jar!");  
			String jarDir = directory.toString().substring(0, index + 4);
			jarDir = jarDir.substring(6);
			classes = loadClasseInJarredPackage(jarDir, jarRootPackageName);
		
		} else {
			throw new ClassNotFoundException(pckgname
					+ " does not appear to be a valid package");
		}
		SortedMap<String, Class> map = new TreeMap<String, Class>();
		for (Class c : classes) {
			String name = c.getSimpleName();
			map.put(name, c);
		}
		return map;
	}

	public static List<Class> loadClasseInJarredPackage(String jarName,
			String packageName) {
		
		log.info("loading " + packageName + " from jar " + jarName);
		
		List<Class> classes = new ArrayList<Class>();

		packageName = packageName.replaceAll("\\.", "/");
		
		log.info("Jar " + jarName + " looking for " + packageName);
		
		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(
					jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if ((jarEntry.getName().startsWith(packageName))
						&& (jarEntry.getName().endsWith(".class"))) {
					
					String name = jarEntry.getName().replaceAll("/", "\\."); 
					name = name.substring(0, name.length() - 6);
					
					//log.info("class in jar - " + name);
					
					Class classLoaded = Class.forName(name);
					
					classes.add(classLoaded);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}

	private void generateImportStatements(StringBuffer buf) {
		SortedSet<String> classNameSet = new TreeSet<String>();
		
		for(Class k : kernelClassMap.values()) {
			classNameSet.add(k.getPackage().getName());
		}
		for(String className : classNameSet) {
			if(className.indexOf("$") > 0) {
				continue;
			}
			buf.append("import ");
			buf.append(className);
			buf.append(".*;\n");
		}
		
		// binding types
		buf.append("import " + BINDING_PACKAGE + ".*;\n");
		
		// axis types
		buf.append("import org.apache.axis.types.*;\n");
		
		// java
		buf.append("import java.util.*;\n");
		
		buf.append("\n");
	}
	
	/*
	 * Generates a convert method for given class
	 */
	private void generateConvertForConcreteClass(StringBuffer buf, Class bindingClass) 
			throws Exception {
		
		Class kernelClass = findKernelClass(bindingClass);
		if(kernelClass == null) {
			buf.append("    // kernel class not found for ");
			buf.append(bindingClass.getName());
			buf.append("\n\n");
			return;
		}
		
		if(kernelClass.isEnum() 
				|| Modifier.isAbstract(kernelClass.getModifiers())) {
			return;
		}
		
		String bindingClassName = bindingClass.getSimpleName();
		String kernelClassName = kernelClass.getSimpleName();
		buf.append("    public static ");
		buf.append(bindingClassName);
		buf.append(" convert(");
		buf.append(kernelClassName);
		buf.append(" ");
		buf.append(lowerFirst(kernelClassName));
		buf.append(") throws Exception {\n");
		
		// check null
		buf.append("        ");
		buf.append("if(");
		buf.append(lowerFirst(kernelClassName));
		buf.append(" == null) {\n");
		buf.append("            return null;\n");
		buf.append("        }\n");		
		
		buf.append("        ");
		buf.append(bindingClassName);
		buf.append(" ret = new ");
		buf.append(bindingClassName);
		buf.append("();\n");
		setAttributeValues(buf, lowerFirst(kernelClassName), bindingClass,
				kernelClass);
		buf.append("        return ret;\n");
		buf.append("    }\n\n");
	}
	
	// find attribute of current class and call with super class recursively
	private void setAttributeValues(StringBuffer buf, String kernelInstance,
			Class currentBindingClass, Class containingKernelClass) throws Exception {
		
		List<Field> fields = getFields(currentBindingClass);
		for(Field f : fields) {
			String fieldClassName = f.getType().getSimpleName();
			String fieldName = f.getName();
			
			Class getterReturnType = getReturnTypeOfKernelGetter(f, 
					kernelInstance, currentBindingClass);
			String getterReturnTypeName = getterReturnType.getSimpleName();
			
			// array type field
			if(fieldClassName.endsWith("[]") 
					&& isBindingClass(fieldClassName.substring(0, 
						fieldClassName.length() - 2))) {
				 
				
				String fieldClassNameSingle = fieldClassName.substring(0, 
						fieldClassName.length() - 2);
				Method method = getGetterMethod(f, currentBindingClass);
				String paramTypeOfReturnType = getParamTypeOfReturnType(method,
						containingKernelClass);
				String arrayName = "_" + fieldName;
				String arrayIndexName = arrayName + "_idx";
				String memberInstance = 
					fieldClassName.substring(0, 1).toLowerCase();
				
				buf.append("        ");
				buf.append(getterReturnTypeName);
				if(paramTypeOfReturnType != null) {
					buf.append("<");
					buf.append(paramTypeOfReturnType);
					buf.append("> ");
				}
				buf.append(lowerFirst(toCamelPattern(fieldName)));
				buf.append(" = ");
				buf.append(buildGettingValueFromKernelInstanceSegment(f, 
						kernelInstance, currentBindingClass));
				buf.append(";\n");
				
				// check null value
				buf.append("        if(");
				buf.append(lowerFirst(toCamelPattern(fieldName)));
				buf.append(" != null) {\n");				
				
				buf.append("            ");
				buf.append(fieldClassName);
				buf.append(" ");
				buf.append(arrayName);
				buf.append(" = new ");
				buf.append(fieldClassNameSingle);
				buf.append("[");
				buf.append(lowerFirst(toCamelPattern(fieldName)));
				if(getterReturnType.isArray()) {
					buf.append(".length");
				} else {
					buf.append(".size()");
				}
				buf.append("];\n");
				buf.append("            int ");
				buf.append(arrayIndexName);
				buf.append(" = 0;\n");
				buf.append("            for(");
				buf.append(paramTypeOfReturnType);
				buf.append(" ");
				buf.append(memberInstance);
				buf.append(" : ");
				buf.append(lowerFirst(toCamelPattern(fieldName)));
				buf.append(") {\n");
				buf.append("                ");
				buf.append(arrayName);
				buf.append("[");
				buf.append(arrayIndexName);
				buf.append("++] = convert(");
				buf.append(memberInstance);
				buf.append(");\n");
				buf.append("            }\n");
				buf.append("            ret.set");
				buf.append(upperFirst(fieldName));
				buf.append("(");
				buf.append(arrayName);
				buf.append(");\n");
				buf.append("        }\n");
				
			} else { // non-array type field	
				if( ! getterReturnType.isPrimitive()) {
					buf.append("        if(");
					buf.append(buildGettingValueFromKernelInstanceSegment(f, 
							kernelInstance, currentBindingClass));
					buf.append(" != null) {\n    ");
				}
				buf.append("        ret.set");
				buf.append(upperFirst(fieldName));
				buf.append("(");				
				buf.append(buildGettingValueFromKernelInstanceSegment(f, 
						kernelInstance, currentBindingClass));				
				buf.append(");\n");
				if( ! getterReturnType.isPrimitive()) {					
					buf.append("        }\n");
				}
			}
		}
		Class superClass = currentBindingClass.getSuperclass();
		if(superClass == null) {
			return;
		}
		String superClassName = superClass.getSimpleName();
		if(superClassName.equals("DATA_VALUE") 
				|| superClassName.equals("Object")) {
			return;
		}		
		setAttributeValues(buf, kernelInstance, superClass, containingKernelClass);
	}
	
	
	
	private String buildGettingValueFromKernelInstanceSegment(Field field, 
			String kernelInstance, Class bindingClass) throws Exception {
		
		String fieldClassName = field.getType().getSimpleName();
		StringBuffer buf = new StringBuffer();		
		
		boolean needExtraBracket = true;
		if(isBindingClass(fieldClassName)) {
			Class kernelClass = findKernelClass(field.getType());
			if(kernelClass.isEnum()) {
				buf.append(fieldClassName);
				buf.append(".fromValue(");
			} else {		
				buf.append("convert(");
			}
		} else if(fieldClassName.equals("Float")) {
			buf.append("new Float(");
		} else if(fieldClassName.equals("float")) {
			buf.append("((float) ");
		} else if(fieldClassName.equals("Token")) {
			buf.append("new Token(");
		} else if(fieldClassName.equals("URI")) {
			buf.append("new URI(");
		} else {
			needExtraBracket = false;
		}
		
		buf.append(kernelInstance);
		buf.append(".");
		
		String getterName = buildKernelGetterName(field, bindingClass);
		
		buf.append(getterName);
		buf.append("()");
		
		if(getReturnTypeOfKernelGetter(
				field, kernelInstance, bindingClass).isEnum()) {
			buf.append(".toString()");
		};
		if(needExtraBracket) {
			buf.append(")");			
		}			
		return buf.toString();
	}
	
	private String getParamTypeOfReturnType(Method method, Class bindingClass) {
		Type retType = method.getGenericReturnType();
		if(!(retType instanceof ParameterizedType)) {
			return null;
		}
		ParameterizedType pt = (ParameterizedType) retType;
		Type type = pt.getActualTypeArguments()[0];
		if(type instanceof Class) {
			return ((Class) type).getSimpleName(); 
		} 
		
		type = ((ParameterizedType) type).getRawType();	
		
		String kernelClassName = findKernelClass(bindingClass).getSimpleName();
		String ret = ((Class) type).getSimpleName();
		// hard-coded swith for History class
		if( ! kernelClassName.equals("History")) {
			ret += "<" + kernelClassName + ">";
		}		
		return ret;
	}
	
	private Class getReturnTypeOfKernelGetter(Field field, 
			String kernelInstance, Class bindingClass) throws Exception {
		
		String getterName = buildKernelGetterName(field, bindingClass);
		
		Class kernelClass = findKernelClass(bindingClass);
		Method method = null;			
		try {
			method = kernelClass.getMethod(getterName, null);
		} catch(Exception e) {
			log.error("failed to get method: " + getterName + " on class "
					+ kernelClass);
			Method[] methods = kernelClass.getMethods();
			for(Method m : methods) {
				log.info("method: " + m.toString());
			}
			throw e;
		}
		return method.getReturnType();
	}
	
	private Method getGetterMethod(Field field, Class bindingClass) 
			throws Exception {		
		String getterName = buildKernelGetterName(field, bindingClass);		
		Class kernelClass = findKernelClass(bindingClass);
		return kernelClass.getMethod(getterName, null);
	}
	
	//	due to inconsistences between rm spec and xml schema
	private String fixAttributeInconsistence(String getterName) {
		getterName = getterName.replace("WorkFlowId", "WorkflowId");
		return getterName.replace("NullFlavour", "NullFlavor");
	}
	
	private String buildKernelGetterName(Field field, Class bindingClass) 
			throws Exception {
		String fieldClassName = field.getType().getSimpleName();
		String fieldName = field.getName();		
		String getterName = toCamelPattern(fieldName);
		getterName = fixAttributeInconsistence(getterName);		
		if(("boolean".equals(fieldClassName)
				|| "Boolean".equals(fieldClassName))
				&& !"DV_BOOLEAN".equals(bindingClass.getSimpleName())) {
			if(fieldName.startsWith("is")) {
				getterName = getterName.substring(2);
			} else if(getterName.indexOf("Is") > 0) {
				getterName = getterName.replace("Is", "");
			} 
			getterName = "is" + getterName;
		} else {
			getterName = "get" + getterName;
		}
		return getterName;
	}
	
	private List<Field> getFields(Class klass) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = klass.getDeclaredFields();
		
		for(Field f : fields) {
			String fieldName = f.getName();
			if(fieldName.startsWith("__") || "typeDesc".equals(fieldName)
					|| "class$0".equals(fieldName)) {
				continue; // skip
			}
			list.add(f);
		}
		return list;			
	}

	private boolean isBindingClass(String className) {
		//if(className.endsWith("[]")) {
		//	className = className.substring(0, className.length() - 2);
		//}
		return bindingClassMap.containsKey(className);
	}
	
	private String lowerFirst(String value) {
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}
	
	private String upperFirst(String value) {
		return value.substring(0, 1).toUpperCase() + value.substring(1);
	}

	private Class findKernelClass(Class bindingClass) {
		return findKernelClass(bindingClass.getSimpleName());
	}
	
	private Class findKernelClass(String bindingClassName) {
		String bindingName = bindingClassName.replace("_", "");
		for(String kernelClassName: kernelClassMap.keySet()) {
			if(kernelClassName.equalsIgnoreCase(bindingName)) {
				return kernelClassMap.get(kernelClassName);
			}
		}
		log.warn("kernel class not found for binding: " + bindingClassName);
		return null; // not found
	}
	
	/* classes from XML binding */
	private SortedMap<String, Class> bindingClassMap;

	/* mapping between abstract class and its concrete subclass */
	private SortedMap<String, List<String>> abstractBindingClassMap;
	
	/* classes from the java kernel */
	private SortedMap<String, Class> kernelClassMap;

	private static Logger log = Logger.getLogger(ConverterGen.class);

	private static final String BINDING_PACKAGE = "org.openehr.schemas.v1";

	/* root package for all kernel classes */
	private static final String KERNEL_PACKAGE = "org.openehr.rm";
	
	/* sample package in rm-core component */
	private static final String KERNEL_CORE = "org.openehr.rm.common";
	
	/* sample package in rm-domain component */
	private static final String KERNEL_DOMAIN = "org.openehr.rm.ehr";
}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is ConverterGen.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2007 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s): Yin Su Lim
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */