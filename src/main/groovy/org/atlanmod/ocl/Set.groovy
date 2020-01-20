package org.atlanmod.ocl

interface Set<T> {

/*
    union(s : Set(T)) : Set(T)
The union of self and s.
post: result->forAll(elem | self->includes(elem) or s->includes(elem)) post: self ->forAll(elem | result->includes(elem))
post: s ->forAll(elem | result->includes(elem))
union(bag : Bag(T)) : Bag(T)
The union of self and bag.
post: result->forAll(elem | result->count(elem) = self->count(elem) + bag->count(elem)) post: self->forAll(elem | result->includes(elem))
post: bag ->forAll(elem | result->includes(elem))
= (s : Set(T)) : Boolean
Evaluates to true if self and s contain the same elements. post: result = (self->forAll(elem | s->includes(elem)) and
s->forAll(elem | self->includes(elem)) )
intersection(s : Set(T)) : Set(T)
The intersection of self and s (i.e., the set of all elements that are in both self and s). post: result->forAll(elem | self->includes(elem) and s->includes(elem))
post: self->forAll(elem | s ->includes(elem) = result->includes(elem))
post: s ->forAll(elem | self->includes(elem) = result->includes(elem))
intersection(bag : Bag(T)) : Set(T)
The intersection of self and bag.
post: result = self->intersection( bag->asSet )
– (s : Set(T)) : Set(T)
The elements of self, which are not in s.
post: result->forAll(elem | self->includes(elem) and s->excludes(elem))

post: self ->forAll(elem | result->includes(elem) = s->excludes(elem))
including(object : T) : Set(T)
The set containing all elements of self plus object.
post: result->forAll(elem | self->includes(elem) or (elem = object)) post: self- >forAll(elem | result->includes(elem))
post: result->includes(object)
excluding(object : T) : Set(T)
The set containing all elements of self without object.
post: result->forAll(elem | self->includes(elem) and (elem <> object)) post: self- >forAll(elem | result->includes(elem) = (object <> elem)) post: result->excludes(object)
symmetricDifference(s : Set(T)) : Set(T)
The sets containing all the elements that are in self or s, but not in both. post: result->forAll(elem | self->includes(elem) xor s->includes(elem)) post: self->forAll(elem | result->includes(elem) = s ->excludes(elem)) post: s ->forAll(elem | result->includes(elem) = self->excludes(elem))
count(object : T) : Integer
The number of occurrences of object in self. post: result <= 1
flatten() : Set(T2)
Redefines the org.atlanmod.ocl.Collection operation. If the element type is not a collection type, this results in the same set as self. If the element type is a collection type, the result is the set containing all the elements of all the recursively flattened elements of self.
post: result = if self.oclType().elementType.oclIsKindOf(CollectionType) then self->iterate(c; acc : Set(T2) = Set{} |
acc->union(c->flatten()->asSet() ) ) else
self endif
asSet() : Set(T)
Redefines the org.atlanmod.ocl.Collection operation. A Set identical to self. This operation exists for convenience reasons. post: result = self
asOrderedSet() : OrderedSet(T)
Redefines the org.atlanmod.ocl.Collection operation. An OrderedSet that contains all the elements from self, in undefined order. post: result->forAll(elem | self->includes(elem))
asSequence() : Sequence(T)
Redefines the org.atlanmod.ocl.Collection operation. A Sequence that contains all the elements from self, in undefined order. post: result->forAll(elem | self->includes(elem))
post: self->forAll(elem | result->count(elem) = 1)
asBag() : Bag(T)
Redefines the org.atlanmod.ocl.Collection operation. The Bag that contains all the elements from self. post: result->forAll(elem | self->includes(elem))
post: self->forAll(elem | result->count(elem) = 1)
     */


/*
Iterator Expressions
 */

    // select()
    // reject()
    // collectNested()
    // sortedBy()


}
