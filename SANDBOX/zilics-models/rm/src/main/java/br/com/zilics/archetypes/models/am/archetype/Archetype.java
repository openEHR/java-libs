
package br.com.zilics.archetypes.models.am.archetype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.zilics.archetypes.models.am.archetype.assertion.Assertion;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeInternalRef;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CMultipleAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CPrimitiveObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CSingleAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.Cardinality;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ConstraintUtils;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CString;
import br.com.zilics.archetypes.models.am.archetype.ontology.ArchetypeOntology;
import br.com.zilics.archetypes.models.am.archetype.ontology.ArchetypeTerm;
import br.com.zilics.archetypes.models.am.archetype.ontology.CodeDefinitionSet;
import br.com.zilics.archetypes.models.am.utils.path.ArchetypePathEvaluator;
import br.com.zilics.archetypes.models.am.utils.validation.SemanticValidationResult;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.common.resource.AuthoredResource;
import br.com.zilics.archetypes.models.rm.exception.SemanticValidateException;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.support.identification.ArchetypeID;
import br.com.zilics.archetypes.models.rm.support.identification.HierObjectID;
import br.com.zilics.archetypes.models.rm.utils.introspect.IntrospectorData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmClassData;
import br.com.zilics.archetypes.models.rm.utils.introspect.RmFieldData;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Archetype equivalent to {@link br.com.zilics.archetypes.models.rm.common.archetyped.Archetyped}
 * class in Common reference model.
 * <p>Defines semantics of identification, lifecycle, versioning, composition and
 * specialisation</p>
 *
 * @author Humberto
 */
public class Archetype extends AuthoredResource {
	private static final long serialVersionUID = -870178782366771202L;

	@EqualsField
	private String adlVersion;
	@NotNull
	@EqualsField
    private ArchetypeID archetypeId;
	@NotNull
	@EqualsField
    private String concept;
	@EqualsField
    private HierObjectID uid;
    private ArchetypeID parentArchetypeId;
    @NotNull
    private CComplexObject definition;
    private ArchetypeOntology ontology;
    private Set<Assertion> invariants;
    
    @Ignore
    private Map<String, CObject> constraints;
    
    @Ignore
    private Map<String, CAttribute> attributes;
    
    @Ignore
    private List<ArchetypeInternalRef> internalReferences;
    
    
    /**
     * Get the adlVersion
     * @return ADL version if archetype was read in from an ADL sharable archetype
     */
    public String getAdlVersion() {
        return adlVersion;
    }

    /**
     * Set the adlVersion
     * @param adlVersion ADL version if archetype was read in from an ADL sharable archetype
     */
    public void setAdlVersion(String adlVersion) {
		assertMutable();
        this.adlVersion = adlVersion;
    }

    /**
     * Get the archetypeId
     * @return Multi-axial identifier of this archetype in archetype space
     */
    public ArchetypeID getArchetypeId() {
        return archetypeId;
    }

    /**
     * Set the archetypeId
     * @param archetypeId Multi-axial identifier of this archetype in archetype space
     */
    public void setArchetypeId(ArchetypeID archetypeId) {
		assertMutable();
        this.archetypeId = archetypeId;
    }

    /**
     * Get the uid
     * @return OID identifier of this archetype
     */
    public HierObjectID getUid() {
        return uid;
    }

    /**
     * Set the uid
     * @param uid OID identifier of this archetype
     */
    public void setUid(HierObjectID uid) {
		assertMutable();
        this.uid = uid;
    }

    /**
     * Get the concept
     * @return The normative meaning of the archetype as a whole,
     * expressed as a local archetype code, typically "at0000".
     */
    public String getConcept() {
        return concept;
    }

    /**
     * Set the concept
     * @param concept The normative meaning of the archetype as a whole,
     * expressed as a local archetype code, typically "at0000".
     */
    public void setConcept(String concept) {
		assertMutable();
        this.concept = concept;
    }


    /**
     * Get the parentArchetypeId
     * @return Identifier of the specialisation parent of this archetype
     */
    public ArchetypeID getParentArchetypeId() {
        return parentArchetypeId;
    }

