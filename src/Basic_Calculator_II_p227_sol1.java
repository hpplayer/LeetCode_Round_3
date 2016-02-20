/*
227. Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/

/**
 * String problem
 * 
 * Similar to Basic_Calculator_p224_sol1, but now we don't need stack
 * We can simply use a prev variable to handle * and / cases, i.e. we can pull prev result from result, then update it with * or / operator, then 
 * add new result to result. We need update prev variable each time we update the result
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 19, 2016 5:25:34 PM
 */
public class Basic_Calculator_II_p227_sol1 {
	public static void main(String[] args){
		String str = "2*3*4";
		System.out.println(new Basic_Calculator_II_p227_sol1().calculate(str));
	}
    public int calculate(String s) {
        //String solution. We don't need stack in this problem since we can simply use a prev variable to record the previous added val
        //if current sign is * or /, then we can simply use prev variable to deduct prev value from result then do the * or /
        
        //prev helps us do * or /, in other words, we can use prev to change the calculation order 
        int prev = 0;
        int result = 0;
        
        //this time we have 4 possible signs, so we need a char variable
        char sign = '+';
        
        for(int i = 0; i <s.length(); i++){
            char c = s.charAt(i);
            //skip empty space
            if(c == ' ') continue;
            
            if(Character.isDigit(c)){
                //Similar to Basic_Calculator_p224_sol1, we need collect all digits when define an input number    
                int temp = c - '0';
                
                while( i+1 < s.length() && Character.isDigit(s.charAt(i+1))){
                    temp = temp * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                
                //but this time we have 4 possible signs so we need treat them differently
                if(sign == '+'){
                    result += temp;
                    prev = temp;
                }else if(sign == '-'){
                    result -= temp;
                    prev = -temp;
                }else if(sign == '*'){
                    //remove prev from result, then add new one with * operator
                    result = result - prev + prev * temp;
                    //update prev
                    prev = prev * temp;
                }else if(sign == '/'){
                    //same as *
                    result = result - prev + prev / temp;
                    prev = prev / temp;
                }
            }else{
                //must be sign char, update sign
                sign = c;
            }
        }
        
        return result;
    }
}
