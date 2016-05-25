import java.util.*;

/**
 * BFS solution
 * 
 * Just visit the input tree level by level (BFS) and return the depth once we found a leaf node
 * 
 * Time complexity: O(N) as we need to visit each node once
 * Space complexity: O(N) as last level will have N/2 nodes, which can also be represented as O(N)
 * 
 * @author hpPlayer
 * @date May 24, 2016 9:30:55 PM
 */
public class Minimum_Depth_of_Binary_Tree_p111_sol1 {
    public int minDepth(TreeNode root) {
        //Using BFS to visit the tree and return minDepth once we found the shortest path to leaves
        
        //boundary check
        if(root == null) return 0;
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        int depth = 0;
        
        while(!que.isEmpty()){
            //each loop is a level in input tree
            depth++;
            int size = que.size();
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                if(curr.left == null && curr.right == null){
                    //we found a leaf node, return curr depth
                    return depth;
                }
                if(curr.left != null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
        }
        
        return depth;
    }
}
