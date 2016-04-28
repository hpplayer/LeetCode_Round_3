/*
19. Remove Nth Node From End of List

Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/

/**
 * Two pointer solution
 * 
 * To remove the target node in one pass, we need two pointers. First pointer will be n + 1 far away from second
 * pointer. Why? second pointer actually points to the node before the target node, so we can remove the target node.
 * Actually, we don't need pointer for target node, as removal does not require target node.
 * 
 * So the basic idea is initializing first pointer and second pointer one node away. Then we let first pointer
 * moves n steps before we start move second pointer. When first pointer reaches null node, second pointer will
 * just point to the node before target node. Then we can do the node removal and return new list
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Apr 27, 2016 10:10:00 PM
 */

public class Remove_Nth_Node_From_End_of_List_p19_sol1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //two pointer solution, we just need to use two pointers to achieve the goal
        //the first pointer will be N + 1 nodes faster than the second pointer. So when first pointer reaches
        //null, second pointer will just point to the node before the target node.
        //Notice: To remove Nth node, we need N-1th node, not Nth node itself
        
        //use a dummy node to handle boundary case (target node is head node)
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //we let first pointer points to dummy, second pointer points to head
        //then we let second pointer moves n steps before we start move first pointer, 
        //so they will have n + 1 gaps in between
        ListNode first = dummy;
        ListNode second = head;
        
        while(n-- > 0) second = second.next;
        
        //since the problem guarantees input is valid, we can safely move two pointers to get solution
        while(second != null){
            first = first.next;
            second = second.next;
        }
        
        //remove target node
        first.next = first.next.next;
        //return head of new list
        return dummy.next;
    }
}
