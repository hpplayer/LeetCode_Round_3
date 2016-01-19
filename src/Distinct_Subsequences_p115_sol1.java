/*
Distinct Subsequences

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without
disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

/**
 * DP approach
 * 
 * First of all, lets get understand what this problem is asking for.
 * This problem requires us to find string t in string s allowing remove chars in s. So in the given example t: rabbit s: rabbbit, we just need
 * to remove a "b" in string s to find string t. We have 3 "b"s that can be removed, so there are totally 3 ways to do this.
 * 
 * Here is the idea:
 * we use a dp table to record number of ways to match certain chars in two input strings.
 * If chars are matched, then we can either use current char in string s or we can delete it
 * If chars are unmatched, then we must delete it from s in order to match string t.
 * We update the dp table based on the rule above, and finally return the value record in last box. This value will be the number of total ways
 * to match string s and t by removing chars in s.
 * 
 * Space complexity: O(len(s) * len(t)), since we only use two rows, we can reduce the space by using rolling row technique
 * Time complexity: O(len(s) * len(t)), since we need visit each box in the matrix once to update the value
 * 
 * 
 * Remark:
 * This problem is similar to other string matching problem which also use dp tables. Examples: Edit Distance(p72)
 * 
 * @author hpPlayer
 * @date Jan 18, 2016 9:51:13 PM
 */
public class Distinct_Subsequences_p115_sol1 {
    public int numDistinct(String s, String t) {
        //boundary check
        if(s == null || t == null) return 0;
        
        //to include boundary cases (s is empty or t is empty), we need a matrix with (n+1)*(m+1) size
        int[][] dp = new int[t.length()+1][s.length()+1];
        
        //for first row, which means match empty string t, we have only 1 way to do that i.e. remove all chars
        for(int i = 0; i <= s.length(); i++){
            dp[0][i] = 1;
        }
        
        for(int i = 1; i <= t.length(); i++){
            //for the first box in each row, which means use empty string s, we have 0 way to do that
            dp[i][0] = 0;
            for(int j = 1; j <= s.length(); j++){
                if(s.charAt(j-1) == t.charAt(i-1)){
                    //if chars match, then we can either use current char in s to match current char in t or 
                    //we can skip current char in s
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }else{
                    //if chars do not match, then we must skip current char in s
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        
        
        return dp[t.length()][s.length()];
    }
}