    /**
     * Set the parentArchetypeId
     * @param parentArchetypeId Identifier of the specialisation parent of this archetype
     */
    public void setParentArchetypeId(ArchetypeID parentArchetypeId) {
		assertMutable();
        this.parentArchetypeId = parentArchetypeId;
    }

    /**
     * Get the definition
     * @return Root node of this archetype
     */
    public CComplexObject getDefinition() {
        return definition;
    }

    /**
     * Set the definition
     * @param definition Root node of this archetype
     */
    public void setDefinition(CComplexObject definition) {
		assertMutable();
        this.definition = definition;
    }

    /**
     * Get the ontology
     * @return The ontology of the archetype
     */
    public ArchetypeOntology getOntology() {
        return ontology;
    }

    /**
     * Set the ontology
     * @param ontology The ontology of the archetype
     */
    public void setOntology(ArchetypeOntology ontology) {
		assertMutable();
        this.ontology = ontology;
    }

    /**
     * Get the invariants
     * @return Invariant statements about this object. Statements are expressed
     * in first order predicate logic, and usually refer to at least two
     * attributes
     */
    public Set<Assertion> getInvariants() {
        return getSet(invariants);
    }

    /**
     * Set the invariants
     * @param invariants Invariant statements about this object. Statements are expressed
     * in first order predicate logic, and usually refer to at least two
     * attributes
     */
    public void setInvariants(Set<Assertion> invariants) {
		assertMutable();
        this.invariants = invariants;
    }
    
    /**
     * Performs the semantic validation based on this archetype.
     * @param value The value (usually a RMObject) to be matched against the archetype constraints
     * @param result The Result of the semantic validation (out)
     */
	public void semanticValidation(Object value, SemanticValidationResult result) {
		if (this.getDefinition() == null) throw new NullPointerException("No definition");
		this.getDefinition().performSemanticValidation(value, result);
	}
	
	/**
	 * Acts like {@link #semanticValidation(Object, SemanticValidationResult)}, but throws
	 * an exception instead of passing a {@link SemanticValidationResult}
	 * @param value The value to be semantic validated
	 * @throws SemanticValidateException An exception containing the {@link SemanticValidationResult}
	 */
	public void semanticValidation(Object value) throws SemanticValidateException {
		SemanticValidationResult result = new SemanticValidationResult();
		semanticValidation(value, result);
		if (result.getItems().size() > 0)
			throw new SemanticValidateException(result);
	}
    
    /**
     * Get a constraint ({@link CObject}) based on the canonical path
     * @param path The canonical path of the constraint
     * @return The constraint itself
     */
    public CObject getCObjectFromPath(String path) {
    	if (constraints != null)
    		return constraints.get(path);
    	return null;
    }
    
    /**
     * Returns a set of strings containing all paths of all constraints
     * @return The set of all paths
     */
    public Set<String> getAllCObjectPaths() {
    	if (constraints != null)
    		return constraints.keySet();
    	return null;
    }
    
    /**
     * Get a {@link CAttribute} based on its canonical path
     * @param path The canonical path of the {@link CAttribute}
     * @return The corresponding {@link CAttribute}
     */
    public CAttribute getCAttributeFromPath(String path) {
    	if (attributes != null)
    		return attributes.get(path);
    	return null;
    }
    
    /**
     * The same of {@link #getAllCAttributePaths()} for {@link CAttribute}
     * @return The set of all paths
     */
    public Set<String> getAllCAttributePaths() {
    	if (attributes != null)
    		return attributes.keySet();
    	return null;    	
    }
    
    /**
     * Instantiate an {@link PathEvaluationContext} for performing A-path queries on this archetype
     * @return The {@link PathEvaluationContext}
     */
    public PathEvaluationContext getPathEvaluatorContext() {
    	return new PathEvaluationContext(new ArchetypePathEvaluator(this));
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "ARCHETYPE[" + archetypeId.getValue() + "]";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	// Here builds a map of all constraints and all CAttributes
    	constraints = new HashMap<String, CObject>();
    	attributes = new HashMap<String, CAttribute>();
    	// Also, a list of all internal references are tracked
    	internalReferences = new ArrayList<ArchetypeInternalRef>();
    	visitCObject(getDefinition(), "", result);
    	// Link the internal references with its targets (CObjects)
    	visitInternalReferences(result);
    }
    
