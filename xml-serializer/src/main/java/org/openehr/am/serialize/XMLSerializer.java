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
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        this.indent = "    "; // 4 white space characters
        this.lineSeparator = System.getProperty("line.separator");
    }
    
    interface CustomWriter {
        public void writeln(String arg) throws IOException;
        
        public void write(String arg) throws IOException;
        
        public void flush() throws IOException;
        
        public void close() throws IOException;
        
        // Add more methods if needed.
    }
    
    class CustomStringWriter extends StringWriter implements CustomWriter {
        
        public CustomStringWriter() {
            super();
        }
        
        public void writeln(String arg) {
            super.write(arg);
            super.write(lineSeparator);
        }
        
        public void write(String arg) {
            super.write(arg);
        }
        
        public void flush() {
            super.flush();
        }
        
        public void close() throws IOException {
            super.close();
        }
    }
    
    class CustomBufferedWriter extends BufferedWriter implements CustomWriter {
        
        public CustomBufferedWriter(Writer writer) {
            super(writer);
        }
        
        public void writeln(String arg) throws IOException {
            super.write(arg);
            newLine();
        }
        
        public void write(String arg) throws IOException {
            super.write(arg);
        }
        
        public void flush() throws IOException {
            super.flush();
        }
        
        public void close() throws IOException {
            super.close();
        }
    }

    /**
     * Output given archetype as string in ADL format
     * 
     * @param archetype
     * @return a string in ADL format
     * @throws IOException
     */
    public String output(Archetype archetype) throws IOException {
        CustomStringWriter writer = new CustomStringWriter();
        output(archetype, writer);
        return writer.toString();
    }

    /**
     * Output given archetype to outputStream
     * 
     * @param archetype
     * @param out
     * @throws IOException
     */
    public void output(Archetype archetype, OutputStream out)
            throws IOException {
        CustomWriter writer = new CustomBufferedWriter(new OutputStreamWriter(
                new BufferedOutputStream(out), encoding));
        output(archetype, writer);
    }

    /**
     * Output given archetype to writer
     * 
     * @param archetype
     * @param out
     * @throws IOException
     */
    public void output(Archetype archetype, CustomWriter out) throws IOException {
        printHeader(archetype, out);
        printDescription(archetype.getDescription(), out);
        // TODO: Print translations
        printDefinition(archetype.getDefinition(), out);
        printOntology(archetype.getOntology(), archetype.getConcept(), out);
        out.writeln("</archetype>");
        out.flush();
        out.close();
    }

    protected void printHeader(Archetype archetype, CustomWriter out) throws IOException {
        out.writeln("<?xml version=\"1.0\" encoding=\"" + encoding.name() + "\"?>");
        out.writeln("<archetype xmlns=\"http://schemas.openehr.org/v1\"\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        indent(1, out);
        out.writeln("<archetype_id>");
        printNoneEmptyString("value", archetype.getArchetypeId().toString(), 2, out);
        indent(1, out);
        out.writeln("</archetype_id>");

        indent(1, out);
        out.writeln("<concept>" + archetype.getConcept() + "</concept>");
        
        final ArchetypeID parentID = archetype.getParentArchetypeId();
        if(parentID != null) {
            indent(1, out);
            out.writeln("<parent_archetype_id>");
            printNoneEmptyString("value", archetype.getArchetypeId().toString(), 2, out);
            indent(1, out);
            out.writeln("</parent_archetype_id>");
        }
        indent(1, out);
        out.writeln("<original_language>");
        printCodePhrase(archetype.getOriginalLanguage(), 2, out);
        indent(1, out);
        out.writeln("</original_language>");
        indent(1, out);
        out.writeln("<is_controlled>" + (archetype.isControlled() ? "true" : "false") + "</is_controlled>");
    }

    protected void printDescription(ResourceDescription description, CustomWriter out)
            throws IOException {

        if (description == null) {
            return;
        }

        indent(1, out);
        out.writeln("<description>");
        printEmptyStringMap("original_author", description.getOriginalAuthor(), 2, out);
        printNoneEmptyStringList("other_contributors", description.getOtherContributors(), 2, out);     
        printEmptyString("lifecycle_state", description.getLifecycleState(), 2, out); 
         
        for (ResourceDescriptionItem item : description.getDetails()) {
            indent(2, out);
            out.writeln("<details>");
            printDescriptionItem(item, 3, out);
            indent(2, out);
            out.writeln("</details>");     
        }
        
        printNoneEmptyString("resource_package_uri", description.getResourcePackageUri(), 2, out);
        printNoneEmptyStringMap("other_details", description.getOtherDetails(), 2, out);
        indent(1, out);
        out.writeln("</description>");
    }

    protected void printDescriptionItem(ResourceDescriptionItem item,
                                        int indent, CustomWriter out)
            throws IOException {
        
        indent(indent, out);
        out.writeln("<language>"); // Mandatory
        printCodePhrase(item.getLanguage(), indent + 1, out);
        indent(indent, out);
        out.writeln("</language>");
        
        printEmptyString("purpose", item.getPurpose(), indent, out); // Mandatory     
        printNoneEmptyStringList("keywords", item.getKeywords(), indent, out);
        printEmptyString("use", item.getUse(), indent, out); // Mandatory 
        printEmptyString("misuse", item.getMisuse(), indent, out); // Mandatory 
        printNoneEmptyString("copyright", item.getCopyright(), indent, out);
        printNoneEmptyStringMap("original_resource_uri", 
                item.getOriginalResourceUri(), indent, out);
        
        printNoneEmptyStringMap("other_details", item.getOtherDetails(), indent, out);
    }
    
    private void printCodePhrase(CodePhrase cp, int indent, CustomWriter out) throws IOException {
        if(cp == null) {
            return;
        }
        
        printEmptyString("code_string", cp.getCodeString(), indent, out);
        indent(indent, out);
        out.writeln("<terminology_id>");
        printEmptyString("value", cp.getTerminologyId().getValue(), indent + 1, out);
        indent(indent, out);
        out.writeln("</terminology_id>");
    }
    
    
    private void printEmptyStringMap(String label, Map<String, String> map, int indent,
            CustomWriter out) throws IOException {
                
        if(map != null && !map.isEmpty()) {
            for(String key : map.keySet()) {
                indent(indent, out);
                out.write("<" + label + " id=\"" + key + "\">");
                if (map.get(key) != null)
                    out.write(map.get(key));
                out.writeln("</" + label + ">");
            }
        }
    }
    
    private void printNoneEmptyStringMap(String label, Map<String, String> map, int indent,
            CustomWriter out) throws IOException {
        if(map != null && !map.isEmpty()) {
            for(String key : map.keySet()) {
                indent(indent, out);
                out.write("<" + label + " id=\"" + key + "\">");
                out.write(map.get(key));
                out.writeln("</" + label + ">");
            }
        }
    }
    
    
    private void printEmptyString(String label, String value, int indent,
            CustomWriter out) throws IOException {

        indent(indent, out);
        out.write("<" + label + ">");
        if (value != null)
            out.write(value);
        out.writeln("</" + label + ">");
    }
    
    private void printNoneEmptyString(String label, String value, int indent,
            CustomWriter out) throws IOException {

        if (StringUtils.isNotEmpty(value)) {
            indent(indent, out);
            out.write("<" + label + ">");
            out.write(value);
            out.writeln("</" + label + ">");
        }
    }

    private void printNoneEmptyStringList(String label, List<String> list,
            int indent, CustomWriter out) throws IOException {

        if (list == null || list.isEmpty()) {
            return;
        }
        
        
        for(int i = 0, j = list.size(); i < j; i++) {
            indent(indent, out);
            out.write("<" + label + ">");
            out.write(list.get(i));
            out.writeln("</" + label + ">");
        }         
    }

    protected void printDefinition(CComplexObject definition, CustomWriter out)
            throws IOException {
        
        if(definition == null) {
            return;
        }

        indent(1, out);
        out.writeln("<definition>");
        printCComplexObjectTop(definition, 2, out);
        indent(1, out);
        out.writeln("</definition>");
    }
    
    protected void printCComplexObjectTop(CComplexObject ccobj, int indent,
            CustomWriter out) throws IOException {

        printCObjectElements(ccobj, indent, out);

        // print all attributes
        if(!ccobj.isAnyAllowed()) {
            for (CAttribute cattribute : ccobj.getAttributes()) {
                printCAttribute(cattribute, indent, out);
            }
        }
    }

    protected void printCComplexObject(CComplexObject ccobj, int indent,
            CustomWriter out) throws IOException {

        indent(indent, out);
        out.writeln("<children xsi:type=\"C_COMPLEX_OBJECT\">");
        printCObjectElements(ccobj, indent + 1, out);

        // print all attributes
        if(!ccobj.isAnyAllowed()) {
            for (CAttribute cattribute : ccobj.getAttributes()) {
                printCAttribute(cattribute, indent + 1, out);
            }
        }
        
        indent(indent, out);
        out.writeln("</children>");
    }
    
    protected void printConstraintRef(ConstraintRef ref,
            int indent, CustomWriter out) throws IOException {
        
        indent(indent, out);
        out.writeln("<children xsi:type=\"CONSTRAINT_REF\">");
        printCObjectElements(ref, indent + 1, out);
        printEmptyString("reference", ref.getReference(), indent + 1, out);
        indent(indent, out);
        out.writeln("</children>");
    }

    protected void printArchetypeInternalRef(ArchetypeInternalRef ref,
            int indent, CustomWriter out) throws IOException {

        indent(indent, out);
        out.writeln("<children xsi:type=\"ARCHETYPE_INTERNAL_REF\">");
        printCObjectElements(ref, indent + 1, out);
        printEmptyString("target_path", ref.getTargetPath(), indent + 1, out);
        indent(indent, out);
        out.writeln("</children>");
    }

    protected void printArchetypeSlot(ArchetypeSlot slot, int indent, CustomWriter out)
            throws IOException {

        indent(indent, out);
        out.writeln("<children xsi:type=\"ARCHETYPE_SLOT\">");
        printCObjectElements(slot, indent + 1, out);

        // print all attributes
        if (!slot.isAnyAllowed()) {
            if (slot.getIncludes() != null) {
                for (Assertion include : slot.getIncludes()) {
                    indent(indent + 1, out);
                    out.writeln("<includes>");
                    printAssertion(include, indent + 2, out);
                    indent(indent + 1, out);
                    out.writeln("</includes>");
                }
            }
            
            if (slot.getExcludes() != null) {
                for (Assertion exclude : slot.getExcludes()) {
                    indent(indent + 1, out);
                    out.writeln("<excludes>");
                    printAssertion(exclude, indent + 2, out);
                    indent(indent + 1, out);
                    out.writeln("</excludes>");
                }
            }
        }

        indent(indent, out);
        out.writeln("</children>");
    }
    
    protected void printAssertion(Assertion assertion, int indent, CustomWriter out) 
            throws IOException {
        
        printEmptyString("tag", assertion.getTag(), indent, out);
        printEmptyString("string_expression", assertion.getStringExpression(), indent, out);
        printExpressionItem("expression", assertion.getExpression(), indent, out);
        
        if(assertion.getVariables() != null) {
            List<AssertionVariable> variables = assertion.getVariables();

            for(AssertionVariable var : variables) {
                indent(indent, out);
                out.writeln("<variables>");
                printEmptyString("name", var.getName(), indent + 1, out);
                printEmptyString("definition", var.getDefinition(), indent + 1, out);
                indent(indent, out);
                out.writeln("</variables>");
            }
        }
    }
    
    protected void printExpressionItem(String label, ExpressionItem expItem, int indent, CustomWriter out)
            throws IOException {
        
        if(expItem instanceof ExpressionLeaf) {
            printExpressionLeaf(label, (ExpressionLeaf)expItem, indent, out);
        } else if(expItem instanceof ExpressionOperator) {
            printExpressionOperator(label, (ExpressionOperator)expItem, indent, out);
        } else {
            // unknown ExpressionItem
            indent(indent, out);
            out.writeln("<" + label + " xsi:type=\"EXPR_ITEM\">");
            printEmptyString("type", expItem.getType(), indent + 1, out);
            indent(indent, out);
            out.writeln("</" + label + ">");
        }
    }
    
    protected void printExpressionLeaf(String label, ExpressionLeaf expLeaf, int indent, CustomWriter out)
            throws IOException {
        
        indent(indent, out);
        out.writeln("<" + label + " xsi:type=\"EXPR_LEAF\">");
        printEmptyString("type", expLeaf.getType(), indent + 1, out);
        printEmptyString("item", expLeaf.getItem().toString(), indent + 1, out);
        printEmptyString("reference_type", expLeaf.getReferenceType().name(), indent + 1, out);
        indent(indent, out);
        out.writeln("</" + label + ">");
    }
    
    protected void printExpressionOperator(String label, ExpressionOperator expOperator, int indent, CustomWriter out)
            throws IOException {
        
        if(expOperator instanceof ExpressionUnaryOperator) {
            indent(indent, out);
            out.writeln("<" + label + " xsi:type=\"EXPR_UNARY_OPERATOR\">");
            printExpressionOperatorElements(expOperator, indent + 1, out);
            printExpressionItem("operand", ((ExpressionUnaryOperator)expOperator).getOperand(), indent + 1, out);
            indent(indent, out);
            out.writeln("</" + label + ">");
        } else if(expOperator instanceof ExpressionBinaryOperator) {
            final ExpressionBinaryOperator expBinaryOperator = (ExpressionBinaryOperator)expOperator;
            indent(indent, out);
            out.writeln("<" + label + " xsi:type=\"EXPR_BINARY_OPERATOR\">");
            printExpressionOperatorElements(expOperator, indent + 1, out);
            printExpressionItem("left_operand", expBinaryOperator.getLeftOperand(), indent + 1, out);
            printExpressionItem("right_operand", expBinaryOperator.getRightOperand(), indent + 1, out);
            indent(indent, out);
            out.writeln("</" + label + ">");
        } else {
            // unknown ExpressionOperator
            indent(indent, out);
            out.writeln("<" + label + " xsi:type=\"EXPR_OPERATOR\">");
            printExpressionOperatorElements(expOperator, indent + 1, out);
            indent(indent, out);
            out.writeln("</" + label + ">");
        }
    }
    
    protected void printExpressionOperatorElements(ExpressionOperator expOperator, int indent, CustomWriter out)
            throws IOException {
        
        printEmptyString("type", expOperator.getType(), indent, out);
        printEmptyString("operator", String.valueOf(expOperator.getOperator().ordinal()), indent, out);
        // FIXME: Typo in specs, 'precedence_overriden'
        printEmptyString("precedence_overridden", 
                expOperator.isPrecedenceOverridden() == true ? "true" : "false", indent, out);   
    }

    protected void printCAttribute(CAttribute cattribute, int indent, CustomWriter out)
            throws IOException {
        
        final boolean isMultipleAttribute;
        
        if (cattribute instanceof CMultipleAttribute)
            isMultipleAttribute = true;
        else
            isMultipleAttribute = false;
        
        indent(indent, out);
        if(isMultipleAttribute) {
            out.writeln("<attributes xsi:type=\"C_MULTIPLE_ATTRIBUTE\">");
        } else {
            out.writeln("<attributes xsi:type=\"C_SINGLE_ATTRIBUTE\">");
        }

        indent(indent + 1, out);
        out.writeln("<existence>");
        indent(indent + 2, out);
        out.writeln("<lower_unbounded>false</lower_unbounded>");
        indent(indent + 2, out);
        out.writeln("<upper_unbounded>false</upper_unbounded>");
        int lower = 0, upper = 0;
        
        if(cattribute.getExistence().equals(CAttribute.Existence.REQUIRED)) {
            lower = 1;
            upper = 1;
        } else if(cattribute.getExistence().equals(CAttribute.Existence.OPTIONAL)) {
            lower = 0;
            upper = 1;
        }
        indent(indent + 2, out);
        out.writeln("<lower>" + Integer.toString(lower) + "</lower>");
        indent(indent + 2, out);
        out.writeln("<upper>" + Integer.toString(upper) + "</upper>");
        
        indent(indent + 1, out);
        out.writeln("</existence>");
        
        if(cattribute.isAnyAllowed()) {
            indent(indent + 1, out);
            out.write("<any_allowed>");
            out.write("true");
            out.writeln("</any_allowed>");
        }
        
        // FIXME: AOM XML schema spec is wrong. Has 'unbounded' attribute for C_ATTRIBUTE and element with name
        // 'rm_type_name' instead of 'rm_attribute_name'.
        printEmptyString("rm_attribute_name", cattribute.getRmAttributeName(), indent + 1, out);
        
        if(!cattribute.isAnyAllowed()) { 
            List<CObject> children = cattribute.getChildren();

            if (children.size() > 1
                    || !(children.get(0) instanceof CPrimitiveObject)) {
                for (CObject cobject : cattribute.getChildren()) {
                    printCObject(cobject, indent + 1, out);
                }
            } else {
                CObject child = children.get(0);
                printCPrimitiveObject((CPrimitiveObject) child, indent + 1, out);
            }
        }
        
        if(isMultipleAttribute) {
            indent(indent + 1, out);
            out.writeln("<cardinality>");
            printCardinality(
                    ((CMultipleAttribute) cattribute).getCardinality(), indent + 2, out);
            indent(indent + 1, out);
            out.writeln("</cardinality>");
        }     
        
        indent(indent, out);
        out.writeln("</attributes>");
    }

    protected void printCObject(CObject cobj, int indent, CustomWriter out)
            throws IOException {

        // print specialised types
        if (cobj instanceof CDomainType) {
            printCDomainType((CDomainType) cobj, indent, out);
        } else if (cobj instanceof CPrimitiveObject) {
            printCPrimitiveObject((CPrimitiveObject) cobj, indent, out);
        } else if (cobj instanceof CComplexObject) {
            printCComplexObject((CComplexObject) cobj, indent, out);
        } else if (cobj instanceof ArchetypeInternalRef) {
            printArchetypeInternalRef((ArchetypeInternalRef) cobj, indent, out);
        } else if (cobj instanceof ConstraintRef) { // FIXME: Add in ADLSerializer as well
            printConstraintRef((ConstraintRef) cobj, indent, out); 
        } else if (cobj instanceof ArchetypeSlot) {
            printArchetypeSlot((ArchetypeSlot) cobj, indent, out);
        }
    }
    
    protected void printCObjectElements(CObject cobj, int indent, CustomWriter out)
            throws IOException {
        if(cobj.isAnyAllowed()) { // Not sure if needed.
            printNoneEmptyString("any_allowed", "true", indent, out);
        }
        
        printEmptyString("rm_type_name", cobj.getRmTypeName(), indent, out);
        printOccurrences(cobj.getOccurrences(), indent, out);
        printEmptyString("node_id", cobj.getNodeID(), indent, out);
    }
    
    protected void printOccurrences(Interval occurrences, int indent, CustomWriter out) 
            throws IOException {
        
        if(occurrences == null) {
            return;
        }
        
        indent(indent, out);
        out.writeln("<occurrences>");
        printInterval(occurrences, indent + 2, out);
        indent(indent, out);
        out.writeln("</occurrences>");
    }

    protected void printCardinality(Cardinality cardinality, int indent, CustomWriter out)
            throws IOException {
               
        if (cardinality.isOrdered()) {
            printNoneEmptyString("is_ordered", "true", indent, out);
        } else {
            printNoneEmptyString("is_ordered", "false", indent, out);
        }
        
        if (cardinality.isUnique()) {
            printNoneEmptyString("is_unique", "true", indent, out);
        } else {
            printNoneEmptyString("is_unique", "false", indent, out);
        }
        
        indent(indent, out);
        out.writeln("<interval>");
        printInterval(cardinality.getInterval(), indent + 1, out);
        indent(indent, out);
        out.writeln("</interval>");
    }

    protected void printCDomainType(CDomainType cdomain, int indent, CustomWriter out)
            throws IOException {
        
        if (cdomain instanceof CCodePhrase) {
            printCCodePhrase((CCodePhrase) cdomain, indent, out);
        } else if (cdomain instanceof CDvOrdinal) {
            printCDvOrdinal((CDvOrdinal) cdomain, indent, out);
        } else if (cdomain instanceof CDvQuantity) {
            printCDvQuantity((CDvQuantity) cdomain, indent, out);
        } else {
            // unknown CDomainType
            System.err.println("Cannot serialize CDomainType of type '" + cdomain.getClass().getName() + "'!");
        }
    }

    protected void printCCodePhrase(CCodePhrase ccp, int indent, CustomWriter out)
            throws IOException {

        indent(indent, out);
        out.writeln("<children xsi:type=\"C_CODE_PHRASE\">"); // TODO: Says C_CODED_TERM in specifications....       
        printCObjectElements(ccp, indent + 1, out);
        
        if(ccp.getTerminologyId() != null) {
            printNoneEmptyString("terminology", ccp.getTerminologyId().getValue(), indent + 1, out);
        }
        
        if (ccp.getCodeList() != null) {
            final List<String> codeList = ccp.getCodeList();
            
            for (int i = 0, j = codeList.size(); i < j; i++) {
                printNoneEmptyString("code_list", codeList.get(i), indent + 1, out);
            }
        }
        
        indent(indent, out);
        out.writeln("</children>");
    }

    protected void printCDvOrdinal(CDvOrdinal cordinal, int indent, CustomWriter out)
            throws IOException {
        
        indent(indent, out);
        out.writeln("<children xsi:type=\"C_DV_ORDINAL\">");  
        printCObjectElements(cordinal, indent + 1, out);

        if(cordinal.getList() != null) {
            final Set<Ordinal> ordinals = cordinal.getList();

            Ordinal ordinal;
            for (Iterator<Ordinal> it = ordinals.iterator(); it.hasNext();) {
                ordinal = it.next();
                indent(indent + 1, out);
                out.writeln("<list>");
                printEmptyString("value", String.valueOf(ordinal.getValue()), indent + 2, out);
                indent(indent + 2, out);
                out.writeln("<symbol>");
                indent(indent + 3, out);
                out.writeln("<defining_code>");
                printCodePhrase(ordinal.getSymbol(), indent + 4, out);
                indent(indent + 3, out);
                out.writeln("</defining_code>");
                indent(indent + 2, out);
                out.writeln("</symbol>");
                indent(indent + 1, out);
                out.writeln("</list>");
            }
        }
        
        indent(indent, out);
        out.writeln("</children>");
    }

    protected void printCDvQuantity(CDvQuantity cquantity, int indent,
            CustomWriter out) throws IOException {
        
        indent(indent, out);
        out.writeln("<children xsi:type=\"C_DV_QUANTITY\">");  
        printCObjectElements(cquantity, indent + 1, out);

        
        CodePhrase property = cquantity.getProperty();
        if (property != null) {
            indent(indent + 1, out);
            out.writeln("<property>");
            printCodePhrase(property, indent + 2, out);
            indent(indent + 1, out);
            out.writeln("</property>");
        }

        if (cquantity.getList() != null) {
            final List<CDvQuantityItem> list = cquantity.getList();

            for (CDvQuantityItem item : list) {
                indent(indent + 1, out);
                out.writeln("<list>");
                
                if(item.getValue() != null) {
                    indent(indent + 2, out);
                    out.writeln("<magnitude>");
                    printInterval(item.getValue(), indent + 3, out);
                    indent(indent + 2, out);
                    out.writeln("</magnitude>"); 
                }
                
                printEmptyString("units", item.getUnits(), indent + 2, out);
                indent(indent + 1, out);
                out.writeln("</list>");
            }
        }

        indent(indent, out);
        out.writeln("</children>"); 
    }

    protected void printOntology(ArchetypeOntology ontology, String concept, CustomWriter out)
            throws IOException {
        
        if(ontology == null) {
            return;
        }

        indent(1, out);
        out.writeln("<ontology>");

        // TODO: Check why this is not in the XML schema specification of the AOM.
        //printNoneEmptyString("primary_language", ontology.getPrimaryLanguage(), 2, out);
        // TODO: Check why this is not in the XML schema specification of the AOM.
        //printNoneEmptyStringList("languages_available", ontology.getLanguages(), 2, out);
        //printNoneEmptyStringList("terminologies_available", ontology.getTerminologies(), 2, out);
        //final int specDepth = StringUtils.countMatches(".", concept);
        //printNoneEmptyString("specialisation_depth", String.valueOf(specDepth), 2, out);
        
        
        if(ontology.getTermDefinitionsList() != null) {
            final List<OntologyDefinitions> termDefinitions = ontology.getTermDefinitionsList();
            
            for (OntologyDefinitions defs : termDefinitions) {
                indent(2, out);
                out.writeln("<term_definitions language=\"" + defs.getLanguage() + "\">");
                
                for (ArchetypeTerm term : defs.getDefinitions()) {
                    indent(3, out);
                    out.writeln("<items code=\"" + term.getCode() + "\">");
                    printNoneEmptyStringMap("items", term.getItems(), 4, out);
                    indent(3, out);
                    out.writeln("</items>");
                }
                
                indent(2, out);
                out.writeln("</term_definitions>");
            }
        }
        
        if(ontology.getConstraintDefinitionsList() != null) {
            final List<OntologyDefinitions> constraintDefinitions = ontology.getConstraintDefinitionsList();
                
            for (OntologyDefinitions defs : constraintDefinitions) {
                indent(2, out);
                out.writeln("<constraint_definitions language=\"" + defs.getLanguage() + "\">");
                
                for (ArchetypeTerm term : defs.getDefinitions()) {
                    indent(3, out);
                    out.writeln("<items code=\"" + term.getCode() + "\">");
                    printNoneEmptyStringMap("items", term.getItems(), 4, out);
                    indent(3, out);
                    out.writeln("</items>");
                }
                
                
                indent(2, out);
                out.writeln("</constraint_definitions>");
            }
        }

        if (ontology.getTermBindingList() != null) {
            final List<OntologyBinding> termBindings = ontology.getTermBindingList();
      
            TermBindingItem item;
            for (OntologyBinding bind : termBindings) {
                indent(2, out);
                out.writeln("<term_bindings terminology=\"" + bind.getTerminology() + "\">");

                for (OntologyBindingItem bindItem : bind.getBindingList()) {
                    item = (TermBindingItem) bindItem;

                    for(String value : item.getTerms()) {
                        indent(3, out);
                        out.writeln("<items code=\"" + item.getCode() + "\">");
                        indent(4, out);
                        out.writeln("<value>");
                        String terminologyId = value.substring(1, value.lastIndexOf("::"));
                        String codeString = value.substring(value.lastIndexOf("::") + 2, value.length() - 1);
                        indent(5, out);
                        out.writeln("<terminology_id>");
                        printEmptyString("value", terminologyId, 6, out);
                        indent(5, out);
                        out.writeln("</terminology_id>");
                        printEmptyString("code_string", codeString, 5, out);
                        indent(4, out);
                        out.writeln("</value>");
                        indent(3, out);
                        out.writeln("</items>");
                    }
                }
                
                indent(2, out);
                out.writeln("</term_bindings>");
            }
        }
        
        if (ontology.getConstraintBindingList() != null) {
            final List<OntologyBinding> constraintBindings = ontology.getConstraintBindingList();
            
            QueryBindingItem item;
            for (OntologyBinding bind : constraintBindings) {
                indent(2, out);
                out.writeln("<constraint_bindings  terminology=\"" + bind.getTerminology() + "\">");

                for (OntologyBindingItem bindItem : bind.getBindingList()) {
                    item = (QueryBindingItem) bindItem;
                    indent(3, out);
                    out.writeln("<items code=\"" + item.getCode() + "\">");
                    printEmptyString("value", item.getQuery().getUrl(), 4, out);
                    indent(3, out);
                    out.writeln("</items>");
                }
                
                indent(2, out);
                out.writeln("</constraint_bindings>");
            }
        }
        
        indent(1, out);
        out.writeln("</ontology>");
    }

    protected void printCPrimitiveObject(CPrimitiveObject cpo, int indent, CustomWriter out)
            throws IOException {
        
        CPrimitive cp = cpo.getItem();
        
        if(cp == null) {
            return;
        }
        
        indent(indent, out);
        out.writeln("<children xsi:type=\"C_PRIMITIVE_OBJECT\">");
        printCObjectElements(cpo, indent + 1, out);
        
        indent(indent + 1, out);
        final int primIndent = indent + 2;        
        if (cp instanceof CBoolean) {
            out.writeln("<item xsi:type=\"C_BOOLEAN\">");
            printCBoolean((CBoolean) cp, primIndent, out);
        } else if (cp instanceof CDate) {
            out.writeln("<item xsi:type=\"C_DATE\">");
            printCDate((CDate) cp, primIndent, out);
        } else if (cp instanceof CDateTime) {
            out.writeln("<item xsi:type=\"C_DATE_TIME\">");
            printCDateTime((CDateTime) cp, primIndent, out);
        } else if (cp instanceof CTime) {
            out.writeln("<item xsi:type=\"C_TIME\">");
            printCTime((CTime) cp, primIndent, out);
        } else if (cp instanceof CDuration) {
            out.writeln("<item xsi:type=\"C_DURATION\">");
            printCDuration((CDuration) cp, primIndent, out);
        } else if (cp instanceof CInteger) {
            out.writeln("<item xsi:type=\"C_INTEGER\">");
            printCInteger((CInteger) cp, primIndent, out);
        } else if (cp instanceof CReal) {
            out.writeln("<item xsi:type=\"C_REAL\">");
            printCReal((CReal) cp, primIndent, out);
        } else if (cp instanceof CString) {
            out.writeln("<item xsi:type=\"C_STRING\">");
            printCString((CString) cp, primIndent, out);
        } else {
            out.writeln("<item xsi:type=\"C_PRIMITIVE\">");
            System.err.println("Cannot serialize CPrimitive of type '" + cp.getClass().getName() + "'!");
        }
        
        indent(indent + 1, out);
        out.writeln("</item>");
        indent(indent, out);
        out.writeln("</children>");
    }

    protected void printCBoolean(CBoolean cboolean, int indent, CustomWriter out)
            throws IOException {
        
        printNoneEmptyString("true_valid", cboolean.isTrueValid() == true ?
                "true" : "false", indent, out);
        printNoneEmptyString("false_valid", cboolean.isFalseValid() == true ?
                "true" : "false", indent, out);
        
        if(cboolean.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", cboolean.assumedValue().booleanValue() == true ?
                    "true" : "false", indent, out);
        }
    }

    protected void printCDate(CDate cdate, int indent, CustomWriter out) throws IOException {
        
        if (cdate.getPattern() != null) {
            printNoneEmptyString("pattern", cdate.getPattern(), indent, out);
        } 
        
        if(cdate.getInterval() != null) {
            indent(indent, out);
            out.writeln("<range>");
            printInterval(cdate.getInterval(), indent + 1, out);
            indent(indent, out);
            out.writeln("</range>");
        }
        
        if(cdate.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", cdate.assumedValue().toString(), indent, out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCDateTime(CDateTime cdatetime, int indent, CustomWriter out)
            throws IOException {
        
        if (cdatetime.getPattern() != null) {
            printNoneEmptyString("pattern", cdatetime.getPattern(), indent, out);
        }
        
        // FIXME: Output timezone_validity. CDateTime seem to be missing this function.
        
        if(cdatetime.getInterval() != null) {
            indent(indent, out);
            out.writeln("<range>");
            printInterval(cdatetime.getInterval(), indent + 1, out);
            indent(indent, out);
            out.writeln("</range>");
        }
        
        if(cdatetime.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", cdatetime.assumedValue().toString(), indent, out);
        }
    }

    protected void printCTime(CTime ctime, int indent, CustomWriter out) throws IOException {
        
        if (ctime.getPattern() != null) {
            printNoneEmptyString("pattern", ctime.getPattern(), indent, out);
        }
        
        // FIXME: Output timezone_validity. CTime seem to be missing this function.
        
        if(ctime.getInterval() != null) {
            indent(indent, out);
            out.writeln("<range>");
            printInterval(ctime.getInterval(), indent + 1, out);
            indent(indent, out);
            out.writeln("</range>");
        }
        
        if(ctime.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", ctime.assumedValue().toString(), indent, out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCDuration(CDuration cduration, int indent, CustomWriter out)
            throws IOException {
                
        if (cduration.getValue() != null) {
            printNoneEmptyString("pattern", cduration.getValue().toString(), indent, out);
        } 

        if(cduration.getInterval() != null) {
            indent(indent, out);
            out.writeln("<range>");
            printInterval(cduration.getInterval(), indent + 1, out);
            indent(indent, out);
            out.writeln("</range>");
        }
        
        if(cduration.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", cduration.assumedValue().toString(), indent, out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCInteger(CInteger cinteger, int indent, CustomWriter out)
            throws IOException {
                       
        if(cinteger.getList() != null) {
            printList(cinteger.getList(), indent, out);
        } 
        
        if(cinteger.getInterval() != null) {
            indent(indent, out);
            out.writeln("<range>");
            printInterval(cinteger.getInterval(), indent + 1, out);
            indent(indent, out);
            out.writeln("</range>");
        }
        
        // TODO: Write 'list_open' element... <list_open> xs:boolean </list_open> [0..1]
        
        if(cinteger.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", cinteger.assumedValue().toString(), indent, out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCReal(CReal creal, int indent, CustomWriter out) throws IOException {
                       
        if (creal.getList() != null) {
            printList(creal.getList(), indent, out);
        } 
        
        if(creal.getInterval() != null) {
            indent(indent, out);
            out.writeln("<range>");
            printInterval(creal.getInterval(), indent + 1, out);
            indent(indent, out);
            out.writeln("</range>");
        }
        
        if(creal.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", creal.assumedValue().toString(), indent, out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printCString(CString cstring, int indent, CustomWriter out) throws IOException {
        
        if(cstring.getPattern() != null) {
            printNoneEmptyString("pattern", cstring.getPattern(), indent, out);
        }
        
        if(cstring.getList() != null){
            printList(cstring.getList(), indent, out);
        }
        
        // TODO: Write 'list_open' element... <list_open> xs:boolean </list_open> [0..1]
        
        if(cstring.hasAssumedValue()) {
            printNoneEmptyString("assumed_value", cstring.assumedValue().toString(), indent, out); // FIXME: Specs has typo 'assummed_value'.
        }
    }

    protected void printList(List list, int indent, CustomWriter out)
            throws IOException {
        
        if(list == null) {
            return;
        }

        for(int i = 0, j = list.size(); i < j; i++) {
            printNoneEmptyString("list", list.get(i).toString(), indent, out);
        }
    }

    @SuppressWarnings("unchecked")
    protected void printInterval(Interval interval, int indent, CustomWriter out)
            throws IOException {
        if(interval == null) {
            return;
        }
        
        final Comparable lower = interval.getLower();
        final Comparable upper = interval.getUpper();

        printNoneEmptyString("upper_included",
                interval.isUpperIncluded() == true ? "true" : "false",
                indent, out);
        printNoneEmptyString("lower_included",
                interval.isLowerIncluded() == true ? "true" : "false",
                indent, out);
  
        
        printNoneEmptyString("upper_unbounded",
                       interval.isUpperUnbounded() ? "true" : "false",
                                indent + 1, out);
        printNoneEmptyString("lower_unbounded",
                       interval.isLowerUnbounded() ? "true" : "false",
                                indent + 1, out);
        
        if(upper != null) {
            printNoneEmptyString("upper", upper.toString(), indent, out);
        }
        
        if(lower != null) {
            printNoneEmptyString("lower", lower.toString(), indent, out);
        }
    }

    private void indent(int level, CustomWriter out) throws IOException {
        for (int i = 0; i < level; i++) {
            out.write(indent);
        }
    }

    /* charset encodings */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static final Charset LATIN1 = Charset.forName("ISO-8859-1");

    /* fields */  
    private Charset encoding;

    private String lineSeparator;

    private String indent;
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
 * Portions created by the Initial Developer are Copyright (C) 2005-2006
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