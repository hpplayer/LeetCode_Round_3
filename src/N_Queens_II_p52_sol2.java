/**
 * iterative backtracking solution
 * 
 * This solution is very similar to N_Queens_I_p51_sol2, and actually even simpler. We just need to increase the counter in the end
 * instead of building a matrix 
 * 
 * Time complexity: exponential, but accurate time complexity is uncertain
 * Space complexity: O(n)
 * 
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 8:39:53 PM
 */
public class N_Queens_II_p52_sol2 {
    public int totalNQueens(int n) {
        //This problem is a simpler version of N_Queens_I_p51_sol2, iterative version of backtracking
        int[] cols = new int[n];
        int col = 0;
        
        //counter
        int count = 0;
        
        while(true){
            //firstly do boundary check, last column/last row
            if(col == n){
                //last column, we found a valid path
                count++;
                //try next row in prev column
                cols[--col]++;
            }else if(cols[col] == n){
                //last row, we need go back to prev col and try new row
                
                //if no prev col we can go back, then we are done with backtracking, return result
                if(col == 0) return count;
                //otherwise go back and try next row
                cols[col] = 0;//reset current column
                cols[--col]++;//try next row in prev row
            }else{
                //general case
                if(isValid(cols, cols[col], col)){
                    //current position is valid, then go to next column
                    col++;
                }else{
                    //current position is invalid, try next row in current column
                    cols[col]++;
                }
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
