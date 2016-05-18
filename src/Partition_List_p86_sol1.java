/*
86. Partition List

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
*/

/**
 * LinkedList solution
 * 
 * We use four dummy nodes to help us partition the input list
 * Two dummy nodes are heads of smaller and larger lists
 * Two dummy nodes are tails of smaller and larger lists
 * 
 * We will keep updating the tail nodes.
 * After we have scanned the input list, we will append Largerhead.next to smallerTail node
 * And set largerTail.next = null (this is important so that we can make sure two lists are clean and 
 * does not have unexpected extra nodes from input list)
 * Then return smallerHead.next
 * 
 * All four dummy nodes are used
 * 
 * Time complexity: O(N)
 * Space complexity: O(1) (if not considering the input list size)
 * 
 * @author hpPlayer
 * @date May 17, 2016 11:01:33 PM
 */
public class Partition_List_p86_sol1 {
    public ListNode partition(ListNode head, int x) {
        //LinkedList solution. Use four dummy nodes to help maintain and control two lists(smaller and larger)
        ListNode smallerHead = new ListNode(-1);
        ListNode largerHead = new ListNode(-1);
        //intially we don't have nodes in two lists, so tail and head nodes are same 
        ListNode smallerTail = smallerHead;
        ListNode largerTail = largerHead;
        
        while(head != null){
            if(head.val < x){
                //append smaller node to smaller list
                smallerTail.next = head;
                //update tail node in smaller list
                smallerTail = head;
            }else{
                //append larger node to larger list
                largerTail.next = head;
                //update tail node in larger list
                largerTail = head;                
            }
            head = head.next;
        }
        
        //merge two lists
        smallerTail.next = largerHead.next;
        //set tail.next of larger list to be null, so our two lists are clean and wouldn't have unexpected nodes from input
        largerTail.next = null;
        
        return smallerHead.next;
    }
}
