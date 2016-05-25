/*
110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
subtrees of every node never differ by more than 1.
*/

/**
 * Post-order traversal
 * 
 * We visit the tree follow post-order traversal order, and record the height from subtrees.
 * If left subtree is imbalanced, right subtree is imbalanced or left and right subtrees have height difference > 1
 * then return false.
 * 
 * Time complexity: O(N) as each node will be visited only once
 * Space complexity: O(N) as we may have a list-like tree, therefore we will have O(N) nested DFS
 * 
 * Sol1 is recursive solution
 * Sol2 is iterative solution using HashMap and stack
 * @author hpPlayer
 * @date May 24, 2016 9:06:40 PM
 */
public class Balanced_Binary_Tree_p110_sol1 {
    public boolean isBalanced(TreeNode root) {
        //DFS solution + postorder traversal
        //we will firstly check left subtree to see if it is balanced, it not return false directly, if it is
        //then we record its height and check its right subtree. If two subtrees are both balanced and they
        //have same height, then we return true for current tree
        
        isBalanced = true;
        DFS(root);
        
        return isBalanced;
    }
    
    boolean isBalanced;
    
    public int DFS(TreeNode root){
        if(root == null){
            //reach boundary, start counting height
            return 0;
        }    
        
        //visit left subtree
        int left = DFS(root.left);
        //if left subtree is imbalanced, then return false directlty, not need to look further
        if(!isBalanced) return -1;
        
        //visit right subtree
        int right = DFS(root.right);
         //if right subtree is imbalanced, then return false directlty, not need to look further
        if(!isBalanced) return -1;
        
        //if left and right subtree are both balanced, but have height difference > 1, then return false
        if(Math.abs(left - right) > 1){
            isBalanced = false;
            return -1;
        }
        
        //otherwise return the max(left, right) + 1;
        
        return Math.max(left, right) + 1;
    }
}
