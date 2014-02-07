package org.openehr.am.validation;

/**
 * Error type reported by the archetype validator
 * 
 * MUST BE IN SYNC WITH THE ERROR TYPE IN THE SPECS
 * 
 * @author rong.chen
 */
public enum ErrorType {
	
	VASID, //archetype specialisation parent identifier validity. the archetype identifier stated in the specialise clause must be the identifier of the immediate specialisation parent archetype.
	VACSD, //archetype concept specialisation depth. the specialisation depth of the concept code must match the specialisation depth of the archetype identifier. 
	VARDT, //archetype definition typename validity. The topmost typename mentioned in the archetype definition section must match the type mentioned in the type-name slot of the first segment of the archetype id.
	VATCD, //archetype code specialisation level validity. Each archetype term (?at? code) and constraint code (?ac? code) used in the archetype definition part must have a specialisation level no greater than the specialisation level of the archetype.
	VACCD, //archetype definition code validity. The node identifier of the root node of the definition section must be the concept code mentioned earlier in the archetype.
	VATDF, //archetype term validity. Each archetype term (?at? code) used as a node identifier the archetype definition must be defined in the term_definitions part of the ontology.
	VACDF, //constraint code validity. Each constraint code (?ac? code) used in the archetype definition part must be defined in the constraint_definitions part of the ontology.
	VONLC, //Secondary languages consistency. Each archetype term used as a node identifier the archetype definition must be defined for each secondary language term_definitions part of the ontology.  
	VOTM, //ontology translations missing. Translations must exist for term_definitions and constraint_definitions sections for all languages defined in the description / translations section.
	VONSD, //ontology code specialisation level validity. No archetype code (at-code or ac-code) defined in the ontology can be of a greater specialisation depth than the archetype.
	VCARM, //attribute constraint name validity, //an attribute name introducing an attribute constraint block must be defined in the underlying information model as an attribute of the type which introduces the enclosing object block.
	VSAM, //specialised archetype attribute multiplicity conformance, //the multiplicity of a redefined attribute must conform, i.e. be the same or narrower, to that of the corresponding attribute in the parent archetype.
	VSANCE, //specialised archetype attribute node existence conformance, //the existence of a redefined attribute node in a specialised archetype must conform to the existence of the corresponding node in the flat parent archetype by having an identical range, or a range wholly contained by the latter.
	VACSO, //single-valued attribute child object occurrences validity, //the occurrences of a child object of a single-valued attribute cannot have an upper limit greater than 1.
	VACSU, //single-valued attribute child node uniqueness, //any object node added as a child to a single-valued attribute must either have a node identifier or reference model type that is unique with respect to the node identifier or the reference model type of all other siblings.
	VACSI, //single-valued attribute child node identifier, //any object node with a node identifier added as a child to a single-valued attribute must have a node identifier that is unique with respect to the node identifiers of all other siblings.
	VACSIT, //single-valued attribute child node reference model type, //any object node without a node identifier added as a child to a single-valued attribute must have a reference model type that is unique with respect to the reference model types of all other siblings.
	VACMI, //child node identification, //any object node added as a child to a container attribute must have a node identifier. 
	VACMM, //child node identifier uniqueness, //the node identifier of an object node added as a child to a container attribute must be unique with respect to the siblings in the container.
	VACMC, //cardinality/occurrences validity: where occurrences and cardinality are stated, the interval represented by: (sum of all occurrences minimum values) .. (sum of all occurrences maximum values) must intersect with the interval stated by the cardinality.
	VCACA, // NEW: attribute items in object node at /items[at0034]/items[at0033]/items[at0014]/items[at0.135]/items cardinality 0..* does not conform to cardinality >=1 in reference model
	VSANCC, //specialised archetype attribute node cardinality conformance, //the cardinality of a redefined (multiply-valued) attribute node in a specialised archetype must conform to the cardinality of the corresponding node in the flat parent archetype by either being identical, or being wholly contained by the latter.
	VCORM, //object constraint type name validity, //a type name introducing an object constraint block must be defined in the underlying information model.
	VCORMT, //NEW: object constraint type validity: a type name introducing an object constraint	block must be the same as or conform to the type stated in the underlying information model of its owning attribute.
	VSONCT, //specialised archetype object node type conformance, //the reference model type of a redefined object node in a specialised archetype must conform to the reference model type in the corresponding node in the flat parent archetype by either being identical, or conforming via an inheritance relationship in the relevant reference model.
	VSONIR, //specialised archetype object node redefinition, //if defined, the node identifier of a redefined object node in a specialised archetype must be redefined into its specialised form if any other aspect of the immediate object constraint is redefined.
	VSONCI, //specialised archetype object node identifier conformance, //if defined, the node identifier of a redefined object node in a specialised archetype must conform to the node identifier in the corresponding node in the flat parent archetype by either being identical, or being a derived identifier at the specialisation level of the child archetype.
	VSONCO, //specialised archetype object node occurrences conformance, //the occurrences of a redefined object node in a specialised archetype must conform to the occurrences in the corresponding node in the flat parent archetype by either being identical, or being wholly contained by the latter.
	VSSM, //specialised archetype sibling marker validity, //the sibling code used in a sibling marker in a specialised archetype must refer to a node found within the same container in the flat parent archetype.
	VOBAV, //object node assumed value validity, //the value of an assumed value must fall within the value space defined by the constraint to which it is attached.
	VCATU, //attribute uniqueness, //sibling attributes occurring within an object node must be uniquely named with respect to each other, in the same way as for class definitions in an object reference model.
	VDFAI, //archetype identifier validity in definition. Any archetype identifier mentioned in an archetype slot in the definition section must conform to the published openEHR specification for archetype identifiers.
	VUNT, //use_node type validity, //the type mentioned in a use_node statement must be the same as or a super-type (according to the reference model) of the reference model type of the node referred to.
	VUNP, //use_node path validity, //the path mentioned in a use_node statement must refer to an object node defined elsewhere in the same archetype or any of its specialisation parent archetypes, that is not itself an internal reference node, and which carries a node identifier if one is needed at the reference point.
	VSCNR, //placeholder constraint node conformance, //a placeholder node can only be defined into a reference model type conformant with the type of the original constraint in the parent archetype.
	VOTC, // openEHR terminology code validity 
	VDSCR, // null, empty or unknown mandatory description item such as purpose or original_author
	VSONT, // different dynamic types of parent and child node (e.g. not both CComplexObjects)
	VOKU, // dADL object key must be unique. A code in the ontology is present more than once for a language	
	VUI, // Additional Validation: Units of a DV_QUANTITY must be expressed in UCUM syntax, e.g. kg/m2, mm[Hg], ms-1, km 
	WOUC, // Warning: A code in the ontology is not used.
	WACMC, //Not an official warning ... cardinality/occurrences validity edge case: where occurrences and cardinality are stated, the interval represented by: (sum of all occurrences minimum values) .. (sum of all occurrences maximum values) must intersect with the interval stated by the cardinality. This is fulfilled, but none of the optional elements could ever be added.
	
