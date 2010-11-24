/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class TermMap"
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
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * An in-memory term serving component that can be used as alternative
 * to full-blown terminology service. It can load from and write to text
 * file using the following syntax for each line. The path item is optional
 * and only required if different texts are needed for the same terminology
 * and code combination.
 * 
 * {terminology}::{code}::{text}[::{path}]
 * 
 * @author rong.chen
 *
 */
public class TermMap {

	public TermMap() {
		termMap = new TreeMap<String, Map<String, Map<String,String>>>();
	}
	
	/**
	 * Loads an in-memory termMap by given file 
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */	
	public static TermMap load(String filename) throws IOException {
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
	public static TermMap load(InputStream input) throws IOException {
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
	static TermMap fromLines(List<String> lines) {
		TermMap termMap = new TermMap();
		termMap.addAll(lines);
		return termMap;
	}
	
	/**
	 * Adds all content from given inputStream
	 * 
	 * @param input
	 * @throws Exception
	 */
	public void addAll(InputStream input) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, UTF8));
		String line = reader.readLine();
		List<String> lines = new ArrayList<String>();
		while(line != null) {
			lines.add(line);
			line = reader.readLine();
		}
		addAll(lines);
	}
	
	/**
	 * Adds all given lines into this termMap
	 * 
	 * @param lines
	 */
	public void addAll(List<String> lines) {
		String text = null;
		String terminology = null;
		String code = null;
		String path = null;
		StringTokenizer tokens = null;
		int totalTokens = 0;
		
		for(String line : lines) {
			tokens = new StringTokenizer(line, DELIMITER);
			totalTokens = tokens.countTokens();
			
			if(totalTokens != 3 && totalTokens != 4) {
		
				log.debug("Wrong formatted line skipped: " + line);
				
				continue;
			}
			terminology = tokens.nextToken();
			code = tokens.nextToken();
			text = tokens.nextToken();
			if(totalTokens == 4) {
				path = tokens.nextToken();
			} else {
				path = ""; // default for all paths
			}
			addTerm(terminology, code, text, path);
		}
	}
	
	/**
	 * Clears all terms from this termMap
	 * 
	 */
	public void clear() {
		termMap.clear();
	}
 	
	/**
	 * Adds a new term and its text into this termMap
	 * 
	 * @param terminology
	 * @param code
	 * @param text
	 * @param path		
	 */
	public void addTerm(String terminology, String code, String text,
			String path) {
		
		if(log.isDebugEnabled()) {
			log.debug("adding term: " + terminology + DELIMITER + code + 
					DELIMITER + text + DELIMITER + path);
		}
		
		Map<String,Map<String, String>> terms = termMap.get(terminology);
		if( terms == null) {
			terms = new TreeMap<String, Map<String, String>>();
			termMap.put(terminology, terms);
			
			log.debug("new terminology: " + terminology + " added..");
			
		}
		Map<String, String> paths = terms.get(code);
		if( paths == null) {
			paths = new TreeMap<String, String>();
			terms.put(code, paths);
		}
		paths.put(path, text);
	}
	
	/**
	 * Retrieves the text for given terminology and code
	 * 
	 * @param terminology
	 * @param code
	 * @param path 
	 * @return null if not found
	 */
	public String getText(String terminology, String code, String path) {
		Map<String,String> paths = null;
		String text  = null;
		
		Map<String,Map<String,String>> terms = termMap.get(terminology);
		
		if(terms != null) {
			paths = terms.get(code);
			if(paths != null) {
				if(paths.size() == 1 
						&& "".equals(paths.keySet().iterator().next())) {
					text = paths.get("");
				} else {
					text = paths.get(path);
					
					// TODO temp fix if no direct match
					if(text == null) {
						for(String p : paths.keySet()) {
							if(path.endsWith(p)) {
								text = paths.get(p);
							}
						}
					}
				}
			}
		}
		
		log.debug("Retrieved text '" + text + "' for [" + terminology + "::" 
				+ code + "] at path: " + path);
		
		return text;
	}
	
	/**
	 * Retrieves the text for given code_prhase in syntax:
	 * {terminology}::{code}
	 * 
	 * @param codePhrase
	 * @return
	 */
	public String getText(String codePhrase, String path) {
		int i = codePhrase.indexOf(DELIMITER);
		if(i <= 0 || i == codePhrase.length()) {
			throw new IllegalArgumentException(
					"wrong format, expected {terminology}::{code}");
		}
		String terminology = codePhrase.substring(0, i);
		String code = codePhrase.substring(i + 2);
		return getText(terminology, code, path);		
	}
	
	/**
	 * Retrieves the text for given code_prhase
	 * 
	 * @param codePhrase
	 * @return
	 */
	public String getText(CodePhrase codePhrase, String path) {
		return getText(codePhrase.getTerminologyId().toString(), 
				codePhrase.getCodeString(), path);
	}
	
	/**
	 * Counts the total number of terminologies
	 * 
	 * @return 0 if empty
	 */
	public int countTerminologies() {
		return termMap.size();
	}
	
	/**
	 * Gets the internal representation of terms, mainly for testing purpose
	 * 
	 * @return
	 */
	public Map<String,Map<String,Map<String,String>>> getTermMap() {
		return termMap;
	}
	
	/**
	 * Writes this termMap into external file using the following syntax:
	 * {terminology}::{code}::{text} for each line
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public void writeTermMap(String filename) throws IOException {
		StringBuffer buf = new StringBuffer();
		String terminology = null;
		String code = null;
		String text = null;
		String path = null;		
		Map<String, String> texts = null;
		Map<String,Map<String,String>> terms = null;
		
		for(Iterator<String> it = termMap.keySet().iterator(); it.hasNext();) {
			terminology = it.next();
			terms = termMap.get(terminology);
			
			for(Iterator<String> codes = terms.keySet().iterator(); 
					codes.hasNext();) {
				
				code = codes.next();
				texts = terms.get(code);
				
				for(Iterator <String> paths = texts.keySet().iterator(); 
						paths.hasNext();) {
					
					path = paths.next();
					text = texts.get(path);
					buf.append(terminology);
					buf.append(DELIMITER);				
					buf.append(code);
					buf.append(DELIMITER);
					buf.append(text);
					buf.append(DELIMITER);
					buf.append(path);
					if(codes.hasNext() || it.hasNext()) {
						buf.append("\r\n");
					}
				}
			}
		}
		FileUtils.writeStringToFile(new File(filename), buf.toString(), UTF8);
	}

	private static final String UTF8 = "UTF-8";
	private static final String DELIMITER = "::";
	private static final Logger log = Logger.getLogger(TermMap.class);
	
	// in-memory representation as [terminology, [code, [path, text]]
	private Map<String, Map<String, Map<String, String>>> termMap;  
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
 * The Original Code is TermMap.java
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