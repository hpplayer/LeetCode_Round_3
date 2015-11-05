/*
Rotate Image

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

/**
 * Pointer problem
 * 
 * We will rotate the image layer by layer. Each layer we will reduce the length to length - 2. So we have length/2 layers.
 * To make rotation clear, we will firstly define the range of each row by using start and end pointer. Then we will begin rotate.
 * The degree of increment/decrement is within range of (0, end - start). We just increase or decrease on start/end to rotate image
 * 
 * Time complexity is O(mn) as we need visit each cell once
 * Space complexity is O(1) as we don't need extra space
 * 
 * Sol1 provides the pointer solution
 * Sol2 provides math solution
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 10:27:32 PM
 */
public class Rotate_Image_p48_sol1 {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        //we will do the rotation layer by layer
        //we totally have len/2 layers
        for(int i = 0; i < len/2; i++){
            //define range in current row
            int start = i, end = len - 1 - i;
            
            //we want adjust the increment/decrement from 0 to len - 1 (exclusive)
            //so we won't change the value in last cell which is already updated by cell at index 0
            for(int j = 0; j < end - start; j++){
                //backup the first cell
                int temp = matrix[start][start + j];
                
                //left to top
                matrix[start][start+j] = matrix[end-j][start];
                //bottom to left
                matrix[end-j][start] = matrix[end][end-j];
                //right to bottom
                matrix[end][end-j] = matrix[start + j][end];
                //top to right
                matrix[start+j][end] = temp;
            }
            
        }
        
    }
}
