/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class RMUtil"
 * keywords:    "rm-skeleton"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2009,2010 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.util;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Action;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.composition.content.entry.AdminEntry;
import org.openehr.rm.composition.content.entry.Entry;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.schemas.v1.ACTIVITY;

/**
 * Support class that provide various utility functions
 * 
 * TODO
 * some of these functions could end up in RM classes themselves 
 * 
 * @author rong.chen
 *
 */

public class RMUtil {
	
	/**
	 * Clears away unnecessary RM structure in given composition
	 * 
	 * The following structures are purged
	 * 1. section with empty list of items
	 * 2. entry with empty items as data or other key attributes
	 * 
	 * TODO
	 * only entry type Observation is expected for now
	 * 
	 * @param composition
	 */
	public static void purge(Composition composition) {
		
		log.debug("Composition.purge() started..");		
		
		List<ContentItem> items = composition.getContent();
		
		for(Iterator<ContentItem> it = items.iterator(); it.hasNext();) {
			ContentItem item = it.next();
			if(isEmpty(item)) {
			
				it.remove();
				
				log.debug("item of class " + item.getClass() + ", removed..");
			
			} else if(item instanceof Section) {
				
				Section section = (Section) item;
				purge(section);				
			}
		}
		
		log.debug("Composition.purge() finished..");
	}
	
	
	/**
	 * Purges the given section. Recursive function.
	 * 
	 * 
	 * @param section
	 */
	public static void purge(Section section) {
	
		log.debug("Section.purge() started.. ");
		
		List<ContentItem> items = section.getItems(); 
		
		for(Iterator<ContentItem> it = items.iterator(); it.hasNext();) {
			ContentItem item = it.next();
			
			if(isEmpty(item)) {
			
				it.remove();
			
				log.debug("item of class " + item.getClass() + ", removed..");
			
			} else if(item instanceof Section) {
				
				Section subsection = (Section) item;
				purge(subsection);				
			}
		}
		
		log.debug("Section.purge() finished.. ");
	}
	
	private static boolean isEmpty(Section section) {
		List<ContentItem> items = section.getItems();
		if(items.isEmpty()) {
			return true;
		}
		for(Iterator<ContentItem> it = items.iterator(); it.hasNext();) {
			ContentItem item = it.next();
			if( ! isEmpty(item)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isEmpty(ContentItem item) {
		if(item instanceof Section) {
			return isEmpty((Section) item);
		} else {
			return isEmpty((Entry) item);
		}
	}

	private static boolean isEmpty(Entry entry) {	
		
		if(entry instanceof Observation) {
			
			Observation obs = (Observation) entry;
			return isEmpty(obs.getData());
	
		} else if(entry instanceof Evaluation) {
		
			Evaluation eval = (Evaluation) entry;
			return isEmpty(eval.getData());
		
		} else if(entry instanceof Instruction) {
			
			Instruction intr = (Instruction) entry;
			List<Activity> activities = intr.getActivities();
			if(activities.isEmpty()) {
				return true;
			}
			for(Iterator<Activity> it = activities.iterator(); it.hasNext();) {
				Activity activity = it.next();
				if( ! isEmpty(activity.getDescription())) {
					return false;
				}
			}
			return true;
		
		} else if(entry instanceof Action) {
			
			Action action = (Action) entry;
			return isEmpty(action.getDescription());
		
		} else if(entry instanceof AdminEntry) {
			
			AdminEntry admin = (AdminEntry) entry;
			return isEmpty(admin.getData());
		} else {
			throw new UnsupportedOperationException("unsupported entry type");
		}
	}
	
	private static boolean isEmpty(History<? extends ItemStructure> history) {
		if(history.getEvents().isEmpty()) {
			return true;
		}
		for(Iterator it = history.getEvents().iterator(); it.hasNext();) {
			Event event = (Event) it.next();
			if( ! isEmpty(event.getData())) {
				return false;
			}
		}
		return true;
	}	
	
	private static boolean isEmpty(ItemStructure structure) {
		if(structure instanceof ItemList) {
			
			ItemList list = (ItemList) structure;
			return list.getItems().isEmpty();
			
		} else if(structure instanceof ItemTree) {
			
			ItemTree tree = (ItemTree) structure;
			return tree.getItems().isEmpty();
			
		} else {
			// TODO more types
			throw new UnsupportedOperationException();
		}
	}
	
	private static final Logger log = Logger.getLogger(RMUtil.class);
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
 * The Original Code is RMUtil.java
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