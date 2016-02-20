import java.util.Stack;

/*
224. Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
*/

/**
 * Stack problem
 * 
 * We use a stack to help us handle ().
 * We use two variables result and sign to help us record the previous result and previous sign
 * When facing (), we firstly push previous result, then push the sign
 * When exiting (), we firstly pop the sign, then pop the previous result
 * For number input, it may have several digits. So we need scan all digits before add it to our result
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * @author hpPlayer
 * @date Feb 19, 2016 4:35:25 PM
 */
public class Basic_Calculator_p224_sol1 {
	public static void main(String[] args){
		String s1 = "1+23";
		
		System.out.println(new Basic_Calculator_p224_sol1().calculate(s1));
	}
    public int calculate(String s) {
        //stack solution. before we entering "()", we use stack to store previous results.
        //After we finish "()", we will pop previous result from stack then add the value to the result from "()"
    
        //result will be used in to ways: 1) store the temp value from () 2) store the accumulated value we get so far
        int result = 0;
        //we use sign to record the last sign
        int sign = 1;
        //stack is used to help us get the result from ()
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            
            if(Character.isDigit(c)){
                //a number may have several digits, here we wanna get the its value
                int temp = c - '0';
                //to avoid indexing issue, we will use i + 1 to scan the digits
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i+1))){
                    temp = temp * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                System.out.println(temp);
                result += sign * temp;
            }else if(c == '+'){
                sign = 1;
            }else if(c == '-'){
                sign = -1;
            }else if(c == '('){
                //we will get into (), we firstly store previous result to stack, then store the sign before () into the stack
                stack.push(result);
                stack.push(sign);
                //then reset variables
                result = 0;
                sign = 1;
            }else if(c == ')'){
                //we will get out of ')', we firstly pop the sign before(), then pop the previous result from stack and add it
                result = stack.pop() * result + stack.pop();
            }
            //for empty spaces, we do nothing
        }
        
        return result;
    }
}
