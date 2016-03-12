/*
73. Set Matrix Zeroes

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

/**
 * Brainstorming + observation solution
 * 
 * To solve the problem in-place, we will make use of first row and first col. Thats because if matrix[i][j] = 0, then we will set matrix[i][0] and
 * matrix[0][j] to zeros anyway. So we can firstly scan the input, and update values in first row/col. In the second scan, we update each cell matrix[i][j]
 * based on matrix[i][0] and matrix[0][j].
 * In case we have zeros in first row/col before update the matrix, we can use two variables to record them, then update first col/row after we have updated
 * remaining matrix
 * 
 * Time complexity: O(mn)
 * Space complexity: O(1) (in-place)
 * 
 * Remark:
 * here we use two exclusive for loops to update first row/col in the end. But we can also merge them into prev for loop that we used to update matrix
 * In this way, we need to update matrix from bot-right to top-left and does not save much time. So I did not list it here
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 11:41:56 AM
 */
public class Set_Matrix_Zeroes_p73_sol1 {
    public void setZeroes(int[][] matrix) {
        //brainstorming solution. Since once we found a cell has 0, we will set its col and row to 0s, including the col cell in first
        //row and row cell in first col. So we scan the input and mark col cell in first row and row cell in first col to be 0 if we 
        //found current cell is 0. In the second scan we just update cell value based on row cell in first col and col cell in first row
        
        //boundary  check
        if(matrix.length == 0) return;
        
        int m = matrix.length, n = matrix[0].length;
        
        //two variables to record if we have 0 in first row/col before we first scan
        boolean setFirstRow = false;
        boolean setFirstCol = false;
        
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                setFirstCol = true;
                break;
            }
        }
        
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == 0){
                setFirstRow = true;
                break;
            }
        }
        
        //begin scan the input matrix, and update cell in first col/row if we found current cell is 0
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }
        
        //now we will update matrix based on the first row/col
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        
        //set first row/col based on prev result
        for(int i = 0; i < m; i++) if(setFirstCol) matrix[i][0] = 0;
        for(int i = 0; i < n; i++) if(setFirstRow) matrix[0][i] = 0;
    }
}
