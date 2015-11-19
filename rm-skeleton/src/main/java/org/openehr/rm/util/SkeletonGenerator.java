/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class SkeletonGenerator"
 * keywords:    "rm-skeleton"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2009,2010 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import org.apache.log4j.Logger;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.primitive.*;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.openehrprofile.datatypes.quantity.*;
import org.openehr.am.openehrprofile.datatypes.text.CCodePhrase;
import org.openehr.am.template.TermMap;
import org.openehr.build.RMObjectBuilder;
import org.openehr.build.SystemValue;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ProportionKind;
import org.openehr.rm.datatypes.quantity.datetime.*;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.support.measurement.*;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

public class SkeletonGenerator {
	
	/**
	 * Factory method to get instance of the RM skeleton generator
	 * 
	 * @return
	 */
	public static SkeletonGenerator getInstance() throws Exception {
		return new SkeletonGenerator();
	}
	
	/* private constructor */
	private SkeletonGenerator() throws Exception {
		measurementService = SimpleMeasurementService.getInstance();
		terminologyService = SimpleTerminologyService.getInstance();	
		openEHRTerminology = terminologyService.terminology(
				TerminologyService.OPENEHR);
		
		builder = initializeBuilder();
		termMap = new TermMap();
	}
	
	// TODO lots of hard-coded settings for the RM builder
	private RMObjectBuilder initializeBuilder() {
		CodePhrase lang = new CodePhrase("ISO_639-1", "sv");
		CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8");
		
		
		
		Map<SystemValue,Object> values = new HashMap<SystemValue,Object>();
        values.put(SystemValue.LANGUAGE, lang);
        values.put(SystemValue.ENCODING, charset);
        
        values.put(SystemValue.TERMINOLOGY_SERVICE, terminologyService);
        values.put(SystemValue.MEASUREMENT_SERVICE, measurementService);   
        return RMObjectBuilder.getInstance(values);
	}
	
	public void loadTermMapFromFile(String filename) throws Exception {
		InputStream input = new FileInputStream(filename);
		termMap.addAll(input);
	}
	
	public void loadTermMapFromClasspath(String filename) throws Exception {
		InputStream input = 
			SkeletonGenerator.class.getClassLoader().getResourceAsStream(filename);
		if(input == null) throw new IllegalArgumentException("Can not open filename="+filename);
		termMap.clear();
		termMap.addAll(input);
	}
	
	/**
	 * Create RM object tree based on given archetype with a default minimum strategy
	 * 
	 * @param archetype
	 * @return
	 * @throws Exception
	 */
	public Object create(Archetype archetype) throws Exception {
	return create(archetype, null, null, null, GenerationStrategy.MINIMUM);		
	}
	
	public Object create(Archetype archetype, 
			GenerationStrategy strategy) throws Exception {
	return create(archetype, null, null, null, strategy);		
	}
	
    //Only used for adding slots
    public Object create(Archetype archetype, Map<String, Object> extraValues, 
	    GenerationStrategy strategy) throws Exception {
	return create(archetype, null, null, extraValues, strategy);		
    }


	/**
	 * Create RM object tree based on given main the archetype and 
	 * associated archetypes in the archetypeMap
	 * 
	 * TODO
	 * This entry is used to create object tree for flattened templates
	 * 
	 * @param archetype
	 * @return
	 * @throws Exception
	 */
	public Object create(Archetype archetype, 
			Map<String, Archetype> archetypeMap) throws Exception {
		
	return create(archetype, null, archetypeMap, null, GenerationStrategy.MINIMUM);		
	}
	
	/**
	 * Create RM object tree with specified template
	 * 
	 * @param archetype
	 * @param templateId
	 * @param archetypeMap
     * @param extraValues extra values indexed by path
	 * @param strategy
	 * @return
	 * @throws Exception
	 */
	public Object create(Archetype archetype, String templateId,
			Map<String, Archetype> archetypeMap,
	    Map<String, Object> extraValues,
	    GenerationStrategy strategy) throws Exception {

	return createComplexObject(archetype.getDefinition(), archetype,
		templateId, archetypeMap, extraValues, strategy);		
    }

    public Object create(Archetype archetype, String templateId,
	    Map<String, Archetype> archetypeMap, 
			GenerationStrategy strategy) throws Exception {
		
		return createComplexObject(archetype.getDefinition(), archetype,
		templateId, archetypeMap, null, strategy);		
	}
	
