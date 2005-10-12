/*
 * Copyright (C) 2004 Rong Chen, Acode HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package org.openehr.am.archetype.output;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.primitive.*;
import org.openehr.am.archetype.constraintmodel.domain.*;
import org.openehr.am.archetype.description.ArchetypeDescription;
import org.openehr.am.archetype.description.ArchetypeDescriptionItem;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.basic.Interval;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * ADLOutputter
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ADLOutputter {

    /**
     * Create an outputter with default encoding, indent and lineSeparator
     */
    public ADLOutputter() {
        this.encoding = UTF8;
        this.indent = "    "; // 4 white space characters
        this.lineSeparator = "\r\n";
    }

    /**
     * Output given archetype as string
     *
     * @param archetype
     * @return
     * @throws IOException
     */
    public String output(Archetype archetype) throws IOException {
        StringWriter writer = new StringWriter();
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
        Writer writer = new BufferedWriter(new OutputStreamWriter(
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
    public void output(Archetype archetype, Writer out) throws IOException {
        printHeader(archetype.getArchetypeId(),
                archetype.getParentArchetypeId(),
                archetype.getConceptCode(), out);
        newline(out);

        printDescription(archetype.getDescription(), out);
        newline(out);

        printDefinition(archetype.getDefinition(), out);
        newline(out);

        printOntology(archetype.getOntology(), out);
    }

    protected void printHeader(ArchetypeID id, ArchetypeID parentId,
                               String conceptCode, Writer out)
            throws IOException {

        out.write("archetype");
        newline(out);
        indent(1, out);
        out.write(id.toString());
        newline(out);

        if (parentId != null) {
            out.write("specialize");
            newline(out);
            indent(1, out);
            out.write(parentId.toString());
            newline(out);
        }

        out.write("concept");
        newline(out);
        indent(1, out);
        out.write("[" + conceptCode + "]");
        newline(out);
    }

    protected void printDescription(ArchetypeDescription description,
                                    Writer out)
            throws IOException {

        if (description == null) {
            return;
        }

        out.write("description");
        newline(out);

        indent(1, out);
        out.write("author = <\"");
        out.write(description.getOriginalAuthor());
        out.write("\">");
        newline(out);

        indent(1, out);
        out.write("status = <\"");
        out.write(description.getLifecycleState());
        out.write("\">");
        newline(out);

        for (ArchetypeDescriptionItem item : description.getDetails()) {
            printDescriptionItem(item, 1, out);
        }
    }

    protected void printDescriptionItem(ArchetypeDescriptionItem item,
                                        int indent, Writer out)
            throws IOException {
        indent(indent, out);
        out.write("description(\"");
        out.write(item.getLanguage().getCodeString());
        out.write("\") = <");
        newline(out);

        printNonEmptyString("purpose", item.getPurpose(), indent + 1, out);
        printNonEmptyString("use", item.getUse(), indent + 1, out);
        printNonEmptyString("misuse", item.getMisuse(), indent + 1, out);
        printNonEmptyString("copyright", item.getCopyright(), indent + 1, out);
        indent(indent, out);
        out.write(">");
        newline(out);
    }

    private void printNonEmptyString(String label, String value, int indent,
                                     Writer out)
            throws IOException {

        if (StringUtils.isEmpty(value)) {
            return;
        }
        indent(indent, out);
        out.write(label);
        out.write(" = <\"");
        out.write(value);
        out.write("\">");
        newline(out);
    }

    protected void printDefinition(CComplexObject definition, Writer out)
            throws IOException {

        out.write("definition");
        newline(out);

        printCComplexObject(definition, 1, out);
    }

    protected void printCComplexObject(CComplexObject ccobj, int indent,
                                       Writer out) throws IOException {

        // print rmTypeName and nodeId
        indent(indent, out);
        out.write(ccobj.getRmTypeName());
        if (StringUtils.isNotEmpty(ccobj.getNodeID())) {
            out.write("[" + ccobj.getNodeID() + "]");
        }

        printOccurrences(ccobj.getOccurrences(), out);

        out.write(" matches {");
        newline(out);

        // print all attributes
        for (CAttribute cattribute : ccobj.getAttributes()) {
            printCAttribute(cattribute, indent + 1, out);
        }

        indent(indent, out);
        out.write("}");
        newline(out);
    }
    
    protected void printOccurrences(Interval<Integer> occurrences,
                                    Writer out) throws IOException {
        
        if (occurrences != null) {
            out.write(" occurrences matches {");
            if (occurrences.getLower() == null) {
                out.write("*");
            } else {
                out.write(Integer.toString(occurrences.getLower()));
            }
            out.write("..");
            if (occurrences.getUpper() == null) {
                out.write("*");
            } else {
                out.write(Integer.toString(occurrences.getUpper()));
            }
            out.write("}");
        }
    }

    protected void printArchetypeInternalRef(ArchetypeInternalRef ref,
                                             int indent,
                                             Writer out) throws IOException {
        indent(indent, out);
        printOccurrences(ref.getOccurrences(), out);
        out.write(" use_node ");
        out.write(ref.getRmTypeName());
        out.write(" ");
        out.write(ref.getTargetPath());
        newline(out);
    }

    protected void printCAttribute(CAttribute cattribute, int indent,
                                   Writer out) throws IOException {
        indent(indent, out);
        out.write(cattribute.getRmAttributeName());
        if (!CAttribute.Existence.REQUIRED.equals(cattribute.getExistence())) {
            out.write(" ");
        }
        printExistence(cattribute.getExistence(), out);
        if (cattribute instanceof CMultipleAttribute) {
            out.write(" ");
            printCardinality(
                    ( (CMultipleAttribute) cattribute ).getCardinality(),
                    out);
        }
        out.write(" matches {");
        List<CObject> children = cattribute.getChildren();
        if (children.size() != 1
                || !( children.get(0) instanceof CPrimitiveObject )) {
            newline(out);
            for (CObject cobject : cattribute.getChildren()) {
                printCObject(cobject, indent + 1, out);
            }
            indent(indent, out);
        } else {
            CObject child = children.get(0);
            printCPrimitiveObject((CPrimitiveObject) child, out);
        }
        out.write("}");
        newline(out);
    }

    protected void printExistence(CAttribute.Existence existence, Writer out)
            throws IOException {
        if (CAttribute.Existence.REQUIRED.equals(existence)) {
            return;
        }
        out.write("existence matches ");
        if (CAttribute.Existence.OPTIONAL.equals(existence)) {
            out.write("{0..1}");
        } else {
            out.write("{0}");
        }
    }

    protected void printCObject(CObject cobj, int indent, Writer out)
            throws IOException {

        // print specialised types
        if (cobj instanceof CDomainType) {
            printCDomainType((CDomainType) cobj, indent, out);
        } else if (cobj instanceof CPrimitiveObject) {
            printCPrimitiveObject((CPrimitiveObject) cobj, out);
        } else if (cobj instanceof CComplexObject) {
            printCComplexObject((CComplexObject) cobj, indent, out);
        } else if (cobj instanceof ArchetypeInternalRef) {
            printArchetypeInternalRef((ArchetypeInternalRef) cobj, indent, out);
        }
    }

    protected void printCardinality(Cardinality cardinality, Writer out)
            throws IOException {
        out.write("cardinality matches {");
        Interval<Integer> interval = cardinality.getInterval();
        if (interval != null) {
            if (interval.isLowerUnbounded()) {
                out.write("*");
            } else {
                out.write(interval.getLower().toString());
            }
            out.write("..");
            if (interval.isUpperUnbounded()) {
                out.write("*");
            } else {
                out.write(interval.getUpper().toString());
            }
        } else {
            out.write("*");
        }
        out.write("; ");
        if (cardinality.isOrdered()) {
            out.write("ordered");
        } else {
            out.write("unordered");
        }
        if (cardinality.isUnique()) {
            out.write("; unique");
        }
        out.write("}");
    }

    protected void printCDomainType(CDomainType cdomain, int indent,
                                    Writer out) throws IOException {
        if (cdomain instanceof CCount) {
            printCCount((CCount) cdomain, indent, out);
        } else if (cdomain instanceof CCodedText) {
            printCCodedText((CCodedText) cdomain, indent, out);
        } else if (cdomain instanceof COrdinal) {
            printCOrdinal((COrdinal) cdomain, indent, out);
        } else if (cdomain instanceof CQuantity) {
            printCQuantity((CQuantity) cdomain, indent, out);
        }
        // unknow CDomainType
    }

    /* not using DV_COUNT because this is a domain type extension */
    protected void printCCount(CCount ccount, int indent, Writer out)
            throws IOException {
        indent(indent, out);
        out.write("COUNT matches {");
        newline(out);
        indent(indent + 1, out);
        out.write("magnitude matches {");
        printInterval(ccount.getMagnitude(), out);
        out.write("}");
        newline(out);
        indent(indent, out);
        out.write("}");
        newline(out);
    }

    protected void printCCodedText(CCodedText ccoded, int indent, Writer out)
            throws IOException {

        indent(indent, out);
        out.write("[" + ccoded.getTerminology() + "::");
        if (ccoded.getCodeList().size() > 1) {
            newline(out);
        }
        for (int i = 0, j = ccoded.getCodeList().size(); i < j; i++) {
            if (j > 1) {
                indent(indent, out);
            }
            out.write(ccoded.getCodeList().get(i));
            if (i != j - 1) {
                out.write(",");
            } else {
                out.write("]");
            }
            newline(out);
        }
    }

    protected void printCOrdinal(COrdinal cordinal, int indent, Writer out)
            throws IOException {

        for (int i = 0, j = cordinal.getList().size(); i < j; i++) {
            Ordinal ordinal = cordinal.getList().get(i);
            indent(indent, out);
            out.write(Integer.toString(ordinal.getValue()));
            out.write("|[");
            out.write(ordinal.getTerminology());
            out.write("::");
            out.write(ordinal.getCode());
            out.write("]");
            if (i != j - 1) {
                out.write(",");
            }
            newline(out);
        }
    }

    /* not using DV_QUANTITY because this is a domain type extension */
    protected void printCQuantity(CQuantity cquantity, int indent, Writer out)
            throws IOException {
        indent(indent, out);
        out.write("QUANTITY matches {");
        newline(out);
        indent(indent + 1, out);
        out.write("magnitude matches {");
        printInterval(cquantity.getMagnitude(), out);
        out.write("}");
        newline(out);
        indent(indent + 1, out);
        out.write("units matches {\"");
        out.write(cquantity.getUnits());
        out.write("\"}");
        newline(out);
        indent(indent, out);
        out.write("}");
        newline(out);
    }

    protected void printOntology(ArchetypeOntology ontology, Writer out)
            throws IOException {

        out.write("ontology");
        newline(out);

        indent(1, out);
        out.write("primary_language = <\"");
        out.write(ontology.getPrimaryLanguage());
        out.write("\">");
        newline(out);

        indent(1, out);
        out.write("languages_available = <\"");
        for (String lang : ontology.getLanguages()) {
            out.write(lang);
            out.write("\", ");
        }
        out.write("...>");
        newline(out);

        for (OntologyDefinitions defs : ontology.getTermDefinitionsList()) {
            indent(1, out);
            out.write("term_definitions(\"");
            out.write(defs.getLanguage());
            out.write("\") = <");
            newline(out);
            for (DefinitionItem item : defs.getDefinitions()) {
                indent(2, out);
                out.write("items(\"");
                out.write(item.getCode());
                out.write("\") = <");
                newline(out);
                indent(3, out);
                out.write("text = <\"");
                out.write(item.getText());
                out.write("\">");
                newline(out);
                indent(3, out);
                out.write("description = <\"");
                out.write(item.getDescription());
                out.write("\">");
                newline(out);
                indent(2, out);
                out.write(">");
                newline(out);
            }
            indent(1, out);
            out.write(">");
            newline(out);
        }
    }

    protected void printCPrimitiveObject(CPrimitiveObject cpo, Writer out)
            throws IOException {

        CPrimitive cp = cpo.getItem();
        if (cp instanceof CBoolean) {
            printCBoolean((CBoolean) cp, out);
        } else if (cp instanceof CDate) {
            printCDate((CDate) cp, out);
        } else if (cp instanceof CDateTime) {
            printCDateTime((CDateTime) cp, out);
        } else if (cp instanceof CTime) {
            printCTime((CTime) cp, out);
        } else if (cp instanceof CDuration) {
            printCDuration((CDuration) cp, out);
        } else if (cp instanceof CInteger) {
            printCInteger((CInteger) cp, out);
        } else if (cp instanceof CReal) {
            printCReal((CReal) cp, out);
        } else if (cp instanceof CString) {
            printCString((CString) cp, out);
        }
        // unknow CPrimitive type
    }

    protected void printCBoolean(CBoolean cboolean, Writer out)
            throws IOException {
        if (cboolean.isTrueValid()) {
            out.write("true");
            if (cboolean.isFalseValid()) {
                out.write(", false");
            }
        } else {
            out.write("false");
        }
    }

    protected void printCDate(CDate cdate, Writer out)
            throws IOException {
        if (cdate.getPattern() != null) {
            out.write(cdate.getPattern());
        } else if (cdate.getList() != null) {
            out.write(cdate.getList().get(0).toString());
        } else {
            printInterval(cdate.getInterval(), out);
        }
    }

    protected void printCDateTime(CDateTime cdatetime, Writer out)
            throws IOException {
        if (cdatetime.getPattern() != null) {
            out.write(cdatetime.getPattern());
        } else if (cdatetime.getList() != null) {
            out.write(cdatetime.getList().get(0).toString());
        } else {
            printInterval(cdatetime.getInterval(), out);
        }
    }

    protected void printCTime(CTime ctime, Writer out)
            throws IOException {
        if (ctime.getPattern() != null) {
            out.write(ctime.getPattern());
        } else if (ctime.getList() != null) {
            out.write(ctime.getList().get(0).toString());
        } else {
            printInterval(ctime.getInterval(), out);
        }
    }

    protected void printCDuration(CDuration cduration, Writer out)
            throws IOException {
        if (cduration.getValue() != null) {
            out.write(cduration.getValue().toString());
        } else {
            printInterval(cduration.getInterval(), out);
        }
    }

    protected void printCInteger(CInteger cinteger, Writer out)
            throws IOException {
        if (cinteger.getList() != null) {
            printList(cinteger.getList(), out);
        } else {
            printInterval(cinteger.getInterval(), out);
        }
    }

    protected void printCReal(CReal creal, Writer out)
            throws IOException {
        if (creal.getList() != null) {
            printList(creal.getList(), out);
        } else {
            printInterval(creal.getInterval(), out);
        }
    }

    protected void printCString(CString cstring, Writer out)
            throws IOException {
        if (cstring.getPattern() != null) {
            out.write("/" + cstring.getPattern() + "/");
        } else {
            printList(cstring.getList(), out, true);
        }
    }

    protected void printList(List list, Writer out) throws IOException {
        printList(list, out, false);
    }

    protected void printList(List list, Writer out, boolean string)
            throws IOException {
        for (int i = 0, j = list.size(); i < j; i++) {
            if (i != 0) {
                out.write(",");
            }
            if (string) {
                out.write("\"");
            }
            out.write(list.get(i).toString());
            if (string) {
                out.write("\"");
            }
        }
    }

    protected void printInterval(Interval interval, Writer out)
            throws IOException {
        out.write("|");
        if (interval.getLower() != null && interval.getUpper() != null) {
            out.write(interval.getLower().toString());
            out.write("..");
            out.write(interval.getUpper().toString());
        } else if (interval.getLower() == null) {
            out.write("<");
            if (interval.isUpperInclusive()) {
                out.write("=");
            }
            out.write(interval.getUpper().toString());
        } else {
            out.write(">");
            if (interval.isLowerInclusive()) {
                out.write("=");
            }
            out.write(interval.getLower().toString());
        }
        out.write("|");
    }

    private void newline(Writer out) throws IOException {
        out.write(lineSeparator);
    }

    private void indent(int level, Writer out) throws IOException {
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
