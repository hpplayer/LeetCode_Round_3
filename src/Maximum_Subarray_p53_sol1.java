/*
53. Maximum Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

/**
 * DP solution
 * 
 * Its advanced version is Maximum_Product_Subarray_p152_sol1
 * We keep the local_max value at index i - 1, when we check value in index i, we can either add it to the existing subarray or use it to start a new 
 * subarray. We will pick the way that gives larger sum
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol2 is a divide-and-conquer solution that using the binary-search style approach
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 10:24:56 AM
 */
public class Maximum_Subarray_p53_sol1 {
    public int maxSubArray(int[] nums) {
        //DP solution, we keep the localMax from previous subarray. For current cell, we can either add it into previous subarray
        //to get a larger array, or we can use it as new start to begin a new subarray with larger array
        int local_max = 0;
        int result = Integer.MIN_VALUE;
        
        for(int num : nums){
            local_max = Math.max(local_max + num, num);
            result = Math.max(local_max, result);
        }
        
        return result;
    }
}
