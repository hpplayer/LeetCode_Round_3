/**
 * Intuitive bit manipulation solution
 * 
 * We just scan input bit by bit to count how many 1s in the input
 * 
 * Time complexity: O(32) = O(1)
 * Space complexity: O(1)
 * 
 * Remark:
 * 1. rightshift >> will keep the sign, in case input is negative, we need use >>> rightshift input to avoid getting stuck
 * This solution can be seen in hammingWeight2() below
 * 2. We don't have unsigned leftshift (<<<) since signed and unsigned leftshift are identical, and both ignore the 
 * sign of input. So in hammingWeight() we use << to update mask
 * 
 * @author hpPlayer
 * @date May 31, 2016 9:51:15 PM
 */
public class Number_of_1_Bits_p191_sol2 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        //intuitive bit manipulation solution
        
        int result = 0;
        int mask = 1;
        
        for(int i = 0; i < 32; i++){
            if( (mask&n) != 0){
                result++;
            }
            mask = mask << 1;
        }
        
        return result;
    }
    
    // you need to treat n as an unsigned value
    public int hammingWeight2(int n) {
        int result = 0;
        
        while(n != 0){
            if( (n&1) == 1) result++;
            n >>>= 1;
        }
        
        return result;
    }
}
