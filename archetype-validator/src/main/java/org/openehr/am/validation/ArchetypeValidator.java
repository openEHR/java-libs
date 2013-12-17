/*
 * component:   "openEHR Reference Implementation"
 * description: "Archetype Validator"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen"
 * support:     "openEHR Java Project <ref_impl_java@openehr.org>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.am.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.ExpressionBinaryOperator;
import org.openehr.am.archetype.assertion.ExpressionLeaf;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.CSingleAttribute;
import org.openehr.am.archetype.constraintmodel.ConstraintRef;
import org.openehr.am.archetype.constraintmodel.primitive.CPrimitive;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyBindingItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.rm.common.resource.ResourceDescriptionItem;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.measurement.MeasurementService;
import org.openehr.rm.support.measurement.SimpleMeasurementService;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

/**
 * Validator for archetypes
 * <p/>
 * 
 * It checks the following
 * .archetype term validity
 * .constraint code validity
 * .ontology translations missing
 * .archetype definition typename validity
 * .archetype definition code validity
 * .attribute constraint name validity
 * .single-valued attribute child object occurrences validity
 * .single-valued attribute child node uniqueness
 * .single-valued attribute child node reference model type (redundant?)
 * .multi-valued attribute child node identification
 * .multi-valued attribute child node identifier uniqueness
 * .object constraint type name validity 
 * .archetype concept specialisation depth
 * .use_node type validity
 * .use_node path validity
 * .ontology code specialisation level validity
 * .archetype specialisation parent identifier validity
 *
 * @author rong.chen
 * @author sebastian.garde 
 * 
 * @version 1.0
 */
public class ArchetypeValidator {

    /**
     * Create an instance of RM inspector
     */
    public ArchetypeValidator() {
        this.rmInspector = new RMInspector();

        try {
            this.termService = SimpleTerminologyService.getInstance();
            this.openEHRTerminology  = termService.terminology(
                    TerminologyService.OPENEHR);

        } catch(Exception e) {
            log.error("failed to start the terminology service", e);
        }
    }	

    private boolean reportConstraintsOnCommonFunctionalPropertiesAsInfo = false;

    public final void setReportConstraintsOnCommonFunctionalPropertiesAsInfo(
            boolean reportConstraintsOnCommonFunctionalPropertiesAsInfo) {
        this.reportConstraintsOnCommonFunctionalPropertiesAsInfo = reportConstraintsOnCommonFunctionalPropertiesAsInfo;
    }

    /**
     * Validates the given archetype 
     * 
     * @param archetype
     * @param reportConstraintsOnFunctionalPropertiesAsInfo if set to true, constraintson functional properties such as "is_integral" as part of DV_PROPORTION and "offset" in EVENT are reported as info only. If set to false (default), they are reported as errors.  
     * @return list of validation errors or empty list if valid
     */

    public List<ValidationError> validate(Archetype archetype, boolean reportConstraintsOnCommonFunctionalPropertiesAsInfo) 
            throws RMInspectionException {
        this.reportConstraintsOnCommonFunctionalPropertiesAsInfo = reportConstraintsOnCommonFunctionalPropertiesAsInfo;
        List<ValidationError> errors = new ArrayList<ValidationError>();

        checkDescription(archetype, errors);
        checkArchetypeDefinitionTypename(archetype, errors);
        checkArchetypeDefinitionCodeValidity(archetype, errors);
        checkObjectConstraints(archetype, errors);
        checkArchetypeTermValidity(archetype, errors);
        checkCodeConstraintValidity(archetype, errors);
        checkOntologyTranslation(archetype, errors);
        checkConceptSpecializationDepth(archetype, errors);
        checkSpecializationParentIdentifierValidity(archetype, errors);
        checkOntologyCodeSpecialisationLevelValidity(archetype, errors);
        checkArchetypeTermBindingsValidity(archetype, errors);

        return errors;

    }



    /**
     * Validates the given archetype 
     * 
     * @param archetype
     * @return list of validation errors or empty list if valid
     */
    public List<ValidationError> validate(Archetype archetype) 
            throws RMInspectionException {
        return validate(archetype, false);
    }


    public void checkDescription(Archetype archetype, List<ValidationError> errors) {
        if (archetype.getDescription() ==null) {
            return;
        }

        // check purpose in each available language
        for (ResourceDescriptionItem detail : archetype.getDescription().getDetails()) {
            if (StringUtils.isBlank(detail.getPurpose()) ||	StringUtils.containsIgnoreCase(detail.getPurpose(),"unknown")) {
                ValidationError error = new ValidationError(ErrorType.VDSCR, "PURPOSE", 
                        detail.getLanguage().getCodeString());
                errors.add(error);
            }   
        }

        // check original author
        Map<String, String> originalAuthor = archetype.getDescription().getOriginalAuthor();
        if (originalAuthor == null || originalAuthor.isEmpty()) {
            ValidationError error = new ValidationError(ErrorType.VDSCR, "ORIGINALAUTHOR");
            errors.add(error);
        } else {
            for (Entry<String, String> authorItem : originalAuthor.entrySet()) {
                if (StringUtils.isBlank(authorItem.getValue()) ||
                        StringUtils.containsIgnoreCase(authorItem.getValue(), "unknown")) {
                    ValidationError error = new ValidationError(ErrorType.VDSCR, "ORIGINALAUTHORPART", authorItem.getKey());
                    errors.add(error);
                }
            }
        }

        // check lifecycle state
        String lifecycle = archetype.getDescription().getLifecycleState();
        if (StringUtils.isBlank(lifecycle) ||
                StringUtils.containsIgnoreCase(lifecycle, "unknown") ||
                StringUtils.isNumeric(lifecycle)) {
            ValidationError error = new ValidationError(ErrorType.VDSCR, "LIFECYCLE");
            errors.add(error);
        }
    }

    public void checkArchetypeDefinitionCodeValidity(Archetype archetype, 
            List<ValidationError> errors) {
        String concept = archetype.getConcept();
        String rootNodeId = archetype.getDefinition().getNodeId();
        if( ! concept.equals(rootNodeId)) {
            ValidationError error = new ValidationError(ErrorType.VACCD, null); 

            errors.add(error);
        }
    }

    public void checkArchetypeUnitsValidity(CDvQuantity cDvQuantity, List<ValidationError> errors) {
        List<CDvQuantityItem> qis = cDvQuantity.getList();
        if (qis != null && qis.size() > 0) {
            MeasurementService sms = SimpleMeasurementService.getInstance();
            for (CDvQuantityItem qi : qis) {
                if (!sms.isValidUnitsString(qi.getUnits())) {
                    ValidationError error = new ValidationError(ErrorType.VUI, null, qi.getUnits());
                    errors.add(error);
                }
            }            
        }        
    }

