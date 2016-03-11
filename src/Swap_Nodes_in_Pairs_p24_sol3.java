/**
 * LinkedList solution
 * 
 * Recursive and forward solution, it needs an extra function that takes the prev node and curr node, therefore longer than sol1 
 * 
 * @author hpPlayer
 * @date Mar 10, 2016 3:59:57 PM
 */
public class Swap_Nodes_in_Pairs_p24_sol3 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        
        helper(dummy, head);
        
        return dummy.next;
    }
    
    public void helper(ListNode prev, ListNode curr){
        if(curr == null || curr.next == null){
            prev.next = curr;
            return;
        }
        
        ListNode next = curr.next.next;
        
        prev.next = curr.next;
        curr.next.next = curr;
        curr.next=  null;    
        
        helper(curr, next);
    }
}
