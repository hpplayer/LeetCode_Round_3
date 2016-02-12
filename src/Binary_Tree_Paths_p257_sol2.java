import java.util.*;

public class Binary_Tree_Paths_p257_sol2 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if(root == null) return result;
        
        DFS(result, root.val + "", root);
        
        return result;
    } 
    
    public void DFS(List<String> result, String str, TreeNode root){
        if(root.left == null && root.right == null){
            result.add(str);
            return;
        } 

        if(root.left != null) DFS(result, str + "->" + root.left.val, root.left);
        if(root.right != null) DFS(result, str + "->" + root.right.val, root.right);
    }
}
