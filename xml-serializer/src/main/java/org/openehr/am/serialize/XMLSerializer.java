package org.openehr.am.serialize;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyBindingItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.ontology.QueryBindingItem;
import org.openehr.am.archetype.ontology.TermBindingItem;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.AssertionVariable;
import org.openehr.am.archetype.assertion.ExpressionBinaryOperator;
import org.openehr.am.archetype.assertion.ExpressionItem;
import org.openehr.am.archetype.assertion.ExpressionLeaf;
import org.openehr.am.archetype.assertion.ExpressionOperator;
import org.openehr.am.archetype.assertion.ExpressionUnaryOperator;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.primitive.*;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.ResourceDescriptionItem;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.basic.Interval;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * XML serializer of the openEHR Archetype Object Model.
 * 
 * @author Mattias Forss
 */
public class XMLSerializer {

    /**
     * Create an outputter with default encoding, indent and lineSeparator
     */
    public XMLSerializer() {
        this.encoding = UTF8;
        outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat().setEncoding(encoding.name()));
    }
    
    /**
     * Output given archetype as string in XML format
     * 
     * @param archetype
     * @return a string in XML format
     * @throws IOException
     */
    public String output(Archetype archetype) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        output(archetype, baos);
        return baos.toString(encoding.name());
    }

    /**
     * Output given archetype to outputStream
     * 
     * @param archetype
     * @param out
     * @throws IOException
     */
    public void output(Archetype archetype, OutputStream out) throws IOException {
        Document document = new Document();
        output(archetype, document);
        outputter.output(document, out);
    }

    /**
     * Output given archetype to Document
     * 
     * @param archetype
     * @param out
     * @throws IOException
     */
    public void output(Archetype archetype, Document out) {
        Element rootElement = new Element("archetype", defaultNamespace);
        rootElement.addNamespaceDeclaration(xsiNamespace);

        out.setRootElement(rootElement);
        output(archetype, rootElement);
    }

    /**
     * Output given archetype to writer
     * 
     * @param archetype
     * @param out
     * @throws IOException
     */
    public void output(Archetype archetype, Element out) {
        printHeader(archetype, out);
        printDescription(archetype.getDescription(), out);
        // TODO: Print translations
        printDefinition(archetype.getDefinition(), out);
        printOntology(archetype.getOntology(), archetype.getConcept(), out);
    }

    protected void printHeader(Archetype archetype, Element out) {
        Element archetypeId = new Element("archetype_id", defaultNamespace);
        out.getChildren().add(archetypeId);
        printString("value", archetype.getArchetypeId().toString(), archetypeId);

        printString("concept", archetype.getConcept(), out);
        
        final ArchetypeID parentID = archetype.getParentArchetypeId();
        if(parentID != null) {
            Element parentArchetypeId = new Element("parent_archetype_id", defaultNamespace);
            out.getChildren().add(parentArchetypeId);
            printString("value", archetype.getArchetypeId().toString(), parentArchetypeId);
        }
        Element originalLanguage = new Element("original_language", defaultNamespace);
        out.getChildren().add(originalLanguage);
        printCodePhrase(archetype.getOriginalLanguage(), originalLanguage);
        printString("is_controlled", archetype.isControlled() ? "true" : "false", out);
    }

    protected void printDescription(ResourceDescription description, Element out) {

        if (description == null)
            return;

        Element des = new Element("description", defaultNamespace);
        out.getChildren().add(des);
        printStringMap("original_author", description.getOriginalAuthor(), des);
        printStringList("other_contributors", description.getOtherContributors(), des);
        printString("lifecycle_state", description.getLifecycleState(), des); 
         
        for (ResourceDescriptionItem item : description.getDetails()) {
            Element details = new Element("details", defaultNamespace);
            des.getChildren().add(details);
            printDescriptionItem(item, details);
        }
        
        printString("resource_package_uri", description.getResourcePackageUri(), des);
        printStringMap("other_details", description.getOtherDetails(), des);
    }

    protected void printDescriptionItem(ResourceDescriptionItem item, Element out) {
        Element language = new Element("language", defaultNamespace);
        out.getChildren().add(language);
        printCodePhrase(item.getLanguage(), language);
        
        printString("purpose", item.getPurpose(), out); // Mandatory     
        printStringList("keywords", item.getKeywords(), out);
        printString("use", item.getUse(), out); // Mandatory 
        printString("misuse", item.getMisuse(), out); // Mandatory 
        printString("copyright", item.getCopyright(), out);
        printStringMap("original_resource_uri", 
                item.getOriginalResourceUri(), out);
        
        printStringMap("other_details", item.getOtherDetails(), out);
    }
    
    private void printCodePhrase(CodePhrase cp, Element out) {
        if (cp == null) return;
        
        printString("code_string", cp.getCodeString(), out);
        Element terminologyId = new Element("terminology_id", defaultNamespace);
        out.getChildren().add(terminologyId);
        printString("value", cp.getTerminologyId().getValue(), terminologyId);
    }
    
    
    private void printStringMap(String label, Map<String, String> map, Element out) {
                
        if(map != null && !map.isEmpty()) {
            for(String key : map.keySet()) {
                Element elm = new Element(label, defaultNamespace);
                out.getChildren().add(elm);
                elm.setAttribute("id", key);
                if (map.get(key) != null)
                    elm.setText(map.get(key));
            }
        }
    }
    
    
    private void printString(String label, String value, Element out) {        
        Element elm = new Element(label, defaultNamespace);
        out.getChildren().add(elm);
        if (value != null)
            elm.setText(value);
    }
    
    private void printStringList(String label, List<String> list, Element out) {

        if (list == null || list.isEmpty())
            return;
        
        for(int i = 0, j = list.size(); i < j; i++) {
            Element elm = new Element(label, defaultNamespace);
            out.getChildren().add(elm);
            elm.setText(list.get(i));
        }         
    }

    protected void printDefinition(CComplexObject definition, Element out) {
        
        if (definition == null) return;

        Element def = new Element("definition", defaultNamespace);
        out.getChildren().add(def);
        printCComplexObjectTop(definition, def);
    }
    
    protected void printCComplexObjectTop(CComplexObject ccobj, Element out) {

        printCObjectElements(ccobj, out);

        // print all attributes
        if(!ccobj.isAnyAllowed()) {
            for (CAttribute cattribute : ccobj.getAttributes()) {
                printCAttribute(cattribute, out);
            }
        }
    }

    protected void printCComplexObject(CComplexObject ccobj, Element out) {

        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "C_COMPLEX_OBJECT", xsiNamespace);
        printCObjectElements(ccobj, children);

        // print all attributes
        if(!ccobj.isAnyAllowed()) {
            for (CAttribute cattribute : ccobj.getAttributes()) {
                printCAttribute(cattribute, children);
            }
        }
        
    }
    
    protected void printConstraintRef(ConstraintRef ref, Element out) {
        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "CONSTRAINT_REF", xsiNamespace);
        printCObjectElements(ref, children);
        printString("reference", ref.getReference(), children);
    }

    protected void printArchetypeInternalRef(ArchetypeInternalRef ref, Element out) {

        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "ARCHETYPE_INTERNAL_REF", xsiNamespace);

        printCObjectElements(ref, children);
        printString("target_path", ref.getTargetPath(), children);
    }

    protected void printArchetypeSlot(ArchetypeSlot slot, Element out) {

        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "ARCHETYPE_SLOT", xsiNamespace);

        printCObjectElements(slot, children);

        // print all attributes
        if (!slot.isAnyAllowed()) {
            if (slot.getIncludes() != null) {
                for (Assertion include : slot.getIncludes()) {
                    Element includes = new Element("includes", defaultNamespace);
                    children.getChildren().add(includes);
                    printAssertion(include, includes);
                }
            }
            
            if (slot.getExcludes() != null) {
                for (Assertion exclude : slot.getExcludes()) {
                    Element excludes = new Element("excludes", defaultNamespace);
                    children.getChildren().add(excludes);
                    printAssertion(exclude, excludes);
                }
            }
        }
    }
    
    protected void printAssertion(Assertion assertion, Element out) {
        
        printString("tag", assertion.getTag(), out);
        printString("string_expression", assertion.getStringExpression(), out);
        printExpressionItem("expression", assertion.getExpression(), out);
        
        if(assertion.getVariables() != null) {
            List<AssertionVariable> variables = assertion.getVariables();

            for(AssertionVariable var : variables) {
                Element vars = new Element("variables", defaultNamespace);
                out.getChildren().add(vars);
                printString("name", var.getName(), vars);
                printString("definition", var.getDefinition(), vars);
            }
        }
    }
    
    protected void printExpressionItem(String label, ExpressionItem expItem, Element out) {
        
        if(expItem instanceof ExpressionLeaf) {
            printExpressionLeaf(label, (ExpressionLeaf) expItem, out);
        } else if(expItem instanceof ExpressionOperator) {
            printExpressionOperator(label, (ExpressionOperator) expItem, out);
        } else {
            // unknown ExpressionItem
            Element elm = new Element(label, defaultNamespace);
            out.getChildren().add(elm);
            elm.setAttribute("type", "EXPR_ITEM", xsiNamespace);
            
            printString("type", expItem.getType(), elm);
        }
    }
    
    protected void printExpressionLeaf(String label, ExpressionLeaf expLeaf, Element out) {
        
        Element elm = new Element(label, defaultNamespace);
        out.getChildren().add(elm);
        elm.setAttribute("type", "EXPR_LEAF", xsiNamespace);
        
        printString("type", expLeaf.getType(), elm);
        if (expLeaf.getItem() instanceof CPrimitive) {
            Element item = new Element("item", defaultNamespace);
            elm.getChildren().add(item);
            printCPrimitive((CPrimitive) expLeaf.getItem(), item);
        } else
            printString("item", expLeaf.getItem().toString(), elm);
        printString("reference_type", expLeaf.getReferenceType().name(), elm);
    }
    
    protected void printExpressionOperator(String label, ExpressionOperator expOperator, Element out) {
        Element elm = new Element(label, defaultNamespace);
        out.getChildren().add(elm);
        if(expOperator instanceof ExpressionUnaryOperator) {
            elm.setAttribute("type", "EXPR_UNARY_OPERATOR", xsiNamespace);
            printExpressionOperatorElements(expOperator, elm);
            printExpressionItem("operand", ((ExpressionUnaryOperator)expOperator).getOperand(), elm);
        } else if(expOperator instanceof ExpressionBinaryOperator) {
            final ExpressionBinaryOperator expBinaryOperator = (ExpressionBinaryOperator)expOperator;
            elm.setAttribute("type", "EXPR_BINARY_OPERATOR", xsiNamespace);
            printExpressionOperatorElements(expOperator, elm);
            printExpressionItem("left_operand", expBinaryOperator.getLeftOperand(), elm);
            printExpressionItem("right_operand", expBinaryOperator.getRightOperand(), elm);
        } else {
            // unknown ExpressionOperator
            elm.setAttribute("type", "EXPR_OPERATOR", xsiNamespace);
            printExpressionOperatorElements(expOperator, elm);
        }
    }
    
    protected void printExpressionOperatorElements(ExpressionOperator expOperator, Element out) {
        
        printString("type", expOperator.getType(), out);
        printString("operator", String.valueOf(expOperator.getOperator().getValue()), out);
        // FIXME: Typo in specs, 'precedence_overriden'
        printString("precedence_overridden", 
                expOperator.isPrecedenceOverridden() == true ? "true" : "false", out);   
    }

    protected void printCAttribute(CAttribute cattribute, Element out) {
        
        final boolean isMultipleAttribute;
        
        if (cattribute instanceof CMultipleAttribute)
            isMultipleAttribute = true;
        else
            isMultipleAttribute = false;
        
        Element attributes = new Element("attributes", defaultNamespace);
        out.getChildren().add(attributes);
        if(isMultipleAttribute) {
            attributes.setAttribute("type", "C_MULTIPLE_ATTRIBUTE", xsiNamespace);
        } else {
            attributes.setAttribute("type", "C_SINGLE_ATTRIBUTE", xsiNamespace);
        }

        Element existence = new Element("existence", defaultNamespace);
        attributes.getChildren().add(existence);
        printString("lower_unbounded", "false", existence);
        printString("upper_unbounded", "false", existence);

        int lower = 0, upper = 0;
        
        if(cattribute.getExistence().equals(CAttribute.Existence.REQUIRED)) {
            lower = 1;
            upper = 1;
        } else if(cattribute.getExistence().equals(CAttribute.Existence.OPTIONAL)) {
            lower = 0;
            upper = 1;
        }
        printString("lower", Integer.toString(lower), existence);
        printString("upper", Integer.toString(upper), existence);
        
        if(cattribute.isAnyAllowed()) {
            printString("any_allowed", "true", attributes);
        }
        
        // FIXME: AOM XML schema spec is wrong. Has 'unbounded' attribute for C_ATTRIBUTE and element with name
        // 'rm_type_name' instead of 'rm_attribute_name'.
        printString("rm_attribute_name", cattribute.getRmAttributeName(), attributes);
        
        if(!cattribute.isAnyAllowed()) { 
            List<CObject> children = cattribute.getChildren();

            if (children.size() > 1
                    || !(children.get(0) instanceof CPrimitiveObject)) {
                for (CObject cobject : cattribute.getChildren()) {
                    printCObject(cobject, attributes);
                }
            } else {
                CObject child = children.get(0);
                printCPrimitiveObject((CPrimitiveObject) child, attributes);
            }
        }
        
        if(isMultipleAttribute) {
            Element cardinality = new Element("cardinality", defaultNamespace);
            attributes.getChildren().add(cardinality);
            printCardinality(
                    ((CMultipleAttribute) cattribute).getCardinality(), cardinality);
        }     
        
    }

    protected void printCObject(CObject cobj, Element out) {

        // print specialised types
        if (cobj instanceof CDomainType) {
            printCDomainType((CDomainType) cobj, out);
        } else if (cobj instanceof CPrimitiveObject) {
            printCPrimitiveObject((CPrimitiveObject) cobj, out);
        } else if (cobj instanceof CComplexObject) {
            printCComplexObject((CComplexObject) cobj, out);
        } else if (cobj instanceof ArchetypeInternalRef) {
            printArchetypeInternalRef((ArchetypeInternalRef) cobj, out);
        } else if (cobj instanceof ConstraintRef) { // FIXME: Add in ADLSerializer as well
            printConstraintRef((ConstraintRef) cobj, out); 
        } else if (cobj instanceof ArchetypeSlot) {
            printArchetypeSlot((ArchetypeSlot) cobj, out);
        }
    }
    
    protected void printCObjectElements(CObject cobj, Element out) {
        if(cobj.isAnyAllowed()) { // Not sure if needed.
            printString("any_allowed", "true", out);
        }
        
        printString("rm_type_name", cobj.getRmTypeName(), out);
        printOccurrences(cobj.getOccurrences(), out);
        printString("node_id", cobj.getNodeID(), out);
    }
    
    protected void printOccurrences(Interval occurrences, Element out) {
        
        if (occurrences == null) return;
        
        Element occurs = new Element("occurrences", defaultNamespace);
        out.getChildren().add(occurs);
        printInterval(occurrences, occurs);
    }

    protected void printCardinality(Cardinality cardinality, Element out) {
               
        if (cardinality.isOrdered()) {
            printString("is_ordered", "true", out);
        } else {
            printString("is_ordered", "false", out);
        }
        
        if (cardinality.isUnique()) {
            printString("is_unique", "true", out);
        } else {
            printString("is_unique", "false", out);
        }
        
        Element interval = new Element("interval", defaultNamespace);
        out.getChildren().add(interval);
        printInterval(cardinality.getInterval(), interval);
    }

    protected void printCDomainType(CDomainType cdomain, Element out) {
        
        if (cdomain instanceof CCodePhrase) {
            printCCodePhrase((CCodePhrase) cdomain, out);
        } else if (cdomain instanceof CDvOrdinal) {
            printCDvOrdinal((CDvOrdinal) cdomain, out);
        } else if (cdomain instanceof CDvQuantity) {
            printCDvQuantity((CDvQuantity) cdomain, out);
        } else {
            // unknown CDomainType
            System.err.println("Cannot serialize CDomainType of type '" + cdomain.getClass().getName() + "'!");
        }
    }

    protected void printCCodePhrase(CCodePhrase ccp, Element out) {

        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "C_CODE_PHRASE", xsiNamespace);
        
        printCObjectElements(ccp, children);
        
        if(ccp.getTerminologyId() != null) {
            Element terminologyId = new Element("terminology_id", defaultNamespace);
            children.getChildren().add(terminologyId);            
            printString("value", ccp.getTerminologyId().getValue(), terminologyId);
        }
        
        if (ccp.getCodeList() != null) {
            final List<String> codeList = ccp.getCodeList();
            
            for (int i = 0, j = codeList.size(); i < j; i++) {
                printString("code_list", codeList.get(i), children);
            }
        }
        
    }

    protected void printCDvOrdinal(CDvOrdinal cordinal, Element out) {
        
        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "C_DV_ORDINAL", xsiNamespace);

        printCObjectElements(cordinal, children);

        if(cordinal.getList() != null) {
            final Set<Ordinal> ordinals = cordinal.getList();

            Ordinal ordinal;
            for (Iterator<Ordinal> it = ordinals.iterator(); it.hasNext();) {
                ordinal = it.next();
                Element list = new Element("list", defaultNamespace);
                children.getChildren().add(list);
                printString("value", String.valueOf(ordinal.getValue()), list);
                Element symbol = new Element("symbol", defaultNamespace);
                list.getChildren().add(symbol);
                Element definingCode = new Element("defining_code", defaultNamespace);
                symbol.getChildren().add(definingCode);
                printCodePhrase(ordinal.getSymbol(), definingCode);
            }
        }        
    }

    protected void printCDvQuantity(CDvQuantity cquantity, Element out) {
        
        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "C_DV_QUANTITY", xsiNamespace);

        printCObjectElements(cquantity, children);

        
        CodePhrase property = cquantity.getProperty();
        if (property != null) {
            Element prop = new Element("property", defaultNamespace);
            children.getChildren().add(prop);
            printCodePhrase(property, prop);
        }

        if (cquantity.getList() != null) {
            final List<CDvQuantityItem> list = cquantity.getList();

            for (CDvQuantityItem item : list) {
                Element lst = new Element("list", defaultNamespace);
                children.getChildren().add(lst);
                
                if(item.getMagnitude() != null) {
                    Element magnitude = new Element("magnitude", defaultNamespace);
                    lst.getChildren().add(magnitude);
                    printInterval(item.getMagnitude(), magnitude);
                }
                
                printString("units", item.getUnits(), lst);
            }
        }
    }

    protected void printOntology(ArchetypeOntology ontology, String concept, Element out) {
        
        if(ontology == null) return;
        
        Element onto = new Element("ontology", defaultNamespace);
        out.getChildren().add(onto);
        

        // TODO: Check why this is not in the XML schema specification of the AOM.
        //printString("primary_language", ontology.getPrimaryLanguage(), 2, out);
        // TODO: Check why this is not in the XML schema specification of the AOM.
        //printStringList("languages_available", ontology.getLanguages(), 2, out);
        //printStringList("terminologies_available", ontology.getTerminologies(), 2, out);
        //final int specDepth = StringUtils.countMatches(".", concept);
        //printString("specialisation_depth", String.valueOf(specDepth), 2, out);
        
        
        if(ontology.getTermDefinitionsList() != null) {
            final List<OntologyDefinitions> termDefinitions = ontology.getTermDefinitionsList();
            
            for (OntologyDefinitions defs : termDefinitions) {
                Element trmDefinitions = new Element("term_definitions", defaultNamespace);
                onto.getChildren().add(trmDefinitions);
                trmDefinitions.setAttribute("language", defs.getLanguage());
                
                for (ArchetypeTerm term : defs.getDefinitions()) {
                    Element items = new Element("items", defaultNamespace);
                    trmDefinitions.getChildren().add(items);
                    items.setAttribute("code", term.getCode());
                   
                    printStringMap("items", term.getItems(), items);
                }
                
            }
        }
        
        if(ontology.getConstraintDefinitionsList() != null) {
            final List<OntologyDefinitions> constraintDefinitions = ontology.getConstraintDefinitionsList();
                
            for (OntologyDefinitions defs : constraintDefinitions) {
                Element consDefinitions = new Element("constraint_definitions", defaultNamespace);
                onto.getChildren().add(consDefinitions);
                consDefinitions.setAttribute("language", defs.getLanguage());
                
                for (ArchetypeTerm term : defs.getDefinitions()) {
                    Element items = new Element("items", defaultNamespace);
                    consDefinitions.getChildren().add(items);
                    items.setAttribute("code", term.getCode());
                   
                    printStringMap("items", term.getItems(), items);
                }                
            }
        }

        if (ontology.getTermBindingList() != null) {
            final List<OntologyBinding> termBindings = ontology.getTermBindingList();
      
            TermBindingItem item;
            for (OntologyBinding bind : termBindings) {
                Element trmBindings = new Element("term_bindings", defaultNamespace);
                onto.getChildren().add(trmBindings);
                trmBindings.setAttribute("terminology", bind.getTerminology());

                for (OntologyBindingItem bindItem : bind.getBindingList()) {
                    item = (TermBindingItem) bindItem;

                    for(String value : item.getTerms()) {
                        Element items = new Element("items", defaultNamespace);
                        trmBindings.getChildren().add(items);
                        items.setAttribute("code", item.getCode());
                        Element vl = new Element("value", defaultNamespace);
                        items.getChildren().add(vl);

                        String terminologyId = value.substring(1, value.lastIndexOf("::"));
                        String codeString = value.substring(value.lastIndexOf("::") + 2, value.length() - 1);
                        Element termId = new Element("terminology_id", defaultNamespace);
                        vl.getChildren().add(termId);
                        printString("value", terminologyId, termId);
                        printString("code_string", codeString, vl);
                    }
                }                
            }
        }
        
        if (ontology.getConstraintBindingList() != null) {
            final List<OntologyBinding> constraintBindings = ontology.getConstraintBindingList();
            
            QueryBindingItem item;
            for (OntologyBinding bind : constraintBindings) {
                Element consBindings = new Element("constraint_bindings", defaultNamespace);
                onto.getChildren().add(consBindings);
                consBindings.setAttribute("terminology", bind.getTerminology());
                
                for (OntologyBindingItem bindItem : bind.getBindingList()) {
                    item = (QueryBindingItem) bindItem;
                    Element items = new Element("items", defaultNamespace);
                    consBindings.getChildren().add(items);
                    items.setAttribute("code", item.getCode());
                    printString("value", item.getQuery().getUrl(), items);
                }
                
            }
        }        
    }

    protected void printCPrimitiveObject(CPrimitiveObject cpo, Element out) {
        CPrimitive cp = cpo.getItem();
        if (cp == null) return;
        
        Element children = new Element("children", defaultNamespace);
        out.getChildren().add(children);
        children.setAttribute("type", "C_PRIMITIVE_OBJECT", xsiNamespace);

        printCObjectElements(cpo, children);

        Element item = new Element("item", defaultNamespace);
        children.getChildren().add(item);
        printCPrimitive(cp, item);
    }
    
    protected void printCPrimitive(CPrimitive cp, Element out) {
        
        if (cp instanceof CBoolean) {
            out.setAttribute("type", "C_BOOLEAN", xsiNamespace);
            printCBoolean((CBoolean) cp, out);
        } else if (cp instanceof CDate) {
            out.setAttribute("type", "C_DATE", xsiNamespace);
            printCDate((CDate) cp, out);
        } else if (cp instanceof CDateTime) {
            out.setAttribute("type", "C_DATE_TIME", xsiNamespace);
            printCDateTime((CDateTime) cp, out);
        } else if (cp instanceof CTime) {
            out.setAttribute("type", "C_TIME", xsiNamespace);
            printCTime((CTime) cp, out);
        } else if (cp instanceof CDuration) {
            out.setAttribute("type", "C_DURATION", xsiNamespace);
            printCDuration((CDuration) cp, out);
        } else if (cp instanceof CInteger) {
            out.setAttribute("type", "C_INTEGER", xsiNamespace);
            printCInteger((CInteger) cp, out);
        } else if (cp instanceof CReal) {
            out.setAttribute("type", "C_REAL", xsiNamespace);
            printCReal((CReal) cp, out);
        } else if (cp instanceof CString) {
            out.setAttribute("type", "C_STRING", xsiNamespace);
            printCString((CString) cp, out);
        } else {
            out.setAttribute("type", "C_PRIMITIVE", xsiNamespace);
            System.err.println("Cannot serialize CPrimitive of type '" + cp.getClass().getName() + "'!");
        }
        
    }

    protected void printCBoolean(CBoolean cboolean, Element out) {
        
        printString("true_valid", cboolean.isTrueValid() == true ?
                "true" : "false", out);
        printString("false_valid", cboolean.isFalseValid() == true ?
                "true" : "false", out);
        
        if(cboolean.hasAssumedValue()) {
            printString("assumed_value", cboolean.assumedValue().booleanValue() == true ?
                    "true" : "false", out);
        }
    }

    protected void printCDate(CDate cdate, Element out) {
        
        if (cdate.getPattern() != null) {
            printString("pattern", cdate.getPattern(), out);
        } 
        
        if(cdate.getInterval() != null) {
            Element range = new Element("range", defaultNamespace);
            out.getChildren().add(range);
            printInterval(cdate.getInterval(), range);
        }
        
        if(cdate.hasAssumedValue()) {
            printString("assumed_value", cdate.assumedValue().toString(), out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCDateTime(CDateTime cdatetime, Element out) {
        
        if (cdatetime.getPattern() != null) {
            printString("pattern", cdatetime.getPattern(), out);
        }
        
        // FIXME: Output timezone_validity. CDateTime seem to be missing this function.
        
        if(cdatetime.getInterval() != null) {
            Element range = new Element("range", defaultNamespace);
            out.getChildren().add(range);            
            printInterval(cdatetime.getInterval(), range);
        }
        
        if(cdatetime.hasAssumedValue()) {
            printString("assumed_value", cdatetime.assumedValue().toString(), out);
        }
    }

    protected void printCTime(CTime ctime, Element out) {
        
        if (ctime.getPattern() != null) {
            printString("pattern", ctime.getPattern(), out);
        }
        
        // FIXME: Output timezone_validity. CTime seem to be missing this function.
        
        if(ctime.getInterval() != null) {
            Element range = new Element("range", defaultNamespace);
            out.getChildren().add(range);
            printInterval(ctime.getInterval(), range);
        }
        
        if(ctime.hasAssumedValue()) {
            printString("assumed_value", ctime.assumedValue().toString(), out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCDuration(CDuration cduration, Element out) {
                
        if (cduration.getValue() != null) {
            printString("pattern", cduration.getValue().toString(), out);
        } 

        if(cduration.getInterval() != null) {
            Element range = new Element("range", defaultNamespace);
            out.getChildren().add(range);
            printInterval(cduration.getInterval(), range);
        }
        
        if(cduration.hasAssumedValue()) {
            printString("assumed_value", cduration.assumedValue().toString(), out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCInteger(CInteger cinteger, Element out) {
                       
        if(cinteger.getList() != null) {
            printList(cinteger.getList(), out);
        } 
        
        if(cinteger.getInterval() != null) {
            Element range = new Element("range", defaultNamespace);
            out.getChildren().add(range);
            printInterval(cinteger.getInterval(), range);
        }
        
        // TODO: Write 'list_open' element... <list_open> xs:boolean </list_open> [0..1]
        
        if(cinteger.hasAssumedValue()) {
            printString("assumed_value", cinteger.assumedValue().toString(), out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCReal(CReal creal, Element out) {
                       
        if (creal.getList() != null) {
            printList(creal.getList(), out);
        } 
        
        if(creal.getInterval() != null) {
            Element range = new Element("range", defaultNamespace);
            out.getChildren().add(range);
            printInterval(creal.getInterval(), range);
        }
        
        if(creal.hasAssumedValue()) {
            printString("assumed_value", creal.assumedValue().toString(), out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCString(CString cstring, Element out) {
        
        if(cstring.getPattern() != null) {
            printString("pattern", cstring.getPattern(), out);
        }
        
        if(cstring.getList() != null){
            printList(cstring.getList(), out);
        }
        
        // TODO: Write 'list_open' element... <list_open> xs:boolean </list_open> [0..1]
        
        if(cstring.hasAssumedValue()) {
            printString("assumed_value", cstring.assumedValue().toString(), out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printList(List list, Element out) {
        
        if(list == null) {
            return;
        }

        for(int i = 0, j = list.size(); i < j; i++) {
            printString("list", list.get(i).toString(), out);
        }
    }

    @SuppressWarnings("unchecked")
    protected void printInterval(Interval interval, Element out) {
        if(interval == null) {
            return;
        }
        
        final Comparable lower = interval.getLower();
        final Comparable upper = interval.getUpper();

        printString("upper_included",
                interval.isUpperIncluded() == true ? "true" : "false",
                out);
        printString("lower_included",
                interval.isLowerIncluded() == true ? "true" : "false",
                out);
  
        
        printString("upper_unbounded",
                       interval.isUpperUnbounded() ? "true" : "false", out);
        printString("lower_unbounded",
                       interval.isLowerUnbounded() ? "true" : "false", out);
        
        if(upper != null) {
            printString("upper", upper.toString(), out);
        }
        
        if(lower != null) {
            printString("lower", lower.toString(), out);
        }
    }

    /* charset encodings */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static final Charset LATIN1 = Charset.forName("ISO-8859-1");
    
    public static final Namespace defaultNamespace = Namespace.getNamespace("http://schemas.openehr.org/v1");
    
    public static final Namespace xsiNamespace = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

    /* fields */  
    private Charset encoding;

    private XMLOutputter outputter;
}

/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is XMLSerializer.
 *
 * The Initial Developer of the Original Code is
 * Link�pings universitet, Sweden.
 * Portions created by the Initial Developer are Copyright (C) 2005-2008
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):  Mattias Forss <mattias.forss@gmail.com>
 *                  Rong Chen
 *                  Erik Sundvall
 *                  Humberto Naves
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */