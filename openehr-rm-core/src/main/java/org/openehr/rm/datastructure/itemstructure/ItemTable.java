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
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Purpose Logical table data structure, in which columns are named
 * and ordered. Some columns may be designated "key" columns,
 * containing key data for each row, in the manner of relational
 * tables. This allows row-naming, where each row represents a body
 * site, a blood antigen etc. All values in a column have the same
 * data type.
 * <p/>
 * Instances of this class are immutable.
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
            public ItemTable(@Attribute(name = "uid") ObjectID uid,
                             @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                             @Attribute(name = "name", required = true) DvText name,
                             @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                             @Attribute(name = "feederAudit") FeederAudit feederAudit,
                             @Attribute(name = "links") Set<Link> links,
                             @Attribute(name = "parent") Locatable parent, 
                             @Attribute(name = "representation", required = true) Cluster representation) {
        // todo: check invariants
        // Valid_structure: representation.items.forall({ITEM}.type =  CLUSTER
        // and then {ITEM}.items.forall({ITEM}.type =  ELEMENT ))
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent, structureCheck(representation));
    }

    /**
     * Construct a ItemStructure
     *
     * @param archetypeNodeId
     * @param name
     * @param representation
     * @throws IllegalArgumentException if representation null
     */
    public ItemTable(String archetypeNodeId, DvText name,
                     Cluster representation) {
        this(null, archetypeNodeId, name, null, null, null, null, representation);
    }

    /**
     * Gets rows
     * 
     * @return rows
     */
    public List<Item> getRows() {
        return ((Cluster) getRepresentation() ).getItems();
    }
    
    /**
     * Return the number of rows
     *
     * @return row count
     */
    public int rowCount() {
        //Cluster firstCol = (Cluster) columns().get(0);
        //return firstCol.getItems().size();
        return getRows().size();
    }

    /**
     * Return the number of columns
     *
     * @return column count
     */
    public int columnCount() {
        Cluster firstCol = (Cluster) getRows().get(0);
        return firstCol.getItems().size();
    }

    /**
     * Return the row names
     *
     * @return List of Text
     */
    public List<DvText> rowNames() {
        //Cluster firstCol = (Cluster) columns().get(0);
        //return fetchNames(( firstCol.getItems() ));
        return fetchNames(getRows());
    }

    /**
     * Return the column names
     *
     * @return List of Text
     */
    public List<DvText> columnNames() {
        //return fetchNames(columns());
        Cluster firstCol = (Cluster) getRows().get(0);
        return fetchNames(( firstCol.getItems() ));
    }

    private List<DvText> fetchNames(List<Item> items) {
        List<DvText> names = new ArrayList<DvText>();
        for (Item item : items) {
            names.add(item.getName());
        }
        return names;
    }

    /**
     * Return row at specified position
     *
     * @param index starts with 0
     * @return List of element
     * @throws IndexOutOfBoundsException
     */
    public Cluster ithRow(int index) {
        if (index < 0 || index >= rowCount()) {
            throw new IndexOutOfBoundsException("invalid index");
        }
        return (Cluster)getRows().get(index);
        /*List<Element> rows = new ArrayList<Element>();
        for (Item item : columns()) {
            Cluster column = (Cluster) item;
            rows.add((Element) column.getItems().get(index));
        }
        return rows;
         */
    }

    /**
     * Boolean True if there is a row with given name
     *
     * @param name
     * @return ture if has row with name
     */
    public boolean hasRowWithName(String name) {
        checkName(name);
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
     * Retrieve row by given name
     *
     * @param name
     * @return List of element
     * @throws IllegalArgumentException if name null or empty
     *                                  or no row found for given name
     */
    public Cluster namedRow(String name) {
        checkName(name);
        /*Cluster firstCol = (Cluster) columns().get(0);
        int index = indexOf(firstCol.getItems(), name);
        if (index < 0) {
            throw new IllegalArgumentException("unknow row name: " + name);
        }
        List<Element> rows = new ArrayList<Element>();
        for (Item item : columns()) {
            Cluster column = (Cluster) item;
            rows.add((Element) column.getItems().get(index));
        }
        return rows;*/
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
     * @param col
     * @param row
     * @return Element found at (col, row)
     * @throws IllegalArgumentException if col < 0
     *                                  or col >= columnCount or row < 0 or row >= rowCount
     */
    public Element elementAtCell(int row, int col) {
        if (row < 0 || row >= rowCount()) {
            throw new IllegalArgumentException("invalid row index: " + row);
        }
        Cluster targetRow = (Cluster) getRows().get(row);
        if (col < 0 || col >= targetRow.getItems().size()) {
            throw new IllegalArgumentException("invalid column index: " + col);
        }
        return (Element) targetRow.getItems().get(col);
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
        /*Cluster firstCol = (Cluster) columns().get(0);
        int col = indexOf(columns(), colKey);
        int row = indexOf(firstCol.getItems(), rowKey);
        if (col < 0 || row < 0) {
            throw new IllegalArgumentException(
                    "unknown keys: " + colKey + ", " + rowKey);
        }
        return elementAtCell(col, row);*/
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

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return item
     * @throws IllegalArgumentException if path invalid
     */
    public Locatable itemAtPath(String path) {
        String whole = whole();
        if (whole.equals(path)) {
            return this;
        }
        if (path.length() <= whole.length()) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        String subpart = path.substring(whole.length() + 1);
        if (subpart.startsWith(ROW_IS)) { // fetch a row

            // todo: how to hold a row with locatable ?
            throw new IllegalArgumentException("invalid path: " + path);

        } else if (subpart.startsWith(COL_IS)) { // fetch a cell
            subpart = subpart.substring(COL_IS.length());
            int index = subpart.indexOf(ItemStructure.PATH_SEPARATOR);
            if (index <= 0) {
                throw new IllegalArgumentException("invalid path: " + path);
            }
            String colname = subpart.substring(0, index);
            Item item = itemWithName(getRows(), colname, path);
            if(item == null) {
                throw new IllegalArgumentException("invalid path: " + path);
            }
            Cluster column = (Cluster) item;
            subpart = subpart.substring(index + 1);
            if(! subpart.startsWith(ROW_IS)
                    || subpart.length() <= ROW_IS.length()) {
                throw new IllegalArgumentException("invalid path: " + path);
            }
            String rowname = subpart.substring(ROW_IS.length());
            return itemWithName(column.getItems(), rowname, path);
        } else { // fetch a column
            return itemWithName(getRows(), subpart, path);
        }
    }

    protected static Cluster structureCheck(Cluster representation) {

        for(Item row : representation.getItems()) {
            if(row instanceof Cluster) {
                for(Item col : ((Cluster)row).getItems()) {
                    if(!(col instanceof Element)) {
                        throw new IllegalArgumentException("invalid col type for itemTable, Element expected");
                    }
                }
            } else {
                throw new IllegalArgumentException("invalid row type for itemTable, Cluster expected");
            }
        }
        return representation;
    }
    
    // return row with given name or null
    private Item itemWithName(List<Item> items, String name, String path) {
        for (Item item : items) {
            if (item.getName().getValue().equals(name)) {
                return item;
            }
        }
        throw new IllegalArgumentException("invalid path: " + path);
    }

    /**
     * Return true if the path is valid with respect to the current
     * item.
     *
     * @param path
     * @return true if valid
     */
    public boolean validPath(String path) {
        try {
            itemAtPath(path);
            return true;
        } catch(IllegalArgumentException iae) {
            return false;
        }
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

    /* tokens used in the query path */
    public static final String ROW_IS = "row=";
    public static final String COL_IS = "col=";

    // POJO start
    ItemTable() {
    }
    // POJO end
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