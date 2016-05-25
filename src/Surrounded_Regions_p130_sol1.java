import java.util.*;

/*
130. Surrounded Regions

Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

/**
 * Iteratively BFS solution
 * 
 * 
 * Only 'O' regions that at least have one 'O' in boundary edge would not be consumed by 'X'
 * So we will start with 'O' in boundary edge and do BFS from it. We firstly convert those 'O' to 'B' to differentiate
 * with other 'O'
 * Then we scan input matrix again and convert remaining 'O' to 'X', and convert 'X' to 'O'
 * 
 * 
 * Time complexity: O(mn)
 * Space complexity: O(mn)
 * 
 * Remark:
 * Previously recursive DFS will give stack overflow error, thats why I list an iterative BFS solution here
 * But if we can further optimize the code, and avoid revisit boundary 'O' in DFS, then our recursive DFS solution
 * can actually work.
 * 
 * @author hpPlayer
 * @date May 24, 2016 11:29:26 PM
 */
public class Surrounded_Regions_p130_sol1 {
    public void solve(char[][] board) {
        //iterative BFS solution to get rid of stackoverflow error (but if we can avoid repeatedly revisit 
    	//bounedary "O", then recursive version also work)
        //we will mark all 'O' regions that connect to boundary 'O' and keep them in the final matrix
        
        //boundary check
        if(board.length == 0) return;
        
        int m = board.length, n = board[0].length;
        
        //use queue to visit matrix in BFS way
        Queue<MyNode> que = new LinkedList<MyNode>();
        
        int[] xOffset = {0, 0, 1, -1};
        int[] yOffset = {1, -1, 0, 0};
        
        //check top and bot row and put boundary 'O' into que
        for(int i = 0; i < n; i++){
            if(board[0][i] == 'O') que.offer(new MyNode(0, i));
            if(board[m-1][i] == 'O') que.offer(new MyNode(m-1, i));
        }

        //check left and right col and put boundary 'O' into que
        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O') que.offer(new MyNode(i, 0));
            if(board[i][n-1] == 'O') que.offer(new MyNode(i, n-1));
        }        
        
        //mark all 'O' cells that connect to boundary 'O'
        while(!que.isEmpty()){
            MyNode curr = que.poll();
            board[curr.x][curr.y] = 'B';
            for(int i = 0; i < xOffset.length; i++){
                int newX = curr.x + xOffset[i];
                int newY = curr.y + yOffset[i];
                //if new cell is also a boundary cell, then we can skip it as it is already in queue once
                //if we use this optimization in recursive solution, then it should also work
                if(newX > 0 && newX < m - 1 && newY > 0 && newY < n -1 && board[newX][newY] == 'O'){
                    que.offer(new MyNode(newX, newY));
                }
            }
        }
        
        //revisit input matrix and update 'B' and 'O' cells
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] == 'B'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private class MyNode{
        int x;
        int y;
        MyNode(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
