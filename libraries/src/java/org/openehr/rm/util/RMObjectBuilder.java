/*
 * component:   "openEHR Reference Implementation"
 * description: "Class RMObjectBuilder"
 * keywords:    "util"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.util;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Reference model class instances builder
 *
 * @author Rong Chen
 * @version 1.0
 */
public class RMObjectBuilder {

    /**
     * Create a RMObjectBuilder
     *
     * @param systemValues
     */
    public RMObjectBuilder(Map<SystemValue, Object> systemValues) {
        if (systemValues == null) {
            throw new IllegalArgumentException("systemValues null");
        }
        this.systemValues = systemValues;
        this.typeMap = new HashMap<String, Class>();
        this.upperCaseMap = new HashMap<String, Class>();

        try {
            loadTypeMap();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("failed to load class, " + e
                    + " when starting rmObjectBuilder..");
        }
    }

    // load all reference types that are possible to instantiate
    // todo: possible to do this by reflection?
    private Map<String, Class> loadTypeMap() throws ClassNotFoundException {
        String[] names;

        // load datatypes classes
        names = new String[]{"DvBoolean", "DvState"};
        loadPackage("datatypes.basic", names);

        names = new String[]{"DvText", "DvCodedText", "DvParagraph"};
        loadPackage("datatypes.text", names);

        names = new String[]{"DvCount", "DvOrdinal", "DvQuantity",
                             "DvInterval", "DvQuantityRatio"};
        loadPackage("datatypes.quantity", names);

        names = new String[]{"DvDate", "DvDateTime", "DvTime", "DvDuration"};
                             //"DvPartialDate", "DvPartialTime"};
        loadPackage("datatypes.quantity.datetime", names);

        names = new String[]{"DvParsable", "DvMultimedia"};
        loadPackage("datatypes.encapsulated", names);

        // load datastructure classes
        names = new String[]{"Element", "Cluster"};
        loadPackage("datastructure.itemstructure.representation", names);

        names = new String[]{"ItemSingle", "ItemList", "ItemTable",
                             "ItemTree"};
        loadPackage("datastructure.itemstructure", names);

        names = new String[]{"History", "IntervalEvent", "PointEvent"};
        loadPackage("datastructure.history", names);

        // load ehr classes
        names = new String[]{"Action", "Evaluation", "Instruction", "Observation",
                                "AdminEntry"};
        loadPackage("composition.content.entry", names);
        loadPackage("composition.content.navigation", new String[]{"Section"});
        loadPackage("composition", new String[]{"Composition"});

        // load demographic classes
        names = new String[]{
            "Address", "PartyIdentity", "Agent", "Group", "Organisation",
            "Person", "Contact", "PartyRelationship", "Role", "Capability"
        };
        loadPackage("demographic", names);

        return typeMap;
    }

    private void loadPackage(String packageName, String[] typeNames)
            throws ClassNotFoundException {
        for (String name : typeNames) {
            Class type = Class.forName("org.openehr.rm." + packageName
                    + "." + name);
            typeMap.put(name, type);
            upperCaseMap.put(name.toUpperCase(), type);
        }
    }

    /*
     * Return a map with name as the key and index of position as the value
     * for required parameters of the full constructor in the RMObject
     *
     * @param rmClass
     * @return
     */

    private Map<String, Class> attributeType(Class rmClass) {

        Map<String, Class> map = new HashMap<String, Class>();
        Constructor constructor = fullConstructor(rmClass);
        if (constructor == null) {
            throw new IllegalArgumentException(
                    "no annotated constructor of " + rmClass + ">");
        }
        Annotation[][] annotations = constructor.getParameterAnnotations();
        Class[] types = constructor.getParameterTypes();

        if (annotations.length != types.length) {
            throw new IllegalArgumentException("less annotations");
        }
        for (int i = 0; i < types.length; i++) {
            if (annotations[ i ].length == 0) {
                throw new IllegalArgumentException(
                        "missing annotations of attribute " + i);
            }
            Attribute attribute = (Attribute) annotations[ i ][ 0 ];
            map.put(attribute.name(), types[ i ]);
        }
        return map;
    }

    /**
     * Return a map with name as the key and index of position as the value
     * for all parameters of the full constructor in the RMObject
     *
     * @param rmClass
     * @return
     */
    private Map<String, Integer> attributeIndex(Class rmClass) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Constructor constructor = fullConstructor(rmClass);
        Annotation[][] annotations = constructor.getParameterAnnotations();

