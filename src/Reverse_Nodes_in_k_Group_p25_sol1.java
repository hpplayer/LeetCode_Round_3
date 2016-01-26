/*
25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

/**
 * Iterative solution
 * 
 * Here is the basic idea:
 * The key part is to track the last node before current k group and the first node after current k group.
 * Here we use an extra function to reverse nodes inside k group, which is almost same to standard reverse list function
 * But now we will attach the first node after current k group to be the tail node after the reversed list
 * After reverse, we will attach the reversed list after the last node before current k group. 
 * Then we iteratively do this until we reach the end
 * 
 * Time complexity would be O(n) 
 * Sol1 is iterative solution
 * Sols is recursive solution
 * 
 * @author hpPlayer
 * @date Jan 25, 2016 7:29:11 PM
 */
		
public class Reverse_Nodes_in_k_Group_p25_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		a.next = b;
		Reverse_Nodes_in_k_Group_p25_sol1 test = new Reverse_Nodes_in_k_Group_p25_sol1();
		ListNode result = test.reverseKGroup(a, 2);
		test.printList(result);
	}
	
    public ListNode reverseKGroup(ListNode head, int k) {
        //iterative solution, they key part is to get the node after k group and hold the node before k group
        
        //create a dummy head to hold the new head of list
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        dummy.next = head;
        
        while(head != null){
            //curr is the pointer in current group
            ListNode curr = head;
            
            //scan for k nodes, if not enough nodes left, we will return list as it is 
            for(int i = 0; i < k; i++){
                if(curr == null) return dummy.next;
                curr = curr.next;
            }
            
            //get the new head in current group. Now head is the first node in current group and curr is the first node in next group 
            prev.next = reverseInGroup(head, curr);
            
            //update prev node to be the last node of current group which becomes head now
            prev = head;
            //update head node to be the first node of next group
            head = curr;
        }
        
        return dummy.next;
    }
    
    public ListNode reverseInGroup(ListNode head, ListNode tail){
        //tail is the node after k group
        ListNode prev = tail;
        ListNode curr = head;
        
        //standard process to reverse nodes
        while(curr != tail){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
    
	public void printList(ListNode head){
		while(head != null){
			System.out.print(head.val);
			if(head.next != null) System.out.print("->");
			head = head.next;
		}
	}
}
