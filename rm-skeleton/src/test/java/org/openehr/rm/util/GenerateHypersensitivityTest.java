package org.openehr.rm.util;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openehr.am.archetype.Archetype;

public class GenerateHypersensitivityTest extends SkeletonGeneratorTestBase {

	public GenerateHypersensitivityTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public void testGenerateSimpleHypersensitivity() throws Exception {
		Archetype archetype = loadArchetype("openEHR-EHR-EVALUATION.hypersensitivity.v1.adl");
		assertNotNull(archetype);
		
		Object obj = generator.create(archetype, null, archetypeMap,
				GenerationStrategy.MAXIMUM);
		
		List<String> lines = dadlBinding.toDADL(obj);
		
		FileUtils.writeLines(new File("hypersensitivity_max.dadl"), lines);
		
		generator.create(archetype, null, archetypeMap,
				GenerationStrategy.MINIMUM);
		
		lines = dadlBinding.toDADL(obj);		
		FileUtils.writeLines(new File("hypersensitivity_min.dadl"), lines);
	}
}
