/*
81. Search in Rotated Sorted Array II

Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

/**
 * Binary search solution
 * 
 * Variation of Search_in_Rotated_Sorted_Array_I_p33_sol1. This time we allows input has duplicates
 * The basic idea is similar to Search_in_Rotated_Sorted_Array_I_p33_sol1, we just decide next search range based on the sorted part.
 * But now we need handle equal case properly.
 * 
 * In Search_in_Rotated_Sorted_Array_I_p33_sol1, when nums[left] == nums[mid], we treat it as a non-rotated part. But now we can't make 
 * such decision since it also implies duplicates as well. But if we found nums[mid] != target, then we can safely jump over nums[left]
 * 
 * Time complexity: O(N), assuming all inputs except target cell is duplicates, then we have to go over all array to locate target
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 16, 2016 1:41:13 AM
 */
public class Search_in_Rotated_Sorted_Array_II_p81_sol1 {
    public boolean search(int[] nums, int target) {
        //Binary solution. Similar to Search_in_Rotated_Sorted_Array_I_p33_sol1, but now we need to handle nums[left] = nums[mid] seperatedly
        
        int left = 0, right = nums.length - 1;
        
        while(left <= right){
            //scan all cells
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                //target found
                return true;
            }else if(nums[mid] > nums[left]){
                //left part is non-rotated
                if(nums[left] <= target && target < nums[mid]){
                    //target is in non-rotated part
                    right = mid - 1;
                }else{
                    //target is in rotated part
                    left = mid + 1;
                }
            }else if(nums[mid] < nums[left]){
                //left part is rotated, therefore we need decide next search range based on right part
                if(nums[mid] < target && target <= nums[right]){
                    //target is in right part
                    left = mid + 1;
                }else{
                    //target is in left part
                    right = mid - 1;
                }
            }else{
                //equal case, where nums[mid] = nums[left]. Maybe duplicates, but nums[left] will definitly not the target cell, so
                //we can safely skip over it
                left++;
            }
        }
        
        return false;
    }
}
