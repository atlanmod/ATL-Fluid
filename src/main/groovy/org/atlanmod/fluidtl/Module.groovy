package org.atlanmod.fluidtl

import org.eclipse.emf.ecore.EPackage

interface Module {
    FromPackages create(EPackage model);
}
