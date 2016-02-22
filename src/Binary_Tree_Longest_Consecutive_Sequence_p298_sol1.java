/*
298. Binary Tree Longest Consecutive Sequence

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
*/

/**
 * pre-order traversal
 * 
 * Nothing special, we just use a global-variable to record the maxLen we get so far.
 * To make the function more simple, we will pass parent node to next layer, so we can compare and update count accordingly
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Sol2 lists a solution without using global variable
 * 
 * @author hpPlayer
 * @date Feb 21, 2016 3:20:59 PM
 */
public class Binary_Tree_Longest_Consecutive_Sequence_p298_sol1 {
    public int longestConsecutive(TreeNode root) {
        //as long as root is not null, we should at least have path with len 1
        if(root == null) return 0;
        //for non-null input, the maxLen should at least be 1
        maxLen = 1;
        DFS(root.left, root, 1);
        DFS(root.right, root, 1);
        return maxLen;
    }
    
    int maxLen; 
    
    public void DFS(TreeNode curr, TreeNode prev, int currLen){
        //boundary check
        if(curr == null) return;
        
        //if curr.val = prev.val + 1, then we add 1 to currLen otherwise, we end old count and start new count
        currLen = curr.val == prev.val + 1? currLen + 1 : 1;
        
        //we update global maxLen if possible
        maxLen = Math.max(currLen, maxLen);
        
        DFS(curr.left, curr, currLen);
        DFS(curr.right, curr, currLen);
    }
}
