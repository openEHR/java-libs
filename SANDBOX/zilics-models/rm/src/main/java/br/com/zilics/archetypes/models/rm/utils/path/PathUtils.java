package br.com.zilics.archetypes.models.rm.utils.path;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

import br.com.zilics.archetypes.models.rm.exception.PathParseException;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;

/**
 * Helper class for parsing A-path queries
 * @author Humberto Naves
 *
 */
public final class PathUtils {
	private PathUtils() {}
	
	/**
	 * Parse an stream 
	 * @param is the input stream
	 * @return the parsed {@link TreeNode}
	 * @throws PathParseException any exception raised during the process
	 */
	public static TreeNode parseArchPath(InputStream is) throws PathParseException {
		return parseArchPath(new InputStreamReader(is));
	}

	/**
	 * Parse a string
	 * @param input the input string
	 * @return the parsed {@link TreeNode}
	 * @throws PathParseException any exception raised during the parsing
	 */
	public static TreeNode parseArchPath(String input) throws PathParseException {
		return parseArchPath(new StringReader(input));
	}

	/**
	 * Parse an reader 
	 * @param reader the reader
	 * @return the parsed {@link TreeNode}
	 * @throws PathParseException any exception raised during the process
	 */
	public static TreeNode parseArchPath(Reader reader) throws PathParseException {
		try {
			ANTLRInputStream input = new ANTLRInputStream();
			input.load(reader, ANTLRInputStream.INITIAL_BUFFER_SIZE, ANTLRInputStream.READ_BUFFER_SIZE);
			ArchPathLexer lexer = new ArchPathLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ArchPathParser parser = new ArchPathParser(tokens);
			return parser.archPath();
		} catch(Throwable t) {
			throw new PathParseException("Can't parse", t);
		}
	}

}
