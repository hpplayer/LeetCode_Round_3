/*
326. Power of Three

Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
*/

/**
 * Intuitive recursive/iterative solution
 * 
 * Divide 3 as much as possible and check if result is 1. If yes, return true, otherwise return false
 * 
 * Time and Space complexity: O(1)
 * 
 * Sol2 provides two math solutions
 * @author hpPlayer
 * @date Feb 25, 2016 4:35:28 PM
 */
		
public class Power_of_Three_p326_sol1 {
    public boolean isPowerOfThree(int n) {
        //intuitive recursive solution, if possible recursively divide n, until we reach 1
        
        if(n == 1) return true;
        if(n <= 0) return false;
        if(n%3 == 0) return isPowerOfThree(n/3);
        
        return false;
    }
    
    public boolean isPowerOfThree2(int n) {
        //intuitive iterative solution, we divide 3 as much as possible and check if remaining num is 1
        
        if(n > 1){
            while(n%3 == 0){
                n /= 3;
            }
        }
        
        return n == 1;
    }
    
}
