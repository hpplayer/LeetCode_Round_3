/*
104. Maximum Depth of Binary Tree

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/

/**
 * Recursive DFS solution
 * 
 * Use a global variable to record the max_depth, and use DFS to update it recursively
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Sol2 is the iterative BFS solution (Of course we can also apply DFS iterative solution)
 * 
 * @author hpPlayer
 * @date May 22, 2016 11:45:37 PM
 */
public class Maximum_Depth_of_Binary_Tree_p104_sol1 {
    int result;
    
    public int maxDepth(TreeNode root) {
        //recursive DFS solution
        result = 0;    
        DFS(root, 0);
        
        return result;
    }
    
    public void DFS(TreeNode node, int depth){
        //boundary case
        if(node == null) return;
        
        //otherwise a valid node, depth + 1
        result = Math.max(result, depth + 1);
        
        //left subtree
        DFS(node.left, depth + 1);
        //right subtree
        DFS(node.right, depth + 1);
    }
}
