/*
100. Same Tree

Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
*/

/**
 * Recursive DSF solution
 * 
 * Classic DFS solution. If node value is different, then return false
 * Otherwise recursively compare its left and right subtree
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Sol1 is the recursive solution
 * Sol2 is the iterative solution
 * 
 * @author hpPlayer
 * @date May 22, 2016 11:32:07 PM
 */
public class Same_Tree_p100_sol1 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //Recursive DSF solution
        
        //boundary case
        if(p == null || q == null) return p == q;
        
        //different nodes, return false
        if(p.val != q.val) return false;
        
        //same nodes, compare left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