	/*
	 * Entering point for complex object creation
	 */
	public Object createComplexObject(CComplexObject ccobj, 
			Archetype archetype, String templateId,
			Map<String,Archetype> archetypeMap, 
	    Map<String, Object> extraValues,
			GenerationStrategy strategy) throws Exception {
		
		log.debug("create complex object " + ccobj.getRmTypeName());
		
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(TEMPLATE_ID, templateId);
		
		String nodeId = ccobj.getNodeId();
		if(nodeId != null) {
			DvText name;
			
			// root node with archetype_id as node_id
			// TODO check if name is already define?
			if(nodeId.startsWith("openEHR")) {
				archetype = archetypeMap.get(nodeId);
				if(archetype == null) {
					throw new Exception("unknown archetype for nodeId: " + nodeId);
				}
				name = retrieveName(archetype.getConcept(), archetype);				
			} else {			
				name = retrieveName(nodeId, archetype);
			}
			valueMap.put("name", name);
			
			// use archetypeId instead of nodeId for root
			if(nodeId.equals(archetype.getConcept())) {
				nodeId = archetype.getArchetypeId().toString();
			}
			valueMap.put("archetype_node_id", nodeId);			
		}
		
		String rmTypeName = ccobj.getRmTypeName();
		
		if(ccobj.getAttributes() != null && ccobj.getAttributes().size() > 0) {
			for(CAttribute cattr : ccobj.getAttributes()) {
				
				// TODO create 'required' attribute
				if(cattr.isAllowed() 
						&& (GenerationStrategy.MAXIMUM.equals(strategy)
								|| GenerationStrategy.MAXIMUM_EMPTY.equals(strategy)
								|| (GenerationStrategy.MINIMUM.equals(strategy) 
										&& cattr.isRequired()))) {					
		    Object attrValue;
		    String path = cattr.path();

		    if(extraValues != null && extraValues.containsKey(path)) {
			attrValue = extraValues.get(path);
		    } else {
			attrValue = createAttribute(cattr, archetype, archetypeMap, extraValues, strategy);
		    }
					valueMap.put(cattr.getRmAttributeName(), attrValue);
				
				} else if("CLUSTER".equals(rmTypeName)) { // TODO quickfix
					
					Object attrValue = createAttribute(cattr, archetype,
			    archetypeMap, extraValues, strategy);
					valueMap.put(cattr.getRmAttributeName(), attrValue);
				}
			}
		}	
		
		// deal with missing required attributes in RM
		if("DV_TEXT".equals(rmTypeName)) {	
			
			if(! valueMap.containsKey(VALUE)) {
				valueMap.put(VALUE, "text value");
			}
	
		} else if("DV_CODED_TEXT".equals(rmTypeName)) {
		
			// TODO type-check is temporary fix
			Object definingCode = valueMap.get(DEFINING_CODE);
			CodePhrase codePhrase = null;
			
			
			if( ! (definingCode instanceof CodePhrase)) {
				valueMap.put(DEFINING_CODE, new CodePhrase("local", "at9999"));
			} else {
				codePhrase = (CodePhrase) valueMap.get(DEFINING_CODE);
			}
			
			
			if(valueMap.get(VALUE) == null) {
				String text = null;
				
				if(codePhrase != null) {
					String code = codePhrase.getCodeString();
					
					if(isLocallyDefined(codePhrase)) {
					
						// archetype terms
						text = retrieveArchetypeTermText(code, archetype);
					
					} else if(isOpenEHRTerm(codePhrase)) {
						
						// openEHR terms
						// TODO hard-coded language "en"
						text = openEHRTerminology.rubricForCode(code, "en");						
						
					} else {
						
						// externally defined term
						text = termMap.getText(codePhrase, ccobj.path());
					}
				}
				if(text == null) {
					text = DEFAULT_CODED_TEXT; 
				}
				valueMap.put(VALUE, text);
			}			
			
		
		}  else if("DV_URI".equals(rmTypeName) || "DV_EHR_URI".equals(rmTypeName)) {	
						
			if(! valueMap.containsKey(VALUE)) {
				valueMap.put(VALUE, DEFAULT_URI);
			}
			
		}else if("DV_DATE_TIME".equals(rmTypeName)) {
		
			if( ! valueMap.containsKey(VALUE)) {
				valueMap.put(VALUE, DEFAULT_DATE_TIME);
			}
		
		} else if("DV_DATE".equals(rmTypeName)) {
		
			if( ! valueMap.containsKey(VALUE)) {
				valueMap.put(VALUE, DEFAULT_DATE);
			}
		
		} else if("DV_TIME".equals(rmTypeName)) {
		
			if( ! valueMap.containsKey(VALUE)) {
				valueMap.put(VALUE, DEFAULT_TIME);
			}
		
	} else if("DV_MULTIMEDIA".equals(rmTypeName)) {

	    CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8"); 
	    CodePhrase language = new CodePhrase("ISO_639-1","en");
	    String alternateText = "alternative text";
	    CodePhrase mediaType = new CodePhrase("IANA_media-types", "text/plain");
	    CodePhrase compressionAlgorithm = new CodePhrase("openehr_compression_algorithms", "other");
	    //byte[] integrityCheck = new byte[0];
	    CodePhrase integrityCheckAlgorithm = new CodePhrase("openehr_integrity_check_algorithms", "SHA-1");
	    DvMultimedia thumbnail = null;
	    DvURI uri = new DvURI("www.iana.org");
	    //byte[] data = new byte[0];
	    TerminologyService terminologyService = SimpleTerminologyService.getInstance();
	    DvMultimedia dm = new DvMultimedia(charset, language, alternateText,
		    mediaType, compressionAlgorithm, null, 
		    integrityCheckAlgorithm, thumbnail, uri, null, terminologyService);

	    return dm;

		} else if("DV_COUNT".equals(rmTypeName)) {
			
			if(valueMap.get(MAGNITUDE) == null) {
				valueMap.put(MAGNITUDE, DEFAULT_COUNT);
			}
	}else if("DV_DURATION".equals(rmTypeName)) {
	    if(valueMap.get(VALUE) == null) {
		valueMap.put(VALUE, DEFAULT_DURATION);
	    }
	}else if("DV_IDENTIFIER".equals(rmTypeName)) {
		    if(valueMap.get(ID) == null) {
			valueMap.put(ID, DEFAULT_ID);
		    }
		    if(valueMap.get(ASSIGNER) == null) {
			valueMap.put(ASSIGNER, DEFAULT_ASSIGNER);
		    }
		    if(valueMap.get(ISSUER) == null) {
			valueMap.put(ISSUER, DEFAULT_ISSUER);
		    }
		    if(valueMap.get(TYPE) == null) {
			valueMap.put(TYPE, DEFAULT_TYPE);
		    }
		} else if("DV_PROPORTION".equals(rmTypeName)) {
		
			// default dv_proportion
			// DV_PROPORTION) <
            //     numerator = <0.5>               
            // 	   denominator = <1.0>
            //     type = <0>
            //     precision = <2>
			// >
			
	    if( ! valueMap.containsKey("numerator") || ! valueMap.containsKey("denominator")) {
				valueMap.put("numerator", "0.5");
				valueMap.put("denominator", "1.0");
				valueMap.put("precision", "1");				
				valueMap.put("type", ProportionKind.RATIO);
			}
		
		} else if("HISTORY".equals(rmTypeName)) {
			
			if(! valueMap.containsKey(ORIGIN)) {
				valueMap.put(ORIGIN, new DvDateTime());	
			}
		
		} else if("EVENT".equals(rmTypeName)) {
			
			if(! valueMap.containsKey(TIME)) {			
				valueMap.put(TIME, new DvDateTime());			}
		
		} else if("OBSERVATION".equals(rmTypeName)) {
			
			addEntryValues(valueMap, archetype);
		
		} else if("EVALUATION".equals(rmTypeName)) {
			
			addEntryValues(valueMap, archetype);
		
		} else if("ADMIN_ENTRY".equals(rmTypeName)) {
			
			addEntryValues(valueMap, archetype);
		
		} else if("ACTION".equals(rmTypeName)) {
			
			addEntryValues(valueMap, archetype);
			if( ! valueMap.containsKey(TIME)) {
				valueMap.put(TIME, new DvDateTime());
			}
			if(valueMap.get(DESCRIPTION) == null) {
				valueMap.put(DESCRIPTION, 
						new ItemTree("at0001", new DvText("tree"), null));
			}
		
		} else if("INSTRUCTION".equals(rmTypeName)) {
			
			if( ! valueMap.containsKey(NARRATIVE)) {
				valueMap.put(NARRATIVE, new DvText("instruction narrative"));
			}
			addEntryValues(valueMap, archetype);
			
		} else if("ACTIVITY".equals(rmTypeName)) {
			
			if( ! valueMap.containsKey(TIMING)) {
				valueMap.put(TIMING, new DvParsable("activity timing", "txt"));
			}
	    if( ! valueMap.containsKey("action_archetype_id")) {
		valueMap.put("action_archetype_id", "string value");
	    }
		} else if("COMPOSITION".equals(rmTypeName)) {
			CodePhrase country = new CodePhrase("ISO_3166-1", "SE");
			DvCodedText category = new DvCodedText("event", 
					new CodePhrase("openehr", "433"));
			
			if( ! valueMap.containsKey("composer")) {				
				valueMap.put("composer", PartyIdentified.named("Doctor"));
			}

			if( ! valueMap.containsKey("territory")) {
				valueMap.put("territory", country);
			}
			
			if( ! valueMap.containsKey("category")) {
				valueMap.put("category", category);
			}
			
			addEntryValues(valueMap, archetype);
			
		} else if("ELEMENT".equals(rmTypeName)) {
			
			if(GenerationStrategy.MAXIMUM_EMPTY.equals(strategy)
		    && (("INPUT".equals(ccobj.getAnnotation()) ||
			    ccobj.isAnyAllowed()))) {
				
				// TODO input annotation needs to be standardized
				valueMap.put(VALUE, null);
				valueMap.put(NULL_FLAVOUR, NULL_FLAVOUR_VALUE);
				
				
			} else {
			
				// special fix to create a wrapping dv_coded_text of code_phrase
				// should not be necessary now when the AOM from flattener is fixed
				Object value = valueMap.get(VALUE);
				String text = null;
				if(value instanceof CodePhrase) {
					CodePhrase code = (CodePhrase) value;
					text = termMap.getText(code, ccobj.path() + "/value");
					if(text == null) {
						text = DEFAULT_CODED_TEXT;
					}
					value = new DvCodedText(text, code);
					valueMap.put(VALUE, value);
				}
			}
		
		} else if("EVENT_CONTEXT".equals(rmTypeName)) {
			
			if( ! valueMap.containsKey(START_TIME)) {
				valueMap.put(START_TIME, new DvDateTime());
			}
 			
			if( ! valueMap.containsKey(SETTING)) {
				valueMap.put(SETTING, new DvCodedText("emergency care", 
						new CodePhrase("openehr", "227")));
			}	
			
		} else if("SECTION".equals(rmTypeName)) {
			
			List list = (List) valueMap.get("items");
			if(list != null && list.isEmpty()) {
				valueMap.remove("items");
			}
	} else if("INTERVAL_EVENT".equals(rmTypeName)) {
	    /*if( ! valueMap.containsKey("width")) {
				valueMap.put("width", new DvDuration("P1d"));
			}
			if( ! valueMap.containsKey("time")) {
				valueMap.put("time", new DvDateTime());
			} */
	    return null;

	} else if("POINT_EVENT".equals(rmTypeName)) {
	    if( ! valueMap.containsKey("time")) {
		valueMap.put("time", new DvDateTime());
	    }
		}
		
		// special fix for event		
		if("EVENT".equals(rmTypeName)) {			
			rmTypeName = "POINT_EVENT";
		} 
		
		Object obj = builder.construct(rmTypeName, valueMap);
		return obj;
	}
	
