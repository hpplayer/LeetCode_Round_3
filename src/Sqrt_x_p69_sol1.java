/*
69. Sqrt(x)

Implement int sqrt(int x).

Compute and return the square root of x.
*/

/**
 * Binary search solution
 * 
 * We use binary search instead of naive search to get close to the number that gives n * n <= x
 * If there is no number give n * n = x, then we will pick the largest number that gives n * n < x, which will be pointed by right pointer in the end
 * 
 * Time & space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 9:26:46 PM
 */

public class Sqrt_x_p69_sol1 {
    public int mySqrt(int x) {
        //binary search problem, we will search in range from 1 to x and find the one that gives i * i = x
        //if we can't find x, then we need to return the closet number i that gives i * i < x
        
        //boundary check
        if(x == 0) return 0;
        
        int left = 1, right = x;
        
        //check each num in the range
        while(left <= right){
            int mid = left + (right - left)/2;
            
            //to avoid overflow, we use x/mid instead of mid * mid to get solution
            if(mid == x/mid){
                return mid;
            }else if(mid < x/mid){
                //too small
                left = mid + 1;
            }else{
                //too large
                right = mid - 1;
            }
        }
        //no exact match, return the largest num that gives num * num < x, which is pointed by end now
        return right;
    }
}
