package fr.ubl.ter.atlfluid.tests;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.m2m.atl.common.ATL.ATLFactory;
import org.eclipse.m2m.atl.common.ATL.ATLPackage;
import org.eclipse.m2m.atl.common.ATL.Module;
import org.eclipse.m2m.atl.common.ATL.Rule;
import org.eclipse.m2m.atl.common.OCL.OCLFactory;
import org.eclipse.m2m.atl.common.OCL.OCLPackage;
import org.eclipse.m2m.atl.common.OCL.OclModel;

public class ModuleBuilder {
	private List<RuleBuilder> rules = new ArrayList<RuleBuilder>();
	
	private OclModel inModel;
	private OclModel inMetaModel;
	private OclModel outModel;
	private OclModel outMetaModel;
	private List<Rule> atlRules = new ArrayList<Rule>();
	
	private Module module;
	private ATLFactory factory;
	private OCLFactory ofactory;
	
	private Package inPackage;
	private Package outPackage;
	
	
	
	
	public ModuleBuilder(){
		ATLPackage.eINSTANCE.eClass();
		OCLPackage.eINSTANCE.eClass();
		factory = ATLFactory.eINSTANCE;
		ofactory = OCLFactory.eINSTANCE;
		module = factory.createModule();
	}
	
	public ModuleBuilder module(String name) {
		module.setName(name);
		return this;
	}
	
	public ModuleBuilder create(String name,Package metacls) {
		outModel = ofactory.createOclModel();
		outModel.setName(name);
		
		
		outPackage = metacls;
		System.out.println("package name = " + metacls.getName());
		List<Class<?>> classes = ClassFinder.find(metacls.getName());
		if(classes.isEmpty()){
			System.out.println("empty package");
		}else{
			System.out.println("package not empty");
			for(Class<?> c : classes){
				System.out.println(c.getName());
			}
		}
		
		
		outMetaModel = ofactory.createOclModel();
		outMetaModel.setName(metacls.getName());
		
		
		module.getOutModels().add(outMetaModel);
		outModel.setMetamodel(outMetaModel); // erreur de references avec metamodel
		
		module.getOutModels().add(outModel);
		
		
		return this;
	}
	
	public ModuleBuilder from(String name, Package metacls) {
		inModel = ofactory.createOclModel();
		inModel.setName(name);
		
		inPackage = metacls;
		
		inMetaModel = ofactory.createOclModel();
		inMetaModel.setName(metacls.getName());
	
		
		module.getInModels().add(inMetaModel);
		inModel.setMetamodel(inMetaModel);
		module.getInModels().add(inModel);
		
		return this;
	}
	
	public RuleBuilder rule(String ruleName) {
		RuleBuilder child = new RuleBuilder(this);
		rules.add(child);
		child.setName(ruleName);
		return child;
		
	}
	
	public Module getModule(){
		for(RuleBuilder rb : rules){
			atlRules.add(rb.getRule());
		}
		//set all the rules in the module
		for(Rule r : atlRules){
			module.getElements().add(r);
		}
		
		return module;
	}
	
	public ATLFactory getAtlFactory(){
		return factory;
	}
	
	public Package getInPackage(){
		return inPackage;
	}
}
