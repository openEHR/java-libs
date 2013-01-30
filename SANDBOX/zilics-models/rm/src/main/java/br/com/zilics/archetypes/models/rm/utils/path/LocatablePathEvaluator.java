package br.com.zilics.archetypes.models.rm.utils.path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.zilics.archetypes.models.am.template.openehrprofile.Template;
import br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateConstraint;
import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.exception.IntrospectorException;
import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

/**
 * The {@link PathEvaluator} for {@link Locatable}s
 * @author Humberto Naves
 *
 */
public class LocatablePathEvaluator implements PathEvaluator {
	private final Locatable root;
	private final Template template;
	
	/**
	 * The default constructor
	 * @param root the root of the evaluation context
	 * @param template the helper template to aid the navigation and checking
	 */
	public LocatablePathEvaluator(Locatable root, Template template) {
		if (root == null) throw new NullPointerException("Null locatable");
		this.root = root;
		this.template = template;
	}	

	/**
	 * {@inheritDoc}
	 */
	public List<String> getAllChildrenNames(ObjectValue current) throws PathEvaluationException {
		RmClassData classData = IntrospectorData.getRmClassDataByJavaClass(current.getClass());
		return new ArrayList<String>(classData.getAllFieldRmNames());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<?> getChildren(ObjectValue current, String childTest) throws PathEvaluationException {
		RmClassData classData = IntrospectorData.getRmClassDataByJavaClass(current.getClass());
		Object value;
		try {
			value = classData.getRmFieldByRmName(childTest).getValue(current);
		} catch (IntrospectorException e) {
			throw new PathEvaluationException("Error while getting child", e);
		}
		if (value == null) return Collections.emptyList();
		if (value instanceof Collection) {
			return new ArrayList((Collection) value);
		} else if (value instanceof Map) {
			return new ArrayList(((Map) value).values());
		}
		ArrayList<Object> result = new ArrayList<Object>();
		result.add(value);
		return result;
	}

	private String getClassName(ObjectValue current) throws PathEvaluationException {
		RmClassData classData = IntrospectorData.getRmClassDataByJavaClass(current.getClass());
		return classData.getRmClassName();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isInstanceOf(ObjectValue current, String className) throws PathEvaluationException {
		RmClassData classData = IntrospectorData.getRmClassDataByRmClassName(className);
		if (classData == null) throw new PathEvaluationException("Unknown class " + className);
		return classData.getJavaClass().isAssignableFrom(current.getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getMetadata(ObjectValue current, String metadataName) throws PathEvaluationException {
		if (! (current instanceof Locatable))
			return null;
		Locatable l = (Locatable) current;
		if ("node_id".equals(metadataName)) {
			return l.getArchetypeNodeId();
		} else if ("class".equals(metadataName)) {
			return getClassName(current);
		} else {
			if (template != null) {
				TemplateConstraint constraint = template.getConstraintFromPath(l.getTemplatePath());
				if (constraint != null)
					return constraint.getMetadata(metadataName);
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getNodeName(ObjectValue current) throws PathEvaluationException {
		if (! (current instanceof Locatable))
			throw new PathEvaluationException("Current should be Locatable: " + current);
		Locatable p = (Locatable) current;
		return p.getOwnerAttributeName();
	}

	/**
	 * {@inheritDoc}
	 */
	public RMObject getParent(ObjectValue current) throws PathEvaluationException {
		if (! (current instanceof Locatable))
			throw new PathEvaluationException("Current should be Locatable: " + current);
		Locatable p = (Locatable) current;
		return p.getParent();
	}

	/**
	 * {@inheritDoc}
	 */
	public Locatable getRoot() {
		return root;
	}

}
