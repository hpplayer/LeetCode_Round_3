import java.util.*;

/*
103. Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

/**
 * Deque + BFS solution
 * 
 * We need a deque in this solution to read node from front or back freely
 * In this solution we use level no. to decide where to read deque.
 * To make the read from queue works correctly, we have to poll old node from one direction and offer new node in another direction
 * To make the node in result having a zigzag shape, we require even level and odd level have different read and write order.
 * If even level read front and add back, then we need odd level read back and write front, and vice versa.
 * In this way, the node we read will from the zigzag order, and we can add them to result follow the read order
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 10, 2016 10:05:41 AM
 */
public class Binary_Tree_Zigzag_Level_Order_Traversal_p103_sol1 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //Deque + BFS solution, use BFS to visit the tree level by level, use Deque to help get the forward and backward order
        
        //boundary check
        if(root == null) return new ArrayList<List<Integer>>();
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        //use level to decide where to poll from que
        int level = 0;
        
        while(!que.isEmpty()){
            int size = que.size();
            List<Integer> temp = new ArrayList<Integer>();
            
            for(int i = 0; i < size; i++){
                if(  (level&1) == 0  ){
                    //in even level, we can pollFirst/offerLast or pollLast/offerFirst
                    //Accordingly in odd level, we need pollLast/offerFirst or pollFirst/offerLast
                    //In either way, we require the write and read order in two levels is reversed 
                    //here we choose pollFirst/offerLast, of course we can choose pollLast/offFirst as well, but in that way we need to change
                	//read/write order in odd level to be pollFirst/offerLast
                    TreeNode curr = que.pollFirst();
                    temp.add(curr.val);
                    if(curr.left != null) que.offerLast(curr.left);
                    if(curr.right != null) que.offerLast(curr.right);
                }else{
                    //Accordingly, here we choose pollLast/offerFirst
                    TreeNode curr = que.pollLast();
                    temp.add(curr.val);
                    if(curr.right != null) que.offerFirst(curr.right);
                    if(curr.left != null) que.offerFirst(curr.left);
                }                
            }
            
            level++;
            result.add(temp);
        }
        
        return result;
    }
}
