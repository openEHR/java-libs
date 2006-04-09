package org.openehr.am.openehrprofile.datatypes.quantity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.util.RMObjectBuilder;
import org.openehr.rm.util.SystemValue;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.am.archetype.constraintmodel.ErrorType;
import org.openehr.am.archetype.ontology.ArchetypeOntology;

/**
 * CDvQuantity
 * 
 * @author Rong Chen
 */
public class CDvQuantity extends CDomainType {

	/**
	 * Constructor
	 * 
	 * @param path
	 * @param list
	 *            null if unspecified
	 * @param property
	 *            null if unspecified, no empty
	 * @throws IllegalArgumentException if list is empty, 
	 *             or both list and property null
	 */
	public CDvQuantity(String path, List<CDvQuantityItem> list,
			DvCodedText property) {

		super(path, "DvQuantity");

		if (list != null && list.isEmpty()) {
			throw new IllegalArgumentException("empty list");
		}
		if(list == null && property == null) {
			throw new IllegalArgumentException("both list and property null");
		}
		this.list = list;
		this.property = property;
	}

	/**
	 * List of value/units pairs.
	 * 
	 * @return
	 */
	public List<CDvQuantityItem> getList() {
		return list;
	}

	/**
	 * Optional constraint on units property
	 * 
	 * @return
	 */
	public DvCodedText getProperty() {
		return property;
	}

	@Override
	public Object createObject(Map<String, Object> objectMap,
			Set<String> inputPaths, Map<String, ErrorType> errorMap,
			Archetype archetype, RMObjectBuilder builder,
			Archetyped archetypeDetails) {
		// TODO Auto-generated method stub
		return super.createObject(objectMap, inputPaths, errorMap, archetype,
				builder, archetypeDetails);
	}

	@Override
	public CComplexObject standardRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object assignedValue(Map<SystemValue, Object> systemValues,
			ArchetypeOntology ontology) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createObject(String value,
			Map<SystemValue, Object> systemValues, ArchetypeOntology ontology)
			throws DVObjectCreationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAssignedValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPath(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSubsetOf(ArchetypeConstraint constraint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	/* fields */
	private List<CDvQuantityItem> list;

	private DvCodedText property;
}
