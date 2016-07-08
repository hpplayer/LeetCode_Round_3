import java.util.*;

/*
254. Factor Combinations

Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/		

/**
 * Backtracking solution
 * 
 * We use backtracking to recursively decompose input n to smaller factors. 
 * Each recursion will try all possible factor combinations with smaller factor in front. If we found a pair, then we add two factors into temp list
 * then add to result list. Then we try to decompose the larger factor in next recursion
 * We don't want to build duplicate combinations like 2 2 4 vs 2 4 2, so we will set a start factor value for each recursion. Therefore our factors in 
 * list will always be in non-descending order and avoids duplicate
 * 
 * Time complexity: recursion, masters theorem, exponential
 * Space complexity: same as above
 * @author hpPlayer
 * @date Mar 9, 2016 9:23:41 PM
 */

public class Factor_Combinations_p254_sol1 {
    public List<List<Integer>> getFactors(int n) {
        //Backtracking solution. We use recursions to decompose the factors. 
        //We enumerate all factors x that meets requirement: x * x <= n. if we found x is a factor, then at least we can insert the x and n/x into the result list. Since our x <= n/x, we can use next recursion to decompse n/x and get more factor combinations with x
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //set the initial factor value to be 2, since we Factors should be greater than 1
        DFS(result, new ArrayList<Integer>(), n, 2);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> temp, int n, int start){
        //We want result to be sorted in ascending order, and we don't want to generate duplicates. But we are getting smaller n and we
        //may get a decomposed factor that becomes equals or smaller than previous factor, which may cause duplicates and violate the order,
        //so here we use a start value to control the min factor value in each recursion
        for(int i = start; i * i <= n; i++){
            //we skip undividable factor
            if(n%i != 0) continue;
            
            //found a pair of factor, we can add them to temp list to build a valid factor combination
            List<Integer> copy = new ArrayList<Integer>(temp);
            //since i <= n/i, we will add i first, then n/i
            copy.add(i);
            copy.add(n/i);
            result.add(copy);
            
            //then we try to decompose larger n/i factor, and our later factors shall not be > i, since i has been inserted into list 
            temp.add(i);
            DFS(result, temp, n/i, i);
            temp.remove(temp.size() - 1);
        }
    }
}
