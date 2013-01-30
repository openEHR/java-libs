/*
 * component:   "openEHR Java Reference Implementation"
 * description: "Class RMBindingTest"
 * keywords:    "XML binding test"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2008 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.binding;

import java.io.InputStream;

import org.openehr.rm.common.changecontrol.OriginalVersion;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.schemas.v1.ORIGINALVERSION;
import org.openehr.schemas.v1.VERSION;
import org.openehr.schemas.v1.VersionDocument;

import junit.framework.TestCase;

public class RMBindingTest extends TestCase {

	public void setUp() throws Exception {
		binding = new XMLBinding();
	}
	
	public void testParseAndBindToOriginalVersion() throws Exception {
		VERSION xobj = VersionDocument.Factory.parse(
				fromClasspath("original_version_002.xml")).getVersion();
		
		assertTrue("expected originial_version, but got: " 
				+ (xobj == null ? null : xobj.getClass()),
				xobj instanceof ORIGINALVERSION);

		Object rmObj = binding.bindToRM(xobj);
		
		assertTrue("rmObj not a OriginalVersion, got "
				+ (rmObj == null ? null : rmObj.getClass()),
				rmObj instanceof OriginalVersion);
		
		OriginalVersion orgVer = (OriginalVersion) rmObj;
		
		Composition comp = (Composition) orgVer.getData();
		Section section = (Section) comp.getContent().get(0);
		
		assertEquals("section.items.size wrong", 1, section.getItems().size());
		
		Observation obser = (Observation) section.getItems().get(0);
		
		assertNotNull("observation is null", obser);
		
		assertEquals("openEHR-EHR-OBSERVATION.laboratory-lipids.v1",
				obser.getArchetypeNodeId());
	}

	private InputStream fromClasspath(String filename) throws Exception {
		return this.getClass().getClassLoader().getResourceAsStream(filename);
	}
	
	private XMLBinding binding;
}
/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is RMBindingTest.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2008 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */