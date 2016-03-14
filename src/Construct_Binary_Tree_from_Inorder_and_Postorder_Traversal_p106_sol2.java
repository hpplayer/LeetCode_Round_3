import java.util.Stack;

/**
 * Post-order and in-order trarversal
 * 
 * In this solution, we will build the tree bottom-up. We use the inorder[] to help us find the curr rightmost node, which means we have finished this
 * subtree. Then we use postorder[] to build the tree, and add finished nodes to prev nodes. We use stack to store nodes
 * 
 * inorder: [left] [root] [right]
 * postorder: [left] [right] [root]
 * 
 * Firstly we push root to stack, then we will read postorder[] backward. We will add [right] to be the right child of [root]. Then we found pointers in 
 * inorder and postorder point to same value, it means we have finished right subtree, so we will pop node from stack and move the pointer in inorder[]
 * to search the root of right subtree. after we found the root node, then we will resume move pointer in postorder[] to add the left child of [root]
 * 
 * Time complexity: O(N), each node will be visited at most twice
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 14, 2016 1:27:23 PM
 */
public class Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal_p106_sol2 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //In this solution, we build the tree from smaller subtrees
        
        //boundary check
        if(inorder.length != postorder.length || inorder.length == 0) return null;
        
        //pointers in two arrays, i is index in inorder[], j is index is postorder[]
        int i = inorder.length - 1, j = postorder.length - 1;
        
        TreeNode root = new TreeNode(postorder[j--]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        //we build tree based on postorder[]
        while(j >= 0){
            //check if last inserted node is the end of a subtree
            TreeNode curr = stack.peek();
            if(curr.val != inorder[i]){
                //we have not finish curr subtree, continue
                TreeNode right = new TreeNode(postorder[j--]);
                curr.right = right;
                stack.push(right);
            }else{
                //we have finished right subtree, we will pop nodes from stack based on inorder[] to find the root of this subtree
                //we will exit the loop when we pop the root node from stack
                while( !stack.isEmpty() && stack.peek().val == inorder[i]){
                    i--;
                    curr = stack.pop();
                }
                TreeNode left = new TreeNode(postorder[j--]);
                curr.left = left;
                stack.push(left);
            }
        }
        
        return root;
    }
}
