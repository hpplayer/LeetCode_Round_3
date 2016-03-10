import java.util.*;

/*
150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/ 

/**
 * Stack solution
 * 
 * Here we use an int stack to contains all operands in the input. If we found next input is an operator, then we pop two operands from the stack,
 * and calculate the result, and push to stack again. Finally we will only have one operand left in the stack which will be our final solution
 * 
 * Time complexity:O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 10:39:18 PM
 */
public class Evaluate_Reverse_Polish_Notation_p150_sol1 {
    public int evalRPN(String[] tokens) {
        //stack solution. Since operator always come after two operands, we can use a stack to store the values. Then we calculate the 
        //result and push to stack again. Finally we shall only have one operand left in the stack, which will be our solution
        
    	//boundary check
    	if(tokens.length == 0) return 0;
    	
        Stack<Integer> stack = new Stack<Integer>();
        
        HashSet<String> operators = new HashSet<String>(Arrays.asList("*", "/", "+", "-"));
        
        for(String s : tokens){
        	if(!operators.contains(s)){
        		//an operand, push to stack
        		stack.push(Integer.valueOf(s));
        	}else{
        		//get prev two operands from stack
        		int num2 = stack.pop();
        		int num1 = stack.pop();
        		
        		//we push calculation result to stack again
        		if(s.equals("+")){
        			stack.push(num1 + num2);
        		}else if(s.equals("*")){
        			stack.push(num1 * num2);
        		}else if(s.equals("/")){
        			stack.push(num1 / num2);
        		}else{
        			stack.push(num1 - num2);
        		}
        	}
        }
        
        return stack.pop();
    }
}
