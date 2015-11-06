/*
Reverse Linked List

Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

/**
 * LinkedList problem
 * 
 * Just a standard way to reverse the list
 * 
 * Both sol1 and sol2 costs O(n) time and O(1) space
 * 
 * Sol1 is the iterative way
 * Sol2 is the recursive way
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 3:44:01 PM
 */

public class Reverse_Linked_List_p206_sol1 {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        
        return prev;
    }
}
