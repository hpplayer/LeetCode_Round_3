/*
 Dungeon Game 
  
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
The dungeon consists of M x N rooms laid out in a 2D grid.
Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2(K) -3	 3
-5	  -10	 1
10	   30	-5(P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/

/**
 * DP problem
 * 
 * This problem is an advanced version of problem Unique Paths (p62) and Unique Paths II(p63)
 * 
 * But this time we need to fill the dp matrix bottom up. This is because the value in dp[][] is the min health we need to enter this
 * room. To get the min health for each room, we need reverse the value in dungeon[][], so the vale will be more meaningful.
 * 
 * We may have two cases:
 * 1) corresponding value in dungeon[][] is very large, then, we just need to keep health 1 
 * 2) corresponding value in dungeon[][] is very small (<), then, we have to keep dp[][] - dungeon[][] health point
 * Here dp[][] is the min value from right and below cell
 * 
 * Time complexity is O(mn) as we fill each cell once
 * Space complexity is O(mn) as we created a O(mn) matrix
 * 
 * Remark:
 * We can use rolling row to reduce space complexity to O(n), see calculateMinimumHP2() below
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 9:08:45 PM
 */
public class Dungeon_Game_p174_sol1 {
    
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0) return 1;
        int m = dungeon.length, n = dungeon[0].length;
        
        //value in dp[][] means the min health requirement to enter this room
        int[][] dp = new int[m][n];
        
        //if last cell gain hp, we just need 1 hp
        //if last cell lose hp, we need 1 - dungeon[m-1][n-1] hp to enter this room
        //the requirement for this cell is that we have to keep at least 1 hp for the princess
        dp[m-1][n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);
        
        //Initialize last column
        //the requirement for each cell in last column can only from cell below
        //we use dp[i+1][n-1] - dungeon[i][n-1] to get the hp required to enter this room
        for(int i = m-2; i >= 0; i--){
            dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1] );
        }
        
        //same as above, but now we update last row
        for(int i = n - 2; i >= 0; i--){
            dp[m-1][i] = Math.max(1,  dp[m-1][i+1] - dungeon[m-1][i] );
        }
        
        //update general cell
        for(int i = m - 2; i >= 0; i--){
            for(int j = n -2; j >= 0; j--){
                //the requirement for each cell can only from cell below and cell right
                //we will pick the one will smaller value, i.e. smaller hp requirement 
                dp[i][j] = Math.max(1,  Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]);
            }
        }
        
        return dp[0][0];
    }
    
    public int calculateMinimumHP2(int[][] dungeon) {
        if(dungeon.length == 0) return 1;
        int m = dungeon.length, n = dungeon[0].length;
        
        //value in dp[][] means the min health requirement to enter this room
        int[] dp = new int[n];
        
        //if last cell gain hp, we just need 1 hp
        //if last cell lose hp, we need 1 - dungeon[m-1][n-1] hp to enter this room
        dp[n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);
        
        
        //same as above, but now we update last row
        for(int i = n - 2; i >= 0; i--){
            dp[i] = Math.max(1,  dp[i+1] - dungeon[m-1][i] );
        }
        
        //update general cell
        for(int i = m - 2; i >= 0; i--){
            dp[n-1] = Math.max(1, dp[n-1] - dungeon[i][n-1]);
            for(int j = n -2; j >= 0; j--){
                //the requirement for each cell can only from cell below and cell right
                //we will pick the one will smaller value, i.e. smaller hp requirement 
                dp[j] = Math.max(1,  Math.min(dp[j], dp[j+1]) - dungeon[i][j]);
            }
        }
        
        return dp[0];
    }
}
