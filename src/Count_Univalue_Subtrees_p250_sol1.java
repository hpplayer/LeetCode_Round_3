/*
250. Count Univalue Subtrees

Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
*/

/**
 * Post-order traversal
 * 
 * We visit the tree through post-order way, then update counter based on left subtree, right subtree and curr val
 * We will pass an expected value to each recursion, so we can compare curr val with parent val
 * So in fact, first part of each recursion is doing comparison and update rooted at curr node, the second half
 * of each recursion is comparing curr node with its parent value
 * 
 * Time complexity: O(N) each node will be visited once
 * Space complexity: O(N) in case input tree is a linkedList-like tree
 * 
 * Sol1 is recursive solution
 * Sol2 is iterative solution with similar idea
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 1:04:53 PM
 */
public class Count_Univalue_Subtrees_p250_sol1 {
    public int countUnivalSubtrees(TreeNode root) {
        //post-order traversal, we update counter based on left subtree, right subtree and curr node
        count = 0;
        
        //the initial expectedVal can be an arbitrary number since we dont have parent node for root node
        isUnivalSubtrees(root, -1);
        
        return count;
    }
    
    int count;
    
    public boolean isUnivalSubtrees(TreeNode root, int expectedVal){
        //boundary case
        if(root == null) return true;
        
        //if left subtree or right subtree does not contain same value, we will return false for curr subtree
        //we use | here to force java runs two execution and avoid shortcut
        if( !isUnivalSubtrees(root.left, root.val) | !isUnivalSubtrees(root.right, root.val) ){
            return false;
        }
        
        //all nodes in left subtree and right subtree have same value with curr node, therefore we can include
        //curr node into a uniValue subtree 
        count++;
        
        //then compare curr value with expected val to determine if we need to include parent node to this uniValue subtree
        return root.val == expectedVal;
    }
}
