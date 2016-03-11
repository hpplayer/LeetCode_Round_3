import java.util.*;

/**
 * BFS + iterative solution
 * 
 * The basic idea is same with sol1, but now we will solve the problem iteratively. Since we will always compare nodes in same level, here we use queue
 * to visit the tree in BFS way. We will try to put target pair nodes together in the queue, therefore, we also need push null child node into queue to
 * make the tree a complete tree. Here if we found both nodes reach the bottom, we cannot simple return true as we did in sol1, here we still need to
 * compare the remaining nodes in queue. The left part is same with sol1, we will compare values in current two nodes. If not same, return false. If same,
 * we will compare the left child of first node with right child of second node, and compare the right child of first node with left child of second node
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 10, 2016 12:39:10 PM
 */
public class Symmetric_Tree_p101_sol2 {
    public boolean isSymmetric(TreeNode root) {
        //BFS + preorder traversal. The basic idea is same with Symmetric_Tree_p101_sol1, but now we use queue to visit the tree
        //and if two nodes both reach bottom, we cannot return directly, since we still need to compare remaining nodes in queue
        
        //boundary check
        if(root == null) return true;
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root.left);
        que.offer(root.right);
        
        while(!que.isEmpty()){
            //we don't know tree structure in ahead, so we need build the complete tree therefore we allow adding null node into queue
            //so that we are guaranteed to get the target pair of nodes during BFS
            TreeNode a = que.poll();
            TreeNode b = que.poll();
            
            if(a == null || b == null){
                //at least one node reaches bottom, here if we found a == b, we can't simply return true as we did in recursive version
                //since we still need to compare remaining nodes in queue
                if(a != b) return false;
                continue;
            }
            
            //check current pair of nodes
            if(a.val != b.val) return false;
            
            //current pair of nodes, we check the children of them
            //compare a.left with b.right and a.right with b.left
            //we will allow adding null nodes into queue to build a complete tree, so we will always compare the target pair
            que.offer(a.left);
            que.offer(b.right);
            
            que.offer(a.right);
            que.offer(b.left);
        }
        
        return true;
    }
}
