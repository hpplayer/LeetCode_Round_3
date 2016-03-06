/**
 * DP solution
 * 
 * Basic idea is same with sol1, but now we use rolling row technique to reduce space complexity to O(n)
 * We update dp[i] value based on old dp[i] value, dp[i-1] value and prev value which represents top-left value
 * 
 * Time complexity: O(mn)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Mar 5, 2016 5:48:16 PM
 */
public class Maximal_Square_p221_sol2 {
    public int maximalSquare(char[][] matrix) {
        //dp solution, using a dp array with prev variable to hold the dp value of left, top and top-left
        
        //boundary check
        if(matrix.length == 0) return 0;;
        
        int prev = 0;
        int[] dp = new int[matrix[0].length];
        
        int result = 0;
        
        //intialize dp array
        for(int i = 0; i < matrix[0].length; i++){
            if(matrix[0][i] == '1'){
                result = 1;
                dp[i] = 1;
            }
        }
        
        //update dp array 
        for(int i = 1; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                int left = j == 0? 0 : dp[j-1]; 
                //we need use prev but we also need update prev, so use an extra variabe to hold old dp value
                int temp = dp[j];
                //if matrix value is 0, then dp value is 0, otherwise we update dp value based on left, top and left-top
                dp[j] = matrix[i][j] == '0'? 0 : Math.min(left, Math.min(dp[j], prev)) + 1;
                prev = temp;
                
                //update result if needed
                result = Math.max(result, dp[j]);
            }
        }
        
        return result * result;
    }
}
