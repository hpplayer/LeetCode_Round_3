/*
303. Range Sum Query - Immutable

Given an integer array nums, find the sum of the elements between indices i and j (i ¡Ü j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
*/

/**
 * DP solution
 * 
 * We preprocess the inputs and calculate all sums from index 0 to each index. 
 * For convenience, we create a sums[] with len + 1 length, and sums[i] means the sum of subarray from index 0 to index i - 1
 * When we need to compute the sum of subarray from index i to index j, we just need to do sums[j+1] - sums[i] to get the sum
 * 
 * Time complexity: preprocess O(N), querySum O(1)
 * Space complexity: O(N)
 * 
 * Remark:
 * Of course we can use segment tree to solve this problem, but it is an overkill as the input array is immutable 
 * 
 * @author hpPlayer
 * @date Apr 17, 2016 11:32:34 PM
 */
public class Range_Sum_Query_Immutable_p303_sol1 {
    //DP solution. Preprocess the input and store all subarray sums in an extra array
    
    int[] sums;
    
    public Range_Sum_Query_Immutable_p303_sol1(int[] nums) {
        //sums[i] means the sum from index 0 to i - 1, therefore to include all sums, we need a sum[] with len + 1 length
        sums = new int[nums.length + 1];
        
        for(int i = 1; i < sums.length; i++){
            sums[i] = nums[i-1] + sums[i-1];
        }
        
    }

    public int sumRange(int i, int j) {
        //to get range between i and j, we just need to subtract sums[i] from sums[j+1]
        return sums[j+1] - sums[i];
    }
}

//Your NumArray object will be instantiated and called as such:
//Range_Sum_Query_Immutable_p303_sol1 numArray = new Range_Sum_Query_Immutable_p303_sol1(nums);
//numArray.sumRange(0, 1);
//numArray.sumRange(1, 2);