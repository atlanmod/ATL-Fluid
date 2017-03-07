package fr.ubl.ter.atlfluid.tests;

import java.util.List;

public class Rule {
	private String name;
	private String inVar;
	private String inType;
	private String condition;
	private String outVar;
	private String outType;
	private List<Binding> bindings;
	
	public Rule(String name,String inVar,String inType,String condition,String outVar,String outType,List<Binding> bindings){
		this.name = name;
		this.inVar = inVar;
		this.inType = inType;
		this.condition = condition;
		this.outVar = outVar;
		this.outType = outType;
		this.bindings = bindings;
	}
	
	public String toString(){
		return "  rule " + name + " {\n" +
				"    from " + "\n" +
				"       "   + inVar + " : " + inType + "\n" +
				"    to "  + "\n"+
				"       "  +  outVar + " : " + outType + "\n" +
				"  }";
	}
}
