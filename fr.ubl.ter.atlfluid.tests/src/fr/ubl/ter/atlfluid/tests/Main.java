package fr.ubl.ter.atlfluid.tests;

public class Main {
	
	
	public static void main(String[]args) {
		// L'exemple ClassDiagram2Relational réécrit en ce à quoi ressemblera sans doute l'API
		
		ModuleBuilder moduleBuilder = new ModuleBuilder();
		
		moduleBuilder.module("ClassDiagram2Relational")
			.create("OUT","Relational")
			.from("IN","ClassDiagram")
			.rule("Package2Schema")
				.from("p","ClassDiagram!Package")
					.pattern("not p.ownedElements.isEmpty()")
				.to("out","Relational!Schema")
					.bind("name","p.name")
					.bind("ownedElements","p.ownedElements")
			.rule("DataType2Type")
				.from("d", "ClassDiagram!DataType")
					.pattern("ClassDiagram!Class.allInstances()->select(c | c.name = d.name)->isEmpty()")
				.to("t", "Relational!Type")
					.bind("name", "d.name");
				
			/* Partie non encore implémentée
			.rule("DataType2Type")
				.from
		*/
		
		Module module = moduleBuilder.getContent();
		System.out.println(module);
	}
}

/* Suite des règles à écrire sous la forme propre à l'API
	rule DataType2Type {
	  from
	    d:ClassDiagram!DataType
	     (ClassDiagram!Class.allInstances()
	        ->select(c | c.name = d.name)->isEmpty())
	  to
	    t:Relational!Type (name<-d.name)
	}
	rule Class2Table {
	  from
	    c:ClassDiagram!Class
	  to
	    t:Relational!Table 
	     (name<-c.name, col<-c.attr, owner<-c.owner,       
	      key<-c.attr->select(a | a.name.endsWith('Id')))
	}
	rule SingleValuedAttribute2Column {
	  from
	    a:ClassDiagram!Attribute
	     (not a.multiValued)
	  to
	    c:Relational!Column 
	     (name<-a.owner.name+'_'+a.name,
	      owner<-a.owner,
	      type<-if a.type.oclIsTypeOf(ClassDiagram!DataType)
	        then a.type else OclUndefined endif)
}
*/