    /**
     * Checks the archetype concept specialisation depth
     * 
     * @param archetype
     * @param errors
     */
    public void checkConceptSpecializationDepth(Archetype archetype,
            List<ValidationError> errors) {
        String concept = archetype.getConcept();
        List<String> specialization = archetype.getArchetypeId().specialisation();
        StringTokenizer tokens = new StringTokenizer(concept, ".");

        if(specialization.size() != tokens.countTokens() - 1) {
            ValidationError error = new ValidationError(ErrorType.VACSD, null, 
                    specialization.size(),  tokens.countTokens() - 1 );
            errors.add(error);
        }
    }

    /** 
     * Checks the validity of the archetype specialisation parent identifier, i.e.
     * that the archetype identifier stated in the specialise clause is the
     * identifier of the immediate specialisation parent archetype.
     *    
     * @param archetype
     * @param errors
     */
    public void checkSpecializationParentIdentifierValidity(Archetype archetype,
            List<ValidationError> errors) {

        if (archetype.getParentArchetypeId() == null) {
            return; // no specialise clause at all
        }

        ArchetypeID atId = archetype.getArchetypeId();
        String base = atId.base();
        String calculatedBaseForParent = base.substring(0, base.lastIndexOf("-"));

        if (!calculatedBaseForParent.equals(archetype.getParentArchetypeId().base())) {
            ValidationError error = new ValidationError(
                    ErrorType.VASID, "NORMAL", archetype.getArchetypeId().toString(), archetype.getParentArchetypeId().toString());
            errors.add(error);
        }       
        if (archetype.getParentArchetypeId().versionID().endsWith("draft")) {
            ValidationError error = new ValidationError(
                    ErrorType.VASID, "DEPRECATED", archetype.getParentArchetypeId().toString());
            errors.add(error);
        }	
    }

    /**
     * Checks ontology code specialisation level validity. No archetype code
     * (at-code or ac-code) defined in the ontology can be of a greater
     * specialisation depth than the archetype.
     * 
     * 
     * @param archetype
     * @param errors
     */
    public void checkOntologyCodeSpecialisationLevelValidity(
            Archetype archetype, List<ValidationError> errors) {

        log.debug("validating ontology code specialisation of archetype: " + 
                archetype.getArchetypeId());

        List<String> specialisation = archetype.getArchetypeId().specialisation();

        log.debug("specialisation list: " + specialisation);

        int level = specialisation == null ? 0 : specialisation.size();

        log.debug("specialisation level: " + level);

        List<OntologyDefinitions> list = null;

        list = archetype.getOntology().getTermDefinitionsList();
        checkOntologyDefinitions(list, errors, level);

        list = archetype.getOntology().getConstraintDefinitionsList();
        checkOntologyDefinitions(list, errors, level);    	
    }

    private void checkOntologyDefinitions(List<OntologyDefinitions> defList,
            List<ValidationError> errors, int level) {

        ValidationError error = null;
        for(OntologyDefinitions defs : defList) {
            for(ArchetypeTerm term : defs.getDefinitions()) {
                if(hasGreaterSpecialisationLevel(term.getCode(), level)) {
                    error = new ValidationError(ErrorType.VONSD, null, term.getCode(), level);
                    errors.add(error);    				
                }
            }
        }
    }

    private boolean hasGreaterSpecialisationLevel(String code, int level) {
        StringTokenizer tokens = new StringTokenizer(code, ".");
        if(tokens.countTokens() - 1 > level) {
            return true;
        }
        return false;
    }


    /**
     * Checks various object constraints
     * 
     * @param archetype
     * @param errors
     * @throws RMInspectionException
     */
    public void checkObjectConstraints(Archetype archetype, 
            List<ValidationError> errors) throws RMInspectionException {
        validateCComplexObject(archetype.getDefinition(), archetype, errors);
    }


    /** checks whether the rmAttrName is a commonly constrained functional property of the parentRMType
     * 
     * @param rmAttrName
     * @param parentRMTypeName
     * @return
     */
    private boolean isCommonFunctionalProperty(String rmAttrName, String parentRMTypeName) {
        if (rmAttrName.equals("is_integral") && parentRMTypeName.equals("DV_PROPORTION")) {
            return true;
        }
        if (rmAttrName.equals("offset") && parentRMTypeName.endsWith("EVENT")) {
            return true;
        }
        if (rmAttrName.equals("type") && parentRMTypeName.endsWith("PARTY_RELATIONSHIP")) {
            return true;
        }

        return false;

    }