	/*
	 * Checks if given code_prhase is locally defined within an archetype
	 */
	private boolean isLocallyDefined(CodePhrase code) {
		return "local".equalsIgnoreCase(code.getTerminologyId().toString())
		&& code.getCodeString().startsWith("at");
	}
	
	private boolean isOpenEHRTerm(CodePhrase code) {
		return "openehr".equalsIgnoreCase(code.getTerminologyId().getValue());
	}
	
	private void addEntryValues(Map<String, Object> valueMap, 
			Archetype archetype) throws Exception {
		
		CodePhrase lang = new CodePhrase("ISO_639-1", "en");
		CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8");
		String tid = (String) valueMap.get(TEMPLATE_ID);
		TemplateID templateId = tid == null ? null : new TemplateID(tid);
		Archetyped archetypeDetails = new Archetyped(
				archetype.getArchetypeId(), templateId, "1.0.1");
		
		valueMap.put("subject", subject());
		//valueMap.put("provider", provider());
		valueMap.put("encoding", charset);
		valueMap.put("language", lang);
		valueMap.put("archetype_details", archetypeDetails);
	}
	
	// test subject
	protected PartySelf subject() throws Exception {
		PartyRef party = new PartyRef(new HierObjectID("1.2.4.5.6.12.1"),
				"PARTY");
		return new PartySelf(party);
	}

