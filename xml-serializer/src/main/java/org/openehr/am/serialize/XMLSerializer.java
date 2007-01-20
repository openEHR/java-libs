package org.openehr.am.serialize;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyBindingItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.ontology.DefinitionItem;
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
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
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
        out.writeln("<archetype xmlns:at=\"openEHR/v1/Archetype\"\n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        indent(1, out);
        out.writeln("<archetype_id>" + archetype.getArchetypeId().toString() + "</archetype_id>");
        indent(1, out);
        out.writeln("<concept_code>" + archetype.getConcept() + "</concept_code>");
        
        final ArchetypeID parentID = archetype.getParentArchetypeId();
        if(parentID != null) {
            indent(1, out);
            out.writeln("<parent_archetype_id>" + parentID.toString() + "</parent_archetype_id>");
        }
        
        // TODO: Output original_language and is_controlled
        // <original_language> CODE_PHRASE </original_language> [1]
        // <is_controlled> xs:boolean </is_controlled> [0..1]
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
        printEmptyString("terminology_id", cp.getTerminologyID().getValue(), indent, out);
    }
    
    private void printDefinitionItem(DefinitionItem defItem, int indent,
            CustomWriter out) throws IOException {
        if(defItem == null) {
            return;
        }
        
        // Text
        indent(indent, out);
        out.writeln("<item>");
        indent(indent + 1, out);
        out.writeln("<key>text</key>");
        indent(indent + 1, out);
        out.writeln("<value>" + defItem.getText() + "</value>");
        indent(indent, out);
        out.writeln("</item>");
        
        // Description
        indent(indent, out);
        out.writeln("<item>");
        indent(indent + 1, out);
        out.writeln("<key>description</key>");
        indent(indent + 1, out);
        out.writeln("<value>" + defItem.getDescription() + "</value>");
        indent(indent, out);
        out.writeln("</item>");
    }
    
    private void printEmptyStringMap(String label, Map<String, String> map, int indent,
            CustomWriter out) throws IOException {
        
        indent(indent, out);
        out.writeln("<" + label + ">");
        
        if(map != null && !map.isEmpty()) {
            for(String key : map.keySet()) {
                indent(indent + 1, out);
                out.writeln("<item>");
                printEmptyString("key", key, indent + 2, out);
                printEmptyString("value", map.get(key), indent + 2, out);
                indent(indent + 1, out);
                out.writeln("</item>");
            }
        }
        
        indent(indent, out);
        out.writeln("</" + label + ">");
    }
    
    private void printNoneEmptyStringMap(String label, Map<String, String> map, int indent,
            CustomWriter out) throws IOException {
        if(map == null || map.isEmpty())
            return;
        
        indent(indent, out);
        out.writeln("<" + label + ">");
        
        for(String key : map.keySet()) {
            indent(indent + 1, out);
            out.writeln("<item>");
            printEmptyString("key", key, indent + 2, out);
            printEmptyString("value", map.get(key), indent + 2, out);
            indent(indent + 1, out);
            out.writeln("</item>");
        }
        
        indent(indent, out);
        out.writeln("</" + label + ">");
    }
    
    
    private void printEmptyString(String label, String value, int indent,
            CustomWriter out) throws IOException {

        indent(indent, out);
        out.write("<" + label + ">");
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
        
        indent(indent, out);
        out.writeln("<" + label + ">");
        
        for(int i = 0, j = list.size(); i < j; i++) {
            indent(indent + 1, out);
            out.write("<item>");
            out.write(list.get(i));
            out.writeln("</item>");
        } 
        
        indent(indent, out);
        out.writeln("</" + label + ">");
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
        out.writeln("<children xsi:type=\"at:C_COMPLEX_OBJECT\">");
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
        out.writeln("<children xsi:type=\"at:CONSTRAINT_REF\">");
        printCObjectElements(ref, indent + 1, out);
        printEmptyString("reference", ref.getReference(), indent + 1, out);
        indent(indent, out);
        out.writeln("</children>");
    }

    protected void printArchetypeInternalRef(ArchetypeInternalRef ref,
            int indent, CustomWriter out) throws IOException {

        indent(indent, out);
        out.writeln("<children xsi:type=\"at:ARCHETYPE_INTERNAL_REF\">");
        printCObjectElements(ref, indent + 1, out);
        printEmptyString("target_path", ref.getTargetPath(), indent + 1, out);
        indent(indent, out);
        out.writeln("</children>");
    }

    protected void printArchetypeSlot(ArchetypeSlot slot, int indent, CustomWriter out)
            throws IOException {

        indent(indent, out);
        out.writeln("<children xsi:type=\"at:ARCHETYPE_SLOT\">");
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
            out.writeln("<" + label + " xsi:type=\"at:EXPR_ITEM\">");
            printEmptyString("type", expItem.getType(), indent + 1, out);
            indent(indent, out);
            out.writeln("</" + label + ">");
        }
    }
    
    protected void printExpressionLeaf(String label, ExpressionLeaf expLeaf, int indent, CustomWriter out)
            throws IOException {
        
        indent(indent, out);
        out.writeln("<" + label + " xsi:type=\"at:EXPR_LEAF\">");
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
            out.writeln("<" + label + " xsi:type=\"at:EXPR_UNARY_OPERATOR\">");
            printExpressionOperatorElements(expOperator, indent + 1, out);
            printExpressionItem("operand", ((ExpressionUnaryOperator)expOperator).getOperand(), indent + 1, out);
            indent(indent, out);
            out.writeln("</" + label + ">");
        } else if(expOperator instanceof ExpressionBinaryOperator) {
            final ExpressionBinaryOperator expBinaryOperator = (ExpressionBinaryOperator)expOperator;
            indent(indent, out);
            out.writeln("<" + label + " xsi:type=\"at:EXPR_BINARY_OPERATOR\">");
            printExpressionOperatorElements(expOperator, indent + 1, out);
            printExpressionItem("left_operand", expBinaryOperator.getLeftOperand(), indent + 1, out);
            printExpressionItem("right_operand", expBinaryOperator.getRightOperand(), indent + 1, out);
            indent(indent, out);
            out.writeln("</" + label + ">");
        } else {
            // unknown ExpressionOperator
            indent(indent, out);
            out.writeln("<" + label + " xsi:type=\"at:EXPR_OPERATOR\">");
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
            out.write("<attributes xsi:type=\"at:C_MULTIPLE_ATTRIBUTE\"");
        } else {
            out.write("<attributes xsi:type=\"at:C_SINGLE_ATTRIBUTE\"");
        }
                    
        if(cattribute.getExistence().equals(CAttribute.Existence.REQUIRED)) {
            out.write(" minOccurs=\"1\"");
            out.write(" maxOccurs=\"1\"");
        } else if(cattribute.getExistence().equals(CAttribute.Existence.OPTIONAL)) {
            out.write(" minOccurs=\"0\"");
            out.write(" maxOccurs=\"1\"");
        } else {
            out.write(" minOccurs=\"0\"");
            out.write(" maxOccurs=\"0\"");
        }
        out.writeln(">");
        
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
        printNoneEmptyString("node_id", cobj.getNodeID(), indent, out);
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
        out.writeln("<children xsi:type=\"at:C_CODED_TEXT\">"); // TODO: Says C_CODED_TERM in specifications....       
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
        out.writeln("<children xsi:type=\"at:C_ORDINAL\">");  
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
                printCodePhrase(ordinal.getSymbol(), indent + 3, out);
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
        out.writeln("<children xsi:type=\"at:C_QUANTITY\">");  
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
        printNoneEmptyStringList("terminologies_available", ontology.getTerminologies(), 2, out);
        final int specDepth = StringUtils.countMatches(".", concept);
        printNoneEmptyString("specialisation_depth", String.valueOf(specDepth), 2, out);
        
        
        if(ontology.getTermDefinitionsList() != null) {
            final List<OntologyDefinitions> termDefinitions = ontology.getTermDefinitionsList();
                
            for (OntologyDefinitions defs : termDefinitions) {
                indent(2, out);
                out.writeln("<term_definitions>");
                printEmptyString("language", defs.getLanguage(), 3, out);
                
                for (DefinitionItem item : defs.getDefinitions()) {
                    indent(3, out);
                    out.writeln("<terms>");
                    printEmptyString("code", item.getCode(), 4, out);
                    indent(4, out);
                    out.writeln("<items>");
                    printDefinitionItem(item, 5, out);
                    indent(4, out);
                    out.writeln("</items>");
                    indent(3, out);
                    out.writeln("</terms>");
                }
                
                indent(2, out);
                out.writeln("</term_definitions>");
            }
        }
        
        if(ontology.getConstraintDefinitionsList() != null) {
            final List<OntologyDefinitions> constraintDefinitions = ontology.getConstraintDefinitionsList();
                
            for (OntologyDefinitions defs : constraintDefinitions) {
                indent(2, out);
                out.writeln("<constraint_definitions>");
                printEmptyString("language", defs.getLanguage(), 3, out);
                
                for (DefinitionItem item : defs.getDefinitions()) {
                    indent(3, out);
                    out.writeln("<terms>");
                    printEmptyString("code", item.getCode(), 4, out);
                    indent(4, out);
                    out.writeln("<items>");
                    printDefinitionItem(item, 5, out);
                    indent(4, out);
                    out.writeln("</items>");
                    indent(3, out);
                    out.writeln("</terms>");
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
                out.writeln("<term_bindings>");
                printEmptyString("terminology", bind.getTerminology(), 3, out);

                for (OntologyBindingItem bindItem : bind.getBindingList()) {
                    item = (TermBindingItem) bindItem;

                    for(String value : item.getTerms()) {
                        indent(3, out);
                        out.writeln("<bindings>");
                        printEmptyString("code", item.getCode(), 4, out);
                        printEmptyString("value", value, 4, out);
                        indent(3, out);
                        out.writeln("</bindings>");
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
                out.writeln("<constraint_bindings>");
                printEmptyString("terminology", bind.getTerminology(), 3, out);

                for (OntologyBindingItem bindItem : bind.getBindingList()) {
                    item = (QueryBindingItem) bindItem;
                    indent(3, out);
                    out.writeln("<bindings>");
                    printEmptyString("code", item.getCode(), 4, out);
                    printEmptyString("value", item.getQuery().getUrl(), 4, out);
                    indent(3, out);
                    out.writeln("</bindings>");
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
        out.writeln("<children xsi:type=\"at:C_PRIMITIVE_OBJECT\">");
        printCObjectElements(cpo, indent + 1, out);
        
        indent(indent + 1, out);
        final int primIndent = indent + 2;        
        if (cp instanceof CBoolean) {
            out.writeln("<item xsi:type=\"at:C_BOOLEAN\">");
            printCBoolean((CBoolean) cp, primIndent, out);
        } else if (cp instanceof CDate) {
            out.writeln("<item xsi:type=\"at:C_DATE\">");
            printCDate((CDate) cp, primIndent, out);
        } else if (cp instanceof CDateTime) {
            out.writeln("<item xsi:type=\"at:C_DATE_TIME\">");
            printCDateTime((CDateTime) cp, primIndent, out);
        } else if (cp instanceof CTime) {
            out.writeln("<item xsi:type=\"at:C_TIME\">");
            printCTime((CTime) cp, primIndent, out);
        } else if (cp instanceof CDuration) {
            out.writeln("<item xsi:type=\"at:C_DURATION\">");
            printCDuration((CDuration) cp, primIndent, out);
        } else if (cp instanceof CInteger) {
            out.writeln("<item xsi:type=\"at:C_INTEGER\">");
            printCInteger((CInteger) cp, primIndent, out);
        } else if (cp instanceof CReal) {
            out.writeln("<item xsi:type=\"at:C_REAL\">");
            printCReal((CReal) cp, primIndent, out);
        } else if (cp instanceof CString) {
            out.writeln("<item xsi:type=\"at:C_STRING\">");
            printCString((CString) cp, primIndent, out);
        } else {
            out.writeln("<item xsi:type=\"at:C_PRIMITIVE\">");
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
        
        String type = null;
        final Comparable lower = interval.getLower();
        final Comparable upper = interval.getUpper();
        
        if(lower instanceof Integer || upper instanceof Integer) {
            type = "integer";
        } else if(lower instanceof Double || upper instanceof Double) {
            type = "real";
        } else if(lower instanceof DvDateTime || upper instanceof DvDateTime) {
            type = "date_time";
        } else if(lower instanceof DvDate || upper instanceof DvDate) {
            type = "date";
        } else if(lower instanceof DvTime || upper instanceof DvTime) {
            type = "time";
        } else if(lower instanceof DvDuration || upper instanceof DvDuration) {
            type = "duration";
        }
                
        printNoneEmptyString("includes_maximum",
                interval.isUpperInclusive() == true ? "true" : "false",
                indent, out);
        printNoneEmptyString("includes_minimum",
                interval.isLowerInclusive() == true ? "true" : "false",
                indent, out);
        
        if(type != null && type.equals("real")) {
            if(upper != null) {
                printNoneEmptyString("has_maximum",
                        interval.has(upper) == true ? "true" : "false",
                                indent + 1, out);
            } else {
                printNoneEmptyString("has_maximum", "false", indent, out);
            }
            
            if(lower != null) {
                printNoneEmptyString("has_minimum",
                        interval.has(lower) == true ? "true" : "false",
                                indent + 1, out);
            } else {
                printNoneEmptyString("has_minimum", "false", indent, out);
            }
        }
        
        if(upper != null) {
            printNoneEmptyString("maximum", upper.toString(), indent, out);
        }
        
        if(lower != null) {
            printNoneEmptyString("minimum", lower.toString(), indent, out);
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
 * Linköpings universitet, Sweden.
 * Portions created by the Initial Developer are Copyright (C) 2005-2006
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):  Mattias Forss <mattias.forss@gmail.com>
 *                  Rong Chen
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