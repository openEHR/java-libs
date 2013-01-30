package br.com.zilics.archetypes.models.adl.serializer;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.archetype.assertion.Assertion;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeInternalRef;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeSlot;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CDomainType;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CMultipleAttribute;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CPrimitiveObject;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.Cardinality;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.ConstraintRef;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CBoolean;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CDate;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CDateTime;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CDuration;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CInteger;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CPrimitive;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CReal;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CString;
import br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CTime;
import br.com.zilics.archetypes.models.am.archetype.ontology.ArchetypeOntology;
import br.com.zilics.archetypes.models.am.archetype.ontology.ArchetypeTerm;
import br.com.zilics.archetypes.models.am.archetype.ontology.CodeDefinitionSet;
import br.com.zilics.archetypes.models.am.archetype.ontology.ConstraintBindingItem;
import br.com.zilics.archetypes.models.am.archetype.ontology.ConstraintBindingSet;
import br.com.zilics.archetypes.models.am.archetype.ontology.TermBindingItem;
import br.com.zilics.archetypes.models.am.archetype.ontology.TermBindingSet;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CDvQuantity;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CQuantityItem;
import br.com.zilics.archetypes.models.am.openehrprofile.datatypes.text.CCodePhrase;
import br.com.zilics.archetypes.models.rm.common.resource.AuthoredResource;
import br.com.zilics.archetypes.models.rm.common.resource.ResourceDescription;
import br.com.zilics.archetypes.models.rm.common.resource.ResourceDescriptionItem;
import br.com.zilics.archetypes.models.rm.common.resource.TranslationDetails;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvOrdinal;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.support.identification.ArchetypeID;

public class ADLSerializer {

	public ADLSerializer() {
		this.encoding = UTF8;
		this.indent = "    "; // 4 white space characters
		this.lineSeparator = "\r\n";
	}

	public String output(Archetype archetype) throws IOException {
		StringWriter writer = new StringWriter();
		output(archetype, writer);
		return writer.toString();
	}

	public String outputDefinitionOnly(Archetype archetype) throws IOException {
		StringWriter writer = new StringWriter();

		printDefinition(archetype.getDefinition(), writer);

		return writer.toString();
	}

