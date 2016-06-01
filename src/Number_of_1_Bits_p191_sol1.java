/*
191. Number of 1 Bits

Write a function that takes an unsigned integer and returns the number of ¡¯1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ¡¯11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/

/**
 * Tricky bit manipulation solution
 * 
 * We make use of the trick to remove the trailing 1.
 * n & (n-1) will always remove the least significant 1
 * We just need to count how many loops we used to remove all 1s to get the solution
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * Sol1 is tricky bit manipulation solution
 * Sol2 is intuitive bit manipulation solution
 * 
 * @author hpPlayer
 * @date May 31, 2016 9:45:19 PM
 */
public class Number_of_1_Bits_p191_sol1 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        //Tricky bit manipulation solution
        
        int result = 0;
        
        //Notice: since we may have negative input as well, we need use n!= 0 instead of n > 0 here!!!!!!!!!!!!!
        while(n != 0){
            //remove least significant 1
            n &= n -1;
            //increase counter
            result++;
        }
        
        return result;
    }
}
