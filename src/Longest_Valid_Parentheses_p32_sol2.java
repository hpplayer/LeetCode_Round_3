/**
 * DP solution
 * 
 * We create a dp array to store the longest valid parentheses ended at each cell
 * So we will only look at char that is able to compose valid parentheses.
 * Obviously, those char would only be ")".
 * We firstly check the char before ")", if it is "(", then we at least have a pair of len 2
 * Then we check the dp value before "(" to see if we can further extend this valid Parentheses
 * If the char before ")" is still a ")", then we will get the char before the previous valid Parentheses to see if we can find a match
 * If we can, then we at least have a valid parentheses of (i - j + 1). Then we check the dp value before that char to see if we can 
 * further extend this valid Parentheses.
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 12:10:55 AM
 */
public class Longest_Valid_Parentheses_p32_sol2 {
    public int longestValidParentheses(String s) {
        //DP solution, we only look at char that can pair with previous chars
        
        //dp value is the longest valid parentheses ended at current index 
        int[] dp = new int[s.length()];
        int result = 0;
        
        //we won't have such char in index 0, so we skip
        for(int i = 1; i < s.length(); i++){
            //"(" won't pair with previous chars, we just skip
            if(s.charAt(i) == '(') continue;
            
            if(s.charAt(i-1) == '('){
                //if i-1 is "(", then we can pair it with current char
                //so at least we have a valid Parentheses of len 2
                dp[i] = 2;
                
                //if we have i-2, then we can check if we can further extend this valid Parentheses
                if(i-2 >= 0) dp[i] += dp[i-2];
            }else{
                //if i - 1 is ")", then we can't pair it with current char
                //we have to check the char before the Parentheses ended at i - 1
                int j = i - dp[i-1]-1;
                
                if(j >= 0 && s.charAt(j) == '('){
                    //if that char can pair with current char, then we at least have i - j + 1 len of valid Parentheses
                    dp[i] = i - j + 1;
                    
                    //if we have j - 1, then we can check if we can further extend this valid Parentheses
                    if(j - 1 >= 0) dp[i] += dp[j-1]; 
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
