import java.util.*;

/*
16. 3Sum Closest

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

/**
 * Three pointer solution
 * 
 * Three pointer solution, similar to other two three-pointer problems _3Sum_Smaller_p259_sol1 and _3Sum_p15
 * But now we need an extra variable to hold the difference. If we found current sum gives a smaller difference, then we will update result to current
 * difference
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 17, 2016 7:17:12 PM
 */
public class _3Sum_Closest_p16_sol1 {
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int diff = Integer.MAX_VALUE;
        
        Arrays.sort(nums);
        
        for(int i = 0; i + 2 < nums.length; i++){
            int start = i + 1, end = nums.length - 1;
            
            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if(Math.abs(target - sum) < diff){
                    diff = Math.abs(target - sum);
                    result = sum;
                }
                if(sum == target){
                    return sum;
                }else if(sum < target){
                    start++;
                }else{
                    end--;
                }
            }
        }
        
        return result;
    }
}