    private void validateCAttribute(CAttribute cattr, 
            Map<String, Class> rmAttrs, CComplexObject parent,
            Archetype archetype, List<ValidationError> errors) 
                    throws RMInspectionException {


        String rmAttrName = cattr.getRmAttributeName();
        Class rmAttrType = rmAttrs.get(rmAttrName);

        Set<String> rmAttrNames = rmAttrs.keySet();
        ValidationError error = null;


        String parentRmClassName = parent.getRmTypeName();
        parentRmClassName = removeGenericTypes(parentRmClassName);
        String parentGenericTypeName = getGenericType(parent.getRmTypeName());
        log.debug("Generic type name: "+ parentGenericTypeName);
        Class parentRmClass = rmInspector.retrieveRMType(parentRmClassName);
        Class parentGenericType = null;

        if(parentGenericTypeName != null) {
            parentGenericType = rmInspector.retrieveRMType(parentGenericTypeName);
        }

        // replace primitive types with object types
        if(int.class.equals(rmAttrType)) {
            rmAttrType = Integer.class;
        } else if(double.class.equals(rmAttrType)) {
            rmAttrType = Double.class;
        } else if(boolean.class.equals(rmAttrType)) {
            rmAttrType = Boolean.class;
        }

        log.debug("1- validating attribute [" + rmAttrName + "] of type [" +
                rmAttrType + "]");
        if( ! rmAttrNames.contains(rmAttrName)) {
            // if it is a commonly constrained functional property, only show this as information according to validator setting.
            if (reportConstraintsOnCommonFunctionalPropertiesAsInfo && isCommonFunctionalProperty(rmAttrName, parent.getRmTypeName())) {
                error = new ValidationError(ErrorType.ICARM, null,
                        rmAttrName, cattr.path(), parent.getRmTypeName()); 
            } else {

                error = new ValidationError(ErrorType.VCARM, null, 
                        rmAttrName, parent.getRmTypeName(), cattr.path());
            }
            errors.add(error);
            return;
        }

        if (cattr instanceof CMultipleAttribute) {
            CMultipleAttribute cmattr = (CMultipleAttribute)cattr;
            checkCardinalityConformsToRMCardinality(cmattr, parent, errors);

            //check Cardinality fits with the minimum and maximum of all occurrences of the children
            checkCardinalityConformsToChildrenOccurrences(cmattr, errors);
        }


        if(cattr.getChildren() == null) {
            return;
        }

        for(CObject cobj : cattr.getChildren()) {

            if(cattr instanceof CSingleAttribute) {

                // check rm type
                String childRMTypeName = cobj.getRmTypeName();
                childRMTypeName = removeGenericTypes(childRMTypeName);

                log.debug("2- validating attribute.child constraint of type: "
                        + cobj.getClass() + ", with rmType: " +
                        childRMTypeName);

                Class childRMType =
                        rmInspector.retrieveRMType(childRMTypeName);

                if(childRMType == null) {
                    error = new ValidationError(ErrorType.VCORM, null, 
                            childRMTypeName, cobj.path());
                    errors.add(error);
                    continue;

                } else if (cobj instanceof CPrimitiveObject) {
                    // need to check the assumed value for primitive objects before skipping if parentRMClass and childRMType are equal (which is the case for SOME, but not all primitive objects)                    
                    log.debug("validating CPrimitiveObject at: "+ cobj.path());
                    validateCPrimitiveObject((CPrimitiveObject) cobj, archetype, errors);
                    log.debug("skipping unnecessary additional checks on " + parentRmClass);
                    continue; // otherwise this is done twice
                } else if( parentRmClass.equals(childRMType)){       

                    log.debug("skipping unnecessary check on " + parentRmClass);
                    continue;
                } else if(!rmAttrType.isEnum() 
                        && !(rmAttrType.isAssignableFrom(childRMType))){

                    log.debug("3- rmAttrType: "+rmAttrType + " with name [" +
                            rmAttrName+ "] is NOT assignable from type [" + 
                            childRMType + "]");

                    ErrorType type = ErrorType.VCORMT;
                    if(cobj instanceof ArchetypeInternalRef) {
                        type = ErrorType.VUNT;
                    }	
                    error = new ValidationError(type, "NORMAL",
                            childRMTypeName, cobj.path(), rmAttrType.getSimpleName());
                    if (!errors.contains(error)) {
                        errors.add(error);
                    }
                    continue;

                } else if (parentGenericType != null
                        && !(parentGenericType.isAssignableFrom(childRMType))) {
                    // the generic type is not assignable from the childRMType, e.g
                    // DV_INTERVAL<DV_TEXT> is wrong as DV_TEXT is a valid RMType, but not one that fits here: must be DV_ORDERED or subclass
                    log.debug("4- rmAttrType: "+ rmAttrType + " of name [" +
                            rmAttrName+ "] with generic type <" + 
                            parentGenericTypeName + 
                            "> is NOT assignable from type [" + 
                            childRMType + "]");

                    ErrorType type = ErrorType.VCORMT;
                    if(cobj instanceof ArchetypeInternalRef) {
                        type = ErrorType.VUNT;
                    }	

                    error = new ValidationError(type, "PARENT",
                            parentGenericTypeName, parent.path(), childRMType.getSimpleName(), cobj.path());								
                    errors.add(error);
                    continue;

                } 

                log.debug("5- rmAttrType: " + rmAttrType + " with name " +
                        rmAttrName+ " IS assignable from "+ childRMType);					

                // check csingle attribute child object occurrences					
                Interval<Integer> occu = cobj.getOccurrences();
                if(occu != null && occu.isUpperIncluded() 
                        && occu.getUpper() > 1) {
                    error = new ValidationError(ErrorType.VACSO, null, 
                            cobj.path());
                    errors.add(error);
                }	
                for(CObject cobj2 : cattr.getChildren()) {
                    if(cobj == cobj2) {
                        continue;
                    }
                    // check child uniqueness						
                    if(cobj2.getRmTypeName().equals(cobj.getRmTypeName())
                            && cobj.getNodeId() == null) {
                        error = new ValidationError(ErrorType.VACSU, null,
                                cobj.path());
                        if( ! errors.contains(error)) {
                            errors.add(error);
                        }
                    }
                    // check child identifier					
                    if(cobj2.getNodeId() != null 
                            && cobj2.getNodeId().equals(cobj.getNodeId())) {
                        error = new ValidationError(ErrorType.VACSI, null,
                                cobj.path());
                        if( ! errors.contains(error)) {
                            errors.add(error);
                        }
                    } 
                    /*VASCIT just seems to be a special case of VACSU?? 
                     * else if (cobj2.getNodeId() == null 
							&& cobj.getRmTypeName().equals(cobj2.getRmTypeName())) {
						// check for multiple attributes with the same rm type when the nodeId is null for one of them
						error = new ValidationError(ErrorType.VACSIT,
								"Cannot add "+cobj.getRmTypeName()+" object with node_id "+cobj.getNodeId()+" to singly-valued attribute "+cattr.getRmAttributeName()+" because attribute already has child with same RM type. (path="+cobj.path()+")");
						if( ! errors.contains(error)) {
							errors.add(error);
						}
					}	*/
                }
            } else { // CMultipleAttribute															

                // checking for cardinality/occurrences validity (VACMC): the interval represented by, (sum of all occurrences minimum values) .. (sum of all occurrences maximum values) must be contained by the interval stated by the cardinality.
                Interval<Integer> cardinalityInterval = 
                        ((CMultipleAttribute)cattr).getCardinality().getInterval();
                if (! cardinalityInterval.isUpperUnbounded() 
                        && ( cobj.getOccurrences().isUpperUnbounded() 
                                || cardinalityInterval.getUpper().compareTo(
                                        cobj.getOccurrences().getUpper()) <0)) {
                    error = new ValidationError(ErrorType.VACMC, "CONTAIN",
                            rmInspector.toUnderscoreSeparated(cobj.getClass().getSimpleName()).toUpperCase(),
                            cobj.getRmTypeName(),
                            cobj.getNodeId(),
                            getIntervalFormalString(cardinalityInterval),
                            getIntervalFormalString(cobj.getOccurrences()),
                            cattr.path());

                    if( ! errors.contains(error)) {
                        errors.add(error);
                    }	

                } else if (cobj.getNodeId() == null) {
                    // check missing child identifier
                    // this is only legal (in most cases) if it is a ArchetypeInternalRef i.e. a use_node reference, but not if there is more than one witohut a node id
                    if (cobj instanceof ArchetypeInternalRef) {
                        // TODO if the object id at the target end of the ref happens to be the same as the object id of a sibling member at the source end then an explicit source-end id is needed even for an internal reference.                        
                        // Could use hasSiblingWithTargetNodeId((ArchetypeInternalRef) cobj, archetype) for this

                        for(CObject cobj2 : cattr.getChildren()) {
                            if(cobj == cobj2) {
                                continue;
                            }
                            if(cobj2.getNodeId()== null && cobj2 instanceof ArchetypeInternalRef &&
                                    ((ArchetypeInternalRef) cobj).getTargetPath().equals(((ArchetypeInternalRef) cobj2).getTargetPath())) {
                                error = new ValidationError(ErrorType.VACMM,  "INTREF", 
                                        cobj.path());
                                if( ! errors.contains(error)) {
                                    errors.add(error);
                                }
                            }
                        }
                    } else {
                        error = new ValidationError(ErrorType.VACMI, null,                                
                                cobj.path());
                        errors.add(error);
                    }
                } else {
                    // check duplicated child identifier 
                    for(CObject cobj2 : cattr.getChildren()) {
                        if(cobj == cobj2) {
                            continue;
                        }
                        if(cobj.getNodeId().equals(cobj2.getNodeId())) {
                            error = new ValidationError(ErrorType.VACMM, "NORMAL",
                                    cobj.path());
                            if( ! errors.contains(error)) {
                                errors.add(error);
                            }
                        }
                    }
                }

                // how about rmType check for multi-valued attributes?
            }

            if (cobj instanceof CDomainType) { // also includes CDVQuantity!
                log.debug("validating CDomainType of node_id: "+ cobj.getNodeId());
                validateCDomainType((CDomainType) cobj, archetype, errors);
            } else if (cobj instanceof CPrimitiveObject) {
                log.debug("validating CPrimitiveObject at: "+ cobj.path());
                validateCPrimitiveObject((CPrimitiveObject) cobj, archetype, errors);
            } else if(cobj instanceof CComplexObject) {
                log.debug("validating ccobj at: "+ cobj.getNodeId());
                validateCComplexObject((CComplexObject) cobj, archetype, errors);
            } else if(cobj instanceof ArchetypeInternalRef) {
                log.debug("validating Internal Reference: "+ cobj.path());
                checkArchetypeInternalRef((ArchetypeInternalRef) cobj, archetype, errors);
            } else if(cobj instanceof ArchetypeSlot) {
                log.debug("validating ArchetypeSlot: "+ cobj.path());
                checkArchetypeSlot((ArchetypeSlot) cobj, rmAttrType, archetype, errors);
            } else {
                log.debug("not continuing with recursion for class: "+ cobj.getClass());
            }
        }
    }