    /**
     * In some cases, the {@link CComplexObject} structure misses required attributes of the corresponding RM class. For
     * example:
     * <pre>
	 *  HISTORY[at0001] matches { <b>-- Event Series</b>
	 *    events cardinality matches {1..*; unordered} matches {
	 *      EVENT[at0002] occurrences matches {0..1} matches { * }
	 *    }
	 *  }
     * </pre>
     * In the above example, the {@link CComplexObject} of the HISTORY constraint misses
     * the required {@link br.com.zilics.archetypes.models.rm.datastructure.history.History#origin}
     * field, so this method build an artificial constraint to reflect the required field, so the new
     * {@link CComplexObject} will look like this:
     * <pre>
	 *  HISTORY[at0001] matches { <b>-- Event Series </b>
	 *    events cardinality matches {1..*; unordered} matches {
	 *      EVENT[at0002] occurrences matches {0..1} matches { * }
	 *    }
	 *    origin matches {
	 *      DV_DATE_TIME matches { * }
	 *    }
	 *  }
     * </pre>
     * @param complex The {@link CComplexObject} to complete
     * @param result The validation result list
     */
    private void buildMissingAttributes(CComplexObject complex, ValidationResult result) {
    	// Let's use the instrospector to figure out with who we are dealing
		RmClassData classData = IntrospectorData.getRmClassDataByRmClassName(complex.getRmTypeName());

		if (classData != null) {
			for(RmFieldData fieldData : classData.getAllRmFields()) {
				// We only build missing attributes for descendants of Locatable
				if (fieldData.getJavaField().getDeclaringClass().isAssignableFrom(Locatable.class)) continue;
				
				if (!complex.getAttributes().containsKey(fieldData.getRmFieldName())) {
					CAttribute attribute;
					int minRequired = (fieldData.isNotEmpty() || fieldData.isNotNull()) ? 1 : 0;

					// attribute should be a multiple one?
					if (fieldData.isCollectionField() || fieldData.isMapField()) {
						CMultipleAttribute multipleAttribute = new CMultipleAttribute();
						Cardinality cardinality = new Cardinality();
						cardinality.setOrdered(false);
						cardinality.setUnique(false);
						Interval<Integer> cardinalityInterval = new Interval<Integer>();
						cardinalityInterval.setLower(0);
						cardinalityInterval.setUpperUnbounded(true);
						cardinality.setInterval(cardinalityInterval);
						multipleAttribute.setCardinality(cardinality);
						attribute = multipleAttribute;
					} else { // or a single one?
						attribute = new CSingleAttribute();
					}
						
					// The existence is fixed, independent of the attribute.
					Interval<Integer> existence = new Interval<Integer>();
					existence.setLower(minRequired); existence.setUpper(1);
					attribute.setExistence(existence);
					attribute.setRmAttributeName(fieldData.getRmFieldName());
					attribute.setChildren(new ArrayList<CObject>());
						
					RmClassData childClassData = IntrospectorData.getRmClassDataByJavaClass(fieldData.getJavaField().getType());
					CObject child;
						
						// Should be any allowed!
					if (childClassData != null) {
						CComplexObject ccomplex = new CComplexObject();
						ccomplex.setRmTypeName(childClassData.getRmClassName());
						ccomplex.setAnyAllowed(true);
						child = ccomplex;
					} else {
						CPrimitiveObject cprimitive = new CPrimitiveObject();
						if (fieldData.getJavaField().getType() == String.class) {
							cprimitive.setItem(new CString(".*", null, null));
						} else
							cprimitive.setAnyAllowed(true);
						child = cprimitive;
					}

					Interval<Integer> occurrences = new Interval<Integer>();
					//occurrences bounds depends on @NotNull/@NotEmpty, and whether the attribute is multiple
					occurrences.setLower(minRequired);
					if(attribute instanceof CMultipleAttribute){
						occurrences.setUpperUnbounded(true);
					} else {
						occurrences.setUpper(1);
					}
						
					child.setOccurrences(occurrences);
					attribute.getChildren().add(child);
						
					complex.getAttributes().put(attribute.getRmAttributeName(), attribute);
				}
			}
		}
		// Do not forget to call validate again!
		complex.validate(result);
    }
    
