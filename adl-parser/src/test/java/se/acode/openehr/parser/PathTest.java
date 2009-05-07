package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CAttribute;

/**
 * Test case tests archetype path logic.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PathTest extends ParserTestBase {

    public PathTest(String test) {
        super(test);
    }

    public void setUp() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-car.paths.test.adl"));
        archetype = parser.parse();
        definition = archetype.getDefinition();
    }

    public void testPath() throws Exception {

        // root path CAR
        assertEquals("root", "/", definition.path());

        // wheels attribute
        CAttribute wheels = definition.getAttributes().get(0);
        assertEquals("wheels", "/wheels", wheels.path());
        
        // first WHEEL node
        CObject firstWheel = wheels.getChildren().get(0);
        assertEquals("first wheel", "/wheels[at0001]",
                firstWheel.path());

        // description and parts of first WHEEL
        CComplexObject firstWheelObj = (CComplexObject) firstWheel;
        CAttribute description = firstWheelObj.getAttributes().get(0);
        assertEquals("first wheel description",
                "/wheels[at0001]/description", description.path());
        CAttribute parts = firstWheelObj.getAttributes().get(1);
        assertEquals("first wheel parts",
                "/wheels[at0001]/parts", parts.path());

        // WHEEL_PART node
        CObject wheelParts = parts.getChildren().get(0);
        assertEquals("wheelPart", "/wheels[at0001]/parts[at0002]",
                wheelParts.path());

        // something and something_else of WHEEL_PART node
        CComplexObject wheelPartsObj = (CComplexObject) wheelParts;
        assertEquals("something of WHEEL_PART",
                "/wheels[at0001]/parts[at0002]/something",
                wheelPartsObj.getAttributes().get(0).path());

        assertEquals("something_else of WHEEL_PART",
                "/wheels[at0001]/parts[at0002]/something_else",
                wheelPartsObj.getAttributes().get(1).path());
    }
    
    public void testNodeAtPath() throws Exception {
    	String[] paths = {
    			"/", 
    			"/wheels[at0001]", 
    			"/wheels[at0001]/description",
    			"/wheels[at0001]/parts[at0002]",
    			"/wheels[at0001]/parts[at0002]/something",
    			"/wheels[at0001]/parts[at0002]/something_else",
    			"/wheels[at0003]", 
    			"/wheels[at0003]/description",
    			"/wheels[at0004]",
    			"/wheels[at0004]/description",
    			"/wheels[at0005]", 
    			"/wheels[at0005]/description"
    	};
    	
    	CAttribute wheels = definition.getAttributes().get(0);
    	CComplexObject wheel1 = ((CComplexObject) wheels.getChildren().get(0));
    	CComplexObject wheel2 = ((CComplexObject) wheels.getChildren().get(1));
    	CComplexObject wheel3 = ((CComplexObject) wheels.getChildren().get(2));
    	CComplexObject wheel4 = ((CComplexObject) wheels.getChildren().get(3));
    	CComplexObject parts = ((CComplexObject) wheel1.getAttributes().get(1)
    								.getChildren().get(0));   			
    	
    	
    	CObject[] nodes = {
    			definition, 
    			wheel1,
    			wheel1.getAttributes().get(0).getChildren().get(0),
    			parts,
    			parts.getAttributes().get(0).getChildren().get(0),
    			parts.getAttributes().get(1).getChildren().get(0),
    			wheel2,
    			wheel2.getAttributes().get(0).getChildren().get(0),
    			wheel3,
    			wheel3.getAttributes().get(0).getChildren().get(0),
    			wheel4,
    			wheel4.getAttributes().get(0).getChildren().get(0),    			
    	};
    			
    	for(int i = 0; i < paths.length; i++) {
    		assertEquals("wrong at path: " + paths[i], nodes[i], 
    				archetype.node(paths[i]));
    	}
    }

    private Archetype archetype;
    private CComplexObject definition;
}
