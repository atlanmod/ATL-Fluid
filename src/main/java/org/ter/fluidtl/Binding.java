package org.ter.fluidtl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/*
 * Another placeholder ?
 */
public class Binding {
	private String featName;
	private String expression;
	
	public Binding(String featName, String expression) {
		this.featName = featName;
		this.expression = expression;
	}


	public void bind(EStructuralFeature feature) {

	}

	public void bind(String featureName) {

	}
}
