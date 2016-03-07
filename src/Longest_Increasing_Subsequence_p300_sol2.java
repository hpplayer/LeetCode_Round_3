
public class Longest_Increasing_Subsequence_p300_sol2 {
	public static void main(String[] args){
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println(lengthOfLIS(nums));
	}
    public static int lengthOfLIS(int[] nums) {
        if(nums.length < 2) return nums.length;
        
        int[] dp = new int[nums.length];
        dp[0] = 1;
        
        int result = 0;
        
        for(int i = 1; i < nums.length; i++){
            int count = 0;
            for(int j = i - 1; j >= 0; j--){
                if(nums[j] < nums[i]){
                    count = Math.max(count, dp[j]);
                }
            }
            dp[i] = count + 1;
            result = Math.max(result, dp[i]);
        }        
        
        return result;
    }
}
