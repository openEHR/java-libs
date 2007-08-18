package org.openehr.tutorial.answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openehr.tutorial.util.*;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.PartyRef;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.tutorial.EHRExercise;

/**
 * Answers to the coding exercise on EHR classes
 * 
 * @author rong.chen
 */
public class EHRAnswer extends EHRExercise {

	/**
	 * Creates a simple observation with data
	 */
	public Observation createObservation() {
		String archetypeId = "openEHR-EHR-OBSERVATION.laboratory-glucose.v1";
		DvText meaning = new DvText("blood glucose");
		Archetyped arch = new Archetyped(new ArchetypeID(archetypeId), "1.1");
		PartySelf subject = new PartySelf(new PartyRef(new HierObjectID(
				"1.2.4.5.6.12.1"), ObjectRef.Type.PARTY));
		PartyRef performer = new PartyRef(new HierObjectID("1.3.3.1.2.42.1"),
				ObjectRef.Type.ORGANISATION);
		PartyIdentified provider = new PartyIdentified(performer, "Dr Chen",
				null);

		History<ItemStructure> data = new DataStructureAnswer().createHistory();

		return new Observation(archetypeId, meaning, arch,
				TestTerminologyAccess.ENGLISH, TestTerminologyAccess.LATIN_1,
				subject, provider, data, termServ);
	}

	/**
	 * Create a simple composition with single observation
	 */
	public Composition createComposition() {
		UIDBasedID id = new HierObjectID("1.11.2.3.4.5.0");
		String archetypeId = "openEHR-EHR-COMPOSITION.encounter.v1";
        String archetypeNodeId = archetypeId;
        
        // make it diff from others
        DvText name = new DvText("Encounter");
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
        		archetypeId), "1.0");
        FeederAudit feederAudit = null;
        Set<Link> links = null;
        Locatable parent = null;
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(createObservation());
        
        CodePhrase language = TestTerminologyAccess.ENGLISH;
        DvDateTime startTime = new DvDateTime();
        EventContext context = new EventContext(null, startTime, null, null, 
        		null, TestCodeSetAccess.EVENT, null, termServ);      
         
        PartyRef performer = new PartyRef(new HierObjectID(
        		"1.3.3.1.2.42.1.199"), ObjectRef.Type.PERSON);
        PartyProxy composer =  new PartyIdentified(performer, "Dr Chen", null);
        DvCodedText category = TestCodeSetAccess.EVENT;
        CodePhrase territory = new CodePhrase("ISO_3166-1", "se");
        
        Composition composition = new Composition(id, archetypeNodeId, name, 
        		archetypeDetails, feederAudit, links, parent, content,
                language, context, composer, category, territory, termServ);
        return composition; 
	}
}
