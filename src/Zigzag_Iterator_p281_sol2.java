import java.util.*;

/**
 * Iterator solution
 * 
 * In this solution, we use built-in iterator to help us read inputs and use a dequeue structure to help us read inputs vertically
 * Basically, we offer a list iterator to the end of deq, then poll a list iterator from the front of deq. After we read a val from this list iterator,
 * we will offer it to the end of deq again, if it still has values left.
 * 
 * Since iterator is automatic, we can easily generalzied it to k input lists case
 * 
 * @author hpPlayer
 * @date Feb 11, 2016 3:18:02 PM
 */
public class Zigzag_Iterator_p281_sol2 {
    
    Deque<Iterator<Integer>> deq;
    
    public Zigzag_Iterator_p281_sol2(List<Integer> v1, List<Integer> v2) {
        //sol2 use deque with iterator, it can be applied to k inputs case
        //use deq so that we can pop from front and add to the end
        
         deq = new LinkedList<Iterator<Integer>>();
         
         //add to the end
         if(!v1.isEmpty()) deq.offerLast(v1.iterator());
         if(!v2.isEmpty()) deq.offerLast(v2.iterator());
         
    }

    public int next() {
        //poll from the front
        Iterator<Integer> curr = deq.pollFirst();
        int result = curr.next();
        
        //if curr still has int unvisited, we will add it to the list again, to the end
        if(curr.hasNext()) deq.offerLast(curr);
        
        return result;
    }

    public boolean hasNext() {
        return !deq.isEmpty();
    }
}
