/*
83. Remove Duplicates from Sorted List

Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

/**
 * Iterative ListNode solution
 * 
 * Basically we just iteratively check the next node of curr node, if it has same value with curr node, then
 * we will remove it and append next.next to curr node. Otherwise we keep it
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Sol1 is the iterative solution
 * Sol2 is the recursive solution
 * 
 * @author hpPlayer
 * @date May 17, 2016 9:12:27 PM
 */
public class Remove_Duplicates_from_Sorted_List_I_p83_sol1 {
    public ListNode deleteDuplicates(ListNode head) {
        //intuitive iterative solution. We will remove duplicate next node and  append non-duplicate ListNode to curr ListNode
        
        //currNode always points to the last Node of updated list
        ListNode curr = head;
        
        //if curr Node is tail node, then it doesn't have any next node, so our loop stops here
        while(curr != null && curr.next != null){
            if(curr.val == curr.next.val){
                //next node has same value, remove it and skip it
                curr.next = curr.next.next;
            }else{
                //next node has different value, append it and update currNode
                curr = curr.next;
            }
        }
        
        //head node will always be preserved
        return head;
    }
}
