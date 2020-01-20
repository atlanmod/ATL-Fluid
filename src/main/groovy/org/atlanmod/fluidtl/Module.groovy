package org.atlanmod.fluidtl

import org.eclipse.emf.ecore.EPackage

interface Module {
    From create(EPackage model);
}
