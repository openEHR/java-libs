/*
 *CISEP - An intelligent clinical record to improve patient security
 *
 *Copyright (c) 2007-2008, Information Eng. Research Unit - Univ. of Alcalá
 *http://www.cc.uah.es/ie
 *
 *This library is free software; you can redistribute it and/or modify it under
 *the terms of the GNU Lesser General Public License as published by the Free
 *Software Foundation; either version 2.1 of the License, or (at your option)
 *any later version.
 *This library is distributed in the hope that it will be useful, but WITHOUT
 *ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 *details.
 *You should have received a copy of the GNU Lesser General Public License along
 *with this library; if not, write to the Free Software Foundation, Inc.,
 *59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package es.uah.cc.ie.ehr2ont.translator;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLHasValue;
import edu.stanford.smi.protegex.owl.model.OWLUnionClass;
import edu.stanford.smi.protegex.owl.model.RDFSLiteral;
import es.uah.cc.ie.ehr2ont.parser.ArchetypeUtils;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import java.util.Iterator;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;

/**
 *
 * @author Leonardo Lezcano
 */
public class OrdinalTranslator extends Translator
{
    public OrdinalTranslator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTrans) 
    {
        super(arc, model, co, superTrans);  
    }

    
    @Override
    public void Translate()
    {
        this.coOWLCls = this.model.SpecializeClass("dtrm:DV_ORDINAL");
        this.SetDefName();
        
        OWLUnionClass unionClass = this.model.owlModel.createOWLUnionClass();                          
        OWLDatatypeProperty prop = this.model.owlModel.getOWLDatatypeProperty("dtrm:value");
        OWLHasValue hasInt = null;
        RDFSLiteral val = null;
        String comm = "";
        String defName; 

        Ordinal ord=null;  
        for(Iterator i=((CDvOrdinal)this.co).getList().iterator();i.hasNext();)
        {
            // alternative way
            //OWLAllValuesFrom createOWLAllValuesFrom(RDFProperty property, RDFSLiteral[] oneOfValues)
            //------
            ord=(Ordinal)i.next();
            val = this.model.owlModel.createRDFSLiteral(ord.getValue());
            hasInt = this.model.owlModel.createOWLHasValue(prop, val);
            unionClass.addOperand(hasInt);
            defName = ArchetypeUtils.getTermDefinitionFor(ArchetypeUtils.idiom, ord.getSymbol().getCodeString(), this.arc);
            comm += Integer.toString(ord.getValue()) + " -> " + defName + ", ";
        }        
        this.coOWLCls.addSuperclass(unionClass);
        this.coOWLCls.addComment(comm);        
    }

}
