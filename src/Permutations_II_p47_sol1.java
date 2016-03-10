import java.util.*;

/*
47. Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

/**
 * Backtracking solution
 * 
 * We firstly sort the array so the duplicates will be put together, then we use a boolean[] array to skip visit same index more than once.
 * Each recursion will add a num in curr index. Each time we finish DFS on current index, we will skip the rest cells that have same value with it,
 * so in same index, we wouldn't add duplicates. The rest is similar to Permutations_I_p46_sol1. 
 * 
 * Time complexity: backtracking, need masters theorem, hard to decide but must be exponential
 * Space complexity: same as above
 * 
 * Sol2 is the iterative version
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 12:29:17 PM
 */
public class Permutations_II_p47_sol1 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        //backtracking solution, to handle duplicates, we will sort the array firstly
        //Each recursion we will add a number to current index. So to avoid insert duplicates in same index, we will skip duplicates in same recursion
    	
    	Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(result, new ArrayList<Integer>(), new boolean[nums.length], nums);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> temp, boolean[] used, int[] nums){
        if(temp.size() == nums.length){
            //reach boundary
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            //skip used num
            if(used[i]) continue;
            
            //add curr num into result
            temp.add(nums[i]);
            used[i] = true;
            DFS(result, temp, used, nums);
            
            //reset so it wouldn't affect inserting other num into curr index
            temp.remove(temp.size()-1);
            used[i] = false;
            
            //we will skip duplicates in curr index
            while(i + 1 < nums.length && nums[i] == nums[i+1]) i++;
        }
        
    }
}
