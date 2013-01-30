
package br.com.zilics.archetypes.models.test;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import junit.framework.TestCase;

/**
 * Abstract test case with JavaBeans capabilities
 *
 * <p>This class, has 2 useful methods:
 * <ul>
 *   <li>{@link #listDirectory(java.lang.String)}</li>
 *   <li>{@link #getJavaBean(java.lang.String)}</li>
 * </ul>
 * </p>
 * @author Humberto
 */
public abstract class ResourceTestCase extends TestCase {

    protected Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * Simple constructor
     */
    public ResourceTestCase() {
        super();
    }

    /**
     * Constructor specifying the test name
     * @param testName testName
     */
    public ResourceTestCase(String testName) {
        super(testName);
    }

    /**
     * List all files at a specific directory, using
     * the current {@link java.lang.ClassLoader} for finding that resource (subdirectories not included)
     * @param directoryName the directory
     * @return list of file names in that directory
     * @throws java.io.IOException exception throw in case of missing directory
     */
    public List<String> listDirectory(String directoryName) throws IOException {
        return listDirectory(this.getClass(), directoryName);
    }

    /**
     * List all files at a specific directory, using
     * the {@link java.lang.ClassLoader} of that {@link java.lang.Class} for finding that resource (subdirectories not included)
     * @param directoryName the directory
     * @param clazz {@link java.lang.Class} owning that {@link java.lang.ClassLoader}
     * @return list of file names in that directory
     * @throws java.io.IOException exception throw in case of missing directory
     */
    public static List<String> listDirectory(Class<?> clazz, String directoryName) throws IOException {
        URL url = clazz.getResource(directoryName);
        String urlFile = URLDecoder.decode(url.getFile(), "UTF-8");
        ArrayList<String> result = new ArrayList<String>();
        if (urlFile.indexOf('!') >= 0) {
            String pathPreffix = urlFile.substring(urlFile.indexOf('!') + 1);
            if (pathPreffix.startsWith("/"))
                pathPreffix = pathPreffix.substring(1);
            urlFile = urlFile.substring(0, urlFile.indexOf('!'));
            urlFile = urlFile.replaceAll("file:", "");
            //urlFile = urlFile.replaceAll("file:\\/", "");
            //System.out.println(urlFile);
            JarFile module = new JarFile(urlFile);
            Enumeration<JarEntry> entries = module.entries();
            while(entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if(!entry.isDirectory()){
                    String name = entry.getName();
                    if (name.startsWith(pathPreffix)) {
                        name = name.substring(pathPreffix.length());
                        if (name.indexOf('/') < 0 && name.length() > 0)
                            result.add(name);
                    }
                }
            }
            return result;
        } else {
            File file = new File(url.getFile());
            for(File f : file.listFiles()){
            	if(!f.isDirectory()){
            		result.add(f.getName());
            	}
            }
            return result;
        }
    }

    /**
     * Decodes a resource into a JavaBean
     * @param resourceName the name of the resource
     * @return the JavaBean decoded
     */
    public Object getJavaBean(String resourceName) {
        return getJavaBean(this.getClass(), resourceName);
    }

    /**
     * Decodes a resource into a JavaBean
     * @param resourceName the name of the resource
     * @param clazz {@link java.lang.Class} owning a {@link java.lang.ClassLoader} from
     * wich we get the resource
     * @return the JavaBean decoded
     */
    public static Object getJavaBean(Class<?> clazz, String resourceName) {
        return getJavaBean(clazz.getResourceAsStream(resourceName));
    }

    /**
     * Decodes a {@link java.io.InputStream} into a JavaBean
     * @param is the stream to decode
     * @return the JavaBean decoded
     */
    public static Object getJavaBean(InputStream is) {
        XMLDecoder xmlDecoder = new XMLDecoder(is);
        return xmlDecoder.readObject();
    }

}



