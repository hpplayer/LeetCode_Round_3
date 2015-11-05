/*
Delete Node in a Linked List

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
the linked list should become 1 -> 2 -> 4 after calling your function.
*/


/**
 * Brainstorming solution
 * 
 * Given only access to the node that needs to be removed, we just copy information of next node to current node, then delete next
 * node. This operation will give us same effect of deleting current node. 
 * 
 * Time complexity:O(1)
 * Space complexity:O(1)
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 12:29:34 AM
 */
public class Delete_Node_in_a_Linked_List_p237_sol1 {
    public void deleteNode(ListNode node) {
        //tail node detected
        if(node.next == null) return;
        
        //otherwise, we just copy next code and delete next node,
        //so we can achieve the same effect of deleting current node
        
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
