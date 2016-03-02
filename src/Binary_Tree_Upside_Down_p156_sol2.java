/**
 * Tree reverse problem
 * 
 * This solution is a tree version of Reverse_Linked_List_p206_sol2. We will begin reverse the tree during the bot-top process.
 * like sol1, we still need prev and prevRight to update current node's left and right child. So we create an extra function that can take
 * those two inputs
 * 
 * Time complexity: O(N)
 * Space complexity: O(N) due to the extra space cost on recursive stack
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 2:02:36 PM
 */
public class Binary_Tree_Upside_Down_p156_sol2 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        //Recursive solution, similar to Reverse_Linked_List_p206_sol2
        return reverse(root, null, null);
    }
    
    public TreeNode reverse(TreeNode curr, TreeNode prev, TreeNode prevRight){
        //found new root
        if(curr == null) return prev;
        
        //we need reverse the tree from bot to top until we found new Root
        TreeNode newRoot = reverse(curr.left, curr, curr.right);
        
        //update curr Node's left and right children
        //new left is right siblings
        curr.left = prevRight;
        //new right is parent
        curr.right = prev;
        
        return newRoot;
    }
}
