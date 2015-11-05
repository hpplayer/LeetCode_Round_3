import java.util.*;
/*

Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/

/**
 * Stack solution.
 * 
 * We use a stack to include all unpaird Parentheses
 * If incoming char is ")", then we try to pair top char in stack. 
 * If top char is "(", then we can pop it out, and calculate the distance. If stack is empty now, then i + 1 would be longest valid
 * Parentheses length. If stack is not empty, then the difference between i and index of current top value would be distance. 
 * For other cases, we have to push current char to stack
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Sol1 is a stack solution 
 * Sol2 is a DP solution, which is faster than sol1 as we don't extra operation on stack
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 11:38:30 PM
 */

public class Longest_Valid_Parentheses_p32_sol1 {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            //if this char can pair a char in stack
            if(s.charAt(i) == ')' && (!stack.isEmpty() && s.charAt(stack.peek()) == '(')){
                stack.pop();
                if(stack.isEmpty()){
                    //all prev chars are paired
                    result = i + 1;
                }else{
                    //partial paried, update result if necessary
                    result = Math.max(result, i - stack.peek());
                }
            }else{
                //current char cannot pair char in stack, just push to stack
                stack.push(i);
            }
        }
        
        return result;
    }
}
