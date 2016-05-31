
public class Reorder_List_p143_sol1 {
	public static void main(String[] args){
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		a.next = b;
		b.next = c;
		c.next = d;
		Reorder_List_p143_sol1 test = new Reorder_List_p143_sol1();
		test.reorderList(a);
		test.printList(a);
	}
	public void printList(ListNode node){
		while(node != null){
			System.out.println(node.val + " ");
			node = node.next;
		}
	}
    public void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        
        prev.next = null;
        ListNode nextHead = null;
        
        while(slow != null){
            ListNode next = slow.next;
            slow.next = nextHead;
            nextHead = slow;
            slow = next;
        }
        
        ListNode pointerB= nextHead;
        ListNode pointerA = head;
        
        while(pointerA != null){
        	ListNode next = pointerA.next;
        	pointerA.next = pointerB;
        	pointerA = next;
        	
        	if(next == null) return;
        	
        	next = pointerB.next;
        	pointerB.next = pointerA;
        	pointerB = next;
        	
        }
    }
}
