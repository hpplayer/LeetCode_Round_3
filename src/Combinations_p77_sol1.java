import java.util.*;

/*
77. Combinations 

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

/**
 * DFS/backtracking solution
 * 
 * Just a general DFS/backtracking solution. But here we will have a early stop if we found we don't have enough
 * elements left in the n
 * 
 * Time complexity: recursion, masters theorem, exponential
 * Space complexity:
 *  n     		k	
 *(   ), i.e. C 
 *  k			n	
 * @author hpPlayer
 * @date May 16, 2016 12:24:41 AM
 */

public class Combinations_p77_sol1 {
    public List<List<Integer>> combine(int n, int k) {
        //DFS/backtracking solution
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(result, new ArrayList<Integer>(), 1, n, k);
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> list, int start, int n, int k){
        if(k == 0){
            //we have included k elements into combination
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        //We will continue the loop if we still have enough elements left after using curr element
        //this early stop can help us to avoid unnecessary DFSs
        for(int i = start; i + k -1<= n; i++){
            list.add(i);
            DFS(result, list, i + 1, n, k - 1);
            list.remove(list.size()-1);
        }
    }
}
