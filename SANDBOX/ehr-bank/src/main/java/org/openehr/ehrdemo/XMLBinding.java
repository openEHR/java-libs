package org.openehr.ehrdemo;

import java.util.List;
import java.util.Set;

import org.apache.axis.types.Token;
import org.apache.axis.types.URI;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.FeederAuditDetails;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.changecontrol.ImportedVersion;
import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.common.changecontrol.Version;
import org.openehr.rm.common.directory.Folder;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.common.generic.PartyRelated;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.common.generic.RevisionHistoryItem;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Action;
import org.openehr.rm.composition.content.entry.Activity;
import org.openehr.rm.composition.content.entry.AdminEntry;
import org.openehr.rm.composition.content.entry.CareEntry;
import org.openehr.rm.composition.content.entry.Entry;
import org.openehr.rm.composition.content.entry.Evaluation;
import org.openehr.rm.composition.content.entry.ISMTransition;
import org.openehr.rm.composition.content.entry.Instruction;
import org.openehr.rm.composition.content.entry.InstructionDetails;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.history.Event;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.history.IntervalEvent;
import org.openehr.rm.datastructure.history.PointEvent;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.ItemTable;
import org.openehr.rm.datastructure.itemstructure.ItemTree;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.basic.DvBoolean;
import org.openehr.rm.datatypes.basic.DvIdentifier;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.encapsulated.DvEncapsulated;
import org.openehr.rm.datatypes.encapsulated.DvMultimedia;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.DvAmount;
import org.openehr.rm.datatypes.quantity.DvCount;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.DvOrdered;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.quantity.DvProportion;
import org.openehr.rm.datatypes.quantity.DvQuantified;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.ReferenceRange;
import org.openehr.rm.datatypes.quantity.datetime.DvDate;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.quantity.datetime.DvTemporal;
import org.openehr.rm.datatypes.quantity.datetime.DvTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvParagraph;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.text.TermMapping;
import org.openehr.rm.datatypes.timespec.DvGeneralTimeSpecification;
import org.openehr.rm.datatypes.timespec.DvPeriodicTimeSpecification;
import org.openehr.rm.datatypes.timespec.DvTimeSpecification;
import org.openehr.rm.datatypes.uri.DvEHRURI;
import org.openehr.rm.datatypes.uri.DvURI;
import org.openehr.rm.support.identification.AccessGroupRef;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.GenericID;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.LocatableRef;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.identification.TemplateID;
import org.openehr.rm.support.identification.TerminologyID;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.schemas.v1.ACCESS_GROUP_REF;
import org.openehr.schemas.v1.ACTION;
import org.openehr.schemas.v1.ACTIVITY;
import org.openehr.schemas.v1.ADMIN_ENTRY;
import org.openehr.schemas.v1.ARCHETYPED;
import org.openehr.schemas.v1.ARCHETYPE_ID;
import org.openehr.schemas.v1.ATTESTATION;
import org.openehr.schemas.v1.AUDIT_DETAILS;
import org.openehr.schemas.v1.CARE_ENTRY;
import org.openehr.schemas.v1.CLUSTER;
import org.openehr.schemas.v1.CODE_PHRASE;
import org.openehr.schemas.v1.COMPOSITION;
import org.openehr.schemas.v1.CONTENT_ITEM;
import org.openehr.schemas.v1.DATA_VALUE;
import org.openehr.schemas.v1.DV_AMOUNT;
import org.openehr.schemas.v1.DV_BOOLEAN;
import org.openehr.schemas.v1.DV_CODED_TEXT;
import org.openehr.schemas.v1.DV_COUNT;
import org.openehr.schemas.v1.DV_DATE;
import org.openehr.schemas.v1.DV_DATE_TIME;
import org.openehr.schemas.v1.DV_DURATION;
import org.openehr.schemas.v1.DV_EHR_URI;
import org.openehr.schemas.v1.DV_ENCAPSULATED;
import org.openehr.schemas.v1.DV_GENERAL_TIME_SPECIFICATION;
import org.openehr.schemas.v1.DV_IDENTIFIER;
import org.openehr.schemas.v1.DV_INTERVAL;
import org.openehr.schemas.v1.DV_MULTIMEDIA;
import org.openehr.schemas.v1.DV_ORDERED;
import org.openehr.schemas.v1.DV_ORDINAL;
import org.openehr.schemas.v1.DV_PARAGRAPH;
import org.openehr.schemas.v1.DV_PARSABLE;
import org.openehr.schemas.v1.DV_PERIODIC_TIME_SPECIFICATION;
import org.openehr.schemas.v1.DV_PROPORTION;
import org.openehr.schemas.v1.DV_QUANTIFIED;
import org.openehr.schemas.v1.DV_QUANTITY;
import org.openehr.schemas.v1.DV_STATE;
import org.openehr.schemas.v1.DV_TEMPORAL;
import org.openehr.schemas.v1.DV_TEXT;
import org.openehr.schemas.v1.DV_TIME;
import org.openehr.schemas.v1.DV_TIME_SPECIFICATION;
import org.openehr.schemas.v1.DV_URI;
import org.openehr.schemas.v1.ELEMENT;
import org.openehr.schemas.v1.ENTRY;
import org.openehr.schemas.v1.EVALUATION;
import org.openehr.schemas.v1.EVENT;
import org.openehr.schemas.v1.EVENT_CONTEXT;
import org.openehr.schemas.v1.FEEDER_AUDIT;
import org.openehr.schemas.v1.FEEDER_AUDIT_DETAILS;
import org.openehr.schemas.v1.FOLDER;
import org.openehr.schemas.v1.GENERIC_ID;
import org.openehr.schemas.v1.HIER_OBJECT_ID;
import org.openehr.schemas.v1.HISTORY;
import org.openehr.schemas.v1.IMPORTED_VERSION;
import org.openehr.schemas.v1.INSTRUCTION;
import org.openehr.schemas.v1.INSTRUCTION_DETAILS;
import org.openehr.schemas.v1.INTERVAL_EVENT;
import org.openehr.schemas.v1.ISM_TRANSITION;
import org.openehr.schemas.v1.ITEM;
import org.openehr.schemas.v1.ITEM_LIST;
import org.openehr.schemas.v1.ITEM_SINGLE;
import org.openehr.schemas.v1.ITEM_STRUCTURE;
import org.openehr.schemas.v1.ITEM_TABLE;
import org.openehr.schemas.v1.ITEM_TREE;
import org.openehr.schemas.v1.LINK;
import org.openehr.schemas.v1.LOCATABLE;
import org.openehr.schemas.v1.LOCATABLE_REF;
import org.openehr.schemas.v1.OBJECT_ID;
import org.openehr.schemas.v1.OBJECT_REF;
import org.openehr.schemas.v1.OBJECT_VERSION_ID;
import org.openehr.schemas.v1.OBSERVATION;
import org.openehr.schemas.v1.ORIGINAL_VERSION;
import org.openehr.schemas.v1.PARTICIPATION;
import org.openehr.schemas.v1.PARTY_IDENTIFIED;
import org.openehr.schemas.v1.PARTY_PROXY;
import org.openehr.schemas.v1.PARTY_REF;
import org.openehr.schemas.v1.PARTY_RELATED;
import org.openehr.schemas.v1.PARTY_SELF;
import org.openehr.schemas.v1.POINT_EVENT;
import org.openehr.schemas.v1.PROPORTION_KIND;
import org.openehr.schemas.v1.REFERENCE_RANGE;
import org.openehr.schemas.v1.REVISION_HISTORY_ITEM;
import org.openehr.schemas.v1.SECTION;
import org.openehr.schemas.v1.TEMPLATE_ID;
import org.openehr.schemas.v1.TERMINOLOGY_ID;
import org.openehr.schemas.v1.TERM_MAPPING;
import org.openehr.schemas.v1.UID_BASED_ID;
import org.openehr.schemas.v1.VERSION;

/**
 * Generated code, don't modify!!
 */
public class XMLBinding {
    public static ACCESS_GROUP_REF convert(AccessGroupRef accessGroupRef) throws Exception {
        if(accessGroupRef == null) {
            return null;
        }
        ACCESS_GROUP_REF ret = new ACCESS_GROUP_REF();
        if(convert(accessGroupRef.getId()) != null) {
            ret.setId(convert(accessGroupRef.getId()));
        }
        if(new Token(accessGroupRef.getNamespace().toString()) != null) {
            ret.setNamespace(new Token(accessGroupRef.getNamespace().toString()));
        }
        if(new Token(accessGroupRef.getType().toString()) != null) {
            ret.setType(new Token(accessGroupRef.getType().toString()));
        }
        return ret;
    }