	// test provider
	protected PartyIdentified provider() throws Exception {
		PartyRef performer = new PartyRef(new HierObjectID("1.3.3.1"),
				"ORGANISATION");
		return new PartyIdentified(performer, "provider's name", null);
	}
	
	public Object createAttribute(CAttribute cattribute, Archetype archetype,
	    Map<String, Archetype> archetypeMap, Map<String, Object> extraValues,
	    GenerationStrategy strategy)
			throws Exception {	
		
		log.debug("create attribute " + cattribute.getRmAttributeName());
		
		List<CObject> children = cattribute.getChildren();
		if(cattribute instanceof CSingleAttribute) {
			
			log.debug("single attribute..");
			
			if(children != null && children.size() > 0) {
				// TODO first child is used for rm generation
				CObject cobj = children.get(0);
		return createObject(cobj, archetype, archetypeMap, extraValues, strategy);
			} else {
				throw new Exception ("no child object..");
			}			
		} else { // multiple c_attribute
			
			log.debug("multiple attribute..");
			
			CMultipleAttribute cma = (CMultipleAttribute) cattribute;
			Collection container = new ArrayList<Object>();
			for(CObject cobj : children) {
				
				log.debug("looping children, required: " + cobj.isRequired());
				
				// TODO only create 'required' child
				if(cobj.isAllowed() && 
						(GenerationStrategy.MAXIMUM.equals(strategy) 
								|| GenerationStrategy.MAXIMUM_EMPTY.equals(strategy)
								|| (GenerationStrategy.MINIMUM.equals(strategy) 
										&& cobj.isRequired()))) {
					
					log.debug("required child");
					
		    Object obj = createObject(cobj, archetype, archetypeMap, 
			    extraValues, strategy);
					if(obj != null) {
						container.add(obj);
					}
				}
				
				// special fix for mistaken optional event:
				// events cardinality matches {1..*; unordered} matches {
				//     EVENT[at0003] occurrences matches {0..*} matches {	
				else if("events".equals(cma.getRmAttributeName())
						&& "EVENT".equals(cobj.getRmTypeName())) {
					
					log.debug("mandatory events attribute fix");
					
					container.add(createObject(cobj, archetype, archetypeMap, 
			    extraValues, strategy));
				}				
			}
			
			// TODO special rule to include first child
			if(container.isEmpty()) {
				
				log.debug("add first child for empty container attribute");
				
				// disabled
				// container.add(createObject(children.get(0), archetype));				
		return null;
			}
				
			return container;
		}
	}
	
