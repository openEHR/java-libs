package org.openehr.rm.common.resource;

import java.util.Hashtable;

public class Annotation {
	
	public Annotation(){
		items = new Hashtable<String, String>();
	}
	
	public String getPath(){
		return path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public Hashtable<String, String> getItems(){
		return items;
	}		
	
	/* fields */	
	private String path;
	private Hashtable<String, String> items;

}
