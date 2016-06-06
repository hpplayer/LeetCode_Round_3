import static org.junit.Assert.*;
import java.math.BigInteger;

/*
306. Additive Number

Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers,
each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?
*/		

/**
 * Observation + Big Integer solution
 * 
 * There is no algorithm or shortcut to solve this problem. We just use loops to try each possible substring, then
 * check if we can reach the end of input.
 * 
 * The tricky part is to handle boundary cases, which I have listed it below.
 * 
 * The basic idea is using a nested loop to determine first two numbers, then using a helper function to check the
 * remaining part of input. We will have an expected numbers for the third and following numbers, so we can use 
 * String.startsWith to check them which will make our code more neat and clean.
 * To answer the follow-up question, here we use BigInteger to catch extra large inputs.
 * 
 * Boundary cases:
 * 1) heading 0s in a substring whose len > 1
 * 2) but we should allow 0 itself to be a valid input
 * 3) very large inputs. In such cases, we can convert string to charArray and add inputs using for loops + carry
 * or just use BigInteger
 * 4) Len range for num1, num2 and num3.
 * For num1, the num1_min_len should be 1, the num1_max_len should be < len(input) - len1. In other words,
 * the remaining len in input should > num1_max_len
 * For num2, the num2_min_len should be 1, the num2_max_len is bounded by the remaining len in input, which should
 * be >= Math.max(num1_curr_len, num2_curr_len)
 * For num3, its len should be determined by num1 and num2, so we don't need to worry about it
 * 
 * Time complexity: recursion, exponential
 * Space complexity: same as above
 * 
 * Remark:
 * Due to the extra cost on bigInteger, this solution is not fast
 * But if we are not worry about the overflow solution, then we can use Long instead of BigInteger, and the speed
 * will improve a lot!
 * 
 * Sol1 is recursive solution that using BigInteger to catch overflow
 * Sol2 is iterative solution that uses similar idea with sol1
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 12:18:52 PM
 */

public class Additive_Number_p306_sol1 {
	public static void main(String[] args){
		//String num = "456565346756354326545423524367538745656588910879108041";
		Additive_Number_p306_sol1 test = new Additive_Number_p306_sol1();
		test.test(test);
		System.out.println("all tests done!");
		//System.out.println(test.isAdditiveNumber(num));
	}
	
    public boolean isAdditiveNumber(String num) {
        //Observation + BigInteger solution. We use BigInteger to handle overflow
        
        int n = num.length();
        
        //we use a nested loop to determine first two nums: num1 and num2
        //for lenofNum1, its max value should let remaining input len still have len = (lenofNum1 + 1)
        //ex: num1 = 99, num2 = 1, and num3 = 100
        for(int lenOfNum1 = 1; lenOfNum1 < n - lenOfNum1; lenOfNum1++){
            //check if num1 starts with 0
            if(num.charAt(0) == '0'&& lenOfNum1 > 1) return false;
            BigInteger num1 = new BigInteger( num.substring(0, lenOfNum1) );
            
            //for lenofnum2, its max value should let remaining len >= Math.max(len_num1, len_num2)
            for(int lenOfNum2 = 1; Math.max(lenOfNum2, lenOfNum1) <= n - lenOfNum1 - lenOfNum2; lenOfNum2++){
                //check if num2 starts with 0
                if(num.charAt(lenOfNum1) == '0' && lenOfNum2 > 1) break;
                BigInteger num2 = new BigInteger(num.substring(lenOfNum1, lenOfNum2 + lenOfNum1));
                if(isValid(num1, num2, num.substring(lenOfNum1+lenOfNum2))) return true;
            }
        }
        
        //all num1 and num2 checked, still cannot find a solution
        return false;
    }
    
    private boolean isValid(BigInteger num1, BigInteger num2, String remainingStr){
        //reach end, return true
        if(remainingStr.length() == 0) return true;
        
        //get sum, i.e. the third num, num3
        BigInteger sum = num1.add(num2);
        String sum_string = sum.toString();
        
        //use string.startsWith to check if remainingStr starts with sum
        //if it is, then check update num1 and num2 to be num2 and sum, and check rest of input
        return remainingStr.startsWith(sum_string) && isValid(num2, sum, remainingStr.substring(sum_string.length()));
    }
    
    public void test(Additive_Number_p306_sol1 sol) {
        assertTrue(sol.isAdditiveNumber("101"));
        assertTrue(sol.isAdditiveNumber("112358"));
        assertTrue(sol.isAdditiveNumber("199100199"));
        assertTrue(sol.isAdditiveNumber("1123115"));
        assertTrue(sol.isAdditiveNumber("112"));
        assertFalse(sol.isAdditiveNumber("1123589"));
        assertFalse(sol.isAdditiveNumber("113"));
    }
}