    private void checkCardinalityConformsToChildrenOccurrences(
            CMultipleAttribute cmattr, List<ValidationError> errors) {

        if (cmattr.getChildren() == null || cmattr.getChildren().size() == 0) { // in the new RM implementation it is no longer null then, but empty
            return; // nothing to check
        }


        Interval<Integer> cardinalityInterval = 
                (cmattr).getCardinality().getInterval();

        int minOcc = 0;
        int maxOcc = 0;
        boolean isOccUpperUnbounded = false;
        for (CObject cobj : cmattr.getChildren()) {

            minOcc +=  cobj.getOccurrences().getLower();

            if (cobj.getOccurrences().isUpperUnbounded()) {
                isOccUpperUnbounded = true;
            }	else {
                maxOcc += cobj.getOccurrences().getUpper();
            }
        }

        // check lower:
        if (! cardinalityInterval.isUpperUnbounded() && minOcc > cardinalityInterval.getUpper()) {
            // no intersection of occurrences and cardinality because the minimal sum of occurrences is greater than the maximal cardinality
            ValidationError error = new ValidationError(ErrorType.VACMC, "INTERSECT",
                    cmattr.path(), getIntervalFormalString(cardinalityInterval), getIntervalFormalString(minOcc, maxOcc, isOccUpperUnbounded)
                    );
            if( ! errors.contains(error)) {
                errors.add(error);
                return; // found an error, so can return
            }
        }

        // check upper
        if ( !isOccUpperUnbounded
                && maxOcc < cardinalityInterval.getLower()) {

            // no intersection of occurrences and cardinality because the maximal sum of occurrences is lower than the minimal cardinality
            ValidationError error = new ValidationError(ErrorType.VACMC, "INTERSECT",
                    cmattr.path(), getIntervalFormalString(cardinalityInterval), getIntervalFormalString(minOcc, maxOcc, isOccUpperUnbounded)
                    );

            if( ! errors.contains(error)) {
                errors.add(error);
            }		    
        }

        if (!cardinalityInterval.isUpperUnbounded() && 
                minOcc != maxOcc && 
                cardinalityInterval.getUpper().intValue() == minOcc) {
            // Although there is an intersection, this intersection doesn't really make sense
            // because it would mean that at least one element could never fulfil its occurrence potential
            // This may not be an error, but should at least be a warning (TBD)
            ValidationError error = new ValidationError(ErrorType.WACMC, null,
                    cmattr.path(), getIntervalFormalString(cardinalityInterval), getIntervalFormalString(minOcc, maxOcc, isOccUpperUnbounded)
                    );

            if( ! errors.contains(error)) {
                errors.add(error);
            }	
        }

    }

    private void checkCardinalityConformsToRMCardinality(CMultipleAttribute cattr, CObject cobj, List<ValidationError> errors) {
        Interval<Integer> rmCardinality = rmInspector.defaultCardinalityInterval(cattr, cobj);
        Interval<Integer> actualCardinality = cattr.getCardinality().getInterval();
        if (rmCardinality.getLower().compareTo(actualCardinality.getLower()) > 0) {
            //VCACA actual lower cardinality lower than allowed
            ValidationError error = new ValidationError(ErrorType.VCACA, null,
                    cattr.path(), getIntervalFormalString(actualCardinality), getIntervalFormalString(rmCardinality)); 
            errors.add(error);

            //attribute items in object node at /items cardinality 0..* does not conform to cardinality >=1 in reference model 
        } else if (rmCardinality.getLower().compareTo(actualCardinality.getLower()) == 0) {
            //WCACA the same...can we do this ... is this not simply ok??? as default is set
        }


        if (!rmCardinality.isUpperUnbounded()) {
            if (actualCardinality.isUpperUnbounded() || 
                    (rmCardinality.getUpper().compareTo(actualCardinality.getUpper()) <0)) {
                //VCACA upper too high ... this may e.g. occur for cardinality of credentials in demographics archetypes
                ValidationError error = new ValidationError(ErrorType.VCACA, null,
                        cattr.path(), getIntervalFormalString(actualCardinality), getIntervalFormalString(rmCardinality)); 
                errors.add(error);

            } else if (rmCardinality.getUpper().compareTo(actualCardinality.getUpper()) ==0) {
                //WCACA
            }			
        }


    }


    /** Checks the assertions of an archetype for validity
     * @param cobj
     * @param rmAttrType
     * @param archetype
     * @param errors
     */
    private void checkArchetypeSlot(ArchetypeSlot slot, Class rmAttrType, Archetype archetype,
            List<ValidationError> errors) {
        if (slot.getIncludes() != null) {
            for (Assertion include : slot.getIncludes()) {
                checkAssertionHasValidArchetypeIds(include, slot, errors);
            }
        }
        if (slot.getExcludes() != null) {
            for (Assertion exclude : slot.getExcludes()) {
                checkAssertionHasValidArchetypeIds(exclude, slot, errors);
            }
        }
    }

