/*
Recover Binary Search Tree

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
*/	

/**
 * In-order traversal in BST solution
 * 
 * In-order traversal is really useful in BST. Follow in-order traversal, we should have exactly following order: prev.val < curr.val < next.val
 * It is not, then we found at least one incorrectly placed TreeNode. So the basic idea is to visit the tree with in-order traversal and search
 * for two swapped nodes. Then swap them back
 * 
 * Since we get two elements are swapped by mistake, there must be a smaller TreeNode get a larger value and a larger TreeNode get a smaller value.
 * Their value are swapped, but the incorrect smaller node is still in smaller tree and incorrect larger node is still in larger tree. So we should
 * visit the incorrect smaller node first, and this node will be detected when we compare its value with next.val i.e. when it is treated as prev node.
 * The incorrect larger node will be detected when we compare its value with prev.val. We don't know if it is close or not close to incorrect smaller node,
 * so we should continue search BST and update it if we found another incorrect node
 * 
 * Running time: O(n), in case our input is like a LinkedList
 * Space complexity: O(n), in case our input is like a LinkedList
 * 
 * 
 * Sol1 is recursive in-order traversal
 * Sol2 is iterative in-order traversal
 * Sol3 is Morris-traversal solution
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 5:07:50 PM
 */
public class Recover_Binary_Search_Tree_p99_sol1 {
    public void recoverTree(TreeNode root) {
        //use inorder traversal to detect incorrect node
        
        inOrder(root);
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;
    
    public void inOrder(TreeNode root){
        if(root == null) return;
        
        inOrder(root.left);
        
        //in inorder traversal of BST, prev should always have smaller value than current value
        if(prev != null && prev.val >= root.val){
            //Since we get two elements are swapped by mistake
            //there must be a smaller val get larger value (TreeNode a), and a larger value get a smaller value (TreeNode b)
            //Obviously we will visit TreeNode a first then visit TreeNode b, since a is still in small value tree
            //TreeNode a will be detected when it is treated prev and we compare it with its next node.
            //TreeNode b will be detected when it is treated as curr and we compare it with its prev node
            //Since TreeNode b may be far away from a, we should cover all cases and just let second = curr
            
            if(first == null) first = prev;
            second = root;
        }
        
        
        //update prev node
        prev = root;
        
        inOrder(root.right);
    }
}
