import java.util.*;
/*
Binary Tree Postorder Traversal

Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
*/	
		
/**
 * Trivial recursive post-order traversal
 * 
 * We firstly visit left tree first then right tree, then add curr.val to result
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Sol1 is recursive solution
 * Sol2 is iterative solution that building result list backward
 * sol3 is iterative solution that building result list forward(not recommended, extremely hard to follow the logic)
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 10:34:32 PM
 */
public class Binary_Tree_Postorder_Traversal_p145_sol1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        DFS(root);
        return result;
    }
    
    List<Integer> result = new ArrayList<Integer>();
    
    public void DFS(TreeNode root){
        if(root == null) return;
        DFS(root.left);
        DFS(root.right);
        result.add(root.val);
    }
}
