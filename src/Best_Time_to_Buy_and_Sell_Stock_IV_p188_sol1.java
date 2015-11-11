/*
Best Time to Buy and Sell Stock IV

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * DP solution 
 * 
 * The basic idea is similar to Best_Time_to_Buy_and_Sell_Stock_III_p123_sol1, but now we need to generalize it to k transactions.
 * For each transaction number, we need record the best buy value and the best buy-and-sell value. The buy value of transaction i should 
 * include the buy-and-sell value if i - 1. 
 * 
 * Two things that we need to notice:
 * 1) we need to initialize the buy value to be Integer.MIN_VALUE for all transactions
 * 2) We need to handle the extreme case that k is very large (the max number of transactions that can make profit will only be n/2)
 * 
 * Time complexity: O(k * n)
 * Space complexity: O(k)
 * 
 * Remark:
 * In this problem, we need to handle a very important corner case where k is extreme large. Let's say now we have an input array with totally n 
 * cells. What is the max number of transactions we can have that only earn money? There would only be n/2 transactions we can make, we buy stock
 * at day 1, then sell at day 2, then buy at day 3 and sell at day 4, etc. If buy at day 2 and sell at day 3 will also earn money, then we would
 * combine three transactions together therefore only have one transaction from day 1 to day 4. So if we found k > n/2, we just use the solution
 * in Best_Time_to_Buy_and_Sell_Stock_II_p122_sol1 and sum all positive profits together
 * 
 * @author hpPlayer
 * @date Nov 11, 2015 3:04:22 PM
 */
public class Best_Time_to_Buy_and_Sell_Stock_IV_p188_sol1 {
    public int maxProfit(int k, int[] prices) {
        //boundary check
        if(prices.length <= 1 || k == 0) return 0;
        if(k > prices.length/2) return maxProfit(prices);
        
        //dp matrix, first column records the best buy price for i transactions
        //second column records the profit we will get for i transactions
        int[][] dp = new int[k][2];
        
        //since we will reverse the buy value to keep the max trend, they may be negative
        //we need to initialize the first column and set all initial value to be Integer.MIN_VALUE
        for(int i = 0; i < k; i++){
            dp[i][0] = Integer.MIN_VALUE;
        }
        
        for(int i = 0; i < prices.length; i++){
            //boundary cell
            dp[0][0] = Math.max(dp[0][0], -prices[i]);
            dp[0][1] = Math.max(dp[0][1], prices[i] + dp[0][0]);
            
            //general cells
            for(int j = 1; j < k; j++){
                //for transaction j, its buy value should include the buyAnedSell value for j - 1 transactions
                dp[j][0] = Math.max(dp[j][0], dp[j-1][1] - prices[i]);
                dp[j][1] = Math.max(dp[j][1], prices[i] + dp[j][0]);
            }
        }
        
        //the best buyAndSell value for transaction k should be the global best one
        return dp[k-1][1];
    }
    
    public int maxProfit(int[] prices){
        int result = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1]) result += prices[i] - prices[i-1];
        }
        return result;
    }
}
