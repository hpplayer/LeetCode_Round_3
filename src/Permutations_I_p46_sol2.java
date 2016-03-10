import java.util.*;

/**
 * Iterative backtracking solution
 * 
 * For each input num, we try to add it to all possible indexes in prev lists 
 * 
 * Time complexity: O(N^3)
 * Space complexity: O(N^2)
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 12:10:25 PM
 */
public class Permutations_I_p46_sol2 {
    public List<List<Integer>> permute(int[] nums) {
        //iterative backtracking solution, we will add each num into all possible indexes of previous list
        
        //use linkedList to make building new list more easier 
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        result.offerLast(new ArrayList<Integer>());
        
        for(int num : nums){
            int size = result.size();
            //we will add curr num into each prev lists in result list
            for(int i = 0; i < size; i++){
                List<Integer> list = result.pollFirst();
                //for each prev list, we will add curr num into each possible index
                //Notice: including index = list.size(), which means we append num to the tail!!!!!!!!!
                for(int j = 0; j <= list.size(); j++){
                    List<Integer> copy = new ArrayList<Integer>(list);
                    copy.add(j, num);
                    result.add(copy);
                }
            }
        }
        
        return result;
    }
}
