
package org.openehr.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.serialize.XMLSerializer;
import se.acode.openehr.parser.ADLParser;

/**
 *
 * @author Humberto
 */
public final class Converter {
    public static void convertAdlToXml(InputStream adlStream, OutputStream xmlStream) throws Exception {
        ADLParser parser = new ADLParser(adlStream, true, true);        
        XMLSerializer serializer = new XMLSerializer();
        Archetype archetype = parser.parse();
        serializer.output(archetype, xmlStream);
    }
    
    private static File getDirectory(String directoryName) throws Exception {
        File directory = new File(directoryName);
        if (!directory.isDirectory()) {
            System.err.printf("`%s' is not a directory\n", directoryName);
            throw new Exception("Invalid directory");
        }
        return directory;
    }
    
    private static InputStream getInputStream(File directory, String fileName) throws IOException {
        File file = new File(directory, fileName);
        FileInputStream fis = new FileInputStream(file);
        return fis;
    }
    
    private static OutputStream getOutputStream(File directory, String fileName) throws IOException {
        File file = new File(directory, fileName);
        FileOutputStream fos = new FileOutputStream(file);
        return fos;
    }
    
    public static void main(String[] args) {
        List<String> archetypeIds = new ArrayList<String>();
        String inputDirectoryName = ".";
        String outputDirectoryName = ".";
        for(int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (i == (args.length - 1)) {
                    System.err.printf("option `%s'  needs an argument\n", args[i]);
                    return;
                }
                if ("-i".equals(args[i])) {
                    inputDirectoryName = args[++i];
                } else if ("-o".equals(args[i])) {
                    outputDirectoryName = args[++i];
                } else {
                    System.err.printf("unknown option `%s'\n", args[i]);
                    return;
                }
            } else {
                archetypeIds.add(args[i]);
            }
        }
        File inputDirectory = null;
        File outputDirectory = null;
        try {
            inputDirectory = getDirectory(inputDirectoryName);
            outputDirectory = getDirectory(outputDirectoryName);
        } catch(Exception ex) {
            return;
        }
        
        if (archetypeIds.size() == 0) {
            for(String fileName : inputDirectory.list()) {
                if (fileName.endsWith(".adl"))
                    archetypeIds.add(fileName.substring(0, fileName.length() - 4));
            }
        }
        
        for(String archetypeId : archetypeIds) {
            System.out.printf("converting %s\n", archetypeId);
            try {
                InputStream is = getInputStream(inputDirectory, archetypeId + ".adl");
                OutputStream os = getOutputStream(outputDirectory, archetypeId + ".xml");
                convertAdlToXml(is, os);
            } catch (Exception ex) {
                System.err.println("can't convert!");
                ex.printStackTrace();
            }
        }
    }
    
}
