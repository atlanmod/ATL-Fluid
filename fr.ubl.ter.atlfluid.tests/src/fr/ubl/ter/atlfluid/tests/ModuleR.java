package fr.ubl.ter.atlfluid.tests;

import java.util.ArrayList;
import java.util.List;


public class ModuleR {
	private String moduleName;
	private List<RuleR> rules;
	private List<ModelR> inModels;
	private List<ModelR> outModels;
	
	public ModuleR(String moduleName){
		this.moduleName = moduleName;
		rules = new ArrayList<RuleR>();
		inModels = new ArrayList<ModelR>();
		outModels = new ArrayList<ModelR>();
	}
	
	public void addRule(RuleR r){
		rules.add(r);
	}
	
	public void addInModel(ModelR im){
		inModels.add(im);
	}
	
	public void addOutModel(ModelR om){
		outModels.add(om);
	}
	
	public String toString(){
		String res = "module  " + moduleName + "{ \n";
		for(int i=0; i < outModels.size();i++){
			res +=   "  create " + outModels.get(i).getName() + " : " + outModels.get(i).getMetaModel() +
					" from " + inModels.get(i).getName() + " : " + inModels.get(i).getMetaModel() + "\n";
		}
		for (RuleR r : rules) 
			res += r.toString()+"\n";
		res += "}";
		return res;
	}
}