    public static ACTION convert(Action action) throws Exception {
        if(action == null) {
            return null;
        }
        ACTION ret = new ACTION();
        if(convert(action.getTime()) != null) {
            ret.setTime(convert(action.getTime()));
        }
        if(convert(action.getDescription()) != null) {
            ret.setDescription(convert(action.getDescription()));
        }
        if(convert(action.getIsmTransition()) != null) {
            ret.setIsm_transition(convert(action.getIsmTransition()));
        }
        if(convert(action.getInstructionDetails()) != null) {
            ret.setInstruction_details(convert(action.getInstructionDetails()));
        }
        if(convert(action.getProtocol()) != null) {
            ret.setProtocol(convert(action.getProtocol()));
        }
        if(convert(action.getGuidelineId()) != null) {
            ret.setGuideline_id(convert(action.getGuidelineId()));
        }
        if(convert(action.getLanguage()) != null) {
            ret.setLanguage(convert(action.getLanguage()));
        }
        if(convert(action.getEncoding()) != null) {
            ret.setEncoding(convert(action.getEncoding()));
        }
        if(convert(action.getSubject()) != null) {
            ret.setSubject(convert(action.getSubject()));
        }
        if(convert(action.getProvider()) != null) {
            ret.setProvider(convert(action.getProvider()));
        }
        List<Participation> otherParticipations = action.getOtherParticipations();
        if(otherParticipations != null) {
            PARTICIPATION[] _other_participations = new PARTICIPATION[otherParticipations.size()];
            int _other_participations_idx = 0;
            for(Participation p : otherParticipations) {
                _other_participations[_other_participations_idx++] = convert(p);
            }
            ret.setOther_participations(_other_participations);
        }
        if(convert(action.getWorkflowId()) != null) {
            ret.setWork_flow_id(convert(action.getWorkflowId()));
        }
        if(convert(action.getName()) != null) {
            ret.setName(convert(action.getName()));
        }
        if(convert(action.getUid()) != null) {
            ret.setUid(convert(action.getUid()));
        }
        Set<Link> links = action.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(action.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(action.getArchetypeDetails()));
        }
        if(convert(action.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(action.getFeederAudit()));
        }
        if(action.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(action.getArchetypeNodeId());
        }
        return ret;
    }

    public static ACTIVITY convert(Activity activity) throws Exception {
        if(activity == null) {
            return null;
        }
        ACTIVITY ret = new ACTIVITY();
        if(convert(activity.getDescription()) != null) {
            ret.setDescription(convert(activity.getDescription()));
        }
        if(convert(activity.getTiming()) != null) {
            ret.setTiming(convert(activity.getTiming()));
        }
        if(activity.getActionArchetypeId() != null) {
            ret.setAction_archetype_id(activity.getActionArchetypeId());
        }
        if(convert(activity.getName()) != null) {
            ret.setName(convert(activity.getName()));
        }
        if(convert(activity.getUid()) != null) {
            ret.setUid(convert(activity.getUid()));
        }
        Set<Link> links = activity.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(activity.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(activity.getArchetypeDetails()));
        }
        if(convert(activity.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(activity.getFeederAudit()));
        }
        if(activity.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(activity.getArchetypeNodeId());
        }
        return ret;
    }

    public static ADMIN_ENTRY convert(AdminEntry adminEntry) throws Exception {
        if(adminEntry == null) {
            return null;
        }
        ADMIN_ENTRY ret = new ADMIN_ENTRY();
        if(convert(adminEntry.getData()) != null) {
            ret.setData(convert(adminEntry.getData()));
        }
        if(convert(adminEntry.getLanguage()) != null) {
            ret.setLanguage(convert(adminEntry.getLanguage()));
        }
        if(convert(adminEntry.getEncoding()) != null) {
            ret.setEncoding(convert(adminEntry.getEncoding()));
        }
        if(convert(adminEntry.getSubject()) != null) {
            ret.setSubject(convert(adminEntry.getSubject()));
        }
        if(convert(adminEntry.getProvider()) != null) {
            ret.setProvider(convert(adminEntry.getProvider()));
        }
        List<Participation> otherParticipations = adminEntry.getOtherParticipations();
        if(otherParticipations != null) {
            PARTICIPATION[] _other_participations = new PARTICIPATION[otherParticipations.size()];
            int _other_participations_idx = 0;
            for(Participation p : otherParticipations) {
                _other_participations[_other_participations_idx++] = convert(p);
            }
            ret.setOther_participations(_other_participations);
        }
        if(convert(adminEntry.getWorkflowId()) != null) {
            ret.setWork_flow_id(convert(adminEntry.getWorkflowId()));
        }
        if(convert(adminEntry.getName()) != null) {
            ret.setName(convert(adminEntry.getName()));
        }
        if(convert(adminEntry.getUid()) != null) {
            ret.setUid(convert(adminEntry.getUid()));
        }
        Set<Link> links = adminEntry.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(adminEntry.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(adminEntry.getArchetypeDetails()));
        }
        if(convert(adminEntry.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(adminEntry.getFeederAudit()));
        }
        if(adminEntry.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(adminEntry.getArchetypeNodeId());
        }
        return ret;
    }

    public static ARCHETYPED convert(Archetyped archetyped) throws Exception {
        if(archetyped == null) {
            return null;
        }
        ARCHETYPED ret = new ARCHETYPED();
        if(convert(archetyped.getArchetypeId()) != null) {
            ret.setArchetype_id(convert(archetyped.getArchetypeId()));
        }
        if(convert(archetyped.getTemplateId()) != null) {
            ret.setTemplate_id(convert(archetyped.getTemplateId()));
        }
        if(archetyped.getRmVersion() != null) {
            ret.setRm_version(archetyped.getRmVersion());
        }
        return ret;
    }

    public static ARCHETYPE_ID convert(ArchetypeID archetypeID) throws Exception {
        if(archetypeID == null) {
            return null;
        }
        ARCHETYPE_ID ret = new ARCHETYPE_ID();
        if(new Token(archetypeID.getValue()) != null) {
            ret.setValue(new Token(archetypeID.getValue()));
        }
        return ret;
    }

    public static ATTESTATION convert(Attestation attestation) throws Exception {
        if(attestation == null) {
            return null;
        }
        ATTESTATION ret = new ATTESTATION();
        if(convert(attestation.getAttestedView()) != null) {
            ret.setAttested_view(convert(attestation.getAttestedView()));
        }
        if(attestation.getProof() != null) {
            ret.setProof(attestation.getProof());
        }
        Set<DvEHRURI> items = attestation.getItems();
        if(items != null) {
            DV_EHR_URI[] _items = new DV_EHR_URI[items.size()];
            int _items_idx = 0;
            for(DvEHRURI d : items) {
                _items[_items_idx++] = convert(d);
            }
            ret.setItems(_items);
        }
        if(convert(attestation.getReason()) != null) {
            ret.setReason(convert(attestation.getReason()));
        }
        ret.setIs_pending(attestation.isPending());
        if(attestation.getSystemId() != null) {
            ret.setSystem_id(attestation.getSystemId());
        }
        if(convert(attestation.getCommitter()) != null) {
            ret.setCommitter(convert(attestation.getCommitter()));
        }
        if(convert(attestation.getTimeCommitted()) != null) {
            ret.setTime_committed(convert(attestation.getTimeCommitted()));
        }
        if(convert(attestation.getChangeType()) != null) {
            ret.setChange_type(convert(attestation.getChangeType()));
        }
        if(convert(attestation.getDescription()) != null) {
            ret.setDescription(convert(attestation.getDescription()));
        }
        return ret;
    }

    public static AUDIT_DETAILS convert(AuditDetails auditDetails) throws Exception {
        if(auditDetails == null) {
            return null;
        }
        AUDIT_DETAILS ret = new AUDIT_DETAILS();
        if(auditDetails.getSystemId() != null) {
            ret.setSystem_id(auditDetails.getSystemId());
        }
        if(convert(auditDetails.getCommitter()) != null) {
            ret.setCommitter(convert(auditDetails.getCommitter()));
        }
        if(convert(auditDetails.getTimeCommitted()) != null) {
            ret.setTime_committed(convert(auditDetails.getTimeCommitted()));
        }
        if(convert(auditDetails.getChangeType()) != null) {
            ret.setChange_type(convert(auditDetails.getChangeType()));
        }
        if(convert(auditDetails.getDescription()) != null) {
            ret.setDescription(convert(auditDetails.getDescription()));
        }
        return ret;
    }

    public static CLUSTER convert(Cluster cluster) throws Exception {
        if(cluster == null) {
            return null;
        }
        CLUSTER ret = new CLUSTER();
        List<Item> items = cluster.getItems();
        if(items != null) {
            ITEM[] _items = new ITEM[items.size()];
            int _items_idx = 0;
            for(Item i : items) {
                _items[_items_idx++] = convert(i);
            }
            ret.setItems(_items);
        }
        if(convert(cluster.getName()) != null) {
            ret.setName(convert(cluster.getName()));
        }
        if(convert(cluster.getUid()) != null) {
            ret.setUid(convert(cluster.getUid()));
        }
        Set<Link> links = cluster.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(cluster.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(cluster.getArchetypeDetails()));
        }
        if(convert(cluster.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(cluster.getFeederAudit()));
        }
        if(cluster.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(cluster.getArchetypeNodeId());
        }
        return ret;
    }

    public static CODE_PHRASE convert(CodePhrase codePhrase) throws Exception {
        if(codePhrase == null) {
            return null;
        }
        CODE_PHRASE ret = new CODE_PHRASE();
        if(convert(codePhrase.getTerminologyId()) != null) {
            ret.setTerminology_id(convert(codePhrase.getTerminologyId()));
        }
        if(codePhrase.getCodeString() != null) {
            ret.setCode_string(codePhrase.getCodeString());
        }
        return ret;
    }

    public static COMPOSITION convert(Composition composition) throws Exception {
        if(composition == null) {
            return null;
        }
        COMPOSITION ret = new COMPOSITION();
        if(convert(composition.getLanguage()) != null) {
            ret.setLanguage(convert(composition.getLanguage()));
        }
        if(convert(composition.getTerritory()) != null) {
            ret.setTerritory(convert(composition.getTerritory()));
        }
        if(convert(composition.getCategory()) != null) {
            ret.setCategory(convert(composition.getCategory()));
        }
        if(convert(composition.getComposer()) != null) {
            ret.setComposer(convert(composition.getComposer()));
        }
        if(convert(composition.getContext()) != null) {
            ret.setContext(convert(composition.getContext()));
        }
        List<ContentItem> content = composition.getContent();
        if(content != null) {
            CONTENT_ITEM[] _content = new CONTENT_ITEM[content.size()];
            int _content_idx = 0;
            for(ContentItem c : content) {
                _content[_content_idx++] = convert(c);
            }
            ret.setContent(_content);
        }
        if(convert(composition.getName()) != null) {
            ret.setName(convert(composition.getName()));
        }
        if(convert(composition.getUid()) != null) {
            ret.setUid(convert(composition.getUid()));
        }
        Set<Link> links = composition.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(composition.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(composition.getArchetypeDetails()));
        }
        if(convert(composition.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(composition.getFeederAudit()));
        }
        if(composition.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(composition.getArchetypeNodeId());
        }
        return ret;
    }

    public static DV_BOOLEAN convert(DvBoolean dvBoolean) throws Exception {
        if(dvBoolean == null) {
            return null;
        }
        DV_BOOLEAN ret = new DV_BOOLEAN();
        ret.setValue(dvBoolean.getValue());
        return ret;
    }

    public static DV_CODED_TEXT convert(DvCodedText dvCodedText) throws Exception {
        if(dvCodedText == null) {
            return null;
        }
        DV_CODED_TEXT ret = new DV_CODED_TEXT();
        if(convert(dvCodedText.getDefiningCode()) != null) {
            ret.setDefining_code(convert(dvCodedText.getDefiningCode()));
        }
        if(dvCodedText.getValue() != null) {
            ret.setValue(dvCodedText.getValue());
        }
        if(convert(dvCodedText.getHyperlink()) != null) {
            ret.setHyperlink(convert(dvCodedText.getHyperlink()));
        }
        if(dvCodedText.getFormatting() != null) {
            ret.setFormatting(dvCodedText.getFormatting());
        }
        List<TermMapping> mappings = dvCodedText.getMappings();
        if(mappings != null) {
            TERM_MAPPING[] _mappings = new TERM_MAPPING[mappings.size()];
            int _mappings_idx = 0;
            for(TermMapping t : mappings) {
                _mappings[_mappings_idx++] = convert(t);
            }
            ret.setMappings(_mappings);
        }
        if(convert(dvCodedText.getLanguage()) != null) {
            ret.setLanguage(convert(dvCodedText.getLanguage()));
        }
        if(convert(dvCodedText.getEncoding()) != null) {
            ret.setEncoding(convert(dvCodedText.getEncoding()));
        }
        return ret;
    }

    public static DV_COUNT convert(DvCount dvCount) throws Exception {
        if(dvCount == null) {
            return null;
        }
        DV_COUNT ret = new DV_COUNT();
        if(dvCount.getMagnitude() != null) {
            ret.setMagnitude(dvCount.getMagnitude());
        }
        ret.setAccuracy(new Float(dvCount.getAccuracy()));
        ret.setAccuracy_is_percent(dvCount.isAccuracyPercent());
        if(dvCount.getMagnitudeStatus() != null) {
            ret.setMagnitude_status(dvCount.getMagnitudeStatus());
        }
        if(convert(dvCount.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvCount.getNormalRange()));
        }
        List<ReferenceRange<DvCount>> otherReferenceRanges = dvCount.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvCount> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvCount.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvCount.getNormalStatus()));
        }
        return ret;
    }

    public static DV_DATE convert(DvDate dvDate) throws Exception {
        if(dvDate == null) {
            return null;
        }
        DV_DATE ret = new DV_DATE();
        if(dvDate.getValue() != null) {
            ret.setValue(dvDate.getValue());
        }
        if(convert(dvDate.getAccuracy()) != null) {
            ret.setAccuracy(convert(dvDate.getAccuracy()));
        }
        if(dvDate.getMagnitudeStatus() != null) {
            ret.setMagnitude_status(dvDate.getMagnitudeStatus());
        }
        if(convert(dvDate.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvDate.getNormalRange()));
        }
        List<ReferenceRange<DvDate>> otherReferenceRanges = dvDate.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvDate> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvDate.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvDate.getNormalStatus()));
        }
        return ret;
    }

    public static DV_DATE_TIME convert(DvDateTime dvDateTime) throws Exception {
        if(dvDateTime == null) {
            return null;
        }
        DV_DATE_TIME ret = new DV_DATE_TIME();
        if(dvDateTime.getValue() != null) {
            ret.setValue(dvDateTime.getValue());
        }
        if(convert(dvDateTime.getAccuracy()) != null) {
            ret.setAccuracy(convert(dvDateTime.getAccuracy()));
        }
        if(dvDateTime.getMagnitudeStatus() != null) {
            ret.setMagnitude_status(dvDateTime.getMagnitudeStatus());
        }
        if(convert(dvDateTime.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvDateTime.getNormalRange()));
        }
        List<ReferenceRange<DvDateTime>> otherReferenceRanges = dvDateTime.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvDateTime> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvDateTime.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvDateTime.getNormalStatus()));
        }
        return ret;
    }

    public static DV_DURATION convert(DvDuration dvDuration) throws Exception {
        if(dvDuration == null) {
            return null;
        }
        DV_DURATION ret = new DV_DURATION();
        if(dvDuration.getValue() != null) {
            ret.setValue(dvDuration.getValue());
        }
        ret.setAccuracy(new Float(dvDuration.getAccuracy()));
        ret.setAccuracy_is_percent(dvDuration.isAccuracyPercent());
        if(dvDuration.getMagnitudeStatus() != null) {
            ret.setMagnitude_status(dvDuration.getMagnitudeStatus());
        }
        if(convert(dvDuration.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvDuration.getNormalRange()));
        }
        List<ReferenceRange<DvDuration>> otherReferenceRanges = dvDuration.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvDuration> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvDuration.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvDuration.getNormalStatus()));
        }
        return ret;
    }

    public static DV_EHR_URI convert(DvEHRURI dvEHRURI) throws Exception {
        if(dvEHRURI == null) {
            return null;
        }
        DV_EHR_URI ret = new DV_EHR_URI();
        if(new URI(dvEHRURI.getValue()) != null) {
            ret.setValue(new URI(dvEHRURI.getValue()));
        }
        return ret;
    }

    public static DV_GENERAL_TIME_SPECIFICATION convert(DvGeneralTimeSpecification dvGeneralTimeSpecification) throws Exception {
        if(dvGeneralTimeSpecification == null) {
            return null;
        }
        DV_GENERAL_TIME_SPECIFICATION ret = new DV_GENERAL_TIME_SPECIFICATION();
        if(convert(dvGeneralTimeSpecification.getValue()) != null) {
            ret.setValue(convert(dvGeneralTimeSpecification.getValue()));
        }
        return ret;
    }

    public static DV_IDENTIFIER convert(DvIdentifier dvIdentifier) throws Exception {
        if(dvIdentifier == null) {
            return null;
        }
        DV_IDENTIFIER ret = new DV_IDENTIFIER();
        if(dvIdentifier.getIssuer() != null) {
            ret.setIssuer(dvIdentifier.getIssuer());
        }
        if(dvIdentifier.getAssigner() != null) {
            ret.setAssigner(dvIdentifier.getAssigner());
        }
        if(dvIdentifier.getId() != null) {
            ret.setId(dvIdentifier.getId());
        }
        if(dvIdentifier.getType() != null) {
            ret.setType(dvIdentifier.getType());
        }
        return ret;
    }

    public static DV_INTERVAL convert(DvInterval dvInterval) throws Exception {
        if(dvInterval == null) {
            return null;
        }
        DV_INTERVAL ret = new DV_INTERVAL();
        if(convert(dvInterval.getLower()) != null) {
            ret.setLower(convert(dvInterval.getLower()));
        }
        if(convert(dvInterval.getUpper()) != null) {
            ret.setUpper(convert(dvInterval.getUpper()));
        }
        ret.setLower_included(dvInterval.isLowerIncluded());
        ret.setUpper_included(dvInterval.isUpperIncluded());
        ret.setLower_unbounded(dvInterval.isLowerUnbounded());
        ret.setUpper_unbounded(dvInterval.isUpperUnbounded());
        return ret;
    }

    public static DV_MULTIMEDIA convert(DvMultimedia dvMultimedia) throws Exception {
        if(dvMultimedia == null) {
            return null;
        }
        DV_MULTIMEDIA ret = new DV_MULTIMEDIA();
        if(dvMultimedia.getAlternateText() != null) {
            ret.setAlternate_text(dvMultimedia.getAlternateText());
        }
        if(convert(dvMultimedia.getUri()) != null) {
            ret.setUri(convert(dvMultimedia.getUri()));
        }
        if(dvMultimedia.getData() != null) {
            ret.setData(dvMultimedia.getData());
        }
        if(convert(dvMultimedia.getMediaType()) != null) {
            ret.setMedia_type(convert(dvMultimedia.getMediaType()));
        }
        if(convert(dvMultimedia.getCompressionAlgorithm()) != null) {
            ret.setCompression_algorithm(convert(dvMultimedia.getCompressionAlgorithm()));
        }
        if(dvMultimedia.getIntegrityCheck() != null) {
            ret.setIntegrity_check(dvMultimedia.getIntegrityCheck());
        }
        if(convert(dvMultimedia.getIntegrityCheckAlgorithm()) != null) {
            ret.setIntegrity_check_algorithm(convert(dvMultimedia.getIntegrityCheckAlgorithm()));
        }
        ret.setSize(dvMultimedia.getSize());
        if(convert(dvMultimedia.getThumbnail()) != null) {
            ret.setThumbnail(convert(dvMultimedia.getThumbnail()));
        }
        if(convert(dvMultimedia.getCharset()) != null) {
            ret.setCharset(convert(dvMultimedia.getCharset()));
        }
        if(convert(dvMultimedia.getLanguage()) != null) {
            ret.setLanguage(convert(dvMultimedia.getLanguage()));
        }
        return ret;
    }

    public static DV_ORDINAL convert(DvOrdinal dvOrdinal) throws Exception {
        if(dvOrdinal == null) {
            return null;
        }
        DV_ORDINAL ret = new DV_ORDINAL();
        ret.setValue(dvOrdinal.getValue());
        if(convert(dvOrdinal.getSymbol()) != null) {
            ret.setSymbol(convert(dvOrdinal.getSymbol()));
        }
        if(convert(dvOrdinal.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvOrdinal.getNormalRange()));
        }
        List<ReferenceRange<DvOrdinal>> otherReferenceRanges = dvOrdinal.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvOrdinal> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvOrdinal.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvOrdinal.getNormalStatus()));
        }
        return ret;
    }

    public static DV_PARAGRAPH convert(DvParagraph dvParagraph) throws Exception {
        if(dvParagraph == null) {
            return null;
        }
        DV_PARAGRAPH ret = new DV_PARAGRAPH();
        List<DvText> items = dvParagraph.getItems();
        if(items != null) {
            DV_TEXT[] _items = new DV_TEXT[items.size()];
            int _items_idx = 0;
            for(DvText d : items) {
                _items[_items_idx++] = convert(d);
            }
            ret.setItems(_items);
        }
        return ret;
    }

    public static DV_PARSABLE convert(DvParsable dvParsable) throws Exception {
        if(dvParsable == null) {
            return null;
        }
        DV_PARSABLE ret = new DV_PARSABLE();
        if(dvParsable.getValue() != null) {
            ret.setValue(dvParsable.getValue());
        }
        if(dvParsable.getFormalism() != null) {
            ret.setFormalism(dvParsable.getFormalism());
        }
        if(convert(dvParsable.getCharset()) != null) {
            ret.setCharset(convert(dvParsable.getCharset()));
        }
        if(convert(dvParsable.getLanguage()) != null) {
            ret.setLanguage(convert(dvParsable.getLanguage()));
        }
        return ret;
    }

    public static DV_PERIODIC_TIME_SPECIFICATION convert(DvPeriodicTimeSpecification dvPeriodicTimeSpecification) throws Exception {
        if(dvPeriodicTimeSpecification == null) {
            return null;
        }
        DV_PERIODIC_TIME_SPECIFICATION ret = new DV_PERIODIC_TIME_SPECIFICATION();
        if(convert(dvPeriodicTimeSpecification.getValue()) != null) {
            ret.setValue(convert(dvPeriodicTimeSpecification.getValue()));
        }
        return ret;
    }

    public static DV_PROPORTION convert(DvProportion dvProportion) throws Exception {
        if(dvProportion == null) {
            return null;
        }
        DV_PROPORTION ret = new DV_PROPORTION();
        ret.setNumerator(((float) dvProportion.getNumerator()));
        ret.setDenominator(((float) dvProportion.getDenominator()));
        if(PROPORTION_KIND.fromValue(dvProportion.getType().toString()) != null) {
            ret.setType(PROPORTION_KIND.fromValue(dvProportion.getType().toString()));
        }
        if(dvProportion.getPrecision() != null) {
            ret.setPrecision(dvProportion.getPrecision());
        }
        ret.setAccuracy(new Float(dvProportion.getAccuracy()));
        ret.setAccuracy_is_percent(dvProportion.isAccuracyPercent());
        if(dvProportion.getMagnitudeStatus() != null) {
            ret.setMagnitude_status(dvProportion.getMagnitudeStatus());
        }
        if(convert(dvProportion.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvProportion.getNormalRange()));
        }
        List<ReferenceRange<DvProportion>> otherReferenceRanges = dvProportion.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvProportion> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvProportion.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvProportion.getNormalStatus()));
        }
        return ret;
    }

    public static DV_QUANTITY convert(DvQuantity dvQuantity) throws Exception {
        if(dvQuantity == null) {
            return null;
        }
        DV_QUANTITY ret = new DV_QUANTITY();
        if(dvQuantity.getMagnitude() != null) {
            ret.setMagnitude(dvQuantity.getMagnitude());
        }
        if(dvQuantity.getUnits() != null) {
            ret.setUnits(dvQuantity.getUnits());
        }
        ret.setPrecision(dvQuantity.getPrecision());
        ret.setAccuracy(new Float(dvQuantity.getAccuracy()));
        ret.setAccuracy_is_percent(dvQuantity.isAccuracyPercent());
        if(dvQuantity.getMagnitudeStatus() != null) {
            ret.setMagnitude_status(dvQuantity.getMagnitudeStatus());
        }
        if(convert(dvQuantity.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvQuantity.getNormalRange()));
        }
        List<ReferenceRange<DvQuantity>> otherReferenceRanges = dvQuantity.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvQuantity> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvQuantity.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvQuantity.getNormalStatus()));
        }
        return ret;
    }

    public static DV_STATE convert(DvState dvState) throws Exception {
        if(dvState == null) {
            return null;
        }
        DV_STATE ret = new DV_STATE();
        if(convert(dvState.getValue()) != null) {
            ret.setValue(convert(dvState.getValue()));
        }
        ret.setIs_terminal(dvState.isTerminal());
        return ret;
    }

    public static DV_TEXT convert(DvText dvText) throws Exception {
        if(dvText == null) {
            return null;
        }
        DV_TEXT ret = new DV_TEXT();
        if(dvText.getValue() != null) {
            ret.setValue(dvText.getValue());
        }
        if(convert(dvText.getHyperlink()) != null) {
            ret.setHyperlink(convert(dvText.getHyperlink()));
        }
        if(dvText.getFormatting() != null) {
            ret.setFormatting(dvText.getFormatting());
        }
        List<TermMapping> mappings = dvText.getMappings();
        if(mappings != null) {
            TERM_MAPPING[] _mappings = new TERM_MAPPING[mappings.size()];
            int _mappings_idx = 0;
            for(TermMapping t : mappings) {
                _mappings[_mappings_idx++] = convert(t);
            }
            ret.setMappings(_mappings);
        }
        if(convert(dvText.getLanguage()) != null) {
            ret.setLanguage(convert(dvText.getLanguage()));
        }
        if(convert(dvText.getEncoding()) != null) {
            ret.setEncoding(convert(dvText.getEncoding()));
        }
        return ret;
    }

    public static DV_TIME convert(DvTime dvTime) throws Exception {
        if(dvTime == null) {
            return null;
        }
        DV_TIME ret = new DV_TIME();
        if(dvTime.getValue() != null) {
            ret.setValue(dvTime.getValue());
        }
        if(convert(dvTime.getAccuracy()) != null) {
            ret.setAccuracy(convert(dvTime.getAccuracy()));
        }
        if(dvTime.getMagnitudeStatus() != null) {
            ret.setMagnitude_status(dvTime.getMagnitudeStatus());
        }
        if(convert(dvTime.getNormalRange()) != null) {
            ret.setNormal_range(convert(dvTime.getNormalRange()));
        }
        List<ReferenceRange<DvTime>> otherReferenceRanges = dvTime.getOtherReferenceRanges();
        if(otherReferenceRanges != null) {
            REFERENCE_RANGE[] _other_reference_ranges = new REFERENCE_RANGE[otherReferenceRanges.size()];
            int _other_reference_ranges_idx = 0;
            for(ReferenceRange<DvTime> r : otherReferenceRanges) {
                _other_reference_ranges[_other_reference_ranges_idx++] = convert(r);
            }
            ret.setOther_reference_ranges(_other_reference_ranges);
        }
        if(convert(dvTime.getNormalStatus()) != null) {
            ret.setNormal_status(convert(dvTime.getNormalStatus()));
        }
        return ret;
    }

    public static DV_URI convert(DvURI dvURI) throws Exception {
        if(dvURI == null) {
            return null;
        }
        DV_URI ret = new DV_URI();
        if(new URI(dvURI.getValue()) != null) {
            ret.setValue(new URI(dvURI.getValue()));
        }
        return ret;
    }

    // kernel class not found for org.openehr.schemas.v1.EHR_EXTRACT_CONTENT

    public static ELEMENT convert(Element element) throws Exception {
        if(element == null) {
            return null;
        }
        ELEMENT ret = new ELEMENT();
        if(convert(element.getValue()) != null) {
            ret.setValue(convert(element.getValue()));
        }
        if(convert(element.getNullFlavor()) != null) {
            ret.setNull_flavour(convert(element.getNullFlavor()));
        }
        if(convert(element.getName()) != null) {
            ret.setName(convert(element.getName()));
        }
        if(convert(element.getUid()) != null) {
            ret.setUid(convert(element.getUid()));
        }
        Set<Link> links = element.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(element.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(element.getArchetypeDetails()));
        }
        if(convert(element.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(element.getFeederAudit()));
        }
        if(element.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(element.getArchetypeNodeId());
        }
        return ret;
    }

    public static EVALUATION convert(Evaluation evaluation) throws Exception {
        if(evaluation == null) {
            return null;
        }
        EVALUATION ret = new EVALUATION();
        if(convert(evaluation.getData()) != null) {
            ret.setData(convert(evaluation.getData()));
        }
        if(convert(evaluation.getProtocol()) != null) {
            ret.setProtocol(convert(evaluation.getProtocol()));
        }
        if(convert(evaluation.getGuidelineId()) != null) {
            ret.setGuideline_id(convert(evaluation.getGuidelineId()));
        }
        if(convert(evaluation.getLanguage()) != null) {
            ret.setLanguage(convert(evaluation.getLanguage()));
        }
        if(convert(evaluation.getEncoding()) != null) {
            ret.setEncoding(convert(evaluation.getEncoding()));
        }
        if(convert(evaluation.getSubject()) != null) {
            ret.setSubject(convert(evaluation.getSubject()));
        }
        if(convert(evaluation.getProvider()) != null) {
            ret.setProvider(convert(evaluation.getProvider()));
        }
        List<Participation> otherParticipations = evaluation.getOtherParticipations();
        if(otherParticipations != null) {
            PARTICIPATION[] _other_participations = new PARTICIPATION[otherParticipations.size()];
            int _other_participations_idx = 0;
            for(Participation p : otherParticipations) {
                _other_participations[_other_participations_idx++] = convert(p);
            }
            ret.setOther_participations(_other_participations);
        }
        if(convert(evaluation.getWorkflowId()) != null) {
            ret.setWork_flow_id(convert(evaluation.getWorkflowId()));
        }
        if(convert(evaluation.getName()) != null) {
            ret.setName(convert(evaluation.getName()));
        }
        if(convert(evaluation.getUid()) != null) {
            ret.setUid(convert(evaluation.getUid()));
        }
        Set<Link> links = evaluation.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(evaluation.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(evaluation.getArchetypeDetails()));
        }
        if(convert(evaluation.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(evaluation.getFeederAudit()));
        }
        if(evaluation.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(evaluation.getArchetypeNodeId());
        }
        return ret;
    }

    public static EVENT_CONTEXT convert(EventContext eventContext) throws Exception {
        if(eventContext == null) {
            return null;
        }
        EVENT_CONTEXT ret = new EVENT_CONTEXT();
        if(convert(eventContext.getStartTime()) != null) {
            ret.setStart_time(convert(eventContext.getStartTime()));
        }
        if(convert(eventContext.getEndTime()) != null) {
            ret.setEnd_time(convert(eventContext.getEndTime()));
        }
        if(eventContext.getLocation() != null) {
            ret.setLocation(eventContext.getLocation());
        }
        if(convert(eventContext.getSetting()) != null) {
            ret.setSetting(convert(eventContext.getSetting()));
        }
        if(convert(eventContext.getOtherContext()) != null) {
            ret.setOther_context(convert(eventContext.getOtherContext()));
        }
        if(convert(eventContext.getHealthCareFacility()) != null) {
            ret.setHealth_care_facility(convert(eventContext.getHealthCareFacility()));
        }
        List<Participation> participations = eventContext.getParticipations();
        if(participations != null) {
            PARTICIPATION[] _participations = new PARTICIPATION[participations.size()];
            int _participations_idx = 0;
            for(Participation p : participations) {
                _participations[_participations_idx++] = convert(p);
            }
            ret.setParticipations(_participations);
        }
        return ret;
    }

    // kernel class not found for org.openehr.schemas.v1.EXTRACT

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_CHAPTER

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_ENTITY_IDENTIFIER

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_ENTITY_MANIFEST

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_FOLDER

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_ITEM

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_REQUEST

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_SPEC

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_UPDATE_SPEC

    // kernel class not found for org.openehr.schemas.v1.EXTRACT_VERSION_SPEC

    public static FEEDER_AUDIT convert(FeederAudit feederAudit) throws Exception {
        if(feederAudit == null) {
            return null;
        }
        FEEDER_AUDIT ret = new FEEDER_AUDIT();
        List<DvIdentifier> originatingSystemItemIds = feederAudit.getOriginatingSystemItemIds();
        if(originatingSystemItemIds != null) {
            DV_IDENTIFIER[] _originating_system_item_ids = new DV_IDENTIFIER[originatingSystemItemIds.size()];
            int _originating_system_item_ids_idx = 0;
            for(DvIdentifier d : originatingSystemItemIds) {
                _originating_system_item_ids[_originating_system_item_ids_idx++] = convert(d);
            }
            ret.setOriginating_system_item_ids(_originating_system_item_ids);
        }
        List<DvIdentifier> feederSystemItemIds = feederAudit.getFeederSystemItemIds();
        if(feederSystemItemIds != null) {
            DV_IDENTIFIER[] _feeder_system_item_ids = new DV_IDENTIFIER[feederSystemItemIds.size()];
            int _feeder_system_item_ids_idx = 0;
            for(DvIdentifier d : feederSystemItemIds) {
                _feeder_system_item_ids[_feeder_system_item_ids_idx++] = convert(d);
            }
            ret.setFeeder_system_item_ids(_feeder_system_item_ids);
        }
        if(convert(feederAudit.getOriginalContent()) != null) {
            ret.setOriginal_content(convert(feederAudit.getOriginalContent()));
        }
        if(convert(feederAudit.getOriginatingSystemAudit()) != null) {
            ret.setOriginating_system_audit(convert(feederAudit.getOriginatingSystemAudit()));
        }
        if(convert(feederAudit.getFeederSystemAudit()) != null) {
            ret.setFeeder_system_audit(convert(feederAudit.getFeederSystemAudit()));
        }
        return ret;
    }

    public static FEEDER_AUDIT_DETAILS convert(FeederAuditDetails feederAuditDetails) throws Exception {
        if(feederAuditDetails == null) {
            return null;
        }
        FEEDER_AUDIT_DETAILS ret = new FEEDER_AUDIT_DETAILS();
        if(feederAuditDetails.getSystemId() != null) {
            ret.setSystem_id(feederAuditDetails.getSystemId());
        }
        if(convert(feederAuditDetails.getLocation()) != null) {
            ret.setLocation(convert(feederAuditDetails.getLocation()));
        }
        if(convert(feederAuditDetails.getProvider()) != null) {
            ret.setProvider(convert(feederAuditDetails.getProvider()));
        }
        if(convert(feederAuditDetails.getSubject()) != null) {
            ret.setSubject(convert(feederAuditDetails.getSubject()));
        }
        if(convert(feederAuditDetails.getTime()) != null) {
            ret.setTime(convert(feederAuditDetails.getTime()));
        }
        if(feederAuditDetails.getVersionId() != null) {
            ret.setVersion_id(feederAuditDetails.getVersionId());
        }
        return ret;
    }

    public static FOLDER convert(Folder folder) throws Exception {
        if(folder == null) {
            return null;
        }
        FOLDER ret = new FOLDER();
        List<Folder> folders = folder.getFolders();
        if(folders != null) {
            FOLDER[] _folders = new FOLDER[folders.size()];
            int _folders_idx = 0;
            for(Folder f : folders) {
                _folders[_folders_idx++] = convert(f);
            }
            ret.setFolders(_folders);
        }
        List<ObjectRef> items = folder.getItems();
        if(items != null) {
            OBJECT_REF[] _items = new OBJECT_REF[items.size()];
            int _items_idx = 0;
            for(ObjectRef o : items) {
                _items[_items_idx++] = convert(o);
            }
            ret.setItems(_items);
        }
        if(convert(folder.getName()) != null) {
            ret.setName(convert(folder.getName()));
        }
        if(convert(folder.getUid()) != null) {
            ret.setUid(convert(folder.getUid()));
        }
        Set<Link> links = folder.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(folder.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(folder.getArchetypeDetails()));
        }
        if(convert(folder.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(folder.getFeederAudit()));
        }
        if(folder.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(folder.getArchetypeNodeId());
        }
        return ret;
    }

    // kernel class not found for org.openehr.schemas.v1.GENERIC_ENTRY

    public static GENERIC_ID convert(GenericID genericID) throws Exception {
        if(genericID == null) {
            return null;
        }
        GENERIC_ID ret = new GENERIC_ID();
        if(genericID.getScheme() != null) {
            ret.setScheme(genericID.getScheme());
        }
        if(new Token(genericID.getValue()) != null) {
            ret.setValue(new Token(genericID.getValue()));
        }
        return ret;
    }

    public static HIER_OBJECT_ID convert(HierObjectID hierObjectID) throws Exception {
        if(hierObjectID == null) {
            return null;
        }
        HIER_OBJECT_ID ret = new HIER_OBJECT_ID();
        if(new Token(hierObjectID.getValue()) != null) {
            ret.setValue(new Token(hierObjectID.getValue()));
        }
        return ret;
    }

    public static HISTORY convert(History history) throws Exception {
        if(history == null) {
            return null;
        }
        HISTORY ret = new HISTORY();
        if(convert(history.getOrigin()) != null) {
            ret.setOrigin(convert(history.getOrigin()));
        }
        if(convert(history.getPeriod()) != null) {
            ret.setPeriod(convert(history.getPeriod()));
        }
        if(convert(history.getDuration()) != null) {
            ret.setDuration(convert(history.getDuration()));
        }
        List<Event> events = history.getEvents();
        if(events != null) {
            EVENT[] _events = new EVENT[events.size()];
            int _events_idx = 0;
            for(Event e : events) {
                _events[_events_idx++] = convert(e);
            }
            ret.setEvents(_events);
        }
        if(convert(history.getSummary()) != null) {
            ret.setSummary(convert(history.getSummary()));
        }
        if(convert(history.getName()) != null) {
            ret.setName(convert(history.getName()));
        }
        if(convert(history.getUid()) != null) {
            ret.setUid(convert(history.getUid()));
        }
        Set<Link> links = history.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(history.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(history.getArchetypeDetails()));
        }
        if(convert(history.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(history.getFeederAudit()));
        }
        if(history.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(history.getArchetypeNodeId());
        }
        return ret;
    }

    public static IMPORTED_VERSION convert(ImportedVersion importedVersion) throws Exception {
        if(importedVersion == null) {
            return null;
        }
        IMPORTED_VERSION ret = new IMPORTED_VERSION();
        if(convert(importedVersion.getItem()) != null) {
            ret.setItem(convert(importedVersion.getItem()));
        }
        if(convert(importedVersion.getContribution()) != null) {
            ret.setContribution(convert(importedVersion.getContribution()));
        }
        if(convert(importedVersion.getCommitAudit()) != null) {
            ret.setCommit_audit(convert(importedVersion.getCommitAudit()));
        }
        if(importedVersion.getSignature() != null) {
            ret.setSignature(importedVersion.getSignature());
        }
        return ret;
    }

    public static INSTRUCTION convert(Instruction instruction) throws Exception {
        if(instruction == null) {
            return null;
        }
        INSTRUCTION ret = new INSTRUCTION();
        if(convert(instruction.getNarrative()) != null) {
            ret.setNarrative(convert(instruction.getNarrative()));
        }
        if(convert(instruction.getExpiryTime()) != null) {
            ret.setExpiry_time(convert(instruction.getExpiryTime()));
        }
        if(convert(instruction.getWfDefinition()) != null) {
            ret.setWf_definition(convert(instruction.getWfDefinition()));
        }
        List<Activity> activities = instruction.getActivities();
        if(activities != null) {
            ACTIVITY[] _activities = new ACTIVITY[activities.size()];
            int _activities_idx = 0;
            for(Activity a : activities) {
                _activities[_activities_idx++] = convert(a);
            }
            ret.setActivities(_activities);
        }
        if(convert(instruction.getProtocol()) != null) {
            ret.setProtocol(convert(instruction.getProtocol()));
        }
        if(convert(instruction.getGuidelineId()) != null) {
            ret.setGuideline_id(convert(instruction.getGuidelineId()));
        }
        if(convert(instruction.getLanguage()) != null) {
            ret.setLanguage(convert(instruction.getLanguage()));
        }
        if(convert(instruction.getEncoding()) != null) {
            ret.setEncoding(convert(instruction.getEncoding()));
        }
        if(convert(instruction.getSubject()) != null) {
            ret.setSubject(convert(instruction.getSubject()));
        }
        if(convert(instruction.getProvider()) != null) {
            ret.setProvider(convert(instruction.getProvider()));
        }
        List<Participation> otherParticipations = instruction.getOtherParticipations();
        if(otherParticipations != null) {
            PARTICIPATION[] _other_participations = new PARTICIPATION[otherParticipations.size()];
            int _other_participations_idx = 0;
            for(Participation p : otherParticipations) {
                _other_participations[_other_participations_idx++] = convert(p);
            }
            ret.setOther_participations(_other_participations);
        }
        if(convert(instruction.getWorkflowId()) != null) {
            ret.setWork_flow_id(convert(instruction.getWorkflowId()));
        }
        if(convert(instruction.getName()) != null) {
            ret.setName(convert(instruction.getName()));
        }
        if(convert(instruction.getUid()) != null) {
            ret.setUid(convert(instruction.getUid()));
        }
        Set<Link> links = instruction.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(instruction.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(instruction.getArchetypeDetails()));
        }
        if(convert(instruction.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(instruction.getFeederAudit()));
        }
        if(instruction.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(instruction.getArchetypeNodeId());
        }
        return ret;
    }

    public static INSTRUCTION_DETAILS convert(InstructionDetails instructionDetails) throws Exception {
        if(instructionDetails == null) {
            return null;
        }
        INSTRUCTION_DETAILS ret = new INSTRUCTION_DETAILS();
        if(convert(instructionDetails.getInstructionId()) != null) {
            ret.setInstruction_id(convert(instructionDetails.getInstructionId()));
        }
        if(instructionDetails.getActivityId() != null) {
            ret.setActivity_id(instructionDetails.getActivityId());
        }
        if(convert(instructionDetails.getWfDetails()) != null) {
            ret.setWf_details(convert(instructionDetails.getWfDetails()));
        }
        return ret;
    }

    public static INTERVAL_EVENT convert(IntervalEvent intervalEvent) throws Exception {
        if(intervalEvent == null) {
            return null;
        }
        INTERVAL_EVENT ret = new INTERVAL_EVENT();
        if(convert(intervalEvent.getWidth()) != null) {
            ret.setWidth(convert(intervalEvent.getWidth()));
        }
        ret.setSample_count(intervalEvent.getSampleCount());
        if(convert(intervalEvent.getMathFunction()) != null) {
            ret.setMath_function(convert(intervalEvent.getMathFunction()));
        }
        if(convert(intervalEvent.getTime()) != null) {
            ret.setTime(convert(intervalEvent.getTime()));
        }
        if(convert(intervalEvent.getData()) != null) {
            ret.setData(convert(intervalEvent.getData()));
        }
        if(convert(intervalEvent.getState()) != null) {
            ret.setState(convert(intervalEvent.getState()));
        }
        if(convert(intervalEvent.getName()) != null) {
            ret.setName(convert(intervalEvent.getName()));
        }
        if(convert(intervalEvent.getUid()) != null) {
            ret.setUid(convert(intervalEvent.getUid()));
        }
        Set<Link> links = intervalEvent.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(intervalEvent.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(intervalEvent.getArchetypeDetails()));
        }
        if(convert(intervalEvent.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(intervalEvent.getFeederAudit()));
        }
        if(intervalEvent.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(intervalEvent.getArchetypeNodeId());
        }
        return ret;
    }

    public static ISM_TRANSITION convert(ISMTransition iSMTransition) throws Exception {
        if(iSMTransition == null) {
            return null;
        }
        ISM_TRANSITION ret = new ISM_TRANSITION();
        if(convert(iSMTransition.getCurrentState()) != null) {
            ret.setCurrent_state(convert(iSMTransition.getCurrentState()));
        }
        if(convert(iSMTransition.getTransition()) != null) {
            ret.setTransition(convert(iSMTransition.getTransition()));
        }
        if(convert(iSMTransition.getCareflowStep()) != null) {
            ret.setCareflow_step(convert(iSMTransition.getCareflowStep()));
        }
        return ret;
    }

    public static ITEM_LIST convert(ItemList itemList) throws Exception {
        if(itemList == null) {
            return null;
        }
        ITEM_LIST ret = new ITEM_LIST();
        List<Element> items = itemList.getItems();
        if(items != null) {
            ELEMENT[] _items = new ELEMENT[items.size()];
            int _items_idx = 0;
            for(Element e : items) {
                _items[_items_idx++] = convert(e);
            }
            ret.setItems(_items);
        }
        if(convert(itemList.getName()) != null) {
            ret.setName(convert(itemList.getName()));
        }
        if(convert(itemList.getUid()) != null) {
            ret.setUid(convert(itemList.getUid()));
        }
        Set<Link> links = itemList.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(itemList.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(itemList.getArchetypeDetails()));
        }
        if(convert(itemList.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(itemList.getFeederAudit()));
        }
        if(itemList.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(itemList.getArchetypeNodeId());
        }
        return ret;
    }

    public static ITEM_SINGLE convert(ItemSingle itemSingle) throws Exception {
        if(itemSingle == null) {
            return null;
        }
        ITEM_SINGLE ret = new ITEM_SINGLE();
        if(convert(itemSingle.getItem()) != null) {
            ret.setItem(convert(itemSingle.getItem()));
        }
        if(convert(itemSingle.getName()) != null) {
            ret.setName(convert(itemSingle.getName()));
        }
        if(convert(itemSingle.getUid()) != null) {
            ret.setUid(convert(itemSingle.getUid()));
        }
        Set<Link> links = itemSingle.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(itemSingle.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(itemSingle.getArchetypeDetails()));
        }
        if(convert(itemSingle.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(itemSingle.getFeederAudit()));
        }
        if(itemSingle.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(itemSingle.getArchetypeNodeId());
        }
        return ret;
    }

    public static ITEM_TABLE convert(ItemTable itemTable) throws Exception {
        if(itemTable == null) {
            return null;
        }
        ITEM_TABLE ret = new ITEM_TABLE();
        List<Cluster> rows = itemTable.getRows();
        if(rows != null) {
            CLUSTER[] _rows = new CLUSTER[rows.size()];
            int _rows_idx = 0;
            for(Cluster c : rows) {
                _rows[_rows_idx++] = convert(c);
            }
            ret.setRows(_rows);
        }
        if(convert(itemTable.getName()) != null) {
            ret.setName(convert(itemTable.getName()));
        }
        if(convert(itemTable.getUid()) != null) {
            ret.setUid(convert(itemTable.getUid()));
        }
        Set<Link> links = itemTable.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(itemTable.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(itemTable.getArchetypeDetails()));
        }
        if(convert(itemTable.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(itemTable.getFeederAudit()));
        }
        if(itemTable.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(itemTable.getArchetypeNodeId());
        }
        return ret;
    }

    public static ITEM_TREE convert(ItemTree itemTree) throws Exception {
        if(itemTree == null) {
            return null;
        }
        ITEM_TREE ret = new ITEM_TREE();
        List<Item> items = itemTree.getItems();
        if(items != null) {
            ITEM[] _items = new ITEM[items.size()];
            int _items_idx = 0;
            for(Item i : items) {
                _items[_items_idx++] = convert(i);
            }
            ret.setItems(_items);
        }
        if(convert(itemTree.getName()) != null) {
            ret.setName(convert(itemTree.getName()));
        }
        if(convert(itemTree.getUid()) != null) {
            ret.setUid(convert(itemTree.getUid()));
        }
        Set<Link> links = itemTree.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(itemTree.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(itemTree.getArchetypeDetails()));
        }
        if(convert(itemTree.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(itemTree.getFeederAudit()));
        }
        if(itemTree.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(itemTree.getArchetypeNodeId());
        }
        return ret;
    }

    public static LINK convert(Link link) throws Exception {
        if(link == null) {
            return null;
        }
        LINK ret = new LINK();
        if(convert(link.getMeaning()) != null) {
            ret.setMeaning(convert(link.getMeaning()));
        }
        if(convert(link.getType()) != null) {
            ret.setType(convert(link.getType()));
        }
        if(convert(link.getTarget()) != null) {
            ret.setTarget(convert(link.getTarget()));
        }
        return ret;
    }

    public static LOCATABLE_REF convert(LocatableRef locatableRef) throws Exception {
        if(locatableRef == null) {
            return null;
        }
        LOCATABLE_REF ret = new LOCATABLE_REF();
        if(locatableRef.getPath() != null) {
            ret.setPath(locatableRef.getPath());
        }
        if(convert(locatableRef.getId()) != null) {
            ret.setId(convert(locatableRef.getId()));
        }
        if(new Token(locatableRef.getNamespace().toString()) != null) {
            ret.setNamespace(new Token(locatableRef.getNamespace().toString()));
        }
        if(new Token(locatableRef.getType().toString()) != null) {
            ret.setType(new Token(locatableRef.getType().toString()));
        }
        return ret;
    }

    public static OBJECT_REF convert(ObjectRef objectRef) throws Exception {
        if(objectRef == null) {
            return null;
        }
        OBJECT_REF ret = new OBJECT_REF();
        if(convert(objectRef.getId()) != null) {
            ret.setId(convert(objectRef.getId()));
        }
        if(new Token(objectRef.getNamespace().toString()) != null) {
            ret.setNamespace(new Token(objectRef.getNamespace().toString()));
        }
        if(new Token(objectRef.getType().toString()) != null) {
            ret.setType(new Token(objectRef.getType().toString()));
        }
        return ret;
    }

    public static OBJECT_VERSION_ID convert(ObjectVersionID objectVersionID) throws Exception {
        if(objectVersionID == null) {
            return null;
        }
        OBJECT_VERSION_ID ret = new OBJECT_VERSION_ID();
        if(new Token(objectVersionID.getValue()) != null) {
            ret.setValue(new Token(objectVersionID.getValue()));
        }
        return ret;
    }

    public static OBSERVATION convert(Observation observation) throws Exception {
        if(observation == null) {
            return null;
        }
        OBSERVATION ret = new OBSERVATION();
        if(convert(observation.getData()) != null) {
            ret.setData(convert(observation.getData()));
        }
        if(convert(observation.getState()) != null) {
            ret.setState(convert(observation.getState()));
        }
        if(convert(observation.getProtocol()) != null) {
            ret.setProtocol(convert(observation.getProtocol()));
        }
        if(convert(observation.getGuidelineId()) != null) {
            ret.setGuideline_id(convert(observation.getGuidelineId()));
        }
        if(convert(observation.getLanguage()) != null) {
            ret.setLanguage(convert(observation.getLanguage()));
        }
        if(convert(observation.getEncoding()) != null) {
            ret.setEncoding(convert(observation.getEncoding()));
        }
        if(convert(observation.getSubject()) != null) {
            ret.setSubject(convert(observation.getSubject()));
        }
        if(convert(observation.getProvider()) != null) {
            ret.setProvider(convert(observation.getProvider()));
        }
        List<Participation> otherParticipations = observation.getOtherParticipations();
        if(otherParticipations != null) {
            PARTICIPATION[] _other_participations = new PARTICIPATION[otherParticipations.size()];
            int _other_participations_idx = 0;
            for(Participation p : otherParticipations) {
                _other_participations[_other_participations_idx++] = convert(p);
            }
            ret.setOther_participations(_other_participations);
        }
        if(convert(observation.getWorkflowId()) != null) {
            ret.setWork_flow_id(convert(observation.getWorkflowId()));
        }
        if(convert(observation.getName()) != null) {
            ret.setName(convert(observation.getName()));
        }
        if(convert(observation.getUid()) != null) {
            ret.setUid(convert(observation.getUid()));
        }
        Set<Link> links = observation.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(observation.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(observation.getArchetypeDetails()));
        }
        if(convert(observation.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(observation.getFeederAudit()));
        }
        if(observation.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(observation.getArchetypeNodeId());
        }
        return ret;
    }

    public static ORIGINAL_VERSION convert(OriginalVersion originalVersion) throws Exception {
        if(originalVersion == null) {
            return null;
        }
        ORIGINAL_VERSION ret = new ORIGINAL_VERSION();
        if(convert(originalVersion.getUid()) != null) {
            ret.setUid(convert(originalVersion.getUid()));
        }
        if(originalVersion.getData() != null) {
            ret.setData(originalVersion.getData());
        }
        if(convert(originalVersion.getPrecedingVersionUid()) != null) {
            ret.setPreceding_version_uid(convert(originalVersion.getPrecedingVersionUid()));
        }
        Set<ObjectVersionID> otherInputVersionUids = originalVersion.getOtherInputVersionUids();
        if(otherInputVersionUids != null) {
            OBJECT_VERSION_ID[] _other_input_version_uids = new OBJECT_VERSION_ID[otherInputVersionUids.size()];
            int _other_input_version_uids_idx = 0;
            for(ObjectVersionID o : otherInputVersionUids) {
                _other_input_version_uids[_other_input_version_uids_idx++] = convert(o);
            }
            ret.setOther_input_version_uids(_other_input_version_uids);
        }
        List<Attestation> attestations = originalVersion.getAttestations();
        if(attestations != null) {
            ATTESTATION[] _attestations = new ATTESTATION[attestations.size()];
            int _attestations_idx = 0;
            for(Attestation a : attestations) {
                _attestations[_attestations_idx++] = convert(a);
            }
            ret.setAttestations(_attestations);
        }
        if(convert(originalVersion.getLifecycleState()) != null) {
            ret.setLifecycle_state(convert(originalVersion.getLifecycleState()));
        }
        if(convert(originalVersion.getContribution()) != null) {
            ret.setContribution(convert(originalVersion.getContribution()));
        }
        if(convert(originalVersion.getCommitAudit()) != null) {
            ret.setCommit_audit(convert(originalVersion.getCommitAudit()));
        }
        if(originalVersion.getSignature() != null) {
            ret.setSignature(originalVersion.getSignature());
        }
        return ret;
    }

    public static PARTICIPATION convert(Participation participation) throws Exception {
        if(participation == null) {
            return null;
        }
        PARTICIPATION ret = new PARTICIPATION();
        if(convert(participation.getFunction()) != null) {
            ret.setFunction(convert(participation.getFunction()));
        }
        if(convert(participation.getPerformer()) != null) {
            ret.setPerformer(convert(participation.getPerformer()));
        }
        if(convert(participation.getTime()) != null) {
            ret.setTime(convert(participation.getTime()));
        }
        if(convert(participation.getMode()) != null) {
            ret.setMode(convert(participation.getMode()));
        }
        return ret;
    }

    public static PARTY_IDENTIFIED convert(PartyIdentified partyIdentified) throws Exception {
        if(partyIdentified == null) {
            return null;
        }
        PARTY_IDENTIFIED ret = new PARTY_IDENTIFIED();
        if(partyIdentified.getName() != null) {
            ret.setName(partyIdentified.getName());
        }
        List<DvIdentifier> identifiers = partyIdentified.getIdentifiers();
        if(identifiers != null) {
            DV_IDENTIFIER[] _identifiers = new DV_IDENTIFIER[identifiers.size()];
            int _identifiers_idx = 0;
            for(DvIdentifier d : identifiers) {
                _identifiers[_identifiers_idx++] = convert(d);
            }
            ret.setIdentifiers(_identifiers);
        }
        if(convert(partyIdentified.getExternalRef()) != null) {
            ret.setExternal_ref(convert(partyIdentified.getExternalRef()));
        }
        return ret;
    }

    public static PARTY_REF convert(PartyRef partyRef) throws Exception {
        if(partyRef == null) {
            return null;
        }
        PARTY_REF ret = new PARTY_REF();
        if(convert(partyRef.getId()) != null) {
            ret.setId(convert(partyRef.getId()));
        }
        if(new Token(partyRef.getNamespace().toString()) != null) {
            ret.setNamespace(new Token(partyRef.getNamespace().toString()));
        }
        if(new Token(partyRef.getType().toString()) != null) {
            ret.setType(new Token(partyRef.getType().toString()));
        }
        return ret;
    }

    public static PARTY_RELATED convert(PartyRelated partyRelated) throws Exception {
        if(partyRelated == null) {
            return null;
        }
        PARTY_RELATED ret = new PARTY_RELATED();
        if(convert(partyRelated.getRelationship()) != null) {
            ret.setRelationship(convert(partyRelated.getRelationship()));
        }
        if(partyRelated.getName() != null) {
            ret.setName(partyRelated.getName());
        }
        List<DvIdentifier> identifiers = partyRelated.getIdentifiers();
        if(identifiers != null) {
            DV_IDENTIFIER[] _identifiers = new DV_IDENTIFIER[identifiers.size()];
            int _identifiers_idx = 0;
            for(DvIdentifier d : identifiers) {
                _identifiers[_identifiers_idx++] = convert(d);
            }
            ret.setIdentifiers(_identifiers);
        }
        if(convert(partyRelated.getExternalRef()) != null) {
            ret.setExternal_ref(convert(partyRelated.getExternalRef()));
        }
        return ret;
    }

    public static PARTY_SELF convert(PartySelf partySelf) throws Exception {
        if(partySelf == null) {
            return null;
        }
        PARTY_SELF ret = new PARTY_SELF();
        if(convert(partySelf.getExternalRef()) != null) {
            ret.setExternal_ref(convert(partySelf.getExternalRef()));
        }
        return ret;
    }

    public static POINT_EVENT convert(PointEvent pointEvent) throws Exception {
        if(pointEvent == null) {
            return null;
        }
        POINT_EVENT ret = new POINT_EVENT();
        if(convert(pointEvent.getTime()) != null) {
            ret.setTime(convert(pointEvent.getTime()));
        }
        if(convert(pointEvent.getData()) != null) {
            ret.setData(convert(pointEvent.getData()));
        }
        if(convert(pointEvent.getState()) != null) {
            ret.setState(convert(pointEvent.getState()));
        }
        if(convert(pointEvent.getName()) != null) {
            ret.setName(convert(pointEvent.getName()));
        }
        if(convert(pointEvent.getUid()) != null) {
            ret.setUid(convert(pointEvent.getUid()));
        }
        Set<Link> links = pointEvent.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(pointEvent.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(pointEvent.getArchetypeDetails()));
        }
        if(convert(pointEvent.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(pointEvent.getFeederAudit()));
        }
        if(pointEvent.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(pointEvent.getArchetypeNodeId());
        }
        return ret;
    }

    public static REFERENCE_RANGE convert(ReferenceRange referenceRange) throws Exception {
        if(referenceRange == null) {
            return null;
        }
        REFERENCE_RANGE ret = new REFERENCE_RANGE();
        if(convert(referenceRange.getMeaning()) != null) {
            ret.setMeaning(convert(referenceRange.getMeaning()));
        }
        if(convert(referenceRange.getRange()) != null) {
            ret.setRange(convert(referenceRange.getRange()));
        }
        return ret;
    }

    public static REVISION_HISTORY_ITEM convert(RevisionHistoryItem revisionHistoryItem) throws Exception {
        if(revisionHistoryItem == null) {
            return null;
        }
        REVISION_HISTORY_ITEM ret = new REVISION_HISTORY_ITEM();
        if(convert(revisionHistoryItem.getVersionId()) != null) {
            ret.setVersion_id(convert(revisionHistoryItem.getVersionId()));
        }
        List<AuditDetails> audits = revisionHistoryItem.getAudits();
        if(audits != null) {
            AUDIT_DETAILS[] _audits = new AUDIT_DETAILS[audits.size()];
            int _audits_idx = 0;
            for(AuditDetails a : audits) {
                _audits[_audits_idx++] = convert(a);
            }
            ret.setAudits(_audits);
        }
        return ret;
    }

    public static SECTION convert(Section section) throws Exception {
        if(section == null) {
            return null;
        }
        SECTION ret = new SECTION();
        List<ContentItem> items = section.getItems();
        if(items != null) {
            CONTENT_ITEM[] _items = new CONTENT_ITEM[items.size()];
            int _items_idx = 0;
            for(ContentItem c : items) {
                _items[_items_idx++] = convert(c);
            }
            ret.setItems(_items);
        }
        if(convert(section.getName()) != null) {
            ret.setName(convert(section.getName()));
        }
        if(convert(section.getUid()) != null) {
            ret.setUid(convert(section.getUid()));
        }
        Set<Link> links = section.getLinks();
        if(links != null) {
            LINK[] _links = new LINK[links.size()];
            int _links_idx = 0;
            for(Link l : links) {
                _links[_links_idx++] = convert(l);
            }
            ret.setLinks(_links);
        }
        if(convert(section.getArchetypeDetails()) != null) {
            ret.setArchetype_details(convert(section.getArchetypeDetails()));
        }
        if(convert(section.getFeederAudit()) != null) {
            ret.setFeeder_audit(convert(section.getFeederAudit()));
        }
        if(section.getArchetypeNodeId() != null) {
            ret.setArchetype_node_id(section.getArchetypeNodeId());
        }
        return ret;
    }

    public static TEMPLATE_ID convert(TemplateID templateID) throws Exception {
        if(templateID == null) {
            return null;
        }
        TEMPLATE_ID ret = new TEMPLATE_ID();
        if(new Token(templateID.getValue()) != null) {
            ret.setValue(new Token(templateID.getValue()));
        }
        return ret;
    }

    public static TERMINOLOGY_ID convert(TerminologyID terminologyID) throws Exception {
        if(terminologyID == null) {
            return null;
        }
        TERMINOLOGY_ID ret = new TERMINOLOGY_ID();
        if(new Token(terminologyID.getValue()) != null) {
            ret.setValue(new Token(terminologyID.getValue()));
        }
        return ret;
    }

    public static TERM_MAPPING convert(TermMapping termMapping) throws Exception {
        if(termMapping == null) {
            return null;
        }
        TERM_MAPPING ret = new TERM_MAPPING();
        if(termMapping.getMatch().toString() != null) {
            ret.setMatch(termMapping.getMatch().toString());
        }
        if(convert(termMapping.getPurpose()) != null) {
            ret.setPurpose(convert(termMapping.getPurpose()));
        }
        if(convert(termMapping.getTarget()) != null) {
            ret.setTarget(convert(termMapping.getTarget()));
        }
        return ret;
    }

    // kernel class not found for org.openehr.schemas.v1.X_VERSIONED_OBJECT

    public static CARE_ENTRY convert(CareEntry careEntry) throws Exception {
        if(careEntry == null) {
            return null;
        }
        if(careEntry instanceof Action) {
            return convert((Action) careEntry);
        } else if(careEntry instanceof Evaluation) {
            return convert((Evaluation) careEntry);
        } else if(careEntry instanceof Instruction) {
            return convert((Instruction) careEntry);
        } else if(careEntry instanceof Observation) {
            return convert((Observation) careEntry);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static CONTENT_ITEM convert(ContentItem contentItem) throws Exception {
        if(contentItem == null) {
            return null;
        }
        if(contentItem instanceof AdminEntry) {
            return convert((AdminEntry) contentItem);
        } else if(contentItem instanceof Action) {
            return convert((Action) contentItem);
        } else if(contentItem instanceof Evaluation) {
            return convert((Evaluation) contentItem);
        } else if(contentItem instanceof Instruction) {
            return convert((Instruction) contentItem);
        } else if(contentItem instanceof Observation) {
            return convert((Observation) contentItem);
        } else if(contentItem instanceof Section) {
            return convert((Section) contentItem);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static DATA_VALUE convert(DataValue dataValue) throws Exception {
        if(dataValue == null) {
            return null;
        }
        if(dataValue instanceof DvBoolean) {
            return convert((DvBoolean) dataValue);
        } else if(dataValue instanceof DvMultimedia) {
            return convert((DvMultimedia) dataValue);
        } else if(dataValue instanceof DvParsable) {
            return convert((DvParsable) dataValue);
        } else if(dataValue instanceof DvIdentifier) {
            return convert((DvIdentifier) dataValue);
        } else if(dataValue instanceof DvInterval) {
            return convert((DvInterval) dataValue);
        } else if(dataValue instanceof DvOrdinal) {
            return convert((DvOrdinal) dataValue);
        } else if(dataValue instanceof DvAmount) {
            return convert((DvAmount) dataValue);
        } else if(dataValue instanceof DvTemporal) {
            return convert((DvTemporal) dataValue);
        } else if(dataValue instanceof DvParagraph) {
            return convert((DvParagraph) dataValue);
        } else if(dataValue instanceof DvState) {
            return convert((DvState) dataValue);
        } else if(dataValue instanceof DvText) {
            return convert((DvText) dataValue);
        } else if(dataValue instanceof DvGeneralTimeSpecification) {
            return convert((DvGeneralTimeSpecification) dataValue);
        } else if(dataValue instanceof DvPeriodicTimeSpecification) {
            return convert((DvPeriodicTimeSpecification) dataValue);
        } else if(dataValue instanceof DvURI) {
            return convert((DvURI) dataValue);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static DV_AMOUNT convert(DvAmount dvAmount) throws Exception {
        if(dvAmount == null) {
            return null;
        }
        if(dvAmount instanceof DvCount) {
            return convert((DvCount) dvAmount);
        } else if(dvAmount instanceof DvDuration) {
            return convert((DvDuration) dvAmount);
        } else if(dvAmount instanceof DvProportion) {
            return convert((DvProportion) dvAmount);
        } else if(dvAmount instanceof DvQuantity) {
            return convert((DvQuantity) dvAmount);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static DV_ENCAPSULATED convert(DvEncapsulated dvEncapsulated) throws Exception {
        if(dvEncapsulated == null) {
            return null;
        }
        if(dvEncapsulated instanceof DvMultimedia) {
            return convert((DvMultimedia) dvEncapsulated);
        } else if(dvEncapsulated instanceof DvParsable) {
            return convert((DvParsable) dvEncapsulated);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static DV_ORDERED convert(DvOrdered dvOrdered) throws Exception {
        if(dvOrdered == null) {
            return null;
        }
        if(dvOrdered instanceof DvOrdinal) {
            return convert((DvOrdinal) dvOrdered);
        } else if(dvOrdered instanceof DvAmount) {
            return convert((DvAmount) dvOrdered);
        } else if(dvOrdered instanceof DvTemporal) {
            return convert((DvTemporal) dvOrdered);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static DV_QUANTIFIED convert(DvQuantified dvQuantified) throws Exception {
        if(dvQuantified == null) {
            return null;
        }
        if(dvQuantified instanceof DvAmount) {
            return convert((DvAmount) dvQuantified);
        } else if(dvQuantified instanceof DvTemporal) {
            return convert((DvTemporal) dvQuantified);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static DV_TEMPORAL convert(DvTemporal dvTemporal) throws Exception {
        if(dvTemporal == null) {
            return null;
        }
        if(dvTemporal instanceof DvDate) {
            return convert((DvDate) dvTemporal);
        } else if(dvTemporal instanceof DvDateTime) {
            return convert((DvDateTime) dvTemporal);
        } else if(dvTemporal instanceof DvTime) {
            return convert((DvTime) dvTemporal);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static DV_TIME_SPECIFICATION convert(DvTimeSpecification dvTimeSpecification) throws Exception {
        if(dvTimeSpecification == null) {
            return null;
        }
        if(dvTimeSpecification instanceof DvGeneralTimeSpecification) {
            return convert((DvGeneralTimeSpecification) dvTimeSpecification);
        } else if(dvTimeSpecification instanceof DvPeriodicTimeSpecification) {
            return convert((DvPeriodicTimeSpecification) dvTimeSpecification);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static ENTRY convert(Entry entry) throws Exception {
        if(entry == null) {
            return null;
        }
        if(entry instanceof AdminEntry) {
            return convert((AdminEntry) entry);
        } else if(entry instanceof Action) {
            return convert((Action) entry);
        } else if(entry instanceof Evaluation) {
            return convert((Evaluation) entry);
        } else if(entry instanceof Instruction) {
            return convert((Instruction) entry);
        } else if(entry instanceof Observation) {
            return convert((Observation) entry);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static EVENT convert(Event event) throws Exception {
        if(event == null) {
            return null;
        }
        if(event instanceof IntervalEvent) {
            return convert((IntervalEvent) event);
        } else if(event instanceof PointEvent) {
            return convert((PointEvent) event);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static ITEM convert(Item item) throws Exception {
        if(item == null) {
            return null;
        }
        if(item instanceof Cluster) {
            return convert((Cluster) item);
        } else if(item instanceof Element) {
            return convert((Element) item);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static ITEM_STRUCTURE convert(ItemStructure itemStructure) throws Exception {
        if(itemStructure == null) {
            return null;
        }
        if(itemStructure instanceof ItemList) {
            return convert((ItemList) itemStructure);
        } else if(itemStructure instanceof ItemSingle) {
            return convert((ItemSingle) itemStructure);
        } else if(itemStructure instanceof ItemTable) {
            return convert((ItemTable) itemStructure);
        } else if(itemStructure instanceof ItemTree) {
            return convert((ItemTree) itemStructure);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static LOCATABLE convert(Locatable locatable) throws Exception {
        if(locatable == null) {
            return null;
        }
        if(locatable instanceof Activity) {
            return convert((Activity) locatable);
        } else if(locatable instanceof Composition) {
            return convert((Composition) locatable);
        } else if(locatable instanceof AdminEntry) {
            return convert((AdminEntry) locatable);
        } else if(locatable instanceof Action) {
            return convert((Action) locatable);
        } else if(locatable instanceof Evaluation) {
            return convert((Evaluation) locatable);
        } else if(locatable instanceof Instruction) {
            return convert((Instruction) locatable);
        } else if(locatable instanceof Observation) {
            return convert((Observation) locatable);
        } else if(locatable instanceof Section) {
            return convert((Section) locatable);
        } else if(locatable instanceof IntervalEvent) {
            return convert((IntervalEvent) locatable);
        } else if(locatable instanceof PointEvent) {
            return convert((PointEvent) locatable);
        } else if(locatable instanceof Folder) {
            return convert((Folder) locatable);
        } else if(locatable instanceof History) {
            return convert((History) locatable);
        } else if(locatable instanceof Cluster) {
            return convert((Cluster) locatable);
        } else if(locatable instanceof Element) {
            return convert((Element) locatable);
        } else if(locatable instanceof ItemList) {
            return convert((ItemList) locatable);
        } else if(locatable instanceof ItemSingle) {
            return convert((ItemSingle) locatable);
        } else if(locatable instanceof ItemTable) {
            return convert((ItemTable) locatable);
        } else if(locatable instanceof ItemTree) {
            return convert((ItemTree) locatable);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static OBJECT_ID convert(ObjectID objectID) throws Exception {
        if(objectID == null) {
            return null;
        }
        if(objectID instanceof ArchetypeID) {
            return convert((ArchetypeID) objectID);
        } else if(objectID instanceof GenericID) {
            return convert((GenericID) objectID);
        } else if(objectID instanceof TemplateID) {
            return convert((TemplateID) objectID);
        } else if(objectID instanceof TerminologyID) {
            return convert((TerminologyID) objectID);
        } else if(objectID instanceof UIDBasedID) {
            return convert((UIDBasedID) objectID);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static PARTY_PROXY convert(PartyProxy partyProxy) throws Exception {
        if(partyProxy == null) {
            return null;
        }
        if(partyProxy instanceof PartyIdentified) {
            return convert((PartyIdentified) partyProxy);
        } else if(partyProxy instanceof PartySelf) {
            return convert((PartySelf) partyProxy);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static UID_BASED_ID convert(UIDBasedID uIDBasedID) throws Exception {
        if(uIDBasedID == null) {
            return null;
        }
        if(uIDBasedID instanceof HierObjectID) {
            return convert((HierObjectID) uIDBasedID);
        } else if(uIDBasedID instanceof ObjectVersionID) {
            return convert((ObjectVersionID) uIDBasedID);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

    public static VERSION convert(Version version) throws Exception {
        if(version == null) {
            return null;
        }
        if(version instanceof ImportedVersion) {
            return convert((ImportedVersion) version);
        } else if(version instanceof OriginalVersion) {
            return convert((OriginalVersion) version);
        }
        throw new IllegalArgumentException("unknown or un-supported type");
    }

}
