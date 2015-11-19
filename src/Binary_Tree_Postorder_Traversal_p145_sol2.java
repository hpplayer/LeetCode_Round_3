import java.util.*;
/**
 * Iterative post-order traversal
 * 
 * The order of children should be same with pre-order traversal
 * But now instead of building list forward, we should build the list backward.
 * We always add new node to the head of list. Therefore, to keep the same child order, we have to visit right child first
 * 
 *   2
 * 1  3 => post-order 1 3 2
 * 
 * we firstly visit 2, add to list
 * then we visit 3, add to head of list
 * lastly we visit 1, add to head of list
 * 
 * => pre-order 2 1 3
 * We firstly visit 2, add to list
 * then we visit 1, add to tail of list
 * then we visit 3, add to tail of list
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * This solution can be related with Binary_Tree_Preorder_Traversal_p144_sol1.
 * In Binary_Tree_Preorder_Traversal_p144_sol1, we build list forward. Here we build list backward
 * 
 * @author hpPlayer
 * @date Nov 18, 2015 10:48:00 PM
 */
public class Binary_Tree_Postorder_Traversal_p145_sol2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        if(root == null) return result;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            result.offerFirst(curr.val);
            //push left child to stack first, so it will be added to list lastly
            if(curr.left != null) stack.push(curr.left);
          //push right child to stack second, so it will be added to list before left child
            if(curr.right != null) stack.push(curr.right);
        }
        
        return result;
    }
}
