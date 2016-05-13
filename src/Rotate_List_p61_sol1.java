/*
61. Rotate List 

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/

/**
 * Cyclic list solution
 * 
 * We convert input list into a Cyclic list, then find the new cut point to break the cycle and get the
 * rotated list
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 13, 2016 12:05:59 AM
 */
public class Rotate_List_p61_sol1 {
    public ListNode rotateRight(ListNode head, int k) {
        //Cyclic list solution. We make the input become a Cyclic list, then find the cut point.
        
        //boundary check
        if(head == null || k == 0) return head;
        
        ListNode curr = head;
        //we want curr node stops at tail node but we only counts node that has next node,
        //to include tail node in size we initialize size with 1 
        int size = 1;
        
        while(curr.next != null){
            curr = curr.next;
            size++;
        }
        
        //update k to avoid unnecessary loops 
        k %= size;
        
        //make input list to be a cyclic list
        curr.next = head;
        
        //find the cut point(size-k), we want locate the index before cut point(-1)
        int index = size - k - 1;
        
        while(index > 0){
            head = head.next;
            index--;
        }
        
        //new head
        ListNode newHead = head.next;
        //break the Cyclic loop and get the rotated list
        head.next = null;
        
        return newHead;
    }
}
