import java.util.*;
/*
Two Sum

Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

/**
 * HashTable problem
 * 
 * Just use a hashTable to store the value of previous ary, the value would be the index of this value
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 12:34:28 AM
 */
public class Two_Sum_I_p1_sol1 {
    public int[] twoSum(int[] nums, int target) {
        //key is value, val is index of that val
        Map<Integer, Integer> hs = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++){
            if(hs.containsKey(target - nums[i])){
            	//found pair
                return new int[]{hs.get(target-nums[i]) + 1, i + 1};
            }else{
            	//no pair, just add a new Entry to hs
                hs.put(nums[i], i);
            }
        }
        
        return new int[]{};
    }
}
