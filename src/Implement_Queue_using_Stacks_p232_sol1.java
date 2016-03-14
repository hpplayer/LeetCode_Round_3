import java.util.*;
/*
232. Implement Queue using Stacks

Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty
operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque
(double-ended queue), as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
*/

/**
 * Queue + stack solution
 * 
 * We use two stacks to implement a valid queue. One stack will be exclusively used to store inputs and the other
 * stack will be exclusively used to store outputs. 
 * Here we use lazy update way to update two stacks, which means we will directly put vals into forward stack
 * not matter whether we still have vals left in backward stack. This is valid since we only read vals from backward
 * stack to output, and we don't need later vals in forward stack at all. Accordingly, in pop() and peek(),
 * we will firstly check if we still have vals left in backward, if yes, then we just poll that val, if no
 * then we need to pop all vals from forward to backward to make sure the order is preserved i.e. the firstly
 * inserted vals in forward stack will be reversed to be the top vals in backward stack
 * 
 * Besides, since peek() and pop() just have a minor difference, here we reuse the same code for both functions
 * 
 * Time complexity: push() O(1), pop() O(N), peek() O(N), empty O(1)
 * Space complexity: O(N)
 * 
 * Remark:
 * Sol2 lists a more intuitive solution that costs O(N) in push()
 * 
 * @author hpPlayer
 * @date Mar 13, 2016 6:01:08 PM
 */

public class Implement_Queue_using_Stacks_p232_sol1 {
    
    Stack<Integer> forward = new Stack<Integer>();
    Stack<Integer> backward = new Stack<Integer>();
    
    // Push element x to the back of queue.
    public void push(int x) {
        //we only push new element into forward. 
        //Here we use laze update method, since we will only output from backward, we don't need to put elements 
        //from backward back to forward
        forward.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        //same operations with peek(), but now we need to remove top element from backward stack
        peek();
        backward.pop();
    }

    // Get the front element.
    public int peek() {
        //if backward is empty, then we need to get new elements from forward
        if(backward.isEmpty()){
            while(!forward.isEmpty()){
                backward.push(forward.pop());
            }
        }
        return backward.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return forward.isEmpty() && backward.isEmpty();
    }
}
