package br.com.zilics.archetypes.models.workbench.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject;

public class ArchetypeTreeComponent extends JTree {

	private static final long serialVersionUID = -8540918036419213574L;
	
	private Archetype archetype;
	
	public ArchetypeTreeComponent() {
		super((TreeModel) null);
		super.setModel(new ArchetypeTreeModel());
		super.setCellEditor(null);
	}
	
	@Override
	public void setCellEditor(TreeCellEditor editor) {
		throw new IllegalArgumentException("Can't set cell editor");
	}
	
	@Override
	public void setModel(TreeModel model) {
		if (model instanceof ArchetypeTreeModel)
			super.setModel(model);
		if (model != null)
			throw new IllegalArgumentException("Can't set this model");
	}

	public Archetype getArchetype() {
		return archetype;
	}
	
	public void setArchetype(Archetype archetype) {
		this.archetype = archetype;
		this.updateUI();
	}
	
	private class ArchetypeTreeModel implements TreeModel {

		public void addTreeModelListener(TreeModelListener l) {
		}

		public void removeTreeModelListener(TreeModelListener l) {
		}


		public Object getChild(Object parent, int index) {
			if (index < 0 || index >= getChildCount(parent)) return null;
			if (parent instanceof CComplexObject) {
				CComplexObject ccompl = (CComplexObject) parent;
				List<CAttribute> children = new ArrayList<CAttribute>(ccompl.getAttributes().values());
				return children.get(index);
			} else if (parent instanceof CAttribute) {
				CAttribute attrib = (CAttribute) parent;
				return attrib.getChildren().get(index);
			}			
			return null;
		}

		public int getChildCount(Object parent) {
			if (parent instanceof CComplexObject) {
				CComplexObject ccompl = (CComplexObject) parent;
				if (ccompl.getAttributes() != null)
					return ccompl.getAttributes().size();
			} else if (parent instanceof CAttribute) {
				CAttribute attrib = (CAttribute) parent;
				if (attrib.getChildren() != null)
					return attrib.getChildren().size();
			}
			return 0;
		}

		public int getIndexOfChild(Object parent, Object child) {
			if (parent instanceof CComplexObject) {
				CComplexObject ccompl = (CComplexObject) parent;
				List<CAttribute> children = new ArrayList<CAttribute>(ccompl.getAttributes().values());
				return children.indexOf(child);
			} else if (parent instanceof CAttribute) {
				CAttribute attrib = (CAttribute) parent;
				return attrib.getChildren().indexOf(child);
			}
			return -1;
		}

		public Object getRoot() {
			if (ArchetypeTreeComponent.this.archetype != null)
				return ArchetypeTreeComponent.this.archetype.getDefinition();
			return null;
		}

		public boolean isLeaf(Object node) {
			return (getChildCount(node) == 0);
		}

		public void valueForPathChanged(TreePath path, Object newValue) {
			throw new IllegalArgumentException("Editor is not supported");
		}
		
	}
}
