/*
Interleaving String

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/	

/**
 * DP solution
 * 
 * We require chars in s3 either comes from s1 or s2. If current char in s3 comes from s1, then we need to check two things. 1) whether char in s1 and
 * s3 are same 2) what is the dp value that we use prev char in s1 to match chars in s3. If both of them satisfied the requirement, then we can set the
 * dp value to true. Same rules apply to s2 and s3 as well. If we can find a match, then we change the value to true meaning we have at least one way
 * to interleave string in this way.Later when we update next match, we can continue following this way to match rest strings. However, if it is not a
 * match, then we set the value to false so later match will not come from this way.
 * 
 * Finally we just need to check the dp value in the dp[m][n], as we must use either the last char in s1 or s2 to end our matching
 * 
 * Remark:
 * 1. For the dp[0][0], we set its value to true. So for the first char, we are free to take the one either from s1 or s2 whichever has the same char
 * 2. For the dp matrix, we don't require all cells to have value true. That's because we may have only one way to match the result, while
 * dp matrix records dp value in all ways 
 * 
 * Time complexity: O(m*n), as we are updating a matrix with O(mn) size
 * Space complexity: O(m*n), as we create a matrix with O(mn) size
 * 
 * Sol1 is dp solution
 * Sol2 is backtracking + memorization solution
 * 
 * Edit_Distance_p72_sol1 is similar to this solution
 * 
 * @author hpPlayer
 * @date Nov 13, 2015 10:06:26 PM
 */

public class Interleaving_String_97_sol1 {
    public boolean isInterleave(String s1, String s2, String s3) {
        //dp solution, we create a dp matrix to check if there is a valid match 
        int m = s1.length(), n = s2.length();
        //we assume s1.length + s2.length = s3.length, otherwise we would have boundary error in below code
        if(m + n != s3.length()) return false;
        
        //we add an extra col/row to handle first char case
        boolean[][] dp = new boolean[m+1][n+1];
        
        //we set dp[0][0] to be true, so we can free to use the first char in s1/s2 which ever is same with first char in s3
        dp[0][0] = true;
        
        //update dp matrix
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                //if we use char in s1, and found a valid way to reach current char in s3
                if(i-1 >= 0 && dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i + j -1)) dp[i][j] = true;
                
                //if we use char in s2, and found a valid way to reach current char in s3
                if(j-1 >= 0 && dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i + j - 1)) dp[i][j] = true;
            }
        }
        
        //since s3 must end with either last char in s1 or s2, we just check dp value in last cell to see if there
        //is a valid way to reach that cell
        return dp[m][n];
    }
}
