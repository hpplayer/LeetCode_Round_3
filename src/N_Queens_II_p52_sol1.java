/*
N-Queens II

Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.
*/

/**
 * standard backtracking solution
 * 
 * This solution is very similar to N_Queens_I_p51_sol1, and actually even simpler. We just need to increase the counter in the end
 * instead of building a matrix 
 * 
 * Time complexity: exponential, but accurate time complexity is uncertain
 * Space complexity: O(n)
 * 
 * Sol1 is the standard backtracking solution
 * Sol2 is the iterative backtracking solution
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 8:39:53 PM
 */
		
public class N_Queens_II_p52_sol1 {
    public int totalNQueens(int n) {
        //This problem is a simpler version of N_Queens_p51_sol1, standard backtracking approach
        count = 0;
        DFS(new int[n], 0);
        return count;
    }
    
    int count;
    
    public void DFS(int[] cols, int col){
        if(col == cols.length){
            //reach the right boundary of cols[], we found a valid path
            count++;
            return;
        }    
        
        for(int i = 0; i < cols.length; i++){
            //try all possible rows in current column
            if(isValid(cols, i, col)){
                cols[col] = i;
                DFS(cols, col+1);
            }
        }
    }
    
    public boolean isValid(int[] cols, int row, int col){
        for(int i = 0; i < col; i++){
            //check all columns before
            
            //if same row return false
            if(cols[i] == row) return false;
            
            //if same diagonal return false
            int delta_x = Math.abs(cols[i] - row);
            int delta_y = Math.abs(i - col);
            if(delta_x == delta_y) return false;
        }
        //all checked, no problem, return true
        return true;
    }
}
