/**
 * Step-wise solution
 * 
 * We pick the polar cell, which is largest in one direction and smallest in another direction.(like top-right, or bot-left)
 * Then compare its value with target, if smaller, then move in the smallest direction to be larger, if larger, then move in the largest direction to be 
 * smaller 
 * 
 * Time complexity: O(mn)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 15, 2016 9:05:58 PM
 */
public class Search_a_2D_Matrix_II_p240_sol2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //step-wise solution, starts from top right, which is smallest number in last col and largest number in first row
        //then based on the comparsion with target, we can have direction of next step
        
        int x = 0, y = matrix[0].length - 1;
        
        while(x < matrix.length && y >= 0){
            if(matrix[x][y] == target){
                return true;
            }else if(matrix[x][y] < target){
                //too small, move bot
                x++;
            }else{
                //too large, move left
                y--;
            }
        }
        
        return false;
    }
}
