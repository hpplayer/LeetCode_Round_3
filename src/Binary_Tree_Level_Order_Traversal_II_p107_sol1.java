import java.util.*;

/*
107. Binary Tree Level Order Traversal II

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
*/

/**
 * BFS solution
 * 
 * This solution is similar to Binary_Tree_Level_Order_Traversal_p102_sol1
 * we use BFS to visit the tree layer by layer, then add it to result, but this time we will add newly generated list into the head of result
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 4:21:07 PM
 */
public class Binary_Tree_Level_Order_Traversal_II_p107_sol1 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //BFS solution, almost similar to Binary_Tree_Level_Order_Traversal_p102_sol1, but now we need insert new level list into
        //the beginning of result instead of inserting into the end as we did in Binary_Tree_Level_Order_Traversal_p102_sol1
        
        //use linkedlist so we can add new list into the head with O(1) time
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        
        //boundary check
        if(root == null) return result;
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            //temp list is used to store nodes in same level
            List<Integer> temp = new ArrayList<Integer>();
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                temp.add(curr.val);
                if(curr.left != null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
            //we have done current level, and now we need to add the temp list to the head of result 
            result.offerFirst(temp);
        }
        
        return result;
    }
}
