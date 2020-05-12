package org.atlanmod.example

import org.atlanmod.fluidtl.Module
import org.atlanmod.fluidtl.Rule
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage
import org.omg.smm.Measurement
import org.omg.smm.SmmPackage
import org.ter.fluidtl.ModuleBuilder

import static org.eclipse.gmt.modisco.java.emf.meta.JavaPackage.CLASS_DECLARATION__NAME
import static org.omg.smm.SmmPackage.MEASUREMENT__NAME


class Main {

    Main() {
        Module module = ModuleBuilder
                .create(SmmPackage.eINSTANCE)
                .from(JavaPackage.eINSTANCE, JavaPackage.eINSTANCE)

        Rule class2mesure = module
                .createRule("class2mesure")
                .from(JavaPackage.CLASS_DECLARATION)
                .to(SmmPackage.MEASURE)
                .bind(MEASUREMENT__NAME, CLASS_DECLARATION__NAME)
    }

    public static void main(String[] args) {
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

        /*

         */
    }
}
