/*
152. Maximum Product Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

/**
 * DP solution
 * 
 * Advanced version of Maximum_Subarray_p53_sol1. The basic idea is same, but now we need to keep both the min and max local value. 
 * Since we allow negative input, the max result may comes from a min value < 0 * a negative as well.
 * Therefore we need keep two values, and for each cell, we can either add it into subarray to get a larger/smaller product, or use itself as a new start
 * to get a larger/smaller product
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 10:18:40 AM
 */
public class Maximum_Product_Subarray_p152_sol1 {
    public int maxProduct(int[] nums) {
        //DP solution. advanced version of Maximum_Subarray_p53_sol1. Since maximum product can be produced by two negative number,
        //we have to record the min number as well in addition to max number
        
        int min = 1,  max = 1;
        int result = Integer.MIN_VALUE;
        
        for(int num : nums){
            //since min/max value will be updated and affect the update of the other value max/min, we need to backup their old value 
            int mn = min, mx = max;
            
            //get the max value so far, we may use curr cell as a new start, or we multiple curr value to previous min/max product to get a larger value
            max = Math.max(mx * num, Math.max(mn * num, num));
           //get the min value so far, we may use curr cell as a new start, or we multiple curr value to previous min/max product to get a smaller value           
            min = Math.min(mx * num, Math.min(mn * num, num));
            
            //for result, we only want get the max value
            result = Math.max(result, max);
        }
        
        return result;
    }
}
