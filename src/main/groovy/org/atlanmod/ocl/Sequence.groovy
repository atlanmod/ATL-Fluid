package org.atlanmod.ocl

interface Sequence {

    /*
    count(object : T) : Integer
The number of occurrences of object in self. = (s : Sequence(T)) : Boolean
True if self contains the same elements as s in the same order. post: result = Sequence{1..self->size()}->forAll(index : Integer |
self->at(index) = s->at(index)) and
self->size() = s->size()
union (s : Sequence(T)) : Sequence(T)
The sequence consisting of all elements in self, followed by all elements in s. post: result->size() = self->size() + s->size()
post: Sequence{1..self->size()}->forAll(index : Integer |
self->at(index) = result->at(index)) post: Sequence{1..s->size()}->forAll(index : Integer |
s->at(index) = result->at(index + self->size() )))
flatten() : Sequence(T2)
Redefines the org.atlanmod.ocl.Collection operation. If the element type is not a collection type, this results in the same sequence as self. If the element type is a collection type, the result is the sequence containing all the elements of all the recursively flattened elements of self. The order of the elements is partial.
post: result = if self.oclType().elementType.oclIsKindOf(CollectionType) then self->iterate(c; acc : Sequence(T2) = Sequence{} |
acc->union(c->flatten()->asSequence() ) ) else
self endif
append (object: T) : Sequence(T)
The sequence of elements, consisting of all elements of self, followed by object. post: result->size() = self->size() + 1
post: result->at(result->size() ) = object

post: Sequence{1..self->size() }->forAll(index : Integer | result->at(index) = self ->at(index))
prepend(object : T) : Sequence(T)
The sequence consisting of object, followed by all elements in self. post: result->size = self->size() + 1
post: result->at(1) = object
post: Sequence{1..self->size()}->forAll(index : Integer |
self->at(index) = result->at(index + 1))
insertAt(index : Integer, object : T) : Sequence(T)
The sequence consisting of self with object inserted at position index. post: result->size = self->size() + 1
post: result->at(index) = object
post: Sequence{1..(index - 1)}->forAll(i : Integer |
self->at(i) = result->at(i))
post: Sequence{(index + 1)..self->size()}->forAll(i : Integer |
self->at(i) = result->at(i + 1))
subSequence(lower : Integer, upper : Integer) : Sequence(T)
The sub-sequence of self starting at number lower, up to and including element number upper. pre : 1 <= lower
pre : lower <= upper
pre : upper <= self->size()
post: result->size() = upper -lower + 1
post: Sequence{lower..upper}->forAll( index |
result->at(index - lower + 1) = self->at(index))
at(i : Integer) : T
The i-th element of sequence.
pre : i >= 1 and i <= self->size()
indexOf(obj : T) : Integer
The index of object obj in the sequence. pre : self->includes(obj)
post : self->at(i) = obj
first() : T
The first element in self. post: result = self->at(1)
last() : T
The last element in self.
post: result = self->at(self->size() )
including(object : T) : Sequence(T)
The sequence containing all elements of self plus object added as the last element. post: result = self.append(object)
excluding(object : T) : Sequence(T)
The sequence containing all elements of self apart from all occurrences of object.
The order of the remaining elements is not changed. post:result->includes(object) = false
post: result->size() = self->size() - self->count(object) post: result = self->iterate(elem; acc : Sequence(T)
= Sequence{}|
if elem = object then acc else acc->append(elem) endif )
reverse() : Sequence(T)
The sequence containing the same elements but with the opposite order. post: result->size() = self->size()
sum() : T
Redefines the org.atlanmod.ocl.Collection operation to remove the requirement for the + operation to be associative and/or commutative, since the order of evaluation is well-defined by the iteration over an ordered collection.
asBag() : Bag(T)
Redefines the org.atlanmod.ocl.Collection operation. The Bag containing all the elements from self, including duplicates. post: result->forAll(elem | self->count(elem) = result->count(elem) )
post: self->forAll(elem | self->count(elem) = result->count(elem) )
asSequence() : Sequence(T)
Redefines the org.atlanmod.ocl.Collection operation. The Sequence identical to the object itself. This operation exists for convenience reasons.
post: result = self
asSet() : Set(T)
Redefines the org.atlanmod.ocl.Collection operation. The Set containing all the elements from self, with duplicates removed. post: result->forAll(elem | self ->includes(elem))
post: self ->forAll(elem | result->includes(elem))
asOrderedSet() : OrderedSet(T)
Redefines the org.atlanmod.ocl.Collection operation. An OrderedSet that contains all the elements from self, in the same order, with duplicates removed.
post: result->forAll(elem | self ->includes(elem))

post: self ->forAll(elem | result->includes(elem)) post: self ->forAll(elem | result->count(elem) = 1) post: self ->forAll(elem1, elem2 |
self->indexOf(elem1) < self->indexOf(elem2)
implies result->indexOf(elem1) < result->indexOf(elem2) )
 */


    /*
Iterator Expressions
 */

    // select()
    // reject()
    // collectNested()
    // sortedBy()

}