    /** Checks that an assertion contains valid archetype ids 
     * 
     * @param assertion
     * @param slot
     * @param errors
     */
    private void checkAssertionHasValidArchetypeIds(Assertion assertion, ArchetypeSlot slot, List<ValidationError> errors) {
        if (assertion.getExpression() instanceof ExpressionBinaryOperator) {

            ExpressionBinaryOperator ebo = (ExpressionBinaryOperator) assertion.getExpression();
            if (ebo.getRightOperand() instanceof ExpressionLeaf) {
                ExpressionLeaf elR =(ExpressionLeaf)ebo.getRightOperand();
                if (elR.getItem() != null && elR.getItem() instanceof CString) {
                    CString elRCStr = (CString)elR.getItem();
                    if (elRCStr.getPattern() !=null) {
                        String pattern = elRCStr.getPattern();
                        if (!pattern.equals(".*"))  {
                            // make readability modifications for the pattern
                            while (pattern.indexOf("(-[a-zA-Z0-9_]+)*\\") >0) {
                                int i = pattern.indexOf("(-[a-zA-Z0-9_]+)*\\");
                                pattern = pattern.replace("(-[a-zA-Z0-9_]+)*\\", "");
                                // ignore any specialised syntax for now
                                //pattern = pattern.substring(0,i+3) + " and specialisations"+ pattern.substring(i+3);
                            }
                            // delete the regex \
                            while (pattern.indexOf("\\.") >0) {
                                pattern = pattern.replace("\\.", ".");
                            }
                            while (pattern.indexOf("|") >0) {
                                String oneId = pattern.substring(0,pattern.indexOf("|") );
                                pattern = pattern.substring(pattern.indexOf("|")+1);								
                                checkOneArchetypeId(slot, errors, oneId);									
                            }
                            // the rest of the pattern is the last archetype id, so test this one too. 
                            checkOneArchetypeId(slot, errors, pattern);									
                        }	
                    }	
                }
            }	
        }

    }

    /** Checks one archetype id to be a valid id or not
     * @param slot
     * @param errors
     * @param oneId
     */
    private void checkOneArchetypeId(ArchetypeSlot slot, List<ValidationError> errors, String oneId) {
        // check for the right number of dots in the id
        boolean containsCorrectNumberOfDots = (StringUtils.countMatches(oneId, ".") == 2);

        // check that the id ends with .v[0..9]*
        boolean endsWithDotVNumber =true; // assume it is ok, until proven false
        if (oneId.lastIndexOf(".v")== -1) {
            endsWithDotVNumber = false;
        } else {
            String tail = oneId.substring(oneId.lastIndexOf(".v")+2);
            log.debug("tail: "+ tail);
            if (tail.length() == 0 || !StringUtils.isNumeric(tail)) {
                endsWithDotVNumber = false;
            }
        }

        // check that the first part of the id (the qualified RM Entity) contains the right number of hyphens
        boolean containsCorrectNumberOfHyphensInQualifiedRMEntity = true; // assume it is ok, until proven wrong								
        String qualifiedRMEntity = oneId.substring(0, oneId.indexOf(".")); 
        if (StringUtils.countMatches(qualifiedRMEntity, "-") != 2) {
            containsCorrectNumberOfHyphensInQualifiedRMEntity = false;
        }

        // add all the errors
        if (!containsCorrectNumberOfDots) {
            ValidationError error = new ValidationError(ErrorType.VDFAI, "NUMBEROFDOTS", oneId, slot.path());
            errors.add(error);

        } 
        if (!endsWithDotVNumber) {                
            ValidationError error = new ValidationError(ErrorType.VDFAI, "DOTVNUMBER", oneId, slot.path());
            errors.add(error);

        }
        if (!containsCorrectNumberOfHyphensInQualifiedRMEntity) {                
            ValidationError error = new ValidationError(ErrorType.VDFAI, "NUMBEROFHYPHENS", oneId, slot.path());
            errors.add(error);

        }

    }


    /**
     * Validates a given c_domain_type constraint
     * 
     * TODO: does not report a problem on values that have a higher precision than specified		
     * 
     * @param cobj
     * @param archetype
     * @param errors
     */	
    private void validateCDomainType(CDomainType cdtobj, Archetype archetype, 
            List<ValidationError> errors) {		

        if (cdtobj.hasAssumedValue()) {
            log.debug("validating assumed value: " +cdtobj.getAssumedValue());
            if (!cdtobj.validValue(cdtobj.getAssumedValue())) {
                ValidationError error  = new ValidationError(ErrorType.VOBAV, null,
                        cdtobj.getAssumedValue(), cdtobj.getRmTypeName(), cdtobj.path());
                errors.add(error);
            }
        } else { 
            log.debug("No assumed value found for : "+cdtobj.getRmTypeName()+" at "+cdtobj.path());	
        }	

        if(cdtobj instanceof CCodePhrase) {
            validateCCodePhrase((CCodePhrase) cdtobj, errors);
        } else if (cdtobj instanceof CDvQuantity) {
            log.debug("validating CDVQuantity object at "+cdtobj.path());
            checkArchetypeUnitsValidity((CDvQuantity)cdtobj, errors);
        } 
        
    }

    /*
     * Checks validity of openEHR codes in given ccodephrase
     */
    private void validateCCodePhrase(CCodePhrase ccodephrase, 
            List<ValidationError> errors) {

        if(ccodephrase.getCodeList() == null 
                || !TerminologyService.OPENEHR.equalsIgnoreCase(
                        ccodephrase.getTerminologyId().toString())) {
            return;
        }
        String codes = "";
        for(String code : ccodephrase.getCodeList()) {
            if( ! openEHRTerminology.allCodes().contains(
                    new CodePhrase(TerminologyService.OPENEHR, code))) {
                codes += code + ", ";
            }
        }
        if(codes.length() != 0) {
            ValidationError error  = new ValidationError(ErrorType.VOTC, null,
                    codes, ccodephrase.path());
            errors.add(error);
        }
    }

    /**
     * @param cobj
     * @param archetype
     * @param errors
     */
    private void validateCPrimitiveObject(CPrimitiveObject cpobj, Archetype archetype, List<ValidationError> errors) {
        // This finds VOBAV validation problems.
        CPrimitive item = cpobj.getItem();
        if (item.hasAssumedValue()) {
            Object assumedValue = item.assumedValue();
            log.debug("Assumed value for CPrimitiveObject: "+assumedValue);

            if (!item.validValue(assumedValue)) {				
                ValidationError error  = new ValidationError(ErrorType.VOBAV, null,
                        assumedValue, cpobj.getRmTypeName(), cpobj.path());
                errors.add(error);
            } else {
                log.debug("Found valid assumed value for CPrimitiveObject: "+cpobj.getRmTypeName()+" at "+cpobj.path());			
            }
        } else { 
            log.debug("No assumed value found for CPrimitiveObject : "+cpobj.getRmTypeName()+" at "+cpobj.path());	
        }
    }


