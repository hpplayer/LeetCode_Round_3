import java.util.*;

/**
 * Iterative BFS solution
 * 
 * We just visit the Tree using iterative BFS and return the num of layers we have counted
 * 
 * Time complexity: O(N)
 * Space complexity: O(n/2)
 * Last layer has n nodes, then all prev layers have totally n - 1 nodes
 * So assume input n, we may have n/2 nodes in leaf layer, which is the layer with most nodes in input tree
 * 
 * @author hpPlayer
 * @date May 22, 2016 11:56:17 PM
 */
		

public class Maximum_Depth_of_Binary_Tree_p104_sol2 {
    public int maxDepth(TreeNode root) {
        //iterative BFS solution.
        
        //boundary check
        if(root == null) return 0;
        
        int result = 0;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        while(!que.isEmpty()){
            //each loop is a layer in tree, so we can update maxDepth based on loop
            int size = que.size();
            result++;
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                if(curr.left != null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
        }
        
        return result;
    }
}
