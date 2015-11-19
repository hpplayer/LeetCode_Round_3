import java.util.*;
/**
 * Trivial recursive post-order traversal
 * 
 * 
 * We firstly add current root's val, then visit left tree after that visit right tree
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 11:05:33 PM
 */
public class Binary_Tree_Preorder_Traversal_p144_sol2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        DFS(root);
        return result;
    }
    
    List<Integer> result = new ArrayList<Integer>();
    
    public void DFS(TreeNode root){
        if(root == null) return;
        
        result.add(root.val);
        
        DFS(root.left);
        DFS(root.right);
    }
}
