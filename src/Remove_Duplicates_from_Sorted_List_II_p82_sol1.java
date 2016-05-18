/*
82. Remove Duplicates from Sorted List II

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/

/**
 * Iterative ListNode solution
 * 
 * This problem is similar to Remove_Duplicates_from_Sorted_List_I_p83_sol1, but now we need to remove all
 * duplicate nodes. To achieve that, we firstly need a prev pointer that points to the last non-duplicate
 * node in the list. Then we use curr and next pointers to scan the rest of input nodes. If we found out
 * that next.val != curr.val, then we can connect prev with curr, since now we know curr is non-duplicate.
 * If we found out that next.val == curr.val, then we will use next pointer to skip over all nodes that have
 * same value with curr node. Then we directly set prev.next to the new next node as we have removed the 
 * duplicated curr nodes from input list. This is important so that we wouldn't get disconnected list.
 * 
 * Remark:
 * Set prev.next = new next node is important when we found curr node has duplicates. So that we can always
 * make sure list is connected and our loop can correctly include non-duplicate nodes and skip duplicate nodes
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol1 is iterative solution
 * Sol2 is recursive solution
 * 
 * @author hpPlayer
 * @date May 17, 2016 10:02:58 PM
 */
public class Remove_Duplicates_from_Sorted_List_II_p82_sol1 {
    public ListNode deleteDuplicates(ListNode head) {
        //iterative ListNode solution. Need a prev pointer to point the end of new list.
        //In Remove_Duplicates_from_Sorted_List_I_p83_sol1, we need check next nodes, and skip over those next nodes
        //that are duplicates, but in this solution, we need check curr and next nodes, and skip curr node if we found it has duplicates
        //We will do 2 things to input list 1) remove duplicate nodes 2) connect non-duplicate nodes
        
        //boundary check
        if(head == null || head.next == null) return head;
        
        //in case head node is a duplicate node and needs to be removed
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        
        ListNode curr = head;
        
        //we will skip over duplicate node in the inner loop
        //for each outter loop, curr.next is a new Node
        while(curr != null && curr.next != null){
            ListNode next = curr.next;
            
            if(curr.val != next.val){
                //different nodes, append curr to updated list
                prev.next = curr;
                //update prev
                prev = curr;
                //update curr and use next loop to check updated curr
                curr = next;
            }else{
                //duplicate nodes, we need skip curr and its duplicate next nodes
                while(next != null && next.val == curr.val) next = next.next;
                
                //update list to remove duplicated nodes
                //Notice: this is important since we are doing updates on input list!!!!!!!!!!!!!!!!
                prev.next = next;
                //update curr to new next node and use next loop for a new check
                curr = next;
            }
        }
        
        return dummy.next;
    }
}
