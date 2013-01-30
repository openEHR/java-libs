package br.com.zilics.archetypes.models.workbench;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.zilics.archetypes.models.adl.parser.ADLParser;
import br.com.zilics.archetypes.models.adl.serializer.ADLSerializer;
import br.com.zilics.archetypes.models.am.archetype.Archetype;
import br.com.zilics.archetypes.models.am.template.openehrprofile.Template;
import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.exception.ValidateException;
import br.com.zilics.archetypes.models.rm.utils.path.PathFunction;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlParser;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlSerializer;

public class Main {
	public static void main(String[] args) {
		if (args.length == 0)
			printHelp(null);

		String mode = null;
		String inputFile = null;
		String outputFile = null;
		String archetypeDirectory = null, templateDirectory = null;
		boolean prettyPrint = false;
		
		for(int i = 0; i < args.length; i++) {
			if ("--help".equals(args[i])) {
				printHelp(mode);
			} else if ("--pretty".equals(args[i]) && "convert".equals(mode)) {
				prettyPrint = true;
			} else if (args[i].startsWith("-")) {
				if (i == args.length - 1) {
					error("Invalid argument `" + args[i] + "'");
				}
				String complement = args[i + 1];
				if ("-t".equals(args[i]) && ("validate".equals(mode) || "path".equals(mode))) {
					templateDirectory = complement;
				} else if ("-a".equals(args[i]) && ("validate".equals(mode) || "path".equals(mode))) {
					archetypeDirectory = complement;
				} else {
					error("Invalid argument `" + args[i] + "'");
				}
				i++;
			} else {
				if (mode == null) {
					mode = args[i];
					if (!"path".equals(mode) &&
						!"convert".equals(mode) &&
						!"validate".equals(mode)) {
						error("Invalid mode: `" + mode + "'");
					}
				} else if (inputFile == null) {
					inputFile = args[i];
				} else if (outputFile == null && "convert".equals(mode)) {
					outputFile = args[i];
				} else {
					error("Unknown argument: `" + args[i] + "'");
				}
			}
		}
		
		if ("convert".equals(mode)) {
			if (inputFile == null || outputFile == null)
				error("You must specify the input and output files");
			RMObject obj = parseInputFile(inputFile);
			
			try {
				obj.validate();
			} catch(ValidateException ex) {
				System.err.println("Problem validating: " + inputFile);
				System.err.println(ex.getResult());
				System.exit(1);
			}
			
			serializeOutputFile(obj, outputFile, prettyPrint);
		} else if ("path".equals(mode)) {
			Map<String, Archetype> archetypes = null;
			Map<String, Template> templates = null;
			RMObject obj = parseInputFile(inputFile);
			PathEvaluationContext context = null;
			
			if (obj instanceof Archetype) {
				context = ((Archetype) obj).getPathEvaluatorContext();
			} else if (obj instanceof Template) {
				Template template = (Template) obj;
				if (archetypeDirectory != null)
					archetypes = parseArchetypes(archetypeDirectory);
				try {
					template.resolveArchetypes(archetypes);
					template.validate();
				} catch(Exception ex) {
					error("Problem with input");					
				}
				context = template.getPathEvaluatorContext();
			} else if (obj instanceof Locatable) {
				if (archetypeDirectory != null)
					archetypes = parseArchetypes(archetypeDirectory);
				if (templateDirectory != null)
					templates = parseTemplates(templateDirectory, archetypes);
				Locatable l = (Locatable) obj;
				try {
					if (l.getArchetypeDetails().getTemplateId() != null && templates != null) {
						Template t = templates.get(l.getArchetypeDetails().getTemplateId().getValue());
						t.semanticValidation(l);
					} else if (l.getArchetypeDetails().getArchetypeId() != null && archetypes != null) {
						Archetype a = archetypes.get(l.getArchetypeDetails().getArchetypeId().getValue());
						a.semanticValidation(l);
					}
				} catch(Exception ex) {
					error("Exception while validating: "  + inputFile + "\n" + ex);
				}
				if (l.getArchetypeDetails() != null && l.getArchetypeDetails().getTemplateId() != null && templates != null) {
					context = l.getPathEvaluatorContext(templates.get(l.getArchetypeDetails().getTemplateId().getValue()));
				} else 
					context = l.getPathEvaluatorContext(null);
			}
			
			if (context == null) {
				error("Invalid input: " + inputFile);
			}

			try {
				obj.validate();
			} catch(Exception ex) {
				ex.printStackTrace();
				error("Problem with input");
			}
			
			Scanner scanner = new Scanner(System.in);
			
			try {
				context.defineFunction("exit", new PathFunction() {
					public ListValue evaluate(List<TreeNode> nodes, PathEvaluationContext context) throws PathEvaluationException {
						System.exit(0);
						return null;
					}
				});
				context.defineFunction("help", new PathFunction() {
					public ListValue evaluate(List<TreeNode> nodes, PathEvaluationContext context) throws PathEvaluationException {
						System.out.println("Help: TODO!");
						return ListValue.EMPTY;
					}
				});
			} catch(Exception ex) {
				ex.printStackTrace();
				error("Error");
			}
			System.out.println("Type \"help()\" for help");
			while(true) {
				System.out.print(">");
				String line = scanner.nextLine();
				if (line.length() == 0) break;
				parseAndEvaluate(line, context);
			}

		} else if ("validate".equals(mode)) {
			Map<String, Archetype> archetypes = null;
			Map<String, Template> templates = null;
			if (archetypeDirectory != null)
				archetypes = parseArchetypes(archetypeDirectory);
			if (templateDirectory != null)
				templates = parseTemplates(templateDirectory, archetypes);
			
			RMObject obj = parseInputFile(inputFile);

			if (obj instanceof Archetype) {
				try {
					obj.validate();
				} catch(Exception ex) {
					ex.printStackTrace();
					error("Exception while validating: "  + inputFile + "\n" + ex);
				}
			} else if (obj instanceof Template) {
				try {
					((Template) obj).resolveArchetypes(archetypes);
					obj.validate();
				} catch(Exception ex) {
					error("Exception while validating: "  + inputFile + "\n" + ex);
				}
			} else if (obj instanceof Locatable) {
				Locatable l = (Locatable) obj;
				try {
					l.validate();
					if (l.getArchetypeDetails().getTemplateId() != null && templates != null) {
						Template t = templates.get(l.getArchetypeDetails().getTemplateId().getValue());
						t.semanticValidation(l);
					} else if (l.getArchetypeDetails().getArchetypeId() != null && archetypes != null) {
						Archetype a = archetypes.get(l.getArchetypeDetails().getArchetypeId().getValue());
						a.semanticValidation(l);
					}
				} catch(Exception ex) {
					error("Exception while validating: "  + inputFile + "\n" + ex);
				}
			}
			
			
		} else {
			printHelp(null);
		}
	}
	
