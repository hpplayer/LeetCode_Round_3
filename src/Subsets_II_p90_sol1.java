import java.util.*;

/*
90. Subsets II

Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

/**
 * Recursive backtracking solution
 * 
 * Similar to Subsets_p78_sol1, but in same level, we need skip duplicate nums
 * 
 * Time complexity: master theory
 * Space complexity: same as above
 * 
 * Sol2 is the itertaive solution
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 8:27:55 PM
 */
public class Subsets_II_p90_sol1 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //recursive backtracking solution, the main part is similar to Subsets_p78_sol1, but now we need to skip same num in same level
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        result.add(new ArrayList<Integer>());
        Arrays.sort(nums);
        
        DFS(result, new ArrayList<Integer>(), 0, nums);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> list, int index, int[] nums){
        for(int i = index; i < nums.length; i++){
            list.add(nums[i]);
            result.add(new ArrayList<Integer>(list));
            DFS(result, list, i + 1, nums);
            list.remove(list.size()-1);
            //to avoid duplicates, we need to skip same num in same level
            while(i + 1 < nums.length && nums[i+1] == nums[i]) i++;
        }
    }
}
