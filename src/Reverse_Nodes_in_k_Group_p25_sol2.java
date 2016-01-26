/**
 * Recursive solution
 * 
 * here is the basic idea:
 * Each recursion is working a group of k nodes. We firstly try to find the updated first node in next group, we call it newHead
 * Then reverse current group. Then we attach newHead to the updated last node in current group, which was the first node
 * Then we return the updated first node in current group to last recursion. So that the whole list will be reversed by k nodes per group
 * 
 * Time complexity would be O(n) 
 * 
 * @author hpPlayer
 * @date Jan 25, 2016 7:43:14 PM
 */
public class Reverse_Nodes_in_k_Group_p25_sol2 {
    public ListNode reverseKGroup(ListNode head, int k) {
        //recursive solution, each recursion will handle a group of k nodes
        //the key idea here is looking for the first node after current group
        
        //boundary check
        if(head == null || k <= 1) return head;
        
        //try to get the first node after current group
        ListNode curr = head;
        for(int i = 0; i < k; i++){
            if(curr == null ) return head;
            curr = curr.next;
        }
        //curr is the old first node in next group, now we try to get the new first node
        ListNode newHead = reverseKGroup(curr, k);
        
        //begin reverse current group
        
        ListNode prev = newHead;
        curr = head;
        
        for(int i = 0; i < k; i++){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
