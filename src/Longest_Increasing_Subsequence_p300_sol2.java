/**
 * Pure dp solution
 * 
 * We use a dp table to record the len of increasing subsequence ended at current value. For each input, we check all cells before it that has smaller
 * value and take the max len then pluse one 
 * 
 * 
 * Time complexity: O(N^2)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 6, 2016 11:41:30 PM
 */
public class Longest_Increasing_Subsequence_p300_sol2 {
	public static void main(String[] args){
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println(lengthOfLIS(nums));
	}
    public static int lengthOfLIS(int[] nums) {
        //dp solution. DP value now means the len of increasing subsequence ended at current value. For each new input, we just scan
        //dp cells before and look at dp cells that have nums[i] < current input
        
        //boundary check 
        if(nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        dp[0] = 1;
        
        //if input array's len >= 1, then result will be at least 1
        int result = 1;
        
        for(int i = 1; i < nums.length; i++){
            int count = 0;
            for(int j = i - 1; j >= 0; j--){
                if(nums[j] < nums[i]){
                    //find the previous dp[j] that gives longest increasing subsequence
                    count = Math.max(count, dp[j]);
                }
            }
            //update dp [i] value. Its len will be previous longest increasing subsequence pluse one to include current input
            dp[i] = count + 1;
            result = Math.max(result, dp[i]);
        }
        
        return result;
    }
}
