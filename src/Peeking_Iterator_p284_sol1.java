import java.util.*;
/*
Peeking Iterator

Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:

Think of "looking ahead". You want to cache the next element.

Is one variable sufficient? Why or why not?

Test your design with call order of peek() before next() vs next() before peek().

For a clean implementation, check out Google's guava library source code.
(https://github.com/google/guava/blob/703ef758b8621cfbab16814f01ddcc5324bdea33/guava-gwt/src-super/com/google/common/collect/super/com/google/common/collect/Iterators.java#L1125)

Follow up: How would you extend your design to be generic and work with all types, not just integer?
*/

/**
 * Cache + design problem
 * 
 * To implement the peek() feature, we just need a cache to store peeked values, then each time when we call next(), we will check 
 * if we have peeked values.
 * 
 * 
 * @author hpPlayer
 * @date Feb 8, 2016 9:12:02 PM
 */

public class Peeking_Iterator_p284_sol1 implements Iterator<Integer> {
    
    //just use a cache int and boolean val to implement peeking feature
    Iterator<Integer> it;
    boolean hasPeeked;
    Integer cache;
    
	public Peeking_Iterator_p284_sol1(Iterator<Integer> iterator) {
	    // initialize any member here.
	    it = iterator;
	    hasPeeked = false;
	    cache = null;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        //Notice: to prevent the user use peek() repeatedly at same integer, we need to check if we have set peek value before
        if(!hasPeeked){
            hasPeeked = true;
            cache = it.next();
        }
        
        return cache;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(hasPeeked){
	        //if we have peek value, then we just return it and reset peek related vals
	        Integer temp = cache;
	        cache = null;
	        hasPeeked = false;
	        return temp;
	    }else{
	        //if we have no peek value, then we just return next() in iterator
	        return it.next();
	    }
	}

	@Override
	public boolean hasNext() {
	    return it.hasNext() || hasPeeked;
	}
}
