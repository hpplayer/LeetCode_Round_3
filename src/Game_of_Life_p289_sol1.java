/*
Game of Life

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules
(taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time:
You cannot update some cells first and then use their updated values to update other cells.

In this question, we represent the board using a 2D array. In principle, the board is infinite,
which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/

/**
 * Bit manipulation solution
 * 
 * In this solution, we just use the last second bit to store the status of next round. We use |2 to set the last second bit,
 * and we use &1 == 1 to check the last bit which stores current status of the cell.
 * There are only two types of cells will live next round: 1) exactly 3 living cells around 2) current cell is living and 
 * we have exactly 2 living cells around
 * 
 * We firstly scan each cell once, and each time we scan 8 neighbors of it
 * Then we scan each cell second time to update the status
 * 
 * So the total running time should be O(mn)
 * Space complexity is O(1) as we solve this problem in place
 * 
 * @author hpPlayer
 * @date Nov 3, 2015 8:13:04 PM
 */
public class Game_of_Life_p289_sol1 {
    public void gameOfLife(int[][] board) {
        //use the last second bit to store the status of next round
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                //get count
                int count = countNeighbor(board, i, j);
                
                //only two kinds of cell will live next round:
                //1) we get exactly 3 living cells around
                //2) current cell is living and we get exactly 2 living cells around
                if(count == 3 || (count == 2 && board[i][j] == 1)){
                    //we use |2 to set the last second bit to be 1
                    //since 2 => 10, &10 will set the last second bit
                    board[i][j] |= 2;
                }
            }
        }
        
        //then we rightshift the value in each cell to update the status of each cell
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                board[i][j] >>= 1;
            }
        }
        
    }
    
    public int countNeighbor(int[][] board, int x, int y){
        //count the 1s in neighbors
        int[] xOffset = {1, -1, -1, 1, 0, 0, 1, -1};
        int[] yOffset = {1, -1, 1, -1, 1, -1, 0, 0};
        
        int count = 0;
        
        for(int i = 0; i < 8; i++){
            int newX = x + xOffset[i];
            int newY = y + yOffset[i];
            
            //check neighbor cells if they are in boundary
            //in case we have added status in the last second bit, we use &1 operation to get current status
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && (board[newX][newY]&1) == 1){
                
                count ++;
            }
        }
        
        return count;
    }
}
