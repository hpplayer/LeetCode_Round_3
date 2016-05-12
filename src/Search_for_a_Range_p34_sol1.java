/*
34. Search for a Range

Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

/**
 * Binary search solution
 * 
 * input in sorted, we can use binary search to found the target range within logN time
 * 
 * In this solution we use binary search to search the lower boundary of target and target + 1
 * The upper boundary of target is just the (lower boundary of target + 1) - 1
 * 
 * when mid pointer points to cell that has same value with target, we must move left or right pointer to avoid stuck
 * in this cell. Since we are looking for lower boundary here, and our binary search always find mid pointer leftward
 * (ex. (2, 2), mid pointer points to index 0), we need to keep left pointer standstill and move right pointer therefore
 * our left pointer will always points to a target cell if we have one.
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 * 
 * Remark:
 * Binary search can be implemented many ways to attack this problem. This solution uses (left == right) as loop
 * condition, but we can also use (left < right) as loop condition. In such case we need to have extra check to make
 * sure left or right pointer points to the target cell
 * 
 * @author hpPlayer
 * @date May 11, 2016 8:52:24 PM
 */
public class Search_for_a_Range_p34_sol1 {
    public int[] searchRange(int[] nums, int target) {
        //binary search solution. In this solution, we always search for the lower boundary of target
        //therefore we firstly search the lower boundary of target, then search for the lower boundary of target + 1
        //the right boundary of target would just be (lower boundary of (target + 1)) - 1
        //to save time, we can let second binary search start after index we found in first search
        
        int[] result = new int[]{-1, -1};
        
        //firstly search the lower boundary of target
        int left = binary_search_lower_boundary(nums, 0, target);
        //left pointer may be out of boundary if we dont have valid input (ex. all vals < target)
        //or left pointer may be within range but does not point to a target cell
        //In either case we wouldn't have target in input, just return result directly 
        if(left >= nums.length || nums[left] != target) return result;
        result[0] = left;
        
        //secondly search the lower boundary of target + 1
        //to save time second search will start with "left" we found above
        int right = binary_search_lower_boundary(nums, left, target+1);
        //upper boundary of target is just (lower boundary of target + 1) - 1
        result[1] = right -1;
        
        return result;
    }
    
    public int binary_search_lower_boundary(int[] nums, int left, int target){
        //in this binary search, we let left pointer points to lower boundary of target
        int right = nums.length - 1;
        
        while(left <= right){
           int mid = left + (right - left)/2;
           //to keep left pointer at lower boundary, we need move right pointer instead when mid pointer points
           //to target cell
           if(nums[mid] < target){
               //too small
               left = mid + 1;
           }else if(nums[mid] == target){
              //we move right pointer when nums[mid] = target, so our left pointer can always point to target cell
               right = mid - 1;
           }else if(nums[mid] > target){
               //too large
               right = mid - 1;
           }
       }
       
       //return left pointer as the lower boundary of target range
       return left;
    }
}
