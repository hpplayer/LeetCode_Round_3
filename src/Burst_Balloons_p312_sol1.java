/*
312. Burst Balloons

Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ¡Ü n ¡Ü 500, 0 ¡Ü nums[i] ¡Ü 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/

/**
 * DP solution
 * 
 * Here is the basic idea:
 * There is no pattern in this problem, so we have to consider all cases and return the max coin sum
 * The tricky part is that if we consider forward then the problem becomes very complicated, since after we remove balloon i, then left and right
 * part will be adjacent and this removal will affect all result after. So this solution solves the problem by thinking backward. 
 * For the last balloon, it has two fixed left and right part which give value 1.Definitely we can scan whole array and find the one gives max value
 * for this step. After fix the last balloon, we can safely look at left and right part since now they have a fixed boundary which is guaranteed to be true.
 * So here comes the dp solution. dp[i][j] means the max coin we can get by bursting balloons between i and j. Since we starts from the base case where
 * we only have 3 balloons, we will use an extra loop to help us get longer range like 4 balloons, 5 balloons, etc. The first inner loop means we will
 * try all possible start point for current range, the second inner loop means we are looking for the last balloon that can give us max coin sums.
 * 
 * 
 * Time complexity:
 * O(n^3) as we have three nested loop
 * 
 * @author hpPlayer
 * @date Jan 26, 2016 1:27:42 PM
 */
public class Burst_Balloons_p312_sol1 {
	public static void main(String[] args){
		int[] nums = {9,76,64,21,97,60,5};
		System.out.println(maxCoins(nums));
	}
	
    public static int maxCoins(int[] nums) {
        //include nums[-1] and nums[n]
        int[] newNums = new int[nums.length+2];
        
        //to save time we only put non-zero num to newNums[] since zero num will not contribute to max coins
        //n is pointer in newNums 
        int n = 1;
        for(int num : nums) if(num != 0) newNums[n++] = num;
        //set nums[-1] and nums[n], now n becomes the size of new array
        newNums[0] = newNums[n++] = 1;
        
        int[][] dp = new int[n][n];
        
        //we fill the dp table from base case to normal case
        //for each dp cell, we will look for the last balloon to burst
        //Since once last balloon is fixed, we can safely get max coin values from left and right side as well
        
        for(int len = 1; len < n; len++){
            //exclude nums[-1] and nums[n] from left and right
            //we need 3 balloons to calcualte coin so left cannot >= n - len
            for(int left = 0; left < n - len; left++){
                int right = left + len;
                //try each balloon in the (left, right) to be the last balloon to burst
                //choose the one that gives max coin sum
                for(int i = left + 1; i < right; i++){
                    dp[left][right] = Math.max(dp[left][right], newNums[left] * newNums[i] * newNums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }

        return dp[0][n-1];
    }
    
    public static void print(int[][] dp){
    	for(int i = 0; i < dp.length; i++){
    		for(int j = 0; j < dp[0].length; j++){
    			System.out.print(dp[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
}
