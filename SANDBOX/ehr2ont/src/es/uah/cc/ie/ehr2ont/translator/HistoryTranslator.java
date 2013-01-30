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

/**
 *
 * @author Leonardo Lezcano & Rosmary Calzadilla
 */

import edu.stanford.smi.protegex.owl.model.OWLUnionClass;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import java.util.Iterator;
import java.util.List;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CMultipleAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;


public class HistoryTranslator extends Translator
{
        
    public HistoryTranslator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTrans)
    {
        super(arc, model, co, superTrans);
    }
    
    public void Translate()
    {       
        
        List<CAttribute> attributes = ((CComplexObject)co).getAttributes();
        CAttribute temp=null;
        for (Iterator it = attributes.iterator(); it.hasNext();)
          {
              temp=(CAttribute)it.next();            
              
              // <editor-fold defaultstate="collapsed" desc="DELETE"> 
              /////DELETE //////
              /*
              if(temp instanceof CMultipleAttribute)
              {
                 System.out.println("ES Multiple");
                  Cardinality car=((CMultipleAttribute)temp).getCardinality();
                 System.out.println(car.isUnique());
                 System.out.println(car.getInterval().getLower());
                 System.out.println(car.getInterval().getUpper());
              }
              */
              ///////////////
              // </editor-fold>
                           
              if(temp.getRmAttributeName().equals("events"))
              {
                  List<CObject> lcobj=temp.getChildren();
                  CObject cobj;
                  if(temp instanceof CMultipleAttribute && !((CMultipleAttribute)temp).getCardinality().isUnique())
                  {
                     this.coOWLCls = this.model.SpecializeClass("dsrm:EVENT_SERIES_T"); 
                     OWLUnionClass unionClass = this.model.owlModel.createOWLUnionClass();
                     for(Iterator ait=lcobj.iterator();ait.hasNext();)
                      {
                          cobj=(CObject)ait.next();
                          if(cobj instanceof CComplexObject && cobj.getRmTypeName().equals("EVENT"))
                          {
                               EventTranslator et=new EventTranslator(arc, model, (CComplexObject)cobj, this);
                               et.Translate();
                               unionClass.addOperand(et.GetResult());                           
                          } 
                      }
                      this.model.SetAllValuesRest(this.coOWLCls, "dsrm:items", unionClass);
                     
                  }
                  else
                  {
                      this.coOWLCls = this.model.SpecializeClass("dsrm:SINGLE_EVENT_T");
                      ///////////
                      //CODE SECTION IN DEVELOPMENT
                      ///////////
                  }
                  this.SetDefName();
              } 
          }
    }

}
