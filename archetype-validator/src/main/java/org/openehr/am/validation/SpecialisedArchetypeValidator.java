package org.openehr.am.validation;

import org.apache.commons.lang.StringUtils;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.CAttribute.Existence;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.rm.common.resource.TranslationDetails;

import java.util.*;
import java.util.Map.Entry;


public class SpecialisedArchetypeValidator extends ArchetypeValidator {

    public SpecialisedArchetypeValidator() {
        super();
    }


    /**
     * Validates the given specialised archetype including specialised validation to its stated parent archetype 
     * 
     * @param archetype
     * @param parentArchetype
     * @param reportConstraintsOnFunctionalPropertiesAsInfo if set to true, constraints on functional properties such as "is_integral" as part of DV_PROPORTION and "offset" in EVENT are reported as info only. If set to false (default), they are reported as errors.  
     * @return list of validation errors or empty list if valid
     */

    public List<ValidationError> validate(Archetype archetype, Archetype parentArchetype, boolean reportConstraintsOnCommonFunctionalPropertiesAsInfo) 
            throws RMInspectionException {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        // normal non-specialised validation first.
        errors.addAll(validate(archetype, reportConstraintsOnCommonFunctionalPropertiesAsInfo));

        int childSpecialisationDepth = StringUtils.countMatches(archetype.getArchetypeId().domainConcept(), "-");

        checkSpecialisedObjectConstraints(archetype, parentArchetype, childSpecialisationDepth, errors);
        checkSpecialisationHierarchyOfAllAtAndAcCodesInTheDefinition(archetype, childSpecialisationDepth, errors);
        log.debug("-- Error size:" +errors.size());
        for (ValidationError error : errors) {
            log.debug("Error: " + error.getType() + " "+ error.getText());
        }
        return errors;

    }


    public void checkSpecialisationHierarchyOfAllAtAndAcCodesInTheDefinition(Archetype archetype, int childSpecialisationDepth, List<ValidationError>errors) {
        for (String atCode : fetchAllATCodes(archetype)) {
            // For all codes: An extra check that the code is no specialised more than the maximum specialisation depth
            if (StringUtils.countMatches(atCode, ".") > childSpecialisationDepth) {
                errors.add (new ValidationError (ErrorType.VATCD, null, 
                        atCode, StringUtils.countMatches(atCode, "."), childSpecialisationDepth)); 
            }
        }

        for (String acCode : fetchAllACCodes(archetype)) {
            // For all codes: An extra check that the code is no specialised more than the maximum specialisation depth
            if (StringUtils.countMatches(acCode, ".") > childSpecialisationDepth) {
                errors.add (new ValidationError (ErrorType.VATCD, null, 
                        acCode, StringUtils.countMatches(acCode, "."), childSpecialisationDepth)); 
            }
        }
    }

