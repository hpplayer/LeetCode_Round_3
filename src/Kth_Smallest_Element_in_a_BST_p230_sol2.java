import java.util.Stack;

/**
 * In-order traversal
 * 
 * We use an iterative version of in-order traversal to visit the tree
 * 
 * @author hpPlayer
 * @date Feb 15, 2016 11:15:54 PM
 */
public class Kth_Smallest_Element_in_a_BST_p230_sol2 {
    public int kthSmallest(TreeNode root, int k) {
        //iterative version of in-order traversal
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                //we have finished the left tree of temp, need go to right subtree
                TreeNode temp = stack.pop();
                if(--k == 0) return temp.val;
                curr = temp.right;
            }
        }
        
        //we have finished the tree but still can't find the kth node, then return an exception
        throw new IllegalArgumentException("wroing input k");
    }
}
