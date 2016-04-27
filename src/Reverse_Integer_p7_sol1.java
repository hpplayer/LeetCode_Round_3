/*
7. Reverse Integer

Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows.
How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Update (2014-11-10):
Test cases had been added to test the overflow behavior.
*/

/**
 * Math solution
 * 
 * We rightshift input to get its last digit and rightshift output to append new digit.
 * We need to be careful with overflow/underflow problems, a good check is just check the number part before we reach
 * the boundary point, i.e. check prefix at int_max/10 or int_min/10. If abs(prefix) > int.max/10, then it must be an
 * overflow. If abs(prefix) = int.max/10, then we get no problem, as the only valid input for this case would be 1463847412
 * i.e. 1 and reverse(int.max/10). If abs(prefix) = int.max/10, then we definitely will not get overflow/underflow issues
 * 
 * Time complexity: O(1), as the len of input is fixed
 * Space complexity: O(1), no extra space is required
 * 
 * Remark:
 * we don't need to care about signs of output and % and / operations will keep sign of input!!!!!!!!!!!!!
 * 
 * @author hpPlayer
 * @date Apr 26, 2016 8:37:52 PM
 */
public class Reverse_Integer_p7_sol1 {
    public int reverse(int x) {
        //Math solution, rightshift input to get bit from rightmost digit and leftshift output to append digit to
        //the rightmost index. For the overflow, we just need to check if abs(curr output) <= Integer.MAX_VALUE / 10,
    	//if it is not, then it must be an overflow/underflow
        
        int result = 0;
        
        while( x != 0 ){
            //check overflow/underflow. If abs(result) > Integer.MAX_VALUE/10 while we still have digits left in input
            //then it must be an overflow/underflow. If abs(result) == Integer.MAX_VALUE/10, then we must not have an overflow/underflow.
        	//Since the only possible input for this case would be 1463847412, first digit larger than 1 would be an invalid int input
            if(Math.abs(result) > Integer.MAX_VALUE / 10) return 0;
            
            //leftshift output
            result = result * 10 + x%10; 
            //rightshift input
            x /= 10;
        }
        
        return result;
    }
}
