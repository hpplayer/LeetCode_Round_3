/*
117. Populating Next Right Pointers in Each Node II

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

/**
 * Tree solution
 * 
 * Advanced version of Populating_Next_Right_Pointers_in_Each_Node_I_p116_sol1. Here we need use extra variables to record last valid child
 * node and the first node in next layer.
 * The main body is similar to Populating_Next_Right_Pointers_in_Each_Node_I_p116_sol1, we still scan current layer through next pointer
 * and connect next layer in current layer. But we need to check if current node has left or right child. We also need to record last valid
 * node in next layer. 
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 6:41:41 PM
 */


public class Populating_Next_Right_Pointers_in_Each_Node_II_p117_sol1 {
    public void connect(TreeLinkNode root) {
        //advanced version of Populating_Next_Right_Pointers_in_Each_Node_I_p116_sol1
        //The tree is not complete, therefore we need find the last valid node and the next valid node, then connect them
        //we also need to record the start node of next level, so we can jump to next level after we finish current level
        //Here we need two varibles to hold prev valid child node in next leveland the start Node of next layer. Of course we still need curr variable
        //to go through current layer
        
        //boundary check
        if(root == null) return;
        
        //startNode records the first node of next layer
        TreeLinkNode startNode = root;
        
        while(startNode != null){
            //curr node will go through current layer with next pointer
            TreeLinkNode curr = startNode;
            //rest start Node
            startNode = null;
            //prev node record the last valid node in next layer
            TreeLinkNode prev = null;
            
            //we continue as long as we have nodes in current layer
            while(curr != null){
                //firstly check left child
                if(curr.left != null){
                    //connect two nodes if prev is not null
                    if(prev != null) prev.next = curr.left;
                    //update prev 
                    prev = curr.left;
                    //update startNode if it is firstNode in next layer
                    if(startNode == null) startNode = curr.left;    
                }
                
                //do the same to right child
                if(curr.right != null){
                    //connect two nodes if prev is not null
                    if(prev != null) prev.next = curr.right;
                    //update prev 
                    prev = curr.right;
                    //update startNode if it is firstNode in next layer
                    if(startNode == null) startNode = curr.right;    
                }
                //go to next node through next pointer
                curr = curr.next;
            }
            
            //finish current layer, go to next layer
        }
    }
}
