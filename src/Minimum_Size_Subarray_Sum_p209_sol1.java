/*
209. Minimum Size Subarray Sum

Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ¡Ý s.
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/


/**
 * Sliding window solution
 * 
 * We use two pointers to maintain a sliding window, we use right pointer to include more cells into the window to reach sum s
 * as long as left pointer <= right pointer and window sum >= s, we can move left pointer to shrink the window
 * 
 * Time complexity: O(N), since each cell will be visited at most twice
 * Space complexity: O(1)
 * 
 * Sol1 is O(N) solution with sliding window approach
 * Sol2 is O(NlogN) solution that still using sliding window approach but use binary search to move the left boundary of window
 * 
 * @author hpPlayer
 * @date Mar 3, 2016 11:33:49 PM
 */
public class Minimum_Size_Subarray_Sum_p209_sol1 {
    public int minSubArrayLen(int s, int[] nums) {
        //sliding win soltuion. We will extend the size of window until currSum >= s, then we try to shrink the window 
        //then we update the min size of sliding window
        
        int left = 0, sum = 0;
        int result = Integer.MAX_VALUE;
        
        for(int right = 0; right < nums.length; right++){
            sum += nums[right];
            //while we have a valid window (left <= right) and window sum >= s, we can move left pointer
            while(sum >= s){
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
            
        }
        
        return result == Integer.MAX_VALUE? 0 : result;
    }
}
