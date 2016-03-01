import java.util.*;
/*
54. Spiral Matrix

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

/**
 * Pointer solution
 * 
 * We use 4 pointers as bars. Those bars will help us locate the scope. We firstly add top row, then right col, then bot row, then left col.
 * We will move row/col when we finish current scope. If new row/col exceeds the scope, then we will return result list
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Feb 29, 2016 5:30:41 PM
 */

public class Spiral_Matrix_I_p54_sol1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        //use 4 bars to locate current scope. In each loop, we will move 4 bar once, if we found current movement cause bars surpass its
        //corresponding bar, then we will return result
        
        //boundary check
        if(matrix.length == 0) return new ArrayList<Integer>();
        
        int left = 0, right = matrix[0].length-1, top = 0, bot = matrix.length-1;
        
        List<Integer> result = new ArrayList<Integer>();
        
        while(true){
            for(int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            //we will move top down, return result if new top > bot
            if(++top > bot) return result;
            
            for(int i = top; i <= bot; i++){
                result.add(matrix[i][right]);
            }
            //we will move right left, return result if new right < left
            if(--right < left) return result;
            
            for(int i = right; i >= left; i--){
                result.add(matrix[bot][i]);
            }
            //we will move bot up, return result if new bot < top
            if(--bot < top) return result;
            
            for(int i = bot; i >= top; i--){
                result.add(matrix[i][left]);
            }
            //we will move left right, return result if new left > right
            if(++left > right) return result;
        }
    }
}
