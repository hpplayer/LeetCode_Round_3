import java.util.*;

/*
Binary Tree Inorder Traversal

Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?
*/

/**
 * Intuitive iterative in-order traversal of tree
 * 
 * We use a stack and a temp variable "curr" to visit the tree following in-order traversal
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Sol1 is iterative version
 * Sol2 is recursive version
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 11:55:55 PM
 */
		
public class Binary_Tree_Inorder_Traversal_p94_sol1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        //boundary check
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                //visit left tree first
                curr = curr.left;
            }else{
                //done current path/tree, need go back
                curr = stack.pop();
                //add current node to list
                result.add(curr.val);
                //after adding current node, begin visit right tree
                curr = curr.right;
            }
        }
        
        return result;
    }
}
