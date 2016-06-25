import java.util.*;

/*
366. Find Leaves of Binary Tree

Given a binary tree, find all leaves and then remove those leaves. Then repeat the previous steps until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Remove the leaves [4, 5, 3] from the tree

          1
         / 
        2          
2. Remove the leaf [2] from the tree

          1          
3. Remove the leaf [1] from the tree

          []         
Returns [4, 5, 3], [2], [1].
*/

/**
 * DFS solution
 * 
 * This problem is not hard, we just need to realize that we should count the height reversely.
 * We want to repeatedly remove leaves and of course we don't want to have duplicate operations
 * To achieve, we just need to count the height reversely from the bottom to top. For each node,
 * we get its height, then add it to the corresponding list in result lists
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 24, 2016 11:30:17 PM
 */
public class Find_Leaves_of_Binary_Tree_p366_sol1 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        //DFS solution, just need to count the height from bottom to top
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(root, result);
        return result;
    }
    
    public int DFS(TreeNode node, List<List<Integer>> result){
        //boundary case
        if(node == null) return -1;
        
        //this height is counted reversely from bottom to top
        int height = Math.max( DFS(node.left, result), DFS(node.right, result) ) + 1;
        
        //first node in curr level, add list
        if( height == result.size() ) result.add(new ArrayList<Integer>());
        
        result.get(height).add(node.val);
        
        return height;
    }
}