	WITB, // Warning: A Term binding is invalid, the referenced path/code doesn't exist in the archetype
	
	ICARM // INFO: A commonly constrained functional property has been constrained, typical examples: offset or is_integral
	
	
	/* Missing validation errors
	- VCAEX: archetype attribute reference model existence conformance: the existence
		of an attribute must conform, i.e. be the same or narrower, to the existence of
		the corresponding attribute in the underlying information model.
	
	- VCAM: archetype attribute reference model multiplicity conformance: the multiplicity,
		i.e. whether an attribute is multiply- or single-valued, of an attribute must conform
		to that of the corresponding attribute in the underlying information model.
		
	- VSSM: This seems to be ADL 1.5 specific. specialised archetype sibling marker validity: the sibling code used in a
		sibling marker in a specialised archetype must refer to a node found within the same
		container in the flat parent archetype.
		
		The following validity rule applies to CONSTRAINT_REFs in a specialised archetype.
	- VSCNR: placeholder constraint node conformance: a placeholder node can only
		be defined into a reference model type conformant with the type of the original constraint
		in the parent archetype.
		
	- VSONNC : not defined in specs, but seems to be the general error of non-conformance for specialised archetypes if nothing else fits (just in the algorithm for spec. validation as published in the specs).
	*/	
}
