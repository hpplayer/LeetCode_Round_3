/*
238. Product of Array Except Self

Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
*/	
		
/**
 * Left scan and right scan solution
 * 
 * To avoid the use of extra space, we will make use of result[] to firstly store the product from left side
 * Then we use an extra counter to record and update product from right side
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 2, 2016 4:25:44 PM
 */
public class Product_of_Array_Except_Self_p238_sol1 {
    public int[] productExceptSelf(int[] nums) {
        //left scan and right scan solution.

        int len = nums.length;
        
        int[] result = new int[len];
        
        //we firstly make use of result array to store product from left side
        //we are told nums.length will > 1, so we are guaranteed to have output array with 2 cells
        result[0] = 1;
        
        for(int i = 1; i < len; i++){
            //result[i-1] contains product from index 0 to i - 2, so to include i - 1 into product, we will multiple nums[i - 1] 
            result[i] = result[i-1] * nums[i-1];
        }
        
        //Then we will update result[] with product from right side. Since we have used result[], we need use an extra variable to keep
        //accumulated product
        
        int temp = 1;
        
        for(int i = len - 2; i >= 0; i--){
            //temp contains the product exclude i + 1, in index i, we need include nums[i+1]
            temp *= nums[i+1];
            result[i] *= temp;
        }
        
        return result;
    }
}
