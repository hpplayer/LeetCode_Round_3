import java.util.Stack;


public class Invert_Binary_Tree_p226_sol2 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            
            //swap subtrees
            TreeNode temp = curr.right;
            curr.right = curr.left;
            curr.left = temp;
            
            //insert non-null subtree to stack
            if(curr.right != null) stack.push(curr.right);
            if(curr.left != null) stack.push(curr.left);
            
        }
        
        return root;
    }
}
