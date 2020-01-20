package org.ter.fluidtl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.m2m.atl.common.ATL.Rule;


public class RuleBuilder {
	private ModuleBuilder parent;
	private Rule atlRule;
	private List< Predicate<EObject> > predicateList = new ArrayList< Predicate<EObject> >();
	private List< Consumer<EObject> > consumerList = new ArrayList< Consumer<EObject> >();
	
	public RuleBuilder(ModuleBuilder parent) {
		//oclRule.setModule(parent.getModule());
		atlRule = parent.getAtlFactory().createMatchedRule();
		this.parent = parent;
	}
	
	//the first variable represent the ocl variable at a given time at execution
	//the second argument represent filter predicate definitions using lambda expressions
	//those predicates are all executed on the first argument(obj) in order to filter it
	public RuleBuilder from(EObject obj,Predicate<EObject>... pred) {
		for(Predicate<EObject> p : pred ){
			predicateList.add(p);	
		}
		System.out.println("predicateList.size = "  + predicateList.size());
		
		//here we should create the atl code that calls oclPatternTest(defined below) on variable obj
		//........................
		
		return this;
	}
	
	
	//we define the ocl actions in atl as lambda expressions implementing consumer functional interface
	//because this is one interface that produce only a side effect
	//Here each consumer instance should be called in one ocl function at atl execution
	public RuleBuilder to(Consumer<EObject>... cons) {
		for(Consumer<EObject> c : cons){
			consumerList.add(c);
		}
		System.out.println("consumerList.size = " + consumerList.size());
		
		return this;
	}
	
	//This function takes all predicates and make their conjunction(and) because we need the resulting
	//predicate to apply it on the variable o( obj in from method)
	//This function should not be called by the user but by atl at execution
	private boolean oclPatternTest(EObject o){
		Predicate<EObject> finalPredicate = (something)->{return true;};
		for(Predicate<EObject> pr : predicateList){
			finalPredicate.and(pr);
		}
		return finalPredicate.test(o);
		
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
