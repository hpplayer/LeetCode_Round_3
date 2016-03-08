import java.util.*;

/*
102. Binary Tree Level Order Traversal

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
*/

/**
 * BFS solution
 * 
 * Intuitive solution, we use BFS to visit the tree layer by layer, then add each layer as a list into the result
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 3:58:21 PM
 */
public class Binary_Tree_Level_Order_Traversal_I_p102_sol1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        //BFS solution, we just use BFS to visit the tree level by level, then add each level as a list into the result
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        //boundary check
        if(root == null) return result;
        
        //we use Queue to do the BFS
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> temp = new ArrayList<Integer>();
            
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                temp.add(curr.val);
                //only add non-null node into the queue
                if(curr.left != null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
            //finish current level, add into result
            result.add(temp);
        }
        return result;
    }
}
