package br.com.zilics.archetypes.models.rm.datastructure.itemstructure.representation;

import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;

/**
 * The leaf variant of ITEM, to which a DATA_VALUE instance is attached.
 * @author Humberto
 */
public class Element extends Item {


	private static final long serialVersionUID = -2785147062335876240L;
	private DvCodedText nullFlavour;
    private DataValue value;


    /**
     * flavour of null value, like indeterminate, not asked etc
     * @return nullFlavour
     */
    public DvCodedText getNullFlavour() {
        return nullFlavour;
    }

    /**
     * data value of this leaf
     * @return value of this leaf
     */
    public DataValue getValue() {
        return value;
    }

    /**
     * Set the flavour of null value
     * @param nullFlavour The flavour of null value
     */
    public void setNullFlavour(DvCodedText nullFlavour) {
		assertMutable();
        this.nullFlavour = nullFlavour;
    }

    /**
     * Set the value of this leaf
     * @param value The value of this leaf
     */
    public void setValue(DataValue value) {
		assertMutable();
        this.value = value;
    }
}