    private void validateCComplexObject(CComplexObject ccobj, Archetype archetype,
            List<ValidationError> errors) throws RMInspectionException {

        checkGenericTypeName(ccobj, errors);		
        if(ccobj.getAttributes() == null) {
            return;
        }

        String rmTypeNameWithoutGeneric = ccobj.getRmTypeName();
        rmTypeNameWithoutGeneric = removeGenericTypes(rmTypeNameWithoutGeneric);

        Set<String> rmAttrNames = rmInspector.retrieveRMAttributeNames(
                rmTypeNameWithoutGeneric);
        Map<String, Class> rmAttrs = rmInspector.retrieveRMAttributes(
                rmTypeNameWithoutGeneric);

        ValidationError error = null;
        if(rmAttrNames.isEmpty()) {
            error = new ValidationError(ErrorType.VCORM, null,
                    rmTypeNameWithoutGeneric, ccobj.path());
            errors.add(error);
            return;
        }


        HashSet<String> attributeNames = new HashSet<String>(); 
        for(CAttribute cattr : ccobj.getAttributes()) {		
            validateCAttribute(cattr, rmAttrs, ccobj, archetype, errors);		

            // also check on the fly if all the attributes are uniquely named
            if (!attributeNames.contains(cattr.getRmAttributeName())) {
                attributeNames.add(cattr.getRmAttributeName());
            } else {
                error = new ValidationError(ErrorType.VCATU, null,
                        cattr.getRmAttributeName(), ccobj.path());
                errors.add(error);
            }
        }		
    }

    /** Checks that a generic type name actually exists. For example would generate an error for
     * DV_INTERVAL<COUNT>, which should really be DV_INTERVAL<DV_COUNT> 
     * @param ccobj
     * @param errors
     */
    private void checkGenericTypeName(CComplexObject ccobj, List<ValidationError> errors) {
        ValidationError error;
        String genericTypeName = getGenericType(ccobj.getRmTypeName());
        log.debug("Generic type name: "+ genericTypeName);
        Class genericType = null;
        if(genericTypeName != null) {
            genericType = rmInspector.retrieveRMType(genericTypeName);
            log.debug("Generic type: "+ genericType);

        }
        if (genericTypeName != null && genericType == null){	
            error = new ValidationError(ErrorType.VCORM, null,
                    ccobj.getRmTypeName(), ccobj.path());			
            errors.add(error);
        } else if (genericType != null) {
            // found a generic type, but we still need to know if it is assignable from here
            if (! DvOrdered.class.isAssignableFrom(genericType)) {				
                error = new ValidationError(ErrorType.VCORMT, "NORMAL",
                        ccobj.getRmTypeName(), ccobj.path(), genericType.getSimpleName());			
                errors.add(error);
            }			
        }
    }

    protected String removeGenericTypes(String rmTypeName) {
        if(rmTypeName.indexOf("<") > 0 && rmTypeName.indexOf(">") > 0) {
            rmTypeName = rmTypeName.substring(0, rmTypeName.indexOf("<"));
        }
        return rmTypeName;
    }

    // TODO very simple solution, only works with one generic type
    protected String getGenericType(String rmTypeName) {
        int start = rmTypeName.indexOf("<");
        int end = rmTypeName.indexOf(">");
        String genericType = null;
        if(start > 0 && end > start) {
            genericType = rmTypeName.substring(start + 1, end);
        }
        return genericType;
    }

    /**
     * Checks the given ArchetypeInternalRef
     * 
     * @param ref
     * @param archetype
     * @param errors
     */
    public void checkArchetypeInternalRef(ArchetypeInternalRef ref, 
            /*Class rmAttributeType,*/ Archetype archetype,
            List<ValidationError> errors) {


        log.debug("validating internal_ref of rmType: " + ref.getRmTypeName() +
                " at " +ref.path() + " with target " +ref.getTargetPath());
        //" for rmAttributeType: " + rmAttributeType);

        // right now unnecessary, should be checked already
        Class rmType = rmInspector.retrieveRMType(ref.getRmTypeName());
        ValidationError error = null;
        if(rmType == null) {

            error = new ValidationError(ErrorType.VUNT, "UNKNOWN",
                    ref.getRmTypeName(), ref.path());
            errors.add(error);
        } 

        // Checking the target and its consistency with the source
        CObject target = (CObject)archetype.node(ref.getTargetPath());
        if(target == null) {
            error = new ValidationError(ErrorType.VUNP, "INVALIDPATH",
                    ref.getTargetPath(), ref.path());
            errors.add(error);
        } else {
            Class targetType = rmInspector.retrieveRMType(target.getRmTypeName());
            log.debug("Target type: "+targetType);
            log.debug("rmtype: "+rmType);
            if(targetType == null) {
                error = new ValidationError(ErrorType.VUNP, "UNKNOWNTARGETRM",
                        "Unknown target rm type at path: " + ref.getTargetPath() + 
                        " of internalRef at: " + ref.path());
                errors.add(error);
            } else if( ! rmType.isAssignableFrom(targetType)) {
                error = new ValidationError(ErrorType.VUNP, "INVALIDTARGETRM",
                        targetType, ref.path());
                errors.add(error);
            }			
        }
    }

    /**
     * Checks the archetype definition typename validity: The topmost typename mentioned
     * in the archetype definition section must match the type mentioned in the type-name
     * slot of the first segment of the archetype id.
     * 
     * @param archetype
     * @param errors
     */
    public void checkArchetypeDefinitionTypename(Archetype archetype, 
            List<ValidationError> errors) {
        String conceptType = archetype.getArchetypeId().rmEntity();
        String topType = archetype.getDefinition().getRmTypeName();
        ValidationError error = null;
        if( ! conceptType.equals(topType)) {
            error = new ValidationError(ErrorType.VARDT, null, topType, conceptType);
            errors.add(error);
        }		
    }

    /**
     * Checks if languages listed in translation section are provided in 
     * term_definition and constraint_definition sections 
     * 
     * TODO: how about missing individual term_def/constraint_def translations?
     * 
     * @param archetype
     * @return errors
     */
    public void checkOntologyTranslation(Archetype archetype,
            List<ValidationError> errors) {
        Set<String> languages = archetype.languagesAvailable();
        String primaryLang = archetype.getOriginalLanguage().getCodeString();
        List<OntologyDefinitions> termDefList = 
                archetype.getOntology().getTermDefinitionsList();
        List<OntologyDefinitions> constraintDefList = 
                archetype.getOntology().getConstraintDefinitionsList();
        Set<String> termDefLangs = retrieveLanguageSet(termDefList);
        Set<String> constraintDefLangs = retrieveLanguageSet(constraintDefList);
        ValidationError error = null;
        for(String lang : languages) {
            if(primaryLang.equals(lang)) {
                continue;
            }
            if( ! termDefLangs.contains(lang)) {
                error = new ValidationError(ErrorType.VOTM, "TERM",
                        lang);
                errors.add(error);
            }

            if(!constraintDefList.isEmpty() 
                    && !constraintDefLangs.contains(lang)) {
                error = new ValidationError(ErrorType.VOTM, "CONSTRAINT",
                        lang);
                errors.add(error);
            }
        }		
    }

