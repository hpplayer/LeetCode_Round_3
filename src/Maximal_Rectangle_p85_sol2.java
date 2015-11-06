import java.util.*;
/**
 * DP solution.
 * 
 * We will use 3 dp arrays to record the previous information of left boundary, right boundary and height respectively
 * value in the d[] means the left boundary/right boundary/height at current cell.
 * We also use 2 variables to indicate the information in current row. The value in each cell depends on the info from previous row and current row
 * 
 * Time complexity: each row, we will scan 4 times, totally we have m rows, so the total time is still O(mn)
 * Space complexity: we only need 3 dp arrays, so the space complexity is O(n) 
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 3:11:59 PM
 */
public class Maximal_Rectangle_p85_sol2 {
    public int maximalRectangle(char[][] matrix) {
        //in this solution, we use 3 arrays to record pre info of left boundary, right boundary and height
        
        //boundary check
        if(matrix.length == 0) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        //3 dp arrays 
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        
        //default boundary for right will be n - 1
        Arrays.fill(right, n-1);
        int result = 0;
        
        for(int i = 0; i < m; i++){
            //two variables that record the boundary info in current row
            //boundary info in previous rows will come from 3 dp arrays
            int curr_left = 0, curr_right = n - 1;
            
            //firstly update height[]
            for(int j = 0; j < n; j++){
                height[j] = matrix[i][j] == '0'? 0 : height[j] + 1;
            }
            
            //secondly update left[]
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '0'){
                    //if current cell is 0, then for all its left cells, their left boundary will only after j
                    curr_left = j + 1;
                    //reset curr_left
                    //don't worry about value 0 will be used in calculating rectangle in this row,
                    //since the height will be 0,. 
                    left[j] = 0;
                }else{
                    //for general case, the left boundary will depend on the boundary in current and last row
                    left[j] = Math.max(left[j], curr_left);
                }
            }
            
            //thirdly update right[], similar to updating left[]
            //but we need to update backward!!!!!!!!!!!!!!!!!!!!!
            for(int j = n-1; j >= 0; j--){
                if(matrix[i][j] == '0'){
                    curr_right = j - 1;
                    right[j] = n-1;
                }else{
                    right[j] = Math.min(right[j], curr_right);
                }
            }

            //get the max area
            for(int j = 0; j < n; j++){
                //when getting the width, we need + 1 to convert to legnth
                //[0,1], the length should be 1 - 0 + 1.
                result = Math.max(result, height[j] * (right[j] - left[j] + 1) );
            }
        }
        
        return result;
    }
}
