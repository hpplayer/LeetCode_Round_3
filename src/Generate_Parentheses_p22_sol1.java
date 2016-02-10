import java.util.*;

/*
22. Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
*/

/**
 * Standard backtracking solution
 * 
 * 
 * We use recursion to enumerate all possible ways to add "(" or ")"
 * If we reach bottom, then add string to result list, if we reach dead end condition, then we return immediately
 * 
 * Sol2 is an iterative DP solution
 * 
 * @author hpPlayer
 * @date Feb 10, 2016 7:17:15 PM
 */

public class Generate_Parentheses_p22_sol1 {
    public List<String> generateParenthesis(int n) {
        //standard backtracking solution
        //we go through all possible ways to add "(" and ")"
        //if reach bot, then add it to result, if reach deadend, we just backtrack
        
        List<String> result = new ArrayList<String>();
        DFS(result, "", n, n);
        
        return result;
    }
    
    public void DFS(List<String> result, String str, int left, int right){
        if(left > right || left < 0 || right < 0){
            //if remaining "(" is more than ")", then there is no need to go any deeper, return now
            return;
        }
        
        if(left == 0 && right == 0){
            //reach bot, add to result
            result.add(str);
            return;
        }
        
        //otherwise try all possible ways to add "(" or ")"
        
        //try add "(" in current position
        DFS(result, str + "(", left - 1, right);
        
        //for ")", we can check if remaining left >= right, in either case, we don't need to go any further
        //we can also remove this if condition
        if(right > left) DFS(result, str + ")", left, right - 1);
        
    }
}
