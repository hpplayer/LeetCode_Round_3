/*
* 
* Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
* 
* The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
* 
* A partially filled sudoku which is valid.
* 
* Note:
* A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
* 
*               
*/

/**
 * Math problem
 * 
 * The key part is to use "/" and "%" operation to get the block number. 
 * 
 * We use three extra matrix to record the visited values in board. If in same row/col/block, we are going to visit a visited 
 * number, then return false.
 * 
 * Remark:
 * We can reduce the space complexity to O(3n) (3 arrays with len(n)) but then the code readability will not be good and it is harder for
 * us to get the correct index in small blocks. so it is not recommended, but I still put code here see isValidSudoku2() below.
 * 
 * Time complexity:
 * O(n*n) as we need read each val in board once
 * Space complexity:
 * O(3*n*n) as we create 3 extra n*n matrix to record visited values
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 3:17:56 PM
 */
public class Valid_Sudoku_p36_sol1 {
    public boolean isValidSudoku(char[][] board) {
        //we scan board[][] normally, but use 3 matrix to hold numbers appeared in each row/col/block
        
        //boundary check
        if(board == null || board.length == 0 || board[0].length == 0) return false;
        
        int n = board.length;
        
        //3 matrix that hold visited values
        
        //rows[i][j] = true means number j is visited in row i
        boolean[][] rows = new boolean[n][n];
        //rows[i][j] = true means number i is visited in col j
        boolean[][] cols = new boolean[n][n];
        //blocks[i][j] = true means number j is visited in small block i
        boolean[][] blocks = new boolean[n][n];
        
        //scan the input board normally
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                //skip invalid char
                if(board[i][j] == '.') continue;
                
                int val = board[i][j] - '1';
                
                //if in same row, we have used this val before
                if(rows[i][val]) return false;
                //if in same col, we have used this val before
                if(cols[val][j]) return false;
                
                //we need get block No. wisely. 
                //we have three blocks in each three rows/cols
                //use i/3*3 + j/3 to get the block number.
                //i/3*3 can change the base per three lines
                //j/3 can change the increment per three columns
                int block_no = i/3*3 + j/3;
                
                //if in same block, we have used this val before
                if(blocks[block_no][val]) return false;
                
                //set corresponding bool value to be true
                rows[i][val] = cols[val][j] = blocks[block_no][val] = true;
            }
        }
        
        
        //all checked and no false return
        return true;
    }
    
    
    public boolean isValidSudoku2(char[][] board) {
        //boundary check
        if(board == null || board.length == 0) return false;
        
        int n = board.length;
        
        for(int i = 0; i < n; i++){
            boolean[] row = new boolean[n];
            boolean[] col = new boolean[n];
            boolean[] block = new boolean[n];            
            for(int j = 0; j < n; j++){
                if(board[i][j] != '.'){
                    int row_val = board[i][j] - '0' - 1;
                    if(row[row_val]) return false;
                    row[row_val] = true;
                }
                
                if(board[j][i] != '.'){
                    int col_val = board[j][i] - '0' - 1;     
                    if(col[col_val]) return false;  
                    col[col_val] = true;
                }
                
                int row_index = 3 * (i/3) + j/3;
                int col_index = 3 * (i%3) + j%3;
                
                if(board[row_index][col_index] != '.'){
                    int block_value = board[row_index][col_index] - '0' - 1; 
                    if(block[block_value]) return false;
                    block[block_value] = true;
                }
               
            }
        }
        
        return true;
    }
}
