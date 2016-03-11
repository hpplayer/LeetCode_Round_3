/*
98. Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/

/**
 * Preorder traversal 
 * 
 * In this problem we use recursive preorder traversal to visit the tree and define the upper and lower limit for each node
 * If the node violate the limit, then we will return false. However if we reach bottom null node, without violation then we return true
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * Here use a strict rule to define the tree that the child node cannot have same value with parent node!!!!!!!!!!!!!!!
 * 
 * Sol2 is an iterative, inorder traversal solution
 * 
 * @author hpPlayer
 * @date Mar 10, 2016 10:45:12 AM
 */
public class Validate_Binary_Search_Tree_p98_sol1 {
    public boolean isValidBST(TreeNode root) {
        //pre-order traversal solution, we use pre-order traversal to visit the tree, and define the min and max limit for each node
        //if any node violates the limit, then we will return false. 
        //If we reach null, then we return true
        
        return DFS(root, null, null);
    }
    
    public boolean DFS(TreeNode root, Integer min, Integer max){
        //reach boundary without problem, return true
        if(root == null) return true;
        
        //violate the limit, return false
        if( (min != null && root.val <= min) || (max != null && root.val >= max) ) return false;
        
        //otherwise visit and check children nodes
        return DFS(root.left, min, root.val) && DFS(root.right, root.val, max);
    }
}
