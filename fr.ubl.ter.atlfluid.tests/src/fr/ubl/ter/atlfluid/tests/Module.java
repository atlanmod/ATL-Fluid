package fr.ubl.ter.atlfluid.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.m2m.atl.emftvm.EmftvmFactory;
import org.eclipse.m2m.atl.emftvm.EmftvmPackage;
import org.eclipse.m2m.atl.emftvm.Metamodel;

public class Module {
	private String moduleName;
	private List<Rule> rules;
	private List<Model> inModels;
	private List<Model> outModels;
	
	public Module(String moduleName){
		this.moduleName = moduleName;
		rules = new ArrayList<Rule>();
		inModels = new ArrayList<Model>();
		outModels = new ArrayList<Model>();
	}
	
	public void addRule(Rule r){
		rules.add(r);
	}
	
	public void addInModel(Model im){
		inModels.add(im);
	}
	
	public void addOutModel(Model om){
		outModels.add(om);
	}
	
	public String toString(){
		String res = "module  " + moduleName + "{ \n";
		for(int i=0; i < outModels.size();i++){
			res +=   "  create " + outModels.get(i).getName() + " : " + outModels.get(i).getMetaModel() +
					" from " + inModels.get(i).getName() + " : " + inModels.get(i).getMetaModel() + "\n";
		}
		for (Rule r : rules) 
			res += r.toString()+"\n";
		res += "}";
		return res;
	}
}