    /**
     * Visit an {@link CObject} and do some validations.
     * This recursive method will calculate the canonical path of each {@link CObject},
     * will also set some {@link Ignore} fields like {@link CObject#ownerArchetype} and
     * call {@link #buildMissingAttributes(CComplexObject, ValidationResult)}.<br/>
     * The methods {@link #visitCObject(CObject, String, ValidationResult)} and {@link #visitCAttribute(CAttribute, String, ValidationResult)}
     * will call each other in the recursiveness.
     * @param obj The {@link CObject} to visit
     * @param canonicalPath the canonical path of the {@link CObject}
     * @param result the validation result 
     */
    private void visitCObject(CObject obj, String canonicalPath, ValidationResult result) {
    	if (obj != null) {
    		String path;
    		if (canonicalPath.length() == 0) path = "/";
    		else path = canonicalPath;
    		
    		// Check for repeated paths
    		if (constraints.containsKey(path))
    			result.addItem(this, "Path repeated: " + path);
    		else {
    			ConstraintUtils.setCanonicalPath(obj, path);
    			constraints.put(path, obj);
    		}

    		ConstraintUtils.setOwnerArchetype(obj, this);
    		
    		if (obj instanceof CComplexObject) {
    			CComplexObject complex = (CComplexObject) obj;
    			if (complex.getAttributes() != null) {
    				buildMissingAttributes(complex, result);

    				for(String attributeName : complex.getAttributes().keySet()) {
    					CAttribute attribute = complex.getAttributes().get(attributeName);
    					// Null attributes are not forgiven
    					if (attribute == null)
    						result.addItem(complex, "Null attribute " + attributeName + " in complexObject");
    					else {
    						// Set the owner constraint
    						ConstraintUtils.setCAttributeOwnerConstraint(attribute, complex);
    						visitCAttribute(attribute, canonicalPath + "/" + attributeName, result);
    					}
    				}
    			}
    		} else if (obj instanceof ArchetypeInternalRef) { // It's a reference
    			//Put in a list to be processed later
    			internalReferences.add((ArchetypeInternalRef) obj);
    		}
    	}
    }
    
    /**
     * Visit an {@link CAttribute} to set its canonical path and do some validations, like {@link #visitCObject(CObject, String, ValidationResult)}.
     * There is an important feature that this method is responsible: differentiate anonymous siblings! For example:
     * <pre>
     * ELEMENT[at0018] occurrences matches {0..1} matches { <b>-- Choice element</b>
	 *   value matches {
	 *     DV_TEXT matches {*}
	 *     DV_COUNT matches {*}
	 *     DV_BOOLEAN matches {*}
	 *   }
	 * }</pre> 
     * We have 3 {@link CComplexObject}s (DV_TEXT, DV_COUNT, DV_BOOLEAN) having the same path!!! This method will artificially introduce
     * increasing numbers in the path to differentiate them: <b>value[1]</b> for DV_TEXT, <b>value[2]</b> for DV_COUNT and <b>value[3]</b> for DV_BOOLEAN.
     * @param attribute the {@link CAttribute} to visit
     * @param canonicalPath the canonical path to set
     * @param result the {@link ValidationResult} 
     */
    private void visitCAttribute(CAttribute attribute, String canonicalPath, ValidationResult result) {
    	if (attribute != null) {
    		if (attributes.containsKey(canonicalPath))
    			result.addItem(this, "Attribute path repeated: " + canonicalPath);
    		else {
        		ConstraintUtils.setCanonicalPath(attribute, canonicalPath);
        		attributes.put(canonicalPath, attribute);    			
    		}

    		ConstraintUtils.setOwnerArchetype(attribute, this);
    		
    		int childCount = 1;
    		if (attribute.getChildren() != null) {
    			for(CObject child : attribute.getChildren()) {
    				if (child == null)
    					result.addItem(attribute, "Null child in attribute");
    				else {
    					// Set the parent CAttribute
    					ConstraintUtils.setCObjectParent(child, attribute);
    				
    					String path;
    					if (child.getNodeId() != null && child.getNodeId().length() > 0)
    						path = canonicalPath + "[" + child.getNodeId() + "]";
    					else //if (attribute.getChildren().size() > 1)
    						path = canonicalPath + "[" + childCount + "]";
    					//	else
    						//	path = canonicalPath;
    					visitCObject(child, path, result);
    					childCount++;
    				}
    			}
    		}
    	}
    }
    
