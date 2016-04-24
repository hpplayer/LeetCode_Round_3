/*
304. Range Sum Query 2D - Immutable

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner
(row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3),
which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ¡Ü row2 and col1 ¡Ü col2.
*/

/**
 * DP solution
 * 
 * Similar to 1D solution, but now we need use an extra matrix to store the sub of 2D area from (0,0) to
 * (i, j). Therefore we need to play with matrix-sums to get the correct sum of target area. Basically,
 * we just need to look at the area above, the area on left, and the area on left-top to calculate sum of
 * target area.
 * Since we always calculate the sum from (0, 0) to target index, we may have overlaps during the calculation,
 * therefore we need get to know the sum of left-top area to include the overlap area.
 * 
 * Time complexity:
 * build matrix O(m*n)
 * querySum: O(1) 
 * Space complexity:
 * O(m*n)
 * 
 * Remark:
 * To handle boundary case (first row and first col), this solution creates an dp matrix with m + 1 and n + 1
 * size. So we need + 1 when converting input indexes to dp indexes
 * 
 * @author hpPlayer
 * @date Apr 23, 2016 10:19:37 PM
 */
public class Range_Sum_Query_2D_Immutable_p304_sol1 {
	public static void main(String[] args){
		int[][] matrix = {{8,-4,5}, {-1, 4, 4}, {-2,3,1}, {-4,4,3}};
		Range_Sum_Query_2D_Immutable_p304_sol1 test = new Range_Sum_Query_2D_Immutable_p304_sol1(matrix);
		System.out.println(test.sumRegion(0, 1, 0, 2));
	}
    //DP solution, we pre-cache the sum of area from (0,0) to (i, j), then use addition and subtract to get the 
    //sum of target area
    
    int[][] dp;
    
    public Range_Sum_Query_2D_Immutable_p304_sol1(int[][] matrix) {
        //boundary check
        if(matrix == null || matrix.length == 0) return;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        //to deal with boundary cases, we build a DP matrix with m + 1 and n + 1 size, and we will not use first 
        //row and first col in dp
        dp = new int[m+1][n+1];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //dp is 1 based matrix (not store values in first row and col), so we need + 1
                
                //we add sum of top and left area, then subtract the overlap area (top-left) and 
                //add val from curr input 
                dp[i+1][j+1] = dp[i][j+1] + dp[i+1][j] - dp[i][j] + matrix[i][j];
            }
        }
        
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //to return the sum of target area, we get the sum at [row2 + 1][col2 + 1] (curr) (remember dp matrix is 1-based indexing, but the input is 0-based indexing), sum at [row1][col2+1] (top), sum at [row2+1][col1] (left) and sum at [row1][col1] (top-left)
        //We remove top area, left area from curr area and add back the overlap area(top-left)
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        
    }
}
