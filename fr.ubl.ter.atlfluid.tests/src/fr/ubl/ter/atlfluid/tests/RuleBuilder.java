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
	
	private void verifyType(String type){
		if(!type.contains("!")){
			throw new Error("the type is not defined");
		}else{
			String str = type.substring(0,type.indexOf('!'));
			boolean found = false;
			for(ModelR m : parent.getInModel()){
				if(m.getMetaModel().equals(str)){
					found = true;
					break;
				}
			}
			if(!found){
				for(ModelR m : parent.getOutModels()){
					if(m.getMetaModel().equals(str)){
						found = true;
						break;
					}
				}
			}
			if(!found){
				throw new Error("type is not defined");
			}
		}
	}
	
	public RuleBuilder from(String var, String type) {
		inVar = var;
		inType = type;
		verifyType(type);
		return this;
	}
	
	public RuleBuilder to(String var, String type) {
		outVar = var;
		outType = type;
		verifyType(type);
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
	
	public RuleR getContent() {
		return new RuleR(name, inVar, inType, condition, outVar, outType, bindings);
	}
	
}
