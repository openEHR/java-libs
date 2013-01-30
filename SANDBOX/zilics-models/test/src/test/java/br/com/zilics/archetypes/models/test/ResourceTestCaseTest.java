
package br.com.zilics.archetypes.models.test;

import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Humberto
 */
public class ResourceTestCaseTest extends ResourceTestCase {
    public ResourceTestCaseTest(String testName) {
        super(testName);
    }

    public void testListDirectory() throws Exception {
        HashSet<String> expected = new HashSet<String>();
        expected.add("file1");
        expected.add("file2");
        expected.add("file3");
        HashSet<String> obtained = new HashSet<String>();
        obtained.addAll(listDirectory("/dir1"));
        assertEquals(expected, obtained);
    }

    public void testListDirectoryInJar() throws Exception {
        List<String> obtained = listDirectory("/junit/framework/");
        assertTrue(obtained.contains("TestCase.class"));
    }

    public void testGetBean() throws Exception {
        assertEquals("Oi", getJavaBean("/testbeans/bean1.xml"));
    }

}
