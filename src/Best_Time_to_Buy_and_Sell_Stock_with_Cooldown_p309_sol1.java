
public class Best_Time_to_Buy_and_Sell_Stock_with_Cooldown_p309_sol1 {
	public static void main(String[] args){
		int[] prices = {1, 2, 3, 0, 2};
		System.out.println(maxProfit(prices));
	}
    public static int maxProfit(int[] prices) {
        
        int[][] dp = new int[prices.length][2];
        
        if(prices[1] > prices[0]) dp[1][0] = prices[1] - prices[0]; 
        		
        for(int i = 2; i < prices.length; i++){
        	dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);//cool down
        	
        	dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]);//do transcation
        	
        	int temp1 = dp[i-2][1] + (prices[i] > prices[i-1]? prices[i] - prices[i-1] : 0);//i - 2 cool down
        	int temp2 = prices[i-2]//i-2 not cool down		
        			
        	int profit = Math.max(prices[i] - prices[i-1], prices[i] - prices[i-2]);
        	if(profit > 0) dp[i][1] += profit;
        }
        
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}
