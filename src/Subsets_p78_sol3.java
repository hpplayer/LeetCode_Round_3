import java.util.*;
/**
 * We use bit mask to decide if we need to insert current num into list
 * That's because we totally have 2^n possibilities while our mask also have 2^n variants.
 * So we can use bit mask to help us generate results
 * 
 * Time complexity: O(n * 2^n) where O(n) is len(num) from outer loop and O(2^n) is the size of result from inner loop
 * Space complexity: O(2^n)
 * 
 * @author hpPlayer
 * @date Feb 23, 2016 12:52:56 PM
 */
public class Subsets_p78_sol3 {
    public List<List<Integer>> subsets(int[] nums) {
        //bit manipulation solution. Based on bit mask, we can decide whether to insert num 
        //get 2^len value
        int size = 1<<nums.length;
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //Initialization
        for(int i = 0; i < size; i++) result.add(new ArrayList<Integer>());
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length; i++){
            //we can skip j = 0, since it is an empty list which should always empty
            for(int j = 1; j < size; j++){
                //use j>>i to get make the ith num the last, so we can use &1 to get its value
                if( (j>>i&1) == 1 ){
                    //if it is 1, then we need to insert it into the list
                    result.get(j).add(nums[i]);
                }
            }
        }
        
        return result;
    }
}
