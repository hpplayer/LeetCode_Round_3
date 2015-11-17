/*
Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.]
*/

/**
 * DP approach
 * 
 * In this solution, we create a dp array to record the min number of perfect squares we need to reach each number smaller than input n
 * During the loop of filling dp array, we will try all perfect squares than equal or smaller than current number, and add the dp value
 * in the rest value
 * 
 * Time complexity: O(n * n^0.5), as we need to scan array with O(n) length once, and for each cell, we need check n^0.5 times to update
 * its value
 * 
 * Space complexity: O(n)
 * 
 * This solution is similar to Word_Break_I_p139_sol1, where we also need to scan all cells before current index
 * 
 * Actually, we don't need to know the dp value for each number smaller than n. We just want to know the value that help us
 * get input n. BFS can help do that, see sol2
 * 
 * @author hpPlayer
 * @date Nov 16, 2015 4:16:24 PM
 */
public class Perfect_Squares_p279_sol1 {
    public int numSquares(int n) {
        //dp approach, we use a dp[] to record the min num of squares we need for each number. 
        //then we scan each dp cells before current index to find the min squares we need to reach current num
        
        //to cover the case that we can use one perfect square to reach num, we will add 0 cell as well
        int[] dp = new int[n+1];
        
        //fill the dp array
        for(int i = 1; i < dp.length; i++){
            //for each num, at least we can use i number of 1 to reach it
            dp[i] = i;
            //try all perfect squares smaller than i
            for(int j = 1; j * j <= i; j++){
                dp[i] = Math.min(dp[i], 1 + dp[i-j*j]);
            }
        }
        
        //return the min num of perfect squares to reach n
        return dp[n];
    }
}
