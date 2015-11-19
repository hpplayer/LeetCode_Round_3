import java.util.*;
/**
 * non-intuitive iterative post-order traversal
 * 
 * We use two variables to track the relationship of prev and current variable.
 * 
 * If prev is parent of current node (prev.left = curr || prev.right = curr), then the tree may still not end, so we try to visit deeper
 * if prev is left child of current node (curr.left == prev), then we have done the left tree, we will try to visit right tree
 * if prev is right child of current node (curr.right == prev), then we have done the right tree, we will pop a node from stack and
 * return to upper root node.
 * In the case prev == curr, it means the node we put to stack is the last node of a path, so we need go back and check its parent node
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 11:39:54 PM
 */
public class Binary_Tree_Postorder_Traversal_p145_sol3 {
    public List<Integer> postorderTraversal(TreeNode root) {
        //we still visit the tree following postorder traveral (left -> right -> root), but this time we need help of prev variable
        
        //boundary check 
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        TreeNode prev = null;
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.peek();
            
            //if we just start visiting the tree/we have not finished the tree traversal(curr is child of prev)
            //we should continuely visit the tree and go deeper
            if(prev == null || prev.left == curr || prev.right == curr){
                //firstly we try to visit the left child
                if(curr.left != null){
                    stack.push(curr.left);
                }else if (curr.right != null){
                    //if no left child, then we should visit the right child
                    stack.push(curr.right);
                }
            }else if(curr.left == prev){
                //if we just finished the left tree(pop a left child from stack), then move to right tree
                if(curr.right != null) stack.push(curr.right);
            }else if(curr.right == prev || curr == prev){
                //if we reach the bottom of left tree or we have done right tree,
                //then we should pop node from stack and go to upper layer
                result.add(stack.pop().val);
            }
            
            //update prev
            prev = curr;
        }
        
        return result;
        
    }
}
