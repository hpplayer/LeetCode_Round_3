/*
348. Design Tic-Tac-Toe

Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?

Hint:

Could you trade extra space such that move() operation can be done in O(1)?
You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
*/

/**
 * Observation solution
 * 
 * Interesting problem!
 * We observe that the requirement to win the game is very strict, only all cells in the row/col/diagonal/anti_diagonal
 * have same mark. If we can cache the previous results in the row/col/diagonal/anti_diagonal, then we can use O(1) time
 * to check if current move will make the player win the game.
 * 
 * we have n rows, n cols, 1 diagonal and 1 anti_diagonal, therefore we need two arrays for rows/cols
 * and two variables for diagonal/anti_diagonal
 * Here we define diagonal to be from left-bot to right-up. And define anti_diagonal to be from left-top to
 * right-bot. To check anti_diagonal, we just check if x == y. To check diagonal, we need to check n - 1 - row == col
 * 
 * In this solution our arrays and variable will record the curr accumulate sum. If abs(sum) == n, then curr player win
 * otherwise return 0 (no one wins)
 * 
 * Time complexity: O(1) for move
 * Space complexity: O(N) 
 * 
 * @author hpPlayer
 * @date Jun 8, 2016 12:03:22 AM
 */
public class Design_Tic_Tac_Toe_p348_sol1 {
    //observation solution
    
    int[] rows;
    int[] cols;
    int diagonal;
    int anti_diagonal;
    int size;
    
    /** Initialize your data structure here. */
    public Design_Tic_Tac_Toe_p348_sol1(int n) {
        rows = new int[n];
        cols = new int[n];
        size = n;
        diagonal = 0;
        anti_diagonal = 0;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        //player1 will increase the counter, player2 will decrease the counter
        int offset = player == 1? 1 : -1;
        
        //update row/col/diagonal/anti_diagonal based on offset
        rows[row] += offset;
        cols[col] += offset;
        //if anti_diagonal
        if(row == col) anti_diagonal += offset;
        //if diagonal
        if( size-1-row == col ) diagonal += offset;
        
        //if any of above edge has sum "size", then curr player win
        //Notice: we need to use Math.abs() to compare sum with size, otherwise we cannot check if player2 wins
        if( Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(anti_diagonal) == size || Math.abs(diagonal) == size) return player;
        
        //otherwise no one wins
        return 0;
    }
}
/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */