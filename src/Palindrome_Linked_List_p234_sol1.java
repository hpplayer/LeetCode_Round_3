/**
 * LinkedList solution
 * 
 * We firstly use fast and slow pointer to find the mid point of the input list
 * Then we reverse second half
 * Finally, we compare the first half and second half to check the palindrome property
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 4, 2016 10:32:47 AM
 */
public class Palindrome_Linked_List_p234_sol1 {
    public boolean isPalindrome(ListNode head) {
        //LinkedList solution. We firstly split the input into half, then compare first half and second half
    	
    	//boundary check
    	if(head == null || head.next == null) return true;
    	
        //prev node used to record the prev node of slow, so that then we can cut two lists clearly
        ListNode prev = null;
        //fast pointer will reach the end
        ListNode fast = head;
        //slow pointer will point to the mid node
        ListNode slow = head;
        
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        
        //cut input list into half
        prev.next = null;
        //reverse second half, and get the new head node
        ListNode newHead = reverse(slow);
        
        //comparing two lists
        while(head != null && newHead != null){
            if(head.val != newHead.val) return false;
            head = head.next;
            newHead = newHead.next;
        }
        
        return true;
    }
    
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        
        return prev;
    }
}
