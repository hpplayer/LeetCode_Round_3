/*
Regular Expression Matching

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") ¡ú false
isMatch("aa","aa") ¡ú true
isMatch("aaa","aa") ¡ú false
isMatch("aa", "a*") ¡ú true
isMatch("aa", ".*") ¡ú true
isMatch("ab", ".*") ¡ú true
isMatch("aab", "c*a*b") ¡ú true
*/


/**
 * DP solution
 * 
 * This problem is similar to problem Wildcard_Matching_p44_sol1
 * 
 * We build a DP table to store previous matching result. For normal char and char ".", we handle them similarly as in p44.
 * For char "*", now it has different meaning. In this problem it represents the usage of preceding element. So now it becmes
 * we can use preceding element 0 times or multiple times. For 0 time, we just look up dp[i][j-2] value, for multiple times,
 * we just look up dp[i-1][j] value but the preceding element must match current char in s
 * 
 * Remark:
 * The difference between this problem and Wildcard_Matching_p44_sol1 is that. In p44, "*" is used as normal char, and here
 * "*" is used as the copy of preceding char. So in p44, we can have cases that "*" appears zero time or multiple times.
 * while in this problem, we can have cases that preceding char is used zero time or multiple times.
 * If we want to keep consistent with p44, we can choose to use preceding element zero times, one times(i.e. ignore *) or multiple times.
 * To keep consistent with Wildcard_Matching_p44_sol1, we can use 3 variables separately. But now we only use two variables, as 
 * one times and multiple times are actually same we just need to look up dp[i-1][j] value
 * 
 * The time complexity is O(mn) as we will scan each cell once
 * The space complexity is O(mn) as we need a m*n matrix to hold dp values
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 1:00:51 AM
 */
public class Regular_Expression_Matching_p10_sol1 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        
        //adding extra col and row so we can handle the case that all previous chars are matched 
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        
        for(int i = 0; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')){
                    //non "*" char, if match, then we check the match result of previous substring  
                    dp[i][j] = dp[i-1][j-1] ;
                }
                
                if(p.charAt(j-1) == '*'){
                    //in this problem, "*" is used to manipulate preceding char
                    //so now we have two cases: 1) use preceding char 0 time 2) use preceding char multiple times

                    boolean zeroUse = j >= 2  && dp[i][j-2];
                    //to use preceding char multiple times, we have to make sure curr char in s matches the preceding char
                    //we just look up the cell above current cell
                    boolean multiUse = (i >= 1 && j >= 2) && (p.charAt(j - 2) == s.charAt(i-1) || p.charAt(j-2) == '.') && dp[i-1][j]; 
                
                    dp[i][j] = zeroUse || multiUse;
                }
            }
        }
        
        return dp[m][n];
    }
}
