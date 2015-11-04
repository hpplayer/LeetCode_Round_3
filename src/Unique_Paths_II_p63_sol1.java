/*
Unique Paths II

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

/**
 * Classic DP problem
 * 
 * This problem is similar to problem Unique_Paths_I_p62_sol1. But now if we found there is an obstacle in input, we set the val
 * to 0. The main idea is still looking at the cell above and the cell left. The path to each cell is the sum of path to left cell
 * and the path to above cell (assume current cell in input is not 1) 
 * 
 * We can also use rolling array to reduce the space complexity to O(n), see uniquePathsWithObstacles2()
 * 
 * Time complexity is O(mn)
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 12:10:35 AM
 */
public class Unique_Paths_II_p63_sol1 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //boundary check
        if(obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        
        int[][] dp = new int[m][n];
        if(obstacleGrid[0][0] == 1) return 0;
        
        dp[0][0] = 1;
        
        //initialization
        for(int i = 1; i < m; i++){
            dp[i][0] =  obstacleGrid[i][0] == 1? 0 : dp[i-1][0];   
        }
        
        for(int i = 1; i < n; i++){
            dp[0][i] =  obstacleGrid[0][i] == 1? 0 : dp[0][i-1];   
        }
        
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
            	//if there is an obstacle in input, then we will set the dp value to 0
                dp[i][j] = obstacleGrid[i][j] == 1? 0 : dp[i-1][j] + dp[i][j-1];   
            }
        }
        
        return dp[m-1][n-1];
    }
    
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        //boundary check
        if(obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        
        int[] dp = new int[n];
        if(obstacleGrid[0][0] == 1) return 0;
        
        dp[0] = 1;
        
        //initialization
        for(int i = 1; i < n; i++){
            dp[i] =  obstacleGrid[0][i] == 1? 0 : dp[i-1];   
        }
        
        for(int i = 1; i < m; i++){
            dp[0] = obstacleGrid[i][0] == 1? 0 : dp[0];
            for(int j = 1; j < n; j++){
                dp[j] = obstacleGrid[i][j] == 1? 0 : dp[j] + dp[j-1];   
            }
        }
        
        return dp[n-1];
    }
}
