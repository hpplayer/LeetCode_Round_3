/*
Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

/**
 * Two pointer solution
 * 
 * One pointer always points to the first zero
 * Another pointer is used to scan the array.
 * 
 * If we found a non-zero num, we will swap it with first zero, then move zero pointer to point the second 0
 * 
 * Time complexity: O(n) as we only scan the array once
 * Space complexity: O(1) as we don't need extra data structure except for the second pointer "zeros"
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 11:56:53 PM
 */

public class Move_Zeroes_p283_sol1 {
    public void moveZeroes(int[] nums) {
        int zeros = 0;//index of first zero
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                //we found a non-zero value, swap it with first zero
                swap(nums, i, zeros);
                //based on our rule, if we have multiple zeros, we should put them together
                //so after swapped first zero, we move to second zero
                zeros ++;
            }
        }
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
