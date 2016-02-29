/*
198. House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically
 contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight
 without alerting the police.
*/

/**
 * DP solution
 * 
 * The max money we can get at index i is based on index i - 1
 * If we Rob house at index i, then we need not rob value at i - 1
 * If we not rob house at index i, then we can either rob house at i - 1 or not, and we will choose the one that gives larger value
 * So This is how the DP function is defined
 * 
 * Time complexity: O(N)
 * Space complexity:O(1)
 * 
 * @author hpPlayer
 * @date Feb 28, 2016 4:22:41 PM
 */

public class House_Robber_I_p198_sol1 {
    public int rob(int[] nums) {
        //DP solution, the max money can be gained by considering rob and notRob two cases
        
        
        int RobNow = 0;
        int NotRob = 0;
        
        for(int i = 0;i < nums.length; i++){
            //firstly backup old RobNow
            int temp = RobNow;
            //rob current house must based on the value that we not rob prev house
            RobNow = NotRob + nums[i];
            //not rob current house may either come from rob prev house or not rob prev house
            //we will pick the one that gives larger value
            NotRob = Math.max(temp, NotRob);
        }
        
        //we return the value that we rob last house or not
        return Math.max(RobNow, NotRob);
    }
}
