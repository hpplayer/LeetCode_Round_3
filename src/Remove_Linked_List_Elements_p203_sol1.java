/*
Remove Linked List Elements

Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
*/

/**
 * Pointer problem
 * 
 * We just use a prev pointer to point the last valid node, and use head to scan the list. If head node needs to be removed, we will
 * let prev.next = head.next. If head node needs to be kept, we just let prev.next = head.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 12:20:27 AM
 */
public class Remove_Linked_List_Elements_p203_sol1 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        while(head != null){
            ListNode next = head.next;
            if(head.val == val){
                prev.next = next;
                head = next;
            }else{
                prev.next = head;
                prev = head;
                head = next;
            }
        }
        
        return dummy.next;
    }
}
