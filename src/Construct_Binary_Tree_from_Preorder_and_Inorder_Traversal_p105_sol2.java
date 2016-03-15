import java.util.Stack;

/**
 * Iterative preorder traversal + inorder traversal solution
 * 
 * Similar to Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_p106_sol2, but now we will visit left subtree first then right subtree
 * Compare to sol1, in sol2, we will build the tree bottom-up. Inorder[] will help us decide if we have finished left subtree or not. Preorder[] will
 * help us get the next node in preorder. As long as we have not reached the boundary, we will add all nodes to the left of prev node. If we reach the
 * boundary, then we will start pop nodes from stack and compare with nodes in inorder[], and our goal is to remove all finished subtrees and search
 * for next subtree whose right subtree has not been finished yet. Then we will pop next node in preorder[] and add to prev node and start build 
 * right subtree from bottom-up style
 * 
 * Time complexity: O(N), each node will be visited at most twice
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 14, 2016 4:59:52 PM
 */
public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal_p105_sol2 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //iterative solution, similar to Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_p106_sol2, but this time
        //we will reach both array forward, and append left subtree first, then right subtree
        
        //boudary check
        if(preorder.length != inorder.length || inorder.length == 0) return null;
        
        //two pointers in two array, i is in preorder and j is in inorder
        int i = 0, j = 0;
        
        TreeNode root = new TreeNode(preorder[i++]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        //we will build trees as long as there are nodes left in preorder[]
        while(i < preorder.length){
            TreeNode curr = stack.peek();
            
            if(curr.val != inorder[j]){
                //we have not finished left subtree yet, continue going left
                TreeNode left = new TreeNode(preorder[i++]);
                curr.left = left;
                stack.push(left);
            }else{
                //we have finished left subtree, start poping nodes from stack until we reach the root node whose right subtree has 
                //not been visited yet
                while(!stack.isEmpty() && stack.peek().val == inorder[j]){
                    curr = stack.pop();
                    j++;
                }
                
                //we have not finished right subtree yet, going right
                TreeNode right = new TreeNode(preorder[i++]);
                curr.right = right;
                stack.push(right);
            }
        }

        return root;
        
    }
}
