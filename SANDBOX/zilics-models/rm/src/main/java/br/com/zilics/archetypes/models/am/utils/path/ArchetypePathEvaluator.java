package br.com.zilics.archetypes.models.am.utils.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;
import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

/**
 * The {@link PathEvaluator} for {@link Archetype}s
 *  
 * @author Humberto Naves
 */
public final class ArchetypePathEvaluator implements PathEvaluator {
	private final Archetype root;
	
	/**
	 * The default constructor
	 * @param root the root {@link Archetype} context value of the A-path queries
	 */
	public ArchetypePathEvaluator(Archetype root) {
		if (root == null) throw new NullPointerException("Null archetype");
		this.root = root;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<?> getChildren(ObjectValue current, String childName) throws PathEvaluationException {
		if (!(current instanceof CObject))
			throw new PathEvaluationException("Current should be CObject");
		CObject obj = (CObject) current;
		if (obj instanceof CComplexObject) {
			CComplexObject complex = (CComplexObject) obj;
			if (complex.getAttributes() != null) {
				CAttribute attribute = complex.getAttributes().get(childName);
				if (attribute != null) {
					return attribute.getChildren();
				}
			}
		}
		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getMetadata(ObjectValue current, String metadataName) throws PathEvaluationException {
		if (!(current instanceof CObject))
			throw new PathEvaluationException("Current should be CObject");
		CObject obj = (CObject) current;
		if ("node_id".equals(metadataName)) {
			return obj.getNodeId();
		} else if ("class".equals(metadataName)) {
			return getClassName(current);
		} else {
			return obj.getMetadata(metadataName);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ObjectValue getParent(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof CObject))
			throw new PathEvaluationException("Current should be CObject");
		CObject obj = (CObject) current;
		if (obj.getParent() != null)
			return obj.getParent().getOwnerConstraint();
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public ObjectValue getRoot() {
		return root.getDefinition();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> getAllChildrenNames(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof CObject))
			throw new PathEvaluationException("Current should be CObject");
		CObject obj = (CObject) current;
		if (obj instanceof CComplexObject) {
			CComplexObject complex = (CComplexObject) obj;
			if (complex.getAttributes() != null)
				return new ArrayList<String>(complex.getAttributes().keySet());
		}
		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getNodeName(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof CObject))
			throw new PathEvaluationException("Current should be CObject");
		CObject obj = (CObject) current;
		if (obj.getParent() != null)
			return obj.getParent().getRmAttributeName();
		return null;
	}

	/**
	 * Auxiliary method for getting the name of an {@link ObjectValue}
	 * @param current the current value
	 * @return the name of the value
	 * @throws PathEvaluationException in case of current not being a {@link CObject}
	 */
	private String getClassName(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof CObject))
			throw new PathEvaluationException("Current should be CObject");
		CObject obj = (CObject) current;
		return obj.getRmTypeName();		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isInstanceOf(ObjectValue current, String className) throws PathEvaluationException {
		String subclass = getClassName(current);
		RmClassData subclassData = IntrospectorData.getRmClassDataByRmClassName(subclass);
		RmClassData classData = IntrospectorData.getRmClassDataByRmClassName(className);
		if (subclassData == null) throw new PathEvaluationException("Unknown class " + subclass);
		if (classData == null) throw new PathEvaluationException("Unknown class " + className);
		return classData.getJavaClass().isAssignableFrom(subclassData.getJavaClass());
	}

}
