
package br.com.zilics.archetypes.models.am.archetype.ontology;

import java.util.Map;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;

/**
 * Local ontology of an archetype
 *
 * @author Humberto
 */
public class ArchetypeOntology extends AMObject {

    private static final long serialVersionUID = -1685137365681426316L;
	
    @NotEmpty
    @EqualsField
    @MapItem(key="language")
    private Map<String, CodeDefinitionSet> termDefinitions;
    @EqualsField
    @MapItem(key="language")
    private Map<String, CodeDefinitionSet> constraintDefinitions;
    @EqualsField
    @MapItem(key="terminology")
    private Map<String, TermBindingSet> termBindings;
    @EqualsField
    @MapItem(key="terminology")
    private Map<String, ConstraintBindingSet> constraintBindings;

    public ArchetypeOntology() {}
    
    public ArchetypeOntology(Map<String, CodeDefinitionSet> termDefinitions,
    		Map<String, CodeDefinitionSet> constraintDefinitions,
    		Map<String, TermBindingSet> termBindings,
    		Map<String, ConstraintBindingSet> constraintBindings) {
    	this.termDefinitions = termDefinitions;
    	this.constraintDefinitions = constraintDefinitions;
    	this.termBindings = termBindings;
    	this.constraintBindings = constraintBindings;
    }
    
    /**
     * Get the termDefinitions
     * @return List of all term codes in the ontology. Most of these correspond
     * to "at" codes in an ADL archetype, which are the nodeIds on
     * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject}
     * descendants. There may be an extra one, if a different term is used as the
     * overall archetype concept from that used as the nodeId of the outermost
     * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject}
     * in the definition part.
     */
    public Map<String, CodeDefinitionSet> getTermDefinitions() {
        return getMap(termDefinitions);
    }

    /**
     * Set the termDefinitions
     * @param termDefinitions List of all term codes in the ontology. Most of these correspond
     * to "at" codes in an ADL archetype, which are the nodeIds on
     * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject}
     * descendants. There may be an extra one, if a different term is used as the
     * overall archetype concept from that used as the nodeId of the outermost
     * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject}
     * in the definition part.
     */
    public void setTermDefinitions(Map<String, CodeDefinitionSet> termDefinitions) {
		assertMutable();
        this.termDefinitions = termDefinitions;
    }

    /**
     * Get the constraintDefinitions
     * @return List of all term codes in the ontology. These correspond to the
     * "ac" codes in an ADL archetype, or equivalently, the
     * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.ConstraintRef#getReference()}
     * values in the archetype definition.
     */
    public Map<String, CodeDefinitionSet> getConstraintDefinitions() {
        return getMap(constraintDefinitions);
    }

    /**
     * Set the constraintDefinitions
     * @param constraintDefinitions List of all term codes in the ontology. These correspond to the
     * "ac" codes in an ADL archetype, or equivalently, the
     * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.ConstraintRef#getReference()}
     * values in the archetype definition.
     */
    public void setConstraintDefinitions(Map<String, CodeDefinitionSet> constraintDefinitions) {
		assertMutable();
        this.constraintDefinitions = constraintDefinitions;
    }

    /**
     * Get the termBindings
     * @return Bindings of terms corresponding to a_code in target external
     * terminology a_terminology_id as a {@link br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase}
     */
    public Map<String, TermBindingSet> getTermBindings() {
        return getMap(termBindings);
    }

    /**
     * Set the termBindings
     * @param termBindings Bindings of terms corresponding to a_code in target external
     * terminology a_terminology_id as a {@link br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase}
     */
    public void setTermBindings(Map<String, TermBindingSet> termBindings) {
		assertMutable();
        this.termBindings = termBindings;
    }

    /**
     * Get the constraintBindings
     * @return Binding of constraint corresponding to a_code in target external
     * terminology a_terminology_id, as a string, which is usually a formal
     * query expression.
     */
    public Map<String, ConstraintBindingSet> getConstraintBindings() {
        return getMap(constraintBindings);
    }

    /**
     * Set the constraintBindings
     * @param constraintBindings Binding of constraint corresponding to a_code in target external
     * terminology a_terminology_id, as a string, which is usually a formal
     * query expression.
     */
    public void setConstraintBindings(Map<String, ConstraintBindingSet> constraintBindings) {
		assertMutable();
        this.constraintBindings = constraintBindings;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "ARCHETYPE_ONTOLOGY";
    }


}
