/**
 * Stepwise solution
 * 
 * Since the input matrix has a perfect sorting properties that for each cell:
 * below cell always > curr cell and left cell always < curr cell
 * We can put the start cell in a corer case like right top or bot left, then move cell smartly
 * 
 * Time complexity: O(m + n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 15, 2016 11:14:51 PM
 */
public class Search_a_2D_Matrix_I_p74_sol2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //stepwise solution. Since input matrix has such properties that below cell > curr cell while left cell <
        //curr cell, we can compare target with curr cell to move cell wisely
        
        //boundary check
        if(matrix.length == 0) return false;
        
        int row = 0, col = matrix[0].length - 1;
        
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] == target){
                //target found
                return true;
            }else if(matrix[row][col] < target){
                //too small, move down
                row++;
            }else{
                //too large, move left
                col--;
            }
        }
        
        
        return false;
    }
}
