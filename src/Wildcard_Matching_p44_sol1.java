/*
Wildcard Matching

Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ¡ú false
isMatch("aa","aa") ¡ú true
isMatch("aaa","aa") ¡ú false
isMatch("aa", "*") ¡ú true
isMatch("aa", "a*") ¡ú true
isMatch("ab", "?*") ¡ú true
isMatch("aab", "c*a*b") ¡ú false
*/

/**
 * Remember to handle two cases separately. Case 1. general case, we require previous sequences are matched, and now we match "a" with "a"
 * or "a" with "?" Case 2. special case with "*", we can either not use it, so we use dp value in dp[i][j-1], or we can use it multiple time
 * times, so we use dp value in dp[i-1][j]
 * 
 * Remark:
 * "*" is very powerful it can match "", "a", "ab", and other sequences
 * This problem is similar to problem Regular_Expression_Matching_p10_sol1
 * 
 * Sol1 is dp solution, which costs O(mn)
 * Sol2 is iterative backtracking solution, which also costs O(mn). But we can return early if we found unmatched pair, so 
 * Sol2 is actually much faster than sol1
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 9:16:44 PM
 */
public class Wildcard_Matching_p44_sol1 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        dp[0][0] = true;
        
        for(int i = 0; i <= m; i++){
            //j = 0 will always be false (we can't use empty pattern to match non-empty string)
            for(int j = 1; j <= n; j++){
                if(i > 0 &&  (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?')){
                    //general case "a" matches "a" or "a" matches "?"
                    //so we require dp[i-1][j-1] are matched as well
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    //special case, we got "*" in pattern, for such case
                    
                    //not use "*", so we use same char in s match prev char in p
                    boolean zeroUse = dp[i][j-1];
                    
                    //use multiple "*", dp[i-1][j] stores result of using multiple "*" to matches chars in s
                    boolean multiUse = i > 0 && dp[i-1][j];
                    
                    dp[i][j] = zeroUse || multiUse;
                }
            }
        }
        
        
        return dp[m][n];
    }
}
