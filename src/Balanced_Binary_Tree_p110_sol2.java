import java.util.*;

/**
 * Iterative post-order traversal solution
 * 
 * We still use stack to visit the input tree, but using HashMap to help us visit the tree follow post-order
 * traversal order
 * 
 * Time complexity: O(N) as each node must be visited once
 * Space complexity: O(N) as we finally we store all nodes in HashMap
 * 
 * @author hpPlayer
 * @date May 24, 2016 9:22:47 PM
 */

public class Balanced_Binary_Tree_p110_sol2 {
    public boolean isBalanced(TreeNode root) {
        //Iterative post-order traversal with HashMap and stack
        
        //boundary check
        if(root == null) return true;
        
        //key is node, value is height of subtree rooted at this node
        HashMap<TreeNode, Integer> hs = new HashMap<TreeNode, Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            //check next node in scope. We may have done both subtrees or only one subtree of this scope
            //so we firstly use peek() and use hashMap to decide next step, then decide whether pop it out
            TreeNode curr = stack.peek();
            
            if( (curr.left == null || hs.containsKey(curr.left)) && (curr.right == null || hs.containsKey(curr.right)) ){
                //if we have done both subtrees, then we need to check if subtree rooted at this node is balanced
                int l = curr.left == null? 0 : hs.get(curr.left);
                int r = curr.right == null? 0 : hs.get(curr.right);
                
                //if it is imbalanced, then return false directly
                if(Math.abs(l - r) > 1 ) return false;
                
                //otherwise update info in the HashMap, and pop it out
                hs.put(curr, Math.max(l, r) + 1);
                stack.pop();
            }else{
                
                if(curr.left != null && !hs.containsKey(curr.left) ){
                    //if we have left subtree, but not visit it before, then start visit it
                    stack.push(curr.left);
                }else{
                    //if we dont have left subtree, or we have visited left subtree, then we need to start
                    //visit right subtree
                    stack.push(curr.right);
                }
            }
        }
        
        //all nodes are checked, return true
        return true;
    }
}
