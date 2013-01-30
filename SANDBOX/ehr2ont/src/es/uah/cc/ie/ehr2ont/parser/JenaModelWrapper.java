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

package es.uah.cc.ie.ehr2ont.parser;

import com.hp.hpl.jena.util.FileUtils;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLAllValuesFrom;
import edu.stanford.smi.protegex.owl.model.OWLClass;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.OWLProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;
import edu.stanford.smi.protegex.owl.model.RDFSDatatypeFactory;
import edu.stanford.smi.protegex.owl.model.RDFSLiteral;
import edu.stanford.smi.protegex.owl.repository.impl.LocalFolderRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.rm.support.basic.Interval;

/**
 * This class encapsulates an original JenaOWLModel to provide easier access to
 * its functionality.
 *
 * @author Leonardo Lezcano
 */
public class JenaModelWrapper {
    
    // <editor-fold defaultstate="collapsed" desc="extending JenaOWLModel"> 
    //In another implementation this class could extend JenaOWLModel to get benefits
    // from protected resources and promote code reusability.
    /*
    extends JenaOWLModel
    public UAHJenaOWLModel(KnowledgeBaseFactory factory, NamespaceManager namespaceManager)
    {
        super(factory, namespaceManager);        
    }
    */
     // </editor-fold>
    
    public JenaOWLModel owlModel;
    public OWLDatatypeProperty idAnnot;
    
    public JenaModelWrapper(String directory, String owlFile)
    {        
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(owlFile));
        }catch(java.io.IOException e){
            System.out.println(e);
        }
        OWLModel owlModel = null;
        try {
            owlModel = ProtegeOWL.createJenaOWLModel();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LocalFolderRepository rep = new LocalFolderRepository(new File(directory));
        owlModel.getRepositoryManager().addGlobalRepository(rep);
         JenaOWLModel jenaModel = (JenaOWLModel)owlModel;
         try{
            reader = new BufferedReader(new FileReader(owlFile));
        }catch(java.io.IOException e){
            System.out.println(e);
        }
            try {
                jenaModel.load(reader, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        this.owlModel = (JenaOWLModel) owlModel;
        this.idAnnot = this.owlModel.createAnnotationOWLDatatypeProperty("NodeID");
        
    }
    
    public OWLNamedClass SpecializeClass(String parentName)
    {
       OWLNamedClass childClass = null;
       OWLNamedClass parentClass = null;
       Collection col = this.owlModel.getClsNameMatches(parentName,1);       
        
       if(col.isEmpty())
                System.out.println("class " + parentName + " does not exist");
        else
        {
            parentClass=(OWLNamedClass)(col.iterator().next());
            String childName = this.owlModel.createNewResourceName(parentClass.getLocalName());            
            childClass = this.owlModel.createOWLNamedSubclass(childName, parentClass);        
        }
       return childClass; 
    }
    
    public void SetAllValuesRest(OWLNamedClass restrictedCls, String property, RDFResource filler)
    {
        //We create an UnNamedClass that contains all instances that fulfill the restriction
        // and then we set it as a NamedClass superClass
//        OWLProperty prop = null;
//        if(filler instanceof RDFSDatatype)
//            prop = this.owlModel.getOWLDatatypeProperty(property);
//        else
//            prop = this.owlModel.getOWLObjectProperty(property);
        OWLProperty prop = this.owlModel.getOWLProperty(property);
        OWLAllValuesFrom rest = this.owlModel.createOWLAllValuesFrom(prop, filler);
        restrictedCls.addSuperclass(rest);        
    }
    
    public OWLNamedClass GetClsByPath(ArchetypeInternalRef pth)
    {
        Collection classes = this.owlModel.getUserDefinedOWLNamedClasses();
        OWLNamedClass cls = null;
        String id = ArchetypeUtils.PathID(pth);
        for (Iterator it = classes.iterator(); it.hasNext();)
        {
            cls = (OWLNamedClass) it.next();
            if(id.equals(cls.getPropertyValue(idAnnot)))
                return cls;            
        }
        return null;
    }
    
    public RDFSDatatype SpecializeDatatype(RDFSDatatype baseType, Interval itvl)
    {
        Object low = (Object)itvl.getLower();
        Object up =  (Object)itvl.getUpper();

        RDFSDatatypeFactory dataFact = this.owlModel.getRDFSDatatypeFactory();
        RDFSDatatype subData = dataFact.createAnonymousDatatype(baseType);
        RDFSLiteral lit = null;
        if(low != null)
        {
          lit = this.owlModel.createRDFSLiteral(low.toString(), baseType);
          if(itvl.isLowerIncluded())
              dataFact.setMinInclusive(subData, lit);
          else
              dataFact.setMinExclusive(subData, lit);
        }
        if(up != null)
        {
          lit = this.owlModel.createRDFSLiteral(up.toString(), baseType);
          if(itvl.isUpperIncluded())
              dataFact.setMaxInclusive(subData, lit);
          else
              dataFact.setMaxExclusive(subData, lit);
        }
        return subData;        
    }
    
    public void Save(String fileName)
    {        
        Collection errors = new ArrayList();        
        this.owlModel.save(new File(fileName).toURI(), FileUtils.langXMLAbbrev, errors);
        System.out.println("File saved with " + errors.size() + " errors.");
    }
  
}
