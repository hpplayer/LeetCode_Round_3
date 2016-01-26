import java.util.LinkedList;

public class Burst_Balloons_p312_sol1 {
	public static void main(String[] args){
		int[] nums = {9,76,64,21,97,60,5};
		System.out.println(maxCoins(nums));
	}
	
    public static int maxCoins(int[] nums) {
    	int[] newNums = new int[nums.length +2];
    	int n = 1;
    	for(int num : nums) newNums[n++] = num;
    	newNums[0] = newNums[newNums.length - 1] = 1;
    	
    	n = newNums.length;
    	
    	int[][] dp = new int[newNums.length][newNums.length];
    	
    	for(int len = 2; len < n; len++){
    		for(int left = 0; left < n - len; left++){
    			int right = left + len;
    			for(int i = left + 1; i < right; i++){
    				dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
    			}
    		}
    	}
    	
    	return dp[0][n-1];
    }
}
