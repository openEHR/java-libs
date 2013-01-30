package br.com.zilics.archetypes.models.rm.support.identification;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Identifier for archetypes. Lexical form: rm_originator '-' rm_name '-'
 * rm_entity '.' concept_name { '-' specialisation }* '.v' number
 * 
 * @author Humberto
 */
@RmClass("ARCHETYPE_ID")
public class ArchetypeID extends ObjectID {

	private static final long serialVersionUID = 6861621309174210862L;
	/* static fields */
	private static final String AXIS_SEPARATOR = ".";
	private static final String SECTION_SEPARATOR = "-";

	
	private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9()_/%$#&]*");
	private static final Pattern VERSION_PATTERN = Pattern.compile("v[a-zA-Z0-9]+");

	/* fields */
	@Ignore
	private String qualifiedRmEntity;
	@Ignore
	private String rmOriginator;
	@Ignore
	private String rmName;
	@Ignore
	private String rmEntity;
	@Ignore
	private String domainConcept;
	@Ignore
	private String conceptName;
	@Ignore
	private String specialisation;
	@Ignore
	private String version;

	/**
	 * Default constructor
	 */
	public ArchetypeID() {}
	
	/**
	 * Another constructor
	 * @param value the value of this id
	 */
	public ArchetypeID(String value) {
		super(value);
	}

    /**
     * Globally qualified reference model entity,
     * eg "openehr-ehr_rm-entry"
     *
     * @return qualifiedRmEntity
     */
    public String qualifiedRmEntity() {
        return qualifiedRmEntity;
    }

    /**
     * Name of the concept represented by this archetype, including
     * specialisation, eg. "biochemistry result-cholesterol"
     *
     * @return domainConcept
     */
    public String domainConcept() {
        return domainConcept;
    }

    /**
     * Name of the concept represented by this archetype
     * without specialisation
     *
     * @return conceptName
     */
    public String conceptName() {
        return conceptName;
    }

    /**
     * Organisation originating the reference model on which this
     * archetype is based, eg "openehr", "cen", "hl7"
     *
     * @return rmOriginator
     */
    public String rmOriginator() {
        return rmOriginator;
    }

    /**
     * Name of the reference model, eg "rim", "ehr_rm", "en13606"
     *
     * @return rmName
     */
    public String rmName() {
        return rmName;
    }

    /**
     * Name of the ontological level within the reference model to
     * which this archetype is targeted, eg for openEHR, "folder",
     * "composition", "section", "entry"
     *
     * @return rmEntity
     */
    public String rmEntity() {
        return rmEntity;
    }

    /**
     * Name of specialisation of concept, if this archetype is a
     * specialisation of another archetype, eg "cholesterol"
     *
     * @return specialisation
     */
    public String specialisation() {
        return specialisation;
    }
    
    /**
     * Return version
     *
     * @return version
     */
    public String version() {
        return version;
    }
    
	
	
    /**
     * {@inheritDoc}
     */
    @Override
	protected void performValidation(ValidationResult result) {
		super.performValidation(result);

		String value = getValue();
    	if (value == null) return;

		StringTokenizer tokens = new StringTokenizer(value, AXIS_SEPARATOR, true);
		if (tokens.countTokens() != 5) {
			result.addItem(this, "Bad format, wrong number of \"" + AXIS_SEPARATOR + "\"");
			return;
		}
		qualifiedRmEntity = tokens.nextToken();
		tokens.nextToken();
		
		domainConcept = tokens.nextToken();
		tokens.nextToken();
		
		version = tokens.nextToken();

		tokens = new StringTokenizer(qualifiedRmEntity, SECTION_SEPARATOR, true);
		if (tokens.countTokens() != 5) {
			result.addItem(this, "Bad format, wrong number of \"" + SECTION_SEPARATOR + "\"");
			return;
		}
		rmOriginator = tokens.nextToken();
		tokens.nextToken();
		
		rmName = tokens.nextToken();
		tokens.nextToken();

		rmEntity = tokens.nextToken();

		tokens = new StringTokenizer(domainConcept, SECTION_SEPARATOR, true);
		if (tokens.countTokens() < 1) {
			result.addItem(this, "Bad format, too few sections for domainConcept");
			return;
		} else if ((tokens.countTokens() % 2) == 0) {
			result.addItem(this, "Bad format");
			return;
		}
		
		conceptName = tokens.nextToken();
		specialisation = null;
		while (tokens.hasMoreTokens()) {
			tokens.nextToken();
			specialisation = tokens.nextToken();
            validateName(specialisation, "specialisation", result);
		}
		validateAll(result);
	}
	
	
	
    // validate all axis and section values
    private void validateAll(ValidationResult result) {
        validateName(rmOriginator, "rm_originator", result);
        validateName(rmName, "rm_name", result);
        validateName(rmEntity, "rm_entity", result);
        validateName(conceptName, "concept_name", result);
        
        if (specialisation != null)
            validateName(specialisation, "specialisation", result);
        validateVersionID(version, result);
    }

    private void validateName(String value, String label, ValidationResult result) {
        if (!NAME_PATTERN.matcher(value).matches()) {
            result.addItem(this, "Wrong format of " + label + ": " + value);
        }
    }

    private void validateVersionID(String version, ValidationResult result) {
        if (!VERSION_PATTERN.matcher(version).matches()) {
            result.addItem(this, "Wrong format of versionID, " + version);
        }
    }
    

}
