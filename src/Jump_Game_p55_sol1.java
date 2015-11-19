/*
Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

/**
 * Greedy solution
 * 
 * We just update and check the max point we can reach from the input array
 * 
 * We use a pointer to scan each cell in the input array and help update max point we can reach
 * If we found max reach point < index of this pointer, then we return false directly.
 * 
 * finally, if we can successfully scan the array, then it indicates at least we can reach len -1 cell in the array, so just return true
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 19, 2015 12:58:46 AM
 */
		

public class Jump_Game_p55_sol1 {
    public boolean canJump(int[] nums) {
        //Greedy solution, we just check the furthest point we can reach from the input array
        
        //boundary check
        if(nums == null || nums.length == 0) return false;
        
        int maxReach = 0;
        
        for(int i = 0; i < nums.length; i++){
            //check each cell in the input and update maxReach
            
            //if we can't reach current cell then just return false directly
            if(i > maxReach) return false;
            
            //update maxReach point based on i + nums[i]
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        
        //because we check i > maxReach in the loop, our maxReach should >= nums.length - 1
        //meet requirement, just return true
        return true;
    }
}
