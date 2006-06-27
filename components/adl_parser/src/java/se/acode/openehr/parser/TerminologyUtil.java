package se.acode.openehr.parser;

import java.io.*;
import java.util.*;

import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.TerminologyID;

/**
 * Utility class for loading openehr terminology from a text file
 * 
 * This is very much tailored for the parser to create property of coded text
 * within c_quantity_item structure.
 * 
 * @author Rong Chen
 */
public class TerminologyUtil {

	/*
	 * Constructor
	 */
	private TerminologyUtil() {
		this.map = new HashMap<String, DvCodedText>();
		this.codeMap = new HashMap<String, DvCodedText>();
		
		try {
			loadTerminology();
		} catch (IOException ioe) {
			System.err.println("failed to load terminology");
			throw new RuntimeException(ioe);
		}
	}

	/* singlton instance */
	private static final String TERMINOLOGY_FILE = "terminology.txt";
	
	private static final TerminologyID OPENEHR = new TerminologyID("openehr", "1.0");	

	private static final TerminologyUtil instance = new TerminologyUtil();

	private Map<String, DvCodedText> map; // rubric as key
	private Map<String, DvCodedText> codeMap; // code as key
	

	/**
	 * Gets an instance of this utility
	 * 
	 * @return
	 */
	public static TerminologyUtil getInstance() {
		return instance;
	}

	/**
	 * Creates an instance of coded text for given text value using pre-loaded
	 * terminology content
	 * 
	 * @param value not null
	 * @return null if no code found for given value
	 */
	public DvCodedText codedText(String value) {
		if(value == null) {
			throw new IllegalArgumentException("value is null");
		}
		return map.get(value.toLowerCase());
	}
	
	/**
	 * Retrieves coded text by given code
	 * 
	 * @param code
	 * @return null if not found
	 */
	public DvCodedText fromCode(String code) {
		return codeMap.get(code);
	}
	
	/**
	 * Gets total count of terms 
	 * 
	 * @return count
	 */
	public int count() {
		return map.size();
	}

	private void loadTerminology() throws IOException {
		BufferedReader reader = null;
		String code = null;
		String rubric = null;

		try {
			reader = new BufferedReader(new InputStreamReader(this.getClass()
					.getClassLoader().getResourceAsStream(TERMINOLOGY_FILE)));
			String line = reader.readLine();
			while (line != null) {
				line = line.trim();
				if (line.startsWith("#")) {
					line = reader.readLine();
					continue;
				}
				StringTokenizer tokens = new StringTokenizer(line, " ");
				if(tokens.countTokens() < 2) {
					line = reader.readLine();
					continue;
				}
				code = tokens.nextToken();
				rubric = line.substring(code.length() + 1, line.length());
				
				// terms are case insensitive
				rubric = rubric.toLowerCase();
				
				CodePhrase cp = new CodePhrase(OPENEHR, code);
				DvCodedText coded = new DvCodedText(rubric, cp);
				this.map.put(rubric, coded);	
				this.codeMap.put(code, coded);
				
				line = reader.readLine();
			}
		} catch(Exception t) {
		
			t.printStackTrace();
			
		} finally {
		
			reader.close();
		}
	}
}
