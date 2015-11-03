/**
 * Recursive solution
 * 
 * Each recursion, we will firstly choose going left or right subtree in next recursion.
 * If root.val <= p.val, we will going right tree and current root node will never be the successor node,
 * so we just return result from recursions
 * 
 * If root.val > p.val, then we will going left tree. current root node may be the successor node depending on whether we have a non-null
 * result from recursions.
 * 
 * In this solution. root ==  null case has been handled in going right subtree
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 1:18:52 PM
 */
public class Inorder_Successor_in_BST_p285_sol2 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;
        
        if(root.val <= p.val){
            //right tree, curr root node will never be successor, so we just return result from right tree
            return inorderSuccessor(root.right, p);
        }else{
            //left tree, curr root node may be the successor. if we found result from left tree is null
            //curr root node will be successor, otherwise, we return the result from left tree
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null? root : left;
        }
    }
}
