import java.util.*;

/**
 * Iterative BFS solution
 * 
 * We will build the combination in a BFS way, i.e. adding one more element each loop
 * To make each produced combination unique and valid, we will have a start value that guides us where to
 * start add next num in the combination. 
 * 
 * To speed up the program, we will check if the start value is too big that we don't have enough elements left
 * The inner loop on start variable will guarantee that newly generated list must be valid with ideal nums of 
 * elements
 * 
 * Time complexity: O(k*n*n*n), we have three nested loops, also in the inner loop, we need to copy each of prev
 * valid list, which may be O(n) at worst
 * Space complexity: 
 *  n     		k	
 *(   ), i.e. C 
 *  k			n	
 * 
 * @author hpPlayer
 * @date May 16, 2016 12:02:42 AM
 */
public class Combinations_p77_sol2 {
    public List<List<Integer>> combine(int n, int k) {
        //Iterative BFS solution. We will add one more number into combinations each loop
        
        //use result so we can poll from front and push to the back
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        
        //start with an empty combination
        result.offerLast(new ArrayList<Integer>());
        
        //we need k loops to add k elements into list
        for(int i = 1; i <= k; i++){
            //record the size so we can expand the combination properly
            int size = result.size();
            for(int j = 0; j < size; j++){
                List<Integer> list = result.pollFirst();
                //decide where to start
                int start = list.isEmpty()? 1 : list.get(list.size() - 1) + 1;
                //we add one more element into the combination.
                //Each elements after start number will be added to curr list and get a new list
                //Notice: since our loop will stop once curr start number is too large to have enough elements left, so it means we are guaranteed next round
                //will only get valid lists with ideal num of elements from curr round
                for(; start + (k - i) <= n; start++){
                    List<Integer> temp = new ArrayList<Integer>(list);
                    temp.add(start);
                    result.offerLast(temp);
                }
            }
        }
        
        return result;
    }
}
