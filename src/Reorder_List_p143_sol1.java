/*
143. Reorder List

Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/

/**
 * LinkedList solution
 * 
 * Here is the basic idea. 
 * 1. We firstly use the fast and slow pointer to find the split node. To make our operation more easier, we will also
 * record the prev node of the split node, then make it.next = null to cut two lists
 * 2. Then we reverse the second half of list and track its new head. 
 * 3. merge first and second half sublist through zig-zag way. We will stop once first half sublist is over.
 * Why stop once first half list is over?
 * Second half sublist will always longer than first half sublist, So our final loop is based on the first half sublist
 * to handle boundary condition. In case input size is odd, we have to add two consecutive nodes in second half
 * to the reordered list in the last step. Based on first half list would make this operation possible.
 * EX: 1, 2,3 after split we will get [1] and [3,2]. We will directly attach [3,2] to [1]
 *  
 * Time complexity: O(N)
 * Find split node: O(N)
 * Reverse second half: O(N)
 * Merge two sublists: O(N)
 *  
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 30, 2016 10:35:21 PM
 */
public class Reorder_List_p143_sol1 {
    public void reorderList(ListNode head) {
        //LinkedList solution. firstly find the split node, then reverse second half sublist, then zigzag two half sublists

    	//boundary check
    	if(head == null || head.next == null) return;
    	
        //firstly find the split node use slow and fast pointer method
        ListNode slow = head;
        ListNode fast = head;
        //we need record the node before split node, so that we can cut them cleanly
        ListNode prev = null;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        
        //cut two sublists
        prev.next = null;
        
        //Secondly reverse the second half list and track its new head
        ListNode newHeadInSecondSubLsit = null;
        
        while(slow != null){
            ListNode next = slow.next;
            slow.next = newHeadInSecondSubLsit;
            newHeadInSecondSubLsit = slow;
            slow = next;
        }
        
        //thirdly merge first and second sublists in zig-zag way
        //since first half list is always smaller and end faster, we will merge sublists based on first half
        ListNode firstPointer = head;
        ListNode secondPointer = newHeadInSecondSubLsit;
        while(firstPointer != null){
            //make node from first half point to node from second half
            ListNode next = firstPointer.next;
            firstPointer.next = secondPointer;
            firstPointer = next;
            
            //if first half is already over, return directly
            if(firstPointer == null) return;
            
            //make node from second half point to node from first half
            next = secondPointer.next;
            secondPointer.next = firstPointer;
            secondPointer = next;
        }

    }
}
