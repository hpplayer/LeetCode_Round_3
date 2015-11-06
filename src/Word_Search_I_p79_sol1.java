/*
Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

/**
 * Backtracking problem
 * 
 * This problem can be easily solved with standard backtracking way. 
 * 
 * It uses recursion, so we need to use master theorem to analyze the time complexity and space complexity. I will not put it here.
 * If later I have enough time to study master theorem I will come back and update it.
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 10:31:29 PM
 */
public class Word_Search_I_p79_sol1 {
    public boolean exist(char[][] board, String word) {
    	//boundary check
        if(board.length == 0 || word.length() == 0) return false;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                //looking for the start of search
            	if(board[i][j] == word.charAt(0)){
            		//set start value to '#' to avoid visit back
                    board[i][j] = '#';
                    //if found return true
                    if(DFS(i, j, board, word, 1)) return true;
                    //not found, reset '#' back so it wouldn't interfere next search
                    board[i][j] = word.charAt(0);
                }
            }
        }
        
        return false;
    }
    
    public boolean DFS(int x, int y, char[][] board, String word, int index){
        if(index == word.length()) return true;
        
        int[] xOffset = {1, -1, 0, 0};
        int[] yOffset = {0, 0, 1, -1};
        
        for(int i = 0; i < 4; i++){
            int newX = x + xOffset[i];
            int newY = y + yOffset[i];
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && board[newX][newY] == word.charAt(index)){
                board[newX][newY] = '#';
                if(DFS(newX, newY, board, word, index+1)) return true;
                board[newX][newY] = word.charAt(index);
            }
        }
        
        return false;
    }
}
