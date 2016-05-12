import java.util.*;
/*
216. Combination Sum III

Find all possible combinations of k numbers that add up to a number n,
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/

/**
 * Backtracking/DFS solution
 * 
 * We use backtracking to enumerate all possible combinations with given size that can reach target number
 * 
 * Time complexity: O(1), since each number will be used only once, and candidates are from 1 to 9.
 * So we would have O(9!) loops at max, which is constant
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date May 11, 2016 11:43:15 PM
 */
public class Combination_Sum_III_p216_sol1 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        //DFS/backtracking solution. This time we pass k, n and start variable to each DFS, so that
        //we will stop going deeper if k < 0 or n < 0, and start variable helps us skip using a number more than once
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(result, new ArrayList<Integer>(), 1, k, n);
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> list, int start, int k, int n){
        //boundary cases
        if(k < 0 || n < 0) return;
        //found a valid combination
        if(k == 0 && n == 0){
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        //go through all possible candidates
        for(int i = start; i <= 9; i++){
            list.add(i);
            DFS(result, list, i + 1, k - 1, n - i);
            list.remove(list.size() - 1);
        }
    }
}
