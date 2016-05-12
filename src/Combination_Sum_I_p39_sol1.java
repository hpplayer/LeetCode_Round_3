import java.util.*;

/*
39. Combination Sum

Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the
candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/

/**
 * DFS or backtracking solution
 * 
 * This problem asks us to enumerate all possible combinations that give target sum. It is easy to come up
 * with DFS solution
 * 
 * time complexity: recursive, master-theorem, exponential
 * space complexity: recursive, master-theorem, exponential
 * 
 * @author hpPlayer
 * @date May 11, 2016 9:51:23 PM
 */
public class Combination_Sum_I_p39_sol1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //backtracking solution. Each DFS will add a new val to curr list
        
        //sort the input first, so elements in result combination will be non-descending
        Arrays.sort(candidates);
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(result, new ArrayList<Integer>(), candidates, 0, target);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> list, int[] nums, int index, int target){
        if(target == 0){
            //found a valid combination
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        if(target < 0){
            //we add too many cells, since all numbers will be positive, we don't need to go further
            return;
        }
        
        for(int i = index; i < nums.length; i++){
            //try curr cell
            target -= nums[i];
            list.add(nums[i]);
            DFS(result, list, nums, i, target);
            //remove curr cell and prepare to add next cell
            target += nums[i];
            list.remove(list.size()-1);
        }
    }
}
