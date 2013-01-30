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

import edu.stanford.smi.protegex.owl.model.OWLUnionClass;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import java.util.Iterator;
import java.util.List;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;

/**
 *
 * @author Leonardo Lezcano
 */
public abstract class ItemStructTranslator extends Translator
{
    String supClsName;
    public ItemStructTranslator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTrans)
    {
        super(arc, model, co, superTrans);
    }

    @Override
    public void Translate()
    {
        this.coOWLCls = this.model.SpecializeClass(this.supClsName);        
        this.SetDefName();        
        List<CAttribute> atributes = ((CComplexObject)co).getAttributes();
        CAttribute temp=null;
        for (Iterator it = atributes.iterator(); it.hasNext();)
          {
              temp=(CAttribute)it.next();
              if(temp.getRmAttributeName().equals("items"))
              {
                  List<CObject> lcobj=temp.getChildren();
                  CObject cobj;
                  OWLUnionClass unionClass = this.model.owlModel.createOWLUnionClass();
                  for(Iterator ait=lcobj.iterator();ait.hasNext();)
                  {
                      cobj=(CObject)ait.next();
                      if(cobj instanceof CComplexObject) 
                      {
                          if(cobj.getRmTypeName().equals("ELEMENT"))
                          {
                               ElementTranslator emt=new ElementTranslator(arc, model, (CComplexObject)cobj, this);
                               emt.Translate();
                               unionClass.addOperand(emt.GetResult());
                               continue;
                          }
                          if(cobj.getRmTypeName().equals("CLUSTER"))
                          {
                              ClusterTranslator clut=new ClusterTranslator(arc, model, (CComplexObject)cobj, this);
                              clut.Translate();
                              unionClass.addOperand(clut.GetResult());                              
                          }
                      }                      
                  } 
                  this.model.SetAllValuesRest(this.coOWLCls, "dsrm:items", unionClass);
              }            
          }
    }

}
