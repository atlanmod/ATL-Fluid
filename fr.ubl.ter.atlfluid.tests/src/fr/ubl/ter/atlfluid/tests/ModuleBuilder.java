package fr.ubl.ter.atlfluid.tests;

import java.util.ArrayList;
import java.util.List;


public class ModuleBuilder {
	private List<RuleBuilder> rules = new ArrayList<RuleBuilder>();
	
	private String moduleName;
	private List<Model> outModels = new ArrayList<Model>();
	private List<Model> inModels = new ArrayList<Model>();
	
	public ModuleBuilder(){
		
	}
	
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
	
	public List<Model> getInModel(){
		return inModels;
	}
	
	public List<Model> getOutModels(){
		return outModels;
	}
	
	public Module getContent(){
		Module result = new Module(moduleName);
		for(Model m : inModels){
			result.addInModel(m);
		}
		for(Model m : outModels){
			result.addOutModel(m);
		}
		for(RuleBuilder rb : rules){
			result.addRule(rb.getContent());
		}
		return result;
	}

}
