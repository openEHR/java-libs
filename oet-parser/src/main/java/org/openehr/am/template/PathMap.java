/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class PathMap"
 * keywords:    "oet-parser"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2009,2010 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.am.template;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * A utility class to load path map generated in the template
 * flattening process with the following format:
 * 
 * [key]=[path]
 * 
 * NOTE char '=' is not allowed in the key
 * 
 * @author rong.chen
 *
 */
public class PathMap {
	
	PathMap() {
		this.keyPathMap = new HashMap<String,String>();
	}
	
	/**
	 * Loads an in-memory termMap by given file 
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */	
	public static PathMap load(String filename) throws IOException {
		List<String> lines = FileUtils.readLines(new File(filename), UTF8);
		return fromLines(lines);
	}
	
	/**
	 * Loads an in-memory termMap by given inputStream 
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static PathMap load(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, UTF8));
		String line = reader.readLine();
		List<String> lines = new ArrayList<String>();
		while(line != null) {
			lines.add(line);
			line = reader.readLine();
		}
		return fromLines(lines);		
	}
	
	/**
	 * Parses given lines and loads a termMap
	 * 
	 * @param lines
	 * @return
	 */
	static PathMap fromLines(List<String> lines) {
		PathMap pathMap = new PathMap();
		pathMap.addAll(lines);
		return pathMap;
	}
	
	/**
	 * Adds all given lines into this path map
	 * 
	 * @param lines
	 */
	public void addAll(List<String> lines) {
		String key = null;
		String path = null;
		int i = 0;
		
		for(String line : lines) {
			if(line.startsWith("#")) { // skip comment
				continue;
			}
			i = line.indexOf(DELIMITER);
			if(i <= 0) {
				log.warn("Wrong formatted line skipped: " + line);				
				continue;
			}
			key = line.substring(0, i).trim();
			path = line.substring(i + 1).trim();
			addPath(key, path);
		}
	}
	
	public void addPath(String key, String path) {
		if(key == null || path == null) {
			throw new IllegalArgumentException("null key or path");
		}
		
		if( ! keyPathMap.containsKey(key)) {			
			
			keyPathMap.put(key, path);
			
		} else if( ! path.equals(keyPathMap.get(key))) {
			
			log.warn("tried to add duplicated key [" + key + 
					"] with different path: " + path);
		}
	}
	
	public int countPaths() {
		return keyPathMap.keySet().size();
	}
	 
	public String getPath(String key) {
		return keyPathMap.get(key);
	}
	
	public SortedSet<String> getKeys() {
		return new TreeSet<String>(keyPathMap.keySet());
	}
	
	/**
	 * Writes this path map to file
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public void writeToFile(String filename) throws IOException {
		List<String> paths = new ArrayList<String>();
		DateFormat df = DateFormat.getDateTimeInstance();
		paths.add("# Generated path map on " + df.format(new Date()));
		
		int maxLen = 0;
		String maxLenKey = null;
		for(Map.Entry<String, String> entry: keyPathMap.entrySet()) {
			String key = entry.getKey();
			String path = entry.getValue();
			if(key.length() > maxLen) {
				maxLen = key.length();
				maxLenKey = key;
			}
			paths.add(key + DELIMITER + path);
		}
		
		log.debug("max length of key: " + maxLen + ", key: " + maxLenKey);
		
		File pathMapFile = new File(filename);		
		FileUtils.writeLines(pathMapFile, UTF8, paths);
		
		log.info("total " + (paths.size() - 1) + 
				" line(s) of path written to file");
	}
	
	private static final String UTF8 = "UTF-8";
	private static final String DELIMITER = "=";
	private Map<String,String> keyPathMap;
	private Logger log = Logger.getLogger(PathMap.class);
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
 * The Original Code is PathMap.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2009-2010 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */