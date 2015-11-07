import java.util.*;
/**
 * DP solution
 * 
 * This solution is similar to Longest_Valid_Parentheses_p32_sol2
 * 
 * We just create a dp array, the value in dp array means the longest valid parentheses ended at current cell. 
 * We will only look at char that can be the end of parentheses, like ), ], }. We firstly check the char before current char, if paired, we update the length
 * accordingly. If not paired, we will check the char before the previous paired char. If paired we update the length accordingly
 * Finally we just check if the last char in input can cover whole length of input s
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * @author hpPlayer
 * @date Nov 7, 2015 2:39:23 AM
 */
public class Valid_Parentheses_p20_sol2 {
    public boolean isValid(String s) {
        Map<Character, Character> hs = new HashMap<Character, Character>();
        hs.put(')', '(');
        hs.put(']', '[');
        hs.put('}', '{');
        
        int[] dp = new int[s.length()];
        
        for(int i = 1; i < s.length(); i++){
            //we skip all chars that cannot pair previous char
            if(!hs.containsKey(s.charAt(i))) continue;
            char c = s.charAt(i);
            if(s.charAt(i-1) == hs.get(c)){
                //if current char can pair the char before it
                dp[i] = 2;
                //lets check if we can extend it
                if(i - 2 >= 0) dp[i] += dp[i-2];
            }else{
                //check the char before previous paired chars
                int j = i - dp[i-1] - 1;
                if(j >= 0 && s.charAt(j) == hs.get(c)){
                    //if current char can pair the char before previous paired chars
                    //then we can further extend the pair by 2
                    dp[i] = dp[i-1] + 2;
                    //lets check if we can extend it
                    if(j-1 >= 0) dp[i] += dp[j-1];
                }
            }
        }
        
        return dp[s.length()-1] == s.length();
    }
}
