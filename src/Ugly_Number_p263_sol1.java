/*
263. Ugly Number

Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.
*/

/**
 * Recursive solution
 * 
 * We divide input by all possible divisor (2, 3, 5) and check if remainder is in {1, 2, 3, 5}
 * If yes, then return true
 * Otherwise return false
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 * 
 * Sol2 is iterative solution
 * 
 * @author hpPlayer
 * @date Feb 25, 2016 10:20:27 AM
 */
public class Ugly_Number_p263_sol1 {
    public boolean isUgly(int num) {
        //recursively divide input by 2 or 3 or 5, if remaining num is 1 or 2 or 3 or 5 then return true, otherwise return false
        
        //boundary check
        if(num <= 0) return false;
        
        if(num == 1 || num == 2 || num == 3 || num == 5) return true;
        
        //divide 2 or or 5 if possible
        if(num%2 == 0) return isUgly(num/2);
        if(num%3 == 0) return isUgly(num/3);
        if(num%5 == 0) return isUgly(num/5);
        
        //otherwise return false
        return false;
    }
}
