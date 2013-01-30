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
 * @author Leonardo Lezcano
 */
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import java.util.Iterator;
import java.util.List;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.rm.datatypes.text.CodePhrase;

        
public class ElementTranslator extends Translator
{
    public ElementTranslator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTrans)
    {
        super(arc, model, co, superTrans);
    }
    
    public  void Translate()
    {
        this.coOWLCls = this.model.SpecializeClass("dsrm:ELEMENT");
        this.SetDefName();        
        
        List<CAttribute> atribute = ((CComplexObject)co).getAttributes();
        CAttribute temp=null;
        for (Iterator it = atribute.iterator(); it.hasNext();)
          {
              temp=(CAttribute)it.next();
              if(temp.getRmAttributeName().equals("value"))
              {
                  List<CObject> lcobj=temp.getChildren();
                  CObject cobj;
                  for(Iterator ait=lcobj.iterator();ait.hasNext();)
                  {
                      cobj=(CObject)ait.next();
                      
                      if(cobj instanceof CComplexObject)
                      {
                          if(cobj.getRmTypeName().equals("DV_COUNT"))
                          {
                              CountTranslator cout = new CountTranslator(arc, model, cobj, this);
                              cout.Translate();
                              this.model.SetAllValuesRest(this.coOWLCls, "dsrm:value", cout.GetResult());                              
                              continue;                              
                          }
                          if(cobj.getRmTypeName().equals("DV_DATE"))
                          {
                              DateTranslator datt = new DateTranslator(arc, model, cobj, this);
                              datt.Translate();
                              this.model.SetAllValuesRest(this.coOWLCls, "dsrm:value", datt.GetResult());                                                            
                              continue;                              
                          }
                          
                          if(cobj.getRmTypeName().equals("DV_CODED_TEXT"))
                          {
                              CodedTextTranslator codtt = new CodedTextTranslator(arc, model, cobj, this);
                              codtt.Translate();
                              this.model.SetAllValuesRest(this.coOWLCls, "dsrm:value", codtt.GetResult());                                                            
                              continue;                              
                          }
                           
                      }
                       if (cobj instanceof CDvOrdinal)
                       {
                           OrdinalTranslator ordt = new OrdinalTranslator(arc, model, cobj, this);
                           ordt.Translate();
                           this.model.SetAllValuesRest(this.coOWLCls, "dsrm:value", ordt.GetResult());
                           continue;
                       }
                      if (cobj instanceof CDvQuantity)
                       {
                          QuantityTranslator quantt = new QuantityTranslator(arc, model, cobj, this);
                          quantt.Translate();
                          this.model.SetAllValuesRest(this.coOWLCls, "dsrm:value", quantt.GetResult());
                          continue;                      
                       }
                      
                            //System.out.println(cobj.getClass().toString());
//                          System.out.println(((CComplexObject)cobj).getRmTypeName());
//                          System.out.println(((CComplexObject)cobj).getNodeID());  
                      // <editor-fold defaultstate="collapsed" desc="Code in Development">                      
                      /*
                       else
                       {                          
                          if(cobj instanceof CComplexObject) // DV_COUNT translation
                          {
                              
                          }
                       }
                       */
                      // </editor-fold>
                  }
              }              
          }
    }

}