	public Object createObject(CObject cobj, Archetype archetype,
	    Map<String, Archetype> archetypeMap, Map<String, Object> extraValues,
	    GenerationStrategy strategy) throws Exception {
		
		log.debug("create object with constraint " + cobj.getClass());
		
		if(cobj instanceof CComplexObject) {
		
			// no need for templateId at this level
			return createComplexObject((CComplexObject) cobj, 
		    archetype, null, archetypeMap, extraValues, strategy);
		
		} else if(cobj instanceof CPrimitiveObject) {
			
			return createPrimitiveTypeObject((CPrimitiveObject) cobj, archetype);
		
		} else if(cobj instanceof CDomainType) {
		
			return createDomainTypeObject((CDomainType) cobj, archetype);
			
		} else if(cobj instanceof ArchetypeInternalRef){
			//fix for multiple events where the data attribute of the events is an InternalRef to the first event described 
			return createArchetypeInternalRefObject((ArchetypeInternalRef) cobj, archetype,
				   archetypeMap,extraValues, strategy);
		
		} else {
			// TODO skip archetype_slot etc, log.warn?
			return null;
		}
	}
	
	//fix for multiple events where the data attribute of the events is an InternalRef to the first event described
	private Object createArchetypeInternalRefObject(ArchetypeInternalRef cobj, Archetype archetype,
		    Map<String, Archetype> archetypeMap, Map<String, Object> extraValues,
		    GenerationStrategy strategy) throws Exception{
		
		CObject cobjRef = (CObject) archetype.node(cobj.getTargetPath());
		
		return createObject(cobjRef, archetype,archetypeMap, extraValues, strategy) ;
	}

