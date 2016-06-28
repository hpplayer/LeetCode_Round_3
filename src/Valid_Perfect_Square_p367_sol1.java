/*
367. Valid Perfect Square

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
*/

/**
 * Binary Search solution
 * 
 * Just use a binary search to find the target candidate. Since mid * mid may be get overflow, we convert it
 * to long type to prevent it.
 * 
 * Time complexity: O(logN)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 27, 2016 9:01:19 PM
 */
public class Valid_Perfect_Square_p367_sol1 {
    public boolean isPerfectSquare(int num) {
        long left = 1, right = num;
        
        while(left <= right){
            long mid = left + (right - left)/2;
            
            if( mid * mid == (long) num  ){
                return true;
            }else if( mid * mid < (long) num  ){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
            
        }
        
        return false;
    }
}
