package org.openehr.rm.common.directory;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;

public class Folder extends Locatable {

    /**
     * Construts a Folder
     *
     * @param uid              null if not specified
     * @param archetypeNodeId  not null
     * @param name             not null
     * @param archetypeDetails null if not specified
     * @param feederAudit      null if not specified
     * @param links            null if not specified
     * @param folders			 null if not specified
     * @param items			 null if not specified
     * @throws IllegalArgumentException if name null or archetypeNodeId null
     *                                  or links not null and empty
     */
    public Folder(ObjectID uid, String archetypeNodeId, DvText name,
            Archetyped archetypeDetails, FeederAudit feederAudit, Set<Link> links,
            Locatable parent, List<Folder> folders, List<ObjectReference> items) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if(folders != null && folders.size() == 0) {
            throw new IllegalArgumentException("empty folders");
        }
        if (items != null && items.isEmpty()) {
            throw new IllegalArgumentException("empty compositions");
        } //TODO: necessary?
        this.folders = folders;
        this.items = items;
    }
    
    /**
     * Sub-folders of this FOLDER
     * @return folder  	A list of folders which are the sub-folders of this FOLDER
     */
    public List<Folder> getFolders() {
        return folders;
    }
    
    /**
     * The list of references to other versioned objects logically in this FOLDER
     * @return items 	List of versioned items in this FOLDER
     */
    public List<ObjectReference> getItems() {
        return items;
    }
    
    /**
     * Equals if two folders have the same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Folder )) return false;
        if (!super.equals(o)) return false;
        
        final Folder folder = (Folder) o;
        return new EqualsBuilder()
//                .appendSuper(super.equals(o))
        .append(folders, folder.folders)
        .append(items, folder.items)
        .isEquals();
    }
    
    /**
     * Return a hash code of this folder
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(41, 97)
        .appendSuper(super.hashCode())
        .append(folders)
        .append(items)
        .toHashCode();
    }
    @Override
            public String pathOfItem(Locatable item) {
        // TODO Auto-generated method stub
        return null;
    }
    
    //POJO starts
    Folder() {
    }
    
    void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
    
    void setItems(List<ObjectReference> items) {
        this.items = items;
    }
    //POJO ends
    
    /* fields */
    private List<Folder> folders;
    private List<ObjectReference> items;
}
