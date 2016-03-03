/**
 * DP solution
 * 
 * 
 * This problem can be easily solved by applying DP solution with the dp matrix
 * But we observe that we only need the dp value from i - 1 layer, therefore we can further optimize the space complexity to O(1)
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 2, 2016 5:47:32 PM
 */
public class Paint_House_I_p256_sol2 {
    public int minCost(int[][] costs) {
        if(costs.length == 0) return 0;
        
        int m = costs.length, n = costs[0].length;
        
        int[][] dp = new int[m][n];
        
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        
        for(int i = 1; i < m; i++){
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
        }
        
        return Math.min(dp[m-1][0], Math.min(dp[m-1][2], dp[m-1][1]));
    }
}
