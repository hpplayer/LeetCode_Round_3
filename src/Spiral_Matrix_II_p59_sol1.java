/*
59. Spiral Matrix II

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

/**
 * Pointer solution
 * 
 * This is the advanced version of Spiral_Matrix_I_p54_sol1, we just need to change the solution a little bit before we can used in this solution
 * Basically, we create a matrix with n*n, then use a counter to tell us the number that we need to fill next. The remaining part is same with 
 * Spiral_Matrix_I_p54_sol1
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Feb 29, 2016 5:45:05 PM
 */
		
public class Spiral_Matrix_II_p59_sol1 {
    public int[][] generateMatrix(int n) {
        int [][] matrix = new int[n][n];
        
        int left = 0, right = n - 1, top = 0, bot = n - 1;
        
        //counter
        int num = 1;
        
        while(true){
            for(int i = left; i <= right; i++){
                matrix[top][i] = num++;
            }
            //we will move top down, return result if new top > bot
            if(++top > bot) return matrix;
            
            for(int i = top; i <= bot; i++){
                matrix[i][right] = num++;
            }
            //we will move right left, return result if new right < left
            if(--right < left) return matrix;
            
            for(int i = right; i >= left; i--){
                matrix[bot][i] = num++;
            }
            //we will move bot up, return result if new bot < top
            if(--bot < top) return matrix;
            
            for(int i = bot; i >= top; i--){
                matrix[i][left] = num++;
            }
            //we will move left right, return result if new left > right
            if(++left > right) return matrix;
        }
    }
}