    public void checkSpecialisedObjectConstraints(Archetype archetype,
            Archetype parentArchetype, int childSpecialisationDepth, List<ValidationError> errors) throws RMInspectionException {
        validateSpecialisedCComplexObject(archetype.getDefinition(), archetype, parentArchetype, childSpecialisationDepth, errors);

    }
    private void validateSpecialisedCComplexObject(CComplexObject ccobj, Archetype archetype,
            Archetype parentArchetype, int childSpecialisationDepth, List<ValidationError> errors) throws RMInspectionException {

        String parentPath = getExpectedPathInParentArchetype(ccobj.path(), childSpecialisationDepth-1);
        String parentPathOrig = parentPath;
        boolean continueWithAttributes = true; 

        if (parentPath != null) {
            CObject parentNode = (CObject)parentArchetype.node(parentPath);

            if (parentNode == null) {
                log.debug("No parent node found for path: "+ parentPath + " (child path: "+ ccobj.path());

                int goingUpTheSpecTree = childSpecialisationDepth -2;
                boolean foundParentFurtherUp = false;

                // if it wasn't found, we have to check further up in the hierarchy of node ids to see if it 
                // is totally missing or if there is an error of the level of specialisation of the node id (e.g. at0001.1 where it should be at0001.0.1
                while (goingUpTheSpecTree >=0) {
                    parentPath = getExpectedPathInParentArchetype(ccobj.path(), goingUpTheSpecTree);
                    if (parentPath !=null) {
                        parentNode = (CObject)parentArchetype.node(parentPath);
                        if (parentNode != null) {
                            foundParentFurtherUp = true;
                        }
                    }
                    goingUpTheSpecTree -=1;
                }

                if (foundParentFurtherUp)  {
                    errors.add(new ValidationError(ErrorType.VSONCI, null,
                            ccobj.getNodeId(), parentNode.getNodeId()));
                } else if (parentPath != null && parentPath.length() > 0 && (parentPath.endsWith("]") || 
                        parentPath.endsWith(ArchetypeConstraint.PATH_SEPARATOR) || Character.isDigit(parentPath.charAt(parentPath.length()-1)))){
                    // we do not want anything in there that ends with an attribute - e.g. /data[at0001]/items[at0004]/items[at0002]/items[at0003]/value as this is just a constraint introduced, but no at code 
                    if (StringUtils.countMatches(ccobj.getNodeId(), ".") == childSpecialisationDepth) {
                        errors.add (new ValidationError (ErrorType.VATDF, "SPECIALISED", 
                                parentPath, ccobj.path()));
                    } else {
                        errors.add (new ValidationError (ErrorType.VATDF, "INTRODUCED",
                                parentPath));
                    }	
                } else if (parentPath==null) {				    
                    errors.add (new ValidationError (ErrorType.VATDF, "INTRODUCED",
                            parentPathOrig));
                }

                continueWithAttributes= false;
                // node must be in ontology of parent....		
            } else {
                if (parentNode instanceof ArchetypeInternalRef) {
                    // if the child is a redefine of a parent use_node, then have to do the comparison to the
                    // use_node target, unless they both are use_nodes, in which case leave them as is
                    // Note: ccobj cannot be an ArchetypeInternalRef anyway - Thomas' Eiffel code is slightly different, so he needs to check...
                    parentNode = (CObject)parentArchetype.node(((ArchetypeInternalRef) parentNode).getTargetPath());					
                }

                if (ccobj.getNodeId() != null) {
                    String parentNodeId = getExpectedPathInParentArchetype(ccobj.getNodeId(), childSpecialisationDepth-1);
                    log.debug("Checking that the corresponding term is in the parent archetype: "+parentNodeId + " for "+ccobj.getNodeId());

                    if (!checkDynamicArchetypeType(parentNode, ccobj, errors)) {
                        return; // if there is an error in the dynamic types already, this is so wrong, that we skip any subsequent validation.
                    }

                    checkTermExistsInParent(parentArchetype, parentNodeId, errors);
                    // not sure this would ever do anything with the above VSONCI validation - as it would never find the parent in the first place
                    continueWithAttributes = checkSpecialisedNodeIdConformance(parentNode, ccobj,childSpecialisationDepth, errors); // VSONCI
                    checkNodeIdIsSpecialisedIfRequired(parentNode, ccobj, parentArchetype, archetype, errors); //VSONIR

                }
                checkSpecialisedRMTypeCompatiblityForNonValueItems(parentNode, ccobj, errors); // VSONCT

            }
        } else {
            log.debug("No appropriate path exists in parent for child path: "+ccobj.path()); // this would be ok - newly introduced node without a parent, no further validation is required.
        }


        //VSONCI method not used - why

        // now check for all its attributes, which in turn check for all its cobjects again...
        if(ccobj.getAttributes() != null && continueWithAttributes) {
            for (CAttribute cattr : ccobj.getAttributes()) {
                checkSpecialisedAttribute(cattr, archetype, parentArchetype, childSpecialisationDepth, errors);			
            }
        }		
    }



    /** Checks that the dynamic class of the node and its corresponding node in the parent are equal (e.g. both CCOmplexObjects)
     * VSONT
     * 
     * @param parentNode
     * @param ccobj
     * @param errors
     * @return true if all is ok, false otherwise, as this is a fatal condition
     */
    private boolean checkDynamicArchetypeType(CObject parentNode,
            CComplexObject ccobj, List<ValidationError> errors) {
        if (!ccobj.getClass().getSimpleName().equals(parentNode.getClass().getSimpleName())) {
            errors.add(new ValidationError(ErrorType.VSONT, null, 
                    ccobj.getClass().getSimpleName(), ccobj.path(), parentNode.getClass().getSimpleName()));
            return false;
        }
        return true;

    }


