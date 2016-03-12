import java.util.*;

/**
 * DFS solution
 * 
 * The basic idea is same with Path_Sum_I_p112_sol1, but now we need to record nodes in the path when updating sum.
 * Therefore we need an extra DFS function to take path list, curr node and sum
 * When we finish curr path, we should remove curr node from path list, so we can start visiting the path in next subtree
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 4:29:10 PM
 */

public class Path_Sum_II_p113_sol1 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        //advanced version of Path_Sum_I_p112_sol1, we still use intuitive DFS way to visit the tree and add nodes into the path
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(result, new ArrayList<Integer>(), root, sum);
        
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> path, TreeNode root, int sum){
        //reach boundary, doing nothing
        if(root == null) return;
        
        //update sum and path list
        sum -= root.val;
        path.add(root.val);
        
        //reach leaf node and sum is 0, then we add curr path into result list 
        if(root.left == null && root.right == null && sum == 0){
            result.add(new ArrayList<Integer>(path));
            //remove curr node and return so we can start search in another subtree
            path.remove(path.size()-1);
            return;
        }
        
        //otherwise we visit left subtree and right subtree
        DFS(result, path, root.left, sum);
        DFS(result, path, root.right, sum);
        
        //finish curr subtree, remove node so we can start search in next subtree
        path.remove(path.size()-1);
    }
}
