package br.com.zilics.archetypes.models.am.utils.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject;
import br.com.zilics.archetypes.models.am.template.openehrprofile.Template;
import br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateConstraint;
import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

/**
 * The {@link PathEvaluator} for {@link Template}s
 *  
 * @author Humberto Naves
 */
public class TemplatePathEvaluator implements PathEvaluator {
	private final Template root;
	
	/**
	 * The default constructor
	 * @param root the root {@link Template} context value of the A-path queries
	 */
	public TemplatePathEvaluator(Template root) {
		if (root == null) throw new NullPointerException("Null template");
		this.root = root;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> getAllChildrenNames(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof TemplateConstraint))
			throw new PathEvaluationException("Current should be TemplateConstraint");
		TemplateConstraint tc = (TemplateConstraint) current;
		if (tc.getArchetypeConstraint() instanceof CComplexObject) {
			CComplexObject complex = (CComplexObject) tc.getArchetypeConstraint();
			if (complex.getAttributes() != null) {
				return new ArrayList<String>(complex.getAttributes().keySet());
			}			
		}
		return Collections.emptyList();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<?> getChildren(ObjectValue current, String childName) throws PathEvaluationException {
		if (!(current instanceof TemplateConstraint))
			throw new PathEvaluationException("Current should be TemplateConstraint");
		TemplateConstraint tc = (TemplateConstraint) current;
		return tc.getChildrenFromAttribute(childName);
	}

	/**
	 * Auxiliary method for getting the name of an {@link ObjectValue}
	 * @param current the current value
	 * @return the name of the value
	 * @throws PathEvaluationException in case of current not being a {@link TemplateConstraint}
	 */
	private String getClassName(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof TemplateConstraint))
			throw new PathEvaluationException("Current should be TemplateConstraint");
		TemplateConstraint tc = (TemplateConstraint) current;
		return tc.getArchetypeConstraint().getRmTypeName();
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

	/**
	 * {@inheritDoc}
	 */
	public Object getMetadata(ObjectValue current, String metadataName) throws PathEvaluationException {
		if (!(current instanceof TemplateConstraint))
			throw new PathEvaluationException("Current should be TemplateConstraint");
		TemplateConstraint tc = (TemplateConstraint) current;
		if ("node_id".equals(metadataName)) {
			return tc.getArchetypeConstraint().getNodeId();
		} else if ("class".equals(metadataName)) {
			return getClassName(current);
		} else {
			return tc.getMetadata(metadataName);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getNodeName(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof TemplateConstraint))
			throw new PathEvaluationException("Current should be TemplateConstraint");
		TemplateConstraint tc = (TemplateConstraint) current;
		return tc.getParentCAttribute().getRmAttributeName();
	}

	/**
	 * {@inheritDoc}
	 */
	public ObjectValue getParent(ObjectValue current) throws PathEvaluationException {
		if (!(current instanceof TemplateConstraint))
			throw new PathEvaluationException("Current should be TemplateConstraint");
		TemplateConstraint tc = (TemplateConstraint) current;
		return tc.getParentConstraint();
	}

	/**
	 * {@inheritDoc}
	 */
	public ObjectValue getRoot() {
		return root.getRootConstraint();
	}
	
}
