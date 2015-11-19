/*
Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/

/**
 * Backtracking problem
 * 
 * The difficulty of this problem is that we need to know where to return and how to reset values
 * In this solution, we will use make DFS return true if there is no more "." in the matrix. And we will return false if we found
 * we tried all possible numbers and still cannot return true from following DFS calls.
 * 
 * Before we try next new number in current cell, we need reset everything including the value in matrix. In the case that we need
 * go back to top DFS and change value, reset value in matrix to be "." is very necessary as it indicates we have not correctly fill
 * this cell yet
 * 
 * In this problem, we also use i/3 *3 + j/3 to get the block number of each cell
 * 
 * Time complexity:  from EPI p295, 9*9 grid, it does not make sense to speak of time complexity. But if generalize to n*n, it should has
 * exponential complexity and actually an NP-complete problem
 * 
 * Space complexity: same as time complexity
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 5:28:50 PM
 */
public class Sudoku_Solver_p37_sol1 {
	public static void main(String[] args){
		char[][] board = { "..9748...".toCharArray(), "7........".toCharArray(), ".2.1.9...".toCharArray(), "..7...24.".toCharArray(),
				".64.1.59.".toCharArray(), ".98...3..".toCharArray(), "...8.3.2.".toCharArray(), "........6".toCharArray(), "...2759..".toCharArray()};
		
		new Sudoku_Solver_p37_sol1().solveSudoku(board);
		
		for(char[] temp : board){
			for(char c : temp){
				System.out.print(c + " ");
			}
			System.out.println();
		}
		
	}
    //use three matrixes to hold used numbers
    boolean[][] rows;
    boolean[][] cols;
    boolean[][] blocks;
    
    public void solveSudoku(char[][] board) {
        //this is a follow-up of Valid_Sudoku_p36_sol1
        //we try all possible numbers and validate each number with methods in Valid_Sudoku_p36_sol1
        
        //boundary check
        if(board == null || board.length == 0 || board[0].length == 0) return;
        
        int n = board.length;
        
        //initialization
        rows = new boolean[n][n];
        cols = new boolean[n][n];
        blocks = new boolean[n][n];
        
        //firstly preprocess 3 matrixes to include existing numbers in input
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                //skip empty cell
                if(board[i][j] == '.') continue;
                //convert val to index
                int val = board[i][j] - '1';
                //set true
                rows[i][val] = cols[val][j] = blocks[i/3*3 + j/3][val] = true; 
            }
        }
        
        DFS(board);
    }
    
    //let DFS return boolean to indicate where we have found a valid way to assign numbers
    public boolean DFS(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                //we only look at empty cell
                if(board[i][j] == '.'){
                    //try all possible numbers
                    for(int a = 0; a < 9; a++){
                        //if this number is not used in row/col/block
                        if(!rows[i][a] && !cols[a][j] && !blocks[i/3*3 + j/3][a]){
                            //set val
                            board[i][j] = (char) (a + '1');
                            //set true
                            rows[i][a] = cols[a][j] = blocks[i/3*3 + j/3][a] = true; 
                            //go to next DFS, if next DFS return true, then we stop here and return true
                            if(DFS(board)) return true;
                            //otherwise we reset vals/booleans and try next number
                            rows[i][a] = cols[a][j] = blocks[i/3*3 + j/3][a] = false; 
                            board[i][j] = '.';
                        }
                    }
                    
                    //if we tried all possible numbers and still cannot return true from following DFS
                    //then we return false and go back to prev DFS
                    return false;
                }
            }
        }
        
        //no empty cell found, return true
        return true;
    }
}
