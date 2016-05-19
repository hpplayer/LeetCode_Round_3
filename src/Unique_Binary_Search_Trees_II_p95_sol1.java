import java.util.*;

/*
95. Unique Binary Search Trees II

Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/**
 * DFS with memorization aka DP solution
 * 
 * The main idea is pushing min and max value for each DFS, and each DFS will generate all variants of subtrees
 * inside this range. In each DFS, we will make each num in (min, max) to be root once, and getting its
 * left and right subtrees by pushing (min, i -1) and (i + 1, max) recursively
 * 
 * Since we may need to build subtrees for a given range several times, we will make use of a HashMap to store
 * subtrees for each generated range. That's how we call it DP solution. 
 * 
 * The idea of pushing min and max to subBSTs can also be found in Validate_Binary_Search_Tree_p98_sol1
 * 
 * Time complexity: recursion, masters theorem, must be exponential
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date May 18, 2016 11:54:12 PM
 */
public class Unique_Binary_Search_Trees_II_p95_sol1 {
    public List<TreeNode> generateTrees(int n) {
        //DFS/DP solution. We recursively build subtrees for a given range. Each DFS will pick each num in(min, max) to be root. Then we recursively build its left and right subtrees
        
        //boudary case, the problem requires us to return empty list instead of a list containing null when input is 0
        if(n == 0) return new ArrayList<TreeNode>();
        
        return DFS(1, n, new HashMap<String, List<TreeNode>>());
    }
    
    public List<TreeNode> DFS(int min, int max, HashMap<String, List<TreeNode>> hs){
        String key = min + "#" + max;
        
        //if subtrees for current range have been generated before
        if(hs.containsKey(key)) return hs.get(key);
        
        List<TreeNode> result = new ArrayList<TreeNode>(); 
        
        //boundary case, return null
        if(min > max){
            result.add(null);
            return result;
        }
        
        //generate subtrees by picking each number in (min, max) as a root once
        for(int i = min; i <= max; i++){
            List<TreeNode> leftList = DFS(min, i - 1, hs);
            List<TreeNode> rightList = DFS(i + 1, max, hs);
            
            //try all combinations from left and right lists to generate all possible subtrees
            for(TreeNode left : leftList){
                for(TreeNode right : rightList){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        
        hs.put(key, result);
        return result;
    }
}
