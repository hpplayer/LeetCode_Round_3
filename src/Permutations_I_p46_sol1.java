import java.util.*;

/*
46. Permutations

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/

/**
 * Backtracking solution
 * 
 * We will build result list index by index, for each index, we will add all non-added num to it, then go to next DFS
 * 
 * Time complexity: backtracking, using masters theorem, hard to decide but must be exponential
 * Space complexity: same as above
 * 
 * Remark:
 * We can also use a HashMap to check if curr num has been inserted into temp list, but based on the OJ result, the extra operation on HashSet is more
 * time-consuming than here where we just use the naive contains() on list
 * 
 * Sol2 is the iterative version of backtracking
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 11:24:18 AM
 */
public class Permutations_I_p46_sol1 {
    public List<List<Integer>> permute(int[] nums) {
        //backtracking solution, we use list.contains() to avoid insert same cell twice    
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(result, new ArrayList<Integer>(), nums);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> temp, int[] nums){
        if(temp.size() == nums.length){
            //reach boundary
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            //in current position in temp list, we check all non-added integer and add them to temp list, then go to next DFS
            //In OJ, using contains() is faster than using a hashSet
            if(!temp.contains(nums[i])){
                temp.add(nums[i]);
                DFS(result, temp, nums);
                temp.remove(temp.size()-1);
            }
        }
    }
}
