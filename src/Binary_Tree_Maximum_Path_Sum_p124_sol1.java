/*
Binary Tree Maximum Path Sum

Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/

/**
 * Post-order traversal problem
 * 
 * We firstly use DFS way to visit the left subtree and right subtree of each each node, those visits shall return the max sum from 
 * left/right subtree. Then we add current root's value to the path sum, and update global maxSum if possible. Then we will return 
 * current max path sum to parent node. Now we can only pick one path either from left or right subtree, whichever is larger.
 * For negative path sum, we can choose to ignore it so at least it will not have negative impact on path sum
 * 
 * Time complexity is O(N), as we need visit each node once
 * Space complexity is O(1), as we just use an extra global variable
 * 
 * Remark:
 * Since the final path sum may < 0, we have to initialize global min with Integer.MIN_VALUE
 * 
 * Sol1 is recursive post-order traversal solution
 * Sol2 is iterative post-order traversal solution uses extra hashmap and stack
 * 
 * So sol1 is more recommended
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 8:26:12 PM
 */

public class Binary_Tree_Maximum_Path_Sum_p124_sol1 {
    public int maxPathSum(TreeNode root) {
        DFS(root);
        return global;
    }
    
    int global = Integer.MIN_VALUE;
    
    //we use DFS to return the value from child nodes
    public int DFS(TreeNode root){
        if(root == null) return 0;
        
        //if path sum from left/right subtree < 0, we can choose to ignore it, so at least it will not have negative impact
        int left = Math.max(0, DFS(root.left));
        int right = Math.max(0, DFS(root.right));
        
        //update path sum at root node
        global = Math.max(global, left + right + root.val);
        
        //when return to parent node, we can only choose one path either from left or right subtree
        //we will pick the one gives larger number
        return root.val + Math.max(left, right);
    }
}
