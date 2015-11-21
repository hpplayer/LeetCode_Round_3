/*
Lowest Common Ancestor of a Binary Search Tree

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: ¡°The lowest common ancestor is defined between two nodes v and w as the lowest node in T that
has both v and w as descendants (where we allow a node to be a descendant of itself).¡±

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant
of itself according to the LCA definition.
*/

/**
 * BST problem
 * 
 * We observe that the LCA in BST is the node that has val between Min(p,q) and Max(p, q)
 * So we just move root accordingly until we found a node meets such requirement
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 20, 2015 12:45:06 AM
 */


public class Lowest_Common_Ancestor_of_a_Binary_Search_Tree_p235_sol1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //a very elegant solution of finding LCA in BST
        //we observed that the LCA will be the node that is larger than Min(p,q) and smaller than Max(p.q)
        
        //boundary check
        if(root == null) return null;
        
        //get the max and min value of p and q
        int max = Math.max(p.val, q.val);
        int min = Math.min(p.val, q.val);
            
        //we will searching parent node in the BST until we reach end
        while(root != null){
            if(root.val > max){
                //too large, go left
                root = root.left;
            }else if(root.val < min){
                //too small, go right
                root = root.right;
            }else{
                //found the target node meeting requirement
                break;
            }
        }
        
        return root;
    }
}
