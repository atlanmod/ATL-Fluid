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
import org.eclipse.m2m.atl.common.OCL.OCLFactory;
import org.eclipse.m2m.atl.common.OCL.OCLPackage;
import org.eclipse.m2m.atl.common.OCL.OclModel;


public class ModuleBuilder {
	private List<RuleBuilder> rules = new ArrayList<RuleBuilder>();
	
	private String moduleName;
	private List<ModelR> outModels = new ArrayList<ModelR>();
	private List<ModelR> inModels = new ArrayList<ModelR>();
	private OclModel inModel;
	private OclModel inMetaModel;
	private OclModel outModel;
	private OclModel outMetaModel;
	
	private Module module;
	private ATLFactory factory;
	private OCLFactory ofactory;
	
	
	public ModuleBuilder(){
		ATLPackage.eINSTANCE.eClass();
		OCLPackage.eINSTANCE.eClass();
		factory = ATLFactory.eINSTANCE;
		ofactory = OCLFactory.eINSTANCE;
		module = factory.createModule();
	}
	
	public ModuleBuilder module(String name) {
		moduleName = name;
		module.setName(name);
		return this;
	}
	
	public ModuleBuilder create(String name, String meta) {
		outModels.add(new ModelR(name, meta));
		outModel = ofactory.createOclModel();
		outModel.setName(name);
		
		outMetaModel = ofactory.createOclModel();
		outMetaModel.setName(meta);
		outModel.setMetamodel(outMetaModel); // erreur de references avec metamodel
			
		module.getOutModels().add(outModel);
		return this;
	}
	
	public ModuleBuilder from(String name, String meta) {
		inModels.add(new ModelR(name, meta));
		inModel = ofactory.createOclModel();
		inModel.setName(name);
		/*
		inMetaModel = ofactory.createOclModel();
		inMetaModel.setName(meta);
	
		inModel.setMetamodel(inMetaModel);
		*/
		module.getInModels().add(inModel);
		return this;
	}
	
	public RuleBuilder rule(String ruleName) {
		RuleBuilder child = new RuleBuilder(this);
		rules.add(child);
		child.setName(ruleName);
		return child;
		
	}
	
	public List<ModelR> getInModel(){
		return inModels;
	}
	
	public List<ModelR> getOutModels(){
		return outModels;
	}
	
	public ModuleR getContent(){
		ModuleR result = new ModuleR(moduleName);
		for(ModelR m : inModels){
			result.addInModel(m);
		}
		for(ModelR m : outModels){
			result.addOutModel(m);
		}
		for(RuleBuilder rb : rules){
			result.addRule(rb.getContent());
		}
		return result;
	}
	
	public Module getModule(){
		return module;
	}

}
