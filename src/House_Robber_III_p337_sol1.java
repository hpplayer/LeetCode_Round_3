/*
337. House Robber III

The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root."
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree".
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
*/

/**
 * Postorder traversal + DP solution
 * 
 * This problem is a tree version of House_Robber_I_p198_sol1.
 * In this solution, we will use postorder traversal to calculate the rob amount from bottom to top
 * Since for each node we have two choices: rob it or not rob it, and their values are based on prev these two values, we have to return an int array
 * to record prev value. Here int[0] means the max cash we will get when choose to not rob curr house, and int[1] means the max cash we will get when
 * we choose to rob curr house
 * 
 *  If we choose to rob curr house at Node a, then
 *  int[1] = a.val + left[0] + right[0], which means we need the max not rob value from left and right child
 *  If we choose to not rob curr house at node a, then
 *  int[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]), which means we are free to rob prev houses and we just want get the max value
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 15, 2016 8:25:14 PM
 */

public class House_Robber_III_p337_sol1 {
    public int rob(TreeNode root) {
        //postorder traversal + DP solution. We use postorder traversal to visit the tree and use dp way to get the max rob value
        
        int[] result = DFS(root);
        
        return Math.max(result[0], result[1]);
    }
    
    public int[] DFS(TreeNode root){
        //reach boundary
        if(root == null) return new int[]{0, 0};
        
        //post-order traversal
        int[] left = DFS(root.left);
        int[] right = DFS(root.right);
        
        //array for curr node
        //result[0] means the max value we will get when not rob curr node
        //result[1] means the max value we will get when rob curr node
        int[] result = new int[2];
        
        //not rob curr house, we are free to rob/not-rob left and right child, and we want get the max one
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //rob curr house can only from not rob two child house
        result[1] = root.val + left[0] + right[0];
        
        return result;
    }
}
