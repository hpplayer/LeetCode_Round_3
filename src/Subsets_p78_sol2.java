import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Iterative solution
 * 
 * Each iteration, we will add a new num to all existing lists. Then we copy those new lists to result list
 * 
 * Time complexity: O(n*2^n) where n is outer loop, 2^n is the max size of result list from the inner loop
 * Space complexity: 2^n
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 12:46:38 PM
 */
public class Subsets_p78_sol2 {
    public List<List<Integer>> subsets(int[] nums) {
        //iterative solution, similar to backtracking solution but now we will add new num to all existing lists
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Arrays.sort(nums);
        
        result.add(new ArrayList<Integer>());
        
        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            for(int j = 0; j < size; j++){
                List<Integer> temp = result.get(j);
                //create a new list including temp and new num
                temp.add(nums[i]);
                result.add(new ArrayList<Integer>(temp));
                //remove last new num and prepare for adding a new num
                temp.remove(temp.size()-1);
            }
        }
        
        return result;
    }
}
