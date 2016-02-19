import java.util.*;
/*
259. 3Sum Smaller

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
*
*/

/**
 * Three-pointer solution
 * 
 * We fix one pointer and use two remaining pointers to search the sequence that meets the requirement
 * We can firstly sort the array, so we are able to move the pointer smartly 
 * If we found current sum < target, then it actually means we found a bunch of sequences that < target. Why? Because if we move 3rd pointer
 * leftward until reach the second pointer, then all sequences in this range will all have sum < target.
 * However if found current sum > target, then we have to move 3rd pointer leftward to continue search for the sequence that has sum < target
 * 
 * Time complexity: O(n^2), As first pointer will move n times, each time, we will scan the subarray which has length O(n) at most 
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 17, 2016 5:14:47 PM
 */
public class _3Sum_Smaller_p259_sol1 {
    public int threeSumSmaller(int[] nums, int target) {
        //three pointer solution. We firstly sort the array, so later we could move the pointer smartly
        //First pointer helps us get the head of sequence, so we can enumerate all sequence without duplicate
        //Second pointer helps us control the beginning of rest sequence, if sum < target, then we can move this pointer to get larger sum
        //Third pointer helps us control the ending of rest sequence, if sum > target, then we can move this pointer to get smaller sum
        
        //we can sort the array. Since the problem does not require us to return indexes, we can just sort the array then return the count
        Arrays.sort(nums);
        
        int result = 0;
        
        //we need at least have to cells after index i
        for(int i = 0; i + 2 < nums.length; i++){
            int start = i + 1, end = nums.length - 1;
            
            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if(sum < target){
                    //current sum < target, if we move end pointer leftward until reach start + 1, then all those sequences should 
                    //have sum < target
                    result += end - start;
                    //move start pointer to look for new sequences
                    start ++;
                }else{
                    //too large, or = target
                    //we can move end pointer leftward to get smaller sum
                    end --;
                }
            }
        }
        
        return result;
    }
}
