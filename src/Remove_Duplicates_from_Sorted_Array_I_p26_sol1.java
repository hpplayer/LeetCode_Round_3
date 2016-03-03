/*
26. Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the new length.

*/

/**
 * Two pointer solution
 * 
 * One point points to the cell after current new array, one point points to current cell in old array
 * Say pointer 1 is index, pointer 2 is i. Then we compare value in nums[index - 1] with nums[i], if they are different, we will copy val in 
 * nums[i] to nums[index]
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 2, 2016 2:03:46 PM
 */
public class Remove_Duplicates_from_Sorted_Array_I_p26_sol1 {
    public int removeDuplicates(int[] nums) {
        //two pointer solution. One pointer points to the cell that we can place new value, and one pointer is used to scan input array
        int index = 0;
        
        for(int i= 0; i < nums.length; i++){
            //index is the cell we can place new value, index - 1 then will be the last cell in curr new array
            //if curr cell has different value with last cell in new array, then we can place it in index
            if(index == 0 || nums[i] != nums[index-1]){
                nums[index++] = nums[i];
            }
        }
        
        return index;
    }
}
