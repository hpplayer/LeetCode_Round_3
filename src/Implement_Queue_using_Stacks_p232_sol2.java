import java.util.Stack;

/**
 * Queue + Stack solution
 * 
 * Intuitive solution. We will always insert new val in forward stack and output old val from backward stack
 * 
 * Time complexity: push O(N), pop O(N), peek O(N), isEmpty() O(1)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 13, 2016 5:53:37 PM
 */
public class Implement_Queue_using_Stacks_p232_sol2 {
    // Push element x to the back of queue.
    Stack<Integer> forward = new Stack<Integer>();
    Stack<Integer> backward = new Stack<Integer>();
    
    public void push(int x) {
        while(!backward.isEmpty()){
            forward.push(backward.pop());
        }
        forward.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        while(!forward.isEmpty()){
            backward.push(forward.pop());
        }
        backward.pop();
    }

    // Get the front element.
    public int peek() {
        while(!forward.isEmpty()){
            backward.push(forward.pop());
        }
        return backward.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return forward.isEmpty() && backward.isEmpty();
    }
}
