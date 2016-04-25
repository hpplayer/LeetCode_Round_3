/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

[
    [3,  0, 1,  4, 2],
    [5,  6, 3,  2, 1],
    [1, ~2, 0,  1, 5],
    [4,  1, 0,  1, 7],
    [1,  0, 3, 0~, 5]
]
            		  
Range Sum Query 2D
The above rectangle (with the ~) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ¡Ü row2 and col1 ¡Ü col2.
*/

/**
 * BIT solution
 * 
 * We extend BIT solution that used in 1D version (Range_Sum_Query_1D_Mutable_p307_sol2) to 2D version to solve
 * this problem. 
 * BIT split indexed of input to several groups based on binary representation. Therefore we can easily apply 
 * BIT concepts to 2D input. Therefore this solution is very similar to 1D version, but now we change BIT[] to
 * BIT[][], and need use nested loops to update and get_sum from BIT[][].
 * 
 * Since build() is very similar to update(), we combine them together in this solution
 * 
 * Time complexity:
 * Build tree: O(m * n * logM * logN)
 * update tree: O(logM * logN)
 * getSum: O(logM * logN)
 * 
 * Space complexity: 
 * O(m * n)
 * 
 * Remark:
 * Theoretically, we can also solve this problem by 2D-segment tree. But it is not intuitive to apply 2D-segment tree, so I only
 * include BIT solution here.
 * 
 * @author hpPlayer
 * @date Apr 24, 2016 10:36:56 PM
 */
public class Range_Sum_Query_2D_Mutable_P308_sol1 {
	public static void main(String[] args){
		int[][] matrix = { {3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
		Range_Sum_Query_2D_Mutable_P308_sol1 test = new Range_Sum_Query_2D_Mutable_P308_sol1(matrix);
		System.out.println(test.sumRegion(0, 0, 1, 1));
		System.out.println(test.sumRegion(2, 1, 4, 3));
		test.update(3, 2, 2);
		System.out.println(test.sumRegion(2, 1, 4, 3));
	}
    //BIT solution. BIT actually provides a way to split input smartly (by manipulating binary representations), so we can partially update the sum[] and
    //easily found all cells that will be affected by the update
    //So we can extend BIT in 1D version to 2D version and use BIT to split input matrix smartly
    
    int m, n;
    int[][] BIT, nums;
    
    public Range_Sum_Query_2D_Mutable_P308_sol1(int[][] matrix) {
        //boundary check
        if(matrix == null || matrix.length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        //same as 1D version, we need dummy nodes for cols and rows, therefore we need m + 1 and n + 1 len
        BIT = new int[m + 1][n + 1];
        //Mirror of matrix, since we combine build() and update() together, all values in nums[][] will be
        //updated in update(). So here we need create an empty matrix for nums, instead of assigning it to matrix directly
        nums = new int[m][n];
        
        //Since update() and build() are very similar, this time we combine them together
        //we will update BIT[][] based on each input cell
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int diff = val - nums[row][col];
        nums[row][col] = val;
        
        //we use i += i & - i and j += j & -j to update all cells belong to same group. 
        //we need to cover all cells in matrix therefore each inner loop will start with col + 1,
        //so we need use another variables in loop to keep original col and row value unchanged
        for(int i = row + 1; i <= m; i += i & -i){
            for(int j = col + 1; j <= n; j += j & -j){
                BIT[i][j] += diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //the way we used to get sum of target area is similar to immutable version.
        //we get the sum of area ended at (row2, col2), then subtract sum of area ended at (row1-1, col2) and (row2, col1-1), since we subtract overlap (ended at (row1-1, col1-1) area twice, we need add overlap back
        return getSum(row2, col2) - getSum(row1-1, col2) - getSum(row2, col1-1) + getSum(row1-1, col1-1);
    }
    
    public int getSum(int row, int col){
        int sum = 0;
        //we use i -= i & -i and j -= j & -j to get parent nodes, so those cells will cover all range of input
        for(int i = row + 1; i > 0; i -= i & -i){
            for(int j = col + 1; j > 0; j -= j & -j){
                sum += BIT[i][j];
            }
        }
        return sum;
    }
}
//Your NumMatrix object will be instantiated and called as such:
//NumMatrix numMatrix = new NumMatrix(matrix);
//numMatrix.sumRegion(0, 1, 2, 3);
//numMatrix.update(1, 1, 10);
//numMatrix.sumRegion(1, 2, 3, 4);