package org.atlanmod.ocl

import org.ter.ocl.Bag
import org.ter.ocl.Expression
import org.ter.ocl.OrderedSet
import org.ter.ocl.Sequence
import org.ter.ocl.Set

import java.util.function.Predicate

interface Collection<E> {

    /**
     *
     * operator "="
     * True if c is a collection of the same kind as self and contains the same elements in the same
     * quantities and in the same order, in the case of an ordered collection type.
     * @param other
     * @return
     */
    boolean equals(org.ter.ocl.Collection<E> other);

    /**
     * operator "<>"
     * True if c is not equal to self.
     * post: result = not (self = c)
     *
     * @param other
     * @return
     */
    public boolean unequals(org.ter.ocl.Collection<E> other);

    /**
     * The number of elements in the collection self.
     * post: result = self->iterate(elem; acc : Integer = 0 | acc + 1)
     *
     * @return
     */
    public int size();

    /**
     * True if elm is an element of self, false otherwise.
     * post: result = (self->count(elm) > 0)
     * @param elm
     * @return
     */
    public boolean includes(E elm);

    /**
     * True if elm is not an element of self, false otherwise.
     *
     * post: result = (self->count(elm) = 0)
     * @param elm
     * @return
     */
    public boolean excludes(E elm);

    /**
     * The number of times that elm occurs in the collection self.
     *
     * post: result = self->iterate(each; acc : Integer = 0 | if each = elm then acc + 1 else acc endif)
     * @param elm
     * @return
     */
    public int count(E elm);

    /**
     * Does self contain all the elements of c2 ?
     *
     * post: result = c2->forAll(elem | self->includes(elem))
     * @param c2
     * @return
     */
    public boolean includesAll(org.ter.ocl.Collection<E> c2);

    /**
     * Does self contain none of the elements of other ?
     *
     * post: result = other->forAll(elem | self->excludes(elem))
     * @param other
     * @return
     */
    public boolean excludesAll(org.ter.ocl.Collection<E> other);

    /**
     * Is self the empty collection?
     *
     * post: result = ( self->size() = 0 )
     *
     * Note: null->isEmpty() returns 'true' in virtue of the implicit casting from null to Bag{}
     * @return
     */
    public boolean isEmpty();

    /**
     * Is self not the empty collection?
     *
     * post: result = ( self->size() <> 0 )
     *
     * Note: null->notEmpty() returns 'false' in virtue of the implicit casting from null to Bag{}.
     * @return
     */
    public boolean notEmpty();

    /**
     * The element with the maximum value of all elements in self. Elements must be of a type supporting the max
     * operation. The max operation - supported by the elements - must take one parameter of type T and be both
     * associative and commutative. UnlimitedNatural, Integer and Real fulfill this condition.
     *
     * post: result = self->iterate( elem; acc : T = self.first() | acc.max(elem) )
     *
     * @return
     */
    public E max();

    /**
     * The element with the minimum value of all elements in self. Elements must be of a type supporting the min
     * operation. The min operation - supported by the elements - must take one parameter of type T and be both
     * associative and commutative. UnlimitedNatural, Integer and Real fulfill this condition.
     *
     * post: result = self->iterate( elem; acc : T = self.first() | acc.min(elem) )
     * @return
     */
    public E min();

    /**
     * The addition of all elements in self. Elements must be of a type supporting the + operation. The + operation must
     * take one parameter of type T and be both associative: (a+b)+c = a+(b+c), and commutative: a+b = b+a.
     * UnlimitedNatural, Integer and Real fulfill this condition.
     *
     * post: result = self->iterate( elem; acc : T = 0 | acc + elem )
     *
     * If the + operation is not both associative and commutative, the sum expression is not well-formed, which may
     * result in unpredictable results during evaluation. If an implementation is able to detect a lack of
     * associativity or commutativity, the implementation may bypass the evaluation and return an invalid result.
     * @return
     */
    public E sum();

    /**
     * @Todo: correct signature
     *
     * product(c2: org.atlanmod.ocl.Collection(T2)) : Set( Tuple( first: T, second: T2) )
     *
     * The cartesian product operation of self and other.
     *
     *
     * post: result = self->iterate (e1; acc: Set(Tuple(first: T, second: T2)) = Set{} | c2->iterate (e2; acc2:
     * Set(Tuple(first: T, second: T2)) = acc | acc2->including (Tuple{first = e1, second = e2}) ) )
     */
    public Set<E> product(org.ter.ocl.Collection<E> other);


    /**
     * The Set containing all the elements from self, with duplicates removed.
     *
     * post: result->forAll(elem | self ->includes(elem))
     * post: self ->forAll(elem | result->includes(elem))
     * @return
     */
    public Set<E> asSet();

    /**
     * An OrderedSet that contains all the elements from self, with duplicates removed, in an order dependent on the
     * particular concrete collection type.
     *
     * post: result->forAll(elem | self->includes(elem)) post: self ->forAll(elem | result->includes(elem))
     * @return
     */
    public OrderedSet<E> asOrderedSet();

    /**
     * A Sequence that contains all the elements from self, in an order dependent on the particular concrete collection
     * type.
     *
     * post: result->forAll(elem | self->includes(elem))
     * post: self ->forAll(elem | result->includes(elem))
     * @return
     */
    public Sequence<E> asSequence();

    /**
     * The Bag that contains all the elements from self.
     *
     * post: result->forAll(elem | self->includes(elem))
     * post: self ->forAll(elem | result->includes(elem))
     *
     * @return
     */
    public Bag<E> asBag();


    /**
     * @Todo: correct me.
     * flatten() : org.atlanmod.ocl.Collection(T2)
     *
     * If the element type is not a collection type, this results in the same collection as self. If the element type is
     * a collection type, the result is a collection containing all the elements of all the recursively flattened
     * elements of self.
     *
     * [1] Well-formedness rules
     * [2] [1] A collection cannot contain invalid values.
     *
     * context org.atlanmod.ocl.Collection
     * inv: self->forAll(not oclIsInvalid())
     */
    public void flatten();



    /*
    Iterator Expressions
     */

    // closure()
    public boolean exists(Expression<Boolean, E> exp);
    public boolean forAll(Expression<Boolean, E> exp);
    // isUnique()
    // any()
    // one()
    public <T> org.ter.ocl.Collection<T> collect(Expression<T, E> exp);

    public org.ter.ocl.Collection<E> select(Predicate<E> exp);

    public org.ter.ocl.Collection<E> reject(Expression<Boolean, E> exp);








    public org.ter.ocl.Collection<E> including(E element) ;

    public org.ter.ocl.Collection<E> excluding(E element);

}
