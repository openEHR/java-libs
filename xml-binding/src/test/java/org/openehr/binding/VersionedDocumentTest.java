package org.openehr.binding;

import org.openehr.schemas.v1.VersionDocument;

public class VersionedDocumentTest extends XMLBindingTestBase {
	
	public void testParsingVersionedDocument() throws Exception {
		VersionDocument vd = 
			VersionDocument.Factory.parse(
					fromClasspath("original_version_002.xml"));				
	}
}
