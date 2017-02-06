package fr.ubl.ter.atlfluid.tests;

import java.util.ArrayList;
import java.util.List;

public class ModuleBuilder {
	private List<RuleBuilder> rules = new ArrayList<RuleBuilder>();
	
	private String moduleName;
	private List<Model> outModels;
	private List<Model> inModels;
	
	public ModuleBuilder module(String name) {
		moduleName = name;
		return this;
	}
	
	public ModuleBuilder create(String name, String meta) {
		outModels.add(new Model(name, meta));
		return this;
	}
	
	public ModuleBuilder from(String name, String meta) {
		inModels.add(new Model(name, meta));
		return this;
	}
	
	public RuleBuilder rule(String ruleName) {
		RuleBuilder child = new RuleBuilder(this);
		rules.add(child);
		child.setName(ruleName);
		return child;
		
	}

}
