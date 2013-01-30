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

/**
 * Useful methods to manage archetypes through OpenEHR API
 */
import java.util.Iterator;
import java.util.List;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyDefinitions;

public class ArchetypeUtils 
{
    public static String idiom = "en";
    
     public static String getTermDefinitionFor(String isoLanguageCode,
            String id, Archetype a){
           ArchetypeOntology ao = a.getOntology();
           List<OntologyDefinitions> odefs = ao.getTermDefinitionsList();
           Iterator it = odefs.iterator();
           while (it.hasNext()){
               OntologyDefinitions od = (OntologyDefinitions)it.next();    
               if (od.getLanguage().equals(isoLanguageCode)){
                   List<ArchetypeTerm> aterms = od.getDefinitions();
                   Iterator it2 = aterms.iterator();
                   while (it2.hasNext()){
                       ArchetypeTerm at =(ArchetypeTerm)it2.next();
                       if (at.getCode().equals(id)){
                           return at.getItem("text");
                       }
                           
                   }
               }
               
           }           
           return "";
        }
     
     public static String getConstraintDefinitionFor(String isoLanguageCode,
            String id, Archetype a){
           ArchetypeOntology ao = a.getOntology();
           List<OntologyDefinitions> odefs = ao.getConstraintDefinitionsList();
           Iterator it = odefs.iterator();
           while (it.hasNext()){
               OntologyDefinitions od = (OntologyDefinitions)it.next();    
               if (od.getLanguage().equals(isoLanguageCode)){
                   List<ArchetypeTerm> aterms = od.getDefinitions();
                   Iterator it2 = aterms.iterator();
                   while (it2.hasNext()){
                       ArchetypeTerm at =(ArchetypeTerm)it2.next();
                       if (at.getCode().equals(id)){
                           return at.getItem("text");
                       }
                           
                   }
               }
               
           }           
           return "";
        }
     
     
     /**
      * Extracts node ID from the entire path or node address.
      */
     public static String PathID(ArchetypeInternalRef ar)
    {
        String total=ar.getTargetPath();
        int i=total.length()-1;
        String aux="";
        boolean flag=false;
        while(i>0)
        {
            if((char)total.charAt(i)==']')
            {
                flag=true;
                i--;
                continue;
            }
            if((char)total.charAt(i)=='[')
                break;            
            if(flag)
                aux=(char)total.charAt(i) + aux;                     
            i--;  
        }
        return aux;
    }
     
     

}
