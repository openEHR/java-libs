Java ADL Parser
===============

VERSION
        Current version 1.0.5, October 21, 2006

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


Installation
============
You need to have j2sdk1.5 and Maven 1.0 (or Maven 2.0) to be able to build 
the parser from source code. If your build machine is online, Maven can 
download relevant libraries (see project.xml for dependencies). Otherwise, 
you will need to download them manually and put it in your local Maven 
repository. You also need to solve the dependency towards the openEHR java 
kernel.


Build with Maven 1.0
====================
Type "maven javacc test" to compile and test the code.

Type "maven javacc jar:install" to install the parser jar file into local Maven
repository, but you can also find it under "target".

Type "maven javacc javadoc" to generate java API docs under "target/docs".


Build with Maven 2.0
====================
Clean build and test: "mvn clean test"

Generate complete jar with dependencies: "mvn package"

Build and install the jar locally: "mvn install"

Test single test case, e.g. "mvn test -Dtest=UnicodeSupportTest"Usage
=====
1. One-time use
You can create the parser for one time use and throw away the instance. The
parser constructor can either take a ADL file name or a String which contains
the archetype in ADL format.

1) Initialize the parser instance:
   File adlFile = new File("my_archetype.adl");
   ADLParser parser = new ADLParser(adlFile);
or
   String adlText = ...; // probably loaded from database
   ADLParser parser = new ADLParser(adlText);

2) Then call parser.parse() to get the Archetype instance


2. Multiple-use
You can also keep the same parser instance after the first use and ask it to
parse more archetypes.

1) Same as above to get the parser instance _and_ parse the first archetype

2) From the _second_ archetype, you need to call the following method:

   parser.reInit(File  adlFile)
or
   parser.reInit(String adlText)

3) Then call parser.parse() to get the Archetype instance.

3. Command Line Interface
The parser has a command line interface built mainly to provide a interactive
way for validating archetypes. Just include all the dependent libraries and
type the following.

java se.acode.openehr.parser.ADLParser [adlFile]


Implementation
==============
The parser is implemented with javaCC (http://javacc.dev.java.net/), most of BNF
production rules are very much like the ones on the specification. But since
JavaCC generates top-down (recursive descent) parsers (unlike YACC-like tools)
and left-recursion is strictly forbidden, some of rules have be rewritten to
work around this limitation. That's where most of the time has been spent and
errors could be introduced unfortunately.
