import java.util.*;
/*
18. 4Sum
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ¡Ü b ¡Ü c ¡Ü d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/

/**
 * Four-pointer solution
 * 
 * The basic idea is similar to _3Sum_p15, but now we use 4 pointers to find the result combination
 * We will move 3rd and 4th pointers smartly to reduce 4 loops to 3 loops. 
 * To avoid duplicates, we will let each pointer jump over following cells that have same value
 * 
 * Time complexity: O(n^3), as we have 3 loops
 * Space complexity: O(n^3), as we may found a pair for each loop and there are n^3 loops
 * 
 * Sol1 provides a 4-pointer approach
 * Sol2 provides a 2-sum like approach
 * 
 * @author hpPlayer
 * @date May 4, 2016 10:28:47 PM
 */
public class _4Sum_p18_sol1 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //four pointers approach, we will use 4 pointers to find the target combination
        //For the last two pointers, we will move them smartly so we can use 3 loops to find result list
        //To avoid duplicates, we will sort the input array first then let each pointer skip duplicate values
        //This works since nested inside loop guarantee all combinations with curr pointer should be found
        //so next duplicate value will give duplicate combination
        
        //sort the input first
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        
        //each loop will stop when we don't have enough elements left
        for(int i = 0; i < nums.length - 3; i++){
            for(int j = i+1; j < nums.length - 2; j++){
                //we let two-pointer approach to move 3rd and 4th pointer
                int left = j + 1, right = nums.length - 1;
                while(left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target){
                        result.add( Arrays.asList(nums[i], nums[j], nums[left], nums[right]) );
                        //if sum == target, then we need move left and right pointer to search for next valid pair
                        left++;
                        right--;
                        //We also need to move left and right pointers if we found duplicate value
                        while(left < right && nums[left] == nums[left-1])left++;
                        while(left < right && nums[right] == nums[right + 1])right--;
                    }else if(sum < target){
                        //too small, move left pointer
                        left++;
                    }else{
                        //too large, move right pointer
                        right--;
                    }
                }
                //We will skip all following cells that have same value with j, this is because left&right loop
                //has found all combinations for j. Next same value of j will give duplicate combinations
                while(j + 1< nums.length - 2 && nums[j] == nums[j+1]) j++;
            }
            //We will skip all following cells that have same value with i, this is because inner loops
            //has found all combinations for i. Next same value of i will give duplicate combinations
            while(i + 1< nums.length - 3 && nums[i] == nums[i+1]) i++;                            
        }
        return result;
    }
}
