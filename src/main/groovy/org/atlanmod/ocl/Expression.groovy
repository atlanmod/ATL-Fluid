package org.atlanmod.ocl

interface Expression<T,E> {
    T evaluate(E e);
}
