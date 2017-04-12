package fr.ubl.ter.atlfluid.tests2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.m2m.atl.common.ATL.Module;
import org.eclipse.m2m.atl.emftvm.EmftvmFactory;
import org.eclipse.m2m.atl.emftvm.Metamodel;
import org.eclipse.m2m.atl.emftvm.Model;
import org.eclipse.m2m.atl.emftvm.impl.resource.EMFTVMResourceFactoryImpl;
import org.eclipse.m2m.atl.emftvm.util.DefaultModuleResolver;
import org.eclipse.m2m.atl.emftvm.util.ModuleResolver;
import org.eclipse.m2m.atl.emftvm.util.TimingData;
import org.eclipse.m2m.atl.engine.emfvm.lib.ExecEnv;

import fr.ubl.ter.atlfluid.tests.*;
import Families.FamiliesFactory;
import Families.Family;
import Families.Member;
import Families.impl.*;

import Persons.*;

public class Main {

	
	public static void main(String[]args) throws IOException {
		
		Member m = FamiliesFactory.eINSTANCE.createMember();
		//creation du module atl
		ModuleBuilder moduleBuilder = new ModuleBuilder();
		
		/*
		moduleBuilder.module("Families2Persons")
		.create("OUT",Person.class.getPackage())
		.from("IN",Family.class.getPackage())
		.rule("Member2Male")
			.from((s "Families!Member")->{s instanceof Female })
			.to("t","Persons!Male")
				.bind("fullName","s.firstName");
		*/
		
		moduleBuilder.module("Families2Persons")
		.create("OUT",Person.class.getPackage())
		.from("IN",Family.class.getPackage())
		.rule("Member2Male")
			.from("s","Families!Member")
				.pattern(/*"not s.isFemale()"*/(s)->{return !(s instanceof Female); })
			.to("t","Persons!Male")
				.bind("fullName","s.firstName");
		
		Module mod = (Module) moduleBuilder.getModule();
		
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("transformation", new XMIResourceFactoryImpl());
		
		ResourceSet resSet = new ResourceSetImpl();
		
		System.out.println("before creating resource");
		Resource resource = resSet.createResource(URI.createURI("transformation/MyTransform.transformation"));
		if(mod == null){
			System.out.println("module is NULL");
		}else{
			if(resource != null){
				resource.getContents().add(mod);
			}else{
				System.out.println("resource is null");
			}
		}
		
		System.out.println("after adding module to ressource");
		
		try {
            resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
            e.printStackTrace();
		}
		
		
		//creation du fichier atl
		/*
		FileWriter fichier = new FileWriter(new File("./transformation/Families2Persons.atl"));
		try {
		   fichier.write(module.toString());
		} catch(IOException ie) {
		   ie.printStackTrace();
		}
		fichier.close();
		*/
		
		//lancement de ATL
		
		
		
		
	}
	
	
	/*
	public final static String IN_METAMODEL = "./metamodels/Families.ecore";
	public final static String IN_METAMODEL_NAME = "Families";
	public final static String OUT_METAMODEL = "./metamodels/Persons.ecore";
	public final static String OUT_METAMODEL_NAME = "Persons";
	
	public final static String IN_MODEL = "./models/sample-Families.xmi";
	public final static String OUT_MODEL = "./models/sample-Persons.xmi";
	
	public final static String TRANSFORMATION_DIR = "./transformation/";
	public final static String TRANSFORMATION_MODULE= "Families2Persons";
	
	// The input and output metamodel nsURIs are resolved using lazy registration of metamodels, see below.
	private String inputMetamodelNsURI;
	private String outputMetamodelNsURI;
	
	
	public void launch(String inMetamodelPath, String inModelPath, String outMetamodelPath,
			String outModelPath, String transformationDir, String transformationModule){
		
		 
		 * Creates the execution environment where the transformation is going to be executed,
		 * you could use an execution pool if you want to run multiple transformations in parallel,
		 * but for the purpose of the example let's keep it simple.
		 
		ExecEnv env = (ExecEnv) EmftvmFactory.eINSTANCE.createExecEnv();
		ResourceSet rs = new ResourceSetImpl();

		
		 * Load meta-models in the resource set we just created, the idea here is to make the meta-models
		 * available in the context of the execution environment, the ResourceSet is later passed to the
		 * ModuleResolver that is the actual class that will run the transformation.
		 * Notice that we use the nsUris to locate the metamodels in the package registry, we initialize them 
		 * from Ecore files that we registered lazily as shown below in e.g. registerInputMetamodel(...) 
		 
		Metamodel inMetamodel = EmftvmFactory.eINSTANCE.createMetamodel();
		inMetamodel.setResource(rs.getResource(URI.createURI(inputMetamodelNsURI), true));
		((org.eclipse.m2m.atl.emftvm.ExecEnv) env).registerMetaModel(IN_METAMODEL_NAME, inMetamodel);
		
		Metamodel outMetamodel = EmftvmFactory.eINSTANCE.createMetamodel();
		outMetamodel.setResource(rs.getResource(URI.createURI(outputMetamodelNsURI), true));
		((org.eclipse.m2m.atl.emftvm.ExecEnv) env).registerMetaModel(OUT_METAMODEL_NAME, outMetamodel);
		
		
		 * Create and register resource factories to read/parse .xmi and .emftvm files,
		 * we need an .xmi parser because our in/output models are .xmi and our transformations are
		 * compiled using the ATL-EMFTV compiler that generates .emftvm files
		 
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("emftvm", new EMFTVMResourceFactoryImpl());
		
		// Load models
		Model inModel = EmftvmFactory.eINSTANCE.createModel();
		inModel.setResource(rs.getResource(URI.createURI(inModelPath, true), true));
		((org.eclipse.m2m.atl.emftvm.ExecEnv) env).registerInputModel("IN", inModel);
		
		Model outModel = EmftvmFactory.eINSTANCE.createModel();
		outModel.setResource(rs.createResource(URI.createURI(outModelPath)));
		((org.eclipse.m2m.atl.emftvm.ExecEnv) env).registerOutputModel("OUT", outModel);
		
		
		 *  Load and run the transformation module
		 *  Point at the directory your transformations are stored, the ModuleResolver will 
		 *  look for the .emftvm file corresponding to the module you want to load and run
		 
		ModuleResolver mr = new DefaultModuleResolver(transformationDir, rs);
		TimingData td = new TimingData();
		((org.eclipse.m2m.atl.emftvm.ExecEnv) env).loadModule(mr, TRANSFORMATION_MODULE);
		td.finishLoading();
		((org.eclipse.m2m.atl.emftvm.ExecEnv) env).run(td);
		td.finish();
			
		// Save models
		try {
			outModel.getResource().save(Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/	
	
	
}
