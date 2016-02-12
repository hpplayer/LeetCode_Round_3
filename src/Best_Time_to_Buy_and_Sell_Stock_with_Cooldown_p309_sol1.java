/*
309. Best Time to Buy and Sell Stock with Cooldown

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
*/	
		
/**
 * DP solution
 * 
 * The analysis of this problem is the most mathematical one I ever get when doing LeetCode
 * 
 * There are three states during the transactions: Buy, Sell, Rest(do nothing)
 * Now assume we have three arrays represent max profit we can make when making transactions ended with Buy, Sell or Profit
 * We should have following functions:
 * Buy[i] = Max(Buy[i-1], Rest[i-1] - price)   (We either do nothing on last day (buy[i-1]), or we buy a stock after rest)
 * Sell[i] = Max(Sell[i-1], Buy[i-1] + price) (we either do nothing on last day (sell[i-1]), or we sell a stock after we bought one)
 * Rest[i] = Max(Buy[i-1], sell[i-1], rest[i-1]) (Do nothing on last day, so its max profit will come from the max profit we can make from i-1 day considering
 * all three transactions)
 * 
 * For the last function, we can simplify it. Based on common sense, We shall have Buy[i-1] <= Rest[i-1] <= Sell[i-1], so we have rest[i] = sell[i-1]
 * plug into function 1 then we will have Buy[i] = Max(Buy[i-1], Sell[i-2] - price)
 * 
 * Time complexity: O(N)
 * 
 * @author hpPlayer
 * @date Feb 11, 2016 10:53:54 AM
 */
public class Best_Time_to_Buy_and_Sell_Stock_with_Cooldown_p309_sol1 {
	public static void main(String[] args){
		int[] prices = {1, 2, 3, 0, 2};
		System.out.println(maxProfit(prices));
	}
	
    public static int maxProfit(int[] prices) {
        //initialize buy with int_min, so that we can have negative buy value when necessary
        int buy = Integer.MIN_VALUE, prev_buy = Integer.MIN_VALUE, sell = 0, prev_sell = 0; 
        
        for(int price : prices){
            //get Buy[i-1]
            prev_buy = buy;
            buy = Math.max(prev_buy, prev_sell - price);
            //get Sell[i-1], in above line prev_sell is sell[i-2], now, it updated to sell[i-1]
            prev_sell = sell;
            sell = Math.max(prev_sell, prev_buy + price);
        }
        
        //based on common sense sell[i] >= buy[i], so we return sell[i]
        return sell;
    }
}
