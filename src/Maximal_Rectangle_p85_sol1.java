import java.util.*;
/*
 * Maximal Rectangle 
 * 
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */

/**
 * Stack solution
 * 
 * This solution is an application of solution Largest_Rectangle_in_Histogram_p84_sol1
 * We firstly create an int[][] and preprocess matrix, so the value in each row will be the height of histogram at that row.
 * Then we use the solution of Largest_Rectangle_in_Histogram_p84_sol1 to get the largest rectangle for each row.
 * 
 * fill int[][] causes O(mn), we apply old solution to each row, so there will be extra O(n) for each row. So the total cost would be O(mn)
 * Space complexity: we use an extra matrix which costs O(mn), and then we use a stack to help get the area which costs O(n).
 * so the total cost would be O(mn  + n)
 *  
 * Sol1 is the stack solution with the application of solution in p84
 * Sol2 is the dp solution and is very specific for this problem. Without the costs from stack, sol2 is much faster
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 2:50:12 PM
 */
public class Maximal_Rectangle_p85_sol1 {
    public int maximalRectangle(char[][] matrix) {
        //boundary check
        if(matrix.length == 0) return 0;
        
        //create an int[][] to record the value in matrix
        int[][] newM = new int[matrix.length][matrix[0].length];
        
        int result = 0;
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                //get the height of histogram of each row
                newM[i][j] = (matrix[i][j] - '0' == 0)? 0 : (i == 0? 1 : newM[i-1][j] + 1);  
            }
            //get the largest rectangle at current row
            result = Math.max(result, getArea(newM[i]));
        }
        
        return result;
    }
    
    public int getArea(int[] row){
        //same code with problem Largest Rectangle in Histogram(p84) 
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        for(int i = 0; i <= row.length; i++){
            int ht = i == row.length? 0 : row[i];
            if(stack.isEmpty() || row[stack.peek()] <= ht){
                stack.push(i);
            }else{
                while(!stack.isEmpty() && row[stack.peek()] > ht){
                    int tallBar = row[stack.pop()];
                    int width = stack.isEmpty()? i : i - stack.peek() - 1;
                    result = Math.max(result,  tallBar * width);
                }
                stack.push(i);
            }
        }
        
        return result;
    }
}
