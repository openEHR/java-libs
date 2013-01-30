Java Template OET Parser

Description
-----------

This component parsers OET template file.
It does not "flatten" the template (i.e. pulling all the referenced archetypes and subtemplates into it)

Usage 
-----

File input = new File ("D:\\temp\\Sample Simple Blood pressure.oet");
TemplateDocument td = TemplateDocument.Factory.parse(input);
TEMPLATE t = td.getTemplate();
System.out.println("lifecycle: "+t.getDescription().getLifecycleState());
System.out.println(t.getDescription().getDetails());
System.out.println("purpose: "+t.getDescription().getDetails().getPurpose());
System.out.println("otherDetails: "+t.getDescription().getOtherDetails());


Libraries
---------

If using the jar without the integrated xmlbeans package, you will need to add the xmlbeans-2.4.0.jar (available from xmlbeans.apache.org) to your project.