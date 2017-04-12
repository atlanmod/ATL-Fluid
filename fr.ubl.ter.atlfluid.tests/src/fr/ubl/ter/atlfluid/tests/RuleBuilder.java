package fr.ubl.ter.atlfluid.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.m2m.atl.common.ATL.Rule;
import org.eclipse.m2m.atl.common.ATL.RuleVariableDeclaration;

import Families.Member;

public class RuleBuilder {
	private ModuleBuilder parent;
	private Rule atlRule;
	Object inType;
	
	public RuleBuilder(ModuleBuilder parent) {
		//oclRule.setModule(parent.getModule());
		atlRule = parent.getAtlFactory().createMatchedRule();
		this.parent = parent;
	}
	
	public RuleBuilder from(String var, Object type ) {
		RuleVariableDeclaration rvd = parent.getAtlFactory().createRuleVariableDeclaration();
		rvd.setRule(atlRule);
		rvd.setVarName(var);
		atlRule.getVariables().add(rvd);
		
		inType = type;
		return this;
	}
	
	public RuleBuilder to(String var, Object type) {
		
		return this;
	}
	
	public RuleBuilder pattern(Predicate<Member> pattern){
		
		return this;
	}
	
	public RuleBuilder bind(String featName, String expression) {
		//bindings.add(new Binding(featName, expression));
		return this;
	}
	
	public void setName(String ruleName) {
		atlRule.setName(ruleName);
	}
	
	public RuleBuilder rule(String ruleName) {
		return parent.rule(ruleName);
	}
	
	public Rule getRule(){
		return atlRule;
	}
}
