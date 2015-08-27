package org.openehr.rm.binding;

import org.apache.log4j.Logger;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.composition.content.entry.Entry;
import org.openehr.rm.datastructure.itemstructure.representation.Element;

import java.lang.reflect.Method;
import java.util.*;

public class XPathUtil {

    public Set<String> extractXPaths(Locatable root) throws Exception {
        Set<String> set = new HashSet<String>();
        buildPath(root, "", set);
        return set;
    }

    private void buildPath(Object obj, String path, Set<String> paths) throws Exception {
        if(obj instanceof List) {
            List list = (List) obj;
            for(int i = 0, j = list.size(); i<j; i++) {
                Object o = list.get(i);
                buildPath(o, path + "[" + (i + 1) + "]", paths);
            }
        } else if(obj instanceof Element) {

            paths.add(path);

        } else if(obj instanceof Locatable) {

            Locatable locatable = (Locatable) obj;
            if(locatable.isArchetypeRoot() && (!path.isEmpty())) {
                return;
            }

            inspector.retrieveRMAttributes(obj.getClass().getSimpleName());
            Class klass = obj.getClass();
            Map<String, Attribute> attributeMap = inspector.attributeMap(klass);
            for(Map.Entry<String, Attribute> entry : attributeMap.entrySet()) {
                String attributeName = entry.getKey();
                Attribute attribute = entry.getValue();

                if(attribute.system()) {
                    continue;
                }

                Method method = klass.getMethod("get" +
                        attributeName.substring(0, 1).toUpperCase() +
                        attributeName.substring(1), null);

                assert(method != null);

                Object value = method.invoke(obj, null);
                if(value != null) {
                    buildPath(value, path + "/" + attributeName, paths);
                }
            }
        }
    }

    public Map<String, Set<String>> extractPaths(Locatable root) throws Exception {
        Map<String, Set<String>> map = new HashMap<String, Set<String>>();
        buildPaths(root, "", "", map);
        return map;
    }

    private void buildPaths(Object obj, String apath, String xpath, Map<String, Set<String>> paths) throws Exception {

        //System.out.println("apath: " + apath + ", xpath: " + xpath
        //		+ ", nodeId: " + (obj instanceof Locatable ? ((Locatable) obj).getArchetypeNodeId() : ""));

        if(obj instanceof List) {
            List list = (List) obj;
            for(int i = 0, j = list.size(); i<j; i++) {
                Object o = list.get(i);

                assert (o instanceof Locatable);

                Logger.getLogger(XPathUtil.class).warn("Null locatable at "+obj+" index="+i);

                Locatable loc = ((Locatable) o);
                String nodeId = loc.getArchetypeNodeId();

                assert (nodeId != null && !nodeId.isEmpty());

                String currentAPath = apath + "[" + getNodeId(loc, nodeId) + "]";
                String currentXPath = xpath + "[" + (i + 1) + "]";

                buildPaths(o, currentAPath, currentXPath, paths);
            }
        } else if(obj instanceof Element) {

            Set<String> set = paths.get(apath);
            if(set == null) {
                set = new HashSet<String>();
                paths.put(apath, set);
            }
            set.add(xpath);

        } else if(obj instanceof Locatable) {

            Locatable locatable = (Locatable) obj;

            inspector.retrieveRMAttributes(obj.getClass().getSimpleName());
            Class klass = obj.getClass();
            Map<String, Attribute> attributeMap = inspector.attributeMap(klass);

            for(Map.Entry<String, Attribute> entry : attributeMap.entrySet()) {
                String attributeName = entry.getKey();
                Attribute attribute = entry.getValue();

                if(attribute.system()) {
                    continue;
                }

                String methodName = "get" +
                        attributeName.substring(0, 1).toUpperCase() +
                        attributeName.substring(1);

                Method method = klass.getMethod(methodName, null);

                assert(method != null);

                Object value = method.invoke(obj, null);

                if(value != null && !methodName.equals("getParent")) {

                    String nodeIdStr = "";
                    if (value instanceof Locatable){
                        nodeIdStr = "[" + ((Locatable)value).getArchetypeNodeId() + "]";
                    }
                    String currentAPath = apath + "/" + attributeName+nodeIdStr;
                    String currentXPath = xpath + "/" + attributeName;
                    buildPaths(value, currentAPath, currentXPath, paths);
                }
            }
        }
    }

    private String getNodeId(Locatable loc, String nodeId) {
        StringBuilder sb = new StringBuilder();
        sb.append(nodeId);
        if (loc instanceof Entry){
            sb.append(" and name/value='" + loc.getName().getValue()+"'");
        }
        return sb.toString();
    }

    public Set<String> extractRootXPaths(Locatable root) throws Exception {
        Set<String> set = new HashSet<String>();
        buildRootPath(root, "", set);
        return set;
    }

    private void buildRootPath(Object obj, String path, Set<String> paths) throws Exception {

        if(obj instanceof List) {
            List list = (List) obj;
            for(int i = 0, j = list.size(); i<j; i++) {
                Object o = list.get(i);
                buildRootPath(o, path + "[" + (i + 1) + "]", paths);
            }
        } else if(obj instanceof Locatable) {
            Locatable l = (Locatable) obj;

            if(l.isArchetypeRoot()) {
                if( ! path.isEmpty()) {
                    paths.add(path);
                    return;
                }
            }
            inspector.retrieveRMAttributes(obj.getClass().getSimpleName());
            Class klass = obj.getClass();
            Map<String, Attribute> attributeMap = inspector.attributeMap(klass);
            for(Map.Entry<String, Attribute> entry : attributeMap.entrySet()) {
                String attributeName = entry.getKey();
                Attribute attribute = entry.getValue();

                if(attribute.system()) {
                    continue; // skip system attributes
                }
                String getterName = "get"+
                        attributeName.substring(0, 1).toUpperCase() +
                        attributeName.substring(1);
                Method method = klass.getMethod(getterName, null);

                assert(method != null);

                Object value = method.invoke(obj, null);
                if(value != null) {
                    buildRootPath(value, path + "/" + attributeName, paths);
                }
            }
        }
    }

    private RMInspector inspector = RMInspector.getInstance();
}
