import java.util.*;
/*
341. Flatten Nested List Iterator

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
 [1,4,6].
*/

/**
 * Stack + Iterator solution
 * 
 * We use a stack to store Iterator of lists, so that we can freely move among different lists.
 * The tricky part is covering the case we have nested empty lists. In such case, our Iterator's hasNext()
 * returns true, but actually we dont have anything to return. We don't want to return null for such case,
 * we want hasNext() directly report false. To do that, we have to improve our hasNext(), we will analyze
 * each list, we only put non-empty list into the stack and pop out those empty lists
 * 
 * Why use stack?
 * Input:
 * [ [  [d], e  ], a] 
 * In stack:
 * [ [d], e ], a  => [d], e, a => d, e, a
 * Stack can help us decompose the nested list while maintaining the order
 *   
 * Time complexity: O(N), since each element will be scanned once
 * Space complexity: O(k), where k is depth of deepest nested list
 * 
 * @author hpPlayer
 * @date Jun 6, 2016 10:56:16 PM
 */
public class Flatten_Nested_List_Iterator_p341_sol1 implements Iterator<Integer>{
    
    Stack<Iterator<NestedInteger>> stack;
    //Since we cannot go back in iterator, we use nextInt to cache pre-checked NestedInteger
    NestedInteger nextInt;
    
    public Flatten_Nested_List_Iterator_p341_sol1(List<NestedInteger> nestedList) {
        //stack + iterator solution. We will use stack to store iterators for non-empty lists
        
        stack = new Stack<Iterator<NestedInteger>>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        //we always make sure nextInt is an integer before we update it
        return nextInt.getInteger();
    }

    @Override
    public boolean hasNext() {
        //we will return !hasNext() once stack is empty
        
        while(!stack.isEmpty()){
            //remove iterators for empty lists
            if( !stack.peek().hasNext() ){
                stack.pop();
            }else{
                //pop next NestedInteger and check if it is an int or list
                NestedInteger temp = stack.peek().next();
                
                if(temp.isInteger()){
                    nextInt = temp;
                    return true;
                }else{
                    stack.push( temp.getList().iterator() );
                }
            }
        }
        
        return false;
    }
}
/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */