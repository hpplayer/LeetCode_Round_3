/*

Best Time to Buy and Sell Stock II

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times).
However, you may not engage in multiple transactions at the same time
(ie, you must sell the stock before you buy again).

*/

/**
 * Greedy solution
 * 
 * We just sum up positive profits from all transactions
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 11, 2015 1:45:50 PM
 */
public class Best_Time_to_Buy_and_Sell_Stock_II_p122_sol1 {
    public int maxProfit(int[] prices) {
        //the basic idea is summing of all positive profit
        
        int result = 0;
        
        for(int i = 1; i < prices.length; i++){
            //if current price is higher than prev price, then add this earnning into result
            if(prices[i] > prices[i-1]) result += prices[i] - prices[i-1];
        }
        
        return result;
    }
}
