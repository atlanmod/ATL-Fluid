package org.atlanmod.ocl

interface OrderedSet<T> {

    /*
    append (object: T) : OrderedSet(T)
The set of elements, consisting of all elements of self, followed by object. post: result->size() = self->size() + 1
post: result->at(result->size() ) = object
post: Sequence{1..self->size() }->forAll(index : Integer |
result->at(index) = self ->at(index))
prepend(object : T) : OrderedSet(T)
The sequence consisting of object, followed by all elements in self. post: result->size = self->size() + 1
post: result->at(1) = object
post: Sequence{1..self->size()}->forAll(index : Integer |
self->at(index) = result->at(index + 1))
insertAt(index : Integer, object : T) : OrderedSet(T)
The set consisting of self with object inserted at position index. post: result->size = self->size() + 1
post: result->at(index) = object
post: Sequence{1..(index - 1)}->forAll(i : Integer |
self->at(i) = result->at(i))
post: Sequence{(index + 1)..self->size()}->forAll(i : Integer |
self->at(i) = result->at(i + 1))
subOrderedSet(lower : Integer, upper : Integer) : OrderedSet(T)
The sub-set of self starting at number lower, up to and including element number upper. pre : 1 <= lower
pre : lower <= upper
pre : upper <= self->size()
post: result->size() = upper -lower + 1
post: Sequence{lower..upper}->forAll( index |
result->at(index - lower + 1) = self->at(index))

 at(i : Integer) : T
The i-th element of self.
pre : i >= 1 and i <= self->size()
indexOf(obj : T) : Integer
The index of object obj in the sequence. pre : self->includes(obj)
post : self->at(i) = obj
first() : T
The first element in self. post: result = self->at(1)
last() : T
The last element in self.
post: result = self->at(self->size() )
reverse() : OrderedSet(T)
The ordered set of elements with same elements but with the opposite order. post: result->size() = self->size()
sum() : T
Redefines the org.atlanmod.ocl.Collection operation to remove the requirement for the + operation to be associative and/or commutative, since the order of evaluation is well-defined by the iteration over an ordered collection.
asSet() : Set(T)
Redefines the Set operation. Returns a Set containing all of the elements of self, in undefined order.
asOrderedSet() : OrderedSet(T)
Redefines the Set operation. An OrderedSet identical to self. post: result = self
post: Sequence{1..self.size()}->forAll(i | result->at(i) = self->at(i))
asSequence() : Sequence(T)
Redefines the Set operation. A Sequence that contains all the elements from self, in the same order. post: Sequence{1..self.size()}->forAll(i | result->at(i) = self->at(i))
asBag() : Bag(T)
Redefines the Set operation. The Bag that contains all the elements from self, in undefined order.
     */

    /*
Iterator Expressions
 */

    // select()
    // reject()
    // collectNested()
    // sortedBy()



}
