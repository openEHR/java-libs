Java openEHR Implementation project
-----------------------------------

VERSION
        Current version 1.2

STATUS
        Based on openEHR target release 0.95 and implemented Reference Model -
        EHR, EHR Extract, Demographics, Common, Data Structures, Data Types
        and Support, and Archetype Object Model.

        Besides, support for archetypes based object creation has been added
        into Archetype Object Model classes.

        The work is still in progress. The intention is to have complete
        implementation of openEHR Reference Model and Archetype Object Model,
        with support for archetype based object creation and validation.

AUTHORS
        Rong Chen, Göran Pestana
        Acode HB, Sweden <http://www.acode.se>

COPYRIGHT
        The software, including all files in this directory and subdirectories
        is copyrighted to Acode HB, Sweden, 2004,2005. All Rights Reserved.

LICENSE
        The softwrare are subject to the Mozilla Public License Version
        1.1 (the 'License'); you may not use this file except in compliance with
        the License. You may obtain a copy of the License at
        http://www.mozilla.org/MPL/

        Alternatively, the contents of this file may be used under the terms of
        either the GNU General Public License Version 2 or later (the 'GPL'), or
        the GNU Lesser General Public License Version 2.1 or later (the 'LGPL'),
        in which case the provisions of the GPL or the LGPL are applicable instead
        of those above. If you wish to allow use of your version of this file only
        under the terms of either the GPL or the LGPL, and not to allow others to
        use your version of this file under the terms of the MPL, indicate your
        decision by deleting the provisions above and replace them with the notice
        and other provisions required by the GPL or the LGPL. If you do not delete
        the provisions above, a recipient may use your version of this file under
        the terms of any one of the MPL, the GPL or the LGPL.

ACKNOWLEDGEMENT
        Thank Thomas Beale for answering questions and good discussion during
        development of this software.

        Thank people from UCL for good collaboration and particulary Nathan Lee
        for authoring installation instructions.
        
DIRECTORY STRUCTURE OF THE REPOSITORY
		This repository is structured using the top-level structure defined at
		http://www.openehr.org/developer/d_svn_um_dir_struct.htm. Due to java's 
		needs, directory structures beneath the top level are fairly typical of
		recommended java structures.

BUILD
        The build script requires JDK and Maven to run. It has been tested with
        J2SDK 1.5.0 and Maven 1.0.2 on both Linux and Windows. It should work
        on other platforms with Java support as well.

        See install.txt for detailed installation instruction.
xx
