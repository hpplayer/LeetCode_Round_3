import java.util.*;

/*
120. Triangle

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
*/

/**
 * DP solution.
 * 
 * To make thing simpler, we read input matrix from bot to top. We use an array to record the path sums and
 * repeatedly update it to record path sums for each row
 * 
 * Element at index i will come from either i or i + 1 whichever has smaller path sum in the array
 * We only have one boundary case, that is the last index in last row, which does not have i + 1 option. We 
 * need to handle it specifically
 * 
 * Time complexity: O(N^2)
 * Space complexity: O(N)
 * 
 * Remark:
 * We can treat last row as a general case by creating an arry with n + 1 len, but it is a small change so 
 * I did not list it here
 * 
 * @author hpPlayer
 * @date May 24, 2016 10:19:03 PM
 */


public class Triangle_p120_sol1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        //DP solution. rolling row technique
        
        //boundary check
        if(triangle.size() == 0) return 0;
        
        //since size of input matrix is just the size of last row
        //we can create the rolling row based on input matrix size
        int[] dp = new int[triangle.size()];
        
        //we initialize array with last row, so our later loop can be consecutive
        for(int i = 0; i < triangle.size(); i++){
            dp[i] = triangle.get(triangle.size() -1).get(i);
        }
        
        for(int i = triangle.size() - 2; i >= 0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                //dp[j] would either come from j or j + 1 whichever gives smaller path sum
                dp[j] = Math.min(dp[j], dp[j+1]) +  triangle.get(i).get(j);
            }
        }
        
        return dp[0];
    }
}