	private Object createPrimitiveTypeObject(CPrimitiveObject cpo, Archetype archetype) 
			throws Exception {
		
		CPrimitive cp = cpo.getItem();	
		
		if(cp.hasDefaultValue()) {
			return cp.defaultValue();
		}
		
		if(cp instanceof CBoolean) {
			if(((CBoolean) cp).isTrueValid()) {
				return "true";
			} else {
				return "false";
			}
		
		} else if(cp instanceof CString) {
		
			return createString((CString) cp);
		
		} else if(cp instanceof CDate) {
			
			return DEFAULT_DATE;
		
		} else if(cp instanceof CTime) {
			
			return DEFAULT_TIME;
		
		} else if(cp instanceof CDateTime) {
			
			return DEFAULT_DATE_TIME;
		
		} else if(cp instanceof CInteger) {
			
			return Integer.valueOf(0);
		
		} else if(cp instanceof CReal) {
		
			return Double.valueOf(0);
		
		} else if(cp instanceof CDuration) {
			
			CDuration cd = (CDuration) cp;
			DvDuration duration = null;
			
			if(cd.hasAssignedValue()) {
				duration = cd.assignedValue();
			
			} else if(cd.hasDefaultValue()) {
				duration = cd.defaultValue();
			
			} else if(cd.hasAssumedValue()) {
				duration = cd.assumedValue();
			
			} else if(cd.getInterval() != null) {
			
				if(cd.getInterval().getLower() != null) {
					duration = cd.getInterval().getLower();
			
				} else if(cd.getInterval().getUpper() != null) {
					duration = cd.getInterval().getUpper();
				} 
			}			
			if(duration == null) {
				return DEFAULT_DURATION;
			} else {
				return duration.toString();
			}
			
		}
		
		// TODO implement other types
		throw new Exception("unsupported primitive type: " + cp.getType());
	}
	
	private String createString(CString cs) {
		if(cs.defaultValue() != null) {
		
			return cs.defaultValue();
		
		} else if (cs.getList() != null && cs.getList().size() > 0) {
		
			return cs.getList().get(0);
		
		} else {
			return "string value";
		}
	}
	
	private Object createDomainTypeObject(CDomainType cpo, Archetype archetype) 
			throws Exception {
		
		if(cpo instanceof CDvQuantity) {
		
			return createDvQuantity((CDvQuantity) cpo);
		
		} else if(cpo instanceof CCodePhrase) {
		
			return createCodePhrase((CCodePhrase) cpo);
		
		} else if(cpo instanceof CDvOrdinal) {
		
			return createDvOrdinal((CDvOrdinal) cpo, archetype);
		
		} else {		
			throw new Exception("unsupported c_domain_type: " + cpo.getClass());
		}
	}
	
	private DvOrdinal createDvOrdinal(CDvOrdinal cdo, Archetype archetype) 
			throws Exception {
		
		if(cdo.getDefaultValue() != null) {
			Ordinal o = cdo.getDefaultValue();
			return new DvOrdinal(o.getValue(), 
					new DvCodedText(DEFAULT_CODED_TEXT, o.getSymbol()));
		} 
		List<Ordinal> list = cdo.getList();
		if(list == null || list.size() == 0) {
			throw new Exception("empty list of ordinal");
		}
		Ordinal ordinal =  list.iterator().next();
		String text = DEFAULT_CODED_TEXT;
		CodePhrase symbol = ordinal.getSymbol();
		String code = symbol.getCodeString();
		
		if(isLocallyDefined(symbol)) {
			text = retrieveArchetypeTermText(code, archetype);
		} else {
			text = termMap.getText(symbol, cdo.path());
		}
		
		return new DvOrdinal(ordinal.getValue(), 
				new DvCodedText(text, ordinal.getSymbol()));
	}
	
