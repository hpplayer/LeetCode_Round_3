/*
141. Linked List Cycle

Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Two pointer solutions
 * 
 * We use a fast pointer and a slow pointer to traverse the list. Fast pointer will be 2X times faster than 
 * slow pointer. If there is a loop, then they will finally meet in the loop. As each movement will reduce the
 * distance between them. We can use 3X, 4X speed difference as well, as long as this speed difference is kept,
 * and we have a loop in the loop, those two pointers will finally meet
 * 
 * Time complexity: 
 * O(N), 
 * without loop, O(N) is intuitive,
 * with loop: distance = len_before_entering_loop + len_fast_pointer_lead_in_loop. Since we get a loop, 
 * len_fast_pointer_lead_in_loop is at most loop len, loop len + len_before_entering_loop <= N, since speed 
 * difference is O(1) in our solution. it takes distance/speed_difference, which is O(N) loops for faster pointer
 * catch up slower pointer
 * 
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Apr 27, 2016 10:39:47 PM
 */
public class Linked_List_Cycle_I_p141_sol1 {
    public boolean hasCycle(ListNode head) {
        //two pointer solution. We let one pointer moves 2X faster than the other pointer. If there is a loop,
        //then they will finally meet  
        
        ListNode slow = head;
        ListNode fast = head;
        
        //to make sure fast.next.next is valid, we need check if fast and fast.next is not null
        while(fast != null && fast.next != null){
            //we let two pointers move first then compare their value, so we won't stuck in first loop
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        
        return false;
    }
}
