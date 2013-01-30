
package br.com.zilics.archetypes.models.rm.datastructure.itemstructure;

import java.util.List;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Cluster;

/**
 * Purpose Logical table data structure, in which columns are named
 * and ordered. Some columns may be designated "key" columns,
 * containing key data for each row, in the manner of relational
 * tables. This allows row-naming, where each row represents a body
 * site, a blood antigen etc. All values in a column have the same
 * data type.
 *
 * @author Humberto
 */
public class ItemTable extends ItemStructure {

	private static final long serialVersionUID = 6508879427771760237L;
	private List<Cluster> rows;

    /**
     * Get the rows
     * @return Physical representation of the table as a list of {@link br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Cluster}s, each containing the data of one row of the table.
     */
    public List<Cluster> getRows() {
        return getList(rows);
    }

    /**
     * Set the rows
     * @param rows Physical representation of the table as a list of {@link br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Cluster}s, each containing the data of one row of the table.
     */
    public void setRows(List<Cluster> rows) {
		assertMutable();
        this.rows = rows;
    }

}
