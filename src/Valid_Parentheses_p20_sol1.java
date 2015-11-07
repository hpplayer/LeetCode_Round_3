import java.util.*;

/*
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

/**
 * Stack solution
 * 
 * we use a stack to store unpaird chars. If next incoming char can pair top char in stack, then we can pop it out.
 * Finally we just check if the stack is empty
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Nov 7, 2015 2:29:46 AM
 */
public class Valid_Parentheses_p20_sol1 {
    public boolean isValid(String s) {
        Map<Character, Character> hs = new HashMap<Character, Character>();
        hs.put(')', '(');
        hs.put(']', '[');
        hs.put('}', '{');
        
        Stack<Character> stack = new Stack<Character>();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(hs.containsKey(c) && !stack.isEmpty() && stack.peek() == hs.get(c)){
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
}
