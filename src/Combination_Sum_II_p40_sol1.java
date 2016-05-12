import java.util.*;
/*
40. Combination Sum II

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where
the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

/**
 * DFS/backtracking solution
 * 
 * Like Combination_Sum_I_p39_sol1, we still use backtracking approach to enumerate all combinations.
 * But this time we will skip duplicates and not use same cell more than once
 * 
 * Time complexity: recursion, exponential, masters theorem
 * Space complexity: same as above
 * 
 * Sol2 is the iterative version of Backtracking/DFS
 * @author hpPlayer
 * @date May 11, 2016 10:38:24 PM
 */
public class Combination_Sum_II_p40_sol1 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //backtracking/DFS solution. In each DFS, we will start after last index and skip duplicate cells
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        DFS(result, new ArrayList<Integer>(), candidates, 0, target);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> list, int[] nums, int index, int sum){
        if(sum == 0){
            //found a valid combination
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        //we use early-break in loop, so all combination should be valid candidate for target i.e. sum < target
        for(int i = index; i < nums.length; i++){
            sum -= nums[i];
            //early break here to stop DFS if adding val will not help us approach target
            if(sum < 0) return;
            list.add(nums[i]);
            DFS(result, list, nums, i + 1, sum);
            sum += nums[i];
            list.remove(list.size() -1);
            //skip all duplicates so we won't have duplicate combinations in result
            while(i + 1 < nums.length && nums[i+1] == nums[i]) i++;
        }
    }
}
