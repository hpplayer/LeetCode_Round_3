/*
369. Plus One Linked List

Given a non-negative number represented as a singly linked list of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4
*/

/**
 * Recursive solution
 * 
 * To add one in reverse way, we just need to visit the list in recursive way. 
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Jun 27, 2016 10:54:11 PM
 */
public class Plus_One_Linked_List_p369_sol1 {
    public ListNode plusOne(ListNode head) {
        if( DFS(head) == 0){
            //we have consumed 1, return head directly
            return head;
        }else{
            //we need add 1 ahead
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
    }
    
    public int DFS(ListNode head){
        //reach tail, start adding 1
        if(head == null) return 1;
        
        int carry = DFS(head.next);
        
        if(carry == 0) return 0;
        
        int val = head.val + carry;
        head.val = val%10;
        
        //use val/10 to return new carry
        return val/10;
    }
}
