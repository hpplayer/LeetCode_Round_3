import java.util.*;
/*
311. Sparse Matrix Multiplication

Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

/**
 * Math + brainstorming solution
 * 
 * Since the input matrixes are sparse, which include a lot of zeros, we can use tricks to skip those cells and save time
 * In this solution, we just swap the inner two loops in the naive approach.
 * In the naive approach, we fill the result matrix row by row, from left col to right col. For each cell, we use third loop to get the multiplication product
 * However, in sol1, we swap the col loop with the third loop, so that we can still fill the cell value but now we can check if one of the component is zero
 * before we going into the loop. Therefore we can save time
 * 
 * Time complexity: O(mnk), where m is len of A, n is len of B[0], k is len of A[0], which is same to len of B
 * Space complexity: O(mn), where m is len of A and n is len of B[0]
 * 
 * 
 * Remark:
 * Here is how we do matrix multiplication in math:
 * A = [a, b
 * 		c, d]
 * B = [e, f
 *      g, h]
 *       
 * result of A * B = [a*e + b*g, a*f + b * h
 * 					  c*e + d*g, c*f + d * h]
 * 
 * [First row from A * first col from B, First row from A * second col from B
 *  Second row from A * first col from B, second row from A * second col from B]
 *  
 * Sol1 is simple and easy to come up with
 * Sol2 provides another approach that only record the non-zero values in data structure to reduce the time and space
 * 
 * @author hpPlayer
 * @date Mar 8, 2016 12:24:03 PM
 */
public class Sparse_Matrix_Multiplication_p311_sol1 {
    public int[][] multiply(int[][] A, int[][] B) {
        //Math + observation solution. This solution is very close to naive solution but we modify the program a bit to improve the speed
        
        //boundary check
        if(A.length == 0 || B.length == 0) throw new IllegalArgumentException();
        //to do the matrix multiplication, we need A[0].length = B.length, otherwise it is an invalid input
        if(A[0].length != B.length) throw new IllegalArgumentException();
         
        //for matrix multiplication, if the input size is A[a][b], B[c][d], then we require b = c, and result matrix has size C[a][d]
        int[][] result = new int[A.length][B[0].length]; 
        
        //result.length == A.length
        for(int i = 0; i < result.length; i++){
            //we swap the inner loop with mid loop, so we can do zero-check before we go into the loop
            
            //inner loop, we should visit each cell in the row from A and each cell in the col from B.
            //we have A[0].length == B.length
            for(int k = 0; k < A[0].length; k++){
            	//Due to the swapped order of loops, we can skip the third loop, if the input is current cell from A is zero
            	if(A[i][k] == 0) continue;
            	//result[0].length == B[0].length
                for(int j = 0; j < result[0].length; j++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return result;
    }
}
