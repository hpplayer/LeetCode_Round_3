import java.util.*;

/*
Unique Paths

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
*/

/**
 * Classic DP problem
 * 
 * The path to reach each cell is the sum of the path reach cell above and cell left.
 * So we can simply build a DP matrix to get the sum.
 * Since each time we only use value left and value above, actually we just need a rolling array (see uniquePaths2())
 * 
 * Time complexity is O(mn)
 * Space complexity before using rolling row is O(mn), after using rolling row is O(n)
 * 
 * @author hpPlayer
 * @date Nov 3, 2015 11:48:33 PM
 */
public class Unique_Paths_I_p62_sol1 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        //initialization, set two boundary edges
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        
        //the paths to reach each cell is the sum of the path reach cell above(dp[i-1][j]) and cell left(dp[i][j-1])
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        //return the value in last cell
        return dp[m-1][n-1];
    }
    
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        
        Arrays.fill(dp, 1);
        
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] = dp[j] + dp[j-1];
            }
        }
        
        //return the value in last cell
        return dp[n-1];
    }
}
