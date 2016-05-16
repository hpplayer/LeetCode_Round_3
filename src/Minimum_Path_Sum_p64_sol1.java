/*
64. Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which
minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/

/**
 * DP solution
 * 
 * A classical dp solution, we just need to a cache to record the path value in above and left cell,
 * then for current value, we take the way that gives smaller sum
 * 
 * Time complexity: O(mn)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date May 15, 2016 10:39:16 PM
 */
public class Minimum_Path_Sum_p64_sol1 {
	public static void main(String[] args){
		int[][] grid = {{1,2}, {5,6}, {1,1}};
		Minimum_Path_Sum_p64_sol1 test = new Minimum_Path_Sum_p64_sol1();
		test.minPathSum(grid);
	}
    public int minPathSum(int[][] grid) {
        //DP solution. Use a cache to catch the path sum value in above and left cell, and decide which way to
        //come from. Here we reduce the cache matrix into a cache array
        if(grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        
        for(int i = 1; i < n; i++) dp[i] = grid[0][i] + dp[i-1];
        
        for(int i = 1; i < m; i++){
            dp[0] = grid[i][0] + dp[0];
            for(int j = 1; j < n; j++){
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }
        return dp[n-1];
    }
}
