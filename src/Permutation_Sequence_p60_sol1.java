import java.util.*;

/*
60. Permutation Sequence

The set [1,2,3,бн,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

/**
 * Math solution
 * 
 * If we fix the first number, then we can have (n-1)! permutations
 * Assume inputs are valid, then k/(n-1)! can tell us the number of first index if we allow duplicate numbers
 * But here we are discussing permutation, and we have to use each number once, therefore now k/(n-1)! is telling us the index
 * of number in current set.
 * 
 * After the first number is fixed, then we can jump to second number. But we also need to update k. Since we have skipped
 * k/(n-1)! * (n-1)! numbers, our new k would be k - k/(n-1)! * (n-1)!.
 * Notice: k/(n-1)! is used to get the index in curr set and we used floor() to get the value, so here k/(n-1)! * (n-1)! is 
 * not same with k
 * 
 * We repeat above steps until we reach the last number. And we need n loops
 * 
 * Time complexity: O(n!) to get the lookup table, O(n^2) to update set (if we always remove the first number in ArrayList,
 * then we need to move whole ArrayList each loop). So total time would be O(n^2), where n is from 1 to 9 so we can also
 * treat the solution as O(1)
 * 
 * Space complexity: O(n) as we need O(N) lookup table and O(n) set
 * 
 * Remark:
 * Attached PDF have given a very good explanation.
 * 
 * 
 * @author hpPlayer
 * @date May 12, 2016 11:20:34 PM
 */
public class Permutation_Sequence_p60_sol1 {
    public String getPermutation(int n, int k) {
        //Math solution. Each time we identify val in curr index using k/(n-1)!, then update k by k -= index * factorial[i-1],
    	//where i is value of current index
        //Index here can either be used as index of num or be used as the size of prev num
        //ex: n = 3, k = 3.
        //3/(2!) = 1, 1 is either the index of num, which is 2, or the size of prev num, which is 1
        //therefore first num would be 2, and we update k to be 3 - 1 * 2 = 1 i.e. we need find the 1st element
        //left in set, which is 1. So 3th number permutation would be 213
        
        //set container, we use list structure here, so we can dynamically remove numbers and still keep the correct order of rest set
        List<Integer> set = new ArrayList<Integer>();
        //look up table that we can use to get factorial numbers
        int[] factorial = new int[n + 1];
        //StringBuilder we used to create result string
        StringBuilder sb = new StringBuilder();
        
        //Initialize the set with first number in permutation
        for(int i = 1; i <= n; i++){
            set.add(i);
        }
        
        //update lookup table
        //0! = 1
        factorial[0] = 1;
        for(int i = 1; i <= n; i++) factorial[i] = factorial[i-1] * i;
        
        //input k is 1 based, but sol1 is based on 0 base, so we need index conversion here
        k--;
        
        //we begin identify number in result permutation from left to right
        for(int i = n; i >= 1; i--){
            //firstly get the index of curr num (index = k/(n-1)!)
            int index = k/factorial[i-1];
            //then get the value in that index in set
            sb.append(set.get(index));
            //we have used the value in set, so remove set
            //list container can help us maintain the oder of rest set
            set.remove(index);
            //Then we need update k. Index is also the count of numbers we skipped in set before getting index. 
            //We have counted index * (n-1)! numbers, so subtract it from k to get new k
            //Notice: when we get index, we are using floor(), so k != index
            k -= index * factorial[i-1];
        }
        
        return sb.toString();
    }
}