    private void checkSpecialisedAttribute(CAttribute cattr, Archetype archetype,
            Archetype parentArchetype, int childSpecialisationDepth, List<ValidationError> errors) throws RMInspectionException {

        // perform validations for attributes
        String parentNodePathInParentArchetype = getExpectedPathInParentArchetype(cattr.parentNodePath(), childSpecialisationDepth-1);
        CAttribute attrInParentArchetype = null;
        if (parentNodePathInParentArchetype != null) {
            CObject parentNodeInParentArchetype = null;
            if (parentNodePathInParentArchetype.length()<=1) {
                parentNodeInParentArchetype = parentArchetype.getDefinition();
            } else {
                parentNodeInParentArchetype = (CObject)parentArchetype.node(parentNodePathInParentArchetype);
            }

            if (parentNodeInParentArchetype == null) {
                log.debug("PARENT NODE IN PARENT ARCHETYPE IS NULL");
            }

            if (parentNodeInParentArchetype instanceof CComplexObject) {			   
                attrInParentArchetype = ((CComplexObject) parentNodeInParentArchetype).getAttribute(cattr.getRmAttributeName());
            } else {
                // Can we really simply ignore this case where the parent node in the parent archetype is not a CComplexObject or do we need to introduce other handling here for _DV_QUANTITY and the like?
                log.debug("PARENT NODE IN PARENT ARCHETYPE IS NOT A CCOMPLEXOBJECT, BUT "+ parentNodeInParentArchetype.getClass().getName());
            }
        }

        if (attrInParentArchetype != null) {
            checkSpecialisedAttributeNodeExistenceConformance(cattr, attrInParentArchetype, errors); //VSANCE
            checkSpecialisedAttributeMultiplicityConformance(cattr, attrInParentArchetype, errors); // VSAM
            checkSpecialisedAttributeNodeCardinalityConformance(cattr, attrInParentArchetype, errors); //VSANCC
        }


        // now get all the attribute's children and validate them.
        if (cattr.getChildren() != null) {
            for (CObject cobj : cattr.getChildren()) {
                log.debug("-----");
                log.debug(cobj.getRmTypeName() + " " +cobj.path());				

                if (cobj instanceof CComplexObject) {
                    validateSpecialisedCComplexObject((CComplexObject)cobj, archetype, parentArchetype, childSpecialisationDepth, errors);
                } else if (cobj instanceof ConstraintRef) {
                    checkSpecialisedConstraintRefConformance((ConstraintRef) cobj, archetype, parentArchetype, childSpecialisationDepth, errors);
                }			

                validateSpecialisedCObject(cobj, archetype, parentArchetype, childSpecialisationDepth, errors);
            }

            // if we have a value field which is a choice, we want to make sure that all restrictions are a correct restriction of the
            // parent values (whereas if this e.g. is a normal cluster with several items we can add some more that are not of the same or a compatible ref model type.
            if (cattr.getRmAttributeName().equals("value")) {
                checkSpecialisedRMTypeCompatiblityOfValueChildren(cattr, parentArchetype, childSpecialisationDepth, errors); // VSONCT
            }
        }
    }

    private void validateSpecialisedCObject(CObject cobj, Archetype archetype,
            Archetype parentArchetype, int childSpecialisationDepth, List<ValidationError> errors) {
        String pathInParent = getExpectedPathInParentArchetype(cobj.path(), childSpecialisationDepth-1);
        if (pathInParent== null) {
            return;
        }

        CObject nodeInParent = (CObject)parentArchetype.node(pathInParent);

        if (nodeInParent == null) {
            return;
        }

        checkSpecialisedOccurrencesCompatiblity(nodeInParent, cobj, errors);
    }


