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

import edu.stanford.smi.protegex.owl.model.OWLAllValuesFrom;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLHasValue;
import edu.stanford.smi.protegex.owl.model.OWLIntersectionClass;
import edu.stanford.smi.protegex.owl.model.OWLUnionClass;
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;
import edu.stanford.smi.protegex.owl.model.RDFSLiteral;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import java.util.Iterator;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;

/**
 *
 * @author Leonardo Lezcano
 */

public class QuantityTranslator extends Translator
{
    public QuantityTranslator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTrans)
    {
        super(arc, model, co, superTrans);
    }

    @Override
    public void Translate()
    {
        this.coOWLCls = this.model.SpecializeClass("dtrm:DV_QUANTITY");
        this.SetDefName();
        
        
        OWLUnionClass unionClass = this.model.owlModel.createOWLUnionClass();
        OWLIntersectionClass intersectionClass;
        RDFSDatatype dtype;
        OWLDatatypeProperty propMag = this.model.owlModel.getOWLDatatypeProperty("dtrm:magnitude");
        OWLDatatypeProperty propUni = this.model.owlModel.getOWLDatatypeProperty("dtrm:units");
        OWLDatatypeProperty propPre = this.model.owlModel.getOWLDatatypeProperty("dtrm:precision");
        OWLAllValuesFrom rest;
        OWLHasValue hasInt;        
        RDFSLiteral val;
        CDvQuantity quant = (CDvQuantity)this.co;
        
        CDvQuantityItem quantItem=null;  
        for(Iterator i=quant.getList().iterator();i.hasNext();)
        {
            quantItem=(CDvQuantityItem)i.next();
             
            intersectionClass = this.model.owlModel.createOWLIntersectionClass();
            if(quantItem.getValue() != null)
            {
                dtype = this.model.SpecializeDatatype(this.model.owlModel.getXSDdouble(), quantItem.getValue());
                rest = this.model.owlModel.createOWLAllValuesFrom(propMag, dtype);
                intersectionClass.addOperand(rest);
            }
            
            if(quantItem.getUnits() != null)
            {
                val = this.model.owlModel.createRDFSLiteral(quantItem.getUnits());
                hasInt = this.model.owlModel.createOWLHasValue(propUni, val);
                intersectionClass.addOperand(hasInt);
            }
            if(quantItem.getPrecision() != null)
            {
                dtype = this.model.SpecializeDatatype(this.model.owlModel.getXSDint(), quantItem.getPrecision());
                rest = this.model.owlModel.createOWLAllValuesFrom(propPre, dtype);
                intersectionClass.addOperand(rest);
            }
            unionClass.addOperand(intersectionClass);            
        }
        this.coOWLCls.addSuperclass(unionClass);
        if(quant.getProperty() != null)
        this.coOWLCls.addComment(quant.getProperty().toString());
        
    }

}
