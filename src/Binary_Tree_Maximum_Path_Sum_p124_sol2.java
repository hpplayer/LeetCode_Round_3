import java.util.*;

/**
 * Similar solution with sol1, but here I use iterative solution instead
 * With the cost of stack and HashMap, this solution is much slower than sol1
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 8:49:46 PM
 */
public class Binary_Tree_Maximum_Path_Sum_p124_sol2 {
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        //use HashMap to check if the children of a root have been visited
        Map<TreeNode, Integer> hs = new HashMap<TreeNode, Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        int result = Integer.MIN_VALUE;
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.peek();
            //check if both left child and right child have been visited
            if( (curr.left == null || hs.containsKey(curr.left)) && (curr.right == null || hs.containsKey(curr.right))) {
                int left = curr.left == null? 0 : Math.max(0, hs.get(curr.left));
                int right = curr.right == null? 0 : Math.max(0, hs.get(curr.right));
                
                result = Math.max(result, left + right + curr.val);
                //done this node, remove from stack
                stack.pop();
                //return to parent node, we can only return one path either from left or from right
                hs.put(curr, Math.max(left, right) + curr.val);
            }else{
                //we come here since we have not visited left or right child of curr, let's check which one is not visited
                if(curr.left != null && !hs.containsKey(curr.left)){
                    //left is not visited
                    stack.push(curr.left);
                }else{
                    //right is not visited
                    //right cannot be null, otherwise if left has been visited and right is null
                    //we will never access this block
                    stack.push(curr.right);
                }
            }
        }
        
        return result;
        
    }
}