    private Set<String> retrieveLanguageSet(List<OntologyDefinitions> list) {
        Set<String> set = new LinkedHashSet<String>();
        for(OntologyDefinitions defs : list) {
            set.add(defs.getLanguage());
        }
        return set;
    }

    /**
     * TODO not used
     * 
     * Check internal references
     *
     * @return map of node path, target path if any wrong internal references
     */
    Map<String, String> checkInternalReferences(Archetype archetype) {
        Map<String, String> errors = new HashMap<String, String>();
        return checkInternalReferences(archetype, archetype.getDefinition(),
                errors);
    }

    private Map<String, String> checkInternalReferences(Archetype archetype,
            CComplexObject ccobj,
            Map<String, String> errors) {
        for (CAttribute cattribute : ccobj.getAttributes()) {
            for (CObject cobj : cattribute.getChildren()) {
                if (cobj instanceof ArchetypeInternalRef) {
                    ArchetypeInternalRef ref = (ArchetypeInternalRef) cobj;
                    CObject target = (CObject)archetype.node(ref.getTargetPath());
                    if (target == null
                            || !target.getRmTypeName().equals(
                                    cobj.getRmTypeName())) {
                        // either target unknown or wrong type
                        errors.put(ref.path(), ref.getTargetPath());
                    }
                }
                if (cobj instanceof CComplexObject) {
                    checkInternalReferences(archetype, (CComplexObject) cobj,
                            errors);
                }
            }
        }
        return errors;
    }

    public void checkArchetypeTermValidity(Archetype archetype,
            List<ValidationError> errors) {
        Set<String> codes = fetchAllATCodes(archetype);    	
        String lang = archetype.getOriginalLanguage().getCodeString();
        List<OntologyDefinitions> defList = 
                archetype.getOntology().getTermDefinitionsList();
        OntologyDefinitions priDefs = null;
        ValidationError error = null;

        ArrayList<OntologyDefinitions> secondaryLanguageOntDefs = new ArrayList<OntologyDefinitions>();
        for(OntologyDefinitions defs : defList) {
            if(lang.equals(defs.getLanguage())) {
                priDefs = defs;
            } else {
                secondaryLanguageOntDefs.add(defs);
            }
        }

        // first check for the primary language
        Set<String> definedCodesPrimLang = new LinkedHashSet<String>();
        if(priDefs == null) {
            for(String code : codes) {
                error = new ValidationError(ErrorType.VATDF, "NORMAL",
                        code);
                errors.add(error);
            }
        } else {
            List<ArchetypeTerm> terms = priDefs.getDefinitions();

            for(ArchetypeTerm term : terms) {
                definedCodesPrimLang.add(term.getCode());
            }
            for(String code : codes) {
                if( ! definedCodesPrimLang.contains(code)) {
                    error = new ValidationError(ErrorType.VATDF, "NORMAL", 
                            code);
                    errors.add(error);
                }
            }
        }

        // now check for the secondary languages
        for (OntologyDefinitions secDefs :secondaryLanguageOntDefs) {
            List<ArchetypeTerm> terms = secDefs.getDefinitions();
            Set<String> definedCodesSecLang = new LinkedHashSet<String>();

            for(ArchetypeTerm term : terms) {
                definedCodesSecLang.add(term.getCode());
            }
            for(String code : codes) {
                // if not present in sec lang, but present in prim lang:
                if( ! definedCodesSecLang.contains(code) && definedCodesPrimLang.contains(code)) {
                    error = new ValidationError(ErrorType.VONLC, "TERM",
                            code, secDefs.getLanguage());
                    errors.add(error);
                }
            }
        }

        checkForUnusedCodes(defList, errors, archetype, codes);
        checkForDoubleCodes(defList, errors, archetype);

    }

    public void checkCodeConstraintValidity(Archetype archetype,
            List<ValidationError> errors) {
        Set<String> codes = fetchAllACCodes(archetype);    	
        String lang = archetype.getOriginalLanguage().getCodeString();
        List<OntologyDefinitions> defList = 
                archetype.getOntology().getConstraintDefinitionsList();
        OntologyDefinitions priDefs = null;
        ValidationError error = null;

        ArrayList<OntologyDefinitions> secondaryLanguageOntDefs = new ArrayList<OntologyDefinitions>();
        for(OntologyDefinitions defs : defList) {
            if(lang.equals(defs.getLanguage())) {
                priDefs = defs;
            } else {
                secondaryLanguageOntDefs.add(defs);
            }
        }

        Set<String> definedCodesPrimLang = new LinkedHashSet<String>();

        if(priDefs == null) {
            for(String code : codes) {
                error = new ValidationError(ErrorType.VACDF, null,
                        code);
                errors.add(error);
            }
        } else {
            List<ArchetypeTerm> terms = priDefs.getDefinitions();
            for(ArchetypeTerm term : terms) {
                definedCodesPrimLang.add(term.getCode());
            }
            for(String code : codes) {
                if( ! definedCodesPrimLang.contains(code)) {
                    error = new ValidationError(ErrorType.VACDF, null,
                            code);
                    errors.add(error);
                }
            }
        }

        // now check for the secondary languages
        for (OntologyDefinitions secDefs :secondaryLanguageOntDefs) {
            List<ArchetypeTerm> terms = secDefs.getDefinitions();
            Set<String> definedCodesSecLang = new LinkedHashSet<String>();

            for(ArchetypeTerm term : terms) {
                definedCodesSecLang.add(term.getCode());
            }
            for(String code : codes) {
                // if not present in sec lang, but present in prim lang:
                if( ! definedCodesSecLang.contains(code) && definedCodesPrimLang.contains(code)) {
                    error = new ValidationError(ErrorType.VONLC, "CONSTRAINT",
                            code, secDefs.getLanguage());
                    errors.add(error);
                }
            }
        }


        checkForUnusedCodes(defList, errors, archetype, codes);
        checkForDoubleCodes(defList, errors, archetype);
    }	



