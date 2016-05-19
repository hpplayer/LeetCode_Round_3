/**
 * Standard recursive way to reverse linkedlist
 * 
 * Just remember to pass the newhead from tail to parent call, reverse the edge between curr and next node and remove the old edge from curr to 
 * next to avoid forming cycles
 * 
 * @author hpPlayer
 * @date Nov 6, 2015 3:46:48 PM
 */
public class Reverse_Linked_List_I_p206_sol2 {
    public ListNode reverseList(ListNode head) {
        //we shall return the tail node
        if(head == null || head.next == null) return head;
        
        ListNode newHead = reverseList(head.next);
        
        //reverse the edge between head and head.next
        //the edge between prev and head is reversed in the prev node
        head.next.next = head;
        
        //to avoid forming cycles, we will cut the old edge from head to head.next
        head.next = null;
        
        return newHead;
    }
}
