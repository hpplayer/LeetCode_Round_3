/*
Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
*/

/**
 * DP problem
 * 
 * We need use two variables, one variable records the min buy price so far and the other variable records the result (largest profit we will earn)
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 11, 2015 1:42:06 PM
 */

public class Best_Time_to_Buy_and_Sell_Stock_I_p121_sol1 {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        
        //min buy prices so far
        int minBuy = prices[0];
        //result, highest sell price - min buy price
        int result = 0;
        
        for(int i = 1; i < prices.length; i++){
            //update result if necessary
            result = Math.max(result, prices[i] - minBuy);
            //update minBuy if necessary
            minBuy = Math.min(minBuy, prices[i]);
        }
        
        return result;
    }
}
