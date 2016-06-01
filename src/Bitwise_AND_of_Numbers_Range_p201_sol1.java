/*
201. Bitwise AND of Numbers Range

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
*/

/**
 * Bit manipulation solution
 * 
 * The tricky is to realize that the "AND_of_Numbers_Range" is based on common prefix. As long as prefix is shared by
 * m and n, we can have same bit in result. After the common part all bits will be 0 as we must have numbers in between
 * that has 0 in that bit, which will make the "AND" operation have 0 in that bit
 * 
 * We found the largest number <= m and n that have common prefix shared by m and n
 * ex. m = 1100, n = 1111, then this number would be 1100, as it shares the common prefix 11 with m and n
 * To identify this input, we start with n, and use n & (n-1) to remove the least significant 1 until we find the 
 * the number with common prefix
 * 
 * Time complexity: O(1) as input range is known
 * Space complexity: O(1)
 * 
 * Sol1 is the bit manipulation solution based on updating n
 * Sol2 is the bit manipulation solution based on updating m and n
 * 
 * Remark:
 * n & (n-1) is commonly used to remove rightmost 1. Number_of_1_Bits_p191_sol1 also uses a such operation
 * @author hpPlayer
 * @date May 31, 2016 11:04:30 PM
 */
public class Bitwise_AND_of_Numbers_Range_p201_sol1 {
    public int rangeBitwiseAnd(int m, int n) {
        //Bit manipulation solution. We iteratively update n until we found the largest number that shares common
        //prefix with m and n
        
        //the target number should be <= m
        while(n > m){
            //keep removing least significant 1 from n until we found the target
            n &= (n-1);
        }
        
        //now n is the target number, return it
        return n;
    }
}
