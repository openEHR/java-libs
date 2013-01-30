package br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation;

import java.util.List;


/**
 * The grouping variant of Item, which may contain further instances of ITEM, in
 * an ordered list.
 * @author Humberto
 */
public class Cluster extends Item {


	private static final long serialVersionUID = -5879924040379156347L;
	private List<Item> items;


    /**
     * Ordered list of items - CLUSTER or ELEMENT objects - under this CLUSTER.
     * @return List of items
     */
    public List<Item> getItems(){
        return getList(items);
    }


    /**
     * Set the list of items
     * @param items List of items
     */
    public void setItems(List<Item> items){
		assertMutable();
        this.items = items;
    }

}