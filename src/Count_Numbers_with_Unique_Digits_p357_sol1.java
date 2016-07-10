/*
357. Count Numbers with Unique Digits

Given a non-negative integer n, count all numbers with unique digits, x, where 0 ¡Ü x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ¡Ü x < 100, excluding [11,22,33,44,55,66,77,88,99])

Hint:

A direct way is to use the backtracking approach.
Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which
represent which number is marked as visited so far in the current number). Start with state (0,0,0) and count all valid number till
we reach number of steps equals to 10n.

This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
Let f(k) = count of numbers with unique digits with length equals k.
f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].
*/

/**
 * DP + Math solution
 * 
 * Based on the knowledge of combination we have:
 * f(1) = 10;
 * f(2) = 9 * 9
 * f(3) = 9 * 9 * 8
 * f(4) = 9 * 9 * 8 * 7
 * ..
 * f(11) = 0 as we must have a duplicate digit when len > 10
 * 
 * So we can see that f(1) is boundary case, but after that we have f(n) = f(n-1) * k, where k is num of remaining
 * digits.
 * 
 * So we can use DP to reuse prev results, and use a variable to record the num of remaining digits
 * 
 * Time complexity: O(1), as the max of num of remaining digits is 10
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jul 9, 2016 9:38:57 PM
 */
public class Count_Numbers_with_Unique_Digits_p357_sol1 {
    public int countNumbersWithUniqueDigits(int n) {
        //boundary check
        if(n == 0) return 1;
        
        //n = 1 is base case
        //total result
        int result = 10;
        //prev result, for n = 2, prev = 9 not 10 since 0 cannot be heading digit
        int prev = 9;
        //remaining digits
        int availableDigits = 9;
        
        while( n > 1 && availableDigits > 0  ){
            prev *= availableDigits;
            result += prev;
            availableDigits--;
            n--;
        }
        
        return result;
    }
}
