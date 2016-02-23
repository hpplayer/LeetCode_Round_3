import java.util.*;
/*
78. Subsets

Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

/**
 * Backtracking solution
 * 
 * Each backtracking layer will add a new num to current list, then we pass current list to next layer
 * After backtracking is finished for current new num, we will remove it from current list and add next new num
 * 
 * Time complexity: Masters theory
 * Space complexity: same as above
 * 
 * Sol1 uses recursive backtracking solution
 * Sol2 uses iterative enumeration solution
 * Sol3 uses bit mask solution and may not work for input array which has len > 32
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 11:47:37 AM
 */
public class Subsets_p78_sol1 {
    public List<List<Integer>> subsets(int[] nums) {
        //backtracking solution. Each layer we will add next num into current list. After addition we will insert the curr list into result
        
        //firstly sort the array, so result list will be in non-descending order. 
        Arrays.sort(nums);
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //we have to manually add empty list
        result.add(new ArrayList<Integer>());
        DFS(result, new ArrayList<Integer>(), 0, nums);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> list, int index, int[] nums){
        //we only scan nums after index, so no duplicate subset will be added
        for(int i = index; i < nums.length; i++){
            //firstly add next num into curr list
            list.add(nums[i]);
            //then add new subset into the result
            result.add(new ArrayList<Integer>(list));
            //then go to next layer with new curr list
            DFS(result, list, i + 1, nums);
            //we remove newly added element and ready to add next num
            list.remove(list.size()-1);
        }
    }
}
