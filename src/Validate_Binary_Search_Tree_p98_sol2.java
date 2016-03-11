import java.util.Stack;

/**
 * In-order traversal
 * 
 * In BST, nodes in in-order traversal will be in ascending order. Therefore we use in-order traversal to visit the tree, and compare curr node with 
 * prev node. if prev.val >= curr.val, then we return false
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 10, 2016 10:52:42 AM
 */

public class Validate_Binary_Search_Tree_p98_sol2 {
    public boolean isValidBST(TreeNode root) {
        //in-order traversal. In BST, the in-order traversal will return nodes in strict ascending order. If any node violate this rule
        //then we will return false
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //prev variable is used to record the prev node in the in-order traversal
        TreeNode prev = null;
        //curr variable is ued to record curr node in the in-order traversal
        TreeNode curr = root;
        
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                //reach bot, poll node from stack
                curr = stack.pop();
                
                //check curr node node with prev node in in-order traversal
                if(prev != null && prev.val >= curr.val) return false;
                
                //we have done curr node, we will go to next node, which is node.right
                prev = curr;
                curr = curr.right;
            }
        }
        
        return true;
    }
}
