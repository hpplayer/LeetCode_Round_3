/*
172. Factorial Trailing Zeroes

Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

/**
 * Math solution
 * 
 * Only nums that divided by 5 can generate trailingZeroes. We have a lot more 2s than 5s, so we only consider 5s here
 * (2s can be from any even n) 
 * For input n, we can use n/5 to get how many nums < n contain 5 in current n. When we say nums, we mean nums from n, n-1,
 * n-2 to 1
 * However some nums contain an extra 5, like 25, 125, ex. So we firstly update n to n/5, then use n/5 again to get 
 * the count of nums that contains 5. Since the property of divided by 5 will be preserved, we can use similar way
 * to count 5s in next round even though we have shrink all nums by a 5
 * We will repeatedly do this until our remaining n < 5
 * 
 * Time complexity: log5(n), since each time we will shrink n by 5 times, our time complexity will be logarithmic 
 * Space complexity: O(1)
 * 
 * Remark:
 * Sol1 is iterative version
 * Sol2 is recursive version
 * Since code are simple, I put them together
 * @author hpPlayer
 * @date May 31, 2016 9:15:34 PM
 */
public class Factorial_Trailing_Zeroes_p172_sol1 {
    public int trailingZeroes(int n) {
        //math problem. Each time we shrink n by 5, and use n/5 to get the count
        
        int result = 0;
        
        while(n >= 5){
            //count how many nums are smaller than n and contain 5s
            result += n/5;
            
            //we remove 5 from each of those nums, and check those nums that contain extra 5
            //Though all nums are shrinked, the property of divied by 5 is not changed, so we can still use
            //n/5 to count those nums in next loop
            n /= 5;
        }
        
        return result;
    }
    
    public int trailingZeroes2(int n) {
        //Math solution, recursive version
        
        //input < 5 cannot have trailing zeros
        if(n < 5) return 0;
        
        //accumulate count in each recursion
        return n/5 + trailingZeroes2(n/5);
    }
}
