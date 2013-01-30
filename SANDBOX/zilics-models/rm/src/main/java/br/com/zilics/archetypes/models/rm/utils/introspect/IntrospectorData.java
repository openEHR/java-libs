package br.com.zilics.archetypes.models.rm.utils.introspect;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.zilics.archetypes.models.rm.RMObject;

/**
 * The BIG SUPER cache of all {@link RmClassData} of all descendants of {@link RMObject}
 * IMPORTANT: If you add a new {@link RMObject} class, please add it here too!!!
 * @author Humberto Naves
 *
 */
public final class IntrospectorData implements Serializable {
	private static final long serialVersionUID = -6695896262485162882L;

	private static final Map<Class<?>, RmClassData> allClasses = new HashMap<Class<?>, RmClassData>();
	private static final Map<String, RmClassData> allClassesByName = new HashMap<String, RmClassData>();
	private static final Map<Class<?>, RmClassData> dynamicLoadedClasses = new HashMap<Class<?>, RmClassData>();

	/**
	 * Initializes the cache...
	 * IMPORTANT: If you add a new {@link RMObject} class, please add it here too!!!
	 */
	static {
		try {
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.Archetype.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.assertion.Assertion.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.assertion.AssertionVariable.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.assertion.ExpressionBinaryOperator.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.assertion.ExpressionItem.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.assertion.ExpressionLeaf.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.assertion.ExpressionOperator.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.assertion.ExpressionUnaryOperator.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeConstraint.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeInternalRef.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.ArchetypeSlot.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.Cardinality.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CAttribute.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CComplexObject.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CDefinedObject.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CDomainType.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CMultipleAttribute.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.ConstraintRef.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CPrimitiveObject.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CReferenceObject.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.CSingleAttribute.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CBoolean.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CDate.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CDateTime.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CDuration.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CInteger.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CPrimitive.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CReal.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CString.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive.CTime.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.ontology.ArchetypeOntology.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.ontology.ArchetypeTerm.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.ontology.CodeDefinitionSet.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.ontology.ConstraintBindingItem.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.ontology.ConstraintBindingSet.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.ontology.TermBindingItem.class);
		processJavaClass(br.com.zilics.archetypes.models.am.archetype.ontology.TermBindingSet.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic.CDvState.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic.NonTerminalState.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic.State.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic.StateMachine.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic.TerminalState.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic.Transition.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CDvOrdinal.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CDvQuantity.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity.CQuantityItem.class);
		processJavaClass(br.com.zilics.archetypes.models.am.openehrprofile.datatypes.text.CCodePhrase.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateAction.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateAdminEntry.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateCluster.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateComposition.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateContentItem.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateElement.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateEntrySubtype.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateEvaluation.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateInstruction.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateItemTree.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.Template.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateLocatable.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateObservation.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateSection.class);
		processJavaClass(br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateStatement.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.archetyped.Archetyped.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.archetyped.FeederAuditDetails.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.archetyped.FeederAudit.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.archetyped.Link.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.archetyped.Locatable.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.archetyped.Pathable.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.changecontrol.Contribution.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.changecontrol.ImportedVersion.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.changecontrol.OriginalVersion.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.changecontrol.VersionedObject.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.changecontrol.Version.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.directory.Folder.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.directory.VersionedFolder.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.Attestation.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.AuditDetails.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.Participation.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.PartyIdentified.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.PartyProxy.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.PartyRelated.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.PartySelf.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.RevisionHistoryItem.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.generic.RevisionHistory.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.resource.AuthoredResource.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.resource.ResourceDescriptionItem.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.resource.ResourceDescription.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.common.resource.TranslationDetails.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.Composition.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.ContentItem.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.Action.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.Activity.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.AdminEntry.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.CareEntry.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.Entry.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.Evaluation.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.InstructionDetails.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.Instruction.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.ISMTransition.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.entry.Observation.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.content.navigation.Section.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.composition.EventContext.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.DataStructure.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.history.Event.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.history.History.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.history.IntervalEvent.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.history.PointEvent.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemList.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemSingle.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemTable.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemTree.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Cluster.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Element.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation.Item.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.basic.DvBoolean.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.basic.DvIdentifier.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.basic.DvState.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvEncapsulated.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvMultimedia.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvParsable.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDate.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDuration.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvTemporal.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvTime.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvAbsoluteQuantity.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvAmount.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvCount.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvInterval.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvOrdered.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvOrdinal.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvProportion.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantified.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.quantity.ReferenceRange.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.text.DvParagraph.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.text.DvText.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.text.TermMapping.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.timespecification.DvGeneralTimeSpecification.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.timespecification.DvPeriodicTimeSpecification.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.timespecification.DvTimeSpecification.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.uri.DvEHRURI.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.datatypes.uri.DvURI.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.basic.Interval.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.AccessGroupRef.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.ArchetypeID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.GenericID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.HierObjectID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.InternetID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.ISO_OID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.LocatableRef.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.ObjectID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.ObjectRef.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.ObjectVersionID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.PartyRef.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.TemplateID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.TerminologyID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.UIDBasedID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.UID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.UUID.class);
		processJavaClass(br.com.zilics.archetypes.models.rm.support.identification.VersionTreeID.class);
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	private IntrospectorData() {}

	/**
	 * Process a Java class and put it on the cache.
	 * @param javaClass the java class to process
	 */
	private static void processJavaClass(Class<?> javaClass) {
		if (!RMObject.class.isAssignableFrom(javaClass))
			throw new IllegalArgumentException("Class is not a subclass of RMObject: " + javaClass);
		
		// If it was already processed
		if (allClasses.containsKey(javaClass)) return;

		// Before adding the new class, first add the superclass.
		if (javaClass != RMObject.class) {
			processJavaClass(javaClass.getSuperclass());
		}
		
		RmClassData rmClassData = new RmClassData(javaClass, allClasses.get(javaClass.getSuperclass()));
		// Put it on the cache
		allClasses.put(javaClass, rmClassData);
		
		// Check for repeated official names
		if (allClassesByName.containsKey(rmClassData.getRmClassName()))
			throw new IllegalArgumentException("Invalid class " + javaClass + ": name repeated - " + rmClassData.getRmClassName());
		allClassesByName.put(rmClassData.getRmClassName(), rmClassData);
	}
	
	/**
	 * Dynamic load a class (useful for test cases) and put it on a separated cache 
	 * @param javaClass the javaClass to process
	 * @return the Intropector data
	 */
	private static synchronized RmClassData getDynamicLoadedRmClassData(Class<?> javaClass) {
		if (!dynamicLoadedClasses.containsKey(javaClass)) {
			RmClassData rmClassData = new RmClassData(javaClass, getRmClassDataByJavaClass(javaClass.getSuperclass()));
			dynamicLoadedClasses.put(javaClass, rmClassData);
		}
		return dynamicLoadedClasses.get(javaClass);
	}

	/**
	 * Return the Introspector data of a class
 	 * @param javaClass the class to get the {@link RmClassData}
	 * @return the resulting data
	 */
	public static RmClassData getRmClassDataByJavaClass(Class<?> javaClass) {
		if (allClasses.containsKey(javaClass))
			return allClasses.get(javaClass);
		if (RMObject.class.isAssignableFrom(javaClass))
			return getDynamicLoadedRmClassData(javaClass);
		return null;
	}

	/**
	 * Return the Introspector data of a class by its official name
	 * @param rmClassName the official name of the class
	 * @return the resulting data
	 */
	public static RmClassData getRmClassDataByRmClassName(String rmClassName) {
		if (rmClassName.indexOf('<') != -1)
			rmClassName = rmClassName.substring(0, rmClassName.indexOf('<'));
		return allClassesByName.get(rmClassName);
	}

	/**
	 * Return a Java class by its official name
	 * @param rmClassName the official name
	 * @return the resulting java class
	 */
	public static Class<?> getJavaClassByRmClassName(String rmClassName) {
		RmClassData rmClassData = getRmClassDataByRmClassName(rmClassName);
		if (rmClassData == null) return null;
		return rmClassData.getJavaClass();
	}

	/**
	 * Get all the {@link RmClassData} that are known to this cache (excluding the dynamic loaded classes)
	 * @return the resulting collection
	 */
	public static Collection<RmClassData> getAllRmClassData() {
		return Collections.unmodifiableCollection(allClassesByName.values());
	}
}
