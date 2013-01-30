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
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import es.uah.cc.ie.ehr2ont.parser.ArchetypeUtils;
import es.uah.cc.ie.ehr2ont.parser.JenaModelWrapper;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CObject;

/**
 * This is the root class for translators’ tree. It generalizes naming process
 * for OWL classes, and defines Translate() method that should be implemented by
 * all translators. 
 */
public abstract class Translator
{
    Archetype arc; 
    CObject co;
    JenaModelWrapper model;
    //In order to avoid the same translation process to be repeted, below var
    //will store the translation resulting OWL Class    
    OWLNamedClass coOWLCls = null;
    Translator supTrans = null;   
    
   
    /**
     * Guarantees that “co” node is completely translated. To accomplish all 
     * inner nodes translation, corresponding translators are 
     * built and invoked before the end of this method. 
     * @param arc Points to the input archetype object that will be translated
     * @param model Points to an OWL ontology that will store traslation results
     * @param co Defines next node to be translated in the adl hierarchy 
     * @param superTranslator Back pointer to super Node translator.
     */
    public Translator(Archetype arc, JenaModelWrapper model, CObject co, Translator superTranslator)
    {
        this.arc = arc;
        this.model=model;
        this.co=co;
        this.supTrans = superTranslator; 
    }
    
    
    public abstract void Translate();
    
    
    public OWLNamedClass GetResult()
    {
        return this.coOWLCls;
    }
    
    protected void SetDefName()
    {
        //if(this.coCls == null)
            //throw new Exception....
        String defName = null;
        if(this.co.getNodeID() != null)
        {        
            this.coOWLCls.setPropertyValue(this.model.idAnnot, this.co.getNodeID());
            defName = ArchetypeUtils.getTermDefinitionFor(ArchetypeUtils.idiom, co.getNodeID(), arc);            
            defName = defName.replace(' ', '_');
        }
        else
        {
            // below sentence could be replaced by above block using this.supTrans.co.getNodeID()
            defName = this.supTrans.coOWLCls.getLocalName();
            defName += "_" + this.coOWLCls.getFirstSuperclass().getLocalName();
        }                
        if(defName != null && this.model.owlModel.isValidResourceName(defName, (RDFResource)coOWLCls))
            coOWLCls.setName(defName);
        else
        {
            //Fill with Archetype Concept and SuperClass Name concatenation
            //this.model.owlModel.createNewResourceName(parentClass.getLocalName());
        }        
    }          

}