    /** Checks for unused codes in the ontology
     * 
     * @param defList
     * @param errors
     * @param archetype
     */
    private void checkForUnusedCodes(List<OntologyDefinitions> defList,
            List<ValidationError> errors, Archetype archetype, Set<String> actuallyUsedCodes) {

        int specialisationDepth = StringUtils.countMatches(archetype.getArchetypeId().domainConcept(), "-");

        // now check for each code if it exists in the definition
        ValidationError error = null;
        for(OntologyDefinitions defs : defList) {
            for(ArchetypeTerm term : defs.getDefinitions()) {
                if(! actuallyUsedCodes.contains(term.getCode())) {
                    // at the moment, we only want to report on unused codes 
                    // that are on the same specialisation depth as this archetype. 
                    if (specialisationDepth == StringUtils.countMatches(term.getCode(), ".")) {
                        error = new ValidationError(ErrorType.WOUC, null,
                                term.getCode(), defs.getLanguage());
                        errors.add(error);    				
                    }	
                }
            }
        }
    }

    /** Checks for codes in the ontology that are there more than once
     * 
     * @param defList
     * @param errors
     * @param archetype
     */
    private void checkForDoubleCodes(List<OntologyDefinitions> defList,
            List<ValidationError> errors, Archetype archetype) {

        // now check for each code if it exists in the definition
        ValidationError error = null;
        for(OntologyDefinitions defs : defList) {
            HashSet<String> foundCodes = new HashSet<String>();
            for(ArchetypeTerm term : defs.getDefinitions()) {
                if(foundCodes.contains(term.getCode())) {
                    // at the moment, we only want to report on unused codes 
                    // that are on the same specialisation depth as this archetype. 
                    error = new ValidationError(ErrorType.VOKU, null,
                            term.getCode(), defs.getLanguage());
                    errors.add(error);    				
                } else {
                    foundCodes.add(term.getCode());
                }
            }
        }
    }

    public void checkArchetypeTermBindingsValidity(Archetype archetype,
            List<ValidationError> errors) {

        List<OntologyBinding> termBindings = archetype.getOntology().getTermBindingList();
        ValidationError error = null;
        if (termBindings != null) {
            for (OntologyBinding binding : termBindings) {
                for (OntologyBindingItem obi : binding.getBindingList()) {
                    if (obi.getCode().startsWith("at")) { // bound to an atcode
                        if (archetype.getOntology().termDefinition(archetype.getOriginalLanguage().getCodeString(), obi.getCode())== null) {
                            error = new ValidationError(ErrorType.WITB, "ATCODE",
                                    obi.getCode());
                            errors.add(error);
                        }
                    } else { // bound to a complete path
                        if (!archetype.physicalPaths().contains(obi.getCode())) {
                            error = new ValidationError(ErrorType.WITB, "PATH",
                                    obi.getCode());
                            errors.add(error);
                        }		
                    }
                }
            }
        }
    }


    /**
     * Traverse the archetype and gather all at codes
     * 
     * @param archetype
     * @return a set of at codes
     */
    Set<String> fetchAllATCodes(Archetype archetype) {
        Set<String> codes = new LinkedHashSet<String>();
        // main concept
        codes.add(archetype.getConcept());

        CComplexObject ccobj = archetype.getDefinition();

        fetchATCodes(ccobj, codes);

        return codes;
    }

    void fetchATCodes(CObject cobj, Set<String> codes) {
        if(cobj.getNodeId() != null) {
            codes.add(cobj.getNodeId());
        }
        if(cobj instanceof CComplexObject) {
            CComplexObject ccobj = (CComplexObject) cobj;    	
            List<CAttribute> cattrList = ccobj.getAttributes();
            if(cattrList == null) {
                return;
            }
            for(CAttribute cattr : cattrList) {
                List<CObject> children = cattr.getChildren();
                if(children == null) {
                    continue;
                }
                for(CObject child : children) {
                    fetchATCodes(child, codes);
                }
            }
        } else if(cobj instanceof CDvOrdinal) {
            CDvOrdinal cord = (CDvOrdinal) cobj;
            Set<Ordinal> list = cord.getList();
            if(list != null) {
                for(Ordinal ord : list) {
                    CodePhrase code = ord.getSymbol();
                    if("local".equalsIgnoreCase(
                            code.getTerminologyId().name())) {
                        codes.add(code.getCodeString());
                    }    					
                }
            }
        } else if(cobj instanceof CCodePhrase) {
            CCodePhrase ccod = (CCodePhrase) cobj;
            List<String> list = ccod.getCodeList();
            if("local".equalsIgnoreCase(ccod.getTerminologyId().name())
                    && list != null) {
                for(String code : list) {
                    if(code.startsWith("at")) {
                        codes.add(code);
                    }
                }
            }
        }
    }

    /**
     * Traverse the archetype and gather all "ac" codes
     * 
     * @param archetype
     * @return a set of at codes
     */
    Set<String> fetchAllACCodes(Archetype archetype) {
        Set<String> codes = new LinkedHashSet<String>();    	
        CComplexObject ccobj = archetype.getDefinition();    	
        fetchACCodes(ccobj, codes);    	
        return codes;
    }

    void fetchACCodes(CObject cobj, Set<String> codes) {
        if(cobj instanceof CComplexObject) {
            CComplexObject ccobj = (CComplexObject) cobj;    	
            List<CAttribute> cattrList = ccobj.getAttributes();
            if(cattrList == null) {
                return;
            }
            for(CAttribute cattr : cattrList) {
                List<CObject> children = cattr.getChildren();
                if(children == null) {
                    continue;
                }
                for(CObject child : children) {
                    fetchACCodes(child, codes);
                }
            }
        } else if(cobj instanceof CCodePhrase) {
            CCodePhrase ccod = (CCodePhrase) cobj;
            List<String> list = ccod.getCodeList();
            if(list != null) {
                for(String code : list) {
                    if(code.startsWith("ac")) {
                        codes.add(code);
                    }
                }
            }    		
        } else if(cobj instanceof ConstraintRef) {
            ConstraintRef ref = (ConstraintRef) cobj;
            if(ref.getReference().startsWith("ac")) {
                codes.add(ref.getReference());
            }
        }
    }

    /** Constructs a formal String for representing this Interval
     * @param Interval
     * @return
     */
    protected String getIntervalFormalString(Interval<Integer> interval) {
        Integer lower = interval.getLower();
        Integer upper = interval.getUpper();
        boolean isUpperUnbounded = interval.isUpperUnbounded();
        return getIntervalFormalString(lower, upper, isUpperUnbounded);
    }

    protected String getIntervalFormalString(Integer lower, Integer upper, boolean isUpperUnbounded) {
        if (lower == null) {
            lower = new Integer(0);
        }

        String formal= ""+lower.intValue() +"..";
        if (isUpperUnbounded) {
            formal += "*";
        } else {
            formal += upper.intValue();
        }

        return formal;	    
    }


    /* Reference Model inspector */
    protected RMInspector rmInspector;

    /* simple terminology service */
    private TerminologyService termService;

    private TerminologyAccess openEHRTerminology;

    protected static final Logger log = Logger.getLogger(ArchetypeValidator.class);
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is Archetype.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2008
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Sebastian Garde
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */
