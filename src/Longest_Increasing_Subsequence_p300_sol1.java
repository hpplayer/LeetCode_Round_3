import java.util.Arrays;

/*
300. Longest Increasing Subsequence

Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/

/**
 * dp + binary search solution.
 * 
 * We maintain a dp array, dp[i] is the tail value in the increasing subsequence with len i + 1
 * We wanna keep tail value as small as possible so later we can extend it more easily.
 * Since cell value is a tail value of a virtual increasing subsequence, and its index is its len, therefore
 * from index 0 to i, the subsequence length is increasing, so is the tail value, otherwise we can append 
 * the "peak" value to other subsequences to extend the length. Therefore we can use binary search to find
 * the tail value in the dp[]. 
 * 
 * For each input value, it will have 3 cases:
 * case 1 smaller than smallest tail value dp[0], then we need update dp[0] to make this tail value more smaller
 * case 2 larger than largest tail value in dp array so far, then we can insert in next index to create a 
 * longer subsequence. Why can't we just append it after existing array? Thats because it will block us to
 * update the tail in existing array, which later may give a longer subsequence, also we need to follow our
 * rule that dp[i] means subsequence with i + 1.
 * case 3 a value in between. In such case, we will use binary search to find the smallest tail that is larger
 * than this input value. Then we can replace this tail value with current input to make extending more easier
 * 
 * Time complexity: O(NlogN) binary search costs logN and we need do it to all inputs
 * Space complexity: O(N) in case the input is ascending, we need a dp array with N len to store all subsequences
 * 
 * Sol2 is an intuitive dp solution. We use a dp array to record the num of previous smaller cells.
 * Sol2 costs O(N^2) time and O(N) space complexity 
 * 
 * @author hpPlayer
 * @date Mar 6, 2016 10:47:56 PM
 */
public class Longest_Increasing_Subsequence_p300_sol1 {
	public static void main(String[] args){
		int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println(new Longest_Increasing_Subsequence_p300_sol1().lengthOfLIS(nums));
	}
    public int lengthOfLIS(int[] nums) {
        //dp + binary solution. Use dp way to record previous created subsequence and use binary search to look for
        //the index of subsequence that we can update tail value
        
        //boundary check
        if(nums.length == 0) return 0;
        
        //dp array. dp[i] means the tail value of a increasing subsequence with len i + 1
        //we will update and make tail value as small as possible, so later we can easily extend this subsequence 
        int[] dp = new int[nums.length];
        
        //intialize the dp array with a subsequence of len 1 
        dp[0] = nums[0];
        int len = 1;
        
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < dp[0]){
                //input is smaller than smallest tail value so far, we need update dp[0]
                dp[0] = nums[i];
            }else if(nums[i] > dp[len - 1]){
                //input is larger than largest tail value so far, we will extend our current longest subsequence
                //and place new tail value(nums[i]) into next dp cell
                dp[len++] = nums[i];
            }else{
                //input value is in between, we need find the closet subsequence which has tail value > input,
                //then we can update it with current input, so this subsequence can be more easily extended
                //we will use binary search to find such tail value(index)
                int index = binarySearch(0, len - 1, nums[i], dp);
                //we update tail value in subsequence we found
                dp[index] = nums[i];
            }
        }
        
        return len;
    }
    
    public int binarySearch(int left, int right, int target, int[] dp){
        //check each cell
        while(left <= right){
            int mid = left + (right - left)/2;
            if(dp[mid] == target){
                //if we found target is already a tail value, then we just return mid
                //current input will not affect existing subsequences
                return mid;
            }else if(dp[mid] < target){
                //too small
                left = mid + 1;
            }else{
                //too large
                right = mid - 1;
            }
        }
        
        //since we need to return the cloest subseuqnce > target, we will return left
        return left;
    }
}
