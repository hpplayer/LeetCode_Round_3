/*
156. Binary Tree Upside Down

Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty,
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
*/   


/**
 * Tree solution
 * 
 * This solution is a tree version of Reverse_Linked_List_p206_sol1. Instead of dealing with ListNode, now we need deal with TreeNode
 * To make our program consecutive, we will traversal through left nodes. We use two variables to record parent node and its right sibling
 * Then current Left node's new left node will be its right sibling, its new right node will be its parent
 * 
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol2 is a recursive version
 * 
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 1:47:32 PM
 */
public class Binary_Tree_Upside_Down_p156_sol1 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        //iterataive version. This solution is very similar to Reverse_Linked_List_p206_sol1.
        //For each loop, we will add children for one node. The next node will be curr.left node
        //curr's new left node will be its siblings i.e. curr's parents' right node
        //curr's new right node will be its parent node
        //after that we update parent and parent's right variable and go to curr's old left node
        
        TreeNode curr = root, prev = null, prevRight = null;
        
        while(curr != null){
            //firstly record old left node which will be our next node
            TreeNode left = curr.left;
            //then update left node to be new node (its siblings, the parent's right node)
            curr.left = prevRight;
            //update prevRight to be curr's right node
            prevRight = curr.right;
            //update right node to be new node(its parent)
            curr.right = prev;
            //update prev to be curr's node
            prev = curr;
            //go to next node i.e. curr's old left node
            curr = left;
        }
        //return prev i.e. last node
        return prev;
    }
}
