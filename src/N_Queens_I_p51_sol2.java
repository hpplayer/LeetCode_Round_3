import java.util.*;
/**
 * Iterative version of backtracking solution
 * 
 * We use a col pointer to scan the columns of virtual matrix. Our backtracking is done after the col reach the bottom and return
 * to the first col.
 * 
 * In the loop, we should do boundary case first. Boundary cases including 1) reach the right boundary of virtual matrix 2) 
 * reach the bottom boundary of virtual matrix. In case 1), we will add path to result, add try a new row in last column. In case 2)
 * we will reset row of current column to be 0 and return to previous column and try next row in previous column
 * 
 * isValid() and buildList() are exactly same with N_Queens_p51_sol1
 * 
 * Time complexity: backtracking..don't know how to analyze at least O(mn)
 * Space complexity: at least O(mn) since we need build a matrix with size m by n
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 6:56:02 PM
 */
public class N_Queens_I_p51_sol2 {
    public List<List<String>> solveNQueens(int n) {
        //iterative backtracking solution, the logic is similar to recursive version
        
        List<List<String>> result = new ArrayList<List<String>>();
        
        int[] cols = new int[n];
        int col = 0;
        
        while(true){
            //like recursive backtracking, we firstly check boundary cases
            if(col == n){
                //reach right boundary of virtual matrix, found a valid path
                result.add(buildList(cols));
                //return to last col, and try next row
                cols[--col]++;
            }else if(cols[col] == n){
                //reach the bottom boundary of virtual martix, return to prev col, and try new row in prev col
                
                //if current col is already first col, then we have done with backtracking, return result
                if(col == 0) return result;
                //otherwise return to prev col and try new row in prev col 
                cols[col--] = 0;
                cols[col]++;
            }else{
                //general cases
                
                if(isValid(cols, cols[col], col)){
                    //if current position is valid, move to next col
                    col++;
                }else{
                    //if current position is invalid, try next row
                    cols[col]++;
                }
            }
        }
    }
    
    public boolean isValid(int[] cols, int row, int col){
        //check if the input position is a valid position
        //we only check visited colums
        for(int i = 0; i < col; i++){
            //input row is occupied
            if(cols[i] == row) return false;
            //check diagnoal. The virtual matrix is a square, so we just need to see if abs(delta_x) == abs(delta_y)
            //if yes, then they are in the same diagonal
            int diffX = Math.abs(row - cols[i]);
            int diffY = Math.abs(col - i);
            
            if(diffX == diffY) return false;
        }
        return true;
    }
    
    public List<String> buildList(int[] cols){
        List<String> temp = new ArrayList<String>();
        
        //we visit the virtual matrix row by row, if we found current position is the place to put Q,
        //then we will put Q in StringBuilder
        for(int i = 0; i < cols.length; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < cols.length; j++){
                if(cols[j] == i){
                     //if we are in the col of current row that put Queen 
                     //then we put Q here
                     sb.append("Q");
                }else{
                    //general cases, put "."
                    sb.append(".");
                }
            }
            temp.add(sb.toString());
        }
        
        return temp;
    }
}
