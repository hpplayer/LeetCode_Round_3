/*
361. Bomb Enemy

Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall
since the wall is too strong to be destroyed.

Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)
*/

/**
 * DP solution
 * 
 * Two loops, the first loop gets the enemy count from left row and top col. The second loop gets the enemy
 * count from right row and bot col. In the second loop, we also update the result if possible
 * 
 * Remark:
 * 1) We should separate the row and col values, so we can retrieve information easier
 * 2) Since this is a new problem, I have not seen a better solution. I will update solution later if I found one
 * Time complexity: O(2*mn)
 * Space complexity: O(mn)
 * 
 * @author hpPlayer
 * @date Jun 18, 2016 4:56:16 PM
 */

public class Bomb_Enemy_p361_sol1 {
    public int maxKilledEnemies(char[][] grid) {
        //DP solution
        
        //boundary check
        if( grid.length == 0 ) return 0;
        
        int m = grid.length, n = grid[0].length;
        
        MyNode[][] dp = new MyNode[m][n];
        
        //initialize matrix
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = new MyNode();
            }
        }
        
        //scan the input and update matrix
        //read values from top left
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //skip walls
                if( grid[i][j] == 'W'  ) continue;
                //accumulate enemys
                if( grid[i][j] == 'E' ){
                    dp[i][j].top = 1;
                    dp[i][j].left = 1;
                }
                //accumulate enemys from top
                dp[i][j].top += i > 0? dp[i-1][j].top : 0;
                //accumulate enemys from left
                dp[i][j].left += j > 0? dp[i][j-1].left : 0;
            }
        }
        
        int result = 0;
        
        //scan the input and update matrix
        //read values from bot right
        for(int i = m-1; i >= 0; i--){
            for(int j = n-1; j >= 0; j--){
                //skip walls
                if( grid[i][j] == 'W'  ) continue;
                //accumulate enemys
                if( grid[i][j] == 'E' ){
                    dp[i][j].bot = 1;
                    dp[i][j].right = 1;
                }
                //accumulate enemys from bot
                dp[i][j].bot += i + 1 < m? dp[i+1][j].bot : 0;
                //accumulate enemys from right
                dp[i][j].right += j + 1 < n? dp[i][j+1].right : 0;
                
                //update result if possible 
                if(grid[i][j] == '0') result = Math.max(result, dp[i][j].top + dp[i][j].bot + dp[i][j].left + dp[i][j].right );
            }
        }        
        
        return result;
    }
    
    private class MyNode{
        int top;
        int bot;
        int left;
        int right;
    }
}
