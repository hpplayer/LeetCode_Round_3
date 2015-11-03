/*
Inorder Successor in BST

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
*/

/**
 * Care about case "root == p"
 * 
 * Sol1 is iterative solution.
 * We firstly make sure node p does not have right tree, otherwise we can directly go its right tree to retrieve the leftmost node as successor node
 * 
 * In other case, successor node will be a parent or ancestor node. So we use an iteration to find the successor.
 * 
 * Stop condition is root != p, and we use an extra variable "prev" to track the parent/ancestor node.
 * In case "root == p" in the beginning, since we have checked p does not have right tree, then the successor will be null. So, we should
 * set "prev" to be null in the beginning
 * 
 * 
 * Sol1 is iterative solution
 * Sol2 is recursive solution
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 1:14:55 PM
 */
public class Inorder_Successor_in_BST_p285_sol1 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right != null){
            //if node p has right tree, then the leftmost node in right tree will be the inorder-successor
            TreeNode curr = p.right;
            while(curr.left != null){
                curr = curr.left;
            }
            return curr;
        }
        
        
      //otherwise, the inorder-successor will be the parent node or ancestor node
      TreeNode prev = null;//!!!!!!!! remember to set prev to be null, in case p does not has successor
      while(root != p){
          if(root.val < p.val){
              //right tree, going right, root will not be successor, so no need update 
             root = root.right;
          }else{
              //left tree, going left, root may be successor, so update prev
              prev = root;
              root = root.left;
          }
      }
      
      //if p is the rightmost node in left tree, i.e. successor will be the root node
      //then we will never update prev node, so prev node is still the original root
      return prev;
    }
}
