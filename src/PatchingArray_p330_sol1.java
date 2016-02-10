import java.util.Arrays;

/*
330. Patching Array

Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:
nums = [1, 3], n = 6
Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
Return 2.
The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
Return 0.
*/

/**
 * Greedy Solution
 * 
 * We scan the input array and use a temp variable to record currSum we can reach so far (exclusive).
 * To cover currSum, we need current val in array <= currSum.
 * So if this is the case, then we can cover all sums until currSum + nums[index] (exclusive)
 * If this is not the case, in other word, current val in array > currSum, then we need a patch to cover currSum.
 * To maximize the new cover range, we will pick currSum as the patch val. Then we compare new currSum with current val in array to see if we can
 * cover new currSum with current val in array
 * 
 * In worst case, we need scan the input array once, which is O(len(nums)), and we may still need logN patches,
 * so the total time complexity is O(len(nums) +LogN)
 * 
 * @author hpPlayer
 * @date Feb 10, 2016 3:41:57 PM
 */
public class PatchingArray_p330_sol1 {
	public static void main(String[] args){
		int[] nums = {1, 5, 10};
		
		System.out.println(minPatches(nums, 20));
	}
	
    public static int minPatches(int[] nums, int n) {
        //greedy solution
        //scan the input array and check if we have covered all numbers from 1 to current sum
        
        //use long type to prevent overflow
        long currSum = 1;
        //index is pointer in array, result is num of patches we need
        int index = 0, result = 0;
        
        while(currSum <= n){
            if(index < nums.length && nums[index] <= currSum){
                //if current num in array <= currSum, then we can cover currSum and all sums until currSum + nums[index]
                currSum += nums[index++];
            }else{
                //if current num in array > currSum, then we need a patch to cover currSum. To maximze the new range, we will pick currSum
                //as new patch
                currSum *= 2;
                result++;
            }
        }
        
        
        return result;
    }
}
