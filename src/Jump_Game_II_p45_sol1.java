/*
Jump Game II

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/


/**
 * Two pointer problem
 * 
 * We use two pointers to locate the search range. Each loop, we will check all values in current range and determine next search range.
 * next search must start from right + 1 so two search ranges will not have overlap. but the right boundary of next search range could be
 * same with its left boundary (single cell) or could be much behind left boundary (multiple cells) 
 * 
 * Time complexity: O(n) as we will look at each cell once
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 19, 2015 1:21:13 AM
 */
public class Jump_Game_II_p45_sol1 {
    public int jump(int[] nums) {
        //boundary check
        if(nums == null || nums.length == 0) return 0;
        
        int left = 0;
        int right = 0;
        
        int step = 0;
        
        //if search range is valid, we will search inside 
        //left == right means we only have one cell in search range
        //right > left means we have multiple cells in search range
        while(left <= right){
            //if right boundary of current search >= nums.length - 1, then return step
            if(right >= nums.length - 1) return step;
            //temp variable is to hold the max right boundary of next search range 
            int temp = 0;
            //search max right boundary of next search range from current range and update temp
            for(int i = left; i <= right; i++){
                //i + nums[i] get the max reach point
                temp = Math.max(temp, i + nums[i]);
            }
            
            //next search range must starts from right + 1 so we won't have duplicate between two ranges
            left = right + 1;
            //update right
            right = temp;
            step++;
        }    
        
        //couldn't reach end
        return -1;
    }
}
