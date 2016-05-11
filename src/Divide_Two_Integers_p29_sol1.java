/*
29. Divide Two Integers 

Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/

/**
 * Bit manipulation solution
 * 
 * To avoid the use of multiplication, division and mod operator, we can only use bit manipulation, more specifically,
 * bit shift to divide the inputs. 
 * Each time we will increase divisor by a size of 2 since each left shift will increase the divisor by a size of 2
 * And we need to be careful with sign, overflow
 * 
 * Time complexity: O(1), as len of int is fixed 32 bits
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 10, 2016 9:37:15 PM
 */
public class Divide_Two_Integers_p29_sol1 {
    public int divide(int dividend, int divisor) {
        //Bit shift solution. Use left shift to increase divisor and get the multiply
        //Each time we will increase divisor by a size of 2, compared with general division, where each loop
        //we can increase divisor by 1 to 9
        
        //if two inputs are integer, there are only two cases that we can get overflow:
        if( divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1) ) return Integer.MAX_VALUE;
        
        //For convenience, we will remove pos/neg sign during shift, and add it back in the end
        boolean isNeg = (dividend < 0) ^ (divisor < 0);
        
        //Remove neg sign of int_min will give overflow, so we use long type to catch it
        long abs_dividend = Math.abs( (long) dividend);
        long abs_divisor = Math.abs( (long) divisor);
        
        int result = 0;
        
        //like general divison, we will continue if dividend is still dividable
        while( abs_dividend >= abs_divisor ){
            //Initialize temp with abs_divisor, each loop we will increase temp to reach curr val of abs_dividend
            long temp = abs_divisor, multiply = 1;
            
            //as long as abs_dividend is still diviable, we will increase temp and multiply
            while(abs_dividend >= (temp << 1) ){
                temp <<= 1;
                multiply <<= 1;
            }
            
            result += multiply;
            abs_dividend -= temp;
        }
        
        //return result and add sign back
        return isNeg? -1 * result : result;
    }
}
