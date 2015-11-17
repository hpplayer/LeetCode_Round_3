import java.util.*;

/*
* 
* The n-queens puzzle is the problem of placing n queens on an n¡Án chessboard 
* such that no two queens attack each other.
* 
* Given an integer n, return all distinct solutions to the n-queens puzzle.
* 
* Each solution contains a distinct board configuration of the n-queens' placement, 
* where 'Q' and '.' both indicate a queen and an empty space respectively.
* 
* For example,
* There exist two distinct solutions to the 4-queens puzzle:
* 
* [
*  [".Q..",  // Solution 1
*   "...Q",
*   "Q...",
*   "..Q."],
* 
*  ["..Q.",  // Solution 2
*   "Q...",
*   "...Q",
*   ".Q.."]
* ]
* 
*               
*/

/**
 * Standard backtracking solution
 * 
 * We will try all possible ways to put Queens in each column, and add the valid way into the result.
 * To make the code more readable, we split the program into several small programs.
 * isValid() is used to validate whether current position is valid
 * buildList() is used to build a list with given way
 * DFS() is used to build to do the backtracking
 * 
 * Remark:
 * 1. In this solution, we only use one array to indicate the position we used to place Queens. 
 * Example: cols[5] = 3 means we put a Queen in column 6 and row 4
 * 2.  Since the matrix is a square, we just need to check abs(delta_x) and abs(delta_y) to see if two queens are in same diagonal
 * 
 * Time complexity: backtracking..don't know how to analyze at least O(mn)
 * Space complexity: at least O(mn) since we need build a matrix with size m by n
 * 
 * Sol1 is the recursive version of backtracking
 * Sol2 is the iterative version of backtracking
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 6:32:45 PM
 */
public class N_Queens_I_p51_sol1 {
    public List<List<String>> solveNQueens(int n) {
        //standard backtracking solution, but now we use one array to reduce space complexity
        
        //cols record the information in each column. The informations is which row we put Queen.
        //so our backtracking solution will look at the virtual matrix column by column from left to right
        List<List<String>> result = new ArrayList<List<String>>();
        DFS(result, new int[n], 0);
        
        return result;
    }
    
    public void DFS(List<List<String>> result, int[] cols, int col){
        if(col == cols.length){
            //if we reach the right boundary of cols[], then it indicates we found a valid way to put Queens
            result.add(buildList(cols));
            return;
        }
        
        //check each possible row in current col and see if we can put a Queen in this row
        for(int i = 0; i < cols.length; i++){
            //if current row is valid, then we put queen here and go to next column
            if(isValid(cols, i, col)){
                //update value in cols[] indicating row i is occupied in current col
                cols[col] = i;
                DFS(result, cols, col+1);
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