    /** Checks for conformance of a constraint ref
     *  VSCNR: placeholder constraint node conformance: a placeholder node can only
     *	be defined into a reference model type conformant with the type of the original constraint
     *	in the parent archetype.
     * 
     * @param cobj
     * @param archetype
     * @param parentArchetype
     * @param errors
     */
    private void checkSpecialisedConstraintRefConformance(ConstraintRef cr,
            Archetype archetype, Archetype parentArchetype, int childSpecialisationDepth,
            List<ValidationError> errors) {				
        String pathInParent = getExpectedPathInParentArchetype(cr.path(), childSpecialisationDepth-1);
        if (pathInParent== null) {
            return;
        }

        CObject nodeInParent = (CObject)parentArchetype.node(pathInParent);

        if (nodeInParent == null) {
            return;
        }

        /*	Not sure what needs to be checked for a VSCNR error, this is not it.		
		Class parentRMType =
			rmInspector.retrieveRMType(nodeInParent.getRmTypeName());
		Class childRMType = rmInspector.retrieveRMType(cr.getRmTypeName());
		if (! parentRMType.isAssignableFrom(childRMType)) {
			errors.add(new ValidationError(ErrorType.VSCNR, "The reference model type ("+childRMType.getSimpleName()+") of the placeholder (Reference) "+cr.getReference()+" at "+cr.path()+" in the specialised archetype is not assignable from the reference model type ("+parentRMType.getSimpleName()+") of the corresponding placeholder in the parent archetype."));
		}
         */
        // also check occurrences

    }


    /** Checks that the existence of the parent archetype's corresponding attribute is conformant 
     * with the child archetype's attribute in the sense of VSANCE error validation
     * 
     * @param cattr
     * @param attrInParentArchetype
     * @param errors
     */
    private void checkSpecialisedAttributeNodeExistenceConformance(
            CAttribute cattr, CAttribute attrInParentArchetype,
            List<ValidationError> errors) {

        if ((cattr.getExistence() == Existence.OPTIONAL && 
                (attrInParentArchetype.getExistence() == Existence.REQUIRED || 
                attrInParentArchetype.getExistence() == Existence.NOT_ALLOWED))
                ||
                (cattr.getExistence() == Existence.REQUIRED &&  
                attrInParentArchetype.getExistence() == Existence.NOT_ALLOWED)					 
                || (cattr.getExistence() == Existence.NOT_ALLOWED &&  
                attrInParentArchetype.getExistence() != Existence.NOT_ALLOWED)
                ) {
            errors.add(new ValidationError(ErrorType.VSANCE, null,
                    cattr.getExistence().toString(), cattr.path(), attrInParentArchetype.getExistence(), attrInParentArchetype.path()));
        }
    }

    /** Checks that the multiplicity of the parent archetype's corresponding attribute is conformant 
     * with the child archetype's attribute in the sense of VSAM error validation
     * 
     * @param cattr
     * @param attrInParentArchetype
     * @param errors
     */
    private void checkSpecialisedAttributeMultiplicityConformance(
            CAttribute cattr, CAttribute attrInParentArchetype,
            List<ValidationError> errors) {

        if (cattr instanceof CMultipleAttribute && !(attrInParentArchetype instanceof CMultipleAttribute)) {
            errors.add(new ValidationError(ErrorType.VSAM, "MULTIPLE", 
                    cattr.path(), attrInParentArchetype.path()));
        }	
        // if it is the other way round, this is okay, as the cardinality is contained.
        if (!(cattr instanceof CMultipleAttribute) && (attrInParentArchetype instanceof CMultipleAttribute)) {
            errors.add(new ValidationError(ErrorType.VSAM, "SINGLE",
                    cattr.path(), attrInParentArchetype.path()));
        }	

    }


    /** Checks that the cardinality of the parent archetype's corresponding attribute is conformant 
     * with the child archetype's attribute in the sense of VSANCC error validation
     * 
     * @param cattr
     * @param attrInParentArchetype
     * @param errors
     */
    private void checkSpecialisedAttributeNodeCardinalityConformance(
            CAttribute cattr, CAttribute attrInParentArchetype,
            List<ValidationError> errors) {

        // if not both are CMultipleAttributes, there is nothing to check here (this would be a VSAM error)
        if (!(cattr instanceof CMultipleAttribute) || !(attrInParentArchetype instanceof CMultipleAttribute)) {
            return;
        } 

        CMultipleAttribute cmattr = (CMultipleAttribute) cattr;
        CMultipleAttribute cmParentAttr = (CMultipleAttribute) attrInParentArchetype;

        Cardinality cardAttr = cmattr.getCardinality();

        Cardinality cardParentAttr = cmParentAttr.getCardinality();

        if (! cardParentAttr.getInterval().isUpperUnbounded() 
                && ( cardAttr.getInterval().isUpperUnbounded() 
                        || cardParentAttr.getInterval().getUpper().compareTo(
                                cardAttr.getInterval().getUpper()) <0)) {
            errors.add(new ValidationError(ErrorType.VSANCC, null,
                    getIntervalFormalString(cardAttr.getInterval()), cattr.path(), getIntervalFormalString(cardParentAttr.getInterval()), attrInParentArchetype.path()));
        } else if (cardParentAttr.getInterval().getLower().compareTo(
                cardAttr.getInterval().getLower()) >0) {
            log.debug("Cardinality error");
            errors.add(new ValidationError(ErrorType.VSANCC, null,
                    getIntervalFormalString(cardAttr.getInterval()), cattr.path(), getIntervalFormalString(cardParentAttr.getInterval()), attrInParentArchetype.path()));	
        }				
    }