    /**
     * Visit the {@link ArchetypeInternalRef} references that should be resolved (expand) and validated.
     * Resolving an {@link ArchetypeInternalRef} should set its {@link ArchetypeInternalRef#targetConstraint}
     * Note that if you have a circular reference of {@link ArchetypeInternalRef}s:
     * <pre>
     * HISTORY[at0001] matches { <b>-- Event Series</b>
	 *   events cardinality matches {1..*; unordered} matches {
	 *     EVENT[at0002] occurrences matches {0..1} matches { <b>-- First event</b>
	 *       data matches {
     *         ITEM_TREE[at0012] matches {<b>-- Tree</b>
	 *           items cardinality matches {1; unordered} matches {
	 *             CLUSTER[at00011] matches { <b>-- Cluster Test</b>
	 *               items cardinality matches {0..*; unordered} matches {
	 *                 ELEMENT[at0004] occurrences matches {0..1} matches { <b>-- Free or coded text element</b>
	 *                   value matches {
	 *                     DV_TEXT matches {*}
	 *                   }
	 *                 }
	 *                 use_node CLUSTER /events[at0026]/data[at0022]/items[at0021]
	 *               }
	 *             }
	 *           }
	 *         }
	 *       }
	 *     }
	 *     POINT_EVENT[at0026] occurrences matches {0..1} matches { <b>-- Second event</b>
	 *       data matches {
     *         ITEM_TREE[at0022] matches {<b>-- Tree</b>
	 *           items cardinality matches {1; unordered} matches {
	 *             CLUSTER[at00021] matches { <b>-- Cluster Test</b>
	 *               items cardinality matches {0..*; unordered} matches {
	 *                 ELEMENT[at0024] occurrences matches {0..1} matches { <b>-- Boolean element</b>
	 *                   value matches {
	 *                     DV_BOOLEAN matches {*}
	 *                   }
	 *                 }
	 *                 use_node CLUSTER /events[at0002]/data[at0012]/items[at0011]
	 *               }
	 *             }
	 *           }
	 *         }
	 *       }
	 *     }
	 *   }
	 * }
     * </pre>
     * Only the first level will be expanded (to avoid infinite recursion), that is:
     * <pre>
     * HISTORY[at0001] matches { <b>-- Event Series</b>
	 *   events cardinality matches {1..*; unordered} matches {
	 *     EVENT[at0002] occurrences matches {0..1} matches { <b>-- First event</b>
	 *       data matches {
     *         ITEM_TREE[at0012] matches {<b>-- Tree</b>
	 *           items cardinality matches {1; unordered} matches {
	 *             CLUSTER[at00011] matches { <b>-- Cluster Test</b>
	 *               items cardinality matches {0..*; unordered} matches {
	 *                 ELEMENT[at0004] occurrences matches {0..1} matches { <b>-- Free or coded text element</b>
	 *                   value matches {
	 *                     DV_TEXT matches {*}
	 *                   }
	 *                 }
	 *                 CLUSTER[at00021] matches { <b>-- Cluster Test</b>
	 *                   items cardinality matches {0..*; unordered} matches {
	 *                     ELEMENT[at0024] occurrences matches {0..1} matches { <b>-- Boolean element</b>
	 *                       value matches {
	 *                         DV_BOOLEAN matches {*}
	 *                       }
	 *                     }
	 *                     <i>use_node CLUSTER /events[at0002]/data[at0012]/items[at0011] <b>-- Will not be expanded again!</b></i>
	 *                   }
	 *                 }
	 *               }
	 *             }
	 *           }
	 *         }
	 *       }
	 *     }
	 *     POINT_EVENT[at0026] occurrences matches {0..1} matches { <b>-- Second event</b>
	 *       data matches {
     *         ITEM_TREE[at0022] matches {<b>-- Tree</b>
	 *           items cardinality matches {1; unordered} matches {
	 *             CLUSTER[at00021] matches { <b>-- Cluster Test</b>
	 *               items cardinality matches {0..*; unordered} matches {
	 *                 ELEMENT[at0024] occurrences matches {0..1} matches { <b>-- Boolean element</b>
	 *                   value matches {
	 *                     DV_BOOLEAN matches {*}
	 *                   }
	 *                 }
	 *                 CLUSTER[at00011] matches { <b>-- Cluster Test</b>
	 *                   items cardinality matches {0..*; unordered} matches {
	 *                     ELEMENT[at0004] occurrences matches {0..1} matches { <b>-- Free or coded text element</b>
	 *                       value matches {
	 *                         DV_TEXT matches {*}
	 *                       }
	 *                     }
	 *                     <i>use_node CLUSTER /events[at0026]/data[at0022]/items[at0021]  <b>-- Will not be expanded again!</b></i>
	 *                   }
	 *                 }
	 *               }
	 *             }
	 *           }
	 *         }
	 *       }
	 *     }
	 *   }
	 * }
     * </pre>
     * @param result The list of validation items
     */
    private void visitInternalReferences(ValidationResult result) {
		for(int i=0; i<internalReferences.size(); i++) { //TODO: If a cicle exists (as in the example in the javadoc above) this method will probably run out of memory.
			ArchetypeInternalRef ref = internalReferences.get(i);
			CObject cobj = getCObjectFromPath(ref.getTargetPath());
			if (cobj == null) {
				result.addItem(this, "Missing internal referece path: " + ref.getTargetPath());
			} else {
				if (cobj.getNodeId() == null || cobj.getNodeId().length() == 0) {
					result.addItem(this, "Internal reference should point to an CObject with nodeId: " + ref.getTargetPath());
				} else {
					// Expand the Internal Reference
					// Make a simple copy now
					ConstraintUtils.setArchetypeInternalRefTargetConstraint(ref, cobj.makeSimpleCopy());
					ConstraintUtils.setCObjectParent(ref.getTargetConstraint(), ref.getParent());
					// Do not forget to call validate again!
					ref.getTargetConstraint().validate(result);
					// Adjust the canonical path
					String path = ref.getParent().getCanonicalPath() + "[" + cobj.getNodeId() + "]";
					// Visit the expanded CObject
					visitCObject(ref.getTargetConstraint(), path, result);
				}
			}
		}
    }

