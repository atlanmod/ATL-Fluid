package org.atlanmod.ocl

interface InteratorExpression<E,O> {
    public O evaluate(E element);
}
