/*
Edit Distance

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

/**
 * DP solution
 * 
 * We create a dp matrix to record the cost of each way to reach current match pair
 * If current char in word1 and word2 are match, then it cost 0, we just copy the value from dp[i-1][j-1]
 * If current char in word1 and word2 are not match, then we should pick the way with min cost that gives the match and plus one
 * 
 * Remark:
 * We are actually updating a dp matrix with size of (m+1) * (n+1), so the time complexity and space complexity are both O(mn)
 * We can reduce the space complexity to O(n+1), but I will not put it here as current solution is already decent
 * 
 * Interleaving_String_97_sol1 is similar to this solution
 * 
 * @author hpPlayer
 * @date Nov 13, 2015 11:11:23 PM
 */
public class Edit_Distance_p72_sol1 {
    public int minDistance(String word1, String word2) {
        //use a dp matrix to record the min cost to reach current match
        
        //here we assume we want to convert from word1 to word2 that is doing operation on word1 only
        
        int m = word1.length(), n = word2.length();
        
        //we put an extra col/row ahead to cover boundary cases(first char or empty string)
        int[][] dp = new int[m+1][n+1];
        
        //empty matches empty, of course 0 cost
        dp[0][0] = 0;
        
        //intialize the first row and first col. There is only one way to convert from word1 to word2 either insertion or deletion
        for(int i = 1; i <= m; i++){
            dp[i][0] = dp[i-1][0] + 1;
        }
        
        for(int i = 1; i <= n; i++){
            dp[0][i] = dp[0][i] + 1;
        }
        
        //update general cells in matrix
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                //index in matrix is 1 unit longer than index in string
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    //match, then 0 cost, we just look up dp value of dp[i-1][j-1]
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //not match, choose the min cost way that can give us a match, 1 cost
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        
        return dp[m][n];
    }
}
