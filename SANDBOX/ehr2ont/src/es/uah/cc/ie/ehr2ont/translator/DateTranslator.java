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

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.primitive.CDate;
import org.openehr.am.archetype.constraintmodel.primitive.CInteger;
import org.openehr.am.archetype.constraintmodel.primitive.CPrimitive;
import org.openehr.rm.support.basic.Interval;

/**
 *
 * @author Leonardo Lezcano
 */
public class DateTranslator extends Translator
{
    public DateTranslator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTrans)
    {
        super(arc, model, co, superTrans);
    }

    @Override
    public void Translate()
    {
        this.coOWLCls = this.model.SpecializeClass("dtrm:DV_DATE");
        this.SetDefName();
        
        List<CAttribute> atributes = ((CComplexObject)co).getAttributes();
        CAttribute temp=null;
        for (Iterator it = atributes.iterator(); it.hasNext();)
        {
              temp=(CAttribute)it.next();
              if(temp.getRmAttributeName().equals("value"))
              {
                  List<CObject> lcobj=temp.getChildren();
                  CObject cobj;
                  for(Iterator ait=lcobj.iterator();ait.hasNext();)
                  {
                      cobj=(CObject)ait.next();
                      if(cobj instanceof CPrimitiveObject)
                      {
                          if(((CPrimitiveObject)cobj).getItem() instanceof CDate)
                          {
                              CDate dat=(CDate)(((CPrimitiveObject)cobj).getItem());
                              String patt1 = "yyyy-??-??";
                              if(patt1.equals(dat.getPattern()))
                              {
                                  Collection col = this.model.owlModel.getClsNameMatches("dtrm:DV_PARTIAL_DATE",1);
                                  OWLNamedClass partialDate = (OWLNamedClass)(col.iterator().next());                                  
                                  this.coOWLCls.addSuperclass(partialDate);
                              }
                              
                              //                          System.out.println(prim.getClass().toString());
                              //System.out.println(dat.getPattern());
                          }
                      }
                  }
              }
        }
        
    }

}
