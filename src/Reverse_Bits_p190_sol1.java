/*
190. Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
*/

/**
 * Bit manipulation solution
 * 
 * We use rightshift to read bit from input one by one. Then we use leftshift to write bit to output one by one.
 * Since we need to reverse the order, we will firstly write 31th index, and write 0th index lastly
 * 
 * This solution is similar to Reverse_Integer_p7_sol1
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * Remark:
 * We can also use merge-sort & hex concepts to set the input in faster way. But such solution needs proficiency in hexadecimal, so I will not list it here.
 * @author hpPlayer
 * @date May 31, 2016 1:52:02 AM
 */
public class Reverse_Bits_p190_sol1 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        //bit manipulation solution. Rightshift input to read bit and leftshift to write bit
        
        int result = 0;
        
        for(int i = 31; i >= 0; i--){
            //leftshift to set bit in output
            result |= (n&1) << i;
            //rightshift to prepare input for next read
            n >>= 1;
        }
        
        return result;
    }
}
