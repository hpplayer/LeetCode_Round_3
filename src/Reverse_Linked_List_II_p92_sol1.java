/*
92. Reverse Linked List II

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ¡Ü m ¡Ü n ¡Ü length of list.
*/

/**
 * ListNode solution
 * 
 * The algorithm is intuitive but writing a clean and neat solution is hard
 * 
 * We need to split the input list into three parts. 
 * part before reverse part, reverse part and part after reverse part
 * To make the solution a one-pass solution. We need do the reverse on-fly. 
 * To do that we create a sublist based on the reverse part. Then we make it connect to first and third parts
 * 
 * Sol1 is a clean and neat solution using above idea
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * It is hard to translate this solution to recursive solution with one pass, since we need to reverse only
 * a part of the input, we need to return new head and next node while we also need to pass parent and curr
 * node to next DFS. If we reverse the reverse part in one DFS, then it is more like an iterative version
 * So I did not put it here
 * 
 * @author hpPlayer
 * @date May 18, 2016 9:45:33 PM
 */
public class Reverse_Linked_List_II_p92_sol1 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //linkedlist solution. We use a sublist to create the reverse part
        
        //boundary check
        if(head == null || head.next == null) return head;
        
        //since first node may be reversed, we need a dummy node to track the new head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = dummy;
        
        //we also need the tail and head of sublist, so that we make it connect to other two parts
        ListNode subHead = null;
        ListNode subTail = null;
        
        //we just need to stop at the nth node
        for(int i = 1; i <= n; i++){
            ListNode next = curr.next;
            if(i < m){
                //nodes in first part, do nothing
                prev = curr;
            }else if(i > m){
                //nodes in reverse part, adding them to sublist
                curr.next = subHead;
                subHead = curr;
            }else{
                //mth node, we initialize it to be the head and tail node of reverse part
                //we put it here since only one node is in mth index. so we use this block the least
                subHead = subTail = curr;
            }
            //Notice: since we may have redefined the curr.next node, we need to move node by using next
            //node which we created early!!!!!!!!!!!!!!!!
            curr = next;
        }
        
        //connect first part and reverse part
        prev.next = subHead;
        //connect reverse part and third part(curr is now the head of third part)
        subTail.next = curr;
        
        return dummy.next;
    }
}
