/*
231. Power of Two

Given an integer, write a function to determine if it is a power of two.
*/

/**
 * Bit manipulation
 * 
 * The binary representation of a number with power of two should be a 1 followed by all 0s
 * We use (n & (n-1)) to remove the first/last 1, if result is 0, then we know it is a number with power of 2
 * Also we need check if input <= 0, a power of 2 will never be <= 0
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 10:51:30 PM
 */
public class Power_of_Two_p231_sol1 {
    public boolean isPowerOfTwo(int n) {
        //n&(n-1) will remove last 1 in binary representation
        return n > 0 && (n&(n-1)) == 0;
    }
}
