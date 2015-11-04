/*
Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.
*/

/**
 * LinkedList problem
 * 
 * This is a very fundamental problem in merging sorted lists. We just look at the head node in each lists and append the smaller 
 * one to last node ( we can prev here) of result lists
 * 
 * Time complexity is O(n1 + n2)
 * Space complexity is O(1)
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 2:11:00 AM
 */
public class Merge_Two_Sorted_Lists_p21_sol1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                prev.next = l1;
                l1 = l1.next;
                prev = prev.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
                prev = prev.next;
            }
        }
        
        if(l1 != null) prev.next = l1;
        if(l2 != null) prev.next = l2;
        
        return dummy.next;
    }
}
