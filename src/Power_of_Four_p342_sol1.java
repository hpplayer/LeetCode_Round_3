/*
342. Power of Four

Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?
*/

/**
 * Bit manipulation + Hex solution
 * 
 * A num would be a power of 4 if it meets following requirements:
 * 1) > 0
 * 2) has a single 1 in binary representation
 * 3) 1 only appears in even indexes (counting from right to left and starting at index 0)
 * 
 * Sol1 just apply those requirements.
 * For requirement 3, we will use 0x55555555. 5 is 0101 in hex representation
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * Remark:
 * 1 is also a power of 4 (4^0 = 1)
 * 
 * @author hpPlayer
 * @date Jun 6, 2016 11:39:26 PM
 */
public class Power_of_Four_p342_sol1 {
    public boolean isPowerOfFour(int num) {
        //bit manipulation + Hex solution
        //5 in hexdecimal is just 0101
        
        //input should be positive, has only one 1 bit (num&(num-1) == 0) and 1 bit is in even indexes
        return num > 0 && (num & (num-1)) == 0 && (num&0x55555555) != 0;
    }
}
