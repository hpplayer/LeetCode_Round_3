import java.util.*;
/*
96. Unique Binary Search Trees

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/


/**
 * Recursive DP solution
 * 
 * This solution is a simpler version of Unique_Binary_Search_Trees_II_p95_sol1.
 * Here we don't need to build the tree recursively instead we need to recursively find the num of ways to build subtrees within given range
 * 
 * The main structure is similar to Unique_Binary_Search_Trees_II_p95_sol1. But now our HashMap will store num of ways as value.
 * Another change is that how we handle boundary cases. In Unique_Binary_Search_Trees_II_p95_sol1, we return null for min > max and return
 * valid node when min == max, but now those two boundary cases will both return 1 indicating there is only one way to build null and single
 * node.
 * 
 * Time complexity: exponential, Master's theorem
 * Space complexity: same as above
 * 
 * 
 * @author hpPlayer
 * @date May 19, 2016 1:23:08 AM
 */
public class Unique_Binary_Search_Trees_I_p96_sol1 {
    public int numTrees(int n) {
        //recursive DP solution. main body is similar to Unique_Binary_Search_Trees_II_p95_sol1
        return DFS(1, n, new HashMap<String, Integer>());
    }
    
    public int DFS(int min, int max, HashMap<String, Integer> hs){
        String key = min + "#" + max;
        if(hs.containsKey(key)) return hs.get(key);
        
        //boundary cases. For both boundary cases, we only have one way to build them
        if(min > max || min == max){
            return 1;
        }
        
        int sum = 0;
        
        //pick each num in the range as the root of subtree
        for(int i = min; i <= max; i++){
            int left = DFS(min, i - 1, hs);
            int right = DFS(i + 1, max, hs);
            //the way to build subtrees would be left * right
            //since boundary cases will return non-zero value, we can safely do this multiplication here
            sum += left * right;
        }
        
        hs.put(key, sum);
        return sum;
    }
}
