package br.com.zilics.archetypes.models.workbench.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.exception.PathParseException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public class PathEvaluatorPanel extends JPanel {
	private static final long serialVersionUID = -202312343093591258L;

	private JTextField pathTextField = null;
	private JEditorPane pathResultEditorPane = null;
	private JScrollPane pathResultScrollPane = null;
	private PathEvaluationContext context;
	
	public PathEvaluatorPanel() {
		super();
		initialize();
	}
	
	public PathEvaluationContext getPathEvaluationContext() {
		return context;
	}
	
	public void setPathEvaluationContext(PathEvaluationContext context) {
		this.context = context;
	}

	private void initialize() {
		setLayout(new BorderLayout());
		add(getPathTextField(), BorderLayout.SOUTH);
		add(getPathResultScrollPane(), BorderLayout.CENTER);
	}
	
	private JTextField getPathTextField() {
		if (pathTextField == null) {
			pathTextField = new JTextField();
			pathTextField.addKeyListener(new KeyListener() {
				
				private ArrayList<String> buffer = new ArrayList<String>();
				private int index = -1;
				
				private void bufferInsert(String cmd) {
					if (buffer.size() > 0) {
						if (buffer.get(buffer.size() - 1).equals(cmd)) return;
					}
					buffer.add(cmd);
				}
				
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						if (index == -1) index = buffer.size() - 1;
						else if (index != 0) index--;
						if (index != -1)
							pathTextField.setText(buffer.get(index));
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						if (index != buffer.size() - 1) index++;
						if (index != -1)
							pathTextField.setText(buffer.get(index));
					}
				}

				public void keyReleased(KeyEvent e) {
				}

				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n') {
						e.consume();
						String cmd = pathTextField.getText();
						insertHtmlIntoPathResultEditorPane("<span style='color: blue'>&gt;" + escapeHTML(cmd) + "</span><br />");
						if (context != null) {
							try {
								ListValue r = context.parseAndEvaluate(cmd);
								insertHtmlIntoPathResultEditorPane("<span>" + escapeHTML(r.toString()) + "</span>");
							} catch (Exception ex) {
								insertHtmlIntoPathResultEditorPane("<span style='color: red'>Exception: " + escapeHTML(ex.getMessage()) + "</span><br />");
								ex.printStackTrace();
							}
						}
						bufferInsert(cmd);
						pathTextField.setText("");
						index = -1;
					}
				}
				
			});
		}
		return pathTextField;
	}
	
	private JEditorPane getPathResultEditorPane() {
		if (pathResultEditorPane == null) {
			pathResultEditorPane = new JEditorPane();
			pathResultEditorPane.setContentType( "text/html" );
			pathResultEditorPane.setEditable(false);
			pathResultEditorPane.setBackground(Color.WHITE);
		}
		return pathResultEditorPane;
	}
	
	private void insertHtmlIntoPathResultEditorPane(String html) {
		HTMLEditorKit kit = ( HTMLEditorKit ) pathResultEditorPane.getEditorKit();
		HTMLDocument doc = (HTMLDocument) pathResultEditorPane.getDocument();
		try {
			kit.insertHTML(doc, doc.getLength(), html, 0, 0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private static final String escapeHTML(String s){
		   StringBuffer sb = new StringBuffer();
		   int n = s.length();
		   for (int i = 0; i < n; i++) {
		      char c = s.charAt(i);
		      switch (c) {
		         case '<': sb.append("&lt;"); break;
		         case '>': sb.append("&gt;"); break;
		         case '&': sb.append("&amp;"); break;
		         case '"': sb.append("&quot;"); break;
		         case '\u00E0': sb.append("&agrave;");break;
		         case '\u00C0': sb.append("&Agrave;");break;
		         case '\u00E2': sb.append("&acirc;");break;
		         case '\u00C2': sb.append("&Acirc;");break;
		         case '\u00E4': sb.append("&auml;");break;
		         case '\u00C4': sb.append("&Auml;");break;
		         case '\u00E5': sb.append("&aring;");break;
		         case '\u00C5': sb.append("&Aring;");break;
		         case '\u00E6': sb.append("&aelig;");break;
		         case '\u00C6': sb.append("&AElig;");break;
		         case '\u00E7': sb.append("&ccedil;");break;
		         case '\u00C7': sb.append("&Ccedil;");break;
		         case '\u00E9': sb.append("&eacute;");break;
		         case '\u00C9': sb.append("&Eacute;");break;
		         case '\u00E8': sb.append("&egrave;");break;
		         case '\u00C8': sb.append("&Egrave;");break;
		         case '\u00EA': sb.append("&ecirc;");break;
		         case '\u00CA': sb.append("&Ecirc;");break;
		         case '\u00EB': sb.append("&euml;");break;
		         case '\u00CB': sb.append("&Euml;");break;
		         case '\u00EF': sb.append("&iuml;");break;
		         case '\u00CF': sb.append("&Iuml;");break;
		         case '\u00F4': sb.append("&ocirc;");break;
		         case '\u00D4': sb.append("&Ocirc;");break;
		         case '\u00F6': sb.append("&ouml;");break;
		         case '\u00D6': sb.append("&Ouml;");break;
		         case '\u00F8': sb.append("&oslash;");break;
		         case '\u00D8': sb.append("&Oslash;");break;
		         case '\u00DF': sb.append("&szlig;");break;
		         case '\u00F9': sb.append("&ugrave;");break;
		         case '\u00D9': sb.append("&Ugrave;");break;         
		         case '\u00FB': sb.append("&ucirc;");break;         
		         case '\u00DB': sb.append("&Ucirc;");break;
		         case '\u00FC': sb.append("&uuml;");break;
		         case '\u00DC': sb.append("&Uuml;");break;
		         case '\u00AE': sb.append("&reg;");break;         
		         case '\u00A9': sb.append("&copy;");break;   
		         case '\u20AC': sb.append("&euro;"); break;
		         case '\n': sb.append("<br />"); break;
		         // be carefull with this one (non-breaking white spaces)
		         case ' ': sb.append("&nbsp;");break;         
		         
		         default:  sb.append(c); break;
		      }
		   }
		   return sb.toString();
		}

	
	private JScrollPane getPathResultScrollPane() {
		if (pathResultScrollPane == null) {
			pathResultScrollPane = new JScrollPane();
			pathResultScrollPane.getViewport().add(getPathResultEditorPane());
		}
		return pathResultScrollPane;
	}

	

}
