/*
167. Two Sum II - Input array is sorted

Given an array of integers that is already sorted in ascending order,
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target,
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

/**
 * Two pointer solution
 * 
 * We set initial two pointers to be 0 and len - 1 .
 * Compare currSum with target, if currSum > target, then move right pointer, if currSum < target, then
 * move left pointer, otherwise return result
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 28, 2016 9:44:41 PM
 */
public class Two_Sum_II_Input_array_is_sorted_p167_sol1 {
    public int[] twoSum(int[] nums, int target) {
        //two pointer solution
        int left = 0, right = nums.length - 1;
        
        while(left < right){
            //we stop the loop when there is only one element left
            int sum = nums[left] + nums[right];
            if(sum == target){
                return new int[]{left + 1, right + 1};
            }else if(sum < target){
                //too small, move left
                left++;
            }else{
                //too large, move right
                right--;
            }
        }
        
        throw new IllegalArgumentException();
    }
}
