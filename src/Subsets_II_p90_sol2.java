import java.util.*;
/**
 * Iterative solution
 * 
 * it is similar to Subsets_p78_sol2, but now we need to deal with duplicates
 * In this solution we use an extra variable prev to record the size of list that we previous insertion affected. 
 * If we found curr num is same with prev num, then we will skip those affected lists and only add new num in lists after them
 * 
 * Time complexity: O(N * 2^n), where N is nums.length for outer loop, 2^n is result size for inner loop
 * Space complexity: O(2^n)
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 8:35:30 PM
 */
public class Subsets_II_p90_sol2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //iterative solution. To avoid duplicate, we need skip add duplicate num to previous lists that we already insert the first duplicate
        //so we need an extra variable records the size of lists that we have inserted first duplicate
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        Arrays.sort(nums);
        
        //prev variable help us record the size of lists that previous insertion affected
        int prev = 0;
        
        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            //if we get duplicate, then inner loop will start after prev
            int start = (i - 1 >= 0 && nums[i-1] == nums[i])? prev : 0;
            
            for(; start < size; start++){
                List<Integer> temp = result.get(start);
                temp.add(nums[i]);
                result.add(new ArrayList<Integer>(temp));
                temp.remove(temp.size()-1);
            }
            
            prev = size;
        }
        
        
        return result;
    }
}
