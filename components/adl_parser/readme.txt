Java ADL Parser
===============

VERSION
        Current version 1.0.1, 04/25/2005

DESCRIPTION
	  This is a Java implementation of a parser that can read openEHR 
	  archetypes in Archetype Definition Language (ADL) format and 
	  output a object tree in the form of Archetype Object Model (AOM). 
	  It also provides some validation of archetypes after parsing.

STATUS
        Based on Archetype Definition Language, Archetype Object Model
        and other related Information Models from openEHR release 0.95.

	  The target specifications are ADL from openEHR release 0.95. It is 
	  so far the only open source pure Java ADL parser and seamlessly 
	  integrated with the current openEHR Java kernel (ref_kernel_java 
	  project).

        The work is still in progress. The intention is to have complete
        implementation of a Java parser that can parse archetypes in ADL text
        and output instances of archetype object model with proper validation
        for the content of the archetypes.

AUTHOR
        Original Author: Rong Chen, Acode HB, Sweden <http://www.acode.se>

COPYRIGHT
        The software, including all files in this directory and subdirectories
        is copyrighted to Acode HB, Sweden, 2004,2005. All Rights Reserved.

LICENSE
        The softwrare is subject to GNU General Public License as published by
        the Free Software Foundation; either version 2 of the License, or
        (at your option) any later version. You may obtain a copy of the
        License at http://www.gnu.org/licenses/gpl.txt

ACKNOWLEDGEMENT
	  This work has been carried out with great helps from the guys at openEHR, 
	  Ocean Informatics and CHIME at UCL. Thomas Beale, in particular, has been 
	  providing guidances and suggestions along the way. His Eiffel parser, which 
	  BNF production rules are directly included in the ADL specification is an 
	  excellent start point for this Java parser.

BUILD
        The build script requires JDK and Maven to run. It has been tested with
        J2SDK 1.5.0 and Maven 1.0.2 on both Redhat 8 and Window 2000.

        To compile the code and run unit tests, type "maven javacc test".

        To generate the jar file, type "maven javacc jar".

	  See documentation/build.txt for details.

USAGE
	  See documentation/usage.txt.
        See unit tests for example of usages.
