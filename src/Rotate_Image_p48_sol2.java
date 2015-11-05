/**
 * Math solution
 * 
 * We firstly reverse the matrix, then do transpose of the matrix
 * The result matrix would be the result of clockwise rotation
 * If we want the counter-clockwise rotation, then we just reverse the matrix horizontally, then do transpose
 *  
 * Time complexity of this solution is O(mn)
 * and space complexity is O(1)
 * @author hpPlayer
 * @date Nov 4, 2015 10:53:51 PM
 */
public class Rotate_Image_p48_sol2 {
    public void rotate(int[][] matrix) {
    	//reverse
        reverse(matrix);
        
        //transpose
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    
    public void reverse(int[][] matrix){
        int start = 0, end = matrix.length - 1;
        while(start < end){
            //while we still have two rows left   
            for(int i = 0; i < matrix.length; i++){
                int temp = matrix[start][i];
                matrix[start][i] = matrix[end][i];
                matrix[end][i] = temp;
            }
            
            start ++;
            end --;
        }
    }
}
