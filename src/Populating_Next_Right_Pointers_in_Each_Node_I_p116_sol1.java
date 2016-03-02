/*
116. Populating Next Right Pointers in Each Node

Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
*/

/**
 * Tree solution
 * 
 * We can use BFS to solve this problem, but that is not hard or cool. We can make use of the next pointer to connect nodes in next layer
 * We have a nested loop, outer loop will go through each layer until we reach leaf layer where we don't have next layer.
 * inner loop, will go through current layer with next pointers and connect nodes in next layer. Thats why we need stop outer loop in leaf layer
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 5:36:17 PM
 */


public class Populating_Next_Right_Pointers_in_Each_Node_I_p116_sol1 {
    public void connect(TreeLinkNode root) {
        //Tree solution, we can make use parent layer's next pointer, to connect children layers' next pointer
        
        //boundary check
        if(root == null) return;
        
        //otherwise we will stop the loop, when we reach the leaf layer which does not have children layer
        while(root.left != null){
            TreeLinkNode curr = root;
            
            //current layer should already be connected when we visiting parent's layer, therefore we can use next pointer in current layer
            //to connect nodes in next layer
            while(curr != null){
                //perfect tree, we must have two children
                curr.left.next = curr.right;
                curr.right.next = curr.next == null? null : curr.next.left;
                curr = curr.next;
            }
            //we have done current layer, lets go to next layer
            root = root.left;
        }
        
    }
}
