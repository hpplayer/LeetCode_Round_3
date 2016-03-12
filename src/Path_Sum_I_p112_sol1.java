/*
112. Path Sum

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

/**
 * DFS solution
 * 
 * Intuitive DFS solution, we just check each path in DFS way and update sum accordingly
 * When we reach the leaf node and sum = 0, then we return true, otherwise we return false
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 4:21:53 PM
 */
public class Path_Sum_I_p112_sol1 {
    public boolean hasPathSum(TreeNode root, int sum) {
        //naive DFS solution, we will check solution in the leaf level to see if the sum is 0
        
        //boundary check
        if(root == null) return false;
        
        //count curr node into path
        sum -= root.val;
        
        //if we are in leaf node and sum is 0, then return true
        if(sum == 0 && root.left == null && root.right == null) return true;
        
        //otherwise, continue check left and right subtree
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    } 
}
