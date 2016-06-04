/*
233. Number of Digit One

Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
*/

/**
 * Math solution
 * 
 * Very problem specific solution, we decompose input into several parts.
 * ex: input is 5132
 * 
 * First decompose part is countOne(999) * 5 (here 999,1999,2999,3999,4999 all include countOne(999))
 * Second decompose part is extraOnes from 1000~1999
 * Third decompose part is countOne(132)
 * 
 * For first and third decompose part, we have to use recursive call to get the solution
 * For second part, we can just check the first digit(FD) of input. If it is > 1, then we will always have
 * 1000 extraOnes, if it is = 1, then we will have (input - base + 1) ones. (ex: input is 1321, then input-base+1
 * would be 1321 - 1000 + 1 = 322 ones)
 * 
 * Time complexity: O(L) where L is input len
 * Space complexity: O(L) where L is input len
 * 
 * Sol1 is recursive solution that decompose input into smaller and smaller inputs
 * Sol2 is iterative solution that counts ones from digit to digit
 * 
 * @author hpPlayer
 * @date Jun 3, 2016 10:37:53 PM
 */
public class Number_of_Digit_One_p233_sol1 {
    public int countDigitOne(int n) {
        //math solution, we decompose input then use recursions and equations to get the result
        
        //boundary case
        if(n <= 0) return 0;
        
        //get base
        int base = 1;
        //to prevent overflow, we use n/base >= 10 to stop the loop
        while(n/base >= 10){
            base *= 10;
        }
        
        int FD = n/base;
        //extraOnes that from numbers starting with the first digit
        int extraOnes = 0;
        
        if(FD == 1){
            //n starts with 1, base also starts with 1, so we can directly use (n - base)
            extraOnes = n - base + 1;
        }else{
            //n starts with a digit > 1, we will have base numbers of nums starts with 1
            extraOnes = base;
        }
        
        //each input can be decomposed into three parts
        return countDigitOne(base - 1) * FD + extraOnes + countDigitOne(n - FD * base);
    }
}
