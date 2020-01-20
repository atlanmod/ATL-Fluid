package org.ter.fluidtl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.m2m.atl.common.ATL.Module;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;

public class Main {
	
	public static void methodeError(){
		IInjector injector = new EMFInjector();
		ModelFactory modelFactory = new EMFModelFactory();
		IReferenceModel atlMetamodel;
		IModel model;
		
		try {
			atlMetamodel = modelFactory.newReferenceModel();
			model = modelFactory.newModel(atlMetamodel);
			File file = new File("transformation/Public2Private.atl");
			FileInputStream fs = new FileInputStream(file);
			BufferedInputStream in = new BufferedInputStream(fs);
			StringWriter out = new StringWriter();
			int b;
			while((b=in.read()) != -1){
				out.write(b);
			}
			out.flush();
			out.close();
			in.close();
			String str = out.toString();
			
			
			injector.inject(model, new StringReader(str),Collections.EMPTY_MAP);
		} catch (ATLCoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args) {
		// L'exemple ClassDiagram2Relational réécrit en ce à quoi ressemblera sans doute l'API
		
		ModuleBuilder moduleBuilder = new ModuleBuilder();
		
		/*
		moduleBuilder.module("ClassDiagram2Relational")
			.create("OUT","Relational")
			.from("IN","ClassDiagram")
			.rule("Package2Schema")
				.from("p","ClassDiagram!Package")
					.pattern("not p. .isEmpty()")
				.to("out","Relational!Schema")
					.bind("name","p.name")
					.bind("ownedElements","p.ownedElements")
			.rule("DataType2Type")
				.from("d", "ClassDiagram!DataType")
					.pattern("ClassDiagram!Class.allInstances()->select(c | c.name = d.name)->isEmpty()")
				.to("t", "Relational!Type")
					.bind("name", "d.name");
		*/
		
		//ModuleR module = moduleBuilder.getContent();
		//System.out.println(module); 
		Module mod = moduleBuilder.getModule();
		
		/*
		ModelFactory modelFactory = new EMFModelFactory();
		
		IInjector injector = new EMFInjector();
		IExtractor extractor = new EMFExtractor();
		
		IReferenceModel atlMetamodel;
		IReferenceModel emfMetamodel; 
		IModel aModel;
		IModel outModel;
		ILauncher atlToEmfLauncher = new EMFVMLauncher();
		AtlStandaloneCompiler atlCompiler;
		//AtlParser atlParser;
				
		
		try {
			atlMetamodel = modelFactory.newReferenceModel();
			injector.inject(atlMetamodel,"metamodels/ATL.ecore");
			emfMetamodel = modelFactory.newReferenceModel();
			injector.inject(emfMetamodel, "metamodels/EMF.ecore");
			
			System.out.println("metamodels loading end");
			//atlCompiler = AtlCompiler.getCompiler(AtlCompiler.DEFAULT_COMPILER_NAME);
			//atlParser = AtlParser.getDefault();
			System.out.println("compiler init end");
			
			// init in and out models
			aModel = modelFactory.newModel(atlMetamodel);
			//atlParser.inject(aModel, "transformation/ClassDiag2Relational.atl", Collections.EMPTY_MAP);
			//atlCompiler.compile(new FileInputStream(statText), "transformation/ClassDiag2Relational.asm");
			
			System.out.println("before injecting in model");
			
			//injector.inject(aModel, new StringReader(module.toString()),Collections.EMPTY_MAP);
			methodeError();
			
			System.out.println("after injecting in model");
			
			outModel = modelFactory.newModel(emfMetamodel);
			
			System.out.println("models loading end");
			
			atlToEmfLauncher.initialize(new HashMap<String,Object>());
			atlToEmfLauncher.addInModel(aModel,"IN","ATL");
			atlToEmfLauncher.addOutModel(outModel, "OUT", "EMF");
			
			System.out.println("processing to transform");
			
			atlToEmfLauncher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), new HashMap<String,Object>(), new FileInputStream("transformations/ATLcopy.atl"));
			
			System.out.println("end transformation. Proceeding to extract out model");
			
			extractor.extract(outModel, "models/transf.xmi");
			
			System.out.println("out module extracted");
			
		} catch (ATLCoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		
		// create stuff
		EMFModelFactory emfFactory = new EMFModelFactory();
		XMIResourceFactoryImpl xmiFactory = new XMIResourceFactoryImpl();

		EMFExtractor extractor = new EMFExtractor();
		EMFInjector emfInjector = new EMFInjector();
		ResourceSet resourceSet = new ResourceSetImpl();

		// load model
		EMFReferenceModel hdlMetaModel = (EMFReferenceModel) emfFactory.newReferenceModel();
		emfInjector.inject(hdlMetaModel, resourceSet.getResource(URI.createFileURI("org.xtext.hal/model/generated/Hdl.ecore"), true));
		EMFModel hdlData = (EMFModel) emfFactory.newModel(hdlMetaModel);

		EMFReferenceModel palMetaModel = (EMFReferenceModel) emfFactory.newReferenceModel();
		emfInjector.inject(palMetaModel, resourceSet.getResource(URI.createFileURI("org.xtext.hal/model/Pal.ecore"), true));
		EMFModel palData = (EMFModel) emfFactory.newModel(palMetaModel);
		palData.setIsTarget(true);

		// load xtext content, convert to xmi
		Resource xmiResource = xmiFactory.createResource(URI.createURI("org.xtext.hal/model/generated/Hdl.xmi"));
		xmiResource.getContents().addAll(hdlModel.getContents());
		emfInjector.inject(hdlData, xmiResource);

		// ATL transformation
		InputStream asm = new FileInputStream("org.xtext.hal/model/Pal.asm");
		EMFVMLauncher launcher = new EMFVMLauncher();

		HashMap<String,Object> options = new HashMap<String,Object>();
		options.put("showSummary", "true");
		options.put("step", "true");
		
		launcher.initialize(Collections.<String, Object> emptyMap());
		launcher.addInModel(hdlData, "IN", "hdl");
		launcher.addOutModel(palData, "OUT", "pal");
		launcher.launch(ILauncher.DEBUG_MODE, new NullProgressMonitor(), options, asm);

		// get output
		Resource t_palData = palData.getResource();
		t_palData.setURI(URI.createURI("palData.xmi")); // Exception in thread "main" java.lang.NullPointerException
		t_palData.save(null);
		
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
		
	}
}

/* Suite des règles à écrire sous la forme propre à l'API
	rule DataType2Type {
	  from
	    d:ClassDiagram!DataType
	     (ClassDiagram!Class.allInstances()
	        ->select(c | c.name = d.name)->isEmpty())
	  to
	    t:Relational!Type (name<-d.name)
	}
	rule Class2Table {
	  from
	    c:ClassDiagram!Class
	  to
	    t:Relational!Table 
	     (name<-c.name, col<-c.attr, owner<-c.owner,       
	      key<-c.attr->select(a | a.name.endsWith('Id')))
	}
	rule SingleValuedAttribute2Column {
	  from
	    a:ClassDiagram!Attribute
	     (not a.multiValued)
	  to
	    c:Relational!Column 
	     (name<-a.owner.name+'_'+a.name,
	      owner<-a.owner,
	      type<-if a.type.oclIsTypeOf(ClassDiagram!DataType)
	        then a.type else OclUndefined endif)
}
*/
