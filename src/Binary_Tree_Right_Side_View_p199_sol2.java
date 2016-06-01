import java.util.*;

/**
 * Recursive DFS solution
 * 
 * We visit curr node first, then right subtree then left subtree
 * We use level counter and size of result list to check if curr node is the first node visited in curr level
 * We visit right subtree first to make sure rightmost node will be visited first in the level 
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date May 31, 2016 10:37:40 PM
 */
public class Binary_Tree_Right_Side_View_p199_sol2 {
    public List<Integer> rightSideView(TreeNode root) {
        //recursive DFS solution
        
        List<Integer> result = new ArrayList<Integer>();
        DFS(root, result, 0);
        
        return result;
    }
    
    public void DFS(TreeNode root, List<Integer> result, int level){
        //boundary case
        if(root == null) return;
        
        //first node visited in curr level, since we always visit the rightmost node first,
        //this first node is guaranteed to be the rightmost node in curr level, so we can add it to result
        if(level == result.size()) result.add(root.val);
        
        DFS(root.right, result, level + 1);
        DFS(root.left, result, level + 1);
    }
}
