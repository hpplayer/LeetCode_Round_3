import java.util.*;

/**
 * Intuitive recursive in-order traversal of tree
 * 
 * visit left tree first, then current root node, then right tree
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 11:58:13 PM
 */

public class Binary_Tree_Inorder_Traversal_p94_sol2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        DFS(root);
        return result;
    }
    
    List<Integer> result = new ArrayList<Integer>();
    
    public void DFS(TreeNode root){
        if(root == null) return;
        
        DFS(root.left);
        
        result.add(root.val);
        
        DFS(root.right);
    }
}
