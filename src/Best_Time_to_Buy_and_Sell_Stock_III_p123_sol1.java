/*
Best Time to Buy and Sell Stock III


Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/**
 * DP solution
 * 
 * First of all we need to found when making two transactions will make more money than one transaction. The solution is
 * when we get a second low points. Example: 1 4 3 5. In this input, make one transaction will make profit of 4, but make 
 * two transactions will make profit of 5.
 * 
 * In this solution, we will use two variables firstBuy and SecondBuy to track the best buy time for two transactions. 
 * We also need two other variables firstBuyAndSell and secondBuyAndSell to track the best profit we can earn from these
 * two transactions
 * The value of SecondBuy variable should also include the profit we earn from firstBuyAndSell. If a single transaction 
 * can already make the max global profit, then the value of firstBuy and SecondBuy and the value of firstBuyAndSell and
 * secondBuyAndSell should be same. In other words, the profit from secondBuyAndSell should always larger or equal the profit
 * from firstBuyAndSell
 * 
 * So the basic idea is to scan the array and update 4 variables accordingly, then return secondBuyAndSell
 * Remember that we need to reverse the value of buy, so we have same trend for buy and buy-sell i.e. we want them all be max 
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 11, 2015 2:15:13 PM
 */
public class Best_Time_to_Buy_and_Sell_Stock_III_p123_sol1 {
    public int maxProfit(int[] prices) {
        //boundary check
        if(prices.length <= 1) return 0;
        
        //reverse the value of buy, so we have same trend for buy and buy-sell i.e. we want them all be max 
        int firstBuy = -prices[0];
        int firstBuyAndSell = 0;
        int secondBuy = -prices[0];
        int secondBuyAndSell = 0;
        
        for(int i = 1; i < prices.length; i++){
            //cover the case that we only make one transaction before index i
            firstBuy = Math.max(firstBuy, -prices[i]);            
            firstBuyAndSell = Math.max(firstBuyAndSell, prices[i] + firstBuy);
            
            //cover the case that we will make two transactions before index i
            //firstBuy and secondBuy will start that have different value if we found a second low point
            //if we only have one low point, then firstBuy = secondBuy and  firstBuyAndSell = secondBuyAndSell
            
            //include firstBuyAndSell into secondBuy
            //if we finish the first transaction and buy the stock second time at current index i
            secondBuy = Math.max(secondBuy, firstBuyAndSell - prices[i]);
            //if we sell the stock second time at current index i
            secondBuyAndSell = Math.max(secondBuyAndSell, prices[i] + secondBuy);
        }
        
        return secondBuyAndSell;
    }
}
