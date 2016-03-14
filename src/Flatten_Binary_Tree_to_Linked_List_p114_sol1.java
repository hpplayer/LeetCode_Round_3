/*
114. Flatten Binary Tree to Linked List

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
*/

/**
 * Observation + preorder traversal
 * 
 * We observe that flatten here means we will insert left subtree into the interval between curr node and its right
 * subtree.
 * So here is the basic idea:
 * We will iteratively visit the tree and start to flat the tree when we found there exists a left subtree.
 * For the left subtree, we need to iteratively find its rightmost node, and let it connect with curr.right
 * Then we set curr.right to curr.left, and set curr.left = null
 * 
 * Time complexity: O(N), each node will be visited once
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 13, 2016 7:40:29 PM
 */
public class Flatten_Binary_Tree_to_Linked_List_p114_sol1 {
    public void flatten(TreeNode root) {
        //observation + preorder traversal solution. We will iteratively visit the tree and move left subtree into
        //the interval between curr node and its right subtree
        
        while(root != null){
            if(root.left != null){
                //found a left subtree
                
                //to insert left subtree between root and right subtree, we need find the rightmost node in left subtree
                TreeNode temp = root.left;
                while(temp.right != null) temp = temp.right;
                
                //tail part: connect root.right to the rightmost node found above
                temp.right = root.right;
                
                //head part: move root.left to root.right
                root.right = root.left;
                //remove old root.left
                root.left = null;
            }
            
            //forward with right edge and search next left subtree
            root = root.right;
        }
        
    }
}
