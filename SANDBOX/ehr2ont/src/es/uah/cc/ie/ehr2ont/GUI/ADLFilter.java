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

package es.uah.cc.ie.ehr2ont.GUI;

/**
 * Filter for ADL file extensions
 *
 * @author Leonardo Lezcano
 */

import javax.swing.filechooser.*;
import java.io.File;

public class ADLFilter extends FileFilter { 

String description;
String extension;

public ADLFilter()
{
    this.description = "Archetype Definition Language File (ADL)";
    this.extension ="adl";    
}
public String getDescription() {
    return description;
}
// Ignore case, always accept directories
// Character before extension must be a period
public boolean accept(File file)
{
    if (file.isDirectory())
        return true;
    else
    {
        String path = file.getAbsolutePath().toLowerCase();
   
        if ((path.endsWith(extension) && (path.charAt(path.length()-extension.length()-1)) == '.'))
        return true;    
    }
    return false;
}
}
