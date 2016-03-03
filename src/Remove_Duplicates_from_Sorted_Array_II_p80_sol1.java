/*
80. Remove Duplicates from Sorted Array II

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
It doesn't matter what you leave beyond the new length.
*/

/**
 * Two pointer solution
 * 
 * Similar to Remove_Duplicates_from_Sorted_Array_I_p26_sol1, but now we need to compare nums[i] with nums[i-2] since we allow a value appear twice
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 2, 2016 2:23:09 PM
 */
public class Remove_Duplicates_from_Sorted_Array_II_p80_sol1 {
    public int removeDuplicates(int[] nums) {
        //two pointer solution. But now we need to compare nums[index-2] with nums[i] since we allow a number appears at most twice
        
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            //we scan input array from index 0, so we can correctly handle case nums.length < 2 as well
            if(index < 2 || nums[i] != nums[index-2]){
                nums[index++] = nums[i];
            }
        }
        
        return index;
    }
}
