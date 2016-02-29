/*
213. House Robber II

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not
get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the
neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous
street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
money you can rob tonight without alerting the police.
*/

/**
 * DP solution
 * 
 * Advanced version of House_Robber_p198_sol1
 * We just need to split the circular array to linear array by looking at index 0
 * in this problem index 1 and index len - 1 are neighbors of index0, therefore there will be two cases:
 * We are either allowed to rob house at index0, which let us rob house from 0 to len - 2 (not allowed to rob len - 1)
 * Or we are not allowed to rob house at index0, which let us rob house from 1 to len - 1 (not allowed to rob 0)
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Feb 28, 2016 5:01:56 PM
 */
public class House_Robber_II_p213_sol1 {
    public int rob(int[] nums) {
        //DP solution, advanced version of House_Robber_p198_sol1
        //now we can think the array as a cycle. We will split the cycle to get linear array
        //We choose the index 0 as split index. If we are allowed to rob index0, then we can rob from 0 to len - 2
        //If we are not allowed to rob index0, then we can rob from 1 to len - 1
        
        //boundary check 
        if(nums.length == 0) return 0;
        //Our program will not work on single input array
        if(nums.length == 1) return nums[0];
        
        //rob index0, we can rob from 0 to len - 2
        //notRob index0, we can rob from 1 to len - 1
        return Math.max( robHelper(0, nums.length - 2, nums), robHelper(1, nums.length - 1, nums) );
    }
    
    public int robHelper(int left, int right, int[] nums){
        int RobNow = 0;
        int NotRob = 0;
        
        for(int i = left; i <= right; i++){
            int temp = RobNow;
            //rob i can only from not rob i - 1
            RobNow = NotRob + nums[i];
            //Not rob i can choose the max one from rob/notRob i - 1
            NotRob = Math.max(NotRob, temp);
        }
        
        return Math.max(NotRob, RobNow);
    }
}
