import java.util.*;
/*
Binary Tree Preorder Traversal

Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/

/**
 * Intuitive iterative pre-order traversal of tree
 * 
 * We build the list forward, so we should visit left child first then right child, therefore left child will be before right child
 * 
 * This solution can be related with Binary_Tree_Postorder_Traversal_p145_sol2.
 * In Binary_Tree_Postorder_Traversal_p145_sol2, we build list backward. Here we build list forward
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Sol1 is iterative solution
 * Sol2 is recursive solution
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 11:00:33 PM
 */
		
public class Binary_Tree_Preorder_Traversal_p144_sol1 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            result.add(curr.val);
            if(curr.right != null) stack.push(curr.right);
            if(curr.left != null) stack.push(curr.left);
        }
        return result;
    }
}
