/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ADLSerializer"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005,2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: $"
 * revision:    "$LastChangedRevision: 41 $"
 * last_change: "$LastChangedDate: 2006-07-12 23:24:42 +0200 (Wed, 12 Jul 2006) $"
 */
package org.openehr.am.serialize;

import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.ResourceDescriptionItem;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.basic.Interval;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.ontology.QueryBindingItem;
import org.openehr.am.archetype.ontology.TermBindingItem;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.primitive.*;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ADL serializer for the openEHR Java kernel
 * 
 * @author Rong Chen
 * @author Mattias Forss, Johan Hjalmarsson
 * 
 * @version 1.0
 */
public class ADLSerializer {

	/**
	 * Create an outputter with default encoding, indent and lineSeparator
	 */
	public ADLSerializer() {
		this.encoding = UTF8;
		this.indent = "    "; // 4 white space characters
		this.lineSeparator = "\r\n";
	}

	/**
	 * Output given archetype as string in ADL format
	 * 
	 * @param archetype
	 * @return a string in ADL format
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
		printHeader(archetype.getArchetypeId(), archetype
				.getParentArchetypeId(), archetype.getConcept(), out);
		newline(out);

		printDescription(archetype.getDescription(), out);
		newline(out);

		printDefinition(archetype.getDefinition(), out);
		newline(out);

		printOntology(archetype.getOntology(), out);
		out.flush();
		out.close();
	}

	protected void printHeader(ArchetypeID id, ArchetypeID parentId,
			String conceptCode, Writer out) throws IOException {

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

	protected void printDescription(ResourceDescription description, Writer out)
			throws IOException {

		if (description == null) {
			return;
		}

		out.write("description");
		newline(out);

		indent(1, out);
		out.write("original_author = <");
		newline(out);
		Map<String, String> map = description.getOriginalAuthor();
		for (String key : map.keySet()) {
			indent(2, out);
			out.write("[\"" + key + "\"] = <\"" + map.get(key) + "\">");
			newline(out);
		}
		indent(1, out);
		out.write(">");
		newline(out);

		indent(1, out);
		out.write("lifecycle_state = <\"");
		out.write(description.getLifecycleState());
		out.write("\">");
		newline(out);

		indent(1, out);
		out.write("details = <");
		newline(out);
		for (ResourceDescriptionItem item : description.getDetails()) {
			printDescriptionItem(item, 2, out);
		}
		indent(1, out);
		out.write(">");
		newline(out);
	}

	protected void printDescriptionItem(ResourceDescriptionItem item,
			int indent, Writer out) throws IOException {
		indent(indent, out);
		out.write("[\"");
		out.write(item.getLanguage().getCodeString());
		out.write("\"] = <");
		newline(out);

		printNoneEmptyString("language", item.getLanguage().getCodeString(),
				indent + 1, out);
		printNoneEmptyString("purpose", item.getPurpose(), indent + 1, out);
		printNoneEmptyStringList("keywords", item.getKeywords(), indent + 1,
				out);
		printNoneEmptyString("copyright", item.getCopyright(), indent + 1, out);
		printNoneEmptyString("use", item.getUse(), indent + 1, out);
		printNoneEmptyString("misuse", item.getMisuse(), indent + 1, out);
		printNoneEmptyStringMap("original_resource_uri", item
				.getOriginalResourceUri(), indent + 1, out);

		// other details not printed

		indent(indent, out);
		out.write(">");
		newline(out);
	}

	private void printNoneEmptyString(String label, String value, int indent,
			Writer out) throws IOException {

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

	private void printNoneEmptyStringList(String label, List<String> list,
			int indent, Writer out) throws IOException {

		if (list == null || list.isEmpty()) {
			return;
		}
		indent(indent, out);
		out.write(label);
		out.write(" = <");
		for (int i = 0, j = list.size(); i < j; i++) {
			out.write("\"");
			out.write(list.get(i));
			out.write("\"");
			if (i != j - 1) {
				out.write(",");
			}
		}
		out.write(">");
		newline(out);
	}

	private void printNoneEmptyStringMap(String label, Map<String, String> map,
			int indent, Writer out) throws IOException {
		if (map == null || map.isEmpty()) {
			return;
		}

		indent(indent, out);
		out.write(label);
		out.write(" = <");
		newline(out);

		for (String key : map.keySet()) {
			indent(2, out);
			out.write("[\"" + key + "\"] = <\"" + map.get(key) + "\">");
			newline(out);
		}

		indent(indent, out);
		out.write(">");
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

		// print all attributes
		if (!ccobj.isAnyAllowed()) {
			for (CAttribute cattribute : ccobj.getAttributes()) {
				printCAttribute(cattribute, indent + 1, out);
			}
			newline(out);
			indent(indent, out);
		} else {
			out.write("*");
		}
		out.write("}");
		newline(out);
	}

	protected void printOccurrences(Interval<Integer> occurrences, Writer out)
			throws IOException {

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
			int indent, Writer out) throws IOException {
		indent(indent, out);
		printOccurrences(ref.getOccurrences(), out);
		out.write(" use_node ");
		out.write(ref.getRmTypeName());
		out.write(" ");
		out.write(ref.getTargetPath());
		newline(out);
	}

	protected void printArchetypeSlot(ArchetypeSlot slot, int indent, Writer out)
			throws IOException {

		indent(indent, out);
		out.write("allow_archetype ");
		out.write(slot.getRmTypeName());
		if (StringUtils.isNotEmpty(slot.getNodeID())) {
			out.write("[" + slot.getNodeID() + "]");
		}

		printOccurrences(slot.getOccurrences(), out);
		out.write(" matches {");

		if (slot.isAnyAllowed()) {
			out.write("*");
		} else {
			if (!slot.getIncludes().isEmpty()) {
				printInvariants(slot.getIncludes(), "include", indent, out);
			}
			if (!slot.getExcludes().isEmpty()) {
				printInvariants(slot.getExcludes(), "exclude", indent, out);
			}
		}
		newline(out);
		indent(indent, out);
		out.write("}");
		newline(out);
	}
	
	private void printInvariants(Collection invariants, String purpose,
			int indent, Writer out)	throws IOException {
		newline(out);
		indent(indent + 1, out);
		out.write(purpose);
		for (Object invariant : invariants) {
			newline(out);
			indent(indent + 2, out);
			out.write(invariant.toString());			
		}
	}

	protected void printCAttribute(CAttribute cattribute, int indent, Writer out)
			throws IOException {
		newline(out);
		indent(indent, out);
		out.write(cattribute.getRmAttributeName());
		if (!CAttribute.Existence.REQUIRED.equals(cattribute.getExistence())) {
			out.write(" ");
		}
		printExistence(cattribute.getExistence(), out);
		if (cattribute instanceof CMultipleAttribute) {
			out.write(" ");
			printCardinality(
					((CMultipleAttribute) cattribute).getCardinality(), out);
		}
		out.write(" matches {");
		List<CObject> children = cattribute.getChildren();
		if (children.size() != 1
				|| !(children.get(0) instanceof CPrimitiveObject)) {
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
		} else if (cobj instanceof ArchetypeSlot) {
			printArchetypeSlot((ArchetypeSlot) cobj, indent, out);
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

	protected void printCDomainType(CDomainType cdomain, int indent, Writer out)
			throws IOException {
		if (cdomain instanceof CDvOrdinal) {
			printCDvOrdinal((CDvOrdinal) cdomain, indent, out);
		} else if (cdomain instanceof CDvQuantity) {
			printCDvQuantity((CDvQuantity) cdomain, indent, out);
		}  
		 else if (cdomain instanceof CCodePhrase) {
		    printCCodePhrase((CCodePhrase) cdomain, indent, out);
		}
		// unknow CDomainType
	}

	protected void printCCodePhrase(CCodePhrase ccoded, int indent, Writer out)
			throws IOException {

		indent(indent, out);

		if (ccoded.getTerminologyId() != null) {
			out.write("[" + ccoded.getTerminologyId().getValue() + "::");
		}

		if (ccoded.getCodeList() != null) {
			if (ccoded.getCodeList().size() > 1) {
				newline(out);

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
			} else {
				out.write(ccoded.getCodeList().get(0));
				out.write("]");
				newline(out);
			}
		} 
	}

	protected void printCDvOrdinal(CDvOrdinal cordinal, int indent, Writer out)
			throws IOException {

		for (Iterator<Ordinal> it = cordinal.getList().iterator(); it.hasNext();) {
			Ordinal ordinal = it.next();
			CodePhrase symbol = ordinal.getSymbol();
			indent(indent, out);
			out.write(Integer.toString(ordinal.getValue()));
			out.write("|[");
			out.write(symbol.getTerminologyID().getValue());
			out.write("::");
			out.write(symbol.getCodeString());
			out.write("]");
			if (it.hasNext()) {
				out.write(",");
			}
			newline(out);
		}
	}

	protected void printCDvQuantity(CDvQuantity cquantity, int indent,
			Writer out) throws IOException {
		indent(indent, out);
		out.write("C_QUANTITY <");
		newline(out);
		indent(indent + 1, out);
		CodePhrase property = cquantity.getProperty();
		if (property != null) {
			out.write("property = <[");
			out.write(property.getTerminologyID().getValue());
			out.write("::");
			out.write(property.getCodeString());
			out.write("]>");
		}
		newline(out);
		List<CDvQuantityItem> list = cquantity.getList();
		if (list != null) {
			indent(indent + 1, out);
			out.write("list = <");
			newline(out);
			int index = 1;
			for (CDvQuantityItem item : list) {
				indent(indent + 2, out);
				out.write("[\"");
				out.write(Integer.toString(index));
				out.write("\"] = <");
				newline(out);
				indent(indent + 3, out);
				out.write("units = <\"");
				out.write(item.getUnits());
				out.write("\">");
				newline(out);
				Interval<Double> value = item.getValue();
				if (value != null) {
					indent(indent + 3, out);
					out.write("magnitude = <");
					printInterval(value, out);
					out.write(">");
					newline(out);
				}
				index++;
				indent(indent + 2, out);
				out.write(">");
				newline(out);
			}
			indent(indent + 1, out);
			out.write(">");
			newline(out);
		}
		indent(indent, out);
		out.write(">");
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
		out.write("languages_available = <");
		for (String lang : ontology.getLanguages()) {
			out.write("\"");
			out.write(lang);
			out.write("\", ");
		}
		out.write("...>");
		newline(out);

		if (ontology.getTerminologies() != null) {
			indent(1, out);
			out.write("terminologies_available = <");
			for (String terminology : ontology.getTerminologies()) {
				out.write("\"");
				out.write(terminology);
				out.write("\", ");
			}
			out.write("...>");
			newline(out);
		}

		indent(1, out);
		out.write("term_definitions = <");
		newline(out);
		for (OntologyDefinitions defs : ontology.getTermDefinitionsList()) {
			indent(2, out);
			out.write("[\"");
			out.write(defs.getLanguage());
			out.write("\"] = <");
			newline(out);
			indent(3, out);
			out.write("items = <");
			newline(out);
			for (DefinitionItem item : defs.getDefinitions()) {
				indent(4, out);
				out.write("[\"");
				out.write(item.getCode());
				out.write("\"] = <");
				newline(out);
				indent(5, out);
				out.write("text = <\"");
				out.write(item.getText());
				out.write("\">");
				newline(out);
				indent(5, out);
				out.write("description = <\"");
				out.write(item.getDescription());
				out.write("\">");
				newline(out);
				indent(4, out);
				out.write(">");
				newline(out);
			}
			for (int i = 3; i > 1; i--) {
				indent(i, out);
				out.write(">");
				newline(out);
			}
		}
		indent(1, out);
		out.write(">");
		newline(out);

		if (ontology.getConstraintDefinitionsList() != null) {
			indent(1, out);
			out.write("constraint_definitions = <");
			newline(out);
			for (OntologyDefinitions constraintdefs : ontology
					.getConstraintDefinitionsList()) {
				indent(2, out);
				out.write("[\"");
				out.write(constraintdefs.getLanguage());
				out.write("\"] = <");
				newline(out);
				indent(3, out);
				out.write("items = <");
				newline(out);
				for (DefinitionItem item : constraintdefs.getDefinitions()) {
					indent(4, out);
					out.write("[\"");
					out.write(item.getCode());
					out.write("\"] = <");
					newline(out);
					indent(5, out);
					out.write("text = <\"");
					out.write(item.getText());
					out.write("\">");
					newline(out);
					indent(5, out);
					out.write("description = <\"");
					out.write(item.getDescription());
					out.write("\">");
					newline(out);
					indent(4, out);
					out.write(">");
					newline(out);
				}
				for (int i = 3; i > 1; i--) {
					indent(i, out);
					out.write(">");
					newline(out);
				}
			}
			indent(1, out);
			out.write(">");
			newline(out);
		}

		if (ontology.getTermBindingList() != null) {
			indent(1, out);
			out.write("term_binding = <");
			newline(out);
			for (int i = 0; i < ontology.getTermBindingList().size(); i++) {
				OntologyBinding bind = ontology.getTermBindingList().get(i);
				indent(2, out);
				out.write("[\"");
				out.write(bind.getTerminology());
				out.write("\"] = <");
				newline(out);
				indent(3, out);
				out.write("items = <");
				newline(out);

				for (int j = 0; j < ontology.getTermBindingList().get(i)
						.getBindingList().size(); j++) {
					TermBindingItem item = (TermBindingItem) ontology
							.getTermBindingList().get(i).getBindingList()
							.get(j);
					indent(4, out);
					out.write("[\"");
					out.write(item.getCode());
					out.write("\"] = <");
					out.write(item.getTerms().get(0));

					if (item.getTerms().size() > 1) {
						for (int k = 1; k < item.getTerms().size(); k++)
							out.write("," + item.getTerms().get(k));
					}

					out.write(">");
					newline(out);
				}
				for (int l = 3; l > 1; l--) {
					indent(l, out);
					out.write(">");
					newline(out);
				}
			}
			indent(1, out);
			out.write(">");
			newline(out);
		}

		if (ontology.getConstraintBindingList() != null) {
			indent(1, out);
			out.write("constraint_binding = <");
			newline(out);
			for (int i = 0; i < ontology.getConstraintBindingList().size(); i++) {
				OntologyBinding bind = ontology.getConstraintBindingList().get(
						i);
				indent(2, out);
				out.write("[\"");
				out.write(bind.getTerminology());
				out.write("\"] = <");
				newline(out);
				indent(3, out);
				out.write("items = <");
				newline(out);

				for (int j = 0; j < ontology.getConstraintBindingList().get(i)
						.getBindingList().size(); j++) {
					QueryBindingItem item = (QueryBindingItem) ontology
							.getConstraintBindingList().get(i).getBindingList()
							.get(j);
					indent(4, out);
					out.write("[\"");
					out.write(item.getCode());
					out.write("\"] = <");
					out.write(item.getQuery().getUrl());
					out.write(">");
					newline(out);
				}
				for (int l = 3; l > 1; l--) {
					indent(l, out);
					out.write(">");
					newline(out);
				}
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

	protected void printCDate(CDate cdate, Writer out) throws IOException {
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

	protected void printCTime(CTime ctime, Writer out) throws IOException {
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

	protected void printCReal(CReal creal, Writer out) throws IOException {
		if (creal.getList() != null) {
			printList(creal.getList(), out);
		} else {
			printInterval(creal.getInterval(), out);
		}
	}

	protected void printCString(CString cstring, Writer out) throws IOException {
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
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is ADLSerializer.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2004-2005 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s): Mattias Forss, Johan Hjalmarsson
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */
