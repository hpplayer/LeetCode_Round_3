/*
268. Missing Number

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

/**
 * Bit manipulation solution
 * 
 * we make use of the property that n^0^n = 0 and n^0 = n
 * we will let 0 to n XOR with 0, then XOR with all nums in input
 * The remaining value would be the missing value
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol1 is bit manipulation solution
 * Sol2 is pure math solution
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 9:42:51 PM
 */
public class Missing_Number_p268_sol1 {
    public int missingNumber(int[] nums) {
        //bit manipulation solution. We make use of n^n^0 = 0 and n^0 = n to get the missing num
        
        int result = 0;
        
        for(int i = 0; i < nums.length; i++){
            //to make for loop goes continuously, we put XOR nums.length after the loop 
            result ^= nums[i] ^ i;
        }
        
        //we do XOR nums.length(i.e. n) now
        
        return result^nums.length;
    }
}
