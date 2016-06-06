/*
338. Counting Bits


Given a non negative integer number num. For every numbers i in the range 0 ¡Ü i ¡Ü num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly
in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any built-in function like __builtin_popcount in c++ or in any other language.
Hint:

You should make use of what you have produced already.
Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
Or does the odd/even status of the number help you in calculating the number of 1s?

*/

/**
 * Observation + Bit manipulation + DP solution
 * 
 * We divide each input into two parts (n>>1) part and (n&1) part
 * (n>>1) part can be found from previous result and (n&1) part can be calculated in O(1) time
 * So this is the quickest and easiest solution
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 9:50:36 PM
 */
public class Counting_Bits_p338_sol1 {
    public int[] countBits(int num) {
        //Observation + DP + bit manipulation solution. We divide each input into two parts 1) n >> 1 2) n & 1
        
        //num from 0 to num (both inclusive)
        int[] dp = new int[num+1];
        
        for(int i = 1; i <= num; i++){
            //each num can be decomposed into 1) n >> 1 (prev results) and 2) n & 1 (odd or even)
            //Notice: to have () around bit manipulation!!!!!!!!!!!!!!!!!!!!!!!!
            dp[i] = dp[i>>1] + (i&1);
        }
        
        return dp;
    }
}