    /* Checks that the specialised node id is a correct id in the sense of VSONCI
     * @return true if all is ok, false if not (as this is a fatal error for any further validation)
     */
    private boolean checkSpecialisedNodeIdConformance(CObject parentNode,
            CComplexObject ccobj, int childSpecialisationDepth, List<ValidationError> errors) { 
        //TODO THis is not used anywhere!!
        if (ccobj.getNodeId().equals(parentNode.getNodeId())) {
            return true; // identical, which is ok (non-specialised)
        }

        if (StringUtils.countMatches(ccobj.getNodeId(), ".") == childSpecialisationDepth) {
            return true; // correct number of specialisations.
        }

        errors.add(new ValidationError(ErrorType.VSONCI, null, 
                ccobj.getNodeId(), parentNode.getNodeId()));
        return false;

    }


    /** Checks that the node is specialised if required in the sense of VSONIR.
     * I.e. it needs to be redefined if any aspect of the immediate object constraint is redefined.
     * 
     * @param parentNode
     * @param ccobj
     * @param errors
     */
    private void checkNodeIdIsSpecialisedIfRequired(CObject parentNode,
            CComplexObject ccobj, Archetype parentArchetype, Archetype archetype, List<ValidationError> errors) {
        if (!ccobj.getNodeId().equals(parentNode.getNodeId())) {
            return; // they don't have the same node id, so we don't need to check here
        }

        if (!equalsDirect(ccobj,parentNode)) {
            // If they are different, but have the same node id, this is a VSONIR error.
            errors.add(new ValidationError(ErrorType.VSONIR, "NORMAL",
                    ccobj.path(), ccobj.getRmTypeName(), parentNode.getRmTypeName(), ccobj.getNodeId()));

        } else {
            // We also want to check whether term descriptions and texts are inconsistent between the two archetypes.
            // This is something that wouldn't occur with source ADLS for the specialisation, as it is excluded automatically, but hence we are using flat archetypes for both child and parent
            String langPrim = archetype.getOriginalLanguage().getCodeString();

            if (!archetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()).equals(
                    parentArchetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()))) {
                if (archetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()) !=null) {				    
                    log.debug("child desc: "+archetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()).getText());
                    log.debug("child desc: "+archetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()).getDescription());
                }
                if (parentArchetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()) !=null) {
                    log.debug("parent desc: "+ parentArchetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()).getText());
                    log.debug("parent desc: "+ parentArchetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()).getDescription());
                }
                // if the node doesn't exist in the parent at all, this is a VATDF error, checked elsewhere.
                if (parentArchetype.getOntology().termDefinition(langPrim, ccobj.getNodeId()) != null) {
                    errors.add(new ValidationError(ErrorType.VSONIR, "TEXTDESCRIPTION",
                            ccobj.path(), ccobj.getRmTypeName(), parentNode.getRmTypeName(), ccobj.getNodeId(), langPrim));

                }
            }	
            else {
                // if the main text and description is ok, we still should check that this is true for all translations as well
                if (archetype.getTranslations() != null && archetype.getTranslations().entrySet()!= null) {
                    for (Entry<String, TranslationDetails> trans : archetype.getTranslations().entrySet()) {
                        if (!archetype.getOntology().termDefinition(trans.getValue().getLanguage().getCodeString(), ccobj.getNodeId()).equals(
                                parentArchetype.getOntology().termDefinition(trans.getValue().getLanguage().getCodeString(), ccobj.getNodeId()))) {
                            // if the node doesn't exist in the parent at all, this is a VATDF error, checked elsewhere.
                            if (parentArchetype.getOntology().termDefinition(trans.getValue().getLanguage().getCodeString(), ccobj.getNodeId()) != null) {
                                errors.add(new ValidationError(ErrorType.VSONIR, "TEXTDESCRIPTION", 
                                        ccobj.path(), ccobj.getRmTypeName(), parentNode.getRmTypeName(), ccobj.getNodeId(), trans.getValue().getLanguage().getCodeString()));
                            }
                        }
                    }
                }
            }						
        }				
    }


    /** Checks that the occurrences of the corresponding node in the parent conforms to the specialised node's occurrences
     * 
     * @param parentNode
     * @param ccobj
     * @param errors
     */
    private void checkSpecialisedOccurrencesCompatiblity(CObject parentNode,
            CObject ccobj, List<ValidationError> errors) {

        if (! parentNode.getOccurrences().isUpperUnbounded() 
                && ( ccobj.getOccurrences().isUpperUnbounded() 
                        || parentNode.getOccurrences().getUpper().compareTo(
                                ccobj.getOccurrences().getUpper()) <0)) {

            errors.add(new ValidationError (ErrorType.VSONCO, null, 
                    getIntervalFormalString(ccobj.getOccurrences()), ccobj.path(), getIntervalFormalString(parentNode.getOccurrences()), parentNode.path()));		
        }				
    }

    /** Checks that the RM type of the parent's corresponding node conforms to the child'S node RM type. 
     * 
     * @param parentNode
     * @param ccobj
     * @param errors
     */

    private void checkSpecialisedRMTypeCompatiblityForNonValueItems(CObject parentNode,
            CComplexObject ccobj, List<ValidationError> errors) {
        if (ccobj.path().endsWith("value") || ccobj.path().endsWith("value/"))
        {
            return; // as value items have a choice construct without further node ids we need to test these differently, i.e. as a whole for all value restrictions.
        }

        String rmChild = ccobj.getRmTypeName();

        rmChild = removeGenericTypes(rmChild);
        Class childRMType =
                rmInspector.retrieveRMType(rmChild);

        String rmParent = parentNode.getRmTypeName();
        rmParent = removeGenericTypes(rmParent);
        Class parentRMType =
                rmInspector.retrieveRMType(rmParent);

        if (parentRMType == null || rmChild == null) {
            errors.add (new ValidationError(ErrorType.VSONCT, "UNKNOWN", 
                    rmChild, ccobj.path(), rmParent));
            // or simply return ??  // there is a problem with the rmtypes (probably one that is unknown), this is a different validation, so ignore here.
        } else if (! parentRMType.isAssignableFrom(childRMType)) {
            errors.add (new ValidationError(ErrorType.VSONCT, "NORMAL",
                    childRMType.getSimpleName(), ccobj.path(), parentRMType.getSimpleName()));
        }
    }

    /** Checks that the RM type(s) of the parent's corresponding node conforms to the child's node RM type(s).
     * Note that because of choice type (multiple constraints to choose from), we need to test this for all children of an attribute at ones.  
     * 
     * @param parentNode
     * @param ccobj
     * @param errors
     */
    private void checkSpecialisedRMTypeCompatiblityOfValueChildren(CAttribute cattr,
            Archetype parentArchetype, int childArchetypeSpecialisationDepth, List<ValidationError> errors) {
        if (cattr.getChildren() == null || cattr.getChildren().size() ==0) {
            return; // nothing to test
        }

        List<CObject> children = cattr.getChildren();

        String parentPath = getExpectedPathInParentArchetype(cattr.parentNodePath(), childArchetypeSpecialisationDepth-1);
        if (parentPath == null) {
            return; // nothing to test
        }
        CObject parentNode =(CObject) parentArchetype.node(parentPath);		
        if (parentNode == null || !(parentNode instanceof CComplexObject)) {
            return;
        }
        CComplexObject parentCNode = (CComplexObject) parentNode;
        CAttribute parentAttr = parentCNode.getAttribute(cattr.getRmAttributeName());
        if (parentAttr == null || parentAttr.getChildren() == null || parentAttr.getChildren().size() == 0) {
            return; // not restricted, nothing to test
        }

        HashMap<CObject, Class> parentObjectsToRMTypesWithoutGenerics = new HashMap<CObject, Class>();
        for (CObject childInParent : parentAttr.getChildren()) {
            // for all children - not only CComplexObjects - because of e.g. C_DV_QUANTITY constraints 
            String childInParentRMTypeWithoutGenerics = removeGenericTypes(childInParent.getRmTypeName());
            Class parentRMType = rmInspector.retrieveRMType(childInParentRMTypeWithoutGenerics);
            if (parentRMType == null) {
                errors.add (new ValidationError(ErrorType.VSONCT, "UNKNOWNFORPARENT",
                        childInParent.getRmTypeName(), childInParent.path()));
            } else { 
                parentObjectsToRMTypesWithoutGenerics.put(childInParent, parentRMType);
            }		
        }

        if (parentObjectsToRMTypesWithoutGenerics.size() == 0) {
            return;
        }

        for (CObject child : children) {
            String rmChildWithoutGenerics = removeGenericTypes(child.getRmTypeName());
            Class childRMType =
                    rmInspector.retrieveRMType(rmChildWithoutGenerics);
            if (childRMType == null) {
                continue; // This is a different error and does not need to be tested here.
            }

            boolean assignable = false;
            // it needs to be assignable to at least one of them! This is relevant for choice datatypes
            for (Entry<CObject, Class> parentToRMTypeWithoutGenerics : parentObjectsToRMTypesWithoutGenerics.entrySet()) {
                if (parentToRMTypeWithoutGenerics.getValue().isAssignableFrom(childRMType)) {

                    // the non-generic part is assignable,
                    // now we need to check if the generic part (if existing) is assignable as well.
                    String genericTypeInParentArchetype = getGenericType (parentToRMTypeWithoutGenerics.getKey().getRmTypeName());
                    if (genericTypeInParentArchetype != null) {
                        String genericTypeInChildArchetype = getGenericType(child.getRmTypeName());
                        if (genericTypeInChildArchetype != null) {
                            // if the parent generic type is set, but genericTypeInChildArchetype is not defined, this is not an assignable combination 
                            Class childGenericRMType =
                                    rmInspector.retrieveRMType(genericTypeInChildArchetype);
                            Class parentGenericRMType =
                                    rmInspector.retrieveRMType(genericTypeInParentArchetype);
                            if (childGenericRMType != null && parentGenericRMType!=null && parentGenericRMType.isAssignableFrom(childGenericRMType)) {
                                assignable = true;
                                break;								 
                            }							
                        }							 
                    } else {
                        assignable = true;
                        break;
                    }	 
                }	
            }

            if (!assignable) {
                log.debug("VSONCT error: at "+child.path());
                String parentRefTypes = "";
                for (Entry<CObject,Class> parentToRMTypeWithoutGenerics: parentObjectsToRMTypesWithoutGenerics.entrySet()) {
                    parentRefTypes += parentToRMTypeWithoutGenerics.getValue().getSimpleName() +", ";
                }
                parentRefTypes = parentRefTypes.substring(0,parentRefTypes.length()-2);

                if (parentObjectsToRMTypesWithoutGenerics.size() ==1) {
                    errors.add (new ValidationError(ErrorType.VSONCT, "NORMAL",
                            childRMType.getSimpleName(), child.path(), parentRefTypes));
                } else {
                    // more than one RM type possible 
                    errors.add (new ValidationError(ErrorType.VSONCT, 
                            childRMType.getSimpleName(), child.path(), parentRefTypes));
                }
            }			
        }
    }


    private void checkTermExistsInParent(Archetype parentArchetype, String code,
            List<ValidationError> errors) {

        String lang = parentArchetype.getOriginalLanguage().getCodeString();
        List<OntologyDefinitions> defList = 
                parentArchetype.getOntology().getTermDefinitionsList();
        OntologyDefinitions priDefs = null;
        ValidationError error = null;

        for(OntologyDefinitions defs : defList) {
            if(lang.equals(defs.getLanguage())) {
                priDefs = defs;
            }
        }
        if(priDefs == null) {
            error = new ValidationError(ErrorType.VATDF, "INPARENT",
                    code);
            errors.add(error);
        } else {
            List<ArchetypeTerm> terms = priDefs.getDefinitions();
            Set<String> definedCodes = new LinkedHashSet<String>();
            for(ArchetypeTerm term : terms) {
                definedCodes.add(term.getCode());
            }
            if( ! definedCodes.contains(code)) {
                error = new ValidationError(ErrorType.VATDF,  "INPARENT",
                        code);
                errors.add(error);
            }
        }

    }




    /** Calculates the expected path in a parent archetype derived from the given path.
     * 
     * @param path
     * @return
     */
    private String getExpectedPathInParentArchetype(String path, int parentArchetypeSpecialisationDepth) {
        log.debug("Path to unspecialise: "+ path);
        log.debug("parent specialisation depth: "+ parentArchetypeSpecialisationDepth);
        String sep=ArchetypeConstraint.PATH_SEPARATOR;
        String expectedPath=path.startsWith(sep) ? sep :""; // only add the separator to the beginning if it is there in the original path (which may also be a simple node id...
        ArrayList<String> pathParts = new ArrayList<String>(Arrays.asList(StringUtils.split(path, sep)));

        for (String pathPart : pathParts) {
            String pathEnd = "";
            if (StringUtils.countMatches(pathPart, ".") > parentArchetypeSpecialisationDepth) {
                if (pathPart.endsWith("]")) {
                    pathEnd = "]";
                }
                if (StringUtils.countMatches(pathPart, ".") >0) {
                    pathPart = pathPart.substring(0, pathPart.lastIndexOf("."));
                }

            }
            // We need to consider that the parent archetype is already a specialised archetype itself!
            while (pathPart.endsWith(".0")) {
                pathPart = pathPart.substring(0, pathPart.length()-2); // need to get the non-redefined node then, going up in the hierarchy as much as possible
            }
            if (pathPart.endsWith("at0")) { // newly introduced node
                log.debug("Path ends with at0: "+ pathPart +" "+expectedPath);
                return null;
            }
            expectedPath += pathPart + pathEnd+sep; 
        }
        if (expectedPath.length() >1 && expectedPath.endsWith(sep)) {
            expectedPath = expectedPath.substring(0,expectedPath.length()-1); // get rid of tailing separator
        }
        log.debug("Unspecialised Path: "+ expectedPath);

        return expectedPath;

    }

    /** Tests whether two objects are directly equals, including their attributes, but without taking into account any direct or indirect children.
     * 
     * @param c1
     * @param c2
     * @return
     */
    private boolean equalsDirect(CObject c1, CObject c2) {
        if (!c1.getRmTypeName().equals(c2.getRmTypeName())) {
            return false;
        }

        if (!c1.getOccurrences().equals(c2.getOccurrences())) {
            return false;
        }
        if (c1.isRequired() != c2.isRequired()) {
            return false;
        }
        if (c1 instanceof CComplexObject) {
            CComplexObject cc1 = (CComplexObject) c1;
            CComplexObject cc2 = (CComplexObject) c2;
            if (cc1.hasAssumedValue() != cc2.hasAssumedValue()) {
                return false;
            }
            if (cc1.getAttributes() != null) {
                if (cc2.getAttributes() == null) {
                    return false;
                }
                if (cc1.getAttributes().size() != cc2.getAttributes().size()) {
                    return false;
                }
                for (CAttribute cc1A : cc1.getAttributes()) {
                    CAttribute cc2A = cc2.getAttribute(cc1A.getRmAttributeName());
                    if (cc2A == null) {
                        return false;
                    }
                    if (!cc1A.getExistence().toString().equals(cc2A.getExistence().toString())) {
                        return false;
                    }
                    if (cc1A.isRequired() != cc2A.isRequired()) {
                        return false;
                    }
                    if (cc1A.isAllowed() != cc2A.isAllowed()) {
                        return false;
                    }
                    if (cc1A instanceof CMultipleAttribute && cc2A instanceof CMultipleAttribute) {
                        // If not both are the same this is tested elswhere...
                        CMultipleAttribute cc1MA = (CMultipleAttribute) cc1A;
                        CMultipleAttribute cc2MA = (CMultipleAttribute) cc2A;
                        if (!cc1MA.getCardinality().equals(cc2MA.getCardinality())) {
                            return false;
                        }
                    }
                }
            }					
        }

        return true; // no differences found
    }

}
