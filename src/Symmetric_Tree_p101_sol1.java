/*
101. Symmetric Tree

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
*/

/**
 * DFS + preorder traversal solution
 * 
 * The problem asks us to check if two subtrees of each node are symmetric.
 * In this solution, we use DFS to compare each subtree. We need a DFS function that receives two inputs, so we can compare them
 * For each node, we firstly compare they have same value, then we use recursions to node a's left tree with node b's right tree, and compare node a's
 * right tree with node b's left tree
 * 
 * Even though we use DFS to visit the tree, we still compare two nodes in same level
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * sol2 is iterative version
 * @author hpPlayer
 * @date Mar 10, 2016 12:24:28 PM
 */
public class Symmetric_Tree_p101_sol1 {
    public boolean isSymmetric(TreeNode root) {
        //DFS + preorder traversal solution, firstly compare node a and node b, them compare a's left with b's right and compare 
        //a's right with b's left
        
        //boundary check
        if(root == null) return true;
        
        return DFS(root.left, root.right);
    }
    
    public boolean DFS(TreeNode a, TreeNode b){
        //at least one node reaches boundary 
        if(a == null || b == null){
            return a == b;
        }
        
        //compare curr pair of inputs
        if(a.val != b.val) return false;
        
        //curr pair of inputs is same, then we continue
        return DFS(a.left, b.right) && DFS(a.right, b.left);
    }
}
