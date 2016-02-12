import java.util.Stack;

/*
155. Min Stack

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/

/**
 * Stack problem
 * 
 * In this solution, we create an inner class Node which contains information of val and min
 * Then we use a built-in stack to finish all operations.
 * 
 * Remark:
 * We can also use self-created linkedlist to finish those operations
 * 
 * Sol2 does not use inner class, instead, it uses two stacks
 * 
 * @author hpPlayer
 * @date Feb 11, 2016 2:05:53 PM
 */
public class Min_Stack_p155_sol1 {
    class Node{
        int val;
        int min;
        Node(int v, int m){
            val = v;
            min = m;
        }
    }
    Stack<Node> stack = new Stack<Node>();
    public void push(int x) {
        if(stack.isEmpty()){
            stack.push(new Node(x, x));
        }else{
            stack.push(new Node(x, Math.min(stack.peek().min, x)) );
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }
}
