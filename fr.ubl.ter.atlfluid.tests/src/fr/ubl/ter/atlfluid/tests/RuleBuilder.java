package fr.ubl.ter.atlfluid.tests;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {
	private ModuleBuilder parent;
	private String name;
	private String inVar;
	private String inType;
	private String condition;
	private String outVar;
	private String outType;
	private List<Binding> bindings = new ArrayList<Binding>();
	
	public RuleBuilder(ModuleBuilder parent) {
		this.parent = parent;
	}
	
	public RuleBuilder from(String var, String type) {
		inVar = var;
		inType = type;
		return this;
	}
	
	public RuleBuilder to(String var, String type) {
		outVar = var;
		outType = type;
		return this;
	}
	
	public RuleBuilder pattern(String pattern) {
		condition = pattern;
		return this;
	}
	
	public RuleBuilder bind(String featName, String expression) {
		bindings.add(new Binding(featName, expression));
		return this;
	}
	
	public void setName(String ruleName) {
		// TODO Auto-generated method stub
		this.name = ruleName;
	}
	
	public RuleBuilder rule(String ruleName) {
		return parent.rule(ruleName);
	}
	
	public Rule getContent() {
		return new Rule(name, inVar, inType, condition, outVar, outType, bindings);
	}
}