        for (int i = 0; i < annotations.length; i++) {
            if (annotations[ i ].length == 0) {
                throw new IllegalArgumentException(
                        "missing annotation at position " + i);
            }
            Attribute attribute = (Attribute) annotations[ i ][ 0 ];
            map.put(attribute.name(), i);
        }
        return map;
    }

    /**
     * Return a map with name as the key and index of position as the value
     * for all parameters of the full constructor in the RMObject
     *
     * @param rmClass
     * @return
     */
    private Map<String, Attribute> attributeMap(Class rmClass) {
        Map<String, Attribute> map = new HashMap<String, Attribute>();
        Constructor constructor = fullConstructor(rmClass);
        Annotation[][] annotations = constructor.getParameterAnnotations();

        for (int i = 0; i < annotations.length; i++) {
            if (annotations[ i ].length == 0) {
                throw new IllegalArgumentException(
                        "missing annotation at position " + i);
            }
            Attribute attribute = (Attribute) annotations[ i ][ 0 ];
            map.put(attribute.name(), attribute);
        }
        return map;
    }

    private static Constructor fullConstructor(Class klass) {
        Constructor[] array = klass.getConstructors();
        for (Constructor constructor : array) {
            if (constructor.isAnnotationPresent(FullConstructor.class)) {
                return constructor;
            }
        }
        return null;
    }

    /**
     * Return system value for given id
     *
     * @param id
     * @return null if not there
     */
    public Object getSystemValue(SystemValue id) {
        return systemValues.get(id);
    }

    /**
     * Construct an instance of RM class of given name and values.
     * <p/>
     * If the input is a string, and the required attribute is some other
     * types (integer, double etc), it will be converted into right type. if
     * there is any error during conversion, AttributeFormatException will be
     * thrown.
     *
     * @param rmClassName
     * @param valueMap
     * @return created instance
     * @throws RMObjectBuildingException
     */
    public RMObject construct(String rmClassName,
                              Map<String, Object> valueMap)
            throws RMObjectBuildingException {

        Class rmClass = typeMap.get(rmClassName);
        if (rmClass == null) {
            rmClass = upperCaseMap.get(rmClassName.replace("_", ""));
        }
        if (rmClass == null) {
            throw new RMObjectBuildingException("RM type unknown: \""
                    + rmClassName + "\"");
        }

        Constructor constructor = fullConstructor(rmClass);
        Map<String, Class> typeMap = attributeType(rmClass);
        Map<String, Integer> indexMap = attributeIndex(rmClass);
        Map<String, Attribute> attributeMap = attributeMap(rmClass);
        Object[] valueArray = new Object[ indexMap.size() ];

        for (String name : typeMap.keySet()) {
            Object value = valueMap.get(name);
            if (!typeMap.containsKey(name) || !attributeMap.containsKey(name)) {
                throw new RMObjectBuildingException(
                        "unknown attribute " + name);
            }
            Class type = typeMap.get(name);
            Integer index = indexMap.get(name);

            Attribute attribute = attributeMap.get(name);
            if (index == null || type == null) {
                throw new RMObjectBuildingException("unknown attribute \""
                        + name + "\"");
            }

            // system supplied value
            if (attribute.system()) {
                SystemValue sysvalue = SystemValue.fromId(name);
                if (sysvalue == null) {
                    throw new RMObjectBuildingException("unknonw system value"
                            + "\"" + name + "\"");
                }
                value = systemValues.get(sysvalue);
                if (value == null) {
                    throw new AttributeMissingException("missing value for " +
                            "system attribute \"" + name + "\"");
                }
            }

            // check required attributes
            if (value == null && attribute.required()) {
                throw new AttributeMissingException("missing value for " +
                        "required attribute \"" + name + "\" of type " + type);
            }

            // set default value for primitive type
            else if (value == null) {
                value = defaultValue(type);
            }

            // in case of string value, convert to right type if necessary
            else if (value instanceof String) {
                String str = (String) value;
                try {

                    // for DvCount
                    if (type.equals(int.class)) {
                        value = Integer.parseInt(str);

                        // for DvQuantity
                    } else if (type.equals(double.class)) {
                        value = Double.parseDouble(str);
                    }
                } catch (NumberFormatException e) {
                    throw new AttributeFormatException("wrong format of "
                            + "attribute " + name + ", expect " + type);
                }
            }
            // check type
            else if (value != null && !type.isPrimitive()) {
                try {
                    type.cast(value);
                } catch (ClassCastException e) {
                    throw new RMObjectBuildingException("value for attribute " +
                            name + " has wrong type, expected \"" + type +
                            "\", but got \"" + value.getClass() + "\"");
                }
            }
            valueArray[ index ] = value;
        }

        Object ret = null;
        try {
            ret = constructor.newInstance(valueArray);
        } catch (Exception e) {
            if (stringParsingTypes.contains(rmClassName)) {
                throw new AttributeFormatException("wrong format for "
                        + "type " + rmClassName);
            }
            throw new RMObjectBuildingException(
                    "failed to create new instance, " + e.getCause());
        }
        return (RMObject) ret;
    }

    // todo: isn't there any support from java api on this?
    private Object defaultValue(Class type) {
        if (type == boolean.class) {
            return Boolean.FALSE;
        } else if (type == double.class) {
            return new Double(0);
        } else if (type == float.class) {
            return new Float(0);
        } else if (type == int.class) {
            return new Integer(0);
        } else if (type == short.class) {
            return new Short((short) 0);
        } else if (type == long.class) {
            return new Long(0);
        } else if (type == char.class) {
            return new Character((char) 0);
        } else if (type == byte.class) {
            return new Byte((byte) 0);
        }
        return null;
    }

    /* fields */
    private final Map<SystemValue, Object> systemValues;

    // loaded rm type map
    private final Map<String, Class> typeMap;
    private final Map<String, Class> upperCaseMap;
    private static final Set<String> stringParsingTypes;

    static {
        // so far only types from quantity.datetime
        stringParsingTypes = new HashSet<String>();
        String[] types = {"DvDate", "DvDateTime", "DvTime", "DvDuration",
                          "DvPartialDate", "DvPartialTime"};
        stringParsingTypes.addAll(Arrays.asList(types));
    }
}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is RMObjectBuilder.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */