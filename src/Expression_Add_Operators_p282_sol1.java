import java.util.*;
/*
Expression Add Operators

Given a string that contains only digits 0-9 and a target value, return all possibilities to add operators +, -, or * between the digits
so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/

/**
 * Backtracking problem
 * 
 * We will do DFS on right part of the input num. we take each possible value of right part, and add different operators between this num
 * and left part.
 * To deal with "*", we will use an extra variable "lastNum" so we can take the last inserted value from left part, then
 * multiple it with current right value, then add back to left part
 * To deal with invalid strings like "00XXX", we will check if current substring starts with "0" and has length longer than 1. If it is,
 * then we just return result as it is not a valid number
 * 
 * Time complexity:
 * For input: "0000...00" and target 0, we can have 3^(n-1) strings in output (each gap has 3 choices, and we have n-1 gaps)
 * so the running time should be O(3^(n-1))
 * Space complexity should be same, as we need a list to contain all those strings
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 7:40:09 PM
 */

public class Expression_Add_Operators_p282_sol1 {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<String>();
        
        for(int i = 1; i <= num.length(); i++){
            if(num.charAt(0) == '0' && i > 1) return result;
            long newNum = Long.valueOf(num.substring(0, i));
            DFS(result, newNum + "", num.substring(i), newNum, target, newNum);
        }
        
        return result;
    }
    
    //To prevent overflow, we use long to pass values
    public void DFS(List<String> result, String left, String num, long sum, long target, long lastNum){
        if(num.length() ==0){
            //boundary check
            if(sum == target){
                result.add(left);
            }
            return;
        }
        
        for(int i = 1; i <= num.length(); i++){
            //try each possible number from current input
            
            //skip invalid number (starts with 0 and has len > 1)
            if(num.charAt(0) == '0' && i > 1) return;
            
            long newNum = Long.valueOf(num.substring(0, i));
            
            //"+"
            DFS(result, left + "+" + newNum, num.substring(i), sum + newNum, target, newNum);
            
            //"*"
            DFS(result, left + "*" + newNum, num.substring(i), sum -lastNum + lastNum * newNum, target, lastNum * newNum);
            
            //"-"
            DFS(result, left + "-" + newNum, num.substring(i), sum - newNum, target, -newNum);
            
        }
    }
}