    /**
     * Get the corresponding definition from this term
     * @param language the language defining this term
     * @param key the term's key
     * @return the corresponding {@link ArchetypeTerm}
     */
    public ArchetypeTerm getTerm(String language, String key) {
    	if (getOntology() != null && getOntology().getTermDefinitions() != null) {
    		Map<String, CodeDefinitionSet> names = getOntology().getTermDefinitions();
    		language = getExistingLanguage(language);
    		CodeDefinitionSet definitionSet = names.get(language);
    		if (definitionSet == null || definitionSet.getItems() == null) return null;
    		return definitionSet.getItems().get(key);
    	}
		return null;
    }
    
    /**
     * Will return a language-string supported by this archetype, as close as possible to the given language-string. 
     * @param language a language string (e.g.: "pt-ao")
     */
    private String getExistingLanguage(String language) {
		Map<String, CodeDefinitionSet> names = getOntology().getTermDefinitions();
		if(names.containsKey(language)) //search the language as given
			return language;
		
    	int ind = language.indexOf('-');
    	if(ind < 0){
    		ind = language.indexOf('_');
    	}
    	if(ind >= 0){
    		language = language.substring(0, ind); //remove the country code and try again
    		if(names.containsKey(language))
    			return language;
    	}
		return getOriginalLanguage().getCodeString(); //ok, give up and return the default lang
	}
}
