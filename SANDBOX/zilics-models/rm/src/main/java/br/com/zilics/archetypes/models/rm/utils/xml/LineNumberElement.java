package br.com.zilics.archetypes.models.rm.utils.xml;

import java.io.IOException;
import java.io.StringWriter;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * An XML element with attached info (startLine, endLine)
 * 
 * @author Humberto Naves
 *
 */
public class LineNumberElement extends Element {

	private static final long serialVersionUID = 9113085609706646837L;
    private static final XMLOutputter outputter;

    private int startLine;
    private int endLine;
    

    static {
        outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));
    }


    /**
     * Constructor
     * @param name
     */
    public LineNumberElement(String name)  {
        super(name);
    }

    /**
     * 
     * @param name
     * @param namespace
     */
    public LineNumberElement(String name, Namespace namespace)  {
        super(name, namespace);
    }

    /**
     * 
     * @param name
     * @param uri
     */
    public LineNumberElement(String name, String uri)  {
        super(name, uri);
    }

    /**
     * 
     * @param name
     * @param prefix
     * @param uri
     */
    public LineNumberElement(String name, String prefix, String uri)  {
        super(name, prefix, uri);
    }

    /**
     * 
     * @return
     */
    public int getEndLine() {
        return endLine;
    }

    /**
     * 
     * @return
     */
    public int getStartLine() {
        return startLine;
    }

    /**
     * 
     * @param endLine
     */
    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    /**
     * 
     * @param startLine
     */
    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	StringWriter sw = new StringWriter();
    	try {
			outputter.output(this, sw);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return getStartLine() + "-" + getEndLine() + " :" + super.toString() + "\n" + sw.toString();
    }

}
