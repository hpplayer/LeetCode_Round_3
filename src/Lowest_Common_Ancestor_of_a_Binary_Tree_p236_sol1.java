/*
Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: ¡°The lowest common ancestor is defined between two nodes v and w as the lowest node in T that
has both v and w as descendants (where we allow a node to be a descendant of itself).¡±

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant
of itself according to the LCA definition.
*/

/**
 * Recursive Post-order traversal 
 * 
 * In this solution, we use recursion to post-order traversal the tree
 * If current node is p or q, we just return current node
 * if current node is null, we just return null
 * 
 * Above is boundary check
 * 
 * For general case, we firstly do recursion on left subtree, then do recursion on right subtree
 * If both left tree and right subtree, then we just return current node as LCA
 * If one of left/right subtree return non-null result, we just continuously return this non-null result
 * Otherwise we have to return null
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 *
 * Sol1 is recursive post-order traversal
 * Sol2 is iterative post-order traversal
 * 
 * @author hpPlayer
 * @date Nov 20, 2015 12:52:32 AM
 */
public class Lowest_Common_Ancestor_of_a_Binary_Tree_p236_sol1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //boundary check
        if(root == null) return null;
        if(root == p || root == q) return root;
        
        //post-order traversal
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        //if both return non-null, then we return current root
        if(left != null && right != null) return root;
        
        //if one of the result return non-null, then we return it
        if(left != null) return left;
        if(right != null) return right;
        
        //otherwise we return null
        return null;
    }
}