	public void output(Archetype archetype, OutputStream out) throws IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(out), encoding));
		output(archetype, writer);
	}

	public void output(Archetype archetype, Writer out) throws IOException {
		printHeader(archetype.getAdlVersion(), archetype.getArchetypeId(), archetype.getParentArchetypeId(), archetype.getConcept(), out);
		newline(out);

		printLanguage(archetype, out);
		newline(out);

		if (archetype.getDescription() != null) {
			printDescription(archetype.getDescription(), out);
			newline(out);
		}

		printDefinition(archetype.getDefinition(), out);
		newline(out);

		printOntology(archetype.getOntology(), out);
		out.flush();
		out.close();
	}

	protected void printHeader(String adlVersion, ArchetypeID id, ArchetypeID parentId, String conceptCode, Writer out) throws IOException {

		out.write("archetype");
		if (adlVersion != null) {
			out.write(" (adl_version=");
			out.write(adlVersion);
			out.write(")");
		}
		newline(out);
		indent(1, out);
		out.write(id.getValue());
		newline(out);

		if (parentId != null) {
			out.write("specialize");
			newline(out);
			indent(1, out);
			out.write(parentId.toString());
			newline(out);
		}

		newline(out);
		out.write("concept");
		newline(out);
		indent(1, out);
		out.write("[" + conceptCode + "]");
		newline(out);
	}

	protected void printLanguage(AuthoredResource authored, Writer out) throws IOException {

		out.write("language");
		newline(out);
		indent(1, out);
		out.write("original_language = <");
		out.write("[");
		out.write(authored.getOriginalLanguage().getTerminologyId().getValue());
		out.write("::");
		out.write(authored.getOriginalLanguage().getCodeString());
		out.write("]");
		out.write(">");
		newline(out);
		if (authored.getTranslations() != null) {
			indent(1, out);
			out.write("translations = <");
			newline(out);
			Set<TranslationDetails> translations = authored.getTranslations();
			for (TranslationDetails td : translations) {
				String lang = td.getLanguage().getCodeString();

				indent(2, out);
				out.write("[\"");
				out.write(lang);
				out.write("\"] = <");
				newline(out);

				indent(3, out);
				out.write("language = <");
				out.write("[");
				out.write(td.getLanguage().getTerminologyId().getValue());
				out.write("::");
				out.write(td.getLanguage().getCodeString());
				out.write("]");
				out.write(">");
				newline(out);

				indent(3, out);
				out.write("author = <");
				newline(out);
				printMap(td.getAuthor(), out, 4);
				indent(3, out);
				out.write(">");
				newline(out);

				if (td.getAccreditation() != null) {
					indent(3, out);
					out.write("accreditation = <\"");
					out.write(td.getAccreditation());
					out.write("\">");
					newline(out);
				}

				if (td.getOtherDetails() != null) {
					indent(3, out);
					out.write("other_details = <");
					newline(out);
					printMap(td.getOtherDetails(), out, 4);
					indent(3, out);
					out.write(">");
					newline(out);
				}

				indent(2, out);
				out.write(">");
				newline(out);
			}
			indent(1, out);
			out.write(">");
			newline(out);
		}
	}

	protected void printMap(Map<String, String> map, Writer out, int indent) throws IOException {
		if (map == null || map.size() == 0) {
			return;
		}
		for (String key : map.keySet()) {
			indent(indent, out);
			out.write("[\"");
			out.write(key);
			out.write("\"] = <\"");
			out.write(map.get(key));
			out.write("\">");
			newline(out);
		}
	}

	protected void printDescription(ResourceDescription description, Writer out) throws IOException {

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

	protected void printDescriptionItem(ResourceDescriptionItem item, int indent, Writer out) throws IOException {
		indent(indent, out);
		out.write("[\"");
		out.write(item.getLanguage().getCodeString());
		out.write("\"] = <");
		newline(out);

		indent(indent + 1, out);
		out.write("language = <");
		out.write("[");
		out.write(item.getLanguage().getTerminologyId().getValue());
		out.write("::");
		out.write(item.getLanguage().getCodeString());
		out.write("]>");
		newline(out);

		printNoneEmptyString("purpose", item.getPurpose(), indent + 1, out);
		printNoneEmptyStringList("keywords", item.getKeywords(), indent + 1, out);
		printNoneEmptyString("copyright", item.getCopyright(), indent + 1, out);
		printNoneEmptyString("use", item.getUse(), indent + 1, out);
		printNoneEmptyString("misuse", item.getMisuse(), indent + 1, out);
		printNoneEmptyStringMap("original_resource_uri", item.getOriginalResourceUri(), indent + 1, out);

		// other details not printed

		indent(indent, out);
		out.write(">");
		newline(out);
	}

	private void printNoneEmptyString(String label, String value, int indent, Writer out) throws IOException {

		if (value == null) {
			return;
		}
		indent(indent, out);
		out.write(label);
		out.write(" = <\"");
		out.write(value);
		out.write("\">");
		newline(out);
	}

	private void printNoneEmptyStringList(String label, List<String> list, int indent, Writer out) throws IOException {

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

	private void printNoneEmptyStringMap(String label, Map<String, String> map, int indent, Writer out) throws IOException {
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

	protected void printDefinition(CComplexObject definition, Writer out) throws IOException {

		out.write("definition");
		newline(out);

		printCComplexObject(definition, 1, out);
	}

	protected void printCComplexObject(CComplexObject ccobj, int indent, Writer out) throws IOException {

		// print rmTypeName and nodeId
		indent(indent, out);
		out.write(ccobj.getRmTypeName());
		if (ccobj.getNodeId() != null) {
			out.write("[" + ccobj.getNodeId() + "]");
		}

		printOccurrences(ccobj.getOccurrences(), out);

		out.write(" matches {");

		// print all attributes
		if (!ccobj.getAnyAllowed()) {
			for (CAttribute cattribute : ccobj.getAttributes().values()) {
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

	protected void printOccurrences(Interval<Integer> occurrences, Writer out) throws IOException {

		Interval<Integer> defaultOccurrences = new Interval<Integer>();
		defaultOccurrences.setLower(1);
		defaultOccurrences.setUpper(1);

		if (occurrences == null || defaultOccurrences.equals(occurrences)) {
			return;
		}
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

	protected void printArchetypeInternalRef(ArchetypeInternalRef ref, int indent, Writer out) throws IOException {
		indent(indent, out);
		out.write("use_node ");
		out.write(ref.getRmTypeName());
		printOccurrences(ref.getOccurrences(), out);
		out.write(" ");
		out.write(ref.getTargetPath());
		newline(out);
	}

	protected void printArchetypeSlot(ArchetypeSlot slot, int indent, Writer out) throws IOException {

		indent(indent, out);
		out.write("allow_archetype ");
		out.write(slot.getRmTypeName());
		if (slot.getNodeId() != null) {
			out.write("[" + slot.getNodeId() + "]");
		}

		printOccurrences(slot.getOccurrences(), out);
		out.write(" matches {");

		if (slot.getAnyAllowed()) {
			out.write("*}");
		} else {
			if (slot.getIncludes() != null) {
				printAssertions(slot.getIncludes(), "include", indent, out);
			}
			if (slot.getExcludes() != null) {
				printAssertions(slot.getExcludes(), "exclude", indent, out);
			}
			newline(out);
			indent(indent, out);
			out.write("}");
		}
		newline(out);
	}

	private void printAssertions(List<Assertion> assertions, String purpose, int indent, Writer out) throws IOException {
		newline(out);
		indent(indent + 1, out);
		out.write(purpose);

		for (Assertion assertion : assertions) {
			newline(out);
			indent(indent + 2, out);

			// FIXME: The string expression is null when an archetype is parsed,
			// but after the archetype is recreated in the archetype
			// editor, the string expression exists. Please provide a valid
			// string expression from the parser since it's _much_ easier to
			// maintain this line of code instead of adding hundreds of lines
			// just to output some expressions, operators etc.
			// Opening an archetype directly in the ADL format view will show
			// the output of the parsed archetype in this way:
			//
			// include
			// null
			out.write(assertion.toString());
		}
	}

	protected void printCAttribute(CAttribute cattribute, int indent, Writer out) throws IOException {
		newline(out);
		indent(indent, out);
		out.write(cattribute.getRmAttributeName());

		printExistence(cattribute.getExistence(), out);
		if (cattribute instanceof CMultipleAttribute) {
			out.write(" ");
			printCardinality(((CMultipleAttribute) cattribute).getCardinality(), out);
		}
		List<CObject> children = cattribute.getChildren();
		out.write(" matches {");
		if (children == null || children.size() == 0) {
			out.write("*");
		} else if (children.size() != 1 || !(children.get(0) instanceof CPrimitiveObject)) {
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

	protected void printExistence(Interval<Integer> existence, Writer out) throws IOException {
		if (existence.getLower() == 1)
			return;

		out.write(" existence matches ");
		if (existence.getUpper() == 1) {
			out.write("{0..1}");
		} else {
			out.write("{0}");
		}
	}

	protected void printCObject(CObject cobj, int indent, Writer out) throws IOException {

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
		} else if (cobj instanceof ConstraintRef) {
			printConstraintRef((ConstraintRef) cobj, indent, out);
		}
	}

	protected void printConstraintRef(ConstraintRef ref, int indent, Writer out) throws IOException {
		indent(indent, out);
		out.write("[");
		out.write(ref.getReference());
		out.write("]");
		newline(out);
	}

	protected void printCardinality(Cardinality cardinality, Writer out) throws IOException {
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

	protected void printCDomainType(CDomainType cdomain, int indent, Writer out) throws IOException {
		if (cdomain instanceof CDvOrdinal) {
			printCDvOrdinal((CDvOrdinal) cdomain, indent, out);
		} else if (cdomain instanceof CDvQuantity) {
			printCDvQuantity((CDvQuantity) cdomain, indent, out);
		} else if (cdomain instanceof CCodePhrase) {
			printCCodePhrase((CCodePhrase) cdomain, indent, out);
		}
		// unknow CDomainType
	}

	protected void printCCodePhrase(CCodePhrase ccoded, int indent, Writer out) throws IOException {

		indent(indent, out);

		if (ccoded.getAnyAllowed()) {
			out.write("C_CODE_PHRASE <");
			newline(out);
			indent(indent, out);
			out.write(">");
			newline(out);
			return;
		}

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
						if (ccoded.getAssumedValue() != null) {
							out.write(";");
							newline(out);
							indent(indent, out);
							out.write(ccoded.getAssumedValue().toString());
						}
						out.write("]");
					}
					newline(out);
				}
			} else {
				out.write(ccoded.getCodeList().get(0));
				if (ccoded.getAssumedValue() != null) {
					out.write(";" + ccoded.getAssumedValue());
				}
				out.write("]");
				newline(out);
			}
		} else {
			out.write("]");
			newline(out);
		}

	}

	protected void printCDvOrdinal(CDvOrdinal cordinal, int indent, Writer out) throws IOException {

		// if the list is null, the CDvOrdinal is not further constrained
		// (other than that it is a CDvOrdinal)

		if (cordinal.getAnyAllowed()) {
			indent(indent, out);
			out.write("C_DV_ORDINAL <");
			newline(out);
			indent(indent, out);
			out.write(">");
			newline(out);
		} else {
			for (Iterator<DvOrdinal> it = cordinal.getList().iterator(); it.hasNext();) {
				DvOrdinal ordinal = it.next();
				indent(indent, out);
				printOrdinal(ordinal, out);
				if (it.hasNext()) {
					out.write(",");
				} else if (cordinal.getAssumedValue() != null) {
					out.write(";");
				}
				newline(out);
			}
			if (cordinal.getAssumedValue() != null) {
				indent(indent, out);
				printOrdinal((DvOrdinal) cordinal.getAssumedValue(), out);
				newline(out);

			}
		}

	}

	protected void printOrdinal(DvOrdinal ordinal, Writer out) throws IOException {
		CodePhrase symbol = ordinal.getSymbol().getDefiningCode();
		out.write(Integer.toString(ordinal.getValue()));
		out.write("|[");
		out.write(symbol.getTerminologyId().getValue());
		out.write("::");
		out.write(symbol.getCodeString());
		out.write("]");
	}

	protected void printCDvQuantity(CDvQuantity cquantity, int indent, Writer out) throws IOException {
		indent(indent, out);
		out.write("C_DV_QUANTITY <");
		newline(out);
		indent(indent + 1, out);
		CodePhrase property = cquantity.getProperty();
		if (property != null) {
			out.write("property = <[");
			out.write(property.getTerminologyId().getValue());
			out.write("::");
			out.write(property.getCodeString());
			out.write("]>");
		}
		List<CQuantityItem> list = cquantity.getList();
		if (list != null) {
			newline(out);
			indent(indent + 1, out);
			out.write("list = <");
			newline(out);
			int index = 1;
			for (CQuantityItem item : list) {
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

				Interval<Double> value = item.getMagnitude();
				if (value != null) {
					indent(indent + 3, out);
					out.write("magnitude = <");
					printInterval(value, out);
					out.write(">");
					newline(out);
				}

				Interval<Integer> precision = item.getPrecision();
				if (precision != null) {
					indent(indent + 3, out);
					out.write("precision = <");
					printInterval(precision, out);
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

	protected void printOntology(ArchetypeOntology ontology, Writer out) throws IOException {

		out.write("ontology");
		newline(out);

		// if (ontology.getTerminologies() != null) {
		// indent(1, out);
		// out.write("terminologies_available = <");
		// for (String terminology : ontology.getTerminologies()) {
		// out.write("\"");
		// out.write(terminology);
		// out.write("\", ");
		// }
		// out.write("...>");
		// newline(out);
		// }

		// *** Term definition section *** (ADL 1.4 spec 8.6.3)
		indent(1, out);
		out.write("term_definitions = <");
		newline(out);
		Collection<CodeDefinitionSet> termDefinitionsList = ontology.getTermDefinitions().values();
		printDefinitionList(out, termDefinitionsList);
		indent(1, out);
		out.write(">");
		newline(out);

		// *** Constraint definition section *** (ADL 1.4 spec 8.6.4)
		Collection<CodeDefinitionSet> constraintDefinitionsList;
		if (ontology.getConstraintDefinitions() != null) {
			constraintDefinitionsList = ontology.getConstraintDefinitions().values();
			indent(1, out);
			out.write("constraint_definitions = <");
			newline(out);
			printDefinitionList(out, constraintDefinitionsList);
			indent(1, out);
			out.write(">");
			newline(out);
		}

		// *** Term binding section *** (ADL 1.4 spec 8.6.5)
		if (ontology.getTermBindings() != null) {
			indent(1, out);
			out.write("term_binding = <");
			newline(out);
			for (TermBindingSet bind : ontology.getTermBindings().values()) {
				indent(2, out);
				out.write("[\"");
				out.write(bind.getTerminology());
				out.write("\"] = <");
				newline(out);
				indent(3, out);
				out.write("items = <");
				newline(out);

				for (TermBindingItem item : bind.getItems().values()) {
					indent(4, out);
					out.write("[\"");
					out.write(item.getCode());
					out.write("\"] = <");
					out.write(item.getValue().getCodeString());

					// if (item.getTerms().size() > 1) {
					// for (int k = 1; k < item.getTerms().size(); k++)
					// out.write("," + item.getTerms().get(k));
					// }

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

		// *** Constraint binding section *** (ADL 1.4 spec 8.6.6)
		if (ontology.getConstraintBindings() != null) {
			indent(1, out);
			out.write("constraint_binding = <");
			newline(out);
			for (ConstraintBindingSet bind : ontology.getConstraintBindings().values()) {
				indent(2, out);
				out.write("[\"");
				out.write(bind.getTerminology());
				out.write("\"] = <");
				newline(out);
				indent(3, out);
				out.write("items = <");
				newline(out);

				for (ConstraintBindingItem item : bind.getItems().values()) {
					indent(4, out);
					out.write("[\"");
					out.write(item.getCode());
					out.write("\"] = <");
					out.write(item.getValue());
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

	private void printDefinitionList(Writer out, Collection<CodeDefinitionSet> termDefinitionsList) throws IOException {
		for (CodeDefinitionSet defs : termDefinitionsList) {
			indent(2, out);
			out.write("[\"");
			out.write(defs.getLanguage());
			out.write("\"] = <");
			newline(out);
			indent(3, out);
			out.write("items = <");
			newline(out);
			for (ArchetypeTerm term : defs.getItems().values()) {
				indent(4, out);
				out.write("[\"");
				out.write(term.getCode());
				out.write("\"] = <");
				newline(out);
				for (Map.Entry<String, String> entry : term.getItems().entrySet()) {
					indent(5, out);
					out.write(entry.getKey());
					out.write(" = <\"");
					out.write(entry.getValue());
					out.write("\">");
					newline(out);
				}
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
	}

	protected void printCPrimitiveObject(CPrimitiveObject cpo, Writer out) throws IOException {

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

	protected void printCBoolean(CBoolean cboolean, Writer out) throws IOException {
		if (cboolean.isTrueValid()) {
			out.write("true");
			if (cboolean.isFalseValid()) {
				out.write(", false");
			}
		} else {
			out.write("false");
		}
		if (cboolean.hasAssumedValue()) {
			out.write("; ");
			if (cboolean.getAssumedValue().booleanValue()) {
				out.write("true");
			} else {
				out.write("false");
			}
		}
	}

	protected void printCDate(CDate cdate, Writer out) throws IOException {
		if (cdate.getPattern() != null) {
			out.write(cdate.getPattern());
		} else if (cdate.getList() != null) {
			out.write(cdate.getList().get(0).toString());
		} else if (cdate.getRange() != null) {
			printInterval(cdate.getRange(), out);
		}
		if (cdate.hasAssumedValue()) {
			out.write("; ");
			out.write(cdate.getAssumedValue().toString());
		}
	}

	protected void printCDateTime(CDateTime cdatetime, Writer out) throws IOException {
		if (cdatetime.getPattern() != null) {
			out.write(cdatetime.getPattern());
		} else if (cdatetime.getList() != null) {
			out.write(cdatetime.getList().get(0).toString());
		} else if (cdatetime.getRange() != null) {
			printInterval(cdatetime.getRange(), out);
		}
		if (cdatetime.hasAssumedValue()) {
			out.write("; ");
			out.write(cdatetime.getAssumedValue().toString());
		}
	}

	protected void printCTime(CTime ctime, Writer out) throws IOException {
		if (ctime.getPattern() != null) {
			out.write(ctime.getPattern());
		} else if (ctime.getList() != null) {
			out.write(ctime.getList().get(0).toString());
		} else if (ctime.getRange() != null) {
			printInterval(ctime.getRange(), out);
		}
		if (ctime.hasAssumedValue()) {
			out.write("; ");
			out.write(ctime.getAssumedValue().toString());
		}
	}

	protected void printCDuration(CDuration cduration, Writer out) throws IOException {
		if (cduration.getPattern() != null) {
			out.write(cduration.getPattern().toString());
		} else if (cduration.getPattern() != null) {
			out.write(cduration.getPattern());
		} else {
			printInterval(cduration.getRange(), out);
		}
		if (cduration.hasAssumedValue()) {
			out.write("; ");
			out.write(cduration.getAssumedValue().toString());
		}
	}

	protected void printCInteger(CInteger cinteger, Writer out) throws IOException {
		if (cinteger.getList() != null) {
			printList(cinteger.getList(), out);
		} else {
			printInterval(cinteger.getRange(), out);
		}
		if (cinteger.hasAssumedValue()) {
			out.write("; ");
			out.write(cinteger.getAssumedValue().toString());
		}
	}

	protected void printCReal(CReal creal, Writer out) throws IOException {
		if (creal.getList() != null) {
			printList(creal.getList(), out);
		} else {
			printInterval(creal.getRange(), out);
		}
		if (creal.hasAssumedValue()) {
			out.write("; ");
			out.write(creal.getAssumedValue().toString());
		}
	}

	protected void printCString(CString cstring, Writer out) throws IOException {
		if (cstring.getPattern() != null) {
			out.write("/" + cstring.getPattern() + "/");
		} else {
			printList(cstring.getList(), out, true);
		}
		if (cstring.hasAssumedValue()) {
			out.write("; ");
			out.write("\"" + cstring.getAssumedValue() + "\"");
		}
	}

	protected void printList(List<?> list, Writer out) throws IOException {
		printList(list, out, false);
	}

	protected void printList(List<?> list, Writer out, boolean string) throws IOException {
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

	protected void printInterval(Interval<?> interval, Writer out) throws IOException {
		out.write("|");
		if (interval.getLower() != null && interval.getUpper() != null) {
			if (interval.getLower().equals(interval.getUpper()) && interval.isLowerIncluded() && interval.isUpperIncluded()) {
				out.write(interval.getLower().toString());
			} else {
				out.write(interval.getLower().toString());
				out.write("..");
				out.write(interval.getUpper().toString());
			}
		} else if (interval.getLower() == null) {
			out.write("<");
			if (interval.isUpperIncluded()) {
				out.write("=");
			}
			out.write(interval.getUpper().toString());
		} else {
			out.write(">");
			if (interval.isLowerIncluded()) {
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

	public static final Charset UTF8 = Charset.forName("UTF-8");
	public static final Charset LATIN1 = Charset.forName("ISO-8859-1");

	private Charset encoding;
	private String lineSeparator;
	private String indent;
}
