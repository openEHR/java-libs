package br.com.zilics.archetypes.models.rm.common.directory;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectRef;

/**
 * The concept of a named folder.
 * @author Humberto
 */
public class Folder extends Locatable {
	private static final long serialVersionUID = 233143169803537412L;
	@EqualsField
	private List<Folder> folders;
	@EqualsField
    private List<ObjectRef> items;

    /**
     * Get the folders
     * @return Sub-folders of this folder.
     */
    public List<Folder> getFolders() {
        return getList(folders);
    }

    /**
     * Set the folders
     * @param folders Sub-folders of this folder.
     */
    public void setFolders(List<Folder> folders) {
    	assertMutable();
        this.folders = folders;
    }

    /**
     * Get the items
     * @return The list of references to other (usually) versioned objects logically in this folder
     */
    public List<ObjectRef> getItems() {
        return getList(items);
    }

    /**
     * Set the items
     * @param items The list of references to other (usually) versioned objects logically in this folder
     */
    public void setItems(List<ObjectRef> items) {
    	assertMutable();
        this.items = items;
    }
}
