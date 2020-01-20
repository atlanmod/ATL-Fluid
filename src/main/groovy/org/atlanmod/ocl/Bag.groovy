package org.atlanmod.ocl

import org.ter.ocl.Set

interface Bag<E> extends Collection<E> {


    /**
     * operator "="
     *
     * True if self and bag contain the same elements, the same number of times.
     * post: result = (self->forAll(elem | self->count(elem) = bag->count(elem)) and
     * bag->forAll(elem | bag->count(elem) = self->count(elem)) )
     *
     * @param other
     * @return
     */
    public Boolean equals(org.ter.ocl.Bag<E> other);

    /**
     * union(bag : Bag(T)) : Bag(T)
     * The union of self and bag.
     *
     * post: result->forAll( elem | result->count(elem) = self->count(elem) + bag->count(elem))
     * post: self ->forAll( elem | result->count(elem) = self->count(elem) + bag->count(elem))
     * post: bag ->forAll( elem | result->count(elem) = self->count(elem) + bag->count(elem))
     *
     * @param other
     * @return
     */
    public org.ter.ocl.Bag<E> union(org.ter.ocl.Bag<E> other);

    /**
     * union(set : Set(T)) : Bag(T)
     *
     * The union of self and set.
     *
     * post: result->forAll(elem | result->count(elem) = self->count(elem) + set->count(elem))
     * post: self ->forAll(elem | result->count(elem) = self->count(elem) + set->count(elem))
     * post: set ->forAll(elem | result->count(elem) = self->count(elem) + set->count(elem))
     *
     * @param set
     * @return
     */
    public org.ter.ocl.Bag<E> union(Set<E> set);

    /*

intersection(bag : Bag(T)) : Bag(T)
The intersection of self and bag. post: result->forAll(elem |
result->count(elem) = self->count(elem).min(bag->count(elem)) ) post: self->forAll(elem |
result->count(elem) = self->count(elem).min(bag->count(elem)) ) post: bag->forAll(elem |
result->count(elem) = self->count(elem).min(bag->count(elem)) )
intersection(set : Set(T)) : Set(T)
The intersection of self and set.
post: result->forAll(elem|result->count(elem) = self->count(elem).min(set->count(elem)) ) post: self ->forAll(elem|result->count(elem) = self->count(elem).min(set->count(elem)) ) post: set ->forAll(elem|result->count(elem) = self->count(elem).min(set->count(elem)) )
including(object : T) : Bag(T)
The bag containing all elements of self plus object. post: result->forAll(elem |
if elem = object then
result->count(elem) = self->count(elem) + 1
else
result->count(elem) = self->count(elem)
endif)
post: self->forAll(elem |
if elem = object then
result->count(elem) = self->count(elem) + 1
else
 result->count(elem) = self->count(elem) endif)
excluding(object : T) : Bag(T)
The bag containing all elements of self apart from all occurrences of object. post: result->forAll(elem |
if elem = object then result->count(elem) = 0
else
result->count(elem) = self->count(elem)
endif)
post: self->forAll(elem |
if elem = object then result->count(elem) = 0
else
result->count(elem) = self->count(elem)
endif)
count(object : T) : Integer
The number of occurrences of object in self. flatten() : Bag(T2)
Redefines the org.atlanmod.ocl.Collection operation. If the element type is not a collection type, this results in the same bag as self. If the element type is a collection type, the result is the bag containing all the elements of all the recursively flattened elements of self.
post: result = if self.oclType().elementType.oclIsKindOf(CollectionType) then self->iterate(c; acc : Bag(T2) = Bag{} |
acc->union(c->flatten()->asBag() ) ) else
self endif
asBag() : Bag(T)
Redefines the org.atlanmod.ocl.Collection operation. A Bag identical to self. This operation exists for convenience reasons. post: result = self
asSequence() : Sequence(T)
Redefines the org.atlanmod.ocl.Collection operation. A Sequence that contains all the elements from self, in undefined order. post: result->forAll(elem | self->count(elem) = result->count(elem))
post: self ->forAll(elem | self->count(elem) = result->count(elem))
asSet() : Set(T)
Redefines the org.atlanmod.ocl.Collection operation. The Set containing all the elements from self, with duplicates removed. post: result->forAll(elem | self ->includes(elem))

post: self ->forAll(elem | result->includes(elem))
asOrderedSet() : OrderedSet(T)
Redefines the org.atlanmod.ocl.Collection operation. An OrderedSet that contains all the elements from self, in undefined order, with duplicates removed.
post: result->forAll(elem | self ->includes(elem)) post: self ->forAll(elem | result->includes(elem)) post: self ->forAll(elem | result->count(elem) = 1)
 */

    /*
Iterator Expressions
 */

    // select()
    // reject()
    // collectNested()
    // sortedBy()

}
