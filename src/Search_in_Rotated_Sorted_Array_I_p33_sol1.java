/*
33. Search in Rotated Sorted Array

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

/**
 * Binary search solution
 * 
 * We just need to modify the binary search a bit. We only search based on the feedback return from non-rotated part. 
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 16, 2016 1:30:35 AM
 */
public class Search_in_Rotated_Sorted_Array_I_p33_sol1 {
    public int search(int[] nums, int target) {
        //binary search solution. Move pointer based on the non-rotated part of input
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                //target found
                return mid;
            }else if(nums[left] <= nums[mid]){
                //if left part is non-rotated
                //Notice: in case nums[left] = nums[mid], it is also a non-rotated part of input!!!!!!!!!!!!!!!!!!!!!!!!
                if(target >= nums[left] && target < nums[mid]){
                     //if target is within non-rotated part
                     right = mid - 1;
                }else{
                    //if target is in rotated part
                    left = mid + 1;
                }
            }else{
                //if right part is non-rotated
                if(target > nums[mid] && target <= nums[right]){
                     //if target is within non-rotated part
                     left = mid + 1;
                }else{
                    //if target is in rotated part
                    right = mid - 1;
                }                
            }
        }
        
        return -1;
    }
}
