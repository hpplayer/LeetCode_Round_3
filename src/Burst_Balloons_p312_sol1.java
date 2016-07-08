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
 * Remark:
 * I provided two functions below maxCoins() and maxCoins, both use similar idea but with small differences
 * In maxCoins(), dp[i][j] means the max coin we can get from burst balloons among i and j, both are inclusive, so we need i-1 and j+1 when calculating last balloon
 * In maxCoins2(), dp[i][j] means the max coin we can get from burst balloons among i+1 and j-1, so i and j are not inclusive, and we don't need extra balloons to
 * calculate the last balloon. Also we preprocess the input to exclude cells with 0 value, no significant improvement though
 * 
 * Another similar O(N^3) dp solution: Longest_Palindromic_Substring_p5_sol2
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
        //dp solution. We find the solution from bottom to top. We firstly find the case that we have only one balloon left with len 1
        //then find the case we have two balloons left, three balloons left, etc. For each length, we will find the balloon in the range that
        //burst last and gives the max coins. Since we are picking the last balloon, this balloon must have a fixed neighbors therefore we 
        //can safely burst it and calcualte the coins it produces
        
        //to cover boundary cases, we include index -1 and n into our scope
        int[] copy = new int[nums.length+2];
        //set index -1 and n
        copy[0] = copy[copy.length-1] = 1;
        //fill other cells
        for(int i = 0; i < nums.length; i++) copy[i+1] = nums[i];
        
        //create dp matrix, dp[i][j] means the max coin we can get from burst balloons among i and j (both inclusive)
        //We still need i-1 and j+1 value to calculate the total coins. In other words, i-j is the range in mid, to burst the last balloon in
        //this range, we still need two balloons in index i - 1 and j + 1
        int[][] dp = new int[copy.length][copy.length];
        
        //mid range can from 1 to nums.length
        for(int len = 1; len <= nums.length; len++){
            //left pointer is for i, since we add extra cell before and after input range, the left range now is from 1 to len(nums) - len + 1 (we add 1 here to make the index offset for extra head cell)
            for(int left = 1; left <= nums.length - len + 1; left++){
                //get the right index
                int right = left + len - 1;
                
                //search for the last balloon to burst which gives the max coins
                for(int i = left; i <= right; i++){
                    //copy[i] will be the last balloons to burst among left and right
                    //since our dp value is inclusive, therefore we need dp[left][i-1] and dp[i+1][right] to get rest value
                    dp[left][right] = Math.max(dp[left][right], copy[left-1] * copy[i] * copy[right+1] + dp[left][i-1] + dp[i+1][right]);
                }
            }
        }
        
        //without extra cell, we are supposed to get value from dp[0][len-1], i.e. from leftmost index 0 to rightmost index len -1
        //but now we rightshift the array due to extra cell ahead, so we need get dp[1][len];
        return dp[1][nums.length];
        
    }
    
    public static int maxCoins2(int[] nums) {
        //include nums[-1] and nums[n]
        int[] newNums = new int[nums.length+2];
        
        //to save time we only put non-zero num to newNums[] since zero num will not contribute to max coins
        //n is pointer in newNums 
        int n = 1;
        for(int num : nums) if(num != 0) newNums[n++] = num;
        //set nums[-1] and nums[n], now n becomes the size of new array
        newNums[0] = newNums[n++] = 1;
        
        //dp[i][j] means the max coins we can get by bursting balloons from i to j
        //But we will not burst the leftmost and rightmost balloon which is i and j, so actually it considers the range between i+1 and j-1
        //so actually now we have considered -1 and len balloon into consideration
        //Now dp has length with len +2, so dp[0][len+1] will give us the correct solution
        //but now we use "n" to avoid 0s, and n = len + 2, so finally we use dp[0][n-1] to get correct solution
        int[][] dp = new int[n][n];
        
        //we fill the dp table from base case to normal case
        //for each dp cell, we will look for the last balloon to burst
        //Since once last balloon is fixed, we can safely get max coin values from left and right side as well
        //len help us get the right index after left index. ex: len =2, left = 0, then right index will be 0 +2 = 2, i.e. the 3rd balloon
        //we choose start len to be 2 because we need at least 3 balloons to get coins and len2 can help us get the 3rd balloon at index left + 2 
        //we choose n -1 to be end len because index issue (the most right index is at n -1)
        for(int len = 2; len < n; len++){
            //exclude nums[-1] and nums[n] from left and right
            //we need 3 balloons to calculate coin so left cannot >= n - len
        	//assume left = 0, then n + maxLen should < n, since the max index in dp[][] is n-1
            for(int left = 0; left < n - len; left++){
            	//Based on the rule above, we can use left + len to get the right index directly
                int right = left + len;
                //try each balloon in the (left, right) to be the last balloon to burst
                //choose the one that gives max coin sum
                for(int i = left + 1; i < right; i++){
                	//dp[left][i] is max coins we can get from left -> i, but we still have balloon left and i not bursted yet
                	//dp[i][right] is max coins we can get from i -> right, but we still have balloon i and right not bursted yet
                	//by picking balloon i, we can connect two parts together and add a new coin amount: newNums[left] * newNums[i] * newNums[right]
                	//so dp[left][right] would be the sum of above values
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
