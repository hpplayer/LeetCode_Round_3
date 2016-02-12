import java.util.*;

/*
281. Zigzag Iterator

Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/		

/**
 * Pointer solution
 * 
 * In this solution, we use a naive approach, we just use two global index/list with one flag to read vals from inputs vertically
 * 
 * Sol2 provides a solution that we can use iterator instead, so it is more convenient
 * 
 * @author hpPlayer
 * @date Feb 11, 2016 3:08:32 PM
 */
public class Zigzag_Iterator_p281_sol1 {
    
    boolean inList1;
    int index1;
    int index2;
    List<Integer> list1;
    List<Integer> list2;
    
    public Zigzag_Iterator_p281_sol1(List<Integer> v1, List<Integer> v2) {
        //sol1, use two pointer and a flag to read two input lists
        list1 = v1;
        list2 = v2;
        
        if(!v1.isEmpty()) inList1 = true;
        else inList1 = false;
        
        index1 = 0;
        index2 = 0;
    }

    public int next() {
        if(inList1){
            //if now we need to read list1
            
            //check if we can switch to other list
            if(index2 < list2.size()) inList1 = false;
            
            return list1.get(index1++); 
        }else{
            //if now we need to read list2
            
            //check if we can switch to other list
            if(index1 < list1.size()) inList1 = true;
            
            return list2.get(index2++);
        }
    }

    public boolean hasNext() {
        return index1 < list1.size() || index2 < list2.size();
    }
}
/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

