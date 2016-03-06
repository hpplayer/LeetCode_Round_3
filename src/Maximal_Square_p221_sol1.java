/*
221. Maximal Square

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*/

/**
 * DP solution
 * 
 * we create a dp matrix that records the max square len we can get when the square ended at current cell
 * We need check three directions, top, left and top left. 
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * Since we only use three values, we can actually use rolling row technique to reduce the space complexity to O(n) where n is len of row
 * See sol2 for this optimized version
 * 
 * @author hpPlayer
 * @date Mar 5, 2016 5:25:59 PM
 */

public class Maximal_Square_p221_sol1 {
    public int maximalSquare(char[][] matrix) {
        //dp matrix solution, the max boundary we can get at dp[i][j] is the min value of top, left and top-left dp value then plus 1
        
        //boundary check
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        
        //record the max square boundary
        int result = 0;
        
        //intialize first row and col
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == '1'){
                result = 1;
                dp[i][0] = 1;
            }
        }
        
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == '1'){
                result = 1;
                dp[0][i] = 1;
            }
        }
        
        
        //update dp matrix based on the top, left and left-top value
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                //we only update the dp value when current cell has value 1 i.e. at least we have a square with len 1
                if(matrix[i][j] == '1'){
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]) );
                    result = Math.max(result, dp[i][j]);                    
                }
            }
        }
        
        //final result is product of result
        return result * result;
    }
}
