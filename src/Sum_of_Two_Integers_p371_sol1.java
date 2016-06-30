/*
371. Sum of Two Integers

Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.
*/

/**
 * Bit manipulation solution
 * 
 * Adding two nums: 
 * using a^b, and we will get the sum of with at least one bit unset
 * The reason is that a^b can help us reset 1 + 1 => 0, 0 + 0 => 0 and 1 + 0 or 0 + 1 => 1
 * 
 * using a&b, and we will get the carry, leftshift this carry then add it to a^b
 * The reason is that only when we have both 1s in bit i, can we have a carry at index i + 1 (leftshift by 1) 
 * 
 * ex: 
 * a: 1, b: 0
 * a^b => 1, a&b => 0, a^b + ((a&b) << 1) => 1
 * 
 * a: 1, b: 1
 * a^b => 0, a&b => 1, a^b + ((a&b) << 1) => 10
 * 
 * Time complexity: O(1) as integer size is known
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 29, 2016 8:43:56 PM
 */
public class Sum_of_Two_Integers_p371_sol1 {
    public int getSum(int a, int b) {
        //for us, b is carry, a is target num
        //so as long as we have b not 0, we will add it to a
        while(b != 0){
            //use a third variable to store carry temporarily
            int carry = a & b;
            
            //update a to be the sum with at least one bit not changed
            a = a ^ b;
            
            //leftshift carry and set it to b
            b = carry << 1;
        }
        
        return a;
    }
}
