package org.atlanmod.ocl

interface Classifier<T> {

    /**
     * Returns a Set containing all of the existing instances of the current classifier
     * (along with instances of all its inherited classifiers).
     *
     * @return
     */
    Set<T> allInstances();

}
