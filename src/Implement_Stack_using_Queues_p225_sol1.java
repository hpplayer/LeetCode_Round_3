import java.util.*;

/*
225. Implement Stack using Queues

Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

Notes:
-You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size,
and is empty operations are valid.
-Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque
(double-ended queue), as long as you use only standard operations of a queue.
-You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
Update (2015-06-11):
The class name of the Java function had been updated to MyStack instead of Stack.
*/

/**
 * Stack + queue solution
 * 
 * We use the property of queue to make it like a cycle, and always put the newly added list to head
 * Here is a trade off, we can do this in push, then push() costs O(N), and pop()/peek() costs O(1)
 * We can also do this in pop()/peek(), then add() costs O(1). It is hard to say which way is better, it depends on the 
 * nums of push() and pop()/peek(). In OJ, the second way makes the code runs faster, but to make the code more concise,
 * I choose the first way
 * 
 * Time complexity: push(): O(N), peek()/poll(): O(1), isEmpty(): O(1)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 13, 2016 6:41:24 PM
 */
public class Implement_Stack_using_Queues_p225_sol1 {
    Queue<Integer> que = new LinkedList<Integer>();
    
    // Push element x onto stack.
    public void push(int x) {
        //we make que as a loop, each time we will put the newly added num into front
        que.offer(x);
        for(int i = 0; i < que.size() - 1; i++){
            que.offer(que.poll());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        //since newly added num is in front, just return poll() and peek() as it is a normal stack
        que.poll();
    }

    // Get the top element.
    public int top() {
        //same as pop()
        return que.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return que.isEmpty();
    }
}
