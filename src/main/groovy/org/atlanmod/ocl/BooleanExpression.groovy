package org.atlanmod.ocl

interface BooleanExpression<T> {
    boolean evaluate(T element);
}
