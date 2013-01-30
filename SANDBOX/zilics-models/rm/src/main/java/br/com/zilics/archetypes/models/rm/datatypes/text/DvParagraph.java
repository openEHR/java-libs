
package br.com.zilics.archetypes.models.rm.datatypes.text;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;

/**
 * A logical composite text value consisting of a series of text.
 *
 * @author Humberto
 */
public class DvParagraph extends DataValue {

	private static final long serialVersionUID = 7020109011677084634L;
	@NotEmpty
	@EqualsField
	private List<DvText> items;

    /**
     * Get the list of items
     * @return Items making up the paragraph, each of which is a text item(which may have
     * its own formatting, and/or have hyperlinks).
     */
    public List<DvText> getItems() {
        return getList(items);
    }

    /**
     * Set the list of items
     * @param items Items making up the paragraph, each of which is a text item(which may have
     * its own formatting, and/or have hyperlinks).
     */
    public void setItems(List<DvText> items) {
		assertMutable();
        this.items = items;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
    	StringBuilder sb = new StringBuilder();
    	for (DvText textItem : items) {
    		sb.append(textItem).append(" - ");
    	}
    	return sb.toString();
    }

}
