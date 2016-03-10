import java.util.*;

/**
 * Iterative backtracking
 * 
 * We firstly sort the array to put duplicates together. To avoid creating duplicate permutations, we will only add duplicate after the index of 
 * its last appearance. This is similar to recursive backtracking, where we will avoid add duplicate in same recursion. The other part is similar to 
 * Permutations_I_p46_sol2, we need to add each num to all legal indexes of all prev lists
 * 
 * Time complexity: O(N^3)
 * Space complexity: O(N^2)
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 1:05:46 PM
 */
public class Permutations_II_p47_sol2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        //iterative version of backtracking. We still sort the nums first to put duplicates together
        //In this solution, to avoid creating duplicate permutations, we will only add new duplicates after the last occur of this duplicate.
        //It is similar to recursive backtracking, where we only allow to add duplicates after last duplicate
        
    	Arrays.sort(nums);
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        result.offerLast(new ArrayList<Integer>());
        
        
        for(int i = 0; i < nums.length; i++){
            int size = result.size();
            //add curr num into all prev lists
            for(int j = 0; j < size; j++){
                List<Integer> list = result.pollFirst();
                //If curr num is duplicate we will add curr num into each possible index after last occur of this num in each list
                //if curr num is non-duplicate, then we will add curr num into all possible indexes in each list
                int start = 0;
                if(i > 0 && nums[i] == nums[i-1]){
                    //Notice: we will only add num after index of its last occurrence, so we add 1 to start to achieve that
                    //if we did not do that, then it will replace old duplicate in last occurrence, and cause the old duplicate rightshift!!!!!!!!!!!!
                    start = getIndex(list, nums[i]) + 1;
                }
                
                //we will add num into each possible index in curr list
                for(; start <= list.size(); start++){
                    List<Integer> copy = new ArrayList<Integer>(list);
                    copy.add(start, nums[i]);
                    result.add(copy);
                }
            }
        }
        
        return result;
    }
    
    public int getIndex(List<Integer> list, int num){
        //get the last occur of num
        int i = list.size() - 1;
        for(; i >= 0; i--){
            if(list.get(i) == num) break;
        }
        
        return i;
    }
}
