/*
136. Single Number

Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

/**
 * Bit manipulation solution
 * 
 * We XOR all nums in input. All elements appear twice will be canceled, and 0 ^ n = n, so final result will be
 * the element that appears only once
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 25, 2016 10:46:42 PM
 */
public class Single_Number_I_p136_sol1 {
    public int singleNumber(int[] nums) {
        //bit manipulation solution
        int result = 0;
        for(int num : nums) result ^= num;
        return result;
    }
}
