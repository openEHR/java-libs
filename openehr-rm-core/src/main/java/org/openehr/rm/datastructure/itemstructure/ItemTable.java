/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ItemTable"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/itemstructure/ItemTable.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.itemstructure;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.*;

/**
 * Purpose Logical table data structure, in which columns are named
 * and ordered. Some columns may be designated "key" columns,
 * containing key data for each row, in the manner of relational
 * tables. This allows row-naming, where each row represents a body
 * site, a blood antigen etc. All values in a column have the same
 * data type.
 * <p/>
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ItemTable extends ItemStructure {

    /**
     * Construct an ItemTable
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param representation   Cluster of Cluster
     */
    @FullConstructor
            public ItemTable(@Attribute(name = "uid") UIDBasedID uid,
                             @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                             @Attribute(name = "name", required = true) DvText name,
                             @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                             @Attribute(name = "feederAudit") FeederAudit feederAudit,
                             @Attribute(name = "links") Set<Link> links,
                             @Attribute(name = "parent") Pathable parent, 
                             @Attribute(name = "rows") List<Cluster> rows) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
        this.rows = rows;
    }
    
    /**
     * Constructs a ItemTable
     *
     * @param archetypeNodeId
     * @param name
     * @param rows null if unspecified
     */
    public ItemTable(String archetypeNodeId, DvText name, List<Cluster> rows) {
        this(null, archetypeNodeId, name, null, null, null, null, rows);
    }
    
    /**
     * Constructs a ItemTable
     *
     * @param archetypeNodeId
     * @param name
     * @param rows null if unspecified
     */
    public ItemTable(String archetypeNodeId, String name, List<Cluster> rows) {
        this(archetypeNodeId, new DvText(name), rows);
    }

    /**
     * Gets rows of this table
     * 
     * @return null if unspecified
     */
    public List<Cluster> getRows() {
    	return rows;
    }
    
    /**
     * Return the number of rows
     *
     * @return row count
     */
    public int rowCount() {
        return rows == null ? 0: rows.size();
    }

    /**
     * Return the number of columns
     *
     * @return column count
     */
    public int columnCount() {
        if(rows == null) {
        	return 0;
        }
    	Cluster firstCol = getRows().get(0);
        return firstCol.getItems().size();
    }

    /**
     * Returns the row names
     *
     * @return list of names
     */
    public List<DvText> rowNames() {
    	if(rows == null) {
    		return Collections.EMPTY_LIST;
    	}
        return fetchNames(rows);
    }

    /**
     * Return the column names
     *
     * @return List of Text
     */
    public List<DvText> columnNames() {
    	if(rows == null) {
    		return Collections.EMPTY_LIST;
    	}
        Cluster firstCol = (Cluster) getRows().get(0);
        return fetchNames(( firstCol.getItems() ));
    }

    	List<DvText> names = new ArrayList<DvText>();
    	private List<DvText> fetchNames(List<? extends Item> items) {
        for (Item item : items) {
            names.add(item.getName());
        }
        return names;
    }

    /**
     * Returns the row at specified position
     *
     * @param index starts with 1
     * @return List of element
     * @throws IndexOutOfBoundsException
     */
    public Cluster ithRow(int index) {
        if (index <= 0 || index > rowCount()) {
            throw new IndexOutOfBoundsException("invalid index");
        }
        return getRows().get(index - 1);        
    }

    /**
     * Returns true if there is a row with given name
     *
     * @param name
     * @return ture if has row with name
     */
    public boolean hasRowWithName(String name) {
        checkName(name);
        if(rows == null) {
        	return false;
        }
        return hasItemWithName(getRows(), name);
    }

    /**
     * Boolean True if there is a column with given name
     *
     * @param name
     * @return true if has column with name
     * @throws IllegalArgumentException if name null or empty
     */
    public boolean hasColumnWithName(String name) {
        checkName(name);        
        Cluster firstRow = (Cluster) getRows().get(0);
        if(rows == null) {
        	return false;
        }        
        return hasItemWithName(firstRow.getItems(), name);
    }

    private void checkName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("null or empty name");
        }
    }

    // return index of element with given name
    private int indexOf(List items, String name) {
        for (int i = 0, j = items.size(); i < j; i++) {
            Item item = (Item) items.get(i);
            if (item.getName().getValue().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private boolean hasItemWithName(List items, String name) {
        return indexOf(items, name) >= 0;
    }

    /**
     * Retrieves a row by given name
     *
     * @param name
     * @return List of element
     * @throws IllegalArgumentException if name null or empty
     *                                  or no row found for given name
     */
    public Cluster namedRow(String name) {
        checkName(name);
        if(rows == null) {
        	return null;
        }
        int index = indexOf(getRows(), name);
        if (index < 0) {
            throw new IllegalArgumentException("unknow row name: " + name);
        }
        return (Cluster)getRows().get(index);
        
    }

    /**
     * True if there is a row whose first n columns have the names
     * in given keys
     *
     * @param keys set of string
     * @return true if has row with key
     * @throws IllegalArgumentException if keys null
     */
    public boolean hasRowWithKey(Set keys) {
        // todo: implement it (problem to understand the spec)
        return false;
    }

    /**
     * Return the row whose first n columns have names equal to the
     * values in  keys
     *
     * @param key
     * @return List of row
     */
    public List<Element> rowWithKey(Set key) {
        List<Element> rows = new ArrayList<Element>();
        // todo: fix it (problem to understand the spec)
        return rows;
    }

    /**
     * Return the element at specified column and row
     *
     * @param column
     * @param row
     * @return Element found at (col, row)
     * @throws IllegalArgumentException if col <= 0 or col > columnCount 
     *                                  or row <= 0 or row > rowCount
     */
    public Element elementAtCell(int column, int row) {
        if (row <= 0 || row > rowCount()) {
            throw new IllegalArgumentException("invalid row index: " + row);
        }
        Cluster targetRow = (Cluster) getRows().get(row - 1);
        if (column <= 0 || column > targetRow.getItems().size()) {
            throw new IllegalArgumentException("invalid column index: " + column);
        }
        return (Element) targetRow.getItems().get(column - 1);
    }

    /**
     * Retrieve the element at the row whose first column has the name
     * colKey and row has the name rowKey
     *
     * @param colKey
     * @param rowKey
     * @return Element found by keys
     * @throws IllegalArgumentException if either key null or empty
     *                                  or no row found for given keys
     */
    public Element elementAtNamedCell(String rowKey, String colKey) {
        if (StringUtils.isEmpty(colKey) ||
                StringUtils.isEmpty(rowKey)) {
            throw new IllegalArgumentException("invalid keys: "
                    + colKey + ", " + rowKey);
        }
        Cluster firstRow = namedRow(rowKey);
        int col = indexOf(firstRow.getItems(), colKey);
        if (col < 0 ) {
            throw new IllegalArgumentException(
                    "unknown keys: " + colKey + ", " + rowKey);
        }
        return (Element)firstRow.getItems().get(col);        
    }

    /**
     * Return the path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return path of given item
     */
    public String pathOfItem(Pathable item) {
        return null;  // todo: implement this method
    }

    protected static List<Cluster> structureCheck(List<Cluster> rows) {
        if(rows == null) {
        	return null;
        }
    	for(Cluster row : rows) {            
	        for(Item col : ((Cluster)row).getItems()) {
	            if(!(col instanceof Element)) {
	                throw new IllegalArgumentException("invalid col type for itemTable, Element expected");
	            }
	        }
        }
        return rows;
    }
    
    @Override
	public List<Object> itemsAtPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pathExists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((names == null) ? 0 : names.hashCode());
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
		return result;
	}

	@Override
	//Generated by Eclipse
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		ItemTable other = (ItemTable) obj;
		if (names == null) {
			if (other.names != null)
				return false;
		} else if (!names.equals(other.names))
			return false;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
			return false;
		return true;
	}

	// POJO start
    ItemTable() {
    }
    // POJO end
    
    private List<Cluster> rows;

	@Override
	public Item asHierarchy() {
		// TODO Auto-generated method stub
		return null;
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
 *  The Original Code is ItemTable.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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