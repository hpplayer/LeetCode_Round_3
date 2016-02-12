import java.util.Stack;

/**
 * Stack problem
 * 
 * In this solution we use two stacks to store normal val and min val
 * 
 * @author hpPlayer
 * @date Feb 11, 2016 2:14:57 PM
 */
public class Min_Stack_p155_sol2 {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    public void push(int x) {
        stack.push(x);
        //To save space in min stack, we only push val to min Stack when new incoming val is smaller or equal to top min val in min Stack
        if(minStack.isEmpty() || minStack.peek() >= x) minStack.push(x);
    }

    public void pop() {
        //we will pop top val from min Stack if poped top val from stack is same with top min from minStack
        if(stack.pop().equals(minStack.peek())) minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
