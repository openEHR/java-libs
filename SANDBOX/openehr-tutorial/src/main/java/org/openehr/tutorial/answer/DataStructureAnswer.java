package org.openehr.tutorial.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.tutorial.DataStructureExercise;

/**
 * Answers of coding exercises on data structure classes
 * 
 * @author rong.chen
 */
public class DataStructureAnswer extends DataStructureExercise {
	
	public Element createElement() {
		DvQuantity quantity = new DvQuantity("mmol/l", 100.0, 0, measureServ);
    	Element element = new Element("at0013.1", new DvText("Blood glucose"),
    			quantity);
    	return element;
	}
	
	public ItemTree createItemTree() {
		List<Item> items = new ArrayList<Item>();
		items.add(createElement());
    	Cluster cluster = new Cluster("at0004", new DvText("cluster"), items);    	
    	ItemTree tree = new ItemTree("at0004", new DvText("Tree"), cluster);   
    	return tree;
	}	
	
	public PointEvent<ItemStructure> createPointEvent() {
		ItemTree data = createItemTree();
		DvText name = new DvText("any event");
		String archetypeNodeId = "at0002";
		DvDateTime time = new DvDateTime();
		
		return new PointEvent<ItemStructure>(null, archetypeNodeId, 
    			name, null, null, null, null, time, data, 
    			null);
	}
	
	public History<ItemStructure> createHistory() {
		UIDBasedID uid = null;
    	String archetypeNodeId = "at0001";
        DvText name = new DvText("data");
        Archetyped archetypeDetails = null;
        FeederAudit feederAudit = null;
        Set<Link> links = null;
        Locatable parent = null; 
        DvDateTime origin = new DvDateTime("2007-07-25T23:11:11");
        List<Event<ItemStructure>> events = new ArrayList<Event<ItemStructure>>();
        PointEvent<ItemStructure> point = createPointEvent();
        events.add(point);
        
        DvDuration period = null;
        DvDuration duration = null;
        ItemStructure summary = null;
    	
    	return new History<ItemStructure>(uid, archetypeNodeId, name,
    			archetypeDetails, feederAudit, links, parent, origin, events, 
    			period, duration, summary);
	}	
}