	private static void parseAndEvaluate(String expr, PathEvaluationContext context) {
		try {
			System.out.println(context.parseAndEvaluate(expr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static RMObject parseInputFile(String fileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			error("File not found: " + fileName);
		}

		try {
			if (fileName.endsWith(".adl")) {
				ADLParser adlParser = new ADLParser(fis);
				return adlParser.parse();
			} else if (fileName.endsWith(".xml") || fileName.endsWith(".oet")) {
				return XmlParser.parseXml(fis);
			} else {
				error("Invalid filename extension: " + fileName);
			}
		} catch(Exception ex) {
			error(ex.toString());
		}
		return null;
	}
	
	private static void serializeOutputFile(RMObject obj, String fileName, boolean prettyPrint) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
		} catch(FileNotFoundException ex) {
			error(ex.toString());
		}

		try {
			if (fileName.endsWith(".adl")) {
				ADLSerializer serializer = new ADLSerializer();
				serializer.output((Archetype) obj, fos);
			} else if (fileName.endsWith(".xml") || fileName.endsWith(".oet")) {
				XmlSerializer.serializeXml(obj, null, fos, prettyPrint);
			} else {
				error("Invalid filename extension: " + fileName);
			}
		} catch(Exception ex) {
			error(ex.toString());
		}
	}
	
	private static Map<String, Archetype> parseArchetypes(String archetypeDirectory) {
		HashMap<String, Archetype> result = new HashMap<String, Archetype>();
		File directory = new File(archetypeDirectory);
		if (!directory.isDirectory()) {
			error("Invalid directory: " + archetypeDirectory);
		}
		for(File child : directory.listFiles()) {
			String fileName = child.getAbsolutePath();
			try {
				if (fileName.endsWith(".adl") || fileName.endsWith(".xml")) {
					Archetype archetype = (Archetype) parseInputFile(fileName);
					archetype.validate();
					result.put(archetype.getArchetypeId().getValue(), archetype);
				}
			} catch(Exception ex) {
				System.err.println("Error while parsing: " + child.getAbsolutePath() + ", " + ex);
			}
		}
		return result;
	}

	private static Map<String, Template> parseTemplates(String templateDirectory, Map<String, Archetype> archetypes) {
		HashMap<String, Template> result = new HashMap<String, Template>();
		File directory = new File(templateDirectory);
		if (!directory.isDirectory()) {
			error("Invalid directory: " + templateDirectory);
		}
		for(File child : directory.listFiles()) {
			String fileName = child.getAbsolutePath();
			try {
				if (fileName.endsWith(".oet")) {
					Template template = (Template) parseInputFile(fileName);
					if (archetypes != null)
						template.resolveArchetypes(archetypes);
					template.validate();
					result.put(template.getName(), template);
				}
			} catch(Exception ex) {
				System.err.println("Error while parsing: " + child.getAbsolutePath() + ", " + ex);
			}
		}
		return result;
	}

	private static void error(String message) {
		System.err.println(message);
		System.exit(1);
	}
	
	private static void printHelp(String mode) {
		if (mode == null) {
			System.out.println(
					"Usage: workbench [mode] [arguments]\n"+
					"where mode could be:\n" +
					"  path\n" +
					"  convert\n" +
					"  validate\n"
			);
		} else if ("convert".equals(mode)) {
			System.out.println(
					"Usage: workbench convert [--pretty] input output\n"
			);			
		} else if ("validate".equals(mode)) {
			System.out.println(
					"Usage: workbench validate [-a archetype directory] [-t template directory] [archetype | template | pathable]\n"
			);						
		} else if ("path".equals(mode)) {
			System.out.println(
					"Usage: workbench path [-a archetype directory] [archetype | template | pathable]\n"
			);						
		}
		System.exit(0);
	}
	
}