	CodePhrase createCodePhrase(CCodePhrase ccp) throws Exception {
		if(ccp.getDefaultValue() != null) {
			return ccp.getDefaultValue();
		}
		TerminologyID tid = ccp.getTerminologyId();
		List<String> codeList = ccp.getCodeList();
		
		String code;
		if(codeList == null || codeList.isEmpty()) {
			code = "0123456789";
		} else {
			code = codeList.get(0);
		}
		return new CodePhrase(tid, code);
	}
	
	DvQuantity createDvQuantity(CDvQuantity cdq) throws Exception {
		if(cdq.getList() == null || cdq.getList().isEmpty()) {
			return new DvQuantity(0.0);
		}
		// TODO take the first item 
		CDvQuantityItem item = cdq.getList().get(0);
		
		// TODO take the lower limit as magnitude or zero
		double magnitude;
		if(item.getMagnitude() != null) {
			magnitude = item.getMagnitude().getLower();
		} else {
			magnitude = 0;
		}
		
		return new DvQuantity(item.getUnits(), magnitude, measurementService); 
	}
	
	/*
	 * Retrieves a language-specific name of given nodeId
	 * 
	 * TODO hard-coded to retrieve the first language for now
	 */
	DvText retrieveName(String nodeId, Archetype archetype) throws Exception {
		DvText name = new DvText(retrieveArchetypeTermText(nodeId, archetype));
		return name;
	}
	
	/*
	 * Retrieves just the text of given nodeId (at code) 
	 * 
	 * @param nodeId
	 * @param archetype
	 * @return
	 * @throws Exception
	 */
	String retrieveArchetypeTermText(String nodeId, Archetype archetype) throws Exception {
		String language = archetype.getOriginalLanguage().getCodeString();
		ArchetypeTerm term = archetype.getOntology().termDefinition(language, nodeId);
		if(term == null) {
			throw new Exception("term of given code: " + nodeId +
					", language: " + language + " not found..");
		}
		return term.getText();
	}
	
	void processCObject(CObject cobj) {
		
	}
	
	// rm attribute names
	private static final String VALUE = "value";
	private static final String NULL_FLAVOUR = "null_flavour";
	private static final String NARRATIVE = "narrative";
	private static final String DEFINING_CODE = "defining_code";
	private static final String ORIGIN = "origin";
	private static final String TIME = "time";
	private static final String TIMING = "timing";
	private static final String START_TIME = "start_time";
	private static final String SETTING = "setting";
	private static final String DESCRIPTION = "description";
	private static final String MAGNITUDE = "magnitude";
	private static final String TEMPLATE_ID = "template_id";
    private static final String ID = "id";
    private static final String ASSIGNER = "assigner";
    private static final String ISSUER = "issuer";
    private static final String TYPE = "type";
		
	// default values
	private static final String DEFAULT_DATE = "2010-01-01";
	private static final String DEFAULT_TIME = "10:00:00";	
	private static final String DEFAULT_DATE_TIME = "2010-01-01T10:00:00";
	private static final String DEFAULT_DURATION = "PT1H";	
	private static final String DEFAULT_TEXT = "text value";
	private static final String DEFAULT_CODED_TEXT = "coded text value";
	private static final String DEFAULT_COUNT = "1";
	private static final String DEFAULT_URI = "http://www.google.com/";
    private static final String DEFAULT_ID = "1";
    private static final String DEFAULT_ISSUER = "1";
    private static final String DEFAULT_ASSIGNER = "1";
    private static final String DEFAULT_TYPE = "1";
	
	private static final DvCodedText NULL_FLAVOUR_VALUE = new DvCodedText(
			"no information", new CodePhrase(TerminologyService.OPENEHR, "271"));
	
	private static final Logger log = Logger.getLogger(SkeletonGenerator.class);
	
	private RMObjectBuilder builder;
	private TermMap termMap;
	private MeasurementService measurementService;
	private TerminologyService terminologyService;
	private TerminologyAccess openEHRTerminology;
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
 * The Original Code is SkeletonGenerator.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2009-2010 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */