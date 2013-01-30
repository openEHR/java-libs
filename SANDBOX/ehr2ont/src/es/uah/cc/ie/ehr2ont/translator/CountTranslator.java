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
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;
import edu.stanford.smi.protegex.owl.model.RDFSDatatypeFactory;
import edu.stanford.smi.protegex.owl.model.RDFSLiteral;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import java.util.Iterator;
import java.util.List;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CPrimitiveObject;
import org.openehr.am.archetype.constraintmodel.primitive.CInteger;
import org.openehr.am.archetype.constraintmodel.primitive.CPrimitive;
import org.openehr.rm.support.basic.Interval;

/**
 *
 * @author Leonardo Lezcano
 */
public class CountTranslator extends Translator
{
    public CountTranslator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTrans)
    {
        super(arc, model, co, superTrans);
    }

    @Override
    public void Translate()
    {
        this.coOWLCls = this.model.SpecializeClass("dtrm:DV_COUNT");
        this.SetDefName();
        
        List<CAttribute> atributes = ((CComplexObject)co).getAttributes();
        CAttribute temp=null;
        for (Iterator it = atributes.iterator(); it.hasNext();)
        {
              temp=(CAttribute)it.next();
              if(temp.getRmAttributeName().equals("magnitude"))
              {
                  List<CObject> lcobj=temp.getChildren();
                  CObject cobj;
                  for(Iterator ait=lcobj.iterator();ait.hasNext();)
                  {
                      cobj=(CObject)ait.next();
                      if(cobj instanceof CPrimitiveObject)
                      {
                          CPrimitive mag = ((CPrimitiveObject)cobj).getItem();
                          //System.out.println(mag.getClass().toString());
                          if(mag instanceof CInteger)
                          {
                              Interval itvl = ((CInteger)mag).getInterval();
//                              System.out.println(itvl.getLower().getClass().toString());
//                              System.out.println(itvl.getLower().toString());
//                              System.out.println(itvl.getUpper().getClass().toString());
//                              System.out.println(itvl.getUpper().toString());
                              
                              RDFSDatatype countData = this.model.SpecializeDatatype(this.model.owlModel.getXSDint(), itvl);
                              this.model.SetAllValuesRest(this.coOWLCls,"dtrm:magnitude", countData);
                          }
                      }
                  }
              }
        }
    }

}
