import java.util.*;

/*
322. Coin Change

You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
*/

/**
 * DP solution
 * 
 * We create a dp table to record the min num of coins to get each amount < given amount.
 * Then we use two loops to update the dp table. Outer loop will visit each cell, inner loop will scan all coins we have and found if any
 * previous number can get current number by adding one coin. We will record the smallest coin we needed in current dp cell
 * 
 * Time complexity: O(N * amount) where N is len of coins[] and amount is val of given input
 * Space complexity: O(amount)
 * 
 * This problem is very similar to problem Perfect_Squares_p279_sol1, we can apply DP and BFS to solve the problem
 * But due to the large test cases, we cannot use BFS in this problem, so DP is more desirable. The DP code has 99% similarity
 * 
 * @author hpPlayer
 * @date Feb 27, 2016 9:37:58 PM
 */
public class Coin_Change_p322_sol1 {
    public int coinChange(int[] coins, int amount) {
        //classic DP problem, we starts from the smallest amount and get the target amount from previous result
        //We use two loops here, the outer loop is used to fill dp[] the inner loop is used to scan coins table
        
        //to make index consistent, we create dp[] with amount + 1 len
        int[] dp = new int[amount+1];
        
        //the max num to get target amount is "amount" i.e. we use "amount" num of 1 coins to get amount
        //so we set the initial value for dp[] to be amount + 1. This can help us get avoid stackoverflow
        Arrays.fill(dp, amount+1);
        
        //reset index 0
        dp[0] = 0;
        
        //outer loop is used to update dp[]
        for(int i = 1; i <= amount; i++){
            //inner loop is used to scan the coins[] and update dp[] if possible
            for(int j = 0; j < coins.length; j++){
                //check if coin denominations <= i amount, since coins[j] is not sorted we can't have early stop
                //we just need one more coin to reach i from i - coins[j]
                if(coins[j] <= i) dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
            }
        }
        
        //check if the value is still amount + 1, then return -1
        //else return dp[amount]
        return dp[amount] == amount + 1? -1 : dp[amount];
    }
}
