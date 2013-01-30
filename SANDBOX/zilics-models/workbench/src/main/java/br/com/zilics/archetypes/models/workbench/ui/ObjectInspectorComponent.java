package br.com.zilics.archetypes.models.workbench.ui;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import br.com.zilics.archetypes.models.workbench.ui.treetable.JTreeTable;
import br.com.zilics.archetypes.models.workbench.ui.treetable.TreeTableModel;


public class ObjectInspectorComponent extends JTreeTable {

	private static final long serialVersionUID = -8374341082111873283L;
	private ObjectInspectorTreeTableModel model;
	
	public ObjectInspectorComponent() {
		this(new ObjectInspectorTreeTableModel());
	}
	
	private ObjectInspectorComponent(ObjectInspectorTreeTableModel model) {
		super(model);
		//super.setCellEditor(null);
		this.model = model;
	}
	
	public Object getInspectedObject() {
		if (model.root == null) return null;
		return model.root.getValue();
	}
	
	public void setInspectedObject(Object inspectedObject) {
		model.root = new ObjectInspectorTreeNode(inspectedObject, inspectedObject.getClass().getSimpleName().toLowerCase());
		this.updateUI();
	}
	
	private static class ObjectInspectorTreeTableModel implements TreeTableModel {
		private ObjectInspectorTreeNode root;

		public void addTreeModelListener(TreeModelListener l) {
		}

		public void removeTreeModelListener(TreeModelListener l) {
		}


		public Object getChild(Object parent, int index) {
			ObjectInspectorTreeNode p = (ObjectInspectorTreeNode) parent;
			if (index < 0 || index >= p.getChildren().size()) return null;
			return p.getChildren().get(index);
		}

		public int getChildCount(Object parent) {
			return ((ObjectInspectorTreeNode) parent).getChildren().size();
		}

		public int getIndexOfChild(Object parent, Object child) {
			ObjectInspectorTreeNode p = (ObjectInspectorTreeNode) parent;
			ObjectInspectorTreeNode c = (ObjectInspectorTreeNode) child;
			if (c.getParent() != p) return -1;
			return c.getIndex();
		}

		public Object getRoot() {
			return root;
		}

		public boolean isLeaf(Object node) {
			return getChildCount(node) == 0;
		}

		public void valueForPathChanged(TreePath path, Object newValue) {
			throw new IllegalArgumentException("Editor is not supported");
		}

		public Class<?> getColumnClass(int column) {
			if (column == 0)
				return TreeTableModel.class;
			else
				return String.class;
		}

		public int getColumnCount() {
			return 2;
		}

		public String getColumnName(int column) {
			if (column == 0)
				return "Object";
			else
				return "Value";
		}

		public Object getValueAt(Object node, int column) {
			ObjectInspectorTreeNode n =  ((ObjectInspectorTreeNode) node);
			if (column == 0)
				return node;
			else {
				if (n.isWrappedTypeOrString()) {
					if (n.getValue() instanceof String)
						return "\"" + n.getValue() + "\"";
					return n.getValue().toString();
				} else {
					if (n.getValue() == null) return "null";
					return n.getValue().getClass().getSimpleName() + " (id = $" + Integer.toHexString(System.identityHashCode(n.getValue())) + ")";
				}
			}
		}

		public boolean isCellEditable(Object node, int column) {
			if (column == 0) return true;
			return false;
		}

		public void setValueAt(Object value, Object node, int column) {
			throw new IllegalArgumentException("Editor is not supported");
		}
		
	}
	
	private static class ObjectInspectorTreeNode implements Serializable {

		private static final long serialVersionUID = -8619917185006479686L;
		
		private String name;
		private ObjectInspectorTreeNode parent;
		private Object value;
		private int index;
		private List<ObjectInspectorTreeNode> children;
		
		private ObjectInspectorTreeNode(Object value, ObjectInspectorTreeNode parent, String name, int index) {
			this.value = value;
			this.index = index;
			this.name = name;
			this.parent = parent;
		}
		
		public ObjectInspectorTreeNode(Object value, String name) {
			this(value, null, name, 0);
		}
		
		public void setName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setParent(ObjectInspectorTreeNode parent) {
			this.parent = parent;
		}
		public ObjectInspectorTreeNode getParent() {
			return parent;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public Object getValue() {
			return value;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getIndex() {
			return index;
		}

		public boolean isWrappedTypeOrString() {
			return (value instanceof Boolean) ||
				(value instanceof Double) ||
				(value instanceof Float) ||
				(value instanceof Short) ||
				(value instanceof Character) ||
				(value instanceof Long) ||
				(value instanceof Integer) ||
				(value instanceof String);
			
		}
		
		public List<ObjectInspectorTreeNode> getChildren() {
			if (children == null) {
				if (value == null) {
					return Collections.emptyList();
				} else if (value.getClass().isArray()) {
					int len = Array.getLength(value);
					children = new ArrayList<ObjectInspectorTreeNode>(len);
					for(int i = 0; i < len; i++) {
						ObjectInspectorTreeNode child = new ObjectInspectorTreeNode(Array.get(value, i), this, Integer.toString(i), i);
						children.add(child);
					}
				} else if (isWrappedTypeOrString()) {
					return Collections.emptyList();
				} else {
					Class<?> clazz = value.getClass();
					children = new ArrayList<ObjectInspectorTreeNode>();
					int index = 0;
					while (clazz != Object.class) {
						Field[] fields = clazz.getDeclaredFields();
						for(Field field : fields) {
							if (Modifier.isStatic(field.getModifiers())) continue;
							field.setAccessible(true);
							try {
								Object v = field.get(value);
								ObjectInspectorTreeNode child = new ObjectInspectorTreeNode(v, this, field.getName(), index++);
								children.add(child);
							} catch (Exception e) {
								throw new RuntimeException(e);
							}
						}
						clazz = clazz.getSuperclass();
					}
				}
			}
			return children;
		}
		
		private boolean compareObject(Object o1, Object o2) {
			if (o1 == o2) return true;
			if (o1 == null || o2 == null) return false;
			return o1.equals(o2);
		}


		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (! (obj instanceof ObjectInspectorTreeNode)) return false;
			ObjectInspectorTreeNode other = (ObjectInspectorTreeNode) obj;
			if (other.index != index) return false;
			return compareObject(other.value, value) && compareObject(other.parent, parent) && compareObject(other.name, name);
		}
		
		@Override
		public int hashCode() {
			final int PRIME = 31;
			int hash = 0;
			if (value != null) hash += value.hashCode();
			hash = hash * PRIME;
			if (name != null) hash += name.hashCode();
			hash = hash * PRIME;
			if (parent != null) hash += parent.hashCode();
			hash = hash * PRIME + index;
			return hash;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		
		
	}
}
