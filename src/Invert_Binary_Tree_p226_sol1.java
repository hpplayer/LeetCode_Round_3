/*
 * Invert Binary Tree
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 */

/**
 * Sol1 is recursive version with pre-order traversal
 * Sol2 is iterative version with pre-order traversal
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 1:39:49 PM
 */
public class Invert_Binary_Tree_p226_sol1 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        
        //back up right tree
        TreeNode temp = root.right;
        
        //set root.right to be reversed version of left tree
        root.right = invertTree(root.left);
        //set root.left to be reversed version of backup right tree 
        root.left = invertTree(temp);
        
        return root;
    }
}
