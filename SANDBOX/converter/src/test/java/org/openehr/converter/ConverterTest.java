
package org.openehr.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import junit.framework.TestCase;

/**
 *
 * @author Humberto
 */
public class ConverterTest extends TestCase {
    public ConverterTest(String testName) {
        super(testName);
    }

    private List<String> listDirectory(String directoryName) throws IOException {
        URL url = this.getClass().getResource(directoryName);
        String urlFile = URLDecoder.decode(url.getFile(), "UTF-8");
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
            ArrayList<String> result = new ArrayList<String>();
            while(entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (name.startsWith(pathPreffix)) {
                    name = name.substring(pathPreffix.length());
                    if (name.indexOf('/') < 0 && name.length() > 0)
                        result.add(name);
                }
            }
            return result;
        } else {
            File file = new File(url.getFile());
            return Arrays.asList(file.list());
        }
    }
    
    public void testConvertAdlToXml() throws Exception {
        for(String fileName : listDirectory("/adl/")) {
            InputStream is = this.getClass().getResourceAsStream("/adl/" + fileName);
            OutputStream os = new FileOutputStream("target/" + fileName.substring(0, fileName.length() - 4) + ".xml");
            Converter.convertAdlToXml(is, os);
        }
    }
}
